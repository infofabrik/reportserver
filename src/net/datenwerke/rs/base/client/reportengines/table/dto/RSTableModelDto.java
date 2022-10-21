package net.datenwerke.rs.base.client.reportengines.table.dto;

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
import net.datenwerke.rs.base.client.reportengines.table.dto.RSTableRowDto;
import net.datenwerke.rs.base.client.reportengines.table.dto.TableDefinitionDto;
import net.datenwerke.rs.base.client.reportengines.table.dto.pa.RSTableModelDtoPA;
import net.datenwerke.rs.base.client.reportengines.table.dto.posomap.RSTableModelDto2PosoMap;
import net.datenwerke.rs.base.service.reportengines.table.output.object.RSTableModel;

/**
 * Dto for {@link RSTableModel}
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class RSTableModelDto extends RsDto {


	private static final long serialVersionUID = 1;


	/* Fields */
	private List<RSTableRowDto> data;
	private  boolean data_m;
	public static final String PROPERTY_DATA = "dpi-rstablemodel-data";

	private transient static PropertyAccessor<RSTableModelDto, List<RSTableRowDto>> data_pa = new PropertyAccessor<RSTableModelDto, List<RSTableRowDto>>() {
		@Override
		public void setValue(RSTableModelDto container, List<RSTableRowDto> object) {
			container.setData(object);
		}

		@Override
		public List<RSTableRowDto> getValue(RSTableModelDto container) {
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
		public void setModified(RSTableModelDto container, boolean modified) {
			container.data_m = modified;
		}

		@Override
		public boolean isModified(RSTableModelDto container) {
			return container.isDataModified();
		}
	};

	private TableDefinitionDto tableDefinition;
	private  boolean tableDefinition_m;
	public static final String PROPERTY_TABLE_DEFINITION = "dpi-rstablemodel-tabledefinition";

	private transient static PropertyAccessor<RSTableModelDto, TableDefinitionDto> tableDefinition_pa = new PropertyAccessor<RSTableModelDto, TableDefinitionDto>() {
		@Override
		public void setValue(RSTableModelDto container, TableDefinitionDto object) {
			container.setTableDefinition(object);
		}

		@Override
		public TableDefinitionDto getValue(RSTableModelDto container) {
			return container.getTableDefinition();
		}

		@Override
		public Class<?> getType() {
			return TableDefinitionDto.class;
		}

		@Override
		public String getPath() {
			return "tableDefinition";
		}

		@Override
		public void setModified(RSTableModelDto container, boolean modified) {
			container.tableDefinition_m = modified;
		}

		@Override
		public boolean isModified(RSTableModelDto container) {
			return container.isTableDefinitionModified();
		}
	};


	public RSTableModelDto() {
		super();
	}

	public List<RSTableRowDto> getData()  {
		if(! isDtoProxy()){
			List<RSTableRowDto> _currentValue = this.data;
			if(null == _currentValue)
				this.data = new ArrayList<RSTableRowDto>();

			return this.data;
		}

		if(isDataModified())
			return this.data;

		if(! GWT.isClient())
			return null;

		List<RSTableRowDto> _value = dtoManager.getProperty(this, instantiatePropertyAccess().data());

		_value = new ChangeMonitoredList<RSTableRowDto>(_value);
		if(_value instanceof HasObjectChangedEventHandler){
			((HasObjectChangedEventHandler)_value).addObjectChangedHandler(new net.datenwerke.gxtdto.client.eventbus.handlers.ObjectChangedEventHandler(){
				@Override
				public void onObjectChangedEvent(net.datenwerke.gxtdto.client.eventbus.events.ObjectChangedEvent event){
					if(! isDataModified())
						setData((List<RSTableRowDto>) ((MonitoredCollection) event.getObject()).getUnderlyingCollection());
				}
			}
			);
		}
		return _value;
	}


	public void setData(List<RSTableRowDto> data)  {
		/* old value */
		List<RSTableRowDto> oldValue = null;
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

		this.fireObjectChangedEvent(RSTableModelDtoPA.INSTANCE.data(), oldValue);
	}


	public boolean isDataModified()  {
		return data_m;
	}


	public static PropertyAccessor<RSTableModelDto, List<RSTableRowDto>> getDataPropertyAccessor()  {
		return data_pa;
	}


	public TableDefinitionDto getTableDefinition()  {
		if(! isDtoProxy()){
			return this.tableDefinition;
		}

		if(isTableDefinitionModified())
			return this.tableDefinition;

		if(! GWT.isClient())
			return null;

		TableDefinitionDto _value = dtoManager.getProperty(this, instantiatePropertyAccess().tableDefinition());

		if(_value instanceof HasObjectChangedEventHandler){
			((HasObjectChangedEventHandler)_value).addObjectChangedHandler(new net.datenwerke.gxtdto.client.eventbus.handlers.ObjectChangedEventHandler(){
				@Override
				public void onObjectChangedEvent(net.datenwerke.gxtdto.client.eventbus.events.ObjectChangedEvent event){
					if(! isTableDefinitionModified())
						setTableDefinition((TableDefinitionDto) event.getObject());
				}
			}
			);
		}
		return _value;
	}


	public void setTableDefinition(TableDefinitionDto tableDefinition)  {
		/* old value */
		TableDefinitionDto oldValue = null;
		if(GWT.isClient())
			oldValue = getTableDefinition();

		/* set new value */
		this.tableDefinition = tableDefinition;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(tableDefinition_pa, oldValue, tableDefinition, this.tableDefinition_m));

		/* set indicator */
		this.tableDefinition_m = true;

		this.fireObjectChangedEvent(RSTableModelDtoPA.INSTANCE.tableDefinition(), oldValue);
	}


	public boolean isTableDefinitionModified()  {
		return tableDefinition_m;
	}


	public static PropertyAccessor<RSTableModelDto, TableDefinitionDto> getTableDefinitionPropertyAccessor()  {
		return tableDefinition_pa;
	}


	@Override
	public String toString()  {
		return super.toString();
	}

	public static Dto2PosoMapper newPosoMapper()  {
		return new RSTableModelDto2PosoMap();
	}

	public RSTableModelDtoPA instantiatePropertyAccess()  {
		return GWT.create(RSTableModelDtoPA.class);
	}

	public void clearModified()  {
		this.data = null;
		this.data_m = false;
		this.tableDefinition = null;
		this.tableDefinition_m = false;
	}


	public boolean isModified()  {
		if(super.isModified())
			return true;
		if(data_m)
			return true;
		if(tableDefinition_m)
			return true;
		return false;
	}


	public List<PropertyAccessor> getPropertyAccessors()  {
		List<PropertyAccessor> list = super.getPropertyAccessors();
		list.add(data_pa);
		list.add(tableDefinition_pa);
		return list;
	}


	public List<PropertyAccessor> getModifiedPropertyAccessors()  {
		List<PropertyAccessor> list = super.getModifiedPropertyAccessors();
		if(data_m)
			list.add(data_pa);
		if(tableDefinition_m)
			list.add(tableDefinition_pa);
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsByView(net.datenwerke.gxtdto.client.dtomanager.DtoView view)  {
		List<PropertyAccessor> list = super.getPropertyAccessorsByView(view);
		if(view.compareTo(DtoView.NORMAL) >= 0){
			list.add(data_pa);
			list.add(tableDefinition_pa);
		}
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsForDtos()  {
		List<PropertyAccessor> list = super.getPropertyAccessorsForDtos();
		list.add(data_pa);
		list.add(tableDefinition_pa);
		return list;
	}



	net.datenwerke.rs.base.client.reportengines.table.dto.RSTableRowDto wl_0;
	net.datenwerke.rs.base.client.reportengines.table.dto.TableDefinitionDto wl_1;

}
