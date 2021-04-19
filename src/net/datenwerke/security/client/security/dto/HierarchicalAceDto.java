package net.datenwerke.security.client.security.dto;

import com.google.gwt.core.client.GWT;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.gxtdto.client.dtomanager.Dto2PosoMapper;
import net.datenwerke.gxtdto.client.dtomanager.DtoView;
import net.datenwerke.gxtdto.client.dtomanager.PropertyAccessor;
import net.datenwerke.gxtdto.client.dtomanager.redoundo.ChangeTracker;
import net.datenwerke.security.client.security.dto.InheritanceTypeDto;
import net.datenwerke.security.client.security.dto.decorator.AceDtoDec;
import net.datenwerke.security.client.security.dto.pa.HierarchicalAceDtoPA;
import net.datenwerke.security.client.security.dto.posomap.HierarchicalAceDto2PosoMap;
import net.datenwerke.security.service.security.entities.HierarchicalAce;

/**
 * Dto for {@link HierarchicalAce}
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class HierarchicalAceDto extends AceDtoDec {


	private static final long serialVersionUID = 1;


	/* Fields */
	private InheritanceTypeDto inheritancetype;
	private  boolean inheritancetype_m;
	public static final String PROPERTY_INHERITANCETYPE = "dpi-hierarchicalace-inheritancetype";

	private transient static PropertyAccessor<HierarchicalAceDto, InheritanceTypeDto> inheritancetype_pa = new PropertyAccessor<HierarchicalAceDto, InheritanceTypeDto>() {
		@Override
		public void setValue(HierarchicalAceDto container, InheritanceTypeDto object) {
			container.setInheritancetype(object);
		}

		@Override
		public InheritanceTypeDto getValue(HierarchicalAceDto container) {
			return container.getInheritancetype();
		}

		@Override
		public Class<?> getType() {
			return InheritanceTypeDto.class;
		}

		@Override
		public String getPath() {
			return "inheritancetype";
		}

		@Override
		public void setModified(HierarchicalAceDto container, boolean modified) {
			container.inheritancetype_m = modified;
		}

		@Override
		public boolean isModified(HierarchicalAceDto container) {
			return container.isInheritancetypeModified();
		}
	};


	public HierarchicalAceDto() {
		super();
	}

	public InheritanceTypeDto getInheritancetype()  {
		if(! isDtoProxy()){
			return this.inheritancetype;
		}

		if(isInheritancetypeModified())
			return this.inheritancetype;

		if(! GWT.isClient())
			return null;

		InheritanceTypeDto _value = dtoManager.getProperty(this, instantiatePropertyAccess().inheritancetype());

		return _value;
	}


	public void setInheritancetype(InheritanceTypeDto inheritancetype)  {
		/* old value */
		InheritanceTypeDto oldValue = null;
		if(GWT.isClient())
			oldValue = getInheritancetype();

		/* set new value */
		this.inheritancetype = inheritancetype;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(inheritancetype_pa, oldValue, inheritancetype, this.inheritancetype_m));

		/* set indicator */
		this.inheritancetype_m = true;

		this.fireObjectChangedEvent(HierarchicalAceDtoPA.INSTANCE.inheritancetype(), oldValue);
	}


	public boolean isInheritancetypeModified()  {
		return inheritancetype_m;
	}


	public static PropertyAccessor<HierarchicalAceDto, InheritanceTypeDto> getInheritancetypePropertyAccessor()  {
		return inheritancetype_pa;
	}


	@Override
	public int hashCode()  {
		if(null == getId())
			return super.hashCode();
		return getId().hashCode();
	}

	@Override
	public boolean equals(Object obj)  {
		if(! (obj instanceof HierarchicalAceDto))
			return false;

		if(null == getId())
			return super.equals(obj);
		return getId().equals(((HierarchicalAceDto)obj).getId());
	}

	@Override
	public String toString()  {
		return getClass().getName() + ": " + getId();
	}

	public static Dto2PosoMapper newPosoMapper()  {
		return new HierarchicalAceDto2PosoMap();
	}

	public HierarchicalAceDtoPA instantiatePropertyAccess()  {
		return GWT.create(HierarchicalAceDtoPA.class);
	}

	public void clearModified()  {
		this.inheritancetype = null;
		this.inheritancetype_m = false;
	}


	public boolean isModified()  {
		if(super.isModified())
			return true;
		if(inheritancetype_m)
			return true;
		return false;
	}


	public List<PropertyAccessor> getPropertyAccessors()  {
		List<PropertyAccessor> list = super.getPropertyAccessors();
		list.add(inheritancetype_pa);
		return list;
	}


	public List<PropertyAccessor> getModifiedPropertyAccessors()  {
		List<PropertyAccessor> list = super.getModifiedPropertyAccessors();
		if(inheritancetype_m)
			list.add(inheritancetype_pa);
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsByView(net.datenwerke.gxtdto.client.dtomanager.DtoView view)  {
		List<PropertyAccessor> list = super.getPropertyAccessorsByView(view);
		if(view.compareTo(DtoView.NORMAL) >= 0){
			list.add(inheritancetype_pa);
		}
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsForDtos()  {
		List<PropertyAccessor> list = super.getPropertyAccessorsForDtos();
		return list;
	}



	net.datenwerke.security.client.security.dto.InheritanceTypeDto wl_0;

}
