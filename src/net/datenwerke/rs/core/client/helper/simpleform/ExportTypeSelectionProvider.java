package net.datenwerke.rs.core.client.helper.simpleform;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.core.client.util.ToggleGroup;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer.VerticalLayoutData;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.form.Radio;

import net.datenwerke.gxtdto.client.baseex.widget.btn.DwTextButton;
import net.datenwerke.gxtdto.client.forms.simpleform.SimpleFormFieldConfiguration;
import net.datenwerke.gxtdto.client.forms.simpleform.hooks.FormFieldProviderHookImpl;
import net.datenwerke.rs.core.client.helper.simpleform.config.SFFCExportTypeSelector;
import net.datenwerke.rs.core.client.reportexporter.ReportExporterUIService;
import net.datenwerke.rs.core.client.reportexporter.dto.ReportExecutionConfigDto;
import net.datenwerke.rs.core.client.reportexporter.exporter.ReportExporter;
import net.datenwerke.rs.core.client.reportexporter.exporter.ReportExporter.ConfigurationFinishedCallback;
import net.datenwerke.rs.core.client.reportexporter.locale.ReportExporterMessages;

public class ExportTypeSelectionProvider extends FormFieldProviderHookImpl {

	private final ReportExporterUIService reportExporterService;
	
	private ToggleGroup exportTypeGroup;
	private Map<Radio, ReportExporter> exporterMap;
	
	private List<ReportExecutionConfigDto> exporterConfig;

	private Object model;

	private ValueProvider vp;
	

	@Inject
	public ExportTypeSelectionProvider(
		ReportExporterUIService reportExporterService	
		){
		
		/* store obejcts */
		this.reportExporterService = reportExporterService;
	}
	
	@Override
	public boolean doConsumes(Class<?> type,
			SimpleFormFieldConfiguration... configs) {
		return ExportTypeSelection.class.equals(type) && null != configs && configs.length > 0 && configs[0] instanceof SFFCExportTypeSelector;
	}
	
	@Override
	public Widget createFormField() {
		final SFFCExportTypeSelector config = (SFFCExportTypeSelector) configs[0];
		
		exportTypeGroup = new ToggleGroup();
		
		final DwTextButton formatConfigBtn = new DwTextButton(ReportExporterMessages.INSTANCE.formatConfigLabel());
		
		List<ReportExporter> exporters = reportExporterService.getCleanedUpAvailableExporters(config.getReport());
		exporterMap = new HashMap<Radio, ReportExporter>();
		boolean first = true;
		
		int horizontalRadioCounter = 0;
		HorizontalPanel horizontalRadioPanel = null;
        VerticalPanel verticalRadioPanel = new VerticalPanel();
        
		for(final ReportExporter exporter : exporters){
			if(! exporter.canBeScheduled())
				continue;
			
			String name = exporter.getExportTitle();

			final Radio radio = new Radio();
			radio.setName("exportFormat"); //$NON-NLS-1$
			radio.setData("rs_outputFormat", exporter.getOutputFormat()); //$NON-NLS-1$
			radio.setBoxLabel(name);
			radio.addValueChangeHandler(new ValueChangeHandler<Boolean>() {
				@Override
				public void onValueChange(ValueChangeEvent<Boolean> event) {
					if(radio.getValue()){
						if(exporter.hasConfiguration())
							formatConfigBtn.enable();
						else
							formatConfigBtn.disable();
					}
				}
			});
			
			if (0 == horizontalRadioCounter % 6 ) {
               horizontalRadioPanel = new HorizontalPanel();
               horizontalRadioPanel.addStyleName("rs-export-type-radio-group");
               verticalRadioPanel.add(horizontalRadioPanel);
            }
			
			exportTypeGroup.add(radio);
			horizontalRadioPanel.add(radio);
			exporterMap.put(radio, exporter);
			
			if(first){
				first = false;
				radio.setValue(true, true);
			}
			horizontalRadioCounter++;
		}
		
		/* selection listener for extra config */
		formatConfigBtn.addSelectHandler(new SelectHandler() {
			@Override
			public void onSelect(SelectEvent event) {
				Radio radio = (Radio) exportTypeGroup.getValue();
				if(null != radio && exporterMap.containsKey(radio)){
					final ReportExporter exporter = exporterMap.get(radio);
					exporter.displayConfiguration(config.getReport(), config.getExecuteReportToken(), false, new ConfigurationFinishedCallback() {
						
						@Override
						public void success() {
							exporterConfig = exporter.getConfiguration();
							
							updateModel();
						}

					});
				}
			}
		});
		
		VerticalLayoutContainer wrapper = new VerticalLayoutContainer();
		wrapper.add(verticalRadioPanel, new VerticalLayoutData(1,30));
		wrapper.add(formatConfigBtn);
		
		return wrapper;
	}

	@Override
	public void addFieldBindings(Object model, ValueProvider vp, Widget field) {
		this.model = model;
		this.vp = vp;
	}
	
	@Override
	public Object getValue(Widget field) {
		return getValue();
	}
	
	private Object getValue() {
		String outputFormat = ((Radio)exportTypeGroup.getValue()).getData("rs_outputFormat");
		
		ExportTypeSelection selection = new ExportTypeSelection();
		selection.setOutputFormat(outputFormat);
		if(null == exporterConfig) 
			selection.setExportConfiguration(new ArrayList<ReportExecutionConfigDto>());
		else 
			selection.setExportConfiguration(exporterConfig);
		
		ReportExporter exporter = exporterMap.get(exportTypeGroup.getValue());
		selection.setConfigured(exporter.isConfigured());
		
		return selection;
	}

	private void updateModel() {
		if(null != model && null != vp)
			vp.setValue(model, getValue());
	}
}
