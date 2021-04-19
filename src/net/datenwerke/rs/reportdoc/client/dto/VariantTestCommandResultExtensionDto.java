package net.datenwerke.rs.reportdoc.client.dto;

import com.google.gwt.core.client.GWT;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.gxtdto.client.dtomanager.Dto2PosoMapper;
import net.datenwerke.gxtdto.client.dtomanager.Dto;
import net.datenwerke.gxtdto.client.dtomanager.DtoView;
import net.datenwerke.gxtdto.client.dtomanager.PropertyAccessor;
import net.datenwerke.gxtdto.client.dtomanager.dtomod.collections.ChangeMonitoredList;
import net.datenwerke.gxtdto.client.dtomanager.dtomod.collections.MonitoredCollection;
import net.datenwerke.gxtdto.client.dtomanager.redoundo.ChangeTracker;
import net.datenwerke.gxtdto.client.eventbus.events.ObjectChangedEvent;
import net.datenwerke.gxtdto.client.eventbus.handlers.ObjectChangedEventHandler;
import net.datenwerke.gxtdto.client.eventbus.handlers.has.HasObjectChangedEventHandler;
import net.datenwerke.rs.core.client.datasourcemanager.dto.DatasourceDefinitionDto;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;
import net.datenwerke.rs.reportdoc.client.dto.pa.VariantTestCommandResultExtensionDtoPA;
import net.datenwerke.rs.reportdoc.client.dto.posomap.VariantTestCommandResultExtensionDto2PosoMap;
import net.datenwerke.rs.reportdoc.service.terminal.commands.VariantTestCommandResultExtension;
import net.datenwerke.rs.terminal.client.terminal.dto.CommandResultExtensionDto;

/**
 * Dto for {@link VariantTestCommandResultExtension}
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class VariantTestCommandResultExtensionDto extends CommandResultExtensionDto {


	private static final long serialVersionUID = 1;


	/* Fields */
	private List<DatasourceDefinitionDto> datasources;
	private  boolean datasources_m;
	public static final String PROPERTY_DATASOURCES = "dpi-varianttestcommandresultextension-datasources";

	private transient static PropertyAccessor<VariantTestCommandResultExtensionDto, List<DatasourceDefinitionDto>> datasources_pa = new PropertyAccessor<VariantTestCommandResultExtensionDto, List<DatasourceDefinitionDto>>() {
		@Override
		public void setValue(VariantTestCommandResultExtensionDto container, List<DatasourceDefinitionDto> object) {
			container.setDatasources(object);
		}

		@Override
		public List<DatasourceDefinitionDto> getValue(VariantTestCommandResultExtensionDto container) {
			return container.getDatasources();
		}

		@Override
		public Class<?> getType() {
			return List.class;
		}

		@Override
		public String getPath() {
			return "datasources";
		}

		@Override
		public void setModified(VariantTestCommandResultExtensionDto container, boolean modified) {
			container.datasources_m = modified;
		}

		@Override
		public boolean isModified(VariantTestCommandResultExtensionDto container) {
			return container.isDatasourcesModified();
		}
	};

	private ReportDto report;
	private  boolean report_m;
	public static final String PROPERTY_REPORT = "dpi-varianttestcommandresultextension-report";

	private transient static PropertyAccessor<VariantTestCommandResultExtensionDto, ReportDto> report_pa = new PropertyAccessor<VariantTestCommandResultExtensionDto, ReportDto>() {
		@Override
		public void setValue(VariantTestCommandResultExtensionDto container, ReportDto object) {
			container.setReport(object);
		}

		@Override
		public ReportDto getValue(VariantTestCommandResultExtensionDto container) {
			return container.getReport();
		}

		@Override
		public Class<?> getType() {
			return ReportDto.class;
		}

		@Override
		public String getPath() {
			return "report";
		}

		@Override
		public void setModified(VariantTestCommandResultExtensionDto container, boolean modified) {
			container.report_m = modified;
		}

		@Override
		public boolean isModified(VariantTestCommandResultExtensionDto container) {
			return container.isReportModified();
		}
	};


	public VariantTestCommandResultExtensionDto() {
		super();
	}

	public List<DatasourceDefinitionDto> getDatasources()  {
		if(! isDtoProxy()){
			List<DatasourceDefinitionDto> _currentValue = this.datasources;
			if(null == _currentValue)
				this.datasources = new ArrayList<DatasourceDefinitionDto>();

			return this.datasources;
		}

		if(isDatasourcesModified())
			return this.datasources;

		if(! GWT.isClient())
			return null;

		List<DatasourceDefinitionDto> _value = dtoManager.getProperty(this, instantiatePropertyAccess().datasources());

		_value = new ChangeMonitoredList<DatasourceDefinitionDto>(_value);
		if(_value instanceof HasObjectChangedEventHandler){
			((HasObjectChangedEventHandler)_value).addObjectChangedHandler(new net.datenwerke.gxtdto.client.eventbus.handlers.ObjectChangedEventHandler(){
				@Override
				public void onObjectChangedEvent(net.datenwerke.gxtdto.client.eventbus.events.ObjectChangedEvent event){
					if(! isDatasourcesModified())
						setDatasources((List<DatasourceDefinitionDto>) ((MonitoredCollection) event.getObject()).getUnderlyingCollection());
				}
			}
			);
		}
		return _value;
	}


	public void setDatasources(List<DatasourceDefinitionDto> datasources)  {
		/* old value */
		List<DatasourceDefinitionDto> oldValue = null;
		if(GWT.isClient())
			oldValue = getDatasources();

		/* set new value */
		this.datasources = datasources;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(datasources_pa, oldValue, datasources, this.datasources_m));

		/* set indicator */
		this.datasources_m = true;

		this.fireObjectChangedEvent(VariantTestCommandResultExtensionDtoPA.INSTANCE.datasources(), oldValue);
	}


	public boolean isDatasourcesModified()  {
		return datasources_m;
	}


	public static PropertyAccessor<VariantTestCommandResultExtensionDto, List<DatasourceDefinitionDto>> getDatasourcesPropertyAccessor()  {
		return datasources_pa;
	}


	public ReportDto getReport()  {
		if(! isDtoProxy()){
			return this.report;
		}

		if(isReportModified())
			return this.report;

		if(! GWT.isClient())
			return null;

		ReportDto _value = dtoManager.getProperty(this, instantiatePropertyAccess().report());

		if(_value instanceof HasObjectChangedEventHandler){
			((HasObjectChangedEventHandler)_value).addObjectChangedHandler(new net.datenwerke.gxtdto.client.eventbus.handlers.ObjectChangedEventHandler(){
				@Override
				public void onObjectChangedEvent(net.datenwerke.gxtdto.client.eventbus.events.ObjectChangedEvent event){
					if(! isReportModified())
						setReport((ReportDto) event.getObject());
				}
			}
			);
		}
		return _value;
	}


	public void setReport(ReportDto report)  {
		/* old value */
		ReportDto oldValue = null;
		if(GWT.isClient())
			oldValue = getReport();

		/* set new value */
		this.report = report;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(report_pa, oldValue, report, this.report_m));

		/* set indicator */
		this.report_m = true;

		this.fireObjectChangedEvent(VariantTestCommandResultExtensionDtoPA.INSTANCE.report(), oldValue);
	}


	public boolean isReportModified()  {
		return report_m;
	}


	public static PropertyAccessor<VariantTestCommandResultExtensionDto, ReportDto> getReportPropertyAccessor()  {
		return report_pa;
	}


	@Override
	public String toString()  {
		return super.toString();
	}

	public static Dto2PosoMapper newPosoMapper()  {
		return new VariantTestCommandResultExtensionDto2PosoMap();
	}

	public VariantTestCommandResultExtensionDtoPA instantiatePropertyAccess()  {
		return GWT.create(VariantTestCommandResultExtensionDtoPA.class);
	}

	public void clearModified()  {
		this.datasources = null;
		this.datasources_m = false;
		this.report = null;
		this.report_m = false;
	}


	public boolean isModified()  {
		if(super.isModified())
			return true;
		if(datasources_m)
			return true;
		if(report_m)
			return true;
		return false;
	}


	public List<PropertyAccessor> getPropertyAccessors()  {
		List<PropertyAccessor> list = super.getPropertyAccessors();
		list.add(datasources_pa);
		list.add(report_pa);
		return list;
	}


	public List<PropertyAccessor> getModifiedPropertyAccessors()  {
		List<PropertyAccessor> list = super.getModifiedPropertyAccessors();
		if(datasources_m)
			list.add(datasources_pa);
		if(report_m)
			list.add(report_pa);
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsByView(net.datenwerke.gxtdto.client.dtomanager.DtoView view)  {
		List<PropertyAccessor> list = super.getPropertyAccessorsByView(view);
		if(view.compareTo(DtoView.NORMAL) >= 0){
			list.add(datasources_pa);
			list.add(report_pa);
		}
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsForDtos()  {
		List<PropertyAccessor> list = super.getPropertyAccessorsForDtos();
		list.add(datasources_pa);
		list.add(report_pa);
		return list;
	}



	net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto wl_0;
	net.datenwerke.rs.core.client.datasourcemanager.dto.DatasourceDefinitionDto wl_1;

}
