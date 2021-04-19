package net.datenwerke.security.client.treedb.dto;

import com.google.gwt.core.client.GWT;
import java.lang.Boolean;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.gxtdto.client.dtomanager.Dto2PosoMapper;
import net.datenwerke.gxtdto.client.dtomanager.DtoView;
import net.datenwerke.gxtdto.client.dtomanager.PropertyAccessor;
import net.datenwerke.gxtdto.client.dtomanager.redoundo.ChangeTracker;
import net.datenwerke.security.client.security.dto.RightDto;
import net.datenwerke.security.client.treedb.dto.pa.SecuredAbstractNodeDtoPA;
import net.datenwerke.security.client.treedb.dto.posomap.SecuredAbstractNodeDto2PosoMap;
import net.datenwerke.security.service.treedb.entities.SecuredAbstractNode;
import net.datenwerke.treedb.client.treedb.dto.decorator.AbstractNodeDtoDec;

/**
 * Dto for {@link SecuredAbstractNode}
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
abstract public class SecuredAbstractNodeDto extends AbstractNodeDtoDec {


	private static final long serialVersionUID = 1;


	/* Fields */
	protected Set<RightDto> availableInheritedAccessRights;
	protected  boolean availableInheritedAccessRights_m;
	public static final String PROPERTY_AVAILABLE_INHERITED_ACCESS_RIGHTS = "dpi-securedabstractnode-availableinheritedaccessrights";

	private transient static PropertyAccessor<SecuredAbstractNodeDto, Set<RightDto>> availableInheritedAccessRights_pa = new PropertyAccessor<SecuredAbstractNodeDto, Set<RightDto>>() {
		@Override
		public void setValue(SecuredAbstractNodeDto container, Set<RightDto> object) {
			container.setAvailableInheritedAccessRights(object);
		}

		@Override
		public Set<RightDto> getValue(SecuredAbstractNodeDto container) {
			return container.getAvailableInheritedAccessRights();
		}

		@Override
		public Class<?> getType() {
			return Set.class;
		}

		@Override
		public String getPath() {
			return "availableInheritedAccessRights";
		}

		@Override
		public void setModified(SecuredAbstractNodeDto container, boolean modified) {
			container.availableInheritedAccessRights_m = modified;
		}

		@Override
		public boolean isModified(SecuredAbstractNodeDto container) {
			return container.isAvailableInheritedAccessRightsModified();
		}
	};

	protected Set<RightDto> availableAccessRights;
	protected  boolean availableAccessRights_m;
	public static final String PROPERTY_AVAILABLE_ACCESS_RIGHTS = "dpi-securedabstractnode-availableaccessrights";

	private transient static PropertyAccessor<SecuredAbstractNodeDto, Set<RightDto>> availableAccessRights_pa = new PropertyAccessor<SecuredAbstractNodeDto, Set<RightDto>>() {
		@Override
		public void setValue(SecuredAbstractNodeDto container, Set<RightDto> object) {
			container.setAvailableAccessRights(object);
		}

		@Override
		public Set<RightDto> getValue(SecuredAbstractNodeDto container) {
			return container.getAvailableAccessRights();
		}

		@Override
		public Class<?> getType() {
			return Set.class;
		}

		@Override
		public String getPath() {
			return "availableAccessRights";
		}

		@Override
		public void setModified(SecuredAbstractNodeDto container, boolean modified) {
			container.availableAccessRights_m = modified;
		}

		@Override
		public boolean isModified(SecuredAbstractNodeDto container) {
			return container.isAvailableAccessRightsModified();
		}
	};

	protected Boolean availableAccessRightsSet;
	protected  boolean availableAccessRightsSet_m;
	public static final String PROPERTY_AVAILABLE_ACCESS_RIGHTS_SET = "dpi-securedabstractnode-availableaccessrightsset";

	private transient static PropertyAccessor<SecuredAbstractNodeDto, Boolean> availableAccessRightsSet_pa = new PropertyAccessor<SecuredAbstractNodeDto, Boolean>() {
		@Override
		public void setValue(SecuredAbstractNodeDto container, Boolean object) {
			container.setAvailableAccessRightsSet(object);
		}

		@Override
		public Boolean getValue(SecuredAbstractNodeDto container) {
			return container.isAvailableAccessRightsSet();
		}

		@Override
		public Class<?> getType() {
			return Boolean.class;
		}

		@Override
		public String getPath() {
			return "availableAccessRightsSet";
		}

		@Override
		public void setModified(SecuredAbstractNodeDto container, boolean modified) {
			container.availableAccessRightsSet_m = modified;
		}

		@Override
		public boolean isModified(SecuredAbstractNodeDto container) {
			return container.isAvailableAccessRightsSetModified();
		}
	};


	public SecuredAbstractNodeDto() {
		super();
	}

	public Set<RightDto> getAvailableInheritedAccessRights()  {
		if(! isDtoProxy()){
			Set<RightDto> _currentValue = this.availableInheritedAccessRights;
			if(null == _currentValue)
				this.availableInheritedAccessRights = new HashSet<RightDto>();

			return this.availableInheritedAccessRights;
		}

		if(isAvailableInheritedAccessRightsModified())
			return this.availableInheritedAccessRights;

		if(! GWT.isClient())
			return null;

		Set<RightDto> _value = dtoManager.getProperty(this, instantiatePropertyAccess().availableInheritedAccessRights());

		return _value;
	}


	public void setAvailableInheritedAccessRights(Set<RightDto> availableInheritedAccessRights)  {
		/* old value */
		Set<RightDto> oldValue = null;
		if(GWT.isClient())
			oldValue = getAvailableInheritedAccessRights();

		/* set new value */
		this.availableInheritedAccessRights = availableInheritedAccessRights;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(availableInheritedAccessRights_pa, oldValue, availableInheritedAccessRights, this.availableInheritedAccessRights_m));

		/* set indicator */
		this.availableInheritedAccessRights_m = true;

		this.fireObjectChangedEvent(SecuredAbstractNodeDtoPA.INSTANCE.availableInheritedAccessRights(), oldValue);
	}


	public boolean isAvailableInheritedAccessRightsModified()  {
		return availableInheritedAccessRights_m;
	}


	public static PropertyAccessor<SecuredAbstractNodeDto, Set<RightDto>> getAvailableInheritedAccessRightsPropertyAccessor()  {
		return availableInheritedAccessRights_pa;
	}


	public Set<RightDto> getAvailableAccessRights()  {
		if(! isDtoProxy()){
			Set<RightDto> _currentValue = this.availableAccessRights;
			if(null == _currentValue)
				this.availableAccessRights = new HashSet<RightDto>();

			return this.availableAccessRights;
		}

		if(isAvailableAccessRightsModified())
			return this.availableAccessRights;

		if(! GWT.isClient())
			return null;

		Set<RightDto> _value = dtoManager.getProperty(this, instantiatePropertyAccess().availableAccessRights());

		return _value;
	}


	public void setAvailableAccessRights(Set<RightDto> availableAccessRights)  {
		/* old value */
		Set<RightDto> oldValue = null;
		if(GWT.isClient())
			oldValue = getAvailableAccessRights();

		/* set new value */
		this.availableAccessRights = availableAccessRights;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(availableAccessRights_pa, oldValue, availableAccessRights, this.availableAccessRights_m));

		/* set indicator */
		this.availableAccessRights_m = true;

		this.fireObjectChangedEvent(SecuredAbstractNodeDtoPA.INSTANCE.availableAccessRights(), oldValue);
	}


	public boolean isAvailableAccessRightsModified()  {
		return availableAccessRights_m;
	}


	public static PropertyAccessor<SecuredAbstractNodeDto, Set<RightDto>> getAvailableAccessRightsPropertyAccessor()  {
		return availableAccessRights_pa;
	}


	public Boolean isAvailableAccessRightsSet()  {
		if(! isDtoProxy()){
			return this.availableAccessRightsSet;
		}

		if(isAvailableAccessRightsSetModified())
			return this.availableAccessRightsSet;

		if(! GWT.isClient())
			return null;

		Boolean _value = dtoManager.getProperty(this, instantiatePropertyAccess().availableAccessRightsSet());

		return _value;
	}


	public void setAvailableAccessRightsSet(Boolean availableAccessRightsSet)  {
		/* old value */
		Boolean oldValue = null;
		if(GWT.isClient())
			oldValue = isAvailableAccessRightsSet();

		/* set new value */
		this.availableAccessRightsSet = availableAccessRightsSet;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(availableAccessRightsSet_pa, oldValue, availableAccessRightsSet, this.availableAccessRightsSet_m));

		/* set indicator */
		this.availableAccessRightsSet_m = true;

		this.fireObjectChangedEvent(SecuredAbstractNodeDtoPA.INSTANCE.availableAccessRightsSet(), oldValue);
	}


	public boolean isAvailableAccessRightsSetModified()  {
		return availableAccessRightsSet_m;
	}


	public static PropertyAccessor<SecuredAbstractNodeDto, Boolean> getAvailableAccessRightsSetPropertyAccessor()  {
		return availableAccessRightsSet_pa;
	}


	@Override
	public int hashCode()  {
		if(null == getId())
			return super.hashCode();
		return getId().hashCode();
	}

	@Override
	public boolean equals(Object obj)  {
		if(! (obj instanceof SecuredAbstractNodeDto))
			return false;

		if(null == getId())
			return super.equals(obj);
		return getId().equals(((SecuredAbstractNodeDto)obj).getId());
	}

	@Override
	public String toString()  {
		return getClass().getName() + ": " + getId();
	}

	public static Dto2PosoMapper newPosoMapper()  {
		return new SecuredAbstractNodeDto2PosoMap();
	}

	public SecuredAbstractNodeDtoPA instantiatePropertyAccess()  {
		return GWT.create(SecuredAbstractNodeDtoPA.class);
	}

	public void clearModified()  {
		this.availableInheritedAccessRights = null;
		this.availableInheritedAccessRights_m = false;
		this.availableAccessRights = null;
		this.availableAccessRights_m = false;
		this.availableAccessRightsSet = null;
		this.availableAccessRightsSet_m = false;
	}


	public boolean isModified()  {
		if(super.isModified())
			return true;
		if(availableInheritedAccessRights_m)
			return true;
		if(availableAccessRights_m)
			return true;
		if(availableAccessRightsSet_m)
			return true;
		return false;
	}


	public List<PropertyAccessor> getPropertyAccessors()  {
		List<PropertyAccessor> list = super.getPropertyAccessors();
		list.add(availableInheritedAccessRights_pa);
		list.add(availableAccessRights_pa);
		list.add(availableAccessRightsSet_pa);
		return list;
	}


	public List<PropertyAccessor> getModifiedPropertyAccessors()  {
		List<PropertyAccessor> list = super.getModifiedPropertyAccessors();
		if(availableInheritedAccessRights_m)
			list.add(availableInheritedAccessRights_pa);
		if(availableAccessRights_m)
			list.add(availableAccessRights_pa);
		if(availableAccessRightsSet_m)
			list.add(availableAccessRightsSet_pa);
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsByView(net.datenwerke.gxtdto.client.dtomanager.DtoView view)  {
		List<PropertyAccessor> list = super.getPropertyAccessorsByView(view);
		if(view.compareTo(DtoView.MINIMAL) >= 0){
			list.add(availableInheritedAccessRights_pa);
			list.add(availableAccessRights_pa);
			list.add(availableAccessRightsSet_pa);
		}
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsForDtos()  {
		List<PropertyAccessor> list = super.getPropertyAccessorsForDtos();
		return list;
	}



	net.datenwerke.security.client.security.dto.GrantAccessDto wl_0;
	net.datenwerke.security.client.usermanager.dto.UserDto wl_1;
	net.datenwerke.security.client.usermanager.dto.OrganisationalUnitDto wl_2;
	net.datenwerke.security.client.security.dto.DeleteDto wl_3;
	net.datenwerke.security.client.security.dto.WriteDto wl_4;
	net.datenwerke.security.client.usermanager.dto.decorator.UserDtoDec wl_5;
	net.datenwerke.security.client.security.dto.ExecuteDto wl_6;
	net.datenwerke.security.client.usermanager.dto.GroupDto wl_7;
	net.datenwerke.security.client.security.dto.ReadDto wl_8;

}
