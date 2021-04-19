package net.datenwerke.rs.base.client.datasources.dto;

import com.google.gwt.core.client.GWT;
import java.lang.NullPointerException;
import java.lang.String;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.gxtdto.client.dtomanager.Dto2PosoMapper;
import net.datenwerke.gxtdto.client.dtomanager.DtoView;
import net.datenwerke.gxtdto.client.dtomanager.PropertyAccessor;
import net.datenwerke.gxtdto.client.dtomanager.redoundo.ChangeTracker;
import net.datenwerke.gxtdto.client.locale.BaseMessages;
import net.datenwerke.rs.base.client.datasources.dto.FormatBasedDatasourceDefinitionDto;
import net.datenwerke.rs.base.client.datasources.dto.pa.CsvDatasourceDtoPA;
import net.datenwerke.rs.base.client.datasources.dto.posomap.CsvDatasourceDto2PosoMap;
import net.datenwerke.rs.base.service.datasources.definitions.CsvDatasource;

/**
 * Dto for {@link CsvDatasource}
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class CsvDatasourceDto extends FormatBasedDatasourceDefinitionDto {


	private static final long serialVersionUID = 1;


	/* Fields */
	private int databaseCache;
	private  boolean databaseCache_m;
	public static final String PROPERTY_DATABASE_CACHE = "dpi-csvdatasource-databasecache";

	private transient static PropertyAccessor<CsvDatasourceDto, Integer> databaseCache_pa = new PropertyAccessor<CsvDatasourceDto, Integer>() {
		@Override
		public void setValue(CsvDatasourceDto container, Integer object) {
			container.setDatabaseCache(object);
		}

		@Override
		public Integer getValue(CsvDatasourceDto container) {
			return container.getDatabaseCache();
		}

		@Override
		public Class<?> getType() {
			return Integer.class;
		}

		@Override
		public String getPath() {
			return "databaseCache";
		}

		@Override
		public void setModified(CsvDatasourceDto container, boolean modified) {
			container.databaseCache_m = modified;
		}

		@Override
		public boolean isModified(CsvDatasourceDto container) {
			return container.isDatabaseCacheModified();
		}
	};

	private String quote;
	private  boolean quote_m;
	public static final String PROPERTY_QUOTE = "dpi-csvdatasource-quote";

	private transient static PropertyAccessor<CsvDatasourceDto, String> quote_pa = new PropertyAccessor<CsvDatasourceDto, String>() {
		@Override
		public void setValue(CsvDatasourceDto container, String object) {
			container.setQuote(object);
		}

		@Override
		public String getValue(CsvDatasourceDto container) {
			return container.getQuote();
		}

		@Override
		public Class<?> getType() {
			return String.class;
		}

		@Override
		public String getPath() {
			return "quote";
		}

		@Override
		public void setModified(CsvDatasourceDto container, boolean modified) {
			container.quote_m = modified;
		}

		@Override
		public boolean isModified(CsvDatasourceDto container) {
			return container.isQuoteModified();
		}
	};

	private String separator;
	private  boolean separator_m;
	public static final String PROPERTY_SEPARATOR = "dpi-csvdatasource-separator";

	private transient static PropertyAccessor<CsvDatasourceDto, String> separator_pa = new PropertyAccessor<CsvDatasourceDto, String>() {
		@Override
		public void setValue(CsvDatasourceDto container, String object) {
			container.setSeparator(object);
		}

		@Override
		public String getValue(CsvDatasourceDto container) {
			return container.getSeparator();
		}

		@Override
		public Class<?> getType() {
			return String.class;
		}

		@Override
		public String getPath() {
			return "separator";
		}

		@Override
		public void setModified(CsvDatasourceDto container, boolean modified) {
			container.separator_m = modified;
		}

		@Override
		public boolean isModified(CsvDatasourceDto container) {
			return container.isSeparatorModified();
		}
	};


	public CsvDatasourceDto() {
		super();
	}

	public int getDatabaseCache()  {
		if(! isDtoProxy()){
			return this.databaseCache;
		}

		if(isDatabaseCacheModified())
			return this.databaseCache;

		if(! GWT.isClient())
			return 0;

		int _value = dtoManager.getProperty(this, instantiatePropertyAccess().databaseCache());

		return _value;
	}


	public void setDatabaseCache(int databaseCache)  {
		/* old value */
		int oldValue = 0;
		if(GWT.isClient())
			oldValue = getDatabaseCache();

		/* set new value */
		this.databaseCache = databaseCache;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(databaseCache_pa, oldValue, databaseCache, this.databaseCache_m));

		/* set indicator */
		this.databaseCache_m = true;

		this.fireObjectChangedEvent(CsvDatasourceDtoPA.INSTANCE.databaseCache(), oldValue);
	}


	public boolean isDatabaseCacheModified()  {
		return databaseCache_m;
	}


	public static PropertyAccessor<CsvDatasourceDto, Integer> getDatabaseCachePropertyAccessor()  {
		return databaseCache_pa;
	}


	public String getQuote()  {
		if(! isDtoProxy()){
			return this.quote;
		}

		if(isQuoteModified())
			return this.quote;

		if(! GWT.isClient())
			return null;

		String _value = dtoManager.getProperty(this, instantiatePropertyAccess().quote());

		return _value;
	}


	public void setQuote(String quote)  {
		/* old value */
		String oldValue = null;
		if(GWT.isClient())
			oldValue = getQuote();

		/* set new value */
		this.quote = quote;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(quote_pa, oldValue, quote, this.quote_m));

		/* set indicator */
		this.quote_m = true;

		this.fireObjectChangedEvent(CsvDatasourceDtoPA.INSTANCE.quote(), oldValue);
	}


	public boolean isQuoteModified()  {
		return quote_m;
	}


	public static PropertyAccessor<CsvDatasourceDto, String> getQuotePropertyAccessor()  {
		return quote_pa;
	}


	public String getSeparator()  {
		if(! isDtoProxy()){
			return this.separator;
		}

		if(isSeparatorModified())
			return this.separator;

		if(! GWT.isClient())
			return null;

		String _value = dtoManager.getProperty(this, instantiatePropertyAccess().separator());

		return _value;
	}


	public void setSeparator(String separator)  {
		/* old value */
		String oldValue = null;
		if(GWT.isClient())
			oldValue = getSeparator();

		/* set new value */
		this.separator = separator;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(separator_pa, oldValue, separator, this.separator_m));

		/* set indicator */
		this.separator_m = true;

		this.fireObjectChangedEvent(CsvDatasourceDtoPA.INSTANCE.separator(), oldValue);
	}


	public boolean isSeparatorModified()  {
		return separator_m;
	}


	public static PropertyAccessor<CsvDatasourceDto, String> getSeparatorPropertyAccessor()  {
		return separator_pa;
	}


	@Override
	public String toDisplayTitle()  {
		try{
			if(null == getName())
				return BaseMessages.INSTANCE.unnamed();
			return getName().toString();
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
		if(! (obj instanceof CsvDatasourceDto))
			return false;

		if(null == getId())
			return super.equals(obj);
		return getId().equals(((CsvDatasourceDto)obj).getId());
	}

	@Override
	public String toString()  {
		return getClass().getName() + ": " + getId();
	}

	public static Dto2PosoMapper newPosoMapper()  {
		return new CsvDatasourceDto2PosoMap();
	}

	public CsvDatasourceDtoPA instantiatePropertyAccess()  {
		return GWT.create(CsvDatasourceDtoPA.class);
	}

	public void clearModified()  {
		this.databaseCache = 0;
		this.databaseCache_m = false;
		this.quote = null;
		this.quote_m = false;
		this.separator = null;
		this.separator_m = false;
	}


	public boolean isModified()  {
		if(super.isModified())
			return true;
		if(databaseCache_m)
			return true;
		if(quote_m)
			return true;
		if(separator_m)
			return true;
		return false;
	}


	public List<PropertyAccessor> getPropertyAccessors()  {
		List<PropertyAccessor> list = super.getPropertyAccessors();
		list.add(databaseCache_pa);
		list.add(quote_pa);
		list.add(separator_pa);
		return list;
	}


	public List<PropertyAccessor> getModifiedPropertyAccessors()  {
		List<PropertyAccessor> list = super.getModifiedPropertyAccessors();
		if(databaseCache_m)
			list.add(databaseCache_pa);
		if(quote_m)
			list.add(quote_pa);
		if(separator_m)
			list.add(separator_pa);
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsByView(net.datenwerke.gxtdto.client.dtomanager.DtoView view)  {
		List<PropertyAccessor> list = super.getPropertyAccessorsByView(view);
		if(view.compareTo(DtoView.NORMAL) >= 0){
			list.add(databaseCache_pa);
			list.add(quote_pa);
			list.add(separator_pa);
		}
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsForDtos()  {
		List<PropertyAccessor> list = super.getPropertyAccessorsForDtos();
		return list;
	}




}
