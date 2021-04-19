package net.datenwerke.scheduler.client.scheduler.dto.config.complex;

import com.google.gwt.core.client.GWT;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.gxtdto.client.dtomanager.Dto2PosoMapper;
import net.datenwerke.gxtdto.client.dtomanager.DtoView;
import net.datenwerke.gxtdto.client.dtomanager.PropertyAccessor;
import net.datenwerke.gxtdto.client.dtomanager.redoundo.ChangeTracker;
import net.datenwerke.scheduler.client.scheduler.dto.config.complex.DateTriggerConfigDto;
import net.datenwerke.scheduler.client.scheduler.dto.config.complex.DaysDto;
import net.datenwerke.scheduler.client.scheduler.dto.config.complex.MonthsDto;
import net.datenwerke.scheduler.client.scheduler.dto.config.complex.NthDto;
import net.datenwerke.scheduler.client.scheduler.dto.config.complex.pa.YearlyNthDayOfWeekConfigDtoPA;
import net.datenwerke.scheduler.client.scheduler.dto.config.complex.posomap.YearlyNthDayOfWeekConfigDto2PosoMap;
import net.datenwerke.scheduler.service.scheduler.triggers.complex.config.YearlyNthDayOfWeekConfig;

/**
 * Dto for {@link YearlyNthDayOfWeekConfig}
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class YearlyNthDayOfWeekConfigDto extends DateTriggerConfigDto {


	private static final long serialVersionUID = 1;


	/* Fields */
	private DaysDto yearlyDay;
	private  boolean yearlyDay_m;
	public static final String PROPERTY_YEARLY_DAY = "dpi-yearlynthdayofweekconfig-yearlyday";

	private transient static PropertyAccessor<YearlyNthDayOfWeekConfigDto, DaysDto> yearlyDay_pa = new PropertyAccessor<YearlyNthDayOfWeekConfigDto, DaysDto>() {
		@Override
		public void setValue(YearlyNthDayOfWeekConfigDto container, DaysDto object) {
			container.setYearlyDay(object);
		}

		@Override
		public DaysDto getValue(YearlyNthDayOfWeekConfigDto container) {
			return container.getYearlyDay();
		}

		@Override
		public Class<?> getType() {
			return DaysDto.class;
		}

		@Override
		public String getPath() {
			return "yearlyDay";
		}

		@Override
		public void setModified(YearlyNthDayOfWeekConfigDto container, boolean modified) {
			container.yearlyDay_m = modified;
		}

		@Override
		public boolean isModified(YearlyNthDayOfWeekConfigDto container) {
			return container.isYearlyDayModified();
		}
	};

	private MonthsDto yearlyMonth;
	private  boolean yearlyMonth_m;
	public static final String PROPERTY_YEARLY_MONTH = "dpi-yearlynthdayofweekconfig-yearlymonth";

	private transient static PropertyAccessor<YearlyNthDayOfWeekConfigDto, MonthsDto> yearlyMonth_pa = new PropertyAccessor<YearlyNthDayOfWeekConfigDto, MonthsDto>() {
		@Override
		public void setValue(YearlyNthDayOfWeekConfigDto container, MonthsDto object) {
			container.setYearlyMonth(object);
		}

		@Override
		public MonthsDto getValue(YearlyNthDayOfWeekConfigDto container) {
			return container.getYearlyMonth();
		}

		@Override
		public Class<?> getType() {
			return MonthsDto.class;
		}

		@Override
		public String getPath() {
			return "yearlyMonth";
		}

		@Override
		public void setModified(YearlyNthDayOfWeekConfigDto container, boolean modified) {
			container.yearlyMonth_m = modified;
		}

		@Override
		public boolean isModified(YearlyNthDayOfWeekConfigDto container) {
			return container.isYearlyMonthModified();
		}
	};

	private NthDto yearlyNth;
	private  boolean yearlyNth_m;
	public static final String PROPERTY_YEARLY_NTH = "dpi-yearlynthdayofweekconfig-yearlynth";

	private transient static PropertyAccessor<YearlyNthDayOfWeekConfigDto, NthDto> yearlyNth_pa = new PropertyAccessor<YearlyNthDayOfWeekConfigDto, NthDto>() {
		@Override
		public void setValue(YearlyNthDayOfWeekConfigDto container, NthDto object) {
			container.setYearlyNth(object);
		}

		@Override
		public NthDto getValue(YearlyNthDayOfWeekConfigDto container) {
			return container.getYearlyNth();
		}

		@Override
		public Class<?> getType() {
			return NthDto.class;
		}

		@Override
		public String getPath() {
			return "yearlyNth";
		}

		@Override
		public void setModified(YearlyNthDayOfWeekConfigDto container, boolean modified) {
			container.yearlyNth_m = modified;
		}

		@Override
		public boolean isModified(YearlyNthDayOfWeekConfigDto container) {
			return container.isYearlyNthModified();
		}
	};


	public YearlyNthDayOfWeekConfigDto() {
		super();
	}

	public DaysDto getYearlyDay()  {
		if(! isDtoProxy()){
			return this.yearlyDay;
		}

		if(isYearlyDayModified())
			return this.yearlyDay;

		if(! GWT.isClient())
			return null;

		DaysDto _value = dtoManager.getProperty(this, instantiatePropertyAccess().yearlyDay());

		return _value;
	}


	public void setYearlyDay(DaysDto yearlyDay)  {
		/* old value */
		DaysDto oldValue = null;
		if(GWT.isClient())
			oldValue = getYearlyDay();

		/* set new value */
		this.yearlyDay = yearlyDay;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(yearlyDay_pa, oldValue, yearlyDay, this.yearlyDay_m));

		/* set indicator */
		this.yearlyDay_m = true;

		this.fireObjectChangedEvent(YearlyNthDayOfWeekConfigDtoPA.INSTANCE.yearlyDay(), oldValue);
	}


	public boolean isYearlyDayModified()  {
		return yearlyDay_m;
	}


	public static PropertyAccessor<YearlyNthDayOfWeekConfigDto, DaysDto> getYearlyDayPropertyAccessor()  {
		return yearlyDay_pa;
	}


	public MonthsDto getYearlyMonth()  {
		if(! isDtoProxy()){
			return this.yearlyMonth;
		}

		if(isYearlyMonthModified())
			return this.yearlyMonth;

		if(! GWT.isClient())
			return null;

		MonthsDto _value = dtoManager.getProperty(this, instantiatePropertyAccess().yearlyMonth());

		return _value;
	}


	public void setYearlyMonth(MonthsDto yearlyMonth)  {
		/* old value */
		MonthsDto oldValue = null;
		if(GWT.isClient())
			oldValue = getYearlyMonth();

		/* set new value */
		this.yearlyMonth = yearlyMonth;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(yearlyMonth_pa, oldValue, yearlyMonth, this.yearlyMonth_m));

		/* set indicator */
		this.yearlyMonth_m = true;

		this.fireObjectChangedEvent(YearlyNthDayOfWeekConfigDtoPA.INSTANCE.yearlyMonth(), oldValue);
	}


	public boolean isYearlyMonthModified()  {
		return yearlyMonth_m;
	}


	public static PropertyAccessor<YearlyNthDayOfWeekConfigDto, MonthsDto> getYearlyMonthPropertyAccessor()  {
		return yearlyMonth_pa;
	}


	public NthDto getYearlyNth()  {
		if(! isDtoProxy()){
			return this.yearlyNth;
		}

		if(isYearlyNthModified())
			return this.yearlyNth;

		if(! GWT.isClient())
			return null;

		NthDto _value = dtoManager.getProperty(this, instantiatePropertyAccess().yearlyNth());

		return _value;
	}


	public void setYearlyNth(NthDto yearlyNth)  {
		/* old value */
		NthDto oldValue = null;
		if(GWT.isClient())
			oldValue = getYearlyNth();

		/* set new value */
		this.yearlyNth = yearlyNth;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(yearlyNth_pa, oldValue, yearlyNth, this.yearlyNth_m));

		/* set indicator */
		this.yearlyNth_m = true;

		this.fireObjectChangedEvent(YearlyNthDayOfWeekConfigDtoPA.INSTANCE.yearlyNth(), oldValue);
	}


	public boolean isYearlyNthModified()  {
		return yearlyNth_m;
	}


	public static PropertyAccessor<YearlyNthDayOfWeekConfigDto, NthDto> getYearlyNthPropertyAccessor()  {
		return yearlyNth_pa;
	}


	@Override
	public int hashCode()  {
		if(null == getId())
			return super.hashCode();
		return getId().hashCode();
	}

	@Override
	public boolean equals(Object obj)  {
		if(! (obj instanceof YearlyNthDayOfWeekConfigDto))
			return false;

		if(null == getId())
			return super.equals(obj);
		return getId().equals(((YearlyNthDayOfWeekConfigDto)obj).getId());
	}

	@Override
	public String toString()  {
		return getClass().getName() + ": " + getId();
	}

	public static Dto2PosoMapper newPosoMapper()  {
		return new YearlyNthDayOfWeekConfigDto2PosoMap();
	}

	public YearlyNthDayOfWeekConfigDtoPA instantiatePropertyAccess()  {
		return GWT.create(YearlyNthDayOfWeekConfigDtoPA.class);
	}

	public void clearModified()  {
		this.yearlyDay = null;
		this.yearlyDay_m = false;
		this.yearlyMonth = null;
		this.yearlyMonth_m = false;
		this.yearlyNth = null;
		this.yearlyNth_m = false;
	}


	public boolean isModified()  {
		if(super.isModified())
			return true;
		if(yearlyDay_m)
			return true;
		if(yearlyMonth_m)
			return true;
		if(yearlyNth_m)
			return true;
		return false;
	}


	public List<PropertyAccessor> getPropertyAccessors()  {
		List<PropertyAccessor> list = super.getPropertyAccessors();
		list.add(yearlyDay_pa);
		list.add(yearlyMonth_pa);
		list.add(yearlyNth_pa);
		return list;
	}


	public List<PropertyAccessor> getModifiedPropertyAccessors()  {
		List<PropertyAccessor> list = super.getModifiedPropertyAccessors();
		if(yearlyDay_m)
			list.add(yearlyDay_pa);
		if(yearlyMonth_m)
			list.add(yearlyMonth_pa);
		if(yearlyNth_m)
			list.add(yearlyNth_pa);
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsByView(net.datenwerke.gxtdto.client.dtomanager.DtoView view)  {
		List<PropertyAccessor> list = super.getPropertyAccessorsByView(view);
		if(view.compareTo(DtoView.NORMAL) >= 0){
			list.add(yearlyDay_pa);
			list.add(yearlyMonth_pa);
			list.add(yearlyNth_pa);
		}
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsForDtos()  {
		List<PropertyAccessor> list = super.getPropertyAccessorsForDtos();
		return list;
	}



	net.datenwerke.scheduler.client.scheduler.dto.config.complex.MonthsDto wl_0;
	net.datenwerke.scheduler.client.scheduler.dto.config.complex.NthDto wl_1;
	net.datenwerke.scheduler.client.scheduler.dto.config.complex.DaysDto wl_2;

}
