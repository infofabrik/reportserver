package net.datenwerke.security.client.security.dto;

import com.google.gwt.core.client.GWT;
import java.lang.String;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.gf.base.client.dtogenerator.RsDto;
import net.datenwerke.gxtdto.client.dtomanager.Dto2PosoMapper;
import net.datenwerke.gxtdto.client.dtomanager.DtoView;
import net.datenwerke.gxtdto.client.dtomanager.PropertyAccessor;
import net.datenwerke.gxtdto.client.dtomanager.redoundo.ChangeTracker;
import net.datenwerke.security.client.security.dto.RightDto;
import net.datenwerke.security.client.security.dto.pa.GrantAccessDtoPA;
import net.datenwerke.security.client.security.dto.posomap.GrantAccessDto2PosoMap;
import net.datenwerke.security.service.security.rights.GrantAccess;

/**
 * Dto for {@link GrantAccess}
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class GrantAccessDto extends RsDto implements RightDto {


	private static final long serialVersionUID = 1;


	/* Fields */
	private String abbreviation;
	private  boolean abbreviation_m;
	public static final String PROPERTY_ABBREVIATION = "dpi-grantaccess-abbreviation";

	private transient static PropertyAccessor<GrantAccessDto, String> abbreviation_pa = new PropertyAccessor<GrantAccessDto, String>() {
		@Override
		public void setValue(GrantAccessDto container, String object) {
			container.setAbbreviation(object);
		}

		@Override
		public String getValue(GrantAccessDto container) {
			return container.getAbbreviation();
		}

		@Override
		public Class<?> getType() {
			return String.class;
		}

		@Override
		public String getPath() {
			return "abbreviation";
		}

		@Override
		public void setModified(GrantAccessDto container, boolean modified) {
			container.abbreviation_m = modified;
		}

		@Override
		public boolean isModified(GrantAccessDto container) {
			return container.isAbbreviationModified();
		}
	};

	private long bitField;
	private  boolean bitField_m;
	public static final String PROPERTY_BIT_FIELD = "dpi-grantaccess-bitfield";

	private transient static PropertyAccessor<GrantAccessDto, Long> bitField_pa = new PropertyAccessor<GrantAccessDto, Long>() {
		@Override
		public void setValue(GrantAccessDto container, Long object) {
			container.setBitField(object);
		}

		@Override
		public Long getValue(GrantAccessDto container) {
			return container.getBitField();
		}

		@Override
		public Class<?> getType() {
			return Long.class;
		}

		@Override
		public String getPath() {
			return "bitField";
		}

		@Override
		public void setModified(GrantAccessDto container, boolean modified) {
			container.bitField_m = modified;
		}

		@Override
		public boolean isModified(GrantAccessDto container) {
			return container.isBitFieldModified();
		}
	};

	private String description;
	private  boolean description_m;
	public static final String PROPERTY_DESCRIPTION = "dpi-grantaccess-description";

	private transient static PropertyAccessor<GrantAccessDto, String> description_pa = new PropertyAccessor<GrantAccessDto, String>() {
		@Override
		public void setValue(GrantAccessDto container, String object) {
			container.setDescription(object);
		}

		@Override
		public String getValue(GrantAccessDto container) {
			return container.getDescription();
		}

		@Override
		public Class<?> getType() {
			return String.class;
		}

		@Override
		public String getPath() {
			return "description";
		}

		@Override
		public void setModified(GrantAccessDto container, boolean modified) {
			container.description_m = modified;
		}

		@Override
		public boolean isModified(GrantAccessDto container) {
			return container.isDescriptionModified();
		}
	};


	public GrantAccessDto() {
		super();
	}

	public void setAbbreviation(String abbreviation)  {
		/* old value */
		String oldValue = null;
		if(GWT.isClient())
			oldValue = getAbbreviation();

		/* set new value */
		this.abbreviation = abbreviation;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(abbreviation_pa, oldValue, abbreviation, this.abbreviation_m));

		/* set indicator */
		this.abbreviation_m = true;

		this.fireObjectChangedEvent(GrantAccessDtoPA.INSTANCE.abbreviation(), oldValue);
	}


	public String getAbbreviation()  {
		if(! isDtoProxy()){
			return this.abbreviation;
		}

		if(isAbbreviationModified())
			return this.abbreviation;

		if(! GWT.isClient())
			return null;

		String _value = dtoManager.getProperty(this, instantiatePropertyAccess().abbreviation());

		return _value;
	}


	public boolean isAbbreviationModified()  {
		return abbreviation_m;
	}


	public static PropertyAccessor<GrantAccessDto, String> getAbbreviationPropertyAccessor()  {
		return abbreviation_pa;
	}


	public void setBitField(long bitField)  {
		/* old value */
		long oldValue = 0;
		if(GWT.isClient())
			oldValue = getBitField();

		/* set new value */
		this.bitField = bitField;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(bitField_pa, oldValue, bitField, this.bitField_m));

		/* set indicator */
		this.bitField_m = true;

		this.fireObjectChangedEvent(GrantAccessDtoPA.INSTANCE.bitField(), oldValue);
	}


	public long getBitField()  {
		if(! isDtoProxy()){
			return this.bitField;
		}

		if(isBitFieldModified())
			return this.bitField;

		if(! GWT.isClient())
			return 0;

		long _value = dtoManager.getProperty(this, instantiatePropertyAccess().bitField());

		return _value;
	}


	public boolean isBitFieldModified()  {
		return bitField_m;
	}


	public static PropertyAccessor<GrantAccessDto, Long> getBitFieldPropertyAccessor()  {
		return bitField_pa;
	}


	public void setDescription(String description)  {
		/* old value */
		String oldValue = null;
		if(GWT.isClient())
			oldValue = getDescription();

		/* set new value */
		this.description = description;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(description_pa, oldValue, description, this.description_m));

		/* set indicator */
		this.description_m = true;

		this.fireObjectChangedEvent(GrantAccessDtoPA.INSTANCE.description(), oldValue);
	}


	public String getDescription()  {
		if(! isDtoProxy()){
			return this.description;
		}

		if(isDescriptionModified())
			return this.description;

		if(! GWT.isClient())
			return null;

		String _value = dtoManager.getProperty(this, instantiatePropertyAccess().description());

		return _value;
	}


	public boolean isDescriptionModified()  {
		return description_m;
	}


	public static PropertyAccessor<GrantAccessDto, String> getDescriptionPropertyAccessor()  {
		return description_pa;
	}


	@Override
	public String toString()  {
		return super.toString();
	}

	public static Dto2PosoMapper newPosoMapper()  {
		return new GrantAccessDto2PosoMap();
	}

	public GrantAccessDtoPA instantiatePropertyAccess()  {
		return GWT.create(GrantAccessDtoPA.class);
	}

	public void clearModified()  {
		this.abbreviation = null;
		this.abbreviation_m = false;
		this.bitField = 0;
		this.bitField_m = false;
		this.description = null;
		this.description_m = false;
	}


	public boolean isModified()  {
		if(super.isModified())
			return true;
		if(abbreviation_m)
			return true;
		if(bitField_m)
			return true;
		if(description_m)
			return true;
		return false;
	}


	public List<PropertyAccessor> getPropertyAccessors()  {
		List<PropertyAccessor> list = super.getPropertyAccessors();
		list.add(abbreviation_pa);
		list.add(bitField_pa);
		list.add(description_pa);
		return list;
	}


	public List<PropertyAccessor> getModifiedPropertyAccessors()  {
		List<PropertyAccessor> list = super.getModifiedPropertyAccessors();
		if(abbreviation_m)
			list.add(abbreviation_pa);
		if(bitField_m)
			list.add(bitField_pa);
		if(description_m)
			list.add(description_pa);
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsByView(net.datenwerke.gxtdto.client.dtomanager.DtoView view)  {
		List<PropertyAccessor> list = super.getPropertyAccessorsByView(view);
		if(view.compareTo(DtoView.NORMAL) >= 0){
			list.add(abbreviation_pa);
			list.add(bitField_pa);
			list.add(description_pa);
		}
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsForDtos()  {
		List<PropertyAccessor> list = super.getPropertyAccessorsForDtos();
		return list;
	}




}
