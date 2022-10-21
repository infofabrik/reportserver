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
import net.datenwerke.rs.base.client.reportengines.table.dto.BinaryOperatorDto;
import net.datenwerke.rs.base.client.reportengines.table.dto.ColumnDto;
import net.datenwerke.rs.base.client.reportengines.table.dto.decorator.FilterSpecDtoDec;
import net.datenwerke.rs.base.client.reportengines.table.dto.pa.BinaryColumnFilterDtoPA;
import net.datenwerke.rs.base.client.reportengines.table.dto.posomap.BinaryColumnFilterDto2PosoMap;
import net.datenwerke.rs.base.service.reportengines.table.entities.filters.BinaryColumnFilter;

/**
 * Dto for {@link BinaryColumnFilter}
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
abstract public class BinaryColumnFilterDto extends FilterSpecDtoDec {


	private static final long serialVersionUID = 1;


	/* Fields */
	private ColumnDto columnA;
	private  boolean columnA_m;
	public static final String PROPERTY_COLUMN_A = "dpi-binarycolumnfilter-columna";

	private transient static PropertyAccessor<BinaryColumnFilterDto, ColumnDto> columnA_pa = new PropertyAccessor<BinaryColumnFilterDto, ColumnDto>() {
		@Override
		public void setValue(BinaryColumnFilterDto container, ColumnDto object) {
			container.setColumnA(object);
		}

		@Override
		public ColumnDto getValue(BinaryColumnFilterDto container) {
			return container.getColumnA();
		}

		@Override
		public Class<?> getType() {
			return ColumnDto.class;
		}

		@Override
		public String getPath() {
			return "columnA";
		}

		@Override
		public void setModified(BinaryColumnFilterDto container, boolean modified) {
			container.columnA_m = modified;
		}

		@Override
		public boolean isModified(BinaryColumnFilterDto container) {
			return container.isColumnAModified();
		}
	};

	private ColumnDto columnB;
	private  boolean columnB_m;
	public static final String PROPERTY_COLUMN_B = "dpi-binarycolumnfilter-columnb";

	private transient static PropertyAccessor<BinaryColumnFilterDto, ColumnDto> columnB_pa = new PropertyAccessor<BinaryColumnFilterDto, ColumnDto>() {
		@Override
		public void setValue(BinaryColumnFilterDto container, ColumnDto object) {
			container.setColumnB(object);
		}

		@Override
		public ColumnDto getValue(BinaryColumnFilterDto container) {
			return container.getColumnB();
		}

		@Override
		public Class<?> getType() {
			return ColumnDto.class;
		}

		@Override
		public String getPath() {
			return "columnB";
		}

		@Override
		public void setModified(BinaryColumnFilterDto container, boolean modified) {
			container.columnB_m = modified;
		}

		@Override
		public boolean isModified(BinaryColumnFilterDto container) {
			return container.isColumnBModified();
		}
	};

	private BinaryOperatorDto operator;
	private  boolean operator_m;
	public static final String PROPERTY_OPERATOR = "dpi-binarycolumnfilter-operator";

	private transient static PropertyAccessor<BinaryColumnFilterDto, BinaryOperatorDto> operator_pa = new PropertyAccessor<BinaryColumnFilterDto, BinaryOperatorDto>() {
		@Override
		public void setValue(BinaryColumnFilterDto container, BinaryOperatorDto object) {
			container.setOperator(object);
		}

		@Override
		public BinaryOperatorDto getValue(BinaryColumnFilterDto container) {
			return container.getOperator();
		}

		@Override
		public Class<?> getType() {
			return BinaryOperatorDto.class;
		}

		@Override
		public String getPath() {
			return "operator";
		}

		@Override
		public void setModified(BinaryColumnFilterDto container, boolean modified) {
			container.operator_m = modified;
		}

		@Override
		public boolean isModified(BinaryColumnFilterDto container) {
			return container.isOperatorModified();
		}
	};


	public BinaryColumnFilterDto() {
		super();
	}

	public ColumnDto getColumnA()  {
		if(! isDtoProxy()){
			return this.columnA;
		}

		if(isColumnAModified())
			return this.columnA;

		if(! GWT.isClient())
			return null;

		ColumnDto _value = dtoManager.getProperty(this, instantiatePropertyAccess().columnA());

		if(_value instanceof HasObjectChangedEventHandler){
			((HasObjectChangedEventHandler)_value).addObjectChangedHandler(new net.datenwerke.gxtdto.client.eventbus.handlers.ObjectChangedEventHandler(){
				@Override
				public void onObjectChangedEvent(net.datenwerke.gxtdto.client.eventbus.events.ObjectChangedEvent event){
					if(! isColumnAModified())
						setColumnA((ColumnDto) event.getObject());
				}
			}
			);
		}
		return _value;
	}


	public void setColumnA(ColumnDto columnA)  {
		/* old value */
		ColumnDto oldValue = null;
		if(GWT.isClient())
			oldValue = getColumnA();

		/* set new value */
		this.columnA = columnA;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(columnA_pa, oldValue, columnA, this.columnA_m));

		/* set indicator */
		this.columnA_m = true;

		this.fireObjectChangedEvent(BinaryColumnFilterDtoPA.INSTANCE.columnA(), oldValue);
	}


	public boolean isColumnAModified()  {
		return columnA_m;
	}


	public static PropertyAccessor<BinaryColumnFilterDto, ColumnDto> getColumnAPropertyAccessor()  {
		return columnA_pa;
	}


	public ColumnDto getColumnB()  {
		if(! isDtoProxy()){
			return this.columnB;
		}

		if(isColumnBModified())
			return this.columnB;

		if(! GWT.isClient())
			return null;

		ColumnDto _value = dtoManager.getProperty(this, instantiatePropertyAccess().columnB());

		if(_value instanceof HasObjectChangedEventHandler){
			((HasObjectChangedEventHandler)_value).addObjectChangedHandler(new net.datenwerke.gxtdto.client.eventbus.handlers.ObjectChangedEventHandler(){
				@Override
				public void onObjectChangedEvent(net.datenwerke.gxtdto.client.eventbus.events.ObjectChangedEvent event){
					if(! isColumnBModified())
						setColumnB((ColumnDto) event.getObject());
				}
			}
			);
		}
		return _value;
	}


	public void setColumnB(ColumnDto columnB)  {
		/* old value */
		ColumnDto oldValue = null;
		if(GWT.isClient())
			oldValue = getColumnB();

		/* set new value */
		this.columnB = columnB;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(columnB_pa, oldValue, columnB, this.columnB_m));

		/* set indicator */
		this.columnB_m = true;

		this.fireObjectChangedEvent(BinaryColumnFilterDtoPA.INSTANCE.columnB(), oldValue);
	}


	public boolean isColumnBModified()  {
		return columnB_m;
	}


	public static PropertyAccessor<BinaryColumnFilterDto, ColumnDto> getColumnBPropertyAccessor()  {
		return columnB_pa;
	}


	public BinaryOperatorDto getOperator()  {
		if(! isDtoProxy()){
			return this.operator;
		}

		if(isOperatorModified())
			return this.operator;

		if(! GWT.isClient())
			return null;

		BinaryOperatorDto _value = dtoManager.getProperty(this, instantiatePropertyAccess().operator());

		return _value;
	}


	public void setOperator(BinaryOperatorDto operator)  {
		/* old value */
		BinaryOperatorDto oldValue = null;
		if(GWT.isClient())
			oldValue = getOperator();

		/* set new value */
		this.operator = operator;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(operator_pa, oldValue, operator, this.operator_m));

		/* set indicator */
		this.operator_m = true;

		this.fireObjectChangedEvent(BinaryColumnFilterDtoPA.INSTANCE.operator(), oldValue);
	}


	public boolean isOperatorModified()  {
		return operator_m;
	}


	public static PropertyAccessor<BinaryColumnFilterDto, BinaryOperatorDto> getOperatorPropertyAccessor()  {
		return operator_pa;
	}


	@Override
	public int hashCode()  {
		if(null == getId())
			return super.hashCode();
		return getId().hashCode();
	}

	@Override
	public boolean equals(Object obj)  {
		if(! (obj instanceof BinaryColumnFilterDto))
			return false;

		if(null == getId())
			return super.equals(obj);
		return getId().equals(((BinaryColumnFilterDto)obj).getId());
	}

	@Override
	public String toString()  {
		return getClass().getName() + ": " + getId();
	}

	public static Dto2PosoMapper newPosoMapper()  {
		return new BinaryColumnFilterDto2PosoMap();
	}

	public BinaryColumnFilterDtoPA instantiatePropertyAccess()  {
		return GWT.create(BinaryColumnFilterDtoPA.class);
	}

	public void clearModified()  {
		this.columnA = null;
		this.columnA_m = false;
		this.columnB = null;
		this.columnB_m = false;
		this.operator = null;
		this.operator_m = false;
	}


	public boolean isModified()  {
		if(super.isModified())
			return true;
		if(columnA_m)
			return true;
		if(columnB_m)
			return true;
		if(operator_m)
			return true;
		return false;
	}


	public List<PropertyAccessor> getPropertyAccessors()  {
		List<PropertyAccessor> list = super.getPropertyAccessors();
		list.add(columnA_pa);
		list.add(columnB_pa);
		list.add(operator_pa);
		return list;
	}


	public List<PropertyAccessor> getModifiedPropertyAccessors()  {
		List<PropertyAccessor> list = super.getModifiedPropertyAccessors();
		if(columnA_m)
			list.add(columnA_pa);
		if(columnB_m)
			list.add(columnB_pa);
		if(operator_m)
			list.add(operator_pa);
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsByView(net.datenwerke.gxtdto.client.dtomanager.DtoView view)  {
		List<PropertyAccessor> list = super.getPropertyAccessorsByView(view);
		if(view.compareTo(DtoView.NORMAL) >= 0){
			list.add(columnA_pa);
			list.add(columnB_pa);
			list.add(operator_pa);
		}
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsForDtos()  {
		List<PropertyAccessor> list = super.getPropertyAccessorsForDtos();
		list.add(columnA_pa);
		list.add(columnB_pa);
		return list;
	}



	net.datenwerke.rs.base.client.reportengines.table.dto.BinaryOperatorDto wl_0;
	net.datenwerke.rs.base.client.reportengines.table.dto.ColumnDto wl_1;

}
