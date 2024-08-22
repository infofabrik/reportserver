package net.datenwerke.rs.reportdoc.client.dto;

import com.google.gwt.core.client.GWT;
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
import net.datenwerke.rs.reportdoc.client.dto.pa.DeployAnalyzeCommandResultExtensionDtoPA;
import net.datenwerke.rs.reportdoc.client.dto.posomap.DeployAnalyzeCommandResultExtensionDto2PosoMap;
import net.datenwerke.rs.reportdoc.service.terminal.commands.DeployAnalyzeCommandResultExtension;
import net.datenwerke.rs.terminal.client.terminal.dto.CommandResultExtensionDto;

/**
 * Dto for {@link DeployAnalyzeCommandResultExtension}
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class DeployAnalyzeCommandResultExtensionDto extends CommandResultExtensionDto {


	private static final long serialVersionUID = 1;


	/* Fields */
	private boolean ignoreCase;
	private  boolean ignoreCase_m;
	public static final String PROPERTY_IGNORE_CASE = "dpi-deployanalyzecommandresultextension-ignorecase";

	private transient static PropertyAccessor<DeployAnalyzeCommandResultExtensionDto, Boolean> ignoreCase_pa = new PropertyAccessor<DeployAnalyzeCommandResultExtensionDto, Boolean>() {
		@Override
		public void setValue(DeployAnalyzeCommandResultExtensionDto container, Boolean object) {
			container.setIgnoreCase(object);
		}

		@Override
		public Boolean getValue(DeployAnalyzeCommandResultExtensionDto container) {
			return container.isIgnoreCase();
		}

		@Override
		public Class<?> getType() {
			return Boolean.class;
		}

		@Override
		public String getPath() {
			return "ignoreCase";
		}

		@Override
		public void setModified(DeployAnalyzeCommandResultExtensionDto container, boolean modified) {
			container.ignoreCase_m = modified;
		}

		@Override
		public boolean isModified(DeployAnalyzeCommandResultExtensionDto container) {
			return container.isIgnoreCaseModified();
		}
	};

	private ReportDto leftReport;
	private  boolean leftReport_m;
	public static final String PROPERTY_LEFT_REPORT = "dpi-deployanalyzecommandresultextension-leftreport";

	private transient static PropertyAccessor<DeployAnalyzeCommandResultExtensionDto, ReportDto> leftReport_pa = new PropertyAccessor<DeployAnalyzeCommandResultExtensionDto, ReportDto>() {
		@Override
		public void setValue(DeployAnalyzeCommandResultExtensionDto container, ReportDto object) {
			container.setLeftReport(object);
		}

		@Override
		public ReportDto getValue(DeployAnalyzeCommandResultExtensionDto container) {
			return container.getLeftReport();
		}

		@Override
		public Class<?> getType() {
			return ReportDto.class;
		}

		@Override
		public String getPath() {
			return "leftReport";
		}

		@Override
		public void setModified(DeployAnalyzeCommandResultExtensionDto container, boolean modified) {
			container.leftReport_m = modified;
		}

		@Override
		public boolean isModified(DeployAnalyzeCommandResultExtensionDto container) {
			return container.isLeftReportModified();
		}
	};

	private ReportDto rightReport;
	private  boolean rightReport_m;
	public static final String PROPERTY_RIGHT_REPORT = "dpi-deployanalyzecommandresultextension-rightreport";

	private transient static PropertyAccessor<DeployAnalyzeCommandResultExtensionDto, ReportDto> rightReport_pa = new PropertyAccessor<DeployAnalyzeCommandResultExtensionDto, ReportDto>() {
		@Override
		public void setValue(DeployAnalyzeCommandResultExtensionDto container, ReportDto object) {
			container.setRightReport(object);
		}

		@Override
		public ReportDto getValue(DeployAnalyzeCommandResultExtensionDto container) {
			return container.getRightReport();
		}

		@Override
		public Class<?> getType() {
			return ReportDto.class;
		}

		@Override
		public String getPath() {
			return "rightReport";
		}

		@Override
		public void setModified(DeployAnalyzeCommandResultExtensionDto container, boolean modified) {
			container.rightReport_m = modified;
		}

		@Override
		public boolean isModified(DeployAnalyzeCommandResultExtensionDto container) {
			return container.isRightReportModified();
		}
	};


	public DeployAnalyzeCommandResultExtensionDto() {
		super();
	}

	public boolean isIgnoreCase()  {
		if(! isDtoProxy()){
			return this.ignoreCase;
		}

		if(isIgnoreCaseModified())
			return this.ignoreCase;

		if(! GWT.isClient())
			return false;

		boolean _value = dtoManager.getProperty(this, instantiatePropertyAccess().ignoreCase());

		return _value;
	}


	public void setIgnoreCase(boolean ignoreCase)  {
		/* old value */
		boolean oldValue = false;
		if(GWT.isClient())
			oldValue = isIgnoreCase();

		/* set new value */
		this.ignoreCase = ignoreCase;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(ignoreCase_pa, oldValue, ignoreCase, this.ignoreCase_m));

		/* set indicator */
		this.ignoreCase_m = true;

		this.fireObjectChangedEvent(DeployAnalyzeCommandResultExtensionDtoPA.INSTANCE.ignoreCase(), oldValue);
	}


	public boolean isIgnoreCaseModified()  {
		return ignoreCase_m;
	}


	public static PropertyAccessor<DeployAnalyzeCommandResultExtensionDto, Boolean> getIgnoreCasePropertyAccessor()  {
		return ignoreCase_pa;
	}


	public ReportDto getLeftReport()  {
		if(! isDtoProxy()){
			return this.leftReport;
		}

		if(isLeftReportModified())
			return this.leftReport;

		if(! GWT.isClient())
			return null;

		ReportDto _value = dtoManager.getProperty(this, instantiatePropertyAccess().leftReport());

		if(_value instanceof HasObjectChangedEventHandler){
			((HasObjectChangedEventHandler)_value).addObjectChangedHandler(new net.datenwerke.gxtdto.client.eventbus.handlers.ObjectChangedEventHandler(){
				@Override
				public void onObjectChangedEvent(net.datenwerke.gxtdto.client.eventbus.events.ObjectChangedEvent event){
					if(! isLeftReportModified())
						setLeftReport((ReportDto) event.getObject());
				}
			}
			);
		}
		return _value;
	}


	public void setLeftReport(ReportDto leftReport)  {
		/* old value */
		ReportDto oldValue = null;
		if(GWT.isClient())
			oldValue = getLeftReport();

		/* set new value */
		this.leftReport = leftReport;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(leftReport_pa, oldValue, leftReport, this.leftReport_m));

		/* set indicator */
		this.leftReport_m = true;

		this.fireObjectChangedEvent(DeployAnalyzeCommandResultExtensionDtoPA.INSTANCE.leftReport(), oldValue);
	}


	public boolean isLeftReportModified()  {
		return leftReport_m;
	}


	public static PropertyAccessor<DeployAnalyzeCommandResultExtensionDto, ReportDto> getLeftReportPropertyAccessor()  {
		return leftReport_pa;
	}


	public ReportDto getRightReport()  {
		if(! isDtoProxy()){
			return this.rightReport;
		}

		if(isRightReportModified())
			return this.rightReport;

		if(! GWT.isClient())
			return null;

		ReportDto _value = dtoManager.getProperty(this, instantiatePropertyAccess().rightReport());

		if(_value instanceof HasObjectChangedEventHandler){
			((HasObjectChangedEventHandler)_value).addObjectChangedHandler(new net.datenwerke.gxtdto.client.eventbus.handlers.ObjectChangedEventHandler(){
				@Override
				public void onObjectChangedEvent(net.datenwerke.gxtdto.client.eventbus.events.ObjectChangedEvent event){
					if(! isRightReportModified())
						setRightReport((ReportDto) event.getObject());
				}
			}
			);
		}
		return _value;
	}


	public void setRightReport(ReportDto rightReport)  {
		/* old value */
		ReportDto oldValue = null;
		if(GWT.isClient())
			oldValue = getRightReport();

		/* set new value */
		this.rightReport = rightReport;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(rightReport_pa, oldValue, rightReport, this.rightReport_m));

		/* set indicator */
		this.rightReport_m = true;

		this.fireObjectChangedEvent(DeployAnalyzeCommandResultExtensionDtoPA.INSTANCE.rightReport(), oldValue);
	}


	public boolean isRightReportModified()  {
		return rightReport_m;
	}


	public static PropertyAccessor<DeployAnalyzeCommandResultExtensionDto, ReportDto> getRightReportPropertyAccessor()  {
		return rightReport_pa;
	}


	@Override
	public String toString()  {
		return super.toString();
	}

	public static Dto2PosoMapper newPosoMapper()  {
		return new DeployAnalyzeCommandResultExtensionDto2PosoMap();
	}

	public DeployAnalyzeCommandResultExtensionDtoPA instantiatePropertyAccess()  {
		return GWT.create(DeployAnalyzeCommandResultExtensionDtoPA.class);
	}

	public void clearModified()  {
		this.ignoreCase = false;
		this.ignoreCase_m = false;
		this.leftReport = null;
		this.leftReport_m = false;
		this.rightReport = null;
		this.rightReport_m = false;
	}


	public boolean isModified()  {
		if(super.isModified())
			return true;
		if(ignoreCase_m)
			return true;
		if(leftReport_m)
			return true;
		if(rightReport_m)
			return true;
		return false;
	}


	public List<PropertyAccessor> getPropertyAccessors()  {
		List<PropertyAccessor> list = super.getPropertyAccessors();
		list.add(ignoreCase_pa);
		list.add(leftReport_pa);
		list.add(rightReport_pa);
		return list;
	}


	public List<PropertyAccessor> getModifiedPropertyAccessors()  {
		List<PropertyAccessor> list = super.getModifiedPropertyAccessors();
		if(ignoreCase_m)
			list.add(ignoreCase_pa);
		if(leftReport_m)
			list.add(leftReport_pa);
		if(rightReport_m)
			list.add(rightReport_pa);
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsByView(net.datenwerke.gxtdto.client.dtomanager.DtoView view)  {
		List<PropertyAccessor> list = super.getPropertyAccessorsByView(view);
		if(view.compareTo(DtoView.NORMAL) >= 0){
			list.add(ignoreCase_pa);
			list.add(leftReport_pa);
			list.add(rightReport_pa);
		}
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsForDtos()  {
		List<PropertyAccessor> list = super.getPropertyAccessorsForDtos();
		list.add(leftReport_pa);
		list.add(rightReport_pa);
		return list;
	}



	net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto wl_0;

}
