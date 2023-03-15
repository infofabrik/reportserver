package net.datenwerke.rs.adminutils.service.systemconsole.generalinfo;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.tuple.ImmutablePair;

import net.datenwerke.rs.adminutils.client.systemconsole.generalinfo.Memory;
import net.datenwerke.rs.adminutils.client.systemconsole.generalinfo.dto.GeneralInfoDto;
import net.datenwerke.rs.core.service.reportmanager.entities.reports.Report;

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
   
   String getNow();
   
   ImmutablePair<Long, Long> getReportCount();
   
   ImmutablePair<Long, Long> getSpecificReportCount(Class<? extends Report> reportClazz, Class<? extends Report> variantClazz);
}
