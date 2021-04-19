package net.datenwerke.security.client.security.dto;

import com.google.gwt.core.client.GWT;
import java.lang.Long;
import java.lang.String;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.gf.base.client.dtogenerator.RsDto;
import net.datenwerke.gxtdto.client.dtomanager.Dto2PosoMapper;
import net.datenwerke.gxtdto.client.dtomanager.DtoView;
import net.datenwerke.gxtdto.client.dtomanager.IdedDto;
import net.datenwerke.gxtdto.client.dtomanager.PropertyAccessor;
import net.datenwerke.gxtdto.client.dtomanager.redoundo.ChangeTracker;
import net.datenwerke.security.client.security.dto.pa.AceAccessMapDtoPA;
import net.datenwerke.security.client.security.dto.posomap.AceAccessMapDto2PosoMap;
import net.datenwerke.security.service.security.entities.AceAccessMap;

/**
 * Dto for {@link AceAccessMap}
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
abstract public class AceAccessMapDto extends RsDto implements IdedDto {


	private static final long serialVersionUID = 1;


	/* Fields */
	private Long access;
	private  boolean access_m;
	public static final String PROPERTY_ACCESS = "dpi-aceaccessmap-access";

	private transient static PropertyAccessor<AceAccessMapDto, Long> access_pa = new PropertyAccessor<AceAccessMapDto, Long>() {
		@Override
		public void setValue(AceAccessMapDto container, Long object) {
			container.setAccess(object);
		}

		@Override
		public Long getValue(AceAccessMapDto container) {
			return container.getAccess();
		}

		@Override
		public Class<?> getType() {
			return Long.class;
		}

		@Override
		public String getPath() {
			return "access";
		}

		@Override
		public void setModified(AceAccessMapDto container, boolean modified) {
			container.access_m = modified;
		}

		@Override
		public boolean isModified(AceAccessMapDto container) {
			return container.isAccessModified();
		}
	};

	private Long id;
	private  boolean id_m;
	public static final String PROPERTY_ID = "dpi-aceaccessmap-id";

	private transient static PropertyAccessor<AceAccessMapDto, Long> id_pa = new PropertyAccessor<AceAccessMapDto, Long>() {
		@Override
		public void setValue(AceAccessMapDto container, Long object) {
			// id field
		}

		@Override
		public Long getValue(AceAccessMapDto container) {
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
		public void setModified(AceAccessMapDto container, boolean modified) {
			container.id_m = modified;
		}

		@Override
		public boolean isModified(AceAccessMapDto container) {
			return container.isIdModified();
		}
	};

	private String securee;
	private  boolean securee_m;
	public static final String PROPERTY_SECUREE = "dpi-aceaccessmap-securee";

	private transient static PropertyAccessor<AceAccessMapDto, String> securee_pa = new PropertyAccessor<AceAccessMapDto, String>() {
		@Override
		public void setValue(AceAccessMapDto container, String object) {
			container.setSecuree(object);
		}

		@Override
		public String getValue(AceAccessMapDto container) {
			return container.getSecuree();
		}

		@Override
		public Class<?> getType() {
			return String.class;
		}

		@Override
		public String getPath() {
			return "securee";
		}

		@Override
		public void setModified(AceAccessMapDto container, boolean modified) {
			container.securee_m = modified;
		}

		@Override
		public boolean isModified(AceAccessMapDto container) {
			return container.isSecureeModified();
		}
	};


	public AceAccessMapDto() {
		super();
	}

	public Long getAccess()  {
		if(! isDtoProxy()){
			return this.access;
		}

		if(isAccessModified())
			return this.access;

		if(! GWT.isClient())
			return null;

		Long _value = dtoManager.getProperty(this, instantiatePropertyAccess().access());

		return _value;
	}


	public void setAccess(Long access)  {
		/* old value */
		Long oldValue = null;
		if(GWT.isClient())
			oldValue = getAccess();

		/* set new value */
		this.access = access;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(access_pa, oldValue, access, this.access_m));

		/* set indicator */
		this.access_m = true;

		this.fireObjectChangedEvent(AceAccessMapDtoPA.INSTANCE.access(), oldValue);
	}


	public boolean isAccessModified()  {
		return access_m;
	}


	public static PropertyAccessor<AceAccessMapDto, Long> getAccessPropertyAccessor()  {
		return access_pa;
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

		this.fireObjectChangedEvent(AceAccessMapDtoPA.INSTANCE.id(), oldValue);
	}


	public boolean isIdModified()  {
		return id_m;
	}


	public static PropertyAccessor<AceAccessMapDto, Long> getIdPropertyAccessor()  {
		return id_pa;
	}


	public String getSecuree()  {
		if(! isDtoProxy()){
			return this.securee;
		}

		if(isSecureeModified())
			return this.securee;

		if(! GWT.isClient())
			return null;

		String _value = dtoManager.getProperty(this, instantiatePropertyAccess().securee());

		return _value;
	}


	public void setSecuree(String securee)  {
		/* old value */
		String oldValue = null;
		if(GWT.isClient())
			oldValue = getSecuree();

		/* set new value */
		this.securee = securee;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(securee_pa, oldValue, securee, this.securee_m));

		/* set indicator */
		this.securee_m = true;

		this.fireObjectChangedEvent(AceAccessMapDtoPA.INSTANCE.securee(), oldValue);
	}


	public boolean isSecureeModified()  {
		return securee_m;
	}


	public static PropertyAccessor<AceAccessMapDto, String> getSecureePropertyAccessor()  {
		return securee_pa;
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
		if(! (obj instanceof AceAccessMapDto))
			return false;

		if(null == getId())
			return super.equals(obj);
		return getId().equals(((AceAccessMapDto)obj).getId());
	}

	@Override
	public String toString()  {
		return getClass().getName() + ": " + getId();
	}

	public static Dto2PosoMapper newPosoMapper()  {
		return new AceAccessMapDto2PosoMap();
	}

	public AceAccessMapDtoPA instantiatePropertyAccess()  {
		return GWT.create(AceAccessMapDtoPA.class);
	}

	public void clearModified()  {
		this.access = null;
		this.access_m = false;
		this.id = null;
		this.id_m = false;
		this.securee = null;
		this.securee_m = false;
	}


	public boolean isModified()  {
		if(super.isModified())
			return true;
		if(access_m)
			return true;
		if(id_m)
			return true;
		if(securee_m)
			return true;
		return false;
	}


	public List<PropertyAccessor> getPropertyAccessors()  {
		List<PropertyAccessor> list = super.getPropertyAccessors();
		list.add(access_pa);
		list.add(id_pa);
		list.add(securee_pa);
		return list;
	}


	public List<PropertyAccessor> getModifiedPropertyAccessors()  {
		List<PropertyAccessor> list = super.getModifiedPropertyAccessors();
		if(access_m)
			list.add(access_pa);
		if(id_m)
			list.add(id_pa);
		if(securee_m)
			list.add(securee_pa);
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsByView(net.datenwerke.gxtdto.client.dtomanager.DtoView view)  {
		List<PropertyAccessor> list = super.getPropertyAccessorsByView(view);
		if(view.compareTo(DtoView.MINIMAL) >= 0){
			list.add(id_pa);
		}
		if(view.compareTo(DtoView.NORMAL) >= 0){
			list.add(access_pa);
			list.add(securee_pa);
		}
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsForDtos()  {
		List<PropertyAccessor> list = super.getPropertyAccessorsForDtos();
		return list;
	}




}
