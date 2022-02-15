package net.datenwerke.rs.tabledatasink.client.tabledatasink.hookers;

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
import net.datenwerke.rs.base.client.reportengines.table.dto.TableReportDto;
import net.datenwerke.rs.core.client.datasinkmanager.DatasinkTreeManagerDao;
import net.datenwerke.rs.core.client.datasinkmanager.HasDefaultDatasink;
import net.datenwerke.rs.core.client.datasinkmanager.helper.forms.DatasinkSelectionField;
import net.datenwerke.rs.core.client.reportexecutor.ui.ReportViewConfiguration;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;
import net.datenwerke.rs.scheduler.client.scheduler.dto.ReportScheduleDefinition;
import net.datenwerke.rs.scheduler.client.scheduler.hooks.ScheduleExportSnippetProviderHook;
import net.datenwerke.rs.scheduler.client.scheduler.schedulereport.pages.JobMetadataConfigurationForm;
import net.datenwerke.rs.tabledatasink.client.tabledatasink.TableDatasinkDao;
import net.datenwerke.rs.tabledatasink.client.tabledatasink.TableDatasinkUiModule;
import net.datenwerke.rs.tabledatasink.client.tabledatasink.dto.ScheduleAsTableDatasinkFileInformation;
import net.datenwerke.rs.tabledatasink.client.tabledatasink.dto.TableDatasinkDto;
import net.datenwerke.rs.tabledatasink.client.tabledatasink.provider.annotations.DatasinkTreeTableDatasink;
import net.datenwerke.rs.theme.client.icon.BaseIcon;

public class TableDatasinkExportSnippetProvider implements ScheduleExportSnippetProviderHook {

   private String isExportAsFileKey;
   private String nameKey;
   private String tableDatasinkKey;

   private final Provider<UITree> treeProvider;
   private final Provider<TableDatasinkDao> datasinkDaoProvider;

   @Inject
   public TableDatasinkExportSnippetProvider(
         @DatasinkTreeTableDatasink Provider<UITree> treeProvider,
         DatasinkTreeManagerDao datasinkTreeManager, 
         Provider<TableDatasinkDao> datasinkDaoProvider
         ) {
      this.treeProvider = treeProvider;
      this.datasinkDaoProvider = datasinkDaoProvider;
   }

   @Override
   public void configureSimpleForm(SimpleForm xform, ReportDto report, Collection<ReportViewConfiguration> configs) {
      xform.setLabelAlign(LabelAlign.LEFT);
      isExportAsFileKey = xform.addField(Boolean.class, "", new SFFCBoolean() {
         @Override
         public String getBoxLabel() {
            return TableDatasinkUiModule.NAME;
         }
      });
      xform.setLabelAlign(LabelAlign.TOP);

      xform.setFieldWidth(260);
      xform.beginFloatRow();

      tableDatasinkKey = xform.addField(DatasinkSelectionField.class, TableDatasinkUiModule.NAME, new SFFCGenericTreeNode() {
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
            return TableDatasinkUiModule.ICON;
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

      xform.addCondition(isExportAsFileKey, new FieldEquals(true), new ShowHideFieldAction(nameKey));
      xform.addCondition(isExportAsFileKey, new FieldEquals(true), new ShowHideFieldAction(tableDatasinkKey));

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

      ScheduleAsTableDatasinkFileInformation info = new ScheduleAsTableDatasinkFileInformation();
      info.setName((String) simpleForm.getValue(nameKey));
      info.setTableDatasinkDto((TableDatasinkDto) simpleForm.getValue(tableDatasinkKey));

      configDto.addAdditionalInfo(info);
   }

   @Override
   public boolean isActive(SimpleForm simpleForm) {
      return (Boolean) simpleForm.getValue(isExportAsFileKey);
   }

   @Override
   public void loadFields(SimpleForm form, ReportScheduleDefinition definition, ReportDto report) {
      form.loadFields();

      final SingleTreeSelectionField tableField = extractSingleTreeSelectionField(form.getField(tableDatasinkKey));

      if (null != definition) {
         form.setValue(nameKey, "${now} - " + definition.getTitle());
         ScheduleAsTableDatasinkFileInformation info = definition
               .getAdditionalInfo(ScheduleAsTableDatasinkFileInformation.class);
         if (null != info) {
            form.setValue(isExportAsFileKey, true);
            form.setValue(nameKey, info.getName());
            tableField.setValue(info.getTableDatasinkDto());
         }
      }

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
         ScheduleAsTableDatasinkFileInformation info = definition
               .getAdditionalInfo(ScheduleAsTableDatasinkFileInformation.class);
         if (null != info)
            form.setValue(nameKey, info.getName());
      }

   }

   @Override
   public boolean appliesFor(ReportDto report, Collection<ReportViewConfiguration> configs) {
      return report instanceof TableReportDto;
   }

}
