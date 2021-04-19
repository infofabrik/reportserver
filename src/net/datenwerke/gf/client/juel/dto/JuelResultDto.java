package net.datenwerke.gf.client.juel.dto;

import com.google.gwt.core.client.GWT;
import java.lang.Boolean;
import java.lang.Double;
import java.lang.Float;
import java.lang.Integer;
import java.lang.Long;
import java.lang.String;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.gf.base.client.dtogenerator.RsDto;
import net.datenwerke.gf.client.juel.dto.JuelResultTypeDto;
import net.datenwerke.gf.client.juel.dto.pa.JuelResultDtoPA;
import net.datenwerke.gf.client.juel.dto.posomap.JuelResultDto2PosoMap;
import net.datenwerke.gf.service.juel.JuelResult;
import net.datenwerke.gxtdto.client.dtomanager.Dto2PosoMapper;
import net.datenwerke.gxtdto.client.dtomanager.DtoView;
import net.datenwerke.gxtdto.client.dtomanager.PropertyAccessor;
import net.datenwerke.gxtdto.client.dtomanager.redoundo.ChangeTracker;

/**
 * Dto for {@link JuelResult}
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
abstract public class JuelResultDto extends RsDto {


	private static final long serialVersionUID = 1;


	/* Fields */
	private Boolean booleanValue;
	private  boolean booleanValue_m;
	public static final String PROPERTY_BOOLEAN_VALUE = "dpi-juelresult-booleanvalue";

	private transient static PropertyAccessor<JuelResultDto, Boolean> booleanValue_pa = new PropertyAccessor<JuelResultDto, Boolean>() {
		@Override
		public void setValue(JuelResultDto container, Boolean object) {
			container.setBooleanValue(object);
		}

		@Override
		public Boolean getValue(JuelResultDto container) {
			return container.isBooleanValue();
		}

		@Override
		public Class<?> getType() {
			return Boolean.class;
		}

		@Override
		public String getPath() {
			return "booleanValue";
		}

		@Override
		public void setModified(JuelResultDto container, boolean modified) {
			container.booleanValue_m = modified;
		}

		@Override
		public boolean isModified(JuelResultDto container) {
			return container.isBooleanValueModified();
		}
	};

	private Date dateValue;
	private  boolean dateValue_m;
	public static final String PROPERTY_DATE_VALUE = "dpi-juelresult-datevalue";

	private transient static PropertyAccessor<JuelResultDto, Date> dateValue_pa = new PropertyAccessor<JuelResultDto, Date>() {
		@Override
		public void setValue(JuelResultDto container, Date object) {
			container.setDateValue(object);
		}

		@Override
		public Date getValue(JuelResultDto container) {
			return container.getDateValue();
		}

		@Override
		public Class<?> getType() {
			return Date.class;
		}

		@Override
		public String getPath() {
			return "dateValue";
		}

		@Override
		public void setModified(JuelResultDto container, boolean modified) {
			container.dateValue_m = modified;
		}

		@Override
		public boolean isModified(JuelResultDto container) {
			return container.isDateValueModified();
		}
	};

	private BigDecimal decimalValue;
	private  boolean decimalValue_m;
	public static final String PROPERTY_DECIMAL_VALUE = "dpi-juelresult-decimalvalue";

	private transient static PropertyAccessor<JuelResultDto, BigDecimal> decimalValue_pa = new PropertyAccessor<JuelResultDto, BigDecimal>() {
		@Override
		public void setValue(JuelResultDto container, BigDecimal object) {
			container.setDecimalValue(object);
		}

		@Override
		public BigDecimal getValue(JuelResultDto container) {
			return container.getDecimalValue();
		}

		@Override
		public Class<?> getType() {
			return BigDecimal.class;
		}

		@Override
		public String getPath() {
			return "decimalValue";
		}

		@Override
		public void setModified(JuelResultDto container, boolean modified) {
			container.decimalValue_m = modified;
		}

		@Override
		public boolean isModified(JuelResultDto container) {
			return container.isDecimalValueModified();
		}
	};

	private Double doubleValue;
	private  boolean doubleValue_m;
	public static final String PROPERTY_DOUBLE_VALUE = "dpi-juelresult-doublevalue";

	private transient static PropertyAccessor<JuelResultDto, Double> doubleValue_pa = new PropertyAccessor<JuelResultDto, Double>() {
		@Override
		public void setValue(JuelResultDto container, Double object) {
			container.setDoubleValue(object);
		}

		@Override
		public Double getValue(JuelResultDto container) {
			return container.getDoubleValue();
		}

		@Override
		public Class<?> getType() {
			return Double.class;
		}

		@Override
		public String getPath() {
			return "doubleValue";
		}

		@Override
		public void setModified(JuelResultDto container, boolean modified) {
			container.doubleValue_m = modified;
		}

		@Override
		public boolean isModified(JuelResultDto container) {
			return container.isDoubleValueModified();
		}
	};

	private Boolean entryNull;
	private  boolean entryNull_m;
	public static final String PROPERTY_ENTRY_NULL = "dpi-juelresult-entrynull";

	private transient static PropertyAccessor<JuelResultDto, Boolean> entryNull_pa = new PropertyAccessor<JuelResultDto, Boolean>() {
		@Override
		public void setValue(JuelResultDto container, Boolean object) {
			container.setEntryNull(object);
		}

		@Override
		public Boolean getValue(JuelResultDto container) {
			return container.isEntryNull();
		}

		@Override
		public Class<?> getType() {
			return Boolean.class;
		}

		@Override
		public String getPath() {
			return "entryNull";
		}

		@Override
		public void setModified(JuelResultDto container, boolean modified) {
			container.entryNull_m = modified;
		}

		@Override
		public boolean isModified(JuelResultDto container) {
			return container.isEntryNullModified();
		}
	};

	private Float floatValue;
	private  boolean floatValue_m;
	public static final String PROPERTY_FLOAT_VALUE = "dpi-juelresult-floatvalue";

	private transient static PropertyAccessor<JuelResultDto, Float> floatValue_pa = new PropertyAccessor<JuelResultDto, Float>() {
		@Override
		public void setValue(JuelResultDto container, Float object) {
			container.setFloatValue(object);
		}

		@Override
		public Float getValue(JuelResultDto container) {
			return container.getFloatValue();
		}

		@Override
		public Class<?> getType() {
			return Float.class;
		}

		@Override
		public String getPath() {
			return "floatValue";
		}

		@Override
		public void setModified(JuelResultDto container, boolean modified) {
			container.floatValue_m = modified;
		}

		@Override
		public boolean isModified(JuelResultDto container) {
			return container.isFloatValueModified();
		}
	};

	private Integer intValue;
	private  boolean intValue_m;
	public static final String PROPERTY_INT_VALUE = "dpi-juelresult-intvalue";

	private transient static PropertyAccessor<JuelResultDto, Integer> intValue_pa = new PropertyAccessor<JuelResultDto, Integer>() {
		@Override
		public void setValue(JuelResultDto container, Integer object) {
			container.setIntValue(object);
		}

		@Override
		public Integer getValue(JuelResultDto container) {
			return container.getIntValue();
		}

		@Override
		public Class<?> getType() {
			return Integer.class;
		}

		@Override
		public String getPath() {
			return "intValue";
		}

		@Override
		public void setModified(JuelResultDto container, boolean modified) {
			container.intValue_m = modified;
		}

		@Override
		public boolean isModified(JuelResultDto container) {
			return container.isIntValueModified();
		}
	};

	private Long longValue;
	private  boolean longValue_m;
	public static final String PROPERTY_LONG_VALUE = "dpi-juelresult-longvalue";

	private transient static PropertyAccessor<JuelResultDto, Long> longValue_pa = new PropertyAccessor<JuelResultDto, Long>() {
		@Override
		public void setValue(JuelResultDto container, Long object) {
			container.setLongValue(object);
		}

		@Override
		public Long getValue(JuelResultDto container) {
			return container.getLongValue();
		}

		@Override
		public Class<?> getType() {
			return Long.class;
		}

		@Override
		public String getPath() {
			return "longValue";
		}

		@Override
		public void setModified(JuelResultDto container, boolean modified) {
			container.longValue_m = modified;
		}

		@Override
		public boolean isModified(JuelResultDto container) {
			return container.isLongValueModified();
		}
	};

	private String stringValue;
	private  boolean stringValue_m;
	public static final String PROPERTY_STRING_VALUE = "dpi-juelresult-stringvalue";

	private transient static PropertyAccessor<JuelResultDto, String> stringValue_pa = new PropertyAccessor<JuelResultDto, String>() {
		@Override
		public void setValue(JuelResultDto container, String object) {
			container.setStringValue(object);
		}

		@Override
		public String getValue(JuelResultDto container) {
			return container.getStringValue();
		}

		@Override
		public Class<?> getType() {
			return String.class;
		}

		@Override
		public String getPath() {
			return "stringValue";
		}

		@Override
		public void setModified(JuelResultDto container, boolean modified) {
			container.stringValue_m = modified;
		}

		@Override
		public boolean isModified(JuelResultDto container) {
			return container.isStringValueModified();
		}
	};

	private JuelResultTypeDto type;
	private  boolean type_m;
	public static final String PROPERTY_TYPE = "dpi-juelresult-type";

	private transient static PropertyAccessor<JuelResultDto, JuelResultTypeDto> type_pa = new PropertyAccessor<JuelResultDto, JuelResultTypeDto>() {
		@Override
		public void setValue(JuelResultDto container, JuelResultTypeDto object) {
			container.setType(object);
		}

		@Override
		public JuelResultTypeDto getValue(JuelResultDto container) {
			return container.getType();
		}

		@Override
		public Class<?> getType() {
			return JuelResultTypeDto.class;
		}

		@Override
		public String getPath() {
			return "type";
		}

		@Override
		public void setModified(JuelResultDto container, boolean modified) {
			container.type_m = modified;
		}

		@Override
		public boolean isModified(JuelResultDto container) {
			return container.isTypeModified();
		}
	};


	public JuelResultDto() {
		super();
	}

	public Boolean isBooleanValue()  {
		if(! isDtoProxy()){
			return this.booleanValue;
		}

		if(isBooleanValueModified())
			return this.booleanValue;

		if(! GWT.isClient())
			return null;

		Boolean _value = dtoManager.getProperty(this, instantiatePropertyAccess().booleanValue());

		return _value;
	}


	public void setBooleanValue(Boolean booleanValue)  {
		/* old value */
		Boolean oldValue = null;
		if(GWT.isClient())
			oldValue = isBooleanValue();

		/* set new value */
		this.booleanValue = booleanValue;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(booleanValue_pa, oldValue, booleanValue, this.booleanValue_m));

		/* set indicator */
		this.booleanValue_m = true;

		this.fireObjectChangedEvent(JuelResultDtoPA.INSTANCE.booleanValue(), oldValue);
	}


	public boolean isBooleanValueModified()  {
		return booleanValue_m;
	}


	public static PropertyAccessor<JuelResultDto, Boolean> getBooleanValuePropertyAccessor()  {
		return booleanValue_pa;
	}


	public Date getDateValue()  {
		if(! isDtoProxy()){
			return this.dateValue;
		}

		if(isDateValueModified())
			return this.dateValue;

		if(! GWT.isClient())
			return null;

		Date _value = dtoManager.getProperty(this, instantiatePropertyAccess().dateValue());

		return _value;
	}


	public void setDateValue(Date dateValue)  {
		/* old value */
		Date oldValue = null;
		if(GWT.isClient())
			oldValue = getDateValue();

		/* set new value */
		this.dateValue = dateValue;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(dateValue_pa, oldValue, dateValue, this.dateValue_m));

		/* set indicator */
		this.dateValue_m = true;

		this.fireObjectChangedEvent(JuelResultDtoPA.INSTANCE.dateValue(), oldValue);
	}


	public boolean isDateValueModified()  {
		return dateValue_m;
	}


	public static PropertyAccessor<JuelResultDto, Date> getDateValuePropertyAccessor()  {
		return dateValue_pa;
	}


	public BigDecimal getDecimalValue()  {
		if(! isDtoProxy()){
			return this.decimalValue;
		}

		if(isDecimalValueModified())
			return this.decimalValue;

		if(! GWT.isClient())
			return null;

		BigDecimal _value = dtoManager.getProperty(this, instantiatePropertyAccess().decimalValue());

		return _value;
	}


	public void setDecimalValue(BigDecimal decimalValue)  {
		/* old value */
		BigDecimal oldValue = null;
		if(GWT.isClient())
			oldValue = getDecimalValue();

		/* set new value */
		this.decimalValue = decimalValue;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(decimalValue_pa, oldValue, decimalValue, this.decimalValue_m));

		/* set indicator */
		this.decimalValue_m = true;

		this.fireObjectChangedEvent(JuelResultDtoPA.INSTANCE.decimalValue(), oldValue);
	}


	public boolean isDecimalValueModified()  {
		return decimalValue_m;
	}


	public static PropertyAccessor<JuelResultDto, BigDecimal> getDecimalValuePropertyAccessor()  {
		return decimalValue_pa;
	}


	public Double getDoubleValue()  {
		if(! isDtoProxy()){
			return this.doubleValue;
		}

		if(isDoubleValueModified())
			return this.doubleValue;

		if(! GWT.isClient())
			return null;

		Double _value = dtoManager.getProperty(this, instantiatePropertyAccess().doubleValue());

		return _value;
	}


	public void setDoubleValue(Double doubleValue)  {
		/* old value */
		Double oldValue = null;
		if(GWT.isClient())
			oldValue = getDoubleValue();

		/* set new value */
		this.doubleValue = doubleValue;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(doubleValue_pa, oldValue, doubleValue, this.doubleValue_m));

		/* set indicator */
		this.doubleValue_m = true;

		this.fireObjectChangedEvent(JuelResultDtoPA.INSTANCE.doubleValue(), oldValue);
	}


	public boolean isDoubleValueModified()  {
		return doubleValue_m;
	}


	public static PropertyAccessor<JuelResultDto, Double> getDoubleValuePropertyAccessor()  {
		return doubleValue_pa;
	}


	public Boolean isEntryNull()  {
		if(! isDtoProxy()){
			return this.entryNull;
		}

		if(isEntryNullModified())
			return this.entryNull;

		if(! GWT.isClient())
			return null;

		Boolean _value = dtoManager.getProperty(this, instantiatePropertyAccess().entryNull());

		return _value;
	}


	public void setEntryNull(Boolean entryNull)  {
		/* old value */
		Boolean oldValue = null;
		if(GWT.isClient())
			oldValue = isEntryNull();

		/* set new value */
		this.entryNull = entryNull;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(entryNull_pa, oldValue, entryNull, this.entryNull_m));

		/* set indicator */
		this.entryNull_m = true;

		this.fireObjectChangedEvent(JuelResultDtoPA.INSTANCE.entryNull(), oldValue);
	}


	public boolean isEntryNullModified()  {
		return entryNull_m;
	}


	public static PropertyAccessor<JuelResultDto, Boolean> getEntryNullPropertyAccessor()  {
		return entryNull_pa;
	}


	public Float getFloatValue()  {
		if(! isDtoProxy()){
			return this.floatValue;
		}

		if(isFloatValueModified())
			return this.floatValue;

		if(! GWT.isClient())
			return null;

		Float _value = dtoManager.getProperty(this, instantiatePropertyAccess().floatValue());

		return _value;
	}


	public void setFloatValue(Float floatValue)  {
		/* old value */
		Float oldValue = null;
		if(GWT.isClient())
			oldValue = getFloatValue();

		/* set new value */
		this.floatValue = floatValue;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(floatValue_pa, oldValue, floatValue, this.floatValue_m));

		/* set indicator */
		this.floatValue_m = true;

		this.fireObjectChangedEvent(JuelResultDtoPA.INSTANCE.floatValue(), oldValue);
	}


	public boolean isFloatValueModified()  {
		return floatValue_m;
	}


	public static PropertyAccessor<JuelResultDto, Float> getFloatValuePropertyAccessor()  {
		return floatValue_pa;
	}


	public Integer getIntValue()  {
		if(! isDtoProxy()){
			return this.intValue;
		}

		if(isIntValueModified())
			return this.intValue;

		if(! GWT.isClient())
			return null;

		Integer _value = dtoManager.getProperty(this, instantiatePropertyAccess().intValue());

		return _value;
	}


	public void setIntValue(Integer intValue)  {
		/* old value */
		Integer oldValue = null;
		if(GWT.isClient())
			oldValue = getIntValue();

		/* set new value */
		this.intValue = intValue;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(intValue_pa, oldValue, intValue, this.intValue_m));

		/* set indicator */
		this.intValue_m = true;

		this.fireObjectChangedEvent(JuelResultDtoPA.INSTANCE.intValue(), oldValue);
	}


	public boolean isIntValueModified()  {
		return intValue_m;
	}


	public static PropertyAccessor<JuelResultDto, Integer> getIntValuePropertyAccessor()  {
		return intValue_pa;
	}


	public Long getLongValue()  {
		if(! isDtoProxy()){
			return this.longValue;
		}

		if(isLongValueModified())
			return this.longValue;

		if(! GWT.isClient())
			return null;

		Long _value = dtoManager.getProperty(this, instantiatePropertyAccess().longValue());

		return _value;
	}


	public void setLongValue(Long longValue)  {
		/* old value */
		Long oldValue = null;
		if(GWT.isClient())
			oldValue = getLongValue();

		/* set new value */
		this.longValue = longValue;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(longValue_pa, oldValue, longValue, this.longValue_m));

		/* set indicator */
		this.longValue_m = true;

		this.fireObjectChangedEvent(JuelResultDtoPA.INSTANCE.longValue(), oldValue);
	}


	public boolean isLongValueModified()  {
		return longValue_m;
	}


	public static PropertyAccessor<JuelResultDto, Long> getLongValuePropertyAccessor()  {
		return longValue_pa;
	}


	public String getStringValue()  {
		if(! isDtoProxy()){
			return this.stringValue;
		}

		if(isStringValueModified())
			return this.stringValue;

		if(! GWT.isClient())
			return null;

		String _value = dtoManager.getProperty(this, instantiatePropertyAccess().stringValue());

		return _value;
	}


	public void setStringValue(String stringValue)  {
		/* old value */
		String oldValue = null;
		if(GWT.isClient())
			oldValue = getStringValue();

		/* set new value */
		this.stringValue = stringValue;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(stringValue_pa, oldValue, stringValue, this.stringValue_m));

		/* set indicator */
		this.stringValue_m = true;

		this.fireObjectChangedEvent(JuelResultDtoPA.INSTANCE.stringValue(), oldValue);
	}


	public boolean isStringValueModified()  {
		return stringValue_m;
	}


	public static PropertyAccessor<JuelResultDto, String> getStringValuePropertyAccessor()  {
		return stringValue_pa;
	}


	public JuelResultTypeDto getType()  {
		if(! isDtoProxy()){
			return this.type;
		}

		if(isTypeModified())
			return this.type;

		if(! GWT.isClient())
			return null;

		JuelResultTypeDto _value = dtoManager.getProperty(this, instantiatePropertyAccess().type());

		return _value;
	}


	public void setType(JuelResultTypeDto type)  {
		/* old value */
		JuelResultTypeDto oldValue = null;
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

		this.fireObjectChangedEvent(JuelResultDtoPA.INSTANCE.type(), oldValue);
	}


	public boolean isTypeModified()  {
		return type_m;
	}


	public static PropertyAccessor<JuelResultDto, JuelResultTypeDto> getTypePropertyAccessor()  {
		return type_pa;
	}


	@Override
	public String toString()  {
		return super.toString();
	}

	public static Dto2PosoMapper newPosoMapper()  {
		return new JuelResultDto2PosoMap();
	}

	public JuelResultDtoPA instantiatePropertyAccess()  {
		return GWT.create(JuelResultDtoPA.class);
	}

	public void clearModified()  {
		this.booleanValue = null;
		this.booleanValue_m = false;
		this.dateValue = null;
		this.dateValue_m = false;
		this.decimalValue = null;
		this.decimalValue_m = false;
		this.doubleValue = null;
		this.doubleValue_m = false;
		this.entryNull = null;
		this.entryNull_m = false;
		this.floatValue = null;
		this.floatValue_m = false;
		this.intValue = null;
		this.intValue_m = false;
		this.longValue = null;
		this.longValue_m = false;
		this.stringValue = null;
		this.stringValue_m = false;
		this.type = null;
		this.type_m = false;
	}


	public boolean isModified()  {
		if(super.isModified())
			return true;
		if(booleanValue_m)
			return true;
		if(dateValue_m)
			return true;
		if(decimalValue_m)
			return true;
		if(doubleValue_m)
			return true;
		if(entryNull_m)
			return true;
		if(floatValue_m)
			return true;
		if(intValue_m)
			return true;
		if(longValue_m)
			return true;
		if(stringValue_m)
			return true;
		if(type_m)
			return true;
		return false;
	}


	public List<PropertyAccessor> getPropertyAccessors()  {
		List<PropertyAccessor> list = super.getPropertyAccessors();
		list.add(booleanValue_pa);
		list.add(dateValue_pa);
		list.add(decimalValue_pa);
		list.add(doubleValue_pa);
		list.add(entryNull_pa);
		list.add(floatValue_pa);
		list.add(intValue_pa);
		list.add(longValue_pa);
		list.add(stringValue_pa);
		list.add(type_pa);
		return list;
	}


	public List<PropertyAccessor> getModifiedPropertyAccessors()  {
		List<PropertyAccessor> list = super.getModifiedPropertyAccessors();
		if(booleanValue_m)
			list.add(booleanValue_pa);
		if(dateValue_m)
			list.add(dateValue_pa);
		if(decimalValue_m)
			list.add(decimalValue_pa);
		if(doubleValue_m)
			list.add(doubleValue_pa);
		if(entryNull_m)
			list.add(entryNull_pa);
		if(floatValue_m)
			list.add(floatValue_pa);
		if(intValue_m)
			list.add(intValue_pa);
		if(longValue_m)
			list.add(longValue_pa);
		if(stringValue_m)
			list.add(stringValue_pa);
		if(type_m)
			list.add(type_pa);
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsByView(net.datenwerke.gxtdto.client.dtomanager.DtoView view)  {
		List<PropertyAccessor> list = super.getPropertyAccessorsByView(view);
		if(view.compareTo(DtoView.NORMAL) >= 0){
			list.add(booleanValue_pa);
			list.add(dateValue_pa);
			list.add(decimalValue_pa);
			list.add(doubleValue_pa);
			list.add(entryNull_pa);
			list.add(floatValue_pa);
			list.add(intValue_pa);
			list.add(longValue_pa);
			list.add(stringValue_pa);
			list.add(type_pa);
		}
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsForDtos()  {
		List<PropertyAccessor> list = super.getPropertyAccessorsForDtos();
		return list;
	}



	net.datenwerke.gf.client.juel.dto.JuelResultTypeDto wl_0;

}
