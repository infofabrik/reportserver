package net.datenwerke.rs.core.client.reportexporter.dto;

import com.google.gwt.core.client.GWT;
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
import net.datenwerke.rs.core.client.reportexporter.dto.pa.RECJxlsDtoPA;
import net.datenwerke.rs.core.client.reportexporter.dto.posomap.RECJxlsDto2PosoMap;
import net.datenwerke.rs.core.service.reportmanager.engine.config.RECJxls;

/**
 * Dto for {@link RECJxls}
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
abstract public class RECJxlsDto extends RsDto implements ReportExecutionConfigDto {


	private static final long serialVersionUID = 1;


	/* Fields */
	private int currencyColumnWidth;
	private  boolean currencyColumnWidth_m;
	public static final String PROPERTY_CURRENCY_COLUMN_WIDTH = "dpi-recjxls-currencycolumnwidth";

	private transient static PropertyAccessor<RECJxlsDto, Integer> currencyColumnWidth_pa = new PropertyAccessor<RECJxlsDto, Integer>() {
		@Override
		public void setValue(RECJxlsDto container, Integer object) {
			container.setCurrencyColumnWidth(object);
		}

		@Override
		public Integer getValue(RECJxlsDto container) {
			return container.getCurrencyColumnWidth();
		}

		@Override
		public Class<?> getType() {
			return Integer.class;
		}

		@Override
		public String getPath() {
			return "currencyColumnWidth";
		}

		@Override
		public void setModified(RECJxlsDto container, boolean modified) {
			container.currencyColumnWidth_m = modified;
		}

		@Override
		public boolean isModified(RECJxlsDto container) {
			return container.isCurrencyColumnWidthModified();
		}
	};

	private int dateColumnWidth;
	private  boolean dateColumnWidth_m;
	public static final String PROPERTY_DATE_COLUMN_WIDTH = "dpi-recjxls-datecolumnwidth";

	private transient static PropertyAccessor<RECJxlsDto, Integer> dateColumnWidth_pa = new PropertyAccessor<RECJxlsDto, Integer>() {
		@Override
		public void setValue(RECJxlsDto container, Integer object) {
			container.setDateColumnWidth(object);
		}

		@Override
		public Integer getValue(RECJxlsDto container) {
			return container.getDateColumnWidth();
		}

		@Override
		public Class<?> getType() {
			return Integer.class;
		}

		@Override
		public String getPath() {
			return "dateColumnWidth";
		}

		@Override
		public void setModified(RECJxlsDto container, boolean modified) {
			container.dateColumnWidth_m = modified;
		}

		@Override
		public boolean isModified(RECJxlsDto container) {
			return container.isDateColumnWidthModified();
		}
	};

	private boolean jxls1;
	private  boolean jxls1_m;
	public static final String PROPERTY_JXLS1 = "dpi-recjxls-jxls1";

	private transient static PropertyAccessor<RECJxlsDto, Boolean> jxls1_pa = new PropertyAccessor<RECJxlsDto, Boolean>() {
		@Override
		public void setValue(RECJxlsDto container, Boolean object) {
			container.setJxls1(object);
		}

		@Override
		public Boolean getValue(RECJxlsDto container) {
			return container.isJxls1();
		}

		@Override
		public Class<?> getType() {
			return Boolean.class;
		}

		@Override
		public String getPath() {
			return "jxls1";
		}

		@Override
		public void setModified(RECJxlsDto container, boolean modified) {
			container.jxls1_m = modified;
		}

		@Override
		public boolean isModified(RECJxlsDto container) {
			return container.isJxls1Modified();
		}
	};

	private boolean jxlsReport;
	private  boolean jxlsReport_m;
	public static final String PROPERTY_JXLS_REPORT = "dpi-recjxls-jxlsreport";

	private transient static PropertyAccessor<RECJxlsDto, Boolean> jxlsReport_pa = new PropertyAccessor<RECJxlsDto, Boolean>() {
		@Override
		public void setValue(RECJxlsDto container, Boolean object) {
			container.setJxlsReport(object);
		}

		@Override
		public Boolean getValue(RECJxlsDto container) {
			return container.isJxlsReport();
		}

		@Override
		public Class<?> getType() {
			return Boolean.class;
		}

		@Override
		public String getPath() {
			return "jxlsReport";
		}

		@Override
		public void setModified(RECJxlsDto container, boolean modified) {
			container.jxlsReport_m = modified;
		}

		@Override
		public boolean isModified(RECJxlsDto container) {
			return container.isJxlsReportModified();
		}
	};

	private int numberColumnWidth;
	private  boolean numberColumnWidth_m;
	public static final String PROPERTY_NUMBER_COLUMN_WIDTH = "dpi-recjxls-numbercolumnwidth";

	private transient static PropertyAccessor<RECJxlsDto, Integer> numberColumnWidth_pa = new PropertyAccessor<RECJxlsDto, Integer>() {
		@Override
		public void setValue(RECJxlsDto container, Integer object) {
			container.setNumberColumnWidth(object);
		}

		@Override
		public Integer getValue(RECJxlsDto container) {
			return container.getNumberColumnWidth();
		}

		@Override
		public Class<?> getType() {
			return Integer.class;
		}

		@Override
		public String getPath() {
			return "numberColumnWidth";
		}

		@Override
		public void setModified(RECJxlsDto container, boolean modified) {
			container.numberColumnWidth_m = modified;
		}

		@Override
		public boolean isModified(RECJxlsDto container) {
			return container.isNumberColumnWidthModified();
		}
	};

	private int textColumnWidth;
	private  boolean textColumnWidth_m;
	public static final String PROPERTY_TEXT_COLUMN_WIDTH = "dpi-recjxls-textcolumnwidth";

	private transient static PropertyAccessor<RECJxlsDto, Integer> textColumnWidth_pa = new PropertyAccessor<RECJxlsDto, Integer>() {
		@Override
		public void setValue(RECJxlsDto container, Integer object) {
			container.setTextColumnWidth(object);
		}

		@Override
		public Integer getValue(RECJxlsDto container) {
			return container.getTextColumnWidth();
		}

		@Override
		public Class<?> getType() {
			return Integer.class;
		}

		@Override
		public String getPath() {
			return "textColumnWidth";
		}

		@Override
		public void setModified(RECJxlsDto container, boolean modified) {
			container.textColumnWidth_m = modified;
		}

		@Override
		public boolean isModified(RECJxlsDto container) {
			return container.isTextColumnWidthModified();
		}
	};


	public RECJxlsDto() {
		super();
	}

	public int getCurrencyColumnWidth()  {
		if(! isDtoProxy()){
			return this.currencyColumnWidth;
		}

		if(isCurrencyColumnWidthModified())
			return this.currencyColumnWidth;

		if(! GWT.isClient())
			return 0;

		int _value = dtoManager.getProperty(this, instantiatePropertyAccess().currencyColumnWidth());

		return _value;
	}


	public void setCurrencyColumnWidth(int currencyColumnWidth)  {
		/* old value */
		int oldValue = 0;
		if(GWT.isClient())
			oldValue = getCurrencyColumnWidth();

		/* set new value */
		this.currencyColumnWidth = currencyColumnWidth;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(currencyColumnWidth_pa, oldValue, currencyColumnWidth, this.currencyColumnWidth_m));

		/* set indicator */
		this.currencyColumnWidth_m = true;

		this.fireObjectChangedEvent(RECJxlsDtoPA.INSTANCE.currencyColumnWidth(), oldValue);
	}


	public boolean isCurrencyColumnWidthModified()  {
		return currencyColumnWidth_m;
	}


	public static PropertyAccessor<RECJxlsDto, Integer> getCurrencyColumnWidthPropertyAccessor()  {
		return currencyColumnWidth_pa;
	}


	public int getDateColumnWidth()  {
		if(! isDtoProxy()){
			return this.dateColumnWidth;
		}

		if(isDateColumnWidthModified())
			return this.dateColumnWidth;

		if(! GWT.isClient())
			return 0;

		int _value = dtoManager.getProperty(this, instantiatePropertyAccess().dateColumnWidth());

		return _value;
	}


	public void setDateColumnWidth(int dateColumnWidth)  {
		/* old value */
		int oldValue = 0;
		if(GWT.isClient())
			oldValue = getDateColumnWidth();

		/* set new value */
		this.dateColumnWidth = dateColumnWidth;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(dateColumnWidth_pa, oldValue, dateColumnWidth, this.dateColumnWidth_m));

		/* set indicator */
		this.dateColumnWidth_m = true;

		this.fireObjectChangedEvent(RECJxlsDtoPA.INSTANCE.dateColumnWidth(), oldValue);
	}


	public boolean isDateColumnWidthModified()  {
		return dateColumnWidth_m;
	}


	public static PropertyAccessor<RECJxlsDto, Integer> getDateColumnWidthPropertyAccessor()  {
		return dateColumnWidth_pa;
	}


	public boolean isJxls1()  {
		if(! isDtoProxy()){
			return this.jxls1;
		}

		if(isJxls1Modified())
			return this.jxls1;

		if(! GWT.isClient())
			return false;

		boolean _value = dtoManager.getProperty(this, instantiatePropertyAccess().jxls1());

		return _value;
	}


	public void setJxls1(boolean jxls1)  {
		/* old value */
		boolean oldValue = false;
		if(GWT.isClient())
			oldValue = isJxls1();

		/* set new value */
		this.jxls1 = jxls1;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(jxls1_pa, oldValue, jxls1, this.jxls1_m));

		/* set indicator */
		this.jxls1_m = true;

		this.fireObjectChangedEvent(RECJxlsDtoPA.INSTANCE.jxls1(), oldValue);
	}


	public boolean isJxls1Modified()  {
		return jxls1_m;
	}


	public static PropertyAccessor<RECJxlsDto, Boolean> getJxls1PropertyAccessor()  {
		return jxls1_pa;
	}


	public boolean isJxlsReport()  {
		if(! isDtoProxy()){
			return this.jxlsReport;
		}

		if(isJxlsReportModified())
			return this.jxlsReport;

		if(! GWT.isClient())
			return false;

		boolean _value = dtoManager.getProperty(this, instantiatePropertyAccess().jxlsReport());

		return _value;
	}


	public void setJxlsReport(boolean jxlsReport)  {
		/* old value */
		boolean oldValue = false;
		if(GWT.isClient())
			oldValue = isJxlsReport();

		/* set new value */
		this.jxlsReport = jxlsReport;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(jxlsReport_pa, oldValue, jxlsReport, this.jxlsReport_m));

		/* set indicator */
		this.jxlsReport_m = true;

		this.fireObjectChangedEvent(RECJxlsDtoPA.INSTANCE.jxlsReport(), oldValue);
	}


	public boolean isJxlsReportModified()  {
		return jxlsReport_m;
	}


	public static PropertyAccessor<RECJxlsDto, Boolean> getJxlsReportPropertyAccessor()  {
		return jxlsReport_pa;
	}


	public int getNumberColumnWidth()  {
		if(! isDtoProxy()){
			return this.numberColumnWidth;
		}

		if(isNumberColumnWidthModified())
			return this.numberColumnWidth;

		if(! GWT.isClient())
			return 0;

		int _value = dtoManager.getProperty(this, instantiatePropertyAccess().numberColumnWidth());

		return _value;
	}


	public void setNumberColumnWidth(int numberColumnWidth)  {
		/* old value */
		int oldValue = 0;
		if(GWT.isClient())
			oldValue = getNumberColumnWidth();

		/* set new value */
		this.numberColumnWidth = numberColumnWidth;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(numberColumnWidth_pa, oldValue, numberColumnWidth, this.numberColumnWidth_m));

		/* set indicator */
		this.numberColumnWidth_m = true;

		this.fireObjectChangedEvent(RECJxlsDtoPA.INSTANCE.numberColumnWidth(), oldValue);
	}


	public boolean isNumberColumnWidthModified()  {
		return numberColumnWidth_m;
	}


	public static PropertyAccessor<RECJxlsDto, Integer> getNumberColumnWidthPropertyAccessor()  {
		return numberColumnWidth_pa;
	}


	public int getTextColumnWidth()  {
		if(! isDtoProxy()){
			return this.textColumnWidth;
		}

		if(isTextColumnWidthModified())
			return this.textColumnWidth;

		if(! GWT.isClient())
			return 0;

		int _value = dtoManager.getProperty(this, instantiatePropertyAccess().textColumnWidth());

		return _value;
	}


	public void setTextColumnWidth(int textColumnWidth)  {
		/* old value */
		int oldValue = 0;
		if(GWT.isClient())
			oldValue = getTextColumnWidth();

		/* set new value */
		this.textColumnWidth = textColumnWidth;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(textColumnWidth_pa, oldValue, textColumnWidth, this.textColumnWidth_m));

		/* set indicator */
		this.textColumnWidth_m = true;

		this.fireObjectChangedEvent(RECJxlsDtoPA.INSTANCE.textColumnWidth(), oldValue);
	}


	public boolean isTextColumnWidthModified()  {
		return textColumnWidth_m;
	}


	public static PropertyAccessor<RECJxlsDto, Integer> getTextColumnWidthPropertyAccessor()  {
		return textColumnWidth_pa;
	}


	@Override
	public String toString()  {
		return super.toString();
	}

	public static Dto2PosoMapper newPosoMapper()  {
		return new RECJxlsDto2PosoMap();
	}

	public RECJxlsDtoPA instantiatePropertyAccess()  {
		return GWT.create(RECJxlsDtoPA.class);
	}

	public void clearModified()  {
		this.currencyColumnWidth = 0;
		this.currencyColumnWidth_m = false;
		this.dateColumnWidth = 0;
		this.dateColumnWidth_m = false;
		this.jxls1 = false;
		this.jxls1_m = false;
		this.jxlsReport = false;
		this.jxlsReport_m = false;
		this.numberColumnWidth = 0;
		this.numberColumnWidth_m = false;
		this.textColumnWidth = 0;
		this.textColumnWidth_m = false;
	}


	public boolean isModified()  {
		if(super.isModified())
			return true;
		if(currencyColumnWidth_m)
			return true;
		if(dateColumnWidth_m)
			return true;
		if(jxls1_m)
			return true;
		if(jxlsReport_m)
			return true;
		if(numberColumnWidth_m)
			return true;
		if(textColumnWidth_m)
			return true;
		return false;
	}


	public List<PropertyAccessor> getPropertyAccessors()  {
		List<PropertyAccessor> list = super.getPropertyAccessors();
		list.add(currencyColumnWidth_pa);
		list.add(dateColumnWidth_pa);
		list.add(jxls1_pa);
		list.add(jxlsReport_pa);
		list.add(numberColumnWidth_pa);
		list.add(textColumnWidth_pa);
		return list;
	}


	public List<PropertyAccessor> getModifiedPropertyAccessors()  {
		List<PropertyAccessor> list = super.getModifiedPropertyAccessors();
		if(currencyColumnWidth_m)
			list.add(currencyColumnWidth_pa);
		if(dateColumnWidth_m)
			list.add(dateColumnWidth_pa);
		if(jxls1_m)
			list.add(jxls1_pa);
		if(jxlsReport_m)
			list.add(jxlsReport_pa);
		if(numberColumnWidth_m)
			list.add(numberColumnWidth_pa);
		if(textColumnWidth_m)
			list.add(textColumnWidth_pa);
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsByView(net.datenwerke.gxtdto.client.dtomanager.DtoView view)  {
		List<PropertyAccessor> list = super.getPropertyAccessorsByView(view);
		if(view.compareTo(DtoView.NORMAL) >= 0){
			list.add(currencyColumnWidth_pa);
			list.add(dateColumnWidth_pa);
			list.add(jxls1_pa);
			list.add(jxlsReport_pa);
			list.add(numberColumnWidth_pa);
			list.add(textColumnWidth_pa);
		}
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsForDtos()  {
		List<PropertyAccessor> list = super.getPropertyAccessorsForDtos();
		return list;
	}




}
