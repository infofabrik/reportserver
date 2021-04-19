package net.datenwerke.rs.grideditor.client.grideditor.dto;

import com.google.gwt.core.client.GWT;
import java.lang.Boolean;
import java.lang.String;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.gf.base.client.dtogenerator.RsDto;
import net.datenwerke.gxtdto.client.dtomanager.Dto2PosoMapper;
import net.datenwerke.gxtdto.client.dtomanager.Dto;
import net.datenwerke.gxtdto.client.dtomanager.DtoView;
import net.datenwerke.gxtdto.client.dtomanager.PropertyAccessor;
import net.datenwerke.gxtdto.client.dtomanager.dtomod.collections.ChangeMonitoredList;
import net.datenwerke.gxtdto.client.dtomanager.dtomod.collections.MonitoredCollection;
import net.datenwerke.gxtdto.client.dtomanager.redoundo.ChangeTracker;
import net.datenwerke.gxtdto.client.eventbus.events.ObjectChangedEvent;
import net.datenwerke.gxtdto.client.eventbus.handlers.ObjectChangedEventHandler;
import net.datenwerke.gxtdto.client.eventbus.handlers.has.HasObjectChangedEventHandler;
import net.datenwerke.rs.base.client.reportengines.table.dto.OrderDto;
import net.datenwerke.rs.grideditor.client.grideditor.dto.EditorDto;
import net.datenwerke.rs.grideditor.client.grideditor.dto.GridEditorRecordEntryDto;
import net.datenwerke.rs.grideditor.client.grideditor.dto.ValidatorDto;
import net.datenwerke.rs.grideditor.client.grideditor.dto.pa.GridEditorColumnConfigDtoPA;
import net.datenwerke.rs.grideditor.client.grideditor.dto.posomap.GridEditorColumnConfigDto2PosoMap;
import net.datenwerke.rs.grideditor.service.grideditor.definition.GridEditorColumnConfig;

/**
 * Dto for {@link GridEditorColumnConfig}
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
abstract public class GridEditorColumnConfigDto extends RsDto {


	private static final long serialVersionUID = 1;


	/* Fields */
	private boolean defaultCaseSensitive;
	private  boolean defaultCaseSensitive_m;
	public static final String PROPERTY_DEFAULT_CASE_SENSITIVE = "dpi-grideditorcolumnconfig-defaultcasesensitive";

	private transient static PropertyAccessor<GridEditorColumnConfigDto, Boolean> defaultCaseSensitive_pa = new PropertyAccessor<GridEditorColumnConfigDto, Boolean>() {
		@Override
		public void setValue(GridEditorColumnConfigDto container, Boolean object) {
			container.setDefaultCaseSensitive(object);
		}

		@Override
		public Boolean getValue(GridEditorColumnConfigDto container) {
			return container.isDefaultCaseSensitive();
		}

		@Override
		public Class<?> getType() {
			return Boolean.class;
		}

		@Override
		public String getPath() {
			return "defaultCaseSensitive";
		}

		@Override
		public void setModified(GridEditorColumnConfigDto container, boolean modified) {
			container.defaultCaseSensitive_m = modified;
		}

		@Override
		public boolean isModified(GridEditorColumnConfigDto container) {
			return container.isDefaultCaseSensitiveModified();
		}
	};

	private GridEditorRecordEntryDto defaultValue;
	private  boolean defaultValue_m;
	public static final String PROPERTY_DEFAULT_VALUE = "dpi-grideditorcolumnconfig-defaultvalue";

	private transient static PropertyAccessor<GridEditorColumnConfigDto, GridEditorRecordEntryDto> defaultValue_pa = new PropertyAccessor<GridEditorColumnConfigDto, GridEditorRecordEntryDto>() {
		@Override
		public void setValue(GridEditorColumnConfigDto container, GridEditorRecordEntryDto object) {
			container.setDefaultValue(object);
		}

		@Override
		public GridEditorRecordEntryDto getValue(GridEditorColumnConfigDto container) {
			return container.getDefaultValue();
		}

		@Override
		public Class<?> getType() {
			return GridEditorRecordEntryDto.class;
		}

		@Override
		public String getPath() {
			return "defaultValue";
		}

		@Override
		public void setModified(GridEditorColumnConfigDto container, boolean modified) {
			container.defaultValue_m = modified;
		}

		@Override
		public boolean isModified(GridEditorColumnConfigDto container) {
			return container.isDefaultValueModified();
		}
	};

	private String displayName;
	private  boolean displayName_m;
	public static final String PROPERTY_DISPLAY_NAME = "dpi-grideditorcolumnconfig-displayname";

	private transient static PropertyAccessor<GridEditorColumnConfigDto, String> displayName_pa = new PropertyAccessor<GridEditorColumnConfigDto, String>() {
		@Override
		public void setValue(GridEditorColumnConfigDto container, String object) {
			container.setDisplayName(object);
		}

		@Override
		public String getValue(GridEditorColumnConfigDto container) {
			return container.getDisplayName();
		}

		@Override
		public Class<?> getType() {
			return String.class;
		}

		@Override
		public String getPath() {
			return "displayName";
		}

		@Override
		public void setModified(GridEditorColumnConfigDto container, boolean modified) {
			container.displayName_m = modified;
		}

		@Override
		public boolean isModified(GridEditorColumnConfigDto container) {
			return container.isDisplayNameModified();
		}
	};

	private boolean editable;
	private  boolean editable_m;
	public static final String PROPERTY_EDITABLE = "dpi-grideditorcolumnconfig-editable";

	private transient static PropertyAccessor<GridEditorColumnConfigDto, Boolean> editable_pa = new PropertyAccessor<GridEditorColumnConfigDto, Boolean>() {
		@Override
		public void setValue(GridEditorColumnConfigDto container, Boolean object) {
			container.setEditable(object);
		}

		@Override
		public Boolean getValue(GridEditorColumnConfigDto container) {
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
		public void setModified(GridEditorColumnConfigDto container, boolean modified) {
			container.editable_m = modified;
		}

		@Override
		public boolean isModified(GridEditorColumnConfigDto container) {
			return container.isEditableModified();
		}
	};

	private EditorDto editor;
	private  boolean editor_m;
	public static final String PROPERTY_EDITOR = "dpi-grideditorcolumnconfig-editor";

	private transient static PropertyAccessor<GridEditorColumnConfigDto, EditorDto> editor_pa = new PropertyAccessor<GridEditorColumnConfigDto, EditorDto>() {
		@Override
		public void setValue(GridEditorColumnConfigDto container, EditorDto object) {
			container.setEditor(object);
		}

		@Override
		public EditorDto getValue(GridEditorColumnConfigDto container) {
			return container.getEditor();
		}

		@Override
		public Class<?> getType() {
			return EditorDto.class;
		}

		@Override
		public String getPath() {
			return "editor";
		}

		@Override
		public void setModified(GridEditorColumnConfigDto container, boolean modified) {
			container.editor_m = modified;
		}

		@Override
		public boolean isModified(GridEditorColumnConfigDto container) {
			return container.isEditorModified();
		}
	};

	private boolean enforceCaseSensitivity;
	private  boolean enforceCaseSensitivity_m;
	public static final String PROPERTY_ENFORCE_CASE_SENSITIVITY = "dpi-grideditorcolumnconfig-enforcecasesensitivity";

	private transient static PropertyAccessor<GridEditorColumnConfigDto, Boolean> enforceCaseSensitivity_pa = new PropertyAccessor<GridEditorColumnConfigDto, Boolean>() {
		@Override
		public void setValue(GridEditorColumnConfigDto container, Boolean object) {
			container.setEnforceCaseSensitivity(object);
		}

		@Override
		public Boolean getValue(GridEditorColumnConfigDto container) {
			return container.isEnforceCaseSensitivity();
		}

		@Override
		public Class<?> getType() {
			return Boolean.class;
		}

		@Override
		public String getPath() {
			return "enforceCaseSensitivity";
		}

		@Override
		public void setModified(GridEditorColumnConfigDto container, boolean modified) {
			container.enforceCaseSensitivity_m = modified;
		}

		@Override
		public boolean isModified(GridEditorColumnConfigDto container) {
			return container.isEnforceCaseSensitivityModified();
		}
	};

	private boolean filterable;
	private  boolean filterable_m;
	public static final String PROPERTY_FILTERABLE = "dpi-grideditorcolumnconfig-filterable";

	private transient static PropertyAccessor<GridEditorColumnConfigDto, Boolean> filterable_pa = new PropertyAccessor<GridEditorColumnConfigDto, Boolean>() {
		@Override
		public void setValue(GridEditorColumnConfigDto container, Boolean object) {
			container.setFilterable(object);
		}

		@Override
		public Boolean getValue(GridEditorColumnConfigDto container) {
			return container.isFilterable();
		}

		@Override
		public Class<?> getType() {
			return Boolean.class;
		}

		@Override
		public String getPath() {
			return "filterable";
		}

		@Override
		public void setModified(GridEditorColumnConfigDto container, boolean modified) {
			container.filterable_m = modified;
		}

		@Override
		public boolean isModified(GridEditorColumnConfigDto container) {
			return container.isFilterableModified();
		}
	};

	private boolean hidden;
	private  boolean hidden_m;
	public static final String PROPERTY_HIDDEN = "dpi-grideditorcolumnconfig-hidden";

	private transient static PropertyAccessor<GridEditorColumnConfigDto, Boolean> hidden_pa = new PropertyAccessor<GridEditorColumnConfigDto, Boolean>() {
		@Override
		public void setValue(GridEditorColumnConfigDto container, Boolean object) {
			container.setHidden(object);
		}

		@Override
		public Boolean getValue(GridEditorColumnConfigDto container) {
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
		public void setModified(GridEditorColumnConfigDto container, boolean modified) {
			container.hidden_m = modified;
		}

		@Override
		public boolean isModified(GridEditorColumnConfigDto container) {
			return container.isHiddenModified();
		}
	};

	private String name;
	private  boolean name_m;
	public static final String PROPERTY_NAME = "dpi-grideditorcolumnconfig-name";

	private transient static PropertyAccessor<GridEditorColumnConfigDto, String> name_pa = new PropertyAccessor<GridEditorColumnConfigDto, String>() {
		@Override
		public void setValue(GridEditorColumnConfigDto container, String object) {
			container.setName(object);
		}

		@Override
		public String getValue(GridEditorColumnConfigDto container) {
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
		public void setModified(GridEditorColumnConfigDto container, boolean modified) {
			container.name_m = modified;
		}

		@Override
		public boolean isModified(GridEditorColumnConfigDto container) {
			return container.isNameModified();
		}
	};

	private OrderDto order;
	private  boolean order_m;
	public static final String PROPERTY_ORDER = "dpi-grideditorcolumnconfig-order";

	private transient static PropertyAccessor<GridEditorColumnConfigDto, OrderDto> order_pa = new PropertyAccessor<GridEditorColumnConfigDto, OrderDto>() {
		@Override
		public void setValue(GridEditorColumnConfigDto container, OrderDto object) {
			container.setOrder(object);
		}

		@Override
		public OrderDto getValue(GridEditorColumnConfigDto container) {
			return container.getOrder();
		}

		@Override
		public Class<?> getType() {
			return OrderDto.class;
		}

		@Override
		public String getPath() {
			return "order";
		}

		@Override
		public void setModified(GridEditorColumnConfigDto container, boolean modified) {
			container.order_m = modified;
		}

		@Override
		public boolean isModified(GridEditorColumnConfigDto container) {
			return container.isOrderModified();
		}
	};

	private Boolean primaryKey;
	private  boolean primaryKey_m;
	public static final String PROPERTY_PRIMARY_KEY = "dpi-grideditorcolumnconfig-primarykey";

	private transient static PropertyAccessor<GridEditorColumnConfigDto, Boolean> primaryKey_pa = new PropertyAccessor<GridEditorColumnConfigDto, Boolean>() {
		@Override
		public void setValue(GridEditorColumnConfigDto container, Boolean object) {
			container.setPrimaryKey(object);
		}

		@Override
		public Boolean getValue(GridEditorColumnConfigDto container) {
			return container.isPrimaryKey();
		}

		@Override
		public Class<?> getType() {
			return Boolean.class;
		}

		@Override
		public String getPath() {
			return "primaryKey";
		}

		@Override
		public void setModified(GridEditorColumnConfigDto container, boolean modified) {
			container.primaryKey_m = modified;
		}

		@Override
		public boolean isModified(GridEditorColumnConfigDto container) {
			return container.isPrimaryKeyModified();
		}
	};

	private boolean sortable;
	private  boolean sortable_m;
	public static final String PROPERTY_SORTABLE = "dpi-grideditorcolumnconfig-sortable";

	private transient static PropertyAccessor<GridEditorColumnConfigDto, Boolean> sortable_pa = new PropertyAccessor<GridEditorColumnConfigDto, Boolean>() {
		@Override
		public void setValue(GridEditorColumnConfigDto container, Boolean object) {
			container.setSortable(object);
		}

		@Override
		public Boolean getValue(GridEditorColumnConfigDto container) {
			return container.isSortable();
		}

		@Override
		public Class<?> getType() {
			return Boolean.class;
		}

		@Override
		public String getPath() {
			return "sortable";
		}

		@Override
		public void setModified(GridEditorColumnConfigDto container, boolean modified) {
			container.sortable_m = modified;
		}

		@Override
		public boolean isModified(GridEditorColumnConfigDto container) {
			return container.isSortableModified();
		}
	};

	private int type;
	private  boolean type_m;
	public static final String PROPERTY_TYPE = "dpi-grideditorcolumnconfig-type";

	private transient static PropertyAccessor<GridEditorColumnConfigDto, Integer> type_pa = new PropertyAccessor<GridEditorColumnConfigDto, Integer>() {
		@Override
		public void setValue(GridEditorColumnConfigDto container, Integer object) {
			container.setType(object);
		}

		@Override
		public Integer getValue(GridEditorColumnConfigDto container) {
			return container.getType();
		}

		@Override
		public Class<?> getType() {
			return Integer.class;
		}

		@Override
		public String getPath() {
			return "type";
		}

		@Override
		public void setModified(GridEditorColumnConfigDto container, boolean modified) {
			container.type_m = modified;
		}

		@Override
		public boolean isModified(GridEditorColumnConfigDto container) {
			return container.isTypeModified();
		}
	};

	private List<ValidatorDto> validators;
	private  boolean validators_m;
	public static final String PROPERTY_VALIDATORS = "dpi-grideditorcolumnconfig-validators";

	private transient static PropertyAccessor<GridEditorColumnConfigDto, List<ValidatorDto>> validators_pa = new PropertyAccessor<GridEditorColumnConfigDto, List<ValidatorDto>>() {
		@Override
		public void setValue(GridEditorColumnConfigDto container, List<ValidatorDto> object) {
			container.setValidators(object);
		}

		@Override
		public List<ValidatorDto> getValue(GridEditorColumnConfigDto container) {
			return container.getValidators();
		}

		@Override
		public Class<?> getType() {
			return List.class;
		}

		@Override
		public String getPath() {
			return "validators";
		}

		@Override
		public void setModified(GridEditorColumnConfigDto container, boolean modified) {
			container.validators_m = modified;
		}

		@Override
		public boolean isModified(GridEditorColumnConfigDto container) {
			return container.isValidatorsModified();
		}
	};

	private int width;
	private  boolean width_m;
	public static final String PROPERTY_WIDTH = "dpi-grideditorcolumnconfig-width";

	private transient static PropertyAccessor<GridEditorColumnConfigDto, Integer> width_pa = new PropertyAccessor<GridEditorColumnConfigDto, Integer>() {
		@Override
		public void setValue(GridEditorColumnConfigDto container, Integer object) {
			container.setWidth(object);
		}

		@Override
		public Integer getValue(GridEditorColumnConfigDto container) {
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
		public void setModified(GridEditorColumnConfigDto container, boolean modified) {
			container.width_m = modified;
		}

		@Override
		public boolean isModified(GridEditorColumnConfigDto container) {
			return container.isWidthModified();
		}
	};


	public GridEditorColumnConfigDto() {
		super();
	}

	public boolean isDefaultCaseSensitive()  {
		if(! isDtoProxy()){
			return this.defaultCaseSensitive;
		}

		if(isDefaultCaseSensitiveModified())
			return this.defaultCaseSensitive;

		if(! GWT.isClient())
			return false;

		boolean _value = dtoManager.getProperty(this, instantiatePropertyAccess().defaultCaseSensitive());

		return _value;
	}


	public void setDefaultCaseSensitive(boolean defaultCaseSensitive)  {
		/* old value */
		boolean oldValue = false;
		if(GWT.isClient())
			oldValue = isDefaultCaseSensitive();

		/* set new value */
		this.defaultCaseSensitive = defaultCaseSensitive;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(defaultCaseSensitive_pa, oldValue, defaultCaseSensitive, this.defaultCaseSensitive_m));

		/* set indicator */
		this.defaultCaseSensitive_m = true;

		this.fireObjectChangedEvent(GridEditorColumnConfigDtoPA.INSTANCE.defaultCaseSensitive(), oldValue);
	}


	public boolean isDefaultCaseSensitiveModified()  {
		return defaultCaseSensitive_m;
	}


	public static PropertyAccessor<GridEditorColumnConfigDto, Boolean> getDefaultCaseSensitivePropertyAccessor()  {
		return defaultCaseSensitive_pa;
	}


	public GridEditorRecordEntryDto getDefaultValue()  {
		if(! isDtoProxy()){
			return this.defaultValue;
		}

		if(isDefaultValueModified())
			return this.defaultValue;

		if(! GWT.isClient())
			return null;

		GridEditorRecordEntryDto _value = dtoManager.getProperty(this, instantiatePropertyAccess().defaultValue());

		if(_value instanceof HasObjectChangedEventHandler){
			((HasObjectChangedEventHandler)_value).addObjectChangedHandler(new net.datenwerke.gxtdto.client.eventbus.handlers.ObjectChangedEventHandler(){
				@Override
				public void onObjectChangedEvent(net.datenwerke.gxtdto.client.eventbus.events.ObjectChangedEvent event){
					if(! isDefaultValueModified())
						setDefaultValue((GridEditorRecordEntryDto) event.getObject());
				}
			}
			);
		}
		return _value;
	}


	public void setDefaultValue(GridEditorRecordEntryDto defaultValue)  {
		/* old value */
		GridEditorRecordEntryDto oldValue = null;
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

		this.fireObjectChangedEvent(GridEditorColumnConfigDtoPA.INSTANCE.defaultValue(), oldValue);
	}


	public boolean isDefaultValueModified()  {
		return defaultValue_m;
	}


	public static PropertyAccessor<GridEditorColumnConfigDto, GridEditorRecordEntryDto> getDefaultValuePropertyAccessor()  {
		return defaultValue_pa;
	}


	public String getDisplayName()  {
		if(! isDtoProxy()){
			return this.displayName;
		}

		if(isDisplayNameModified())
			return this.displayName;

		if(! GWT.isClient())
			return null;

		String _value = dtoManager.getProperty(this, instantiatePropertyAccess().displayName());

		return _value;
	}


	public void setDisplayName(String displayName)  {
		/* old value */
		String oldValue = null;
		if(GWT.isClient())
			oldValue = getDisplayName();

		/* set new value */
		this.displayName = displayName;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(displayName_pa, oldValue, displayName, this.displayName_m));

		/* set indicator */
		this.displayName_m = true;

		this.fireObjectChangedEvent(GridEditorColumnConfigDtoPA.INSTANCE.displayName(), oldValue);
	}


	public boolean isDisplayNameModified()  {
		return displayName_m;
	}


	public static PropertyAccessor<GridEditorColumnConfigDto, String> getDisplayNamePropertyAccessor()  {
		return displayName_pa;
	}


	public boolean isEditable()  {
		if(! isDtoProxy()){
			return this.editable;
		}

		if(isEditableModified())
			return this.editable;

		if(! GWT.isClient())
			return false;

		boolean _value = dtoManager.getProperty(this, instantiatePropertyAccess().editable());

		return _value;
	}


	public void setEditable(boolean editable)  {
		/* old value */
		boolean oldValue = false;
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

		this.fireObjectChangedEvent(GridEditorColumnConfigDtoPA.INSTANCE.editable(), oldValue);
	}


	public boolean isEditableModified()  {
		return editable_m;
	}


	public static PropertyAccessor<GridEditorColumnConfigDto, Boolean> getEditablePropertyAccessor()  {
		return editable_pa;
	}


	public EditorDto getEditor()  {
		if(! isDtoProxy()){
			return this.editor;
		}

		if(isEditorModified())
			return this.editor;

		if(! GWT.isClient())
			return null;

		EditorDto _value = dtoManager.getProperty(this, instantiatePropertyAccess().editor());

		if(_value instanceof HasObjectChangedEventHandler){
			((HasObjectChangedEventHandler)_value).addObjectChangedHandler(new net.datenwerke.gxtdto.client.eventbus.handlers.ObjectChangedEventHandler(){
				@Override
				public void onObjectChangedEvent(net.datenwerke.gxtdto.client.eventbus.events.ObjectChangedEvent event){
					if(! isEditorModified())
						setEditor((EditorDto) event.getObject());
				}
			}
			);
		}
		return _value;
	}


	public void setEditor(EditorDto editor)  {
		/* old value */
		EditorDto oldValue = null;
		if(GWT.isClient())
			oldValue = getEditor();

		/* set new value */
		this.editor = editor;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(editor_pa, oldValue, editor, this.editor_m));

		/* set indicator */
		this.editor_m = true;

		this.fireObjectChangedEvent(GridEditorColumnConfigDtoPA.INSTANCE.editor(), oldValue);
	}


	public boolean isEditorModified()  {
		return editor_m;
	}


	public static PropertyAccessor<GridEditorColumnConfigDto, EditorDto> getEditorPropertyAccessor()  {
		return editor_pa;
	}


	public boolean isEnforceCaseSensitivity()  {
		if(! isDtoProxy()){
			return this.enforceCaseSensitivity;
		}

		if(isEnforceCaseSensitivityModified())
			return this.enforceCaseSensitivity;

		if(! GWT.isClient())
			return false;

		boolean _value = dtoManager.getProperty(this, instantiatePropertyAccess().enforceCaseSensitivity());

		return _value;
	}


	public void setEnforceCaseSensitivity(boolean enforceCaseSensitivity)  {
		/* old value */
		boolean oldValue = false;
		if(GWT.isClient())
			oldValue = isEnforceCaseSensitivity();

		/* set new value */
		this.enforceCaseSensitivity = enforceCaseSensitivity;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(enforceCaseSensitivity_pa, oldValue, enforceCaseSensitivity, this.enforceCaseSensitivity_m));

		/* set indicator */
		this.enforceCaseSensitivity_m = true;

		this.fireObjectChangedEvent(GridEditorColumnConfigDtoPA.INSTANCE.enforceCaseSensitivity(), oldValue);
	}


	public boolean isEnforceCaseSensitivityModified()  {
		return enforceCaseSensitivity_m;
	}


	public static PropertyAccessor<GridEditorColumnConfigDto, Boolean> getEnforceCaseSensitivityPropertyAccessor()  {
		return enforceCaseSensitivity_pa;
	}


	public boolean isFilterable()  {
		if(! isDtoProxy()){
			return this.filterable;
		}

		if(isFilterableModified())
			return this.filterable;

		if(! GWT.isClient())
			return false;

		boolean _value = dtoManager.getProperty(this, instantiatePropertyAccess().filterable());

		return _value;
	}


	public void setFilterable(boolean filterable)  {
		/* old value */
		boolean oldValue = false;
		if(GWT.isClient())
			oldValue = isFilterable();

		/* set new value */
		this.filterable = filterable;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(filterable_pa, oldValue, filterable, this.filterable_m));

		/* set indicator */
		this.filterable_m = true;

		this.fireObjectChangedEvent(GridEditorColumnConfigDtoPA.INSTANCE.filterable(), oldValue);
	}


	public boolean isFilterableModified()  {
		return filterable_m;
	}


	public static PropertyAccessor<GridEditorColumnConfigDto, Boolean> getFilterablePropertyAccessor()  {
		return filterable_pa;
	}


	public boolean isHidden()  {
		if(! isDtoProxy()){
			return this.hidden;
		}

		if(isHiddenModified())
			return this.hidden;

		if(! GWT.isClient())
			return false;

		boolean _value = dtoManager.getProperty(this, instantiatePropertyAccess().hidden());

		return _value;
	}


	public void setHidden(boolean hidden)  {
		/* old value */
		boolean oldValue = false;
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

		this.fireObjectChangedEvent(GridEditorColumnConfigDtoPA.INSTANCE.hidden(), oldValue);
	}


	public boolean isHiddenModified()  {
		return hidden_m;
	}


	public static PropertyAccessor<GridEditorColumnConfigDto, Boolean> getHiddenPropertyAccessor()  {
		return hidden_pa;
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

		this.fireObjectChangedEvent(GridEditorColumnConfigDtoPA.INSTANCE.name(), oldValue);
	}


	public boolean isNameModified()  {
		return name_m;
	}


	public static PropertyAccessor<GridEditorColumnConfigDto, String> getNamePropertyAccessor()  {
		return name_pa;
	}


	public OrderDto getOrder()  {
		if(! isDtoProxy()){
			return this.order;
		}

		if(isOrderModified())
			return this.order;

		if(! GWT.isClient())
			return null;

		OrderDto _value = dtoManager.getProperty(this, instantiatePropertyAccess().order());

		return _value;
	}


	public void setOrder(OrderDto order)  {
		/* old value */
		OrderDto oldValue = null;
		if(GWT.isClient())
			oldValue = getOrder();

		/* set new value */
		this.order = order;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(order_pa, oldValue, order, this.order_m));

		/* set indicator */
		this.order_m = true;

		this.fireObjectChangedEvent(GridEditorColumnConfigDtoPA.INSTANCE.order(), oldValue);
	}


	public boolean isOrderModified()  {
		return order_m;
	}


	public static PropertyAccessor<GridEditorColumnConfigDto, OrderDto> getOrderPropertyAccessor()  {
		return order_pa;
	}


	public Boolean isPrimaryKey()  {
		if(! isDtoProxy()){
			return this.primaryKey;
		}

		if(isPrimaryKeyModified())
			return this.primaryKey;

		if(! GWT.isClient())
			return null;

		Boolean _value = dtoManager.getProperty(this, instantiatePropertyAccess().primaryKey());

		return _value;
	}


	public void setPrimaryKey(Boolean primaryKey)  {
		/* old value */
		Boolean oldValue = null;
		if(GWT.isClient())
			oldValue = isPrimaryKey();

		/* set new value */
		this.primaryKey = primaryKey;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(primaryKey_pa, oldValue, primaryKey, this.primaryKey_m));

		/* set indicator */
		this.primaryKey_m = true;

		this.fireObjectChangedEvent(GridEditorColumnConfigDtoPA.INSTANCE.primaryKey(), oldValue);
	}


	public boolean isPrimaryKeyModified()  {
		return primaryKey_m;
	}


	public static PropertyAccessor<GridEditorColumnConfigDto, Boolean> getPrimaryKeyPropertyAccessor()  {
		return primaryKey_pa;
	}


	public boolean isSortable()  {
		if(! isDtoProxy()){
			return this.sortable;
		}

		if(isSortableModified())
			return this.sortable;

		if(! GWT.isClient())
			return false;

		boolean _value = dtoManager.getProperty(this, instantiatePropertyAccess().sortable());

		return _value;
	}


	public void setSortable(boolean sortable)  {
		/* old value */
		boolean oldValue = false;
		if(GWT.isClient())
			oldValue = isSortable();

		/* set new value */
		this.sortable = sortable;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(sortable_pa, oldValue, sortable, this.sortable_m));

		/* set indicator */
		this.sortable_m = true;

		this.fireObjectChangedEvent(GridEditorColumnConfigDtoPA.INSTANCE.sortable(), oldValue);
	}


	public boolean isSortableModified()  {
		return sortable_m;
	}


	public static PropertyAccessor<GridEditorColumnConfigDto, Boolean> getSortablePropertyAccessor()  {
		return sortable_pa;
	}


	public int getType()  {
		if(! isDtoProxy()){
			return this.type;
		}

		if(isTypeModified())
			return this.type;

		if(! GWT.isClient())
			return 0;

		int _value = dtoManager.getProperty(this, instantiatePropertyAccess().type());

		return _value;
	}


	public void setType(int type)  {
		/* old value */
		int oldValue = 0;
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

		this.fireObjectChangedEvent(GridEditorColumnConfigDtoPA.INSTANCE.type(), oldValue);
	}


	public boolean isTypeModified()  {
		return type_m;
	}


	public static PropertyAccessor<GridEditorColumnConfigDto, Integer> getTypePropertyAccessor()  {
		return type_pa;
	}


	public List<ValidatorDto> getValidators()  {
		if(! isDtoProxy()){
			List<ValidatorDto> _currentValue = this.validators;
			if(null == _currentValue)
				this.validators = new ArrayList<ValidatorDto>();

			return this.validators;
		}

		if(isValidatorsModified())
			return this.validators;

		if(! GWT.isClient())
			return null;

		List<ValidatorDto> _value = dtoManager.getProperty(this, instantiatePropertyAccess().validators());

		_value = new ChangeMonitoredList<ValidatorDto>(_value);
		if(_value instanceof HasObjectChangedEventHandler){
			((HasObjectChangedEventHandler)_value).addObjectChangedHandler(new net.datenwerke.gxtdto.client.eventbus.handlers.ObjectChangedEventHandler(){
				@Override
				public void onObjectChangedEvent(net.datenwerke.gxtdto.client.eventbus.events.ObjectChangedEvent event){
					if(! isValidatorsModified())
						setValidators((List<ValidatorDto>) ((MonitoredCollection) event.getObject()).getUnderlyingCollection());
				}
			}
			);
		}
		return _value;
	}


	public void setValidators(List<ValidatorDto> validators)  {
		/* old value */
		List<ValidatorDto> oldValue = null;
		if(GWT.isClient())
			oldValue = getValidators();

		/* set new value */
		this.validators = validators;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(validators_pa, oldValue, validators, this.validators_m));

		/* set indicator */
		this.validators_m = true;

		this.fireObjectChangedEvent(GridEditorColumnConfigDtoPA.INSTANCE.validators(), oldValue);
	}


	public boolean isValidatorsModified()  {
		return validators_m;
	}


	public static PropertyAccessor<GridEditorColumnConfigDto, List<ValidatorDto>> getValidatorsPropertyAccessor()  {
		return validators_pa;
	}


	public int getWidth()  {
		if(! isDtoProxy()){
			return this.width;
		}

		if(isWidthModified())
			return this.width;

		if(! GWT.isClient())
			return 0;

		int _value = dtoManager.getProperty(this, instantiatePropertyAccess().width());

		return _value;
	}


	public void setWidth(int width)  {
		/* old value */
		int oldValue = 0;
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

		this.fireObjectChangedEvent(GridEditorColumnConfigDtoPA.INSTANCE.width(), oldValue);
	}


	public boolean isWidthModified()  {
		return width_m;
	}


	public static PropertyAccessor<GridEditorColumnConfigDto, Integer> getWidthPropertyAccessor()  {
		return width_pa;
	}


	@Override
	public String toString()  {
		return super.toString();
	}

	public static Dto2PosoMapper newPosoMapper()  {
		return new GridEditorColumnConfigDto2PosoMap();
	}

	public GridEditorColumnConfigDtoPA instantiatePropertyAccess()  {
		return GWT.create(GridEditorColumnConfigDtoPA.class);
	}

	public void clearModified()  {
		this.defaultCaseSensitive = false;
		this.defaultCaseSensitive_m = false;
		this.defaultValue = null;
		this.defaultValue_m = false;
		this.displayName = null;
		this.displayName_m = false;
		this.editable = false;
		this.editable_m = false;
		this.editor = null;
		this.editor_m = false;
		this.enforceCaseSensitivity = false;
		this.enforceCaseSensitivity_m = false;
		this.filterable = false;
		this.filterable_m = false;
		this.hidden = false;
		this.hidden_m = false;
		this.name = null;
		this.name_m = false;
		this.order = null;
		this.order_m = false;
		this.primaryKey = null;
		this.primaryKey_m = false;
		this.sortable = false;
		this.sortable_m = false;
		this.type = 0;
		this.type_m = false;
		this.validators = null;
		this.validators_m = false;
		this.width = 0;
		this.width_m = false;
	}


	public boolean isModified()  {
		if(super.isModified())
			return true;
		if(defaultCaseSensitive_m)
			return true;
		if(defaultValue_m)
			return true;
		if(displayName_m)
			return true;
		if(editable_m)
			return true;
		if(editor_m)
			return true;
		if(enforceCaseSensitivity_m)
			return true;
		if(filterable_m)
			return true;
		if(hidden_m)
			return true;
		if(name_m)
			return true;
		if(order_m)
			return true;
		if(primaryKey_m)
			return true;
		if(sortable_m)
			return true;
		if(type_m)
			return true;
		if(validators_m)
			return true;
		if(width_m)
			return true;
		return false;
	}


	public List<PropertyAccessor> getPropertyAccessors()  {
		List<PropertyAccessor> list = super.getPropertyAccessors();
		list.add(defaultCaseSensitive_pa);
		list.add(defaultValue_pa);
		list.add(displayName_pa);
		list.add(editable_pa);
		list.add(editor_pa);
		list.add(enforceCaseSensitivity_pa);
		list.add(filterable_pa);
		list.add(hidden_pa);
		list.add(name_pa);
		list.add(order_pa);
		list.add(primaryKey_pa);
		list.add(sortable_pa);
		list.add(type_pa);
		list.add(validators_pa);
		list.add(width_pa);
		return list;
	}


	public List<PropertyAccessor> getModifiedPropertyAccessors()  {
		List<PropertyAccessor> list = super.getModifiedPropertyAccessors();
		if(defaultCaseSensitive_m)
			list.add(defaultCaseSensitive_pa);
		if(defaultValue_m)
			list.add(defaultValue_pa);
		if(displayName_m)
			list.add(displayName_pa);
		if(editable_m)
			list.add(editable_pa);
		if(editor_m)
			list.add(editor_pa);
		if(enforceCaseSensitivity_m)
			list.add(enforceCaseSensitivity_pa);
		if(filterable_m)
			list.add(filterable_pa);
		if(hidden_m)
			list.add(hidden_pa);
		if(name_m)
			list.add(name_pa);
		if(order_m)
			list.add(order_pa);
		if(primaryKey_m)
			list.add(primaryKey_pa);
		if(sortable_m)
			list.add(sortable_pa);
		if(type_m)
			list.add(type_pa);
		if(validators_m)
			list.add(validators_pa);
		if(width_m)
			list.add(width_pa);
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsByView(net.datenwerke.gxtdto.client.dtomanager.DtoView view)  {
		List<PropertyAccessor> list = super.getPropertyAccessorsByView(view);
		if(view.compareTo(DtoView.NORMAL) >= 0){
			list.add(defaultCaseSensitive_pa);
			list.add(defaultValue_pa);
			list.add(displayName_pa);
			list.add(editable_pa);
			list.add(editor_pa);
			list.add(enforceCaseSensitivity_pa);
			list.add(filterable_pa);
			list.add(hidden_pa);
			list.add(name_pa);
			list.add(order_pa);
			list.add(primaryKey_pa);
			list.add(sortable_pa);
			list.add(type_pa);
			list.add(validators_pa);
			list.add(width_pa);
		}
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsForDtos()  {
		List<PropertyAccessor> list = super.getPropertyAccessorsForDtos();
		list.add(defaultValue_pa);
		list.add(editor_pa);
		list.add(validators_pa);
		return list;
	}



	net.datenwerke.rs.grideditor.client.grideditor.dto.EditorDto wl_0;
	net.datenwerke.rs.grideditor.client.grideditor.dto.ValidatorDto wl_1;
	net.datenwerke.rs.grideditor.client.grideditor.dto.GridEditorRecordEntryDto wl_2;
	net.datenwerke.rs.base.client.reportengines.table.dto.OrderDto wl_3;

}
