package net.datenwerke.rs.adminutils.service.systemconsole.generalinfo;

import static java.util.stream.Collectors.toList;
import static net.datenwerke.rs.adminutils.client.systemconsole.generalinfo.Memory.FREE;
import static net.datenwerke.rs.adminutils.client.systemconsole.generalinfo.Memory.FREE_FORMATTED;
import static net.datenwerke.rs.adminutils.client.systemconsole.generalinfo.Memory.FREE_IN_MB;
import static net.datenwerke.rs.adminutils.client.systemconsole.generalinfo.Memory.MAX;
import static net.datenwerke.rs.adminutils.client.systemconsole.generalinfo.Memory.MAX_FORMATTED;
import static net.datenwerke.rs.adminutils.client.systemconsole.generalinfo.Memory.MAX_IN_MB;
import static net.datenwerke.rs.adminutils.client.systemconsole.generalinfo.Memory.TOTAL;
import static net.datenwerke.rs.adminutils.client.systemconsole.generalinfo.Memory.TOTAL_FORMATTED;
import static net.datenwerke.rs.adminutils.client.systemconsole.generalinfo.Memory.TOTAL_IN_MB;
import static net.datenwerke.rs.adminutils.client.systemconsole.generalinfo.Memory.USED;
import static net.datenwerke.rs.adminutils.client.systemconsole.generalinfo.Memory.USED_FORMATTED;
import static net.datenwerke.rs.adminutils.client.systemconsole.generalinfo.Memory.USED_IN_MB;
import static net.datenwerke.rs.utils.file.RsFileUtils.byteCountToDisplaySize;

import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import javax.net.ssl.SSLContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.ProvisionException;

import groovy.lang.GroovySystem;
import net.datenwerke.gf.service.history.HistoryService;
import net.datenwerke.rs.EnvironmentValidatorHelperService;
import net.datenwerke.rs.adminutils.client.systemconsole.generalinfo.Memory;
import net.datenwerke.rs.adminutils.client.systemconsole.generalinfo.dto.GeneralInfoDto;
import net.datenwerke.rs.adminutils.service.logs.LogFilesService;
import net.datenwerke.rs.base.service.datasources.DatasourceHelperService;
import net.datenwerke.rs.base.service.datasources.definitions.DatabaseDatasource;
import net.datenwerke.rs.configservice.service.configservice.ConfigDirService;
import net.datenwerke.rs.core.service.internaldb.TempTableService;
import net.datenwerke.rs.license.service.LicenseService;
import net.datenwerke.rs.utils.localization.LocalizationServiceImpl;
import net.datenwerke.rs.utils.properties.PropertiesUtilService;
import net.datenwerke.security.service.authenticator.ReportServerPAM;

public class GeneralInfoServiceImpl implements GeneralInfoService {

   private final RuntimeMXBean runtimeMxBean = ManagementFactory.getRuntimeMXBean();

   private final Provider<ServletContext> servletContextProvider;
   private final Provider<HttpServletRequest> servletRequestProvider;
   private final Provider<LicenseService> licenseServiceProvider;
   private final Provider<DatasourceHelperService> datasourceHelperServiceProvider;
   private final Provider<TempTableService> tempTableServiceProvider;
   private final Provider<Set<ReportServerPAM>> pamProvider;
   private final Provider<EnvironmentValidatorHelperService> envServiceProvider;
   private final Provider<HistoryService> historyServiceProvider;
   private final Provider<ConfigDirService> configDirServiceProvider;
   private final Provider<LogFilesService> logFilesServiceProvider;
   
   private final Logger log = LoggerFactory.getLogger( getClass() );
   
   @Inject
   public GeneralInfoServiceImpl(
         Provider<ServletContext> servletContextProvider,
         Provider<HttpServletRequest> servletRequestProvider, 
         Provider<LicenseService> licenseServiceProvider,
         Provider<DatasourceHelperService> datasourceHelperServiceProvider,
         Provider<TempTableService> tempTableServiceProvider,
         Provider<Set<ReportServerPAM>> pamProvider,
         Provider<EnvironmentValidatorHelperService> environmentValidatorHelperServiceProvider,
         Provider<HistoryService> historyServiceProvider,
         Provider<ConfigDirService> configDirServiceProvider,
         Provider<LogFilesService> logFilesServiceProvider
         ) {
      this.servletContextProvider = servletContextProvider;
      this.servletRequestProvider = servletRequestProvider;
      this.licenseServiceProvider = licenseServiceProvider;
      this.datasourceHelperServiceProvider = datasourceHelperServiceProvider;
      this.tempTableServiceProvider = tempTableServiceProvider;
      this.pamProvider = pamProvider;
      this.envServiceProvider = environmentValidatorHelperServiceProvider;
      this.historyServiceProvider = historyServiceProvider;
      this.configDirServiceProvider = configDirServiceProvider;
      this.logFilesServiceProvider = logFilesServiceProvider;
   }

   @Override
   public String getJavaVersion() {
      try {
         return runtimeMxBean.getVmVendor() + " " + runtimeMxBean.getVmName() + " " + runtimeMxBean.getVmVersion() + " ("
               + runtimeMxBean.getSpecVersion() + ")";
      } catch (SecurityException e) {
         log.warn("Cannot read java version", e);
         return ExceptionUtils.getRootCauseMessage(e);
      }
   }

   @Override
   public String getVmArguments() {
      try {
         return String.join(" ", runtimeMxBean.getInputArguments());
      } catch (SecurityException e) {
         log.warn("Cannot read VM arguments", e);
         return ExceptionUtils.getRootCauseMessage(e);
      }
   }

   @Override
   public String getApplicationServer() {
      return servletContextProvider.get().getServerInfo();
   }

   @Override
   public String getOsVersion() {
      try {
         String osVersion = readSystemProperty("os.version");
         String osArchitecture = readSystemProperty("os.arch");
         final StringBuilder sb = new StringBuilder();
         sb.append(readSystemProperty("os.name"));
         if (null != osVersion) {
            sb.append(" ").append(osVersion);
         }
         if (null != osArchitecture) {
            sb.append(" (" + osArchitecture).append(")");
         }
         return sb.toString();
      } catch (SecurityException e) {
         log.warn("Cannot read OS version", e);
         return ExceptionUtils.getRootCauseMessage(e);
      }
   }

   @Override
   public String getUserAgent() {
      try {
         if (null == servletRequestProvider.get())
            return null;
         return servletRequestProvider.get().getHeader("User-Agent");
      } catch (ProvisionException e) {
         // guice OutOfScopeException in scheduler
         return null;
      } 
   }

   @Override
   public String getRsVersion() {
      return licenseServiceProvider.get().getRsVersion();
   }

   @Override
   public GeneralInfoDto getGeneralInfo() {
      GeneralInfoDto info = new GeneralInfoDto();

      info.setRsVersion(getRsVersion());
      info.setJavaVersion(getJavaVersion());
      info.setVmArguments(getVmArguments());
      info.setApplicationServer(getApplicationServer());
      info.setRequestURL(getRequestURL());
      info.setServerName(getServerName());
      info.setServerPort(getServerPort());
      info.setScheme(getScheme());
      info.setContextPath(getContextPath());
      info.setProtocol(getProtocol());
      info.setMaxMemory(getMemoryValues().get(MAX_FORMATTED)+ "");
      info.setOsVersion(getOsVersion());
      info.setCatalinaHome(getCatalinaHome());
      info.setCatalinaBase(getCatalinaBase());
      info.setLogFilesDirectory(getLogFilesDirectory());
      info.setJvmUserTimezone(getJvmUserTimezone());
      info.setJvmUserCountry(getJvmUserCountry());
      info.setJvmUserLanguage(getJvmUserLanguage());
      info.setJavaHome(getJavaHome());
      info.setJvmFileEncoding(getJvmFileEncoding());
      info.setUserAgent(getUserAgent());
      info.setGroovyVersion(getGroovyVersion());
      info.setLocale(getLocale());
      info.setJvmLocale(getJvmLocale());
      info.setSupportedSslProtocols(getSupportedSslProtocols());
      info.setDefaultSslProtocols(getDefaultSslProtocols());
      info.setEnabledSslProtocols(getEnabledSslProtocols());
      info.setStaticPams(getStaticPams());
      
      setHibernateProperties(info);
      setSchemaVersion(info);
      setInternalDb(info);
      
      info.setConfigDir(getConfigDirectory());
      
      return info;
   }
   
   private void setInternalDb(GeneralInfoDto info) {
      final String errorMsg = "No internal database found. Check your /fileserver/etc/datasources/internaldb.cf configuration file.";
      DatasourceHelperService datasourceHelperService = datasourceHelperServiceProvider.get();
      
      DatabaseDatasource internalDbDatasource = tempTableServiceProvider.get().getInternalDbDatasource();
      if (null == internalDbDatasource) {
         info.setInternalDbConfigured(false);
         info.setInternalDbDatasourceName(errorMsg);
         return;
      }
      
      try {
         info.setInternalDbConfigured(true);
         info.setInternalDbId(internalDbDatasource.getId()+"");
         final List<String> paths = historyServiceProvider.get().getFormattedObjectPaths(internalDbDatasource);
         info.setInternalDbPath(paths.isEmpty()? "path not found": paths.get(0));
         info.setInternalDbDatasourceName(internalDbDatasource.getName());
         Map<String, Object> datasourceMetadata = datasourceHelperService
               .fetchInfoDatasourceMetadata(internalDbDatasource, true, true, true, true);
         info.setInternalDbDatabaseName(datasourceMetadata.get("getDatabaseProductName").toString());
         info.setInternalDbVersion(datasourceMetadata.get("getDatabaseProductVersion").toString());
         info.setInternalDbDriverName(datasourceMetadata.get("getDriverName").toString());
         info.setInternalDbDriverVersion(datasourceMetadata.get("getDriverVersion").toString());
         info.setInternalDbJdbcMajorVersion(datasourceMetadata.get("getJDBCMajorVersion").toString());
         info.setInternalDbJdbcMinorVersion(datasourceMetadata.get("getJDBCMinorVersion").toString());
         info.setInternalDbJdbcUrl(datasourceMetadata.get("getURL").toString());
         info.setInternalDbUsername(datasourceMetadata.get("getUserName").toString());
         try {
            info.setInternalDbJdbcProperties(null != internalDbDatasource.parseJdbcProperties()
                  ? PropertiesUtilService.convert(internalDbDatasource.parseJdbcProperties())
                  : null);
         } catch (Exception e) {
            info.setInternalDbJdbcProperties(new HashMap<String,String>() {
               private static final long serialVersionUID = 1L;
            {
               put("error", ExceptionUtils.getRootCauseMessage(e));
            }});
         }
      } catch (Exception e) {
         info.setInternalDbDatasourceName(internalDbDatasource.getName() + ": " + ExceptionUtils.getRootCauseMessage(e));
      }
   }
   
   private void setSchemaVersion(GeneralInfoDto info) {
      try {
         info.setSchemaVersion(envServiceProvider.get().getSchemaVersion());
      } catch (SQLException e) {
         info.setSchemaVersion("Unknown (" + ExceptionUtils.getRootCauseMessage(e) + ")");
      }
   }
   
   private void setHibernateProperties(GeneralInfoDto info) {
      final Properties jpaProperties = envServiceProvider.get().getJpaProperties();
      info.setHibernateDialect(jpaProperties.getProperty("hibernate.dialect"));
      info.setHibernateDriverClass(jpaProperties.getProperty("hibernate.connection.driver_class"));
      info.setHibernateConnectionUrl(jpaProperties.getProperty("hibernate.connection.url"));
      info.setHibernateConnectionUsername(jpaProperties.getProperty("hibernate.connection.username"));
      info.setHibernateDefaultSchema(jpaProperties.getProperty("hibernate.default_schema"));
   }

   @Override
   public String getLocale() {
      return LocalizationServiceImpl.getLocale().toString();
   }

   @Override
   public String getJvmLocale() {
      return Locale.getDefault().toString();
   }

   @Override
   public List<String> getSupportedSslProtocols() {
      try {
         String[] protocols = SSLContext.getDefault().getSupportedSSLParameters().getProtocols();
         if (null == protocols)
            return Collections.emptyList();
         return Arrays.asList(protocols);
      } catch (Exception e) {
         log.warn("cannot read supported SSL protocols", e); 
         return Arrays.asList("Unknown (" + ExceptionUtils.getRootCauseMessage(e) + ")");
      }
   }

   @Override
   public List<String> getDefaultSslProtocols() {
      try {
         String[] protocols = SSLContext.getDefault().getDefaultSSLParameters().getProtocols();
         if (null == protocols)
            return Collections.emptyList();
         return Arrays.asList(protocols);
      } catch (Exception e) {
         log.warn("cannot read default SSL protocols", e); 
         return Arrays.asList("Unknown (" + ExceptionUtils.getRootCauseMessage(e) + ")");
      }
   }

   @Override
   public List<String> getEnabledSslProtocols() {
      try {
         String[] protocols = SSLContext.getDefault().createSSLEngine().getEnabledProtocols();
         if (null == protocols)
            return Collections.emptyList();
         return Arrays.asList(protocols);
      } catch (Exception e) {
         log.warn("cannot read enabled SSL protocols", e); 
         return Arrays.asList("Unknown (" + ExceptionUtils.getRootCauseMessage(e) + ")");
      }
   }
   
   @Override
   public String getGroovyVersion() {
      return GroovySystem.getVersion();
   }

   @Override
   public List<String> getStaticPams() {
      return pamProvider.get()
         .stream()
         .map(pam -> pam.getClass().getName())
         .collect(toList());
   }

   @Override
   public Map<Memory, Object> getMemoryValues() {
      final Map<Memory, Object> values = new HashMap<>();
      final int mb = 1024 * 1024;
      final Runtime runtime = Runtime.getRuntime();
      final long total = runtime.totalMemory();
      final long free = runtime.freeMemory();
      final long max = runtime.maxMemory();
      final long used = total - free;
      
      values.put(USED, used);
      values.put(USED_IN_MB, used / mb);
      values.put(USED_FORMATTED, byteCountToDisplaySize(used));
      
      values.put(TOTAL, total);
      values.put(TOTAL_IN_MB, total / mb);
      values.put(TOTAL_FORMATTED, byteCountToDisplaySize(total));
      
      values.put(FREE, free);
      values.put(FREE_IN_MB, free / mb);
      values.put(FREE_FORMATTED, byteCountToDisplaySize(free));
      
      values.put(MAX, max);
      values.put(MAX_IN_MB, max / mb);
      values.put(MAX_FORMATTED, byteCountToDisplaySize(max));
      return values;
   }

   @Override
   public String getConfigDirectory() {
      ConfigDirService configDirService = configDirServiceProvider.get();
      StringBuilder sb = new StringBuilder();
      sb.append(configDirService.isEnabled() ? configDirService.getConfigDir().getAbsolutePath() : "Not Configured");
      
      if (configDirService.isEnabled()) {
         sb.append(" (")
         .append(configDirService.getConfigDir().exists() && configDirService.getConfigDir().canRead() ? "OK)"
               : "INACCESSIBLE)");
      }
      return sb.toString();
   }

   @Override
   public String getServerName() {
      try {
         if (null == servletRequestProvider.get())
            return null;
         return servletRequestProvider.get().getServerName();
      } catch (ProvisionException e) {
         // guice OutOfScopeException in scheduler
         return null;
      }
   }

   @Override
   public int getServerPort() {
      try {
         if (null == servletRequestProvider.get())
            return -1;
         return servletRequestProvider.get().getServerPort();
      } catch (ProvisionException e) {
         // guice OutOfScopeException in scheduler
         return -1;
      }
   }

   @Override
   public String getRequestURL() {
      try {
         if (null == servletRequestProvider.get())
            return null;
         return servletRequestProvider.get().getRequestURL().toString();
      } catch (ProvisionException e) {
         // guice OutOfScopeException in scheduler
         return null;
      }
   }

   @Override
   public String getScheme() {
      try {
         if (null == servletRequestProvider.get())
            return null;
         return servletRequestProvider.get().getScheme();
      } catch (ProvisionException e) {
         // guice OutOfScopeException in scheduler
         return null;
      }
   }

   @Override
   public String getContextPath() {
      try {
         if (null == servletRequestProvider.get())
            return null;
         return servletRequestProvider.get().getContextPath();
      } catch (ProvisionException e) {
         // guice OutOfScopeException in scheduler
         return null;
      }
   }

   @Override
   public String getProtocol() {
      try {
         if (null == servletRequestProvider.get())
            return null;
         return servletRequestProvider.get().getProtocol();
      } catch (ProvisionException e) {
         // guice OutOfScopeException in scheduler
         return null;
      } 
   }

   @Override
   public String getCatalinaHome() {
      return readSystemProperty("catalina.home");
   }

   @Override
   public String getCatalinaBase() {
      return readSystemProperty("catalina.base");
   }

   @Override
   public String getJvmUserTimezone() {
      return readSystemProperty("user.timezone");
   }

   @Override
   public String getJvmUserCountry() {
      return readSystemProperty("user.country");
   }

   @Override
   public String getJvmUserLanguage() {
      return readSystemProperty("user.language");
   }

   @Override
   public String getJavaHome() {
      return readSystemProperty("java.home");
   }

   @Override
   public String getJvmFileEncoding() {
      return readSystemProperty("file.encoding");
   }
   
   private String readSystemProperty(String property) {
      try {
         return runtimeMxBean.getSystemProperties().get(property);
      } catch (SecurityException e) {
         log.warn("Cannot read " + property, e);
         return "Unknown (" + ExceptionUtils.getRootCauseMessage(e) + ")";
      }
   }

   @Override
   public String getLogFilesDirectory() {
      Path logDir = Paths.get(logFilesServiceProvider.get().getLogDirectory());
      StringBuilder sb = new StringBuilder();
      sb.append(logDir.toAbsolutePath().toString());
      
      sb.append(" (")
         .append((Files.exists(logDir) && Files.isReadable(logDir))? "OK)" : "INACCESSIBLE)");
      
      return sb.toString();
   }

}
