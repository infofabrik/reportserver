package net.datenwerke.rs.adminutils.service.systemconsole.generalinfo;

import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import com.google.inject.Inject;
import com.google.inject.Provider;

import eu.bitwalker.useragentutils.UserAgent;
import net.datenwerke.rs.license.service.LicenseService;
import net.datenwerke.rsenterprise.license.service.annotations.EnterpriseChecked;
import net.datenwerke.security.service.security.SecurityService;

@EnterpriseChecked
public class GeneralInfoServiceImpl implements GeneralInfoService {

   private final RuntimeMXBean runtimeMxBean = ManagementFactory.getRuntimeMXBean();

   private final Provider<ServletContext> servletContextProvider;
   private final Provider<HttpServletRequest> servletRequestProvider;
   private final Provider<LicenseService> licenseServiceProvider;

   @Inject
   public GeneralInfoServiceImpl(
         Provider<ServletContext> servletContextProvider,
         Provider<HttpServletRequest> servletRequestProvider, 
         Provider<SecurityService> securityServiceProvider,
         Provider<LicenseService> licenseServiceProvider
         ) {
      this.servletContextProvider = servletContextProvider;
      this.servletRequestProvider = servletRequestProvider;
      this.licenseServiceProvider = licenseServiceProvider;
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
      return agent.getBrowserVersion().getVersion();
   }

   @Override
   public String getRsVersion() {
      return licenseServiceProvider.get().getRsVersion();
   }

}
