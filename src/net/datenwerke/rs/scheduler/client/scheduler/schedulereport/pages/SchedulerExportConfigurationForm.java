package net.datenwerke.rs.scheduler.client.scheduler.schedulereport.pages;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;

import com.google.gwt.core.client.JsonUtils;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import com.sencha.gxt.widget.core.client.Component;
import com.sencha.gxt.widget.core.client.form.FormPanel.LabelAlign;

import net.datenwerke.gxtdto.client.baseex.widget.DwContentPanel;
import net.datenwerke.gxtdto.client.dialog.error.SimpleErrorDialog;
import net.datenwerke.gxtdto.client.forms.simpleform.SimpleForm;
import net.datenwerke.gxtdto.client.forms.simpleform.SimpleMultiForm;
import net.datenwerke.gxtdto.client.forms.simpleform.actions.SimpleFormAction;
import net.datenwerke.gxtdto.client.forms.simpleform.conditions.FieldEquals;
import net.datenwerke.gxtdto.client.forms.simpleform.json.SimpleFormJsonConfig;
import net.datenwerke.gxtdto.client.forms.wizard.Validatable;
import net.datenwerke.gxtdto.client.forms.wizard.WizardPageChangeListener;
import net.datenwerke.gxtdto.client.forms.wizard.WizardResizer;
import net.datenwerke.gxtdto.client.locale.BaseMessages;
import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.core.client.reportexecutor.ui.ReportViewConfiguration;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;
import net.datenwerke.rs.core.client.sendto.SendToClientConfig;
import net.datenwerke.rs.core.client.sendto.SendToJsonConfig;
import net.datenwerke.rs.scheduler.client.scheduler.dto.ReportScheduleDefinition;
import net.datenwerke.rs.scheduler.client.scheduler.dto.ReportScheduleDefinitionSendToConfig;
import net.datenwerke.rs.scheduler.client.scheduler.hooks.ScheduleExportSnippetProviderHook;
import net.datenwerke.rs.scheduler.client.scheduler.locale.SchedulerMessages;
import net.datenwerke.rs.utils.misc.Nullable;

public class SchedulerExportConfigurationForm extends DwContentPanel
      implements Validatable, WizardResizer, WizardPageChangeListener {

   private static final String SEND_TO_ACTIVE_KEY = "__xx_sendToActive";

   private final HookHandlerService hookHandler;

   private Map<ScheduleExportSnippetProviderHook, SimpleForm> formMap = new IdentityHashMap<ScheduleExportSnippetProviderHook, SimpleForm>();

   private List<ScheduleExportSnippetProviderHook> snippetProviders;

   private ArrayList<SendToClientConfig> sendToConfigs;
   private Map<String, SimpleForm> sendToForms = new HashMap<String, SimpleForm>();
   private Map<String, SimpleForm> sendToActivationForms = new HashMap<String, SimpleForm>();

   private ReportScheduleDefinition definition;
   private Collection<ReportViewConfiguration> configs;

   private SimpleMultiForm form;

   private ReportDto report;

   @Inject
   public SchedulerExportConfigurationForm(HookHandlerService hookHandler,
         @Assisted Collection<ReportViewConfiguration> configs, @Assisted ArrayList<SendToClientConfig> sendToConfigs,
         @Nullable @Assisted ReportScheduleDefinition definition) {
      super();

      /* store objects */
      this.hookHandler = hookHandler;
      this.sendToConfigs = sendToConfigs;

      this.definition = definition;
      this.configs = configs;

   }

   private void initForm(ReportDto report) {
      setBorders(false);
      setBodyBorder(false);
      setHeaderVisible(false);
      enableScrollContainer();

      formMap.clear();
      sendToForms.clear();
      sendToActivationForms.clear();

      form = SimpleMultiForm.createInlineInstance();
      form.addClassName("rs-scheduler-export-action-form");

      this.snippetProviders = hookHandler.getHookers(ScheduleExportSnippetProviderHook.class);

      snippetProviders.stream().filter(hooker -> hooker.appliesFor(report, configs)).forEach(hooker -> {
         final SimpleForm xform = SimpleForm.getInlineInstance();
         xform.addClassName("rs-scheduler-export-action-subform");

         hooker.configureSimpleForm(xform, report, configs);
         formMap.put(hooker, xform);

         hooker.loadFields(xform, definition, report);

         form.addSubForm(xform);
      });

      /* send-to configs */
      sendToConfigs.forEach(sendToConfig -> {
         final SimpleForm activateForm = SimpleForm.getInlineInstance();

         String title = sendToConfig.getTitle();

         activateForm.setLabelAlign(LabelAlign.LEFT);
         sendToActivationForms.put(sendToConfig.getId(), activateForm);
         activateForm.addField(Boolean.class, SEND_TO_ACTIVE_KEY, title);
         form.addSubForm(activateForm);

         if (null != definition)
            if (null != definition && null != definition.getSendToConfig(sendToConfig.getId()))
               activateForm.setValue(SEND_TO_ACTIVE_KEY, true);

         if (null != sendToConfig.getForm()) {
            SendToJsonConfig formConfig;
            try {
               formConfig = JsonUtils.safeEval(sendToConfig.getForm()).cast();

               final SimpleForm detailForm = SimpleForm.fromJson((SimpleFormJsonConfig) formConfig.getForm().cast());

               if (null != definition) {
                  final Map<String, String> values = definition.getSendToConfig(sendToConfig.getId()).getValues();
                  if (null != values) {
                     detailForm.getFieldKeys().stream().filter(field -> values.containsKey(field))
                           .forEach(field -> detailForm.setValue(field, values.get(field)));
                  }
               }

               detailForm.loadFields();
               form.addSubForm(detailForm);
               sendToForms.put(sendToConfig.getId(), detailForm);

               activateForm.addCondition(SEND_TO_ACTIVE_KEY, new FieldEquals(true), new SimpleFormAction() {
                  @Override
                  public void onSuccess(SimpleForm form) {
                     for (String key : detailForm.getFieldKeys()) {
                        Widget field = detailForm.getDisplayedField(key);
                        if (null == field)
                           continue;
                        if (field instanceof Component)
                           ((Component) field).show();
                        else
                           field.setVisible(true);
                     }

                     detailForm.forceLayout();
                  }

                  @Override
                  public void onFailure(SimpleForm form) {
                     for (String key : detailForm.getFieldKeys()) {
                        Widget field = detailForm.getDisplayedField(key);
                        if (null == field)
                           continue;
                        if (field instanceof Component)
                           ((Component) field).hide();
                        else
                           field.setVisible(false);
                     }

                     detailForm.forceLayout();
                  }
               });

            } catch (RuntimeException e) {
               new SimpleErrorDialog(BaseMessages.INSTANCE.error(), e.getMessage()).show();
               return;
            }
         }

         activateForm.loadFields();
      });

      form.loadFields();

      DwContentPanel wrapper = new DwContentPanel();
      wrapper.setLightDarkStyle();
      wrapper.setHeading(SchedulerMessages.INSTANCE.exportConfigHeadline());
      wrapper.setInfoText(SchedulerMessages.INSTANCE.exportConfigDescription());
      wrapper.add(form);

      setWidget(wrapper);
   }

   @Override
   public boolean isValid() {
      boolean active = false;
      for (ScheduleExportSnippetProviderHook hooker : snippetProviders) {
         if (!formMap.containsKey(hooker))
            continue;
         if (hooker.isActive(formMap.get(hooker)))
            active = true;
         if (hooker.isActive(formMap.get(hooker)) && !hooker.isValid(formMap.get(hooker)))
            return false;
      }

      for (String id : sendToActivationForms.keySet()) {
         SimpleForm form = sendToActivationForms.get(id);
         if (Boolean.TRUE.equals(form.getValue(SEND_TO_ACTIVE_KEY))) {
            active = true;
            if (sendToForms.containsKey(id) && !sendToForms.get(id).isValid())
               return false;
         }
      }

      return active;
   }

   public void configureConfig(final ReportScheduleDefinition configDto) {
      snippetProviders.stream().filter(hooker -> formMap.containsKey(hooker))
            .forEach(hooker -> hooker.configureConfig(configDto, formMap.get(hooker)));

      /* send to */
      for (String id : sendToActivationForms.keySet()) {
         Boolean isActive = (Boolean) sendToActivationForms.get(id).getValue(SEND_TO_ACTIVE_KEY);
         if (Boolean.TRUE.equals(isActive)) {
            Map<String, String> values = new HashMap<>();
            if (sendToForms.containsKey(id)) {
               SimpleForm form = sendToForms.get(id);
               values = form.getStringValueMap();
            }
            configDto.addSendToConfigs(new ReportScheduleDefinitionSendToConfig(id, values));
         }
      }
   }

   @Override
   public int getPageHeight() {
      return 505;
   }

   @Override
   public void onPageChange(final int pageNr, final Widget page) {
      if (page instanceof JobReportConfigurationForm) {
         final ReportDto report = ((JobReportConfigurationForm) page).getReport();

         if (report != this.report)
            initForm(report);

         this.report = report;
      }

      snippetProviders.stream().filter(hooker -> hooker.appliesFor(report, configs))
            .forEach(hooker -> hooker.onWizardPageChange(pageNr, page, formMap.get(hooker), definition, report));
   }

}
