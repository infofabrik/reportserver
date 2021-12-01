package net.datenwerke.rs.crystal.client.crystal.reportengines;

import java.util.ArrayList;
import java.util.List;

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
import net.datenwerke.rs.core.client.reportexporter.dto.ReportExecutionConfigDto;
import net.datenwerke.rs.core.client.reportexporter.dto.decorator.RECCsvDtoDec;
import net.datenwerke.rs.core.client.reportexporter.dto.pa.RECCsvDtoPA;
import net.datenwerke.rs.core.client.reportexporter.exporter.generic.Export2CSV;
import net.datenwerke.rs.core.client.reportexporter.locale.ReportExporterMessages;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;
import net.datenwerke.rs.crystal.client.crystal.dto.CrystalReportDto;

public class Crystal2CSV extends Export2CSV {
	
	private final ReportExporterDao exporterDao;
	private final ClientConfigJSONService jsonService;
	private static final String configurationFile = "csvexport.cf";

	@Inject
	public Crystal2CSV(ReportExporterDao exporterDao, ClientConfigJSONService jsonService) {
		super(exporterDao, jsonService);
		this.exporterDao = exporterDao;
		this.jsonService = jsonService;
	}

	@Override
	public boolean consumes(ReportDto report) {
		return report instanceof CrystalReportDto;
	}

	@Override
	public boolean hasConfiguration() {
		return false;
	}
	
	@Override
	public boolean isConfigured() {
		return null != config;
	}
	
	@Override
	public void displayConfiguration(final ReportDto report, final String executorToken, boolean allowAutomaticConfig, final ConfigurationFinishedCallback callback){
		final DwWindow window = new DwWindow();
		window.setHeading(getExportTitle());
		window.getHeader().setIcon(getIcon());
		window.setSize(420, 300);
		window.setModal(true);
		
		final SimpleForm form = SimpleForm.getInlineInstance();
		window.add(form, new MarginData(10));
		form.addField(String.class, RECCsvDtoPA.INSTANCE.separator(), ReportexecutorMessages.INSTANCE.csvConfigSeparator());
		form.addField(String.class, RECCsvDtoPA.INSTANCE.quote(), ReportexecutorMessages.INSTANCE.csvConfigQuote());
		
		if(null == config) {
			config = new RECCsvDtoDec();
			exporterDao.getExportDefaultSettingsAsJSON(configurationFile, new RsAsyncCallback<String>() {
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
	public List<ReportExecutionConfigDto> getConfiguration() {
		List<ReportExecutionConfigDto> configs = new ArrayList<ReportExecutionConfigDto>();
		if (null != config)
			configs.add(config);
		return configs;
	}
}
