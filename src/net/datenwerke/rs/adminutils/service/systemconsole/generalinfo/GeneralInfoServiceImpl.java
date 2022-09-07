package net.datenwerke.rs.adminutils.service.systemconsole.generalinfo;

import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import com.google.inject.Inject;
import com.google.inject.Provider;

import eu.bitwalker.useragentutils.UserAgent;
import eu.bitwalker.useragentutils.Version;
import net.datenwerke.rs.adminutils.client.systemconsole.generalinfo.dto.GeneralInfoDto;
import net.datenwerke.rs.base.service.datasources.DatasourceHelperService;
import net.datenwerke.rs.base.service.datasources.definitions.DatabaseDatasource;
import net.datenwerke.rs.core.service.internaldb.TempTableService;
import net.datenwerke.rs.license.service.LicenseService;

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
   public String getBrowserName() {
      HttpServletRequest request = servletRequestProvider.get();
      UserAgent agent = UserAgent.parseUserAgentString(request.getHeader("User-Agent"));
      String browserName = agent.getBrowser().getGroup().toString();
      if (null != browserName)
         browserName = browserName.substring(0, 1).toUpperCase() + browserName.substring(1).toLowerCase();
      return browserName;
   }

   @Override
   public String getBrowserVersion() {
      HttpServletRequest request = servletRequestProvider.get();
      UserAgent agent = UserAgent.parseUserAgentString(request.getHeader("User-Agent"));
      Version version = agent.getBrowserVersion();
      if (null == version)
         return null;
      return version.getVersion();
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
      info.setBrowserName(getBrowserName());
      info.setBrowserVersion(getBrowserVersion());

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

}
