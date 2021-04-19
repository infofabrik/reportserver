package net.datenwerke.scheduler.client.scheduler.dto.history;

import com.google.gwt.core.client.GWT;
import java.lang.IllegalStateException;
import java.lang.Long;
import java.lang.String;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.gf.base.client.dtogenerator.RsDto;
import net.datenwerke.gxtdto.client.dtomanager.Dto2PosoMapper;
import net.datenwerke.gxtdto.client.dtomanager.Dto;
import net.datenwerke.gxtdto.client.dtomanager.DtoView;
import net.datenwerke.gxtdto.client.dtomanager.IdedDto;
import net.datenwerke.gxtdto.client.dtomanager.PropertyAccessor;
import net.datenwerke.gxtdto.client.dtomanager.dtomod.collections.ChangeMonitoredSet;
import net.datenwerke.gxtdto.client.dtomanager.dtomod.collections.MonitoredCollection;
import net.datenwerke.gxtdto.client.dtomanager.redoundo.ChangeTracker;
import net.datenwerke.gxtdto.client.eventbus.events.ObjectChangedEvent;
import net.datenwerke.gxtdto.client.eventbus.handlers.ObjectChangedEventHandler;
import net.datenwerke.gxtdto.client.eventbus.handlers.has.HasObjectChangedEventHandler;
import net.datenwerke.scheduler.client.scheduler.dto.OutcomeDto;
import net.datenwerke.scheduler.client.scheduler.dto.history.HistoryEntryPropertyDto;
import net.datenwerke.scheduler.client.scheduler.dto.history.pa.JobEntryDtoPA;
import net.datenwerke.scheduler.client.scheduler.dto.history.posomap.JobEntryDto2PosoMap;
import net.datenwerke.scheduler.service.scheduler.entities.history.JobEntry;

/**
 * Dto for {@link JobEntry}
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class JobEntryDto extends RsDto implements IdedDto {


	private static final long serialVersionUID = 1;

	private Long dtoId;


	/* Fields */
	private String errorDescription;
	private  boolean errorDescription_m;
	public static final String PROPERTY_ERROR_DESCRIPTION = "dpi-jobentry-errordescription";

	private transient static PropertyAccessor<JobEntryDto, String> errorDescription_pa = new PropertyAccessor<JobEntryDto, String>() {
		@Override
		public void setValue(JobEntryDto container, String object) {
			container.setErrorDescription(object);
		}

		@Override
		public String getValue(JobEntryDto container) {
			return container.getErrorDescription();
		}

		@Override
		public Class<?> getType() {
			return String.class;
		}

		@Override
		public String getPath() {
			return "errorDescription";
		}

		@Override
		public void setModified(JobEntryDto container, boolean modified) {
			container.errorDescription_m = modified;
		}

		@Override
		public boolean isModified(JobEntryDto container) {
			return container.isErrorDescriptionModified();
		}
	};

	private Set<HistoryEntryPropertyDto> historyProperties;
	private  boolean historyProperties_m;
	public static final String PROPERTY_HISTORY_PROPERTIES = "dpi-jobentry-historyproperties";

	private transient static PropertyAccessor<JobEntryDto, Set<HistoryEntryPropertyDto>> historyProperties_pa = new PropertyAccessor<JobEntryDto, Set<HistoryEntryPropertyDto>>() {
		@Override
		public void setValue(JobEntryDto container, Set<HistoryEntryPropertyDto> object) {
			container.setHistoryProperties(object);
		}

		@Override
		public Set<HistoryEntryPropertyDto> getValue(JobEntryDto container) {
			return container.getHistoryProperties();
		}

		@Override
		public Class<?> getType() {
			return Set.class;
		}

		@Override
		public String getPath() {
			return "historyProperties";
		}

		@Override
		public void setModified(JobEntryDto container, boolean modified) {
			container.historyProperties_m = modified;
		}

		@Override
		public boolean isModified(JobEntryDto container) {
			return container.isHistoryPropertiesModified();
		}
	};

	private Long id;
	private  boolean id_m;
	public static final String PROPERTY_ID = "dpi-jobentry-id";

	private transient static PropertyAccessor<JobEntryDto, Long> id_pa = new PropertyAccessor<JobEntryDto, Long>() {
		@Override
		public void setValue(JobEntryDto container, Long object) {
			// id field
		}

		@Override
		public Long getValue(JobEntryDto container) {
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
		public void setModified(JobEntryDto container, boolean modified) {
			container.id_m = modified;
		}

		@Override
		public boolean isModified(JobEntryDto container) {
			return container.isIdModified();
		}
	};

	private OutcomeDto outcome;
	private  boolean outcome_m;
	public static final String PROPERTY_OUTCOME = "dpi-jobentry-outcome";

	private transient static PropertyAccessor<JobEntryDto, OutcomeDto> outcome_pa = new PropertyAccessor<JobEntryDto, OutcomeDto>() {
		@Override
		public void setValue(JobEntryDto container, OutcomeDto object) {
			container.setOutcome(object);
		}

		@Override
		public OutcomeDto getValue(JobEntryDto container) {
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
		public void setModified(JobEntryDto container, boolean modified) {
			container.outcome_m = modified;
		}

		@Override
		public boolean isModified(JobEntryDto container) {
			return container.isOutcomeModified();
		}
	};


	public JobEntryDto() {
		super();
	}

	public String getErrorDescription()  {
		if(! isDtoProxy()){
			return this.errorDescription;
		}

		if(isErrorDescriptionModified())
			return this.errorDescription;

		if(! GWT.isClient())
			return null;

		String _value = dtoManager.getProperty(this, instantiatePropertyAccess().errorDescription());

		return _value;
	}


	public void setErrorDescription(String errorDescription)  {
		/* old value */
		String oldValue = null;
		if(GWT.isClient())
			oldValue = getErrorDescription();

		/* set new value */
		this.errorDescription = errorDescription;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(errorDescription_pa, oldValue, errorDescription, this.errorDescription_m));

		/* set indicator */
		this.errorDescription_m = true;

		this.fireObjectChangedEvent(JobEntryDtoPA.INSTANCE.errorDescription(), oldValue);
	}


	public boolean isErrorDescriptionModified()  {
		return errorDescription_m;
	}


	public static PropertyAccessor<JobEntryDto, String> getErrorDescriptionPropertyAccessor()  {
		return errorDescription_pa;
	}


	public Set<HistoryEntryPropertyDto> getHistoryProperties()  {
		if(! isDtoProxy()){
			Set<HistoryEntryPropertyDto> _currentValue = this.historyProperties;
			if(null == _currentValue)
				this.historyProperties = new HashSet<HistoryEntryPropertyDto>();

			return this.historyProperties;
		}

		if(isHistoryPropertiesModified())
			return this.historyProperties;

		if(! GWT.isClient())
			return null;

		Set<HistoryEntryPropertyDto> _value = dtoManager.getProperty(this, instantiatePropertyAccess().historyProperties());

		_value = new ChangeMonitoredSet<HistoryEntryPropertyDto>(_value);
		if(_value instanceof HasObjectChangedEventHandler){
			((HasObjectChangedEventHandler)_value).addObjectChangedHandler(new net.datenwerke.gxtdto.client.eventbus.handlers.ObjectChangedEventHandler(){
				@Override
				public void onObjectChangedEvent(net.datenwerke.gxtdto.client.eventbus.events.ObjectChangedEvent event){
					if(! isHistoryPropertiesModified())
						setHistoryProperties((Set<HistoryEntryPropertyDto>) ((MonitoredCollection) event.getObject()).getUnderlyingCollection());
				}
			}
			);
		}
		return _value;
	}


	public void setHistoryProperties(Set<HistoryEntryPropertyDto> historyProperties)  {
		/* old value */
		Set<HistoryEntryPropertyDto> oldValue = null;
		if(GWT.isClient())
			oldValue = getHistoryProperties();

		/* set new value */
		this.historyProperties = historyProperties;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(historyProperties_pa, oldValue, historyProperties, this.historyProperties_m));

		/* set indicator */
		this.historyProperties_m = true;

		this.fireObjectChangedEvent(JobEntryDtoPA.INSTANCE.historyProperties(), oldValue);
	}


	public boolean isHistoryPropertiesModified()  {
		return historyProperties_m;
	}


	public static PropertyAccessor<JobEntryDto, Set<HistoryEntryPropertyDto>> getHistoryPropertiesPropertyAccessor()  {
		return historyProperties_pa;
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


	public static PropertyAccessor<JobEntryDto, Long> getIdPropertyAccessor()  {
		return id_pa;
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

		this.fireObjectChangedEvent(JobEntryDtoPA.INSTANCE.outcome(), oldValue);
	}


	public boolean isOutcomeModified()  {
		return outcome_m;
	}


	public static PropertyAccessor<JobEntryDto, OutcomeDto> getOutcomePropertyAccessor()  {
		return outcome_pa;
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
		if(! (obj instanceof JobEntryDto))
			return false;

		if(null == getId())
			return super.equals(obj);
		return getId().equals(((JobEntryDto)obj).getId());
	}

	@Override
	public String toString()  {
		return getClass().getName() + ": " + getId();
	}

	public static Dto2PosoMapper newPosoMapper()  {
		return new JobEntryDto2PosoMap();
	}

	public JobEntryDtoPA instantiatePropertyAccess()  {
		return GWT.create(JobEntryDtoPA.class);
	}

	public void clearModified()  {
		this.errorDescription = null;
		this.errorDescription_m = false;
		this.historyProperties = null;
		this.historyProperties_m = false;
		this.id = null;
		this.id_m = false;
		this.outcome = null;
		this.outcome_m = false;
	}


	public boolean isModified()  {
		if(super.isModified())
			return true;
		if(errorDescription_m)
			return true;
		if(historyProperties_m)
			return true;
		if(id_m)
			return true;
		if(outcome_m)
			return true;
		return false;
	}


	public List<PropertyAccessor> getPropertyAccessors()  {
		List<PropertyAccessor> list = super.getPropertyAccessors();
		list.add(errorDescription_pa);
		list.add(historyProperties_pa);
		list.add(id_pa);
		list.add(outcome_pa);
		return list;
	}


	public List<PropertyAccessor> getModifiedPropertyAccessors()  {
		List<PropertyAccessor> list = super.getModifiedPropertyAccessors();
		if(errorDescription_m)
			list.add(errorDescription_pa);
		if(historyProperties_m)
			list.add(historyProperties_pa);
		if(id_m)
			list.add(id_pa);
		if(outcome_m)
			list.add(outcome_pa);
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsByView(net.datenwerke.gxtdto.client.dtomanager.DtoView view)  {
		List<PropertyAccessor> list = super.getPropertyAccessorsByView(view);
		if(view.compareTo(DtoView.MINIMAL) >= 0){
			list.add(id_pa);
		}
		if(view.compareTo(DtoView.NORMAL) >= 0){
			list.add(errorDescription_pa);
			list.add(historyProperties_pa);
			list.add(outcome_pa);
		}
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsForDtos()  {
		List<PropertyAccessor> list = super.getPropertyAccessorsForDtos();
		list.add(historyProperties_pa);
		return list;
	}



	net.datenwerke.scheduler.client.scheduler.dto.history.HistoryEntryPropertyDto wl_0;
	net.datenwerke.scheduler.client.scheduler.dto.OutcomeDto wl_1;

}
