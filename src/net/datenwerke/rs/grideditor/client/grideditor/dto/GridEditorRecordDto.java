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
import net.datenwerke.rs.grideditor.client.grideditor.dto.GridEditorRecordEntryDto;
import net.datenwerke.rs.grideditor.client.grideditor.dto.pa.GridEditorRecordDtoPA;
import net.datenwerke.rs.grideditor.client.grideditor.dto.posomap.GridEditorRecordDto2PosoMap;
import net.datenwerke.rs.grideditor.service.grideditor.definition.GridEditorRecord;

/**
 * Dto for {@link GridEditorRecord}
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
abstract public class GridEditorRecordDto extends RsDto {


	private static final long serialVersionUID = 1;


	/* Fields */
	private List<GridEditorRecordEntryDto> data;
	private  boolean data_m;
	public static final String PROPERTY_DATA = "dpi-grideditorrecord-data";

	private transient static PropertyAccessor<GridEditorRecordDto, List<GridEditorRecordEntryDto>> data_pa = new PropertyAccessor<GridEditorRecordDto, List<GridEditorRecordEntryDto>>() {
		@Override
		public void setValue(GridEditorRecordDto container, List<GridEditorRecordEntryDto> object) {
			container.setData(object);
		}

		@Override
		public List<GridEditorRecordEntryDto> getValue(GridEditorRecordDto container) {
			return container.getData();
		}

		@Override
		public Class<?> getType() {
			return List.class;
		}

		@Override
		public String getPath() {
			return "data";
		}

		@Override
		public void setModified(GridEditorRecordDto container, boolean modified) {
			container.data_m = modified;
		}

		@Override
		public boolean isModified(GridEditorRecordDto container) {
			return container.isDataModified();
		}
	};

	private int id;
	private  boolean id_m;
	public static final String PROPERTY_ID = "dpi-grideditorrecord-id";

	private transient static PropertyAccessor<GridEditorRecordDto, Integer> id_pa = new PropertyAccessor<GridEditorRecordDto, Integer>() {
		@Override
		public void setValue(GridEditorRecordDto container, Integer object) {
			container.setId(object);
		}

		@Override
		public Integer getValue(GridEditorRecordDto container) {
			return container.getId();
		}

		@Override
		public Class<?> getType() {
			return Integer.class;
		}

		@Override
		public String getPath() {
			return "id";
		}

		@Override
		public void setModified(GridEditorRecordDto container, boolean modified) {
			container.id_m = modified;
		}

		@Override
		public boolean isModified(GridEditorRecordDto container) {
			return container.isIdModified();
		}
	};


	public GridEditorRecordDto() {
		super();
	}

	public List<GridEditorRecordEntryDto> getData()  {
		if(! isDtoProxy()){
			List<GridEditorRecordEntryDto> _currentValue = this.data;
			if(null == _currentValue)
				this.data = new ArrayList<GridEditorRecordEntryDto>();

			return this.data;
		}

		if(isDataModified())
			return this.data;

		if(! GWT.isClient())
			return null;

		List<GridEditorRecordEntryDto> _value = dtoManager.getProperty(this, instantiatePropertyAccess().data());

		_value = new ChangeMonitoredList<GridEditorRecordEntryDto>(_value);
		if(_value instanceof HasObjectChangedEventHandler){
			((HasObjectChangedEventHandler)_value).addObjectChangedHandler(new net.datenwerke.gxtdto.client.eventbus.handlers.ObjectChangedEventHandler(){
				@Override
				public void onObjectChangedEvent(net.datenwerke.gxtdto.client.eventbus.events.ObjectChangedEvent event){
					if(! isDataModified())
						setData((List<GridEditorRecordEntryDto>) ((MonitoredCollection) event.getObject()).getUnderlyingCollection());
				}
			}
			);
		}
		return _value;
	}


	public void setData(List<GridEditorRecordEntryDto> data)  {
		/* old value */
		List<GridEditorRecordEntryDto> oldValue = null;
		if(GWT.isClient())
			oldValue = getData();

		/* set new value */
		this.data = data;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(data_pa, oldValue, data, this.data_m));

		/* set indicator */
		this.data_m = true;

		this.fireObjectChangedEvent(GridEditorRecordDtoPA.INSTANCE.data(), oldValue);
	}


	public boolean isDataModified()  {
		return data_m;
	}


	public static PropertyAccessor<GridEditorRecordDto, List<GridEditorRecordEntryDto>> getDataPropertyAccessor()  {
		return data_pa;
	}


	public int getId()  {
		if(! isDtoProxy()){
			return this.id;
		}

		if(isIdModified())
			return this.id;

		if(! GWT.isClient())
			return 0;

		int _value = dtoManager.getProperty(this, instantiatePropertyAccess().id());

		return _value;
	}


	public void setId(int id)  {
		/* old value */
		int oldValue = 0;
		if(GWT.isClient())
			oldValue = getId();

		/* set new value */
		this.id = id;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(id_pa, oldValue, id, this.id_m));

		/* set indicator */
		this.id_m = true;

		this.fireObjectChangedEvent(GridEditorRecordDtoPA.INSTANCE.id(), oldValue);
	}


	public boolean isIdModified()  {
		return id_m;
	}


	public static PropertyAccessor<GridEditorRecordDto, Integer> getIdPropertyAccessor()  {
		return id_pa;
	}


	@Override
	public String toString()  {
		return super.toString();
	}

	public static Dto2PosoMapper newPosoMapper()  {
		return new GridEditorRecordDto2PosoMap();
	}

	public GridEditorRecordDtoPA instantiatePropertyAccess()  {
		return GWT.create(GridEditorRecordDtoPA.class);
	}

	public void clearModified()  {
		this.data = null;
		this.data_m = false;
		this.id = 0;
		this.id_m = false;
	}


	public boolean isModified()  {
		if(super.isModified())
			return true;
		if(data_m)
			return true;
		if(id_m)
			return true;
		return false;
	}


	public List<PropertyAccessor> getPropertyAccessors()  {
		List<PropertyAccessor> list = super.getPropertyAccessors();
		list.add(data_pa);
		list.add(id_pa);
		return list;
	}


	public List<PropertyAccessor> getModifiedPropertyAccessors()  {
		List<PropertyAccessor> list = super.getModifiedPropertyAccessors();
		if(data_m)
			list.add(data_pa);
		if(id_m)
			list.add(id_pa);
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsByView(net.datenwerke.gxtdto.client.dtomanager.DtoView view)  {
		List<PropertyAccessor> list = super.getPropertyAccessorsByView(view);
		if(view.compareTo(DtoView.NORMAL) >= 0){
			list.add(data_pa);
			list.add(id_pa);
		}
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsForDtos()  {
		List<PropertyAccessor> list = super.getPropertyAccessorsForDtos();
		list.add(data_pa);
		return list;
	}



	net.datenwerke.rs.grideditor.client.grideditor.dto.GridEditorRecordEntryDto wl_0;

}
