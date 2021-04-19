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
import net.datenwerke.rs.base.client.reportengines.table.dto.CurrencyTypeDto;
import net.datenwerke.rs.base.client.reportengines.table.dto.decorator.ColumnFormatNumberDtoDec;
import net.datenwerke.rs.base.client.reportengines.table.dto.pa.ColumnFormatCurrencyDtoPA;
import net.datenwerke.rs.base.client.reportengines.table.dto.posomap.ColumnFormatCurrencyDto2PosoMap;
import net.datenwerke.rs.base.service.reportengines.table.entities.format.ColumnFormatCurrency;

/**
 * Dto for {@link ColumnFormatCurrency}
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
abstract public class ColumnFormatCurrencyDto extends ColumnFormatNumberDtoDec {


	private static final long serialVersionUID = 1;


	/* Fields */
	private CurrencyTypeDto currencyType;
	private  boolean currencyType_m;
	public static final String PROPERTY_CURRENCY_TYPE = "dpi-columnformatcurrency-currencytype";

	private transient static PropertyAccessor<ColumnFormatCurrencyDto, CurrencyTypeDto> currencyType_pa = new PropertyAccessor<ColumnFormatCurrencyDto, CurrencyTypeDto>() {
		@Override
		public void setValue(ColumnFormatCurrencyDto container, CurrencyTypeDto object) {
			container.setCurrencyType(object);
		}

		@Override
		public CurrencyTypeDto getValue(ColumnFormatCurrencyDto container) {
			return container.getCurrencyType();
		}

		@Override
		public Class<?> getType() {
			return CurrencyTypeDto.class;
		}

		@Override
		public String getPath() {
			return "currencyType";
		}

		@Override
		public void setModified(ColumnFormatCurrencyDto container, boolean modified) {
			container.currencyType_m = modified;
		}

		@Override
		public boolean isModified(ColumnFormatCurrencyDto container) {
			return container.isCurrencyTypeModified();
		}
	};


	public ColumnFormatCurrencyDto() {
		super();
	}

	public CurrencyTypeDto getCurrencyType()  {
		if(! isDtoProxy()){
			return this.currencyType;
		}

		if(isCurrencyTypeModified())
			return this.currencyType;

		if(! GWT.isClient())
			return null;

		CurrencyTypeDto _value = dtoManager.getProperty(this, instantiatePropertyAccess().currencyType());

		return _value;
	}


	public void setCurrencyType(CurrencyTypeDto currencyType)  {
		/* old value */
		CurrencyTypeDto oldValue = null;
		if(GWT.isClient())
			oldValue = getCurrencyType();

		/* set new value */
		this.currencyType = currencyType;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(currencyType_pa, oldValue, currencyType, this.currencyType_m));

		/* set indicator */
		this.currencyType_m = true;

		this.fireObjectChangedEvent(ColumnFormatCurrencyDtoPA.INSTANCE.currencyType(), oldValue);
	}


	public boolean isCurrencyTypeModified()  {
		return currencyType_m;
	}


	public static PropertyAccessor<ColumnFormatCurrencyDto, CurrencyTypeDto> getCurrencyTypePropertyAccessor()  {
		return currencyType_pa;
	}


	@Override
	public int hashCode()  {
		if(null == getId())
			return super.hashCode();
		return getId().hashCode();
	}

	@Override
	public boolean equals(Object obj)  {
		if(! (obj instanceof ColumnFormatCurrencyDto))
			return false;

		if(null == getId())
			return super.equals(obj);
		return getId().equals(((ColumnFormatCurrencyDto)obj).getId());
	}

	@Override
	public String toString()  {
		return getClass().getName() + ": " + getId();
	}

	public static Dto2PosoMapper newPosoMapper()  {
		return new ColumnFormatCurrencyDto2PosoMap();
	}

	public ColumnFormatCurrencyDtoPA instantiatePropertyAccess()  {
		return GWT.create(ColumnFormatCurrencyDtoPA.class);
	}

	public void clearModified()  {
		this.currencyType = null;
		this.currencyType_m = false;
	}


	public boolean isModified()  {
		if(super.isModified())
			return true;
		if(currencyType_m)
			return true;
		return false;
	}


	public List<PropertyAccessor> getPropertyAccessors()  {
		List<PropertyAccessor> list = super.getPropertyAccessors();
		list.add(currencyType_pa);
		return list;
	}


	public List<PropertyAccessor> getModifiedPropertyAccessors()  {
		List<PropertyAccessor> list = super.getModifiedPropertyAccessors();
		if(currencyType_m)
			list.add(currencyType_pa);
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsByView(net.datenwerke.gxtdto.client.dtomanager.DtoView view)  {
		List<PropertyAccessor> list = super.getPropertyAccessorsByView(view);
		if(view.compareTo(DtoView.NORMAL) >= 0){
			list.add(currencyType_pa);
		}
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsForDtos()  {
		List<PropertyAccessor> list = super.getPropertyAccessorsForDtos();
		return list;
	}



	net.datenwerke.rs.base.client.reportengines.table.dto.CurrencyTypeDto wl_0;

}
