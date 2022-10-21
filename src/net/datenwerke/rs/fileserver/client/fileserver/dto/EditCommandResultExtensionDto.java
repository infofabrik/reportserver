package net.datenwerke.rs.fileserver.client.fileserver.dto;

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
import net.datenwerke.gxtdto.client.dtomanager.redoundo.ChangeTracker;
import net.datenwerke.gxtdto.client.eventbus.events.ObjectChangedEvent;
import net.datenwerke.gxtdto.client.eventbus.handlers.ObjectChangedEventHandler;
import net.datenwerke.gxtdto.client.eventbus.handlers.has.HasObjectChangedEventHandler;
import net.datenwerke.rs.fileserver.client.fileserver.dto.FileServerFileDto;
import net.datenwerke.rs.fileserver.client.fileserver.dto.pa.EditCommandResultExtensionDtoPA;
import net.datenwerke.rs.fileserver.client.fileserver.dto.posomap.EditCommandResultExtensionDto2PosoMap;
import net.datenwerke.rs.fileserver.service.fileserver.terminal.commands.EditCommandResultExtension;
import net.datenwerke.rs.terminal.client.terminal.dto.CommandResultExtensionDto;

/**
 * Dto for {@link EditCommandResultExtension}
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class EditCommandResultExtensionDto extends CommandResultExtensionDto {


	private static final long serialVersionUID = 1;


	/* Fields */
	private String data;
	private  boolean data_m;
	public static final String PROPERTY_DATA = "dpi-editcommandresultextension-data";

	private transient static PropertyAccessor<EditCommandResultExtensionDto, String> data_pa = new PropertyAccessor<EditCommandResultExtensionDto, String>() {
		@Override
		public void setValue(EditCommandResultExtensionDto container, String object) {
			container.setData(object);
		}

		@Override
		public String getValue(EditCommandResultExtensionDto container) {
			return container.getData();
		}

		@Override
		public Class<?> getType() {
			return String.class;
		}

		@Override
		public String getPath() {
			return "data";
		}

		@Override
		public void setModified(EditCommandResultExtensionDto container, boolean modified) {
			container.data_m = modified;
		}

		@Override
		public boolean isModified(EditCommandResultExtensionDto container) {
			return container.isDataModified();
		}
	};

	private FileServerFileDto file;
	private  boolean file_m;
	public static final String PROPERTY_FILE = "dpi-editcommandresultextension-file";

	private transient static PropertyAccessor<EditCommandResultExtensionDto, FileServerFileDto> file_pa = new PropertyAccessor<EditCommandResultExtensionDto, FileServerFileDto>() {
		@Override
		public void setValue(EditCommandResultExtensionDto container, FileServerFileDto object) {
			container.setFile(object);
		}

		@Override
		public FileServerFileDto getValue(EditCommandResultExtensionDto container) {
			return container.getFile();
		}

		@Override
		public Class<?> getType() {
			return FileServerFileDto.class;
		}

		@Override
		public String getPath() {
			return "file";
		}

		@Override
		public void setModified(EditCommandResultExtensionDto container, boolean modified) {
			container.file_m = modified;
		}

		@Override
		public boolean isModified(EditCommandResultExtensionDto container) {
			return container.isFileModified();
		}
	};


	public EditCommandResultExtensionDto() {
		super();
	}

	public String getData()  {
		if(! isDtoProxy()){
			return this.data;
		}

		if(isDataModified())
			return this.data;

		if(! GWT.isClient())
			return null;

		String _value = dtoManager.getProperty(this, instantiatePropertyAccess().data());

		return _value;
	}


	public void setData(String data)  {
		/* old value */
		String oldValue = null;
		if(GWT.isClient())
			oldValue = getData();

		/* set new value */
		this.data = data;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(data_pa, oldValue, data, this.data_m));

		/* set indicator */
		this.data_m = true;

		this.fireObjectChangedEvent(EditCommandResultExtensionDtoPA.INSTANCE.data(), oldValue);
	}


	public boolean isDataModified()  {
		return data_m;
	}


	public static PropertyAccessor<EditCommandResultExtensionDto, String> getDataPropertyAccessor()  {
		return data_pa;
	}


	public FileServerFileDto getFile()  {
		if(! isDtoProxy()){
			return this.file;
		}

		if(isFileModified())
			return this.file;

		if(! GWT.isClient())
			return null;

		FileServerFileDto _value = dtoManager.getProperty(this, instantiatePropertyAccess().file());

		if(_value instanceof HasObjectChangedEventHandler){
			((HasObjectChangedEventHandler)_value).addObjectChangedHandler(new net.datenwerke.gxtdto.client.eventbus.handlers.ObjectChangedEventHandler(){
				@Override
				public void onObjectChangedEvent(net.datenwerke.gxtdto.client.eventbus.events.ObjectChangedEvent event){
					if(! isFileModified())
						setFile((FileServerFileDto) event.getObject());
				}
			}
			);
		}
		return _value;
	}


	public void setFile(FileServerFileDto file)  {
		/* old value */
		FileServerFileDto oldValue = null;
		if(GWT.isClient())
			oldValue = getFile();

		/* set new value */
		this.file = file;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(file_pa, oldValue, file, this.file_m));

		/* set indicator */
		this.file_m = true;

		this.fireObjectChangedEvent(EditCommandResultExtensionDtoPA.INSTANCE.file(), oldValue);
	}


	public boolean isFileModified()  {
		return file_m;
	}


	public static PropertyAccessor<EditCommandResultExtensionDto, FileServerFileDto> getFilePropertyAccessor()  {
		return file_pa;
	}


	@Override
	public String toString()  {
		return super.toString();
	}

	public static Dto2PosoMapper newPosoMapper()  {
		return new EditCommandResultExtensionDto2PosoMap();
	}

	public EditCommandResultExtensionDtoPA instantiatePropertyAccess()  {
		return GWT.create(EditCommandResultExtensionDtoPA.class);
	}

	public void clearModified()  {
		this.data = null;
		this.data_m = false;
		this.file = null;
		this.file_m = false;
	}


	public boolean isModified()  {
		if(super.isModified())
			return true;
		if(data_m)
			return true;
		if(file_m)
			return true;
		return false;
	}


	public List<PropertyAccessor> getPropertyAccessors()  {
		List<PropertyAccessor> list = super.getPropertyAccessors();
		list.add(data_pa);
		list.add(file_pa);
		return list;
	}


	public List<PropertyAccessor> getModifiedPropertyAccessors()  {
		List<PropertyAccessor> list = super.getModifiedPropertyAccessors();
		if(data_m)
			list.add(data_pa);
		if(file_m)
			list.add(file_pa);
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsByView(net.datenwerke.gxtdto.client.dtomanager.DtoView view)  {
		List<PropertyAccessor> list = super.getPropertyAccessorsByView(view);
		if(view.compareTo(DtoView.NORMAL) >= 0){
			list.add(data_pa);
			list.add(file_pa);
		}
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsForDtos()  {
		List<PropertyAccessor> list = super.getPropertyAccessorsForDtos();
		list.add(file_pa);
		return list;
	}



	net.datenwerke.rs.fileserver.client.fileserver.dto.FileServerFileDto wl_0;

}
