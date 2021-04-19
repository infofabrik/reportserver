package net.datenwerke.rs.core.client.i18tools.dto;

import com.google.gwt.core.client.GWT;
import java.lang.String;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.gf.base.client.dtogenerator.RsDto;
import net.datenwerke.gxtdto.client.dtomanager.Dto2PosoMapper;
import net.datenwerke.gxtdto.client.dtomanager.DtoView;
import net.datenwerke.gxtdto.client.dtomanager.PropertyAccessor;
import net.datenwerke.gxtdto.client.dtomanager.redoundo.ChangeTracker;
import net.datenwerke.rs.core.client.i18tools.dto.pa.FormatPatternsDtoPA;
import net.datenwerke.rs.core.client.i18tools.dto.posomap.FormatPatternsDto2PosoMap;
import net.datenwerke.rs.core.service.i18ntools.FormatPatterns;

/**
 * Dto for {@link FormatPatterns}
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class FormatPatternsDto extends RsDto {


	private static final long serialVersionUID = 1;


	/* Fields */
	private String currencyPattern;
	private  boolean currencyPattern_m;
	public static final String PROPERTY_CURRENCY_PATTERN = "dpi-formatpatterns-currencypattern";

	private transient static PropertyAccessor<FormatPatternsDto, String> currencyPattern_pa = new PropertyAccessor<FormatPatternsDto, String>() {
		@Override
		public void setValue(FormatPatternsDto container, String object) {
			container.setCurrencyPattern(object);
		}

		@Override
		public String getValue(FormatPatternsDto container) {
			return container.getCurrencyPattern();
		}

		@Override
		public Class<?> getType() {
			return String.class;
		}

		@Override
		public String getPath() {
			return "currencyPattern";
		}

		@Override
		public void setModified(FormatPatternsDto container, boolean modified) {
			container.currencyPattern_m = modified;
		}

		@Override
		public boolean isModified(FormatPatternsDto container) {
			return container.isCurrencyPatternModified();
		}
	};

	private String integerPattern;
	private  boolean integerPattern_m;
	public static final String PROPERTY_INTEGER_PATTERN = "dpi-formatpatterns-integerpattern";

	private transient static PropertyAccessor<FormatPatternsDto, String> integerPattern_pa = new PropertyAccessor<FormatPatternsDto, String>() {
		@Override
		public void setValue(FormatPatternsDto container, String object) {
			container.setIntegerPattern(object);
		}

		@Override
		public String getValue(FormatPatternsDto container) {
			return container.getIntegerPattern();
		}

		@Override
		public Class<?> getType() {
			return String.class;
		}

		@Override
		public String getPath() {
			return "integerPattern";
		}

		@Override
		public void setModified(FormatPatternsDto container, boolean modified) {
			container.integerPattern_m = modified;
		}

		@Override
		public boolean isModified(FormatPatternsDto container) {
			return container.isIntegerPatternModified();
		}
	};

	private String longDatePattern;
	private  boolean longDatePattern_m;
	public static final String PROPERTY_LONG_DATE_PATTERN = "dpi-formatpatterns-longdatepattern";

	private transient static PropertyAccessor<FormatPatternsDto, String> longDatePattern_pa = new PropertyAccessor<FormatPatternsDto, String>() {
		@Override
		public void setValue(FormatPatternsDto container, String object) {
			container.setLongDatePattern(object);
		}

		@Override
		public String getValue(FormatPatternsDto container) {
			return container.getLongDatePattern();
		}

		@Override
		public Class<?> getType() {
			return String.class;
		}

		@Override
		public String getPath() {
			return "longDatePattern";
		}

		@Override
		public void setModified(FormatPatternsDto container, boolean modified) {
			container.longDatePattern_m = modified;
		}

		@Override
		public boolean isModified(FormatPatternsDto container) {
			return container.isLongDatePatternModified();
		}
	};

	private String longDateTimePattern;
	private  boolean longDateTimePattern_m;
	public static final String PROPERTY_LONG_DATE_TIME_PATTERN = "dpi-formatpatterns-longdatetimepattern";

	private transient static PropertyAccessor<FormatPatternsDto, String> longDateTimePattern_pa = new PropertyAccessor<FormatPatternsDto, String>() {
		@Override
		public void setValue(FormatPatternsDto container, String object) {
			container.setLongDateTimePattern(object);
		}

		@Override
		public String getValue(FormatPatternsDto container) {
			return container.getLongDateTimePattern();
		}

		@Override
		public Class<?> getType() {
			return String.class;
		}

		@Override
		public String getPath() {
			return "longDateTimePattern";
		}

		@Override
		public void setModified(FormatPatternsDto container, boolean modified) {
			container.longDateTimePattern_m = modified;
		}

		@Override
		public boolean isModified(FormatPatternsDto container) {
			return container.isLongDateTimePatternModified();
		}
	};

	private String longTimePattern;
	private  boolean longTimePattern_m;
	public static final String PROPERTY_LONG_TIME_PATTERN = "dpi-formatpatterns-longtimepattern";

	private transient static PropertyAccessor<FormatPatternsDto, String> longTimePattern_pa = new PropertyAccessor<FormatPatternsDto, String>() {
		@Override
		public void setValue(FormatPatternsDto container, String object) {
			container.setLongTimePattern(object);
		}

		@Override
		public String getValue(FormatPatternsDto container) {
			return container.getLongTimePattern();
		}

		@Override
		public Class<?> getType() {
			return String.class;
		}

		@Override
		public String getPath() {
			return "longTimePattern";
		}

		@Override
		public void setModified(FormatPatternsDto container, boolean modified) {
			container.longTimePattern_m = modified;
		}

		@Override
		public boolean isModified(FormatPatternsDto container) {
			return container.isLongTimePatternModified();
		}
	};

	private String numberPattern;
	private  boolean numberPattern_m;
	public static final String PROPERTY_NUMBER_PATTERN = "dpi-formatpatterns-numberpattern";

	private transient static PropertyAccessor<FormatPatternsDto, String> numberPattern_pa = new PropertyAccessor<FormatPatternsDto, String>() {
		@Override
		public void setValue(FormatPatternsDto container, String object) {
			container.setNumberPattern(object);
		}

		@Override
		public String getValue(FormatPatternsDto container) {
			return container.getNumberPattern();
		}

		@Override
		public Class<?> getType() {
			return String.class;
		}

		@Override
		public String getPath() {
			return "numberPattern";
		}

		@Override
		public void setModified(FormatPatternsDto container, boolean modified) {
			container.numberPattern_m = modified;
		}

		@Override
		public boolean isModified(FormatPatternsDto container) {
			return container.isNumberPatternModified();
		}
	};

	private String percentPattern;
	private  boolean percentPattern_m;
	public static final String PROPERTY_PERCENT_PATTERN = "dpi-formatpatterns-percentpattern";

	private transient static PropertyAccessor<FormatPatternsDto, String> percentPattern_pa = new PropertyAccessor<FormatPatternsDto, String>() {
		@Override
		public void setValue(FormatPatternsDto container, String object) {
			container.setPercentPattern(object);
		}

		@Override
		public String getValue(FormatPatternsDto container) {
			return container.getPercentPattern();
		}

		@Override
		public Class<?> getType() {
			return String.class;
		}

		@Override
		public String getPath() {
			return "percentPattern";
		}

		@Override
		public void setModified(FormatPatternsDto container, boolean modified) {
			container.percentPattern_m = modified;
		}

		@Override
		public boolean isModified(FormatPatternsDto container) {
			return container.isPercentPatternModified();
		}
	};

	private String shortDatePattern;
	private  boolean shortDatePattern_m;
	public static final String PROPERTY_SHORT_DATE_PATTERN = "dpi-formatpatterns-shortdatepattern";

	private transient static PropertyAccessor<FormatPatternsDto, String> shortDatePattern_pa = new PropertyAccessor<FormatPatternsDto, String>() {
		@Override
		public void setValue(FormatPatternsDto container, String object) {
			container.setShortDatePattern(object);
		}

		@Override
		public String getValue(FormatPatternsDto container) {
			return container.getShortDatePattern();
		}

		@Override
		public Class<?> getType() {
			return String.class;
		}

		@Override
		public String getPath() {
			return "shortDatePattern";
		}

		@Override
		public void setModified(FormatPatternsDto container, boolean modified) {
			container.shortDatePattern_m = modified;
		}

		@Override
		public boolean isModified(FormatPatternsDto container) {
			return container.isShortDatePatternModified();
		}
	};

	private String shortDateTimePattern;
	private  boolean shortDateTimePattern_m;
	public static final String PROPERTY_SHORT_DATE_TIME_PATTERN = "dpi-formatpatterns-shortdatetimepattern";

	private transient static PropertyAccessor<FormatPatternsDto, String> shortDateTimePattern_pa = new PropertyAccessor<FormatPatternsDto, String>() {
		@Override
		public void setValue(FormatPatternsDto container, String object) {
			container.setShortDateTimePattern(object);
		}

		@Override
		public String getValue(FormatPatternsDto container) {
			return container.getShortDateTimePattern();
		}

		@Override
		public Class<?> getType() {
			return String.class;
		}

		@Override
		public String getPath() {
			return "shortDateTimePattern";
		}

		@Override
		public void setModified(FormatPatternsDto container, boolean modified) {
			container.shortDateTimePattern_m = modified;
		}

		@Override
		public boolean isModified(FormatPatternsDto container) {
			return container.isShortDateTimePatternModified();
		}
	};

	private String shortTimePattern;
	private  boolean shortTimePattern_m;
	public static final String PROPERTY_SHORT_TIME_PATTERN = "dpi-formatpatterns-shorttimepattern";

	private transient static PropertyAccessor<FormatPatternsDto, String> shortTimePattern_pa = new PropertyAccessor<FormatPatternsDto, String>() {
		@Override
		public void setValue(FormatPatternsDto container, String object) {
			container.setShortTimePattern(object);
		}

		@Override
		public String getValue(FormatPatternsDto container) {
			return container.getShortTimePattern();
		}

		@Override
		public Class<?> getType() {
			return String.class;
		}

		@Override
		public String getPath() {
			return "shortTimePattern";
		}

		@Override
		public void setModified(FormatPatternsDto container, boolean modified) {
			container.shortTimePattern_m = modified;
		}

		@Override
		public boolean isModified(FormatPatternsDto container) {
			return container.isShortTimePatternModified();
		}
	};


	public FormatPatternsDto() {
		super();
	}

	public String getCurrencyPattern()  {
		if(! isDtoProxy()){
			return this.currencyPattern;
		}

		if(isCurrencyPatternModified())
			return this.currencyPattern;

		if(! GWT.isClient())
			return null;

		String _value = dtoManager.getProperty(this, instantiatePropertyAccess().currencyPattern());

		return _value;
	}


	public void setCurrencyPattern(String currencyPattern)  {
		/* old value */
		String oldValue = null;
		if(GWT.isClient())
			oldValue = getCurrencyPattern();

		/* set new value */
		this.currencyPattern = currencyPattern;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(currencyPattern_pa, oldValue, currencyPattern, this.currencyPattern_m));

		/* set indicator */
		this.currencyPattern_m = true;

		this.fireObjectChangedEvent(FormatPatternsDtoPA.INSTANCE.currencyPattern(), oldValue);
	}


	public boolean isCurrencyPatternModified()  {
		return currencyPattern_m;
	}


	public static PropertyAccessor<FormatPatternsDto, String> getCurrencyPatternPropertyAccessor()  {
		return currencyPattern_pa;
	}


	public String getIntegerPattern()  {
		if(! isDtoProxy()){
			return this.integerPattern;
		}

		if(isIntegerPatternModified())
			return this.integerPattern;

		if(! GWT.isClient())
			return null;

		String _value = dtoManager.getProperty(this, instantiatePropertyAccess().integerPattern());

		return _value;
	}


	public void setIntegerPattern(String integerPattern)  {
		/* old value */
		String oldValue = null;
		if(GWT.isClient())
			oldValue = getIntegerPattern();

		/* set new value */
		this.integerPattern = integerPattern;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(integerPattern_pa, oldValue, integerPattern, this.integerPattern_m));

		/* set indicator */
		this.integerPattern_m = true;

		this.fireObjectChangedEvent(FormatPatternsDtoPA.INSTANCE.integerPattern(), oldValue);
	}


	public boolean isIntegerPatternModified()  {
		return integerPattern_m;
	}


	public static PropertyAccessor<FormatPatternsDto, String> getIntegerPatternPropertyAccessor()  {
		return integerPattern_pa;
	}


	public String getLongDatePattern()  {
		if(! isDtoProxy()){
			return this.longDatePattern;
		}

		if(isLongDatePatternModified())
			return this.longDatePattern;

		if(! GWT.isClient())
			return null;

		String _value = dtoManager.getProperty(this, instantiatePropertyAccess().longDatePattern());

		return _value;
	}


	public void setLongDatePattern(String longDatePattern)  {
		/* old value */
		String oldValue = null;
		if(GWT.isClient())
			oldValue = getLongDatePattern();

		/* set new value */
		this.longDatePattern = longDatePattern;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(longDatePattern_pa, oldValue, longDatePattern, this.longDatePattern_m));

		/* set indicator */
		this.longDatePattern_m = true;

		this.fireObjectChangedEvent(FormatPatternsDtoPA.INSTANCE.longDatePattern(), oldValue);
	}


	public boolean isLongDatePatternModified()  {
		return longDatePattern_m;
	}


	public static PropertyAccessor<FormatPatternsDto, String> getLongDatePatternPropertyAccessor()  {
		return longDatePattern_pa;
	}


	public String getLongDateTimePattern()  {
		if(! isDtoProxy()){
			return this.longDateTimePattern;
		}

		if(isLongDateTimePatternModified())
			return this.longDateTimePattern;

		if(! GWT.isClient())
			return null;

		String _value = dtoManager.getProperty(this, instantiatePropertyAccess().longDateTimePattern());

		return _value;
	}


	public void setLongDateTimePattern(String longDateTimePattern)  {
		/* old value */
		String oldValue = null;
		if(GWT.isClient())
			oldValue = getLongDateTimePattern();

		/* set new value */
		this.longDateTimePattern = longDateTimePattern;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(longDateTimePattern_pa, oldValue, longDateTimePattern, this.longDateTimePattern_m));

		/* set indicator */
		this.longDateTimePattern_m = true;

		this.fireObjectChangedEvent(FormatPatternsDtoPA.INSTANCE.longDateTimePattern(), oldValue);
	}


	public boolean isLongDateTimePatternModified()  {
		return longDateTimePattern_m;
	}


	public static PropertyAccessor<FormatPatternsDto, String> getLongDateTimePatternPropertyAccessor()  {
		return longDateTimePattern_pa;
	}


	public String getLongTimePattern()  {
		if(! isDtoProxy()){
			return this.longTimePattern;
		}

		if(isLongTimePatternModified())
			return this.longTimePattern;

		if(! GWT.isClient())
			return null;

		String _value = dtoManager.getProperty(this, instantiatePropertyAccess().longTimePattern());

		return _value;
	}


	public void setLongTimePattern(String longTimePattern)  {
		/* old value */
		String oldValue = null;
		if(GWT.isClient())
			oldValue = getLongTimePattern();

		/* set new value */
		this.longTimePattern = longTimePattern;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(longTimePattern_pa, oldValue, longTimePattern, this.longTimePattern_m));

		/* set indicator */
		this.longTimePattern_m = true;

		this.fireObjectChangedEvent(FormatPatternsDtoPA.INSTANCE.longTimePattern(), oldValue);
	}


	public boolean isLongTimePatternModified()  {
		return longTimePattern_m;
	}


	public static PropertyAccessor<FormatPatternsDto, String> getLongTimePatternPropertyAccessor()  {
		return longTimePattern_pa;
	}


	public String getNumberPattern()  {
		if(! isDtoProxy()){
			return this.numberPattern;
		}

		if(isNumberPatternModified())
			return this.numberPattern;

		if(! GWT.isClient())
			return null;

		String _value = dtoManager.getProperty(this, instantiatePropertyAccess().numberPattern());

		return _value;
	}


	public void setNumberPattern(String numberPattern)  {
		/* old value */
		String oldValue = null;
		if(GWT.isClient())
			oldValue = getNumberPattern();

		/* set new value */
		this.numberPattern = numberPattern;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(numberPattern_pa, oldValue, numberPattern, this.numberPattern_m));

		/* set indicator */
		this.numberPattern_m = true;

		this.fireObjectChangedEvent(FormatPatternsDtoPA.INSTANCE.numberPattern(), oldValue);
	}


	public boolean isNumberPatternModified()  {
		return numberPattern_m;
	}


	public static PropertyAccessor<FormatPatternsDto, String> getNumberPatternPropertyAccessor()  {
		return numberPattern_pa;
	}


	public String getPercentPattern()  {
		if(! isDtoProxy()){
			return this.percentPattern;
		}

		if(isPercentPatternModified())
			return this.percentPattern;

		if(! GWT.isClient())
			return null;

		String _value = dtoManager.getProperty(this, instantiatePropertyAccess().percentPattern());

		return _value;
	}


	public void setPercentPattern(String percentPattern)  {
		/* old value */
		String oldValue = null;
		if(GWT.isClient())
			oldValue = getPercentPattern();

		/* set new value */
		this.percentPattern = percentPattern;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(percentPattern_pa, oldValue, percentPattern, this.percentPattern_m));

		/* set indicator */
		this.percentPattern_m = true;

		this.fireObjectChangedEvent(FormatPatternsDtoPA.INSTANCE.percentPattern(), oldValue);
	}


	public boolean isPercentPatternModified()  {
		return percentPattern_m;
	}


	public static PropertyAccessor<FormatPatternsDto, String> getPercentPatternPropertyAccessor()  {
		return percentPattern_pa;
	}


	public String getShortDatePattern()  {
		if(! isDtoProxy()){
			return this.shortDatePattern;
		}

		if(isShortDatePatternModified())
			return this.shortDatePattern;

		if(! GWT.isClient())
			return null;

		String _value = dtoManager.getProperty(this, instantiatePropertyAccess().shortDatePattern());

		return _value;
	}


	public void setShortDatePattern(String shortDatePattern)  {
		/* old value */
		String oldValue = null;
		if(GWT.isClient())
			oldValue = getShortDatePattern();

		/* set new value */
		this.shortDatePattern = shortDatePattern;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(shortDatePattern_pa, oldValue, shortDatePattern, this.shortDatePattern_m));

		/* set indicator */
		this.shortDatePattern_m = true;

		this.fireObjectChangedEvent(FormatPatternsDtoPA.INSTANCE.shortDatePattern(), oldValue);
	}


	public boolean isShortDatePatternModified()  {
		return shortDatePattern_m;
	}


	public static PropertyAccessor<FormatPatternsDto, String> getShortDatePatternPropertyAccessor()  {
		return shortDatePattern_pa;
	}


	public String getShortDateTimePattern()  {
		if(! isDtoProxy()){
			return this.shortDateTimePattern;
		}

		if(isShortDateTimePatternModified())
			return this.shortDateTimePattern;

		if(! GWT.isClient())
			return null;

		String _value = dtoManager.getProperty(this, instantiatePropertyAccess().shortDateTimePattern());

		return _value;
	}


	public void setShortDateTimePattern(String shortDateTimePattern)  {
		/* old value */
		String oldValue = null;
		if(GWT.isClient())
			oldValue = getShortDateTimePattern();

		/* set new value */
		this.shortDateTimePattern = shortDateTimePattern;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(shortDateTimePattern_pa, oldValue, shortDateTimePattern, this.shortDateTimePattern_m));

		/* set indicator */
		this.shortDateTimePattern_m = true;

		this.fireObjectChangedEvent(FormatPatternsDtoPA.INSTANCE.shortDateTimePattern(), oldValue);
	}


	public boolean isShortDateTimePatternModified()  {
		return shortDateTimePattern_m;
	}


	public static PropertyAccessor<FormatPatternsDto, String> getShortDateTimePatternPropertyAccessor()  {
		return shortDateTimePattern_pa;
	}


	public String getShortTimePattern()  {
		if(! isDtoProxy()){
			return this.shortTimePattern;
		}

		if(isShortTimePatternModified())
			return this.shortTimePattern;

		if(! GWT.isClient())
			return null;

		String _value = dtoManager.getProperty(this, instantiatePropertyAccess().shortTimePattern());

		return _value;
	}


	public void setShortTimePattern(String shortTimePattern)  {
		/* old value */
		String oldValue = null;
		if(GWT.isClient())
			oldValue = getShortTimePattern();

		/* set new value */
		this.shortTimePattern = shortTimePattern;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(shortTimePattern_pa, oldValue, shortTimePattern, this.shortTimePattern_m));

		/* set indicator */
		this.shortTimePattern_m = true;

		this.fireObjectChangedEvent(FormatPatternsDtoPA.INSTANCE.shortTimePattern(), oldValue);
	}


	public boolean isShortTimePatternModified()  {
		return shortTimePattern_m;
	}


	public static PropertyAccessor<FormatPatternsDto, String> getShortTimePatternPropertyAccessor()  {
		return shortTimePattern_pa;
	}


	@Override
	public String toString()  {
		return super.toString();
	}

	public static Dto2PosoMapper newPosoMapper()  {
		return new FormatPatternsDto2PosoMap();
	}

	public FormatPatternsDtoPA instantiatePropertyAccess()  {
		return GWT.create(FormatPatternsDtoPA.class);
	}

	public void clearModified()  {
		this.currencyPattern = null;
		this.currencyPattern_m = false;
		this.integerPattern = null;
		this.integerPattern_m = false;
		this.longDatePattern = null;
		this.longDatePattern_m = false;
		this.longDateTimePattern = null;
		this.longDateTimePattern_m = false;
		this.longTimePattern = null;
		this.longTimePattern_m = false;
		this.numberPattern = null;
		this.numberPattern_m = false;
		this.percentPattern = null;
		this.percentPattern_m = false;
		this.shortDatePattern = null;
		this.shortDatePattern_m = false;
		this.shortDateTimePattern = null;
		this.shortDateTimePattern_m = false;
		this.shortTimePattern = null;
		this.shortTimePattern_m = false;
	}


	public boolean isModified()  {
		if(super.isModified())
			return true;
		if(currencyPattern_m)
			return true;
		if(integerPattern_m)
			return true;
		if(longDatePattern_m)
			return true;
		if(longDateTimePattern_m)
			return true;
		if(longTimePattern_m)
			return true;
		if(numberPattern_m)
			return true;
		if(percentPattern_m)
			return true;
		if(shortDatePattern_m)
			return true;
		if(shortDateTimePattern_m)
			return true;
		if(shortTimePattern_m)
			return true;
		return false;
	}


	public List<PropertyAccessor> getPropertyAccessors()  {
		List<PropertyAccessor> list = super.getPropertyAccessors();
		list.add(currencyPattern_pa);
		list.add(integerPattern_pa);
		list.add(longDatePattern_pa);
		list.add(longDateTimePattern_pa);
		list.add(longTimePattern_pa);
		list.add(numberPattern_pa);
		list.add(percentPattern_pa);
		list.add(shortDatePattern_pa);
		list.add(shortDateTimePattern_pa);
		list.add(shortTimePattern_pa);
		return list;
	}


	public List<PropertyAccessor> getModifiedPropertyAccessors()  {
		List<PropertyAccessor> list = super.getModifiedPropertyAccessors();
		if(currencyPattern_m)
			list.add(currencyPattern_pa);
		if(integerPattern_m)
			list.add(integerPattern_pa);
		if(longDatePattern_m)
			list.add(longDatePattern_pa);
		if(longDateTimePattern_m)
			list.add(longDateTimePattern_pa);
		if(longTimePattern_m)
			list.add(longTimePattern_pa);
		if(numberPattern_m)
			list.add(numberPattern_pa);
		if(percentPattern_m)
			list.add(percentPattern_pa);
		if(shortDatePattern_m)
			list.add(shortDatePattern_pa);
		if(shortDateTimePattern_m)
			list.add(shortDateTimePattern_pa);
		if(shortTimePattern_m)
			list.add(shortTimePattern_pa);
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsByView(net.datenwerke.gxtdto.client.dtomanager.DtoView view)  {
		List<PropertyAccessor> list = super.getPropertyAccessorsByView(view);
		if(view.compareTo(DtoView.NORMAL) >= 0){
			list.add(currencyPattern_pa);
			list.add(integerPattern_pa);
			list.add(longDatePattern_pa);
			list.add(longDateTimePattern_pa);
			list.add(longTimePattern_pa);
			list.add(numberPattern_pa);
			list.add(percentPattern_pa);
			list.add(shortDatePattern_pa);
			list.add(shortDateTimePattern_pa);
			list.add(shortTimePattern_pa);
		}
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsForDtos()  {
		List<PropertyAccessor> list = super.getPropertyAccessorsForDtos();
		return list;
	}




}
