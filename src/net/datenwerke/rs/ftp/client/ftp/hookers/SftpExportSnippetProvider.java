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
import net.datenwerke.rs.ftp.client.ftp.SftpDao;
import net.datenwerke.rs.ftp.client.ftp.dto.ScheduleAsSftpFileInformation;
import net.datenwerke.rs.ftp.client.ftp.dto.SftpDatasinkDto;
import net.datenwerke.rs.ftp.client.ftp.provider.annotations.DatasinkTreeSftp;
import net.datenwerke.rs.scheduleasfile.client.scheduleasfile.locale.ScheduleAsFileMessages;
import net.datenwerke.rs.scheduler.client.scheduler.dto.ReportScheduleDefinition;
import net.datenwerke.rs.scheduler.client.scheduler.hooks.ScheduleExportSnippetProviderHook;
import net.datenwerke.rs.scheduler.client.scheduler.locale.SchedulerMessages;
import net.datenwerke.rs.scheduler.client.scheduler.schedulereport.pages.JobMetadataConfigurationForm;
import net.datenwerke.rs.theme.client.icon.BaseIcon;

public class SftpExportSnippetProvider implements ScheduleExportSnippetProviderHook {

   private String isExportAsSftpKey;
   private String folderKey;
   private String nameKey;
   private String sftpKey;
   private String compressedKey;

   private final Provider<UITree> treeProvider;
   private final Provider<SftpDao> datasinkDaoProvider;
   private final DatasinkTreeManagerDao datasinkTreeManager;

   @Inject
   public SftpExportSnippetProvider(@DatasinkTreeSftp Provider<UITree> treeProvider,
         DatasinkTreeManagerDao datasinkTreeManager, Provider<SftpDao> datasinkDaoProvider) {
      this.treeProvider = treeProvider;
      this.datasinkTreeManager = datasinkTreeManager;
      this.datasinkDaoProvider = datasinkDaoProvider;
   }

   @Override
   public boolean appliesFor(ReportDto report, Collection<ReportViewConfiguration> configs) {
      return true;
   }

   @Override
   public void configureSimpleForm(final SimpleForm xform, ReportDto report,
         Collection<ReportViewConfiguration> configs) {

      xform.setLabelAlign(LabelAlign.LEFT);
      isExportAsSftpKey = xform.addField(Boolean.class, "", new SFFCBoolean() {
         @Override
         public String getBoxLabel() {
            return FtpUiModule.SFTP_NAME;
         }
      });
      xform.setLabelAlign(LabelAlign.TOP);

      xform.setFieldWidth(260);
      xform.beginFloatRow();

      sftpKey = xform.addField(DatasinkSelectionField.class, FtpUiModule.SFTP_NAME, new SFFCGenericTreeNode() {
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
            return FtpUiModule.SFTP_ICON;
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

      xform.addCondition(isExportAsSftpKey, new FieldEquals(true), new ShowHideFieldAction(folderKey));
      xform.addCondition(isExportAsSftpKey, new FieldEquals(true), new ShowHideFieldAction(nameKey));
      xform.addCondition(isExportAsSftpKey, new FieldEquals(true), new ShowHideFieldAction(sftpKey));
      xform.addCondition(isExportAsSftpKey, new FieldEquals(true), new ShowHideFieldAction(compressedKey));

   }

   @Override
   public boolean isValid(SimpleForm simpleForm) {
      simpleForm.clearInvalid();
      return simpleForm.isValid();
   }

   @Override
   public void configureConfig(ReportScheduleDefinition configDto, SimpleForm form) {
      if (!isActive(form))
         return;

      ScheduleAsSftpFileInformation info = new ScheduleAsSftpFileInformation();
      info.setName((String) form.getValue(nameKey));
      info.setFolder((String) form.getValue(folderKey));
      info.setSftpDatasinkDto((SftpDatasinkDto) form.getValue(sftpKey));
      info.setCompressed((Boolean) form.getValue(compressedKey));

      configDto.addAdditionalInfo(info);
   }

   @Override
   public boolean isActive(SimpleForm simpleForm) {
      return (Boolean) simpleForm.getValue(isExportAsSftpKey);
   }

   @Override
   public void loadFields(final SimpleForm form, ReportScheduleDefinition definition, ReportDto report) {
      form.loadFields();

      final SingleTreeSelectionField sftpField = extractSingleTreeSelectionField(form.getField(sftpKey));

      if (null != definition) {
         form.setValue(nameKey, "${now} - " + definition.getTitle());
         ScheduleAsSftpFileInformation info = definition.getAdditionalInfo(ScheduleAsSftpFileInformation.class);
         if (null != info) {
            form.setValue(isExportAsSftpKey, true);
            form.setValue(nameKey, info.getName());
            form.setValue(folderKey, info.getFolder());
            form.setValue(compressedKey, info.isCompressed());
            sftpField.setValue(info.getSftpDatasinkDto());
         }
      }

      sftpField.addValueChangeHandler(event -> {
         if (null == event.getValue())
            return;

         datasinkTreeManager.loadFullViewNode((SftpDatasinkDto) event.getValue(),
               new RsAsyncCallback<SftpDatasinkDto>() {
                  @Override
                  public void onSuccess(SftpDatasinkDto result) {
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
         ScheduleAsSftpFileInformation info = definition.getAdditionalInfo(ScheduleAsSftpFileInformation.class);
         if (null != info)
            form.setValue(nameKey, info.getName());
      }
   }

}
