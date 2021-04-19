package net.datenwerke.scheduler.client.scheduler.dto.config.complex;

import com.google.gwt.core.client.GWT;
import java.lang.IllegalStateException;
import java.lang.Integer;
import java.lang.Long;
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
import net.datenwerke.gxtdto.client.dtomanager.redoundo.ChangeTracker;
import net.datenwerke.gxtdto.client.eventbus.events.ObjectChangedEvent;
import net.datenwerke.gxtdto.client.eventbus.handlers.ObjectChangedEventHandler;
import net.datenwerke.gxtdto.client.eventbus.handlers.has.HasObjectChangedEventHandler;
import net.datenwerke.scheduler.client.scheduler.dto.config.complex.DailyRepeatTypeDto;
import net.datenwerke.scheduler.client.scheduler.dto.config.complex.EndTypesDto;
import net.datenwerke.scheduler.client.scheduler.dto.config.complex.TimeDto;
import net.datenwerke.scheduler.client.scheduler.dto.config.complex.TimeUnitsDto;
import net.datenwerke.scheduler.client.scheduler.dto.config.complex.pa.DateTriggerConfigDtoPA;
import net.datenwerke.scheduler.client.scheduler.dto.config.complex.posomap.DateTriggerConfigDto2PosoMap;
import net.datenwerke.scheduler.service.scheduler.triggers.complex.config.DateTriggerConfig;

/**
 * Dto for {@link DateTriggerConfig}
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class DateTriggerConfigDto extends RsDto implements IdedDto {


	private static final long serialVersionUID = 1;

	private Long dtoId;


	/* Fields */
	private TimeDto atTime;
	private  boolean atTime_m;
	public static final String PROPERTY_AT_TIME = "dpi-datetriggerconfig-attime";

	private transient static PropertyAccessor<DateTriggerConfigDto, TimeDto> atTime_pa = new PropertyAccessor<DateTriggerConfigDto, TimeDto>() {
		@Override
		public void setValue(DateTriggerConfigDto container, TimeDto object) {
			container.setAtTime(object);
		}

		@Override
		public TimeDto getValue(DateTriggerConfigDto container) {
			return container.getAtTime();
		}

		@Override
		public Class<?> getType() {
			return TimeDto.class;
		}

		@Override
		public String getPath() {
			return "atTime";
		}

		@Override
		public void setModified(DateTriggerConfigDto container, boolean modified) {
			container.atTime_m = modified;
		}

		@Override
		public boolean isModified(DateTriggerConfigDto container) {
			return container.isAtTimeModified();
		}
	};

	private DailyRepeatTypeDto dailyRepeatType;
	private  boolean dailyRepeatType_m;
	public static final String PROPERTY_DAILY_REPEAT_TYPE = "dpi-datetriggerconfig-dailyrepeattype";

	private transient static PropertyAccessor<DateTriggerConfigDto, DailyRepeatTypeDto> dailyRepeatType_pa = new PropertyAccessor<DateTriggerConfigDto, DailyRepeatTypeDto>() {
		@Override
		public void setValue(DateTriggerConfigDto container, DailyRepeatTypeDto object) {
			container.setDailyRepeatType(object);
		}

		@Override
		public DailyRepeatTypeDto getValue(DateTriggerConfigDto container) {
			return container.getDailyRepeatType();
		}

		@Override
		public Class<?> getType() {
			return DailyRepeatTypeDto.class;
		}

		@Override
		public String getPath() {
			return "dailyRepeatType";
		}

		@Override
		public void setModified(DateTriggerConfigDto container, boolean modified) {
			container.dailyRepeatType_m = modified;
		}

		@Override
		public boolean isModified(DateTriggerConfigDto container) {
			return container.isDailyRepeatTypeModified();
		}
	};

	private EndTypesDto endType;
	private  boolean endType_m;
	public static final String PROPERTY_END_TYPE = "dpi-datetriggerconfig-endtype";

	private transient static PropertyAccessor<DateTriggerConfigDto, EndTypesDto> endType_pa = new PropertyAccessor<DateTriggerConfigDto, EndTypesDto>() {
		@Override
		public void setValue(DateTriggerConfigDto container, EndTypesDto object) {
			container.setEndType(object);
		}

		@Override
		public EndTypesDto getValue(DateTriggerConfigDto container) {
			return container.getEndType();
		}

		@Override
		public Class<?> getType() {
			return EndTypesDto.class;
		}

		@Override
		public String getPath() {
			return "endType";
		}

		@Override
		public void setModified(DateTriggerConfigDto container, boolean modified) {
			container.endType_m = modified;
		}

		@Override
		public boolean isModified(DateTriggerConfigDto container) {
			return container.isEndTypeModified();
		}
	};

	private Date firstExecution;
	private  boolean firstExecution_m;
	public static final String PROPERTY_FIRST_EXECUTION = "dpi-datetriggerconfig-firstexecution";

	private transient static PropertyAccessor<DateTriggerConfigDto, Date> firstExecution_pa = new PropertyAccessor<DateTriggerConfigDto, Date>() {
		@Override
		public void setValue(DateTriggerConfigDto container, Date object) {
			container.setFirstExecution(object);
		}

		@Override
		public Date getValue(DateTriggerConfigDto container) {
			return container.getFirstExecution();
		}

		@Override
		public Class<?> getType() {
			return Date.class;
		}

		@Override
		public String getPath() {
			return "firstExecution";
		}

		@Override
		public void setModified(DateTriggerConfigDto container, boolean modified) {
			container.firstExecution_m = modified;
		}

		@Override
		public boolean isModified(DateTriggerConfigDto container) {
			return container.isFirstExecutionModified();
		}
	};

	private Long id;
	private  boolean id_m;
	public static final String PROPERTY_ID = "dpi-datetriggerconfig-id";

	private transient static PropertyAccessor<DateTriggerConfigDto, Long> id_pa = new PropertyAccessor<DateTriggerConfigDto, Long>() {
		@Override
		public void setValue(DateTriggerConfigDto container, Long object) {
			// id field
		}

		@Override
		public Long getValue(DateTriggerConfigDto container) {
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
		public void setModified(DateTriggerConfigDto container, boolean modified) {
			container.id_m = modified;
		}

		@Override
		public boolean isModified(DateTriggerConfigDto container) {
			return container.isIdModified();
		}
	};

	private Date lastExecution;
	private  boolean lastExecution_m;
	public static final String PROPERTY_LAST_EXECUTION = "dpi-datetriggerconfig-lastexecution";

	private transient static PropertyAccessor<DateTriggerConfigDto, Date> lastExecution_pa = new PropertyAccessor<DateTriggerConfigDto, Date>() {
		@Override
		public void setValue(DateTriggerConfigDto container, Date object) {
			container.setLastExecution(object);
		}

		@Override
		public Date getValue(DateTriggerConfigDto container) {
			return container.getLastExecution();
		}

		@Override
		public Class<?> getType() {
			return Date.class;
		}

		@Override
		public String getPath() {
			return "lastExecution";
		}

		@Override
		public void setModified(DateTriggerConfigDto container, boolean modified) {
			container.lastExecution_m = modified;
		}

		@Override
		public boolean isModified(DateTriggerConfigDto container) {
			return container.isLastExecutionModified();
		}
	};

	private Integer numberOfExecutions;
	private  boolean numberOfExecutions_m;
	public static final String PROPERTY_NUMBER_OF_EXECUTIONS = "dpi-datetriggerconfig-numberofexecutions";

	private transient static PropertyAccessor<DateTriggerConfigDto, Integer> numberOfExecutions_pa = new PropertyAccessor<DateTriggerConfigDto, Integer>() {
		@Override
		public void setValue(DateTriggerConfigDto container, Integer object) {
			container.setNumberOfExecutions(object);
		}

		@Override
		public Integer getValue(DateTriggerConfigDto container) {
			return container.getNumberOfExecutions();
		}

		@Override
		public Class<?> getType() {
			return Integer.class;
		}

		@Override
		public String getPath() {
			return "numberOfExecutions";
		}

		@Override
		public void setModified(DateTriggerConfigDto container, boolean modified) {
			container.numberOfExecutions_m = modified;
		}

		@Override
		public boolean isModified(DateTriggerConfigDto container) {
			return container.isNumberOfExecutionsModified();
		}
	};

	private TimeDto timeRangeEnd;
	private  boolean timeRangeEnd_m;
	public static final String PROPERTY_TIME_RANGE_END = "dpi-datetriggerconfig-timerangeend";

	private transient static PropertyAccessor<DateTriggerConfigDto, TimeDto> timeRangeEnd_pa = new PropertyAccessor<DateTriggerConfigDto, TimeDto>() {
		@Override
		public void setValue(DateTriggerConfigDto container, TimeDto object) {
			container.setTimeRangeEnd(object);
		}

		@Override
		public TimeDto getValue(DateTriggerConfigDto container) {
			return container.getTimeRangeEnd();
		}

		@Override
		public Class<?> getType() {
			return TimeDto.class;
		}

		@Override
		public String getPath() {
			return "timeRangeEnd";
		}

		@Override
		public void setModified(DateTriggerConfigDto container, boolean modified) {
			container.timeRangeEnd_m = modified;
		}

		@Override
		public boolean isModified(DateTriggerConfigDto container) {
			return container.isTimeRangeEndModified();
		}
	};

	private Integer timeRangeInterval;
	private  boolean timeRangeInterval_m;
	public static final String PROPERTY_TIME_RANGE_INTERVAL = "dpi-datetriggerconfig-timerangeinterval";

	private transient static PropertyAccessor<DateTriggerConfigDto, Integer> timeRangeInterval_pa = new PropertyAccessor<DateTriggerConfigDto, Integer>() {
		@Override
		public void setValue(DateTriggerConfigDto container, Integer object) {
			container.setTimeRangeInterval(object);
		}

		@Override
		public Integer getValue(DateTriggerConfigDto container) {
			return container.getTimeRangeInterval();
		}

		@Override
		public Class<?> getType() {
			return Integer.class;
		}

		@Override
		public String getPath() {
			return "timeRangeInterval";
		}

		@Override
		public void setModified(DateTriggerConfigDto container, boolean modified) {
			container.timeRangeInterval_m = modified;
		}

		@Override
		public boolean isModified(DateTriggerConfigDto container) {
			return container.isTimeRangeIntervalModified();
		}
	};

	private TimeDto timeRangeStart;
	private  boolean timeRangeStart_m;
	public static final String PROPERTY_TIME_RANGE_START = "dpi-datetriggerconfig-timerangestart";

	private transient static PropertyAccessor<DateTriggerConfigDto, TimeDto> timeRangeStart_pa = new PropertyAccessor<DateTriggerConfigDto, TimeDto>() {
		@Override
		public void setValue(DateTriggerConfigDto container, TimeDto object) {
			container.setTimeRangeStart(object);
		}

		@Override
		public TimeDto getValue(DateTriggerConfigDto container) {
			return container.getTimeRangeStart();
		}

		@Override
		public Class<?> getType() {
			return TimeDto.class;
		}

		@Override
		public String getPath() {
			return "timeRangeStart";
		}

		@Override
		public void setModified(DateTriggerConfigDto container, boolean modified) {
			container.timeRangeStart_m = modified;
		}

		@Override
		public boolean isModified(DateTriggerConfigDto container) {
			return container.isTimeRangeStartModified();
		}
	};

	private TimeUnitsDto timeRangeUnit;
	private  boolean timeRangeUnit_m;
	public static final String PROPERTY_TIME_RANGE_UNIT = "dpi-datetriggerconfig-timerangeunit";

	private transient static PropertyAccessor<DateTriggerConfigDto, TimeUnitsDto> timeRangeUnit_pa = new PropertyAccessor<DateTriggerConfigDto, TimeUnitsDto>() {
		@Override
		public void setValue(DateTriggerConfigDto container, TimeUnitsDto object) {
			container.setTimeRangeUnit(object);
		}

		@Override
		public TimeUnitsDto getValue(DateTriggerConfigDto container) {
			return container.getTimeRangeUnit();
		}

		@Override
		public Class<?> getType() {
			return TimeUnitsDto.class;
		}

		@Override
		public String getPath() {
			return "timeRangeUnit";
		}

		@Override
		public void setModified(DateTriggerConfigDto container, boolean modified) {
			container.timeRangeUnit_m = modified;
		}

		@Override
		public boolean isModified(DateTriggerConfigDto container) {
			return container.isTimeRangeUnitModified();
		}
	};


	public DateTriggerConfigDto() {
		super();
	}

	public TimeDto getAtTime()  {
		if(! isDtoProxy()){
			return this.atTime;
		}

		if(isAtTimeModified())
			return this.atTime;

		if(! GWT.isClient())
			return null;

		TimeDto _value = dtoManager.getProperty(this, instantiatePropertyAccess().atTime());

		if(_value instanceof HasObjectChangedEventHandler){
			((HasObjectChangedEventHandler)_value).addObjectChangedHandler(new net.datenwerke.gxtdto.client.eventbus.handlers.ObjectChangedEventHandler(){
				@Override
				public void onObjectChangedEvent(net.datenwerke.gxtdto.client.eventbus.events.ObjectChangedEvent event){
					if(! isAtTimeModified())
						setAtTime((TimeDto) event.getObject());
				}
			}
			);
		}
		return _value;
	}


	public void setAtTime(TimeDto atTime)  {
		/* old value */
		TimeDto oldValue = null;
		if(GWT.isClient())
			oldValue = getAtTime();

		/* set new value */
		this.atTime = atTime;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(atTime_pa, oldValue, atTime, this.atTime_m));

		/* set indicator */
		this.atTime_m = true;

		this.fireObjectChangedEvent(DateTriggerConfigDtoPA.INSTANCE.atTime(), oldValue);
	}


	public boolean isAtTimeModified()  {
		return atTime_m;
	}


	public static PropertyAccessor<DateTriggerConfigDto, TimeDto> getAtTimePropertyAccessor()  {
		return atTime_pa;
	}


	public DailyRepeatTypeDto getDailyRepeatType()  {
		if(! isDtoProxy()){
			return this.dailyRepeatType;
		}

		if(isDailyRepeatTypeModified())
			return this.dailyRepeatType;

		if(! GWT.isClient())
			return null;

		DailyRepeatTypeDto _value = dtoManager.getProperty(this, instantiatePropertyAccess().dailyRepeatType());

		return _value;
	}


	public void setDailyRepeatType(DailyRepeatTypeDto dailyRepeatType)  {
		/* old value */
		DailyRepeatTypeDto oldValue = null;
		if(GWT.isClient())
			oldValue = getDailyRepeatType();

		/* set new value */
		this.dailyRepeatType = dailyRepeatType;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(dailyRepeatType_pa, oldValue, dailyRepeatType, this.dailyRepeatType_m));

		/* set indicator */
		this.dailyRepeatType_m = true;

		this.fireObjectChangedEvent(DateTriggerConfigDtoPA.INSTANCE.dailyRepeatType(), oldValue);
	}


	public boolean isDailyRepeatTypeModified()  {
		return dailyRepeatType_m;
	}


	public static PropertyAccessor<DateTriggerConfigDto, DailyRepeatTypeDto> getDailyRepeatTypePropertyAccessor()  {
		return dailyRepeatType_pa;
	}


	public EndTypesDto getEndType()  {
		if(! isDtoProxy()){
			return this.endType;
		}

		if(isEndTypeModified())
			return this.endType;

		if(! GWT.isClient())
			return null;

		EndTypesDto _value = dtoManager.getProperty(this, instantiatePropertyAccess().endType());

		return _value;
	}


	public void setEndType(EndTypesDto endType)  {
		/* old value */
		EndTypesDto oldValue = null;
		if(GWT.isClient())
			oldValue = getEndType();

		/* set new value */
		this.endType = endType;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(endType_pa, oldValue, endType, this.endType_m));

		/* set indicator */
		this.endType_m = true;

		this.fireObjectChangedEvent(DateTriggerConfigDtoPA.INSTANCE.endType(), oldValue);
	}


	public boolean isEndTypeModified()  {
		return endType_m;
	}


	public static PropertyAccessor<DateTriggerConfigDto, EndTypesDto> getEndTypePropertyAccessor()  {
		return endType_pa;
	}


	public Date getFirstExecution()  {
		if(! isDtoProxy()){
			return this.firstExecution;
		}

		if(isFirstExecutionModified())
			return this.firstExecution;

		if(! GWT.isClient())
			return null;

		Date _value = dtoManager.getProperty(this, instantiatePropertyAccess().firstExecution());

		return _value;
	}


	public void setFirstExecution(Date firstExecution)  {
		/* old value */
		Date oldValue = null;
		if(GWT.isClient())
			oldValue = getFirstExecution();

		/* set new value */
		this.firstExecution = firstExecution;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(firstExecution_pa, oldValue, firstExecution, this.firstExecution_m));

		/* set indicator */
		this.firstExecution_m = true;

		this.fireObjectChangedEvent(DateTriggerConfigDtoPA.INSTANCE.firstExecution(), oldValue);
	}


	public boolean isFirstExecutionModified()  {
		return firstExecution_m;
	}


	public static PropertyAccessor<DateTriggerConfigDto, Date> getFirstExecutionPropertyAccessor()  {
		return firstExecution_pa;
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


	public static PropertyAccessor<DateTriggerConfigDto, Long> getIdPropertyAccessor()  {
		return id_pa;
	}


	public Date getLastExecution()  {
		if(! isDtoProxy()){
			return this.lastExecution;
		}

		if(isLastExecutionModified())
			return this.lastExecution;

		if(! GWT.isClient())
			return null;

		Date _value = dtoManager.getProperty(this, instantiatePropertyAccess().lastExecution());

		return _value;
	}


	public void setLastExecution(Date lastExecution)  {
		/* old value */
		Date oldValue = null;
		if(GWT.isClient())
			oldValue = getLastExecution();

		/* set new value */
		this.lastExecution = lastExecution;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(lastExecution_pa, oldValue, lastExecution, this.lastExecution_m));

		/* set indicator */
		this.lastExecution_m = true;

		this.fireObjectChangedEvent(DateTriggerConfigDtoPA.INSTANCE.lastExecution(), oldValue);
	}


	public boolean isLastExecutionModified()  {
		return lastExecution_m;
	}


	public static PropertyAccessor<DateTriggerConfigDto, Date> getLastExecutionPropertyAccessor()  {
		return lastExecution_pa;
	}


	public Integer getNumberOfExecutions()  {
		if(! isDtoProxy()){
			return this.numberOfExecutions;
		}

		if(isNumberOfExecutionsModified())
			return this.numberOfExecutions;

		if(! GWT.isClient())
			return null;

		Integer _value = dtoManager.getProperty(this, instantiatePropertyAccess().numberOfExecutions());

		return _value;
	}


	public void setNumberOfExecutions(Integer numberOfExecutions)  {
		/* old value */
		Integer oldValue = null;
		if(GWT.isClient())
			oldValue = getNumberOfExecutions();

		/* set new value */
		this.numberOfExecutions = numberOfExecutions;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(numberOfExecutions_pa, oldValue, numberOfExecutions, this.numberOfExecutions_m));

		/* set indicator */
		this.numberOfExecutions_m = true;

		this.fireObjectChangedEvent(DateTriggerConfigDtoPA.INSTANCE.numberOfExecutions(), oldValue);
	}


	public boolean isNumberOfExecutionsModified()  {
		return numberOfExecutions_m;
	}


	public static PropertyAccessor<DateTriggerConfigDto, Integer> getNumberOfExecutionsPropertyAccessor()  {
		return numberOfExecutions_pa;
	}


	public TimeDto getTimeRangeEnd()  {
		if(! isDtoProxy()){
			return this.timeRangeEnd;
		}

		if(isTimeRangeEndModified())
			return this.timeRangeEnd;

		if(! GWT.isClient())
			return null;

		TimeDto _value = dtoManager.getProperty(this, instantiatePropertyAccess().timeRangeEnd());

		if(_value instanceof HasObjectChangedEventHandler){
			((HasObjectChangedEventHandler)_value).addObjectChangedHandler(new net.datenwerke.gxtdto.client.eventbus.handlers.ObjectChangedEventHandler(){
				@Override
				public void onObjectChangedEvent(net.datenwerke.gxtdto.client.eventbus.events.ObjectChangedEvent event){
					if(! isTimeRangeEndModified())
						setTimeRangeEnd((TimeDto) event.getObject());
				}
			}
			);
		}
		return _value;
	}


	public void setTimeRangeEnd(TimeDto timeRangeEnd)  {
		/* old value */
		TimeDto oldValue = null;
		if(GWT.isClient())
			oldValue = getTimeRangeEnd();

		/* set new value */
		this.timeRangeEnd = timeRangeEnd;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(timeRangeEnd_pa, oldValue, timeRangeEnd, this.timeRangeEnd_m));

		/* set indicator */
		this.timeRangeEnd_m = true;

		this.fireObjectChangedEvent(DateTriggerConfigDtoPA.INSTANCE.timeRangeEnd(), oldValue);
	}


	public boolean isTimeRangeEndModified()  {
		return timeRangeEnd_m;
	}


	public static PropertyAccessor<DateTriggerConfigDto, TimeDto> getTimeRangeEndPropertyAccessor()  {
		return timeRangeEnd_pa;
	}


	public Integer getTimeRangeInterval()  {
		if(! isDtoProxy()){
			return this.timeRangeInterval;
		}

		if(isTimeRangeIntervalModified())
			return this.timeRangeInterval;

		if(! GWT.isClient())
			return null;

		Integer _value = dtoManager.getProperty(this, instantiatePropertyAccess().timeRangeInterval());

		return _value;
	}


	public void setTimeRangeInterval(Integer timeRangeInterval)  {
		/* old value */
		Integer oldValue = null;
		if(GWT.isClient())
			oldValue = getTimeRangeInterval();

		/* set new value */
		this.timeRangeInterval = timeRangeInterval;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(timeRangeInterval_pa, oldValue, timeRangeInterval, this.timeRangeInterval_m));

		/* set indicator */
		this.timeRangeInterval_m = true;

		this.fireObjectChangedEvent(DateTriggerConfigDtoPA.INSTANCE.timeRangeInterval(), oldValue);
	}


	public boolean isTimeRangeIntervalModified()  {
		return timeRangeInterval_m;
	}


	public static PropertyAccessor<DateTriggerConfigDto, Integer> getTimeRangeIntervalPropertyAccessor()  {
		return timeRangeInterval_pa;
	}


	public TimeDto getTimeRangeStart()  {
		if(! isDtoProxy()){
			return this.timeRangeStart;
		}

		if(isTimeRangeStartModified())
			return this.timeRangeStart;

		if(! GWT.isClient())
			return null;

		TimeDto _value = dtoManager.getProperty(this, instantiatePropertyAccess().timeRangeStart());

		if(_value instanceof HasObjectChangedEventHandler){
			((HasObjectChangedEventHandler)_value).addObjectChangedHandler(new net.datenwerke.gxtdto.client.eventbus.handlers.ObjectChangedEventHandler(){
				@Override
				public void onObjectChangedEvent(net.datenwerke.gxtdto.client.eventbus.events.ObjectChangedEvent event){
					if(! isTimeRangeStartModified())
						setTimeRangeStart((TimeDto) event.getObject());
				}
			}
			);
		}
		return _value;
	}


	public void setTimeRangeStart(TimeDto timeRangeStart)  {
		/* old value */
		TimeDto oldValue = null;
		if(GWT.isClient())
			oldValue = getTimeRangeStart();

		/* set new value */
		this.timeRangeStart = timeRangeStart;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(timeRangeStart_pa, oldValue, timeRangeStart, this.timeRangeStart_m));

		/* set indicator */
		this.timeRangeStart_m = true;

		this.fireObjectChangedEvent(DateTriggerConfigDtoPA.INSTANCE.timeRangeStart(), oldValue);
	}


	public boolean isTimeRangeStartModified()  {
		return timeRangeStart_m;
	}


	public static PropertyAccessor<DateTriggerConfigDto, TimeDto> getTimeRangeStartPropertyAccessor()  {
		return timeRangeStart_pa;
	}


	public TimeUnitsDto getTimeRangeUnit()  {
		if(! isDtoProxy()){
			return this.timeRangeUnit;
		}

		if(isTimeRangeUnitModified())
			return this.timeRangeUnit;

		if(! GWT.isClient())
			return null;

		TimeUnitsDto _value = dtoManager.getProperty(this, instantiatePropertyAccess().timeRangeUnit());

		return _value;
	}


	public void setTimeRangeUnit(TimeUnitsDto timeRangeUnit)  {
		/* old value */
		TimeUnitsDto oldValue = null;
		if(GWT.isClient())
			oldValue = getTimeRangeUnit();

		/* set new value */
		this.timeRangeUnit = timeRangeUnit;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(timeRangeUnit_pa, oldValue, timeRangeUnit, this.timeRangeUnit_m));

		/* set indicator */
		this.timeRangeUnit_m = true;

		this.fireObjectChangedEvent(DateTriggerConfigDtoPA.INSTANCE.timeRangeUnit(), oldValue);
	}


	public boolean isTimeRangeUnitModified()  {
		return timeRangeUnit_m;
	}


	public static PropertyAccessor<DateTriggerConfigDto, TimeUnitsDto> getTimeRangeUnitPropertyAccessor()  {
		return timeRangeUnit_pa;
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
		if(! (obj instanceof DateTriggerConfigDto))
			return false;

		if(null == getId())
			return super.equals(obj);
		return getId().equals(((DateTriggerConfigDto)obj).getId());
	}

	@Override
	public String toString()  {
		return getClass().getName() + ": " + getId();
	}

	public static Dto2PosoMapper newPosoMapper()  {
		return new DateTriggerConfigDto2PosoMap();
	}

	public DateTriggerConfigDtoPA instantiatePropertyAccess()  {
		return GWT.create(DateTriggerConfigDtoPA.class);
	}

	public void clearModified()  {
		this.atTime = null;
		this.atTime_m = false;
		this.dailyRepeatType = null;
		this.dailyRepeatType_m = false;
		this.endType = null;
		this.endType_m = false;
		this.firstExecution = null;
		this.firstExecution_m = false;
		this.id = null;
		this.id_m = false;
		this.lastExecution = null;
		this.lastExecution_m = false;
		this.numberOfExecutions = null;
		this.numberOfExecutions_m = false;
		this.timeRangeEnd = null;
		this.timeRangeEnd_m = false;
		this.timeRangeInterval = null;
		this.timeRangeInterval_m = false;
		this.timeRangeStart = null;
		this.timeRangeStart_m = false;
		this.timeRangeUnit = null;
		this.timeRangeUnit_m = false;
	}


	public boolean isModified()  {
		if(super.isModified())
			return true;
		if(atTime_m)
			return true;
		if(dailyRepeatType_m)
			return true;
		if(endType_m)
			return true;
		if(firstExecution_m)
			return true;
		if(id_m)
			return true;
		if(lastExecution_m)
			return true;
		if(numberOfExecutions_m)
			return true;
		if(timeRangeEnd_m)
			return true;
		if(timeRangeInterval_m)
			return true;
		if(timeRangeStart_m)
			return true;
		if(timeRangeUnit_m)
			return true;
		return false;
	}


	public List<PropertyAccessor> getPropertyAccessors()  {
		List<PropertyAccessor> list = super.getPropertyAccessors();
		list.add(atTime_pa);
		list.add(dailyRepeatType_pa);
		list.add(endType_pa);
		list.add(firstExecution_pa);
		list.add(id_pa);
		list.add(lastExecution_pa);
		list.add(numberOfExecutions_pa);
		list.add(timeRangeEnd_pa);
		list.add(timeRangeInterval_pa);
		list.add(timeRangeStart_pa);
		list.add(timeRangeUnit_pa);
		return list;
	}


	public List<PropertyAccessor> getModifiedPropertyAccessors()  {
		List<PropertyAccessor> list = super.getModifiedPropertyAccessors();
		if(atTime_m)
			list.add(atTime_pa);
		if(dailyRepeatType_m)
			list.add(dailyRepeatType_pa);
		if(endType_m)
			list.add(endType_pa);
		if(firstExecution_m)
			list.add(firstExecution_pa);
		if(id_m)
			list.add(id_pa);
		if(lastExecution_m)
			list.add(lastExecution_pa);
		if(numberOfExecutions_m)
			list.add(numberOfExecutions_pa);
		if(timeRangeEnd_m)
			list.add(timeRangeEnd_pa);
		if(timeRangeInterval_m)
			list.add(timeRangeInterval_pa);
		if(timeRangeStart_m)
			list.add(timeRangeStart_pa);
		if(timeRangeUnit_m)
			list.add(timeRangeUnit_pa);
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsByView(net.datenwerke.gxtdto.client.dtomanager.DtoView view)  {
		List<PropertyAccessor> list = super.getPropertyAccessorsByView(view);
		if(view.compareTo(DtoView.MINIMAL) >= 0){
			list.add(id_pa);
		}
		if(view.compareTo(DtoView.NORMAL) >= 0){
			list.add(atTime_pa);
			list.add(dailyRepeatType_pa);
			list.add(endType_pa);
			list.add(firstExecution_pa);
			list.add(lastExecution_pa);
			list.add(numberOfExecutions_pa);
			list.add(timeRangeEnd_pa);
			list.add(timeRangeInterval_pa);
			list.add(timeRangeStart_pa);
			list.add(timeRangeUnit_pa);
		}
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsForDtos()  {
		List<PropertyAccessor> list = super.getPropertyAccessorsForDtos();
		list.add(atTime_pa);
		list.add(timeRangeEnd_pa);
		list.add(timeRangeStart_pa);
		return list;
	}



	net.datenwerke.scheduler.client.scheduler.dto.config.complex.EndTypesDto wl_0;
	net.datenwerke.scheduler.client.scheduler.dto.config.complex.TimeDto wl_1;
	net.datenwerke.scheduler.client.scheduler.dto.config.complex.DailyRepeatTypeDto wl_2;
	net.datenwerke.scheduler.client.scheduler.dto.config.complex.TimeUnitsDto wl_3;

}
