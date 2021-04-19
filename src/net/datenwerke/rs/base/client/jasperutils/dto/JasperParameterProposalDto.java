package net.datenwerke.rs.base.client.jasperutils.dto;

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
import net.datenwerke.rs.base.client.jasperutils.dto.pa.JasperParameterProposalDtoPA;
import net.datenwerke.rs.base.client.jasperutils.dto.posomap.JasperParameterProposalDto2PosoMap;
import net.datenwerke.rs.base.service.reportengines.jasper.util.JasperParameterProposal;
import net.datenwerke.rs.core.client.parameters.dto.ParameterDefinitionDto;
import net.datenwerke.rs.core.client.parameters.dto.ParameterProposalDto;

/**
 * Dto for {@link JasperParameterProposal}
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class JasperParameterProposalDto extends RsDto implements IdedDto, ParameterProposalDto {


	private static final long serialVersionUID = 1;


	/* Fields */
	private String defaultValueExpression;
	private  boolean defaultValueExpression_m;
	public static final String PROPERTY_DEFAULT_VALUE_EXPRESSION = "dpi-jasperparameterproposal-defaultvalueexpression";

	private transient static PropertyAccessor<JasperParameterProposalDto, String> defaultValueExpression_pa = new PropertyAccessor<JasperParameterProposalDto, String>() {
		@Override
		public void setValue(JasperParameterProposalDto container, String object) {
			container.setDefaultValueExpression(object);
		}

		@Override
		public String getValue(JasperParameterProposalDto container) {
			return container.getDefaultValueExpression();
		}

		@Override
		public Class<?> getType() {
			return String.class;
		}

		@Override
		public String getPath() {
			return "defaultValueExpression";
		}

		@Override
		public void setModified(JasperParameterProposalDto container, boolean modified) {
			container.defaultValueExpression_m = modified;
		}

		@Override
		public boolean isModified(JasperParameterProposalDto container) {
			return container.isDefaultValueExpressionModified();
		}
	};

	private boolean forPromting;
	private  boolean forPromting_m;
	public static final String PROPERTY_FOR_PROMTING = "dpi-jasperparameterproposal-forpromting";

	private transient static PropertyAccessor<JasperParameterProposalDto, Boolean> forPromting_pa = new PropertyAccessor<JasperParameterProposalDto, Boolean>() {
		@Override
		public void setValue(JasperParameterProposalDto container, Boolean object) {
			container.setForPromting(object);
		}

		@Override
		public Boolean getValue(JasperParameterProposalDto container) {
			return container.isForPromting();
		}

		@Override
		public Class<?> getType() {
			return Boolean.class;
		}

		@Override
		public String getPath() {
			return "forPromting";
		}

		@Override
		public void setModified(JasperParameterProposalDto container, boolean modified) {
			container.forPromting_m = modified;
		}

		@Override
		public boolean isModified(JasperParameterProposalDto container) {
			return container.isForPromtingModified();
		}
	};

	private String key;
	private  boolean key_m;
	public static final String PROPERTY_KEY = "dpi-jasperparameterproposal-key";

	private transient static PropertyAccessor<JasperParameterProposalDto, String> key_pa = new PropertyAccessor<JasperParameterProposalDto, String>() {
		@Override
		public void setValue(JasperParameterProposalDto container, String object) {
			// id field
		}

		@Override
		public String getValue(JasperParameterProposalDto container) {
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
		public void setModified(JasperParameterProposalDto container, boolean modified) {
			container.key_m = modified;
		}

		@Override
		public boolean isModified(JasperParameterProposalDto container) {
			return container.isKeyModified();
		}
	};

	private String name;
	private  boolean name_m;
	public static final String PROPERTY_NAME = "dpi-jasperparameterproposal-name";

	private transient static PropertyAccessor<JasperParameterProposalDto, String> name_pa = new PropertyAccessor<JasperParameterProposalDto, String>() {
		@Override
		public void setValue(JasperParameterProposalDto container, String object) {
			container.setName(object);
		}

		@Override
		public String getValue(JasperParameterProposalDto container) {
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
		public void setModified(JasperParameterProposalDto container, boolean modified) {
			container.name_m = modified;
		}

		@Override
		public boolean isModified(JasperParameterProposalDto container) {
			return container.isNameModified();
		}
	};

	private ParameterDefinitionDto parameterProposal;
	private  boolean parameterProposal_m;
	public static final String PROPERTY_PARAMETER_PROPOSAL = "dpi-jasperparameterproposal-parameterproposal";

	private transient static PropertyAccessor<JasperParameterProposalDto, ParameterDefinitionDto> parameterProposal_pa = new PropertyAccessor<JasperParameterProposalDto, ParameterDefinitionDto>() {
		@Override
		public void setValue(JasperParameterProposalDto container, ParameterDefinitionDto object) {
			container.setParameterProposal(object);
		}

		@Override
		public ParameterDefinitionDto getValue(JasperParameterProposalDto container) {
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
		public void setModified(JasperParameterProposalDto container, boolean modified) {
			container.parameterProposal_m = modified;
		}

		@Override
		public boolean isModified(JasperParameterProposalDto container) {
			return container.isParameterProposalModified();
		}
	};

	private String type;
	private  boolean type_m;
	public static final String PROPERTY_TYPE = "dpi-jasperparameterproposal-type";

	private transient static PropertyAccessor<JasperParameterProposalDto, String> type_pa = new PropertyAccessor<JasperParameterProposalDto, String>() {
		@Override
		public void setValue(JasperParameterProposalDto container, String object) {
			container.setType(object);
		}

		@Override
		public String getValue(JasperParameterProposalDto container) {
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
		public void setModified(JasperParameterProposalDto container, boolean modified) {
			container.type_m = modified;
		}

		@Override
		public boolean isModified(JasperParameterProposalDto container) {
			return container.isTypeModified();
		}
	};


	public JasperParameterProposalDto() {
		super();
	}

	public String getDefaultValueExpression()  {
		if(! isDtoProxy()){
			return this.defaultValueExpression;
		}

		if(isDefaultValueExpressionModified())
			return this.defaultValueExpression;

		if(! GWT.isClient())
			return null;

		String _value = dtoManager.getProperty(this, instantiatePropertyAccess().defaultValueExpression());

		return _value;
	}


	public void setDefaultValueExpression(String defaultValueExpression)  {
		/* old value */
		String oldValue = null;
		if(GWT.isClient())
			oldValue = getDefaultValueExpression();

		/* set new value */
		this.defaultValueExpression = defaultValueExpression;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(defaultValueExpression_pa, oldValue, defaultValueExpression, this.defaultValueExpression_m));

		/* set indicator */
		this.defaultValueExpression_m = true;

		this.fireObjectChangedEvent(JasperParameterProposalDtoPA.INSTANCE.defaultValueExpression(), oldValue);
	}


	public boolean isDefaultValueExpressionModified()  {
		return defaultValueExpression_m;
	}


	public static PropertyAccessor<JasperParameterProposalDto, String> getDefaultValueExpressionPropertyAccessor()  {
		return defaultValueExpression_pa;
	}


	public boolean isForPromting()  {
		if(! isDtoProxy()){
			return this.forPromting;
		}

		if(isForPromtingModified())
			return this.forPromting;

		if(! GWT.isClient())
			return false;

		boolean _value = dtoManager.getProperty(this, instantiatePropertyAccess().forPromting());

		return _value;
	}


	public void setForPromting(boolean forPromting)  {
		/* old value */
		boolean oldValue = false;
		if(GWT.isClient())
			oldValue = isForPromting();

		/* set new value */
		this.forPromting = forPromting;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(forPromting_pa, oldValue, forPromting, this.forPromting_m));

		/* set indicator */
		this.forPromting_m = true;

		this.fireObjectChangedEvent(JasperParameterProposalDtoPA.INSTANCE.forPromting(), oldValue);
	}


	public boolean isForPromtingModified()  {
		return forPromting_m;
	}


	public static PropertyAccessor<JasperParameterProposalDto, Boolean> getForPromtingPropertyAccessor()  {
		return forPromting_pa;
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

		this.fireObjectChangedEvent(JasperParameterProposalDtoPA.INSTANCE.key(), oldValue);
	}


	public boolean isKeyModified()  {
		return key_m;
	}


	public static PropertyAccessor<JasperParameterProposalDto, String> getKeyPropertyAccessor()  {
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

		this.fireObjectChangedEvent(JasperParameterProposalDtoPA.INSTANCE.name(), oldValue);
	}


	public boolean isNameModified()  {
		return name_m;
	}


	public static PropertyAccessor<JasperParameterProposalDto, String> getNamePropertyAccessor()  {
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

		this.fireObjectChangedEvent(JasperParameterProposalDtoPA.INSTANCE.parameterProposal(), oldValue);
	}


	public boolean isParameterProposalModified()  {
		return parameterProposal_m;
	}


	public static PropertyAccessor<JasperParameterProposalDto, ParameterDefinitionDto> getParameterProposalPropertyAccessor()  {
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

		this.fireObjectChangedEvent(JasperParameterProposalDtoPA.INSTANCE.type(), oldValue);
	}


	public boolean isTypeModified()  {
		return type_m;
	}


	public static PropertyAccessor<JasperParameterProposalDto, String> getTypePropertyAccessor()  {
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
		if(! (obj instanceof JasperParameterProposalDto))
			return false;

		if(null == getKey())
			return super.equals(obj);
		return getKey().equals(((JasperParameterProposalDto)obj).getKey());
	}

	@Override
	public String toString()  {
		return getClass().getName() + ": " + getKey();
	}

	public static Dto2PosoMapper newPosoMapper()  {
		return new JasperParameterProposalDto2PosoMap();
	}

	public JasperParameterProposalDtoPA instantiatePropertyAccess()  {
		return GWT.create(JasperParameterProposalDtoPA.class);
	}

	public void clearModified()  {
		this.defaultValueExpression = null;
		this.defaultValueExpression_m = false;
		this.forPromting = false;
		this.forPromting_m = false;
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
		if(defaultValueExpression_m)
			return true;
		if(forPromting_m)
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
		list.add(defaultValueExpression_pa);
		list.add(forPromting_pa);
		list.add(key_pa);
		list.add(name_pa);
		list.add(parameterProposal_pa);
		list.add(type_pa);
		return list;
	}


	public List<PropertyAccessor> getModifiedPropertyAccessors()  {
		List<PropertyAccessor> list = super.getModifiedPropertyAccessors();
		if(defaultValueExpression_m)
			list.add(defaultValueExpression_pa);
		if(forPromting_m)
			list.add(forPromting_pa);
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
			list.add(defaultValueExpression_pa);
			list.add(forPromting_pa);
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
