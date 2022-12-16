package net.datenwerke.rs.scriptdatasource.client.scriptdatasource.dto;

import com.google.gwt.core.client.GWT;
import java.lang.String;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.gxtdto.client.dtomanager.Dto2PosoMapper;
import net.datenwerke.gxtdto.client.dtomanager.DtoView;
import net.datenwerke.gxtdto.client.dtomanager.PropertyAccessor;
import net.datenwerke.gxtdto.client.dtomanager.redoundo.ChangeTracker;
import net.datenwerke.rs.core.client.datasourcemanager.dto.DatasourceDefinitionConfigDto;
import net.datenwerke.rs.scriptdatasource.client.scriptdatasource.dto.pa.ScriptDatasourceConfigDtoPA;
import net.datenwerke.rs.scriptdatasource.client.scriptdatasource.dto.posomap.ScriptDatasourceConfigDto2PosoMap;
import net.datenwerke.rs.scriptdatasource.service.scriptdatasource.entities.ScriptDatasourceConfig;

/**
 * Dto for {@link ScriptDatasourceConfig}
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class ScriptDatasourceConfigDto extends DatasourceDefinitionConfigDto {


	private static final long serialVersionUID = 1;


	/* Fields */
	private String arguments;
	private  boolean arguments_m;
	public static final String PROPERTY_ARGUMENTS = "dpi-scriptdatasourceconfig-arguments";

	private transient static PropertyAccessor<ScriptDatasourceConfigDto, String> arguments_pa = new PropertyAccessor<ScriptDatasourceConfigDto, String>() {
		@Override
		public void setValue(ScriptDatasourceConfigDto container, String object) {
			container.setArguments(object);
		}

		@Override
		public String getValue(ScriptDatasourceConfigDto container) {
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
		public void setModified(ScriptDatasourceConfigDto container, boolean modified) {
			container.arguments_m = modified;
		}

		@Override
		public boolean isModified(ScriptDatasourceConfigDto container) {
			return container.isArgumentsModified();
		}
	};

	private String queryWrapper;
	private  boolean queryWrapper_m;
	public static final String PROPERTY_QUERY_WRAPPER = "dpi-scriptdatasourceconfig-querywrapper";

	private transient static PropertyAccessor<ScriptDatasourceConfigDto, String> queryWrapper_pa = new PropertyAccessor<ScriptDatasourceConfigDto, String>() {
		@Override
		public void setValue(ScriptDatasourceConfigDto container, String object) {
			container.setQueryWrapper(object);
		}

		@Override
		public String getValue(ScriptDatasourceConfigDto container) {
			return container.getQueryWrapper();
		}

		@Override
		public Class<?> getType() {
			return String.class;
		}

		@Override
		public String getPath() {
			return "queryWrapper";
		}

		@Override
		public void setModified(ScriptDatasourceConfigDto container, boolean modified) {
			container.queryWrapper_m = modified;
		}

		@Override
		public boolean isModified(ScriptDatasourceConfigDto container) {
			return container.isQueryWrapperModified();
		}
	};

	private String script;
	private  boolean script_m;
	public static final String PROPERTY_SCRIPT = "dpi-scriptdatasourceconfig-script";

	private transient static PropertyAccessor<ScriptDatasourceConfigDto, String> script_pa = new PropertyAccessor<ScriptDatasourceConfigDto, String>() {
		@Override
		public void setValue(ScriptDatasourceConfigDto container, String object) {
			container.setScript(object);
		}

		@Override
		public String getValue(ScriptDatasourceConfigDto container) {
			return container.getScript();
		}

		@Override
		public Class<?> getType() {
			return String.class;
		}

		@Override
		public String getPath() {
			return "script";
		}

		@Override
		public void setModified(ScriptDatasourceConfigDto container, boolean modified) {
			container.script_m = modified;
		}

		@Override
		public boolean isModified(ScriptDatasourceConfigDto container) {
			return container.isScriptModified();
		}
	};


	public ScriptDatasourceConfigDto() {
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

		this.fireObjectChangedEvent(ScriptDatasourceConfigDtoPA.INSTANCE.arguments(), oldValue);
	}


	public boolean isArgumentsModified()  {
		return arguments_m;
	}


	public static PropertyAccessor<ScriptDatasourceConfigDto, String> getArgumentsPropertyAccessor()  {
		return arguments_pa;
	}


	public String getQueryWrapper()  {
		if(! isDtoProxy()){
			return this.queryWrapper;
		}

		if(isQueryWrapperModified())
			return this.queryWrapper;

		if(! GWT.isClient())
			return null;

		String _value = dtoManager.getProperty(this, instantiatePropertyAccess().queryWrapper());

		return _value;
	}


	public void setQueryWrapper(String queryWrapper)  {
		/* old value */
		String oldValue = null;
		if(GWT.isClient())
			oldValue = getQueryWrapper();

		/* set new value */
		this.queryWrapper = queryWrapper;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(queryWrapper_pa, oldValue, queryWrapper, this.queryWrapper_m));

		/* set indicator */
		this.queryWrapper_m = true;

		this.fireObjectChangedEvent(ScriptDatasourceConfigDtoPA.INSTANCE.queryWrapper(), oldValue);
	}


	public boolean isQueryWrapperModified()  {
		return queryWrapper_m;
	}


	public static PropertyAccessor<ScriptDatasourceConfigDto, String> getQueryWrapperPropertyAccessor()  {
		return queryWrapper_pa;
	}


	public String getScript()  {
		if(! isDtoProxy()){
			return this.script;
		}

		if(isScriptModified())
			return this.script;

		if(! GWT.isClient())
			return null;

		String _value = dtoManager.getProperty(this, instantiatePropertyAccess().script());

		return _value;
	}


	public void setScript(String script)  {
		/* old value */
		String oldValue = null;
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

		this.fireObjectChangedEvent(ScriptDatasourceConfigDtoPA.INSTANCE.script(), oldValue);
	}


	public boolean isScriptModified()  {
		return script_m;
	}


	public static PropertyAccessor<ScriptDatasourceConfigDto, String> getScriptPropertyAccessor()  {
		return script_pa;
	}


	@Override
	public int hashCode()  {
		if(null == getId())
			return super.hashCode();
		return getId().hashCode();
	}

	@Override
	public boolean equals(Object obj)  {
		if(! (obj instanceof ScriptDatasourceConfigDto))
			return false;

		if(null == getId())
			return super.equals(obj);
		return getId().equals(((ScriptDatasourceConfigDto)obj).getId());
	}

	@Override
	public String toString()  {
		return getClass().getName() + ": " + getId();
	}

	public static Dto2PosoMapper newPosoMapper()  {
		return new ScriptDatasourceConfigDto2PosoMap();
	}

	public ScriptDatasourceConfigDtoPA instantiatePropertyAccess()  {
		return GWT.create(ScriptDatasourceConfigDtoPA.class);
	}

	public void clearModified()  {
		this.arguments = null;
		this.arguments_m = false;
		this.queryWrapper = null;
		this.queryWrapper_m = false;
		this.script = null;
		this.script_m = false;
	}


	public boolean isModified()  {
		if(super.isModified())
			return true;
		if(arguments_m)
			return true;
		if(queryWrapper_m)
			return true;
		if(script_m)
			return true;
		return false;
	}


	public List<PropertyAccessor> getPropertyAccessors()  {
		List<PropertyAccessor> list = super.getPropertyAccessors();
		list.add(arguments_pa);
		list.add(queryWrapper_pa);
		list.add(script_pa);
		return list;
	}


	public List<PropertyAccessor> getModifiedPropertyAccessors()  {
		List<PropertyAccessor> list = super.getModifiedPropertyAccessors();
		if(arguments_m)
			list.add(arguments_pa);
		if(queryWrapper_m)
			list.add(queryWrapper_pa);
		if(script_m)
			list.add(script_pa);
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsByView(net.datenwerke.gxtdto.client.dtomanager.DtoView view)  {
		List<PropertyAccessor> list = super.getPropertyAccessorsByView(view);
		if(view.compareTo(DtoView.NORMAL) >= 0){
			list.add(arguments_pa);
			list.add(queryWrapper_pa);
			list.add(script_pa);
		}
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsForDtos()  {
		List<PropertyAccessor> list = super.getPropertyAccessorsForDtos();
		return list;
	}




}
