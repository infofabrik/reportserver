package net.datenwerke.rs.ftp.client.ftp.hookers;

import static net.datenwerke.rs.core.client.datasinkmanager.helper.forms.simpleform.DatasinkSelectionFieldProvider.extractSingleTreeSelectionField;

import java.util.Collection;

import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.sencha.gxt.widget.core.client.form.FormPanel.LabelAlign;

import net.datenwerke.gf.client.treedb.UITree;
import net.datenwerke.gf.client.treedb.selection.SingleTreeSelectionField;
import net.datenwerke.gf.client.treedb.simpleform.SFFCGenericTreeNode;
import net.datenwerke.gxtdto.client.dtomanager.callback.RsAsyncCallback;
import net.datenwerke.gxtdto.client.forms.simpleform.SimpleForm;
import net.datenwerke.gxtdto.client.forms.simpleform.actions.ShowHideFieldAction;
import net.datenwerke.gxtdto.client.forms.simpleform.conditions.FieldEquals;
import net.datenwerke.gxtdto.client.forms.simpleform.providers.configs.SFFCAllowBlank;
import net.datenwerke.gxtdto.client.forms.simpleform.providers.configs.SFFCBoolean;
import net.datenwerke.gxtdto.client.forms.simpleform.providers.configs.SFFCDatasinkDao;
import net.datenwerke.gxtdto.client.locale.BaseMessages;
import net.datenwerke.rs.core.client.datasinkmanager.DatasinkTreeManagerDao;
import net.datenwerke.rs.core.client.datasinkmanager.HasDefaultDatasink;
import net.datenwerke.rs.core.client.datasinkmanager.helper.forms.DatasinkSelectionField;
import net.datenwerke.rs.core.client.reportexecutor.ui.ReportViewConfiguration;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;
import net.datenwerke.rs.ftp.client.ftp.FtpUiModule;
import net.datenwerke.rs.ftp.client.ftp.FtpsDao;
import net.datenwerke.rs.ftp.client.ftp.dto.FtpsDatasinkDto;
import net.datenwerke.rs.ftp.client.ftp.dto.ScheduleAsFtpsFileInformation;
import net.datenwerke.rs.ftp.client.ftp.provider.annotations.DatasinkTreeFtps;
import net.datenwerke.rs.scheduleasfile.client.scheduleasfile.locale.ScheduleAsFileMessages;
import net.datenwerke.rs.scheduler.client.scheduler.dto.ReportScheduleDefinition;
import net.datenwerke.rs.scheduler.client.scheduler.hooks.ScheduleExportSnippetProviderHook;
import net.datenwerke.rs.scheduler.client.scheduler.locale.SchedulerMessages;
import net.datenwerke.rs.scheduler.client.scheduler.schedulereport.pages.JobMetadataConfigurationForm;
import net.datenwerke.rs.theme.client.icon.BaseIcon;

public class FtpsExportSnippetProvider implements ScheduleExportSnippetProviderHook {

   private String isExportAsFtpsKey;
   private String folderKey;
   private String nameKey;
   private String ftpsKey;
   private String compressedKey;

   private final Provider<UITree> treeProvider;
   private final Provider<FtpsDao> datasinkDaoProvider;
   private final DatasinkTreeManagerDao datasinkTreeManager;

   @Inject
   public FtpsExportSnippetProvider(@DatasinkTreeFtps Provider<UITree> treeProvider,
         DatasinkTreeManagerDao datasinkTreeManager, Provider<FtpsDao> datasinkDaoProvider) {
      this.treeProvider = treeProvider;
      this.datasinkTreeManager = datasinkTreeManager;
      this.datasinkDaoProvider = datasinkDaoProvider;
   }

   @Override
   public void configureSimpleForm(SimpleForm xform, ReportDto report, Collection<ReportViewConfiguration> configs) {
      xform.setLabelAlign(LabelAlign.LEFT);
      isExportAsFtpsKey = xform.addField(Boolean.class, "", new SFFCBoolean() {
         @Override
         public String getBoxLabel() {
            return FtpUiModule.FTPS_NAME;
         }
      });
      xform.setLabelAlign(LabelAlign.TOP);

      xform.setFieldWidth(260);
      xform.beginFloatRow();

      ftpsKey = xform.addField(DatasinkSelectionField.class, FtpUiModule.FTPS_NAME, new SFFCGenericTreeNode() {
         @Override
         public UITree getTreeForPopup() {
            return treeProvider.get();
         }
      }, new SFFCAllowBlank() {
         @Override
         public boolean allowBlank() {
            return false;
         }
      }, new SFFCDatasinkDao() {
         @Override
         public Provider<? extends HasDefaultDatasink> getDatasinkDaoProvider() {
            return datasinkDaoProvider;
         }

         @Override
         public BaseIcon getIcon() {
            return FtpUiModule.FTPS_ICON;
         }
      });

      folderKey = xform.addField(String.class, ScheduleAsFileMessages.INSTANCE.folder(), new SFFCAllowBlank() {
         @Override
         public boolean allowBlank() {
            return false;
         }
      });

      xform.endRow();
      xform.setFieldWidth(530);

      nameKey = xform.addField(String.class, BaseMessages.INSTANCE.propertyName(), new SFFCAllowBlank() {
         @Override
         public boolean allowBlank() {
            return false;
         }
      });

      xform.setLabelAlign(LabelAlign.LEFT);
      compressedKey = xform.addField(Boolean.class, "", new SFFCBoolean() {
         @Override
         public String getBoxLabel() {
            return SchedulerMessages.INSTANCE.reportCompress();
         }
      });

      xform.addCondition(isExportAsFtpsKey, new FieldEquals(true), new ShowHideFieldAction(folderKey));
      xform.addCondition(isExportAsFtpsKey, new FieldEquals(true), new ShowHideFieldAction(nameKey));
      xform.addCondition(isExportAsFtpsKey, new FieldEquals(true), new ShowHideFieldAction(ftpsKey));
      xform.addCondition(isExportAsFtpsKey, new FieldEquals(true), new ShowHideFieldAction(compressedKey));

   }

   @Override
   public boolean isValid(SimpleForm simpleForm) {
      simpleForm.clearInvalid();
      return simpleForm.isValid();
   }

   @Override
   public void configureConfig(ReportScheduleDefinition configDto, SimpleForm simpleForm) {
      if (!isActive(simpleForm))
         return;

      ScheduleAsFtpsFileInformation info = new ScheduleAsFtpsFileInformation();
      info.setName((String) simpleForm.getValue(nameKey));
      info.setFolder((String) simpleForm.getValue(folderKey));
      info.setFtpsDatasinkDto((FtpsDatasinkDto) simpleForm.getValue(ftpsKey));
      info.setCompressed((Boolean) simpleForm.getValue(compressedKey));

      configDto.addAdditionalInfo(info);

   }

   @Override
   public boolean isActive(SimpleForm simpleForm) {
      return (Boolean) simpleForm.getValue(isExportAsFtpsKey);
   }

   @Override
   public void loadFields(SimpleForm form, ReportScheduleDefinition definition, ReportDto report) {
      form.loadFields();

      final SingleTreeSelectionField ftpsField = extractSingleTreeSelectionField(form.getField(ftpsKey));

      if (null != definition) {
         form.setValue(nameKey, "${now} - " + definition.getTitle());
         ScheduleAsFtpsFileInformation info = definition.getAdditionalInfo(ScheduleAsFtpsFileInformation.class);
         if (null != info) {
            form.setValue(isExportAsFtpsKey, true);
            form.setValue(nameKey, info.getName());
            form.setValue(folderKey, info.getFolder());
            form.setValue(compressedKey, info.isCompressed());
            ftpsField.setValue(info.getFtpsDatasinkDto());
         }
      }

      ftpsField.addValueChangeHandler(event -> {
         if (null == event.getValue())
            return;

         datasinkTreeManager.loadFullViewNode((FtpsDatasinkDto) event.getValue(),
               new RsAsyncCallback<FtpsDatasinkDto>() {
                  @Override
                  public void onSuccess(FtpsDatasinkDto result) {
                     form.setValue(folderKey, result.getFolder());
                  }

                  @Override
                  public void onFailure(Throwable caught) {
                     super.onFailure(caught);
                  }
               });

      });

   }

   @Override
   public void onWizardPageChange(int pageNr, Widget page, SimpleForm form, ReportScheduleDefinition definition,
         ReportDto report) {
      if (!(page instanceof JobMetadataConfigurationForm))
         return;

      JobMetadataConfigurationForm metadataForm = (JobMetadataConfigurationForm) page;

      String jobTitle = metadataForm.getTitleValue();

      form.setValue(nameKey, "${now} - " + jobTitle);
      if (null != definition) {
         ScheduleAsFtpsFileInformation info = definition.getAdditionalInfo(ScheduleAsFtpsFileInformation.class);
         if (null != info)
            form.setValue(nameKey, info.getName());
      }

   }

   @Override
   public boolean appliesFor(ReportDto report, Collection<ReportViewConfiguration> configs) {
      return true;
   }

}