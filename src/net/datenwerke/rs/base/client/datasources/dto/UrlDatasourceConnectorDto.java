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
import net.datenwerke.rs.base.client.datasources.dto.pa.UrlDatasourceConnectorDtoPA;
import net.datenwerke.rs.base.client.datasources.dto.posomap.UrlDatasourceConnectorDto2PosoMap;
import net.datenwerke.rs.base.service.datasources.connectors.UrlDatasourceConnector;

/**
 * Dto for {@link UrlDatasourceConnector}
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class UrlDatasourceConnectorDto extends DatasourceConnectorDtoDec {


	private static final long serialVersionUID = 1;


	/* Fields */
	private String url;
	private  boolean url_m;
	public static final String PROPERTY_URL = "dpi-urldatasourceconnector-url";

	private transient static PropertyAccessor<UrlDatasourceConnectorDto, String> url_pa = new PropertyAccessor<UrlDatasourceConnectorDto, String>() {
		@Override
		public void setValue(UrlDatasourceConnectorDto container, String object) {
			container.setUrl(object);
		}

		@Override
		public String getValue(UrlDatasourceConnectorDto container) {
			return container.getUrl();
		}

		@Override
		public Class<?> getType() {
			return String.class;
		}

		@Override
		public String getPath() {
			return "url";
		}

		@Override
		public void setModified(UrlDatasourceConnectorDto container, boolean modified) {
			container.url_m = modified;
		}

		@Override
		public boolean isModified(UrlDatasourceConnectorDto container) {
			return container.isUrlModified();
		}
	};


	public UrlDatasourceConnectorDto() {
		super();
	}

	public String getUrl()  {
		if(! isDtoProxy()){
			return this.url;
		}

		if(isUrlModified())
			return this.url;

		if(! GWT.isClient())
			return null;

		String _value = dtoManager.getProperty(this, instantiatePropertyAccess().url());

		return _value;
	}


	public void setUrl(String url)  {
		/* old value */
		String oldValue = null;
		if(GWT.isClient())
			oldValue = getUrl();

		/* set new value */
		this.url = url;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(url_pa, oldValue, url, this.url_m));

		/* set indicator */
		this.url_m = true;

		this.fireObjectChangedEvent(UrlDatasourceConnectorDtoPA.INSTANCE.url(), oldValue);
	}


	public boolean isUrlModified()  {
		return url_m;
	}


	public static PropertyAccessor<UrlDatasourceConnectorDto, String> getUrlPropertyAccessor()  {
		return url_pa;
	}


	@Override
	public int hashCode()  {
		if(null == getId())
			return super.hashCode();
		return getId().hashCode();
	}

	@Override
	public boolean equals(Object obj)  {
		if(! (obj instanceof UrlDatasourceConnectorDto))
			return false;

		if(null == getId())
			return super.equals(obj);
		return getId().equals(((UrlDatasourceConnectorDto)obj).getId());
	}

	@Override
	public String toString()  {
		return getClass().getName() + ": " + getId();
	}

	public static Dto2PosoMapper newPosoMapper()  {
		return new UrlDatasourceConnectorDto2PosoMap();
	}

	public UrlDatasourceConnectorDtoPA instantiatePropertyAccess()  {
		return GWT.create(UrlDatasourceConnectorDtoPA.class);
	}

	public void clearModified()  {
		this.url = null;
		this.url_m = false;
	}


	public boolean isModified()  {
		if(super.isModified())
			return true;
		if(url_m)
			return true;
		return false;
	}


	public List<PropertyAccessor> getPropertyAccessors()  {
		List<PropertyAccessor> list = super.getPropertyAccessors();
		list.add(url_pa);
		return list;
	}


	public List<PropertyAccessor> getModifiedPropertyAccessors()  {
		List<PropertyAccessor> list = super.getModifiedPropertyAccessors();
		if(url_m)
			list.add(url_pa);
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsByView(net.datenwerke.gxtdto.client.dtomanager.DtoView view)  {
		List<PropertyAccessor> list = super.getPropertyAccessorsByView(view);
		if(view.compareTo(DtoView.ALL) >= 0){
			list.add(url_pa);
		}
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsForDtos()  {
		List<PropertyAccessor> list = super.getPropertyAccessorsForDtos();
		return list;
	}




}
