package net.datenwerke.rs.scp.client.scp.hookers;

import static net.datenwerke.rs.core.client.datasinkmanager.helper.forms.simpleform.DatasinkSimpleFormProvider.extractSingleTreeSelectionField;

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
import net.datenwerke.rs.scheduleasfile.client.scheduleasfile.locale.ScheduleAsFileMessages;
import net.datenwerke.rs.scheduler.client.scheduler.dto.ReportScheduleDefinition;
import net.datenwerke.rs.scheduler.client.scheduler.hooks.ScheduleExportSnippetProviderHook;
import net.datenwerke.rs.scheduler.client.scheduler.locale.SchedulerMessages;
import net.datenwerke.rs.scheduler.client.scheduler.schedulereport.pages.JobMetadataConfigurationForm;
import net.datenwerke.rs.scp.client.scp.ScpDao;
import net.datenwerke.rs.scp.client.scp.ScpUiModule;
import net.datenwerke.rs.scp.client.scp.dto.ScheduleAsScpFileInformation;
import net.datenwerke.rs.scp.client.scp.dto.ScpDatasinkDto;
import net.datenwerke.rs.scp.client.scp.provider.annotations.DatasinkTreeScp;
import net.datenwerke.rs.theme.client.icon.BaseIcon;

public class ScpExportSnippetProvider implements ScheduleExportSnippetProviderHook {

   private String isExportAsScpKey;
   private String folderKey;
   private String nameKey;
   private String scpKey;
   private String compressedKey;

   private final Provider<UITree> treeProvider;
   private final Provider<ScpDao> datasinkDaoProvider;
   private final DatasinkTreeManagerDao datasinkTreeManager;

   @Inject
   public ScpExportSnippetProvider(@DatasinkTreeScp Provider<UITree> treeProvider,
         DatasinkTreeManagerDao datasinkTreeManager, Provider<ScpDao> datasinkDaoProvider) {
      this.treeProvider = treeProvider;
      this.datasinkTreeManager = datasinkTreeManager;
      this.datasinkDaoProvider = datasinkDaoProvider;
   }

   @Override
   public void configureSimpleForm(SimpleForm xform, ReportDto report, Collection<ReportViewConfiguration> configs) {
      xform.setLabelAlign(LabelAlign.LEFT);
      isExportAsScpKey = xform.addField(Boolean.class, "", new SFFCBoolean() {
         @Override
         public String getBoxLabel() {
            return ScpUiModule.NAME;
         }
      });
      xform.setLabelAlign(LabelAlign.TOP);

      xform.setFieldWidth(260);
      xform.beginFloatRow();

      scpKey = xform.addField(DatasinkSelectionField.class, ScpUiModule.NAME, new SFFCGenericTreeNode() {
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
            return ScpUiModule.ICON;
         }
      });

      folderKey = xform.addField(String.class, ScheduleAsFileMessages.INSTANCE.folder(), new SFFCAllowBlank() {
         @Override
         public boolean allowBlank() {
            return false;
         }
      });

      xform.endRow();
      xform.setFieldWidth(1);

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

      xform.addCondition(isExportAsScpKey, new FieldEquals(true), new ShowHideFieldAction(folderKey));
      xform.addCondition(isExportAsScpKey, new FieldEquals(true), new ShowHideFieldAction(nameKey));
      xform.addCondition(isExportAsScpKey, new FieldEquals(true), new ShowHideFieldAction(scpKey));
      xform.addCondition(isExportAsScpKey, new FieldEquals(true), new ShowHideFieldAction(compressedKey));

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

      ScheduleAsScpFileInformation info = new ScheduleAsScpFileInformation();
      info.setName((String) simpleForm.getValue(nameKey));
      info.setFolder((String) simpleForm.getValue(folderKey));
      info.setCompressed((Boolean) simpleForm.getValue(compressedKey));
      info.setScpDatasinkDto((ScpDatasinkDto) simpleForm.getValue(scpKey));

      configDto.addAdditionalInfo(info);

   }

   @Override
   public boolean isActive(SimpleForm simpleForm) {
      return (Boolean) simpleForm.getValue(isExportAsScpKey);
   }

   @Override
   public void loadFields(SimpleForm form, ReportScheduleDefinition definition, ReportDto report) {
      form.loadFields();

      final SingleTreeSelectionField scpField = extractSingleTreeSelectionField(form.getField(scpKey));

      if (null != definition) {
         form.setValue(nameKey, "${now} - " + definition.getTitle());
         ScheduleAsScpFileInformation info = definition.getAdditionalInfo(ScheduleAsScpFileInformation.class);
         if (null != info) {
            form.setValue(isExportAsScpKey, true);
            form.setValue(nameKey, info.getName());
            form.setValue(folderKey, info.getFolder());
            form.setValue(compressedKey, info.isCompressed());
            scpField.setValue(info.getScpDatasinkDto());
         }
      }

      scpField.addValueChangeHandler(event -> {
         if (null == event.getValue())
            return;

         datasinkTreeManager.loadFullViewNode((ScpDatasinkDto) event.getValue(), new RsAsyncCallback<ScpDatasinkDto>() {
            @Override
            public void onSuccess(ScpDatasinkDto result) {
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
         ScheduleAsScpFileInformation info = definition.getAdditionalInfo(ScheduleAsScpFileInformation.class);
         if (null != info)
            form.setValue(nameKey, info.getName());
      }

   }

   @Override
   public boolean appliesFor(ReportDto report, Collection<ReportViewConfiguration> configs) {
      return true;
   }

}
