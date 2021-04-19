package net.datenwerke.rs.terminal.client.terminal.dto;

import com.google.gwt.core.client.GWT;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.gxtdto.client.dtomanager.Dto2PosoMapper;
import net.datenwerke.gxtdto.client.dtomanager.Dto;
import net.datenwerke.gxtdto.client.dtomanager.DtoView;
import net.datenwerke.gxtdto.client.dtomanager.PropertyAccessor;
import net.datenwerke.gxtdto.client.dtomanager.redoundo.ChangeTracker;
import net.datenwerke.gxtdto.client.eventbus.events.ObjectChangedEvent;
import net.datenwerke.gxtdto.client.eventbus.handlers.ObjectChangedEventHandler;
import net.datenwerke.gxtdto.client.eventbus.handlers.has.HasObjectChangedEventHandler;
import net.datenwerke.rs.base.client.reportengines.table.dto.RSTableModelDto;
import net.datenwerke.rs.terminal.client.terminal.dto.decorator.CommandResultEntryDtoDec;
import net.datenwerke.rs.terminal.client.terminal.dto.pa.CommandResultTableDtoPA;
import net.datenwerke.rs.terminal.client.terminal.dto.posomap.CommandResultTableDto2PosoMap;
import net.datenwerke.rs.terminal.service.terminal.obj.CommandResultTable;

/**
 * Dto for {@link CommandResultTable}
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
abstract public class CommandResultTableDto extends CommandResultEntryDtoDec {


	private static final long serialVersionUID = 1;


	/* Fields */
	private RSTableModelDto table;
	private  boolean table_m;
	public static final String PROPERTY_TABLE = "dpi-commandresulttable-table";

	private transient static PropertyAccessor<CommandResultTableDto, RSTableModelDto> table_pa = new PropertyAccessor<CommandResultTableDto, RSTableModelDto>() {
		@Override
		public void setValue(CommandResultTableDto container, RSTableModelDto object) {
			container.setTable(object);
		}

		@Override
		public RSTableModelDto getValue(CommandResultTableDto container) {
			return container.getTable();
		}

		@Override
		public Class<?> getType() {
			return RSTableModelDto.class;
		}

		@Override
		public String getPath() {
			return "table";
		}

		@Override
		public void setModified(CommandResultTableDto container, boolean modified) {
			container.table_m = modified;
		}

		@Override
		public boolean isModified(CommandResultTableDto container) {
			return container.isTableModified();
		}
	};


	public CommandResultTableDto() {
		super();
	}

	public RSTableModelDto getTable()  {
		if(! isDtoProxy()){
			return this.table;
		}

		if(isTableModified())
			return this.table;

		if(! GWT.isClient())
			return null;

		RSTableModelDto _value = dtoManager.getProperty(this, instantiatePropertyAccess().table());

		if(_value instanceof HasObjectChangedEventHandler){
			((HasObjectChangedEventHandler)_value).addObjectChangedHandler(new net.datenwerke.gxtdto.client.eventbus.handlers.ObjectChangedEventHandler(){
				@Override
				public void onObjectChangedEvent(net.datenwerke.gxtdto.client.eventbus.events.ObjectChangedEvent event){
					if(! isTableModified())
						setTable((RSTableModelDto) event.getObject());
				}
			}
			);
		}
		return _value;
	}


	public void setTable(RSTableModelDto table)  {
		/* old value */
		RSTableModelDto oldValue = null;
		if(GWT.isClient())
			oldValue = getTable();

		/* set new value */
		this.table = table;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(table_pa, oldValue, table, this.table_m));

		/* set indicator */
		this.table_m = true;

		this.fireObjectChangedEvent(CommandResultTableDtoPA.INSTANCE.table(), oldValue);
	}


	public boolean isTableModified()  {
		return table_m;
	}


	public static PropertyAccessor<CommandResultTableDto, RSTableModelDto> getTablePropertyAccessor()  {
		return table_pa;
	}


	@Override
	public String toString()  {
		return super.toString();
	}

	public static Dto2PosoMapper newPosoMapper()  {
		return new CommandResultTableDto2PosoMap();
	}

	public CommandResultTableDtoPA instantiatePropertyAccess()  {
		return GWT.create(CommandResultTableDtoPA.class);
	}

	public void clearModified()  {
		this.table = null;
		this.table_m = false;
	}


	public boolean isModified()  {
		if(super.isModified())
			return true;
		if(table_m)
			return true;
		return false;
	}


	public List<PropertyAccessor> getPropertyAccessors()  {
		List<PropertyAccessor> list = super.getPropertyAccessors();
		list.add(table_pa);
		return list;
	}


	public List<PropertyAccessor> getModifiedPropertyAccessors()  {
		List<PropertyAccessor> list = super.getModifiedPropertyAccessors();
		if(table_m)
			list.add(table_pa);
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsByView(net.datenwerke.gxtdto.client.dtomanager.DtoView view)  {
		List<PropertyAccessor> list = super.getPropertyAccessorsByView(view);
		if(view.compareTo(DtoView.NORMAL) >= 0){
			list.add(table_pa);
		}
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsForDtos()  {
		List<PropertyAccessor> list = super.getPropertyAccessorsForDtos();
		list.add(table_pa);
		return list;
	}



	net.datenwerke.rs.base.client.reportengines.table.dto.RSTableModelDto wl_0;

}
