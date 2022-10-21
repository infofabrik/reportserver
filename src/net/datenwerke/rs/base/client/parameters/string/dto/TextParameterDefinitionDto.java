package net.datenwerke.rs.base.client.parameters.string.dto;

import com.google.gwt.core.client.GWT;
import java.lang.Integer;
import java.lang.NullPointerException;
import java.lang.String;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.gxtdto.client.dtomanager.Dto2PosoMapper;
import net.datenwerke.gxtdto.client.dtomanager.DtoView;
import net.datenwerke.gxtdto.client.dtomanager.PropertyAccessor;
import net.datenwerke.gxtdto.client.dtomanager.redoundo.ChangeTracker;
import net.datenwerke.gxtdto.client.locale.BaseMessages;
import net.datenwerke.rs.base.client.parameters.locale.RsMessages;
import net.datenwerke.rs.base.client.parameters.string.dto.pa.TextParameterDefinitionDtoPA;
import net.datenwerke.rs.base.client.parameters.string.dto.posomap.TextParameterDefinitionDto2PosoMap;
import net.datenwerke.rs.base.service.parameters.string.TextParameterDefinition;
import net.datenwerke.rs.core.client.parameters.dto.DatatypeDto;
import net.datenwerke.rs.core.client.parameters.dto.decorator.ParameterDefinitionDtoDec;

/**
 * Dto for {@link TextParameterDefinition}
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class TextParameterDefinitionDto extends ParameterDefinitionDtoDec {


	private static final long serialVersionUID = 1;


	/* Fields */
	private String defaultValue;
	private  boolean defaultValue_m;
	public static final String PROPERTY_DEFAULT_VALUE = "dpi-textparameterdefinition-defaultvalue";

	private transient static PropertyAccessor<TextParameterDefinitionDto, String> defaultValue_pa = new PropertyAccessor<TextParameterDefinitionDto, String>() {
		@Override
		public void setValue(TextParameterDefinitionDto container, String object) {
			container.setDefaultValue(object);
		}

		@Override
		public String getValue(TextParameterDefinitionDto container) {
			return container.getDefaultValue();
		}

		@Override
		public Class<?> getType() {
			return String.class;
		}

		@Override
		public String getPath() {
			return "defaultValue";
		}

		@Override
		public void setModified(TextParameterDefinitionDto container, boolean modified) {
			container.defaultValue_m = modified;
		}

		@Override
		public boolean isModified(TextParameterDefinitionDto container) {
			return container.isDefaultValueModified();
		}
	};

	private Integer height;
	private  boolean height_m;
	public static final String PROPERTY_HEIGHT = "dpi-textparameterdefinition-height";

	private transient static PropertyAccessor<TextParameterDefinitionDto, Integer> height_pa = new PropertyAccessor<TextParameterDefinitionDto, Integer>() {
		@Override
		public void setValue(TextParameterDefinitionDto container, Integer object) {
			container.setHeight(object);
		}

		@Override
		public Integer getValue(TextParameterDefinitionDto container) {
			return container.getHeight();
		}

		@Override
		public Class<?> getType() {
			return Integer.class;
		}

		@Override
		public String getPath() {
			return "height";
		}

		@Override
		public void setModified(TextParameterDefinitionDto container, boolean modified) {
			container.height_m = modified;
		}

		@Override
		public boolean isModified(TextParameterDefinitionDto container) {
			return container.isHeightModified();
		}
	};

	private boolean returnNullWhenEmpty;
	private  boolean returnNullWhenEmpty_m;
	public static final String PROPERTY_RETURN_NULL_WHEN_EMPTY = "dpi-textparameterdefinition-returnnullwhenempty";

	private transient static PropertyAccessor<TextParameterDefinitionDto, Boolean> returnNullWhenEmpty_pa = new PropertyAccessor<TextParameterDefinitionDto, Boolean>() {
		@Override
		public void setValue(TextParameterDefinitionDto container, Boolean object) {
			container.setReturnNullWhenEmpty(object);
		}

		@Override
		public Boolean getValue(TextParameterDefinitionDto container) {
			return container.isReturnNullWhenEmpty();
		}

		@Override
		public Class<?> getType() {
			return Boolean.class;
		}

		@Override
		public String getPath() {
			return "returnNullWhenEmpty";
		}

		@Override
		public void setModified(TextParameterDefinitionDto container, boolean modified) {
			container.returnNullWhenEmpty_m = modified;
		}

		@Override
		public boolean isModified(TextParameterDefinitionDto container) {
			return container.isReturnNullWhenEmptyModified();
		}
	};

	private DatatypeDto returnType;
	private  boolean returnType_m;
	public static final String PROPERTY_RETURN_TYPE = "dpi-textparameterdefinition-returntype";

	private transient static PropertyAccessor<TextParameterDefinitionDto, DatatypeDto> returnType_pa = new PropertyAccessor<TextParameterDefinitionDto, DatatypeDto>() {
		@Override
		public void setValue(TextParameterDefinitionDto container, DatatypeDto object) {
			container.setReturnType(object);
		}

		@Override
		public DatatypeDto getValue(TextParameterDefinitionDto container) {
			return container.getReturnType();
		}

		@Override
		public Class<?> getType() {
			return DatatypeDto.class;
		}

		@Override
		public String getPath() {
			return "returnType";
		}

		@Override
		public void setModified(TextParameterDefinitionDto container, boolean modified) {
			container.returnType_m = modified;
		}

		@Override
		public boolean isModified(TextParameterDefinitionDto container) {
			return container.isReturnTypeModified();
		}
	};

	private String validatorRegex;
	private  boolean validatorRegex_m;
	public static final String PROPERTY_VALIDATOR_REGEX = "dpi-textparameterdefinition-validatorregex";

	private transient static PropertyAccessor<TextParameterDefinitionDto, String> validatorRegex_pa = new PropertyAccessor<TextParameterDefinitionDto, String>() {
		@Override
		public void setValue(TextParameterDefinitionDto container, String object) {
			container.setValidatorRegex(object);
		}

		@Override
		public String getValue(TextParameterDefinitionDto container) {
			return container.getValidatorRegex();
		}

		@Override
		public Class<?> getType() {
			return String.class;
		}

		@Override
		public String getPath() {
			return "validatorRegex";
		}

		@Override
		public void setModified(TextParameterDefinitionDto container, boolean modified) {
			container.validatorRegex_m = modified;
		}

		@Override
		public boolean isModified(TextParameterDefinitionDto container) {
			return container.isValidatorRegexModified();
		}
	};

	private Integer width;
	private  boolean width_m;
	public static final String PROPERTY_WIDTH = "dpi-textparameterdefinition-width";

	private transient static PropertyAccessor<TextParameterDefinitionDto, Integer> width_pa = new PropertyAccessor<TextParameterDefinitionDto, Integer>() {
		@Override
		public void setValue(TextParameterDefinitionDto container, Integer object) {
			container.setWidth(object);
		}

		@Override
		public Integer getValue(TextParameterDefinitionDto container) {
			return container.getWidth();
		}

		@Override
		public Class<?> getType() {
			return Integer.class;
		}

		@Override
		public String getPath() {
			return "width";
		}

		@Override
		public void setModified(TextParameterDefinitionDto container, boolean modified) {
			container.width_m = modified;
		}

		@Override
		public boolean isModified(TextParameterDefinitionDto container) {
			return container.isWidthModified();
		}
	};


	public TextParameterDefinitionDto() {
		super();
	}

	public String getDefaultValue()  {
		if(! isDtoProxy()){
			return this.defaultValue;
		}

		if(isDefaultValueModified())
			return this.defaultValue;

		if(! GWT.isClient())
			return null;

		String _value = dtoManager.getProperty(this, instantiatePropertyAccess().defaultValue());

		return _value;
	}


	public void setDefaultValue(String defaultValue)  {
		/* old value */
		String oldValue = null;
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

		this.fireObjectChangedEvent(TextParameterDefinitionDtoPA.INSTANCE.defaultValue(), oldValue);
	}


	public boolean isDefaultValueModified()  {
		return defaultValue_m;
	}


	public static PropertyAccessor<TextParameterDefinitionDto, String> getDefaultValuePropertyAccessor()  {
		return defaultValue_pa;
	}


	public Integer getHeight()  {
		if(! isDtoProxy()){
			return this.height;
		}

		if(isHeightModified())
			return this.height;

		if(! GWT.isClient())
			return null;

		Integer _value = dtoManager.getProperty(this, instantiatePropertyAccess().height());

		return _value;
	}


	public void setHeight(Integer height)  {
		/* old value */
		Integer oldValue = null;
		if(GWT.isClient())
			oldValue = getHeight();

		/* set new value */
		this.height = height;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(height_pa, oldValue, height, this.height_m));

		/* set indicator */
		this.height_m = true;

		this.fireObjectChangedEvent(TextParameterDefinitionDtoPA.INSTANCE.height(), oldValue);
	}


	public boolean isHeightModified()  {
		return height_m;
	}


	public static PropertyAccessor<TextParameterDefinitionDto, Integer> getHeightPropertyAccessor()  {
		return height_pa;
	}


	public boolean isReturnNullWhenEmpty()  {
		if(! isDtoProxy()){
			return this.returnNullWhenEmpty;
		}

		if(isReturnNullWhenEmptyModified())
			return this.returnNullWhenEmpty;

		if(! GWT.isClient())
			return false;

		boolean _value = dtoManager.getProperty(this, instantiatePropertyAccess().returnNullWhenEmpty());

		return _value;
	}


	public void setReturnNullWhenEmpty(boolean returnNullWhenEmpty)  {
		/* old value */
		boolean oldValue = false;
		if(GWT.isClient())
			oldValue = isReturnNullWhenEmpty();

		/* set new value */
		this.returnNullWhenEmpty = returnNullWhenEmpty;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(returnNullWhenEmpty_pa, oldValue, returnNullWhenEmpty, this.returnNullWhenEmpty_m));

		/* set indicator */
		this.returnNullWhenEmpty_m = true;

		this.fireObjectChangedEvent(TextParameterDefinitionDtoPA.INSTANCE.returnNullWhenEmpty(), oldValue);
	}


	public boolean isReturnNullWhenEmptyModified()  {
		return returnNullWhenEmpty_m;
	}


	public static PropertyAccessor<TextParameterDefinitionDto, Boolean> getReturnNullWhenEmptyPropertyAccessor()  {
		return returnNullWhenEmpty_pa;
	}


	public DatatypeDto getReturnType()  {
		if(! isDtoProxy()){
			return this.returnType;
		}

		if(isReturnTypeModified())
			return this.returnType;

		if(! GWT.isClient())
			return null;

		DatatypeDto _value = dtoManager.getProperty(this, instantiatePropertyAccess().returnType());

		return _value;
	}


	public void setReturnType(DatatypeDto returnType)  {
		/* old value */
		DatatypeDto oldValue = null;
		if(GWT.isClient())
			oldValue = getReturnType();

		/* set new value */
		this.returnType = returnType;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(returnType_pa, oldValue, returnType, this.returnType_m));

		/* set indicator */
		this.returnType_m = true;

		this.fireObjectChangedEvent(TextParameterDefinitionDtoPA.INSTANCE.returnType(), oldValue);
	}


	public boolean isReturnTypeModified()  {
		return returnType_m;
	}


	public static PropertyAccessor<TextParameterDefinitionDto, DatatypeDto> getReturnTypePropertyAccessor()  {
		return returnType_pa;
	}


	public String getValidatorRegex()  {
		if(! isDtoProxy()){
			return this.validatorRegex;
		}

		if(isValidatorRegexModified())
			return this.validatorRegex;

		if(! GWT.isClient())
			return null;

		String _value = dtoManager.getProperty(this, instantiatePropertyAccess().validatorRegex());

		return _value;
	}


	public void setValidatorRegex(String validatorRegex)  {
		/* old value */
		String oldValue = null;
		if(GWT.isClient())
			oldValue = getValidatorRegex();

		/* set new value */
		this.validatorRegex = validatorRegex;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(validatorRegex_pa, oldValue, validatorRegex, this.validatorRegex_m));

		/* set indicator */
		this.validatorRegex_m = true;

		this.fireObjectChangedEvent(TextParameterDefinitionDtoPA.INSTANCE.validatorRegex(), oldValue);
	}


	public boolean isValidatorRegexModified()  {
		return validatorRegex_m;
	}


	public static PropertyAccessor<TextParameterDefinitionDto, String> getValidatorRegexPropertyAccessor()  {
		return validatorRegex_pa;
	}


	public Integer getWidth()  {
		if(! isDtoProxy()){
			return this.width;
		}

		if(isWidthModified())
			return this.width;

		if(! GWT.isClient())
			return null;

		Integer _value = dtoManager.getProperty(this, instantiatePropertyAccess().width());

		return _value;
	}


	public void setWidth(Integer width)  {
		/* old value */
		Integer oldValue = null;
		if(GWT.isClient())
			oldValue = getWidth();

		/* set new value */
		this.width = width;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(width_pa, oldValue, width, this.width_m));

		/* set indicator */
		this.width_m = true;

		this.fireObjectChangedEvent(TextParameterDefinitionDtoPA.INSTANCE.width(), oldValue);
	}


	public boolean isWidthModified()  {
		return width_m;
	}


	public static PropertyAccessor<TextParameterDefinitionDto, Integer> getWidthPropertyAccessor()  {
		return width_pa;
	}


	@Override
	public String toDisplayTitle()  {
		try{
			return RsMessages.INSTANCE.textParameterText();
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
		if(! (obj instanceof TextParameterDefinitionDto))
			return false;

		if(null == getId())
			return super.equals(obj);
		return getId().equals(((TextParameterDefinitionDto)obj).getId());
	}

	@Override
	public String toString()  {
		return getClass().getName() + ": " + getId();
	}

	public static Dto2PosoMapper newPosoMapper()  {
		return new TextParameterDefinitionDto2PosoMap();
	}

	public TextParameterDefinitionDtoPA instantiatePropertyAccess()  {
		return GWT.create(TextParameterDefinitionDtoPA.class);
	}

	public void clearModified()  {
		this.defaultValue = null;
		this.defaultValue_m = false;
		this.height = null;
		this.height_m = false;
		this.returnNullWhenEmpty = false;
		this.returnNullWhenEmpty_m = false;
		this.returnType = null;
		this.returnType_m = false;
		this.validatorRegex = null;
		this.validatorRegex_m = false;
		this.width = null;
		this.width_m = false;
	}


	public boolean isModified()  {
		if(super.isModified())
			return true;
		if(defaultValue_m)
			return true;
		if(height_m)
			return true;
		if(returnNullWhenEmpty_m)
			return true;
		if(returnType_m)
			return true;
		if(validatorRegex_m)
			return true;
		if(width_m)
			return true;
		return false;
	}


	public List<PropertyAccessor> getPropertyAccessors()  {
		List<PropertyAccessor> list = super.getPropertyAccessors();
		list.add(defaultValue_pa);
		list.add(height_pa);
		list.add(returnNullWhenEmpty_pa);
		list.add(returnType_pa);
		list.add(validatorRegex_pa);
		list.add(width_pa);
		return list;
	}


	public List<PropertyAccessor> getModifiedPropertyAccessors()  {
		List<PropertyAccessor> list = super.getModifiedPropertyAccessors();
		if(defaultValue_m)
			list.add(defaultValue_pa);
		if(height_m)
			list.add(height_pa);
		if(returnNullWhenEmpty_m)
			list.add(returnNullWhenEmpty_pa);
		if(returnType_m)
			list.add(returnType_pa);
		if(validatorRegex_m)
			list.add(validatorRegex_pa);
		if(width_m)
			list.add(width_pa);
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsByView(net.datenwerke.gxtdto.client.dtomanager.DtoView view)  {
		List<PropertyAccessor> list = super.getPropertyAccessorsByView(view);
		if(view.compareTo(DtoView.NORMAL) >= 0){
			list.add(defaultValue_pa);
			list.add(height_pa);
			list.add(returnNullWhenEmpty_pa);
			list.add(returnType_pa);
			list.add(validatorRegex_pa);
			list.add(width_pa);
		}
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsForDtos()  {
		List<PropertyAccessor> list = super.getPropertyAccessorsForDtos();
		return list;
	}



	net.datenwerke.rs.core.client.parameters.dto.DatatypeDto wl_0;

}
