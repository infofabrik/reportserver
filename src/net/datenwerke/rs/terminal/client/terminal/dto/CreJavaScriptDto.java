package net.datenwerke.rs.terminal.client.terminal.dto;

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
import net.datenwerke.rs.terminal.client.terminal.dto.CommandResultExtensionDto;
import net.datenwerke.rs.terminal.client.terminal.dto.pa.CreJavaScriptDtoPA;
import net.datenwerke.rs.terminal.client.terminal.dto.posomap.CreJavaScriptDto2PosoMap;
import net.datenwerke.rs.terminal.service.terminal.obj.CreJavaScript;

/**
 * Dto for {@link CreJavaScript}
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class CreJavaScriptDto extends CommandResultExtensionDto {


	private static final long serialVersionUID = 1;


	/* Fields */
	private String data;
	private  boolean data_m;
	public static final String PROPERTY_DATA = "dpi-crejavascript-data";

	private transient static PropertyAccessor<CreJavaScriptDto, String> data_pa = new PropertyAccessor<CreJavaScriptDto, String>() {
		@Override
		public void setValue(CreJavaScriptDto container, String object) {
			container.setData(object);
		}

		@Override
		public String getValue(CreJavaScriptDto container) {
			return container.getData();
		}

		@Override
		public Class<?> getType() {
			return String.class;
		}

		@Override
		public String getPath() {
			return "data";
		}

		@Override
		public void setModified(CreJavaScriptDto container, boolean modified) {
			container.data_m = modified;
		}

		@Override
		public boolean isModified(CreJavaScriptDto container) {
			return container.isDataModified();
		}
	};


	public CreJavaScriptDto() {
		super();
	}

	public String getData()  {
		if(! isDtoProxy()){
			return this.data;
		}

		if(isDataModified())
			return this.data;

		if(! GWT.isClient())
			return null;

		String _value = dtoManager.getProperty(this, instantiatePropertyAccess().data());

		return _value;
	}


	public void setData(String data)  {
		/* old value */
		String oldValue = null;
		if(GWT.isClient())
			oldValue = getData();

		/* set new value */
		this.data = data;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(data_pa, oldValue, data, this.data_m));

		/* set indicator */
		this.data_m = true;

		this.fireObjectChangedEvent(CreJavaScriptDtoPA.INSTANCE.data(), oldValue);
	}


	public boolean isDataModified()  {
		return data_m;
	}


	public static PropertyAccessor<CreJavaScriptDto, String> getDataPropertyAccessor()  {
		return data_pa;
	}


	@Override
	public String toString()  {
		return super.toString();
	}

	public static Dto2PosoMapper newPosoMapper()  {
		return new CreJavaScriptDto2PosoMap();
	}

	public CreJavaScriptDtoPA instantiatePropertyAccess()  {
		return GWT.create(CreJavaScriptDtoPA.class);
	}

	public void clearModified()  {
		this.data = null;
		this.data_m = false;
	}


	public boolean isModified()  {
		if(super.isModified())
			return true;
		if(data_m)
			return true;
		return false;
	}


	public List<PropertyAccessor> getPropertyAccessors()  {
		List<PropertyAccessor> list = super.getPropertyAccessors();
		list.add(data_pa);
		return list;
	}


	public List<PropertyAccessor> getModifiedPropertyAccessors()  {
		List<PropertyAccessor> list = super.getModifiedPropertyAccessors();
		if(data_m)
			list.add(data_pa);
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsByView(net.datenwerke.gxtdto.client.dtomanager.DtoView view)  {
		List<PropertyAccessor> list = super.getPropertyAccessorsByView(view);
		if(view.compareTo(DtoView.NORMAL) >= 0){
			list.add(data_pa);
		}
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsForDtos()  {
		List<PropertyAccessor> list = super.getPropertyAccessorsForDtos();
		return list;
	}




}
