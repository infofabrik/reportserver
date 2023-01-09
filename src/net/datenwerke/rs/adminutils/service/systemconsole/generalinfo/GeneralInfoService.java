package net.datenwerke.rs.adminutils.service.systemconsole.generalinfo;

import java.util.List;

import net.datenwerke.rs.adminutils.client.systemconsole.generalinfo.dto.GeneralInfoDto;

public interface GeneralInfoService {

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
}
