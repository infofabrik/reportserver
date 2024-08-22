package net.datenwerke.rs.adminutils.client.logs.dto;

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
import net.datenwerke.rs.adminutils.client.logs.dto.pa.ViewLogFileCommandResultExtensionDtoPA;
import net.datenwerke.rs.adminutils.client.logs.dto.posomap.ViewLogFileCommandResultExtensionDto2PosoMap;
import net.datenwerke.rs.adminutils.service.logs.terminal.commands.ViewLogFileCommandResultExtension;
import net.datenwerke.rs.terminal.client.terminal.dto.CommandResultExtensionDto;

/**
 * Dto for {@link ViewLogFileCommandResultExtension}
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class ViewLogFileCommandResultExtensionDto extends CommandResultExtensionDto {


	private static final long serialVersionUID = 1;


	/* Fields */
	private List<String> data;
	private  boolean data_m;
	public static final String PROPERTY_DATA = "dpi-viewlogfilecommandresultextension-data";

	private transient static PropertyAccessor<ViewLogFileCommandResultExtensionDto, List<String>> data_pa = new PropertyAccessor<ViewLogFileCommandResultExtensionDto, List<String>>() {
		@Override
		public void setValue(ViewLogFileCommandResultExtensionDto container, List<String> object) {
			container.setData(object);
		}

		@Override
		public List<String> getValue(ViewLogFileCommandResultExtensionDto container) {
			return container.getData();
		}

		@Override
		public Class<?> getType() {
			return List.class;
		}

		@Override
		public String getPath() {
			return "data";
		}

		@Override
		public void setModified(ViewLogFileCommandResultExtensionDto container, boolean modified) {
			container.data_m = modified;
		}

		@Override
		public boolean isModified(ViewLogFileCommandResultExtensionDto container) {
			return container.isDataModified();
		}
	};

	private String filename;
	private  boolean filename_m;
	public static final String PROPERTY_FILENAME = "dpi-viewlogfilecommandresultextension-filename";

	private transient static PropertyAccessor<ViewLogFileCommandResultExtensionDto, String> filename_pa = new PropertyAccessor<ViewLogFileCommandResultExtensionDto, String>() {
		@Override
		public void setValue(ViewLogFileCommandResultExtensionDto container, String object) {
			container.setFilename(object);
		}

		@Override
		public String getValue(ViewLogFileCommandResultExtensionDto container) {
			return container.getFilename();
		}

		@Override
		public Class<?> getType() {
			return String.class;
		}

		@Override
		public String getPath() {
			return "filename";
		}

		@Override
		public void setModified(ViewLogFileCommandResultExtensionDto container, boolean modified) {
			container.filename_m = modified;
		}

		@Override
		public boolean isModified(ViewLogFileCommandResultExtensionDto container) {
			return container.isFilenameModified();
		}
	};


	public ViewLogFileCommandResultExtensionDto() {
		super();
	}

	public List<String> getData()  {
		if(! isDtoProxy()){
			List<String> _currentValue = this.data;
			if(null == _currentValue)
				this.data = new ArrayList<String>();

			return this.data;
		}

		if(isDataModified())
			return this.data;

		if(! GWT.isClient())
			return null;

		List<String> _value = dtoManager.getProperty(this, instantiatePropertyAccess().data());

		return _value;
	}


	public void setData(List<String> data)  {
		/* old value */
		List<String> oldValue = null;
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

		this.fireObjectChangedEvent(ViewLogFileCommandResultExtensionDtoPA.INSTANCE.data(), oldValue);
	}


	public boolean isDataModified()  {
		return data_m;
	}


	public static PropertyAccessor<ViewLogFileCommandResultExtensionDto, List<String>> getDataPropertyAccessor()  {
		return data_pa;
	}


	public String getFilename()  {
		if(! isDtoProxy()){
			return this.filename;
		}

		if(isFilenameModified())
			return this.filename;

		if(! GWT.isClient())
			return null;

		String _value = dtoManager.getProperty(this, instantiatePropertyAccess().filename());

		return _value;
	}


	public void setFilename(String filename)  {
		/* old value */
		String oldValue = null;
		if(GWT.isClient())
			oldValue = getFilename();

		/* set new value */
		this.filename = filename;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(filename_pa, oldValue, filename, this.filename_m));

		/* set indicator */
		this.filename_m = true;

		this.fireObjectChangedEvent(ViewLogFileCommandResultExtensionDtoPA.INSTANCE.filename(), oldValue);
	}


	public boolean isFilenameModified()  {
		return filename_m;
	}


	public static PropertyAccessor<ViewLogFileCommandResultExtensionDto, String> getFilenamePropertyAccessor()  {
		return filename_pa;
	}


	@Override
	public String toString()  {
		return super.toString();
	}

	public static Dto2PosoMapper newPosoMapper()  {
		return new ViewLogFileCommandResultExtensionDto2PosoMap();
	}

	public ViewLogFileCommandResultExtensionDtoPA instantiatePropertyAccess()  {
		return GWT.create(ViewLogFileCommandResultExtensionDtoPA.class);
	}

	public void clearModified()  {
		this.data = null;
		this.data_m = false;
		this.filename = null;
		this.filename_m = false;
	}


	public boolean isModified()  {
		if(super.isModified())
			return true;
		if(data_m)
			return true;
		if(filename_m)
			return true;
		return false;
	}


	public List<PropertyAccessor> getPropertyAccessors()  {
		List<PropertyAccessor> list = super.getPropertyAccessors();
		list.add(data_pa);
		list.add(filename_pa);
		return list;
	}


	public List<PropertyAccessor> getModifiedPropertyAccessors()  {
		List<PropertyAccessor> list = super.getModifiedPropertyAccessors();
		if(data_m)
			list.add(data_pa);
		if(filename_m)
			list.add(filename_pa);
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsByView(net.datenwerke.gxtdto.client.dtomanager.DtoView view)  {
		List<PropertyAccessor> list = super.getPropertyAccessorsByView(view);
		if(view.compareTo(DtoView.NORMAL) >= 0){
			list.add(data_pa);
			list.add(filename_pa);
		}
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsForDtos()  {
		List<PropertyAccessor> list = super.getPropertyAccessorsForDtos();
		return list;
	}




}
