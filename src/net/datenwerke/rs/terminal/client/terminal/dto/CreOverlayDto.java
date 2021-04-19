package net.datenwerke.rs.terminal.client.terminal.dto;

import com.google.gwt.core.client.GWT;
import java.lang.String;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.gxtdto.client.dtomanager.Dto2PosoMapper;
import net.datenwerke.gxtdto.client.dtomanager.DtoView;
import net.datenwerke.gxtdto.client.dtomanager.PropertyAccessor;
import net.datenwerke.gxtdto.client.dtomanager.redoundo.ChangeTracker;
import net.datenwerke.rs.terminal.client.terminal.dto.CommandResultExtensionDto;
import net.datenwerke.rs.terminal.client.terminal.dto.pa.CreOverlayDtoPA;
import net.datenwerke.rs.terminal.client.terminal.dto.posomap.CreOverlayDto2PosoMap;
import net.datenwerke.rs.terminal.service.terminal.obj.CreOverlay;

/**
 * Dto for {@link CreOverlay}
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class CreOverlayDto extends CommandResultExtensionDto {


	private static final long serialVersionUID = 1;


	/* Fields */
	private Map<String, String> cssProperties;
	private  boolean cssProperties_m;
	public static final String PROPERTY_CSS_PROPERTIES = "dpi-creoverlay-cssproperties";

	private transient static PropertyAccessor<CreOverlayDto, Map<String, String>> cssProperties_pa = new PropertyAccessor<CreOverlayDto, Map<String, String>>() {
		@Override
		public void setValue(CreOverlayDto container, Map<String, String> object) {
			container.setCssProperties(object);
		}

		@Override
		public Map<String, String> getValue(CreOverlayDto container) {
			return container.getCssProperties();
		}

		@Override
		public Class<?> getType() {
			return Map.class;
		}

		@Override
		public String getPath() {
			return "cssProperties";
		}

		@Override
		public void setModified(CreOverlayDto container, boolean modified) {
			container.cssProperties_m = modified;
		}

		@Override
		public boolean isModified(CreOverlayDto container) {
			return container.isCssPropertiesModified();
		}
	};

	private String name;
	private  boolean name_m;
	public static final String PROPERTY_NAME = "dpi-creoverlay-name";

	private transient static PropertyAccessor<CreOverlayDto, String> name_pa = new PropertyAccessor<CreOverlayDto, String>() {
		@Override
		public void setValue(CreOverlayDto container, String object) {
			container.setName(object);
		}

		@Override
		public String getValue(CreOverlayDto container) {
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
		public void setModified(CreOverlayDto container, boolean modified) {
			container.name_m = modified;
		}

		@Override
		public boolean isModified(CreOverlayDto container) {
			return container.isNameModified();
		}
	};

	private boolean remove;
	private  boolean remove_m;
	public static final String PROPERTY_REMOVE = "dpi-creoverlay-remove";

	private transient static PropertyAccessor<CreOverlayDto, Boolean> remove_pa = new PropertyAccessor<CreOverlayDto, Boolean>() {
		@Override
		public void setValue(CreOverlayDto container, Boolean object) {
			container.setRemove(object);
		}

		@Override
		public Boolean getValue(CreOverlayDto container) {
			return container.isRemove();
		}

		@Override
		public Class<?> getType() {
			return Boolean.class;
		}

		@Override
		public String getPath() {
			return "remove";
		}

		@Override
		public void setModified(CreOverlayDto container, boolean modified) {
			container.remove_m = modified;
		}

		@Override
		public boolean isModified(CreOverlayDto container) {
			return container.isRemoveModified();
		}
	};

	private String text;
	private  boolean text_m;
	public static final String PROPERTY_TEXT = "dpi-creoverlay-text";

	private transient static PropertyAccessor<CreOverlayDto, String> text_pa = new PropertyAccessor<CreOverlayDto, String>() {
		@Override
		public void setValue(CreOverlayDto container, String object) {
			container.setText(object);
		}

		@Override
		public String getValue(CreOverlayDto container) {
			return container.getText();
		}

		@Override
		public Class<?> getType() {
			return String.class;
		}

		@Override
		public String getPath() {
			return "text";
		}

		@Override
		public void setModified(CreOverlayDto container, boolean modified) {
			container.text_m = modified;
		}

		@Override
		public boolean isModified(CreOverlayDto container) {
			return container.isTextModified();
		}
	};


	public CreOverlayDto() {
		super();
	}

	public Map<String, String> getCssProperties()  {
		if(! isDtoProxy()){
			return this.cssProperties;
		}

		if(isCssPropertiesModified())
			return this.cssProperties;

		if(! GWT.isClient())
			return null;

		Map<String, String> _value = dtoManager.getProperty(this, instantiatePropertyAccess().cssProperties());

		return _value;
	}


	public void setCssProperties(Map<String, String> cssProperties)  {
		/* old value */
		Map<String, String> oldValue = null;
		if(GWT.isClient())
			oldValue = getCssProperties();

		/* set new value */
		this.cssProperties = cssProperties;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(cssProperties_pa, oldValue, cssProperties, this.cssProperties_m));

		/* set indicator */
		this.cssProperties_m = true;

		this.fireObjectChangedEvent(CreOverlayDtoPA.INSTANCE.cssProperties(), oldValue);
	}


	public boolean isCssPropertiesModified()  {
		return cssProperties_m;
	}


	public static PropertyAccessor<CreOverlayDto, Map<String, String>> getCssPropertiesPropertyAccessor()  {
		return cssProperties_pa;
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

		this.fireObjectChangedEvent(CreOverlayDtoPA.INSTANCE.name(), oldValue);
	}


	public boolean isNameModified()  {
		return name_m;
	}


	public static PropertyAccessor<CreOverlayDto, String> getNamePropertyAccessor()  {
		return name_pa;
	}


	public boolean isRemove()  {
		if(! isDtoProxy()){
			return this.remove;
		}

		if(isRemoveModified())
			return this.remove;

		if(! GWT.isClient())
			return false;

		boolean _value = dtoManager.getProperty(this, instantiatePropertyAccess().remove());

		return _value;
	}


	public void setRemove(boolean remove)  {
		/* old value */
		boolean oldValue = false;
		if(GWT.isClient())
			oldValue = isRemove();

		/* set new value */
		this.remove = remove;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(remove_pa, oldValue, remove, this.remove_m));

		/* set indicator */
		this.remove_m = true;

		this.fireObjectChangedEvent(CreOverlayDtoPA.INSTANCE.remove(), oldValue);
	}


	public boolean isRemoveModified()  {
		return remove_m;
	}


	public static PropertyAccessor<CreOverlayDto, Boolean> getRemovePropertyAccessor()  {
		return remove_pa;
	}


	public String getText()  {
		if(! isDtoProxy()){
			return this.text;
		}

		if(isTextModified())
			return this.text;

		if(! GWT.isClient())
			return null;

		String _value = dtoManager.getProperty(this, instantiatePropertyAccess().text());

		return _value;
	}


	public void setText(String text)  {
		/* old value */
		String oldValue = null;
		if(GWT.isClient())
			oldValue = getText();

		/* set new value */
		this.text = text;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(text_pa, oldValue, text, this.text_m));

		/* set indicator */
		this.text_m = true;

		this.fireObjectChangedEvent(CreOverlayDtoPA.INSTANCE.text(), oldValue);
	}


	public boolean isTextModified()  {
		return text_m;
	}


	public static PropertyAccessor<CreOverlayDto, String> getTextPropertyAccessor()  {
		return text_pa;
	}


	@Override
	public String toString()  {
		return super.toString();
	}

	public static Dto2PosoMapper newPosoMapper()  {
		return new CreOverlayDto2PosoMap();
	}

	public CreOverlayDtoPA instantiatePropertyAccess()  {
		return GWT.create(CreOverlayDtoPA.class);
	}

	public void clearModified()  {
		this.cssProperties = null;
		this.cssProperties_m = false;
		this.name = null;
		this.name_m = false;
		this.remove = false;
		this.remove_m = false;
		this.text = null;
		this.text_m = false;
	}


	public boolean isModified()  {
		if(super.isModified())
			return true;
		if(cssProperties_m)
			return true;
		if(name_m)
			return true;
		if(remove_m)
			return true;
		if(text_m)
			return true;
		return false;
	}


	public List<PropertyAccessor> getPropertyAccessors()  {
		List<PropertyAccessor> list = super.getPropertyAccessors();
		list.add(cssProperties_pa);
		list.add(name_pa);
		list.add(remove_pa);
		list.add(text_pa);
		return list;
	}


	public List<PropertyAccessor> getModifiedPropertyAccessors()  {
		List<PropertyAccessor> list = super.getModifiedPropertyAccessors();
		if(cssProperties_m)
			list.add(cssProperties_pa);
		if(name_m)
			list.add(name_pa);
		if(remove_m)
			list.add(remove_pa);
		if(text_m)
			list.add(text_pa);
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsByView(net.datenwerke.gxtdto.client.dtomanager.DtoView view)  {
		List<PropertyAccessor> list = super.getPropertyAccessorsByView(view);
		if(view.compareTo(DtoView.NORMAL) >= 0){
			list.add(cssProperties_pa);
			list.add(name_pa);
			list.add(remove_pa);
			list.add(text_pa);
		}
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsForDtos()  {
		List<PropertyAccessor> list = super.getPropertyAccessorsForDtos();
		return list;
	}




}
