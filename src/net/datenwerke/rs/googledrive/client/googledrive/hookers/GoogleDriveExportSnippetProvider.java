package net.datenwerke.rs.googledrive.client.googledrive.hookers;

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
import net.datenwerke.rs.core.client.datasinkmanager.DatasinkUIModule;
import net.datenwerke.rs.core.client.datasinkmanager.HasDefaultDatasink;
import net.datenwerke.rs.core.client.datasinkmanager.helper.forms.DatasinkSelectionField;
import net.datenwerke.rs.core.client.reportexecutor.ui.ReportViewConfiguration;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;
import net.datenwerke.rs.googledrive.client.googledrive.GoogleDriveDao;
import net.datenwerke.rs.googledrive.client.googledrive.GoogleDriveUiModule;
import net.datenwerke.rs.googledrive.client.googledrive.dto.GoogleDriveDatasinkDto;
import net.datenwerke.rs.googledrive.client.googledrive.dto.ScheduleAsGoogleDriveFileInformation;
import net.datenwerke.rs.googledrive.client.googledrive.provider.annotations.DatasinkTreeGoogleDrive;
import net.datenwerke.rs.scheduleasfile.client.scheduleasfile.locale.ScheduleAsFileMessages;
import net.datenwerke.rs.scheduler.client.scheduler.dto.ReportScheduleDefinition;
import net.datenwerke.rs.scheduler.client.scheduler.hooks.ScheduleExportSnippetProviderHook;
import net.datenwerke.rs.scheduler.client.scheduler.locale.SchedulerMessages;
import net.datenwerke.rs.scheduler.client.scheduler.schedulereport.pages.JobMetadataConfigurationForm;
import net.datenwerke.rs.theme.client.icon.BaseIcon;

public class GoogleDriveExportSnippetProvider implements ScheduleExportSnippetProviderHook {
   private String isExportAsFileKey;
   private String folderKey;
   private String nameKey;
   private String googleDriveKey;
   private String compressedKey;

   private final Provider<UITree> treeProvider;
   private final Provider<GoogleDriveDao> datasinkDaoProvider;
   private final DatasinkTreeManagerDao datasinkTreeManager;

   @Inject
   public GoogleDriveExportSnippetProvider(@DatasinkTreeGoogleDrive Provider<UITree> treeProvider,
         DatasinkTreeManagerDao datasinkTreeManager, Provider<GoogleDriveDao> datasinkDaoProvider) {
      this.treeProvider = treeProvider;
      this.datasinkTreeManager = datasinkTreeManager;
      this.datasinkDaoProvider = datasinkDaoProvider;
   }

   @Override
   public void configureSimpleForm(SimpleForm xform, ReportDto report, Collection<ReportViewConfiguration> configs) {
      xform.setLabelAlign(LabelAlign.LEFT);
      isExportAsFileKey = xform.addField(Boolean.class, "", new SFFCBoolean() {
         @Override
         public String getBoxLabel() {
            return GoogleDriveUiModule.NAME;
         }
      });
      xform.setLabelAlign(LabelAlign.TOP);

      xform.setFieldWidth(260);
      xform.beginFloatRow();

      googleDriveKey = xform.addField(DatasinkSelectionField.class, GoogleDriveUiModule.NAME,
            new SFFCGenericTreeNode() {
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
                  return GoogleDriveUiModule.ICON;
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

      xform.addCondition(isExportAsFileKey, new FieldEquals(true), new ShowHideFieldAction(folderKey));
      xform.addCondition(isExportAsFileKey, new FieldEquals(true), new ShowHideFieldAction(nameKey));
      xform.addCondition(isExportAsFileKey, new FieldEquals(true), new ShowHideFieldAction(googleDriveKey));
      xform.addCondition(isExportAsFileKey, new FieldEquals(true), new ShowHideFieldAction(compressedKey));

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

      ScheduleAsGoogleDriveFileInformation info = new ScheduleAsGoogleDriveFileInformation();
      info.setName((String) simpleForm.getValue(nameKey));
      info.setFolder((String) simpleForm.getValue(folderKey));
      info.setCompressed((Boolean) simpleForm.getValue(compressedKey));
      info.setGoogleDriveDatasinkDto((GoogleDriveDatasinkDto) simpleForm.getValue(googleDriveKey));

      configDto.addAdditionalInfo(info);
   }

   @Override
   public boolean isActive(SimpleForm simpleForm) {
      return (Boolean) simpleForm.getValue(isExportAsFileKey);
   }

   @Override
   public void loadFields(SimpleForm form, ReportScheduleDefinition definition, ReportDto report) {
      form.loadFields();

      final SingleTreeSelectionField googleDriveField = extractSingleTreeSelectionField(form.getField(googleDriveKey));

      if (null != definition) {
         form.setValue(nameKey, DatasinkUIModule.DEFAULT_FILE_NAME);
         ScheduleAsGoogleDriveFileInformation info = definition
               .getAdditionalInfo(ScheduleAsGoogleDriveFileInformation.class);
         if (null != info) {
            form.setValue(isExportAsFileKey, true);
            form.setValue(nameKey, info.getName());
            form.setValue(folderKey, info.getFolder());
            form.setValue(compressedKey, info.isCompressed());
            googleDriveField.setValue(info.getGoogleDriveDatasinkDto());
         }
      }

      googleDriveField.addValueChangeHandler(event -> {
         if (null == event.getValue())
            return;

         datasinkTreeManager.loadFullViewNode((GoogleDriveDatasinkDto) event.getValue(),
               new RsAsyncCallback<GoogleDriveDatasinkDto>() {
                  @Override
                  public void onSuccess(GoogleDriveDatasinkDto result) {
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

      form.setValue(nameKey, DatasinkUIModule.DEFAULT_FILE_NAME);
      if (null != definition) {
         ScheduleAsGoogleDriveFileInformation info = definition
               .getAdditionalInfo(ScheduleAsGoogleDriveFileInformation.class);
         if (null != info)
            form.setValue(nameKey, info.getName());
      }

   }

   @Override
   public boolean appliesFor(ReportDto report, Collection<ReportViewConfiguration> configs) {
      return true;
   }

}