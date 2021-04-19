package net.datenwerke.scheduler.client.scheduler.dto.history;

import com.google.gwt.core.client.GWT;
import java.lang.IllegalStateException;
import java.lang.Long;
import java.util.ArrayList;
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
import net.datenwerke.scheduler.client.scheduler.dto.history.ExecutionLogEntryDto;
import net.datenwerke.scheduler.client.scheduler.dto.history.pa.JobHistoryDtoPA;
import net.datenwerke.scheduler.client.scheduler.dto.history.posomap.JobHistoryDto2PosoMap;
import net.datenwerke.scheduler.service.scheduler.entities.history.JobHistory;

/**
 * Dto for {@link JobHistory}
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class JobHistoryDto extends RsDto implements IdedDto {


	private static final long serialVersionUID = 1;

	private Long dtoId;


	/* Fields */
	private List<ExecutionLogEntryDto> executionLogEntries;
	private  boolean executionLogEntries_m;
	public static final String PROPERTY_EXECUTION_LOG_ENTRIES = "dpi-jobhistory-executionlogentries";

	private transient static PropertyAccessor<JobHistoryDto, List<ExecutionLogEntryDto>> executionLogEntries_pa = new PropertyAccessor<JobHistoryDto, List<ExecutionLogEntryDto>>() {
		@Override
		public void setValue(JobHistoryDto container, List<ExecutionLogEntryDto> object) {
			container.setExecutionLogEntries(object);
		}

		@Override
		public List<ExecutionLogEntryDto> getValue(JobHistoryDto container) {
			return container.getExecutionLogEntries();
		}

		@Override
		public Class<?> getType() {
			return List.class;
		}

		@Override
		public String getPath() {
			return "executionLogEntries";
		}

		@Override
		public void setModified(JobHistoryDto container, boolean modified) {
			container.executionLogEntries_m = modified;
		}

		@Override
		public boolean isModified(JobHistoryDto container) {
			return container.isExecutionLogEntriesModified();
		}
	};

	private Long id;
	private  boolean id_m;
	public static final String PROPERTY_ID = "dpi-jobhistory-id";

	private transient static PropertyAccessor<JobHistoryDto, Long> id_pa = new PropertyAccessor<JobHistoryDto, Long>() {
		@Override
		public void setValue(JobHistoryDto container, Long object) {
			// id field
		}

		@Override
		public Long getValue(JobHistoryDto container) {
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
		public void setModified(JobHistoryDto container, boolean modified) {
			container.id_m = modified;
		}

		@Override
		public boolean isModified(JobHistoryDto container) {
			return container.isIdModified();
		}
	};


	public JobHistoryDto() {
		super();
	}

	public List<ExecutionLogEntryDto> getExecutionLogEntries()  {
		if(! isDtoProxy()){
			List<ExecutionLogEntryDto> _currentValue = this.executionLogEntries;
			if(null == _currentValue)
				this.executionLogEntries = new ArrayList<ExecutionLogEntryDto>();

			return this.executionLogEntries;
		}

		if(isExecutionLogEntriesModified())
			return this.executionLogEntries;

		if(! GWT.isClient())
			return null;

		List<ExecutionLogEntryDto> _value = dtoManager.getProperty(this, instantiatePropertyAccess().executionLogEntries());

		_value = new ChangeMonitoredList<ExecutionLogEntryDto>(_value);
		if(_value instanceof HasObjectChangedEventHandler){
			((HasObjectChangedEventHandler)_value).addObjectChangedHandler(new net.datenwerke.gxtdto.client.eventbus.handlers.ObjectChangedEventHandler(){
				@Override
				public void onObjectChangedEvent(net.datenwerke.gxtdto.client.eventbus.events.ObjectChangedEvent event){
					if(! isExecutionLogEntriesModified())
						setExecutionLogEntries((List<ExecutionLogEntryDto>) ((MonitoredCollection) event.getObject()).getUnderlyingCollection());
				}
			}
			);
		}
		return _value;
	}


	public void setExecutionLogEntries(List<ExecutionLogEntryDto> executionLogEntries)  {
		/* old value */
		List<ExecutionLogEntryDto> oldValue = null;
		if(GWT.isClient())
			oldValue = getExecutionLogEntries();

		/* set new value */
		this.executionLogEntries = executionLogEntries;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(executionLogEntries_pa, oldValue, executionLogEntries, this.executionLogEntries_m));

		/* set indicator */
		this.executionLogEntries_m = true;

		this.fireObjectChangedEvent(JobHistoryDtoPA.INSTANCE.executionLogEntries(), oldValue);
	}


	public boolean isExecutionLogEntriesModified()  {
		return executionLogEntries_m;
	}


	public static PropertyAccessor<JobHistoryDto, List<ExecutionLogEntryDto>> getExecutionLogEntriesPropertyAccessor()  {
		return executionLogEntries_pa;
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


	public static PropertyAccessor<JobHistoryDto, Long> getIdPropertyAccessor()  {
		return id_pa;
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
		if(! (obj instanceof JobHistoryDto))
			return false;

		if(null == getId())
			return super.equals(obj);
		return getId().equals(((JobHistoryDto)obj).getId());
	}

	@Override
	public String toString()  {
		return getClass().getName() + ": " + getId();
	}

	public static Dto2PosoMapper newPosoMapper()  {
		return new JobHistoryDto2PosoMap();
	}

	public JobHistoryDtoPA instantiatePropertyAccess()  {
		return GWT.create(JobHistoryDtoPA.class);
	}

	public void clearModified()  {
		this.executionLogEntries = null;
		this.executionLogEntries_m = false;
		this.id = null;
		this.id_m = false;
	}


	public boolean isModified()  {
		if(super.isModified())
			return true;
		if(executionLogEntries_m)
			return true;
		if(id_m)
			return true;
		return false;
	}


	public List<PropertyAccessor> getPropertyAccessors()  {
		List<PropertyAccessor> list = super.getPropertyAccessors();
		list.add(executionLogEntries_pa);
		list.add(id_pa);
		return list;
	}


	public List<PropertyAccessor> getModifiedPropertyAccessors()  {
		List<PropertyAccessor> list = super.getModifiedPropertyAccessors();
		if(executionLogEntries_m)
			list.add(executionLogEntries_pa);
		if(id_m)
			list.add(id_pa);
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsByView(net.datenwerke.gxtdto.client.dtomanager.DtoView view)  {
		List<PropertyAccessor> list = super.getPropertyAccessorsByView(view);
		if(view.compareTo(DtoView.MINIMAL) >= 0){
			list.add(id_pa);
		}
		if(view.compareTo(DtoView.LIST) >= 0){
			list.add(executionLogEntries_pa);
		}
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsForDtos()  {
		List<PropertyAccessor> list = super.getPropertyAccessorsForDtos();
		list.add(executionLogEntries_pa);
		return list;
	}



	net.datenwerke.scheduler.client.scheduler.dto.history.ExecutionLogEntryDto wl_0;

}
