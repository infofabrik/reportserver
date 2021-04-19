package net.datenwerke.rs.core.client.reportexporter.exporter.generic;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.resources.client.ImageResource;
import com.google.inject.Inject;
import com.sencha.gxt.widget.core.client.container.MarginData;

import net.datenwerke.gf.client.config.ClientConfigJSONService;
import net.datenwerke.gxtdto.client.baseex.widget.DwWindow;
import net.datenwerke.gxtdto.client.baseex.widget.DwWindow.OnButtonClickHandler;
import net.datenwerke.gxtdto.client.baseex.widget.btn.DwTextButton;
import net.datenwerke.gxtdto.client.dtomanager.callback.RsAsyncCallback;
import net.datenwerke.gxtdto.client.forms.simpleform.SimpleForm;
import net.datenwerke.rs.core.client.reportexecutor.locale.ReportexecutorMessages;
import net.datenwerke.rs.core.client.reportexporter.ReportExporterDao;
import net.datenwerke.rs.core.client.reportexporter.dto.RECCsvDto;
import net.datenwerke.rs.core.client.reportexporter.dto.ReportExecutionConfigDto;
import net.datenwerke.rs.core.client.reportexporter.dto.decorator.RECCsvDtoDec;
import net.datenwerke.rs.core.client.reportexporter.dto.pa.RECCsvDtoPA;
import net.datenwerke.rs.core.client.reportexporter.exporter.ReportExporterImpl;
import net.datenwerke.rs.core.client.reportexporter.locale.ReportExporterMessages;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;
import net.datenwerke.rs.theme.client.icon.BaseIcon;

public abstract class Export2CSV extends ReportExporterImpl {
	
	protected RECCsvDto config;
	private final ReportExporterDao exporterDao;
	private final ClientConfigJSONService jsonService;
	private static final String CONFIGURATION_FILE = "csvexport.cf";
	
	@Inject
	public Export2CSV(ReportExporterDao exporterDao, ClientConfigJSONService jsonService) {
		this.exporterDao = exporterDao;
		this.jsonService = jsonService;
	}
	
	@Override
	public boolean consumesConfiguration(ReportDto report) {
		return true;
	}
	
	@Override
	public void configureFrom(List<ReportExecutionConfigDto> exportConfiguration) {
		if(null == exportConfiguration || exportConfiguration.isEmpty())
			return;
		
		for(ReportExecutionConfigDto config : exportConfiguration){
			if(config instanceof RECCsvDtoDec){
				this.config = (RECCsvDto) config;
				break;
			}
		}
	}
	
	@Override
	public void displayConfiguration(final ReportDto report, final String executorToken, boolean allowAutomaticConfig, final ConfigurationFinishedCallback callback) {
		final DwWindow window = new DwWindow();
		window.setHeading(getExportTitle());
		window.getHeader().setIcon(getIcon());
		window.setSize(420, 360);
		window.setModal(true);
		
		final SimpleForm form = SimpleForm.getInlineInstance();
		window.add(form, new MarginData(10));
		form.addField(Boolean.class, RECCsvDtoPA.INSTANCE.printHeader(), ReportexecutorMessages.INSTANCE.csvConfigPrintHeader());
		form.addField(String.class, RECCsvDtoPA.INSTANCE.separator(), ReportexecutorMessages.INSTANCE.csvConfigSeparator());
		form.addField(String.class, RECCsvDtoPA.INSTANCE.quote(), ReportexecutorMessages.INSTANCE.csvConfigQuote());
		form.addField(String.class, RECCsvDtoPA.INSTANCE.lineSeparator(), ReportexecutorMessages.INSTANCE.csvConfigLineSeparator());
		form.addField(String.class, RECCsvDtoPA.INSTANCE.charset(), ReportexecutorMessages.INSTANCE.csvConfigCharset());
		
		if(null == config) {
			config = new RECCsvDtoDec();
			exporterDao.getExportDefaultSettingsAsJSON(CONFIGURATION_FILE, new RsAsyncCallback<String>() {
				@Override
				public void onSuccess(String result) {
					if (!"".equals(result.trim())) {
						jsonService.setJSONConfig(result);
						boolean printHeader = jsonService.getBoolean("csv.printHeader", true);
						String separator = jsonService.getString("csv.separator", ";");
						String quote = jsonService.getString("csv.quote", "\"");
						String lineSeparator = jsonService.getString("csv.lineSeparator", "\\r\\n");
						config.setPrintHeader(printHeader);
						config.setSeparator(separator);
						config.setQuote(quote);
						config.setLineSeparator(lineSeparator);
					}
				}
			});
			exporterDao.getExportDefaultCharset(new RsAsyncCallback<String> () {
				@Override
				public void onSuccess(String result) {
					if (null != result && !"".contentEquals(result)) {
						config.setCharset(result);
					}
				}
			});
		}
		form.bind(config);
		
		window.addCancelButton();
		
		DwTextButton submitBtn = window.addSubmitButton(new OnButtonClickHandler() {
			@Override
			public void onClick() {
				callback.success();
			}
		});
		submitBtn.setText(ReportExporterMessages.INSTANCE.exportReport());
		
		window.show();
	}
	
	@Override
	public boolean isConfigured() {
		return null != config;
	}
	
	
	@Override
	public List<ReportExecutionConfigDto> getConfiguration(){
		List<ReportExecutionConfigDto> configs = new ArrayList<ReportExecutionConfigDto>();
		if (null != config)
			configs.add(config);
		return configs;
	}
	
	@Override
	public String getExportDescription() {
		return ReportexecutorMessages.INSTANCE.export2CSV();
	}

	@Override
	public String getExportTitle() {
		return "CSV"; //$NON-NLS-1$
	}

	@Override
	public String getOutputFormat() {
		return "CSV"; //$NON-NLS-1$
	}

	@Override
	public ImageResource getIcon() {
		return BaseIcon.FILE_TEXT_O.toImageResource();
	}

	@Override
	public boolean hasConfiguration() {
		return true;
	}

}