package net.datenwerke.rs.adminutils.client.systemconsole.generalinfo.ui;

import static java.util.stream.Collectors.joining;

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

   protected void init(final GeneralInfoDto result) {
      final SimpleForm form = SimpleForm.getNewInstance();
      form.setHeading(SystemConsoleMessages.INSTANCE.generalInfo());
      form.setLabelAlign(LabelAlign.LEFT);

      form.setLabelWidth(200);
      
      Map<Optional<String>, String> generalInfo = new LinkedHashMap<>();
      generalInfo.put(Optional.of(result.getRsVersion()), SystemConsoleMessages.INSTANCE.versionLabel());
      generalInfo.put(Optional.of(result.getJavaVersion()), SystemConsoleMessages.INSTANCE.javaVersionLabel());
      generalInfo.put(Optional.of(result.getVmArguments()), "JVM Args");
      generalInfo.put(Optional.of(result.getApplicationServer()), SystemConsoleMessages.INSTANCE.applicationServerLabel());
      generalInfo.put(Optional.of(result.getMaxMemory()), SystemConsoleMessages.INSTANCE.maxMemoryLabel());
      generalInfo.put(Optional.of(result.getGroovyVersion()), SystemConsoleMessages.INSTANCE.groovyVersionLabel());
      generalInfo.put(Optional.of(result.getLocale()), "Locale");
      generalInfo.put(Optional.of(result.getJvmLocale()), "JVM Locale");
      generalInfo.put(Optional.of(result.getOsVersion()), SystemConsoleMessages.INSTANCE.operationSystemLabel());      
      generalInfo.forEach((value, description) -> addFieldToForm(value, description, form));

      Map<Optional<String>, String> browserInfo = new LinkedHashMap<>();
      browserInfo.put(Optional.of(result.getUserAgent()), SystemConsoleMessages.INSTANCE.userAgentLabel());
      browserInfo.forEach((value, description) -> addFieldToForm(value, description, form));
      
      form.addField(Separator.class, new SFFCSpace());
      form.addField(Separator.class, new SFFCSpace());
      Map<Optional<String>, String> pamsInfo = new LinkedHashMap<>();
      pamsInfo.put(Optional.ofNullable(result.getStaticPams().stream().collect(joining(", "))),
            SystemConsoleMessages.INSTANCE.staticPamsLabel());
      pamsInfo.forEach((value, description) -> addFieldToForm(value, description, form));
      
      form.addField(Separator.class, new SFFCSpace());
      form.addField(Separator.class, new SFFCSpace());
      
      form.addField(StaticLabel.class, SystemConsoleMessages.INSTANCE.dbConfig(), new SFFCStaticLabel() {
         @Override
         public String getLabel() {
            return "";
         }
      });
      
      Map<Optional<String>, String> dbConfigInfo = new LinkedHashMap<>();
      dbConfigInfo.put(Optional.ofNullable(result.getHibernateDialect()), "hibernate.dialect");
      dbConfigInfo.put(Optional.ofNullable(result.getHibernateDriverClass()), "hibernate.connection.driver_class");
      dbConfigInfo.put(Optional.ofNullable(result.getHibernateConnectionUrl()), "hibernate.connection.url");
      dbConfigInfo.put(Optional.ofNullable(result.getHibernateConnectionUsername()), "hibernate.connection.username");
      dbConfigInfo.put(Optional.ofNullable(result.getHibernateDefaultSchema()), "hibernate.default_schema");
      
      dbConfigInfo.put(Optional.ofNullable(result.getSchemaVersion()), SystemConsoleMessages.INSTANCE.schemaVersion());   
      dbConfigInfo.forEach((value, description) -> addFieldToForm(value, description, form));
      
      form.addField(Separator.class, new SFFCSpace());
      form.addField(Separator.class, new SFFCSpace());
      
      form.addField(StaticLabel.class, SystemConsoleMessages.INSTANCE.internalDb(), new SFFCStaticLabel() {
         @Override
         public String getLabel() {
            return "";
         }
      });

      Map<Optional<String>, String> internalDbInfo = new LinkedHashMap<>();
      internalDbInfo.put(Optional.ofNullable(result.getInternalDbId()), BaseMessages.INSTANCE.id());
      internalDbInfo.put(Optional.ofNullable(result.getInternalDbDatasourceName()), BaseMessages.INSTANCE.name());
      internalDbInfo.put(Optional.ofNullable(result.getInternalDbPath()), BaseMessages.INSTANCE.path());
      internalDbInfo.put(Optional.ofNullable(result.getInternalDbDatabaseName()),
            SystemConsoleMessages.INSTANCE.internalDbName());
      internalDbInfo.put(Optional.ofNullable(result.getInternalDbVersion()),
            SystemConsoleMessages.INSTANCE.internalDbVersion());
      internalDbInfo.put(Optional.ofNullable(result.getInternalDbDriverName()),
            SystemConsoleMessages.INSTANCE.internalDbDriverName());
      internalDbInfo.put(Optional.ofNullable(result.getInternalDbDriverVersion()),
            SystemConsoleMessages.INSTANCE.internalDbDriverVersion());
      internalDbInfo.put(Optional.ofNullable(result.getInternalDbJdbcMajorVersion()),
            SystemConsoleMessages.INSTANCE.internalDbJdbcMajorVersion());
      internalDbInfo.put(Optional.ofNullable(result.getInternalDbJdbcMinorVersion()),
            SystemConsoleMessages.INSTANCE.internalDbJdbcMinorVersion());
      internalDbInfo.put(Optional.ofNullable(result.getInternalDbJdbcUrl()),
            SystemConsoleMessages.INSTANCE.internalDbJdbcUrl());
      internalDbInfo.put(Optional.ofNullable(result.getInternalDbUsername()),
            SystemConsoleMessages.INSTANCE.internalDbUsername());
      internalDbInfo.forEach((value, description) -> addFieldToForm(value, description, form));
      
      form.addField(Separator.class, new SFFCSpace());
      form.addField(Separator.class, new SFFCSpace());
      form.addField(StaticLabel.class, "SSL", new SFFCStaticLabel() {
         @Override
         public String getLabel() {
            return "";
         }
      });
      Map<Optional<String>, String> sslInfo = new LinkedHashMap<>();
      sslInfo.put(Optional.ofNullable(result.getSupportedSslProtocols().stream().collect(joining(", "))),
            SystemConsoleMessages.INSTANCE.supportedSslProtocols());
      sslInfo.put(Optional.ofNullable(result.getDefaultSslProtocols().stream().collect(joining(", "))),
            SystemConsoleMessages.INSTANCE.defaultSslProtocols());
      sslInfo.put(Optional.ofNullable(result.getEnabledSslProtocols().stream().collect(joining(", "))),
            SystemConsoleMessages.INSTANCE.enabledSslProtocols());
      sslInfo.forEach((value, description) -> addFieldToForm(value, description, form));
      
      form.loadFields();

      wrapper.add(form, new VerticalLayoutData(1, -1, new Margins(10)));

      form.clearButtonBar();

      Scheduler.get().scheduleDeferred(forceLayoutCommand);
   }
   
   private void addFieldToForm(Optional<String> value, String description, SimpleForm form) {
      if (value.isPresent()) {
         form.addField(StaticLabel.class, description, new SFFCStaticLabel() {
            @Override
            public String getLabel() {
               return value.get().replace(";", "; ");
            }
         });
      }
   }
}
