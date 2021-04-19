package net.datenwerke.rs.terminal.client.terminal.dto;

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
import net.datenwerke.gxtdto.client.dtomanager.PropertyAccessor;
import net.datenwerke.gxtdto.client.dtomanager.redoundo.ChangeTracker;
import net.datenwerke.gxtdto.client.eventbus.events.ObjectChangedEvent;
import net.datenwerke.gxtdto.client.eventbus.handlers.ObjectChangedEventHandler;
import net.datenwerke.gxtdto.client.eventbus.handlers.has.HasObjectChangedEventHandler;
import net.datenwerke.rs.terminal.client.terminal.dto.CommandResultListDto;
import net.datenwerke.rs.terminal.client.terminal.dto.pa.AutocompleteResultDtoPA;
import net.datenwerke.rs.terminal.client.terminal.dto.posomap.AutocompleteResultDto2PosoMap;
import net.datenwerke.rs.terminal.service.terminal.obj.AutocompleteResult;

/**
 * Dto for {@link AutocompleteResult}
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
abstract public class AutocompleteResultDto extends RsDto {


	private static final long serialVersionUID = 1;


	/* Fields */
	private CommandResultListDto cmdEntries;
	private  boolean cmdEntries_m;
	public static final String PROPERTY_CMD_ENTRIES = "dpi-autocompleteresult-cmdentries";

	private transient static PropertyAccessor<AutocompleteResultDto, CommandResultListDto> cmdEntries_pa = new PropertyAccessor<AutocompleteResultDto, CommandResultListDto>() {
		@Override
		public void setValue(AutocompleteResultDto container, CommandResultListDto object) {
			container.setCmdEntries(object);
		}

		@Override
		public CommandResultListDto getValue(AutocompleteResultDto container) {
			return container.getCmdEntries();
		}

		@Override
		public Class<?> getType() {
			return CommandResultListDto.class;
		}

		@Override
		public String getPath() {
			return "cmdEntries";
		}

		@Override
		public void setModified(AutocompleteResultDto container, boolean modified) {
			container.cmdEntries_m = modified;
		}

		@Override
		public boolean isModified(AutocompleteResultDto container) {
			return container.isCmdEntriesModified();
		}
	};

	private String completeStump;
	private  boolean completeStump_m;
	public static final String PROPERTY_COMPLETE_STUMP = "dpi-autocompleteresult-completestump";

	private transient static PropertyAccessor<AutocompleteResultDto, String> completeStump_pa = new PropertyAccessor<AutocompleteResultDto, String>() {
		@Override
		public void setValue(AutocompleteResultDto container, String object) {
			container.setCompleteStump(object);
		}

		@Override
		public String getValue(AutocompleteResultDto container) {
			return container.getCompleteStump();
		}

		@Override
		public Class<?> getType() {
			return String.class;
		}

		@Override
		public String getPath() {
			return "completeStump";
		}

		@Override
		public void setModified(AutocompleteResultDto container, boolean modified) {
			container.completeStump_m = modified;
		}

		@Override
		public boolean isModified(AutocompleteResultDto container) {
			return container.isCompleteStumpModified();
		}
	};

	private CommandResultListDto entries;
	private  boolean entries_m;
	public static final String PROPERTY_ENTRIES = "dpi-autocompleteresult-entries";

	private transient static PropertyAccessor<AutocompleteResultDto, CommandResultListDto> entries_pa = new PropertyAccessor<AutocompleteResultDto, CommandResultListDto>() {
		@Override
		public void setValue(AutocompleteResultDto container, CommandResultListDto object) {
			container.setEntries(object);
		}

		@Override
		public CommandResultListDto getValue(AutocompleteResultDto container) {
			return container.getEntries();
		}

		@Override
		public Class<?> getType() {
			return CommandResultListDto.class;
		}

		@Override
		public String getPath() {
			return "entries";
		}

		@Override
		public void setModified(AutocompleteResultDto container, boolean modified) {
			container.entries_m = modified;
		}

		@Override
		public boolean isModified(AutocompleteResultDto container) {
			return container.isEntriesModified();
		}
	};

	private CommandResultListDto objectEntries;
	private  boolean objectEntries_m;
	public static final String PROPERTY_OBJECT_ENTRIES = "dpi-autocompleteresult-objectentries";

	private transient static PropertyAccessor<AutocompleteResultDto, CommandResultListDto> objectEntries_pa = new PropertyAccessor<AutocompleteResultDto, CommandResultListDto>() {
		@Override
		public void setValue(AutocompleteResultDto container, CommandResultListDto object) {
			container.setObjectEntries(object);
		}

		@Override
		public CommandResultListDto getValue(AutocompleteResultDto container) {
			return container.getObjectEntries();
		}

		@Override
		public Class<?> getType() {
			return CommandResultListDto.class;
		}

		@Override
		public String getPath() {
			return "objectEntries";
		}

		@Override
		public void setModified(AutocompleteResultDto container, boolean modified) {
			container.objectEntries_m = modified;
		}

		@Override
		public boolean isModified(AutocompleteResultDto container) {
			return container.isObjectEntriesModified();
		}
	};


	public AutocompleteResultDto() {
		super();
	}

	public CommandResultListDto getCmdEntries()  {
		if(! isDtoProxy()){
			return this.cmdEntries;
		}

		if(isCmdEntriesModified())
			return this.cmdEntries;

		if(! GWT.isClient())
			return null;

		CommandResultListDto _value = dtoManager.getProperty(this, instantiatePropertyAccess().cmdEntries());

		if(_value instanceof HasObjectChangedEventHandler){
			((HasObjectChangedEventHandler)_value).addObjectChangedHandler(new net.datenwerke.gxtdto.client.eventbus.handlers.ObjectChangedEventHandler(){
				@Override
				public void onObjectChangedEvent(net.datenwerke.gxtdto.client.eventbus.events.ObjectChangedEvent event){
					if(! isCmdEntriesModified())
						setCmdEntries((CommandResultListDto) event.getObject());
				}
			}
			);
		}
		return _value;
	}


	public void setCmdEntries(CommandResultListDto cmdEntries)  {
		/* old value */
		CommandResultListDto oldValue = null;
		if(GWT.isClient())
			oldValue = getCmdEntries();

		/* set new value */
		this.cmdEntries = cmdEntries;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(cmdEntries_pa, oldValue, cmdEntries, this.cmdEntries_m));

		/* set indicator */
		this.cmdEntries_m = true;

		this.fireObjectChangedEvent(AutocompleteResultDtoPA.INSTANCE.cmdEntries(), oldValue);
	}


	public boolean isCmdEntriesModified()  {
		return cmdEntries_m;
	}


	public static PropertyAccessor<AutocompleteResultDto, CommandResultListDto> getCmdEntriesPropertyAccessor()  {
		return cmdEntries_pa;
	}


	public String getCompleteStump()  {
		if(! isDtoProxy()){
			return this.completeStump;
		}

		if(isCompleteStumpModified())
			return this.completeStump;

		if(! GWT.isClient())
			return null;

		String _value = dtoManager.getProperty(this, instantiatePropertyAccess().completeStump());

		return _value;
	}


	public void setCompleteStump(String completeStump)  {
		/* old value */
		String oldValue = null;
		if(GWT.isClient())
			oldValue = getCompleteStump();

		/* set new value */
		this.completeStump = completeStump;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(completeStump_pa, oldValue, completeStump, this.completeStump_m));

		/* set indicator */
		this.completeStump_m = true;

		this.fireObjectChangedEvent(AutocompleteResultDtoPA.INSTANCE.completeStump(), oldValue);
	}


	public boolean isCompleteStumpModified()  {
		return completeStump_m;
	}


	public static PropertyAccessor<AutocompleteResultDto, String> getCompleteStumpPropertyAccessor()  {
		return completeStump_pa;
	}


	public CommandResultListDto getEntries()  {
		if(! isDtoProxy()){
			return this.entries;
		}

		if(isEntriesModified())
			return this.entries;

		if(! GWT.isClient())
			return null;

		CommandResultListDto _value = dtoManager.getProperty(this, instantiatePropertyAccess().entries());

		if(_value instanceof HasObjectChangedEventHandler){
			((HasObjectChangedEventHandler)_value).addObjectChangedHandler(new net.datenwerke.gxtdto.client.eventbus.handlers.ObjectChangedEventHandler(){
				@Override
				public void onObjectChangedEvent(net.datenwerke.gxtdto.client.eventbus.events.ObjectChangedEvent event){
					if(! isEntriesModified())
						setEntries((CommandResultListDto) event.getObject());
				}
			}
			);
		}
		return _value;
	}


	public void setEntries(CommandResultListDto entries)  {
		/* old value */
		CommandResultListDto oldValue = null;
		if(GWT.isClient())
			oldValue = getEntries();

		/* set new value */
		this.entries = entries;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(entries_pa, oldValue, entries, this.entries_m));

		/* set indicator */
		this.entries_m = true;

		this.fireObjectChangedEvent(AutocompleteResultDtoPA.INSTANCE.entries(), oldValue);
	}


	public boolean isEntriesModified()  {
		return entries_m;
	}


	public static PropertyAccessor<AutocompleteResultDto, CommandResultListDto> getEntriesPropertyAccessor()  {
		return entries_pa;
	}


	public CommandResultListDto getObjectEntries()  {
		if(! isDtoProxy()){
			return this.objectEntries;
		}

		if(isObjectEntriesModified())
			return this.objectEntries;

		if(! GWT.isClient())
			return null;

		CommandResultListDto _value = dtoManager.getProperty(this, instantiatePropertyAccess().objectEntries());

		if(_value instanceof HasObjectChangedEventHandler){
			((HasObjectChangedEventHandler)_value).addObjectChangedHandler(new net.datenwerke.gxtdto.client.eventbus.handlers.ObjectChangedEventHandler(){
				@Override
				public void onObjectChangedEvent(net.datenwerke.gxtdto.client.eventbus.events.ObjectChangedEvent event){
					if(! isObjectEntriesModified())
						setObjectEntries((CommandResultListDto) event.getObject());
				}
			}
			);
		}
		return _value;
	}


	public void setObjectEntries(CommandResultListDto objectEntries)  {
		/* old value */
		CommandResultListDto oldValue = null;
		if(GWT.isClient())
			oldValue = getObjectEntries();

		/* set new value */
		this.objectEntries = objectEntries;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(objectEntries_pa, oldValue, objectEntries, this.objectEntries_m));

		/* set indicator */
		this.objectEntries_m = true;

		this.fireObjectChangedEvent(AutocompleteResultDtoPA.INSTANCE.objectEntries(), oldValue);
	}


	public boolean isObjectEntriesModified()  {
		return objectEntries_m;
	}


	public static PropertyAccessor<AutocompleteResultDto, CommandResultListDto> getObjectEntriesPropertyAccessor()  {
		return objectEntries_pa;
	}


	@Override
	public String toString()  {
		return super.toString();
	}

	public static Dto2PosoMapper newPosoMapper()  {
		return new AutocompleteResultDto2PosoMap();
	}

	public AutocompleteResultDtoPA instantiatePropertyAccess()  {
		return GWT.create(AutocompleteResultDtoPA.class);
	}

	public void clearModified()  {
		this.cmdEntries = null;
		this.cmdEntries_m = false;
		this.completeStump = null;
		this.completeStump_m = false;
		this.entries = null;
		this.entries_m = false;
		this.objectEntries = null;
		this.objectEntries_m = false;
	}


	public boolean isModified()  {
		if(super.isModified())
			return true;
		if(cmdEntries_m)
			return true;
		if(completeStump_m)
			return true;
		if(entries_m)
			return true;
		if(objectEntries_m)
			return true;
		return false;
	}


	public List<PropertyAccessor> getPropertyAccessors()  {
		List<PropertyAccessor> list = super.getPropertyAccessors();
		list.add(cmdEntries_pa);
		list.add(completeStump_pa);
		list.add(entries_pa);
		list.add(objectEntries_pa);
		return list;
	}


	public List<PropertyAccessor> getModifiedPropertyAccessors()  {
		List<PropertyAccessor> list = super.getModifiedPropertyAccessors();
		if(cmdEntries_m)
			list.add(cmdEntries_pa);
		if(completeStump_m)
			list.add(completeStump_pa);
		if(entries_m)
			list.add(entries_pa);
		if(objectEntries_m)
			list.add(objectEntries_pa);
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsByView(net.datenwerke.gxtdto.client.dtomanager.DtoView view)  {
		List<PropertyAccessor> list = super.getPropertyAccessorsByView(view);
		if(view.compareTo(DtoView.NORMAL) >= 0){
			list.add(cmdEntries_pa);
			list.add(completeStump_pa);
			list.add(entries_pa);
			list.add(objectEntries_pa);
		}
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsForDtos()  {
		List<PropertyAccessor> list = super.getPropertyAccessorsForDtos();
		list.add(cmdEntries_pa);
		list.add(entries_pa);
		list.add(objectEntries_pa);
		return list;
	}



	net.datenwerke.rs.terminal.client.terminal.dto.CommandResultListDto wl_0;

}
