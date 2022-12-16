package net.datenwerke.rs.scriptdatasource.client.scriptdatasource.dto;

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
import net.datenwerke.rs.core.client.datasourcemanager.dto.DatasourceDefinitionDto;
import net.datenwerke.rs.fileserver.client.fileserver.dto.FileServerFileDto;
import net.datenwerke.rs.scriptdatasource.client.scriptdatasource.dto.pa.ScriptDatasourceDtoPA;
import net.datenwerke.rs.scriptdatasource.client.scriptdatasource.dto.posomap.ScriptDatasourceDto2PosoMap;
import net.datenwerke.rs.scriptdatasource.service.scriptdatasource.entities.ScriptDatasource;

/**
 * Dto for {@link ScriptDatasource}
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class ScriptDatasourceDto extends DatasourceDefinitionDto {


	private static final long serialVersionUID = 1;


	/* Fields */
	private int databaseCache;
	private  boolean databaseCache_m;
	public static final String PROPERTY_DATABASE_CACHE = "dpi-scriptdatasource-databasecache";

	private transient static PropertyAccessor<ScriptDatasourceDto, Integer> databaseCache_pa = new PropertyAccessor<ScriptDatasourceDto, Integer>() {
		@Override
		public void setValue(ScriptDatasourceDto container, Integer object) {
			container.setDatabaseCache(object);
		}

		@Override
		public Integer getValue(ScriptDatasourceDto container) {
			return container.getDatabaseCache();
		}

		@Override
		public Class<?> getType() {
			return Integer.class;
		}

		@Override
		public String getPath() {
			return "databaseCache";
		}

		@Override
		public void setModified(ScriptDatasourceDto container, boolean modified) {
			container.databaseCache_m = modified;
		}

		@Override
		public boolean isModified(ScriptDatasourceDto container) {
			return container.isDatabaseCacheModified();
		}
	};

	private boolean defineAtTarget;
	private  boolean defineAtTarget_m;
	public static final String PROPERTY_DEFINE_AT_TARGET = "dpi-scriptdatasource-defineattarget";

	private transient static PropertyAccessor<ScriptDatasourceDto, Boolean> defineAtTarget_pa = new PropertyAccessor<ScriptDatasourceDto, Boolean>() {
		@Override
		public void setValue(ScriptDatasourceDto container, Boolean object) {
			container.setDefineAtTarget(object);
		}

		@Override
		public Boolean getValue(ScriptDatasourceDto container) {
			return container.isDefineAtTarget();
		}

		@Override
		public Class<?> getType() {
			return Boolean.class;
		}

		@Override
		public String getPath() {
			return "defineAtTarget";
		}

		@Override
		public void setModified(ScriptDatasourceDto container, boolean modified) {
			container.defineAtTarget_m = modified;
		}

		@Override
		public boolean isModified(ScriptDatasourceDto container) {
			return container.isDefineAtTargetModified();
		}
	};

	private FileServerFileDto script;
	private  boolean script_m;
	public static final String PROPERTY_SCRIPT = "dpi-scriptdatasource-script";

	private transient static PropertyAccessor<ScriptDatasourceDto, FileServerFileDto> script_pa = new PropertyAccessor<ScriptDatasourceDto, FileServerFileDto>() {
		@Override
		public void setValue(ScriptDatasourceDto container, FileServerFileDto object) {
			container.setScript(object);
		}

		@Override
		public FileServerFileDto getValue(ScriptDatasourceDto container) {
			return container.getScript();
		}

		@Override
		public Class<?> getType() {
			return FileServerFileDto.class;
		}

		@Override
		public String getPath() {
			return "script";
		}

		@Override
		public void setModified(ScriptDatasourceDto container, boolean modified) {
			container.script_m = modified;
		}

		@Override
		public boolean isModified(ScriptDatasourceDto container) {
			return container.isScriptModified();
		}
	};


	public ScriptDatasourceDto() {
		super();
	}

	public int getDatabaseCache()  {
		if(! isDtoProxy()){
			return this.databaseCache;
		}

		if(isDatabaseCacheModified())
			return this.databaseCache;

		if(! GWT.isClient())
			return 0;

		int _value = dtoManager.getProperty(this, instantiatePropertyAccess().databaseCache());

		return _value;
	}


	public void setDatabaseCache(int databaseCache)  {
		/* old value */
		int oldValue = 0;
		if(GWT.isClient())
			oldValue = getDatabaseCache();

		/* set new value */
		this.databaseCache = databaseCache;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(databaseCache_pa, oldValue, databaseCache, this.databaseCache_m));

		/* set indicator */
		this.databaseCache_m = true;

		this.fireObjectChangedEvent(ScriptDatasourceDtoPA.INSTANCE.databaseCache(), oldValue);
	}


	public boolean isDatabaseCacheModified()  {
		return databaseCache_m;
	}


	public static PropertyAccessor<ScriptDatasourceDto, Integer> getDatabaseCachePropertyAccessor()  {
		return databaseCache_pa;
	}


	public boolean isDefineAtTarget()  {
		if(! isDtoProxy()){
			return this.defineAtTarget;
		}

		if(isDefineAtTargetModified())
			return this.defineAtTarget;

		if(! GWT.isClient())
			return false;

		boolean _value = dtoManager.getProperty(this, instantiatePropertyAccess().defineAtTarget());

		return _value;
	}


	public void setDefineAtTarget(boolean defineAtTarget)  {
		/* old value */
		boolean oldValue = false;
		if(GWT.isClient())
			oldValue = isDefineAtTarget();

		/* set new value */
		this.defineAtTarget = defineAtTarget;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(defineAtTarget_pa, oldValue, defineAtTarget, this.defineAtTarget_m));

		/* set indicator */
		this.defineAtTarget_m = true;

		this.fireObjectChangedEvent(ScriptDatasourceDtoPA.INSTANCE.defineAtTarget(), oldValue);
	}


	public boolean isDefineAtTargetModified()  {
		return defineAtTarget_m;
	}


	public static PropertyAccessor<ScriptDatasourceDto, Boolean> getDefineAtTargetPropertyAccessor()  {
		return defineAtTarget_pa;
	}


	public FileServerFileDto getScript()  {
		if(! isDtoProxy()){
			return this.script;
		}

		if(isScriptModified())
			return this.script;

		if(! GWT.isClient())
			return null;

		FileServerFileDto _value = dtoManager.getProperty(this, instantiatePropertyAccess().script());

		if(_value instanceof HasObjectChangedEventHandler){
			((HasObjectChangedEventHandler)_value).addObjectChangedHandler(new net.datenwerke.gxtdto.client.eventbus.handlers.ObjectChangedEventHandler(){
				@Override
				public void onObjectChangedEvent(net.datenwerke.gxtdto.client.eventbus.events.ObjectChangedEvent event){
					if(! isScriptModified())
						setScript((FileServerFileDto) event.getObject());
				}
			}
			);
		}
		return _value;
	}


	public void setScript(FileServerFileDto script)  {
		/* old value */
		FileServerFileDto oldValue = null;
		if(GWT.isClient())
			oldValue = getScript();

		/* set new value */
		this.script = script;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(script_pa, oldValue, script, this.script_m));

		/* set indicator */
		this.script_m = true;

		this.fireObjectChangedEvent(ScriptDatasourceDtoPA.INSTANCE.script(), oldValue);
	}


	public boolean isScriptModified()  {
		return script_m;
	}


	public static PropertyAccessor<ScriptDatasourceDto, FileServerFileDto> getScriptPropertyAccessor()  {
		return script_pa;
	}


	@Override
	public String toDisplayTitle()  {
		try{
			if(null == getName())
				return BaseMessages.INSTANCE.unnamed();
			return getName().toString();
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
		if(! (obj instanceof ScriptDatasourceDto))
			return false;

		if(null == getId())
			return super.equals(obj);
		return getId().equals(((ScriptDatasourceDto)obj).getId());
	}

	@Override
	public String toString()  {
		return getClass().getName() + ": " + getId();
	}

	public static Dto2PosoMapper newPosoMapper()  {
		return new ScriptDatasourceDto2PosoMap();
	}

	public ScriptDatasourceDtoPA instantiatePropertyAccess()  {
		return GWT.create(ScriptDatasourceDtoPA.class);
	}

	public void clearModified()  {
		this.databaseCache = 0;
		this.databaseCache_m = false;
		this.defineAtTarget = false;
		this.defineAtTarget_m = false;
		this.script = null;
		this.script_m = false;
	}


	public boolean isModified()  {
		if(super.isModified())
			return true;
		if(databaseCache_m)
			return true;
		if(defineAtTarget_m)
			return true;
		if(script_m)
			return true;
		return false;
	}


	public List<PropertyAccessor> getPropertyAccessors()  {
		List<PropertyAccessor> list = super.getPropertyAccessors();
		list.add(databaseCache_pa);
		list.add(defineAtTarget_pa);
		list.add(script_pa);
		return list;
	}


	public List<PropertyAccessor> getModifiedPropertyAccessors()  {
		List<PropertyAccessor> list = super.getModifiedPropertyAccessors();
		if(databaseCache_m)
			list.add(databaseCache_pa);
		if(defineAtTarget_m)
			list.add(defineAtTarget_pa);
		if(script_m)
			list.add(script_pa);
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsByView(net.datenwerke.gxtdto.client.dtomanager.DtoView view)  {
		List<PropertyAccessor> list = super.getPropertyAccessorsByView(view);
		if(view.compareTo(DtoView.MINIMAL) >= 0){
			list.add(defineAtTarget_pa);
		}
		if(view.compareTo(DtoView.NORMAL) >= 0){
			list.add(databaseCache_pa);
			list.add(script_pa);
		}
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsForDtos()  {
		List<PropertyAccessor> list = super.getPropertyAccessorsForDtos();
		list.add(script_pa);
		return list;
	}



	net.datenwerke.rs.fileserver.client.fileserver.dto.FileServerFileDto wl_0;

}
