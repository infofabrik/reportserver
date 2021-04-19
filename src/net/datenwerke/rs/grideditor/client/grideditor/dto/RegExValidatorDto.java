package net.datenwerke.rs.grideditor.client.grideditor.dto;

import com.google.gwt.core.client.GWT;
import java.lang.String;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.gxtdto.client.dtomanager.Dto2PosoMapper;
import net.datenwerke.gxtdto.client.dtomanager.DtoView;
import net.datenwerke.gxtdto.client.dtomanager.PropertyAccessor;
import net.datenwerke.gxtdto.client.dtomanager.redoundo.ChangeTracker;
import net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.ValidatorDtoDec;
import net.datenwerke.rs.grideditor.client.grideditor.dto.pa.RegExValidatorDtoPA;
import net.datenwerke.rs.grideditor.client.grideditor.dto.posomap.RegExValidatorDto2PosoMap;
import net.datenwerke.rs.grideditor.service.grideditor.definition.validator.RegExValidator;

/**
 * Dto for {@link RegExValidator}
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
abstract public class RegExValidatorDto extends ValidatorDtoDec {


	private static final long serialVersionUID = 1;


	/* Fields */
	private String regex;
	private  boolean regex_m;
	public static final String PROPERTY_REGEX = "dpi-regexvalidator-regex";

	private transient static PropertyAccessor<RegExValidatorDto, String> regex_pa = new PropertyAccessor<RegExValidatorDto, String>() {
		@Override
		public void setValue(RegExValidatorDto container, String object) {
			container.setRegex(object);
		}

		@Override
		public String getValue(RegExValidatorDto container) {
			return container.getRegex();
		}

		@Override
		public Class<?> getType() {
			return String.class;
		}

		@Override
		public String getPath() {
			return "regex";
		}

		@Override
		public void setModified(RegExValidatorDto container, boolean modified) {
			container.regex_m = modified;
		}

		@Override
		public boolean isModified(RegExValidatorDto container) {
			return container.isRegexModified();
		}
	};


	public RegExValidatorDto() {
		super();
	}

	public String getRegex()  {
		if(! isDtoProxy()){
			return this.regex;
		}

		if(isRegexModified())
			return this.regex;

		if(! GWT.isClient())
			return null;

		String _value = dtoManager.getProperty(this, instantiatePropertyAccess().regex());

		return _value;
	}


	public void setRegex(String regex)  {
		/* old value */
		String oldValue = null;
		if(GWT.isClient())
			oldValue = getRegex();

		/* set new value */
		this.regex = regex;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(regex_pa, oldValue, regex, this.regex_m));

		/* set indicator */
		this.regex_m = true;

		this.fireObjectChangedEvent(RegExValidatorDtoPA.INSTANCE.regex(), oldValue);
	}


	public boolean isRegexModified()  {
		return regex_m;
	}


	public static PropertyAccessor<RegExValidatorDto, String> getRegexPropertyAccessor()  {
		return regex_pa;
	}


	@Override
	public String toString()  {
		return super.toString();
	}

	public static Dto2PosoMapper newPosoMapper()  {
		return new RegExValidatorDto2PosoMap();
	}

	public RegExValidatorDtoPA instantiatePropertyAccess()  {
		return GWT.create(RegExValidatorDtoPA.class);
	}

	public void clearModified()  {
		this.regex = null;
		this.regex_m = false;
	}


	public boolean isModified()  {
		if(super.isModified())
			return true;
		if(regex_m)
			return true;
		return false;
	}


	public List<PropertyAccessor> getPropertyAccessors()  {
		List<PropertyAccessor> list = super.getPropertyAccessors();
		list.add(regex_pa);
		return list;
	}


	public List<PropertyAccessor> getModifiedPropertyAccessors()  {
		List<PropertyAccessor> list = super.getModifiedPropertyAccessors();
		if(regex_m)
			list.add(regex_pa);
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsByView(net.datenwerke.gxtdto.client.dtomanager.DtoView view)  {
		List<PropertyAccessor> list = super.getPropertyAccessorsByView(view);
		if(view.compareTo(DtoView.NORMAL) >= 0){
			list.add(regex_pa);
		}
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsForDtos()  {
		List<PropertyAccessor> list = super.getPropertyAccessorsForDtos();
		return list;
	}




}
