package net.datenwerke.rs.core.client.reportexporter.dto;

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
import net.datenwerke.rs.core.client.reportexporter.dto.ReportExecutionConfigDto;
import net.datenwerke.rs.core.client.reportexporter.dto.pa.RECCsvDtoPA;
import net.datenwerke.rs.core.client.reportexporter.dto.posomap.RECCsvDto2PosoMap;
import net.datenwerke.rs.core.service.reportmanager.engine.config.RECCsv;

/**
 * Dto for {@link RECCsv}
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
abstract public class RECCsvDto extends RsDto implements ReportExecutionConfigDto {


	private static final long serialVersionUID = 1;


	/* Fields */
	private String charset;
	private  boolean charset_m;
	public static final String PROPERTY_CHARSET = "dpi-reccsv-charset";

	private transient static PropertyAccessor<RECCsvDto, String> charset_pa = new PropertyAccessor<RECCsvDto, String>() {
		@Override
		public void setValue(RECCsvDto container, String object) {
			container.setCharset(object);
		}

		@Override
		public String getValue(RECCsvDto container) {
			return container.getCharset();
		}

		@Override
		public Class<?> getType() {
			return String.class;
		}

		@Override
		public String getPath() {
			return "charset";
		}

		@Override
		public void setModified(RECCsvDto container, boolean modified) {
			container.charset_m = modified;
		}

		@Override
		public boolean isModified(RECCsvDto container) {
			return container.isCharsetModified();
		}
	};

	private String lineSeparator;
	private  boolean lineSeparator_m;
	public static final String PROPERTY_LINE_SEPARATOR = "dpi-reccsv-lineseparator";

	private transient static PropertyAccessor<RECCsvDto, String> lineSeparator_pa = new PropertyAccessor<RECCsvDto, String>() {
		@Override
		public void setValue(RECCsvDto container, String object) {
			container.setLineSeparator(object);
		}

		@Override
		public String getValue(RECCsvDto container) {
			return container.getLineSeparator();
		}

		@Override
		public Class<?> getType() {
			return String.class;
		}

		@Override
		public String getPath() {
			return "lineSeparator";
		}

		@Override
		public void setModified(RECCsvDto container, boolean modified) {
			container.lineSeparator_m = modified;
		}

		@Override
		public boolean isModified(RECCsvDto container) {
			return container.isLineSeparatorModified();
		}
	};

	private boolean printHeader;
	private  boolean printHeader_m;
	public static final String PROPERTY_PRINT_HEADER = "dpi-reccsv-printheader";

	private transient static PropertyAccessor<RECCsvDto, Boolean> printHeader_pa = new PropertyAccessor<RECCsvDto, Boolean>() {
		@Override
		public void setValue(RECCsvDto container, Boolean object) {
			container.setPrintHeader(object);
		}

		@Override
		public Boolean getValue(RECCsvDto container) {
			return container.isPrintHeader();
		}

		@Override
		public Class<?> getType() {
			return Boolean.class;
		}

		@Override
		public String getPath() {
			return "printHeader";
		}

		@Override
		public void setModified(RECCsvDto container, boolean modified) {
			container.printHeader_m = modified;
		}

		@Override
		public boolean isModified(RECCsvDto container) {
			return container.isPrintHeaderModified();
		}
	};

	private String quote;
	private  boolean quote_m;
	public static final String PROPERTY_QUOTE = "dpi-reccsv-quote";

	private transient static PropertyAccessor<RECCsvDto, String> quote_pa = new PropertyAccessor<RECCsvDto, String>() {
		@Override
		public void setValue(RECCsvDto container, String object) {
			container.setQuote(object);
		}

		@Override
		public String getValue(RECCsvDto container) {
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
		public void setModified(RECCsvDto container, boolean modified) {
			container.quote_m = modified;
		}

		@Override
		public boolean isModified(RECCsvDto container) {
			return container.isQuoteModified();
		}
	};

	private String separator;
	private  boolean separator_m;
	public static final String PROPERTY_SEPARATOR = "dpi-reccsv-separator";

	private transient static PropertyAccessor<RECCsvDto, String> separator_pa = new PropertyAccessor<RECCsvDto, String>() {
		@Override
		public void setValue(RECCsvDto container, String object) {
			container.setSeparator(object);
		}

		@Override
		public String getValue(RECCsvDto container) {
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
		public void setModified(RECCsvDto container, boolean modified) {
			container.separator_m = modified;
		}

		@Override
		public boolean isModified(RECCsvDto container) {
			return container.isSeparatorModified();
		}
	};


	public RECCsvDto() {
		super();
	}

	public String getCharset()  {
		if(! isDtoProxy()){
			return this.charset;
		}

		if(isCharsetModified())
			return this.charset;

		if(! GWT.isClient())
			return null;

		String _value = dtoManager.getProperty(this, instantiatePropertyAccess().charset());

		return _value;
	}


	public void setCharset(String charset)  {
		/* old value */
		String oldValue = null;
		if(GWT.isClient())
			oldValue = getCharset();

		/* set new value */
		this.charset = charset;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(charset_pa, oldValue, charset, this.charset_m));

		/* set indicator */
		this.charset_m = true;

		this.fireObjectChangedEvent(RECCsvDtoPA.INSTANCE.charset(), oldValue);
	}


	public boolean isCharsetModified()  {
		return charset_m;
	}


	public static PropertyAccessor<RECCsvDto, String> getCharsetPropertyAccessor()  {
		return charset_pa;
	}


	public String getLineSeparator()  {
		if(! isDtoProxy()){
			return this.lineSeparator;
		}

		if(isLineSeparatorModified())
			return this.lineSeparator;

		if(! GWT.isClient())
			return null;

		String _value = dtoManager.getProperty(this, instantiatePropertyAccess().lineSeparator());

		return _value;
	}


	public void setLineSeparator(String lineSeparator)  {
		/* old value */
		String oldValue = null;
		if(GWT.isClient())
			oldValue = getLineSeparator();

		/* set new value */
		this.lineSeparator = lineSeparator;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(lineSeparator_pa, oldValue, lineSeparator, this.lineSeparator_m));

		/* set indicator */
		this.lineSeparator_m = true;

		this.fireObjectChangedEvent(RECCsvDtoPA.INSTANCE.lineSeparator(), oldValue);
	}


	public boolean isLineSeparatorModified()  {
		return lineSeparator_m;
	}


	public static PropertyAccessor<RECCsvDto, String> getLineSeparatorPropertyAccessor()  {
		return lineSeparator_pa;
	}


	public boolean isPrintHeader()  {
		if(! isDtoProxy()){
			return this.printHeader;
		}

		if(isPrintHeaderModified())
			return this.printHeader;

		if(! GWT.isClient())
			return false;

		boolean _value = dtoManager.getProperty(this, instantiatePropertyAccess().printHeader());

		return _value;
	}


	public void setPrintHeader(boolean printHeader)  {
		/* old value */
		boolean oldValue = false;
		if(GWT.isClient())
			oldValue = isPrintHeader();

		/* set new value */
		this.printHeader = printHeader;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(printHeader_pa, oldValue, printHeader, this.printHeader_m));

		/* set indicator */
		this.printHeader_m = true;

		this.fireObjectChangedEvent(RECCsvDtoPA.INSTANCE.printHeader(), oldValue);
	}


	public boolean isPrintHeaderModified()  {
		return printHeader_m;
	}


	public static PropertyAccessor<RECCsvDto, Boolean> getPrintHeaderPropertyAccessor()  {
		return printHeader_pa;
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

		this.fireObjectChangedEvent(RECCsvDtoPA.INSTANCE.quote(), oldValue);
	}


	public boolean isQuoteModified()  {
		return quote_m;
	}


	public static PropertyAccessor<RECCsvDto, String> getQuotePropertyAccessor()  {
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

		this.fireObjectChangedEvent(RECCsvDtoPA.INSTANCE.separator(), oldValue);
	}


	public boolean isSeparatorModified()  {
		return separator_m;
	}


	public static PropertyAccessor<RECCsvDto, String> getSeparatorPropertyAccessor()  {
		return separator_pa;
	}


	@Override
	public String toString()  {
		return super.toString();
	}

	public static Dto2PosoMapper newPosoMapper()  {
		return new RECCsvDto2PosoMap();
	}

	public RECCsvDtoPA instantiatePropertyAccess()  {
		return GWT.create(RECCsvDtoPA.class);
	}

	public void clearModified()  {
		this.charset = null;
		this.charset_m = false;
		this.lineSeparator = null;
		this.lineSeparator_m = false;
		this.printHeader = false;
		this.printHeader_m = false;
		this.quote = null;
		this.quote_m = false;
		this.separator = null;
		this.separator_m = false;
	}


	public boolean isModified()  {
		if(super.isModified())
			return true;
		if(charset_m)
			return true;
		if(lineSeparator_m)
			return true;
		if(printHeader_m)
			return true;
		if(quote_m)
			return true;
		if(separator_m)
			return true;
		return false;
	}


	public List<PropertyAccessor> getPropertyAccessors()  {
		List<PropertyAccessor> list = super.getPropertyAccessors();
		list.add(charset_pa);
		list.add(lineSeparator_pa);
		list.add(printHeader_pa);
		list.add(quote_pa);
		list.add(separator_pa);
		return list;
	}


	public List<PropertyAccessor> getModifiedPropertyAccessors()  {
		List<PropertyAccessor> list = super.getModifiedPropertyAccessors();
		if(charset_m)
			list.add(charset_pa);
		if(lineSeparator_m)
			list.add(lineSeparator_pa);
		if(printHeader_m)
			list.add(printHeader_pa);
		if(quote_m)
			list.add(quote_pa);
		if(separator_m)
			list.add(separator_pa);
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsByView(net.datenwerke.gxtdto.client.dtomanager.DtoView view)  {
		List<PropertyAccessor> list = super.getPropertyAccessorsByView(view);
		if(view.compareTo(DtoView.NORMAL) >= 0){
			list.add(charset_pa);
			list.add(lineSeparator_pa);
			list.add(printHeader_pa);
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
