package net.datenwerke.scheduler.client.scheduler.dto.config.complex;

import com.google.gwt.core.client.GWT;
import java.lang.Integer;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.gf.base.client.dtogenerator.RsDto;
import net.datenwerke.gxtdto.client.dtomanager.Dto2PosoMapper;
import net.datenwerke.gxtdto.client.dtomanager.DtoView;
import net.datenwerke.gxtdto.client.dtomanager.PropertyAccessor;
import net.datenwerke.gxtdto.client.dtomanager.redoundo.ChangeTracker;
import net.datenwerke.scheduler.client.scheduler.dto.config.complex.pa.TimeDtoPA;
import net.datenwerke.scheduler.client.scheduler.dto.config.complex.posomap.TimeDto2PosoMap;
import net.datenwerke.scheduler.service.scheduler.triggers.complex.config.Time;

/**
 * Dto for {@link Time}
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
abstract public class TimeDto extends RsDto {


	private static final long serialVersionUID = 1;


	/* Fields */
	private Integer hour;
	private  boolean hour_m;
	public static final String PROPERTY_HOUR = "dpi-time-hour";

	private transient static PropertyAccessor<TimeDto, Integer> hour_pa = new PropertyAccessor<TimeDto, Integer>() {
		@Override
		public void setValue(TimeDto container, Integer object) {
			container.setHour(object);
		}

		@Override
		public Integer getValue(TimeDto container) {
			return container.getHour();
		}

		@Override
		public Class<?> getType() {
			return Integer.class;
		}

		@Override
		public String getPath() {
			return "hour";
		}

		@Override
		public void setModified(TimeDto container, boolean modified) {
			container.hour_m = modified;
		}

		@Override
		public boolean isModified(TimeDto container) {
			return container.isHourModified();
		}
	};

	private Integer minutes;
	private  boolean minutes_m;
	public static final String PROPERTY_MINUTES = "dpi-time-minutes";

	private transient static PropertyAccessor<TimeDto, Integer> minutes_pa = new PropertyAccessor<TimeDto, Integer>() {
		@Override
		public void setValue(TimeDto container, Integer object) {
			container.setMinutes(object);
		}

		@Override
		public Integer getValue(TimeDto container) {
			return container.getMinutes();
		}

		@Override
		public Class<?> getType() {
			return Integer.class;
		}

		@Override
		public String getPath() {
			return "minutes";
		}

		@Override
		public void setModified(TimeDto container, boolean modified) {
			container.minutes_m = modified;
		}

		@Override
		public boolean isModified(TimeDto container) {
			return container.isMinutesModified();
		}
	};


	public TimeDto() {
		super();
	}

	public Integer getHour()  {
		if(! isDtoProxy()){
			return this.hour;
		}

		if(isHourModified())
			return this.hour;

		if(! GWT.isClient())
			return null;

		Integer _value = dtoManager.getProperty(this, instantiatePropertyAccess().hour());

		return _value;
	}


	public void setHour(Integer hour)  {
		/* old value */
		Integer oldValue = null;
		if(GWT.isClient())
			oldValue = getHour();

		/* set new value */
		this.hour = hour;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(hour_pa, oldValue, hour, this.hour_m));

		/* set indicator */
		this.hour_m = true;

		this.fireObjectChangedEvent(TimeDtoPA.INSTANCE.hour(), oldValue);
	}


	public boolean isHourModified()  {
		return hour_m;
	}


	public static PropertyAccessor<TimeDto, Integer> getHourPropertyAccessor()  {
		return hour_pa;
	}


	public Integer getMinutes()  {
		if(! isDtoProxy()){
			return this.minutes;
		}

		if(isMinutesModified())
			return this.minutes;

		if(! GWT.isClient())
			return null;

		Integer _value = dtoManager.getProperty(this, instantiatePropertyAccess().minutes());

		return _value;
	}


	public void setMinutes(Integer minutes)  {
		/* old value */
		Integer oldValue = null;
		if(GWT.isClient())
			oldValue = getMinutes();

		/* set new value */
		this.minutes = minutes;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(minutes_pa, oldValue, minutes, this.minutes_m));

		/* set indicator */
		this.minutes_m = true;

		this.fireObjectChangedEvent(TimeDtoPA.INSTANCE.minutes(), oldValue);
	}


	public boolean isMinutesModified()  {
		return minutes_m;
	}


	public static PropertyAccessor<TimeDto, Integer> getMinutesPropertyAccessor()  {
		return minutes_pa;
	}


	@Override
	public String toString()  {
		return super.toString();
	}

	public static Dto2PosoMapper newPosoMapper()  {
		return new TimeDto2PosoMap();
	}

	public TimeDtoPA instantiatePropertyAccess()  {
		return GWT.create(TimeDtoPA.class);
	}

	public void clearModified()  {
		this.hour = null;
		this.hour_m = false;
		this.minutes = null;
		this.minutes_m = false;
	}


	public boolean isModified()  {
		if(super.isModified())
			return true;
		if(hour_m)
			return true;
		if(minutes_m)
			return true;
		return false;
	}


	public List<PropertyAccessor> getPropertyAccessors()  {
		List<PropertyAccessor> list = super.getPropertyAccessors();
		list.add(hour_pa);
		list.add(minutes_pa);
		return list;
	}


	public List<PropertyAccessor> getModifiedPropertyAccessors()  {
		List<PropertyAccessor> list = super.getModifiedPropertyAccessors();
		if(hour_m)
			list.add(hour_pa);
		if(minutes_m)
			list.add(minutes_pa);
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsByView(net.datenwerke.gxtdto.client.dtomanager.DtoView view)  {
		List<PropertyAccessor> list = super.getPropertyAccessorsByView(view);
		if(view.compareTo(DtoView.NORMAL) >= 0){
			list.add(hour_pa);
			list.add(minutes_pa);
		}
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsForDtos()  {
		List<PropertyAccessor> list = super.getPropertyAccessorsForDtos();
		return list;
	}




}
