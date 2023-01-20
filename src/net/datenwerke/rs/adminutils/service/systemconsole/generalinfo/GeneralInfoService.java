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
   
   List<String> getStaticPams();

   String getRsVersion();
   
   String getLocale();
   
   String getJvmLocale();
   
   GeneralInfoDto getGeneralInfo();
   
   String getGroovyVersion();
   
   Map<Memory, Object> getMemoryValues();
   
   String getConfigDir();
}
