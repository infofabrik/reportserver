package net.datenwerke.rs.base.ext.client.parameters.fileselection.dto;

import com.google.gwt.core.client.GWT;
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
import net.datenwerke.rs.base.ext.client.parameters.fileselection.dto.SelectedParameterFileDto;
import net.datenwerke.rs.base.ext.client.parameters.fileselection.dto.pa.FileSelectionParameterInstanceDtoPA;
import net.datenwerke.rs.base.ext.client.parameters.fileselection.dto.posomap.FileSelectionParameterInstanceDto2PosoMap;
import net.datenwerke.rs.base.ext.service.parameters.fileselection.FileSelectionParameterInstance;
import net.datenwerke.rs.core.client.parameters.dto.ParameterInstanceDto;

/**
 * Dto for {@link FileSelectionParameterInstance}
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
abstract public class FileSelectionParameterInstanceDto extends ParameterInstanceDto {


	private static final long serialVersionUID = 1;


	/* Fields */
	private List<SelectedParameterFileDto> selectedFiles;
	private  boolean selectedFiles_m;
	public static final String PROPERTY_SELECTED_FILES = "dpi-fileselectionparameterinstance-selectedfiles";

	private transient static PropertyAccessor<FileSelectionParameterInstanceDto, List<SelectedParameterFileDto>> selectedFiles_pa = new PropertyAccessor<FileSelectionParameterInstanceDto, List<SelectedParameterFileDto>>() {
		@Override
		public void setValue(FileSelectionParameterInstanceDto container, List<SelectedParameterFileDto> object) {
			container.setSelectedFiles(object);
		}

		@Override
		public List<SelectedParameterFileDto> getValue(FileSelectionParameterInstanceDto container) {
			return container.getSelectedFiles();
		}

		@Override
		public Class<?> getType() {
			return List.class;
		}

		@Override
		public String getPath() {
			return "selectedFiles";
		}

		@Override
		public void setModified(FileSelectionParameterInstanceDto container, boolean modified) {
			container.selectedFiles_m = modified;
		}

		@Override
		public boolean isModified(FileSelectionParameterInstanceDto container) {
			return container.isSelectedFilesModified();
		}
	};


	public FileSelectionParameterInstanceDto() {
		super();
	}

	public List<SelectedParameterFileDto> getSelectedFiles()  {
		if(! isDtoProxy()){
			List<SelectedParameterFileDto> _currentValue = this.selectedFiles;
			if(null == _currentValue)
				this.selectedFiles = new ArrayList<SelectedParameterFileDto>();

			return this.selectedFiles;
		}

		if(isSelectedFilesModified())
			return this.selectedFiles;

		if(! GWT.isClient())
			return null;

		List<SelectedParameterFileDto> _value = dtoManager.getProperty(this, instantiatePropertyAccess().selectedFiles());

		_value = new ChangeMonitoredList<SelectedParameterFileDto>(_value);
		if(_value instanceof HasObjectChangedEventHandler){
			((HasObjectChangedEventHandler)_value).addObjectChangedHandler(new net.datenwerke.gxtdto.client.eventbus.handlers.ObjectChangedEventHandler(){
				@Override
				public void onObjectChangedEvent(net.datenwerke.gxtdto.client.eventbus.events.ObjectChangedEvent event){
					if(! isSelectedFilesModified())
						setSelectedFiles((List<SelectedParameterFileDto>) ((MonitoredCollection) event.getObject()).getUnderlyingCollection());
				}
			}
			);
		}
		return _value;
	}


	public void setSelectedFiles(List<SelectedParameterFileDto> selectedFiles)  {
		/* old value */
		List<SelectedParameterFileDto> oldValue = null;
		if(GWT.isClient())
			oldValue = getSelectedFiles();

		/* set new value */
		this.selectedFiles = selectedFiles;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(selectedFiles_pa, oldValue, selectedFiles, this.selectedFiles_m));

		/* set indicator */
		this.selectedFiles_m = true;

		this.fireObjectChangedEvent(FileSelectionParameterInstanceDtoPA.INSTANCE.selectedFiles(), oldValue);
	}


	public boolean isSelectedFilesModified()  {
		return selectedFiles_m;
	}


	public static PropertyAccessor<FileSelectionParameterInstanceDto, List<SelectedParameterFileDto>> getSelectedFilesPropertyAccessor()  {
		return selectedFiles_pa;
	}


	@Override
	public int hashCode()  {
		if(null == getId())
			return super.hashCode();
		return getId().hashCode();
	}

	@Override
	public boolean equals(Object obj)  {
		if(! (obj instanceof FileSelectionParameterInstanceDto))
			return false;

		if(null == getId())
			return super.equals(obj);
		return getId().equals(((FileSelectionParameterInstanceDto)obj).getId());
	}

	@Override
	public String toString()  {
		return getClass().getName() + ": " + getId();
	}

	public static Dto2PosoMapper newPosoMapper()  {
		return new FileSelectionParameterInstanceDto2PosoMap();
	}

	public FileSelectionParameterInstanceDtoPA instantiatePropertyAccess()  {
		return GWT.create(FileSelectionParameterInstanceDtoPA.class);
	}

	public void clearModified()  {
		this.selectedFiles = null;
		this.selectedFiles_m = false;
	}


	public boolean isModified()  {
		if(super.isModified())
			return true;
		if(selectedFiles_m)
			return true;
		return false;
	}


	public List<PropertyAccessor> getPropertyAccessors()  {
		List<PropertyAccessor> list = super.getPropertyAccessors();
		list.add(selectedFiles_pa);
		return list;
	}


	public List<PropertyAccessor> getModifiedPropertyAccessors()  {
		List<PropertyAccessor> list = super.getModifiedPropertyAccessors();
		if(selectedFiles_m)
			list.add(selectedFiles_pa);
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsByView(net.datenwerke.gxtdto.client.dtomanager.DtoView view)  {
		List<PropertyAccessor> list = super.getPropertyAccessorsByView(view);
		if(view.compareTo(DtoView.NORMAL) >= 0){
			list.add(selectedFiles_pa);
		}
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsForDtos()  {
		List<PropertyAccessor> list = super.getPropertyAccessorsForDtos();
		list.add(selectedFiles_pa);
		return list;
	}



	net.datenwerke.rs.base.ext.client.parameters.fileselection.dto.SelectedParameterFileDto wl_0;

}
