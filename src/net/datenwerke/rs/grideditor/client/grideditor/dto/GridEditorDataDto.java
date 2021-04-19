package net.datenwerke.rs.grideditor.client.grideditor.dto;

import com.google.gwt.core.client.GWT;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.gf.base.client.dtogenerator.RsDto;
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
import net.datenwerke.rs.grideditor.client.grideditor.dto.GridEditorConfigDto;
import net.datenwerke.rs.grideditor.client.grideditor.dto.GridEditorRecordDto;
import net.datenwerke.rs.grideditor.client.grideditor.dto.pa.GridEditorDataDtoPA;
import net.datenwerke.rs.grideditor.client.grideditor.dto.posomap.GridEditorDataDto2PosoMap;
import net.datenwerke.rs.grideditor.service.grideditor.definition.GridEditorData;

/**
 * Dto for {@link GridEditorData}
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
abstract public class GridEditorDataDto extends RsDto {


	private static final long serialVersionUID = 1;


	/* Fields */
	private GridEditorConfigDto config;
	private  boolean config_m;
	public static final String PROPERTY_CONFIG = "dpi-grideditordata-config";

	private transient static PropertyAccessor<GridEditorDataDto, GridEditorConfigDto> config_pa = new PropertyAccessor<GridEditorDataDto, GridEditorConfigDto>() {
		@Override
		public void setValue(GridEditorDataDto container, GridEditorConfigDto object) {
			container.setConfig(object);
		}

		@Override
		public GridEditorConfigDto getValue(GridEditorDataDto container) {
			return container.getConfig();
		}

		@Override
		public Class<?> getType() {
			return GridEditorConfigDto.class;
		}

		@Override
		public String getPath() {
			return "config";
		}

		@Override
		public void setModified(GridEditorDataDto container, boolean modified) {
			container.config_m = modified;
		}

		@Override
		public boolean isModified(GridEditorDataDto container) {
			return container.isConfigModified();
		}
	};

	private List<GridEditorRecordDto> dataRecords;
	private  boolean dataRecords_m;
	public static final String PROPERTY_DATA_RECORDS = "dpi-grideditordata-datarecords";

	private transient static PropertyAccessor<GridEditorDataDto, List<GridEditorRecordDto>> dataRecords_pa = new PropertyAccessor<GridEditorDataDto, List<GridEditorRecordDto>>() {
		@Override
		public void setValue(GridEditorDataDto container, List<GridEditorRecordDto> object) {
			container.setDataRecords(object);
		}

		@Override
		public List<GridEditorRecordDto> getValue(GridEditorDataDto container) {
			return container.getDataRecords();
		}

		@Override
		public Class<?> getType() {
			return List.class;
		}

		@Override
		public String getPath() {
			return "dataRecords";
		}

		@Override
		public void setModified(GridEditorDataDto container, boolean modified) {
			container.dataRecords_m = modified;
		}

		@Override
		public boolean isModified(GridEditorDataDto container) {
			return container.isDataRecordsModified();
		}
	};


	public GridEditorDataDto() {
		super();
	}

	public GridEditorConfigDto getConfig()  {
		if(! isDtoProxy()){
			return this.config;
		}

		if(isConfigModified())
			return this.config;

		if(! GWT.isClient())
			return null;

		GridEditorConfigDto _value = dtoManager.getProperty(this, instantiatePropertyAccess().config());

		if(_value instanceof HasObjectChangedEventHandler){
			((HasObjectChangedEventHandler)_value).addObjectChangedHandler(new net.datenwerke.gxtdto.client.eventbus.handlers.ObjectChangedEventHandler(){
				@Override
				public void onObjectChangedEvent(net.datenwerke.gxtdto.client.eventbus.events.ObjectChangedEvent event){
					if(! isConfigModified())
						setConfig((GridEditorConfigDto) event.getObject());
				}
			}
			);
		}
		return _value;
	}


	public void setConfig(GridEditorConfigDto config)  {
		/* old value */
		GridEditorConfigDto oldValue = null;
		if(GWT.isClient())
			oldValue = getConfig();

		/* set new value */
		this.config = config;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(config_pa, oldValue, config, this.config_m));

		/* set indicator */
		this.config_m = true;

		this.fireObjectChangedEvent(GridEditorDataDtoPA.INSTANCE.config(), oldValue);
	}


	public boolean isConfigModified()  {
		return config_m;
	}


	public static PropertyAccessor<GridEditorDataDto, GridEditorConfigDto> getConfigPropertyAccessor()  {
		return config_pa;
	}


	public List<GridEditorRecordDto> getDataRecords()  {
		if(! isDtoProxy()){
			List<GridEditorRecordDto> _currentValue = this.dataRecords;
			if(null == _currentValue)
				this.dataRecords = new ArrayList<GridEditorRecordDto>();

			return this.dataRecords;
		}

		if(isDataRecordsModified())
			return this.dataRecords;

		if(! GWT.isClient())
			return null;

		List<GridEditorRecordDto> _value = dtoManager.getProperty(this, instantiatePropertyAccess().dataRecords());

		_value = new ChangeMonitoredList<GridEditorRecordDto>(_value);
		if(_value instanceof HasObjectChangedEventHandler){
			((HasObjectChangedEventHandler)_value).addObjectChangedHandler(new net.datenwerke.gxtdto.client.eventbus.handlers.ObjectChangedEventHandler(){
				@Override
				public void onObjectChangedEvent(net.datenwerke.gxtdto.client.eventbus.events.ObjectChangedEvent event){
					if(! isDataRecordsModified())
						setDataRecords((List<GridEditorRecordDto>) ((MonitoredCollection) event.getObject()).getUnderlyingCollection());
				}
			}
			);
		}
		return _value;
	}


	public void setDataRecords(List<GridEditorRecordDto> dataRecords)  {
		/* old value */
		List<GridEditorRecordDto> oldValue = null;
		if(GWT.isClient())
			oldValue = getDataRecords();

		/* set new value */
		this.dataRecords = dataRecords;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(dataRecords_pa, oldValue, dataRecords, this.dataRecords_m));

		/* set indicator */
		this.dataRecords_m = true;

		this.fireObjectChangedEvent(GridEditorDataDtoPA.INSTANCE.dataRecords(), oldValue);
	}


	public boolean isDataRecordsModified()  {
		return dataRecords_m;
	}


	public static PropertyAccessor<GridEditorDataDto, List<GridEditorRecordDto>> getDataRecordsPropertyAccessor()  {
		return dataRecords_pa;
	}


	@Override
	public String toString()  {
		return super.toString();
	}

	public static Dto2PosoMapper newPosoMapper()  {
		return new GridEditorDataDto2PosoMap();
	}

	public GridEditorDataDtoPA instantiatePropertyAccess()  {
		return GWT.create(GridEditorDataDtoPA.class);
	}

	public void clearModified()  {
		this.config = null;
		this.config_m = false;
		this.dataRecords = null;
		this.dataRecords_m = false;
	}


	public boolean isModified()  {
		if(super.isModified())
			return true;
		if(config_m)
			return true;
		if(dataRecords_m)
			return true;
		return false;
	}


	public List<PropertyAccessor> getPropertyAccessors()  {
		List<PropertyAccessor> list = super.getPropertyAccessors();
		list.add(config_pa);
		list.add(dataRecords_pa);
		return list;
	}


	public List<PropertyAccessor> getModifiedPropertyAccessors()  {
		List<PropertyAccessor> list = super.getModifiedPropertyAccessors();
		if(config_m)
			list.add(config_pa);
		if(dataRecords_m)
			list.add(dataRecords_pa);
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsByView(net.datenwerke.gxtdto.client.dtomanager.DtoView view)  {
		List<PropertyAccessor> list = super.getPropertyAccessorsByView(view);
		if(view.compareTo(DtoView.NORMAL) >= 0){
			list.add(config_pa);
			list.add(dataRecords_pa);
		}
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsForDtos()  {
		List<PropertyAccessor> list = super.getPropertyAccessorsForDtos();
		list.add(config_pa);
		list.add(dataRecords_pa);
		return list;
	}



	net.datenwerke.rs.grideditor.client.grideditor.dto.GridEditorConfigDto wl_0;
	net.datenwerke.rs.grideditor.client.grideditor.dto.GridEditorRecordDto wl_1;

}
