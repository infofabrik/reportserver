package net.datenwerke.rs.base.client.reportengines.table.dto;

import com.google.gwt.core.client.GWT;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.gxtdto.client.dtomanager.Dto2PosoMapper;
import net.datenwerke.gxtdto.client.dtomanager.DtoView;
import net.datenwerke.gxtdto.client.dtomanager.PropertyAccessor;
import net.datenwerke.gxtdto.client.dtomanager.redoundo.ChangeTracker;
import net.datenwerke.rs.base.client.reportengines.table.dto.NumberTypeDto;
import net.datenwerke.rs.base.client.reportengines.table.dto.decorator.ColumnFormatDtoDec;
import net.datenwerke.rs.base.client.reportengines.table.dto.pa.ColumnFormatNumberDtoPA;
import net.datenwerke.rs.base.client.reportengines.table.dto.posomap.ColumnFormatNumberDto2PosoMap;
import net.datenwerke.rs.base.service.reportengines.table.entities.format.ColumnFormatNumber;

/**
 * Dto for {@link ColumnFormatNumber}
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
abstract public class ColumnFormatNumberDto extends ColumnFormatDtoDec {


	private static final long serialVersionUID = 1;


	/* Fields */
	private int numberOfDecimalPlaces;
	private  boolean numberOfDecimalPlaces_m;
	public static final String PROPERTY_NUMBER_OF_DECIMAL_PLACES = "dpi-columnformatnumber-numberofdecimalplaces";

	private transient static PropertyAccessor<ColumnFormatNumberDto, Integer> numberOfDecimalPlaces_pa = new PropertyAccessor<ColumnFormatNumberDto, Integer>() {
		@Override
		public void setValue(ColumnFormatNumberDto container, Integer object) {
			container.setNumberOfDecimalPlaces(object);
		}

		@Override
		public Integer getValue(ColumnFormatNumberDto container) {
			return container.getNumberOfDecimalPlaces();
		}

		@Override
		public Class<?> getType() {
			return Integer.class;
		}

		@Override
		public String getPath() {
			return "numberOfDecimalPlaces";
		}

		@Override
		public void setModified(ColumnFormatNumberDto container, boolean modified) {
			container.numberOfDecimalPlaces_m = modified;
		}

		@Override
		public boolean isModified(ColumnFormatNumberDto container) {
			return container.isNumberOfDecimalPlacesModified();
		}
	};

	private boolean thousandSeparator;
	private  boolean thousandSeparator_m;
	public static final String PROPERTY_THOUSAND_SEPARATOR = "dpi-columnformatnumber-thousandseparator";

	private transient static PropertyAccessor<ColumnFormatNumberDto, Boolean> thousandSeparator_pa = new PropertyAccessor<ColumnFormatNumberDto, Boolean>() {
		@Override
		public void setValue(ColumnFormatNumberDto container, Boolean object) {
			container.setThousandSeparator(object);
		}

		@Override
		public Boolean getValue(ColumnFormatNumberDto container) {
			return container.isThousandSeparator();
		}

		@Override
		public Class<?> getType() {
			return Boolean.class;
		}

		@Override
		public String getPath() {
			return "thousandSeparator";
		}

		@Override
		public void setModified(ColumnFormatNumberDto container, boolean modified) {
			container.thousandSeparator_m = modified;
		}

		@Override
		public boolean isModified(ColumnFormatNumberDto container) {
			return container.isThousandSeparatorModified();
		}
	};

	private NumberTypeDto type;
	private  boolean type_m;
	public static final String PROPERTY_TYPE = "dpi-columnformatnumber-type";

	private transient static PropertyAccessor<ColumnFormatNumberDto, NumberTypeDto> type_pa = new PropertyAccessor<ColumnFormatNumberDto, NumberTypeDto>() {
		@Override
		public void setValue(ColumnFormatNumberDto container, NumberTypeDto object) {
			container.setType(object);
		}

		@Override
		public NumberTypeDto getValue(ColumnFormatNumberDto container) {
			return container.getType();
		}

		@Override
		public Class<?> getType() {
			return NumberTypeDto.class;
		}

		@Override
		public String getPath() {
			return "type";
		}

		@Override
		public void setModified(ColumnFormatNumberDto container, boolean modified) {
			container.type_m = modified;
		}

		@Override
		public boolean isModified(ColumnFormatNumberDto container) {
			return container.isTypeModified();
		}
	};


	public ColumnFormatNumberDto() {
		super();
	}

	public int getNumberOfDecimalPlaces()  {
		if(! isDtoProxy()){
			return this.numberOfDecimalPlaces;
		}

		if(isNumberOfDecimalPlacesModified())
			return this.numberOfDecimalPlaces;

		if(! GWT.isClient())
			return 0;

		int _value = dtoManager.getProperty(this, instantiatePropertyAccess().numberOfDecimalPlaces());

		return _value;
	}


	public void setNumberOfDecimalPlaces(int numberOfDecimalPlaces)  {
		/* old value */
		int oldValue = 0;
		if(GWT.isClient())
			oldValue = getNumberOfDecimalPlaces();

		/* set new value */
		this.numberOfDecimalPlaces = numberOfDecimalPlaces;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(numberOfDecimalPlaces_pa, oldValue, numberOfDecimalPlaces, this.numberOfDecimalPlaces_m));

		/* set indicator */
		this.numberOfDecimalPlaces_m = true;

		this.fireObjectChangedEvent(ColumnFormatNumberDtoPA.INSTANCE.numberOfDecimalPlaces(), oldValue);
	}


	public boolean isNumberOfDecimalPlacesModified()  {
		return numberOfDecimalPlaces_m;
	}


	public static PropertyAccessor<ColumnFormatNumberDto, Integer> getNumberOfDecimalPlacesPropertyAccessor()  {
		return numberOfDecimalPlaces_pa;
	}


	public boolean isThousandSeparator()  {
		if(! isDtoProxy()){
			return this.thousandSeparator;
		}

		if(isThousandSeparatorModified())
			return this.thousandSeparator;

		if(! GWT.isClient())
			return false;

		boolean _value = dtoManager.getProperty(this, instantiatePropertyAccess().thousandSeparator());

		return _value;
	}


	public void setThousandSeparator(boolean thousandSeparator)  {
		/* old value */
		boolean oldValue = false;
		if(GWT.isClient())
			oldValue = isThousandSeparator();

		/* set new value */
		this.thousandSeparator = thousandSeparator;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(thousandSeparator_pa, oldValue, thousandSeparator, this.thousandSeparator_m));

		/* set indicator */
		this.thousandSeparator_m = true;

		this.fireObjectChangedEvent(ColumnFormatNumberDtoPA.INSTANCE.thousandSeparator(), oldValue);
	}


	public boolean isThousandSeparatorModified()  {
		return thousandSeparator_m;
	}


	public static PropertyAccessor<ColumnFormatNumberDto, Boolean> getThousandSeparatorPropertyAccessor()  {
		return thousandSeparator_pa;
	}


	public NumberTypeDto getType()  {
		if(! isDtoProxy()){
			return this.type;
		}

		if(isTypeModified())
			return this.type;

		if(! GWT.isClient())
			return null;

		NumberTypeDto _value = dtoManager.getProperty(this, instantiatePropertyAccess().type());

		return _value;
	}


	public void setType(NumberTypeDto type)  {
		/* old value */
		NumberTypeDto oldValue = null;
		if(GWT.isClient())
			oldValue = getType();

		/* set new value */
		this.type = type;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(type_pa, oldValue, type, this.type_m));

		/* set indicator */
		this.type_m = true;

		this.fireObjectChangedEvent(ColumnFormatNumberDtoPA.INSTANCE.type(), oldValue);
	}


	public boolean isTypeModified()  {
		return type_m;
	}


	public static PropertyAccessor<ColumnFormatNumberDto, NumberTypeDto> getTypePropertyAccessor()  {
		return type_pa;
	}


	@Override
	public int hashCode()  {
		if(null == getId())
			return super.hashCode();
		return getId().hashCode();
	}

	@Override
	public boolean equals(Object obj)  {
		if(! (obj instanceof ColumnFormatNumberDto))
			return false;

		if(null == getId())
			return super.equals(obj);
		return getId().equals(((ColumnFormatNumberDto)obj).getId());
	}

	@Override
	public String toString()  {
		return getClass().getName() + ": " + getId();
	}

	public static Dto2PosoMapper newPosoMapper()  {
		return new ColumnFormatNumberDto2PosoMap();
	}

	public ColumnFormatNumberDtoPA instantiatePropertyAccess()  {
		return GWT.create(ColumnFormatNumberDtoPA.class);
	}

	public void clearModified()  {
		this.numberOfDecimalPlaces = 0;
		this.numberOfDecimalPlaces_m = false;
		this.thousandSeparator = false;
		this.thousandSeparator_m = false;
		this.type = null;
		this.type_m = false;
	}


	public boolean isModified()  {
		if(super.isModified())
			return true;
		if(numberOfDecimalPlaces_m)
			return true;
		if(thousandSeparator_m)
			return true;
		if(type_m)
			return true;
		return false;
	}


	public List<PropertyAccessor> getPropertyAccessors()  {
		List<PropertyAccessor> list = super.getPropertyAccessors();
		list.add(numberOfDecimalPlaces_pa);
		list.add(thousandSeparator_pa);
		list.add(type_pa);
		return list;
	}


	public List<PropertyAccessor> getModifiedPropertyAccessors()  {
		List<PropertyAccessor> list = super.getModifiedPropertyAccessors();
		if(numberOfDecimalPlaces_m)
			list.add(numberOfDecimalPlaces_pa);
		if(thousandSeparator_m)
			list.add(thousandSeparator_pa);
		if(type_m)
			list.add(type_pa);
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsByView(net.datenwerke.gxtdto.client.dtomanager.DtoView view)  {
		List<PropertyAccessor> list = super.getPropertyAccessorsByView(view);
		if(view.compareTo(DtoView.NORMAL) >= 0){
			list.add(numberOfDecimalPlaces_pa);
			list.add(thousandSeparator_pa);
			list.add(type_pa);
		}
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsForDtos()  {
		List<PropertyAccessor> list = super.getPropertyAccessorsForDtos();
		return list;
	}



	net.datenwerke.rs.base.client.reportengines.table.dto.NumberTypeDto wl_0;

}
