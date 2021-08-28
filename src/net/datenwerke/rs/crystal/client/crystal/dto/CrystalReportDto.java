package net.datenwerke.rs.crystal.client.crystal.dto;

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
import net.datenwerke.rs.core.client.reportmanager.dto.reports.decorator.ReportDtoDec;
import net.datenwerke.rs.crystal.client.crystal.dto.CrystalReportFileDto;
import net.datenwerke.rs.crystal.client.crystal.dto.pa.CrystalReportDtoPA;
import net.datenwerke.rs.crystal.client.crystal.dto.posomap.CrystalReportDto2PosoMap;
import net.datenwerke.rs.crystal.client.crystal.locale.CrystalMessages;
import net.datenwerke.rs.crystal.service.crystal.entities.CrystalReport;
import net.datenwerke.rs.theme.client.icon.BaseIcon;

/**
 * Dto for {@link CrystalReport}
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
abstract public class CrystalReportDto extends ReportDtoDec {


	private static final long serialVersionUID = 1;


	/* Fields */
	private CrystalReportFileDto reportFile;
	private  boolean reportFile_m;
	public static final String PROPERTY_REPORT_FILE = "dpi-crystalreport-reportfile";

	private transient static PropertyAccessor<CrystalReportDto, CrystalReportFileDto> reportFile_pa = new PropertyAccessor<CrystalReportDto, CrystalReportFileDto>() {
		@Override
		public void setValue(CrystalReportDto container, CrystalReportFileDto object) {
			container.setReportFile(object);
		}

		@Override
		public CrystalReportFileDto getValue(CrystalReportDto container) {
			return container.getReportFile();
		}

		@Override
		public Class<?> getType() {
			return CrystalReportFileDto.class;
		}

		@Override
		public String getPath() {
			return "reportFile";
		}

		@Override
		public void setModified(CrystalReportDto container, boolean modified) {
			container.reportFile_m = modified;
		}

		@Override
		public boolean isModified(CrystalReportDto container) {
			return container.isReportFileModified();
		}
	};


	public CrystalReportDto() {
		super();
	}

	public CrystalReportFileDto getReportFile()  {
		if(! isDtoProxy()){
			return this.reportFile;
		}

		if(isReportFileModified())
			return this.reportFile;

		if(! GWT.isClient())
			return null;

		CrystalReportFileDto _value = dtoManager.getProperty(this, instantiatePropertyAccess().reportFile());

		if(_value instanceof HasObjectChangedEventHandler){
			((HasObjectChangedEventHandler)_value).addObjectChangedHandler(new net.datenwerke.gxtdto.client.eventbus.handlers.ObjectChangedEventHandler(){
				@Override
				public void onObjectChangedEvent(net.datenwerke.gxtdto.client.eventbus.events.ObjectChangedEvent event){
					if(! isReportFileModified())
						setReportFile((CrystalReportFileDto) event.getObject());
				}
			}
			);
		}
		return _value;
	}


	public void setReportFile(CrystalReportFileDto reportFile)  {
		/* old value */
		CrystalReportFileDto oldValue = null;
		if(GWT.isClient())
			oldValue = getReportFile();

		/* set new value */
		this.reportFile = reportFile;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(reportFile_pa, oldValue, reportFile, this.reportFile_m));

		/* set indicator */
		this.reportFile_m = true;

		this.fireObjectChangedEvent(CrystalReportDtoPA.INSTANCE.reportFile(), oldValue);
	}


	public boolean isReportFileModified()  {
		return reportFile_m;
	}


	public static PropertyAccessor<CrystalReportDto, CrystalReportFileDto> getReportFilePropertyAccessor()  {
		return reportFile_pa;
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
		return CrystalMessages.INSTANCE.reportTypeName();
	}

	@Override
	public BaseIcon toIcon()  {
		return BaseIcon.from("diamond");
	}

	@Override
	public int hashCode()  {
		if(null == getId())
			return super.hashCode();
		return getId().hashCode();
	}

	@Override
	public boolean equals(Object obj)  {
		if(! (obj instanceof CrystalReportDto))
			return false;

		if(null == getId())
			return super.equals(obj);
		return getId().equals(((CrystalReportDto)obj).getId());
	}

	@Override
	public String toString()  {
		return getClass().getName() + ": " + getId();
	}

	public static Dto2PosoMapper newPosoMapper()  {
		return new CrystalReportDto2PosoMap();
	}

	public CrystalReportDtoPA instantiatePropertyAccess()  {
		return GWT.create(CrystalReportDtoPA.class);
	}

	public void clearModified()  {
		this.reportFile = null;
		this.reportFile_m = false;
	}


	public boolean isModified()  {
		if(super.isModified())
			return true;
		if(reportFile_m)
			return true;
		return false;
	}


	public List<PropertyAccessor> getPropertyAccessors()  {
		List<PropertyAccessor> list = super.getPropertyAccessors();
		list.add(reportFile_pa);
		return list;
	}


	public List<PropertyAccessor> getModifiedPropertyAccessors()  {
		List<PropertyAccessor> list = super.getModifiedPropertyAccessors();
		if(reportFile_m)
			list.add(reportFile_pa);
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsByView(net.datenwerke.gxtdto.client.dtomanager.DtoView view)  {
		List<PropertyAccessor> list = super.getPropertyAccessorsByView(view);
		if(view.compareTo(DtoView.NORMAL) >= 0){
			list.add(reportFile_pa);
		}
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsForDtos()  {
		List<PropertyAccessor> list = super.getPropertyAccessorsForDtos();
		list.add(reportFile_pa);
		return list;
	}



	net.datenwerke.rs.crystal.client.crystal.dto.CrystalReportFileDto wl_0;

}
