package net.datenwerke.scheduler.client.scheduler.dto.history;

import com.google.gwt.core.client.GWT;
import java.lang.IllegalStateException;
import java.lang.Long;
import java.lang.String;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.gf.base.client.dtogenerator.RsDto;
import net.datenwerke.gxtdto.client.dtomanager.Dto2PosoMapper;
import net.datenwerke.gxtdto.client.dtomanager.Dto;
import net.datenwerke.gxtdto.client.dtomanager.DtoView;
import net.datenwerke.gxtdto.client.dtomanager.IdedDto;
import net.datenwerke.gxtdto.client.dtomanager.PropertyAccessor;
import net.datenwerke.gxtdto.client.dtomanager.dtomod.collections.ChangeMonitoredList;
import net.datenwerke.gxtdto.client.dtomanager.dtomod.collections.MonitoredCollection;
import net.datenwerke.gxtdto.client.dtomanager.redoundo.ChangeTracker;
import net.datenwerke.gxtdto.client.eventbus.events.ObjectChangedEvent;
import net.datenwerke.gxtdto.client.eventbus.handlers.ObjectChangedEventHandler;
import net.datenwerke.gxtdto.client.eventbus.handlers.has.HasObjectChangedEventHandler;
import net.datenwerke.scheduler.client.scheduler.dto.OutcomeDto;
import net.datenwerke.scheduler.client.scheduler.dto.VetoJobExecutionModeDto;
import net.datenwerke.scheduler.client.scheduler.dto.history.ActionEntryDto;
import net.datenwerke.scheduler.client.scheduler.dto.history.JobEntryDto;
import net.datenwerke.scheduler.client.scheduler.dto.history.pa.ExecutionLogEntryDtoPA;
import net.datenwerke.scheduler.client.scheduler.dto.history.posomap.ExecutionLogEntryDto2PosoMap;
import net.datenwerke.scheduler.service.scheduler.entities.history.ExecutionLogEntry;

/**
 * Dto for {@link ExecutionLogEntry}
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class ExecutionLogEntryDto extends RsDto implements IdedDto {


	private static final long serialVersionUID = 1;

	private Long dtoId;


	/* Fields */
	private List<ActionEntryDto> actionEntries;
	private  boolean actionEntries_m;
	public static final String PROPERTY_ACTION_ENTRIES = "dpi-executionlogentry-actionentries";

	private transient static PropertyAccessor<ExecutionLogEntryDto, List<ActionEntryDto>> actionEntries_pa = new PropertyAccessor<ExecutionLogEntryDto, List<ActionEntryDto>>() {
		@Override
		public void setValue(ExecutionLogEntryDto container, List<ActionEntryDto> object) {
			container.setActionEntries(object);
		}

		@Override
		public List<ActionEntryDto> getValue(ExecutionLogEntryDto container) {
			return container.getActionEntries();
		}

		@Override
		public Class<?> getType() {
			return List.class;
		}

		@Override
		public String getPath() {
			return "actionEntries";
		}

		@Override
		public void setModified(ExecutionLogEntryDto container, boolean modified) {
			container.actionEntries_m = modified;
		}

		@Override
		public boolean isModified(ExecutionLogEntryDto container) {
			return container.isActionEntriesModified();
		}
	};

	private String badErrorDescription;
	private  boolean badErrorDescription_m;
	public static final String PROPERTY_BAD_ERROR_DESCRIPTION = "dpi-executionlogentry-baderrordescription";

	private transient static PropertyAccessor<ExecutionLogEntryDto, String> badErrorDescription_pa = new PropertyAccessor<ExecutionLogEntryDto, String>() {
		@Override
		public void setValue(ExecutionLogEntryDto container, String object) {
			container.setBadErrorDescription(object);
		}

		@Override
		public String getValue(ExecutionLogEntryDto container) {
			return container.getBadErrorDescription();
		}

		@Override
		public Class<?> getType() {
			return String.class;
		}

		@Override
		public String getPath() {
			return "badErrorDescription";
		}

		@Override
		public void setModified(ExecutionLogEntryDto container, boolean modified) {
			container.badErrorDescription_m = modified;
		}

		@Override
		public boolean isModified(ExecutionLogEntryDto container) {
			return container.isBadErrorDescriptionModified();
		}
	};

	private Date end;
	private  boolean end_m;
	public static final String PROPERTY_END = "dpi-executionlogentry-end";

	private transient static PropertyAccessor<ExecutionLogEntryDto, Date> end_pa = new PropertyAccessor<ExecutionLogEntryDto, Date>() {
		@Override
		public void setValue(ExecutionLogEntryDto container, Date object) {
			container.setEnd(object);
		}

		@Override
		public Date getValue(ExecutionLogEntryDto container) {
			return container.getEnd();
		}

		@Override
		public Class<?> getType() {
			return Date.class;
		}

		@Override
		public String getPath() {
			return "end";
		}

		@Override
		public void setModified(ExecutionLogEntryDto container, boolean modified) {
			container.end_m = modified;
		}

		@Override
		public boolean isModified(ExecutionLogEntryDto container) {
			return container.isEndModified();
		}
	};

	private Long id;
	private  boolean id_m;
	public static final String PROPERTY_ID = "dpi-executionlogentry-id";

	private transient static PropertyAccessor<ExecutionLogEntryDto, Long> id_pa = new PropertyAccessor<ExecutionLogEntryDto, Long>() {
		@Override
		public void setValue(ExecutionLogEntryDto container, Long object) {
			// id field
		}

		@Override
		public Long getValue(ExecutionLogEntryDto container) {
			return container.getId();
		}

		@Override
		public Class<?> getType() {
			return Long.class;
		}

		@Override
		public String getPath() {
			return "id";
		}

		@Override
		public void setModified(ExecutionLogEntryDto container, boolean modified) {
			container.id_m = modified;
		}

		@Override
		public boolean isModified(ExecutionLogEntryDto container) {
			return container.isIdModified();
		}
	};

	private JobEntryDto jobEntry;
	private  boolean jobEntry_m;
	public static final String PROPERTY_JOB_ENTRY = "dpi-executionlogentry-jobentry";

	private transient static PropertyAccessor<ExecutionLogEntryDto, JobEntryDto> jobEntry_pa = new PropertyAccessor<ExecutionLogEntryDto, JobEntryDto>() {
		@Override
		public void setValue(ExecutionLogEntryDto container, JobEntryDto object) {
			container.setJobEntry(object);
		}

		@Override
		public JobEntryDto getValue(ExecutionLogEntryDto container) {
			return container.getJobEntry();
		}

		@Override
		public Class<?> getType() {
			return JobEntryDto.class;
		}

		@Override
		public String getPath() {
			return "jobEntry";
		}

		@Override
		public void setModified(ExecutionLogEntryDto container, boolean modified) {
			container.jobEntry_m = modified;
		}

		@Override
		public boolean isModified(ExecutionLogEntryDto container) {
			return container.isJobEntryModified();
		}
	};

	private OutcomeDto outcome;
	private  boolean outcome_m;
	public static final String PROPERTY_OUTCOME = "dpi-executionlogentry-outcome";

	private transient static PropertyAccessor<ExecutionLogEntryDto, OutcomeDto> outcome_pa = new PropertyAccessor<ExecutionLogEntryDto, OutcomeDto>() {
		@Override
		public void setValue(ExecutionLogEntryDto container, OutcomeDto object) {
			container.setOutcome(object);
		}

		@Override
		public OutcomeDto getValue(ExecutionLogEntryDto container) {
			return container.getOutcome();
		}

		@Override
		public Class<?> getType() {
			return OutcomeDto.class;
		}

		@Override
		public String getPath() {
			return "outcome";
		}

		@Override
		public void setModified(ExecutionLogEntryDto container, boolean modified) {
			container.outcome_m = modified;
		}

		@Override
		public boolean isModified(ExecutionLogEntryDto container) {
			return container.isOutcomeModified();
		}
	};

	private Date scheduledStart;
	private  boolean scheduledStart_m;
	public static final String PROPERTY_SCHEDULED_START = "dpi-executionlogentry-scheduledstart";

	private transient static PropertyAccessor<ExecutionLogEntryDto, Date> scheduledStart_pa = new PropertyAccessor<ExecutionLogEntryDto, Date>() {
		@Override
		public void setValue(ExecutionLogEntryDto container, Date object) {
			container.setScheduledStart(object);
		}

		@Override
		public Date getValue(ExecutionLogEntryDto container) {
			return container.getScheduledStart();
		}

		@Override
		public Class<?> getType() {
			return Date.class;
		}

		@Override
		public String getPath() {
			return "scheduledStart";
		}

		@Override
		public void setModified(ExecutionLogEntryDto container, boolean modified) {
			container.scheduledStart_m = modified;
		}

		@Override
		public boolean isModified(ExecutionLogEntryDto container) {
			return container.isScheduledStartModified();
		}
	};

	private Date start;
	private  boolean start_m;
	public static final String PROPERTY_START = "dpi-executionlogentry-start";

	private transient static PropertyAccessor<ExecutionLogEntryDto, Date> start_pa = new PropertyAccessor<ExecutionLogEntryDto, Date>() {
		@Override
		public void setValue(ExecutionLogEntryDto container, Date object) {
			container.setStart(object);
		}

		@Override
		public Date getValue(ExecutionLogEntryDto container) {
			return container.getStart();
		}

		@Override
		public Class<?> getType() {
			return Date.class;
		}

		@Override
		public String getPath() {
			return "start";
		}

		@Override
		public void setModified(ExecutionLogEntryDto container, boolean modified) {
			container.start_m = modified;
		}

		@Override
		public boolean isModified(ExecutionLogEntryDto container) {
			return container.isStartModified();
		}
	};

	private String vetoExplanation;
	private  boolean vetoExplanation_m;
	public static final String PROPERTY_VETO_EXPLANATION = "dpi-executionlogentry-vetoexplanation";

	private transient static PropertyAccessor<ExecutionLogEntryDto, String> vetoExplanation_pa = new PropertyAccessor<ExecutionLogEntryDto, String>() {
		@Override
		public void setValue(ExecutionLogEntryDto container, String object) {
			container.setVetoExplanation(object);
		}

		@Override
		public String getValue(ExecutionLogEntryDto container) {
			return container.getVetoExplanation();
		}

		@Override
		public Class<?> getType() {
			return String.class;
		}

		@Override
		public String getPath() {
			return "vetoExplanation";
		}

		@Override
		public void setModified(ExecutionLogEntryDto container, boolean modified) {
			container.vetoExplanation_m = modified;
		}

		@Override
		public boolean isModified(ExecutionLogEntryDto container) {
			return container.isVetoExplanationModified();
		}
	};

	private VetoJobExecutionModeDto vetoMode;
	private  boolean vetoMode_m;
	public static final String PROPERTY_VETO_MODE = "dpi-executionlogentry-vetomode";

	private transient static PropertyAccessor<ExecutionLogEntryDto, VetoJobExecutionModeDto> vetoMode_pa = new PropertyAccessor<ExecutionLogEntryDto, VetoJobExecutionModeDto>() {
		@Override
		public void setValue(ExecutionLogEntryDto container, VetoJobExecutionModeDto object) {
			container.setVetoMode(object);
		}

		@Override
		public VetoJobExecutionModeDto getValue(ExecutionLogEntryDto container) {
			return container.getVetoMode();
		}

		@Override
		public Class<?> getType() {
			return VetoJobExecutionModeDto.class;
		}

		@Override
		public String getPath() {
			return "vetoMode";
		}

		@Override
		public void setModified(ExecutionLogEntryDto container, boolean modified) {
			container.vetoMode_m = modified;
		}

		@Override
		public boolean isModified(ExecutionLogEntryDto container) {
			return container.isVetoModeModified();
		}
	};


	public ExecutionLogEntryDto() {
		super();
	}

	public List<ActionEntryDto> getActionEntries()  {
		if(! isDtoProxy()){
			List<ActionEntryDto> _currentValue = this.actionEntries;
			if(null == _currentValue)
				this.actionEntries = new ArrayList<ActionEntryDto>();

			return this.actionEntries;
		}

		if(isActionEntriesModified())
			return this.actionEntries;

		if(! GWT.isClient())
			return null;

		List<ActionEntryDto> _value = dtoManager.getProperty(this, instantiatePropertyAccess().actionEntries());

		_value = new ChangeMonitoredList<ActionEntryDto>(_value);
		if(_value instanceof HasObjectChangedEventHandler){
			((HasObjectChangedEventHandler)_value).addObjectChangedHandler(new net.datenwerke.gxtdto.client.eventbus.handlers.ObjectChangedEventHandler(){
				@Override
				public void onObjectChangedEvent(net.datenwerke.gxtdto.client.eventbus.events.ObjectChangedEvent event){
					if(! isActionEntriesModified())
						setActionEntries((List<ActionEntryDto>) ((MonitoredCollection) event.getObject()).getUnderlyingCollection());
				}
			}
			);
		}
		return _value;
	}


	public void setActionEntries(List<ActionEntryDto> actionEntries)  {
		/* old value */
		List<ActionEntryDto> oldValue = null;
		if(GWT.isClient())
			oldValue = getActionEntries();

		/* set new value */
		this.actionEntries = actionEntries;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(actionEntries_pa, oldValue, actionEntries, this.actionEntries_m));

		/* set indicator */
		this.actionEntries_m = true;

		this.fireObjectChangedEvent(ExecutionLogEntryDtoPA.INSTANCE.actionEntries(), oldValue);
	}


	public boolean isActionEntriesModified()  {
		return actionEntries_m;
	}


	public static PropertyAccessor<ExecutionLogEntryDto, List<ActionEntryDto>> getActionEntriesPropertyAccessor()  {
		return actionEntries_pa;
	}


	public String getBadErrorDescription()  {
		if(! isDtoProxy()){
			return this.badErrorDescription;
		}

		if(isBadErrorDescriptionModified())
			return this.badErrorDescription;

		if(! GWT.isClient())
			return null;

		String _value = dtoManager.getProperty(this, instantiatePropertyAccess().badErrorDescription());

		return _value;
	}


	public void setBadErrorDescription(String badErrorDescription)  {
		/* old value */
		String oldValue = null;
		if(GWT.isClient())
			oldValue = getBadErrorDescription();

		/* set new value */
		this.badErrorDescription = badErrorDescription;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(badErrorDescription_pa, oldValue, badErrorDescription, this.badErrorDescription_m));

		/* set indicator */
		this.badErrorDescription_m = true;

		this.fireObjectChangedEvent(ExecutionLogEntryDtoPA.INSTANCE.badErrorDescription(), oldValue);
	}


	public boolean isBadErrorDescriptionModified()  {
		return badErrorDescription_m;
	}


	public static PropertyAccessor<ExecutionLogEntryDto, String> getBadErrorDescriptionPropertyAccessor()  {
		return badErrorDescription_pa;
	}


	public Date getEnd()  {
		if(! isDtoProxy()){
			return this.end;
		}

		if(isEndModified())
			return this.end;

		if(! GWT.isClient())
			return null;

		Date _value = dtoManager.getProperty(this, instantiatePropertyAccess().end());

		return _value;
	}


	public void setEnd(Date end)  {
		/* old value */
		Date oldValue = null;
		if(GWT.isClient())
			oldValue = getEnd();

		/* set new value */
		this.end = end;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(end_pa, oldValue, end, this.end_m));

		/* set indicator */
		this.end_m = true;

		this.fireObjectChangedEvent(ExecutionLogEntryDtoPA.INSTANCE.end(), oldValue);
	}


	public boolean isEndModified()  {
		return end_m;
	}


	public static PropertyAccessor<ExecutionLogEntryDto, Date> getEndPropertyAccessor()  {
		return end_pa;
	}


	public final Long getId()  {
		return dtoId;
	}

	public final void setId(Long id)  {
		if (null != dtoId)
			throw new IllegalStateException("Id already set!");
		this.dtoId = id;
	}

	public boolean isIdModified()  {
		return id_m;
	}


	public static PropertyAccessor<ExecutionLogEntryDto, Long> getIdPropertyAccessor()  {
		return id_pa;
	}


	public JobEntryDto getJobEntry()  {
		if(! isDtoProxy()){
			return this.jobEntry;
		}

		if(isJobEntryModified())
			return this.jobEntry;

		if(! GWT.isClient())
			return null;

		JobEntryDto _value = dtoManager.getProperty(this, instantiatePropertyAccess().jobEntry());

		if(_value instanceof HasObjectChangedEventHandler){
			((HasObjectChangedEventHandler)_value).addObjectChangedHandler(new net.datenwerke.gxtdto.client.eventbus.handlers.ObjectChangedEventHandler(){
				@Override
				public void onObjectChangedEvent(net.datenwerke.gxtdto.client.eventbus.events.ObjectChangedEvent event){
					if(! isJobEntryModified())
						setJobEntry((JobEntryDto) event.getObject());
				}
			}
			);
		}
		return _value;
	}


	public void setJobEntry(JobEntryDto jobEntry)  {
		/* old value */
		JobEntryDto oldValue = null;
		if(GWT.isClient())
			oldValue = getJobEntry();

		/* set new value */
		this.jobEntry = jobEntry;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(jobEntry_pa, oldValue, jobEntry, this.jobEntry_m));

		/* set indicator */
		this.jobEntry_m = true;

		this.fireObjectChangedEvent(ExecutionLogEntryDtoPA.INSTANCE.jobEntry(), oldValue);
	}


	public boolean isJobEntryModified()  {
		return jobEntry_m;
	}


	public static PropertyAccessor<ExecutionLogEntryDto, JobEntryDto> getJobEntryPropertyAccessor()  {
		return jobEntry_pa;
	}


	public OutcomeDto getOutcome()  {
		if(! isDtoProxy()){
			return this.outcome;
		}

		if(isOutcomeModified())
			return this.outcome;

		if(! GWT.isClient())
			return null;

		OutcomeDto _value = dtoManager.getProperty(this, instantiatePropertyAccess().outcome());

		return _value;
	}


	public void setOutcome(OutcomeDto outcome)  {
		/* old value */
		OutcomeDto oldValue = null;
		if(GWT.isClient())
			oldValue = getOutcome();

		/* set new value */
		this.outcome = outcome;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(outcome_pa, oldValue, outcome, this.outcome_m));

		/* set indicator */
		this.outcome_m = true;

		this.fireObjectChangedEvent(ExecutionLogEntryDtoPA.INSTANCE.outcome(), oldValue);
	}


	public boolean isOutcomeModified()  {
		return outcome_m;
	}


	public static PropertyAccessor<ExecutionLogEntryDto, OutcomeDto> getOutcomePropertyAccessor()  {
		return outcome_pa;
	}


	public Date getScheduledStart()  {
		if(! isDtoProxy()){
			return this.scheduledStart;
		}

		if(isScheduledStartModified())
			return this.scheduledStart;

		if(! GWT.isClient())
			return null;

		Date _value = dtoManager.getProperty(this, instantiatePropertyAccess().scheduledStart());

		return _value;
	}


	public void setScheduledStart(Date scheduledStart)  {
		/* old value */
		Date oldValue = null;
		if(GWT.isClient())
			oldValue = getScheduledStart();

		/* set new value */
		this.scheduledStart = scheduledStart;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(scheduledStart_pa, oldValue, scheduledStart, this.scheduledStart_m));

		/* set indicator */
		this.scheduledStart_m = true;

		this.fireObjectChangedEvent(ExecutionLogEntryDtoPA.INSTANCE.scheduledStart(), oldValue);
	}


	public boolean isScheduledStartModified()  {
		return scheduledStart_m;
	}


	public static PropertyAccessor<ExecutionLogEntryDto, Date> getScheduledStartPropertyAccessor()  {
		return scheduledStart_pa;
	}


	public Date getStart()  {
		if(! isDtoProxy()){
			return this.start;
		}

		if(isStartModified())
			return this.start;

		if(! GWT.isClient())
			return null;

		Date _value = dtoManager.getProperty(this, instantiatePropertyAccess().start());

		return _value;
	}


	public void setStart(Date start)  {
		/* old value */
		Date oldValue = null;
		if(GWT.isClient())
			oldValue = getStart();

		/* set new value */
		this.start = start;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(start_pa, oldValue, start, this.start_m));

		/* set indicator */
		this.start_m = true;

		this.fireObjectChangedEvent(ExecutionLogEntryDtoPA.INSTANCE.start(), oldValue);
	}


	public boolean isStartModified()  {
		return start_m;
	}


	public static PropertyAccessor<ExecutionLogEntryDto, Date> getStartPropertyAccessor()  {
		return start_pa;
	}


	public String getVetoExplanation()  {
		if(! isDtoProxy()){
			return this.vetoExplanation;
		}

		if(isVetoExplanationModified())
			return this.vetoExplanation;

		if(! GWT.isClient())
			return null;

		String _value = dtoManager.getProperty(this, instantiatePropertyAccess().vetoExplanation());

		return _value;
	}


	public void setVetoExplanation(String vetoExplanation)  {
		/* old value */
		String oldValue = null;
		if(GWT.isClient())
			oldValue = getVetoExplanation();

		/* set new value */
		this.vetoExplanation = vetoExplanation;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(vetoExplanation_pa, oldValue, vetoExplanation, this.vetoExplanation_m));

		/* set indicator */
		this.vetoExplanation_m = true;

		this.fireObjectChangedEvent(ExecutionLogEntryDtoPA.INSTANCE.vetoExplanation(), oldValue);
	}


	public boolean isVetoExplanationModified()  {
		return vetoExplanation_m;
	}


	public static PropertyAccessor<ExecutionLogEntryDto, String> getVetoExplanationPropertyAccessor()  {
		return vetoExplanation_pa;
	}


	public VetoJobExecutionModeDto getVetoMode()  {
		if(! isDtoProxy()){
			return this.vetoMode;
		}

		if(isVetoModeModified())
			return this.vetoMode;

		if(! GWT.isClient())
			return null;

		VetoJobExecutionModeDto _value = dtoManager.getProperty(this, instantiatePropertyAccess().vetoMode());

		return _value;
	}


	public void setVetoMode(VetoJobExecutionModeDto vetoMode)  {
		/* old value */
		VetoJobExecutionModeDto oldValue = null;
		if(GWT.isClient())
			oldValue = getVetoMode();

		/* set new value */
		this.vetoMode = vetoMode;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(vetoMode_pa, oldValue, vetoMode, this.vetoMode_m));

		/* set indicator */
		this.vetoMode_m = true;

		this.fireObjectChangedEvent(ExecutionLogEntryDtoPA.INSTANCE.vetoMode(), oldValue);
	}


	public boolean isVetoModeModified()  {
		return vetoMode_m;
	}


	public static PropertyAccessor<ExecutionLogEntryDto, VetoJobExecutionModeDto> getVetoModePropertyAccessor()  {
		return vetoMode_pa;
	}


	@Override
	public void setDtoId(Object id)  {
		setId((Long) id);
	}

	@Override
	public Object getDtoId()  {
		return getId();
	}

	@Override
	public int hashCode()  {
		if(null == getId())
			return super.hashCode();
		return getId().hashCode();
	}

	@Override
	public boolean equals(Object obj)  {
		if(! (obj instanceof ExecutionLogEntryDto))
			return false;

		if(null == getId())
			return super.equals(obj);
		return getId().equals(((ExecutionLogEntryDto)obj).getId());
	}

	@Override
	public String toString()  {
		return getClass().getName() + ": " + getId();
	}

	public static Dto2PosoMapper newPosoMapper()  {
		return new ExecutionLogEntryDto2PosoMap();
	}

	public ExecutionLogEntryDtoPA instantiatePropertyAccess()  {
		return GWT.create(ExecutionLogEntryDtoPA.class);
	}

	public void clearModified()  {
		this.actionEntries = null;
		this.actionEntries_m = false;
		this.badErrorDescription = null;
		this.badErrorDescription_m = false;
		this.end = null;
		this.end_m = false;
		this.id = null;
		this.id_m = false;
		this.jobEntry = null;
		this.jobEntry_m = false;
		this.outcome = null;
		this.outcome_m = false;
		this.scheduledStart = null;
		this.scheduledStart_m = false;
		this.start = null;
		this.start_m = false;
		this.vetoExplanation = null;
		this.vetoExplanation_m = false;
		this.vetoMode = null;
		this.vetoMode_m = false;
	}


	public boolean isModified()  {
		if(super.isModified())
			return true;
		if(actionEntries_m)
			return true;
		if(badErrorDescription_m)
			return true;
		if(end_m)
			return true;
		if(id_m)
			return true;
		if(jobEntry_m)
			return true;
		if(outcome_m)
			return true;
		if(scheduledStart_m)
			return true;
		if(start_m)
			return true;
		if(vetoExplanation_m)
			return true;
		if(vetoMode_m)
			return true;
		return false;
	}


	public List<PropertyAccessor> getPropertyAccessors()  {
		List<PropertyAccessor> list = super.getPropertyAccessors();
		list.add(actionEntries_pa);
		list.add(badErrorDescription_pa);
		list.add(end_pa);
		list.add(id_pa);
		list.add(jobEntry_pa);
		list.add(outcome_pa);
		list.add(scheduledStart_pa);
		list.add(start_pa);
		list.add(vetoExplanation_pa);
		list.add(vetoMode_pa);
		return list;
	}


	public List<PropertyAccessor> getModifiedPropertyAccessors()  {
		List<PropertyAccessor> list = super.getModifiedPropertyAccessors();
		if(actionEntries_m)
			list.add(actionEntries_pa);
		if(badErrorDescription_m)
			list.add(badErrorDescription_pa);
		if(end_m)
			list.add(end_pa);
		if(id_m)
			list.add(id_pa);
		if(jobEntry_m)
			list.add(jobEntry_pa);
		if(outcome_m)
			list.add(outcome_pa);
		if(scheduledStart_m)
			list.add(scheduledStart_pa);
		if(start_m)
			list.add(start_pa);
		if(vetoExplanation_m)
			list.add(vetoExplanation_pa);
		if(vetoMode_m)
			list.add(vetoMode_pa);
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsByView(net.datenwerke.gxtdto.client.dtomanager.DtoView view)  {
		List<PropertyAccessor> list = super.getPropertyAccessorsByView(view);
		if(view.compareTo(DtoView.MINIMAL) >= 0){
			list.add(id_pa);
		}
		if(view.compareTo(DtoView.LIST) >= 0){
			list.add(end_pa);
			list.add(outcome_pa);
			list.add(scheduledStart_pa);
			list.add(start_pa);
			list.add(vetoExplanation_pa);
			list.add(vetoMode_pa);
		}
		if(view.compareTo(DtoView.NORMAL) >= 0){
			list.add(actionEntries_pa);
			list.add(badErrorDescription_pa);
			list.add(jobEntry_pa);
		}
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsForDtos()  {
		List<PropertyAccessor> list = super.getPropertyAccessorsForDtos();
		list.add(actionEntries_pa);
		list.add(jobEntry_pa);
		return list;
	}



	net.datenwerke.scheduler.client.scheduler.dto.VetoJobExecutionModeDto wl_0;
	net.datenwerke.scheduler.client.scheduler.dto.history.JobEntryDto wl_1;
	net.datenwerke.scheduler.client.scheduler.dto.OutcomeDto wl_2;
	net.datenwerke.scheduler.client.scheduler.dto.history.ActionEntryDto wl_3;

}
