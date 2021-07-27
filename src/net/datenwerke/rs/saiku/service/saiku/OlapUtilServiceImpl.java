package net.datenwerke.rs.saiku.service.saiku;

import static java.util.stream.Collectors.toList;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
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

import org.apache.commons.lang.StringUtils;
import org.olap4j.OlapConnection;
import org.olap4j.OlapException;
import org.olap4j.mdx.IdentifierNode;
import org.olap4j.metadata.Cube;
import org.olap4j.metadata.Dimension;
import org.olap4j.metadata.Hierarchy;
import org.olap4j.metadata.Level;
import org.olap4j.metadata.Measure;
import org.olap4j.metadata.Member;
import org.saiku.datasources.connection.ISaikuConnection;
import org.saiku.olap.dto.SaikuDimension;
import org.saiku.olap.dto.SaikuHierarchy;
import org.saiku.olap.dto.SaikuMember;
import org.saiku.olap.dto.SimpleCubeElement;
import org.saiku.olap.util.ObjectUtil;
import org.saiku.olap.util.exception.SaikuOlapException;
import org.saiku.service.util.MondrianDictionary;
import org.saiku.service.util.exception.SaikuServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import com.google.inject.Provider;

import mondrian.olap.CacheControl;
import mondrian.olap4j.SaikuMondrianHelper;
import mondrian.rolap.RolapConnection;
import mondrian.rolap.RolapSchema;
import mondrian.rolap.RolapSchema.MondrianSchemaException;
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
import net.datenwerke.rs.saiku.service.datasource.MondrianDatasource;
import net.datenwerke.rs.saiku.service.datasource.MondrianDatasourceConfig;
import net.datenwerke.rs.saiku.service.hooks.OlapCubeCacheHook;
import net.datenwerke.rs.saiku.service.saiku.entities.SaikuReport;
import net.datenwerke.rs.saiku.service.saiku.reportengine.hooks.OlapConnectionHook;
import net.datenwerke.rs.saiku.service.saiku.reportengine.hooks.OlapConnectionPropertiesHook;
import net.datenwerke.security.service.authenticator.AuthenticatorService;

public class OlapUtilServiceImpl implements OlapUtilService {

   private static final Logger log = LoggerFactory.getLogger(OlapUtilServiceImpl.class);

   private static final String XMLA_DRIVER = "org.olap4j.driver.xmla.XmlaOlap4jDriver";

   private static final String MONDRIAN_USERNAME_KEY = "jdbcUser";
   private static final String MONDRIAN_PASSWORD_KEY = "jdbcPassword";

   private static final String XMLA_USERNAME_KEY = "User";
   private static final String XMLA_PASSWORD_KEY = "Password";

   private final HookHandlerService hookHandlerService;
   private final JdbcService jdbcService;
   private final Provider<AuthenticatorService> authenticatorService;

   private final Provider<ParameterSetFactory> parameterSetFactory;

   private final Provider<net.datenwerke.rs.legacysaiku.service.saiku.OlapUtilService> oldOlapUtilService;

   @Inject
   public OlapUtilServiceImpl(
         HookHandlerService hookHandlerService, 
         JdbcService jdbcService,
         Provider<ParameterSetFactory> parameterSetFactory, 
         Provider<AuthenticatorService> authenticatorService,
         Provider<net.datenwerke.rs.legacysaiku.service.saiku.OlapUtilService> oldOlapUtilService
         ) {
      this.hookHandlerService = hookHandlerService;
      this.jdbcService = jdbcService;
      this.parameterSetFactory = parameterSetFactory;
      this.authenticatorService = authenticatorService;
      this.oldOlapUtilService = oldOlapUtilService;
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
      Optional<Cube> cachedCube = hookHandlerService.getHookers(OlapCubeCacheHook.class)
         .stream()
         .map(cache -> cache.getCubeFromCache(report))
         .findAny(); // only one cache
      if (cachedCube.isPresent())
         return cachedCube.get();
      
      DatasourceContainer dsc = report.getDatasourceContainer();
      MondrianDatasource datasource = (MondrianDatasource) dsc.getDatasource();
      MondrianDatasourceConfig datasourceConfig = (MondrianDatasourceConfig) dsc.getDatasourceConfig();

      final OlapConnection olapConnection = getOlapConnection(datasource, report, false);

      final Cube cube = olapConnection.getOlapSchema().getCubes().get(datasourceConfig.getCubeName());

      hookHandlerService.getHookers(OlapCubeCacheHook.class)
         .stream()
         .findAny() // only one cache
         .ifPresent(cache -> cache.putCubeInCache(report, cube, olapConnection));
      
      return cube;
   }

   @Override
   public List<String> getCubes(final MondrianDatasource datasource, final SaikuReport report)
         throws ClassNotFoundException, IOException, SQLException {
      if (datasource.isMondrian3())
         return oldOlapUtilService.get().getCubes(datasource, report);

      return getOlapConnection(datasource, report, false).getOlapSchema().getCubes()
         .stream()
         .map(Cube::getName)
         .collect(toList());
   }

   @Override
   public List<Dimension> getAllDimensions(final Cube cube) {
      return cube.getDimensions()
         .stream()
         .filter(dim -> !dim.getName().equals("Measures") && !dim.getUniqueName().equals("[Measures]"))
         .collect(toList());
   }

   @Override
   public List<SaikuHierarchy> getAllDimensionHierarchies(Cube cube, String dimensionName) {
      SaikuDimension dim = getDimension(cube, dimensionName);
      if (dim == null) 
         throw new SaikuServiceException("Cannot find dimension ( " + dimensionName + ") for cube ( " + cube + " )");
      
      return dim.getHierarchies();
   }

   @Override
   public SaikuDimension getDimension(Cube cube, String dimensionName) {
      Dimension dim = cube.getDimensions().get(dimensionName);
      if (dim != null) 
         return ObjectUtil.convert(dim);
      
      return null;
   }

   @Override
   public List<Member> getAllMeasures(final Cube cube) throws OlapException {
      List<Member> measures = cube.getMeasures()
         .stream()
         .filter(Measure::isVisible)
         .collect(toList());
      
      if (0 == measures.size()) {
         Hierarchy hierarchy = cube.getDimensions().get("Measures").getDefaultHierarchy();
         measures = hierarchy.getRootMembers();
      }

      return measures;
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
   public OlapConnection getOlapConnection(MondrianDatasource mondrianDatasource, SaikuReport report,
         boolean resetParameters) throws IOException, ClassNotFoundException, SQLException {
      if (!authenticatorService.get().isAuthenticated())
         throw new IllegalStateException("not authenticated");

      Properties props = new Properties();
      props.load(new StringReader(mondrianDatasource.getProperties().replace("\\", "\\u005c")));

      String driver = props.getProperty(ISaikuConnection.DRIVER_KEY);

      if (XMLA_DRIVER.equals(driver)) {
         /* use fields if no property defined */
         if (!props.containsKey(XMLA_USERNAME_KEY) && null != mondrianDatasource.getUsername()
               && !"".contentEquals(mondrianDatasource.getUsername().trim())) {
            props.setProperty(XMLA_USERNAME_KEY, mondrianDatasource.getUsername());
         }
         if (!props.containsKey(XMLA_PASSWORD_KEY) && null != mondrianDatasource.getPassword()
               && !"".contentEquals(mondrianDatasource.getPassword().trim())) {
            props.setProperty(XMLA_PASSWORD_KEY, mondrianDatasource.getPassword());
         }
      } else {
         /* set up for mondrian */
         String mondrianSchema = mondrianDatasource.getMondrianSchema();

         if (null != mondrianDatasource.getMondrianSchema()
               && !"".contentEquals(mondrianDatasource.getMondrianSchema().trim())) {
            /* init parameter set */
            ParameterSet parameterSet = parameterSetFactory.get().create(authenticatorService.get().getCurrentUser(),
                  report);
            if (null != report)
               parameterSet.addAll(report.getParameterInstances());

            Map<String, ParameterValue> pMap = new HashMap<>();
            if (null != parameterSet)
               pMap = parameterSet.getParameterMap();

            String replacedMondrianSchema = replaceParametersInQuery(parameterSet, pMap, mondrianSchema,
                  resetParameters);
            props.setProperty("CatalogContent", replacedMondrianSchema);

            /* use fields if no property defined */
            if (!props.containsKey(MONDRIAN_USERNAME_KEY) && null != mondrianDatasource.getUsername()
                  && !"".contentEquals(mondrianDatasource.getUsername().trim())) {
               props.setProperty(MONDRIAN_USERNAME_KEY, mondrianDatasource.getUsername());
            }
            if (!props.containsKey(MONDRIAN_PASSWORD_KEY) && null != mondrianDatasource.getPassword()
                  && !"".contentEquals(mondrianDatasource.getPassword().trim())) {
               props.setProperty(MONDRIAN_PASSWORD_KEY, mondrianDatasource.getPassword());
            }
         }

      }

      if (!props.containsKey(ISaikuConnection.URL_KEY) && null != mondrianDatasource.getUrl()
            && !"".contentEquals(mondrianDatasource.getUrl().trim())) {
         props.setProperty(ISaikuConnection.URL_KEY, jdbcService.adaptJdbcUrl(mondrianDatasource.getUrl()));
      }

      String url = props.getProperty(ISaikuConnection.URL_KEY);
      if (url.length() > 0 && url.charAt(url.length() - 1) != ';') {
         url += ";";
      }

      /* allow hook to adapt configuration */
      for (OlapConnectionPropertiesHook ocph : hookHandlerService.getHookers(OlapConnectionPropertiesHook.class)) {
         String adaptedUrl = ocph.adaptUrl(url);
         if (null != adaptedUrl)
            url = adaptedUrl;

         String adaptedDriver = ocph.adaptDriver(driver);
         if (null != adaptedDriver)
            driver = adaptedDriver;

         ocph.adaptProperties(props);
      }

      /* load driver and get connection */
      Class.forName(driver);

      OlapConnection connection = (OlapConnection) DriverManager.getConnection(url, props);

      /* allow hook to adapt connection */
      Optional<OlapConnectionHook> olapConnHook = hookHandlerService.getHookers(OlapConnectionHook.class)
         .stream()
         .findAny();
      if (olapConnHook.isPresent())
         connection = olapConnHook.get().postprocessConnection(mondrianDatasource, connection);
         
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
   public SaikuMember getMember(Cube cube, String uniqueMemberName) {
      try {
         Member m = cube.lookupMember(IdentifierNode.parseIdentifier(uniqueMemberName).getSegmentList());
         if (m != null) {
            return ObjectUtil.convert(m);
         }
         return null;
      } catch (Exception e) {
         throw new RuntimeException("Cannot find member: " + uniqueMemberName + " in cube:" + cube.getName(), e);
      }
   }

   @Override
   public List<SimpleCubeElement> getAllMembers(Cube cube, String hierarchy, String level) {
      return getAllMembers(cube, hierarchy, level, null, -1);
   }

   @Override
   public List<SimpleCubeElement> getAllMembers(Cube nativeCube, String hierarchy, String level, String searchString,
         int searchLimit) {
      try {
         OlapConnection con = nativeCube.getSchema().getCatalog().getDatabase().getOlapConnection();
         Hierarchy h = findHierarchy(hierarchy, nativeCube);

         boolean search = StringUtils.isNotBlank(searchString);
         int found = 0;
         List<SimpleCubeElement> simpleMembers;
         if (h != null) {
            Level l = h.getLevels().get(level);
            if (l == null) {
               for (Level lvl : h.getLevels()) {
                  if (lvl.getUniqueName().equals(level) || lvl.getName().equals(level)) {
                     l = lvl;
                     break;
                  }
               }
            }
            if (l == null) {
               throw new SaikuOlapException(
                     "Cannot find level " + level + " in hierarchy " + hierarchy + " of cube " + nativeCube.getName());
            }
            if (isMondrian(nativeCube)) {
               if (SaikuMondrianHelper.hasAnnotation(l, MondrianDictionary.SQLMemberLookup)) {
                  if (search) {
                     ResultSet rs = SaikuMondrianHelper.getSQLMemberLookup(con, MondrianDictionary.SQLMemberLookup, l,
                           searchString);
                     simpleMembers = ObjectUtil.convert2simple(rs);
                     log.debug("Found " + simpleMembers.size() + " members using SQL lookup for level " + level);
                     return simpleMembers;
                  } else {
                     return new ArrayList<>();
                  }
               }

            }
            if (search || searchLimit > 0) {
               List<Member> foundMembers = new ArrayList<>();
               List<Member> lokuplist;
               if (SaikuMondrianHelper.isMondrianConnection(con)
                     && SaikuMondrianHelper.getMondrianServer(con).getVersion().getMajorVersion() >= 4) {
                  lokuplist = SaikuMondrianHelper.getMDXMemberLookup(con, nativeCube.getName(), l);
               } else {
                  lokuplist = l.getMembers();
               }
               for (Member m : lokuplist) {
                  if (search) {
                     if (m.getName().toLowerCase().contains(searchString)
                           || m.getCaption().toLowerCase().contains(searchString)) {
                        foundMembers.add(m);
                        found++;
                     }
                  } else {
                     foundMembers.add(m);
                     found++;
                  }
                  if (searchLimit > 0 && found >= searchLimit) {
                     break;
                  }
               }
               simpleMembers = ObjectUtil.convert2Simple(foundMembers);
            } else {
               List<Member> lookuplist = null;
               if (SaikuMondrianHelper.isMondrianConnection(con)
                     && SaikuMondrianHelper.getMondrianServer(con).getVersion().getMajorVersion() >= 4) {
                  lookuplist = SaikuMondrianHelper.getMDXMemberLookup(con, nativeCube.getName(), l);
               } else {
                  lookuplist = l.getMembers();
               }
               simpleMembers = ObjectUtil.convert2Simple(lookuplist);
            }
            return simpleMembers;
         }
      } catch (Exception e) {
         throw new SaikuServiceException("Cannot get all members", e);
      }

      return new ArrayList<>();

   }

   @Override
   public Map<String, Object> getProperties(Cube c) {
      Map<String, Object> properties = new HashMap<>();
      try {
         OlapConnection con = c.getSchema().getCatalog().getDatabase().getOlapConnection();
         properties.put("saiku.olap.query.drillthrough", c.isDrillThroughEnabled());
         properties.put("org.saiku.query.explain", con.isWrapperFor(RolapConnection.class));

         try {
            Boolean isScenario = (c.getDimensions().get("Scenario") != null);
            properties.put("org.saiku.connection.scenario", isScenario);
         } catch (Exception e) {
            properties.put("org.saiku.connection.scenario", false);
         }
      } catch (Exception e) {
         throw new SaikuServiceException(e);
      }
      return properties;
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

   private boolean isMondrian(Cube cube) {
      OlapConnection con = cube.getSchema().getCatalog().getDatabase().getOlapConnection();
      try {
         return con.isWrapperFor(RolapConnection.class);
      } catch (SQLException e) {
         log.error("SQLException", e.getNextException());
      }
      return false;
   }

   private Hierarchy findHierarchy(String name, Cube cube) {
      Hierarchy h = cube.getHierarchies().get(name);
      if (h != null) {
         return h;
      }
      
      for (Hierarchy hierarchy : cube.getHierarchies()) {
         if (hierarchy.getUniqueName().equals(name)) {
            return hierarchy;
         }
      }
      return null;
   }

   @Override
   public void flushCache(final Report report) {
      if (report instanceof SaikuReport) {

         MondrianDatasource mondrianDatasource = (MondrianDatasource) report.getDatasourceContainer().getDatasource();
         if (mondrianDatasource.isMondrian3()) {
            oldOlapUtilService.get().flushCache(report);
            return;
         }

         try {
            OlapConnection con = getOlapConnection((SaikuReport) report);
            RolapConnection rolapConnection = con.unwrap(mondrian.rolap.RolapConnection.class);
            RolapSchema rolapSchema = rolapConnection.getSchema();
            CacheControl cacheControl = rolapConnection.getCacheControl(null);

            for (mondrian.olap.Cube cube : rolapSchema.getCubes()) {
               Cube reportCube = getCube((SaikuReport) report);
               if (!reportCube.getName().equals(cube.getName()))
                  continue;

               cacheControl.flushSchema(cube.getSchema());
            }

         } catch (Exception e) {
            throw new RuntimeException(e);
         }
      } else {
         throw new IllegalArgumentException("Mondrian cache flush on illegal report type: " + report.getClass());
      }
   }

   @Override
   public void flushCache(MondrianDatasource datasource) {

      if (datasource.isMondrian3()) {
         oldOlapUtilService.get().flushCache(datasource);
         return;
      }

      try {
         final OlapConnection con = getOlapConnection(datasource, null, true);
         final RolapConnection rolapConnection = con.unwrap(mondrian.rolap.RolapConnection.class);
         final RolapSchema rolapSchema = rolapConnection.getSchema();
         final CacheControl cacheControl = rolapConnection.getCacheControl(null);

         Arrays.stream(rolapSchema.getCubes())
            .map(mondrian.olap.Cube::getSchema)
            .forEach(cacheControl::flushSchema);
         
      } catch (MondrianSchemaException e) {
         // the cache cannot be flush with $!{params} causing an invalid syntax
      } catch (Exception e) {
         throw new RuntimeException(e);
      }
   }

   @Override
   public boolean testConnection(MondrianDatasource datasource) throws ConnectionTestFailedException {
      
      if (datasource.isMondrian3()) 
         return oldOlapUtilService.get().testConnection(datasource);
      
      try {
         final OlapConnection con = getOlapConnection(datasource, null, true);
         con.unwrap(mondrian.rolap.RolapConnection.class).getSchema();
         
      } catch (Exception e) {
         throw new ConnectionTestFailedException(
               "Connection test failed in datasource \"" + datasource.getName() + "\": " + e.getMessage(), e);
      }
      
      return true;
   }

}
