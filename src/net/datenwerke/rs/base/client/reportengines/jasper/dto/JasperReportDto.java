package net.datenwerke.rs.base.client.reportengines.jasper.dto;

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
import net.datenwerke.gxtdto.client.dtomanager.dtomod.collections.ChangeMonitoredList;
import net.datenwerke.gxtdto.client.dtomanager.dtomod.collections.MonitoredCollection;
import net.datenwerke.gxtdto.client.dtomanager.redoundo.ChangeTracker;
import net.datenwerke.gxtdto.client.eventbus.events.ObjectChangedEvent;
import net.datenwerke.gxtdto.client.eventbus.handlers.ObjectChangedEventHandler;
import net.datenwerke.gxtdto.client.eventbus.handlers.has.HasObjectChangedEventHandler;
import net.datenwerke.gxtdto.client.locale.BaseMessages;
import net.datenwerke.rs.base.client.reportengines.jasper.dto.JasperReportJRXMLFileDto;
import net.datenwerke.rs.base.client.reportengines.jasper.dto.pa.JasperReportDtoPA;
import net.datenwerke.rs.base.client.reportengines.jasper.dto.posomap.JasperReportDto2PosoMap;
import net.datenwerke.rs.base.client.reportengines.jasper.locale.JasperMessages;
import net.datenwerke.rs.base.service.reportengines.jasper.entities.JasperReport;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.decorator.ReportDtoDec;
import net.datenwerke.rs.theme.client.icon.BaseIcon;

/**
 * Dto for {@link JasperReport}
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
abstract public class JasperReportDto extends ReportDtoDec {


	private static final long serialVersionUID = 1;


	/* Fields */
	private JasperReportJRXMLFileDto masterFile;
	private  boolean masterFile_m;
	public static final String PROPERTY_MASTER_FILE = "dpi-jasperreport-masterfile";

	private transient static PropertyAccessor<JasperReportDto, JasperReportJRXMLFileDto> masterFile_pa = new PropertyAccessor<JasperReportDto, JasperReportJRXMLFileDto>() {
		@Override
		public void setValue(JasperReportDto container, JasperReportJRXMLFileDto object) {
			container.setMasterFile(object);
		}

		@Override
		public JasperReportJRXMLFileDto getValue(JasperReportDto container) {
			return container.getMasterFile();
		}

		@Override
		public Class<?> getType() {
			return JasperReportJRXMLFileDto.class;
		}

		@Override
		public String getPath() {
			return "masterFile";
		}

		@Override
		public void setModified(JasperReportDto container, boolean modified) {
			container.masterFile_m = modified;
		}

		@Override
		public boolean isModified(JasperReportDto container) {
			return container.isMasterFileModified();
		}
	};

	private List<JasperReportJRXMLFileDto> subFiles;
	private  boolean subFiles_m;
	public static final String PROPERTY_SUB_FILES = "dpi-jasperreport-subfiles";

	private transient static PropertyAccessor<JasperReportDto, List<JasperReportJRXMLFileDto>> subFiles_pa = new PropertyAccessor<JasperReportDto, List<JasperReportJRXMLFileDto>>() {
		@Override
		public void setValue(JasperReportDto container, List<JasperReportJRXMLFileDto> object) {
			container.setSubFiles(object);
		}

		@Override
		public List<JasperReportJRXMLFileDto> getValue(JasperReportDto container) {
			return container.getSubFiles();
		}

		@Override
		public Class<?> getType() {
			return List.class;
		}

		@Override
		public String getPath() {
			return "subFiles";
		}

		@Override
		public void setModified(JasperReportDto container, boolean modified) {
			container.subFiles_m = modified;
		}

		@Override
		public boolean isModified(JasperReportDto container) {
			return container.isSubFilesModified();
		}
	};


	public JasperReportDto() {
		super();
	}

	public JasperReportJRXMLFileDto getMasterFile()  {
		if(! isDtoProxy()){
			return this.masterFile;
		}

		if(isMasterFileModified())
			return this.masterFile;

		if(! GWT.isClient())
			return null;

		JasperReportJRXMLFileDto _value = dtoManager.getProperty(this, instantiatePropertyAccess().masterFile());

		if(_value instanceof HasObjectChangedEventHandler){
			((HasObjectChangedEventHandler)_value).addObjectChangedHandler(new net.datenwerke.gxtdto.client.eventbus.handlers.ObjectChangedEventHandler(){
				@Override
				public void onObjectChangedEvent(net.datenwerke.gxtdto.client.eventbus.events.ObjectChangedEvent event){
					if(! isMasterFileModified())
						setMasterFile((JasperReportJRXMLFileDto) event.getObject());
				}
			}
			);
		}
		return _value;
	}


	public void setMasterFile(JasperReportJRXMLFileDto masterFile)  {
		/* old value */
		JasperReportJRXMLFileDto oldValue = null;
		if(GWT.isClient())
			oldValue = getMasterFile();

		/* set new value */
		this.masterFile = masterFile;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(masterFile_pa, oldValue, masterFile, this.masterFile_m));

		/* set indicator */
		this.masterFile_m = true;

		this.fireObjectChangedEvent(JasperReportDtoPA.INSTANCE.masterFile(), oldValue);
	}


	public boolean isMasterFileModified()  {
		return masterFile_m;
	}


	public static PropertyAccessor<JasperReportDto, JasperReportJRXMLFileDto> getMasterFilePropertyAccessor()  {
		return masterFile_pa;
	}


	public List<JasperReportJRXMLFileDto> getSubFiles()  {
		if(! isDtoProxy()){
			List<JasperReportJRXMLFileDto> _currentValue = this.subFiles;
			if(null == _currentValue)
				this.subFiles = new ArrayList<JasperReportJRXMLFileDto>();

			return this.subFiles;
		}

		if(isSubFilesModified())
			return this.subFiles;

		if(! GWT.isClient())
			return null;

		List<JasperReportJRXMLFileDto> _value = dtoManager.getProperty(this, instantiatePropertyAccess().subFiles());

		_value = new ChangeMonitoredList<JasperReportJRXMLFileDto>(_value);
		if(_value instanceof HasObjectChangedEventHandler){
			((HasObjectChangedEventHandler)_value).addObjectChangedHandler(new net.datenwerke.gxtdto.client.eventbus.handlers.ObjectChangedEventHandler(){
				@Override
				public void onObjectChangedEvent(net.datenwerke.gxtdto.client.eventbus.events.ObjectChangedEvent event){
					if(! isSubFilesModified())
						setSubFiles((List<JasperReportJRXMLFileDto>) ((MonitoredCollection) event.getObject()).getUnderlyingCollection());
				}
			}
			);
		}
		return _value;
	}


	public void setSubFiles(List<JasperReportJRXMLFileDto> subFiles)  {
		/* old value */
		List<JasperReportJRXMLFileDto> oldValue = null;
		if(GWT.isClient())
			oldValue = getSubFiles();

		/* set new value */
		this.subFiles = subFiles;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(subFiles_pa, oldValue, subFiles, this.subFiles_m));

		/* set indicator */
		this.subFiles_m = true;

		this.fireObjectChangedEvent(JasperReportDtoPA.INSTANCE.subFiles(), oldValue);
	}


	public boolean isSubFilesModified()  {
		return subFiles_m;
	}


	public static PropertyAccessor<JasperReportDto, List<JasperReportJRXMLFileDto>> getSubFilesPropertyAccessor()  {
		return subFiles_pa;
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
		return JasperMessages.INSTANCE.reportTypeName();
	}

	@Override
	public BaseIcon toIcon()  {
		return BaseIcon.from("jpg");
	}

	@Override
	public int hashCode()  {
		if(null == getId())
			return super.hashCode();
		return getId().hashCode();
	}

	@Override
	public boolean equals(Object obj)  {
		if(! (obj instanceof JasperReportDto))
			return false;

		if(null == getId())
			return super.equals(obj);
		return getId().equals(((JasperReportDto)obj).getId());
	}

	@Override
	public String toString()  {
		return getClass().getName() + ": " + getId();
	}

	public static Dto2PosoMapper newPosoMapper()  {
		return new JasperReportDto2PosoMap();
	}

	public JasperReportDtoPA instantiatePropertyAccess()  {
		return GWT.create(JasperReportDtoPA.class);
	}

	public void clearModified()  {
		this.masterFile = null;
		this.masterFile_m = false;
		this.subFiles = null;
		this.subFiles_m = false;
	}


	public boolean isModified()  {
		if(super.isModified())
			return true;
		if(masterFile_m)
			return true;
		if(subFiles_m)
			return true;
		return false;
	}


	public List<PropertyAccessor> getPropertyAccessors()  {
		List<PropertyAccessor> list = super.getPropertyAccessors();
		list.add(masterFile_pa);
		list.add(subFiles_pa);
		return list;
	}


	public List<PropertyAccessor> getModifiedPropertyAccessors()  {
		List<PropertyAccessor> list = super.getModifiedPropertyAccessors();
		if(masterFile_m)
			list.add(masterFile_pa);
		if(subFiles_m)
			list.add(subFiles_pa);
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsByView(net.datenwerke.gxtdto.client.dtomanager.DtoView view)  {
		List<PropertyAccessor> list = super.getPropertyAccessorsByView(view);
		if(view.compareTo(DtoView.NORMAL) >= 0){
			list.add(masterFile_pa);
			list.add(subFiles_pa);
		}
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsForDtos()  {
		List<PropertyAccessor> list = super.getPropertyAccessorsForDtos();
		list.add(masterFile_pa);
		list.add(subFiles_pa);
		return list;
	}



	net.datenwerke.rs.base.client.reportengines.jasper.dto.JasperReportJRXMLFileDto wl_0;

}
