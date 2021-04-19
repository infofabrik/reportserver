package net.datenwerke.rs.terminal.client.terminal.dto;

import com.google.gwt.core.client.GWT;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.gf.base.client.dtogenerator.RsDto;
import net.datenwerke.gxtdto.client.dtomanager.Dto2PosoMapper;
import net.datenwerke.gxtdto.client.dtomanager.Dto;
import net.datenwerke.gxtdto.client.dtomanager.DtoView;
import net.datenwerke.gxtdto.client.dtomanager.PropertyAccessor;
import net.datenwerke.gxtdto.client.dtomanager.dtomod.collections.ChangeMonitoredList;
import net.datenwerke.gxtdto.client.dtomanager.dtomod.collections.ChangeMonitoredSet;
import net.datenwerke.gxtdto.client.dtomanager.dtomod.collections.MonitoredCollection;
import net.datenwerke.gxtdto.client.dtomanager.redoundo.ChangeTracker;
import net.datenwerke.gxtdto.client.eventbus.events.ObjectChangedEvent;
import net.datenwerke.gxtdto.client.eventbus.handlers.ObjectChangedEventHandler;
import net.datenwerke.gxtdto.client.eventbus.handlers.has.HasObjectChangedEventHandler;
import net.datenwerke.rs.terminal.client.terminal.dto.CommandResultEntryDto;
import net.datenwerke.rs.terminal.client.terminal.dto.CommandResultExtensionDto;
import net.datenwerke.rs.terminal.client.terminal.dto.CommandResultModifierDto;
import net.datenwerke.rs.terminal.client.terminal.dto.DisplayModeDto;
import net.datenwerke.rs.terminal.client.terminal.dto.pa.CommandResultDtoPA;
import net.datenwerke.rs.terminal.client.terminal.dto.posomap.CommandResultDto2PosoMap;
import net.datenwerke.rs.terminal.service.terminal.obj.CommandResult;

/**
 * Dto for {@link CommandResult}
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
abstract public class CommandResultDto extends RsDto {


	private static final long serialVersionUID = 1;


	/* Fields */
	private DisplayModeDto displayMode;
	private  boolean displayMode_m;
	public static final String PROPERTY_DISPLAY_MODE = "dpi-commandresult-displaymode";

	private transient static PropertyAccessor<CommandResultDto, DisplayModeDto> displayMode_pa = new PropertyAccessor<CommandResultDto, DisplayModeDto>() {
		@Override
		public void setValue(CommandResultDto container, DisplayModeDto object) {
			container.setDisplayMode(object);
		}

		@Override
		public DisplayModeDto getValue(CommandResultDto container) {
			return container.getDisplayMode();
		}

		@Override
		public Class<?> getType() {
			return DisplayModeDto.class;
		}

		@Override
		public String getPath() {
			return "displayMode";
		}

		@Override
		public void setModified(CommandResultDto container, boolean modified) {
			container.displayMode_m = modified;
		}

		@Override
		public boolean isModified(CommandResultDto container) {
			return container.isDisplayModeModified();
		}
	};

	private List<CommandResultEntryDto> entryList;
	private  boolean entryList_m;
	public static final String PROPERTY_ENTRY_LIST = "dpi-commandresult-entrylist";

	private transient static PropertyAccessor<CommandResultDto, List<CommandResultEntryDto>> entryList_pa = new PropertyAccessor<CommandResultDto, List<CommandResultEntryDto>>() {
		@Override
		public void setValue(CommandResultDto container, List<CommandResultEntryDto> object) {
			container.setEntryList(object);
		}

		@Override
		public List<CommandResultEntryDto> getValue(CommandResultDto container) {
			return container.getEntryList();
		}

		@Override
		public Class<?> getType() {
			return List.class;
		}

		@Override
		public String getPath() {
			return "entryList";
		}

		@Override
		public void setModified(CommandResultDto container, boolean modified) {
			container.entryList_m = modified;
		}

		@Override
		public boolean isModified(CommandResultDto container) {
			return container.isEntryListModified();
		}
	};

	private List<CommandResultExtensionDto> extensions;
	private  boolean extensions_m;
	public static final String PROPERTY_EXTENSIONS = "dpi-commandresult-extensions";

	private transient static PropertyAccessor<CommandResultDto, List<CommandResultExtensionDto>> extensions_pa = new PropertyAccessor<CommandResultDto, List<CommandResultExtensionDto>>() {
		@Override
		public void setValue(CommandResultDto container, List<CommandResultExtensionDto> object) {
			container.setExtensions(object);
		}

		@Override
		public List<CommandResultExtensionDto> getValue(CommandResultDto container) {
			return container.getExtensions();
		}

		@Override
		public Class<?> getType() {
			return List.class;
		}

		@Override
		public String getPath() {
			return "extensions";
		}

		@Override
		public void setModified(CommandResultDto container, boolean modified) {
			container.extensions_m = modified;
		}

		@Override
		public boolean isModified(CommandResultDto container) {
			return container.isExtensionsModified();
		}
	};

	private Set<CommandResultModifierDto> modifiers;
	private  boolean modifiers_m;
	public static final String PROPERTY_MODIFIERS = "dpi-commandresult-modifiers";

	private transient static PropertyAccessor<CommandResultDto, Set<CommandResultModifierDto>> modifiers_pa = new PropertyAccessor<CommandResultDto, Set<CommandResultModifierDto>>() {
		@Override
		public void setValue(CommandResultDto container, Set<CommandResultModifierDto> object) {
			container.setModifiers(object);
		}

		@Override
		public Set<CommandResultModifierDto> getValue(CommandResultDto container) {
			return container.getModifiers();
		}

		@Override
		public Class<?> getType() {
			return Set.class;
		}

		@Override
		public String getPath() {
			return "modifiers";
		}

		@Override
		public void setModified(CommandResultDto container, boolean modified) {
			container.modifiers_m = modified;
		}

		@Override
		public boolean isModified(CommandResultDto container) {
			return container.isModifiersModified();
		}
	};


	public CommandResultDto() {
		super();
	}

	public DisplayModeDto getDisplayMode()  {
		if(! isDtoProxy()){
			return this.displayMode;
		}

		if(isDisplayModeModified())
			return this.displayMode;

		if(! GWT.isClient())
			return null;

		DisplayModeDto _value = dtoManager.getProperty(this, instantiatePropertyAccess().displayMode());

		return _value;
	}


	public void setDisplayMode(DisplayModeDto displayMode)  {
		/* old value */
		DisplayModeDto oldValue = null;
		if(GWT.isClient())
			oldValue = getDisplayMode();

		/* set new value */
		this.displayMode = displayMode;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(displayMode_pa, oldValue, displayMode, this.displayMode_m));

		/* set indicator */
		this.displayMode_m = true;

		this.fireObjectChangedEvent(CommandResultDtoPA.INSTANCE.displayMode(), oldValue);
	}


	public boolean isDisplayModeModified()  {
		return displayMode_m;
	}


	public static PropertyAccessor<CommandResultDto, DisplayModeDto> getDisplayModePropertyAccessor()  {
		return displayMode_pa;
	}


	public List<CommandResultEntryDto> getEntryList()  {
		if(! isDtoProxy()){
			List<CommandResultEntryDto> _currentValue = this.entryList;
			if(null == _currentValue)
				this.entryList = new ArrayList<CommandResultEntryDto>();

			return this.entryList;
		}

		if(isEntryListModified())
			return this.entryList;

		if(! GWT.isClient())
			return null;

		List<CommandResultEntryDto> _value = dtoManager.getProperty(this, instantiatePropertyAccess().entryList());

		_value = new ChangeMonitoredList<CommandResultEntryDto>(_value);
		if(_value instanceof HasObjectChangedEventHandler){
			((HasObjectChangedEventHandler)_value).addObjectChangedHandler(new net.datenwerke.gxtdto.client.eventbus.handlers.ObjectChangedEventHandler(){
				@Override
				public void onObjectChangedEvent(net.datenwerke.gxtdto.client.eventbus.events.ObjectChangedEvent event){
					if(! isEntryListModified())
						setEntryList((List<CommandResultEntryDto>) ((MonitoredCollection) event.getObject()).getUnderlyingCollection());
				}
			}
			);
		}
		return _value;
	}


	public void setEntryList(List<CommandResultEntryDto> entryList)  {
		/* old value */
		List<CommandResultEntryDto> oldValue = null;
		if(GWT.isClient())
			oldValue = getEntryList();

		/* set new value */
		this.entryList = entryList;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(entryList_pa, oldValue, entryList, this.entryList_m));

		/* set indicator */
		this.entryList_m = true;

		this.fireObjectChangedEvent(CommandResultDtoPA.INSTANCE.entryList(), oldValue);
	}


	public boolean isEntryListModified()  {
		return entryList_m;
	}


	public static PropertyAccessor<CommandResultDto, List<CommandResultEntryDto>> getEntryListPropertyAccessor()  {
		return entryList_pa;
	}


	public List<CommandResultExtensionDto> getExtensions()  {
		if(! isDtoProxy()){
			List<CommandResultExtensionDto> _currentValue = this.extensions;
			if(null == _currentValue)
				this.extensions = new ArrayList<CommandResultExtensionDto>();

			return this.extensions;
		}

		if(isExtensionsModified())
			return this.extensions;

		if(! GWT.isClient())
			return null;

		List<CommandResultExtensionDto> _value = dtoManager.getProperty(this, instantiatePropertyAccess().extensions());

		_value = new ChangeMonitoredList<CommandResultExtensionDto>(_value);
		if(_value instanceof HasObjectChangedEventHandler){
			((HasObjectChangedEventHandler)_value).addObjectChangedHandler(new net.datenwerke.gxtdto.client.eventbus.handlers.ObjectChangedEventHandler(){
				@Override
				public void onObjectChangedEvent(net.datenwerke.gxtdto.client.eventbus.events.ObjectChangedEvent event){
					if(! isExtensionsModified())
						setExtensions((List<CommandResultExtensionDto>) ((MonitoredCollection) event.getObject()).getUnderlyingCollection());
				}
			}
			);
		}
		return _value;
	}


	public void setExtensions(List<CommandResultExtensionDto> extensions)  {
		/* old value */
		List<CommandResultExtensionDto> oldValue = null;
		if(GWT.isClient())
			oldValue = getExtensions();

		/* set new value */
		this.extensions = extensions;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(extensions_pa, oldValue, extensions, this.extensions_m));

		/* set indicator */
		this.extensions_m = true;

		this.fireObjectChangedEvent(CommandResultDtoPA.INSTANCE.extensions(), oldValue);
	}


	public boolean isExtensionsModified()  {
		return extensions_m;
	}


	public static PropertyAccessor<CommandResultDto, List<CommandResultExtensionDto>> getExtensionsPropertyAccessor()  {
		return extensions_pa;
	}


	public Set<CommandResultModifierDto> getModifiers()  {
		if(! isDtoProxy()){
			Set<CommandResultModifierDto> _currentValue = this.modifiers;
			if(null == _currentValue)
				this.modifiers = new HashSet<CommandResultModifierDto>();

			return this.modifiers;
		}

		if(isModifiersModified())
			return this.modifiers;

		if(! GWT.isClient())
			return null;

		Set<CommandResultModifierDto> _value = dtoManager.getProperty(this, instantiatePropertyAccess().modifiers());

		_value = new ChangeMonitoredSet<CommandResultModifierDto>(_value);
		if(_value instanceof HasObjectChangedEventHandler){
			((HasObjectChangedEventHandler)_value).addObjectChangedHandler(new net.datenwerke.gxtdto.client.eventbus.handlers.ObjectChangedEventHandler(){
				@Override
				public void onObjectChangedEvent(net.datenwerke.gxtdto.client.eventbus.events.ObjectChangedEvent event){
					if(! isModifiersModified())
						setModifiers((Set<CommandResultModifierDto>) ((MonitoredCollection) event.getObject()).getUnderlyingCollection());
				}
			}
			);
		}
		return _value;
	}


	public void setModifiers(Set<CommandResultModifierDto> modifiers)  {
		/* old value */
		Set<CommandResultModifierDto> oldValue = null;
		if(GWT.isClient())
			oldValue = getModifiers();

		/* set new value */
		this.modifiers = modifiers;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(modifiers_pa, oldValue, modifiers, this.modifiers_m));

		/* set indicator */
		this.modifiers_m = true;

		this.fireObjectChangedEvent(CommandResultDtoPA.INSTANCE.modifiers(), oldValue);
	}


	public boolean isModifiersModified()  {
		return modifiers_m;
	}


	public static PropertyAccessor<CommandResultDto, Set<CommandResultModifierDto>> getModifiersPropertyAccessor()  {
		return modifiers_pa;
	}


	@Override
	public String toString()  {
		return super.toString();
	}

	public static Dto2PosoMapper newPosoMapper()  {
		return new CommandResultDto2PosoMap();
	}

	public CommandResultDtoPA instantiatePropertyAccess()  {
		return GWT.create(CommandResultDtoPA.class);
	}

	public void clearModified()  {
		this.displayMode = null;
		this.displayMode_m = false;
		this.entryList = null;
		this.entryList_m = false;
		this.extensions = null;
		this.extensions_m = false;
		this.modifiers = null;
		this.modifiers_m = false;
	}


	public boolean isModified()  {
		if(super.isModified())
			return true;
		if(displayMode_m)
			return true;
		if(entryList_m)
			return true;
		if(extensions_m)
			return true;
		if(modifiers_m)
			return true;
		return false;
	}


	public List<PropertyAccessor> getPropertyAccessors()  {
		List<PropertyAccessor> list = super.getPropertyAccessors();
		list.add(displayMode_pa);
		list.add(entryList_pa);
		list.add(extensions_pa);
		list.add(modifiers_pa);
		return list;
	}


	public List<PropertyAccessor> getModifiedPropertyAccessors()  {
		List<PropertyAccessor> list = super.getModifiedPropertyAccessors();
		if(displayMode_m)
			list.add(displayMode_pa);
		if(entryList_m)
			list.add(entryList_pa);
		if(extensions_m)
			list.add(extensions_pa);
		if(modifiers_m)
			list.add(modifiers_pa);
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsByView(net.datenwerke.gxtdto.client.dtomanager.DtoView view)  {
		List<PropertyAccessor> list = super.getPropertyAccessorsByView(view);
		if(view.compareTo(DtoView.NORMAL) >= 0){
			list.add(displayMode_pa);
			list.add(entryList_pa);
			list.add(extensions_pa);
			list.add(modifiers_pa);
		}
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsForDtos()  {
		List<PropertyAccessor> list = super.getPropertyAccessorsForDtos();
		list.add(entryList_pa);
		list.add(extensions_pa);
		list.add(modifiers_pa);
		return list;
	}



	net.datenwerke.rs.terminal.client.terminal.dto.CommandResultExtensionDto wl_0;
	net.datenwerke.gf.base.client.dtogenerator.RsDto wl_1;
	net.datenwerke.rs.terminal.client.terminal.dto.CommandResultEntryDto wl_2;
	net.datenwerke.rs.terminal.client.terminal.dto.CommandResultModifierDto wl_3;
	net.datenwerke.rs.terminal.client.terminal.dto.DisplayModeDto wl_4;

}
