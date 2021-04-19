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
import net.datenwerke.rs.scripting.client.scripting.dto.DisplayConditionDto;
import net.datenwerke.rs.scripting.client.scripting.dto.pa.AddMenuEntryExtensionDtoPA;
import net.datenwerke.rs.scripting.client.scripting.dto.posomap.AddMenuEntryExtensionDto2PosoMap;
import net.datenwerke.rs.scripting.service.scripting.extensions.AddMenuEntryExtension;
import net.datenwerke.rs.terminal.client.terminal.dto.CommandResultExtensionDto;

/**
 * Dto for {@link AddMenuEntryExtension}
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class AddMenuEntryExtensionDto extends CommandResultExtensionDto {


	private static final long serialVersionUID = 1;


	/* Fields */
	private String arguments;
	private  boolean arguments_m;
	public static final String PROPERTY_ARGUMENTS = "dpi-addmenuentryextension-arguments";

	private transient static PropertyAccessor<AddMenuEntryExtensionDto, String> arguments_pa = new PropertyAccessor<AddMenuEntryExtensionDto, String>() {
		@Override
		public void setValue(AddMenuEntryExtensionDto container, String object) {
			container.setArguments(object);
		}

		@Override
		public String getValue(AddMenuEntryExtensionDto container) {
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
		public void setModified(AddMenuEntryExtensionDto container, boolean modified) {
			container.arguments_m = modified;
		}

		@Override
		public boolean isModified(AddMenuEntryExtensionDto container) {
			return container.isArgumentsModified();
		}
	};

	private List<DisplayConditionDto> displayConditions;
	private  boolean displayConditions_m;
	public static final String PROPERTY_DISPLAY_CONDITIONS = "dpi-addmenuentryextension-displayconditions";

	private transient static PropertyAccessor<AddMenuEntryExtensionDto, List<DisplayConditionDto>> displayConditions_pa = new PropertyAccessor<AddMenuEntryExtensionDto, List<DisplayConditionDto>>() {
		@Override
		public void setValue(AddMenuEntryExtensionDto container, List<DisplayConditionDto> object) {
			container.setDisplayConditions(object);
		}

		@Override
		public List<DisplayConditionDto> getValue(AddMenuEntryExtensionDto container) {
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
		public void setModified(AddMenuEntryExtensionDto container, boolean modified) {
			container.displayConditions_m = modified;
		}

		@Override
		public boolean isModified(AddMenuEntryExtensionDto container) {
			return container.isDisplayConditionsModified();
		}
	};

	private String icon;
	private  boolean icon_m;
	public static final String PROPERTY_ICON = "dpi-addmenuentryextension-icon";

	private transient static PropertyAccessor<AddMenuEntryExtensionDto, String> icon_pa = new PropertyAccessor<AddMenuEntryExtensionDto, String>() {
		@Override
		public void setValue(AddMenuEntryExtensionDto container, String object) {
			container.setIcon(object);
		}

		@Override
		public String getValue(AddMenuEntryExtensionDto container) {
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
		public void setModified(AddMenuEntryExtensionDto container, boolean modified) {
			container.icon_m = modified;
		}

		@Override
		public boolean isModified(AddMenuEntryExtensionDto container) {
			return container.isIconModified();
		}
	};

	private String javaScript;
	private  boolean javaScript_m;
	public static final String PROPERTY_JAVA_SCRIPT = "dpi-addmenuentryextension-javascript";

	private transient static PropertyAccessor<AddMenuEntryExtensionDto, String> javaScript_pa = new PropertyAccessor<AddMenuEntryExtensionDto, String>() {
		@Override
		public void setValue(AddMenuEntryExtensionDto container, String object) {
			container.setJavaScript(object);
		}

		@Override
		public String getValue(AddMenuEntryExtensionDto container) {
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
		public void setModified(AddMenuEntryExtensionDto container, boolean modified) {
			container.javaScript_m = modified;
		}

		@Override
		public boolean isModified(AddMenuEntryExtensionDto container) {
			return container.isJavaScriptModified();
		}
	};

	private String label;
	private  boolean label_m;
	public static final String PROPERTY_LABEL = "dpi-addmenuentryextension-label";

	private transient static PropertyAccessor<AddMenuEntryExtensionDto, String> label_pa = new PropertyAccessor<AddMenuEntryExtensionDto, String>() {
		@Override
		public void setValue(AddMenuEntryExtensionDto container, String object) {
			container.setLabel(object);
		}

		@Override
		public String getValue(AddMenuEntryExtensionDto container) {
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
		public void setModified(AddMenuEntryExtensionDto container, boolean modified) {
			container.label_m = modified;
		}

		@Override
		public boolean isModified(AddMenuEntryExtensionDto container) {
			return container.isLabelModified();
		}
	};

	private String menuName;
	private  boolean menuName_m;
	public static final String PROPERTY_MENU_NAME = "dpi-addmenuentryextension-menuname";

	private transient static PropertyAccessor<AddMenuEntryExtensionDto, String> menuName_pa = new PropertyAccessor<AddMenuEntryExtensionDto, String>() {
		@Override
		public void setValue(AddMenuEntryExtensionDto container, String object) {
			container.setMenuName(object);
		}

		@Override
		public String getValue(AddMenuEntryExtensionDto container) {
			return container.getMenuName();
		}

		@Override
		public Class<?> getType() {
			return String.class;
		}

		@Override
		public String getPath() {
			return "menuName";
		}

		@Override
		public void setModified(AddMenuEntryExtensionDto container, boolean modified) {
			container.menuName_m = modified;
		}

		@Override
		public boolean isModified(AddMenuEntryExtensionDto container) {
			return container.isMenuNameModified();
		}
	};

	private String scriptLocation;
	private  boolean scriptLocation_m;
	public static final String PROPERTY_SCRIPT_LOCATION = "dpi-addmenuentryextension-scriptlocation";

	private transient static PropertyAccessor<AddMenuEntryExtensionDto, String> scriptLocation_pa = new PropertyAccessor<AddMenuEntryExtensionDto, String>() {
		@Override
		public void setValue(AddMenuEntryExtensionDto container, String object) {
			container.setScriptLocation(object);
		}

		@Override
		public String getValue(AddMenuEntryExtensionDto container) {
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
		public void setModified(AddMenuEntryExtensionDto container, boolean modified) {
			container.scriptLocation_m = modified;
		}

		@Override
		public boolean isModified(AddMenuEntryExtensionDto container) {
			return container.isScriptLocationModified();
		}
	};

	private List<AddMenuEntryExtensionDto> subMenuEntries;
	private  boolean subMenuEntries_m;
	public static final String PROPERTY_SUB_MENU_ENTRIES = "dpi-addmenuentryextension-submenuentries";

	private transient static PropertyAccessor<AddMenuEntryExtensionDto, List<AddMenuEntryExtensionDto>> subMenuEntries_pa = new PropertyAccessor<AddMenuEntryExtensionDto, List<AddMenuEntryExtensionDto>>() {
		@Override
		public void setValue(AddMenuEntryExtensionDto container, List<AddMenuEntryExtensionDto> object) {
			container.setSubMenuEntries(object);
		}

		@Override
		public List<AddMenuEntryExtensionDto> getValue(AddMenuEntryExtensionDto container) {
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
		public void setModified(AddMenuEntryExtensionDto container, boolean modified) {
			container.subMenuEntries_m = modified;
		}

		@Override
		public boolean isModified(AddMenuEntryExtensionDto container) {
			return container.isSubMenuEntriesModified();
		}
	};


	public AddMenuEntryExtensionDto() {
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

		this.fireObjectChangedEvent(AddMenuEntryExtensionDtoPA.INSTANCE.arguments(), oldValue);
	}


	public boolean isArgumentsModified()  {
		return arguments_m;
	}


	public static PropertyAccessor<AddMenuEntryExtensionDto, String> getArgumentsPropertyAccessor()  {
		return arguments_pa;
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

		this.fireObjectChangedEvent(AddMenuEntryExtensionDtoPA.INSTANCE.displayConditions(), oldValue);
	}


	public boolean isDisplayConditionsModified()  {
		return displayConditions_m;
	}


	public static PropertyAccessor<AddMenuEntryExtensionDto, List<DisplayConditionDto>> getDisplayConditionsPropertyAccessor()  {
		return displayConditions_pa;
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

		this.fireObjectChangedEvent(AddMenuEntryExtensionDtoPA.INSTANCE.icon(), oldValue);
	}


	public boolean isIconModified()  {
		return icon_m;
	}


	public static PropertyAccessor<AddMenuEntryExtensionDto, String> getIconPropertyAccessor()  {
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

		this.fireObjectChangedEvent(AddMenuEntryExtensionDtoPA.INSTANCE.javaScript(), oldValue);
	}


	public boolean isJavaScriptModified()  {
		return javaScript_m;
	}


	public static PropertyAccessor<AddMenuEntryExtensionDto, String> getJavaScriptPropertyAccessor()  {
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

		this.fireObjectChangedEvent(AddMenuEntryExtensionDtoPA.INSTANCE.label(), oldValue);
	}


	public boolean isLabelModified()  {
		return label_m;
	}


	public static PropertyAccessor<AddMenuEntryExtensionDto, String> getLabelPropertyAccessor()  {
		return label_pa;
	}


	public String getMenuName()  {
		if(! isDtoProxy()){
			return this.menuName;
		}

		if(isMenuNameModified())
			return this.menuName;

		if(! GWT.isClient())
			return null;

		String _value = dtoManager.getProperty(this, instantiatePropertyAccess().menuName());

		return _value;
	}


	public void setMenuName(String menuName)  {
		/* old value */
		String oldValue = null;
		if(GWT.isClient())
			oldValue = getMenuName();

		/* set new value */
		this.menuName = menuName;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(menuName_pa, oldValue, menuName, this.menuName_m));

		/* set indicator */
		this.menuName_m = true;

		this.fireObjectChangedEvent(AddMenuEntryExtensionDtoPA.INSTANCE.menuName(), oldValue);
	}


	public boolean isMenuNameModified()  {
		return menuName_m;
	}


	public static PropertyAccessor<AddMenuEntryExtensionDto, String> getMenuNamePropertyAccessor()  {
		return menuName_pa;
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

		this.fireObjectChangedEvent(AddMenuEntryExtensionDtoPA.INSTANCE.scriptLocation(), oldValue);
	}


	public boolean isScriptLocationModified()  {
		return scriptLocation_m;
	}


	public static PropertyAccessor<AddMenuEntryExtensionDto, String> getScriptLocationPropertyAccessor()  {
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

		this.fireObjectChangedEvent(AddMenuEntryExtensionDtoPA.INSTANCE.subMenuEntries(), oldValue);
	}


	public boolean isSubMenuEntriesModified()  {
		return subMenuEntries_m;
	}


	public static PropertyAccessor<AddMenuEntryExtensionDto, List<AddMenuEntryExtensionDto>> getSubMenuEntriesPropertyAccessor()  {
		return subMenuEntries_pa;
	}


	@Override
	public String toString()  {
		return super.toString();
	}

	public static Dto2PosoMapper newPosoMapper()  {
		return new AddMenuEntryExtensionDto2PosoMap();
	}

	public AddMenuEntryExtensionDtoPA instantiatePropertyAccess()  {
		return GWT.create(AddMenuEntryExtensionDtoPA.class);
	}

	public void clearModified()  {
		this.arguments = null;
		this.arguments_m = false;
		this.displayConditions = null;
		this.displayConditions_m = false;
		this.icon = null;
		this.icon_m = false;
		this.javaScript = null;
		this.javaScript_m = false;
		this.label = null;
		this.label_m = false;
		this.menuName = null;
		this.menuName_m = false;
		this.scriptLocation = null;
		this.scriptLocation_m = false;
		this.subMenuEntries = null;
		this.subMenuEntries_m = false;
	}


	public boolean isModified()  {
		if(super.isModified())
			return true;
		if(arguments_m)
			return true;
		if(displayConditions_m)
			return true;
		if(icon_m)
			return true;
		if(javaScript_m)
			return true;
		if(label_m)
			return true;
		if(menuName_m)
			return true;
		if(scriptLocation_m)
			return true;
		if(subMenuEntries_m)
			return true;
		return false;
	}


	public List<PropertyAccessor> getPropertyAccessors()  {
		List<PropertyAccessor> list = super.getPropertyAccessors();
		list.add(arguments_pa);
		list.add(displayConditions_pa);
		list.add(icon_pa);
		list.add(javaScript_pa);
		list.add(label_pa);
		list.add(menuName_pa);
		list.add(scriptLocation_pa);
		list.add(subMenuEntries_pa);
		return list;
	}


	public List<PropertyAccessor> getModifiedPropertyAccessors()  {
		List<PropertyAccessor> list = super.getModifiedPropertyAccessors();
		if(arguments_m)
			list.add(arguments_pa);
		if(displayConditions_m)
			list.add(displayConditions_pa);
		if(icon_m)
			list.add(icon_pa);
		if(javaScript_m)
			list.add(javaScript_pa);
		if(label_m)
			list.add(label_pa);
		if(menuName_m)
			list.add(menuName_pa);
		if(scriptLocation_m)
			list.add(scriptLocation_pa);
		if(subMenuEntries_m)
			list.add(subMenuEntries_pa);
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsByView(net.datenwerke.gxtdto.client.dtomanager.DtoView view)  {
		List<PropertyAccessor> list = super.getPropertyAccessorsByView(view);
		if(view.compareTo(DtoView.NORMAL) >= 0){
			list.add(arguments_pa);
			list.add(displayConditions_pa);
			list.add(icon_pa);
			list.add(javaScript_pa);
			list.add(label_pa);
			list.add(menuName_pa);
			list.add(scriptLocation_pa);
			list.add(subMenuEntries_pa);
		}
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsForDtos()  {
		List<PropertyAccessor> list = super.getPropertyAccessorsForDtos();
		list.add(displayConditions_pa);
		list.add(subMenuEntries_pa);
		return list;
	}



	net.datenwerke.rs.scripting.client.scripting.dto.AddMenuEntryExtensionDto wl_0;
	net.datenwerke.rs.scripting.client.scripting.dto.DisplayConditionDto wl_1;

}
