package net.datenwerke.rs.tsreportarea.client.tsreportarea.dto;

import com.google.gwt.core.client.GWT;
import java.lang.Boolean;
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
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;
import net.datenwerke.rs.tsreportarea.client.tsreportarea.dto.decorator.TsDiskGeneralReferenceDtoDec;
import net.datenwerke.rs.tsreportarea.client.tsreportarea.dto.pa.TsDiskReportReferenceDtoPA;
import net.datenwerke.rs.tsreportarea.client.tsreportarea.dto.posomap.TsDiskReportReferenceDto2PosoMap;
import net.datenwerke.rs.tsreportarea.service.tsreportarea.entities.TsDiskReportReference;

/**
 * Dto for {@link TsDiskReportReference}
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
abstract public class TsDiskReportReferenceDto extends TsDiskGeneralReferenceDtoDec {


	private static final long serialVersionUID = 1;


	/* Fields */
	private Boolean hardlink;
	private  boolean hardlink_m;
	public static final String PROPERTY_HARDLINK = "dpi-tsdiskreportreference-hardlink";

	private transient static PropertyAccessor<TsDiskReportReferenceDto, Boolean> hardlink_pa = new PropertyAccessor<TsDiskReportReferenceDto, Boolean>() {
		@Override
		public void setValue(TsDiskReportReferenceDto container, Boolean object) {
			container.setHardlink(object);
		}

		@Override
		public Boolean getValue(TsDiskReportReferenceDto container) {
			return container.isHardlink();
		}

		@Override
		public Class<?> getType() {
			return Boolean.class;
		}

		@Override
		public String getPath() {
			return "hardlink";
		}

		@Override
		public void setModified(TsDiskReportReferenceDto container, boolean modified) {
			container.hardlink_m = modified;
		}

		@Override
		public boolean isModified(TsDiskReportReferenceDto container) {
			return container.isHardlinkModified();
		}
	};

	private ReportDto report;
	private  boolean report_m;
	public static final String PROPERTY_REPORT = "dpi-tsdiskreportreference-report";

	private transient static PropertyAccessor<TsDiskReportReferenceDto, ReportDto> report_pa = new PropertyAccessor<TsDiskReportReferenceDto, ReportDto>() {
		@Override
		public void setValue(TsDiskReportReferenceDto container, ReportDto object) {
			container.setReport(object);
		}

		@Override
		public ReportDto getValue(TsDiskReportReferenceDto container) {
			return container.getReport();
		}

		@Override
		public Class<?> getType() {
			return ReportDto.class;
		}

		@Override
		public String getPath() {
			return "report";
		}

		@Override
		public void setModified(TsDiskReportReferenceDto container, boolean modified) {
			container.report_m = modified;
		}

		@Override
		public boolean isModified(TsDiskReportReferenceDto container) {
			return container.isReportModified();
		}
	};


	public TsDiskReportReferenceDto() {
		super();
	}

	public Boolean isHardlink()  {
		if(! isDtoProxy()){
			return this.hardlink;
		}

		if(isHardlinkModified())
			return this.hardlink;

		if(! GWT.isClient())
			return null;

		Boolean _value = dtoManager.getProperty(this, instantiatePropertyAccess().hardlink());

		return _value;
	}


	public void setHardlink(Boolean hardlink)  {
		/* old value */
		Boolean oldValue = null;
		if(GWT.isClient())
			oldValue = isHardlink();

		/* set new value */
		this.hardlink = hardlink;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(hardlink_pa, oldValue, hardlink, this.hardlink_m));

		/* set indicator */
		this.hardlink_m = true;

		this.fireObjectChangedEvent(TsDiskReportReferenceDtoPA.INSTANCE.hardlink(), oldValue);
	}


	public boolean isHardlinkModified()  {
		return hardlink_m;
	}


	public static PropertyAccessor<TsDiskReportReferenceDto, Boolean> getHardlinkPropertyAccessor()  {
		return hardlink_pa;
	}


	public ReportDto getReport()  {
		if(! isDtoProxy()){
			return this.report;
		}

		if(isReportModified())
			return this.report;

		if(! GWT.isClient())
			return null;

		ReportDto _value = dtoManager.getProperty(this, instantiatePropertyAccess().report());

		if(_value instanceof HasObjectChangedEventHandler){
			((HasObjectChangedEventHandler)_value).addObjectChangedHandler(new net.datenwerke.gxtdto.client.eventbus.handlers.ObjectChangedEventHandler(){
				@Override
				public void onObjectChangedEvent(net.datenwerke.gxtdto.client.eventbus.events.ObjectChangedEvent event){
					if(! isReportModified())
						setReport((ReportDto) event.getObject());
				}
			}
			);
		}
		return _value;
	}


	public void setReport(ReportDto report)  {
		/* old value */
		ReportDto oldValue = null;
		if(GWT.isClient())
			oldValue = getReport();

		/* set new value */
		this.report = report;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(report_pa, oldValue, report, this.report_m));

		/* set indicator */
		this.report_m = true;

		this.fireObjectChangedEvent(TsDiskReportReferenceDtoPA.INSTANCE.report(), oldValue);
	}


	public boolean isReportModified()  {
		return report_m;
	}


	public static PropertyAccessor<TsDiskReportReferenceDto, ReportDto> getReportPropertyAccessor()  {
		return report_pa;
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
		if(! (obj instanceof TsDiskReportReferenceDto))
			return false;

		if(null == getId())
			return super.equals(obj);
		return getId().equals(((TsDiskReportReferenceDto)obj).getId());
	}

	@Override
	public String toString()  {
		return getClass().getName() + ": " + getId();
	}

	public static Dto2PosoMapper newPosoMapper()  {
		return new TsDiskReportReferenceDto2PosoMap();
	}

	public TsDiskReportReferenceDtoPA instantiatePropertyAccess()  {
		return GWT.create(TsDiskReportReferenceDtoPA.class);
	}

	public void clearModified()  {
		this.hardlink = null;
		this.hardlink_m = false;
		this.report = null;
		this.report_m = false;
	}


	public boolean isModified()  {
		if(super.isModified())
			return true;
		if(hardlink_m)
			return true;
		if(report_m)
			return true;
		return false;
	}


	public List<PropertyAccessor> getPropertyAccessors()  {
		List<PropertyAccessor> list = super.getPropertyAccessors();
		list.add(hardlink_pa);
		list.add(report_pa);
		return list;
	}


	public List<PropertyAccessor> getModifiedPropertyAccessors()  {
		List<PropertyAccessor> list = super.getModifiedPropertyAccessors();
		if(hardlink_m)
			list.add(hardlink_pa);
		if(report_m)
			list.add(report_pa);
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsByView(net.datenwerke.gxtdto.client.dtomanager.DtoView view)  {
		List<PropertyAccessor> list = super.getPropertyAccessorsByView(view);
		if(view.compareTo(DtoView.LIST) >= 0){
			list.add(hardlink_pa);
			list.add(report_pa);
		}
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsForDtos()  {
		List<PropertyAccessor> list = super.getPropertyAccessorsForDtos();
		list.add(report_pa);
		return list;
	}



	net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto wl_0;

}
