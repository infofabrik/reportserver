package net.datenwerke.security.client.usermanager.dto;

import com.google.gwt.core.client.GWT;
import java.lang.String;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.gxtdto.client.dtomanager.Dto2PosoMapper;
import net.datenwerke.gxtdto.client.dtomanager.DtoView;
import net.datenwerke.gxtdto.client.dtomanager.PropertyAccessor;
import net.datenwerke.gxtdto.client.dtomanager.redoundo.ChangeTracker;
import net.datenwerke.security.client.treedb.dto.decorator.SecuredAbstractNodeDtoDec;
import net.datenwerke.security.client.usermanager.dto.pa.AbstractUserManagerNodeDtoPA;
import net.datenwerke.security.client.usermanager.dto.posomap.AbstractUserManagerNodeDto2PosoMap;
import net.datenwerke.security.service.usermanager.entities.AbstractUserManagerNode;

/**
 * Dto for {@link AbstractUserManagerNode}
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
abstract public class AbstractUserManagerNodeDto extends SecuredAbstractNodeDtoDec {


	private static final long serialVersionUID = 1;


	/* Fields */
	private String guid;
	private  boolean guid_m;
	public static final String PROPERTY_GUID = "dpi-abstractusermanagernode-guid";

	private transient static PropertyAccessor<AbstractUserManagerNodeDto, String> guid_pa = new PropertyAccessor<AbstractUserManagerNodeDto, String>() {
		@Override
		public void setValue(AbstractUserManagerNodeDto container, String object) {
			container.setGuid(object);
		}

		@Override
		public String getValue(AbstractUserManagerNodeDto container) {
			return container.getGuid();
		}

		@Override
		public Class<?> getType() {
			return String.class;
		}

		@Override
		public String getPath() {
			return "guid";
		}

		@Override
		public void setModified(AbstractUserManagerNodeDto container, boolean modified) {
			container.guid_m = modified;
		}

		@Override
		public boolean isModified(AbstractUserManagerNodeDto container) {
			return container.isGuidModified();
		}
	};

	private String origin;
	private  boolean origin_m;
	public static final String PROPERTY_ORIGIN = "dpi-abstractusermanagernode-origin";

	private transient static PropertyAccessor<AbstractUserManagerNodeDto, String> origin_pa = new PropertyAccessor<AbstractUserManagerNodeDto, String>() {
		@Override
		public void setValue(AbstractUserManagerNodeDto container, String object) {
			container.setOrigin(object);
		}

		@Override
		public String getValue(AbstractUserManagerNodeDto container) {
			return container.getOrigin();
		}

		@Override
		public Class<?> getType() {
			return String.class;
		}

		@Override
		public String getPath() {
			return "origin";
		}

		@Override
		public void setModified(AbstractUserManagerNodeDto container, boolean modified) {
			container.origin_m = modified;
		}

		@Override
		public boolean isModified(AbstractUserManagerNodeDto container) {
			return container.isOriginModified();
		}
	};


	public AbstractUserManagerNodeDto() {
		super();
	}

	public String getGuid()  {
		if(! isDtoProxy()){
			return this.guid;
		}

		if(isGuidModified())
			return this.guid;

		if(! GWT.isClient())
			return null;

		String _value = dtoManager.getProperty(this, instantiatePropertyAccess().guid());

		return _value;
	}


	public void setGuid(String guid)  {
		/* old value */
		String oldValue = null;
		if(GWT.isClient())
			oldValue = getGuid();

		/* set new value */
		this.guid = guid;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(guid_pa, oldValue, guid, this.guid_m));

		/* set indicator */
		this.guid_m = true;

		this.fireObjectChangedEvent(AbstractUserManagerNodeDtoPA.INSTANCE.guid(), oldValue);
	}


	public boolean isGuidModified()  {
		return guid_m;
	}


	public static PropertyAccessor<AbstractUserManagerNodeDto, String> getGuidPropertyAccessor()  {
		return guid_pa;
	}


	public String getOrigin()  {
		if(! isDtoProxy()){
			return this.origin;
		}

		if(isOriginModified())
			return this.origin;

		if(! GWT.isClient())
			return null;

		String _value = dtoManager.getProperty(this, instantiatePropertyAccess().origin());

		return _value;
	}


	public void setOrigin(String origin)  {
		/* old value */
		String oldValue = null;
		if(GWT.isClient())
			oldValue = getOrigin();

		/* set new value */
		this.origin = origin;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(origin_pa, oldValue, origin, this.origin_m));

		/* set indicator */
		this.origin_m = true;

		this.fireObjectChangedEvent(AbstractUserManagerNodeDtoPA.INSTANCE.origin(), oldValue);
	}


	public boolean isOriginModified()  {
		return origin_m;
	}


	public static PropertyAccessor<AbstractUserManagerNodeDto, String> getOriginPropertyAccessor()  {
		return origin_pa;
	}


	@Override
	public int hashCode()  {
		if(null == getId())
			return super.hashCode();
		return getId().hashCode();
	}

	@Override
	public boolean equals(Object obj)  {
		if(! (obj instanceof AbstractUserManagerNodeDto))
			return false;

		if(null == getId())
			return super.equals(obj);
		return getId().equals(((AbstractUserManagerNodeDto)obj).getId());
	}

	@Override
	public String toString()  {
		return getClass().getName() + ": " + getId();
	}

	public static Dto2PosoMapper newPosoMapper()  {
		return new AbstractUserManagerNodeDto2PosoMap();
	}

	public AbstractUserManagerNodeDtoPA instantiatePropertyAccess()  {
		return GWT.create(AbstractUserManagerNodeDtoPA.class);
	}

	public void clearModified()  {
		this.guid = null;
		this.guid_m = false;
		this.origin = null;
		this.origin_m = false;
	}


	public boolean isModified()  {
		if(super.isModified())
			return true;
		if(guid_m)
			return true;
		if(origin_m)
			return true;
		return false;
	}


	public List<PropertyAccessor> getPropertyAccessors()  {
		List<PropertyAccessor> list = super.getPropertyAccessors();
		list.add(guid_pa);
		list.add(origin_pa);
		return list;
	}


	public List<PropertyAccessor> getModifiedPropertyAccessors()  {
		List<PropertyAccessor> list = super.getModifiedPropertyAccessors();
		if(guid_m)
			list.add(guid_pa);
		if(origin_m)
			list.add(origin_pa);
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsByView(net.datenwerke.gxtdto.client.dtomanager.DtoView view)  {
		List<PropertyAccessor> list = super.getPropertyAccessorsByView(view);
		if(view.compareTo(DtoView.MINIMAL) >= 0){
			list.add(guid_pa);
			list.add(origin_pa);
		}
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsForDtos()  {
		List<PropertyAccessor> list = super.getPropertyAccessorsForDtos();
		return list;
	}



	net.datenwerke.security.client.usermanager.dto.UserDto wl_0;
	net.datenwerke.security.client.usermanager.dto.OrganisationalUnitDto wl_1;
	net.datenwerke.security.client.usermanager.dto.GroupDto wl_2;

}
