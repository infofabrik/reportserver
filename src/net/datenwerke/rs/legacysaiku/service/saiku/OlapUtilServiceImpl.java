package net.datenwerke.rs.legacysaiku.service.saiku;

import static java.util.stream.Collectors.toList;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.inject.Inject;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

import org.legacysaiku.datasources.connection.ISaikuConnection;
import org.olap4j.OlapConnection;
import org.olap4j.OlapException;
import org.olap4j.metadata.Cube;
import org.olap4j.metadata.Dimension;
import org.olap4j.metadata.Hierarchy;
import org.olap4j.metadata.Level;
import org.olap4j.metadata.Measure;
import org.olap4j.metadata.Member;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import com.google.inject.Provider;

import mondrian3.olap.CacheControl;
import mondrian3.olap4j.MondrianOlap4jDriver;
import mondrian3.rolap.RolapConnection;
import mondrian3.rolap.RolapSchema;
import net.datenwerke.dbpool.JdbcService;
import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.adminutils.client.datasourcetester.ConnectionTestFailedException;
import net.datenwerke.rs.base.service.datasources.table.impl.utils.JasperStyleParameterParser;
import net.datenwerke.rs.core.service.datasourcemanager.entities.DatasourceContainer;
import net.datenwerke.rs.core.service.reportmanager.entities.reports.Report;
import net.datenwerke.rs.core.service.reportmanager.parameters.ParameterSet;
import net.datenwerke.rs.core.service.reportmanager.parameters.ParameterSetFactory;
import net.datenwerke.rs.core.service.reportmanager.parameters.ParameterValue;
import net.datenwerke.rs.core.service.reportmanager.parameters.ParameterValueImpl;
import net.datenwerke.rs.legacysaiku.service.saiku.reportengine.hooks.OlapConnectionHook;
import net.datenwerke.rs.saiku.service.datasource.MondrianDatasource;
import net.datenwerke.rs.saiku.service.datasource.MondrianDatasourceConfig;
import net.datenwerke.rs.saiku.service.hooks.OlapCubeCacheHook;
import net.datenwerke.rs.saiku.service.saiku.entities.SaikuReport;
import net.datenwerke.security.service.authenticator.AuthenticatorService;

public class OlapUtilServiceImpl implements OlapUtilService {

   private static final String USERNAME_KEY = "jdbcUser";
   private static final String PASSWORD_KEY = "jdbcPassword";

   private HookHandlerService hookHandlerService;
   private JdbcService jdbcService;

   private final Provider<AuthenticatorService> authenticatorService;
   private final Provider<ParameterSetFactory> parameterSetFactory;

   @Inject
   public OlapUtilServiceImpl(HookHandlerService hookHandlerService, JdbcService jdbcService,
         Provider<ParameterSetFactory> parameterSetFactory, Provider<AuthenticatorService> authenticatorService) {
      this.hookHandlerService = hookHandlerService;
      this.jdbcService = jdbcService;
      this.parameterSetFactory = parameterSetFactory;
      this.authenticatorService = authenticatorService;
   }

   @Override
   public OlapConnection getOlapConnection(SaikuReport report)
         throws ClassNotFoundException, IOException, SQLException {
      DatasourceContainer dsc = report.getDatasourceContainer();
      MondrianDatasource datasource = (MondrianDatasource) dsc.getDatasource();

      return getOlapConnection(datasource, report, false);
   }

   @Override
   public Cube getCube(final SaikuReport report) throws ClassNotFoundException, IOException, SQLException {
      for (OlapCubeCacheHook cache : hookHandlerService.getHookers(OlapCubeCacheHook.class)) {
         Cube cube = cache.getCubeFromCache(report);
         if (null != cube)
            return cube;
         break; // only one cache
      }

      DatasourceContainer dsc = report.getDatasourceContainer();
      MondrianDatasource datasource = (MondrianDatasource) dsc.getDatasource();
      MondrianDatasourceConfig datasourceConfig = (MondrianDatasourceConfig) dsc.getDatasourceConfig();

      final OlapConnection olapConnection = getOlapConnection(datasource, report, false);

      final Cube cube = olapConnection.getOlapSchema().getCubes().get(datasourceConfig.getCubeName());

      hookHandlerService.getHookers(OlapCubeCacheHook.class).stream().findAny() // only one cache
            .ifPresent(cache -> cache.putCubeInCache(report, cube, olapConnection));

      return cube;
   }

   @Override
   public List<String> getCubes(MondrianDatasource datasource, SaikuReport saikuReport)
         throws ClassNotFoundException, IOException, SQLException {
      OlapConnection olapConnection = getOlapConnection(datasource, saikuReport, false);

      return olapConnection.getOlapSchema().getCubes().stream().map(Cube::getName).collect(toList());
   }

   @Override
   public List<Dimension> getAllDimensions(Cube cube) {
      return cube.getDimensions().stream()
            .filter(dim -> !dim.getName().equals("Measures") && !dim.getUniqueName().equals("[Measures]"))
            .collect(toList());
   }

   @Override
   public List<Member> getAllMeasures(Cube cube) throws OlapException {
      List<Member> measures = new ArrayList<Member>();
      for (Measure measure : cube.getMeasures()) {
         if (measure.isVisible()) {
            measures.add(measure);
         }
      }
      if (measures.size() == 0) {
         Hierarchy hierarchy = cube.getDimensions().get("Measures").getDefaultHierarchy();
         measures = hierarchy.getRootMembers();
      }

      return measures;
   }

   @Override
   public OlapConnection getOlapConnection(MondrianDatasource mondrianDatasource, SaikuReport report,
         boolean resetParameters) throws IOException, ClassNotFoundException, SQLException {
      Properties props = new Properties();
      props.load(new StringReader(mondrianDatasource.getProperties()));

      if (null != mondrianDatasource.getMondrianSchema()) {
         /* init parameter set */
         ParameterSet parameterSet = parameterSetFactory.get().create(authenticatorService.get().getCurrentUser(),
               report);
         if (null != report)
            parameterSet.addAll(report.getParameterInstances());

         Map<String, ParameterValue> pMap = new HashMap<>();
         if (null != parameterSet)
            pMap = parameterSet.getParameterMap();

         String mondrianSchema = mondrianDatasource.getMondrianSchema();
         String replacedMondrianSchema = replaceParametersInQuery(parameterSet, pMap, mondrianSchema, resetParameters);
         props.setProperty("CatalogContent", replacedMondrianSchema);
      }

      /* use fields if no property defined */
      if (!props.containsKey(USERNAME_KEY) && null != mondrianDatasource.getUsername()) {
         props.setProperty(USERNAME_KEY, mondrianDatasource.getUsername());
      }
      if (!props.containsKey(PASSWORD_KEY) && null != mondrianDatasource.getPassword()) {
         props.setProperty(PASSWORD_KEY, mondrianDatasource.getPassword());
      }
      if (!props.containsKey(ISaikuConnection.URL_KEY) && null != mondrianDatasource.getUrl()) {
         props.setProperty(ISaikuConnection.URL_KEY, jdbcService.adaptJdbcUrl(mondrianDatasource.getUrl()));
      }

      String url = props.getProperty(ISaikuConnection.URL_KEY);

      if (url.length() > 0 && url.charAt(url.length() - 1) != ';') {
         url += ";";
      }

      MondrianOlap4jDriver mondrianOlap4jDriver = new MondrianOlap4jDriver();
      OlapConnection connection = (OlapConnection) mondrianOlap4jDriver.connect(url, props);

      for (OlapConnectionHook och : hookHandlerService.getHookers(OlapConnectionHook.class)) {
         connection = och.postprocessConnection(connection);
      }

      return connection.unwrap(OlapConnection.class);
   }

   @Override
   public List<Member> getAllMembers(Cube cube, String dimensionName, String hierarchyName, String levelName)
         throws OlapException {
      Dimension dim = cube.getDimensions().get(dimensionName);
      if (dim != null) {
         Hierarchy h = dim.getHierarchies().get(hierarchyName);
         if (h == null) {
            for (Hierarchy hlist : dim.getHierarchies()) {
               if (hlist.getUniqueName().equals(hierarchyName) || hlist.getName().equals(hierarchyName)) {
                  h = hlist;
               }
            }
         }

         if (h != null) {
            Level l = h.getLevels().get(levelName);
            if (l == null) {
               for (Level lvl : h.getLevels()) {
                  if (lvl.getUniqueName().equals(levelName) || lvl.getName().equals(levelName)) {
                     return lvl.getMembers();
                  }
               }
            } else {
               return l.getMembers();
            }

         }
      }
      return new ArrayList<Member>();
   }

   @Override
   public String replaceParametersInQuery(ParameterSet parameterSet, Map<String, ParameterValue> pMap,
         String mondrianSchema, boolean resetParameters) {
      String replacedSchema = null;

      try {
         DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
         factory.setNamespaceAware(true);
         DocumentBuilder builder = factory.newDocumentBuilder();
         Document doc = null;
         if (null == mondrianSchema || "".equals(mondrianSchema.trim()))
            return "";

         InputSource is = new InputSource(new StringReader(mondrianSchema));
         doc = builder.parse(is);

         XPathFactory xpathFactory = XPathFactory.newInstance();
         XPath xpath = xpathFactory.newXPath();
         XPathExpression expr = xpath.compile("//SQL");
         NodeList nodes = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);
         for (int i = 0; i < nodes.getLength(); i++) {
            Node node = nodes.item(i);
            NodeList childNodes = node.getChildNodes();
            for (int j = 0; j < childNodes.getLength(); j++) {
               Node childNode = childNodes.item(j);
               if (childNode.getNodeType() == Node.TEXT_NODE || childNode.getNodeType() == Node.CDATA_SECTION_NODE) {
                  String textContent = childNode.getTextContent();
                  if (null != textContent) {
                     if (!"".equals(textContent.trim())) {

                        String replacedQuery = null;
                        if (resetParameters) {
                           /* We set all parameter values with null, since these are not available here. */
                           /* First, we parse the parameters from the query. */
                           JasperStyleParameterParser parser = new JasperStyleParameterParser(textContent, pMap,
                                 parameterSet, true);
                           List<String> queryParameters = parser.getCollectedParameterNames();
                           for (final String parameter : queryParameters) {
                              parameterSet.addVariable(parameter, null);
                              pMap.put(parameter, new ParameterValueImpl(parameter, null, Object.class));
                           }
                           /* Now we evaluate the parsed parameters to NULL */
                           parser = new JasperStyleParameterParser(textContent, pMap, parameterSet);
                           replacedQuery = parser.createQueryAsString();
                        } else {
                           JasperStyleParameterParser parser = new JasperStyleParameterParser(textContent, pMap,
                                 parameterSet);
                           replacedQuery = parser.createQueryAsString();

                        }

                        childNode.setTextContent(replacedQuery);
                     }
                  }
               }
            }

         }

         DOMSource domSource = new DOMSource(doc);
         StringWriter writer = new StringWriter();
         StreamResult result = new StreamResult(writer);
         TransformerFactory tf = TransformerFactory.newInstance();
         Transformer transformer = tf.newTransformer();
         transformer.transform(domSource, result);
         replacedSchema = writer.toString();
      } catch (Exception e) {
         throw new RuntimeException("Error in Mondrian schema parameter replacement", e);
      }

      return replacedSchema;
   }

   @Override
   public List<Level> getAllLevels(Cube cube, String dimensionName, String hierarchyName) throws OlapException {
      Dimension dim = cube.getDimensions().get(dimensionName);
      if (dim != null) {
         Hierarchy h = dim.getHierarchies().get(hierarchyName);
         if (h == null) {
            for (Hierarchy hlist : dim.getHierarchies()) {
               if (hlist.getUniqueName().equals(hierarchyName) || hlist.getName().equals(hierarchyName)) {
                  h = hlist;
               }
            }
         }

         if (h != null) {
            return h.getLevels();
         }
      }

      return new ArrayList<Level>();
   }

   @Override
   public List<Member> getHierarchyRootMembers(Cube cube, String hierarchyName) throws OlapException {
      Hierarchy h = cube.getHierarchies().get(hierarchyName);
      if (h == null) {
         for (Hierarchy hlist : cube.getHierarchies()) {
            if (hlist.getUniqueName().equals(hierarchyName) || hlist.getName().equals(hierarchyName)) {
               h = hlist;
            }
         }
      }
      if (h != null) {
         return h.getRootMembers();
      }

      return Collections.EMPTY_LIST;
   }

   @Override
   public void flushCache(Report report) {
      if (report instanceof SaikuReport) {

         try {
            final OlapConnection con = getOlapConnection((SaikuReport) report);
            final RolapConnection rolapConnection = con.unwrap(mondrian3.rolap.RolapConnection.class);
            final RolapSchema rolapSchema = rolapConnection.getSchema();
            final CacheControl cacheControl = rolapConnection.getCacheControl(null);

            final Cube reportCube = getCube((SaikuReport) report);
            Arrays.stream(rolapSchema.getCubes()).filter(cube -> reportCube.getName().equals(cube.getName()))
                  .map(mondrian3.olap.Cube::getSchema).findAny().ifPresent(cacheControl::flushSchema);

         } catch (Exception e) {
            throw new RuntimeException(e);
         }
      } else {
         throw new IllegalArgumentException("Mondrian cache flush on illegal report type: " + report.getClass());
      }

   }

   @Override
   public void flushCache(MondrianDatasource datasource) {
      try {
         final OlapConnection con = getOlapConnection(datasource, null, true);
         final RolapConnection rolapConnection = con.unwrap(mondrian3.rolap.RolapConnection.class);
         final RolapSchema rolapSchema = rolapConnection.getSchema();
         final CacheControl cacheControl = rolapConnection.getCacheControl(null);

         Arrays.stream(rolapSchema.getCubes()).map(mondrian3.olap.Cube::getSchema).forEach(cacheControl::flushSchema);

      } catch (Exception e) {
         throw new RuntimeException(e);
      }
   }

   @Override
   public boolean testConnection(MondrianDatasource datasource) throws ConnectionTestFailedException {
      try {
         final OlapConnection con = getOlapConnection(datasource, null, true);
         con.unwrap(mondrian3.rolap.RolapConnection.class).getSchema();

      } catch (Exception e) {
         throw new ConnectionTestFailedException(
               "Connection test failed in datasource \"" + datasource.getName() + "\": " + e.getMessage(), e);
      }

      return true;
   }
}
