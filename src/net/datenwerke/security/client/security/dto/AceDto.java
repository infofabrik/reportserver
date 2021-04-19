package net.datenwerke.security.client.security.dto;

import com.google.gwt.core.client.GWT;
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
import net.datenwerke.security.client.security.dto.AccessTypeDto;
import net.datenwerke.security.client.security.dto.AceAccessMapDto;
import net.datenwerke.security.client.security.dto.pa.AceDtoPA;
import net.datenwerke.security.client.security.dto.posomap.AceDto2PosoMap;
import net.datenwerke.security.client.usermanager.dto.AbstractUserManagerNodeDto;
import net.datenwerke.security.service.security.entities.Ace;

/**
 * Dto for {@link Ace}
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
abstract public class AceDto extends RsDto implements IdedDto {


	private static final long serialVersionUID = 1;


	/* Fields */
	private Set<AceAccessMapDto> accessMaps;
	private  boolean accessMaps_m;
	public static final String PROPERTY_ACCESS_MAPS = "dpi-ace-accessmaps";

	private transient static PropertyAccessor<AceDto, Set<AceAccessMapDto>> accessMaps_pa = new PropertyAccessor<AceDto, Set<AceAccessMapDto>>() {
		@Override
		public void setValue(AceDto container, Set<AceAccessMapDto> object) {
			container.setAccessMaps(object);
		}

		@Override
		public Set<AceAccessMapDto> getValue(AceDto container) {
			return container.getAccessMaps();
		}

		@Override
		public Class<?> getType() {
			return Set.class;
		}

		@Override
		public String getPath() {
			return "accessMaps";
		}

		@Override
		public void setModified(AceDto container, boolean modified) {
			container.accessMaps_m = modified;
		}

		@Override
		public boolean isModified(AceDto container) {
			return container.isAccessMapsModified();
		}
	};

	private AccessTypeDto accesstype;
	private  boolean accesstype_m;
	public static final String PROPERTY_ACCESSTYPE = "dpi-ace-accesstype";

	private transient static PropertyAccessor<AceDto, AccessTypeDto> accesstype_pa = new PropertyAccessor<AceDto, AccessTypeDto>() {
		@Override
		public void setValue(AceDto container, AccessTypeDto object) {
			container.setAccesstype(object);
		}

		@Override
		public AccessTypeDto getValue(AceDto container) {
			return container.getAccesstype();
		}

		@Override
		public Class<?> getType() {
			return AccessTypeDto.class;
		}

		@Override
		public String getPath() {
			return "accesstype";
		}

		@Override
		public void setModified(AceDto container, boolean modified) {
			container.accesstype_m = modified;
		}

		@Override
		public boolean isModified(AceDto container) {
			return container.isAccesstypeModified();
		}
	};

	private AbstractUserManagerNodeDto folk;
	private  boolean folk_m;
	public static final String PROPERTY_FOLK = "dpi-ace-folk";

	private transient static PropertyAccessor<AceDto, AbstractUserManagerNodeDto> folk_pa = new PropertyAccessor<AceDto, AbstractUserManagerNodeDto>() {
		@Override
		public void setValue(AceDto container, AbstractUserManagerNodeDto object) {
			container.setFolk(object);
		}

		@Override
		public AbstractUserManagerNodeDto getValue(AceDto container) {
			return container.getFolk();
		}

		@Override
		public Class<?> getType() {
			return AbstractUserManagerNodeDto.class;
		}

		@Override
		public String getPath() {
			return "folk";
		}

		@Override
		public void setModified(AceDto container, boolean modified) {
			container.folk_m = modified;
		}

		@Override
		public boolean isModified(AceDto container) {
			return container.isFolkModified();
		}
	};

	private Long id;
	private  boolean id_m;
	public static final String PROPERTY_ID = "dpi-ace-id";

	private transient static PropertyAccessor<AceDto, Long> id_pa = new PropertyAccessor<AceDto, Long>() {
		@Override
		public void setValue(AceDto container, Long object) {
			// id field
		}

		@Override
		public Long getValue(AceDto container) {
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
		public void setModified(AceDto container, boolean modified) {
			container.id_m = modified;
		}

		@Override
		public boolean isModified(AceDto container) {
			return container.isIdModified();
		}
	};

	private int n;
	private  boolean n_m;
	public static final String PROPERTY_N = "dpi-ace-n";

	private transient static PropertyAccessor<AceDto, Integer> n_pa = new PropertyAccessor<AceDto, Integer>() {
		@Override
		public void setValue(AceDto container, Integer object) {
			container.setN(object);
		}

		@Override
		public Integer getValue(AceDto container) {
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
		public void setModified(AceDto container, boolean modified) {
			container.n_m = modified;
		}

		@Override
		public boolean isModified(AceDto container) {
			return container.isNModified();
		}
	};


	public AceDto() {
		super();
	}

	public Set<AceAccessMapDto> getAccessMaps()  {
		if(! isDtoProxy()){
			Set<AceAccessMapDto> _currentValue = this.accessMaps;
			if(null == _currentValue)
				this.accessMaps = new HashSet<AceAccessMapDto>();

			return this.accessMaps;
		}

		if(isAccessMapsModified())
			return this.accessMaps;

		if(! GWT.isClient())
			return null;

		Set<AceAccessMapDto> _value = dtoManager.getProperty(this, instantiatePropertyAccess().accessMaps());

		_value = new ChangeMonitoredSet<AceAccessMapDto>(_value);
		if(_value instanceof HasObjectChangedEventHandler){
			((HasObjectChangedEventHandler)_value).addObjectChangedHandler(new net.datenwerke.gxtdto.client.eventbus.handlers.ObjectChangedEventHandler(){
				@Override
				public void onObjectChangedEvent(net.datenwerke.gxtdto.client.eventbus.events.ObjectChangedEvent event){
					if(! isAccessMapsModified())
						setAccessMaps((Set<AceAccessMapDto>) ((MonitoredCollection) event.getObject()).getUnderlyingCollection());
				}
			}
			);
		}
		return _value;
	}


	public void setAccessMaps(Set<AceAccessMapDto> accessMaps)  {
		/* old value */
		Set<AceAccessMapDto> oldValue = null;
		if(GWT.isClient())
			oldValue = getAccessMaps();

		/* set new value */
		this.accessMaps = accessMaps;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(accessMaps_pa, oldValue, accessMaps, this.accessMaps_m));

		/* set indicator */
		this.accessMaps_m = true;

		this.fireObjectChangedEvent(AceDtoPA.INSTANCE.accessMaps(), oldValue);
	}


	public boolean isAccessMapsModified()  {
		return accessMaps_m;
	}


	public static PropertyAccessor<AceDto, Set<AceAccessMapDto>> getAccessMapsPropertyAccessor()  {
		return accessMaps_pa;
	}


	public AccessTypeDto getAccesstype()  {
		if(! isDtoProxy()){
			return this.accesstype;
		}

		if(isAccesstypeModified())
			return this.accesstype;

		if(! GWT.isClient())
			return null;

		AccessTypeDto _value = dtoManager.getProperty(this, instantiatePropertyAccess().accesstype());

		return _value;
	}


	public void setAccesstype(AccessTypeDto accesstype)  {
		/* old value */
		AccessTypeDto oldValue = null;
		if(GWT.isClient())
			oldValue = getAccesstype();

		/* set new value */
		this.accesstype = accesstype;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(accesstype_pa, oldValue, accesstype, this.accesstype_m));

		/* set indicator */
		this.accesstype_m = true;

		this.fireObjectChangedEvent(AceDtoPA.INSTANCE.accesstype(), oldValue);
	}


	public boolean isAccesstypeModified()  {
		return accesstype_m;
	}


	public static PropertyAccessor<AceDto, AccessTypeDto> getAccesstypePropertyAccessor()  {
		return accesstype_pa;
	}


	public AbstractUserManagerNodeDto getFolk()  {
		if(! isDtoProxy()){
			return this.folk;
		}

		if(isFolkModified())
			return this.folk;

		if(! GWT.isClient())
			return null;

		AbstractUserManagerNodeDto _value = dtoManager.getProperty(this, instantiatePropertyAccess().folk());

		if(_value instanceof HasObjectChangedEventHandler){
			((HasObjectChangedEventHandler)_value).addObjectChangedHandler(new net.datenwerke.gxtdto.client.eventbus.handlers.ObjectChangedEventHandler(){
				@Override
				public void onObjectChangedEvent(net.datenwerke.gxtdto.client.eventbus.events.ObjectChangedEvent event){
					if(! isFolkModified())
						setFolk((AbstractUserManagerNodeDto) event.getObject());
				}
			}
			);
		}
		return _value;
	}


	public void setFolk(AbstractUserManagerNodeDto folk)  {
		/* old value */
		AbstractUserManagerNodeDto oldValue = null;
		if(GWT.isClient())
			oldValue = getFolk();

		/* set new value */
		this.folk = folk;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(folk_pa, oldValue, folk, this.folk_m));

		/* set indicator */
		this.folk_m = true;

		this.fireObjectChangedEvent(AceDtoPA.INSTANCE.folk(), oldValue);
	}


	public boolean isFolkModified()  {
		return folk_m;
	}


	public static PropertyAccessor<AceDto, AbstractUserManagerNodeDto> getFolkPropertyAccessor()  {
		return folk_pa;
	}


	public Long getId()  {
		if(! isDtoProxy()){
			return this.id;
		}

		if(isIdModified())
			return this.id;

		if(! GWT.isClient())
			return null;

		Long _value = dtoManager.getProperty(this, instantiatePropertyAccess().id());

		return _value;
	}


	public void setId(Long id)  {
		/* old value */
		Long oldValue = null;
		if(GWT.isClient())
			oldValue = getId();

		/* set new value */
		this.id = id;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(id_pa, oldValue, id, this.id_m));

		/* set indicator */
		this.id_m = true;

		this.fireObjectChangedEvent(AceDtoPA.INSTANCE.id(), oldValue);
	}


	public boolean isIdModified()  {
		return id_m;
	}


	public static PropertyAccessor<AceDto, Long> getIdPropertyAccessor()  {
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

		this.fireObjectChangedEvent(AceDtoPA.INSTANCE.n(), oldValue);
	}


	public boolean isNModified()  {
		return n_m;
	}


	public static PropertyAccessor<AceDto, Integer> getNPropertyAccessor()  {
		return n_pa;
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
		if(! (obj instanceof AceDto))
			return false;

		if(null == getId())
			return super.equals(obj);
		return getId().equals(((AceDto)obj).getId());
	}

	@Override
	public String toString()  {
		return getClass().getName() + ": " + getId();
	}

	public static Dto2PosoMapper newPosoMapper()  {
		return new AceDto2PosoMap();
	}

	public AceDtoPA instantiatePropertyAccess()  {
		return GWT.create(AceDtoPA.class);
	}

	public void clearModified()  {
		this.accessMaps = null;
		this.accessMaps_m = false;
		this.accesstype = null;
		this.accesstype_m = false;
		this.folk = null;
		this.folk_m = false;
		this.id = null;
		this.id_m = false;
		this.n = 0;
		this.n_m = false;
	}


	public boolean isModified()  {
		if(super.isModified())
			return true;
		if(accessMaps_m)
			return true;
		if(accesstype_m)
			return true;
		if(folk_m)
			return true;
		if(id_m)
			return true;
		if(n_m)
			return true;
		return false;
	}


	public List<PropertyAccessor> getPropertyAccessors()  {
		List<PropertyAccessor> list = super.getPropertyAccessors();
		list.add(accessMaps_pa);
		list.add(accesstype_pa);
		list.add(folk_pa);
		list.add(id_pa);
		list.add(n_pa);
		return list;
	}


	public List<PropertyAccessor> getModifiedPropertyAccessors()  {
		List<PropertyAccessor> list = super.getModifiedPropertyAccessors();
		if(accessMaps_m)
			list.add(accessMaps_pa);
		if(accesstype_m)
			list.add(accesstype_pa);
		if(folk_m)
			list.add(folk_pa);
		if(id_m)
			list.add(id_pa);
		if(n_m)
			list.add(n_pa);
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsByView(net.datenwerke.gxtdto.client.dtomanager.DtoView view)  {
		List<PropertyAccessor> list = super.getPropertyAccessorsByView(view);
		if(view.compareTo(DtoView.MINIMAL) >= 0){
			list.add(id_pa);
		}
		if(view.compareTo(DtoView.NORMAL) >= 0){
			list.add(accessMaps_pa);
			list.add(accesstype_pa);
			list.add(folk_pa);
			list.add(n_pa);
		}
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsForDtos()  {
		List<PropertyAccessor> list = super.getPropertyAccessorsForDtos();
		list.add(accessMaps_pa);
		list.add(folk_pa);
		return list;
	}



	net.datenwerke.security.client.security.dto.AceAccessMapDto wl_0;
	net.datenwerke.security.client.security.dto.AccessTypeDto wl_1;
	net.datenwerke.security.client.usermanager.dto.AbstractUserManagerNodeDto wl_2;

}
