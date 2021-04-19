package net.datenwerke.rs.scripting.client.scripting.dto;

import com.google.gwt.core.client.GWT;
import java.lang.String;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
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
import net.datenwerke.rs.scripting.client.scripting.dto.AddMenuEntryExtensionDto;
import net.datenwerke.rs.scripting.client.scripting.dto.AddToolbarEntryExtensionDto;
import net.datenwerke.rs.scripting.client.scripting.dto.DisplayConditionDto;
import net.datenwerke.rs.scripting.client.scripting.dto.pa.AddToolbarEntryExtensionDtoPA;
import net.datenwerke.rs.scripting.client.scripting.dto.posomap.AddToolbarEntryExtensionDto2PosoMap;
import net.datenwerke.rs.scripting.service.scripting.extensions.AddToolbarEntryExtension;
import net.datenwerke.rs.terminal.client.terminal.dto.CommandResultExtensionDto;

/**
 * Dto for {@link AddToolbarEntryExtension}
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class AddToolbarEntryExtensionDto extends CommandResultExtensionDto {


	private static final long serialVersionUID = 1;


	/* Fields */
	private String arguments;
	private  boolean arguments_m;
	public static final String PROPERTY_ARGUMENTS = "dpi-addtoolbarentryextension-arguments";

	private transient static PropertyAccessor<AddToolbarEntryExtensionDto, String> arguments_pa = new PropertyAccessor<AddToolbarEntryExtensionDto, String>() {
		@Override
		public void setValue(AddToolbarEntryExtensionDto container, String object) {
			container.setArguments(object);
		}

		@Override
		public String getValue(AddToolbarEntryExtensionDto container) {
			return container.getArguments();
		}

		@Override
		public Class<?> getType() {
			return String.class;
		}

		@Override
		public String getPath() {
			return "arguments";
		}

		@Override
		public void setModified(AddToolbarEntryExtensionDto container, boolean modified) {
			container.arguments_m = modified;
		}

		@Override
		public boolean isModified(AddToolbarEntryExtensionDto container) {
			return container.isArgumentsModified();
		}
	};

	private int columns;
	private  boolean columns_m;
	public static final String PROPERTY_COLUMNS = "dpi-addtoolbarentryextension-columns";

	private transient static PropertyAccessor<AddToolbarEntryExtensionDto, Integer> columns_pa = new PropertyAccessor<AddToolbarEntryExtensionDto, Integer>() {
		@Override
		public void setValue(AddToolbarEntryExtensionDto container, Integer object) {
			container.setColumns(object);
		}

		@Override
		public Integer getValue(AddToolbarEntryExtensionDto container) {
			return container.getColumns();
		}

		@Override
		public Class<?> getType() {
			return Integer.class;
		}

		@Override
		public String getPath() {
			return "columns";
		}

		@Override
		public void setModified(AddToolbarEntryExtensionDto container, boolean modified) {
			container.columns_m = modified;
		}

		@Override
		public boolean isModified(AddToolbarEntryExtensionDto container) {
			return container.isColumnsModified();
		}
	};

	private List<DisplayConditionDto> displayConditions;
	private  boolean displayConditions_m;
	public static final String PROPERTY_DISPLAY_CONDITIONS = "dpi-addtoolbarentryextension-displayconditions";

	private transient static PropertyAccessor<AddToolbarEntryExtensionDto, List<DisplayConditionDto>> displayConditions_pa = new PropertyAccessor<AddToolbarEntryExtensionDto, List<DisplayConditionDto>>() {
		@Override
		public void setValue(AddToolbarEntryExtensionDto container, List<DisplayConditionDto> object) {
			container.setDisplayConditions(object);
		}

		@Override
		public List<DisplayConditionDto> getValue(AddToolbarEntryExtensionDto container) {
			return container.getDisplayConditions();
		}

		@Override
		public Class<?> getType() {
			return List.class;
		}

		@Override
		public String getPath() {
			return "displayConditions";
		}

		@Override
		public void setModified(AddToolbarEntryExtensionDto container, boolean modified) {
			container.displayConditions_m = modified;
		}

		@Override
		public boolean isModified(AddToolbarEntryExtensionDto container) {
			return container.isDisplayConditionsModified();
		}
	};

	private List<AddToolbarEntryExtensionDto> groupEntries;
	private  boolean groupEntries_m;
	public static final String PROPERTY_GROUP_ENTRIES = "dpi-addtoolbarentryextension-groupentries";

	private transient static PropertyAccessor<AddToolbarEntryExtensionDto, List<AddToolbarEntryExtensionDto>> groupEntries_pa = new PropertyAccessor<AddToolbarEntryExtensionDto, List<AddToolbarEntryExtensionDto>>() {
		@Override
		public void setValue(AddToolbarEntryExtensionDto container, List<AddToolbarEntryExtensionDto> object) {
			container.setGroupEntries(object);
		}

		@Override
		public List<AddToolbarEntryExtensionDto> getValue(AddToolbarEntryExtensionDto container) {
			return container.getGroupEntries();
		}

		@Override
		public Class<?> getType() {
			return List.class;
		}

		@Override
		public String getPath() {
			return "groupEntries";
		}

		@Override
		public void setModified(AddToolbarEntryExtensionDto container, boolean modified) {
			container.groupEntries_m = modified;
		}

		@Override
		public boolean isModified(AddToolbarEntryExtensionDto container) {
			return container.isGroupEntriesModified();
		}
	};

	private String icon;
	private  boolean icon_m;
	public static final String PROPERTY_ICON = "dpi-addtoolbarentryextension-icon";

	private transient static PropertyAccessor<AddToolbarEntryExtensionDto, String> icon_pa = new PropertyAccessor<AddToolbarEntryExtensionDto, String>() {
		@Override
		public void setValue(AddToolbarEntryExtensionDto container, String object) {
			container.setIcon(object);
		}

		@Override
		public String getValue(AddToolbarEntryExtensionDto container) {
			return container.getIcon();
		}

		@Override
		public Class<?> getType() {
			return String.class;
		}

		@Override
		public String getPath() {
			return "icon";
		}

		@Override
		public void setModified(AddToolbarEntryExtensionDto container, boolean modified) {
			container.icon_m = modified;
		}

		@Override
		public boolean isModified(AddToolbarEntryExtensionDto container) {
			return container.isIconModified();
		}
	};

	private String javaScript;
	private  boolean javaScript_m;
	public static final String PROPERTY_JAVA_SCRIPT = "dpi-addtoolbarentryextension-javascript";

	private transient static PropertyAccessor<AddToolbarEntryExtensionDto, String> javaScript_pa = new PropertyAccessor<AddToolbarEntryExtensionDto, String>() {
		@Override
		public void setValue(AddToolbarEntryExtensionDto container, String object) {
			container.setJavaScript(object);
		}

		@Override
		public String getValue(AddToolbarEntryExtensionDto container) {
			return container.getJavaScript();
		}

		@Override
		public Class<?> getType() {
			return String.class;
		}

		@Override
		public String getPath() {
			return "javaScript";
		}

		@Override
		public void setModified(AddToolbarEntryExtensionDto container, boolean modified) {
			container.javaScript_m = modified;
		}

		@Override
		public boolean isModified(AddToolbarEntryExtensionDto container) {
			return container.isJavaScriptModified();
		}
	};

	private String label;
	private  boolean label_m;
	public static final String PROPERTY_LABEL = "dpi-addtoolbarentryextension-label";

	private transient static PropertyAccessor<AddToolbarEntryExtensionDto, String> label_pa = new PropertyAccessor<AddToolbarEntryExtensionDto, String>() {
		@Override
		public void setValue(AddToolbarEntryExtensionDto container, String object) {
			container.setLabel(object);
		}

		@Override
		public String getValue(AddToolbarEntryExtensionDto container) {
			return container.getLabel();
		}

		@Override
		public Class<?> getType() {
			return String.class;
		}

		@Override
		public String getPath() {
			return "label";
		}

		@Override
		public void setModified(AddToolbarEntryExtensionDto container, boolean modified) {
			container.label_m = modified;
		}

		@Override
		public boolean isModified(AddToolbarEntryExtensionDto container) {
			return container.isLabelModified();
		}
	};

	private boolean large;
	private  boolean large_m;
	public static final String PROPERTY_LARGE = "dpi-addtoolbarentryextension-large";

	private transient static PropertyAccessor<AddToolbarEntryExtensionDto, Boolean> large_pa = new PropertyAccessor<AddToolbarEntryExtensionDto, Boolean>() {
		@Override
		public void setValue(AddToolbarEntryExtensionDto container, Boolean object) {
			container.setLarge(object);
		}

		@Override
		public Boolean getValue(AddToolbarEntryExtensionDto container) {
			return container.isLarge();
		}

		@Override
		public Class<?> getType() {
			return Boolean.class;
		}

		@Override
		public String getPath() {
			return "large";
		}

		@Override
		public void setModified(AddToolbarEntryExtensionDto container, boolean modified) {
			container.large_m = modified;
		}

		@Override
		public boolean isModified(AddToolbarEntryExtensionDto container) {
			return container.isLargeModified();
		}
	};

	private boolean left;
	private  boolean left_m;
	public static final String PROPERTY_LEFT = "dpi-addtoolbarentryextension-left";

	private transient static PropertyAccessor<AddToolbarEntryExtensionDto, Boolean> left_pa = new PropertyAccessor<AddToolbarEntryExtensionDto, Boolean>() {
		@Override
		public void setValue(AddToolbarEntryExtensionDto container, Boolean object) {
			container.setLeft(object);
		}

		@Override
		public Boolean getValue(AddToolbarEntryExtensionDto container) {
			return container.isLeft();
		}

		@Override
		public Class<?> getType() {
			return Boolean.class;
		}

		@Override
		public String getPath() {
			return "left";
		}

		@Override
		public void setModified(AddToolbarEntryExtensionDto container, boolean modified) {
			container.left_m = modified;
		}

		@Override
		public boolean isModified(AddToolbarEntryExtensionDto container) {
			return container.isLeftModified();
		}
	};

	private String scriptLocation;
	private  boolean scriptLocation_m;
	public static final String PROPERTY_SCRIPT_LOCATION = "dpi-addtoolbarentryextension-scriptlocation";

	private transient static PropertyAccessor<AddToolbarEntryExtensionDto, String> scriptLocation_pa = new PropertyAccessor<AddToolbarEntryExtensionDto, String>() {
		@Override
		public void setValue(AddToolbarEntryExtensionDto container, String object) {
			container.setScriptLocation(object);
		}

		@Override
		public String getValue(AddToolbarEntryExtensionDto container) {
			return container.getScriptLocation();
		}

		@Override
		public Class<?> getType() {
			return String.class;
		}

		@Override
		public String getPath() {
			return "scriptLocation";
		}

		@Override
		public void setModified(AddToolbarEntryExtensionDto container, boolean modified) {
			container.scriptLocation_m = modified;
		}

		@Override
		public boolean isModified(AddToolbarEntryExtensionDto container) {
			return container.isScriptLocationModified();
		}
	};

	private List<AddMenuEntryExtensionDto> subMenuEntries;
	private  boolean subMenuEntries_m;
	public static final String PROPERTY_SUB_MENU_ENTRIES = "dpi-addtoolbarentryextension-submenuentries";

	private transient static PropertyAccessor<AddToolbarEntryExtensionDto, List<AddMenuEntryExtensionDto>> subMenuEntries_pa = new PropertyAccessor<AddToolbarEntryExtensionDto, List<AddMenuEntryExtensionDto>>() {
		@Override
		public void setValue(AddToolbarEntryExtensionDto container, List<AddMenuEntryExtensionDto> object) {
			container.setSubMenuEntries(object);
		}

		@Override
		public List<AddMenuEntryExtensionDto> getValue(AddToolbarEntryExtensionDto container) {
			return container.getSubMenuEntries();
		}

		@Override
		public Class<?> getType() {
			return List.class;
		}

		@Override
		public String getPath() {
			return "subMenuEntries";
		}

		@Override
		public void setModified(AddToolbarEntryExtensionDto container, boolean modified) {
			container.subMenuEntries_m = modified;
		}

		@Override
		public boolean isModified(AddToolbarEntryExtensionDto container) {
			return container.isSubMenuEntriesModified();
		}
	};

	private String toolbarName;
	private  boolean toolbarName_m;
	public static final String PROPERTY_TOOLBAR_NAME = "dpi-addtoolbarentryextension-toolbarname";

	private transient static PropertyAccessor<AddToolbarEntryExtensionDto, String> toolbarName_pa = new PropertyAccessor<AddToolbarEntryExtensionDto, String>() {
		@Override
		public void setValue(AddToolbarEntryExtensionDto container, String object) {
			container.setToolbarName(object);
		}

		@Override
		public String getValue(AddToolbarEntryExtensionDto container) {
			return container.getToolbarName();
		}

		@Override
		public Class<?> getType() {
			return String.class;
		}

		@Override
		public String getPath() {
			return "toolbarName";
		}

		@Override
		public void setModified(AddToolbarEntryExtensionDto container, boolean modified) {
			container.toolbarName_m = modified;
		}

		@Override
		public boolean isModified(AddToolbarEntryExtensionDto container) {
			return container.isToolbarNameModified();
		}
	};


	public AddToolbarEntryExtensionDto() {
		super();
	}

	public String getArguments()  {
		if(! isDtoProxy()){
			return this.arguments;
		}

		if(isArgumentsModified())
			return this.arguments;

		if(! GWT.isClient())
			return null;

		String _value = dtoManager.getProperty(this, instantiatePropertyAccess().arguments());

		return _value;
	}


	public void setArguments(String arguments)  {
		/* old value */
		String oldValue = null;
		if(GWT.isClient())
			oldValue = getArguments();

		/* set new value */
		this.arguments = arguments;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(arguments_pa, oldValue, arguments, this.arguments_m));

		/* set indicator */
		this.arguments_m = true;

		this.fireObjectChangedEvent(AddToolbarEntryExtensionDtoPA.INSTANCE.arguments(), oldValue);
	}


	public boolean isArgumentsModified()  {
		return arguments_m;
	}


	public static PropertyAccessor<AddToolbarEntryExtensionDto, String> getArgumentsPropertyAccessor()  {
		return arguments_pa;
	}


	public int getColumns()  {
		if(! isDtoProxy()){
			return this.columns;
		}

		if(isColumnsModified())
			return this.columns;

		if(! GWT.isClient())
			return 0;

		int _value = dtoManager.getProperty(this, instantiatePropertyAccess().columns());

		return _value;
	}


	public void setColumns(int columns)  {
		/* old value */
		int oldValue = 0;
		if(GWT.isClient())
			oldValue = getColumns();

		/* set new value */
		this.columns = columns;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(columns_pa, oldValue, columns, this.columns_m));

		/* set indicator */
		this.columns_m = true;

		this.fireObjectChangedEvent(AddToolbarEntryExtensionDtoPA.INSTANCE.columns(), oldValue);
	}


	public boolean isColumnsModified()  {
		return columns_m;
	}


	public static PropertyAccessor<AddToolbarEntryExtensionDto, Integer> getColumnsPropertyAccessor()  {
		return columns_pa;
	}


	public List<DisplayConditionDto> getDisplayConditions()  {
		if(! isDtoProxy()){
			List<DisplayConditionDto> _currentValue = this.displayConditions;
			if(null == _currentValue)
				this.displayConditions = new ArrayList<DisplayConditionDto>();

			return this.displayConditions;
		}

		if(isDisplayConditionsModified())
			return this.displayConditions;

		if(! GWT.isClient())
			return null;

		List<DisplayConditionDto> _value = dtoManager.getProperty(this, instantiatePropertyAccess().displayConditions());

		_value = new ChangeMonitoredList<DisplayConditionDto>(_value);
		if(_value instanceof HasObjectChangedEventHandler){
			((HasObjectChangedEventHandler)_value).addObjectChangedHandler(new net.datenwerke.gxtdto.client.eventbus.handlers.ObjectChangedEventHandler(){
				@Override
				public void onObjectChangedEvent(net.datenwerke.gxtdto.client.eventbus.events.ObjectChangedEvent event){
					if(! isDisplayConditionsModified())
						setDisplayConditions((List<DisplayConditionDto>) ((MonitoredCollection) event.getObject()).getUnderlyingCollection());
				}
			}
			);
		}
		return _value;
	}


	public void setDisplayConditions(List<DisplayConditionDto> displayConditions)  {
		/* old value */
		List<DisplayConditionDto> oldValue = null;
		if(GWT.isClient())
			oldValue = getDisplayConditions();

		/* set new value */
		this.displayConditions = displayConditions;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(displayConditions_pa, oldValue, displayConditions, this.displayConditions_m));

		/* set indicator */
		this.displayConditions_m = true;

		this.fireObjectChangedEvent(AddToolbarEntryExtensionDtoPA.INSTANCE.displayConditions(), oldValue);
	}


	public boolean isDisplayConditionsModified()  {
		return displayConditions_m;
	}


	public static PropertyAccessor<AddToolbarEntryExtensionDto, List<DisplayConditionDto>> getDisplayConditionsPropertyAccessor()  {
		return displayConditions_pa;
	}


	public List<AddToolbarEntryExtensionDto> getGroupEntries()  {
		if(! isDtoProxy()){
			List<AddToolbarEntryExtensionDto> _currentValue = this.groupEntries;
			if(null == _currentValue)
				this.groupEntries = new ArrayList<AddToolbarEntryExtensionDto>();

			return this.groupEntries;
		}

		if(isGroupEntriesModified())
			return this.groupEntries;

		if(! GWT.isClient())
			return null;

		List<AddToolbarEntryExtensionDto> _value = dtoManager.getProperty(this, instantiatePropertyAccess().groupEntries());

		_value = new ChangeMonitoredList<AddToolbarEntryExtensionDto>(_value);
		if(_value instanceof HasObjectChangedEventHandler){
			((HasObjectChangedEventHandler)_value).addObjectChangedHandler(new net.datenwerke.gxtdto.client.eventbus.handlers.ObjectChangedEventHandler(){
				@Override
				public void onObjectChangedEvent(net.datenwerke.gxtdto.client.eventbus.events.ObjectChangedEvent event){
					if(! isGroupEntriesModified())
						setGroupEntries((List<AddToolbarEntryExtensionDto>) ((MonitoredCollection) event.getObject()).getUnderlyingCollection());
				}
			}
			);
		}
		return _value;
	}


	public void setGroupEntries(List<AddToolbarEntryExtensionDto> groupEntries)  {
		/* old value */
		List<AddToolbarEntryExtensionDto> oldValue = null;
		if(GWT.isClient())
			oldValue = getGroupEntries();

		/* set new value */
		this.groupEntries = groupEntries;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(groupEntries_pa, oldValue, groupEntries, this.groupEntries_m));

		/* set indicator */
		this.groupEntries_m = true;

		this.fireObjectChangedEvent(AddToolbarEntryExtensionDtoPA.INSTANCE.groupEntries(), oldValue);
	}


	public boolean isGroupEntriesModified()  {
		return groupEntries_m;
	}


	public static PropertyAccessor<AddToolbarEntryExtensionDto, List<AddToolbarEntryExtensionDto>> getGroupEntriesPropertyAccessor()  {
		return groupEntries_pa;
	}


	public String getIcon()  {
		if(! isDtoProxy()){
			return this.icon;
		}

		if(isIconModified())
			return this.icon;

		if(! GWT.isClient())
			return null;

		String _value = dtoManager.getProperty(this, instantiatePropertyAccess().icon());

		return _value;
	}


	public void setIcon(String icon)  {
		/* old value */
		String oldValue = null;
		if(GWT.isClient())
			oldValue = getIcon();

		/* set new value */
		this.icon = icon;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(icon_pa, oldValue, icon, this.icon_m));

		/* set indicator */
		this.icon_m = true;

		this.fireObjectChangedEvent(AddToolbarEntryExtensionDtoPA.INSTANCE.icon(), oldValue);
	}


	public boolean isIconModified()  {
		return icon_m;
	}


	public static PropertyAccessor<AddToolbarEntryExtensionDto, String> getIconPropertyAccessor()  {
		return icon_pa;
	}


	public String getJavaScript()  {
		if(! isDtoProxy()){
			return this.javaScript;
		}

		if(isJavaScriptModified())
			return this.javaScript;

		if(! GWT.isClient())
			return null;

		String _value = dtoManager.getProperty(this, instantiatePropertyAccess().javaScript());

		return _value;
	}


	public void setJavaScript(String javaScript)  {
		/* old value */
		String oldValue = null;
		if(GWT.isClient())
			oldValue = getJavaScript();

		/* set new value */
		this.javaScript = javaScript;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(javaScript_pa, oldValue, javaScript, this.javaScript_m));

		/* set indicator */
		this.javaScript_m = true;

		this.fireObjectChangedEvent(AddToolbarEntryExtensionDtoPA.INSTANCE.javaScript(), oldValue);
	}


	public boolean isJavaScriptModified()  {
		return javaScript_m;
	}


	public static PropertyAccessor<AddToolbarEntryExtensionDto, String> getJavaScriptPropertyAccessor()  {
		return javaScript_pa;
	}


	public String getLabel()  {
		if(! isDtoProxy()){
			return this.label;
		}

		if(isLabelModified())
			return this.label;

		if(! GWT.isClient())
			return null;

		String _value = dtoManager.getProperty(this, instantiatePropertyAccess().label());

		return _value;
	}


	public void setLabel(String label)  {
		/* old value */
		String oldValue = null;
		if(GWT.isClient())
			oldValue = getLabel();

		/* set new value */
		this.label = label;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(label_pa, oldValue, label, this.label_m));

		/* set indicator */
		this.label_m = true;

		this.fireObjectChangedEvent(AddToolbarEntryExtensionDtoPA.INSTANCE.label(), oldValue);
	}


	public boolean isLabelModified()  {
		return label_m;
	}


	public static PropertyAccessor<AddToolbarEntryExtensionDto, String> getLabelPropertyAccessor()  {
		return label_pa;
	}


	public boolean isLarge()  {
		if(! isDtoProxy()){
			return this.large;
		}

		if(isLargeModified())
			return this.large;

		if(! GWT.isClient())
			return false;

		boolean _value = dtoManager.getProperty(this, instantiatePropertyAccess().large());

		return _value;
	}


	public void setLarge(boolean large)  {
		/* old value */
		boolean oldValue = false;
		if(GWT.isClient())
			oldValue = isLarge();

		/* set new value */
		this.large = large;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(large_pa, oldValue, large, this.large_m));

		/* set indicator */
		this.large_m = true;

		this.fireObjectChangedEvent(AddToolbarEntryExtensionDtoPA.INSTANCE.large(), oldValue);
	}


	public boolean isLargeModified()  {
		return large_m;
	}


	public static PropertyAccessor<AddToolbarEntryExtensionDto, Boolean> getLargePropertyAccessor()  {
		return large_pa;
	}


	public boolean isLeft()  {
		if(! isDtoProxy()){
			return this.left;
		}

		if(isLeftModified())
			return this.left;

		if(! GWT.isClient())
			return false;

		boolean _value = dtoManager.getProperty(this, instantiatePropertyAccess().left());

		return _value;
	}


	public void setLeft(boolean left)  {
		/* old value */
		boolean oldValue = false;
		if(GWT.isClient())
			oldValue = isLeft();

		/* set new value */
		this.left = left;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(left_pa, oldValue, left, this.left_m));

		/* set indicator */
		this.left_m = true;

		this.fireObjectChangedEvent(AddToolbarEntryExtensionDtoPA.INSTANCE.left(), oldValue);
	}


	public boolean isLeftModified()  {
		return left_m;
	}


	public static PropertyAccessor<AddToolbarEntryExtensionDto, Boolean> getLeftPropertyAccessor()  {
		return left_pa;
	}


	public String getScriptLocation()  {
		if(! isDtoProxy()){
			return this.scriptLocation;
		}

		if(isScriptLocationModified())
			return this.scriptLocation;

		if(! GWT.isClient())
			return null;

		String _value = dtoManager.getProperty(this, instantiatePropertyAccess().scriptLocation());

		return _value;
	}


	public void setScriptLocation(String scriptLocation)  {
		/* old value */
		String oldValue = null;
		if(GWT.isClient())
			oldValue = getScriptLocation();

		/* set new value */
		this.scriptLocation = scriptLocation;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(scriptLocation_pa, oldValue, scriptLocation, this.scriptLocation_m));

		/* set indicator */
		this.scriptLocation_m = true;

		this.fireObjectChangedEvent(AddToolbarEntryExtensionDtoPA.INSTANCE.scriptLocation(), oldValue);
	}


	public boolean isScriptLocationModified()  {
		return scriptLocation_m;
	}


	public static PropertyAccessor<AddToolbarEntryExtensionDto, String> getScriptLocationPropertyAccessor()  {
		return scriptLocation_pa;
	}


	public List<AddMenuEntryExtensionDto> getSubMenuEntries()  {
		if(! isDtoProxy()){
			List<AddMenuEntryExtensionDto> _currentValue = this.subMenuEntries;
			if(null == _currentValue)
				this.subMenuEntries = new ArrayList<AddMenuEntryExtensionDto>();

			return this.subMenuEntries;
		}

		if(isSubMenuEntriesModified())
			return this.subMenuEntries;

		if(! GWT.isClient())
			return null;

		List<AddMenuEntryExtensionDto> _value = dtoManager.getProperty(this, instantiatePropertyAccess().subMenuEntries());

		_value = new ChangeMonitoredList<AddMenuEntryExtensionDto>(_value);
		if(_value instanceof HasObjectChangedEventHandler){
			((HasObjectChangedEventHandler)_value).addObjectChangedHandler(new net.datenwerke.gxtdto.client.eventbus.handlers.ObjectChangedEventHandler(){
				@Override
				public void onObjectChangedEvent(net.datenwerke.gxtdto.client.eventbus.events.ObjectChangedEvent event){
					if(! isSubMenuEntriesModified())
						setSubMenuEntries((List<AddMenuEntryExtensionDto>) ((MonitoredCollection) event.getObject()).getUnderlyingCollection());
				}
			}
			);
		}
		return _value;
	}


	public void setSubMenuEntries(List<AddMenuEntryExtensionDto> subMenuEntries)  {
		/* old value */
		List<AddMenuEntryExtensionDto> oldValue = null;
		if(GWT.isClient())
			oldValue = getSubMenuEntries();

		/* set new value */
		this.subMenuEntries = subMenuEntries;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(subMenuEntries_pa, oldValue, subMenuEntries, this.subMenuEntries_m));

		/* set indicator */
		this.subMenuEntries_m = true;

		this.fireObjectChangedEvent(AddToolbarEntryExtensionDtoPA.INSTANCE.subMenuEntries(), oldValue);
	}


	public boolean isSubMenuEntriesModified()  {
		return subMenuEntries_m;
	}


	public static PropertyAccessor<AddToolbarEntryExtensionDto, List<AddMenuEntryExtensionDto>> getSubMenuEntriesPropertyAccessor()  {
		return subMenuEntries_pa;
	}


	public String getToolbarName()  {
		if(! isDtoProxy()){
			return this.toolbarName;
		}

		if(isToolbarNameModified())
			return this.toolbarName;

		if(! GWT.isClient())
			return null;

		String _value = dtoManager.getProperty(this, instantiatePropertyAccess().toolbarName());

		return _value;
	}


	public void setToolbarName(String toolbarName)  {
		/* old value */
		String oldValue = null;
		if(GWT.isClient())
			oldValue = getToolbarName();

		/* set new value */
		this.toolbarName = toolbarName;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(toolbarName_pa, oldValue, toolbarName, this.toolbarName_m));

		/* set indicator */
		this.toolbarName_m = true;

		this.fireObjectChangedEvent(AddToolbarEntryExtensionDtoPA.INSTANCE.toolbarName(), oldValue);
	}


	public boolean isToolbarNameModified()  {
		return toolbarName_m;
	}


	public static PropertyAccessor<AddToolbarEntryExtensionDto, String> getToolbarNamePropertyAccessor()  {
		return toolbarName_pa;
	}


	@Override
	public String toString()  {
		return super.toString();
	}

	public static Dto2PosoMapper newPosoMapper()  {
		return new AddToolbarEntryExtensionDto2PosoMap();
	}

	public AddToolbarEntryExtensionDtoPA instantiatePropertyAccess()  {
		return GWT.create(AddToolbarEntryExtensionDtoPA.class);
	}

	public void clearModified()  {
		this.arguments = null;
		this.arguments_m = false;
		this.columns = 0;
		this.columns_m = false;
		this.displayConditions = null;
		this.displayConditions_m = false;
		this.groupEntries = null;
		this.groupEntries_m = false;
		this.icon = null;
		this.icon_m = false;
		this.javaScript = null;
		this.javaScript_m = false;
		this.label = null;
		this.label_m = false;
		this.large = false;
		this.large_m = false;
		this.left = false;
		this.left_m = false;
		this.scriptLocation = null;
		this.scriptLocation_m = false;
		this.subMenuEntries = null;
		this.subMenuEntries_m = false;
		this.toolbarName = null;
		this.toolbarName_m = false;
	}


	public boolean isModified()  {
		if(super.isModified())
			return true;
		if(arguments_m)
			return true;
		if(columns_m)
			return true;
		if(displayConditions_m)
			return true;
		if(groupEntries_m)
			return true;
		if(icon_m)
			return true;
		if(javaScript_m)
			return true;
		if(label_m)
			return true;
		if(large_m)
			return true;
		if(left_m)
			return true;
		if(scriptLocation_m)
			return true;
		if(subMenuEntries_m)
			return true;
		if(toolbarName_m)
			return true;
		return false;
	}


	public List<PropertyAccessor> getPropertyAccessors()  {
		List<PropertyAccessor> list = super.getPropertyAccessors();
		list.add(arguments_pa);
		list.add(columns_pa);
		list.add(displayConditions_pa);
		list.add(groupEntries_pa);
		list.add(icon_pa);
		list.add(javaScript_pa);
		list.add(label_pa);
		list.add(large_pa);
		list.add(left_pa);
		list.add(scriptLocation_pa);
		list.add(subMenuEntries_pa);
		list.add(toolbarName_pa);
		return list;
	}


	public List<PropertyAccessor> getModifiedPropertyAccessors()  {
		List<PropertyAccessor> list = super.getModifiedPropertyAccessors();
		if(arguments_m)
			list.add(arguments_pa);
		if(columns_m)
			list.add(columns_pa);
		if(displayConditions_m)
			list.add(displayConditions_pa);
		if(groupEntries_m)
			list.add(groupEntries_pa);
		if(icon_m)
			list.add(icon_pa);
		if(javaScript_m)
			list.add(javaScript_pa);
		if(label_m)
			list.add(label_pa);
		if(large_m)
			list.add(large_pa);
		if(left_m)
			list.add(left_pa);
		if(scriptLocation_m)
			list.add(scriptLocation_pa);
		if(subMenuEntries_m)
			list.add(subMenuEntries_pa);
		if(toolbarName_m)
			list.add(toolbarName_pa);
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsByView(net.datenwerke.gxtdto.client.dtomanager.DtoView view)  {
		List<PropertyAccessor> list = super.getPropertyAccessorsByView(view);
		if(view.compareTo(DtoView.NORMAL) >= 0){
			list.add(arguments_pa);
			list.add(columns_pa);
			list.add(displayConditions_pa);
			list.add(groupEntries_pa);
			list.add(icon_pa);
			list.add(javaScript_pa);
			list.add(label_pa);
			list.add(large_pa);
			list.add(left_pa);
			list.add(scriptLocation_pa);
			list.add(subMenuEntries_pa);
			list.add(toolbarName_pa);
		}
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsForDtos()  {
		List<PropertyAccessor> list = super.getPropertyAccessorsForDtos();
		list.add(displayConditions_pa);
		list.add(groupEntries_pa);
		list.add(subMenuEntries_pa);
		return list;
	}



	net.datenwerke.rs.scripting.client.scripting.dto.AddToolbarEntryExtensionDto wl_0;
	net.datenwerke.rs.scripting.client.scripting.dto.AddMenuEntryExtensionDto wl_1;
	net.datenwerke.rs.scripting.client.scripting.dto.DisplayConditionDto wl_2;

}
