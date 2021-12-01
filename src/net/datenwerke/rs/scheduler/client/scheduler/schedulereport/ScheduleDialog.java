package net.datenwerke.rs.scheduler.client.scheduler.schedulereport;

import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import com.google.inject.Inject;

import net.datenwerke.gxtdto.client.forms.wizard.WizardDialog;
import net.datenwerke.gxtdto.client.locale.BaseMessages;
import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.core.client.reportexecutor.ui.ReportViewConfiguration;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;
import net.datenwerke.rs.core.client.sendto.SendToClientConfig;
import net.datenwerke.rs.scheduler.client.scheduler.dto.ReportScheduleDefinition;
import net.datenwerke.rs.scheduler.client.scheduler.hooks.ScheduleConfigWizardPageProviderHook;
import net.datenwerke.rs.scheduler.client.scheduler.locale.SchedulerMessages;
import net.datenwerke.rs.scheduler.client.scheduler.schedulereport.pages.ExportConfigFormFactory;
import net.datenwerke.rs.scheduler.client.scheduler.schedulereport.pages.JobMetadataConfigurationForm;
import net.datenwerke.rs.scheduler.client.scheduler.schedulereport.pages.JobReportConfigurationForm;
import net.datenwerke.rs.scheduler.client.scheduler.schedulereport.pages.SchedulerExportConfigurationForm;
import net.datenwerke.rs.scheduler.client.scheduler.schedulereport.pages.SchedulerMetadataConfigFormFactory;
import net.datenwerke.rs.scheduler.client.scheduler.schedulereport.pages.SchedulerReportConfigFormFactory;
import net.datenwerke.rs.scheduler.client.scheduler.schedulereport.pages.SchedulerUsersConfigFormFactory;
import net.datenwerke.rs.scheduler.client.scheduler.schedulereport.pages.SchedulerUsersConfigurationForm;
import net.datenwerke.rs.scheduler.client.scheduler.schedulereport.pages.SeriesConfigFormFactory;
import net.datenwerke.rs.scheduler.client.scheduler.schedulereport.pages.SeriesConfigurationForm;
import net.datenwerke.scheduler.client.scheduler.dto.config.complex.DateTriggerConfigDto;
import net.datenwerke.security.client.usermanager.dto.ie.StrippedDownUser;

public class ScheduleDialog {

   private final SchedulerUsersConfigFormFactory schedulerUsersConfigurationFormFactory;
   private final SchedulerMetadataConfigFormFactory metadataConfigurationFormFactory;
   private final SchedulerReportConfigFormFactory reportConfigurationFormFactory;
   private final ExportConfigFormFactory exportFormFactory;
   private final SeriesConfigFormFactory seriesConfigurationFormFactory;
   private final HookHandlerService hookHandler;

   public interface DialogCallback {
      void finished(ReportScheduleDefinition configDto, WizardDialog dialog);
   }

   @Inject
   public ScheduleDialog(
         SchedulerUsersConfigFormFactory schedulerUsersConfigurationFormFactory,
         SchedulerMetadataConfigFormFactory metadataConfigurationFormFactory,
         SchedulerReportConfigFormFactory reportConfigurationFormFactory, 
         ExportConfigFormFactory exportFormFactory,
         SeriesConfigFormFactory seriesConfigurationFormFactory, 
         HookHandlerService hookHandler) {

      /* store objects */
      this.schedulerUsersConfigurationFormFactory = schedulerUsersConfigurationFormFactory;
      this.metadataConfigurationFormFactory = metadataConfigurationFormFactory;
      this.reportConfigurationFormFactory = reportConfigurationFormFactory;
      this.exportFormFactory = exportFormFactory;
      this.seriesConfigurationFormFactory = seriesConfigurationFormFactory;
      this.hookHandler = hookHandler;

   }

   public void displayDialog(final Optional<ReportDto> report, Collection<ReportViewConfiguration> configs,
         ArrayList<SendToClientConfig> sendToConfigs, DialogCallback callback) {
      displayDialog(report, configs, sendToConfigs, null, callback);
   }

   public void displayEdit(ReportScheduleDefinition definition, ArrayList<SendToClientConfig> sendToConfigs,
         ReportDto report, DialogCallback callback) {
      displayDialog(Optional.of(report), new HashSet<ReportViewConfiguration>(), sendToConfigs, definition, callback);
   }

   protected void displayDialog(
         final Optional<ReportDto> report, 
         Collection<ReportViewConfiguration> configs,
         ArrayList<SendToClientConfig> sendToConfigs, 
         ReportScheduleDefinition jobDefinition,
         final DialogCallback callback) {
      
      final List<ScheduleConfigWizardPageProviderHook> advancedPages = hookHandler
            .getHookers(ScheduleConfigWizardPageProviderHook.class);
      
      final JobReportConfigurationForm jobReportConfigForm = reportConfigurationFormFactory.create(report, configs,
            jobDefinition, advancedPages);
      final SchedulerUsersConfigurationForm schedulerUsersConfigForm = schedulerUsersConfigurationFormFactory.create(jobDefinition);
      final JobMetadataConfigurationForm jobMetadataConfigForm = metadataConfigurationFormFactory.create(jobDefinition);
      final SchedulerExportConfigurationForm exportForm = exportFormFactory.create(configs, sendToConfigs,
            jobDefinition);
      final SeriesConfigurationForm seriesConfigForm = seriesConfigurationFormFactory.create(jobDefinition);

      WizardDialog wizard = new WizardDialog() {
         @Override
         public void onFinish() {
            DateTriggerConfigDto schedulerConfigDto = seriesConfigForm.createDto();
            final ReportScheduleDefinition configDto = new ReportScheduleDefinition();
            configDto.setSchedulerConfig(schedulerConfigDto);
            configDto.setReport(jobReportConfigForm.getReport());

            configDto.setTitle(jobMetadataConfigForm.getTitleValue());
            configDto.setDescription(jobMetadataConfigForm.getDescriptionValue());

            configDto.setExecutor(schedulerUsersConfigForm.getExecutor());

            configDto.setRecipients(schedulerUsersConfigForm.getSelectedRecipientsStore().getAll()
                  .stream()
                  .map(StrippedDownUser::getId)
                  .collect(toList()));

            /* owners */
            configDto.setOwners(new ArrayList<>(schedulerUsersConfigForm.getSelectedOwnerStore().getAll()));

            exportForm.configureConfig(configDto);
            configDto.setOutputFormat(jobReportConfigForm.getOutputFormat());
            configDto.setExportConfiguration(jobReportConfigForm.getExportConfiguration());

            advancedPages.forEach(pageProvider -> pageProvider.configureConfig(configDto, jobReportConfigForm.getReport()));

            callback.finished(configDto, this);
         }

      };

      wizard.setCollapsible(true);
      wizard.setTitleCollapse(true);
      wizard.setModal(false);

      wizard.addPage(jobReportConfigForm);
      wizard.addPage(schedulerUsersConfigForm);
      wizard.addPage(jobMetadataConfigForm);
      wizard.addPage(exportForm);
      wizard.addPage(seriesConfigForm);
      
      wizard.setSize(640, 556);
      wizard.setHeading(
            SchedulerMessages.INSTANCE.scheduleReportMulti(report.isPresent() ? report.get().getName() : BaseMessages.INSTANCE.name()));
      wizard.show();
   }

}
