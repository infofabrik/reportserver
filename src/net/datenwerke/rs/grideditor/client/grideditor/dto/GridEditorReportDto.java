package net.datenwerke.rs.grideditor.client.grideditor.dto;

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
import net.datenwerke.rs.grideditor.client.grideditor.dto.pa.GridEditorReportDtoPA;
import net.datenwerke.rs.grideditor.client.grideditor.dto.posomap.GridEditorReportDto2PosoMap;
import net.datenwerke.rs.grideditor.client.grideditor.locale.GridEditorMessages;
import net.datenwerke.rs.grideditor.service.grideditor.entities.GridEditorReport;

/**
 * Dto for {@link GridEditorReport}
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
abstract public class GridEditorReportDto extends ReportDtoDec {


	private static final long serialVersionUID = 1;


	/* Fields */
	private String arguments;
	private  boolean arguments_m;
	public static final String PROPERTY_ARGUMENTS = "dpi-grideditorreport-arguments";

	private transient static PropertyAccessor<GridEditorReportDto, String> arguments_pa = new PropertyAccessor<GridEditorReportDto, String>() {
		@Override
		public void setValue(GridEditorReportDto container, String object) {
			container.setArguments(object);
		}

		@Override
		public String getValue(GridEditorReportDto container) {
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
		public void setModified(GridEditorReportDto container, boolean modified) {
			container.arguments_m = modified;
		}

		@Override
		public boolean isModified(GridEditorReportDto container) {
			return container.isArgumentsModified();
		}
	};

	private FileServerFileDto script;
	private  boolean script_m;
	public static final String PROPERTY_SCRIPT = "dpi-grideditorreport-script";

	private transient static PropertyAccessor<GridEditorReportDto, FileServerFileDto> script_pa = new PropertyAccessor<GridEditorReportDto, FileServerFileDto>() {
		@Override
		public void setValue(GridEditorReportDto container, FileServerFileDto object) {
			container.setScript(object);
		}

		@Override
		public FileServerFileDto getValue(GridEditorReportDto container) {
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
		public void setModified(GridEditorReportDto container, boolean modified) {
			container.script_m = modified;
		}

		@Override
		public boolean isModified(GridEditorReportDto container) {
			return container.isScriptModified();
		}
	};


	public GridEditorReportDto() {
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

		this.fireObjectChangedEvent(GridEditorReportDtoPA.INSTANCE.arguments(), oldValue);
	}


	public boolean isArgumentsModified()  {
		return arguments_m;
	}


	public static PropertyAccessor<GridEditorReportDto, String> getArgumentsPropertyAccessor()  {
		return arguments_pa;
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

		this.fireObjectChangedEvent(GridEditorReportDtoPA.INSTANCE.script(), oldValue);
	}


	public boolean isScriptModified()  {
		return script_m;
	}


	public static PropertyAccessor<GridEditorReportDto, FileServerFileDto> getScriptPropertyAccessor()  {
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
		return GridEditorMessages.INSTANCE.reportTypeName();
	}

	@Override
	public int hashCode()  {
		if(null == getId())
			return super.hashCode();
		return getId().hashCode();
	}

	@Override
	public boolean equals(Object obj)  {
		if(! (obj instanceof GridEditorReportDto))
			return false;

		if(null == getId())
			return super.equals(obj);
		return getId().equals(((GridEditorReportDto)obj).getId());
	}

	@Override
	public String toString()  {
		return getClass().getName() + ": " + getId();
	}

	public static Dto2PosoMapper newPosoMapper()  {
		return new GridEditorReportDto2PosoMap();
	}

	public GridEditorReportDtoPA instantiatePropertyAccess()  {
		return GWT.create(GridEditorReportDtoPA.class);
	}

	public void clearModified()  {
		this.arguments = null;
		this.arguments_m = false;
		this.script = null;
		this.script_m = false;
	}


	public boolean isModified()  {
		if(super.isModified())
			return true;
		if(arguments_m)
			return true;
		if(script_m)
			return true;
		return false;
	}


	public List<PropertyAccessor> getPropertyAccessors()  {
		List<PropertyAccessor> list = super.getPropertyAccessors();
		list.add(arguments_pa);
		list.add(script_pa);
		return list;
	}


	public List<PropertyAccessor> getModifiedPropertyAccessors()  {
		List<PropertyAccessor> list = super.getModifiedPropertyAccessors();
		if(arguments_m)
			list.add(arguments_pa);
		if(script_m)
			list.add(script_pa);
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsByView(net.datenwerke.gxtdto.client.dtomanager.DtoView view)  {
		List<PropertyAccessor> list = super.getPropertyAccessorsByView(view);
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
