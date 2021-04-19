package net.datenwerke.rs.grideditor.client.grideditor.dto;

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
import net.datenwerke.rs.grideditor.client.grideditor.dto.pa.ValidatorDtoPA;
import net.datenwerke.rs.grideditor.client.grideditor.dto.posomap.ValidatorDto2PosoMap;
import net.datenwerke.rs.grideditor.service.grideditor.definition.validator.Validator;

/**
 * Dto for {@link Validator}
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
abstract public class ValidatorDto extends RsDto {


	private static final long serialVersionUID = 1;


	/* Fields */
	private String errorMsg;
	private  boolean errorMsg_m;
	public static final String PROPERTY_ERROR_MSG = "dpi-validator-errormsg";

	private transient static PropertyAccessor<ValidatorDto, String> errorMsg_pa = new PropertyAccessor<ValidatorDto, String>() {
		@Override
		public void setValue(ValidatorDto container, String object) {
			container.setErrorMsg(object);
		}

		@Override
		public String getValue(ValidatorDto container) {
			return container.getErrorMsg();
		}

		@Override
		public Class<?> getType() {
			return String.class;
		}

		@Override
		public String getPath() {
			return "errorMsg";
		}

		@Override
		public void setModified(ValidatorDto container, boolean modified) {
			container.errorMsg_m = modified;
		}

		@Override
		public boolean isModified(ValidatorDto container) {
			return container.isErrorMsgModified();
		}
	};


	public ValidatorDto() {
		super();
	}

	public String getErrorMsg()  {
		if(! isDtoProxy()){
			return this.errorMsg;
		}

		if(isErrorMsgModified())
			return this.errorMsg;

		if(! GWT.isClient())
			return null;

		String _value = dtoManager.getProperty(this, instantiatePropertyAccess().errorMsg());

		return _value;
	}


	public void setErrorMsg(String errorMsg)  {
		/* old value */
		String oldValue = null;
		if(GWT.isClient())
			oldValue = getErrorMsg();

		/* set new value */
		this.errorMsg = errorMsg;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(errorMsg_pa, oldValue, errorMsg, this.errorMsg_m));

		/* set indicator */
		this.errorMsg_m = true;

		this.fireObjectChangedEvent(ValidatorDtoPA.INSTANCE.errorMsg(), oldValue);
	}


	public boolean isErrorMsgModified()  {
		return errorMsg_m;
	}


	public static PropertyAccessor<ValidatorDto, String> getErrorMsgPropertyAccessor()  {
		return errorMsg_pa;
	}


	@Override
	public String toString()  {
		return super.toString();
	}

	public static Dto2PosoMapper newPosoMapper()  {
		return new ValidatorDto2PosoMap();
	}

	public ValidatorDtoPA instantiatePropertyAccess()  {
		return GWT.create(ValidatorDtoPA.class);
	}

	public void clearModified()  {
		this.errorMsg = null;
		this.errorMsg_m = false;
	}


	public boolean isModified()  {
		if(super.isModified())
			return true;
		if(errorMsg_m)
			return true;
		return false;
	}


	public List<PropertyAccessor> getPropertyAccessors()  {
		List<PropertyAccessor> list = super.getPropertyAccessors();
		list.add(errorMsg_pa);
		return list;
	}


	public List<PropertyAccessor> getModifiedPropertyAccessors()  {
		List<PropertyAccessor> list = super.getModifiedPropertyAccessors();
		if(errorMsg_m)
			list.add(errorMsg_pa);
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsByView(net.datenwerke.gxtdto.client.dtomanager.DtoView view)  {
		List<PropertyAccessor> list = super.getPropertyAccessorsByView(view);
		if(view.compareTo(DtoView.NORMAL) >= 0){
			list.add(errorMsg_pa);
		}
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsForDtos()  {
		List<PropertyAccessor> list = super.getPropertyAccessorsForDtos();
		return list;
	}




}
