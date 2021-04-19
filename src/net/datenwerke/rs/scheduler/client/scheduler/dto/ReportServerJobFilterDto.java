package net.datenwerke.rs.scheduler.client.scheduler.dto;

import com.google.gwt.core.client.GWT;
import java.lang.String;
import java.util.ArrayList;
import java.util.HashSet;
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
import net.datenwerke.rs.scheduler.client.scheduler.dto.pa.ReportServerJobFilterDtoPA;
import net.datenwerke.rs.scheduler.client.scheduler.dto.posomap.ReportServerJobFilterDto2PosoMap;
import net.datenwerke.rs.scheduler.service.scheduler.jobs.filter.ReportServerJobFilter;
import net.datenwerke.scheduler.client.scheduler.dto.filter.JobFilterConfigurationDto;
import net.datenwerke.security.client.usermanager.dto.UserDto;

/**
 * Dto for {@link ReportServerJobFilter}
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class ReportServerJobFilterDto extends JobFilterConfigurationDto {


	private static final long serialVersionUID = 1;


	/* Fields */
	private boolean fromCurrentUser;
	private  boolean fromCurrentUser_m;
	public static final String PROPERTY_FROM_CURRENT_USER = "dpi-reportserverjobfilter-fromcurrentuser";

	private transient static PropertyAccessor<ReportServerJobFilterDto, Boolean> fromCurrentUser_pa = new PropertyAccessor<ReportServerJobFilterDto, Boolean>() {
		@Override
		public void setValue(ReportServerJobFilterDto container, Boolean object) {
			container.setFromCurrentUser(object);
		}

		@Override
		public Boolean getValue(ReportServerJobFilterDto container) {
			return container.isFromCurrentUser();
		}

		@Override
		public Class<?> getType() {
			return Boolean.class;
		}

		@Override
		public String getPath() {
			return "fromCurrentUser";
		}

		@Override
		public void setModified(ReportServerJobFilterDto container, boolean modified) {
			container.fromCurrentUser_m = modified;
		}

		@Override
		public boolean isModified(ReportServerJobFilterDto container) {
			return container.isFromCurrentUserModified();
		}
	};

	private UserDto fromUser;
	private  boolean fromUser_m;
	public static final String PROPERTY_FROM_USER = "dpi-reportserverjobfilter-fromuser";

	private transient static PropertyAccessor<ReportServerJobFilterDto, UserDto> fromUser_pa = new PropertyAccessor<ReportServerJobFilterDto, UserDto>() {
		@Override
		public void setValue(ReportServerJobFilterDto container, UserDto object) {
			container.setFromUser(object);
		}

		@Override
		public UserDto getValue(ReportServerJobFilterDto container) {
			return container.getFromUser();
		}

		@Override
		public Class<?> getType() {
			return UserDto.class;
		}

		@Override
		public String getPath() {
			return "fromUser";
		}

		@Override
		public void setModified(ReportServerJobFilterDto container, boolean modified) {
			container.fromUser_m = modified;
		}

		@Override
		public boolean isModified(ReportServerJobFilterDto container) {
			return container.isFromUserModified();
		}
	};

	private UserDto owner;
	private  boolean owner_m;
	public static final String PROPERTY_OWNER = "dpi-reportserverjobfilter-owner";

	private transient static PropertyAccessor<ReportServerJobFilterDto, UserDto> owner_pa = new PropertyAccessor<ReportServerJobFilterDto, UserDto>() {
		@Override
		public void setValue(ReportServerJobFilterDto container, UserDto object) {
			container.setOwner(object);
		}

		@Override
		public UserDto getValue(ReportServerJobFilterDto container) {
			return container.getOwner();
		}

		@Override
		public Class<?> getType() {
			return UserDto.class;
		}

		@Override
		public String getPath() {
			return "owner";
		}

		@Override
		public void setModified(ReportServerJobFilterDto container, boolean modified) {
			container.owner_m = modified;
		}

		@Override
		public boolean isModified(ReportServerJobFilterDto container) {
			return container.isOwnerModified();
		}
	};

	private String reportId;
	private  boolean reportId_m;
	public static final String PROPERTY_REPORT_ID = "dpi-reportserverjobfilter-reportid";

	private transient static PropertyAccessor<ReportServerJobFilterDto, String> reportId_pa = new PropertyAccessor<ReportServerJobFilterDto, String>() {
		@Override
		public void setValue(ReportServerJobFilterDto container, String object) {
			container.setReportId(object);
		}

		@Override
		public String getValue(ReportServerJobFilterDto container) {
			return container.getReportId();
		}

		@Override
		public Class<?> getType() {
			return String.class;
		}

		@Override
		public String getPath() {
			return "reportId";
		}

		@Override
		public void setModified(ReportServerJobFilterDto container, boolean modified) {
			container.reportId_m = modified;
		}

		@Override
		public boolean isModified(ReportServerJobFilterDto container) {
			return container.isReportIdModified();
		}
	};

	private Set<Long> reports;
	private  boolean reports_m;
	public static final String PROPERTY_REPORTS = "dpi-reportserverjobfilter-reports";

	private transient static PropertyAccessor<ReportServerJobFilterDto, Set<Long>> reports_pa = new PropertyAccessor<ReportServerJobFilterDto, Set<Long>>() {
		@Override
		public void setValue(ReportServerJobFilterDto container, Set<Long> object) {
			container.setReports(object);
		}

		@Override
		public Set<Long> getValue(ReportServerJobFilterDto container) {
			return container.getReports();
		}

		@Override
		public Class<?> getType() {
			return Set.class;
		}

		@Override
		public String getPath() {
			return "reports";
		}

		@Override
		public void setModified(ReportServerJobFilterDto container, boolean modified) {
			container.reports_m = modified;
		}

		@Override
		public boolean isModified(ReportServerJobFilterDto container) {
			return container.isReportsModified();
		}
	};

	private UserDto scheduledBy;
	private  boolean scheduledBy_m;
	public static final String PROPERTY_SCHEDULED_BY = "dpi-reportserverjobfilter-scheduledby";

	private transient static PropertyAccessor<ReportServerJobFilterDto, UserDto> scheduledBy_pa = new PropertyAccessor<ReportServerJobFilterDto, UserDto>() {
		@Override
		public void setValue(ReportServerJobFilterDto container, UserDto object) {
			container.setScheduledBy(object);
		}

		@Override
		public UserDto getValue(ReportServerJobFilterDto container) {
			return container.getScheduledBy();
		}

		@Override
		public Class<?> getType() {
			return UserDto.class;
		}

		@Override
		public String getPath() {
			return "scheduledBy";
		}

		@Override
		public void setModified(ReportServerJobFilterDto container, boolean modified) {
			container.scheduledBy_m = modified;
		}

		@Override
		public boolean isModified(ReportServerJobFilterDto container) {
			return container.isScheduledByModified();
		}
	};

	private boolean toCurrentUser;
	private  boolean toCurrentUser_m;
	public static final String PROPERTY_TO_CURRENT_USER = "dpi-reportserverjobfilter-tocurrentuser";

	private transient static PropertyAccessor<ReportServerJobFilterDto, Boolean> toCurrentUser_pa = new PropertyAccessor<ReportServerJobFilterDto, Boolean>() {
		@Override
		public void setValue(ReportServerJobFilterDto container, Boolean object) {
			container.setToCurrentUser(object);
		}

		@Override
		public Boolean getValue(ReportServerJobFilterDto container) {
			return container.isToCurrentUser();
		}

		@Override
		public Class<?> getType() {
			return Boolean.class;
		}

		@Override
		public String getPath() {
			return "toCurrentUser";
		}

		@Override
		public void setModified(ReportServerJobFilterDto container, boolean modified) {
			container.toCurrentUser_m = modified;
		}

		@Override
		public boolean isModified(ReportServerJobFilterDto container) {
			return container.isToCurrentUserModified();
		}
	};

	private UserDto toUser;
	private  boolean toUser_m;
	public static final String PROPERTY_TO_USER = "dpi-reportserverjobfilter-touser";

	private transient static PropertyAccessor<ReportServerJobFilterDto, UserDto> toUser_pa = new PropertyAccessor<ReportServerJobFilterDto, UserDto>() {
		@Override
		public void setValue(ReportServerJobFilterDto container, UserDto object) {
			container.setToUser(object);
		}

		@Override
		public UserDto getValue(ReportServerJobFilterDto container) {
			return container.getToUser();
		}

		@Override
		public Class<?> getType() {
			return UserDto.class;
		}

		@Override
		public String getPath() {
			return "toUser";
		}

		@Override
		public void setModified(ReportServerJobFilterDto container, boolean modified) {
			container.toUser_m = modified;
		}

		@Override
		public boolean isModified(ReportServerJobFilterDto container) {
			return container.isToUserModified();
		}
	};


	public ReportServerJobFilterDto() {
		super();
	}

	public boolean isFromCurrentUser()  {
		if(! isDtoProxy()){
			return this.fromCurrentUser;
		}

		if(isFromCurrentUserModified())
			return this.fromCurrentUser;

		if(! GWT.isClient())
			return false;

		boolean _value = dtoManager.getProperty(this, instantiatePropertyAccess().fromCurrentUser());

		return _value;
	}


	public void setFromCurrentUser(boolean fromCurrentUser)  {
		/* old value */
		boolean oldValue = false;
		if(GWT.isClient())
			oldValue = isFromCurrentUser();

		/* set new value */
		this.fromCurrentUser = fromCurrentUser;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(fromCurrentUser_pa, oldValue, fromCurrentUser, this.fromCurrentUser_m));

		/* set indicator */
		this.fromCurrentUser_m = true;

		this.fireObjectChangedEvent(ReportServerJobFilterDtoPA.INSTANCE.fromCurrentUser(), oldValue);
	}


	public boolean isFromCurrentUserModified()  {
		return fromCurrentUser_m;
	}


	public static PropertyAccessor<ReportServerJobFilterDto, Boolean> getFromCurrentUserPropertyAccessor()  {
		return fromCurrentUser_pa;
	}


	public UserDto getFromUser()  {
		if(! isDtoProxy()){
			return this.fromUser;
		}

		if(isFromUserModified())
			return this.fromUser;

		if(! GWT.isClient())
			return null;

		UserDto _value = dtoManager.getProperty(this, instantiatePropertyAccess().fromUser());

		if(_value instanceof HasObjectChangedEventHandler){
			((HasObjectChangedEventHandler)_value).addObjectChangedHandler(new net.datenwerke.gxtdto.client.eventbus.handlers.ObjectChangedEventHandler(){
				@Override
				public void onObjectChangedEvent(net.datenwerke.gxtdto.client.eventbus.events.ObjectChangedEvent event){
					if(! isFromUserModified())
						setFromUser((UserDto) event.getObject());
				}
			}
			);
		}
		return _value;
	}


	public void setFromUser(UserDto fromUser)  {
		/* old value */
		UserDto oldValue = null;
		if(GWT.isClient())
			oldValue = getFromUser();

		/* set new value */
		this.fromUser = fromUser;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(fromUser_pa, oldValue, fromUser, this.fromUser_m));

		/* set indicator */
		this.fromUser_m = true;

		this.fireObjectChangedEvent(ReportServerJobFilterDtoPA.INSTANCE.fromUser(), oldValue);
	}


	public boolean isFromUserModified()  {
		return fromUser_m;
	}


	public static PropertyAccessor<ReportServerJobFilterDto, UserDto> getFromUserPropertyAccessor()  {
		return fromUser_pa;
	}


	public UserDto getOwner()  {
		if(! isDtoProxy()){
			return this.owner;
		}

		if(isOwnerModified())
			return this.owner;

		if(! GWT.isClient())
			return null;

		UserDto _value = dtoManager.getProperty(this, instantiatePropertyAccess().owner());

		if(_value instanceof HasObjectChangedEventHandler){
			((HasObjectChangedEventHandler)_value).addObjectChangedHandler(new net.datenwerke.gxtdto.client.eventbus.handlers.ObjectChangedEventHandler(){
				@Override
				public void onObjectChangedEvent(net.datenwerke.gxtdto.client.eventbus.events.ObjectChangedEvent event){
					if(! isOwnerModified())
						setOwner((UserDto) event.getObject());
				}
			}
			);
		}
		return _value;
	}


	public void setOwner(UserDto owner)  {
		/* old value */
		UserDto oldValue = null;
		if(GWT.isClient())
			oldValue = getOwner();

		/* set new value */
		this.owner = owner;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(owner_pa, oldValue, owner, this.owner_m));

		/* set indicator */
		this.owner_m = true;

		this.fireObjectChangedEvent(ReportServerJobFilterDtoPA.INSTANCE.owner(), oldValue);
	}


	public boolean isOwnerModified()  {
		return owner_m;
	}


	public static PropertyAccessor<ReportServerJobFilterDto, UserDto> getOwnerPropertyAccessor()  {
		return owner_pa;
	}


	public String getReportId()  {
		if(! isDtoProxy()){
			return this.reportId;
		}

		if(isReportIdModified())
			return this.reportId;

		if(! GWT.isClient())
			return null;

		String _value = dtoManager.getProperty(this, instantiatePropertyAccess().reportId());

		return _value;
	}


	public void setReportId(String reportId)  {
		/* old value */
		String oldValue = null;
		if(GWT.isClient())
			oldValue = getReportId();

		/* set new value */
		this.reportId = reportId;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(reportId_pa, oldValue, reportId, this.reportId_m));

		/* set indicator */
		this.reportId_m = true;

		this.fireObjectChangedEvent(ReportServerJobFilterDtoPA.INSTANCE.reportId(), oldValue);
	}


	public boolean isReportIdModified()  {
		return reportId_m;
	}


	public static PropertyAccessor<ReportServerJobFilterDto, String> getReportIdPropertyAccessor()  {
		return reportId_pa;
	}


	public Set<Long> getReports()  {
		if(! isDtoProxy()){
			Set<Long> _currentValue = this.reports;
			if(null == _currentValue)
				this.reports = new HashSet<Long>();

			return this.reports;
		}

		if(isReportsModified())
			return this.reports;

		if(! GWT.isClient())
			return null;

		Set<Long> _value = dtoManager.getProperty(this, instantiatePropertyAccess().reports());

		return _value;
	}


	public void setReports(Set<Long> reports)  {
		/* old value */
		Set<Long> oldValue = null;
		if(GWT.isClient())
			oldValue = getReports();

		/* set new value */
		this.reports = reports;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(reports_pa, oldValue, reports, this.reports_m));

		/* set indicator */
		this.reports_m = true;

		this.fireObjectChangedEvent(ReportServerJobFilterDtoPA.INSTANCE.reports(), oldValue);
	}


	public boolean isReportsModified()  {
		return reports_m;
	}


	public static PropertyAccessor<ReportServerJobFilterDto, Set<Long>> getReportsPropertyAccessor()  {
		return reports_pa;
	}


	public UserDto getScheduledBy()  {
		if(! isDtoProxy()){
			return this.scheduledBy;
		}

		if(isScheduledByModified())
			return this.scheduledBy;

		if(! GWT.isClient())
			return null;

		UserDto _value = dtoManager.getProperty(this, instantiatePropertyAccess().scheduledBy());

		if(_value instanceof HasObjectChangedEventHandler){
			((HasObjectChangedEventHandler)_value).addObjectChangedHandler(new net.datenwerke.gxtdto.client.eventbus.handlers.ObjectChangedEventHandler(){
				@Override
				public void onObjectChangedEvent(net.datenwerke.gxtdto.client.eventbus.events.ObjectChangedEvent event){
					if(! isScheduledByModified())
						setScheduledBy((UserDto) event.getObject());
				}
			}
			);
		}
		return _value;
	}


	public void setScheduledBy(UserDto scheduledBy)  {
		/* old value */
		UserDto oldValue = null;
		if(GWT.isClient())
			oldValue = getScheduledBy();

		/* set new value */
		this.scheduledBy = scheduledBy;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(scheduledBy_pa, oldValue, scheduledBy, this.scheduledBy_m));

		/* set indicator */
		this.scheduledBy_m = true;

		this.fireObjectChangedEvent(ReportServerJobFilterDtoPA.INSTANCE.scheduledBy(), oldValue);
	}


	public boolean isScheduledByModified()  {
		return scheduledBy_m;
	}


	public static PropertyAccessor<ReportServerJobFilterDto, UserDto> getScheduledByPropertyAccessor()  {
		return scheduledBy_pa;
	}


	public boolean isToCurrentUser()  {
		if(! isDtoProxy()){
			return this.toCurrentUser;
		}

		if(isToCurrentUserModified())
			return this.toCurrentUser;

		if(! GWT.isClient())
			return false;

		boolean _value = dtoManager.getProperty(this, instantiatePropertyAccess().toCurrentUser());

		return _value;
	}


	public void setToCurrentUser(boolean toCurrentUser)  {
		/* old value */
		boolean oldValue = false;
		if(GWT.isClient())
			oldValue = isToCurrentUser();

		/* set new value */
		this.toCurrentUser = toCurrentUser;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(toCurrentUser_pa, oldValue, toCurrentUser, this.toCurrentUser_m));

		/* set indicator */
		this.toCurrentUser_m = true;

		this.fireObjectChangedEvent(ReportServerJobFilterDtoPA.INSTANCE.toCurrentUser(), oldValue);
	}


	public boolean isToCurrentUserModified()  {
		return toCurrentUser_m;
	}


	public static PropertyAccessor<ReportServerJobFilterDto, Boolean> getToCurrentUserPropertyAccessor()  {
		return toCurrentUser_pa;
	}


	public UserDto getToUser()  {
		if(! isDtoProxy()){
			return this.toUser;
		}

		if(isToUserModified())
			return this.toUser;

		if(! GWT.isClient())
			return null;

		UserDto _value = dtoManager.getProperty(this, instantiatePropertyAccess().toUser());

		if(_value instanceof HasObjectChangedEventHandler){
			((HasObjectChangedEventHandler)_value).addObjectChangedHandler(new net.datenwerke.gxtdto.client.eventbus.handlers.ObjectChangedEventHandler(){
				@Override
				public void onObjectChangedEvent(net.datenwerke.gxtdto.client.eventbus.events.ObjectChangedEvent event){
					if(! isToUserModified())
						setToUser((UserDto) event.getObject());
				}
			}
			);
		}
		return _value;
	}


	public void setToUser(UserDto toUser)  {
		/* old value */
		UserDto oldValue = null;
		if(GWT.isClient())
			oldValue = getToUser();

		/* set new value */
		this.toUser = toUser;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(toUser_pa, oldValue, toUser, this.toUser_m));

		/* set indicator */
		this.toUser_m = true;

		this.fireObjectChangedEvent(ReportServerJobFilterDtoPA.INSTANCE.toUser(), oldValue);
	}


	public boolean isToUserModified()  {
		return toUser_m;
	}


	public static PropertyAccessor<ReportServerJobFilterDto, UserDto> getToUserPropertyAccessor()  {
		return toUser_pa;
	}


	@Override
	public String toString()  {
		return super.toString();
	}

	public static Dto2PosoMapper newPosoMapper()  {
		return new ReportServerJobFilterDto2PosoMap();
	}

	public ReportServerJobFilterDtoPA instantiatePropertyAccess()  {
		return GWT.create(ReportServerJobFilterDtoPA.class);
	}

	public void clearModified()  {
		this.fromCurrentUser = false;
		this.fromCurrentUser_m = false;
		this.fromUser = null;
		this.fromUser_m = false;
		this.owner = null;
		this.owner_m = false;
		this.reportId = null;
		this.reportId_m = false;
		this.reports = null;
		this.reports_m = false;
		this.scheduledBy = null;
		this.scheduledBy_m = false;
		this.toCurrentUser = false;
		this.toCurrentUser_m = false;
		this.toUser = null;
		this.toUser_m = false;
	}


	public boolean isModified()  {
		if(super.isModified())
			return true;
		if(fromCurrentUser_m)
			return true;
		if(fromUser_m)
			return true;
		if(owner_m)
			return true;
		if(reportId_m)
			return true;
		if(reports_m)
			return true;
		if(scheduledBy_m)
			return true;
		if(toCurrentUser_m)
			return true;
		if(toUser_m)
			return true;
		return false;
	}


	public List<PropertyAccessor> getPropertyAccessors()  {
		List<PropertyAccessor> list = super.getPropertyAccessors();
		list.add(fromCurrentUser_pa);
		list.add(fromUser_pa);
		list.add(owner_pa);
		list.add(reportId_pa);
		list.add(reports_pa);
		list.add(scheduledBy_pa);
		list.add(toCurrentUser_pa);
		list.add(toUser_pa);
		return list;
	}


	public List<PropertyAccessor> getModifiedPropertyAccessors()  {
		List<PropertyAccessor> list = super.getModifiedPropertyAccessors();
		if(fromCurrentUser_m)
			list.add(fromCurrentUser_pa);
		if(fromUser_m)
			list.add(fromUser_pa);
		if(owner_m)
			list.add(owner_pa);
		if(reportId_m)
			list.add(reportId_pa);
		if(reports_m)
			list.add(reports_pa);
		if(scheduledBy_m)
			list.add(scheduledBy_pa);
		if(toCurrentUser_m)
			list.add(toCurrentUser_pa);
		if(toUser_m)
			list.add(toUser_pa);
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsByView(net.datenwerke.gxtdto.client.dtomanager.DtoView view)  {
		List<PropertyAccessor> list = super.getPropertyAccessorsByView(view);
		if(view.compareTo(DtoView.NORMAL) >= 0){
			list.add(fromCurrentUser_pa);
			list.add(fromUser_pa);
			list.add(owner_pa);
			list.add(reportId_pa);
			list.add(reports_pa);
			list.add(scheduledBy_pa);
			list.add(toCurrentUser_pa);
			list.add(toUser_pa);
		}
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsForDtos()  {
		List<PropertyAccessor> list = super.getPropertyAccessorsForDtos();
		list.add(fromUser_pa);
		list.add(owner_pa);
		list.add(scheduledBy_pa);
		list.add(toUser_pa);
		return list;
	}



	net.datenwerke.security.client.usermanager.dto.UserDto wl_0;

}
