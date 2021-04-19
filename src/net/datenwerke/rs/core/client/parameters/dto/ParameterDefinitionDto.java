package net.datenwerke.rs.core.client.parameters.dto;

import com.google.gwt.core.client.GWT;
import java.lang.Boolean;
import java.lang.IllegalStateException;
import java.lang.Integer;
import java.lang.Long;
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
import net.datenwerke.gxtdto.client.dtomanager.dtomod.collections.ChangeMonitoredList;
import net.datenwerke.gxtdto.client.dtomanager.dtomod.collections.MonitoredCollection;
import net.datenwerke.gxtdto.client.dtomanager.redoundo.ChangeTracker;
import net.datenwerke.gxtdto.client.eventbus.events.ObjectChangedEvent;
import net.datenwerke.gxtdto.client.eventbus.handlers.ObjectChangedEventHandler;
import net.datenwerke.gxtdto.client.eventbus.handlers.has.HasObjectChangedEventHandler;
import net.datenwerke.rs.core.client.parameters.dto.ParameterDefinitionDto;
import net.datenwerke.rs.core.client.parameters.dto.pa.ParameterDefinitionDtoPA;
import net.datenwerke.rs.core.client.parameters.dto.posomap.ParameterDefinitionDto2PosoMap;
import net.datenwerke.rs.core.service.parameters.entities.ParameterDefinition;

/**
 * Dto for {@link ParameterDefinition}
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
abstract public class ParameterDefinitionDto extends RsDto implements IdedDto {


	private static final long serialVersionUID = 1;

	private Long dtoId;


	/* Fields */
	private List<ParameterDefinitionDto> dependsOn;
	private  boolean dependsOn_m;
	public static final String PROPERTY_DEPENDS_ON = "dpi-parameterdefinition-dependson";

	private transient static PropertyAccessor<ParameterDefinitionDto, List<ParameterDefinitionDto>> dependsOn_pa = new PropertyAccessor<ParameterDefinitionDto, List<ParameterDefinitionDto>>() {
		@Override
		public void setValue(ParameterDefinitionDto container, List<ParameterDefinitionDto> object) {
			container.setDependsOn(object);
		}

		@Override
		public List<ParameterDefinitionDto> getValue(ParameterDefinitionDto container) {
			return container.getDependsOn();
		}

		@Override
		public Class<?> getType() {
			return List.class;
		}

		@Override
		public String getPath() {
			return "dependsOn";
		}

		@Override
		public void setModified(ParameterDefinitionDto container, boolean modified) {
			container.dependsOn_m = modified;
		}

		@Override
		public boolean isModified(ParameterDefinitionDto container) {
			return container.isDependsOnModified();
		}
	};

	private String description;
	private  boolean description_m;
	public static final String PROPERTY_DESCRIPTION = "dpi-parameterdefinition-description";

	private transient static PropertyAccessor<ParameterDefinitionDto, String> description_pa = new PropertyAccessor<ParameterDefinitionDto, String>() {
		@Override
		public void setValue(ParameterDefinitionDto container, String object) {
			container.setDescription(object);
		}

		@Override
		public String getValue(ParameterDefinitionDto container) {
			return container.getDescription();
		}

		@Override
		public Class<?> getType() {
			return String.class;
		}

		@Override
		public String getPath() {
			return "description";
		}

		@Override
		public void setModified(ParameterDefinitionDto container, boolean modified) {
			container.description_m = modified;
		}

		@Override
		public boolean isModified(ParameterDefinitionDto container) {
			return container.isDescriptionModified();
		}
	};

	private Boolean displayInline;
	private  boolean displayInline_m;
	public static final String PROPERTY_DISPLAY_INLINE = "dpi-parameterdefinition-displayinline";

	private transient static PropertyAccessor<ParameterDefinitionDto, Boolean> displayInline_pa = new PropertyAccessor<ParameterDefinitionDto, Boolean>() {
		@Override
		public void setValue(ParameterDefinitionDto container, Boolean object) {
			container.setDisplayInline(object);
		}

		@Override
		public Boolean getValue(ParameterDefinitionDto container) {
			return container.isDisplayInline();
		}

		@Override
		public Class<?> getType() {
			return Boolean.class;
		}

		@Override
		public String getPath() {
			return "displayInline";
		}

		@Override
		public void setModified(ParameterDefinitionDto container, boolean modified) {
			container.displayInline_m = modified;
		}

		@Override
		public boolean isModified(ParameterDefinitionDto container) {
			return container.isDisplayInlineModified();
		}
	};

	private Boolean editable;
	private  boolean editable_m;
	public static final String PROPERTY_EDITABLE = "dpi-parameterdefinition-editable";

	private transient static PropertyAccessor<ParameterDefinitionDto, Boolean> editable_pa = new PropertyAccessor<ParameterDefinitionDto, Boolean>() {
		@Override
		public void setValue(ParameterDefinitionDto container, Boolean object) {
			container.setEditable(object);
		}

		@Override
		public Boolean getValue(ParameterDefinitionDto container) {
			return container.isEditable();
		}

		@Override
		public Class<?> getType() {
			return Boolean.class;
		}

		@Override
		public String getPath() {
			return "editable";
		}

		@Override
		public void setModified(ParameterDefinitionDto container, boolean modified) {
			container.editable_m = modified;
		}

		@Override
		public boolean isModified(ParameterDefinitionDto container) {
			return container.isEditableModified();
		}
	};

	private Boolean hidden;
	private  boolean hidden_m;
	public static final String PROPERTY_HIDDEN = "dpi-parameterdefinition-hidden";

	private transient static PropertyAccessor<ParameterDefinitionDto, Boolean> hidden_pa = new PropertyAccessor<ParameterDefinitionDto, Boolean>() {
		@Override
		public void setValue(ParameterDefinitionDto container, Boolean object) {
			container.setHidden(object);
		}

		@Override
		public Boolean getValue(ParameterDefinitionDto container) {
			return container.isHidden();
		}

		@Override
		public Class<?> getType() {
			return Boolean.class;
		}

		@Override
		public String getPath() {
			return "hidden";
		}

		@Override
		public void setModified(ParameterDefinitionDto container, boolean modified) {
			container.hidden_m = modified;
		}

		@Override
		public boolean isModified(ParameterDefinitionDto container) {
			return container.isHiddenModified();
		}
	};

	private Long id;
	private  boolean id_m;
	public static final String PROPERTY_ID = "dpi-parameterdefinition-id";

	private transient static PropertyAccessor<ParameterDefinitionDto, Long> id_pa = new PropertyAccessor<ParameterDefinitionDto, Long>() {
		@Override
		public void setValue(ParameterDefinitionDto container, Long object) {
			// id field
		}

		@Override
		public Long getValue(ParameterDefinitionDto container) {
			return container.getId();
		}

		@Override
		public Class<?> getType() {
			return Long.class;
		}

		@Override
		public String getPath() {
			return "id";
		}

		@Override
		public void setModified(ParameterDefinitionDto container, boolean modified) {
			container.id_m = modified;
		}

		@Override
		public boolean isModified(ParameterDefinitionDto container) {
			return container.isIdModified();
		}
	};

	private String key;
	private  boolean key_m;
	public static final String PROPERTY_KEY = "dpi-parameterdefinition-key";

	private transient static PropertyAccessor<ParameterDefinitionDto, String> key_pa = new PropertyAccessor<ParameterDefinitionDto, String>() {
		@Override
		public void setValue(ParameterDefinitionDto container, String object) {
			container.setKey(object);
		}

		@Override
		public String getValue(ParameterDefinitionDto container) {
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
		public void setModified(ParameterDefinitionDto container, boolean modified) {
			container.key_m = modified;
		}

		@Override
		public boolean isModified(ParameterDefinitionDto container) {
			return container.isKeyModified();
		}
	};

	private Integer labelWidth;
	private  boolean labelWidth_m;
	public static final String PROPERTY_LABEL_WIDTH = "dpi-parameterdefinition-labelwidth";

	private transient static PropertyAccessor<ParameterDefinitionDto, Integer> labelWidth_pa = new PropertyAccessor<ParameterDefinitionDto, Integer>() {
		@Override
		public void setValue(ParameterDefinitionDto container, Integer object) {
			container.setLabelWidth(object);
		}

		@Override
		public Integer getValue(ParameterDefinitionDto container) {
			return container.getLabelWidth();
		}

		@Override
		public Class<?> getType() {
			return Integer.class;
		}

		@Override
		public String getPath() {
			return "labelWidth";
		}

		@Override
		public void setModified(ParameterDefinitionDto container, boolean modified) {
			container.labelWidth_m = modified;
		}

		@Override
		public boolean isModified(ParameterDefinitionDto container) {
			return container.isLabelWidthModified();
		}
	};

	private boolean mandatory;
	private  boolean mandatory_m;
	public static final String PROPERTY_MANDATORY = "dpi-parameterdefinition-mandatory";

	private transient static PropertyAccessor<ParameterDefinitionDto, Boolean> mandatory_pa = new PropertyAccessor<ParameterDefinitionDto, Boolean>() {
		@Override
		public void setValue(ParameterDefinitionDto container, Boolean object) {
			container.setMandatory(object);
		}

		@Override
		public Boolean getValue(ParameterDefinitionDto container) {
			return container.isMandatory();
		}

		@Override
		public Class<?> getType() {
			return Boolean.class;
		}

		@Override
		public String getPath() {
			return "mandatory";
		}

		@Override
		public void setModified(ParameterDefinitionDto container, boolean modified) {
			container.mandatory_m = modified;
		}

		@Override
		public boolean isModified(ParameterDefinitionDto container) {
			return container.isMandatoryModified();
		}
	};

	private int n;
	private  boolean n_m;
	public static final String PROPERTY_N = "dpi-parameterdefinition-n";

	private transient static PropertyAccessor<ParameterDefinitionDto, Integer> n_pa = new PropertyAccessor<ParameterDefinitionDto, Integer>() {
		@Override
		public void setValue(ParameterDefinitionDto container, Integer object) {
			container.setN(object);
		}

		@Override
		public Integer getValue(ParameterDefinitionDto container) {
			return container.getN();
		}

		@Override
		public Class<?> getType() {
			return Integer.class;
		}

		@Override
		public String getPath() {
			return "n";
		}

		@Override
		public void setModified(ParameterDefinitionDto container, boolean modified) {
			container.n_m = modified;
		}

		@Override
		public boolean isModified(ParameterDefinitionDto container) {
			return container.isNModified();
		}
	};

	private String name;
	private  boolean name_m;
	public static final String PROPERTY_NAME = "dpi-parameterdefinition-name";

	private transient static PropertyAccessor<ParameterDefinitionDto, String> name_pa = new PropertyAccessor<ParameterDefinitionDto, String>() {
		@Override
		public void setValue(ParameterDefinitionDto container, String object) {
			container.setName(object);
		}

		@Override
		public String getValue(ParameterDefinitionDto container) {
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
		public void setModified(ParameterDefinitionDto container, boolean modified) {
			container.name_m = modified;
		}

		@Override
		public boolean isModified(ParameterDefinitionDto container) {
			return container.isNameModified();
		}
	};


	public ParameterDefinitionDto() {
		super();
	}

	public List<ParameterDefinitionDto> getDependsOn()  {
		if(! isDtoProxy()){
			List<ParameterDefinitionDto> _currentValue = this.dependsOn;
			if(null == _currentValue)
				this.dependsOn = new ArrayList<ParameterDefinitionDto>();

			return this.dependsOn;
		}

		if(isDependsOnModified())
			return this.dependsOn;

		if(! GWT.isClient())
			return null;

		List<ParameterDefinitionDto> _value = dtoManager.getProperty(this, instantiatePropertyAccess().dependsOn());

		_value = new ChangeMonitoredList<ParameterDefinitionDto>(_value);
		if(_value instanceof HasObjectChangedEventHandler){
			((HasObjectChangedEventHandler)_value).addObjectChangedHandler(new net.datenwerke.gxtdto.client.eventbus.handlers.ObjectChangedEventHandler(){
				@Override
				public void onObjectChangedEvent(net.datenwerke.gxtdto.client.eventbus.events.ObjectChangedEvent event){
					if(! isDependsOnModified())
						setDependsOn((List<ParameterDefinitionDto>) ((MonitoredCollection) event.getObject()).getUnderlyingCollection());
				}
			}
			);
		}
		return _value;
	}


	public void setDependsOn(List<ParameterDefinitionDto> dependsOn)  {
		/* old value */
		List<ParameterDefinitionDto> oldValue = null;
		if(GWT.isClient())
			oldValue = getDependsOn();

		/* set new value */
		this.dependsOn = dependsOn;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(dependsOn_pa, oldValue, dependsOn, this.dependsOn_m));

		/* set indicator */
		this.dependsOn_m = true;

		this.fireObjectChangedEvent(ParameterDefinitionDtoPA.INSTANCE.dependsOn(), oldValue);
	}


	public boolean isDependsOnModified()  {
		return dependsOn_m;
	}


	public static PropertyAccessor<ParameterDefinitionDto, List<ParameterDefinitionDto>> getDependsOnPropertyAccessor()  {
		return dependsOn_pa;
	}


	public String getDescription()  {
		if(! isDtoProxy()){
			return this.description;
		}

		if(isDescriptionModified())
			return this.description;

		if(! GWT.isClient())
			return null;

		String _value = dtoManager.getProperty(this, instantiatePropertyAccess().description());

		return _value;
	}


	public void setDescription(String description)  {
		/* old value */
		String oldValue = null;
		if(GWT.isClient())
			oldValue = getDescription();

		/* set new value */
		this.description = description;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(description_pa, oldValue, description, this.description_m));

		/* set indicator */
		this.description_m = true;

		this.fireObjectChangedEvent(ParameterDefinitionDtoPA.INSTANCE.description(), oldValue);
	}


	public boolean isDescriptionModified()  {
		return description_m;
	}


	public static PropertyAccessor<ParameterDefinitionDto, String> getDescriptionPropertyAccessor()  {
		return description_pa;
	}


	public Boolean isDisplayInline()  {
		if(! isDtoProxy()){
			return this.displayInline;
		}

		if(isDisplayInlineModified())
			return this.displayInline;

		if(! GWT.isClient())
			return null;

		Boolean _value = dtoManager.getProperty(this, instantiatePropertyAccess().displayInline());

		return _value;
	}


	public void setDisplayInline(Boolean displayInline)  {
		/* old value */
		Boolean oldValue = null;
		if(GWT.isClient())
			oldValue = isDisplayInline();

		/* set new value */
		this.displayInline = displayInline;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(displayInline_pa, oldValue, displayInline, this.displayInline_m));

		/* set indicator */
		this.displayInline_m = true;

		this.fireObjectChangedEvent(ParameterDefinitionDtoPA.INSTANCE.displayInline(), oldValue);
	}


	public boolean isDisplayInlineModified()  {
		return displayInline_m;
	}


	public static PropertyAccessor<ParameterDefinitionDto, Boolean> getDisplayInlinePropertyAccessor()  {
		return displayInline_pa;
	}


	public Boolean isEditable()  {
		if(! isDtoProxy()){
			return this.editable;
		}

		if(isEditableModified())
			return this.editable;

		if(! GWT.isClient())
			return null;

		Boolean _value = dtoManager.getProperty(this, instantiatePropertyAccess().editable());

		return _value;
	}


	public void setEditable(Boolean editable)  {
		/* old value */
		Boolean oldValue = null;
		if(GWT.isClient())
			oldValue = isEditable();

		/* set new value */
		this.editable = editable;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(editable_pa, oldValue, editable, this.editable_m));

		/* set indicator */
		this.editable_m = true;

		this.fireObjectChangedEvent(ParameterDefinitionDtoPA.INSTANCE.editable(), oldValue);
	}


	public boolean isEditableModified()  {
		return editable_m;
	}


	public static PropertyAccessor<ParameterDefinitionDto, Boolean> getEditablePropertyAccessor()  {
		return editable_pa;
	}


	public Boolean isHidden()  {
		if(! isDtoProxy()){
			return this.hidden;
		}

		if(isHiddenModified())
			return this.hidden;

		if(! GWT.isClient())
			return null;

		Boolean _value = dtoManager.getProperty(this, instantiatePropertyAccess().hidden());

		return _value;
	}


	public void setHidden(Boolean hidden)  {
		/* old value */
		Boolean oldValue = null;
		if(GWT.isClient())
			oldValue = isHidden();

		/* set new value */
		this.hidden = hidden;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(hidden_pa, oldValue, hidden, this.hidden_m));

		/* set indicator */
		this.hidden_m = true;

		this.fireObjectChangedEvent(ParameterDefinitionDtoPA.INSTANCE.hidden(), oldValue);
	}


	public boolean isHiddenModified()  {
		return hidden_m;
	}


	public static PropertyAccessor<ParameterDefinitionDto, Boolean> getHiddenPropertyAccessor()  {
		return hidden_pa;
	}


	public final Long getId()  {
		return dtoId;
	}

	public final void setId(Long id)  {
		if (null != dtoId)
			throw new IllegalStateException("Id already set!");
		this.dtoId = id;
	}

	public boolean isIdModified()  {
		return id_m;
	}


	public static PropertyAccessor<ParameterDefinitionDto, Long> getIdPropertyAccessor()  {
		return id_pa;
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

		this.fireObjectChangedEvent(ParameterDefinitionDtoPA.INSTANCE.key(), oldValue);
	}


	public boolean isKeyModified()  {
		return key_m;
	}


	public static PropertyAccessor<ParameterDefinitionDto, String> getKeyPropertyAccessor()  {
		return key_pa;
	}


	public Integer getLabelWidth()  {
		if(! isDtoProxy()){
			return this.labelWidth;
		}

		if(isLabelWidthModified())
			return this.labelWidth;

		if(! GWT.isClient())
			return null;

		Integer _value = dtoManager.getProperty(this, instantiatePropertyAccess().labelWidth());

		return _value;
	}


	public void setLabelWidth(Integer labelWidth)  {
		/* old value */
		Integer oldValue = null;
		if(GWT.isClient())
			oldValue = getLabelWidth();

		/* set new value */
		this.labelWidth = labelWidth;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(labelWidth_pa, oldValue, labelWidth, this.labelWidth_m));

		/* set indicator */
		this.labelWidth_m = true;

		this.fireObjectChangedEvent(ParameterDefinitionDtoPA.INSTANCE.labelWidth(), oldValue);
	}


	public boolean isLabelWidthModified()  {
		return labelWidth_m;
	}


	public static PropertyAccessor<ParameterDefinitionDto, Integer> getLabelWidthPropertyAccessor()  {
		return labelWidth_pa;
	}


	public boolean isMandatory()  {
		if(! isDtoProxy()){
			return this.mandatory;
		}

		if(isMandatoryModified())
			return this.mandatory;

		if(! GWT.isClient())
			return false;

		boolean _value = dtoManager.getProperty(this, instantiatePropertyAccess().mandatory());

		return _value;
	}


	public void setMandatory(boolean mandatory)  {
		/* old value */
		boolean oldValue = false;
		if(GWT.isClient())
			oldValue = isMandatory();

		/* set new value */
		this.mandatory = mandatory;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(mandatory_pa, oldValue, mandatory, this.mandatory_m));

		/* set indicator */
		this.mandatory_m = true;

		this.fireObjectChangedEvent(ParameterDefinitionDtoPA.INSTANCE.mandatory(), oldValue);
	}


	public boolean isMandatoryModified()  {
		return mandatory_m;
	}


	public static PropertyAccessor<ParameterDefinitionDto, Boolean> getMandatoryPropertyAccessor()  {
		return mandatory_pa;
	}


	public int getN()  {
		if(! isDtoProxy()){
			return this.n;
		}

		if(isNModified())
			return this.n;

		if(! GWT.isClient())
			return 0;

		int _value = dtoManager.getProperty(this, instantiatePropertyAccess().n());

		return _value;
	}


	public void setN(int n)  {
		/* old value */
		int oldValue = 0;
		if(GWT.isClient())
			oldValue = getN();

		/* set new value */
		this.n = n;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(n_pa, oldValue, n, this.n_m));

		/* set indicator */
		this.n_m = true;

		this.fireObjectChangedEvent(ParameterDefinitionDtoPA.INSTANCE.n(), oldValue);
	}


	public boolean isNModified()  {
		return n_m;
	}


	public static PropertyAccessor<ParameterDefinitionDto, Integer> getNPropertyAccessor()  {
		return n_pa;
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

		this.fireObjectChangedEvent(ParameterDefinitionDtoPA.INSTANCE.name(), oldValue);
	}


	public boolean isNameModified()  {
		return name_m;
	}


	public static PropertyAccessor<ParameterDefinitionDto, String> getNamePropertyAccessor()  {
		return name_pa;
	}


	@Override
	public void setDtoId(Object id)  {
		setId((Long) id);
	}

	@Override
	public Object getDtoId()  {
		return getId();
	}

	@Override
	public int hashCode()  {
		if(null == getId())
			return super.hashCode();
		return getId().hashCode();
	}

	@Override
	public boolean equals(Object obj)  {
		if(! (obj instanceof ParameterDefinitionDto))
			return false;

		if(null == getId())
			return super.equals(obj);
		return getId().equals(((ParameterDefinitionDto)obj).getId());
	}

	@Override
	public String toString()  {
		return getClass().getName() + ": " + getId();
	}

	public static Dto2PosoMapper newPosoMapper()  {
		return new ParameterDefinitionDto2PosoMap();
	}

	public ParameterDefinitionDtoPA instantiatePropertyAccess()  {
		return GWT.create(ParameterDefinitionDtoPA.class);
	}

	public void clearModified()  {
		this.dependsOn = null;
		this.dependsOn_m = false;
		this.description = null;
		this.description_m = false;
		this.displayInline = null;
		this.displayInline_m = false;
		this.editable = null;
		this.editable_m = false;
		this.hidden = null;
		this.hidden_m = false;
		this.id = null;
		this.id_m = false;
		this.key = null;
		this.key_m = false;
		this.labelWidth = null;
		this.labelWidth_m = false;
		this.mandatory = false;
		this.mandatory_m = false;
		this.n = 0;
		this.n_m = false;
		this.name = null;
		this.name_m = false;
	}


	public boolean isModified()  {
		if(super.isModified())
			return true;
		if(dependsOn_m)
			return true;
		if(description_m)
			return true;
		if(displayInline_m)
			return true;
		if(editable_m)
			return true;
		if(hidden_m)
			return true;
		if(id_m)
			return true;
		if(key_m)
			return true;
		if(labelWidth_m)
			return true;
		if(mandatory_m)
			return true;
		if(n_m)
			return true;
		if(name_m)
			return true;
		return false;
	}


	public List<PropertyAccessor> getPropertyAccessors()  {
		List<PropertyAccessor> list = super.getPropertyAccessors();
		list.add(dependsOn_pa);
		list.add(description_pa);
		list.add(displayInline_pa);
		list.add(editable_pa);
		list.add(hidden_pa);
		list.add(id_pa);
		list.add(key_pa);
		list.add(labelWidth_pa);
		list.add(mandatory_pa);
		list.add(n_pa);
		list.add(name_pa);
		return list;
	}


	public List<PropertyAccessor> getModifiedPropertyAccessors()  {
		List<PropertyAccessor> list = super.getModifiedPropertyAccessors();
		if(dependsOn_m)
			list.add(dependsOn_pa);
		if(description_m)
			list.add(description_pa);
		if(displayInline_m)
			list.add(displayInline_pa);
		if(editable_m)
			list.add(editable_pa);
		if(hidden_m)
			list.add(hidden_pa);
		if(id_m)
			list.add(id_pa);
		if(key_m)
			list.add(key_pa);
		if(labelWidth_m)
			list.add(labelWidth_pa);
		if(mandatory_m)
			list.add(mandatory_pa);
		if(n_m)
			list.add(n_pa);
		if(name_m)
			list.add(name_pa);
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsByView(net.datenwerke.gxtdto.client.dtomanager.DtoView view)  {
		List<PropertyAccessor> list = super.getPropertyAccessorsByView(view);
		if(view.compareTo(DtoView.MINIMAL) >= 0){
			list.add(description_pa);
			list.add(id_pa);
			list.add(key_pa);
			list.add(name_pa);
		}
		if(view.compareTo(DtoView.NORMAL) >= 0){
			list.add(dependsOn_pa);
			list.add(displayInline_pa);
			list.add(editable_pa);
			list.add(hidden_pa);
			list.add(labelWidth_pa);
			list.add(mandatory_pa);
			list.add(n_pa);
		}
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsForDtos()  {
		List<PropertyAccessor> list = super.getPropertyAccessorsForDtos();
		list.add(dependsOn_pa);
		return list;
	}



	net.datenwerke.rs.core.client.parameters.dto.ParameterDefinitionDto wl_0;

}
