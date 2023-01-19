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

import groovy.lang.GroovySystem;
import net.datenwerke.gf.service.history.HistoryService;
import net.datenwerke.rs.EnvironmentValidatorHelperService;
import net.datenwerke.rs.adminutils.client.systemconsole.generalinfo.Memory;
import net.datenwerke.rs.adminutils.client.systemconsole.generalinfo.dto.GeneralInfoDto;
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
   
   private static final Logger log = LoggerFactory.getLogger( GeneralInfoServiceImpl.class );
   
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
         Provider<ConfigDirService> configDirServiceProvider
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
   }

   @Override
   public String getJavaVersion() {
      return runtimeMxBean.getVmVendor() + " " + runtimeMxBean.getVmName() + " " + runtimeMxBean.getVmVersion() + " ("
            + runtimeMxBean.getSpecVersion() + ")";
   }

   @Override
   public String getVmArguments() {
      return String.join(" ", runtimeMxBean.getInputArguments());
   }

   @Override
   public String getApplicationServer() {
      return servletContextProvider.get().getServerInfo();
   }

   @Override
   public String getOsVersion() {
      return System.getProperty("os.name");
   }

   @Override
   public String getUserAgent() {
      HttpServletRequest request = servletRequestProvider.get();
      return request.getHeader("User-Agent");
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
      info.setMaxMemory(getMemoryValues().get(MAX_FORMATTED)+ "");
      info.setOsVersion(getOsVersion());
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
      
      info.setConfigDir(getConfigDir());
      
      return info;
   }
   
   private void setInternalDb(GeneralInfoDto info) {
      String errorMsg = "No internal database found. Check your /fileserver/etc/datasources/internaldb.cf configuration file.";
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
            info.setInternalDbJdbcProperties(new HashMap<String,String>() {{
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
         log.warn(ExceptionUtils.getRootCauseMessage(e)); 
         return Collections.emptyList();
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
         log.warn(ExceptionUtils.getRootCauseMessage(e)); 
         return Collections.emptyList();
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
         log.warn(ExceptionUtils.getRootCauseMessage(e)); 
         return Collections.emptyList();
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
   public String getConfigDir() {
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

}
