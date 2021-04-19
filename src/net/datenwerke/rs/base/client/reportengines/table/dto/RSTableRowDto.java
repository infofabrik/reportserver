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
import net.datenwerke.gxtdto.client.dtomanager.redoundo.ChangeTracker;
import net.datenwerke.gxtdto.client.eventbus.events.ObjectChangedEvent;
import net.datenwerke.gxtdto.client.eventbus.handlers.ObjectChangedEventHandler;
import net.datenwerke.gxtdto.client.eventbus.handlers.has.HasObjectChangedEventHandler;
import net.datenwerke.rs.base.client.reportengines.table.dto.TableDefinitionDto;
import net.datenwerke.rs.base.client.reportengines.table.dto.pa.RSTableRowDtoPA;
import net.datenwerke.rs.base.client.reportengines.table.dto.posomap.RSTableRowDto2PosoMap;
import net.datenwerke.rs.base.service.reportengines.table.output.object.RSTableRow;

/**
 * Dto for {@link RSTableRow}
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class RSTableRowDto extends RsDto {


	private static final long serialVersionUID = 1;


	/* Fields */
	private TableDefinitionDto tableDefinition;
	private  boolean tableDefinition_m;
	public static final String PROPERTY_TABLE_DEFINITION = "dpi-rstablerow-tabledefinition";

	private transient static PropertyAccessor<RSTableRowDto, TableDefinitionDto> tableDefinition_pa = new PropertyAccessor<RSTableRowDto, TableDefinitionDto>() {
		@Override
		public void setValue(RSTableRowDto container, TableDefinitionDto object) {
			container.setTableDefinition(object);
		}

		@Override
		public TableDefinitionDto getValue(RSTableRowDto container) {
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
		public void setModified(RSTableRowDto container, boolean modified) {
			container.tableDefinition_m = modified;
		}

		@Override
		public boolean isModified(RSTableRowDto container) {
			return container.isTableDefinitionModified();
		}
	};


	public RSTableRowDto() {
		super();
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

		this.fireObjectChangedEvent(RSTableRowDtoPA.INSTANCE.tableDefinition(), oldValue);
	}


	public boolean isTableDefinitionModified()  {
		return tableDefinition_m;
	}


	public static PropertyAccessor<RSTableRowDto, TableDefinitionDto> getTableDefinitionPropertyAccessor()  {
		return tableDefinition_pa;
	}


	@Override
	public String toString()  {
		return super.toString();
	}

	public static Dto2PosoMapper newPosoMapper()  {
		return new RSTableRowDto2PosoMap();
	}

	public RSTableRowDtoPA instantiatePropertyAccess()  {
		return GWT.create(RSTableRowDtoPA.class);
	}

	public void clearModified()  {
		this.tableDefinition = null;
		this.tableDefinition_m = false;
	}


	public boolean isModified()  {
		if(super.isModified())
			return true;
		if(tableDefinition_m)
			return true;
		return false;
	}


	public List<PropertyAccessor> getPropertyAccessors()  {
		List<PropertyAccessor> list = super.getPropertyAccessors();
		list.add(tableDefinition_pa);
		return list;
	}


	public List<PropertyAccessor> getModifiedPropertyAccessors()  {
		List<PropertyAccessor> list = super.getModifiedPropertyAccessors();
		if(tableDefinition_m)
			list.add(tableDefinition_pa);
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsByView(net.datenwerke.gxtdto.client.dtomanager.DtoView view)  {
		List<PropertyAccessor> list = super.getPropertyAccessorsByView(view);
		if(view.compareTo(DtoView.NORMAL) >= 0){
			list.add(tableDefinition_pa);
		}
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsForDtos()  {
		List<PropertyAccessor> list = super.getPropertyAccessorsForDtos();
		list.add(tableDefinition_pa);
		return list;
	}



	net.datenwerke.rs.base.client.reportengines.table.dto.TableDefinitionDto wl_0;

}
