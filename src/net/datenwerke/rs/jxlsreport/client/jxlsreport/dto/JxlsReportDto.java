package net.datenwerke.rs.jxlsreport.client.jxlsreport.dto;

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
import net.datenwerke.rs.jxlsreport.client.jxlsreport.dto.JxlsReportFileDto;
import net.datenwerke.rs.jxlsreport.client.jxlsreport.dto.pa.JxlsReportDtoPA;
import net.datenwerke.rs.jxlsreport.client.jxlsreport.dto.posomap.JxlsReportDto2PosoMap;
import net.datenwerke.rs.jxlsreport.client.jxlsreport.locale.JxlsReportMessages;
import net.datenwerke.rs.jxlsreport.service.jxlsreport.entities.JxlsReport;
import net.datenwerke.rs.theme.client.icon.BaseIcon;

/**
 * Dto for {@link JxlsReport}
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
abstract public class JxlsReportDto extends ReportDtoDec {


	private static final long serialVersionUID = 1;


	/* Fields */
	private boolean jxlsOne;
	private  boolean jxlsOne_m;
	public static final String PROPERTY_JXLS_ONE = "dpi-jxlsreport-jxlsone";

	private transient static PropertyAccessor<JxlsReportDto, Boolean> jxlsOne_pa = new PropertyAccessor<JxlsReportDto, Boolean>() {
		@Override
		public void setValue(JxlsReportDto container, Boolean object) {
			container.setJxlsOne(object);
		}

		@Override
		public Boolean getValue(JxlsReportDto container) {
			return container.isJxlsOne();
		}

		@Override
		public Class<?> getType() {
			return Boolean.class;
		}

		@Override
		public String getPath() {
			return "jxlsOne";
		}

		@Override
		public void setModified(JxlsReportDto container, boolean modified) {
			container.jxlsOne_m = modified;
		}

		@Override
		public boolean isModified(JxlsReportDto container) {
			return container.isJxlsOneModified();
		}
	};

	private JxlsReportFileDto reportFile;
	private  boolean reportFile_m;
	public static final String PROPERTY_REPORT_FILE = "dpi-jxlsreport-reportfile";

	private transient static PropertyAccessor<JxlsReportDto, JxlsReportFileDto> reportFile_pa = new PropertyAccessor<JxlsReportDto, JxlsReportFileDto>() {
		@Override
		public void setValue(JxlsReportDto container, JxlsReportFileDto object) {
			container.setReportFile(object);
		}

		@Override
		public JxlsReportFileDto getValue(JxlsReportDto container) {
			return container.getReportFile();
		}

		@Override
		public Class<?> getType() {
			return JxlsReportFileDto.class;
		}

		@Override
		public String getPath() {
			return "reportFile";
		}

		@Override
		public void setModified(JxlsReportDto container, boolean modified) {
			container.reportFile_m = modified;
		}

		@Override
		public boolean isModified(JxlsReportDto container) {
			return container.isReportFileModified();
		}
	};


	public JxlsReportDto() {
		super();
	}

	public boolean isJxlsOne()  {
		if(! isDtoProxy()){
			return this.jxlsOne;
		}

		if(isJxlsOneModified())
			return this.jxlsOne;

		if(! GWT.isClient())
			return false;

		boolean _value = dtoManager.getProperty(this, instantiatePropertyAccess().jxlsOne());

		return _value;
	}


	public void setJxlsOne(boolean jxlsOne)  {
		/* old value */
		boolean oldValue = false;
		if(GWT.isClient())
			oldValue = isJxlsOne();

		/* set new value */
		this.jxlsOne = jxlsOne;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(jxlsOne_pa, oldValue, jxlsOne, this.jxlsOne_m));

		/* set indicator */
		this.jxlsOne_m = true;

		this.fireObjectChangedEvent(JxlsReportDtoPA.INSTANCE.jxlsOne(), oldValue);
	}


	public boolean isJxlsOneModified()  {
		return jxlsOne_m;
	}


	public static PropertyAccessor<JxlsReportDto, Boolean> getJxlsOnePropertyAccessor()  {
		return jxlsOne_pa;
	}


	public JxlsReportFileDto getReportFile()  {
		if(! isDtoProxy()){
			return this.reportFile;
		}

		if(isReportFileModified())
			return this.reportFile;

		if(! GWT.isClient())
			return null;

		JxlsReportFileDto _value = dtoManager.getProperty(this, instantiatePropertyAccess().reportFile());

		if(_value instanceof HasObjectChangedEventHandler){
			((HasObjectChangedEventHandler)_value).addObjectChangedHandler(new net.datenwerke.gxtdto.client.eventbus.handlers.ObjectChangedEventHandler(){
				@Override
				public void onObjectChangedEvent(net.datenwerke.gxtdto.client.eventbus.events.ObjectChangedEvent event){
					if(! isReportFileModified())
						setReportFile((JxlsReportFileDto) event.getObject());
				}
			}
			);
		}
		return _value;
	}


	public void setReportFile(JxlsReportFileDto reportFile)  {
		/* old value */
		JxlsReportFileDto oldValue = null;
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

		this.fireObjectChangedEvent(JxlsReportDtoPA.INSTANCE.reportFile(), oldValue);
	}


	public boolean isReportFileModified()  {
		return reportFile_m;
	}


	public static PropertyAccessor<JxlsReportDto, JxlsReportFileDto> getReportFilePropertyAccessor()  {
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
		return JxlsReportMessages.INSTANCE.reportTypeName();
	}

	@Override
	public BaseIcon toIcon()  {
		return BaseIcon.from("file-excel-o");
	}

	@Override
	public int hashCode()  {
		if(null == getId())
			return super.hashCode();
		return getId().hashCode();
	}

	@Override
	public boolean equals(Object obj)  {
		if(! (obj instanceof JxlsReportDto))
			return false;

		if(null == getId())
			return super.equals(obj);
		return getId().equals(((JxlsReportDto)obj).getId());
	}

	@Override
	public String toString()  {
		return getClass().getName() + ": " + getId();
	}

	public static Dto2PosoMapper newPosoMapper()  {
		return new JxlsReportDto2PosoMap();
	}

	public JxlsReportDtoPA instantiatePropertyAccess()  {
		return GWT.create(JxlsReportDtoPA.class);
	}

	public void clearModified()  {
		this.jxlsOne = false;
		this.jxlsOne_m = false;
		this.reportFile = null;
		this.reportFile_m = false;
	}


	public boolean isModified()  {
		if(super.isModified())
			return true;
		if(jxlsOne_m)
			return true;
		if(reportFile_m)
			return true;
		return false;
	}


	public List<PropertyAccessor> getPropertyAccessors()  {
		List<PropertyAccessor> list = super.getPropertyAccessors();
		list.add(jxlsOne_pa);
		list.add(reportFile_pa);
		return list;
	}


	public List<PropertyAccessor> getModifiedPropertyAccessors()  {
		List<PropertyAccessor> list = super.getModifiedPropertyAccessors();
		if(jxlsOne_m)
			list.add(jxlsOne_pa);
		if(reportFile_m)
			list.add(reportFile_pa);
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsByView(net.datenwerke.gxtdto.client.dtomanager.DtoView view)  {
		List<PropertyAccessor> list = super.getPropertyAccessorsByView(view);
		if(view.compareTo(DtoView.NORMAL) >= 0){
			list.add(jxlsOne_pa);
			list.add(reportFile_pa);
		}
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsForDtos()  {
		List<PropertyAccessor> list = super.getPropertyAccessorsForDtos();
		list.add(reportFile_pa);
		return list;
	}



	net.datenwerke.rs.jxlsreport.client.jxlsreport.dto.JxlsReportFileDto wl_0;

}
