package net.datenwerke.rs.uservariables.client.variabletypes.string.dto;

import com.google.gwt.core.client.GWT;
import java.lang.Integer;
import java.lang.NullPointerException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.gxtdto.client.dtomanager.Dto2PosoMapper;
import net.datenwerke.gxtdto.client.dtomanager.DtoView;
import net.datenwerke.gxtdto.client.dtomanager.PropertyAccessor;
import net.datenwerke.gxtdto.client.dtomanager.redoundo.ChangeTracker;
import net.datenwerke.gxtdto.client.locale.BaseMessages;
import net.datenwerke.rs.uservariables.client.uservariables.dto.UserVariableDefinitionDto;
import net.datenwerke.rs.uservariables.client.uservariables.locale.UserVariablesMessages;
import net.datenwerke.rs.uservariables.client.variabletypes.string.dto.pa.StringUserVariableDefinitionDtoPA;
import net.datenwerke.rs.uservariables.client.variabletypes.string.dto.posomap.StringUserVariableDefinitionDto2PosoMap;
import net.datenwerke.rs.uservariables.service.variabletypes.string.StringUserVariableDefinition;

/**
 * Dto for {@link StringUserVariableDefinition}
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class StringUserVariableDefinitionDto extends UserVariableDefinitionDto {


	private static final long serialVersionUID = 1;


	/* Fields */
	private Integer height;
	private  boolean height_m;
	public static final String PROPERTY_HEIGHT = "dpi-stringuservariabledefinition-height";

	private transient static PropertyAccessor<StringUserVariableDefinitionDto, Integer> height_pa = new PropertyAccessor<StringUserVariableDefinitionDto, Integer>() {
		@Override
		public void setValue(StringUserVariableDefinitionDto container, Integer object) {
			container.setHeight(object);
		}

		@Override
		public Integer getValue(StringUserVariableDefinitionDto container) {
			return container.getHeight();
		}

		@Override
		public Class<?> getType() {
			return Integer.class;
		}

		@Override
		public String getPath() {
			return "height";
		}

		@Override
		public void setModified(StringUserVariableDefinitionDto container, boolean modified) {
			container.height_m = modified;
		}

		@Override
		public boolean isModified(StringUserVariableDefinitionDto container) {
			return container.isHeightModified();
		}
	};

	private Integer width;
	private  boolean width_m;
	public static final String PROPERTY_WIDTH = "dpi-stringuservariabledefinition-width";

	private transient static PropertyAccessor<StringUserVariableDefinitionDto, Integer> width_pa = new PropertyAccessor<StringUserVariableDefinitionDto, Integer>() {
		@Override
		public void setValue(StringUserVariableDefinitionDto container, Integer object) {
			container.setWidth(object);
		}

		@Override
		public Integer getValue(StringUserVariableDefinitionDto container) {
			return container.getWidth();
		}

		@Override
		public Class<?> getType() {
			return Integer.class;
		}

		@Override
		public String getPath() {
			return "width";
		}

		@Override
		public void setModified(StringUserVariableDefinitionDto container, boolean modified) {
			container.width_m = modified;
		}

		@Override
		public boolean isModified(StringUserVariableDefinitionDto container) {
			return container.isWidthModified();
		}
	};


	public StringUserVariableDefinitionDto() {
		super();
	}

	public Integer getHeight()  {
		if(! isDtoProxy()){
			return this.height;
		}

		if(isHeightModified())
			return this.height;

		if(! GWT.isClient())
			return null;

		Integer _value = dtoManager.getProperty(this, instantiatePropertyAccess().height());

		return _value;
	}


	public void setHeight(Integer height)  {
		/* old value */
		Integer oldValue = null;
		if(GWT.isClient())
			oldValue = getHeight();

		/* set new value */
		this.height = height;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(height_pa, oldValue, height, this.height_m));

		/* set indicator */
		this.height_m = true;

		this.fireObjectChangedEvent(StringUserVariableDefinitionDtoPA.INSTANCE.height(), oldValue);
	}


	public boolean isHeightModified()  {
		return height_m;
	}


	public static PropertyAccessor<StringUserVariableDefinitionDto, Integer> getHeightPropertyAccessor()  {
		return height_pa;
	}


	public Integer getWidth()  {
		if(! isDtoProxy()){
			return this.width;
		}

		if(isWidthModified())
			return this.width;

		if(! GWT.isClient())
			return null;

		Integer _value = dtoManager.getProperty(this, instantiatePropertyAccess().width());

		return _value;
	}


	public void setWidth(Integer width)  {
		/* old value */
		Integer oldValue = null;
		if(GWT.isClient())
			oldValue = getWidth();

		/* set new value */
		this.width = width;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(width_pa, oldValue, width, this.width_m));

		/* set indicator */
		this.width_m = true;

		this.fireObjectChangedEvent(StringUserVariableDefinitionDtoPA.INSTANCE.width(), oldValue);
	}


	public boolean isWidthModified()  {
		return width_m;
	}


	public static PropertyAccessor<StringUserVariableDefinitionDto, Integer> getWidthPropertyAccessor()  {
		return width_pa;
	}


	@Override
	public String toDisplayTitle()  {
		try{
			return UserVariablesMessages.INSTANCE.stringVariableText();
		} catch(NullPointerException e){
			return BaseMessages.INSTANCE.unnamed();
		}
	}

	@Override
	public int hashCode()  {
		if(null == getId())
			return super.hashCode();
		return getId().hashCode();
	}

	@Override
	public boolean equals(Object obj)  {
		if(! (obj instanceof StringUserVariableDefinitionDto))
			return false;

		if(null == getId())
			return super.equals(obj);
		return getId().equals(((StringUserVariableDefinitionDto)obj).getId());
	}

	@Override
	public String toString()  {
		return getClass().getName() + ": " + getId();
	}

	public static Dto2PosoMapper newPosoMapper()  {
		return new StringUserVariableDefinitionDto2PosoMap();
	}

	public StringUserVariableDefinitionDtoPA instantiatePropertyAccess()  {
		return GWT.create(StringUserVariableDefinitionDtoPA.class);
	}

	public void clearModified()  {
		this.height = null;
		this.height_m = false;
		this.width = null;
		this.width_m = false;
	}


	public boolean isModified()  {
		if(super.isModified())
			return true;
		if(height_m)
			return true;
		if(width_m)
			return true;
		return false;
	}


	public List<PropertyAccessor> getPropertyAccessors()  {
		List<PropertyAccessor> list = super.getPropertyAccessors();
		list.add(height_pa);
		list.add(width_pa);
		return list;
	}


	public List<PropertyAccessor> getModifiedPropertyAccessors()  {
		List<PropertyAccessor> list = super.getModifiedPropertyAccessors();
		if(height_m)
			list.add(height_pa);
		if(width_m)
			list.add(width_pa);
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsByView(net.datenwerke.gxtdto.client.dtomanager.DtoView view)  {
		List<PropertyAccessor> list = super.getPropertyAccessorsByView(view);
		if(view.compareTo(DtoView.NORMAL) >= 0){
			list.add(height_pa);
			list.add(width_pa);
		}
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsForDtos()  {
		List<PropertyAccessor> list = super.getPropertyAccessorsForDtos();
		return list;
	}




}
