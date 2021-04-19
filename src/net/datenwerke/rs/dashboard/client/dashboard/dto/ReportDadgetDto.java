package net.datenwerke.rs.dashboard.client.dashboard.dto;

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
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;
import net.datenwerke.rs.dashboard.client.dashboard.dadgets.i.ReportContainerDadget;
import net.datenwerke.rs.dashboard.client.dashboard.dto.decorator.DadgetDtoDec;
import net.datenwerke.rs.dashboard.client.dashboard.dto.pa.ReportDadgetDtoPA;
import net.datenwerke.rs.dashboard.client.dashboard.dto.posomap.ReportDadgetDto2PosoMap;
import net.datenwerke.rs.dashboard.service.dashboard.dagets.ReportDadget;
import net.datenwerke.rs.tsreportarea.client.tsreportarea.dto.TsDiskReportReferenceDto;

/**
 * Dto for {@link ReportDadget}
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
abstract public class ReportDadgetDto extends DadgetDtoDec implements ReportContainerDadget {


	private static final long serialVersionUID = 1;


	/* Fields */
	private String config;
	private  boolean config_m;
	public static final String PROPERTY_CONFIG = "dpi-reportdadget-config";

	private transient static PropertyAccessor<ReportDadgetDto, String> config_pa = new PropertyAccessor<ReportDadgetDto, String>() {
		@Override
		public void setValue(ReportDadgetDto container, String object) {
			container.setConfig(object);
		}

		@Override
		public String getValue(ReportDadgetDto container) {
			return container.getConfig();
		}

		@Override
		public Class<?> getType() {
			return String.class;
		}

		@Override
		public String getPath() {
			return "config";
		}

		@Override
		public void setModified(ReportDadgetDto container, boolean modified) {
			container.config_m = modified;
		}

		@Override
		public boolean isModified(ReportDadgetDto container) {
			return container.isConfigModified();
		}
	};

	private ReportDto report;
	private  boolean report_m;
	public static final String PROPERTY_REPORT = "dpi-reportdadget-report";

	private transient static PropertyAccessor<ReportDadgetDto, ReportDto> report_pa = new PropertyAccessor<ReportDadgetDto, ReportDto>() {
		@Override
		public void setValue(ReportDadgetDto container, ReportDto object) {
			container.setReport(object);
		}

		@Override
		public ReportDto getValue(ReportDadgetDto container) {
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
		public void setModified(ReportDadgetDto container, boolean modified) {
			container.report_m = modified;
		}

		@Override
		public boolean isModified(ReportDadgetDto container) {
			return container.isReportModified();
		}
	};

	private TsDiskReportReferenceDto reportReference;
	private  boolean reportReference_m;
	public static final String PROPERTY_REPORT_REFERENCE = "dpi-reportdadget-reportreference";

	private transient static PropertyAccessor<ReportDadgetDto, TsDiskReportReferenceDto> reportReference_pa = new PropertyAccessor<ReportDadgetDto, TsDiskReportReferenceDto>() {
		@Override
		public void setValue(ReportDadgetDto container, TsDiskReportReferenceDto object) {
			container.setReportReference(object);
		}

		@Override
		public TsDiskReportReferenceDto getValue(ReportDadgetDto container) {
			return container.getReportReference();
		}

		@Override
		public Class<?> getType() {
			return TsDiskReportReferenceDto.class;
		}

		@Override
		public String getPath() {
			return "reportReference";
		}

		@Override
		public void setModified(ReportDadgetDto container, boolean modified) {
			container.reportReference_m = modified;
		}

		@Override
		public boolean isModified(ReportDadgetDto container) {
			return container.isReportReferenceModified();
		}
	};

	private boolean showExecuteButton;
	private  boolean showExecuteButton_m;
	public static final String PROPERTY_SHOW_EXECUTE_BUTTON = "dpi-reportdadget-showexecutebutton";

	private transient static PropertyAccessor<ReportDadgetDto, Boolean> showExecuteButton_pa = new PropertyAccessor<ReportDadgetDto, Boolean>() {
		@Override
		public void setValue(ReportDadgetDto container, Boolean object) {
			container.setShowExecuteButton(object);
		}

		@Override
		public Boolean getValue(ReportDadgetDto container) {
			return container.isShowExecuteButton();
		}

		@Override
		public Class<?> getType() {
			return Boolean.class;
		}

		@Override
		public String getPath() {
			return "showExecuteButton";
		}

		@Override
		public void setModified(ReportDadgetDto container, boolean modified) {
			container.showExecuteButton_m = modified;
		}

		@Override
		public boolean isModified(ReportDadgetDto container) {
			return container.isShowExecuteButtonModified();
		}
	};

	private ReportDto reportForDisplay;
	private  boolean reportForDisplay_m;
	public static final String PROPERTY_REPORT_FOR_DISPLAY = "dpi-reportdadget-reportfordisplay";

	private transient static PropertyAccessor<ReportDadgetDto, ReportDto> reportForDisplay_pa = new PropertyAccessor<ReportDadgetDto, ReportDto>() {
		@Override
		public void setValue(ReportDadgetDto container, ReportDto object) {
			container.setReportForDisplay(object);
		}

		@Override
		public ReportDto getValue(ReportDadgetDto container) {
			return container.getReportForDisplay();
		}

		@Override
		public Class<?> getType() {
			return ReportDto.class;
		}

		@Override
		public String getPath() {
			return "reportForDisplay";
		}

		@Override
		public void setModified(ReportDadgetDto container, boolean modified) {
			container.reportForDisplay_m = modified;
		}

		@Override
		public boolean isModified(ReportDadgetDto container) {
			return container.isReportForDisplayModified();
		}
	};


	public ReportDadgetDto() {
		super();
	}

	public String getConfig()  {
		if(! isDtoProxy()){
			return this.config;
		}

		if(isConfigModified())
			return this.config;

		if(! GWT.isClient())
			return null;

		String _value = dtoManager.getProperty(this, instantiatePropertyAccess().config());

		return _value;
	}


	public void setConfig(String config)  {
		/* old value */
		String oldValue = null;
		if(GWT.isClient())
			oldValue = getConfig();

		/* set new value */
		this.config = config;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(config_pa, oldValue, config, this.config_m));

		/* set indicator */
		this.config_m = true;

		this.fireObjectChangedEvent(ReportDadgetDtoPA.INSTANCE.config(), oldValue);
	}


	public boolean isConfigModified()  {
		return config_m;
	}


	public static PropertyAccessor<ReportDadgetDto, String> getConfigPropertyAccessor()  {
		return config_pa;
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

		this.fireObjectChangedEvent(ReportDadgetDtoPA.INSTANCE.report(), oldValue);
	}


	public boolean isReportModified()  {
		return report_m;
	}


	public static PropertyAccessor<ReportDadgetDto, ReportDto> getReportPropertyAccessor()  {
		return report_pa;
	}


	public TsDiskReportReferenceDto getReportReference()  {
		if(! isDtoProxy()){
			return this.reportReference;
		}

		if(isReportReferenceModified())
			return this.reportReference;

		if(! GWT.isClient())
			return null;

		TsDiskReportReferenceDto _value = dtoManager.getProperty(this, instantiatePropertyAccess().reportReference());

		if(_value instanceof HasObjectChangedEventHandler){
			((HasObjectChangedEventHandler)_value).addObjectChangedHandler(new net.datenwerke.gxtdto.client.eventbus.handlers.ObjectChangedEventHandler(){
				@Override
				public void onObjectChangedEvent(net.datenwerke.gxtdto.client.eventbus.events.ObjectChangedEvent event){
					if(! isReportReferenceModified())
						setReportReference((TsDiskReportReferenceDto) event.getObject());
				}
			}
			);
		}
		return _value;
	}


	public void setReportReference(TsDiskReportReferenceDto reportReference)  {
		/* old value */
		TsDiskReportReferenceDto oldValue = null;
		if(GWT.isClient())
			oldValue = getReportReference();

		/* set new value */
		this.reportReference = reportReference;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(reportReference_pa, oldValue, reportReference, this.reportReference_m));

		/* set indicator */
		this.reportReference_m = true;

		this.fireObjectChangedEvent(ReportDadgetDtoPA.INSTANCE.reportReference(), oldValue);
	}


	public boolean isReportReferenceModified()  {
		return reportReference_m;
	}


	public static PropertyAccessor<ReportDadgetDto, TsDiskReportReferenceDto> getReportReferencePropertyAccessor()  {
		return reportReference_pa;
	}


	public boolean isShowExecuteButton()  {
		if(! isDtoProxy()){
			return this.showExecuteButton;
		}

		if(isShowExecuteButtonModified())
			return this.showExecuteButton;

		if(! GWT.isClient())
			return false;

		boolean _value = dtoManager.getProperty(this, instantiatePropertyAccess().showExecuteButton());

		return _value;
	}


	public void setShowExecuteButton(boolean showExecuteButton)  {
		/* old value */
		boolean oldValue = false;
		if(GWT.isClient())
			oldValue = isShowExecuteButton();

		/* set new value */
		this.showExecuteButton = showExecuteButton;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(showExecuteButton_pa, oldValue, showExecuteButton, this.showExecuteButton_m));

		/* set indicator */
		this.showExecuteButton_m = true;

		this.fireObjectChangedEvent(ReportDadgetDtoPA.INSTANCE.showExecuteButton(), oldValue);
	}


	public boolean isShowExecuteButtonModified()  {
		return showExecuteButton_m;
	}


	public static PropertyAccessor<ReportDadgetDto, Boolean> getShowExecuteButtonPropertyAccessor()  {
		return showExecuteButton_pa;
	}


	public ReportDto getReportForDisplay()  {
		if(! isDtoProxy()){
			return this.reportForDisplay;
		}

		if(isReportForDisplayModified())
			return this.reportForDisplay;

		if(! GWT.isClient())
			return null;

		ReportDto _value = dtoManager.getProperty(this, instantiatePropertyAccess().reportForDisplay());

		return _value;
	}


	public void setReportForDisplay(ReportDto reportForDisplay)  {
		/* old value */
		ReportDto oldValue = null;
		if(GWT.isClient())
			oldValue = getReportForDisplay();

		/* set new value */
		this.reportForDisplay = reportForDisplay;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(reportForDisplay_pa, oldValue, reportForDisplay, this.reportForDisplay_m));

		/* set indicator */
		this.reportForDisplay_m = true;

		this.fireObjectChangedEvent(ReportDadgetDtoPA.INSTANCE.reportForDisplay(), oldValue);
	}


	public boolean isReportForDisplayModified()  {
		return reportForDisplay_m;
	}


	public static PropertyAccessor<ReportDadgetDto, ReportDto> getReportForDisplayPropertyAccessor()  {
		return reportForDisplay_pa;
	}


	@Override
	public int hashCode()  {
		if(null == getId())
			return super.hashCode();
		return getId().hashCode();
	}

	@Override
	public boolean equals(Object obj)  {
		if(! (obj instanceof ReportDadgetDto))
			return false;

		if(null == getId())
			return super.equals(obj);
		return getId().equals(((ReportDadgetDto)obj).getId());
	}

	@Override
	public String toString()  {
		return getClass().getName() + ": " + getId();
	}

	public static Dto2PosoMapper newPosoMapper()  {
		return new ReportDadgetDto2PosoMap();
	}

	public ReportDadgetDtoPA instantiatePropertyAccess()  {
		return GWT.create(ReportDadgetDtoPA.class);
	}

	public void clearModified()  {
		this.config = null;
		this.config_m = false;
		this.report = null;
		this.report_m = false;
		this.reportReference = null;
		this.reportReference_m = false;
		this.showExecuteButton = false;
		this.showExecuteButton_m = false;
		this.reportForDisplay = null;
		this.reportForDisplay_m = false;
	}


	public boolean isModified()  {
		if(super.isModified())
			return true;
		if(config_m)
			return true;
		if(report_m)
			return true;
		if(reportReference_m)
			return true;
		if(showExecuteButton_m)
			return true;
		if(reportForDisplay_m)
			return true;
		return false;
	}


	public List<PropertyAccessor> getPropertyAccessors()  {
		List<PropertyAccessor> list = super.getPropertyAccessors();
		list.add(config_pa);
		list.add(report_pa);
		list.add(reportReference_pa);
		list.add(showExecuteButton_pa);
		list.add(reportForDisplay_pa);
		return list;
	}


	public List<PropertyAccessor> getModifiedPropertyAccessors()  {
		List<PropertyAccessor> list = super.getModifiedPropertyAccessors();
		if(config_m)
			list.add(config_pa);
		if(report_m)
			list.add(report_pa);
		if(reportReference_m)
			list.add(reportReference_pa);
		if(showExecuteButton_m)
			list.add(showExecuteButton_pa);
		if(reportForDisplay_m)
			list.add(reportForDisplay_pa);
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsByView(net.datenwerke.gxtdto.client.dtomanager.DtoView view)  {
		List<PropertyAccessor> list = super.getPropertyAccessorsByView(view);
		if(view.compareTo(DtoView.MINIMAL) >= 0){
			list.add(config_pa);
			list.add(reportForDisplay_pa);
		}
		if(view.compareTo(DtoView.NORMAL) >= 0){
			list.add(report_pa);
			list.add(reportReference_pa);
			list.add(showExecuteButton_pa);
		}
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsForDtos()  {
		List<PropertyAccessor> list = super.getPropertyAccessorsForDtos();
		list.add(report_pa);
		list.add(reportReference_pa);
		return list;
	}



	net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto wl_0;
	net.datenwerke.rs.tsreportarea.client.tsreportarea.dto.TsDiskReportReferenceDto wl_1;

}
