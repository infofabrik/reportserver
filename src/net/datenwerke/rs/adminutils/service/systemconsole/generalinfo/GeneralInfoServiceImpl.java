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
import net.datenwerke.rs.adminutils.client.systemconsole.generalinfo.Memory;
import net.datenwerke.rs.adminutils.client.systemconsole.generalinfo.dto.GeneralInfoDto;
import net.datenwerke.rs.base.service.datasources.DatasourceHelperService;
import net.datenwerke.rs.base.service.datasources.definitions.DatabaseDatasource;
import net.datenwerke.rs.core.service.internaldb.TempTableService;
import net.datenwerke.rs.license.service.LicenseService;
import net.datenwerke.rs.utils.localization.LocalizationServiceImpl;
import net.datenwerke.security.service.authenticator.ReportServerPAM;

public class GeneralInfoServiceImpl implements GeneralInfoService {

   private final RuntimeMXBean runtimeMxBean = ManagementFactory.getRuntimeMXBean();

   private final Provider<ServletContext> servletContextProvider;
   private final Provider<HttpServletRequest> servletRequestProvider;
   private final Provider<LicenseService> licenseServiceProvider;
   private final Provider<DatasourceHelperService> datasourceHelperServiceProvider;
   private final Provider<TempTableService> tempTableServiceProvider;
   private final Provider<Set<ReportServerPAM>> pamProvider;
   private static final Logger log = LoggerFactory.getLogger( GeneralInfoServiceImpl.class );
   
   @Inject
   public GeneralInfoServiceImpl(
         Provider<ServletContext> servletContextProvider,
         Provider<HttpServletRequest> servletRequestProvider, 
         Provider<LicenseService> licenseServiceProvider,
         Provider<DatasourceHelperService> datasourceHelperServiceProvider,
         Provider<TempTableService> tempTableServiceProvider,
         Provider<Set<ReportServerPAM>> pamProvider
         ) {
      this.servletContextProvider = servletContextProvider;
      this.servletRequestProvider = servletRequestProvider;
      this.licenseServiceProvider = licenseServiceProvider;
      this.datasourceHelperServiceProvider = datasourceHelperServiceProvider;
      this.tempTableServiceProvider = tempTableServiceProvider;
      this.pamProvider = pamProvider;
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
      String errorMsg = "No internal database found. Check your /fileserver/etc/datasources/internaldb.cf configuration file.";
      DatasourceHelperService datasourceHelperService = datasourceHelperServiceProvider.get();
      
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

      DatabaseDatasource internalDbDatasource = tempTableServiceProvider.get().getInternalDbDatasource();
      if (null == internalDbDatasource) {
         info.setInternalDbDatasourceName(errorMsg);
         return info;
      }
      
      try {
         Map<String, Object> datasourceMetadata = datasourceHelperService
               .fetchInfoDatasourceMetadata(internalDbDatasource, true, true, true, true);
         info.setInternalDbId(internalDbDatasource.getId()+"");
         info.setInternalDbDatasourceName(internalDbDatasource.getName());
         info.setInternalDbDatabaseName(datasourceMetadata.get("getDatabaseProductName").toString());
         info.setInternalDbVersion(datasourceMetadata.get("getDatabaseProductVersion").toString());
         info.setInternalDbDriverName(datasourceMetadata.get("getDriverName").toString());
         info.setInternalDbDriverVersion(datasourceMetadata.get("getDriverVersion").toString());
         info.setInternalDbJdbcMajorVersion(datasourceMetadata.get("getJDBCMajorVersion").toString());
         info.setInternalDbJdbcMinorVersion(datasourceMetadata.get("getJDBCMinorVersion").toString());
         info.setInternalDbJdbcUrl(datasourceMetadata.get("getURL").toString());
         info.setInternalDbUsername(datasourceMetadata.get("getUserName").toString());
      } catch (SQLException e) {
         info.setInternalDbDatasourceName(errorMsg);
      }
      
      return info;
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
         return Arrays.asList(SSLContext.getDefault().getSupportedSSLParameters().getProtocols());
      } catch (Exception e) {
         log.warn(ExceptionUtils.getRootCauseMessage(e)); 
         return Collections.emptyList();
      }
   }

   @Override
   public List<String> getDefaultSslProtocols() {
      try {
         return Arrays.asList(SSLContext.getDefault().getDefaultSSLParameters().getProtocols());
      } catch (Exception e) {
         log.warn(ExceptionUtils.getRootCauseMessage(e)); 
         return Collections.emptyList();
      }
   }

   @Override
   public List<String> getEnabledSslProtocols() {
      try {
         return Arrays.asList(SSLContext.getDefault().createSSLEngine().getEnabledProtocols());
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

}
