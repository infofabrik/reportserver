package net.datenwerke.rs.crystal.client.crystal.dto;

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
import net.datenwerke.gxtdto.client.dtomanager.DtoView;
import net.datenwerke.gxtdto.client.dtomanager.IdedDto;
import net.datenwerke.gxtdto.client.dtomanager.PropertyAccessor;
import net.datenwerke.gxtdto.client.dtomanager.redoundo.ChangeTracker;
import net.datenwerke.rs.crystal.client.crystal.dto.pa.CrystalReportFileDtoPA;
import net.datenwerke.rs.crystal.client.crystal.dto.posomap.CrystalReportFileDto2PosoMap;
import net.datenwerke.rs.crystal.service.crystal.entities.CrystalReportFile;

/**
 * Dto for {@link CrystalReportFile}
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class CrystalReportFileDto extends RsDto implements IdedDto {


	private static final long serialVersionUID = 1;

	private Long dtoId;


	/* Fields */
	private Long id;
	private  boolean id_m;
	public static final String PROPERTY_ID = "dpi-crystalreportfile-id";

	private transient static PropertyAccessor<CrystalReportFileDto, Long> id_pa = new PropertyAccessor<CrystalReportFileDto, Long>() {
		@Override
		public void setValue(CrystalReportFileDto container, Long object) {
			// id field
		}

		@Override
		public Long getValue(CrystalReportFileDto container) {
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
		public void setModified(CrystalReportFileDto container, boolean modified) {
			container.id_m = modified;
		}

		@Override
		public boolean isModified(CrystalReportFileDto container) {
			return container.isIdModified();
		}
	};

	private String name;
	private  boolean name_m;
	public static final String PROPERTY_NAME = "dpi-crystalreportfile-name";

	private transient static PropertyAccessor<CrystalReportFileDto, String> name_pa = new PropertyAccessor<CrystalReportFileDto, String>() {
		@Override
		public void setValue(CrystalReportFileDto container, String object) {
			container.setName(object);
		}

		@Override
		public String getValue(CrystalReportFileDto container) {
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
		public void setModified(CrystalReportFileDto container, boolean modified) {
			container.name_m = modified;
		}

		@Override
		public boolean isModified(CrystalReportFileDto container) {
			return container.isNameModified();
		}
	};


	public CrystalReportFileDto() {
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


	public static PropertyAccessor<CrystalReportFileDto, Long> getIdPropertyAccessor()  {
		return id_pa;
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

		this.fireObjectChangedEvent(CrystalReportFileDtoPA.INSTANCE.name(), oldValue);
	}


	public boolean isNameModified()  {
		return name_m;
	}


	public static PropertyAccessor<CrystalReportFileDto, String> getNamePropertyAccessor()  {
		return name_pa;
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
		if(! (obj instanceof CrystalReportFileDto))
			return false;

		if(null == getId())
			return super.equals(obj);
		return getId().equals(((CrystalReportFileDto)obj).getId());
	}

	@Override
	public String toString()  {
		return getClass().getName() + ": " + getId();
	}

	public static Dto2PosoMapper newPosoMapper()  {
		return new CrystalReportFileDto2PosoMap();
	}

	public CrystalReportFileDtoPA instantiatePropertyAccess()  {
		return GWT.create(CrystalReportFileDtoPA.class);
	}

	public void clearModified()  {
		this.id = null;
		this.id_m = false;
		this.name = null;
		this.name_m = false;
	}


	public boolean isModified()  {
		if(super.isModified())
			return true;
		if(id_m)
			return true;
		if(name_m)
			return true;
		return false;
	}


	public List<PropertyAccessor> getPropertyAccessors()  {
		List<PropertyAccessor> list = super.getPropertyAccessors();
		list.add(id_pa);
		list.add(name_pa);
		return list;
	}


	public List<PropertyAccessor> getModifiedPropertyAccessors()  {
		List<PropertyAccessor> list = super.getModifiedPropertyAccessors();
		if(id_m)
			list.add(id_pa);
		if(name_m)
			list.add(name_pa);
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsByView(net.datenwerke.gxtdto.client.dtomanager.DtoView view)  {
		List<PropertyAccessor> list = super.getPropertyAccessorsByView(view);
		if(view.compareTo(DtoView.MINIMAL) >= 0){
			list.add(id_pa);
			list.add(name_pa);
		}
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsForDtos()  {
		List<PropertyAccessor> list = super.getPropertyAccessorsForDtos();
		return list;
	}




}
