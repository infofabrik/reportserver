package net.datenwerke.rs.adminutils.service.systemconsole.generalinfo;

import net.datenwerke.rs.adminutils.client.systemconsole.generalinfo.dto.GeneralInfoDto;

public interface GeneralInfoService {

   String getJavaVersion();

   String getVmArguments();

   String getApplicationServer();

   String getOsVersion();

   String getBrowserName();

   String getBrowserVersion();

   String getRsVersion();
   
   GeneralInfoDto getGeneralInfo();
}
