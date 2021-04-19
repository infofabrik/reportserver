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
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;
import net.datenwerke.rs.scripting.client.scripting.dto.pa.AddReportExportFormatProviderDtoPA;
import net.datenwerke.rs.scripting.client.scripting.dto.posomap.AddReportExportFormatProviderDto2PosoMap;
import net.datenwerke.rs.scripting.service.scripting.extensions.AddReportExportFormatProvider;
import net.datenwerke.rs.terminal.client.terminal.dto.CommandResultExtensionDto;

/**
 * Dto for {@link AddReportExportFormatProvider}
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class AddReportExportFormatProviderDto extends CommandResultExtensionDto {


	private static final long serialVersionUID = 1;


	/* Fields */
	private String description;
	private  boolean description_m;
	public static final String PROPERTY_DESCRIPTION = "dpi-addreportexportformatprovider-description";

	private transient static PropertyAccessor<AddReportExportFormatProviderDto, String> description_pa = new PropertyAccessor<AddReportExportFormatProviderDto, String>() {
		@Override
		public void setValue(AddReportExportFormatProviderDto container, String object) {
			container.setDescription(object);
		}

		@Override
		public String getValue(AddReportExportFormatProviderDto container) {
			return container.getDescription();
		}

		@Override
		public Class<?> getType() {
			return String.class;
		}

		@Override
		public String getPath() {
			return "description";
		}

		@Override
		public void setModified(AddReportExportFormatProviderDto container, boolean modified) {
			container.description_m = modified;
		}

		@Override
		public boolean isModified(AddReportExportFormatProviderDto container) {
			return container.isDescriptionModified();
		}
	};

	private String icon;
	private  boolean icon_m;
	public static final String PROPERTY_ICON = "dpi-addreportexportformatprovider-icon";

	private transient static PropertyAccessor<AddReportExportFormatProviderDto, String> icon_pa = new PropertyAccessor<AddReportExportFormatProviderDto, String>() {
		@Override
		public void setValue(AddReportExportFormatProviderDto container, String object) {
			container.setIcon(object);
		}

		@Override
		public String getValue(AddReportExportFormatProviderDto container) {
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
		public void setModified(AddReportExportFormatProviderDto container, boolean modified) {
			container.icon_m = modified;
		}

		@Override
		public boolean isModified(AddReportExportFormatProviderDto container) {
			return container.isIconModified();
		}
	};

	private String outputFormat;
	private  boolean outputFormat_m;
	public static final String PROPERTY_OUTPUT_FORMAT = "dpi-addreportexportformatprovider-outputformat";

	private transient static PropertyAccessor<AddReportExportFormatProviderDto, String> outputFormat_pa = new PropertyAccessor<AddReportExportFormatProviderDto, String>() {
		@Override
		public void setValue(AddReportExportFormatProviderDto container, String object) {
			container.setOutputFormat(object);
		}

		@Override
		public String getValue(AddReportExportFormatProviderDto container) {
			return container.getOutputFormat();
		}

		@Override
		public Class<?> getType() {
			return String.class;
		}

		@Override
		public String getPath() {
			return "outputFormat";
		}

		@Override
		public void setModified(AddReportExportFormatProviderDto container, boolean modified) {
			container.outputFormat_m = modified;
		}

		@Override
		public boolean isModified(AddReportExportFormatProviderDto container) {
			return container.isOutputFormatModified();
		}
	};

	private ReportDto reportType;
	private  boolean reportType_m;
	public static final String PROPERTY_REPORT_TYPE = "dpi-addreportexportformatprovider-reporttype";

	private transient static PropertyAccessor<AddReportExportFormatProviderDto, ReportDto> reportType_pa = new PropertyAccessor<AddReportExportFormatProviderDto, ReportDto>() {
		@Override
		public void setValue(AddReportExportFormatProviderDto container, ReportDto object) {
			container.setReportType(object);
		}

		@Override
		public ReportDto getValue(AddReportExportFormatProviderDto container) {
			return container.getReportType();
		}

		@Override
		public Class<?> getType() {
			return ReportDto.class;
		}

		@Override
		public String getPath() {
			return "reportType";
		}

		@Override
		public void setModified(AddReportExportFormatProviderDto container, boolean modified) {
			container.reportType_m = modified;
		}

		@Override
		public boolean isModified(AddReportExportFormatProviderDto container) {
			return container.isReportTypeModified();
		}
	};

	private boolean skipDownload;
	private  boolean skipDownload_m;
	public static final String PROPERTY_SKIP_DOWNLOAD = "dpi-addreportexportformatprovider-skipdownload";

	private transient static PropertyAccessor<AddReportExportFormatProviderDto, Boolean> skipDownload_pa = new PropertyAccessor<AddReportExportFormatProviderDto, Boolean>() {
		@Override
		public void setValue(AddReportExportFormatProviderDto container, Boolean object) {
			container.setSkipDownload(object);
		}

		@Override
		public Boolean getValue(AddReportExportFormatProviderDto container) {
			return container.isSkipDownload();
		}

		@Override
		public Class<?> getType() {
			return Boolean.class;
		}

		@Override
		public String getPath() {
			return "skipDownload";
		}

		@Override
		public void setModified(AddReportExportFormatProviderDto container, boolean modified) {
			container.skipDownload_m = modified;
		}

		@Override
		public boolean isModified(AddReportExportFormatProviderDto container) {
			return container.isSkipDownloadModified();
		}
	};

	private String title;
	private  boolean title_m;
	public static final String PROPERTY_TITLE = "dpi-addreportexportformatprovider-title";

	private transient static PropertyAccessor<AddReportExportFormatProviderDto, String> title_pa = new PropertyAccessor<AddReportExportFormatProviderDto, String>() {
		@Override
		public void setValue(AddReportExportFormatProviderDto container, String object) {
			container.setTitle(object);
		}

		@Override
		public String getValue(AddReportExportFormatProviderDto container) {
			return container.getTitle();
		}

		@Override
		public Class<?> getType() {
			return String.class;
		}

		@Override
		public String getPath() {
			return "title";
		}

		@Override
		public void setModified(AddReportExportFormatProviderDto container, boolean modified) {
			container.title_m = modified;
		}

		@Override
		public boolean isModified(AddReportExportFormatProviderDto container) {
			return container.isTitleModified();
		}
	};


	public AddReportExportFormatProviderDto() {
		super();
	}

	public String getDescription()  {
		if(! isDtoProxy()){
			return this.description;
		}

		if(isDescriptionModified())
			return this.description;

		if(! GWT.isClient())
			return null;

		String _value = dtoManager.getProperty(this, instantiatePropertyAccess().description());

		return _value;
	}


	public void setDescription(String description)  {
		/* old value */
		String oldValue = null;
		if(GWT.isClient())
			oldValue = getDescription();

		/* set new value */
		this.description = description;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(description_pa, oldValue, description, this.description_m));

		/* set indicator */
		this.description_m = true;

		this.fireObjectChangedEvent(AddReportExportFormatProviderDtoPA.INSTANCE.description(), oldValue);
	}


	public boolean isDescriptionModified()  {
		return description_m;
	}


	public static PropertyAccessor<AddReportExportFormatProviderDto, String> getDescriptionPropertyAccessor()  {
		return description_pa;
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

		this.fireObjectChangedEvent(AddReportExportFormatProviderDtoPA.INSTANCE.icon(), oldValue);
	}


	public boolean isIconModified()  {
		return icon_m;
	}


	public static PropertyAccessor<AddReportExportFormatProviderDto, String> getIconPropertyAccessor()  {
		return icon_pa;
	}


	public String getOutputFormat()  {
		if(! isDtoProxy()){
			return this.outputFormat;
		}

		if(isOutputFormatModified())
			return this.outputFormat;

		if(! GWT.isClient())
			return null;

		String _value = dtoManager.getProperty(this, instantiatePropertyAccess().outputFormat());

		return _value;
	}


	public void setOutputFormat(String outputFormat)  {
		/* old value */
		String oldValue = null;
		if(GWT.isClient())
			oldValue = getOutputFormat();

		/* set new value */
		this.outputFormat = outputFormat;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(outputFormat_pa, oldValue, outputFormat, this.outputFormat_m));

		/* set indicator */
		this.outputFormat_m = true;

		this.fireObjectChangedEvent(AddReportExportFormatProviderDtoPA.INSTANCE.outputFormat(), oldValue);
	}


	public boolean isOutputFormatModified()  {
		return outputFormat_m;
	}


	public static PropertyAccessor<AddReportExportFormatProviderDto, String> getOutputFormatPropertyAccessor()  {
		return outputFormat_pa;
	}


	public ReportDto getReportType()  {
		if(! isDtoProxy()){
			return this.reportType;
		}

		if(isReportTypeModified())
			return this.reportType;

		if(! GWT.isClient())
			return null;

		ReportDto _value = dtoManager.getProperty(this, instantiatePropertyAccess().reportType());

		return _value;
	}


	public void setReportType(ReportDto reportType)  {
		/* old value */
		ReportDto oldValue = null;
		if(GWT.isClient())
			oldValue = getReportType();

		/* set new value */
		this.reportType = reportType;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(reportType_pa, oldValue, reportType, this.reportType_m));

		/* set indicator */
		this.reportType_m = true;

		this.fireObjectChangedEvent(AddReportExportFormatProviderDtoPA.INSTANCE.reportType(), oldValue);
	}


	public boolean isReportTypeModified()  {
		return reportType_m;
	}


	public static PropertyAccessor<AddReportExportFormatProviderDto, ReportDto> getReportTypePropertyAccessor()  {
		return reportType_pa;
	}


	public boolean isSkipDownload()  {
		if(! isDtoProxy()){
			return this.skipDownload;
		}

		if(isSkipDownloadModified())
			return this.skipDownload;

		if(! GWT.isClient())
			return false;

		boolean _value = dtoManager.getProperty(this, instantiatePropertyAccess().skipDownload());

		return _value;
	}


	public void setSkipDownload(boolean skipDownload)  {
		/* old value */
		boolean oldValue = false;
		if(GWT.isClient())
			oldValue = isSkipDownload();

		/* set new value */
		this.skipDownload = skipDownload;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(skipDownload_pa, oldValue, skipDownload, this.skipDownload_m));

		/* set indicator */
		this.skipDownload_m = true;

		this.fireObjectChangedEvent(AddReportExportFormatProviderDtoPA.INSTANCE.skipDownload(), oldValue);
	}


	public boolean isSkipDownloadModified()  {
		return skipDownload_m;
	}


	public static PropertyAccessor<AddReportExportFormatProviderDto, Boolean> getSkipDownloadPropertyAccessor()  {
		return skipDownload_pa;
	}


	public String getTitle()  {
		if(! isDtoProxy()){
			return this.title;
		}

		if(isTitleModified())
			return this.title;

		if(! GWT.isClient())
			return null;

		String _value = dtoManager.getProperty(this, instantiatePropertyAccess().title());

		return _value;
	}


	public void setTitle(String title)  {
		/* old value */
		String oldValue = null;
		if(GWT.isClient())
			oldValue = getTitle();

		/* set new value */
		this.title = title;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(title_pa, oldValue, title, this.title_m));

		/* set indicator */
		this.title_m = true;

		this.fireObjectChangedEvent(AddReportExportFormatProviderDtoPA.INSTANCE.title(), oldValue);
	}


	public boolean isTitleModified()  {
		return title_m;
	}


	public static PropertyAccessor<AddReportExportFormatProviderDto, String> getTitlePropertyAccessor()  {
		return title_pa;
	}


	@Override
	public String toString()  {
		return super.toString();
	}

	public static Dto2PosoMapper newPosoMapper()  {
		return new AddReportExportFormatProviderDto2PosoMap();
	}

	public AddReportExportFormatProviderDtoPA instantiatePropertyAccess()  {
		return GWT.create(AddReportExportFormatProviderDtoPA.class);
	}

	public void clearModified()  {
		this.description = null;
		this.description_m = false;
		this.icon = null;
		this.icon_m = false;
		this.outputFormat = null;
		this.outputFormat_m = false;
		this.reportType = null;
		this.reportType_m = false;
		this.skipDownload = false;
		this.skipDownload_m = false;
		this.title = null;
		this.title_m = false;
	}


	public boolean isModified()  {
		if(super.isModified())
			return true;
		if(description_m)
			return true;
		if(icon_m)
			return true;
		if(outputFormat_m)
			return true;
		if(reportType_m)
			return true;
		if(skipDownload_m)
			return true;
		if(title_m)
			return true;
		return false;
	}


	public List<PropertyAccessor> getPropertyAccessors()  {
		List<PropertyAccessor> list = super.getPropertyAccessors();
		list.add(description_pa);
		list.add(icon_pa);
		list.add(outputFormat_pa);
		list.add(reportType_pa);
		list.add(skipDownload_pa);
		list.add(title_pa);
		return list;
	}


	public List<PropertyAccessor> getModifiedPropertyAccessors()  {
		List<PropertyAccessor> list = super.getModifiedPropertyAccessors();
		if(description_m)
			list.add(description_pa);
		if(icon_m)
			list.add(icon_pa);
		if(outputFormat_m)
			list.add(outputFormat_pa);
		if(reportType_m)
			list.add(reportType_pa);
		if(skipDownload_m)
			list.add(skipDownload_pa);
		if(title_m)
			list.add(title_pa);
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsByView(net.datenwerke.gxtdto.client.dtomanager.DtoView view)  {
		List<PropertyAccessor> list = super.getPropertyAccessorsByView(view);
		if(view.compareTo(DtoView.NORMAL) >= 0){
			list.add(description_pa);
			list.add(icon_pa);
			list.add(outputFormat_pa);
			list.add(reportType_pa);
			list.add(skipDownload_pa);
			list.add(title_pa);
		}
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsForDtos()  {
		List<PropertyAccessor> list = super.getPropertyAccessorsForDtos();
		return list;
	}




}
