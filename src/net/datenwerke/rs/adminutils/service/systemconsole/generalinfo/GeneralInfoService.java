package net.datenwerke.rs.adminutils.service.systemconsole.generalinfo;

import java.util.List;
import java.util.Map;

import net.datenwerke.rs.adminutils.client.systemconsole.generalinfo.Memory;
import net.datenwerke.rs.adminutils.client.systemconsole.generalinfo.dto.GeneralInfoDto;

public interface GeneralInfoService {
   
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

   List<String> getSupportedSslProtocols();
   
   List<String> getDefaultSslProtocols();
   
   List<String> getEnabledSslProtocols();
   
   String getKnownHostsFile(boolean appendFileCheck);
   
   String getUserHome();
   
   List<String> getStaticPams();

   String getRsVersion();
   
   String getLocale();
   
   String getJvmLocale();
   
   GeneralInfoDto getGeneralInfo();
   
   String getGroovyVersion();
   
   Map<Memory, Object> getMemoryValues();
   
   String getConfigDirectory(boolean appendFileCheck);
   
   String getCatalinaHome();
   
   String getCatalinaBase();
   
   String getJvmUserTimezone();
   
   String getJvmUserCountry();
   
   String getJvmUserLanguage();
   
   String getJavaHome();
   
   String getJvmFileEncoding();
   
   String getLogFilesDirectory(boolean appendFileCheck);
   
}
