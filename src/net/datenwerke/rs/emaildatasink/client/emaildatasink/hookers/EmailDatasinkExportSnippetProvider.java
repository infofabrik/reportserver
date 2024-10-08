package net.datenwerke.rs.emaildatasink.client.emaildatasink.hookers;

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
import net.datenwerke.gxtdto.client.forms.simpleform.providers.configs.SFFCShowTwinButton;
import net.datenwerke.gxtdto.client.forms.simpleform.providers.configs.impl.SFFCTextAreaImpl;
import net.datenwerke.gxtdto.client.locale.BaseMessages;
import net.datenwerke.rs.core.client.datasinkmanager.DatasinkUIModule;
import net.datenwerke.rs.core.client.datasinkmanager.HasDefaultDatasink;
import net.datenwerke.rs.core.client.datasinkmanager.helper.forms.DatasinkSelectionField;
import net.datenwerke.rs.core.client.reportexecutor.ui.ReportViewConfiguration;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;
import net.datenwerke.rs.emaildatasink.client.emaildatasink.EmailDatasinkDao;
import net.datenwerke.rs.emaildatasink.client.emaildatasink.EmailDatasinkUiModule;
import net.datenwerke.rs.emaildatasink.client.emaildatasink.dto.EmailDatasinkDto;
import net.datenwerke.rs.emaildatasink.client.emaildatasink.dto.ScheduleAsEmailDatasinkFileInformation;
import net.datenwerke.rs.emaildatasink.client.emaildatasink.provider.annotations.DatasinkTreeEmail;
import net.datenwerke.rs.scheduler.client.scheduler.dto.ReportScheduleDefinition;
import net.datenwerke.rs.scheduler.client.scheduler.hooks.ScheduleExportSnippetProviderHook;
import net.datenwerke.rs.scheduler.client.scheduler.locale.SchedulerMessages;
import net.datenwerke.rs.scheduler.client.scheduler.schedulereport.pages.JobMetadataConfigurationForm;
import net.datenwerke.rs.theme.client.icon.BaseIcon;

public class EmailDatasinkExportSnippetProvider implements ScheduleExportSnippetProviderHook {

   private String isExportAsEmailKey;
   private String nameKey;
   private String emailKey;
   private String subjectKey;
   private String messageKey;
   private String compressedKey;

   private final Provider<UITree> treeProvider;
   private final Provider<EmailDatasinkDao> datasinkDaoProvider;

   @Inject
   public EmailDatasinkExportSnippetProvider(@DatasinkTreeEmail Provider<UITree> treeProvider,
         Provider<EmailDatasinkDao> datasinkDaoProvider) {
      this.treeProvider = treeProvider;
      this.datasinkDaoProvider = datasinkDaoProvider;
   }

   @Override
   public void configureSimpleForm(SimpleForm xform, ReportDto report, Collection<ReportViewConfiguration> configs) {
      xform.setLabelAlign(LabelAlign.LEFT);
      isExportAsEmailKey = xform.addField(Boolean.class, "", new SFFCBoolean() {
         @Override
         public String getBoxLabel() {
            return EmailDatasinkUiModule.NAME;
         }
      });
      xform.setLabelAlign(LabelAlign.TOP);

      xform.setFieldWidth(260);
      xform.beginFloatRow();

      emailKey = xform.addField(DatasinkSelectionField.class, EmailDatasinkUiModule.NAME, new SFFCGenericTreeNode() {
         @Override
         public UITree getTreeForPopup() {
            return treeProvider.get();
         }
      }, new SFFCAllowBlank() {
         @Override
         public boolean allowBlank() {
            return false;
         }
      }, new SFFCShowTwinButton() {
         @Override
         public boolean showTwinButton() {
            return true;
         }
      }, new SFFCDatasinkDao() {
         @Override
         public Provider<? extends HasDefaultDatasink> getDatasinkDaoProvider() {
            return datasinkDaoProvider;
         }

         @Override
         public BaseIcon getIcon() {
            return EmailDatasinkUiModule.ICON;
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

      xform.setLabelWidth(300);
      xform.setLabelAlign(LabelAlign.TOP);
      subjectKey = xform.addField(String.class, SchedulerMessages.INSTANCE.subject(), new SFFCAllowBlank() {
         @Override
         public boolean allowBlank() {
            return false;
         }
      });

      xform.setLabelAlign(LabelAlign.TOP);
      messageKey = xform.addField(String.class, SchedulerMessages.INSTANCE.message(), new SFFCTextAreaImpl());

      xform.setLabelAlign(LabelAlign.LEFT);
      compressedKey = xform.addField(Boolean.class, "", new SFFCBoolean() {
         @Override
         public String getBoxLabel() {
            return SchedulerMessages.INSTANCE.reportCompress();
         }
      });

      xform.addCondition(isExportAsEmailKey, new FieldEquals(true), new ShowHideFieldAction(nameKey));
      xform.addCondition(isExportAsEmailKey, new FieldEquals(true), new ShowHideFieldAction(emailKey));
      xform.addCondition(isExportAsEmailKey, new FieldEquals(true), new ShowHideFieldAction(subjectKey));
      xform.addCondition(isExportAsEmailKey, new FieldEquals(true), new ShowHideFieldAction(messageKey));
      xform.addCondition(isExportAsEmailKey, new FieldEquals(true), new ShowHideFieldAction(compressedKey));

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
      info.setSubject((String) simpleForm.getValue(subjectKey));
      info.setMessage((String) simpleForm.getValue(messageKey));
      info.setName((String) simpleForm.getValue(nameKey));
      info.setCompressed((Boolean) simpleForm.getValue(compressedKey));
      info.setEmailDatasinkDto((EmailDatasinkDto) simpleForm.getValue(emailKey));

      configDto.addAdditionalInfo(info);

   }

   @Override
   public boolean isActive(SimpleForm simpleForm) {
      return (Boolean) simpleForm.getValue(isExportAsEmailKey);
   }

   @Override
   public void loadFields(SimpleForm form, ReportScheduleDefinition definition, ReportDto report) {
      form.loadFields();

      final SingleTreeSelectionField emailField = extractSingleTreeSelectionField(form.getField(emailKey));

      if (null != definition) {
         form.setValue(nameKey, DatasinkUIModule.DEFAULT_FILE_NAME);
         ScheduleAsEmailDatasinkFileInformation info = definition
               .getAdditionalInfo(ScheduleAsEmailDatasinkFileInformation.class);
         if (null != info) {
            form.setValue(isExportAsEmailKey, true);
            form.setValue(subjectKey, info.getSubject());
            form.setValue(messageKey, info.getMessage());
            form.setValue(nameKey, info.getName());
            form.setValue(compressedKey, info.isCompressed());
            emailField.setValue(info.getEmailDatasinkDto());
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