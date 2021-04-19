package net.datenwerke.scheduler.client.scheduler.dto.config.complex;

import com.google.gwt.core.client.GWT;
import java.lang.Integer;
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
import net.datenwerke.scheduler.client.scheduler.dto.config.complex.NthDto;
import net.datenwerke.scheduler.client.scheduler.dto.config.complex.pa.MonthlyNthDayOfWeekConfigDtoPA;
import net.datenwerke.scheduler.client.scheduler.dto.config.complex.posomap.MonthlyNthDayOfWeekConfigDto2PosoMap;
import net.datenwerke.scheduler.service.scheduler.triggers.complex.config.MonthlyNthDayOfWeekConfig;

/**
 * Dto for {@link MonthlyNthDayOfWeekConfig}
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class MonthlyNthDayOfWeekConfigDto extends DateTriggerConfigDto {


	private static final long serialVersionUID = 1;


	/* Fields */
	private Integer month;
	private  boolean month_m;
	public static final String PROPERTY_MONTH = "dpi-monthlynthdayofweekconfig-month";

	private transient static PropertyAccessor<MonthlyNthDayOfWeekConfigDto, Integer> month_pa = new PropertyAccessor<MonthlyNthDayOfWeekConfigDto, Integer>() {
		@Override
		public void setValue(MonthlyNthDayOfWeekConfigDto container, Integer object) {
			container.setMonth(object);
		}

		@Override
		public Integer getValue(MonthlyNthDayOfWeekConfigDto container) {
			return container.getMonth();
		}

		@Override
		public Class<?> getType() {
			return Integer.class;
		}

		@Override
		public String getPath() {
			return "month";
		}

		@Override
		public void setModified(MonthlyNthDayOfWeekConfigDto container, boolean modified) {
			container.month_m = modified;
		}

		@Override
		public boolean isModified(MonthlyNthDayOfWeekConfigDto container) {
			return container.isMonthModified();
		}
	};

	private DaysDto monthlyDay;
	private  boolean monthlyDay_m;
	public static final String PROPERTY_MONTHLY_DAY = "dpi-monthlynthdayofweekconfig-monthlyday";

	private transient static PropertyAccessor<MonthlyNthDayOfWeekConfigDto, DaysDto> monthlyDay_pa = new PropertyAccessor<MonthlyNthDayOfWeekConfigDto, DaysDto>() {
		@Override
		public void setValue(MonthlyNthDayOfWeekConfigDto container, DaysDto object) {
			container.setMonthlyDay(object);
		}

		@Override
		public DaysDto getValue(MonthlyNthDayOfWeekConfigDto container) {
			return container.getMonthlyDay();
		}

		@Override
		public Class<?> getType() {
			return DaysDto.class;
		}

		@Override
		public String getPath() {
			return "monthlyDay";
		}

		@Override
		public void setModified(MonthlyNthDayOfWeekConfigDto container, boolean modified) {
			container.monthlyDay_m = modified;
		}

		@Override
		public boolean isModified(MonthlyNthDayOfWeekConfigDto container) {
			return container.isMonthlyDayModified();
		}
	};

	private NthDto monthlyNth;
	private  boolean monthlyNth_m;
	public static final String PROPERTY_MONTHLY_NTH = "dpi-monthlynthdayofweekconfig-monthlynth";

	private transient static PropertyAccessor<MonthlyNthDayOfWeekConfigDto, NthDto> monthlyNth_pa = new PropertyAccessor<MonthlyNthDayOfWeekConfigDto, NthDto>() {
		@Override
		public void setValue(MonthlyNthDayOfWeekConfigDto container, NthDto object) {
			container.setMonthlyNth(object);
		}

		@Override
		public NthDto getValue(MonthlyNthDayOfWeekConfigDto container) {
			return container.getMonthlyNth();
		}

		@Override
		public Class<?> getType() {
			return NthDto.class;
		}

		@Override
		public String getPath() {
			return "monthlyNth";
		}

		@Override
		public void setModified(MonthlyNthDayOfWeekConfigDto container, boolean modified) {
			container.monthlyNth_m = modified;
		}

		@Override
		public boolean isModified(MonthlyNthDayOfWeekConfigDto container) {
			return container.isMonthlyNthModified();
		}
	};


	public MonthlyNthDayOfWeekConfigDto() {
		super();
	}

	public Integer getMonth()  {
		if(! isDtoProxy()){
			return this.month;
		}

		if(isMonthModified())
			return this.month;

		if(! GWT.isClient())
			return null;

		Integer _value = dtoManager.getProperty(this, instantiatePropertyAccess().month());

		return _value;
	}


	public void setMonth(Integer month)  {
		/* old value */
		Integer oldValue = null;
		if(GWT.isClient())
			oldValue = getMonth();

		/* set new value */
		this.month = month;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(month_pa, oldValue, month, this.month_m));

		/* set indicator */
		this.month_m = true;

		this.fireObjectChangedEvent(MonthlyNthDayOfWeekConfigDtoPA.INSTANCE.month(), oldValue);
	}


	public boolean isMonthModified()  {
		return month_m;
	}


	public static PropertyAccessor<MonthlyNthDayOfWeekConfigDto, Integer> getMonthPropertyAccessor()  {
		return month_pa;
	}


	public DaysDto getMonthlyDay()  {
		if(! isDtoProxy()){
			return this.monthlyDay;
		}

		if(isMonthlyDayModified())
			return this.monthlyDay;

		if(! GWT.isClient())
			return null;

		DaysDto _value = dtoManager.getProperty(this, instantiatePropertyAccess().monthlyDay());

		return _value;
	}


	public void setMonthlyDay(DaysDto monthlyDay)  {
		/* old value */
		DaysDto oldValue = null;
		if(GWT.isClient())
			oldValue = getMonthlyDay();

		/* set new value */
		this.monthlyDay = monthlyDay;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(monthlyDay_pa, oldValue, monthlyDay, this.monthlyDay_m));

		/* set indicator */
		this.monthlyDay_m = true;

		this.fireObjectChangedEvent(MonthlyNthDayOfWeekConfigDtoPA.INSTANCE.monthlyDay(), oldValue);
	}


	public boolean isMonthlyDayModified()  {
		return monthlyDay_m;
	}


	public static PropertyAccessor<MonthlyNthDayOfWeekConfigDto, DaysDto> getMonthlyDayPropertyAccessor()  {
		return monthlyDay_pa;
	}


	public NthDto getMonthlyNth()  {
		if(! isDtoProxy()){
			return this.monthlyNth;
		}

		if(isMonthlyNthModified())
			return this.monthlyNth;

		if(! GWT.isClient())
			return null;

		NthDto _value = dtoManager.getProperty(this, instantiatePropertyAccess().monthlyNth());

		return _value;
	}


	public void setMonthlyNth(NthDto monthlyNth)  {
		/* old value */
		NthDto oldValue = null;
		if(GWT.isClient())
			oldValue = getMonthlyNth();

		/* set new value */
		this.monthlyNth = monthlyNth;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(monthlyNth_pa, oldValue, monthlyNth, this.monthlyNth_m));

		/* set indicator */
		this.monthlyNth_m = true;

		this.fireObjectChangedEvent(MonthlyNthDayOfWeekConfigDtoPA.INSTANCE.monthlyNth(), oldValue);
	}


	public boolean isMonthlyNthModified()  {
		return monthlyNth_m;
	}


	public static PropertyAccessor<MonthlyNthDayOfWeekConfigDto, NthDto> getMonthlyNthPropertyAccessor()  {
		return monthlyNth_pa;
	}


	@Override
	public int hashCode()  {
		if(null == getId())
			return super.hashCode();
		return getId().hashCode();
	}

	@Override
	public boolean equals(Object obj)  {
		if(! (obj instanceof MonthlyNthDayOfWeekConfigDto))
			return false;

		if(null == getId())
			return super.equals(obj);
		return getId().equals(((MonthlyNthDayOfWeekConfigDto)obj).getId());
	}

	@Override
	public String toString()  {
		return getClass().getName() + ": " + getId();
	}

	public static Dto2PosoMapper newPosoMapper()  {
		return new MonthlyNthDayOfWeekConfigDto2PosoMap();
	}

	public MonthlyNthDayOfWeekConfigDtoPA instantiatePropertyAccess()  {
		return GWT.create(MonthlyNthDayOfWeekConfigDtoPA.class);
	}

	public void clearModified()  {
		this.month = null;
		this.month_m = false;
		this.monthlyDay = null;
		this.monthlyDay_m = false;
		this.monthlyNth = null;
		this.monthlyNth_m = false;
	}


	public boolean isModified()  {
		if(super.isModified())
			return true;
		if(month_m)
			return true;
		if(monthlyDay_m)
			return true;
		if(monthlyNth_m)
			return true;
		return false;
	}


	public List<PropertyAccessor> getPropertyAccessors()  {
		List<PropertyAccessor> list = super.getPropertyAccessors();
		list.add(month_pa);
		list.add(monthlyDay_pa);
		list.add(monthlyNth_pa);
		return list;
	}


	public List<PropertyAccessor> getModifiedPropertyAccessors()  {
		List<PropertyAccessor> list = super.getModifiedPropertyAccessors();
		if(month_m)
			list.add(month_pa);
		if(monthlyDay_m)
			list.add(monthlyDay_pa);
		if(monthlyNth_m)
			list.add(monthlyNth_pa);
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsByView(net.datenwerke.gxtdto.client.dtomanager.DtoView view)  {
		List<PropertyAccessor> list = super.getPropertyAccessorsByView(view);
		if(view.compareTo(DtoView.NORMAL) >= 0){
			list.add(month_pa);
			list.add(monthlyDay_pa);
			list.add(monthlyNth_pa);
		}
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsForDtos()  {
		List<PropertyAccessor> list = super.getPropertyAccessorsForDtos();
		return list;
	}



	net.datenwerke.scheduler.client.scheduler.dto.config.complex.NthDto wl_0;
	net.datenwerke.scheduler.client.scheduler.dto.config.complex.DaysDto wl_1;

}
