package net.datenwerke.rs.scheduler.client.scheduler.hookers;

import java.util.Collection;

import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.sencha.gxt.widget.core.client.form.FormPanel.LabelAlign;

import net.datenwerke.gxtdto.client.dtomanager.callback.RsAsyncCallback;
import net.datenwerke.gxtdto.client.forms.simpleform.SimpleForm;
import net.datenwerke.gxtdto.client.forms.simpleform.actions.ShowHideFieldAction;
import net.datenwerke.gxtdto.client.forms.simpleform.conditions.FieldEquals;
import net.datenwerke.gxtdto.client.forms.simpleform.providers.configs.SFFCBoolean;
import net.datenwerke.gxtdto.client.forms.simpleform.providers.configs.impl.SFFCTextAreaImpl;
import net.datenwerke.rs.core.client.reportexecutor.ui.ReportViewConfiguration;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;
import net.datenwerke.rs.scheduler.client.scheduler.SchedulerDao;
import net.datenwerke.rs.scheduler.client.scheduler.dto.EmailInformation;
import net.datenwerke.rs.scheduler.client.scheduler.dto.ReportScheduleDefinition;
import net.datenwerke.rs.scheduler.client.scheduler.hooks.ScheduleExportSnippetProviderHook;
import net.datenwerke.rs.scheduler.client.scheduler.locale.SchedulerMessages;

public class EmailExportSnippetProvider implements ScheduleExportSnippetProviderHook {

   private final Provider<SchedulerDao> schedulerDaoProvider;

   private String isEmailKey;
   private String subjectKey;
   private String messageKey;
   private String compressedKey;

   @Inject
   public EmailExportSnippetProvider(Provider<SchedulerDao> schedulerDaoProvider) {
      this.schedulerDaoProvider = schedulerDaoProvider;
   }

   @Override
   public void configureSimpleForm(SimpleForm xform, ReportDto report, Collection<ReportViewConfiguration> configs) {
      xform.setLabelWidth(0);
      xform.setLabelAlign(LabelAlign.LEFT);
      isEmailKey = xform.addField(Boolean.class, "", new SFFCBoolean() {
         @Override
         public String getBoxLabel() {
            return SchedulerMessages.INSTANCE.askSendEmail() + " (deprecated)";
         }
      });

      xform.setLabelWidth(300);
      xform.setLabelAlign(LabelAlign.TOP);
      subjectKey = xform.addField(String.class, SchedulerMessages.INSTANCE.subject());

      xform.setLabelAlign(LabelAlign.TOP);
      messageKey = xform.addField(String.class, SchedulerMessages.INSTANCE.message(), new SFFCTextAreaImpl());

      xform.setLabelAlign(LabelAlign.LEFT);
      compressedKey = xform.addField(Boolean.class, "", new SFFCBoolean() {
         @Override
         public String getBoxLabel() {
            return SchedulerMessages.INSTANCE.reportCompress();
         }
      });

      xform.addCondition(isEmailKey, new FieldEquals(true), new ShowHideFieldAction(subjectKey));
      xform.addCondition(isEmailKey, new FieldEquals(true), new ShowHideFieldAction(messageKey));
      xform.addCondition(isEmailKey, new FieldEquals(true), new ShowHideFieldAction(compressedKey));

   }

   @Override
   public boolean isValid(SimpleForm simpleForm) {
      String subject = (String) simpleForm.getValue(subjectKey);

      return (null != subject && !subject.isEmpty());

   }

   @Override
   public void loadFields(SimpleForm form, ReportScheduleDefinition definition, ReportDto report) {
      if (null == definition) {
         form.setValue(isEmailKey, true);
         schedulerDaoProvider.get().isDefaultEmailCompression(new RsAsyncCallback<Boolean>() {
            @Override
            public void onSuccess(Boolean result) {
               if (null != result) {
                  form.setValue(compressedKey, result);
               }
            }
         });
      }

      if (null != definition) {
         EmailInformation info = definition.getAdditionalInfo(EmailInformation.class);
         if (null != info) {
            form.setValue(isEmailKey, true);
            form.setValue(compressedKey, info.isCompressed());
            form.setValue(subjectKey, info.getSubject());
            form.setValue(messageKey, info.getMessage());
         }
      }

      form.loadFields();
   }

   @Override
   public void configureConfig(ReportScheduleDefinition configDto, SimpleForm simpleForm) {
      if (!isActive(simpleForm))
         return;

      EmailInformation emailInfo = new EmailInformation();

      emailInfo.setSubject((String) simpleForm.getValue(subjectKey));
      emailInfo.setMessage((String) simpleForm.getValue(messageKey));
      emailInfo.setCompressed((Boolean) simpleForm.getValue(compressedKey));

      configDto.addAdditionalInfo(emailInfo);
   }

   @Override
   public boolean isActive(SimpleForm simpleForm) {
      return (Boolean) simpleForm.getValue(isEmailKey);
   }

   @Override
   public boolean appliesFor(ReportDto report, Collection<ReportViewConfiguration> configs) {
      return true;
   }

   @Override
   public void onWizardPageChange(int pageNr, Widget page, SimpleForm form, ReportScheduleDefinition definition,
         ReportDto report) {
   }

}
