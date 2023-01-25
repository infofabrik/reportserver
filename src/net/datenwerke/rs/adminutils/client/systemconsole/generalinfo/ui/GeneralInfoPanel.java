package net.datenwerke.rs.adminutils.client.systemconsole.generalinfo.ui;

import static java.util.stream.Collectors.joining;

import java.util.AbstractMap.SimpleImmutableEntry;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

import com.google.gwt.core.client.Scheduler;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.sencha.gxt.core.client.dom.ScrollSupport.ScrollMode;
import com.sencha.gxt.core.client.util.Margins;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer.VerticalLayoutData;
import com.sencha.gxt.widget.core.client.form.FormPanel.LabelAlign;

import net.datenwerke.gxtdto.client.baseex.widget.DwContentPanel;
import net.datenwerke.gxtdto.client.dtomanager.callback.RsAsyncCallback;
import net.datenwerke.gxtdto.client.forms.simpleform.SimpleForm;
import net.datenwerke.gxtdto.client.forms.simpleform.providers.configs.SFFCSpace;
import net.datenwerke.gxtdto.client.forms.simpleform.providers.configs.SFFCStaticLabel;
import net.datenwerke.gxtdto.client.forms.simpleform.providers.dummy.Separator;
import net.datenwerke.gxtdto.client.forms.simpleform.providers.dummy.StaticLabel;
import net.datenwerke.gxtdto.client.locale.BaseMessages;
import net.datenwerke.rs.adminutils.client.systemconsole.generalinfo.GeneralInfoDao;
import net.datenwerke.rs.adminutils.client.systemconsole.generalinfo.dto.GeneralInfoDto;
import net.datenwerke.rs.adminutils.client.systemconsole.locale.SystemConsoleMessages;

/**
 * 
 *
 */
@Singleton
public class GeneralInfoPanel extends DwContentPanel {

   private final GeneralInfoDao generalInfoDao;

   private VerticalLayoutContainer wrapper;
   
   private enum Type {
      RS_VERSION, JAVA_VERSION, JAVA_HOME, JAVA_VM_ARGUMENTS, APPLICATION_SERVER, CATALINA_HOME, CATALINA_BASE, LOG_FILES_DIRECTORY,
      REQUEST_URL, REQUEST_SCHEME, REQUEST_SERVER_NAME, REQUEST_SERVER_PORT, REQUEST_CONTEXT_PATH, REQUEST_PROTOCOL,
      MAX_MEMORY, CONFIG_DIR, GROOVY_VERSION, LOCALE, JVM_LOCALE, JVM_USER_LANGUAGE, JVM_USER_COUNTRY,
      JVM_USER_TIMEZONE, JVM_FILE_ENCODING, JVM_SERVER_TIME, OS_SYSTEM, USER_AGENT, STATIC_PAMS, HIBERNATE_DIALECT,
      HIBERNATE_DB_DATABASE_NAME, HIBERNATE_DB_DATABASE_VERSION,
      HIBERNATE_DB_DRIVER_NAME, HIBERNATE_DB_DRIVER_VERSION, HIBERNATE_DB_JDBC_MAJOR_VERSION,
      HIBERNATE_DB_JDBC_MINOR_VERSION, HIBERNATE_DB_JDBC_URL, HIBERNATE_DB_JDBC_USERNAME,
      HIBERNATE_SCHEMA, SCHEMA_VERSION, INTERNAL_DB_NAME,
      INTERNAL_DB_ID, INTERNAL_DB_PATH, INTERNAL_DB_DATABASE_NAME, INTERNAL_DB_DATABASE_VERSION,
      INTERNAL_DB_DRIVER_NAME, INTERNAL_DB_DRIVER_VERSION, INTERNAL_DB_JDBC_MAJOR_VERSION,
      INTERNAL_DB_JDBC_MINOR_VERSION, INTERNAL_DB_JDBC_URL, INTERNAL_DB_USERNAME, INTERNAL_DB_JDBC_PROPERTIES,
      KNOWN_HOSTS_FILE, SUPPORTED_SSL_PROTOCOLS, DEFAULT_SSL_PROTOCOLS, ENABLED_SSL_PROTOCOLS,
      
      SFTP_SERVER_DISABLED, SFTP_SERVER_PORT, SFTP_SERVER_KEY
   }

   @Inject
   public GeneralInfoPanel(GeneralInfoDao licenseDao) {

      this.generalInfoDao = licenseDao;

      /* initialize ui */
      initializeUI();
   }

   private void initializeUI() {
      setHeading(SystemConsoleMessages.INSTANCE.generalInfo());
      addStyleName("rs-generalinfo");

      wrapper = new VerticalLayoutContainer();
      wrapper.setScrollMode(ScrollMode.AUTOY);
      add(wrapper);

      updateView();
   }

   protected void updateView() {
      mask(BaseMessages.INSTANCE.loadingMsg());

      wrapper.clear();

      generalInfoDao.loadGeneralInfo(new RsAsyncCallback<GeneralInfoDto>() {
         @Override
         public void onSuccess(GeneralInfoDto result) {
            super.onSuccess(result);
            init(result);
            unmask();
         }

         @Override
         public void onFailure(Throwable caught) {
            super.onFailure(caught);
            unmask();
         }
      });
   }

   protected void init(final GeneralInfoDto generalInfoDto) {
      final SimpleForm form = SimpleForm.getNewInstance();
      form.setHeading(SystemConsoleMessages.INSTANCE.generalInfo());
      form.setLabelAlign(LabelAlign.LEFT);

      form.setLabelWidth(200);

      final Map<Type, SimpleImmutableEntry<String, Optional<Object>>> generalInfo = new LinkedHashMap<>();
      generalInfo.put(Type.RS_VERSION, new SimpleImmutableEntry<String, Optional<Object>>(
            SystemConsoleMessages.INSTANCE.versionLabel(), Optional.of(generalInfoDto.getRsVersion())));
      generalInfo.put(Type.JAVA_VERSION, new SimpleImmutableEntry<String, Optional<Object>>(
            SystemConsoleMessages.INSTANCE.javaVersionLabel(), Optional.of(generalInfoDto.getJavaVersion())));
      generalInfo.put(Type.JAVA_HOME, new SimpleImmutableEntry<String, Optional<Object>>(
            "Java home", Optional.of(generalInfoDto.getJavaHome())));
      generalInfo.put(Type.JAVA_VM_ARGUMENTS,
            new SimpleImmutableEntry<String, Optional<Object>>("JVM Args", Optional.of(generalInfoDto.getVmArguments())));
      generalInfo.put(Type.APPLICATION_SERVER, new SimpleImmutableEntry<String, Optional<Object>>(
            SystemConsoleMessages.INSTANCE.applicationServerLabel(), Optional.of(generalInfoDto.getApplicationServer())));
      generalInfo.put(Type.CATALINA_HOME, new SimpleImmutableEntry<String, Optional<Object>>(
            "Catalina home", Optional.of(generalInfoDto.getCatalinaHome())));
      generalInfo.put(Type.CATALINA_BASE, new SimpleImmutableEntry<String, Optional<Object>>(
            "Catalina base", Optional.of(generalInfoDto.getCatalinaBase())));
      generalInfo.put(Type.LOG_FILES_DIRECTORY, new SimpleImmutableEntry<String, Optional<Object>>(
            SystemConsoleMessages.INSTANCE.logsDirectory(), Optional.of(generalInfoDto.getLogFilesDirectory())));
      generalInfo.put(Type.MAX_MEMORY, new SimpleImmutableEntry<String, Optional<Object>>(
            SystemConsoleMessages.INSTANCE.maxMemoryLabel(), Optional.of(generalInfoDto.getMaxMemory())));
      generalInfo.put(Type.CONFIG_DIR, new SimpleImmutableEntry<String, Optional<Object>>(
            SystemConsoleMessages.INSTANCE.configDirLabel(), Optional.of(generalInfoDto.getConfigDir())));
      generalInfo.put(Type.GROOVY_VERSION, new SimpleImmutableEntry<String, Optional<Object>>(
            SystemConsoleMessages.INSTANCE.groovyVersionLabel(), Optional.of(generalInfoDto.getGroovyVersion())));
      generalInfo.put(Type.LOCALE,
            new SimpleImmutableEntry<String, Optional<Object>>("Locale", Optional.of(generalInfoDto.getLocale())));
      generalInfo.put(Type.JVM_LOCALE,
            new SimpleImmutableEntry<String, Optional<Object>>("JVM locale", Optional.of(generalInfoDto.getJvmLocale())));
      generalInfo.put(Type.JVM_USER_LANGUAGE, new SimpleImmutableEntry<String, Optional<Object>>(
            SystemConsoleMessages.INSTANCE.jvmUserLanguage(), Optional.of(generalInfoDto.getJvmUserLanguage())));
      generalInfo.put(Type.JVM_USER_COUNTRY, new SimpleImmutableEntry<String, Optional<Object>>(
            SystemConsoleMessages.INSTANCE.jvmUserCountry(), Optional.of(generalInfoDto.getJvmUserCountry())));
      generalInfo.put(Type.JVM_USER_TIMEZONE, new SimpleImmutableEntry<String, Optional<Object>>(
            SystemConsoleMessages.INSTANCE.jvmUserTimezone(), Optional.of(generalInfoDto.getJvmUserTimezone())));
      generalInfo.put(Type.JVM_FILE_ENCODING, new SimpleImmutableEntry<String, Optional<Object>>(
            SystemConsoleMessages.INSTANCE.jvmFileEncoding(), Optional.of(generalInfoDto.getJvmFileEncoding())));
      generalInfo.put(Type.JVM_SERVER_TIME, new SimpleImmutableEntry<String, Optional<Object>>(
            SystemConsoleMessages.INSTANCE.jvmServerTime(), Optional.of(generalInfoDto.getNow())));
      generalInfo.put(Type.OS_SYSTEM, new SimpleImmutableEntry<String, Optional<Object>>(
            SystemConsoleMessages.INSTANCE.operationSystemLabel(), Optional.of(generalInfoDto.getOsVersion())));
      generalInfo.put(Type.USER_AGENT, new SimpleImmutableEntry<String, Optional<Object>>(
            SystemConsoleMessages.INSTANCE.userAgentLabel(), Optional.of(generalInfoDto.getUserAgent())));
      generalInfo.put(Type.REQUEST_URL, new SimpleImmutableEntry<String, Optional<Object>>(
            SystemConsoleMessages.INSTANCE.requestUrl(), Optional.of(generalInfoDto.getRequestURL())));
      generalInfo.put(Type.REQUEST_SCHEME, new SimpleImmutableEntry<String, Optional<Object>>(
            SystemConsoleMessages.INSTANCE.scheme(), Optional.of(generalInfoDto.getScheme())));
      generalInfo.put(Type.REQUEST_SERVER_NAME, new SimpleImmutableEntry<String, Optional<Object>>(
            SystemConsoleMessages.INSTANCE.serverName(), Optional.of(generalInfoDto.getServerName())));
      generalInfo.put(Type.REQUEST_SERVER_PORT,
            new SimpleImmutableEntry<String, Optional<Object>>(SystemConsoleMessages.INSTANCE.serverPort(),
                  generalInfoDto.getServerPort() != -1 ? Optional.of(generalInfoDto.getServerPort()) : Optional.empty()));
      generalInfo.put(Type.REQUEST_CONTEXT_PATH, new SimpleImmutableEntry<String, Optional<Object>>(
            SystemConsoleMessages.INSTANCE.contextPath(), Optional.of(generalInfoDto.getContextPath())));
      generalInfo.put(Type.REQUEST_PROTOCOL, new SimpleImmutableEntry<String, Optional<Object>>(
            SystemConsoleMessages.INSTANCE.protocol(), Optional.of(generalInfoDto.getProtocol())));
      generalInfo.forEach((key, pair) -> addFieldToForm(pair.getKey(), pair.getValue(), form));

      form.addField(Separator.class, new SFFCSpace());
      form.addField(Separator.class, new SFFCSpace());

      final Map<Type, SimpleImmutableEntry<String, Optional<Object>>> pamsInfo = new LinkedHashMap<>();
      pamsInfo.put(Type.STATIC_PAMS,
            new SimpleImmutableEntry<String, Optional<Object>>(SystemConsoleMessages.INSTANCE.staticPamsLabel(),
                  Optional.ofNullable(generalInfoDto.getStaticPams().stream().collect(joining(", ")))));
      pamsInfo.forEach((key, pair) -> addFieldToForm(pair.getKey(), pair.getValue(), form));

      form.addField(Separator.class, new SFFCSpace());
      form.addField(Separator.class, new SFFCSpace());

      form.addField(StaticLabel.class, SystemConsoleMessages.INSTANCE.dbConfig(), new SFFCStaticLabel() {
         @Override
         public String getLabel() {
            return "";
         }
      });

      final Map<Type, SimpleImmutableEntry<String, Optional<Object>>> dbConfigInfo = new LinkedHashMap<>();
      dbConfigInfo.put(Type.HIBERNATE_DIALECT, new SimpleImmutableEntry<String, Optional<Object>>("hibernate.dialect",
            Optional.ofNullable(generalInfoDto.getHibernateDialect())));
      dbConfigInfo.put(Type.HIBERNATE_SCHEMA, new SimpleImmutableEntry<String, Optional<Object>>(
            "hibernate.default_schema", Optional.ofNullable(generalInfoDto.getHibernateDefaultSchema())));
      dbConfigInfo.put(Type.HIBERNATE_DB_DATABASE_NAME,
            new SimpleImmutableEntry<String, Optional<Object>>(SystemConsoleMessages.INSTANCE.internalDbName(),
                  Optional.ofNullable(generalInfoDto.getHibernateDbDatabaseName())));
      dbConfigInfo.put(Type.HIBERNATE_DB_DATABASE_VERSION, new SimpleImmutableEntry<String, Optional<Object>>(
            SystemConsoleMessages.INSTANCE.internalDbVersion(), Optional.ofNullable(generalInfoDto.getHibernateDbVersion())));
      dbConfigInfo.put(Type.HIBERNATE_DB_DRIVER_NAME,
            new SimpleImmutableEntry<String, Optional<Object>>(SystemConsoleMessages.INSTANCE.internalDbDriverName(),
                  Optional.ofNullable(generalInfoDto.getHibernateDbDriverName())));
      dbConfigInfo.put(Type.HIBERNATE_DB_DRIVER_VERSION,
            new SimpleImmutableEntry<String, Optional<Object>>(
                  SystemConsoleMessages.INSTANCE.internalDbDriverVersion(),
                  Optional.ofNullable(generalInfoDto.getHibernateDbDriverVersion())));
      dbConfigInfo.put(Type.HIBERNATE_DB_JDBC_MAJOR_VERSION,
            new SimpleImmutableEntry<String, Optional<Object>>(
                  SystemConsoleMessages.INSTANCE.internalDbJdbcMajorVersion(),
                  Optional.ofNullable(generalInfoDto.getHibernateDbJdbcMajorVersion())));
      dbConfigInfo.put(Type.HIBERNATE_DB_JDBC_MINOR_VERSION,
            new SimpleImmutableEntry<String, Optional<Object>>(
                  SystemConsoleMessages.INSTANCE.internalDbJdbcMinorVersion(),
                  Optional.ofNullable(generalInfoDto.getHibernateDbJdbcMinorVersion())));
      dbConfigInfo.put(Type.HIBERNATE_DB_JDBC_URL, new SimpleImmutableEntry<String, Optional<Object>>(
            SystemConsoleMessages.INSTANCE.internalDbJdbcUrl(), Optional.ofNullable(generalInfoDto.getHibernateDbJdbcUrl())));
      dbConfigInfo.put(Type.INTERNAL_DB_USERNAME,
            new SimpleImmutableEntry<String, Optional<Object>>(SystemConsoleMessages.INSTANCE.internalDbUsername(),
                  Optional.ofNullable(generalInfoDto.getHibernateDbJdbcUsername())));

      dbConfigInfo.put(Type.SCHEMA_VERSION, new SimpleImmutableEntry<String, Optional<Object>>(
            SystemConsoleMessages.INSTANCE.schemaVersion(), Optional.ofNullable(generalInfoDto.getSchemaVersion())));
      dbConfigInfo.forEach((key, pair) -> addFieldToForm(pair.getKey(), pair.getValue(), form));

      form.addField(Separator.class, new SFFCSpace());
      form.addField(Separator.class, new SFFCSpace());

      form.addField(StaticLabel.class, SystemConsoleMessages.INSTANCE.internalDb(), new SFFCStaticLabel() {
         @Override
         public String getLabel() {
            return "";
         }
      });

      final Map<Type, SimpleImmutableEntry<String, Optional<Object>>> internalDbInfo = new LinkedHashMap<>();
      internalDbInfo.put(Type.INTERNAL_DB_NAME, new SimpleImmutableEntry<String, Optional<Object>>(
            BaseMessages.INSTANCE.name(), Optional.ofNullable(generalInfoDto.getInternalDbDatasourceName())));
      if (generalInfoDto.isInternalDbConfigured())
         internalDbInfo.put(Type.INTERNAL_DB_ID, new SimpleImmutableEntry<String, Optional<Object>>(
               BaseMessages.INSTANCE.id(), Optional.ofNullable(generalInfoDto.getInternalDbId())));
      if (generalInfoDto.isInternalDbConfigured())
         internalDbInfo.put(Type.INTERNAL_DB_PATH, new SimpleImmutableEntry<String, Optional<Object>>(
               BaseMessages.INSTANCE.path(), Optional.ofNullable(generalInfoDto.getInternalDbPath())));
      if (generalInfoDto.isInternalDbConfigured())
         internalDbInfo.put(Type.INTERNAL_DB_DATABASE_NAME,
               new SimpleImmutableEntry<String, Optional<Object>>(SystemConsoleMessages.INSTANCE.internalDbName(),
                     Optional.ofNullable(generalInfoDto.getInternalDbDatabaseName())));
      if (generalInfoDto.isInternalDbConfigured())
         internalDbInfo.put(Type.INTERNAL_DB_DATABASE_VERSION, new SimpleImmutableEntry<String, Optional<Object>>(
               SystemConsoleMessages.INSTANCE.internalDbVersion(), Optional.ofNullable(generalInfoDto.getInternalDbVersion())));
      if (generalInfoDto.isInternalDbConfigured())
         internalDbInfo.put(Type.INTERNAL_DB_DRIVER_NAME,
               new SimpleImmutableEntry<String, Optional<Object>>(SystemConsoleMessages.INSTANCE.internalDbDriverName(),
                     Optional.ofNullable(generalInfoDto.getInternalDbDriverName())));
      if (generalInfoDto.isInternalDbConfigured())
         internalDbInfo.put(Type.INTERNAL_DB_DRIVER_VERSION,
               new SimpleImmutableEntry<String, Optional<Object>>(
                     SystemConsoleMessages.INSTANCE.internalDbDriverVersion(),
                     Optional.ofNullable(generalInfoDto.getInternalDbDriverVersion())));
      if (generalInfoDto.isInternalDbConfigured())
         internalDbInfo.put(Type.INTERNAL_DB_JDBC_MAJOR_VERSION,
               new SimpleImmutableEntry<String, Optional<Object>>(
                     SystemConsoleMessages.INSTANCE.internalDbJdbcMajorVersion(),
                     Optional.ofNullable(generalInfoDto.getInternalDbJdbcMajorVersion())));
      if (generalInfoDto.isInternalDbConfigured())
         internalDbInfo.put(Type.INTERNAL_DB_JDBC_MINOR_VERSION,
               new SimpleImmutableEntry<String, Optional<Object>>(
                     SystemConsoleMessages.INSTANCE.internalDbJdbcMinorVersion(),
                     Optional.ofNullable(generalInfoDto.getInternalDbJdbcMinorVersion())));
      if (generalInfoDto.isInternalDbConfigured())
         internalDbInfo.put(Type.INTERNAL_DB_JDBC_URL, new SimpleImmutableEntry<String, Optional<Object>>(
               SystemConsoleMessages.INSTANCE.internalDbJdbcUrl(), Optional.ofNullable(generalInfoDto.getInternalDbJdbcUrl())));
      if (generalInfoDto.isInternalDbConfigured())
         internalDbInfo.put(Type.INTERNAL_DB_USERNAME,
               new SimpleImmutableEntry<String, Optional<Object>>(SystemConsoleMessages.INSTANCE.internalDbUsername(),
                     Optional.ofNullable(generalInfoDto.getInternalDbUsername())));
      if (generalInfoDto.isInternalDbConfigured())
         internalDbInfo.put(Type.INTERNAL_DB_JDBC_PROPERTIES,
               new SimpleImmutableEntry<String, Optional<Object>>(
                     SystemConsoleMessages.INSTANCE.internalDbJdbcProperties(),
                     Optional.ofNullable(generalInfoDto.getInternalDbJdbcProperties())));

      internalDbInfo.forEach((key, pair) -> addFieldToForm(pair.getKey(), pair.getValue(), form));
      
      form.addField(Separator.class, new SFFCSpace());
      form.addField(Separator.class, new SFFCSpace());
      
      form.addField(StaticLabel.class, SystemConsoleMessages.INSTANCE.sftpServer(), new SFFCStaticLabel() {
         @Override
         public String getLabel() {
            return "";
         }
      });
      
      final Map<Type, SimpleImmutableEntry<String, Optional<Object>>> sftpInfo = new LinkedHashMap<>();
      if (!generalInfoDto.isSftpEnabled()) {
         sftpInfo.put(Type.SFTP_SERVER_DISABLED, new SimpleImmutableEntry<String, Optional<Object>>(
               SystemConsoleMessages.INSTANCE.disabled(), Optional.of(true)));
      } else {
         sftpInfo.put(Type.SFTP_SERVER_KEY, new SimpleImmutableEntry<String, Optional<Object>>(
               SystemConsoleMessages.INSTANCE.sftpKey(), Optional.ofNullable(generalInfoDto.getSftpKey())));
         sftpInfo.put(Type.SFTP_SERVER_PORT, new SimpleImmutableEntry<String, Optional<Object>>(
               SystemConsoleMessages.INSTANCE.sftpPort(), Optional.ofNullable(generalInfoDto.getSftpPort())));
      }
      sftpInfo.forEach((key, pair) -> addFieldToForm(pair.getKey(), pair.getValue(), form));
      
      form.addField(Separator.class, new SFFCSpace());
      form.addField(Separator.class, new SFFCSpace());
      
      form.addField(StaticLabel.class, "SSL/SSH", new SFFCStaticLabel() {
         @Override
         public String getLabel() {
            return "";
         }
      });
      
      final Map<Type, SimpleImmutableEntry<String, Optional<Object>>> sslInfo = new LinkedHashMap<>();
      sslInfo.put(Type.KNOWN_HOSTS_FILE,
            new SimpleImmutableEntry<String, Optional<Object>>(SystemConsoleMessages.INSTANCE.knownHostsFile(),
                  Optional.of(generalInfoDto.getSslKnownHosts())));
      sslInfo.put(Type.SUPPORTED_SSL_PROTOCOLS,
            new SimpleImmutableEntry<String, Optional<Object>>(SystemConsoleMessages.INSTANCE.supportedSslProtocols(),
                  Optional.of(generalInfoDto.getSupportedSslProtocols().stream().collect(joining(", ")))));
      sslInfo.put(Type.DEFAULT_SSL_PROTOCOLS,
            new SimpleImmutableEntry<String, Optional<Object>>(SystemConsoleMessages.INSTANCE.defaultSslProtocols(),
                  Optional.of(generalInfoDto.getDefaultSslProtocols().stream().collect(joining(", ")))));
      sslInfo.put(Type.ENABLED_SSL_PROTOCOLS,
            new SimpleImmutableEntry<String, Optional<Object>>(SystemConsoleMessages.INSTANCE.enabledSslProtocols(),
                  Optional.of(generalInfoDto.getEnabledSslProtocols().stream().collect(joining(", ")))));
      sslInfo.forEach((key, pair) -> addFieldToForm(pair.getKey(), pair.getValue(), form));
      
      form.loadFields();

      wrapper.add(form, new VerticalLayoutData(1, -1, new Margins(10)));

      form.clearButtonBar();

      Scheduler.get().scheduleDeferred(forceLayoutCommand);
   }
   
   private void addFieldToForm(String description, Optional<Object> value, SimpleForm form) {
      form.addField(StaticLabel.class, description, new SFFCStaticLabel() {
         @Override
         public String getLabel() {
            return value.isPresent() ? value.get().toString(): "";
         }
      });
   }
}
