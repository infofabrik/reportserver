package net.datenwerke.rs;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.regex.Pattern;

import javax.inject.Singleton;
import javax.persistence.PersistenceException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathException;
import javax.xml.xpath.XPathFactory;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.boot.registry.BootstrapServiceRegistry;
import org.hibernate.boot.registry.BootstrapServiceRegistryBuilder;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.boot.spi.MetadataImplementor;
import org.hibernate.dialect.Dialect;
import org.hibernate.jpa.boot.internal.EntityManagerFactoryBuilderImpl;
import org.hibernate.jpa.boot.internal.ParsedPersistenceXmlDescriptor;
import org.hibernate.jpa.boot.internal.PersistenceXmlParser;
import org.hibernate.jpa.boot.spi.Bootstrap;
import org.hibernate.jpa.boot.spi.EntityManagerFactoryBuilder;
import org.hibernate.jpa.boot.spi.PersistenceUnitDescriptor;
import org.hibernate.jpa.boot.spi.ProviderChecker;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.tool.hbm2ddl.SchemaValidator;
import org.hibernate.tool.schema.spi.SchemaManagementException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import com.google.common.base.Splitter;

import net.datenwerke.rs.configservice.service.configservice.ConfigDirService;
import net.datenwerke.rs.configservice.service.configservice.ConfigDirServiceImpl;
import net.datenwerke.rs.configservice.service.configservice.LibDirClasspathHelper;
import net.datenwerke.rs.utils.xml.SimpleNamespaceContext;
import net.datenwerke.rs.utils.xml.XMLUtilsServiceImpl;

/**
 * 
 * Validates the environment on RS startup and handles the generation of an
 * error message if necessary.
 * 
 * As this has to work without any of the RS services, some functionality is
 * duplicated here.
 *
 */

@Singleton
public class EnvironmentValidator extends HttpServlet {

   private static final long serialVersionUID = 1564350579396856648L;

   private final static Logger logger = LoggerFactory.getLogger(EnvironmentValidator.class.getName());

   private static boolean hasError = false;

   private static List<String> propkeys = Arrays.asList("hibernate.dialect", "hibernate.connection.driver_class",
         "hibernate.connection.url", "hibernate.connection.username", "hibernate.connection.password",
         "hibernate.default_schema");

   private static Properties jpaProperties;

   private static StringBuffer errorInfo = new StringBuffer();

   public static boolean startup(boolean isEnterprise) {
      hasError = false;
      StringBuilder sb = new StringBuilder();

      if (isEnterprise)
         writeEnterpriseBanner(sb);
      else
         writeBanner(sb);

      writeVersionInfo(sb);
      writeJavaProps(sb);
      writeConfigDetails(sb);
      sb.append("\r\n");

      jpaProperties = getJpaProperties();
      loadCfgdirLibs();

      try {
         hasError |= !validatePersistence(sb);
      } catch (Exception e) {
         logger.warn("An error occured validating the database configuration", e);
      }
      logger.info(sb.toString());
      errorInfo.append(sb);

      if (hasError)
         return hasError;

      try {
         schemaupdate();
      } catch (Exception e) {
         logger.warn("Error processing the schema update", e);
         hasError = true;
      }

      if (hasError)
         return hasError;

      try {
         if (!"false".equals(jpaProperties.getProperty("rs.validateschema", "true"))) {
            logger.info("Validating database schema...");
            hasError |= !hibernateValidateSchema(errorInfo);
         }
      } catch (Exception e) {
         logger.warn("Error running the hibernate valdidator", e);
      }

      return hasError;
   }

   protected static EntityManagerFactoryBuilder getEntityManagerFactoryBuilder(final String persistenceUnitName,
         final Map properties) {

      final Map integration = (properties);
      final List<ParsedPersistenceXmlDescriptor> units;
      try {
         units = PersistenceXmlParser.locatePersistenceUnits(integration);
      } catch (Exception e) {
         throw new PersistenceException("Unable to locate persistence units", e);
      }

      if (persistenceUnitName == null && units.size() > 1) {
         // no persistence-unit name to look for was given and we found
         // multiple persistence-units
         throw new PersistenceException("No name provided and multiple persistence units found");
      }

      return units.stream()
            .filter(persistenceUnit -> persistenceUnitName == null
                  || persistenceUnit.getName().equals(persistenceUnitName))
            // See if we (Hibernate) are the persistence provider
            .filter(persistenceUnit -> ProviderChecker.isProvider(persistenceUnit, properties))
            .map(persistenceUnit -> getEntityManagerFactoryBuilder(persistenceUnit, integration, null)).findAny()
            .orElseThrow(() -> new IllegalStateException("No EntityManagerFactoryBuilder available"));
   }

   protected static EntityManagerFactoryBuilder getEntityManagerFactoryBuilder(
         PersistenceUnitDescriptor persistenceUnitDescriptor, Map integration, ClassLoader providedClassLoader) {
      return Bootstrap.getEntityManagerFactoryBuilder(persistenceUnitDescriptor, integration, providedClassLoader);
   }

   private static boolean hibernateValidateSchema(StringBuffer sb) throws NoSuchMethodException, SecurityException,
         IllegalAccessException, IllegalArgumentException, InvocationTargetException {
      long start = System.currentTimeMillis();
      ServiceRegistry serviceRegistry = null;
      try {
         BootstrapServiceRegistry bsr = new BootstrapServiceRegistryBuilder().build();
         StandardServiceRegistryBuilder ssrBuilder = new StandardServiceRegistryBuilder(bsr);
         ssrBuilder.applySettings(jpaProperties);
         serviceRegistry = ssrBuilder.build();

         EntityManagerFactoryBuilderImpl emfb = (EntityManagerFactoryBuilderImpl) getEntityManagerFactoryBuilder(
               "reportServerPU", jpaProperties);

         Method method = emfb.getClass().getDeclaredMethod("metadata");
         method.setAccessible(true);
         MetadataImplementor metadata = (MetadataImplementor) method.invoke(emfb);

         SchemaValidator schemaValidator = new SchemaValidator();

         try {
            schemaValidator.validate(metadata, serviceRegistry);
         } catch (SchemaManagementException sme) {
            logger.error(sme.getMessage());
            sb.append(sme.getMessage());
            return false;
         }

         if (start + 10000 < System.currentTimeMillis())
            logger.info("Schema validation completed");

         return true;
      } finally {
         StandardServiceRegistryBuilder.destroy(serviceRegistry);
      }

   }

   private static void writeConfigDetails(StringBuilder sb) {
      ConfigDirService configDirService = new ConfigDirServiceImpl(null);
      sb.append("rs.configdir: ").append(
            configDirService.isEnabled() ? configDirService.getConfigDir().getAbsolutePath() : "Not Configured");
      if (configDirService.isEnabled()) {
         sb.append(" (")
               .append(configDirService.getConfigDir().exists() && configDirService.getConfigDir().canRead() ? "OK)"
                     : "INACCESSIBLE)");
      }

      sb.append("\r\n");
   }

   private static void writeJavaProps(StringBuilder sb) {
      RuntimeMXBean runtimeMxBean = ManagementFactory.getRuntimeMXBean();

      sb.append("Java Version: ");
      sb.append(runtimeMxBean.getVmVendor() + " ");
      sb.append(runtimeMxBean.getVmName() + " ");
      sb.append(runtimeMxBean.getVmVersion());
      sb.append(" (" + runtimeMxBean.getSpecVersion() + ")\r\n");

      List<String> arguments = runtimeMxBean.getInputArguments();
      sb.append("VM Args: " + String.join(" ", arguments) + "\r\n");
      
      Runtime runtime = Runtime.getRuntime();
      int mb = 1024 * 1024;
      sb.append("Max memory: " + NumberFormat.getIntegerInstance().format(runtime.maxMemory() / mb) +  " MB\r\n");
   }

   private static void writeVersionInfo(StringBuilder sb) {
      InputStream propfile = EnvironmentValidator.class.getClassLoader().getResourceAsStream("rsversion.properties");
      if (null != propfile) {
         Properties p = new Properties();
         try {
            p.load(propfile);
            String date = p.getProperty("buildDate");
            sb.append("Version: ").append(p.getProperty("version")).append(" ")
                  .append(date.substring(date.indexOf("-") + 1)).append("\r\n");
         } catch (IOException e) {
         }
      } else {
         sb.append("Version: Unknown\r\n");
      }
      sb.append("Code Version: " + ReportServerServiceConfig.CODE_VERSION + "\r\n");
   }

   private static boolean validatePersistence(StringBuilder sb) throws InstantiationException, IllegalAccessException {
      sb.append("### DB Config ###\r\n");

      sb.append("hibernate.dialect: ").append(jpaProperties.getProperty("hibernate.dialect"));
      Class<?> dialectClass = null;
      try {
         dialectClass = Class.forName(jpaProperties.getProperty("hibernate.dialect"));
      } catch (ClassNotFoundException e1) {
      }
      sb.append(null != dialectClass ? " (OK)" : " (Class Not Found)");
      sb.append("\r\n");

      sb.append("hibernate.connection.driver_class: ")
            .append(jpaProperties.getProperty("hibernate.connection.driver_class"));
      Class<?> driverClass = null;
      try {
         driverClass = Class.forName(jpaProperties.getProperty("hibernate.connection.driver_class"));
      } catch (ClassNotFoundException e) {
      }
      sb.append(null != driverClass ? " (OK)" : " (Class Not Found)");
      sb.append("\r\n");

      String url = jpaProperties.getProperty("hibernate.connection.url");
      sb.append("hibernate.connection.url: ").append(url);
      if (null != driverClass) {
         Driver d = (Driver) driverClass.newInstance();
         boolean urlok = false;
         try {
            urlok = d.acceptsURL(url);
         } catch (SQLException e) {
            e.printStackTrace();
         }
         sb.append(urlok ? " (OK)" : " (Driver does not recognize URL)");
      }
      sb.append("\r\n");

      String username = jpaProperties.getProperty("hibernate.connection.username");
      sb.append("hibernate.connection.username: ").append(username).append("\r\n");

      if (null != jpaProperties.getProperty("hibernate.connection.password"))
         sb.append("hibernate.connection.password: ").append("**********").append("\r\n");

      String schema = jpaProperties.getProperty("hibernate.default_schema");
      sb.append("hibernate.default_schema: ").append(schema).append("\r\n");

      sb.append("\r\n");

      String schemaVersion = null;
      sb.append("Connection Test: ");
      Connection conn = null;
      try {
         Dialect dialect = (Dialect) dialectClass.newInstance();
         String query = dialect.getCurrentTimestampSelectString();

         conn = openConnection();
         PreparedStatement stmt = conn.prepareStatement(query);
         ResultSet resultSet = stmt.executeQuery();
         resultSet.close();
         stmt.close();
         sb.append("OK\r\n");

         sb.append("Schema Version: ");
         try {
            stmt = conn.prepareStatement(
                  "SELECT * FROM RS_SCHEMAINFO WHERE KEY_FIELD = 'schemaversion' ORDER BY ENTITY_ID DESC");
            resultSet = stmt.executeQuery();
            if (resultSet.next()) {
               schemaVersion = resultSet.getString("value");
               sb.append(resultSet.getString("value"));
            } else {
               sb.append(sb.append("No version number found. Did you forget a commit during installation?"));
            }
         } catch (SQLException e) {
            sb.append("Unknown (").append(e.getMessage()).append(")");
         } finally {
            resultSet.close();
            stmt.close();
         }
         sb.append("\r\n\r\n");
         sb.append("### Internal datasource metadata ###\r\n");
         DatabaseMetaData metaData = conn.getMetaData();
         sb.append("Database name: " + metaData.getDatabaseProductName()).append("\r\n");
         sb.append("Database version: " + metaData.getDatabaseProductVersion()).append("\r\n");
         sb.append("Driver name: " + metaData.getDriverName()).append("\r\n");
         sb.append("Driver version: " + metaData.getDriverVersion()).append("\r\n");
         sb.append("JDBC major version: " + metaData.getJDBCMajorVersion()).append("\r\n");
         sb.append("JDBC minor version: " + metaData.getJDBCMinorVersion()).append("\r\n");
         sb.append("JDBC URL: " + metaData.getURL()).append("\r\n");
         sb.append("JDBC username: " + metaData.getUserName()).append("\r\n");
         sb.append("\r\n");


         
      } catch (Exception e) {
         sb.append("Failed (").append(e.getMessage()).append(")\r\n");
         return false;
      } finally {
         if (null != conn) {
            try {
               conn.close();
            } catch (SQLException e) {
            }
         }
      }
      return true;
   }

   private static String getSchemaVersion() {
      String res = getSchemaMeta("schemaversion");
      if (null == res)
         res = getSchemaRsVersion();

      return res;
   }

   private static String getSchemaRsVersion() {
      return getSchemaMeta("version");
   }

   private static String getSchemaMeta(String field) {
      try (Connection conn = openConnection()) {
         try (PreparedStatement stmt = conn
               .prepareStatement("SELECT * FROM RS_SCHEMAINFO WHERE KEY_FIELD = ? ORDER BY ENTITY_ID DESC")) {
            stmt.setString(1, field);
            try (ResultSet resultSet = stmt.executeQuery()) {
               while (resultSet.next()) {
                  return resultSet.getString("value");
               }
            }
         }
      } catch (SQLException e) {
      }

      return null;
   }

   private static Connection openConnection() throws SQLException {
      String url = jpaProperties.getProperty("hibernate.connection.url");
      String username = jpaProperties.getProperty("hibernate.connection.username");
      String password = jpaProperties.getProperty("hibernate.connection.password");

      return DriverManager.getConnection(url, username, password);
   }

   private static String getBranchFromSchema(String schemaVersion) {
      String currentBranch = null;
      int currentVersion;
      int i = StringUtils.countMatches(schemaVersion, ".");
      if (i == 2) {
         currentBranch = schemaVersion.substring(0, schemaVersion.lastIndexOf("."));
      } else {
         currentBranch = schemaVersion.substring(0, schemaVersion.lastIndexOf("-"));
      }
      try {
         currentVersion = Integer.valueOf(schemaVersion.substring(schemaVersion.indexOf("-") + 1));
      } catch (NumberFormatException e) {
         currentVersion = -1;
      }

      return currentBranch;
   }

   private static int getSchemaVersionFromSchema(String schemaVersion) {
      String currentBranch = null;
      int currentVersion;
      int i = StringUtils.countMatches(schemaVersion, ".");
      if (i == 2) {
         currentBranch = schemaVersion.substring(0, schemaVersion.lastIndexOf("."));
      } else {
         currentBranch = schemaVersion.substring(0, schemaVersion.lastIndexOf("-"));
      }
      try {
         currentVersion = Integer.valueOf(schemaVersion.substring(schemaVersion.indexOf("-") + 1));
      } catch (NumberFormatException e) {
         currentVersion = -1;
      }
      return currentVersion;
   }

   private static boolean schemaupdate() throws IOException, ClassNotFoundException, SQLException {

      String currentSchemaVersion = getSchemaVersion();
      if (null == currentSchemaVersion)
         return true;

      InputStream propfile = EnvironmentValidator.class.getClassLoader().getResourceAsStream("rsversion.properties");
      if (null == propfile)
         return true;

      Properties p = new Properties();
      p.load(propfile);

      String requiredSchemaVersion = p.getProperty("schemaversion");
      if (null == requiredSchemaVersion)
         return true;

      String requiredBranch = getBranchFromSchema(requiredSchemaVersion);
      int requiredVersion = getSchemaVersionFromSchema(requiredSchemaVersion);

      String currentBranch = getBranchFromSchema(currentSchemaVersion);
      int currentVersion = getSchemaVersionFromSchema(currentSchemaVersion);

      Class<?> dialectClass = Class.forName(jpaProperties.getProperty("hibernate.dialect"));
      String dialect = resolveDialect(dialectClass);

      while (hasSpecialUpdateScript(currentBranch, dialect, currentVersion)) {
         logger.info("Performing database update preparations " + currentBranch + "-" + currentVersion);
         String script = getSpecialUpdateScript(currentBranch, dialect, currentVersion);

         runSqlScript(script);

         /* reload info from database */
         String prev = currentSchemaVersion;
         currentSchemaVersion = getSchemaVersion();
         currentBranch = getBranchFromSchema(currentSchemaVersion);
         currentVersion = getSchemaVersionFromSchema(currentSchemaVersion);

         /* don't loop forever */
         if (prev.equals(currentSchemaVersion))
            break;
      }

      if (!requiredBranch.equals(currentBranch)) {
         logger.info("Performing database update " + currentBranch + " -> " + requiredBranch);
         doCrossBranchUpdate(currentBranch, currentVersion, requiredBranch, requiredVersion, dialect);

         /* reload info from database */
         currentSchemaVersion = getSchemaVersion();
         currentBranch = getBranchFromSchema(currentSchemaVersion);
         currentVersion = getSchemaVersionFromSchema(currentSchemaVersion);
      }

      if (currentVersion < requiredVersion) {
         logger.info("Performing database update " + currentSchemaVersion + " -> " + requiredSchemaVersion);
         doVersionUpdate(currentBranch, currentVersion, requiredVersion, dialect);
      }

      return true;
   }

   private static String resolveDialect(Class<?> dialectClass) {
      HashMap<String, String> dialects = new HashMap<>();
      dialects.put("mysql", "MySQL5");
      dialects.put("oracle", "Oracle");
      dialects.put("db2", "DB2");
      dialects.put("postgres", "PostgreSQL");
      dialects.put("sqlserver", "SQLServer");
      dialects.put("mssql", "SQLServer");

      do {
         for (String k : dialects.keySet()) {
            if (dialectClass.getName().toLowerCase().contains(k)) {
               return dialects.get(k);
            }
         }

         dialectClass = dialectClass.getSuperclass();
      } while (dialectClass != null);

      return null;
   }

   private static String getBranchUpdateScript(String fromBranch, int fromVersion, String toBranch, int toVersion,
         String dialect) {
      String sname = "cb_" + fromBranch + "-" + toBranch + "/" + fromBranch + "-" + fromVersion + "/" + fromBranch + "-"
            + fromVersion + "-" + toBranch + "-" + toVersion + "-" + dialect + "_UPDATE.sql";
      try {
         InputStream is = EnvironmentValidator.class.getClassLoader()
               .getResourceAsStream("net/datenwerke/rs/schemaupdate/resources/" + sname);
         if (null == is) {
            is = EnvironmentValidator.class.getClassLoader().getResourceAsStream("resources/schemaupdate/" + sname);
         }

         return IOUtils.toString(is);
      } catch (Exception e) {
         logger.warn("Update Script " + sname + " not found. ");
      }

      return null;
   }

   private static boolean hasBranchUpdateScript(String fromBranch, int fromVersion, String toBranch, int toVersion,
         String dialect) {
      String sname = "cb_" + fromBranch + "-" + toBranch + "/" + fromBranch + "-" + fromVersion + "/" + fromBranch + "-"
            + fromVersion + "-" + toBranch + "-" + toVersion + "-" + dialect + "_UPDATE.sql";
      try {
         InputStream is = EnvironmentValidator.class.getClassLoader()
               .getResourceAsStream("net/datenwerke/rs/schemaupdate/resources/" + sname);
         if (null == is) {
            is = EnvironmentValidator.class.getClassLoader().getResourceAsStream("resources/schemaupdate/" + sname);
         }

         return null != is;
      } catch (Exception e) {
      }

      return false;
   }

   private static String getUpdateScript(String branch, String dialect, int schemaversion) {
      String sname = branch + "/" + branch + "-" + schemaversion + "/" + branch + "-" + schemaversion + "-" + dialect
            + "_UPDATE.sql";
      try {
         InputStream is = EnvironmentValidator.class.getClassLoader()
               .getResourceAsStream("net/datenwerke/rs/schemaupdate/resources/" + sname);
         if (null == is) {
            is = EnvironmentValidator.class.getClassLoader().getResourceAsStream("resources/schemaupdate/" + sname);
         }

         return IOUtils.toString(is);
      } catch (Exception e) {
         logger.warn("Update Script " + sname + " not found. ");
      }

      return null;
   }

   private static boolean hasUpdateScript(String branch, String dialect, int schemaversion) {
      String sname = branch + "/" + branch + "-" + schemaversion + "/" + branch + "-" + schemaversion + "-" + dialect
            + "_UPDATE.sql";
      try {
         InputStream is = EnvironmentValidator.class.getClassLoader()
               .getResourceAsStream("net/datenwerke/rs/schemaupdate/resources/" + sname);
         if (null == is) {
            is = EnvironmentValidator.class.getClassLoader().getResourceAsStream("resources/schemaupdate/" + sname);
         }

         return is != null;
      } catch (Exception e) {
      }

      return false;
   }

   private static boolean hasSpecialUpdateScript(String branch, String dialect, int schemaversion) {
      String sname = branch + "/" + branch + "-" + schemaversion + "/" + branch + "-prepare" + "-" + schemaversion + "-"
            + dialect + "_UPDATE.sql";
      try {
         InputStream is = EnvironmentValidator.class.getClassLoader()
               .getResourceAsStream("net/datenwerke/rs/schemaupdate/resources/" + sname);
         if (null == is) {
            is = EnvironmentValidator.class.getClassLoader().getResourceAsStream("resources/schemaupdate/" + sname);
         }

         return is != null;
      } catch (Exception e) {
      }

      return false;
   }

   private static String getSpecialUpdateScript(String branch, String dialect, int schemaversion) {
      String sname = branch + "/" + branch + "-" + schemaversion + "/" + branch + "-prepare" + "-" + schemaversion + "-"
            + dialect + "_UPDATE.sql";
      try {
         InputStream is = EnvironmentValidator.class.getClassLoader()
               .getResourceAsStream("net/datenwerke/rs/schemaupdate/resources/" + sname);
         if (null == is) {
            is = EnvironmentValidator.class.getClassLoader().getResourceAsStream("resources/schemaupdate/" + sname);
         }

         return IOUtils.toString(is);
      } catch (Exception e) {
         logger.warn("Update Script " + sname + " not found. ");
      }

      return null;
   }

   private static void doCrossBranchUpdate(String fromBranch, int fromVersion, String toBranch, int toVersion,
         String dialect) throws SQLException {
      /* first run all updates for the current branch */
      if (hasUpdateScript(fromBranch, dialect, fromVersion + 1)) {
         doVersionUpdate(fromBranch, fromVersion, Integer.MAX_VALUE, dialect);
      }

      /* reload info from database */
      String svAfterUpdate = getSchemaVersion();
      String fromBranch2 = getBranchFromSchema(svAfterUpdate);
      int fromVersion2 = getSchemaVersionFromSchema(svAfterUpdate);

      int testTargetVersion = toVersion;
      while (testTargetVersion > 0) {
         if (hasBranchUpdateScript(fromBranch2, fromVersion2, toBranch, testTargetVersion, dialect)) {
            break;
         }
         testTargetVersion--;
      }

      if (testTargetVersion == 0)
         throw new RuntimeException("No update script found");

      String script = getBranchUpdateScript(fromBranch2, fromVersion2, toBranch, testTargetVersion, dialect);

      logger.info("Running script " + "cb_" + fromBranch + "-" + toBranch + "/" + fromBranch + "-" + fromVersion + "/"
            + fromBranch + "-" + fromVersion + "-" + toBranch + "-" + toVersion + "-" + dialect + "_UPDATE.sql");

      runSqlScript(script);

      /* reload info from database */
      svAfterUpdate = getSchemaVersion();
      String branchAfterUpdate = getBranchFromSchema(svAfterUpdate);
      int schemaVersionAfterUpdate = getSchemaVersionFromSchema(svAfterUpdate);

      if (!(toBranch.equals(branchAfterUpdate) && schemaVersionAfterUpdate == testTargetVersion)) {
         throw new RuntimeException("Schemaupdate produced unexpected result: " + svAfterUpdate);
      }

   }

   private static void doVersionUpdate(String branch, int fromVersion, int toVersion, String dialect)
         throws SQLException {
      int currentVersion = fromVersion;
      while (currentVersion < toVersion) {
         /*
          * if toVersion is explicitly set update should fail when the update script is
          * missing, if using MAX_INT just try until one script is missing
          */
         if (toVersion < Integer.MAX_VALUE || hasUpdateScript(branch, dialect, currentVersion + 1)) {
            String script = getUpdateScript(branch, dialect, currentVersion + 1);

            logger.info("Running script " + branch + "-" + (currentVersion + 1) + "-" + dialect + "_UPDATE.sql");

            runSqlScript(script);

            /* verify success */
            String svAfterUpdate = getSchemaVersion();
            String branchAfterUpdate = getBranchFromSchema(svAfterUpdate);
            int schemaAfterUpdate = getSchemaVersionFromSchema(svAfterUpdate);

            if (!(branch.equals(branchAfterUpdate) && schemaAfterUpdate == currentVersion + 1)) {
               throw new RuntimeException("Schemaupdate produced unexpected result: " + svAfterUpdate);
            }

            currentVersion = currentVersion + 1;
         } else {
            break;
         }
      }

   }

   private static void runSqlScript(String script) throws SQLException {
      /* filter comments */
      script = script.replaceAll("(?m)^\\s*#.*$", "");
      script = script.replaceAll("(?m)^\\s*-- .*$", "");
      script = script.replaceAll("(?mi)^\\s*go\\s*$", "");

      Iterable<String> split = Splitter.on(Pattern.compile(";\\s*$", Pattern.MULTILINE)).trimResults()
            .omitEmptyStrings().split(script);

      try (Connection conn = openConnection()) {

         int n = 1;
         Iterator<String> iterator = split.iterator();
         List<Map> replacementStack = new ArrayList<>();

         processStatements(conn, iterator, n, replacementStack);

         if (!conn.getAutoCommit())
            conn.commit();
      }

   }

   private static void processStatements(Connection conn, Iterator<String> iterator, int n, List<Map> replacementStack)
         throws SQLException {
      while (iterator.hasNext()) {
         String sn = iterator.next();

         /* preproces sn */
         final Map<String, Object> currentMap = new HashMap<>();
         replacementStack.forEach(currentMap::putAll);

         for (Entry<String, Object> e : currentMap.entrySet()) {
            // we need both as the script may use any of both variable names (upper or
            // lowercase)
            sn = sn.replace("${" + e.getKey().toLowerCase() + "}", String.valueOf(e.getValue()));
            sn = sn.replace("${" + e.getKey().toUpperCase() + "}", String.valueOf(e.getValue()));
         }

         /* check for commit */
         if (sn.toLowerCase().equals("commit") && conn.getAutoCommit())
            continue;

         if (sn.toLowerCase().startsWith("rs_for:")) {
            try (PreparedStatement stmt = conn.prepareStatement(sn.substring("rs_for:".length()))) {
               /* find statements until foreach ends */
               List<String> innerStmts = new ArrayList<>();
               int endCnt = 1;
               while (iterator.hasNext()) {
                  String inner = iterator.next();

                  if (inner.startsWith("rs_endfor"))
                     endCnt--;
                  else if (inner.startsWith("rs_for:"))
                     endCnt++;

                  if (endCnt == 0)
                     break;

                  innerStmts.add(inner);
               }

               if (0 != endCnt)
                  throw new IllegalStateException("Incorrectly nested for each.");

               ResultSet rs = stmt.executeQuery();
               int columnCount = rs.getMetaData().getColumnCount();

               int cnt = 0;
               while (rs.next()) {
                  /* prepare new map with values */
                  Map<String, Object> replacementMap = new HashMap<>();
                  replacementStack.add(replacementMap);

                  for (int i = 0; i < columnCount; i++) {
                     Object object = rs.getObject(i + 1);
                     if (object instanceof String)
                        object = ((String) object).replaceAll("'", "''");
                     replacementMap.put(rs.getMetaData().getColumnLabel(i + 1).toLowerCase(), object);
                     replacementMap.put(rs.getMetaData().getColumnLabel(i + 1).toLowerCase() + "_cnt", cnt);
                  }

                  /* run inner statements */
                  processStatements(conn, innerStmts.iterator(), ++n, replacementStack);

                  /* remove last map */
                  replacementStack.remove(replacementStack.size() - 1);

                  /* increase counter */
                  cnt++;
               }

            } catch (SQLException e) {
               logger.error(
                     "Error executing update statement: " + e.getMessage() + " (n=" + n + ", statement=" + sn + ")");
               throw e;
            }

         } else if (sn.toLowerCase().startsWith("rs_endfor")) {
         } else {
            logger.debug("Run sql: " + sn);
            try (PreparedStatement stmt = conn.prepareStatement(sn)) {
               stmt.execute();
            } catch (SQLException e) {
               logger.error(
                     "Error executing update statement: " + e.getMessage() + " (n=" + n + ", statement=" + sn + ")");
               throw e;
            }
         }

         n++;
      }
   }

   private static void loadCfgdirLibs() {
      ConfigDirService configDirService = new ConfigDirServiceImpl(null);
      new LibDirClasspathHelper(configDirService).loadLibs();
   }

   private static Properties getJpaProperties() {
      Properties jpaProperties = new Properties();

      try { /* persistence.xml */
         InputStream pxmlstream = Thread.currentThread().getContextClassLoader()
               .getResourceAsStream("META-INF/persistence.xml");
         if (null != pxmlstream) {
            XMLUtilsServiceImpl xml = new XMLUtilsServiceImpl(true);
            Document pxml = xml.readInputStreamIntoJAXPDoc(pxmlstream);

            XPathFactory factory = XPathFactory.newInstance();
            XPath xpath = factory.newXPath();
            xpath.setNamespaceContext(new SimpleNamespaceContext("p", "http://java.sun.com/xml/ns/persistence"));

            for (String propkey : propkeys) {
               String xpstr = "/p:persistence/p:persistence-unit[@name='" + ReportServerPUModule.JPA_UNIT_NAME
                     + "']/p:properties/p:property[@name='" + propkey + "']/@value";
               String val = xpath.evaluate(xpstr, pxml);
               jpaProperties.setProperty(propkey, val);
            }
         }
      } catch (ParserConfigurationException e1) {
         e1.printStackTrace();
      } catch (SAXException e1) {
         e1.printStackTrace();
      } catch (IOException e1) {
         e1.printStackTrace();
      } catch (XPathException e) {
         e.printStackTrace();
      }

      ConfigDirService configDirService = new ConfigDirServiceImpl(null);
      ReportServerPUStartup.loadPersistenceProperties(configDirService, jpaProperties);

      return jpaProperties;
   }

   private static void writeBanner(StringBuilder sb) {
      sb.append("\r\n\r\n\r\n\r\n\r\n");
      sb.append("  _____                       _    _____                          \r\n");
      sb.append(" |  __ \\                     | |  / ____|                         \r\n");
      sb.append(" | |__) |___ _ __   ___  _ __| |_| (___   ___ _ ____   _____ _ __ \r\n");
      sb.append(" |  _  // _ \\ '_ \\ / _ \\| '__| __|\\___ \\ / _ \\ '__\\ \\ / / _ \\ '__|\r\n");
      sb.append(" | | \\ \\  __/ |_) | (_) | |  | |_ ____) |  __/ |   \\ V /  __/ |   \r\n");
      sb.append(" |_|  \\_\\___| .__/ \\___/|_|   \\__|_____/ \\___|_|    \\_/ \\___|_|   \r\n");
      sb.append("            | |                                                   \r\n");
      sb.append("            |_|                                                   \r\n\r\n");
   }

   private static void writeEnterpriseBanner(StringBuilder sb) {
      sb.append("\r\n\r\n\r\n\r\n\r\n");
      sb.append(
            "  _____                       _    _____                            ______       _                       _          \r\n"
                  + " |  __ \\                     | |  / ____|                          |  ____|     | |                     (_)         \r\n"
                  + " | |__) |___ _ __   ___  _ __| |_| (___   ___ _ ____   _____ _ __  | |__   _ __ | |_ ___ _ __ _ __  _ __ _ ___  ___ \r\n"
                  + " |  _  // _ \\ '_ \\ / _ \\| '__| __|\\___ \\ / _ \\ '__\\ \\ / / _ \\ '__| |  __| | '_ \\| __/ _ \\ '__| '_ \\| '__| / __|/ _ \\\r\n"
                  + " | | \\ \\  __/ |_) | (_) | |  | |_ ____) |  __/ |   \\ V /  __/ |    | |____| | | | ||  __/ |  | |_) | |  | \\__ \\  __/\r\n"
                  + " |_|  \\_\\___| .__/ \\___/|_|   \\__|_____/ \\___|_|    \\_/ \\___|_|    |______|_| |_|\\__\\___|_|  | .__/|_|  |_|___/\\___|\r\n"
                  + "            | |                                                                              | |                    \r\n"
                  + "            |_|                                                                              |_|                    ");
      sb.append("\r\n\r\n");
   }

   @Override
   protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      resp.setContentType("text/html");
      PrintWriter writer = resp.getWriter();
      writer.println("<html>\r\n" + "<title>ReportServer</title>\r\n" + "</head>\r\n" + "<body>\r\n"
            + "<p style=\"font-family:sans-serif; \">ReportServer encountered an error while validating its environment. Please check the server log files for additional information.</p>\r\n"
            + "<pre>");

      writer.println(errorInfo.toString());

      writer.println("</pre>\r\n" + "\r\n" + "</body>\r\n" + "</html>");
      writer.close();
   }

}
