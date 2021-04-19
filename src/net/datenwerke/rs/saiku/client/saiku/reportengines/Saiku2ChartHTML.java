package net.datenwerke.rs.saiku.client.saiku.reportengines;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import net.datenwerke.gxtdto.client.baseex.widget.DwWindow;
import net.datenwerke.gxtdto.client.baseex.widget.DwWindow.OnButtonClickHandler;
import net.datenwerke.gxtdto.client.forms.simpleform.SimpleForm;
import net.datenwerke.gxtdto.client.forms.simpleform.providers.configs.lists.SFFCFancyStaticList;
import net.datenwerke.gxtdto.client.resources.BaseResources;
import net.datenwerke.rs.base.client.reportengines.table.dto.TableReportDto;
import net.datenwerke.rs.core.client.datasourcemanager.dto.DatasourceContainerDto;
import net.datenwerke.rs.core.client.reportexporter.dto.ReportExecutionConfigDto;
import net.datenwerke.rs.core.client.reportexporter.exporter.ReportExporterImpl;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;
import net.datenwerke.rs.saiku.client.datasource.dto.MondrianDatasourceDto;
import net.datenwerke.rs.saiku.client.saiku.dto.SaikuReportDto;
import net.datenwerke.rs.saiku.client.saiku.dto.decorator.RECSaikuChartDtoDec;
import net.datenwerke.rs.saiku.client.saiku.dto.pa.RECSaikuChartDtoPA;
import net.datenwerke.rs.saiku.client.saiku.locale.SaikuMessages;
import net.datenwerke.rs.theme.client.icon.BaseIcon;

import com.google.gwt.resources.client.ImageResource;
import com.sencha.gxt.widget.core.client.container.MarginData;

public class Saiku2ChartHTML extends ReportExporterImpl {

	private RECSaikuChartDtoDec config;
	private enum ChartTypes {
		
		bar("Bar", BaseResources.INSTANCE.saikuChartBar()),
		stackedBar("Stacked Bar", BaseResources.INSTANCE.saikuChartStackedbar()),
		stackedBar100("Stacked Bar 100%", BaseResources.INSTANCE.saikuChart100bar()),
		multiplebar("Multiple Bar", BaseResources.INSTANCE.saikuChartMultiple()), 
		line("Line", BaseResources.INSTANCE.saikuChartLine()), 
		area("Area", BaseResources.INSTANCE.saikuChartArea()),
		heatgrid("Heat Grid", BaseResources.INSTANCE.saikuChartHeat()),
		sunburst("Sunburst", BaseResources.INSTANCE.saikuChartSunburst()),
		multiplesunburst("Multiple Sunburst", BaseResources.INSTANCE.saikuChartMultiSunburst()),
		dot("Dot", BaseResources.INSTANCE.saikuChartDot()), 
		waterfall("Waterfall", BaseResources.INSTANCE.saikuChartWaterfall()), 
		pie("Pie", BaseResources.INSTANCE.saikuChartPie());

		
		public final String label;
		public final ImageResource image;

		ChartTypes(String label, ImageResource image){
			this.label = label;
			this.image = image;
		}
	}

	@Override
	public boolean consumesConfiguration(ReportDto report) {
		return true;
	}
	
	@Override
	public boolean canBeScheduled() {
		return true;
	}

	@Override
	public void configureFrom(List<ReportExecutionConfigDto> exportConfiguration) {
		if(null == exportConfiguration || exportConfiguration.isEmpty())
			return;
		
		for(ReportExecutionConfigDto config : exportConfiguration){
			if(config instanceof RECSaikuChartDtoDec){
				this.config = (RECSaikuChartDtoDec) config;
				break;
			}
		}
	}
	
	@Override
	public void displayConfiguration(ReportDto report, String executorToken, boolean allowAutomaticConfig, final ConfigurationFinishedCallback callback) {
		final DwWindow window = new DwWindow();
		window.setHeading(getExportTitle());
		window.getHeader().setIcon(getIcon());
		window.setSize(250, 180	);
		window.setModal(true);
		
		final SimpleForm form = SimpleForm.getInlineInstance();
		window.add(form, new MarginData(10));
		
		form.addField(List.class,RECSaikuChartDtoPA.INSTANCE.type(), SaikuMessages.INSTANCE.chartType(), createFancySFFC());
		
		if(null == config){
			config = new RECSaikuChartDtoDec();
			config.setType("bar");
		}
		form.bind(config);
		
		window.addCancelButton();
		
		window.addSubmitButton(new OnButtonClickHandler() {
			@Override
			public void onClick() {
				callback.success();
			}
		});
		
		window.show();
	}
	
	private SFFCFancyStaticList<String> createFancySFFC() {
		return new SFFCFancyStaticList<String>() {

			@Override
			public int getHeight() {
				return 36;
			}

			@Override
			public int getWidth() {
				return 300;
			}

			@Override
			public Map<String, ImageResource> getIconMap() {
				LinkedHashMap<String, ImageResource> icons = new LinkedHashMap<String, ImageResource>();
				
				for(ChartTypes ct : ChartTypes.values()){
					icons.put(ct.label, ct.image);
				}
								
				return icons;
			}

			@Override
			public Map<String, String> getDescriptionMap() {
				LinkedHashMap<String, String> descriptions = new LinkedHashMap<String, String>();
				return descriptions;
			}

			@Override
			public Map<String, String> getValues() {
				LinkedHashMap<String, String> values = new LinkedHashMap<String, String>();
				
				for(ChartTypes ct : ChartTypes.values()){
					values.put(ct.label, ct.name());
				}
				
				return values;
			}

		};
	}

	@Override
	public boolean isConfigured() {
		return null != config;
	}
	
	@Override
	public List<ReportExecutionConfigDto> getConfiguration(){
		List<ReportExecutionConfigDto> configs = new ArrayList<ReportExecutionConfigDto>();
		configs.add(config);
		return configs;
	}
	
	@Override
	public boolean consumes(ReportDto report) {
		boolean isInstanceOfSaikuReportDto = report instanceof SaikuReportDto;
		
		if (isInstanceOfSaikuReportDto) {
		    DatasourceContainerDto datasourceContainer = report.getDatasourceContainer();
		    isInstanceOfSaikuReportDto = !((MondrianDatasourceDto)datasourceContainer.getDatasource()).isMondrian3();
			
		}
		
		return isInstanceOfSaikuReportDto || (report instanceof TableReportDto && ((TableReportDto)report).isCubeFlag());
	}

	@Override
	public String getExportTitle() {
		return "Chart";
	}

	@Override
	public String getExportDescription() {
		return SaikuMessages.INSTANCE.export2chartHtml();
	}

	@Override
	public ImageResource getIcon() {
		return BaseIcon.CHART_LINE.toImageResource();
	}

	@Override
	public String getOutputFormat() {
		return "SAIKU_CHART_HTML";
	}

	@Override
	public boolean hasConfiguration() {
		return true;
	}

}
