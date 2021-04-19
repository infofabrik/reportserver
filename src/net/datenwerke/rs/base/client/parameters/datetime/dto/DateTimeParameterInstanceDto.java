package net.datenwerke.rs.base.client.parameters.datetime.dto;

import com.google.gwt.core.client.GWT;
import java.lang.String;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.gxtdto.client.dtomanager.Dto2PosoMapper;
import net.datenwerke.gxtdto.client.dtomanager.DtoView;
import net.datenwerke.gxtdto.client.dtomanager.PropertyAccessor;
import net.datenwerke.gxtdto.client.dtomanager.redoundo.ChangeTracker;
import net.datenwerke.rs.base.client.parameters.datetime.dto.pa.DateTimeParameterInstanceDtoPA;
import net.datenwerke.rs.base.client.parameters.datetime.dto.posomap.DateTimeParameterInstanceDto2PosoMap;
import net.datenwerke.rs.base.service.parameters.datetime.DateTimeParameterInstance;
import net.datenwerke.rs.core.client.parameters.dto.ParameterInstanceDto;

/**
 * Dto for {@link DateTimeParameterInstance}
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
abstract public class DateTimeParameterInstanceDto extends ParameterInstanceDto {


	private static final long serialVersionUID = 1;


	/* Fields */
	private String formula;
	private  boolean formula_m;
	public static final String PROPERTY_FORMULA = "dpi-datetimeparameterinstance-formula";

	private transient static PropertyAccessor<DateTimeParameterInstanceDto, String> formula_pa = new PropertyAccessor<DateTimeParameterInstanceDto, String>() {
		@Override
		public void setValue(DateTimeParameterInstanceDto container, String object) {
			container.setFormula(object);
		}

		@Override
		public String getValue(DateTimeParameterInstanceDto container) {
			return container.getFormula();
		}

		@Override
		public Class<?> getType() {
			return String.class;
		}

		@Override
		public String getPath() {
			return "formula";
		}

		@Override
		public void setModified(DateTimeParameterInstanceDto container, boolean modified) {
			container.formula_m = modified;
		}

		@Override
		public boolean isModified(DateTimeParameterInstanceDto container) {
			return container.isFormulaModified();
		}
	};

	private String strValue;
	private  boolean strValue_m;
	public static final String PROPERTY_STR_VALUE = "dpi-datetimeparameterinstance-strvalue";

	private transient static PropertyAccessor<DateTimeParameterInstanceDto, String> strValue_pa = new PropertyAccessor<DateTimeParameterInstanceDto, String>() {
		@Override
		public void setValue(DateTimeParameterInstanceDto container, String object) {
			container.setStrValue(object);
		}

		@Override
		public String getValue(DateTimeParameterInstanceDto container) {
			return container.getStrValue();
		}

		@Override
		public Class<?> getType() {
			return String.class;
		}

		@Override
		public String getPath() {
			return "strValue";
		}

		@Override
		public void setModified(DateTimeParameterInstanceDto container, boolean modified) {
			container.strValue_m = modified;
		}

		@Override
		public boolean isModified(DateTimeParameterInstanceDto container) {
			return container.isStrValueModified();
		}
	};

	private Date value;
	private  boolean value_m;
	public static final String PROPERTY_VALUE = "dpi-datetimeparameterinstance-value";

	private transient static PropertyAccessor<DateTimeParameterInstanceDto, Date> value_pa = new PropertyAccessor<DateTimeParameterInstanceDto, Date>() {
		@Override
		public void setValue(DateTimeParameterInstanceDto container, Date object) {
			container.setValue(object);
		}

		@Override
		public Date getValue(DateTimeParameterInstanceDto container) {
			return container.getValue();
		}

		@Override
		public Class<?> getType() {
			return Date.class;
		}

		@Override
		public String getPath() {
			return "value";
		}

		@Override
		public void setModified(DateTimeParameterInstanceDto container, boolean modified) {
			container.value_m = modified;
		}

		@Override
		public boolean isModified(DateTimeParameterInstanceDto container) {
			return container.isValueModified();
		}
	};


	public DateTimeParameterInstanceDto() {
		super();
	}

	public String getFormula()  {
		if(! isDtoProxy()){
			return this.formula;
		}

		if(isFormulaModified())
			return this.formula;

		if(! GWT.isClient())
			return null;

		String _value = dtoManager.getProperty(this, instantiatePropertyAccess().formula());

		return _value;
	}


	public void setFormula(String formula)  {
		/* old value */
		String oldValue = null;
		if(GWT.isClient())
			oldValue = getFormula();

		/* set new value */
		this.formula = formula;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(formula_pa, oldValue, formula, this.formula_m));

		/* set indicator */
		this.formula_m = true;

		this.fireObjectChangedEvent(DateTimeParameterInstanceDtoPA.INSTANCE.formula(), oldValue);
	}


	public boolean isFormulaModified()  {
		return formula_m;
	}


	public static PropertyAccessor<DateTimeParameterInstanceDto, String> getFormulaPropertyAccessor()  {
		return formula_pa;
	}


	public String getStrValue()  {
		if(! isDtoProxy()){
			return this.strValue;
		}

		if(isStrValueModified())
			return this.strValue;

		if(! GWT.isClient())
			return null;

		String _value = dtoManager.getProperty(this, instantiatePropertyAccess().strValue());

		return _value;
	}


	public void setStrValue(String strValue)  {
		/* old value */
		String oldValue = null;
		if(GWT.isClient())
			oldValue = getStrValue();

		/* set new value */
		this.strValue = strValue;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(strValue_pa, oldValue, strValue, this.strValue_m));

		/* set indicator */
		this.strValue_m = true;

		this.fireObjectChangedEvent(DateTimeParameterInstanceDtoPA.INSTANCE.strValue(), oldValue);
	}


	public boolean isStrValueModified()  {
		return strValue_m;
	}


	public static PropertyAccessor<DateTimeParameterInstanceDto, String> getStrValuePropertyAccessor()  {
		return strValue_pa;
	}


	public Date getValue()  {
		if(! isDtoProxy()){
			return this.value;
		}

		if(isValueModified())
			return this.value;

		if(! GWT.isClient())
			return null;

		Date _value = dtoManager.getProperty(this, instantiatePropertyAccess().value());

		return _value;
	}


	public void setValue(Date value)  {
		/* old value */
		Date oldValue = null;
		if(GWT.isClient())
			oldValue = getValue();

		/* set new value */
		this.value = value;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(value_pa, oldValue, value, this.value_m));

		/* set indicator */
		this.value_m = true;

		this.fireObjectChangedEvent(DateTimeParameterInstanceDtoPA.INSTANCE.value(), oldValue);
	}


	public boolean isValueModified()  {
		return value_m;
	}


	public static PropertyAccessor<DateTimeParameterInstanceDto, Date> getValuePropertyAccessor()  {
		return value_pa;
	}


	@Override
	public int hashCode()  {
		if(null == getId())
			return super.hashCode();
		return getId().hashCode();
	}

	@Override
	public boolean equals(Object obj)  {
		if(! (obj instanceof DateTimeParameterInstanceDto))
			return false;

		if(null == getId())
			return super.equals(obj);
		return getId().equals(((DateTimeParameterInstanceDto)obj).getId());
	}

	@Override
	public String toString()  {
		return getClass().getName() + ": " + getId();
	}

	public static Dto2PosoMapper newPosoMapper()  {
		return new DateTimeParameterInstanceDto2PosoMap();
	}

	public DateTimeParameterInstanceDtoPA instantiatePropertyAccess()  {
		return GWT.create(DateTimeParameterInstanceDtoPA.class);
	}

	public void clearModified()  {
		this.formula = null;
		this.formula_m = false;
		this.strValue = null;
		this.strValue_m = false;
		this.value = null;
		this.value_m = false;
	}


	public boolean isModified()  {
		if(super.isModified())
			return true;
		if(formula_m)
			return true;
		if(strValue_m)
			return true;
		if(value_m)
			return true;
		return false;
	}


	public List<PropertyAccessor> getPropertyAccessors()  {
		List<PropertyAccessor> list = super.getPropertyAccessors();
		list.add(formula_pa);
		list.add(strValue_pa);
		list.add(value_pa);
		return list;
	}


	public List<PropertyAccessor> getModifiedPropertyAccessors()  {
		List<PropertyAccessor> list = super.getModifiedPropertyAccessors();
		if(formula_m)
			list.add(formula_pa);
		if(strValue_m)
			list.add(strValue_pa);
		if(value_m)
			list.add(value_pa);
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsByView(net.datenwerke.gxtdto.client.dtomanager.DtoView view)  {
		List<PropertyAccessor> list = super.getPropertyAccessorsByView(view);
		if(view.compareTo(DtoView.NORMAL) >= 0){
			list.add(formula_pa);
			list.add(strValue_pa);
			list.add(value_pa);
		}
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsForDtos()  {
		List<PropertyAccessor> list = super.getPropertyAccessorsForDtos();
		return list;
	}




}
