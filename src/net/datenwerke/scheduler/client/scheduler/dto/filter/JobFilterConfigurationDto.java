package net.datenwerke.scheduler.client.scheduler.dto.filter;

import com.google.gwt.core.client.GWT;
import java.lang.String;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.gf.base.client.dtogenerator.RsDto;
import net.datenwerke.gxtdto.client.dtomanager.Dto2PosoMapper;
import net.datenwerke.gxtdto.client.dtomanager.DtoView;
import net.datenwerke.gxtdto.client.dtomanager.PropertyAccessor;
import net.datenwerke.gxtdto.client.dtomanager.redoundo.ChangeTracker;
import net.datenwerke.scheduler.client.scheduler.dto.JobExecutionStatusDto;
import net.datenwerke.scheduler.client.scheduler.dto.OutcomeDto;
import net.datenwerke.scheduler.client.scheduler.dto.filter.JobFilterCriteriaDto;
import net.datenwerke.scheduler.client.scheduler.dto.filter.OrderDto;
import net.datenwerke.scheduler.client.scheduler.dto.filter.pa.JobFilterConfigurationDtoPA;
import net.datenwerke.scheduler.client.scheduler.dto.filter.posomap.JobFilterConfigurationDto2PosoMap;
import net.datenwerke.scheduler.service.scheduler.stores.jpa.filter.JobFilterConfiguration;

/**
 * Dto for {@link JobFilterConfiguration}
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class JobFilterConfigurationDto extends RsDto implements JobFilterCriteriaDto {


	private static final long serialVersionUID = 1;


	/* Fields */
	private boolean active;
	private  boolean active_m;
	public static final String PROPERTY_ACTIVE = "dpi-jobfilterconfiguration-active";

	private transient static PropertyAccessor<JobFilterConfigurationDto, Boolean> active_pa = new PropertyAccessor<JobFilterConfigurationDto, Boolean>() {
		@Override
		public void setValue(JobFilterConfigurationDto container, Boolean object) {
			container.setActive(object);
		}

		@Override
		public Boolean getValue(JobFilterConfigurationDto container) {
			return container.isActive();
		}

		@Override
		public Class<?> getType() {
			return Boolean.class;
		}

		@Override
		public String getPath() {
			return "active";
		}

		@Override
		public void setModified(JobFilterConfigurationDto container, boolean modified) {
			container.active_m = modified;
		}

		@Override
		public boolean isModified(JobFilterConfigurationDto container) {
			return container.isActiveModified();
		}
	};

	private JobExecutionStatusDto executionStatus;
	private  boolean executionStatus_m;
	public static final String PROPERTY_EXECUTION_STATUS = "dpi-jobfilterconfiguration-executionstatus";

	private transient static PropertyAccessor<JobFilterConfigurationDto, JobExecutionStatusDto> executionStatus_pa = new PropertyAccessor<JobFilterConfigurationDto, JobExecutionStatusDto>() {
		@Override
		public void setValue(JobFilterConfigurationDto container, JobExecutionStatusDto object) {
			container.setExecutionStatus(object);
		}

		@Override
		public JobExecutionStatusDto getValue(JobFilterConfigurationDto container) {
			return container.getExecutionStatus();
		}

		@Override
		public Class<?> getType() {
			return JobExecutionStatusDto.class;
		}

		@Override
		public String getPath() {
			return "executionStatus";
		}

		@Override
		public void setModified(JobFilterConfigurationDto container, boolean modified) {
			container.executionStatus_m = modified;
		}

		@Override
		public boolean isModified(JobFilterConfigurationDto container) {
			return container.isExecutionStatusModified();
		}
	};

	private boolean inActive;
	private  boolean inActive_m;
	public static final String PROPERTY_IN_ACTIVE = "dpi-jobfilterconfiguration-inactive";

	private transient static PropertyAccessor<JobFilterConfigurationDto, Boolean> inActive_pa = new PropertyAccessor<JobFilterConfigurationDto, Boolean>() {
		@Override
		public void setValue(JobFilterConfigurationDto container, Boolean object) {
			container.setInActive(object);
		}

		@Override
		public Boolean getValue(JobFilterConfigurationDto container) {
			return container.isInActive();
		}

		@Override
		public Class<?> getType() {
			return Boolean.class;
		}

		@Override
		public String getPath() {
			return "inActive";
		}

		@Override
		public void setModified(JobFilterConfigurationDto container, boolean modified) {
			container.inActive_m = modified;
		}

		@Override
		public boolean isModified(JobFilterConfigurationDto container) {
			return container.isInActiveModified();
		}
	};

	private String jobId;
	private  boolean jobId_m;
	public static final String PROPERTY_JOB_ID = "dpi-jobfilterconfiguration-jobid";

	private transient static PropertyAccessor<JobFilterConfigurationDto, String> jobId_pa = new PropertyAccessor<JobFilterConfigurationDto, String>() {
		@Override
		public void setValue(JobFilterConfigurationDto container, String object) {
			container.setJobId(object);
		}

		@Override
		public String getValue(JobFilterConfigurationDto container) {
			return container.getJobId();
		}

		@Override
		public Class<?> getType() {
			return String.class;
		}

		@Override
		public String getPath() {
			return "jobId";
		}

		@Override
		public void setModified(JobFilterConfigurationDto container, boolean modified) {
			container.jobId_m = modified;
		}

		@Override
		public boolean isModified(JobFilterConfigurationDto container) {
			return container.isJobIdModified();
		}
	};

	private String jobTitle;
	private  boolean jobTitle_m;
	public static final String PROPERTY_JOB_TITLE = "dpi-jobfilterconfiguration-jobtitle";

	private transient static PropertyAccessor<JobFilterConfigurationDto, String> jobTitle_pa = new PropertyAccessor<JobFilterConfigurationDto, String>() {
		@Override
		public void setValue(JobFilterConfigurationDto container, String object) {
			container.setJobTitle(object);
		}

		@Override
		public String getValue(JobFilterConfigurationDto container) {
			return container.getJobTitle();
		}

		@Override
		public Class<?> getType() {
			return String.class;
		}

		@Override
		public String getPath() {
			return "jobTitle";
		}

		@Override
		public void setModified(JobFilterConfigurationDto container, boolean modified) {
			container.jobTitle_m = modified;
		}

		@Override
		public boolean isModified(JobFilterConfigurationDto container) {
			return container.isJobTitleModified();
		}
	};

	private OutcomeDto lastOutcome;
	private  boolean lastOutcome_m;
	public static final String PROPERTY_LAST_OUTCOME = "dpi-jobfilterconfiguration-lastoutcome";

	private transient static PropertyAccessor<JobFilterConfigurationDto, OutcomeDto> lastOutcome_pa = new PropertyAccessor<JobFilterConfigurationDto, OutcomeDto>() {
		@Override
		public void setValue(JobFilterConfigurationDto container, OutcomeDto object) {
			container.setLastOutcome(object);
		}

		@Override
		public OutcomeDto getValue(JobFilterConfigurationDto container) {
			return container.getLastOutcome();
		}

		@Override
		public Class<?> getType() {
			return OutcomeDto.class;
		}

		@Override
		public String getPath() {
			return "lastOutcome";
		}

		@Override
		public void setModified(JobFilterConfigurationDto container, boolean modified) {
			container.lastOutcome_m = modified;
		}

		@Override
		public boolean isModified(JobFilterConfigurationDto container) {
			return container.isLastOutcomeModified();
		}
	};

	private int limit;
	private  boolean limit_m;
	public static final String PROPERTY_LIMIT = "dpi-jobfilterconfiguration-limit";

	private transient static PropertyAccessor<JobFilterConfigurationDto, Integer> limit_pa = new PropertyAccessor<JobFilterConfigurationDto, Integer>() {
		@Override
		public void setValue(JobFilterConfigurationDto container, Integer object) {
			container.setLimit(object);
		}

		@Override
		public Integer getValue(JobFilterConfigurationDto container) {
			return container.getLimit();
		}

		@Override
		public Class<?> getType() {
			return Integer.class;
		}

		@Override
		public String getPath() {
			return "limit";
		}

		@Override
		public void setModified(JobFilterConfigurationDto container, boolean modified) {
			container.limit_m = modified;
		}

		@Override
		public boolean isModified(JobFilterConfigurationDto container) {
			return container.isLimitModified();
		}
	};

	private int offset;
	private  boolean offset_m;
	public static final String PROPERTY_OFFSET = "dpi-jobfilterconfiguration-offset";

	private transient static PropertyAccessor<JobFilterConfigurationDto, Integer> offset_pa = new PropertyAccessor<JobFilterConfigurationDto, Integer>() {
		@Override
		public void setValue(JobFilterConfigurationDto container, Integer object) {
			container.setOffset(object);
		}

		@Override
		public Integer getValue(JobFilterConfigurationDto container) {
			return container.getOffset();
		}

		@Override
		public Class<?> getType() {
			return Integer.class;
		}

		@Override
		public String getPath() {
			return "offset";
		}

		@Override
		public void setModified(JobFilterConfigurationDto container, boolean modified) {
			container.offset_m = modified;
		}

		@Override
		public boolean isModified(JobFilterConfigurationDto container) {
			return container.isOffsetModified();
		}
	};

	private OrderDto order;
	private  boolean order_m;
	public static final String PROPERTY_ORDER = "dpi-jobfilterconfiguration-order";

	private transient static PropertyAccessor<JobFilterConfigurationDto, OrderDto> order_pa = new PropertyAccessor<JobFilterConfigurationDto, OrderDto>() {
		@Override
		public void setValue(JobFilterConfigurationDto container, OrderDto object) {
			container.setOrder(object);
		}

		@Override
		public OrderDto getValue(JobFilterConfigurationDto container) {
			return container.getOrder();
		}

		@Override
		public Class<?> getType() {
			return OrderDto.class;
		}

		@Override
		public String getPath() {
			return "order";
		}

		@Override
		public void setModified(JobFilterConfigurationDto container, boolean modified) {
			container.order_m = modified;
		}

		@Override
		public boolean isModified(JobFilterConfigurationDto container) {
			return container.isOrderModified();
		}
	};

	private String sortField;
	private  boolean sortField_m;
	public static final String PROPERTY_SORT_FIELD = "dpi-jobfilterconfiguration-sortfield";

	private transient static PropertyAccessor<JobFilterConfigurationDto, String> sortField_pa = new PropertyAccessor<JobFilterConfigurationDto, String>() {
		@Override
		public void setValue(JobFilterConfigurationDto container, String object) {
			container.setSortField(object);
		}

		@Override
		public String getValue(JobFilterConfigurationDto container) {
			return container.getSortField();
		}

		@Override
		public Class<?> getType() {
			return String.class;
		}

		@Override
		public String getPath() {
			return "sortField";
		}

		@Override
		public void setModified(JobFilterConfigurationDto container, boolean modified) {
			container.sortField_m = modified;
		}

		@Override
		public boolean isModified(JobFilterConfigurationDto container) {
			return container.isSortFieldModified();
		}
	};


	public JobFilterConfigurationDto() {
		super();
	}

	public boolean isActive()  {
		if(! isDtoProxy()){
			return this.active;
		}

		if(isActiveModified())
			return this.active;

		if(! GWT.isClient())
			return false;

		boolean _value = dtoManager.getProperty(this, instantiatePropertyAccess().active());

		return _value;
	}


	public void setActive(boolean active)  {
		/* old value */
		boolean oldValue = false;
		if(GWT.isClient())
			oldValue = isActive();

		/* set new value */
		this.active = active;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(active_pa, oldValue, active, this.active_m));

		/* set indicator */
		this.active_m = true;

		this.fireObjectChangedEvent(JobFilterConfigurationDtoPA.INSTANCE.active(), oldValue);
	}


	public boolean isActiveModified()  {
		return active_m;
	}


	public static PropertyAccessor<JobFilterConfigurationDto, Boolean> getActivePropertyAccessor()  {
		return active_pa;
	}


	public JobExecutionStatusDto getExecutionStatus()  {
		if(! isDtoProxy()){
			return this.executionStatus;
		}

		if(isExecutionStatusModified())
			return this.executionStatus;

		if(! GWT.isClient())
			return null;

		JobExecutionStatusDto _value = dtoManager.getProperty(this, instantiatePropertyAccess().executionStatus());

		return _value;
	}


	public void setExecutionStatus(JobExecutionStatusDto executionStatus)  {
		/* old value */
		JobExecutionStatusDto oldValue = null;
		if(GWT.isClient())
			oldValue = getExecutionStatus();

		/* set new value */
		this.executionStatus = executionStatus;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(executionStatus_pa, oldValue, executionStatus, this.executionStatus_m));

		/* set indicator */
		this.executionStatus_m = true;

		this.fireObjectChangedEvent(JobFilterConfigurationDtoPA.INSTANCE.executionStatus(), oldValue);
	}


	public boolean isExecutionStatusModified()  {
		return executionStatus_m;
	}


	public static PropertyAccessor<JobFilterConfigurationDto, JobExecutionStatusDto> getExecutionStatusPropertyAccessor()  {
		return executionStatus_pa;
	}


	public boolean isInActive()  {
		if(! isDtoProxy()){
			return this.inActive;
		}

		if(isInActiveModified())
			return this.inActive;

		if(! GWT.isClient())
			return false;

		boolean _value = dtoManager.getProperty(this, instantiatePropertyAccess().inActive());

		return _value;
	}


	public void setInActive(boolean inActive)  {
		/* old value */
		boolean oldValue = false;
		if(GWT.isClient())
			oldValue = isInActive();

		/* set new value */
		this.inActive = inActive;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(inActive_pa, oldValue, inActive, this.inActive_m));

		/* set indicator */
		this.inActive_m = true;

		this.fireObjectChangedEvent(JobFilterConfigurationDtoPA.INSTANCE.inActive(), oldValue);
	}


	public boolean isInActiveModified()  {
		return inActive_m;
	}


	public static PropertyAccessor<JobFilterConfigurationDto, Boolean> getInActivePropertyAccessor()  {
		return inActive_pa;
	}


	public String getJobId()  {
		if(! isDtoProxy()){
			return this.jobId;
		}

		if(isJobIdModified())
			return this.jobId;

		if(! GWT.isClient())
			return null;

		String _value = dtoManager.getProperty(this, instantiatePropertyAccess().jobId());

		return _value;
	}


	public void setJobId(String jobId)  {
		/* old value */
		String oldValue = null;
		if(GWT.isClient())
			oldValue = getJobId();

		/* set new value */
		this.jobId = jobId;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(jobId_pa, oldValue, jobId, this.jobId_m));

		/* set indicator */
		this.jobId_m = true;

		this.fireObjectChangedEvent(JobFilterConfigurationDtoPA.INSTANCE.jobId(), oldValue);
	}


	public boolean isJobIdModified()  {
		return jobId_m;
	}


	public static PropertyAccessor<JobFilterConfigurationDto, String> getJobIdPropertyAccessor()  {
		return jobId_pa;
	}


	public String getJobTitle()  {
		if(! isDtoProxy()){
			return this.jobTitle;
		}

		if(isJobTitleModified())
			return this.jobTitle;

		if(! GWT.isClient())
			return null;

		String _value = dtoManager.getProperty(this, instantiatePropertyAccess().jobTitle());

		return _value;
	}


	public void setJobTitle(String jobTitle)  {
		/* old value */
		String oldValue = null;
		if(GWT.isClient())
			oldValue = getJobTitle();

		/* set new value */
		this.jobTitle = jobTitle;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(jobTitle_pa, oldValue, jobTitle, this.jobTitle_m));

		/* set indicator */
		this.jobTitle_m = true;

		this.fireObjectChangedEvent(JobFilterConfigurationDtoPA.INSTANCE.jobTitle(), oldValue);
	}


	public boolean isJobTitleModified()  {
		return jobTitle_m;
	}


	public static PropertyAccessor<JobFilterConfigurationDto, String> getJobTitlePropertyAccessor()  {
		return jobTitle_pa;
	}


	public OutcomeDto getLastOutcome()  {
		if(! isDtoProxy()){
			return this.lastOutcome;
		}

		if(isLastOutcomeModified())
			return this.lastOutcome;

		if(! GWT.isClient())
			return null;

		OutcomeDto _value = dtoManager.getProperty(this, instantiatePropertyAccess().lastOutcome());

		return _value;
	}


	public void setLastOutcome(OutcomeDto lastOutcome)  {
		/* old value */
		OutcomeDto oldValue = null;
		if(GWT.isClient())
			oldValue = getLastOutcome();

		/* set new value */
		this.lastOutcome = lastOutcome;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(lastOutcome_pa, oldValue, lastOutcome, this.lastOutcome_m));

		/* set indicator */
		this.lastOutcome_m = true;

		this.fireObjectChangedEvent(JobFilterConfigurationDtoPA.INSTANCE.lastOutcome(), oldValue);
	}


	public boolean isLastOutcomeModified()  {
		return lastOutcome_m;
	}


	public static PropertyAccessor<JobFilterConfigurationDto, OutcomeDto> getLastOutcomePropertyAccessor()  {
		return lastOutcome_pa;
	}


	public int getLimit()  {
		if(! isDtoProxy()){
			return this.limit;
		}

		if(isLimitModified())
			return this.limit;

		if(! GWT.isClient())
			return 0;

		int _value = dtoManager.getProperty(this, instantiatePropertyAccess().limit());

		return _value;
	}


	public void setLimit(int limit)  {
		/* old value */
		int oldValue = 0;
		if(GWT.isClient())
			oldValue = getLimit();

		/* set new value */
		this.limit = limit;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(limit_pa, oldValue, limit, this.limit_m));

		/* set indicator */
		this.limit_m = true;

		this.fireObjectChangedEvent(JobFilterConfigurationDtoPA.INSTANCE.limit(), oldValue);
	}


	public boolean isLimitModified()  {
		return limit_m;
	}


	public static PropertyAccessor<JobFilterConfigurationDto, Integer> getLimitPropertyAccessor()  {
		return limit_pa;
	}


	public int getOffset()  {
		if(! isDtoProxy()){
			return this.offset;
		}

		if(isOffsetModified())
			return this.offset;

		if(! GWT.isClient())
			return 0;

		int _value = dtoManager.getProperty(this, instantiatePropertyAccess().offset());

		return _value;
	}


	public void setOffset(int offset)  {
		/* old value */
		int oldValue = 0;
		if(GWT.isClient())
			oldValue = getOffset();

		/* set new value */
		this.offset = offset;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(offset_pa, oldValue, offset, this.offset_m));

		/* set indicator */
		this.offset_m = true;

		this.fireObjectChangedEvent(JobFilterConfigurationDtoPA.INSTANCE.offset(), oldValue);
	}


	public boolean isOffsetModified()  {
		return offset_m;
	}


	public static PropertyAccessor<JobFilterConfigurationDto, Integer> getOffsetPropertyAccessor()  {
		return offset_pa;
	}


	public OrderDto getOrder()  {
		if(! isDtoProxy()){
			return this.order;
		}

		if(isOrderModified())
			return this.order;

		if(! GWT.isClient())
			return null;

		OrderDto _value = dtoManager.getProperty(this, instantiatePropertyAccess().order());

		return _value;
	}


	public void setOrder(OrderDto order)  {
		/* old value */
		OrderDto oldValue = null;
		if(GWT.isClient())
			oldValue = getOrder();

		/* set new value */
		this.order = order;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(order_pa, oldValue, order, this.order_m));

		/* set indicator */
		this.order_m = true;

		this.fireObjectChangedEvent(JobFilterConfigurationDtoPA.INSTANCE.order(), oldValue);
	}


	public boolean isOrderModified()  {
		return order_m;
	}


	public static PropertyAccessor<JobFilterConfigurationDto, OrderDto> getOrderPropertyAccessor()  {
		return order_pa;
	}


	public String getSortField()  {
		if(! isDtoProxy()){
			return this.sortField;
		}

		if(isSortFieldModified())
			return this.sortField;

		if(! GWT.isClient())
			return null;

		String _value = dtoManager.getProperty(this, instantiatePropertyAccess().sortField());

		return _value;
	}


	public void setSortField(String sortField)  {
		/* old value */
		String oldValue = null;
		if(GWT.isClient())
			oldValue = getSortField();

		/* set new value */
		this.sortField = sortField;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(sortField_pa, oldValue, sortField, this.sortField_m));

		/* set indicator */
		this.sortField_m = true;

		this.fireObjectChangedEvent(JobFilterConfigurationDtoPA.INSTANCE.sortField(), oldValue);
	}


	public boolean isSortFieldModified()  {
		return sortField_m;
	}


	public static PropertyAccessor<JobFilterConfigurationDto, String> getSortFieldPropertyAccessor()  {
		return sortField_pa;
	}


	@Override
	public String toString()  {
		return super.toString();
	}

	public static Dto2PosoMapper newPosoMapper()  {
		return new JobFilterConfigurationDto2PosoMap();
	}

	public JobFilterConfigurationDtoPA instantiatePropertyAccess()  {
		return GWT.create(JobFilterConfigurationDtoPA.class);
	}

	public void clearModified()  {
		this.active = false;
		this.active_m = false;
		this.executionStatus = null;
		this.executionStatus_m = false;
		this.inActive = false;
		this.inActive_m = false;
		this.jobId = null;
		this.jobId_m = false;
		this.jobTitle = null;
		this.jobTitle_m = false;
		this.lastOutcome = null;
		this.lastOutcome_m = false;
		this.limit = 0;
		this.limit_m = false;
		this.offset = 0;
		this.offset_m = false;
		this.order = null;
		this.order_m = false;
		this.sortField = null;
		this.sortField_m = false;
	}


	public boolean isModified()  {
		if(super.isModified())
			return true;
		if(active_m)
			return true;
		if(executionStatus_m)
			return true;
		if(inActive_m)
			return true;
		if(jobId_m)
			return true;
		if(jobTitle_m)
			return true;
		if(lastOutcome_m)
			return true;
		if(limit_m)
			return true;
		if(offset_m)
			return true;
		if(order_m)
			return true;
		if(sortField_m)
			return true;
		return false;
	}


	public List<PropertyAccessor> getPropertyAccessors()  {
		List<PropertyAccessor> list = super.getPropertyAccessors();
		list.add(active_pa);
		list.add(executionStatus_pa);
		list.add(inActive_pa);
		list.add(jobId_pa);
		list.add(jobTitle_pa);
		list.add(lastOutcome_pa);
		list.add(limit_pa);
		list.add(offset_pa);
		list.add(order_pa);
		list.add(sortField_pa);
		return list;
	}


	public List<PropertyAccessor> getModifiedPropertyAccessors()  {
		List<PropertyAccessor> list = super.getModifiedPropertyAccessors();
		if(active_m)
			list.add(active_pa);
		if(executionStatus_m)
			list.add(executionStatus_pa);
		if(inActive_m)
			list.add(inActive_pa);
		if(jobId_m)
			list.add(jobId_pa);
		if(jobTitle_m)
			list.add(jobTitle_pa);
		if(lastOutcome_m)
			list.add(lastOutcome_pa);
		if(limit_m)
			list.add(limit_pa);
		if(offset_m)
			list.add(offset_pa);
		if(order_m)
			list.add(order_pa);
		if(sortField_m)
			list.add(sortField_pa);
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsByView(net.datenwerke.gxtdto.client.dtomanager.DtoView view)  {
		List<PropertyAccessor> list = super.getPropertyAccessorsByView(view);
		if(view.compareTo(DtoView.NORMAL) >= 0){
			list.add(active_pa);
			list.add(executionStatus_pa);
			list.add(inActive_pa);
			list.add(jobId_pa);
			list.add(jobTitle_pa);
			list.add(lastOutcome_pa);
			list.add(limit_pa);
			list.add(offset_pa);
			list.add(order_pa);
			list.add(sortField_pa);
		}
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsForDtos()  {
		List<PropertyAccessor> list = super.getPropertyAccessorsForDtos();
		return list;
	}



	net.datenwerke.scheduler.client.scheduler.dto.JobExecutionStatusDto wl_0;
	net.datenwerke.scheduler.client.scheduler.dto.filter.OrderDto wl_1;
	net.datenwerke.scheduler.client.scheduler.dto.OutcomeDto wl_2;

}
