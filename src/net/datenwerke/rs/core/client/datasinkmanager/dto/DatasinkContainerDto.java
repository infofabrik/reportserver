package net.datenwerke.rs.core.client.datasinkmanager.dto;

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
import net.datenwerke.rs.core.client.datasinkmanager.dto.DatasinkDefinitionDto;
import net.datenwerke.rs.core.client.datasinkmanager.dto.pa.DatasinkContainerDtoPA;
import net.datenwerke.rs.core.client.datasinkmanager.dto.posomap.DatasinkContainerDto2PosoMap;
import net.datenwerke.rs.core.service.datasinkmanager.entities.DatasinkContainer;

/**
 * Dto for {@link DatasinkContainer}
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class DatasinkContainerDto extends RsDto implements IdedDto {


	private static final long serialVersionUID = 1;

	private Long dtoId;


	/* Fields */
	private DatasinkDefinitionDto datasink;
	private  boolean datasink_m;
	public static final String PROPERTY_DATASINK = "dpi-datasinkcontainer-datasink";

	private transient static PropertyAccessor<DatasinkContainerDto, DatasinkDefinitionDto> datasink_pa = new PropertyAccessor<DatasinkContainerDto, DatasinkDefinitionDto>() {
		@Override
		public void setValue(DatasinkContainerDto container, DatasinkDefinitionDto object) {
			container.setDatasink(object);
		}

		@Override
		public DatasinkDefinitionDto getValue(DatasinkContainerDto container) {
			return container.getDatasink();
		}

		@Override
		public Class<?> getType() {
			return DatasinkDefinitionDto.class;
		}

		@Override
		public String getPath() {
			return "datasink";
		}

		@Override
		public void setModified(DatasinkContainerDto container, boolean modified) {
			container.datasink_m = modified;
		}

		@Override
		public boolean isModified(DatasinkContainerDto container) {
			return container.isDatasinkModified();
		}
	};

	private Long id;
	private  boolean id_m;
	public static final String PROPERTY_ID = "dpi-datasinkcontainer-id";

	private transient static PropertyAccessor<DatasinkContainerDto, Long> id_pa = new PropertyAccessor<DatasinkContainerDto, Long>() {
		@Override
		public void setValue(DatasinkContainerDto container, Long object) {
			// id field
		}

		@Override
		public Long getValue(DatasinkContainerDto container) {
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
		public void setModified(DatasinkContainerDto container, boolean modified) {
			container.id_m = modified;
		}

		@Override
		public boolean isModified(DatasinkContainerDto container) {
			return container.isIdModified();
		}
	};


	public DatasinkContainerDto() {
		super();
	}

	public DatasinkDefinitionDto getDatasink()  {
		if(! isDtoProxy()){
			return this.datasink;
		}

		if(isDatasinkModified())
			return this.datasink;

		if(! GWT.isClient())
			return null;

		DatasinkDefinitionDto _value = dtoManager.getProperty(this, instantiatePropertyAccess().datasink());

		if(_value instanceof HasObjectChangedEventHandler){
			((HasObjectChangedEventHandler)_value).addObjectChangedHandler(new net.datenwerke.gxtdto.client.eventbus.handlers.ObjectChangedEventHandler(){
				@Override
				public void onObjectChangedEvent(net.datenwerke.gxtdto.client.eventbus.events.ObjectChangedEvent event){
					if(! isDatasinkModified())
						setDatasink((DatasinkDefinitionDto) event.getObject());
				}
			}
			);
		}
		return _value;
	}


	public void setDatasink(DatasinkDefinitionDto datasink)  {
		/* old value */
		DatasinkDefinitionDto oldValue = null;
		if(GWT.isClient())
			oldValue = getDatasink();

		/* set new value */
		this.datasink = datasink;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(datasink_pa, oldValue, datasink, this.datasink_m));

		/* set indicator */
		this.datasink_m = true;

		this.fireObjectChangedEvent(DatasinkContainerDtoPA.INSTANCE.datasink(), oldValue);
	}


	public boolean isDatasinkModified()  {
		return datasink_m;
	}


	public static PropertyAccessor<DatasinkContainerDto, DatasinkDefinitionDto> getDatasinkPropertyAccessor()  {
		return datasink_pa;
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


	public static PropertyAccessor<DatasinkContainerDto, Long> getIdPropertyAccessor()  {
		return id_pa;
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
		if(! (obj instanceof DatasinkContainerDto))
			return false;

		if(null == getId())
			return super.equals(obj);
		return getId().equals(((DatasinkContainerDto)obj).getId());
	}

	@Override
	public String toString()  {
		return getClass().getName() + ": " + getId();
	}

	public static Dto2PosoMapper newPosoMapper()  {
		return new DatasinkContainerDto2PosoMap();
	}

	public DatasinkContainerDtoPA instantiatePropertyAccess()  {
		return GWT.create(DatasinkContainerDtoPA.class);
	}

	public void clearModified()  {
		this.datasink = null;
		this.datasink_m = false;
		this.id = null;
		this.id_m = false;
	}


	public boolean isModified()  {
		if(super.isModified())
			return true;
		if(datasink_m)
			return true;
		if(id_m)
			return true;
		return false;
	}


	public List<PropertyAccessor> getPropertyAccessors()  {
		List<PropertyAccessor> list = super.getPropertyAccessors();
		list.add(datasink_pa);
		list.add(id_pa);
		return list;
	}


	public List<PropertyAccessor> getModifiedPropertyAccessors()  {
		List<PropertyAccessor> list = super.getModifiedPropertyAccessors();
		if(datasink_m)
			list.add(datasink_pa);
		if(id_m)
			list.add(id_pa);
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsByView(net.datenwerke.gxtdto.client.dtomanager.DtoView view)  {
		List<PropertyAccessor> list = super.getPropertyAccessorsByView(view);
		if(view.compareTo(DtoView.MINIMAL) >= 0){
			list.add(id_pa);
		}
		if(view.compareTo(DtoView.NORMAL) >= 0){
			list.add(datasink_pa);
		}
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsForDtos()  {
		List<PropertyAccessor> list = super.getPropertyAccessorsForDtos();
		list.add(datasink_pa);
		return list;
	}



	net.datenwerke.rs.core.client.datasinkmanager.dto.DatasinkDefinitionDto wl_0;

}
