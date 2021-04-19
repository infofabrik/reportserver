package net.datenwerke.scheduler.client.scheduler.dto.config.complex;

import com.google.gwt.core.client.GWT;
import java.lang.Integer;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.gxtdto.client.dtomanager.Dto2PosoMapper;
import net.datenwerke.gxtdto.client.dtomanager.Dto;
import net.datenwerke.gxtdto.client.dtomanager.DtoView;
import net.datenwerke.gxtdto.client.dtomanager.PropertyAccessor;
import net.datenwerke.gxtdto.client.dtomanager.dtomod.collections.ChangeMonitoredSet;
import net.datenwerke.gxtdto.client.dtomanager.dtomod.collections.MonitoredCollection;
import net.datenwerke.gxtdto.client.dtomanager.redoundo.ChangeTracker;
import net.datenwerke.gxtdto.client.eventbus.events.ObjectChangedEvent;
import net.datenwerke.gxtdto.client.eventbus.handlers.ObjectChangedEventHandler;
import net.datenwerke.gxtdto.client.eventbus.handlers.has.HasObjectChangedEventHandler;
import net.datenwerke.scheduler.client.scheduler.dto.config.complex.DateTriggerConfigDto;
import net.datenwerke.scheduler.client.scheduler.dto.config.complex.DaysDto;
import net.datenwerke.scheduler.client.scheduler.dto.config.complex.pa.WeeklyConfigDtoPA;
import net.datenwerke.scheduler.client.scheduler.dto.config.complex.posomap.WeeklyConfigDto2PosoMap;
import net.datenwerke.scheduler.service.scheduler.triggers.complex.config.WeeklyConfig;

/**
 * Dto for {@link WeeklyConfig}
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class WeeklyConfigDto extends DateTriggerConfigDto {


	private static final long serialVersionUID = 1;


	/* Fields */
	private Set<DaysDto> weeklyDays;
	private  boolean weeklyDays_m;
	public static final String PROPERTY_WEEKLY_DAYS = "dpi-weeklyconfig-weeklydays";

	private transient static PropertyAccessor<WeeklyConfigDto, Set<DaysDto>> weeklyDays_pa = new PropertyAccessor<WeeklyConfigDto, Set<DaysDto>>() {
		@Override
		public void setValue(WeeklyConfigDto container, Set<DaysDto> object) {
			container.setWeeklyDays(object);
		}

		@Override
		public Set<DaysDto> getValue(WeeklyConfigDto container) {
			return container.getWeeklyDays();
		}

		@Override
		public Class<?> getType() {
			return Set.class;
		}

		@Override
		public String getPath() {
			return "weeklyDays";
		}

		@Override
		public void setModified(WeeklyConfigDto container, boolean modified) {
			container.weeklyDays_m = modified;
		}

		@Override
		public boolean isModified(WeeklyConfigDto container) {
			return container.isWeeklyDaysModified();
		}
	};

	private Integer weeklyN;
	private  boolean weeklyN_m;
	public static final String PROPERTY_WEEKLY_N = "dpi-weeklyconfig-weeklyn";

	private transient static PropertyAccessor<WeeklyConfigDto, Integer> weeklyN_pa = new PropertyAccessor<WeeklyConfigDto, Integer>() {
		@Override
		public void setValue(WeeklyConfigDto container, Integer object) {
			container.setWeeklyN(object);
		}

		@Override
		public Integer getValue(WeeklyConfigDto container) {
			return container.getWeeklyN();
		}

		@Override
		public Class<?> getType() {
			return Integer.class;
		}

		@Override
		public String getPath() {
			return "weeklyN";
		}

		@Override
		public void setModified(WeeklyConfigDto container, boolean modified) {
			container.weeklyN_m = modified;
		}

		@Override
		public boolean isModified(WeeklyConfigDto container) {
			return container.isWeeklyNModified();
		}
	};


	public WeeklyConfigDto() {
		super();
	}

	public Set<DaysDto> getWeeklyDays()  {
		if(! isDtoProxy()){
			Set<DaysDto> _currentValue = this.weeklyDays;
			if(null == _currentValue)
				this.weeklyDays = new HashSet<DaysDto>();

			return this.weeklyDays;
		}

		if(isWeeklyDaysModified())
			return this.weeklyDays;

		if(! GWT.isClient())
			return null;

		Set<DaysDto> _value = dtoManager.getProperty(this, instantiatePropertyAccess().weeklyDays());

		_value = new ChangeMonitoredSet<DaysDto>(_value);
		if(_value instanceof HasObjectChangedEventHandler){
			((HasObjectChangedEventHandler)_value).addObjectChangedHandler(new net.datenwerke.gxtdto.client.eventbus.handlers.ObjectChangedEventHandler(){
				@Override
				public void onObjectChangedEvent(net.datenwerke.gxtdto.client.eventbus.events.ObjectChangedEvent event){
					if(! isWeeklyDaysModified())
						setWeeklyDays((Set<DaysDto>) ((MonitoredCollection) event.getObject()).getUnderlyingCollection());
				}
			}
			);
		}
		return _value;
	}


	public void setWeeklyDays(Set<DaysDto> weeklyDays)  {
		/* old value */
		Set<DaysDto> oldValue = null;
		if(GWT.isClient())
			oldValue = getWeeklyDays();

		/* set new value */
		this.weeklyDays = weeklyDays;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(weeklyDays_pa, oldValue, weeklyDays, this.weeklyDays_m));

		/* set indicator */
		this.weeklyDays_m = true;

		this.fireObjectChangedEvent(WeeklyConfigDtoPA.INSTANCE.weeklyDays(), oldValue);
	}


	public boolean isWeeklyDaysModified()  {
		return weeklyDays_m;
	}


	public static PropertyAccessor<WeeklyConfigDto, Set<DaysDto>> getWeeklyDaysPropertyAccessor()  {
		return weeklyDays_pa;
	}


	public Integer getWeeklyN()  {
		if(! isDtoProxy()){
			return this.weeklyN;
		}

		if(isWeeklyNModified())
			return this.weeklyN;

		if(! GWT.isClient())
			return null;

		Integer _value = dtoManager.getProperty(this, instantiatePropertyAccess().weeklyN());

		return _value;
	}


	public void setWeeklyN(Integer weeklyN)  {
		/* old value */
		Integer oldValue = null;
		if(GWT.isClient())
			oldValue = getWeeklyN();

		/* set new value */
		this.weeklyN = weeklyN;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(weeklyN_pa, oldValue, weeklyN, this.weeklyN_m));

		/* set indicator */
		this.weeklyN_m = true;

		this.fireObjectChangedEvent(WeeklyConfigDtoPA.INSTANCE.weeklyN(), oldValue);
	}


	public boolean isWeeklyNModified()  {
		return weeklyN_m;
	}


	public static PropertyAccessor<WeeklyConfigDto, Integer> getWeeklyNPropertyAccessor()  {
		return weeklyN_pa;
	}


	@Override
	public int hashCode()  {
		if(null == getId())
			return super.hashCode();
		return getId().hashCode();
	}

	@Override
	public boolean equals(Object obj)  {
		if(! (obj instanceof WeeklyConfigDto))
			return false;

		if(null == getId())
			return super.equals(obj);
		return getId().equals(((WeeklyConfigDto)obj).getId());
	}

	@Override
	public String toString()  {
		return getClass().getName() + ": " + getId();
	}

	public static Dto2PosoMapper newPosoMapper()  {
		return new WeeklyConfigDto2PosoMap();
	}

	public WeeklyConfigDtoPA instantiatePropertyAccess()  {
		return GWT.create(WeeklyConfigDtoPA.class);
	}

	public void clearModified()  {
		this.weeklyDays = null;
		this.weeklyDays_m = false;
		this.weeklyN = null;
		this.weeklyN_m = false;
	}


	public boolean isModified()  {
		if(super.isModified())
			return true;
		if(weeklyDays_m)
			return true;
		if(weeklyN_m)
			return true;
		return false;
	}


	public List<PropertyAccessor> getPropertyAccessors()  {
		List<PropertyAccessor> list = super.getPropertyAccessors();
		list.add(weeklyDays_pa);
		list.add(weeklyN_pa);
		return list;
	}


	public List<PropertyAccessor> getModifiedPropertyAccessors()  {
		List<PropertyAccessor> list = super.getModifiedPropertyAccessors();
		if(weeklyDays_m)
			list.add(weeklyDays_pa);
		if(weeklyN_m)
			list.add(weeklyN_pa);
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsByView(net.datenwerke.gxtdto.client.dtomanager.DtoView view)  {
		List<PropertyAccessor> list = super.getPropertyAccessorsByView(view);
		if(view.compareTo(DtoView.NORMAL) >= 0){
			list.add(weeklyDays_pa);
			list.add(weeklyN_pa);
		}
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsForDtos()  {
		List<PropertyAccessor> list = super.getPropertyAccessorsForDtos();
		list.add(weeklyDays_pa);
		return list;
	}



	net.datenwerke.scheduler.client.scheduler.dto.config.complex.DaysDto wl_0;

}
