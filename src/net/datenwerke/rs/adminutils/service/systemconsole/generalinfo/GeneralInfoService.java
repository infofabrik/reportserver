package net.datenwerke.rs.adminutils.service.systemconsole.generalinfo;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.tuple.ImmutablePair;

import net.datenwerke.rs.adminutils.client.systemconsole.generalinfo.Memory;

public interface GeneralInfoService {

   String getRestURL();

   String getRequestURL();

   String getServerName();

   int getServerPort();

   String getScheme();

   String getContextPath();

   String getProtocol();

   String getJavaVersion();

   String getVmArguments();

   String getApplicationServer();

   String getOsVersion();

   String getUserAgent();

   String getUserHome(boolean appendFileCheck);

   String getRsVersion();

   String getLocale();

   String getJvmLocale();

   Map<ImmutablePair<String, String>, Map<ImmutablePair<String, String>, Object>> getGeneralInfo();

   String getGroovyVersion();

   Map<Memory, Object> getMemoryValues();

   String getConfigDirectory(boolean appendFileCheck);
   
   String getIOTmpDir(boolean appendFileCheck);

   String getCatalinaHome(boolean appendFileCheck);

   String getCatalinaBase(boolean appendFileCheck);

   String getJvmUserTimezone();

   String getJvmUserCountry();

   String getJvmUserLanguage();

   String getJavaHome(boolean appendFileCheck);

   String getJvmFileEncoding();

   String getLogFilesDirectory(boolean appendFileCheck);
   
   String getInstallationPath(boolean appendFileCheck);

   String getNow();
   
   boolean isHeadless();

   List<String> getAvailableFonts();
   
   List<String> getExternalJars();
   
   List<String> getInternalJars();

}
