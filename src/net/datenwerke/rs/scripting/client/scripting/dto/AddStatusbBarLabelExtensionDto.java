package net.datenwerke.rs.scripting.client.scripting.dto;

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
import net.datenwerke.rs.scripting.client.scripting.dto.pa.AddStatusbBarLabelExtensionDtoPA;
import net.datenwerke.rs.scripting.client.scripting.dto.posomap.AddStatusbBarLabelExtensionDto2PosoMap;
import net.datenwerke.rs.scripting.service.scripting.extensions.AddStatusbBarLabelExtension;
import net.datenwerke.rs.terminal.client.terminal.dto.CommandResultExtensionDto;

/**
 * Dto for {@link AddStatusbBarLabelExtension}
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class AddStatusbBarLabelExtensionDto extends CommandResultExtensionDto {


	private static final long serialVersionUID = 1;


	/* Fields */
	private boolean clear;
	private  boolean clear_m;
	public static final String PROPERTY_CLEAR = "dpi-addstatusbbarlabelextension-clear";

	private transient static PropertyAccessor<AddStatusbBarLabelExtensionDto, Boolean> clear_pa = new PropertyAccessor<AddStatusbBarLabelExtensionDto, Boolean>() {
		@Override
		public void setValue(AddStatusbBarLabelExtensionDto container, Boolean object) {
			container.setClear(object);
		}

		@Override
		public Boolean getValue(AddStatusbBarLabelExtensionDto container) {
			return container.isClear();
		}

		@Override
		public Class<?> getType() {
			return Boolean.class;
		}

		@Override
		public String getPath() {
			return "clear";
		}

		@Override
		public void setModified(AddStatusbBarLabelExtensionDto container, boolean modified) {
			container.clear_m = modified;
		}

		@Override
		public boolean isModified(AddStatusbBarLabelExtensionDto container) {
			return container.isClearModified();
		}
	};

	private String icon;
	private  boolean icon_m;
	public static final String PROPERTY_ICON = "dpi-addstatusbbarlabelextension-icon";

	private transient static PropertyAccessor<AddStatusbBarLabelExtensionDto, String> icon_pa = new PropertyAccessor<AddStatusbBarLabelExtensionDto, String>() {
		@Override
		public void setValue(AddStatusbBarLabelExtensionDto container, String object) {
			container.setIcon(object);
		}

		@Override
		public String getValue(AddStatusbBarLabelExtensionDto container) {
			return container.getIcon();
		}

		@Override
		public Class<?> getType() {
			return String.class;
		}

		@Override
		public String getPath() {
			return "icon";
		}

		@Override
		public void setModified(AddStatusbBarLabelExtensionDto container, boolean modified) {
			container.icon_m = modified;
		}

		@Override
		public boolean isModified(AddStatusbBarLabelExtensionDto container) {
			return container.isIconModified();
		}
	};

	private String label;
	private  boolean label_m;
	public static final String PROPERTY_LABEL = "dpi-addstatusbbarlabelextension-label";

	private transient static PropertyAccessor<AddStatusbBarLabelExtensionDto, String> label_pa = new PropertyAccessor<AddStatusbBarLabelExtensionDto, String>() {
		@Override
		public void setValue(AddStatusbBarLabelExtensionDto container, String object) {
			container.setLabel(object);
		}

		@Override
		public String getValue(AddStatusbBarLabelExtensionDto container) {
			return container.getLabel();
		}

		@Override
		public Class<?> getType() {
			return String.class;
		}

		@Override
		public String getPath() {
			return "label";
		}

		@Override
		public void setModified(AddStatusbBarLabelExtensionDto container, boolean modified) {
			container.label_m = modified;
		}

		@Override
		public boolean isModified(AddStatusbBarLabelExtensionDto container) {
			return container.isLabelModified();
		}
	};

	private boolean left;
	private  boolean left_m;
	public static final String PROPERTY_LEFT = "dpi-addstatusbbarlabelextension-left";

	private transient static PropertyAccessor<AddStatusbBarLabelExtensionDto, Boolean> left_pa = new PropertyAccessor<AddStatusbBarLabelExtensionDto, Boolean>() {
		@Override
		public void setValue(AddStatusbBarLabelExtensionDto container, Boolean object) {
			container.setLeft(object);
		}

		@Override
		public Boolean getValue(AddStatusbBarLabelExtensionDto container) {
			return container.isLeft();
		}

		@Override
		public Class<?> getType() {
			return Boolean.class;
		}

		@Override
		public String getPath() {
			return "left";
		}

		@Override
		public void setModified(AddStatusbBarLabelExtensionDto container, boolean modified) {
			container.left_m = modified;
		}

		@Override
		public boolean isModified(AddStatusbBarLabelExtensionDto container) {
			return container.isLeftModified();
		}
	};


	public AddStatusbBarLabelExtensionDto() {
		super();
	}

	public boolean isClear()  {
		if(! isDtoProxy()){
			return this.clear;
		}

		if(isClearModified())
			return this.clear;

		if(! GWT.isClient())
			return false;

		boolean _value = dtoManager.getProperty(this, instantiatePropertyAccess().clear());

		return _value;
	}


	public void setClear(boolean clear)  {
		/* old value */
		boolean oldValue = false;
		if(GWT.isClient())
			oldValue = isClear();

		/* set new value */
		this.clear = clear;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(clear_pa, oldValue, clear, this.clear_m));

		/* set indicator */
		this.clear_m = true;

		this.fireObjectChangedEvent(AddStatusbBarLabelExtensionDtoPA.INSTANCE.clear(), oldValue);
	}


	public boolean isClearModified()  {
		return clear_m;
	}


	public static PropertyAccessor<AddStatusbBarLabelExtensionDto, Boolean> getClearPropertyAccessor()  {
		return clear_pa;
	}


	public String getIcon()  {
		if(! isDtoProxy()){
			return this.icon;
		}

		if(isIconModified())
			return this.icon;

		if(! GWT.isClient())
			return null;

		String _value = dtoManager.getProperty(this, instantiatePropertyAccess().icon());

		return _value;
	}


	public void setIcon(String icon)  {
		/* old value */
		String oldValue = null;
		if(GWT.isClient())
			oldValue = getIcon();

		/* set new value */
		this.icon = icon;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(icon_pa, oldValue, icon, this.icon_m));

		/* set indicator */
		this.icon_m = true;

		this.fireObjectChangedEvent(AddStatusbBarLabelExtensionDtoPA.INSTANCE.icon(), oldValue);
	}


	public boolean isIconModified()  {
		return icon_m;
	}


	public static PropertyAccessor<AddStatusbBarLabelExtensionDto, String> getIconPropertyAccessor()  {
		return icon_pa;
	}


	public String getLabel()  {
		if(! isDtoProxy()){
			return this.label;
		}

		if(isLabelModified())
			return this.label;

		if(! GWT.isClient())
			return null;

		String _value = dtoManager.getProperty(this, instantiatePropertyAccess().label());

		return _value;
	}


	public void setLabel(String label)  {
		/* old value */
		String oldValue = null;
		if(GWT.isClient())
			oldValue = getLabel();

		/* set new value */
		this.label = label;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(label_pa, oldValue, label, this.label_m));

		/* set indicator */
		this.label_m = true;

		this.fireObjectChangedEvent(AddStatusbBarLabelExtensionDtoPA.INSTANCE.label(), oldValue);
	}


	public boolean isLabelModified()  {
		return label_m;
	}


	public static PropertyAccessor<AddStatusbBarLabelExtensionDto, String> getLabelPropertyAccessor()  {
		return label_pa;
	}


	public boolean isLeft()  {
		if(! isDtoProxy()){
			return this.left;
		}

		if(isLeftModified())
			return this.left;

		if(! GWT.isClient())
			return false;

		boolean _value = dtoManager.getProperty(this, instantiatePropertyAccess().left());

		return _value;
	}


	public void setLeft(boolean left)  {
		/* old value */
		boolean oldValue = false;
		if(GWT.isClient())
			oldValue = isLeft();

		/* set new value */
		this.left = left;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(left_pa, oldValue, left, this.left_m));

		/* set indicator */
		this.left_m = true;

		this.fireObjectChangedEvent(AddStatusbBarLabelExtensionDtoPA.INSTANCE.left(), oldValue);
	}


	public boolean isLeftModified()  {
		return left_m;
	}


	public static PropertyAccessor<AddStatusbBarLabelExtensionDto, Boolean> getLeftPropertyAccessor()  {
		return left_pa;
	}


	@Override
	public String toString()  {
		return super.toString();
	}

	public static Dto2PosoMapper newPosoMapper()  {
		return new AddStatusbBarLabelExtensionDto2PosoMap();
	}

	public AddStatusbBarLabelExtensionDtoPA instantiatePropertyAccess()  {
		return GWT.create(AddStatusbBarLabelExtensionDtoPA.class);
	}

	public void clearModified()  {
		this.clear = false;
		this.clear_m = false;
		this.icon = null;
		this.icon_m = false;
		this.label = null;
		this.label_m = false;
		this.left = false;
		this.left_m = false;
	}


	public boolean isModified()  {
		if(super.isModified())
			return true;
		if(clear_m)
			return true;
		if(icon_m)
			return true;
		if(label_m)
			return true;
		if(left_m)
			return true;
		return false;
	}


	public List<PropertyAccessor> getPropertyAccessors()  {
		List<PropertyAccessor> list = super.getPropertyAccessors();
		list.add(clear_pa);
		list.add(icon_pa);
		list.add(label_pa);
		list.add(left_pa);
		return list;
	}


	public List<PropertyAccessor> getModifiedPropertyAccessors()  {
		List<PropertyAccessor> list = super.getModifiedPropertyAccessors();
		if(clear_m)
			list.add(clear_pa);
		if(icon_m)
			list.add(icon_pa);
		if(label_m)
			list.add(label_pa);
		if(left_m)
			list.add(left_pa);
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsByView(net.datenwerke.gxtdto.client.dtomanager.DtoView view)  {
		List<PropertyAccessor> list = super.getPropertyAccessorsByView(view);
		if(view.compareTo(DtoView.NORMAL) >= 0){
			list.add(clear_pa);
			list.add(icon_pa);
			list.add(label_pa);
			list.add(left_pa);
		}
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsForDtos()  {
		List<PropertyAccessor> list = super.getPropertyAccessorsForDtos();
		return list;
	}




}
