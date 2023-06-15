package net.datenwerke.rs.adminutils.service.systemconsole.generalinfo.hookers;

import static net.datenwerke.rs.adminutils.client.systemconsole.generalinfo.Memory.MAX_FORMATTED;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.inject.Inject;

import org.apache.commons.lang3.tuple.ImmutablePair;

import net.datenwerke.rs.adminutils.service.systemconsole.generalinfo.GeneralInfoService;
import net.datenwerke.rs.adminutils.service.systemconsole.generalinfo.hooks.GeneralInfoCategoryProviderHook;
import net.datenwerke.rs.adminutils.service.systemconsole.generalinfo.locale.GeneralInfoMessages;

public class MainInfoCategoryProviderHooker implements GeneralInfoCategoryProviderHook {

   private final GeneralInfoService generalInfoService;
   
   private static final String RS_VERSION = "RS_VERSION";
   private static final String JAVA_VERSION = "JAVA_VERSION";
   private static final String JAVA_HOME = "JAVA_HOME";
   private static final String JVM_ARGS = "JVM_ARGS";
   private static final String USER_HOME = "USER_HOME";
   private static final String APPLICATION_SERVER = "APPLICATION_SERVER";
   private static final String CATALINA_HOME = "CATALINA_HOME";
   private static final String CATALINA_BASE = "CATALINA_BASE";
   private static final String LOG_FILES_DIRECTORY = "LOG_FILES_DIRECTORY";
   private static final String HEADLESS = "HEADLESS";
   private static final String MAX_MEMORY = "MAX_MEMORY";
   private static final String CONFIG_DIR = "CONFIG_DIR";
   private static final String GROOVY_VERSION = "GROOVY_VERSION";
   private static final String LOCALE = "LOCALE";
   private static final String JVM_LOCALE = "JVM_LOCALE";
   private static final String JVM_USER_LANGUAGE = "JVM_USER_LANGUAGE";
   private static final String JVM_USER_COUNTRY = "JVM_USER_COUNTRY";
   private static final String JVM_USER_TIMEZONE = "JVM_USER_TIMEZONE";
   private static final String JVM_FILE_ENCODING = "JVM_FILE_ENCODING";
   private static final String JVM_SERVER_TIME = "JVM_SERVER_TIME";
   private static final String OPERATING_SYSTEM = "OPERATING_SYSTEM";
   private static final String USER_AGENT = "USER_AGENT";
   private static final String REST_URL = "REST_URL";
   private static final String REQUEST_URL = "REQUEST_URL";
   private static final String REQUEST_SCHEME = "REQUEST_SCHEME";
   private static final String REQUEST_SERVER_NAME = "REQUEST_SERVER_NAME";
   private static final String REQUEST_SERVER_PORT = "REQUEST_SERVER_PORT";
   private static final String REQUEST_CONTEXT_PATH = "REQUEST_CONTEXT_PATH";
   private static final String REQUEST_PROTOCOL = "REQUEST_PROTOCOL";
   private static final String FONTS = "FONTS";
   private static final String MAIN = "MAIN";
   
   @Inject
   public MainInfoCategoryProviderHooker(
         GeneralInfoService generalInfoService
         ) {
      this.generalInfoService = generalInfoService;
   }
   
   @Override
   public Map<ImmutablePair<String, String>, Map<ImmutablePair<String, String>, Object>> provideCategory() {
      final Map<ImmutablePair<String, String>, Object> props = new LinkedHashMap<>();

      props.put(ImmutablePair.of(RS_VERSION, GeneralInfoMessages.INSTANCE.reportServerVersion()),
            generalInfoService.getRsVersion());
      props.put(ImmutablePair.of(JAVA_VERSION, GeneralInfoMessages.INSTANCE.javaVersionLabel()),
            generalInfoService.getJavaVersion());
      props.put(ImmutablePair.of(JAVA_HOME, "Java home"), generalInfoService.getJavaHome());
      props.put(ImmutablePair.of(JVM_ARGS, "JVM args"), generalInfoService.getVmArguments());
      props.put(ImmutablePair.of(USER_HOME, "User home"), generalInfoService.getUserHome(true));
      props.put(ImmutablePair.of(APPLICATION_SERVER, GeneralInfoMessages.INSTANCE.applicationServerLabel()),
            generalInfoService.getApplicationServer());
      props.put(ImmutablePair.of(CATALINA_HOME, "Catalina home"), generalInfoService.getCatalinaHome());
      props.put(ImmutablePair.of(CATALINA_BASE, "Catalina base"), generalInfoService.getCatalinaBase());
      props.put(ImmutablePair.of(LOG_FILES_DIRECTORY, GeneralInfoMessages.INSTANCE.logsDirectory()),
            generalInfoService.getLogFilesDirectory(true));
      props.put(ImmutablePair.of(HEADLESS, "Headless"),
            generalInfoService.isHeadless());
      props.put(ImmutablePair.of(MAX_MEMORY, GeneralInfoMessages.INSTANCE.maxMemoryLabel()),
            generalInfoService.getMemoryValues().get(MAX_FORMATTED));
      props.put(ImmutablePair.of(CONFIG_DIR, GeneralInfoMessages.INSTANCE.configDirLabel()),
            generalInfoService.getConfigDirectory(true));
      props.put(ImmutablePair.of(GROOVY_VERSION, GeneralInfoMessages.INSTANCE.groovyVersionLabel()),
            generalInfoService.getGroovyVersion());
      props.put(ImmutablePair.of(LOCALE, "Locale"), generalInfoService.getLocale());
      props.put(ImmutablePair.of(JVM_LOCALE, "JVM locale"), generalInfoService.getJvmLocale());
      props.put(ImmutablePair.of(JVM_USER_LANGUAGE, GeneralInfoMessages.INSTANCE.jvmUserLanguage()),
            generalInfoService.getJvmUserLanguage());
      props.put(ImmutablePair.of(JVM_USER_COUNTRY, GeneralInfoMessages.INSTANCE.jvmUserCountry()),
            generalInfoService.getJvmUserCountry());
      props.put(ImmutablePair.of(JVM_USER_TIMEZONE, GeneralInfoMessages.INSTANCE.jvmUserTimezone()),
            generalInfoService.getJvmUserTimezone());
      props.put(ImmutablePair.of(JVM_FILE_ENCODING, GeneralInfoMessages.INSTANCE.jvmFileEncoding()),
            generalInfoService.getJvmFileEncoding());
      props.put(ImmutablePair.of(JVM_SERVER_TIME, GeneralInfoMessages.INSTANCE.jvmServerTime()),
            generalInfoService.getNow());
      props.put(ImmutablePair.of(OPERATING_SYSTEM, GeneralInfoMessages.INSTANCE.operatingSystemLabel()),
            generalInfoService.getOsVersion());
      props.put(ImmutablePair.of(USER_AGENT, GeneralInfoMessages.INSTANCE.userAgentLabel()),
            generalInfoService.getUserAgent());
      props.put(ImmutablePair.of(REST_URL, GeneralInfoMessages.INSTANCE.restUrl()), generalInfoService.getRestURL());
      props.put(ImmutablePair.of(REQUEST_URL, GeneralInfoMessages.INSTANCE.requestUrl()),
            generalInfoService.getRequestURL());
      props.put(ImmutablePair.of(REQUEST_SCHEME, GeneralInfoMessages.INSTANCE.scheme()),
            generalInfoService.getScheme());
      props.put(ImmutablePair.of(REQUEST_SERVER_NAME, GeneralInfoMessages.INSTANCE.serverName()),
            generalInfoService.getServerName());
      props.put(ImmutablePair.of(REQUEST_SERVER_PORT, GeneralInfoMessages.INSTANCE.serverPort()),
            generalInfoService.getServerPort());
      props.put(ImmutablePair.of(REQUEST_CONTEXT_PATH, GeneralInfoMessages.INSTANCE.contextPath()),
            generalInfoService.getContextPath());
      props.put(ImmutablePair.of(REQUEST_PROTOCOL, GeneralInfoMessages.INSTANCE.protocol()),
            generalInfoService.getProtocol());
      props.put(ImmutablePair.of(FONTS, GeneralInfoMessages.INSTANCE.fonts()),
            generalInfoService.getAvailableFonts());

      return Collections.singletonMap(ImmutablePair.of(MAIN, GeneralInfoMessages.INSTANCE.mainInfo()), props);
   }

}
