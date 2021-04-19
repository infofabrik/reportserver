package net.datenwerke.rs.dashboard.client.dashboard.dto;

import com.google.gwt.core.client.GWT;
import java.lang.IllegalStateException;
import java.lang.Long;
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
import net.datenwerke.rs.core.client.parameters.dto.ParameterInstanceDto;
import net.datenwerke.rs.dashboard.client.dashboard.dto.DadgetContainerDto;
import net.datenwerke.rs.dashboard.client.dashboard.dto.pa.DadgetDtoPA;
import net.datenwerke.rs.dashboard.client.dashboard.dto.posomap.DadgetDto2PosoMap;
import net.datenwerke.rs.dashboard.service.dashboard.entities.Dadget;

/**
 * Dto for {@link Dadget}
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
abstract public class DadgetDto extends RsDto implements IdedDto {


	private static final long serialVersionUID = 1;

	private Long dtoId;


	/* Fields */
	private int col;
	private  boolean col_m;
	public static final String PROPERTY_COL = "dpi-dadget-col";

	private transient static PropertyAccessor<DadgetDto, Integer> col_pa = new PropertyAccessor<DadgetDto, Integer>() {
		@Override
		public void setValue(DadgetDto container, Integer object) {
			container.setCol(object);
		}

		@Override
		public Integer getValue(DadgetDto container) {
			return container.getCol();
		}

		@Override
		public Class<?> getType() {
			return Integer.class;
		}

		@Override
		public String getPath() {
			return "col";
		}

		@Override
		public void setModified(DadgetDto container, boolean modified) {
			container.col_m = modified;
		}

		@Override
		public boolean isModified(DadgetDto container) {
			return container.isColModified();
		}
	};

	private DadgetContainerDto container;
	private  boolean container_m;
	public static final String PROPERTY_CONTAINER = "dpi-dadget-container";

	private transient static PropertyAccessor<DadgetDto, DadgetContainerDto> container_pa = new PropertyAccessor<DadgetDto, DadgetContainerDto>() {
		@Override
		public void setValue(DadgetDto container, DadgetContainerDto object) {
			container.setContainer(object);
		}

		@Override
		public DadgetContainerDto getValue(DadgetDto container) {
			return container.getContainer();
		}

		@Override
		public Class<?> getType() {
			return DadgetContainerDto.class;
		}

		@Override
		public String getPath() {
			return "container";
		}

		@Override
		public void setModified(DadgetDto container, boolean modified) {
			container.container_m = modified;
		}

		@Override
		public boolean isModified(DadgetDto container) {
			return container.isContainerModified();
		}
	};

	private int height;
	private  boolean height_m;
	public static final String PROPERTY_HEIGHT = "dpi-dadget-height";

	private transient static PropertyAccessor<DadgetDto, Integer> height_pa = new PropertyAccessor<DadgetDto, Integer>() {
		@Override
		public void setValue(DadgetDto container, Integer object) {
			container.setHeight(object);
		}

		@Override
		public Integer getValue(DadgetDto container) {
			return container.getHeight();
		}

		@Override
		public Class<?> getType() {
			return Integer.class;
		}

		@Override
		public String getPath() {
			return "height";
		}

		@Override
		public void setModified(DadgetDto container, boolean modified) {
			container.height_m = modified;
		}

		@Override
		public boolean isModified(DadgetDto container) {
			return container.isHeightModified();
		}
	};

	private Long id;
	private  boolean id_m;
	public static final String PROPERTY_ID = "dpi-dadget-id";

	private transient static PropertyAccessor<DadgetDto, Long> id_pa = new PropertyAccessor<DadgetDto, Long>() {
		@Override
		public void setValue(DadgetDto container, Long object) {
			// id field
		}

		@Override
		public Long getValue(DadgetDto container) {
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
		public void setModified(DadgetDto container, boolean modified) {
			container.id_m = modified;
		}

		@Override
		public boolean isModified(DadgetDto container) {
			return container.isIdModified();
		}
	};

	private int n;
	private  boolean n_m;
	public static final String PROPERTY_N = "dpi-dadget-n";

	private transient static PropertyAccessor<DadgetDto, Integer> n_pa = new PropertyAccessor<DadgetDto, Integer>() {
		@Override
		public void setValue(DadgetDto container, Integer object) {
			container.setN(object);
		}

		@Override
		public Integer getValue(DadgetDto container) {
			return container.getN();
		}

		@Override
		public Class<?> getType() {
			return Integer.class;
		}

		@Override
		public String getPath() {
			return "n";
		}

		@Override
		public void setModified(DadgetDto container, boolean modified) {
			container.n_m = modified;
		}

		@Override
		public boolean isModified(DadgetDto container) {
			return container.isNModified();
		}
	};

	private Set<ParameterInstanceDto> parameterInstances;
	private  boolean parameterInstances_m;
	public static final String PROPERTY_PARAMETER_INSTANCES = "dpi-dadget-parameterinstances";

	private transient static PropertyAccessor<DadgetDto, Set<ParameterInstanceDto>> parameterInstances_pa = new PropertyAccessor<DadgetDto, Set<ParameterInstanceDto>>() {
		@Override
		public void setValue(DadgetDto container, Set<ParameterInstanceDto> object) {
			container.setParameterInstances(object);
		}

		@Override
		public Set<ParameterInstanceDto> getValue(DadgetDto container) {
			return container.getParameterInstances();
		}

		@Override
		public Class<?> getType() {
			return Set.class;
		}

		@Override
		public String getPath() {
			return "parameterInstances";
		}

		@Override
		public void setModified(DadgetDto container, boolean modified) {
			container.parameterInstances_m = modified;
		}

		@Override
		public boolean isModified(DadgetDto container) {
			return container.isParameterInstancesModified();
		}
	};

	private long reloadInterval;
	private  boolean reloadInterval_m;
	public static final String PROPERTY_RELOAD_INTERVAL = "dpi-dadget-reloadinterval";

	private transient static PropertyAccessor<DadgetDto, Long> reloadInterval_pa = new PropertyAccessor<DadgetDto, Long>() {
		@Override
		public void setValue(DadgetDto container, Long object) {
			container.setReloadInterval(object);
		}

		@Override
		public Long getValue(DadgetDto container) {
			return container.getReloadInterval();
		}

		@Override
		public Class<?> getType() {
			return Long.class;
		}

		@Override
		public String getPath() {
			return "reloadInterval";
		}

		@Override
		public void setModified(DadgetDto container, boolean modified) {
			container.reloadInterval_m = modified;
		}

		@Override
		public boolean isModified(DadgetDto container) {
			return container.isReloadIntervalModified();
		}
	};


	public DadgetDto() {
		super();
	}

	public int getCol()  {
		if(! isDtoProxy()){
			return this.col;
		}

		if(isColModified())
			return this.col;

		if(! GWT.isClient())
			return 0;

		int _value = dtoManager.getProperty(this, instantiatePropertyAccess().col());

		return _value;
	}


	public void setCol(int col)  {
		/* old value */
		int oldValue = 0;
		if(GWT.isClient())
			oldValue = getCol();

		/* set new value */
		this.col = col;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(col_pa, oldValue, col, this.col_m));

		/* set indicator */
		this.col_m = true;

		this.fireObjectChangedEvent(DadgetDtoPA.INSTANCE.col(), oldValue);
	}


	public boolean isColModified()  {
		return col_m;
	}


	public static PropertyAccessor<DadgetDto, Integer> getColPropertyAccessor()  {
		return col_pa;
	}


	public DadgetContainerDto getContainer()  {
		if(! isDtoProxy()){
			return this.container;
		}

		if(isContainerModified())
			return this.container;

		if(! GWT.isClient())
			return null;

		DadgetContainerDto _value = dtoManager.getProperty(this, instantiatePropertyAccess().container());

		return _value;
	}


	public void setContainer(DadgetContainerDto container)  {
		/* old value */
		DadgetContainerDto oldValue = null;
		if(GWT.isClient())
			oldValue = getContainer();

		/* set new value */
		this.container = container;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(container_pa, oldValue, container, this.container_m));

		/* set indicator */
		this.container_m = true;

		this.fireObjectChangedEvent(DadgetDtoPA.INSTANCE.container(), oldValue);
	}


	public boolean isContainerModified()  {
		return container_m;
	}


	public static PropertyAccessor<DadgetDto, DadgetContainerDto> getContainerPropertyAccessor()  {
		return container_pa;
	}


	public int getHeight()  {
		if(! isDtoProxy()){
			return this.height;
		}

		if(isHeightModified())
			return this.height;

		if(! GWT.isClient())
			return 0;

		int _value = dtoManager.getProperty(this, instantiatePropertyAccess().height());

		return _value;
	}


	public void setHeight(int height)  {
		/* old value */
		int oldValue = 0;
		if(GWT.isClient())
			oldValue = getHeight();

		/* set new value */
		this.height = height;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(height_pa, oldValue, height, this.height_m));

		/* set indicator */
		this.height_m = true;

		this.fireObjectChangedEvent(DadgetDtoPA.INSTANCE.height(), oldValue);
	}


	public boolean isHeightModified()  {
		return height_m;
	}


	public static PropertyAccessor<DadgetDto, Integer> getHeightPropertyAccessor()  {
		return height_pa;
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


	public static PropertyAccessor<DadgetDto, Long> getIdPropertyAccessor()  {
		return id_pa;
	}


	public int getN()  {
		if(! isDtoProxy()){
			return this.n;
		}

		if(isNModified())
			return this.n;

		if(! GWT.isClient())
			return 0;

		int _value = dtoManager.getProperty(this, instantiatePropertyAccess().n());

		return _value;
	}


	public void setN(int n)  {
		/* old value */
		int oldValue = 0;
		if(GWT.isClient())
			oldValue = getN();

		/* set new value */
		this.n = n;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(n_pa, oldValue, n, this.n_m));

		/* set indicator */
		this.n_m = true;

		this.fireObjectChangedEvent(DadgetDtoPA.INSTANCE.n(), oldValue);
	}


	public boolean isNModified()  {
		return n_m;
	}


	public static PropertyAccessor<DadgetDto, Integer> getNPropertyAccessor()  {
		return n_pa;
	}


	public Set<ParameterInstanceDto> getParameterInstances()  {
		if(! isDtoProxy()){
			Set<ParameterInstanceDto> _currentValue = this.parameterInstances;
			if(null == _currentValue)
				this.parameterInstances = new HashSet<ParameterInstanceDto>();

			return this.parameterInstances;
		}

		if(isParameterInstancesModified())
			return this.parameterInstances;

		if(! GWT.isClient())
			return null;

		Set<ParameterInstanceDto> _value = dtoManager.getProperty(this, instantiatePropertyAccess().parameterInstances());

		_value = new ChangeMonitoredSet<ParameterInstanceDto>(_value);
		if(_value instanceof HasObjectChangedEventHandler){
			((HasObjectChangedEventHandler)_value).addObjectChangedHandler(new net.datenwerke.gxtdto.client.eventbus.handlers.ObjectChangedEventHandler(){
				@Override
				public void onObjectChangedEvent(net.datenwerke.gxtdto.client.eventbus.events.ObjectChangedEvent event){
					if(! isParameterInstancesModified())
						setParameterInstances((Set<ParameterInstanceDto>) ((MonitoredCollection) event.getObject()).getUnderlyingCollection());
				}
			}
			);
		}
		return _value;
	}


	public void setParameterInstances(Set<ParameterInstanceDto> parameterInstances)  {
		/* old value */
		Set<ParameterInstanceDto> oldValue = null;
		if(GWT.isClient())
			oldValue = getParameterInstances();

		/* set new value */
		this.parameterInstances = parameterInstances;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(parameterInstances_pa, oldValue, parameterInstances, this.parameterInstances_m));

		/* set indicator */
		this.parameterInstances_m = true;

		this.fireObjectChangedEvent(DadgetDtoPA.INSTANCE.parameterInstances(), oldValue);
	}


	public boolean isParameterInstancesModified()  {
		return parameterInstances_m;
	}


	public static PropertyAccessor<DadgetDto, Set<ParameterInstanceDto>> getParameterInstancesPropertyAccessor()  {
		return parameterInstances_pa;
	}


	public long getReloadInterval()  {
		if(! isDtoProxy()){
			return this.reloadInterval;
		}

		if(isReloadIntervalModified())
			return this.reloadInterval;

		if(! GWT.isClient())
			return 0;

		long _value = dtoManager.getProperty(this, instantiatePropertyAccess().reloadInterval());

		return _value;
	}


	public void setReloadInterval(long reloadInterval)  {
		/* old value */
		long oldValue = 0;
		if(GWT.isClient())
			oldValue = getReloadInterval();

		/* set new value */
		this.reloadInterval = reloadInterval;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(reloadInterval_pa, oldValue, reloadInterval, this.reloadInterval_m));

		/* set indicator */
		this.reloadInterval_m = true;

		this.fireObjectChangedEvent(DadgetDtoPA.INSTANCE.reloadInterval(), oldValue);
	}


	public boolean isReloadIntervalModified()  {
		return reloadInterval_m;
	}


	public static PropertyAccessor<DadgetDto, Long> getReloadIntervalPropertyAccessor()  {
		return reloadInterval_pa;
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
		if(! (obj instanceof DadgetDto))
			return false;

		if(null == getId())
			return super.equals(obj);
		return getId().equals(((DadgetDto)obj).getId());
	}

	@Override
	public String toString()  {
		return getClass().getName() + ": " + getId();
	}

	public static Dto2PosoMapper newPosoMapper()  {
		return new DadgetDto2PosoMap();
	}

	public DadgetDtoPA instantiatePropertyAccess()  {
		return GWT.create(DadgetDtoPA.class);
	}

	public void clearModified()  {
		this.col = 0;
		this.col_m = false;
		this.container = null;
		this.container_m = false;
		this.height = 0;
		this.height_m = false;
		this.id = null;
		this.id_m = false;
		this.n = 0;
		this.n_m = false;
		this.parameterInstances = null;
		this.parameterInstances_m = false;
		this.reloadInterval = 0;
		this.reloadInterval_m = false;
	}


	public boolean isModified()  {
		if(super.isModified())
			return true;
		if(col_m)
			return true;
		if(container_m)
			return true;
		if(height_m)
			return true;
		if(id_m)
			return true;
		if(n_m)
			return true;
		if(parameterInstances_m)
			return true;
		if(reloadInterval_m)
			return true;
		return false;
	}


	public List<PropertyAccessor> getPropertyAccessors()  {
		List<PropertyAccessor> list = super.getPropertyAccessors();
		list.add(col_pa);
		list.add(container_pa);
		list.add(height_pa);
		list.add(id_pa);
		list.add(n_pa);
		list.add(parameterInstances_pa);
		list.add(reloadInterval_pa);
		return list;
	}


	public List<PropertyAccessor> getModifiedPropertyAccessors()  {
		List<PropertyAccessor> list = super.getModifiedPropertyAccessors();
		if(col_m)
			list.add(col_pa);
		if(container_m)
			list.add(container_pa);
		if(height_m)
			list.add(height_pa);
		if(id_m)
			list.add(id_pa);
		if(n_m)
			list.add(n_pa);
		if(parameterInstances_m)
			list.add(parameterInstances_pa);
		if(reloadInterval_m)
			list.add(reloadInterval_pa);
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsByView(net.datenwerke.gxtdto.client.dtomanager.DtoView view)  {
		List<PropertyAccessor> list = super.getPropertyAccessorsByView(view);
		if(view.compareTo(DtoView.MINIMAL) >= 0){
			list.add(id_pa);
		}
		if(view.compareTo(DtoView.NORMAL) >= 0){
			list.add(col_pa);
			list.add(container_pa);
			list.add(height_pa);
			list.add(n_pa);
			list.add(parameterInstances_pa);
			list.add(reloadInterval_pa);
		}
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsForDtos()  {
		List<PropertyAccessor> list = super.getPropertyAccessorsForDtos();
		list.add(parameterInstances_pa);
		return list;
	}



	net.datenwerke.rs.core.client.parameters.dto.ParameterInstanceDto wl_0;
	net.datenwerke.rs.dashboard.client.dashboard.dto.DadgetContainerDto wl_1;

}
