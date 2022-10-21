package net.datenwerke.rs.base.client.parameters.datetime.dto;

import com.google.gwt.core.client.GWT;
import java.lang.Boolean;
import java.lang.NullPointerException;
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
import net.datenwerke.gxtdto.client.locale.BaseMessages;
import net.datenwerke.rs.base.client.parameters.datetime.dto.ModeDto;
import net.datenwerke.rs.base.client.parameters.datetime.dto.pa.DateTimeParameterDefinitionDtoPA;
import net.datenwerke.rs.base.client.parameters.datetime.dto.posomap.DateTimeParameterDefinitionDto2PosoMap;
import net.datenwerke.rs.base.client.parameters.locale.RsMessages;
import net.datenwerke.rs.base.service.parameters.datetime.DateTimeParameterDefinition;
import net.datenwerke.rs.core.client.parameters.dto.decorator.ParameterDefinitionDtoDec;

/**
 * Dto for {@link DateTimeParameterDefinition}
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
abstract public class DateTimeParameterDefinitionDto extends ParameterDefinitionDtoDec {


	private static final long serialVersionUID = 1;


	/* Fields */
	private Date defaultValue;
	private  boolean defaultValue_m;
	public static final String PROPERTY_DEFAULT_VALUE = "dpi-datetimeparameterdefinition-defaultvalue";

	private transient static PropertyAccessor<DateTimeParameterDefinitionDto, Date> defaultValue_pa = new PropertyAccessor<DateTimeParameterDefinitionDto, Date>() {
		@Override
		public void setValue(DateTimeParameterDefinitionDto container, Date object) {
			container.setDefaultValue(object);
		}

		@Override
		public Date getValue(DateTimeParameterDefinitionDto container) {
			return container.getDefaultValue();
		}

		@Override
		public Class<?> getType() {
			return Date.class;
		}

		@Override
		public String getPath() {
			return "defaultValue";
		}

		@Override
		public void setModified(DateTimeParameterDefinitionDto container, boolean modified) {
			container.defaultValue_m = modified;
		}

		@Override
		public boolean isModified(DateTimeParameterDefinitionDto container) {
			return container.isDefaultValueModified();
		}
	};

	private String formula;
	private  boolean formula_m;
	public static final String PROPERTY_FORMULA = "dpi-datetimeparameterdefinition-formula";

	private transient static PropertyAccessor<DateTimeParameterDefinitionDto, String> formula_pa = new PropertyAccessor<DateTimeParameterDefinitionDto, String>() {
		@Override
		public void setValue(DateTimeParameterDefinitionDto container, String object) {
			container.setFormula(object);
		}

		@Override
		public String getValue(DateTimeParameterDefinitionDto container) {
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
		public void setModified(DateTimeParameterDefinitionDto container, boolean modified) {
			container.formula_m = modified;
		}

		@Override
		public boolean isModified(DateTimeParameterDefinitionDto container) {
			return container.isFormulaModified();
		}
	};

	private ModeDto mode;
	private  boolean mode_m;
	public static final String PROPERTY_MODE = "dpi-datetimeparameterdefinition-mode";

	private transient static PropertyAccessor<DateTimeParameterDefinitionDto, ModeDto> mode_pa = new PropertyAccessor<DateTimeParameterDefinitionDto, ModeDto>() {
		@Override
		public void setValue(DateTimeParameterDefinitionDto container, ModeDto object) {
			container.setMode(object);
		}

		@Override
		public ModeDto getValue(DateTimeParameterDefinitionDto container) {
			return container.getMode();
		}

		@Override
		public Class<?> getType() {
			return ModeDto.class;
		}

		@Override
		public String getPath() {
			return "mode";
		}

		@Override
		public void setModified(DateTimeParameterDefinitionDto container, boolean modified) {
			container.mode_m = modified;
		}

		@Override
		public boolean isModified(DateTimeParameterDefinitionDto container) {
			return container.isModeModified();
		}
	};

	private Boolean useNowAsDefault;
	private  boolean useNowAsDefault_m;
	public static final String PROPERTY_USE_NOW_AS_DEFAULT = "dpi-datetimeparameterdefinition-usenowasdefault";

	private transient static PropertyAccessor<DateTimeParameterDefinitionDto, Boolean> useNowAsDefault_pa = new PropertyAccessor<DateTimeParameterDefinitionDto, Boolean>() {
		@Override
		public void setValue(DateTimeParameterDefinitionDto container, Boolean object) {
			container.setUseNowAsDefault(object);
		}

		@Override
		public Boolean getValue(DateTimeParameterDefinitionDto container) {
			return container.isUseNowAsDefault();
		}

		@Override
		public Class<?> getType() {
			return Boolean.class;
		}

		@Override
		public String getPath() {
			return "useNowAsDefault";
		}

		@Override
		public void setModified(DateTimeParameterDefinitionDto container, boolean modified) {
			container.useNowAsDefault_m = modified;
		}

		@Override
		public boolean isModified(DateTimeParameterDefinitionDto container) {
			return container.isUseNowAsDefaultModified();
		}
	};


	public DateTimeParameterDefinitionDto() {
		super();
	}

	public Date getDefaultValue()  {
		if(! isDtoProxy()){
			return this.defaultValue;
		}

		if(isDefaultValueModified())
			return this.defaultValue;

		if(! GWT.isClient())
			return null;

		Date _value = dtoManager.getProperty(this, instantiatePropertyAccess().defaultValue());

		return _value;
	}


	public void setDefaultValue(Date defaultValue)  {
		/* old value */
		Date oldValue = null;
		if(GWT.isClient())
			oldValue = getDefaultValue();

		/* set new value */
		this.defaultValue = defaultValue;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(defaultValue_pa, oldValue, defaultValue, this.defaultValue_m));

		/* set indicator */
		this.defaultValue_m = true;

		this.fireObjectChangedEvent(DateTimeParameterDefinitionDtoPA.INSTANCE.defaultValue(), oldValue);
	}


	public boolean isDefaultValueModified()  {
		return defaultValue_m;
	}


	public static PropertyAccessor<DateTimeParameterDefinitionDto, Date> getDefaultValuePropertyAccessor()  {
		return defaultValue_pa;
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

		this.fireObjectChangedEvent(DateTimeParameterDefinitionDtoPA.INSTANCE.formula(), oldValue);
	}


	public boolean isFormulaModified()  {
		return formula_m;
	}


	public static PropertyAccessor<DateTimeParameterDefinitionDto, String> getFormulaPropertyAccessor()  {
		return formula_pa;
	}


	public ModeDto getMode()  {
		if(! isDtoProxy()){
			return this.mode;
		}

		if(isModeModified())
			return this.mode;

		if(! GWT.isClient())
			return null;

		ModeDto _value = dtoManager.getProperty(this, instantiatePropertyAccess().mode());

		return _value;
	}


	public void setMode(ModeDto mode)  {
		/* old value */
		ModeDto oldValue = null;
		if(GWT.isClient())
			oldValue = getMode();

		/* set new value */
		this.mode = mode;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(mode_pa, oldValue, mode, this.mode_m));

		/* set indicator */
		this.mode_m = true;

		this.fireObjectChangedEvent(DateTimeParameterDefinitionDtoPA.INSTANCE.mode(), oldValue);
	}


	public boolean isModeModified()  {
		return mode_m;
	}


	public static PropertyAccessor<DateTimeParameterDefinitionDto, ModeDto> getModePropertyAccessor()  {
		return mode_pa;
	}


	public Boolean isUseNowAsDefault()  {
		if(! isDtoProxy()){
			return this.useNowAsDefault;
		}

		if(isUseNowAsDefaultModified())
			return this.useNowAsDefault;

		if(! GWT.isClient())
			return null;

		Boolean _value = dtoManager.getProperty(this, instantiatePropertyAccess().useNowAsDefault());

		return _value;
	}


	public void setUseNowAsDefault(Boolean useNowAsDefault)  {
		/* old value */
		Boolean oldValue = null;
		if(GWT.isClient())
			oldValue = isUseNowAsDefault();

		/* set new value */
		this.useNowAsDefault = useNowAsDefault;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(useNowAsDefault_pa, oldValue, useNowAsDefault, this.useNowAsDefault_m));

		/* set indicator */
		this.useNowAsDefault_m = true;

		this.fireObjectChangedEvent(DateTimeParameterDefinitionDtoPA.INSTANCE.useNowAsDefault(), oldValue);
	}


	public boolean isUseNowAsDefaultModified()  {
		return useNowAsDefault_m;
	}


	public static PropertyAccessor<DateTimeParameterDefinitionDto, Boolean> getUseNowAsDefaultPropertyAccessor()  {
		return useNowAsDefault_pa;
	}


	@Override
	public String toDisplayTitle()  {
		try{
			return RsMessages.INSTANCE.dateTimeParameterText();
		} catch(NullPointerException e){
			return BaseMessages.INSTANCE.unnamed();
		}
	}

	@Override
	public int hashCode()  {
		if(null == getId())
			return super.hashCode();
		return getId().hashCode();
	}

	@Override
	public boolean equals(Object obj)  {
		if(! (obj instanceof DateTimeParameterDefinitionDto))
			return false;

		if(null == getId())
			return super.equals(obj);
		return getId().equals(((DateTimeParameterDefinitionDto)obj).getId());
	}

	@Override
	public String toString()  {
		return getClass().getName() + ": " + getId();
	}

	public static Dto2PosoMapper newPosoMapper()  {
		return new DateTimeParameterDefinitionDto2PosoMap();
	}

	public DateTimeParameterDefinitionDtoPA instantiatePropertyAccess()  {
		return GWT.create(DateTimeParameterDefinitionDtoPA.class);
	}

	public void clearModified()  {
		this.defaultValue = null;
		this.defaultValue_m = false;
		this.formula = null;
		this.formula_m = false;
		this.mode = null;
		this.mode_m = false;
		this.useNowAsDefault = null;
		this.useNowAsDefault_m = false;
	}


	public boolean isModified()  {
		if(super.isModified())
			return true;
		if(defaultValue_m)
			return true;
		if(formula_m)
			return true;
		if(mode_m)
			return true;
		if(useNowAsDefault_m)
			return true;
		return false;
	}


	public List<PropertyAccessor> getPropertyAccessors()  {
		List<PropertyAccessor> list = super.getPropertyAccessors();
		list.add(defaultValue_pa);
		list.add(formula_pa);
		list.add(mode_pa);
		list.add(useNowAsDefault_pa);
		return list;
	}


	public List<PropertyAccessor> getModifiedPropertyAccessors()  {
		List<PropertyAccessor> list = super.getModifiedPropertyAccessors();
		if(defaultValue_m)
			list.add(defaultValue_pa);
		if(formula_m)
			list.add(formula_pa);
		if(mode_m)
			list.add(mode_pa);
		if(useNowAsDefault_m)
			list.add(useNowAsDefault_pa);
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsByView(net.datenwerke.gxtdto.client.dtomanager.DtoView view)  {
		List<PropertyAccessor> list = super.getPropertyAccessorsByView(view);
		if(view.compareTo(DtoView.NORMAL) >= 0){
			list.add(defaultValue_pa);
			list.add(formula_pa);
			list.add(mode_pa);
			list.add(useNowAsDefault_pa);
		}
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsForDtos()  {
		List<PropertyAccessor> list = super.getPropertyAccessorsForDtos();
		return list;
	}



	net.datenwerke.rs.base.client.parameters.datetime.dto.ModeDto wl_0;

}
