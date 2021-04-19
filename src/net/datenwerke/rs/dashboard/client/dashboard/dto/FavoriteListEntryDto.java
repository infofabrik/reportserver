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
import net.datenwerke.gxtdto.client.dtomanager.redoundo.ChangeTracker;
import net.datenwerke.gxtdto.client.eventbus.events.ObjectChangedEvent;
import net.datenwerke.gxtdto.client.eventbus.handlers.ObjectChangedEventHandler;
import net.datenwerke.gxtdto.client.eventbus.handlers.has.HasObjectChangedEventHandler;
import net.datenwerke.rs.dashboard.client.dashboard.dto.pa.FavoriteListEntryDtoPA;
import net.datenwerke.rs.dashboard.client.dashboard.dto.posomap.FavoriteListEntryDto2PosoMap;
import net.datenwerke.rs.dashboard.service.dashboard.dagets.FavoriteListEntry;
import net.datenwerke.rs.tsreportarea.client.tsreportarea.dto.AbstractTsDiskNodeDto;

/**
 * Dto for {@link FavoriteListEntry}
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
abstract public class FavoriteListEntryDto extends RsDto implements IdedDto {


	private static final long serialVersionUID = 1;

	private Long dtoId;


	/* Fields */
	private Long id;
	private  boolean id_m;
	public static final String PROPERTY_ID = "dpi-favoritelistentry-id";

	private transient static PropertyAccessor<FavoriteListEntryDto, Long> id_pa = new PropertyAccessor<FavoriteListEntryDto, Long>() {
		@Override
		public void setValue(FavoriteListEntryDto container, Long object) {
			// id field
		}

		@Override
		public Long getValue(FavoriteListEntryDto container) {
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
		public void setModified(FavoriteListEntryDto container, boolean modified) {
			container.id_m = modified;
		}

		@Override
		public boolean isModified(FavoriteListEntryDto container) {
			return container.isIdModified();
		}
	};

	private AbstractTsDiskNodeDto referenceEntry;
	private  boolean referenceEntry_m;
	public static final String PROPERTY_REFERENCE_ENTRY = "dpi-favoritelistentry-referenceentry";

	private transient static PropertyAccessor<FavoriteListEntryDto, AbstractTsDiskNodeDto> referenceEntry_pa = new PropertyAccessor<FavoriteListEntryDto, AbstractTsDiskNodeDto>() {
		@Override
		public void setValue(FavoriteListEntryDto container, AbstractTsDiskNodeDto object) {
			container.setReferenceEntry(object);
		}

		@Override
		public AbstractTsDiskNodeDto getValue(FavoriteListEntryDto container) {
			return container.getReferenceEntry();
		}

		@Override
		public Class<?> getType() {
			return AbstractTsDiskNodeDto.class;
		}

		@Override
		public String getPath() {
			return "referenceEntry";
		}

		@Override
		public void setModified(FavoriteListEntryDto container, boolean modified) {
			container.referenceEntry_m = modified;
		}

		@Override
		public boolean isModified(FavoriteListEntryDto container) {
			return container.isReferenceEntryModified();
		}
	};


	public FavoriteListEntryDto() {
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


	public static PropertyAccessor<FavoriteListEntryDto, Long> getIdPropertyAccessor()  {
		return id_pa;
	}


	public AbstractTsDiskNodeDto getReferenceEntry()  {
		if(! isDtoProxy()){
			return this.referenceEntry;
		}

		if(isReferenceEntryModified())
			return this.referenceEntry;

		if(! GWT.isClient())
			return null;

		AbstractTsDiskNodeDto _value = dtoManager.getProperty(this, instantiatePropertyAccess().referenceEntry());

		if(_value instanceof HasObjectChangedEventHandler){
			((HasObjectChangedEventHandler)_value).addObjectChangedHandler(new net.datenwerke.gxtdto.client.eventbus.handlers.ObjectChangedEventHandler(){
				@Override
				public void onObjectChangedEvent(net.datenwerke.gxtdto.client.eventbus.events.ObjectChangedEvent event){
					if(! isReferenceEntryModified())
						setReferenceEntry((AbstractTsDiskNodeDto) event.getObject());
				}
			}
			);
		}
		return _value;
	}


	public void setReferenceEntry(AbstractTsDiskNodeDto referenceEntry)  {
		/* old value */
		AbstractTsDiskNodeDto oldValue = null;
		if(GWT.isClient())
			oldValue = getReferenceEntry();

		/* set new value */
		this.referenceEntry = referenceEntry;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(referenceEntry_pa, oldValue, referenceEntry, this.referenceEntry_m));

		/* set indicator */
		this.referenceEntry_m = true;

		this.fireObjectChangedEvent(FavoriteListEntryDtoPA.INSTANCE.referenceEntry(), oldValue);
	}


	public boolean isReferenceEntryModified()  {
		return referenceEntry_m;
	}


	public static PropertyAccessor<FavoriteListEntryDto, AbstractTsDiskNodeDto> getReferenceEntryPropertyAccessor()  {
		return referenceEntry_pa;
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
		if(! (obj instanceof FavoriteListEntryDto))
			return false;

		if(null == getId())
			return super.equals(obj);
		return getId().equals(((FavoriteListEntryDto)obj).getId());
	}

	@Override
	public String toString()  {
		return getClass().getName() + ": " + getId();
	}

	public static Dto2PosoMapper newPosoMapper()  {
		return new FavoriteListEntryDto2PosoMap();
	}

	public FavoriteListEntryDtoPA instantiatePropertyAccess()  {
		return GWT.create(FavoriteListEntryDtoPA.class);
	}

	public void clearModified()  {
		this.id = null;
		this.id_m = false;
		this.referenceEntry = null;
		this.referenceEntry_m = false;
	}


	public boolean isModified()  {
		if(super.isModified())
			return true;
		if(id_m)
			return true;
		if(referenceEntry_m)
			return true;
		return false;
	}


	public List<PropertyAccessor> getPropertyAccessors()  {
		List<PropertyAccessor> list = super.getPropertyAccessors();
		list.add(id_pa);
		list.add(referenceEntry_pa);
		return list;
	}


	public List<PropertyAccessor> getModifiedPropertyAccessors()  {
		List<PropertyAccessor> list = super.getModifiedPropertyAccessors();
		if(id_m)
			list.add(id_pa);
		if(referenceEntry_m)
			list.add(referenceEntry_pa);
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsByView(net.datenwerke.gxtdto.client.dtomanager.DtoView view)  {
		List<PropertyAccessor> list = super.getPropertyAccessorsByView(view);
		if(view.compareTo(DtoView.MINIMAL) >= 0){
			list.add(id_pa);
		}
		if(view.compareTo(DtoView.NORMAL) >= 0){
			list.add(referenceEntry_pa);
		}
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsForDtos()  {
		List<PropertyAccessor> list = super.getPropertyAccessorsForDtos();
		list.add(referenceEntry_pa);
		return list;
	}



	net.datenwerke.rs.tsreportarea.client.tsreportarea.dto.AbstractTsDiskNodeDto wl_0;

}
