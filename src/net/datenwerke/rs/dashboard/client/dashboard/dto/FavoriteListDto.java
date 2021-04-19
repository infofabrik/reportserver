package net.datenwerke.rs.dashboard.client.dashboard.dto;

import com.google.gwt.core.client.GWT;
import java.lang.IllegalStateException;
import java.lang.Long;
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
import net.datenwerke.rs.dashboard.client.dashboard.dto.FavoriteListEntryDto;
import net.datenwerke.rs.dashboard.client.dashboard.dto.pa.FavoriteListDtoPA;
import net.datenwerke.rs.dashboard.client.dashboard.dto.posomap.FavoriteListDto2PosoMap;
import net.datenwerke.rs.dashboard.service.dashboard.dagets.FavoriteList;

/**
 * Dto for {@link FavoriteList}
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
abstract public class FavoriteListDto extends RsDto implements IdedDto {


	private static final long serialVersionUID = 1;

	private Long dtoId;


	/* Fields */
	private Long id;
	private  boolean id_m;
	public static final String PROPERTY_ID = "dpi-favoritelist-id";

	private transient static PropertyAccessor<FavoriteListDto, Long> id_pa = new PropertyAccessor<FavoriteListDto, Long>() {
		@Override
		public void setValue(FavoriteListDto container, Long object) {
			// id field
		}

		@Override
		public Long getValue(FavoriteListDto container) {
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
		public void setModified(FavoriteListDto container, boolean modified) {
			container.id_m = modified;
		}

		@Override
		public boolean isModified(FavoriteListDto container) {
			return container.isIdModified();
		}
	};

	private List<FavoriteListEntryDto> referenceEntries;
	private  boolean referenceEntries_m;
	public static final String PROPERTY_REFERENCE_ENTRIES = "dpi-favoritelist-referenceentries";

	private transient static PropertyAccessor<FavoriteListDto, List<FavoriteListEntryDto>> referenceEntries_pa = new PropertyAccessor<FavoriteListDto, List<FavoriteListEntryDto>>() {
		@Override
		public void setValue(FavoriteListDto container, List<FavoriteListEntryDto> object) {
			container.setReferenceEntries(object);
		}

		@Override
		public List<FavoriteListEntryDto> getValue(FavoriteListDto container) {
			return container.getReferenceEntries();
		}

		@Override
		public Class<?> getType() {
			return List.class;
		}

		@Override
		public String getPath() {
			return "referenceEntries";
		}

		@Override
		public void setModified(FavoriteListDto container, boolean modified) {
			container.referenceEntries_m = modified;
		}

		@Override
		public boolean isModified(FavoriteListDto container) {
			return container.isReferenceEntriesModified();
		}
	};


	public FavoriteListDto() {
		super();
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


	public static PropertyAccessor<FavoriteListDto, Long> getIdPropertyAccessor()  {
		return id_pa;
	}


	public List<FavoriteListEntryDto> getReferenceEntries()  {
		if(! isDtoProxy()){
			List<FavoriteListEntryDto> _currentValue = this.referenceEntries;
			if(null == _currentValue)
				this.referenceEntries = new ArrayList<FavoriteListEntryDto>();

			return this.referenceEntries;
		}

		if(isReferenceEntriesModified())
			return this.referenceEntries;

		if(! GWT.isClient())
			return null;

		List<FavoriteListEntryDto> _value = dtoManager.getProperty(this, instantiatePropertyAccess().referenceEntries());

		_value = new ChangeMonitoredList<FavoriteListEntryDto>(_value);
		if(_value instanceof HasObjectChangedEventHandler){
			((HasObjectChangedEventHandler)_value).addObjectChangedHandler(new net.datenwerke.gxtdto.client.eventbus.handlers.ObjectChangedEventHandler(){
				@Override
				public void onObjectChangedEvent(net.datenwerke.gxtdto.client.eventbus.events.ObjectChangedEvent event){
					if(! isReferenceEntriesModified())
						setReferenceEntries((List<FavoriteListEntryDto>) ((MonitoredCollection) event.getObject()).getUnderlyingCollection());
				}
			}
			);
		}
		return _value;
	}


	public void setReferenceEntries(List<FavoriteListEntryDto> referenceEntries)  {
		/* old value */
		List<FavoriteListEntryDto> oldValue = null;
		if(GWT.isClient())
			oldValue = getReferenceEntries();

		/* set new value */
		this.referenceEntries = referenceEntries;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(referenceEntries_pa, oldValue, referenceEntries, this.referenceEntries_m));

		/* set indicator */
		this.referenceEntries_m = true;

		this.fireObjectChangedEvent(FavoriteListDtoPA.INSTANCE.referenceEntries(), oldValue);
	}


	public boolean isReferenceEntriesModified()  {
		return referenceEntries_m;
	}


	public static PropertyAccessor<FavoriteListDto, List<FavoriteListEntryDto>> getReferenceEntriesPropertyAccessor()  {
		return referenceEntries_pa;
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
		if(! (obj instanceof FavoriteListDto))
			return false;

		if(null == getId())
			return super.equals(obj);
		return getId().equals(((FavoriteListDto)obj).getId());
	}

	@Override
	public String toString()  {
		return getClass().getName() + ": " + getId();
	}

	public static Dto2PosoMapper newPosoMapper()  {
		return new FavoriteListDto2PosoMap();
	}

	public FavoriteListDtoPA instantiatePropertyAccess()  {
		return GWT.create(FavoriteListDtoPA.class);
	}

	public void clearModified()  {
		this.id = null;
		this.id_m = false;
		this.referenceEntries = null;
		this.referenceEntries_m = false;
	}


	public boolean isModified()  {
		if(super.isModified())
			return true;
		if(id_m)
			return true;
		if(referenceEntries_m)
			return true;
		return false;
	}


	public List<PropertyAccessor> getPropertyAccessors()  {
		List<PropertyAccessor> list = super.getPropertyAccessors();
		list.add(id_pa);
		list.add(referenceEntries_pa);
		return list;
	}


	public List<PropertyAccessor> getModifiedPropertyAccessors()  {
		List<PropertyAccessor> list = super.getModifiedPropertyAccessors();
		if(id_m)
			list.add(id_pa);
		if(referenceEntries_m)
			list.add(referenceEntries_pa);
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsByView(net.datenwerke.gxtdto.client.dtomanager.DtoView view)  {
		List<PropertyAccessor> list = super.getPropertyAccessorsByView(view);
		if(view.compareTo(DtoView.MINIMAL) >= 0){
			list.add(id_pa);
		}
		if(view.compareTo(DtoView.NORMAL) >= 0){
			list.add(referenceEntries_pa);
		}
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsForDtos()  {
		List<PropertyAccessor> list = super.getPropertyAccessorsForDtos();
		list.add(referenceEntries_pa);
		return list;
	}



	net.datenwerke.rs.dashboard.client.dashboard.dto.FavoriteListEntryDto wl_0;

}
