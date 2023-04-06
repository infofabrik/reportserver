package net.datenwerke.rs.remoteserver.client.remoteservermanager.dto;

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
import net.datenwerke.rs.remoteserver.client.remoteservermanager.dto.RemoteServerDefinitionDto;
import net.datenwerke.rs.remoteserver.client.remoteservermanager.dto.pa.RemoteServerContainerDtoPA;
import net.datenwerke.rs.remoteserver.client.remoteservermanager.dto.posomap.RemoteServerContainerDto2PosoMap;
import net.datenwerke.rs.remoteserver.service.remoteservermanager.entities.RemoteServerContainer;

/**
 * Dto for {@link RemoteServerContainer}
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class RemoteServerContainerDto extends RsDto implements IdedDto {


	private static final long serialVersionUID = 1;

	private Long dtoId;


	/* Fields */
	private Long id;
	private  boolean id_m;
	public static final String PROPERTY_ID = "dpi-remoteservercontainer-id";

	private transient static PropertyAccessor<RemoteServerContainerDto, Long> id_pa = new PropertyAccessor<RemoteServerContainerDto, Long>() {
		@Override
		public void setValue(RemoteServerContainerDto container, Long object) {
			// id field
		}

		@Override
		public Long getValue(RemoteServerContainerDto container) {
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
		public void setModified(RemoteServerContainerDto container, boolean modified) {
			container.id_m = modified;
		}

		@Override
		public boolean isModified(RemoteServerContainerDto container) {
			return container.isIdModified();
		}
	};

	private RemoteServerDefinitionDto remoteServer;
	private  boolean remoteServer_m;
	public static final String PROPERTY_REMOTE_SERVER = "dpi-remoteservercontainer-remoteserver";

	private transient static PropertyAccessor<RemoteServerContainerDto, RemoteServerDefinitionDto> remoteServer_pa = new PropertyAccessor<RemoteServerContainerDto, RemoteServerDefinitionDto>() {
		@Override
		public void setValue(RemoteServerContainerDto container, RemoteServerDefinitionDto object) {
			container.setRemoteServer(object);
		}

		@Override
		public RemoteServerDefinitionDto getValue(RemoteServerContainerDto container) {
			return container.getRemoteServer();
		}

		@Override
		public Class<?> getType() {
			return RemoteServerDefinitionDto.class;
		}

		@Override
		public String getPath() {
			return "remoteServer";
		}

		@Override
		public void setModified(RemoteServerContainerDto container, boolean modified) {
			container.remoteServer_m = modified;
		}

		@Override
		public boolean isModified(RemoteServerContainerDto container) {
			return container.isRemoteServerModified();
		}
	};


	public RemoteServerContainerDto() {
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


	public static PropertyAccessor<RemoteServerContainerDto, Long> getIdPropertyAccessor()  {
		return id_pa;
	}


	public RemoteServerDefinitionDto getRemoteServer()  {
		if(! isDtoProxy()){
			return this.remoteServer;
		}

		if(isRemoteServerModified())
			return this.remoteServer;

		if(! GWT.isClient())
			return null;

		RemoteServerDefinitionDto _value = dtoManager.getProperty(this, instantiatePropertyAccess().remoteServer());

		if(_value instanceof HasObjectChangedEventHandler){
			((HasObjectChangedEventHandler)_value).addObjectChangedHandler(new net.datenwerke.gxtdto.client.eventbus.handlers.ObjectChangedEventHandler(){
				@Override
				public void onObjectChangedEvent(net.datenwerke.gxtdto.client.eventbus.events.ObjectChangedEvent event){
					if(! isRemoteServerModified())
						setRemoteServer((RemoteServerDefinitionDto) event.getObject());
				}
			}
			);
		}
		return _value;
	}


	public void setRemoteServer(RemoteServerDefinitionDto remoteServer)  {
		/* old value */
		RemoteServerDefinitionDto oldValue = null;
		if(GWT.isClient())
			oldValue = getRemoteServer();

		/* set new value */
		this.remoteServer = remoteServer;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(remoteServer_pa, oldValue, remoteServer, this.remoteServer_m));

		/* set indicator */
		this.remoteServer_m = true;

		this.fireObjectChangedEvent(RemoteServerContainerDtoPA.INSTANCE.remoteServer(), oldValue);
	}


	public boolean isRemoteServerModified()  {
		return remoteServer_m;
	}


	public static PropertyAccessor<RemoteServerContainerDto, RemoteServerDefinitionDto> getRemoteServerPropertyAccessor()  {
		return remoteServer_pa;
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
		if(! (obj instanceof RemoteServerContainerDto))
			return false;

		if(null == getId())
			return super.equals(obj);
		return getId().equals(((RemoteServerContainerDto)obj).getId());
	}

	@Override
	public String toString()  {
		return getClass().getName() + ": " + getId();
	}

	public static Dto2PosoMapper newPosoMapper()  {
		return new RemoteServerContainerDto2PosoMap();
	}

	public RemoteServerContainerDtoPA instantiatePropertyAccess()  {
		return GWT.create(RemoteServerContainerDtoPA.class);
	}

	public void clearModified()  {
		this.id = null;
		this.id_m = false;
		this.remoteServer = null;
		this.remoteServer_m = false;
	}


	public boolean isModified()  {
		if(super.isModified())
			return true;
		if(id_m)
			return true;
		if(remoteServer_m)
			return true;
		return false;
	}


	public List<PropertyAccessor> getPropertyAccessors()  {
		List<PropertyAccessor> list = super.getPropertyAccessors();
		list.add(id_pa);
		list.add(remoteServer_pa);
		return list;
	}


	public List<PropertyAccessor> getModifiedPropertyAccessors()  {
		List<PropertyAccessor> list = super.getModifiedPropertyAccessors();
		if(id_m)
			list.add(id_pa);
		if(remoteServer_m)
			list.add(remoteServer_pa);
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsByView(net.datenwerke.gxtdto.client.dtomanager.DtoView view)  {
		List<PropertyAccessor> list = super.getPropertyAccessorsByView(view);
		if(view.compareTo(DtoView.MINIMAL) >= 0){
			list.add(id_pa);
		}
		if(view.compareTo(DtoView.NORMAL) >= 0){
			list.add(remoteServer_pa);
		}
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsForDtos()  {
		List<PropertyAccessor> list = super.getPropertyAccessorsForDtos();
		list.add(remoteServer_pa);
		return list;
	}



	net.datenwerke.rs.remoteserver.client.remoteservermanager.dto.RemoteServerDefinitionDto wl_0;

}
