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
import net.datenwerke.scheduler.client.scheduler.dto.config.complex.pa.MonthlyNthDayConfigDtoPA;
import net.datenwerke.scheduler.client.scheduler.dto.config.complex.posomap.MonthlyNthDayConfigDto2PosoMap;
import net.datenwerke.scheduler.service.scheduler.triggers.complex.config.MonthlyNthDayConfig;

/**
 * Dto for {@link MonthlyNthDayConfig}
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class MonthlyNthDayConfigDto extends DateTriggerConfigDto {


	private static final long serialVersionUID = 1;


	/* Fields */
	private Integer dayInMonth;
	private  boolean dayInMonth_m;
	public static final String PROPERTY_DAY_IN_MONTH = "dpi-monthlynthdayconfig-dayinmonth";

	private transient static PropertyAccessor<MonthlyNthDayConfigDto, Integer> dayInMonth_pa = new PropertyAccessor<MonthlyNthDayConfigDto, Integer>() {
		@Override
		public void setValue(MonthlyNthDayConfigDto container, Integer object) {
			container.setDayInMonth(object);
		}

		@Override
		public Integer getValue(MonthlyNthDayConfigDto container) {
			return container.getDayInMonth();
		}

		@Override
		public Class<?> getType() {
			return Integer.class;
		}

		@Override
		public String getPath() {
			return "dayInMonth";
		}

		@Override
		public void setModified(MonthlyNthDayConfigDto container, boolean modified) {
			container.dayInMonth_m = modified;
		}

		@Override
		public boolean isModified(MonthlyNthDayConfigDto container) {
			return container.isDayInMonthModified();
		}
	};

	private Integer month;
	private  boolean month_m;
	public static final String PROPERTY_MONTH = "dpi-monthlynthdayconfig-month";

	private transient static PropertyAccessor<MonthlyNthDayConfigDto, Integer> month_pa = new PropertyAccessor<MonthlyNthDayConfigDto, Integer>() {
		@Override
		public void setValue(MonthlyNthDayConfigDto container, Integer object) {
			container.setMonth(object);
		}

		@Override
		public Integer getValue(MonthlyNthDayConfigDto container) {
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
		public void setModified(MonthlyNthDayConfigDto container, boolean modified) {
			container.month_m = modified;
		}

		@Override
		public boolean isModified(MonthlyNthDayConfigDto container) {
			return container.isMonthModified();
		}
	};


	public MonthlyNthDayConfigDto() {
		super();
	}

	public Integer getDayInMonth()  {
		if(! isDtoProxy()){
			return this.dayInMonth;
		}

		if(isDayInMonthModified())
			return this.dayInMonth;

		if(! GWT.isClient())
			return null;

		Integer _value = dtoManager.getProperty(this, instantiatePropertyAccess().dayInMonth());

		return _value;
	}


	public void setDayInMonth(Integer dayInMonth)  {
		/* old value */
		Integer oldValue = null;
		if(GWT.isClient())
			oldValue = getDayInMonth();

		/* set new value */
		this.dayInMonth = dayInMonth;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(dayInMonth_pa, oldValue, dayInMonth, this.dayInMonth_m));

		/* set indicator */
		this.dayInMonth_m = true;

		this.fireObjectChangedEvent(MonthlyNthDayConfigDtoPA.INSTANCE.dayInMonth(), oldValue);
	}


	public boolean isDayInMonthModified()  {
		return dayInMonth_m;
	}


	public static PropertyAccessor<MonthlyNthDayConfigDto, Integer> getDayInMonthPropertyAccessor()  {
		return dayInMonth_pa;
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

		this.fireObjectChangedEvent(MonthlyNthDayConfigDtoPA.INSTANCE.month(), oldValue);
	}


	public boolean isMonthModified()  {
		return month_m;
	}


	public static PropertyAccessor<MonthlyNthDayConfigDto, Integer> getMonthPropertyAccessor()  {
		return month_pa;
	}


	@Override
	public int hashCode()  {
		if(null == getId())
			return super.hashCode();
		return getId().hashCode();
	}

	@Override
	public boolean equals(Object obj)  {
		if(! (obj instanceof MonthlyNthDayConfigDto))
			return false;

		if(null == getId())
			return super.equals(obj);
		return getId().equals(((MonthlyNthDayConfigDto)obj).getId());
	}

	@Override
	public String toString()  {
		return getClass().getName() + ": " + getId();
	}

	public static Dto2PosoMapper newPosoMapper()  {
		return new MonthlyNthDayConfigDto2PosoMap();
	}

	public MonthlyNthDayConfigDtoPA instantiatePropertyAccess()  {
		return GWT.create(MonthlyNthDayConfigDtoPA.class);
	}

	public void clearModified()  {
		this.dayInMonth = null;
		this.dayInMonth_m = false;
		this.month = null;
		this.month_m = false;
	}


	public boolean isModified()  {
		if(super.isModified())
			return true;
		if(dayInMonth_m)
			return true;
		if(month_m)
			return true;
		return false;
	}


	public List<PropertyAccessor> getPropertyAccessors()  {
		List<PropertyAccessor> list = super.getPropertyAccessors();
		list.add(dayInMonth_pa);
		list.add(month_pa);
		return list;
	}


	public List<PropertyAccessor> getModifiedPropertyAccessors()  {
		List<PropertyAccessor> list = super.getModifiedPropertyAccessors();
		if(dayInMonth_m)
			list.add(dayInMonth_pa);
		if(month_m)
			list.add(month_pa);
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsByView(net.datenwerke.gxtdto.client.dtomanager.DtoView view)  {
		List<PropertyAccessor> list = super.getPropertyAccessorsByView(view);
		if(view.compareTo(DtoView.NORMAL) >= 0){
			list.add(dayInMonth_pa);
			list.add(month_pa);
		}
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsForDtos()  {
		List<PropertyAccessor> list = super.getPropertyAccessorsForDtos();
		return list;
	}




}
