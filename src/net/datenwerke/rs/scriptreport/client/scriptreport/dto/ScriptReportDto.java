package net.datenwerke.rs.scriptreport.client.scriptreport.dto;

import com.google.gwt.core.client.GWT;
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
import net.datenwerke.rs.core.client.reportmanager.dto.reports.decorator.ReportDtoDec;
import net.datenwerke.rs.fileserver.client.fileserver.dto.FileServerFileDto;
import net.datenwerke.rs.scriptreport.client.scriptreport.dto.pa.ScriptReportDtoPA;
import net.datenwerke.rs.scriptreport.client.scriptreport.dto.posomap.ScriptReportDto2PosoMap;
import net.datenwerke.rs.scriptreport.client.scriptreport.locale.ScriptReportMessages;
import net.datenwerke.rs.scriptreport.service.scriptreport.entities.ScriptReport;

/**
 * Dto for {@link ScriptReport}
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
abstract public class ScriptReportDto extends ReportDtoDec {


	private static final long serialVersionUID = 1;


	/* Fields */
	private String arguments;
	private  boolean arguments_m;
	public static final String PROPERTY_ARGUMENTS = "dpi-scriptreport-arguments";

	private transient static PropertyAccessor<ScriptReportDto, String> arguments_pa = new PropertyAccessor<ScriptReportDto, String>() {
		@Override
		public void setValue(ScriptReportDto container, String object) {
			container.setArguments(object);
		}

		@Override
		public String getValue(ScriptReportDto container) {
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
		public void setModified(ScriptReportDto container, boolean modified) {
			container.arguments_m = modified;
		}

		@Override
		public boolean isModified(ScriptReportDto container) {
			return container.isArgumentsModified();
		}
	};

	private List<String> exportFormats;
	private  boolean exportFormats_m;
	public static final String PROPERTY_EXPORT_FORMATS = "dpi-scriptreport-exportformats";

	private transient static PropertyAccessor<ScriptReportDto, List<String>> exportFormats_pa = new PropertyAccessor<ScriptReportDto, List<String>>() {
		@Override
		public void setValue(ScriptReportDto container, List<String> object) {
			container.setExportFormats(object);
		}

		@Override
		public List<String> getValue(ScriptReportDto container) {
			return container.getExportFormats();
		}

		@Override
		public Class<?> getType() {
			return List.class;
		}

		@Override
		public String getPath() {
			return "exportFormats";
		}

		@Override
		public void setModified(ScriptReportDto container, boolean modified) {
			container.exportFormats_m = modified;
		}

		@Override
		public boolean isModified(ScriptReportDto container) {
			return container.isExportFormatsModified();
		}
	};

	private FileServerFileDto script;
	private  boolean script_m;
	public static final String PROPERTY_SCRIPT = "dpi-scriptreport-script";

	private transient static PropertyAccessor<ScriptReportDto, FileServerFileDto> script_pa = new PropertyAccessor<ScriptReportDto, FileServerFileDto>() {
		@Override
		public void setValue(ScriptReportDto container, FileServerFileDto object) {
			container.setScript(object);
		}

		@Override
		public FileServerFileDto getValue(ScriptReportDto container) {
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
		public void setModified(ScriptReportDto container, boolean modified) {
			container.script_m = modified;
		}

		@Override
		public boolean isModified(ScriptReportDto container) {
			return container.isScriptModified();
		}
	};


	public ScriptReportDto() {
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

		this.fireObjectChangedEvent(ScriptReportDtoPA.INSTANCE.arguments(), oldValue);
	}


	public boolean isArgumentsModified()  {
		return arguments_m;
	}


	public static PropertyAccessor<ScriptReportDto, String> getArgumentsPropertyAccessor()  {
		return arguments_pa;
	}


	public List<String> getExportFormats()  {
		if(! isDtoProxy()){
			List<String> _currentValue = this.exportFormats;
			if(null == _currentValue)
				this.exportFormats = new ArrayList<String>();

			return this.exportFormats;
		}

		if(isExportFormatsModified())
			return this.exportFormats;

		if(! GWT.isClient())
			return null;

		List<String> _value = dtoManager.getProperty(this, instantiatePropertyAccess().exportFormats());

		return _value;
	}


	public void setExportFormats(List<String> exportFormats)  {
		/* old value */
		List<String> oldValue = null;
		if(GWT.isClient())
			oldValue = getExportFormats();

		/* set new value */
		this.exportFormats = exportFormats;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(exportFormats_pa, oldValue, exportFormats, this.exportFormats_m));

		/* set indicator */
		this.exportFormats_m = true;

		this.fireObjectChangedEvent(ScriptReportDtoPA.INSTANCE.exportFormats(), oldValue);
	}


	public boolean isExportFormatsModified()  {
		return exportFormats_m;
	}


	public static PropertyAccessor<ScriptReportDto, List<String>> getExportFormatsPropertyAccessor()  {
		return exportFormats_pa;
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

		this.fireObjectChangedEvent(ScriptReportDtoPA.INSTANCE.script(), oldValue);
	}


	public boolean isScriptModified()  {
		return script_m;
	}


	public static PropertyAccessor<ScriptReportDto, FileServerFileDto> getScriptPropertyAccessor()  {
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
	public String toTypeDescription()  {
		return ScriptReportMessages.INSTANCE.reportTypeName();
	}

	@Override
	public int hashCode()  {
		if(null == getId())
			return super.hashCode();
		return getId().hashCode();
	}

	@Override
	public boolean equals(Object obj)  {
		if(! (obj instanceof ScriptReportDto))
			return false;

		if(null == getId())
			return super.equals(obj);
		return getId().equals(((ScriptReportDto)obj).getId());
	}

	@Override
	public String toString()  {
		return getClass().getName() + ": " + getId();
	}

	public static Dto2PosoMapper newPosoMapper()  {
		return new ScriptReportDto2PosoMap();
	}

	public ScriptReportDtoPA instantiatePropertyAccess()  {
		return GWT.create(ScriptReportDtoPA.class);
	}

	public void clearModified()  {
		this.arguments = null;
		this.arguments_m = false;
		this.exportFormats = null;
		this.exportFormats_m = false;
		this.script = null;
		this.script_m = false;
	}


	public boolean isModified()  {
		if(super.isModified())
			return true;
		if(arguments_m)
			return true;
		if(exportFormats_m)
			return true;
		if(script_m)
			return true;
		return false;
	}


	public List<PropertyAccessor> getPropertyAccessors()  {
		List<PropertyAccessor> list = super.getPropertyAccessors();
		list.add(arguments_pa);
		list.add(exportFormats_pa);
		list.add(script_pa);
		return list;
	}


	public List<PropertyAccessor> getModifiedPropertyAccessors()  {
		List<PropertyAccessor> list = super.getModifiedPropertyAccessors();
		if(arguments_m)
			list.add(arguments_pa);
		if(exportFormats_m)
			list.add(exportFormats_pa);
		if(script_m)
			list.add(script_pa);
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsByView(net.datenwerke.gxtdto.client.dtomanager.DtoView view)  {
		List<PropertyAccessor> list = super.getPropertyAccessorsByView(view);
		if(view.compareTo(DtoView.LIST) >= 0){
			list.add(exportFormats_pa);
		}
		if(view.compareTo(DtoView.NORMAL) >= 0){
			list.add(arguments_pa);
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
