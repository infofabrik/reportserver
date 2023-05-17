package net.datenwerke.rs.adminutils.service.systemconsole.generalinfo.locale;

import net.datenwerke.rs.utils.localization.LocalizationServiceImpl;
import net.datenwerke.rs.utils.localization.Messages;

public interface GeneralInfoMessages extends Messages {

   public final static GeneralInfoMessages INSTANCE = LocalizationServiceImpl.getMessages(GeneralInfoMessages.class);

   String mainInfo();

   String versionLabel();

   String javaVersionLabel();

   String applicationServerLabel();

   String logsDirectory();

   String maxMemoryLabel();

   String configDirLabel();

   String groovyVersionLabel();

   String jvmUserLanguage();

   String jvmUserCountry();

   String jvmUserTimezone();

   String jvmFileEncoding();

   String jvmServerTime();

   String operatingSystemLabel();

   String userAgentLabel();

   String requestUrl();

   String scheme();

   String serverName();

   String serverPort();

   String contextPath();

   String protocol();

   String restUrl();

   String fonts();

}
