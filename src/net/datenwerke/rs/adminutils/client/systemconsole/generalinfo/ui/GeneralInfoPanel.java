package net.datenwerke.rs.adminutils.client.systemconsole.generalinfo.ui;

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

      form.setLabelWidth(150);
      
      Map<Optional<String>, String> generalInfo = new LinkedHashMap<>();
      generalInfo.put(Optional.of(result.getRsVersion()), SystemConsoleMessages.INSTANCE.versionLabel());
      generalInfo.put(Optional.of(result.getJavaVersion()), SystemConsoleMessages.INSTANCE.javaVersionLabel());
      generalInfo.put(Optional.of(result.getVmArguments()), "JVM Args");
      generalInfo.put(Optional.of(result.getApplicationServer()), SystemConsoleMessages.INSTANCE.applicationServerLabel());
      generalInfo.put(Optional.of(result.getOsVersion()), SystemConsoleMessages.INSTANCE.operationSystemLabel());      
      generalInfo.forEach((value, description) -> addFieldToForm(value, description, form));

      Map<Optional<String>, String> browserInfo = new LinkedHashMap<>();
      browserInfo.put(Optional.of(result.getBrowserName()), SystemConsoleMessages.INSTANCE.browserNameLabel());
      browserInfo.put(Optional.of(result.getBrowserVersion()), SystemConsoleMessages.INSTANCE.browserVersionLabel());
      browserInfo.forEach((value, description) -> addFieldToForm(value, description, form));
      
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
