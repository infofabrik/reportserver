package net.datenwerke.rs.base.client.reportengines.table.dto;

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
import net.datenwerke.rs.base.client.reportengines.table.dto.ColumnDto;
import net.datenwerke.rs.base.client.reportengines.table.dto.decorator.FilterSpecDtoDec;
import net.datenwerke.rs.base.client.reportengines.table.dto.pa.ColumnFilterDtoPA;
import net.datenwerke.rs.base.client.reportengines.table.dto.posomap.ColumnFilterDto2PosoMap;
import net.datenwerke.rs.base.service.reportengines.table.entities.filters.ColumnFilter;

/**
 * Dto for {@link ColumnFilter}
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
abstract public class ColumnFilterDto extends FilterSpecDtoDec {


	private static final long serialVersionUID = 1;


	/* Fields */
	private ColumnDto column;
	private  boolean column_m;
	public static final String PROPERTY_COLUMN = "dpi-columnfilter-column";

	private transient static PropertyAccessor<ColumnFilterDto, ColumnDto> column_pa = new PropertyAccessor<ColumnFilterDto, ColumnDto>() {
		@Override
		public void setValue(ColumnFilterDto container, ColumnDto object) {
			container.setColumn(object);
		}

		@Override
		public ColumnDto getValue(ColumnFilterDto container) {
			return container.getColumn();
		}

		@Override
		public Class<?> getType() {
			return ColumnDto.class;
		}

		@Override
		public String getPath() {
			return "column";
		}

		@Override
		public void setModified(ColumnFilterDto container, boolean modified) {
			container.column_m = modified;
		}

		@Override
		public boolean isModified(ColumnFilterDto container) {
			return container.isColumnModified();
		}
	};


	public ColumnFilterDto() {
		super();
	}

	public ColumnDto getColumn()  {
		if(! isDtoProxy()){
			return this.column;
		}

		if(isColumnModified())
			return this.column;

		if(! GWT.isClient())
			return null;

		ColumnDto _value = dtoManager.getProperty(this, instantiatePropertyAccess().column());

		if(_value instanceof HasObjectChangedEventHandler){
			((HasObjectChangedEventHandler)_value).addObjectChangedHandler(new net.datenwerke.gxtdto.client.eventbus.handlers.ObjectChangedEventHandler(){
				@Override
				public void onObjectChangedEvent(net.datenwerke.gxtdto.client.eventbus.events.ObjectChangedEvent event){
					if(! isColumnModified())
						setColumn((ColumnDto) event.getObject());
				}
			}
			);
		}
		return _value;
	}


	public void setColumn(ColumnDto column)  {
		/* old value */
		ColumnDto oldValue = null;
		if(GWT.isClient())
			oldValue = getColumn();

		/* set new value */
		this.column = column;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(column_pa, oldValue, column, this.column_m));

		/* set indicator */
		this.column_m = true;

		this.fireObjectChangedEvent(ColumnFilterDtoPA.INSTANCE.column(), oldValue);
	}


	public boolean isColumnModified()  {
		return column_m;
	}


	public static PropertyAccessor<ColumnFilterDto, ColumnDto> getColumnPropertyAccessor()  {
		return column_pa;
	}


	@Override
	public int hashCode()  {
		if(null == getId())
			return super.hashCode();
		return getId().hashCode();
	}

	@Override
	public boolean equals(Object obj)  {
		if(! (obj instanceof ColumnFilterDto))
			return false;

		if(null == getId())
			return super.equals(obj);
		return getId().equals(((ColumnFilterDto)obj).getId());
	}

	@Override
	public String toString()  {
		return getClass().getName() + ": " + getId();
	}

	public static Dto2PosoMapper newPosoMapper()  {
		return new ColumnFilterDto2PosoMap();
	}

	public ColumnFilterDtoPA instantiatePropertyAccess()  {
		return GWT.create(ColumnFilterDtoPA.class);
	}

	public void clearModified()  {
		this.column = null;
		this.column_m = false;
	}


	public boolean isModified()  {
		if(super.isModified())
			return true;
		if(column_m)
			return true;
		return false;
	}


	public List<PropertyAccessor> getPropertyAccessors()  {
		List<PropertyAccessor> list = super.getPropertyAccessors();
		list.add(column_pa);
		return list;
	}


	public List<PropertyAccessor> getModifiedPropertyAccessors()  {
		List<PropertyAccessor> list = super.getModifiedPropertyAccessors();
		if(column_m)
			list.add(column_pa);
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsByView(net.datenwerke.gxtdto.client.dtomanager.DtoView view)  {
		List<PropertyAccessor> list = super.getPropertyAccessorsByView(view);
		if(view.compareTo(DtoView.NORMAL) >= 0){
			list.add(column_pa);
		}
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsForDtos()  {
		List<PropertyAccessor> list = super.getPropertyAccessorsForDtos();
		list.add(column_pa);
		return list;
	}



	net.datenwerke.rs.base.client.reportengines.table.dto.ColumnDto wl_0;

}
