package net.datenwerke.rs.scriptdatasink.client.scriptdatasink.hookers;

import static net.datenwerke.rs.core.client.datasinkmanager.helper.forms.simpleform.DatasinkSelectionFieldProvider.extractSingleTreeSelectionField;

import java.util.Collection;

import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.sencha.gxt.widget.core.client.form.FormPanel.LabelAlign;

import net.datenwerke.gf.client.treedb.UITree;
import net.datenwerke.gf.client.treedb.selection.SingleTreeSelectionField;
import net.datenwerke.gf.client.treedb.simpleform.SFFCGenericTreeNode;
import net.datenwerke.gxtdto.client.forms.simpleform.SimpleForm;
import net.datenwerke.gxtdto.client.forms.simpleform.actions.ShowHideFieldAction;
import net.datenwerke.gxtdto.client.forms.simpleform.conditions.FieldEquals;
import net.datenwerke.gxtdto.client.forms.simpleform.providers.configs.SFFCAllowBlank;
import net.datenwerke.gxtdto.client.forms.simpleform.providers.configs.SFFCBoolean;
import net.datenwerke.gxtdto.client.forms.simpleform.providers.configs.SFFCDatasinkDao;
import net.datenwerke.gxtdto.client.locale.BaseMessages;
import net.datenwerke.rs.core.client.datasinkmanager.DatasinkUIModule;
import net.datenwerke.rs.core.client.datasinkmanager.HasDefaultDatasink;
import net.datenwerke.rs.core.client.datasinkmanager.helper.forms.DatasinkSelectionField;
import net.datenwerke.rs.core.client.reportexecutor.ui.ReportViewConfiguration;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;
import net.datenwerke.rs.scheduler.client.scheduler.dto.ReportScheduleDefinition;
import net.datenwerke.rs.scheduler.client.scheduler.hooks.ScheduleExportSnippetProviderHook;
import net.datenwerke.rs.scheduler.client.scheduler.locale.SchedulerMessages;
import net.datenwerke.rs.scheduler.client.scheduler.schedulereport.pages.JobMetadataConfigurationForm;
import net.datenwerke.rs.scriptdatasink.client.scriptdatasink.ScriptDatasinkDao;
import net.datenwerke.rs.scriptdatasink.client.scriptdatasink.ScriptDatasinkUiModule;
import net.datenwerke.rs.scriptdatasink.client.scriptdatasink.dto.ScheduleAsScriptDatasinkInformation;
import net.datenwerke.rs.scriptdatasink.client.scriptdatasink.dto.ScriptDatasinkDto;
import net.datenwerke.rs.scriptdatasink.client.scriptdatasink.provider.annotations.DatasinkTreeScriptDatasink;
import net.datenwerke.rs.theme.client.icon.BaseIcon;

public class ScriptDatasinkExportSnippetProvider implements ScheduleExportSnippetProviderHook {

   private String isExportAsScriptDatasinkKey;
   private String nameKey;
   private String scriptDatasinkKey;
   private String compressedKey;

   private final Provider<UITree> treeProvider;
   private final Provider<ScriptDatasinkDao> datasinkDaoProvider;

   @Inject
   public ScriptDatasinkExportSnippetProvider(
         @DatasinkTreeScriptDatasink Provider<UITree> treeProvider,
         Provider<ScriptDatasinkDao> datasinkDaoProvider
         ) {
      this.treeProvider = treeProvider;
      this.datasinkDaoProvider = datasinkDaoProvider;
   }

   @Override
   public void configureSimpleForm(SimpleForm xform, ReportDto report, Collection<ReportViewConfiguration> configs) {
      xform.setLabelAlign(LabelAlign.LEFT);
      isExportAsScriptDatasinkKey = xform.addField(Boolean.class, "", new SFFCBoolean() {
         @Override
         public String getBoxLabel() {
            return ScriptDatasinkUiModule.NAME;
         }
      });
      xform.setLabelAlign(LabelAlign.TOP);

      xform.setFieldWidth(260);
      xform.beginFloatRow();

      scriptDatasinkKey = xform.addField(DatasinkSelectionField.class, ScriptDatasinkUiModule.NAME,
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
                  return ScriptDatasinkUiModule.ICON;
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

      xform.addCondition(isExportAsScriptDatasinkKey, new FieldEquals(true), new ShowHideFieldAction(nameKey));
      xform.addCondition(isExportAsScriptDatasinkKey, new FieldEquals(true),
            new ShowHideFieldAction(scriptDatasinkKey));
      xform.addCondition(isExportAsScriptDatasinkKey, new FieldEquals(true), new ShowHideFieldAction(compressedKey));

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

      ScheduleAsScriptDatasinkInformation info = new ScheduleAsScriptDatasinkInformation();
      info.setName((String) simpleForm.getValue(nameKey));
      info.setCompressed((Boolean) simpleForm.getValue(compressedKey));
      info.setScriptDatasinkDto((ScriptDatasinkDto) simpleForm.getValue(scriptDatasinkKey));

      configDto.addAdditionalInfo(info);

   }

   @Override
   public boolean isActive(SimpleForm simpleForm) {
      return (Boolean) simpleForm.getValue(isExportAsScriptDatasinkKey);
   }

   @Override
   public void loadFields(SimpleForm form, ReportScheduleDefinition definition, ReportDto report) {
      form.loadFields();

      final SingleTreeSelectionField scriptDatasinkField = extractSingleTreeSelectionField(
            form.getField(scriptDatasinkKey));

      if (null != definition) {
         form.setValue(nameKey, DatasinkUIModule.DEFAULT_FILE_NAME);
         ScheduleAsScriptDatasinkInformation info = definition
               .getAdditionalInfo(ScheduleAsScriptDatasinkInformation.class);
         if (null != info) {
            form.setValue(isExportAsScriptDatasinkKey, true);
            form.setValue(nameKey, info.getName());
            form.setValue(compressedKey, info.isCompressed());
            scriptDatasinkField.setValue(info.getScriptDatasinkDto());
         }
      }

   }

   @Override
   public void onWizardPageChange(int pageNr, Widget page, SimpleForm form, ReportScheduleDefinition definition,
         ReportDto report) {
      if (!(page instanceof JobMetadataConfigurationForm))
         return;

      form.setValue(nameKey, DatasinkUIModule.DEFAULT_FILE_NAME);
      if (null != definition) {
         ScheduleAsScriptDatasinkInformation info = definition
               .getAdditionalInfo(ScheduleAsScriptDatasinkInformation.class);
         if (null != info)
            form.setValue(nameKey, info.getName());
      }

   }

   @Override
   public boolean appliesFor(ReportDto report, Collection<ReportViewConfiguration> configs) {
      return true;
   }

}