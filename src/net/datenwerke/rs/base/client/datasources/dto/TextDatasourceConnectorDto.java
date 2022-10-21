package net.datenwerke.rs.base.client.datasources.dto;

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
import net.datenwerke.rs.base.client.datasources.dto.decorator.DatasourceConnectorDtoDec;
import net.datenwerke.rs.base.client.datasources.dto.pa.TextDatasourceConnectorDtoPA;
import net.datenwerke.rs.base.client.datasources.dto.posomap.TextDatasourceConnectorDto2PosoMap;
import net.datenwerke.rs.base.service.datasources.connectors.TextDatasourceConnector;

/**
 * Dto for {@link TextDatasourceConnector}
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class TextDatasourceConnectorDto extends DatasourceConnectorDtoDec {


	private static final long serialVersionUID = 1;


	/* Fields */
	private String data;
	private  boolean data_m;
	public static final String PROPERTY_DATA = "dpi-textdatasourceconnector-data";

	private transient static PropertyAccessor<TextDatasourceConnectorDto, String> data_pa = new PropertyAccessor<TextDatasourceConnectorDto, String>() {
		@Override
		public void setValue(TextDatasourceConnectorDto container, String object) {
			container.setData(object);
		}

		@Override
		public String getValue(TextDatasourceConnectorDto container) {
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
		public void setModified(TextDatasourceConnectorDto container, boolean modified) {
			container.data_m = modified;
		}

		@Override
		public boolean isModified(TextDatasourceConnectorDto container) {
			return container.isDataModified();
		}
	};


	public TextDatasourceConnectorDto() {
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

		this.fireObjectChangedEvent(TextDatasourceConnectorDtoPA.INSTANCE.data(), oldValue);
	}


	public boolean isDataModified()  {
		return data_m;
	}


	public static PropertyAccessor<TextDatasourceConnectorDto, String> getDataPropertyAccessor()  {
		return data_pa;
	}


	@Override
	public int hashCode()  {
		if(null == getId())
			return super.hashCode();
		return getId().hashCode();
	}

	@Override
	public boolean equals(Object obj)  {
		if(! (obj instanceof TextDatasourceConnectorDto))
			return false;

		if(null == getId())
			return super.equals(obj);
		return getId().equals(((TextDatasourceConnectorDto)obj).getId());
	}

	@Override
	public String toString()  {
		return getClass().getName() + ": " + getId();
	}

	public static Dto2PosoMapper newPosoMapper()  {
		return new TextDatasourceConnectorDto2PosoMap();
	}

	public TextDatasourceConnectorDtoPA instantiatePropertyAccess()  {
		return GWT.create(TextDatasourceConnectorDtoPA.class);
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
		if(view.compareTo(DtoView.ALL) >= 0){
			list.add(data_pa);
		}
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsForDtos()  {
		List<PropertyAccessor> list = super.getPropertyAccessorsForDtos();
		return list;
	}




}
