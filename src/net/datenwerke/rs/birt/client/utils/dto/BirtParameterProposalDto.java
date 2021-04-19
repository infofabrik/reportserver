package net.datenwerke.rs.birt.client.utils.dto;

import com.google.gwt.core.client.GWT;
import java.lang.String;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.gf.base.client.dtogenerator.RsDto;
import net.datenwerke.gxtdto.client.dtomanager.Dto2PosoMapper;
import net.datenwerke.gxtdto.client.dtomanager.Dto;
import net.datenwerke.gxtdto.client.dtomanager.DtoView;
import net.datenwerke.gxtdto.client.dtomanager.IdedDto;
import net.datenwerke.gxtdto.client.dtomanager.PropertyAccessor;
import net.datenwerke.gxtdto.client.dtomanager.redoundo.ChangeTracker;
import net.datenwerke.gxtdto.client.eventbus.events.ObjectChangedEvent;
import net.datenwerke.gxtdto.client.eventbus.handlers.ObjectChangedEventHandler;
import net.datenwerke.gxtdto.client.eventbus.handlers.has.HasObjectChangedEventHandler;
import net.datenwerke.rs.birt.client.utils.dto.pa.BirtParameterProposalDtoPA;
import net.datenwerke.rs.birt.client.utils.dto.posomap.BirtParameterProposalDto2PosoMap;
import net.datenwerke.rs.birt.service.utils.BirtParameterProposal;
import net.datenwerke.rs.core.client.parameters.dto.ParameterDefinitionDto;
import net.datenwerke.rs.core.client.parameters.dto.ParameterProposalDto;

/**
 * Dto for {@link BirtParameterProposal}
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class BirtParameterProposalDto extends RsDto implements IdedDto, ParameterProposalDto {


	private static final long serialVersionUID = 1;


	/* Fields */
	private String key;
	private  boolean key_m;
	public static final String PROPERTY_KEY = "dpi-birtparameterproposal-key";

	private transient static PropertyAccessor<BirtParameterProposalDto, String> key_pa = new PropertyAccessor<BirtParameterProposalDto, String>() {
		@Override
		public void setValue(BirtParameterProposalDto container, String object) {
			// id field
		}

		@Override
		public String getValue(BirtParameterProposalDto container) {
			return container.getKey();
		}

		@Override
		public Class<?> getType() {
			return String.class;
		}

		@Override
		public String getPath() {
			return "key";
		}

		@Override
		public void setModified(BirtParameterProposalDto container, boolean modified) {
			container.key_m = modified;
		}

		@Override
		public boolean isModified(BirtParameterProposalDto container) {
			return container.isKeyModified();
		}
	};

	private String name;
	private  boolean name_m;
	public static final String PROPERTY_NAME = "dpi-birtparameterproposal-name";

	private transient static PropertyAccessor<BirtParameterProposalDto, String> name_pa = new PropertyAccessor<BirtParameterProposalDto, String>() {
		@Override
		public void setValue(BirtParameterProposalDto container, String object) {
			container.setName(object);
		}

		@Override
		public String getValue(BirtParameterProposalDto container) {
			return container.getName();
		}

		@Override
		public Class<?> getType() {
			return String.class;
		}

		@Override
		public String getPath() {
			return "name";
		}

		@Override
		public void setModified(BirtParameterProposalDto container, boolean modified) {
			container.name_m = modified;
		}

		@Override
		public boolean isModified(BirtParameterProposalDto container) {
			return container.isNameModified();
		}
	};

	private ParameterDefinitionDto parameterProposal;
	private  boolean parameterProposal_m;
	public static final String PROPERTY_PARAMETER_PROPOSAL = "dpi-birtparameterproposal-parameterproposal";

	private transient static PropertyAccessor<BirtParameterProposalDto, ParameterDefinitionDto> parameterProposal_pa = new PropertyAccessor<BirtParameterProposalDto, ParameterDefinitionDto>() {
		@Override
		public void setValue(BirtParameterProposalDto container, ParameterDefinitionDto object) {
			container.setParameterProposal(object);
		}

		@Override
		public ParameterDefinitionDto getValue(BirtParameterProposalDto container) {
			return container.getParameterProposal();
		}

		@Override
		public Class<?> getType() {
			return ParameterDefinitionDto.class;
		}

		@Override
		public String getPath() {
			return "parameterProposal";
		}

		@Override
		public void setModified(BirtParameterProposalDto container, boolean modified) {
			container.parameterProposal_m = modified;
		}

		@Override
		public boolean isModified(BirtParameterProposalDto container) {
			return container.isParameterProposalModified();
		}
	};

	private String type;
	private  boolean type_m;
	public static final String PROPERTY_TYPE = "dpi-birtparameterproposal-type";

	private transient static PropertyAccessor<BirtParameterProposalDto, String> type_pa = new PropertyAccessor<BirtParameterProposalDto, String>() {
		@Override
		public void setValue(BirtParameterProposalDto container, String object) {
			container.setType(object);
		}

		@Override
		public String getValue(BirtParameterProposalDto container) {
			return container.getType();
		}

		@Override
		public Class<?> getType() {
			return String.class;
		}

		@Override
		public String getPath() {
			return "type";
		}

		@Override
		public void setModified(BirtParameterProposalDto container, boolean modified) {
			container.type_m = modified;
		}

		@Override
		public boolean isModified(BirtParameterProposalDto container) {
			return container.isTypeModified();
		}
	};


	public BirtParameterProposalDto() {
		super();
	}

	public String getKey()  {
		if(! isDtoProxy()){
			return this.key;
		}

		if(isKeyModified())
			return this.key;

		if(! GWT.isClient())
			return null;

		String _value = dtoManager.getProperty(this, instantiatePropertyAccess().key());

		return _value;
	}


	public void setKey(String key)  {
		/* old value */
		String oldValue = null;
		if(GWT.isClient())
			oldValue = getKey();

		/* set new value */
		this.key = key;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(key_pa, oldValue, key, this.key_m));

		/* set indicator */
		this.key_m = true;

		this.fireObjectChangedEvent(BirtParameterProposalDtoPA.INSTANCE.key(), oldValue);
	}


	public boolean isKeyModified()  {
		return key_m;
	}


	public static PropertyAccessor<BirtParameterProposalDto, String> getKeyPropertyAccessor()  {
		return key_pa;
	}


	public String getName()  {
		if(! isDtoProxy()){
			return this.name;
		}

		if(isNameModified())
			return this.name;

		if(! GWT.isClient())
			return null;

		String _value = dtoManager.getProperty(this, instantiatePropertyAccess().name());

		return _value;
	}


	public void setName(String name)  {
		/* old value */
		String oldValue = null;
		if(GWT.isClient())
			oldValue = getName();

		/* set new value */
		this.name = name;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(name_pa, oldValue, name, this.name_m));

		/* set indicator */
		this.name_m = true;

		this.fireObjectChangedEvent(BirtParameterProposalDtoPA.INSTANCE.name(), oldValue);
	}


	public boolean isNameModified()  {
		return name_m;
	}


	public static PropertyAccessor<BirtParameterProposalDto, String> getNamePropertyAccessor()  {
		return name_pa;
	}


	public ParameterDefinitionDto getParameterProposal()  {
		if(! isDtoProxy()){
			return this.parameterProposal;
		}

		if(isParameterProposalModified())
			return this.parameterProposal;

		if(! GWT.isClient())
			return null;

		ParameterDefinitionDto _value = dtoManager.getProperty(this, instantiatePropertyAccess().parameterProposal());

		if(_value instanceof HasObjectChangedEventHandler){
			((HasObjectChangedEventHandler)_value).addObjectChangedHandler(new net.datenwerke.gxtdto.client.eventbus.handlers.ObjectChangedEventHandler(){
				@Override
				public void onObjectChangedEvent(net.datenwerke.gxtdto.client.eventbus.events.ObjectChangedEvent event){
					if(! isParameterProposalModified())
						setParameterProposal((ParameterDefinitionDto) event.getObject());
				}
			}
			);
		}
		return _value;
	}


	public void setParameterProposal(ParameterDefinitionDto parameterProposal)  {
		/* old value */
		ParameterDefinitionDto oldValue = null;
		if(GWT.isClient())
			oldValue = getParameterProposal();

		/* set new value */
		this.parameterProposal = parameterProposal;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(parameterProposal_pa, oldValue, parameterProposal, this.parameterProposal_m));

		/* set indicator */
		this.parameterProposal_m = true;

		this.fireObjectChangedEvent(BirtParameterProposalDtoPA.INSTANCE.parameterProposal(), oldValue);
	}


	public boolean isParameterProposalModified()  {
		return parameterProposal_m;
	}


	public static PropertyAccessor<BirtParameterProposalDto, ParameterDefinitionDto> getParameterProposalPropertyAccessor()  {
		return parameterProposal_pa;
	}


	public String getType()  {
		if(! isDtoProxy()){
			return this.type;
		}

		if(isTypeModified())
			return this.type;

		if(! GWT.isClient())
			return null;

		String _value = dtoManager.getProperty(this, instantiatePropertyAccess().type());

		return _value;
	}


	public void setType(String type)  {
		/* old value */
		String oldValue = null;
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

		this.fireObjectChangedEvent(BirtParameterProposalDtoPA.INSTANCE.type(), oldValue);
	}


	public boolean isTypeModified()  {
		return type_m;
	}


	public static PropertyAccessor<BirtParameterProposalDto, String> getTypePropertyAccessor()  {
		return type_pa;
	}


	@Override
	public void setDtoId(Object id)  {
		setKey((String) id);
	}

	@Override
	public Object getDtoId()  {
		return getKey();
	}

	@Override
	public int hashCode()  {
		if(null == getKey())
			return super.hashCode();
		return getKey().hashCode();
	}

	@Override
	public boolean equals(Object obj)  {
		if(! (obj instanceof BirtParameterProposalDto))
			return false;

		if(null == getKey())
			return super.equals(obj);
		return getKey().equals(((BirtParameterProposalDto)obj).getKey());
	}

	@Override
	public String toString()  {
		return getClass().getName() + ": " + getKey();
	}

	public static Dto2PosoMapper newPosoMapper()  {
		return new BirtParameterProposalDto2PosoMap();
	}

	public BirtParameterProposalDtoPA instantiatePropertyAccess()  {
		return GWT.create(BirtParameterProposalDtoPA.class);
	}

	public void clearModified()  {
		this.key = null;
		this.key_m = false;
		this.name = null;
		this.name_m = false;
		this.parameterProposal = null;
		this.parameterProposal_m = false;
		this.type = null;
		this.type_m = false;
	}


	public boolean isModified()  {
		if(super.isModified())
			return true;
		if(key_m)
			return true;
		if(name_m)
			return true;
		if(parameterProposal_m)
			return true;
		if(type_m)
			return true;
		return false;
	}


	public List<PropertyAccessor> getPropertyAccessors()  {
		List<PropertyAccessor> list = super.getPropertyAccessors();
		list.add(key_pa);
		list.add(name_pa);
		list.add(parameterProposal_pa);
		list.add(type_pa);
		return list;
	}


	public List<PropertyAccessor> getModifiedPropertyAccessors()  {
		List<PropertyAccessor> list = super.getModifiedPropertyAccessors();
		if(key_m)
			list.add(key_pa);
		if(name_m)
			list.add(name_pa);
		if(parameterProposal_m)
			list.add(parameterProposal_pa);
		if(type_m)
			list.add(type_pa);
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsByView(net.datenwerke.gxtdto.client.dtomanager.DtoView view)  {
		List<PropertyAccessor> list = super.getPropertyAccessorsByView(view);
		if(view.compareTo(DtoView.MINIMAL) >= 0){
			list.add(key_pa);
		}
		if(view.compareTo(DtoView.NORMAL) >= 0){
			list.add(name_pa);
			list.add(parameterProposal_pa);
			list.add(type_pa);
		}
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsForDtos()  {
		List<PropertyAccessor> list = super.getPropertyAccessorsForDtos();
		list.add(parameterProposal_pa);
		return list;
	}



	net.datenwerke.rs.core.client.parameters.dto.ParameterDefinitionDto wl_0;

}
