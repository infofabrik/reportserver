package net.datenwerke.rs.scheduler.client.scheduler.schedulereport.pages;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;

import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;

import net.datenwerke.gxtdto.client.baseex.widget.DwContentPanel;
import net.datenwerke.gxtdto.client.forms.simpleform.SimpleForm;
import net.datenwerke.gxtdto.client.forms.simpleform.SimpleMultiForm;
import net.datenwerke.gxtdto.client.forms.wizard.Validatable;
import net.datenwerke.gxtdto.client.forms.wizard.WizardPageChangeListener;
import net.datenwerke.gxtdto.client.forms.wizard.WizardResizer;
import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.core.client.reportexecutor.ui.ReportViewConfiguration;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;
import net.datenwerke.rs.core.client.sendto.SendToClientConfig;
import net.datenwerke.rs.core.service.reportmanager.ReportExecutorService;
import net.datenwerke.rs.printer.client.printer.hookers.PrinterExportSnippetProvider;
import net.datenwerke.rs.scheduler.client.scheduler.dto.ReportScheduleDefinition;
import net.datenwerke.rs.scheduler.client.scheduler.dto.ReportScheduleDefinitionSendToConfig;
import net.datenwerke.rs.scheduler.client.scheduler.hooks.ScheduleExportSnippetProviderHook;
import net.datenwerke.rs.scheduler.client.scheduler.locale.SchedulerMessages;
import net.datenwerke.rs.tabledatasink.client.tabledatasink.hookers.TableDatasinkExportSnippetProvider;
import net.datenwerke.rs.utils.misc.Nullable;

public class SchedulerExportConfigurationForm extends DwContentPanel
      implements Validatable, WizardResizer, WizardPageChangeListener {

   private static final String SEND_TO_ACTIVE_KEY = "__xx_sendToActive";

   private final HookHandlerService hookHandler;

   private Map<ScheduleExportSnippetProviderHook, SimpleForm> formMap = new IdentityHashMap<>();

   private List<ScheduleExportSnippetProviderHook> snippetProviders;

   private Map<String, SimpleForm> sendToForms = new HashMap<>();
   private Map<String, SimpleForm> sendToActivationForms = new HashMap<>();

   private ReportScheduleDefinition definition;
   private Collection<ReportViewConfiguration> configs;

   private SimpleMultiForm form;

   private ReportDto report;
   private String outputFormat;

   @Inject
   public SchedulerExportConfigurationForm(
         HookHandlerService hookHandler,
         @Assisted Collection<ReportViewConfiguration> configs, 
         @Assisted ArrayList<SendToClientConfig> sendToConfigs,
         @Nullable @Assisted ReportScheduleDefinition definition
         ) {
      super();

      /* store objects */
      this.hookHandler = hookHandler;

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

      snippetProviders
         .stream()
         .filter(hooker -> hooker.appliesFor(report, configs))
         .filter(this::filter)
         .forEach(hooker -> {
            final SimpleForm xform = SimpleForm.getInlineInstance();
            xform.addClassName("rs-scheduler-export-action-subform");
   
            hooker.configureSimpleForm(xform, report, configs);
            formMap.put(hooker, xform);
   
            hooker.loadFields(xform, definition, report);
            form.addSubForm(xform);
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
      if ("RS_STREAM_TABLE".equals(outputFormat))
         return 265;
      
      return 505;
   }

   @Override
   public void onPageChange(final int pageNr, final Widget page) {
      if (page instanceof JobReportConfigurationForm) {
         JobReportConfigurationForm jobReportConfigForm = (JobReportConfigurationForm) page;
         
         outputFormat = jobReportConfigForm.getOutputFormat();
         
         final ReportDto report = jobReportConfigForm.getReport();
         initForm(report);
         this.report = report;
      }

      snippetProviders
         .stream()
         .filter(this::filter)
         .filter(hooker -> hooker.appliesFor(report, configs))
         .forEach(hooker -> hooker.onWizardPageChange(pageNr, page, formMap.get(hooker), definition, report));
   }
   
   private boolean filter(ScheduleExportSnippetProviderHook hooker) {
      if (ReportExecutorService.OUTPUT_FORMAT_STREAM_TABLE.equals(outputFormat))
         return hooker instanceof TableDatasinkExportSnippetProvider;
      else {
         if (! ReportExecutorService.OUTPUT_FORMAT_PDF.equals(outputFormat)) 
            return ! (hooker instanceof PrinterExportSnippetProvider) 
                  && ! (hooker instanceof TableDatasinkExportSnippetProvider);
         else
            return ! (hooker instanceof TableDatasinkExportSnippetProvider);
      }
         
   }

}
