package net.datenwerke.rs.emaildatasink.client.emaildatasink.hookers;

import static net.datenwerke.rs.core.client.datasinkmanager.helper.forms.simpleform.DatasinkSimpleFormProvider.extractSingleTreeSelectionField;

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
import net.datenwerke.gxtdto.client.locale.BaseMessages;
import net.datenwerke.rs.core.client.datasinkmanager.helper.forms.DatasinkSelectionField;
import net.datenwerke.rs.core.client.datasinkmanager.locale.DatasinksMessages;
import net.datenwerke.rs.core.client.reportexecutor.ui.ReportViewConfiguration;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;
import net.datenwerke.rs.emaildatasink.client.emaildatasink.dto.EmailDatasinkDto;
import net.datenwerke.rs.emaildatasink.client.emaildatasink.dto.ScheduleAsEmailDatasinkFileInformation;
import net.datenwerke.rs.emaildatasink.client.emaildatasink.provider.annotations.DatasinkTreeEmail;
import net.datenwerke.rs.scheduler.client.scheduler.dto.ReportScheduleDefinition;
import net.datenwerke.rs.scheduler.client.scheduler.hooks.ScheduleExportSnippetProviderHook;
import net.datenwerke.rs.scheduler.client.scheduler.schedulereport.pages.JobMetadataConfigurationForm;

public class EmailDatasinkExportSnippetProvider implements ScheduleExportSnippetProviderHook {

   private String isExportAsFileKey;
   private String nameKey;
   private String emailKey;

   private final Provider<UITree> treeProvider;

   @Inject
   public EmailDatasinkExportSnippetProvider(@DatasinkTreeEmail Provider<UITree> treeProvider) {
      this.treeProvider = treeProvider;
   }

   @Override
   public void configureSimpleForm(SimpleForm xform, ReportDto report, Collection<ReportViewConfiguration> configs) {
      xform.setLabelAlign(LabelAlign.LEFT);
      isExportAsFileKey = xform.addField(Boolean.class, "", new SFFCBoolean() {
         @Override
         public String getBoxLabel() {
            return DatasinksMessages.INSTANCE.email() + " - SMTP";
         }
      });
      xform.setLabelAlign(LabelAlign.TOP);

      xform.setFieldWidth(260);
      xform.beginFloatRow();

      emailKey = xform.addField(DatasinkSelectionField.class, DatasinksMessages.INSTANCE.email() + " - SMTP",
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
            });

      xform.endRow();
      xform.setFieldWidth(530);

      nameKey = xform.addField(String.class, BaseMessages.INSTANCE.propertyName(), new SFFCAllowBlank() {
         @Override
         public boolean allowBlank() {
            return false;
         }
      });

      xform.addCondition(isExportAsFileKey, new FieldEquals(true), new ShowHideFieldAction(nameKey));
      xform.addCondition(isExportAsFileKey, new FieldEquals(true), new ShowHideFieldAction(emailKey));

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

      ScheduleAsEmailDatasinkFileInformation info = new ScheduleAsEmailDatasinkFileInformation();
      info.setName((String) simpleForm.getValue(nameKey));
      info.setEmailDatasinkDto((EmailDatasinkDto) simpleForm.getValue(emailKey));

      configDto.addAdditionalInfo(info);

   }

   @Override
   public boolean isActive(SimpleForm simpleForm) {
      return (Boolean) simpleForm.getValue(isExportAsFileKey);
   }

   @Override
   public void loadFields(SimpleForm form, ReportScheduleDefinition definition, ReportDto report) {
      form.loadFields();

      final SingleTreeSelectionField emailField = extractSingleTreeSelectionField(form.getField(emailKey));

      if (null != definition) {
         form.setValue(nameKey, "${now} - " + definition.getTitle());
         ScheduleAsEmailDatasinkFileInformation info = definition
               .getAdditionalInfo(ScheduleAsEmailDatasinkFileInformation.class);
         if (null != info) {
            form.setValue(isExportAsFileKey, true);
            form.setValue(nameKey, info.getName());
            emailField.setValue(info.getEmailDatasinkDto());
         }
      }

      emailField.addValueChangeHandler(event -> {
         if (null == event.getValue())
            return;

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
         ScheduleAsEmailDatasinkFileInformation info = definition
               .getAdditionalInfo(ScheduleAsEmailDatasinkFileInformation.class);
         if (null != info)
            form.setValue(nameKey, info.getName());
      }

   }

   @Override
   public boolean appliesFor(ReportDto report, Collection<ReportViewConfiguration> configs) {
      return true;
   }

}