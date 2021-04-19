package net.datenwerke.rs.scheduler.client.scheduler.schedulereport;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.gxtdto.client.forms.wizard.WizardDialog;
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
import net.datenwerke.rs.scheduler.client.scheduler.schedulereport.pages.SchedulerBaseConfigurationForm;
import net.datenwerke.rs.scheduler.client.scheduler.schedulereport.pages.SchedulerExportConfigurationForm;
import net.datenwerke.rs.scheduler.client.scheduler.schedulereport.pages.SchedulerMetadataConfigFormFactory;
import net.datenwerke.rs.scheduler.client.scheduler.schedulereport.pages.SchedulerReportConfigFormFactory;
import net.datenwerke.rs.scheduler.client.scheduler.schedulereport.pages.SeriesConfigFormFactory;
import net.datenwerke.rs.scheduler.client.scheduler.schedulereport.pages.SeriesConfigurationForm;
import net.datenwerke.scheduler.client.scheduler.dto.config.complex.DateTriggerConfigDto;

public class ScheduleDialog {

	private final Provider<SchedulerBaseConfigurationForm> schedulerBaseConfigurationFormProvider;
	private final SchedulerMetadataConfigFormFactory metadataConfigurationFormFactory;
	private final SchedulerReportConfigFormFactory reportConfigurationFormFactory;
	private final ExportConfigFormFactory exportFormFactory;
	private final SeriesConfigFormFactory seriesConfigurationFormFactory;
	private final HookHandlerService hookHandler;
	
	
	public interface DialogCallback{
		void finished(ReportScheduleDefinition configDto, WizardDialog dialog);
	}
	
	@Inject
	public ScheduleDialog(
			Provider<SchedulerBaseConfigurationForm> schedulerConfigurationFormProvider,
			SchedulerMetadataConfigFormFactory metadataConfigurationFormFactory,
			SchedulerReportConfigFormFactory reportConfigurationFormFactory,
			ExportConfigFormFactory exportFormFactory,
			SeriesConfigFormFactory seriesConfigurationFormFactory,
			HookHandlerService hookHandler
			) {
		
		/* store objects */
		this.schedulerBaseConfigurationFormProvider = schedulerConfigurationFormProvider;
		this.metadataConfigurationFormFactory = metadataConfigurationFormFactory;
		this.reportConfigurationFormFactory = reportConfigurationFormFactory;
		this.exportFormFactory = exportFormFactory;
		this.seriesConfigurationFormFactory = seriesConfigurationFormFactory;
		this.hookHandler = hookHandler;
		
	}

	public void displayDialog(final ReportDto report, Collection<ReportViewConfiguration> configs, ArrayList<SendToClientConfig> sendToConfigs, DialogCallback callback){
		displayDialog(report, configs, sendToConfigs, null, callback);
	}
	
	public void displayEdit(ReportScheduleDefinition definition, ArrayList<SendToClientConfig> sendToConfigs, ReportDto report, DialogCallback callback) {
		displayDialog(report, new HashSet<ReportViewConfiguration>(), sendToConfigs, definition, callback);
	}
	
	protected void displayDialog(final ReportDto report, Collection<ReportViewConfiguration> configs, ArrayList<SendToClientConfig> sendToConfigs, ReportScheduleDefinition definition, final DialogCallback callback){
		final SchedulerBaseConfigurationForm schedulerConfigForm = schedulerBaseConfigurationFormProvider.get();
		final JobMetadataConfigurationForm jobMetadataConfigForm = metadataConfigurationFormFactory.create(report, configs, definition);
		final JobReportConfigurationForm jobReportConfigForm = reportConfigurationFormFactory.create(report, configs, definition);
		final SchedulerExportConfigurationForm exportForm = exportFormFactory.create(report, configs, sendToConfigs, definition);
		final SeriesConfigurationForm seriesConfigForm = seriesConfigurationFormFactory.create(report, definition);

		final List<ScheduleConfigWizardPageProviderHook> addPages = hookHandler.getHookers(ScheduleConfigWizardPageProviderHook.class);
		
		WizardDialog wizard = new WizardDialog(){
			@Override
			public void onFinish() {
				DateTriggerConfigDto schedulerConfigDto = seriesConfigForm.createDto();
				ReportScheduleDefinition configDto = new ReportScheduleDefinition();
				configDto.setSchedulerConfig(schedulerConfigDto);
				configDto.setReport(jobReportConfigForm.getReport());
				
				configDto.setTitle(jobMetadataConfigForm.getTitleValue());
				configDto.setDescription(jobMetadataConfigForm.getDescriptionValue());
				
				configDto.setExecutor(schedulerConfigForm.getExecutor());
				
				configDto.setRecipients(
					schedulerConfigForm.getSelectedRecipientsStore().getAll().stream()
						.map( sUser -> sUser.getId()).collect(Collectors.toList())
				);
				
				/* owners */
				configDto.setOwners(new ArrayList<>(schedulerConfigForm.getSelectedOwnerStore().getAll()));
				
				exportForm.configureConfig(configDto);
				configDto.setOutputFormat(jobReportConfigForm.getOutputFormat());
				configDto.setExportConfiguration(jobReportConfigForm.getExportConfiguration());
				
				addPages.forEach(pageProvider -> pageProvider.configureConfig(configDto, report));
				
				callback.finished(configDto, this);
			}
			
		};
		
		wizard.setCollapsible(true);
		wizard.setTitleCollapse(true);
		wizard.setModal(false);
		
		schedulerConfigForm.configureForm(report, definition, wizard, addPages);
		wizard.addPage(schedulerConfigForm);
		wizard.addPage(jobMetadataConfigForm);
		wizard.addPage(jobReportConfigForm);
		wizard.addPage(exportForm);
		
		addPages.stream()
			.filter(pageProvider -> ! pageProvider.isAdvanced())
			.forEach(pageProvider -> wizard.addPage(pageProvider.getPage(report, definition)));
		
		wizard.addPage(seriesConfigForm);
		wizard.setSize(640, 580);
		wizard.setHeading(SchedulerMessages.INSTANCE.scheduleReportMulti(report.getName()));
		wizard.show();
	}

}
