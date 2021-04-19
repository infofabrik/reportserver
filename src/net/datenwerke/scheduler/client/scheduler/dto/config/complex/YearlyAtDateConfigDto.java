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
import net.datenwerke.scheduler.client.scheduler.dto.config.complex.MonthsDto;
import net.datenwerke.scheduler.client.scheduler.dto.config.complex.pa.YearlyAtDateConfigDtoPA;
import net.datenwerke.scheduler.client.scheduler.dto.config.complex.posomap.YearlyAtDateConfigDto2PosoMap;
import net.datenwerke.scheduler.service.scheduler.triggers.complex.config.YearlyAtDateConfig;

/**
 * Dto for {@link YearlyAtDateConfig}
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class YearlyAtDateConfigDto extends DateTriggerConfigDto {


	private static final long serialVersionUID = 1;


	/* Fields */
	private MonthsDto yearlyMonth;
	private  boolean yearlyMonth_m;
	public static final String PROPERTY_YEARLY_MONTH = "dpi-yearlyatdateconfig-yearlymonth";

	private transient static PropertyAccessor<YearlyAtDateConfigDto, MonthsDto> yearlyMonth_pa = new PropertyAccessor<YearlyAtDateConfigDto, MonthsDto>() {
		@Override
		public void setValue(YearlyAtDateConfigDto container, MonthsDto object) {
			container.setYearlyMonth(object);
		}

		@Override
		public MonthsDto getValue(YearlyAtDateConfigDto container) {
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
		public void setModified(YearlyAtDateConfigDto container, boolean modified) {
			container.yearlyMonth_m = modified;
		}

		@Override
		public boolean isModified(YearlyAtDateConfigDto container) {
			return container.isYearlyMonthModified();
		}
	};

	private Integer yearlyNDay;
	private  boolean yearlyNDay_m;
	public static final String PROPERTY_YEARLY_N_DAY = "dpi-yearlyatdateconfig-yearlynday";

	private transient static PropertyAccessor<YearlyAtDateConfigDto, Integer> yearlyNDay_pa = new PropertyAccessor<YearlyAtDateConfigDto, Integer>() {
		@Override
		public void setValue(YearlyAtDateConfigDto container, Integer object) {
			container.setYearlyNDay(object);
		}

		@Override
		public Integer getValue(YearlyAtDateConfigDto container) {
			return container.getYearlyNDay();
		}

		@Override
		public Class<?> getType() {
			return Integer.class;
		}

		@Override
		public String getPath() {
			return "yearlyNDay";
		}

		@Override
		public void setModified(YearlyAtDateConfigDto container, boolean modified) {
			container.yearlyNDay_m = modified;
		}

		@Override
		public boolean isModified(YearlyAtDateConfigDto container) {
			return container.isYearlyNDayModified();
		}
	};


	public YearlyAtDateConfigDto() {
		super();
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

		this.fireObjectChangedEvent(YearlyAtDateConfigDtoPA.INSTANCE.yearlyMonth(), oldValue);
	}


	public boolean isYearlyMonthModified()  {
		return yearlyMonth_m;
	}


	public static PropertyAccessor<YearlyAtDateConfigDto, MonthsDto> getYearlyMonthPropertyAccessor()  {
		return yearlyMonth_pa;
	}


	public Integer getYearlyNDay()  {
		if(! isDtoProxy()){
			return this.yearlyNDay;
		}

		if(isYearlyNDayModified())
			return this.yearlyNDay;

		if(! GWT.isClient())
			return null;

		Integer _value = dtoManager.getProperty(this, instantiatePropertyAccess().yearlyNDay());

		return _value;
	}


	public void setYearlyNDay(Integer yearlyNDay)  {
		/* old value */
		Integer oldValue = null;
		if(GWT.isClient())
			oldValue = getYearlyNDay();

		/* set new value */
		this.yearlyNDay = yearlyNDay;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(yearlyNDay_pa, oldValue, yearlyNDay, this.yearlyNDay_m));

		/* set indicator */
		this.yearlyNDay_m = true;

		this.fireObjectChangedEvent(YearlyAtDateConfigDtoPA.INSTANCE.yearlyNDay(), oldValue);
	}


	public boolean isYearlyNDayModified()  {
		return yearlyNDay_m;
	}


	public static PropertyAccessor<YearlyAtDateConfigDto, Integer> getYearlyNDayPropertyAccessor()  {
		return yearlyNDay_pa;
	}


	@Override
	public int hashCode()  {
		if(null == getId())
			return super.hashCode();
		return getId().hashCode();
	}

	@Override
	public boolean equals(Object obj)  {
		if(! (obj instanceof YearlyAtDateConfigDto))
			return false;

		if(null == getId())
			return super.equals(obj);
		return getId().equals(((YearlyAtDateConfigDto)obj).getId());
	}

	@Override
	public String toString()  {
		return getClass().getName() + ": " + getId();
	}

	public static Dto2PosoMapper newPosoMapper()  {
		return new YearlyAtDateConfigDto2PosoMap();
	}

	public YearlyAtDateConfigDtoPA instantiatePropertyAccess()  {
		return GWT.create(YearlyAtDateConfigDtoPA.class);
	}

	public void clearModified()  {
		this.yearlyMonth = null;
		this.yearlyMonth_m = false;
		this.yearlyNDay = null;
		this.yearlyNDay_m = false;
	}


	public boolean isModified()  {
		if(super.isModified())
			return true;
		if(yearlyMonth_m)
			return true;
		if(yearlyNDay_m)
			return true;
		return false;
	}


	public List<PropertyAccessor> getPropertyAccessors()  {
		List<PropertyAccessor> list = super.getPropertyAccessors();
		list.add(yearlyMonth_pa);
		list.add(yearlyNDay_pa);
		return list;
	}


	public List<PropertyAccessor> getModifiedPropertyAccessors()  {
		List<PropertyAccessor> list = super.getModifiedPropertyAccessors();
		if(yearlyMonth_m)
			list.add(yearlyMonth_pa);
		if(yearlyNDay_m)
			list.add(yearlyNDay_pa);
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsByView(net.datenwerke.gxtdto.client.dtomanager.DtoView view)  {
		List<PropertyAccessor> list = super.getPropertyAccessorsByView(view);
		if(view.compareTo(DtoView.NORMAL) >= 0){
			list.add(yearlyMonth_pa);
			list.add(yearlyNDay_pa);
		}
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsForDtos()  {
		List<PropertyAccessor> list = super.getPropertyAccessorsForDtos();
		return list;
	}



	net.datenwerke.scheduler.client.scheduler.dto.config.complex.MonthsDto wl_0;

}
