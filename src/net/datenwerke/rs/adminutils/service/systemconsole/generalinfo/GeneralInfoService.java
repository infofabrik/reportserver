package net.datenwerke.rs.adminutils.service.systemconsole.generalinfo;

public interface GeneralInfoService {

   String getJavaVersion();

   String getVmArguments();

   String getApplicationServer();

   String getOsVersion();

   String getBrowserName();

   String getBrowserVersion();

   String getRsVersion();
}
