package net.datenwerke.rs.scriptreport.client.scriptreport.parameters.dto;

import com.google.gwt.core.client.GWT;
import java.lang.Integer;
import java.lang.NullPointerException;
import java.lang.String;
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
import net.datenwerke.rs.core.client.parameters.dto.decorator.ParameterDefinitionDtoDec;
import net.datenwerke.rs.fileserver.client.fileserver.dto.FileServerFileDto;
import net.datenwerke.rs.scriptreport.client.scriptreport.locale.ScriptReportMessages;
import net.datenwerke.rs.scriptreport.client.scriptreport.parameters.dto.pa.ScriptParameterDefinitionDtoPA;
import net.datenwerke.rs.scriptreport.client.scriptreport.parameters.dto.posomap.ScriptParameterDefinitionDto2PosoMap;
import net.datenwerke.rs.scriptreport.service.scriptreport.parameter.ScriptParameterDefinition;

/**
 * Dto for {@link ScriptParameterDefinition}
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class ScriptParameterDefinitionDto extends ParameterDefinitionDtoDec {


	private static final long serialVersionUID = 1;


	/* Fields */
	private String arguments;
	private  boolean arguments_m;
	public static final String PROPERTY_ARGUMENTS = "dpi-scriptparameterdefinition-arguments";

	private transient static PropertyAccessor<ScriptParameterDefinitionDto, String> arguments_pa = new PropertyAccessor<ScriptParameterDefinitionDto, String>() {
		@Override
		public void setValue(ScriptParameterDefinitionDto container, String object) {
			container.setArguments(object);
		}

		@Override
		public String getValue(ScriptParameterDefinitionDto container) {
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
		public void setModified(ScriptParameterDefinitionDto container, boolean modified) {
			container.arguments_m = modified;
		}

		@Override
		public boolean isModified(ScriptParameterDefinitionDto container) {
			return container.isArgumentsModified();
		}
	};

	private String defaultValue;
	private  boolean defaultValue_m;
	public static final String PROPERTY_DEFAULT_VALUE = "dpi-scriptparameterdefinition-defaultvalue";

	private transient static PropertyAccessor<ScriptParameterDefinitionDto, String> defaultValue_pa = new PropertyAccessor<ScriptParameterDefinitionDto, String>() {
		@Override
		public void setValue(ScriptParameterDefinitionDto container, String object) {
			container.setDefaultValue(object);
		}

		@Override
		public String getValue(ScriptParameterDefinitionDto container) {
			return container.getDefaultValue();
		}

		@Override
		public Class<?> getType() {
			return String.class;
		}

		@Override
		public String getPath() {
			return "defaultValue";
		}

		@Override
		public void setModified(ScriptParameterDefinitionDto container, boolean modified) {
			container.defaultValue_m = modified;
		}

		@Override
		public boolean isModified(ScriptParameterDefinitionDto container) {
			return container.isDefaultValueModified();
		}
	};

	private Integer height;
	private  boolean height_m;
	public static final String PROPERTY_HEIGHT = "dpi-scriptparameterdefinition-height";

	private transient static PropertyAccessor<ScriptParameterDefinitionDto, Integer> height_pa = new PropertyAccessor<ScriptParameterDefinitionDto, Integer>() {
		@Override
		public void setValue(ScriptParameterDefinitionDto container, Integer object) {
			container.setHeight(object);
		}

		@Override
		public Integer getValue(ScriptParameterDefinitionDto container) {
			return container.getHeight();
		}

		@Override
		public Class<?> getType() {
			return Integer.class;
		}

		@Override
		public String getPath() {
			return "height";
		}

		@Override
		public void setModified(ScriptParameterDefinitionDto container, boolean modified) {
			container.height_m = modified;
		}

		@Override
		public boolean isModified(ScriptParameterDefinitionDto container) {
			return container.isHeightModified();
		}
	};

	private FileServerFileDto script;
	private  boolean script_m;
	public static final String PROPERTY_SCRIPT = "dpi-scriptparameterdefinition-script";

	private transient static PropertyAccessor<ScriptParameterDefinitionDto, FileServerFileDto> script_pa = new PropertyAccessor<ScriptParameterDefinitionDto, FileServerFileDto>() {
		@Override
		public void setValue(ScriptParameterDefinitionDto container, FileServerFileDto object) {
			container.setScript(object);
		}

		@Override
		public FileServerFileDto getValue(ScriptParameterDefinitionDto container) {
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
		public void setModified(ScriptParameterDefinitionDto container, boolean modified) {
			container.script_m = modified;
		}

		@Override
		public boolean isModified(ScriptParameterDefinitionDto container) {
			return container.isScriptModified();
		}
	};

	private Integer width;
	private  boolean width_m;
	public static final String PROPERTY_WIDTH = "dpi-scriptparameterdefinition-width";

	private transient static PropertyAccessor<ScriptParameterDefinitionDto, Integer> width_pa = new PropertyAccessor<ScriptParameterDefinitionDto, Integer>() {
		@Override
		public void setValue(ScriptParameterDefinitionDto container, Integer object) {
			container.setWidth(object);
		}

		@Override
		public Integer getValue(ScriptParameterDefinitionDto container) {
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
		public void setModified(ScriptParameterDefinitionDto container, boolean modified) {
			container.width_m = modified;
		}

		@Override
		public boolean isModified(ScriptParameterDefinitionDto container) {
			return container.isWidthModified();
		}
	};


	public ScriptParameterDefinitionDto() {
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

		this.fireObjectChangedEvent(ScriptParameterDefinitionDtoPA.INSTANCE.arguments(), oldValue);
	}


	public boolean isArgumentsModified()  {
		return arguments_m;
	}


	public static PropertyAccessor<ScriptParameterDefinitionDto, String> getArgumentsPropertyAccessor()  {
		return arguments_pa;
	}


	public String getDefaultValue()  {
		if(! isDtoProxy()){
			return this.defaultValue;
		}

		if(isDefaultValueModified())
			return this.defaultValue;

		if(! GWT.isClient())
			return null;

		String _value = dtoManager.getProperty(this, instantiatePropertyAccess().defaultValue());

		return _value;
	}


	public void setDefaultValue(String defaultValue)  {
		/* old value */
		String oldValue = null;
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

		this.fireObjectChangedEvent(ScriptParameterDefinitionDtoPA.INSTANCE.defaultValue(), oldValue);
	}


	public boolean isDefaultValueModified()  {
		return defaultValue_m;
	}


	public static PropertyAccessor<ScriptParameterDefinitionDto, String> getDefaultValuePropertyAccessor()  {
		return defaultValue_pa;
	}


	public Integer getHeight()  {
		if(! isDtoProxy()){
			return this.height;
		}

		if(isHeightModified())
			return this.height;

		if(! GWT.isClient())
			return null;

		Integer _value = dtoManager.getProperty(this, instantiatePropertyAccess().height());

		return _value;
	}


	public void setHeight(Integer height)  {
		/* old value */
		Integer oldValue = null;
		if(GWT.isClient())
			oldValue = getHeight();

		/* set new value */
		this.height = height;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(height_pa, oldValue, height, this.height_m));

		/* set indicator */
		this.height_m = true;

		this.fireObjectChangedEvent(ScriptParameterDefinitionDtoPA.INSTANCE.height(), oldValue);
	}


	public boolean isHeightModified()  {
		return height_m;
	}


	public static PropertyAccessor<ScriptParameterDefinitionDto, Integer> getHeightPropertyAccessor()  {
		return height_pa;
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

		this.fireObjectChangedEvent(ScriptParameterDefinitionDtoPA.INSTANCE.script(), oldValue);
	}


	public boolean isScriptModified()  {
		return script_m;
	}


	public static PropertyAccessor<ScriptParameterDefinitionDto, FileServerFileDto> getScriptPropertyAccessor()  {
		return script_pa;
	}


	public Integer getWidth()  {
		if(! isDtoProxy()){
			return this.width;
		}

		if(isWidthModified())
			return this.width;

		if(! GWT.isClient())
			return null;

		Integer _value = dtoManager.getProperty(this, instantiatePropertyAccess().width());

		return _value;
	}


	public void setWidth(Integer width)  {
		/* old value */
		Integer oldValue = null;
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

		this.fireObjectChangedEvent(ScriptParameterDefinitionDtoPA.INSTANCE.width(), oldValue);
	}


	public boolean isWidthModified()  {
		return width_m;
	}


	public static PropertyAccessor<ScriptParameterDefinitionDto, Integer> getWidthPropertyAccessor()  {
		return width_pa;
	}


	@Override
	public String toDisplayTitle()  {
		try{
			return ScriptReportMessages.INSTANCE.scriptParameterText();
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
		if(! (obj instanceof ScriptParameterDefinitionDto))
			return false;

		if(null == getId())
			return super.equals(obj);
		return getId().equals(((ScriptParameterDefinitionDto)obj).getId());
	}

	@Override
	public String toString()  {
		return getClass().getName() + ": " + getId();
	}

	public static Dto2PosoMapper newPosoMapper()  {
		return new ScriptParameterDefinitionDto2PosoMap();
	}

	public ScriptParameterDefinitionDtoPA instantiatePropertyAccess()  {
		return GWT.create(ScriptParameterDefinitionDtoPA.class);
	}

	public void clearModified()  {
		this.arguments = null;
		this.arguments_m = false;
		this.defaultValue = null;
		this.defaultValue_m = false;
		this.height = null;
		this.height_m = false;
		this.script = null;
		this.script_m = false;
		this.width = null;
		this.width_m = false;
	}


	public boolean isModified()  {
		if(super.isModified())
			return true;
		if(arguments_m)
			return true;
		if(defaultValue_m)
			return true;
		if(height_m)
			return true;
		if(script_m)
			return true;
		if(width_m)
			return true;
		return false;
	}


	public List<PropertyAccessor> getPropertyAccessors()  {
		List<PropertyAccessor> list = super.getPropertyAccessors();
		list.add(arguments_pa);
		list.add(defaultValue_pa);
		list.add(height_pa);
		list.add(script_pa);
		list.add(width_pa);
		return list;
	}


	public List<PropertyAccessor> getModifiedPropertyAccessors()  {
		List<PropertyAccessor> list = super.getModifiedPropertyAccessors();
		if(arguments_m)
			list.add(arguments_pa);
		if(defaultValue_m)
			list.add(defaultValue_pa);
		if(height_m)
			list.add(height_pa);
		if(script_m)
			list.add(script_pa);
		if(width_m)
			list.add(width_pa);
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsByView(net.datenwerke.gxtdto.client.dtomanager.DtoView view)  {
		List<PropertyAccessor> list = super.getPropertyAccessorsByView(view);
		if(view.compareTo(DtoView.NORMAL) >= 0){
			list.add(arguments_pa);
			list.add(defaultValue_pa);
			list.add(height_pa);
			list.add(script_pa);
			list.add(width_pa);
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
