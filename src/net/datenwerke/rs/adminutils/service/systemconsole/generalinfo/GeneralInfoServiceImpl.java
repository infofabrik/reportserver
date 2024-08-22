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

import java.awt.GraphicsEnvironment;
import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.ProvisionException;

import groovy.lang.GroovySystem;
import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.adminutils.client.systemconsole.generalinfo.Memory;
import net.datenwerke.rs.adminutils.service.logs.LogFilesService;
import net.datenwerke.rs.adminutils.service.systemconsole.generalinfo.hooks.GeneralInfoCategoryProviderHook;
import net.datenwerke.rs.configservice.service.configservice.ConfigDirService;
import net.datenwerke.rs.license.service.LicenseService;
import net.datenwerke.rs.utils.file.RsFileUtils;
import net.datenwerke.rs.utils.localization.LocalizationServiceImpl;
import net.datenwerke.rs.utils.misc.DateUtils;

public class GeneralInfoServiceImpl implements GeneralInfoService {

   private final RuntimeMXBean runtimeMxBean = ManagementFactory.getRuntimeMXBean();

   private final Provider<ServletContext> servletContextProvider;
   private final Provider<HttpServletRequest> servletRequestProvider;
   private final Provider<LicenseService> licenseServiceProvider;
   private final Provider<ConfigDirService> configDirServiceProvider;
   private final Provider<LogFilesService> logFilesServiceProvider;
   private final Provider<HookHandlerService> hookHandlerServiceProvider;
   
   private final Logger log = LoggerFactory.getLogger( getClass() );
   
   @Inject
   public GeneralInfoServiceImpl(
         Provider<ServletContext> servletContextProvider,
         Provider<HttpServletRequest> servletRequestProvider, 
         Provider<LicenseService> licenseServiceProvider,
         Provider<ConfigDirService> configDirServiceProvider,
         Provider<LogFilesService> logFilesServiceProvider,
         Provider<HookHandlerService> hookHandlerServiceProvider
         ) {
      this.servletContextProvider = servletContextProvider;
      this.servletRequestProvider = servletRequestProvider;
      this.licenseServiceProvider = licenseServiceProvider;
      this.configDirServiceProvider = configDirServiceProvider;
      this.logFilesServiceProvider = logFilesServiceProvider;
      this.hookHandlerServiceProvider = hookHandlerServiceProvider;
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
   public Map<ImmutablePair<String, String>, Map<ImmutablePair<String, String>, Object>> getGeneralInfo() {
      return hookHandlerServiceProvider.get().getHookers(GeneralInfoCategoryProviderHook.class)
         .stream()
         .map(GeneralInfoCategoryProviderHook::provideCategory)
         .reduce( new LinkedHashMap<>(), (into, valuesToAdd) -> {
            into.putAll(valuesToAdd);
            return into;
         });
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
   public String getGroovyVersion() {
      return GroovySystem.getVersion();
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
   public String getConfigDirectory(boolean appendFileCheck) {
      ConfigDirService configDirService = configDirServiceProvider.get();
      if (!configDirService.isEnabled())
         return "Not Configured";
      
      Path configDir = configDirService.getConfigDir().toPath();
      return printPath(configDir, appendFileCheck);
   }
   
   @Override
   public String getIOTmpDir(boolean appendFileCheck) {
      String ioTmpDir = readSystemProperty("java.io.tmpdir");
      if (null == ioTmpDir)
         return "not set";
      
      Path dir = Paths.get(ioTmpDir);
      if (appendFileCheck)
         return RsFileUtils.appendFileCheck(dir);
      
      return dir.toAbsolutePath().toString();
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
   public String getRestURL() {
      String requestURL = getRequestURL();
      if (null == requestURL)
         return null;
      return requestURL.substring(0, requestURL.lastIndexOf("/")) + "/rest";
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
   public String getCatalinaHome(boolean appendFileCheck) {
      return printPath(readSystemProperty("catalina.home"), appendFileCheck);
   }

   @Override
   public String getCatalinaBase(boolean appendFileCheck) {
      return printPath(readSystemProperty("catalina.base"), appendFileCheck);
   }

   @Override
   public String getJvmUserTimezone() {
      return ZoneId.systemDefault().toString();
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
   public String getJavaHome(boolean appendFileCheck) {
      return printPath(readSystemProperty("java.home"), appendFileCheck);
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
   public String getLogFilesDirectory(boolean appendFileCheck) {
      Path logDir = Paths.get(logFilesServiceProvider.get().getLogDirectory());
      return printPath(logDir, appendFileCheck);
   }
   
   private String printPath(String path, boolean appendFileCheck) {
      if (null == path)
         return "not set";
      
      Path p = Paths.get(path);
      return printPath(p, appendFileCheck);
   }
   
   private String printPath(Path path, boolean appendFileCheck) {
      if (appendFileCheck)
         return RsFileUtils.appendFileCheck(path);
      
      return path.toAbsolutePath().toString(); 
   }
   
   @Override
   public String getUserHome(boolean appendFileCheck) {
      return printPath(readSystemProperty("user.home"), appendFileCheck);
   }

   @Override
   public String getNow() {
      return DateUtils.formatCurrentDate();
   }

   @Override
   public boolean isHeadless() {
      return GraphicsEnvironment.isHeadless();
   }

   @Override
   public List<String> getAvailableFonts() {
      return Arrays.asList(GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames());
   }

   @Override
   public List<String> getExternalJars() {
      ConfigDirService configDirService = configDirServiceProvider.get();
      if (!configDirService.isEnabled())
         return Collections.singletonList("Not configured");
      
      Path configDir = configDirService.getConfigDir().toPath();
      Path externalDir = configDir.resolve("lib");
      try {
         return listFiles(externalDir);
      } catch (Exception e) {
         return Collections.singletonList(ExceptionUtils.getRootCauseMessage(e));
      }
   }

   @Override
   public List<String> getInternalJars() {
      Path internalDir = Paths.get(servletContextProvider.get().getRealPath("/WEB-INF/lib"));
      try {
         return listFiles(internalDir);
      } catch (Exception e) {
         return Collections.singletonList(ExceptionUtils.getRootCauseMessage(e));
      }
   }
   
   private List<String> listFiles(Path dir) throws IOException {
      return Files.list(dir)
      .map(p -> p.getFileName().toString())
      .sorted()
      .collect(toList());
   }

   @Override
   public String getInstallationPath(boolean appendFileCheck) {
      Path dir = Paths.get(servletContextProvider.get().getRealPath("/"));
      return printPath(dir, appendFileCheck);
   }

}
