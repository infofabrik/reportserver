package net.datenwerke.rs.uservariables.client.parameters.dto;

import com.google.gwt.core.client.GWT;
import java.lang.NullPointerException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.gxtdto.client.dtomanager.Dto2PosoMapper;
import net.datenwerke.gxtdto.client.dtomanager.Dto;
import net.datenwerke.gxtdto.client.dtomanager.DtoView;
import net.datenwerke.gxtdto.client.dtomanager.PropertyAccessor;
import net.datenwerke.gxtdto.client.dtomanager.redoundo.ChangeTracker;
import net.datenwerke.gxtdto.client.eventbus.events.ObjectChangedEvent;
import net.datenwerke.gxtdto.client.eventbus.handlers.ObjectChangedEventHandler;
import net.datenwerke.gxtdto.client.eventbus.handlers.has.HasObjectChangedEventHandler;
import net.datenwerke.gxtdto.client.locale.BaseMessages;
import net.datenwerke.rs.core.client.parameters.dto.decorator.ParameterDefinitionDtoDec;
import net.datenwerke.rs.uservariables.client.parameters.dto.pa.UserVariableParameterDefinitionDtoPA;
import net.datenwerke.rs.uservariables.client.parameters.dto.posomap.UserVariableParameterDefinitionDto2PosoMap;
import net.datenwerke.rs.uservariables.client.uservariables.dto.UserVariableDefinitionDto;
import net.datenwerke.rs.uservariables.client.uservariables.locale.UserVariablesMessages;
import net.datenwerke.rs.uservariables.service.parameters.UserVariableParameterDefinition;

/**
 * Dto for {@link UserVariableParameterDefinition}
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class UserVariableParameterDefinitionDto extends ParameterDefinitionDtoDec {


	private static final long serialVersionUID = 1;


	/* Fields */
	private UserVariableDefinitionDto userVariableDefinition;
	private  boolean userVariableDefinition_m;
	public static final String PROPERTY_USER_VARIABLE_DEFINITION = "dpi-uservariableparameterdefinition-uservariabledefinition";

	private transient static PropertyAccessor<UserVariableParameterDefinitionDto, UserVariableDefinitionDto> userVariableDefinition_pa = new PropertyAccessor<UserVariableParameterDefinitionDto, UserVariableDefinitionDto>() {
		@Override
		public void setValue(UserVariableParameterDefinitionDto container, UserVariableDefinitionDto object) {
			container.setUserVariableDefinition(object);
		}

		@Override
		public UserVariableDefinitionDto getValue(UserVariableParameterDefinitionDto container) {
			return container.getUserVariableDefinition();
		}

		@Override
		public Class<?> getType() {
			return UserVariableDefinitionDto.class;
		}

		@Override
		public String getPath() {
			return "userVariableDefinition";
		}

		@Override
		public void setModified(UserVariableParameterDefinitionDto container, boolean modified) {
			container.userVariableDefinition_m = modified;
		}

		@Override
		public boolean isModified(UserVariableParameterDefinitionDto container) {
			return container.isUserVariableDefinitionModified();
		}
	};


	public UserVariableParameterDefinitionDto() {
		super();
	}

	public UserVariableDefinitionDto getUserVariableDefinition()  {
		if(! isDtoProxy()){
			return this.userVariableDefinition;
		}

		if(isUserVariableDefinitionModified())
			return this.userVariableDefinition;

		if(! GWT.isClient())
			return null;

		UserVariableDefinitionDto _value = dtoManager.getProperty(this, instantiatePropertyAccess().userVariableDefinition());

		if(_value instanceof HasObjectChangedEventHandler){
			((HasObjectChangedEventHandler)_value).addObjectChangedHandler(new net.datenwerke.gxtdto.client.eventbus.handlers.ObjectChangedEventHandler(){
				@Override
				public void onObjectChangedEvent(net.datenwerke.gxtdto.client.eventbus.events.ObjectChangedEvent event){
					if(! isUserVariableDefinitionModified())
						setUserVariableDefinition((UserVariableDefinitionDto) event.getObject());
				}
			}
			);
		}
		return _value;
	}


	public void setUserVariableDefinition(UserVariableDefinitionDto userVariableDefinition)  {
		/* old value */
		UserVariableDefinitionDto oldValue = null;
		if(GWT.isClient())
			oldValue = getUserVariableDefinition();

		/* set new value */
		this.userVariableDefinition = userVariableDefinition;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(userVariableDefinition_pa, oldValue, userVariableDefinition, this.userVariableDefinition_m));

		/* set indicator */
		this.userVariableDefinition_m = true;

		this.fireObjectChangedEvent(UserVariableParameterDefinitionDtoPA.INSTANCE.userVariableDefinition(), oldValue);
	}


	public boolean isUserVariableDefinitionModified()  {
		return userVariableDefinition_m;
	}


	public static PropertyAccessor<UserVariableParameterDefinitionDto, UserVariableDefinitionDto> getUserVariableDefinitionPropertyAccessor()  {
		return userVariableDefinition_pa;
	}


	@Override
	public String toDisplayTitle()  {
		try{
			return UserVariablesMessages.INSTANCE.userVariablesParameterText();
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
		if(! (obj instanceof UserVariableParameterDefinitionDto))
			return false;

		if(null == getId())
			return super.equals(obj);
		return getId().equals(((UserVariableParameterDefinitionDto)obj).getId());
	}

	@Override
	public String toString()  {
		return getClass().getName() + ": " + getId();
	}

	public static Dto2PosoMapper newPosoMapper()  {
		return new UserVariableParameterDefinitionDto2PosoMap();
	}

	public UserVariableParameterDefinitionDtoPA instantiatePropertyAccess()  {
		return GWT.create(UserVariableParameterDefinitionDtoPA.class);
	}

	public void clearModified()  {
		this.userVariableDefinition = null;
		this.userVariableDefinition_m = false;
	}


	public boolean isModified()  {
		if(super.isModified())
			return true;
		if(userVariableDefinition_m)
			return true;
		return false;
	}


	public List<PropertyAccessor> getPropertyAccessors()  {
		List<PropertyAccessor> list = super.getPropertyAccessors();
		list.add(userVariableDefinition_pa);
		return list;
	}


	public List<PropertyAccessor> getModifiedPropertyAccessors()  {
		List<PropertyAccessor> list = super.getModifiedPropertyAccessors();
		if(userVariableDefinition_m)
			list.add(userVariableDefinition_pa);
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsByView(net.datenwerke.gxtdto.client.dtomanager.DtoView view)  {
		List<PropertyAccessor> list = super.getPropertyAccessorsByView(view);
		if(view.compareTo(DtoView.NORMAL) >= 0){
			list.add(userVariableDefinition_pa);
		}
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsForDtos()  {
		List<PropertyAccessor> list = super.getPropertyAccessorsForDtos();
		list.add(userVariableDefinition_pa);
		return list;
	}



	net.datenwerke.rs.uservariables.client.uservariables.dto.UserVariableDefinitionDto wl_0;

}
