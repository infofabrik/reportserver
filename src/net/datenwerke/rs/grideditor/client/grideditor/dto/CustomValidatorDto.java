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
import net.datenwerke.rs.grideditor.client.grideditor.dto.pa.CustomValidatorDtoPA;
import net.datenwerke.rs.grideditor.client.grideditor.dto.posomap.CustomValidatorDto2PosoMap;
import net.datenwerke.rs.grideditor.service.grideditor.definition.validator.CustomValidator;

/**
 * Dto for {@link CustomValidator}
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
abstract public class CustomValidatorDto extends ValidatorDtoDec {


	private static final long serialVersionUID = 1;


	/* Fields */
	private String clientValidator;
	private  boolean clientValidator_m;
	public static final String PROPERTY_CLIENT_VALIDATOR = "dpi-customvalidator-clientvalidator";

	private transient static PropertyAccessor<CustomValidatorDto, String> clientValidator_pa = new PropertyAccessor<CustomValidatorDto, String>() {
		@Override
		public void setValue(CustomValidatorDto container, String object) {
			container.setClientValidator(object);
		}

		@Override
		public String getValue(CustomValidatorDto container) {
			return container.getClientValidator();
		}

		@Override
		public Class<?> getType() {
			return String.class;
		}

		@Override
		public String getPath() {
			return "clientValidator";
		}

		@Override
		public void setModified(CustomValidatorDto container, boolean modified) {
			container.clientValidator_m = modified;
		}

		@Override
		public boolean isModified(CustomValidatorDto container) {
			return container.isClientValidatorModified();
		}
	};


	public CustomValidatorDto() {
		super();
	}

	public String getClientValidator()  {
		if(! isDtoProxy()){
			return this.clientValidator;
		}

		if(isClientValidatorModified())
			return this.clientValidator;

		if(! GWT.isClient())
			return null;

		String _value = dtoManager.getProperty(this, instantiatePropertyAccess().clientValidator());

		return _value;
	}


	public void setClientValidator(String clientValidator)  {
		/* old value */
		String oldValue = null;
		if(GWT.isClient())
			oldValue = getClientValidator();

		/* set new value */
		this.clientValidator = clientValidator;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(clientValidator_pa, oldValue, clientValidator, this.clientValidator_m));

		/* set indicator */
		this.clientValidator_m = true;

		this.fireObjectChangedEvent(CustomValidatorDtoPA.INSTANCE.clientValidator(), oldValue);
	}


	public boolean isClientValidatorModified()  {
		return clientValidator_m;
	}


	public static PropertyAccessor<CustomValidatorDto, String> getClientValidatorPropertyAccessor()  {
		return clientValidator_pa;
	}


	@Override
	public String toString()  {
		return super.toString();
	}

	public static Dto2PosoMapper newPosoMapper()  {
		return new CustomValidatorDto2PosoMap();
	}

	public CustomValidatorDtoPA instantiatePropertyAccess()  {
		return GWT.create(CustomValidatorDtoPA.class);
	}

	public void clearModified()  {
		this.clientValidator = null;
		this.clientValidator_m = false;
	}


	public boolean isModified()  {
		if(super.isModified())
			return true;
		if(clientValidator_m)
			return true;
		return false;
	}


	public List<PropertyAccessor> getPropertyAccessors()  {
		List<PropertyAccessor> list = super.getPropertyAccessors();
		list.add(clientValidator_pa);
		return list;
	}


	public List<PropertyAccessor> getModifiedPropertyAccessors()  {
		List<PropertyAccessor> list = super.getModifiedPropertyAccessors();
		if(clientValidator_m)
			list.add(clientValidator_pa);
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsByView(net.datenwerke.gxtdto.client.dtomanager.DtoView view)  {
		List<PropertyAccessor> list = super.getPropertyAccessorsByView(view);
		if(view.compareTo(DtoView.NORMAL) >= 0){
			list.add(clientValidator_pa);
		}
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsForDtos()  {
		List<PropertyAccessor> list = super.getPropertyAccessorsForDtos();
		return list;
	}




}
