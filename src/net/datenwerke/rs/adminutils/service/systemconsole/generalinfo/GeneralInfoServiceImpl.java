package net.datenwerke.rs.adminutils.service.systemconsole.generalinfo;

import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.rs.adminutils.client.systemconsole.generalinfo.dto.GeneralInfoDto;
import net.datenwerke.rs.base.service.datasources.DatasourceHelperService;
import net.datenwerke.rs.base.service.datasources.definitions.DatabaseDatasource;
import net.datenwerke.rs.core.service.internaldb.TempTableService;
import net.datenwerke.rs.license.service.LicenseService;
import net.datenwerke.rs.utils.localization.LocalizationServiceImpl;

public class GeneralInfoServiceImpl implements GeneralInfoService {

   private final RuntimeMXBean runtimeMxBean = ManagementFactory.getRuntimeMXBean();

   private final Provider<ServletContext> servletContextProvider;
   private final Provider<HttpServletRequest> servletRequestProvider;
   private final Provider<LicenseService> licenseServiceProvider;
   private final Provider<DatasourceHelperService> datasourceHelperServiceProvider;
   private final Provider<TempTableService> tempTableServiceProvider;

   @Inject
   public GeneralInfoServiceImpl(
         Provider<ServletContext> servletContextProvider,
         Provider<HttpServletRequest> servletRequestProvider, 
         Provider<LicenseService> licenseServiceProvider,
         Provider<DatasourceHelperService> datasourceHelperServiceProvider,
         Provider<TempTableService> tempTableServiceProvider
         ) {
      this.servletContextProvider = servletContextProvider;
      this.servletRequestProvider = servletRequestProvider;
      this.licenseServiceProvider = licenseServiceProvider;
      this.datasourceHelperServiceProvider = datasourceHelperServiceProvider;
      this.tempTableServiceProvider = tempTableServiceProvider;
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

      Runtime runtime = Runtime.getRuntime();
      int mb = 1024 * 1024;

      info.setRsVersion(getRsVersion());
      info.setJavaVersion(getJavaVersion());
      info.setVmArguments(getVmArguments());
      info.setApplicationServer(getApplicationServer());
      info.setMaxMemory(NumberFormat.getIntegerInstance().format(runtime.maxMemory() / mb) + " MB");
      info.setOsVersion(getOsVersion());
      info.setUserAgent(getUserAgent());
      info.setLocale(getLocale());
      info.setJvmLocale(getJvmLocale());

      DatabaseDatasource internalDbDatasource = tempTableServiceProvider.get().getInternalDbDatasource();
      if (null == internalDbDatasource) {
         info.setInternalDbDatasourceName(errorMsg);
         return info;
      }
      
      try {
         Map<String, Object> datasourceMetadata = datasourceHelperService
               .fetchInfoDatasourceMetadata(internalDbDatasource);
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

}
