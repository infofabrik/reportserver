package net.datenwerke.rs.dashboard.client.dashboard.dto;

import com.google.gwt.core.client.GWT;
import java.lang.IllegalStateException;
import java.lang.Long;
import java.lang.String;
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
import net.datenwerke.rs.dashboard.client.dashboard.dto.DadgetDto;
import net.datenwerke.rs.dashboard.client.dashboard.dto.LayoutTypeDto;
import net.datenwerke.rs.dashboard.client.dashboard.dto.pa.DashboardDtoPA;
import net.datenwerke.rs.dashboard.client.dashboard.dto.posomap.DashboardDto2PosoMap;
import net.datenwerke.rs.dashboard.service.dashboard.entities.Dashboard;

/**
 * Dto for {@link Dashboard}
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
abstract public class DashboardDto extends RsDto implements IdedDto {


	private static final long serialVersionUID = 1;

	private Long dtoId;


	/* Fields */
	private List<DadgetDto> dadgets;
	private  boolean dadgets_m;
	public static final String PROPERTY_DADGETS = "dpi-dashboard-dadgets";

	private transient static PropertyAccessor<DashboardDto, List<DadgetDto>> dadgets_pa = new PropertyAccessor<DashboardDto, List<DadgetDto>>() {
		@Override
		public void setValue(DashboardDto container, List<DadgetDto> object) {
			container.setDadgets(object);
		}

		@Override
		public List<DadgetDto> getValue(DashboardDto container) {
			return container.getDadgets();
		}

		@Override
		public Class<?> getType() {
			return List.class;
		}

		@Override
		public String getPath() {
			return "dadgets";
		}

		@Override
		public void setModified(DashboardDto container, boolean modified) {
			container.dadgets_m = modified;
		}

		@Override
		public boolean isModified(DashboardDto container) {
			return container.isDadgetsModified();
		}
	};

	private String description;
	private  boolean description_m;
	public static final String PROPERTY_DESCRIPTION = "dpi-dashboard-description";

	private transient static PropertyAccessor<DashboardDto, String> description_pa = new PropertyAccessor<DashboardDto, String>() {
		@Override
		public void setValue(DashboardDto container, String object) {
			container.setDescription(object);
		}

		@Override
		public String getValue(DashboardDto container) {
			return container.getDescription();
		}

		@Override
		public Class<?> getType() {
			return String.class;
		}

		@Override
		public String getPath() {
			return "description";
		}

		@Override
		public void setModified(DashboardDto container, boolean modified) {
			container.description_m = modified;
		}

		@Override
		public boolean isModified(DashboardDto container) {
			return container.isDescriptionModified();
		}
	};

	private Long id;
	private  boolean id_m;
	public static final String PROPERTY_ID = "dpi-dashboard-id";

	private transient static PropertyAccessor<DashboardDto, Long> id_pa = new PropertyAccessor<DashboardDto, Long>() {
		@Override
		public void setValue(DashboardDto container, Long object) {
			// id field
		}

		@Override
		public Long getValue(DashboardDto container) {
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
		public void setModified(DashboardDto container, boolean modified) {
			container.id_m = modified;
		}

		@Override
		public boolean isModified(DashboardDto container) {
			return container.isIdModified();
		}
	};

	private LayoutTypeDto layout;
	private  boolean layout_m;
	public static final String PROPERTY_LAYOUT = "dpi-dashboard-layout";

	private transient static PropertyAccessor<DashboardDto, LayoutTypeDto> layout_pa = new PropertyAccessor<DashboardDto, LayoutTypeDto>() {
		@Override
		public void setValue(DashboardDto container, LayoutTypeDto object) {
			container.setLayout(object);
		}

		@Override
		public LayoutTypeDto getValue(DashboardDto container) {
			return container.getLayout();
		}

		@Override
		public Class<?> getType() {
			return LayoutTypeDto.class;
		}

		@Override
		public String getPath() {
			return "layout";
		}

		@Override
		public void setModified(DashboardDto container, boolean modified) {
			container.layout_m = modified;
		}

		@Override
		public boolean isModified(DashboardDto container) {
			return container.isLayoutModified();
		}
	};

	private int n;
	private  boolean n_m;
	public static final String PROPERTY_N = "dpi-dashboard-n";

	private transient static PropertyAccessor<DashboardDto, Integer> n_pa = new PropertyAccessor<DashboardDto, Integer>() {
		@Override
		public void setValue(DashboardDto container, Integer object) {
			container.setN(object);
		}

		@Override
		public Integer getValue(DashboardDto container) {
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
		public void setModified(DashboardDto container, boolean modified) {
			container.n_m = modified;
		}

		@Override
		public boolean isModified(DashboardDto container) {
			return container.isNModified();
		}
	};

	private String name;
	private  boolean name_m;
	public static final String PROPERTY_NAME = "dpi-dashboard-name";

	private transient static PropertyAccessor<DashboardDto, String> name_pa = new PropertyAccessor<DashboardDto, String>() {
		@Override
		public void setValue(DashboardDto container, String object) {
			container.setName(object);
		}

		@Override
		public String getValue(DashboardDto container) {
			return container.getName();
		}

		@Override
		public Class<?> getType() {
			return String.class;
		}

		@Override
		public String getPath() {
			return "name";
		}

		@Override
		public void setModified(DashboardDto container, boolean modified) {
			container.name_m = modified;
		}

		@Override
		public boolean isModified(DashboardDto container) {
			return container.isNameModified();
		}
	};

	private boolean primary;
	private  boolean primary_m;
	public static final String PROPERTY_PRIMARY = "dpi-dashboard-primary";

	private transient static PropertyAccessor<DashboardDto, Boolean> primary_pa = new PropertyAccessor<DashboardDto, Boolean>() {
		@Override
		public void setValue(DashboardDto container, Boolean object) {
			container.setPrimary(object);
		}

		@Override
		public Boolean getValue(DashboardDto container) {
			return container.isPrimary();
		}

		@Override
		public Class<?> getType() {
			return Boolean.class;
		}

		@Override
		public String getPath() {
			return "primary";
		}

		@Override
		public void setModified(DashboardDto container, boolean modified) {
			container.primary_m = modified;
		}

		@Override
		public boolean isModified(DashboardDto container) {
			return container.isPrimaryModified();
		}
	};

	private long reloadInterval;
	private  boolean reloadInterval_m;
	public static final String PROPERTY_RELOAD_INTERVAL = "dpi-dashboard-reloadinterval";

	private transient static PropertyAccessor<DashboardDto, Long> reloadInterval_pa = new PropertyAccessor<DashboardDto, Long>() {
		@Override
		public void setValue(DashboardDto container, Long object) {
			container.setReloadInterval(object);
		}

		@Override
		public Long getValue(DashboardDto container) {
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
		public void setModified(DashboardDto container, boolean modified) {
			container.reloadInterval_m = modified;
		}

		@Override
		public boolean isModified(DashboardDto container) {
			return container.isReloadIntervalModified();
		}
	};

	private boolean singlePage;
	private  boolean singlePage_m;
	public static final String PROPERTY_SINGLE_PAGE = "dpi-dashboard-singlepage";

	private transient static PropertyAccessor<DashboardDto, Boolean> singlePage_pa = new PropertyAccessor<DashboardDto, Boolean>() {
		@Override
		public void setValue(DashboardDto container, Boolean object) {
			container.setSinglePage(object);
		}

		@Override
		public Boolean getValue(DashboardDto container) {
			return container.isSinglePage();
		}

		@Override
		public Class<?> getType() {
			return Boolean.class;
		}

		@Override
		public String getPath() {
			return "singlePage";
		}

		@Override
		public void setModified(DashboardDto container, boolean modified) {
			container.singlePage_m = modified;
		}

		@Override
		public boolean isModified(DashboardDto container) {
			return container.isSinglePageModified();
		}
	};


	public DashboardDto() {
		super();
	}

	public List<DadgetDto> getDadgets()  {
		if(! isDtoProxy()){
			List<DadgetDto> _currentValue = this.dadgets;
			if(null == _currentValue)
				this.dadgets = new ArrayList<DadgetDto>();

			return this.dadgets;
		}

		if(isDadgetsModified())
			return this.dadgets;

		if(! GWT.isClient())
			return null;

		List<DadgetDto> _value = dtoManager.getProperty(this, instantiatePropertyAccess().dadgets());

		_value = new ChangeMonitoredList<DadgetDto>(_value);
		if(_value instanceof HasObjectChangedEventHandler){
			((HasObjectChangedEventHandler)_value).addObjectChangedHandler(new net.datenwerke.gxtdto.client.eventbus.handlers.ObjectChangedEventHandler(){
				@Override
				public void onObjectChangedEvent(net.datenwerke.gxtdto.client.eventbus.events.ObjectChangedEvent event){
					if(! isDadgetsModified())
						setDadgets((List<DadgetDto>) ((MonitoredCollection) event.getObject()).getUnderlyingCollection());
				}
			}
			);
		}
		return _value;
	}


	public void setDadgets(List<DadgetDto> dadgets)  {
		/* old value */
		List<DadgetDto> oldValue = null;
		if(GWT.isClient())
			oldValue = getDadgets();

		/* set new value */
		this.dadgets = dadgets;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(dadgets_pa, oldValue, dadgets, this.dadgets_m));

		/* set indicator */
		this.dadgets_m = true;

		this.fireObjectChangedEvent(DashboardDtoPA.INSTANCE.dadgets(), oldValue);
	}


	public boolean isDadgetsModified()  {
		return dadgets_m;
	}


	public static PropertyAccessor<DashboardDto, List<DadgetDto>> getDadgetsPropertyAccessor()  {
		return dadgets_pa;
	}


	public String getDescription()  {
		if(! isDtoProxy()){
			return this.description;
		}

		if(isDescriptionModified())
			return this.description;

		if(! GWT.isClient())
			return null;

		String _value = dtoManager.getProperty(this, instantiatePropertyAccess().description());

		return _value;
	}


	public void setDescription(String description)  {
		/* old value */
		String oldValue = null;
		if(GWT.isClient())
			oldValue = getDescription();

		/* set new value */
		this.description = description;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(description_pa, oldValue, description, this.description_m));

		/* set indicator */
		this.description_m = true;

		this.fireObjectChangedEvent(DashboardDtoPA.INSTANCE.description(), oldValue);
	}


	public boolean isDescriptionModified()  {
		return description_m;
	}


	public static PropertyAccessor<DashboardDto, String> getDescriptionPropertyAccessor()  {
		return description_pa;
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


	public static PropertyAccessor<DashboardDto, Long> getIdPropertyAccessor()  {
		return id_pa;
	}


	public LayoutTypeDto getLayout()  {
		if(! isDtoProxy()){
			return this.layout;
		}

		if(isLayoutModified())
			return this.layout;

		if(! GWT.isClient())
			return null;

		LayoutTypeDto _value = dtoManager.getProperty(this, instantiatePropertyAccess().layout());

		return _value;
	}


	public void setLayout(LayoutTypeDto layout)  {
		/* old value */
		LayoutTypeDto oldValue = null;
		if(GWT.isClient())
			oldValue = getLayout();

		/* set new value */
		this.layout = layout;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(layout_pa, oldValue, layout, this.layout_m));

		/* set indicator */
		this.layout_m = true;

		this.fireObjectChangedEvent(DashboardDtoPA.INSTANCE.layout(), oldValue);
	}


	public boolean isLayoutModified()  {
		return layout_m;
	}


	public static PropertyAccessor<DashboardDto, LayoutTypeDto> getLayoutPropertyAccessor()  {
		return layout_pa;
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

		this.fireObjectChangedEvent(DashboardDtoPA.INSTANCE.n(), oldValue);
	}


	public boolean isNModified()  {
		return n_m;
	}


	public static PropertyAccessor<DashboardDto, Integer> getNPropertyAccessor()  {
		return n_pa;
	}


	public String getName()  {
		if(! isDtoProxy()){
			return this.name;
		}

		if(isNameModified())
			return this.name;

		if(! GWT.isClient())
			return null;

		String _value = dtoManager.getProperty(this, instantiatePropertyAccess().name());

		return _value;
	}


	public void setName(String name)  {
		/* old value */
		String oldValue = null;
		if(GWT.isClient())
			oldValue = getName();

		/* set new value */
		this.name = name;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(name_pa, oldValue, name, this.name_m));

		/* set indicator */
		this.name_m = true;

		this.fireObjectChangedEvent(DashboardDtoPA.INSTANCE.name(), oldValue);
	}


	public boolean isNameModified()  {
		return name_m;
	}


	public static PropertyAccessor<DashboardDto, String> getNamePropertyAccessor()  {
		return name_pa;
	}


	public boolean isPrimary()  {
		if(! isDtoProxy()){
			return this.primary;
		}

		if(isPrimaryModified())
			return this.primary;

		if(! GWT.isClient())
			return false;

		boolean _value = dtoManager.getProperty(this, instantiatePropertyAccess().primary());

		return _value;
	}


	public void setPrimary(boolean primary)  {
		/* old value */
		boolean oldValue = false;
		if(GWT.isClient())
			oldValue = isPrimary();

		/* set new value */
		this.primary = primary;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(primary_pa, oldValue, primary, this.primary_m));

		/* set indicator */
		this.primary_m = true;

		this.fireObjectChangedEvent(DashboardDtoPA.INSTANCE.primary(), oldValue);
	}


	public boolean isPrimaryModified()  {
		return primary_m;
	}


	public static PropertyAccessor<DashboardDto, Boolean> getPrimaryPropertyAccessor()  {
		return primary_pa;
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

		this.fireObjectChangedEvent(DashboardDtoPA.INSTANCE.reloadInterval(), oldValue);
	}


	public boolean isReloadIntervalModified()  {
		return reloadInterval_m;
	}


	public static PropertyAccessor<DashboardDto, Long> getReloadIntervalPropertyAccessor()  {
		return reloadInterval_pa;
	}


	public boolean isSinglePage()  {
		if(! isDtoProxy()){
			return this.singlePage;
		}

		if(isSinglePageModified())
			return this.singlePage;

		if(! GWT.isClient())
			return false;

		boolean _value = dtoManager.getProperty(this, instantiatePropertyAccess().singlePage());

		return _value;
	}


	public void setSinglePage(boolean singlePage)  {
		/* old value */
		boolean oldValue = false;
		if(GWT.isClient())
			oldValue = isSinglePage();

		/* set new value */
		this.singlePage = singlePage;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(singlePage_pa, oldValue, singlePage, this.singlePage_m));

		/* set indicator */
		this.singlePage_m = true;

		this.fireObjectChangedEvent(DashboardDtoPA.INSTANCE.singlePage(), oldValue);
	}


	public boolean isSinglePageModified()  {
		return singlePage_m;
	}


	public static PropertyAccessor<DashboardDto, Boolean> getSinglePagePropertyAccessor()  {
		return singlePage_pa;
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
		if(! (obj instanceof DashboardDto))
			return false;

		if(null == getId())
			return super.equals(obj);
		return getId().equals(((DashboardDto)obj).getId());
	}

	@Override
	public String toString()  {
		return getClass().getName() + ": " + getId();
	}

	public static Dto2PosoMapper newPosoMapper()  {
		return new DashboardDto2PosoMap();
	}

	public DashboardDtoPA instantiatePropertyAccess()  {
		return GWT.create(DashboardDtoPA.class);
	}

	public void clearModified()  {
		this.dadgets = null;
		this.dadgets_m = false;
		this.description = null;
		this.description_m = false;
		this.id = null;
		this.id_m = false;
		this.layout = null;
		this.layout_m = false;
		this.n = 0;
		this.n_m = false;
		this.name = null;
		this.name_m = false;
		this.primary = false;
		this.primary_m = false;
		this.reloadInterval = 0;
		this.reloadInterval_m = false;
		this.singlePage = false;
		this.singlePage_m = false;
	}


	public boolean isModified()  {
		if(super.isModified())
			return true;
		if(dadgets_m)
			return true;
		if(description_m)
			return true;
		if(id_m)
			return true;
		if(layout_m)
			return true;
		if(n_m)
			return true;
		if(name_m)
			return true;
		if(primary_m)
			return true;
		if(reloadInterval_m)
			return true;
		if(singlePage_m)
			return true;
		return false;
	}


	public List<PropertyAccessor> getPropertyAccessors()  {
		List<PropertyAccessor> list = super.getPropertyAccessors();
		list.add(dadgets_pa);
		list.add(description_pa);
		list.add(id_pa);
		list.add(layout_pa);
		list.add(n_pa);
		list.add(name_pa);
		list.add(primary_pa);
		list.add(reloadInterval_pa);
		list.add(singlePage_pa);
		return list;
	}


	public List<PropertyAccessor> getModifiedPropertyAccessors()  {
		List<PropertyAccessor> list = super.getModifiedPropertyAccessors();
		if(dadgets_m)
			list.add(dadgets_pa);
		if(description_m)
			list.add(description_pa);
		if(id_m)
			list.add(id_pa);
		if(layout_m)
			list.add(layout_pa);
		if(n_m)
			list.add(n_pa);
		if(name_m)
			list.add(name_pa);
		if(primary_m)
			list.add(primary_pa);
		if(reloadInterval_m)
			list.add(reloadInterval_pa);
		if(singlePage_m)
			list.add(singlePage_pa);
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsByView(net.datenwerke.gxtdto.client.dtomanager.DtoView view)  {
		List<PropertyAccessor> list = super.getPropertyAccessorsByView(view);
		if(view.compareTo(DtoView.MINIMAL) >= 0){
			list.add(id_pa);
			list.add(name_pa);
		}
		if(view.compareTo(DtoView.NORMAL) >= 0){
			list.add(dadgets_pa);
			list.add(description_pa);
			list.add(layout_pa);
			list.add(n_pa);
			list.add(primary_pa);
			list.add(reloadInterval_pa);
			list.add(singlePage_pa);
		}
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsForDtos()  {
		List<PropertyAccessor> list = super.getPropertyAccessorsForDtos();
		list.add(dadgets_pa);
		return list;
	}



	net.datenwerke.rs.dashboard.client.dashboard.dto.LayoutTypeDto wl_0;
	net.datenwerke.rs.dashboard.client.dashboard.dto.DadgetDto wl_1;

}
