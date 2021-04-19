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
import net.datenwerke.scheduler.client.scheduler.dto.config.complex.DailyPatternDto;
import net.datenwerke.scheduler.client.scheduler.dto.config.complex.DateTriggerConfigDto;
import net.datenwerke.scheduler.client.scheduler.dto.config.complex.pa.DailyConfigDtoPA;
import net.datenwerke.scheduler.client.scheduler.dto.config.complex.posomap.DailyConfigDto2PosoMap;
import net.datenwerke.scheduler.service.scheduler.triggers.complex.config.DailyConfig;

/**
 * Dto for {@link DailyConfig}
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class DailyConfigDto extends DateTriggerConfigDto {


	private static final long serialVersionUID = 1;


	/* Fields */
	private Integer dailyN;
	private  boolean dailyN_m;
	public static final String PROPERTY_DAILY_N = "dpi-dailyconfig-dailyn";

	private transient static PropertyAccessor<DailyConfigDto, Integer> dailyN_pa = new PropertyAccessor<DailyConfigDto, Integer>() {
		@Override
		public void setValue(DailyConfigDto container, Integer object) {
			container.setDailyN(object);
		}

		@Override
		public Integer getValue(DailyConfigDto container) {
			return container.getDailyN();
		}

		@Override
		public Class<?> getType() {
			return Integer.class;
		}

		@Override
		public String getPath() {
			return "dailyN";
		}

		@Override
		public void setModified(DailyConfigDto container, boolean modified) {
			container.dailyN_m = modified;
		}

		@Override
		public boolean isModified(DailyConfigDto container) {
			return container.isDailyNModified();
		}
	};

	private DailyPatternDto pattern;
	private  boolean pattern_m;
	public static final String PROPERTY_PATTERN = "dpi-dailyconfig-pattern";

	private transient static PropertyAccessor<DailyConfigDto, DailyPatternDto> pattern_pa = new PropertyAccessor<DailyConfigDto, DailyPatternDto>() {
		@Override
		public void setValue(DailyConfigDto container, DailyPatternDto object) {
			container.setPattern(object);
		}

		@Override
		public DailyPatternDto getValue(DailyConfigDto container) {
			return container.getPattern();
		}

		@Override
		public Class<?> getType() {
			return DailyPatternDto.class;
		}

		@Override
		public String getPath() {
			return "pattern";
		}

		@Override
		public void setModified(DailyConfigDto container, boolean modified) {
			container.pattern_m = modified;
		}

		@Override
		public boolean isModified(DailyConfigDto container) {
			return container.isPatternModified();
		}
	};


	public DailyConfigDto() {
		super();
	}

	public Integer getDailyN()  {
		if(! isDtoProxy()){
			return this.dailyN;
		}

		if(isDailyNModified())
			return this.dailyN;

		if(! GWT.isClient())
			return null;

		Integer _value = dtoManager.getProperty(this, instantiatePropertyAccess().dailyN());

		return _value;
	}


	public void setDailyN(Integer dailyN)  {
		/* old value */
		Integer oldValue = null;
		if(GWT.isClient())
			oldValue = getDailyN();

		/* set new value */
		this.dailyN = dailyN;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(dailyN_pa, oldValue, dailyN, this.dailyN_m));

		/* set indicator */
		this.dailyN_m = true;

		this.fireObjectChangedEvent(DailyConfigDtoPA.INSTANCE.dailyN(), oldValue);
	}


	public boolean isDailyNModified()  {
		return dailyN_m;
	}


	public static PropertyAccessor<DailyConfigDto, Integer> getDailyNPropertyAccessor()  {
		return dailyN_pa;
	}


	public DailyPatternDto getPattern()  {
		if(! isDtoProxy()){
			return this.pattern;
		}

		if(isPatternModified())
			return this.pattern;

		if(! GWT.isClient())
			return null;

		DailyPatternDto _value = dtoManager.getProperty(this, instantiatePropertyAccess().pattern());

		return _value;
	}


	public void setPattern(DailyPatternDto pattern)  {
		/* old value */
		DailyPatternDto oldValue = null;
		if(GWT.isClient())
			oldValue = getPattern();

		/* set new value */
		this.pattern = pattern;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(pattern_pa, oldValue, pattern, this.pattern_m));

		/* set indicator */
		this.pattern_m = true;

		this.fireObjectChangedEvent(DailyConfigDtoPA.INSTANCE.pattern(), oldValue);
	}


	public boolean isPatternModified()  {
		return pattern_m;
	}


	public static PropertyAccessor<DailyConfigDto, DailyPatternDto> getPatternPropertyAccessor()  {
		return pattern_pa;
	}


	@Override
	public int hashCode()  {
		if(null == getId())
			return super.hashCode();
		return getId().hashCode();
	}

	@Override
	public boolean equals(Object obj)  {
		if(! (obj instanceof DailyConfigDto))
			return false;

		if(null == getId())
			return super.equals(obj);
		return getId().equals(((DailyConfigDto)obj).getId());
	}

	@Override
	public String toString()  {
		return getClass().getName() + ": " + getId();
	}

	public static Dto2PosoMapper newPosoMapper()  {
		return new DailyConfigDto2PosoMap();
	}

	public DailyConfigDtoPA instantiatePropertyAccess()  {
		return GWT.create(DailyConfigDtoPA.class);
	}

	public void clearModified()  {
		this.dailyN = null;
		this.dailyN_m = false;
		this.pattern = null;
		this.pattern_m = false;
	}


	public boolean isModified()  {
		if(super.isModified())
			return true;
		if(dailyN_m)
			return true;
		if(pattern_m)
			return true;
		return false;
	}


	public List<PropertyAccessor> getPropertyAccessors()  {
		List<PropertyAccessor> list = super.getPropertyAccessors();
		list.add(dailyN_pa);
		list.add(pattern_pa);
		return list;
	}


	public List<PropertyAccessor> getModifiedPropertyAccessors()  {
		List<PropertyAccessor> list = super.getModifiedPropertyAccessors();
		if(dailyN_m)
			list.add(dailyN_pa);
		if(pattern_m)
			list.add(pattern_pa);
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsByView(net.datenwerke.gxtdto.client.dtomanager.DtoView view)  {
		List<PropertyAccessor> list = super.getPropertyAccessorsByView(view);
		if(view.compareTo(DtoView.NORMAL) >= 0){
			list.add(dailyN_pa);
			list.add(pattern_pa);
		}
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsForDtos()  {
		List<PropertyAccessor> list = super.getPropertyAccessorsForDtos();
		return list;
	}



	net.datenwerke.scheduler.client.scheduler.dto.config.complex.DailyPatternDto wl_0;

}
