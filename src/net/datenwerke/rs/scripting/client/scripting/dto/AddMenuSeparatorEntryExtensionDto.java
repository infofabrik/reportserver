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
import net.datenwerke.rs.scripting.client.scripting.dto.pa.AddMenuSeparatorEntryExtensionDtoPA;
import net.datenwerke.rs.scripting.client.scripting.dto.posomap.AddMenuSeparatorEntryExtensionDto2PosoMap;
import net.datenwerke.rs.scripting.service.scripting.extensions.AddMenuSeparatorEntryExtension;
import net.datenwerke.rs.terminal.client.terminal.dto.CommandResultExtensionDto;

/**
 * Dto for {@link AddMenuSeparatorEntryExtension}
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class AddMenuSeparatorEntryExtensionDto extends CommandResultExtensionDto {


	private static final long serialVersionUID = 1;


	/* Fields */
	private String menuName;
	private  boolean menuName_m;
	public static final String PROPERTY_MENU_NAME = "dpi-addmenuseparatorentryextension-menuname";

	private transient static PropertyAccessor<AddMenuSeparatorEntryExtensionDto, String> menuName_pa = new PropertyAccessor<AddMenuSeparatorEntryExtensionDto, String>() {
		@Override
		public void setValue(AddMenuSeparatorEntryExtensionDto container, String object) {
			container.setMenuName(object);
		}

		@Override
		public String getValue(AddMenuSeparatorEntryExtensionDto container) {
			return container.getMenuName();
		}

		@Override
		public Class<?> getType() {
			return String.class;
		}

		@Override
		public String getPath() {
			return "menuName";
		}

		@Override
		public void setModified(AddMenuSeparatorEntryExtensionDto container, boolean modified) {
			container.menuName_m = modified;
		}

		@Override
		public boolean isModified(AddMenuSeparatorEntryExtensionDto container) {
			return container.isMenuNameModified();
		}
	};


	public AddMenuSeparatorEntryExtensionDto() {
		super();
	}

	public String getMenuName()  {
		if(! isDtoProxy()){
			return this.menuName;
		}

		if(isMenuNameModified())
			return this.menuName;

		if(! GWT.isClient())
			return null;

		String _value = dtoManager.getProperty(this, instantiatePropertyAccess().menuName());

		return _value;
	}


	public void setMenuName(String menuName)  {
		/* old value */
		String oldValue = null;
		if(GWT.isClient())
			oldValue = getMenuName();

		/* set new value */
		this.menuName = menuName;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(menuName_pa, oldValue, menuName, this.menuName_m));

		/* set indicator */
		this.menuName_m = true;

		this.fireObjectChangedEvent(AddMenuSeparatorEntryExtensionDtoPA.INSTANCE.menuName(), oldValue);
	}


	public boolean isMenuNameModified()  {
		return menuName_m;
	}


	public static PropertyAccessor<AddMenuSeparatorEntryExtensionDto, String> getMenuNamePropertyAccessor()  {
		return menuName_pa;
	}


	@Override
	public String toString()  {
		return super.toString();
	}

	public static Dto2PosoMapper newPosoMapper()  {
		return new AddMenuSeparatorEntryExtensionDto2PosoMap();
	}

	public AddMenuSeparatorEntryExtensionDtoPA instantiatePropertyAccess()  {
		return GWT.create(AddMenuSeparatorEntryExtensionDtoPA.class);
	}

	public void clearModified()  {
		this.menuName = null;
		this.menuName_m = false;
	}


	public boolean isModified()  {
		if(super.isModified())
			return true;
		if(menuName_m)
			return true;
		return false;
	}


	public List<PropertyAccessor> getPropertyAccessors()  {
		List<PropertyAccessor> list = super.getPropertyAccessors();
		list.add(menuName_pa);
		return list;
	}


	public List<PropertyAccessor> getModifiedPropertyAccessors()  {
		List<PropertyAccessor> list = super.getModifiedPropertyAccessors();
		if(menuName_m)
			list.add(menuName_pa);
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsByView(net.datenwerke.gxtdto.client.dtomanager.DtoView view)  {
		List<PropertyAccessor> list = super.getPropertyAccessorsByView(view);
		if(view.compareTo(DtoView.NORMAL) >= 0){
			list.add(menuName_pa);
		}
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsForDtos()  {
		List<PropertyAccessor> list = super.getPropertyAccessorsForDtos();
		return list;
	}




}
