package net.datenwerke.rs.base.client.reportengines.table.dto;

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
import net.datenwerke.rs.base.client.reportengines.table.dto.pa.CompiledHTMLTableReportDtoPA;
import net.datenwerke.rs.base.client.reportengines.table.dto.posomap.CompiledHTMLTableReportDto2PosoMap;
import net.datenwerke.rs.base.service.reportengines.table.output.object.CompiledHTMLTableReport;
import net.datenwerke.rs.core.client.reportexecutor.dto.CompiledHtmlReportDto;

/**
 * Dto for {@link CompiledHTMLTableReport}
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class CompiledHTMLTableReportDto extends RsDto implements CompiledHtmlReportDto {


	private static final long serialVersionUID = 1;


	/* Fields */
	private String report;
	private  boolean report_m;
	public static final String PROPERTY_REPORT = "dpi-compiledhtmltablereport-report";

	private transient static PropertyAccessor<CompiledHTMLTableReportDto, String> report_pa = new PropertyAccessor<CompiledHTMLTableReportDto, String>() {
		@Override
		public void setValue(CompiledHTMLTableReportDto container, String object) {
			container.setReport(object);
		}

		@Override
		public String getValue(CompiledHTMLTableReportDto container) {
			return container.getReport();
		}

		@Override
		public Class<?> getType() {
			return String.class;
		}

		@Override
		public String getPath() {
			return "report";
		}

		@Override
		public void setModified(CompiledHTMLTableReportDto container, boolean modified) {
			container.report_m = modified;
		}

		@Override
		public boolean isModified(CompiledHTMLTableReportDto container) {
			return container.isReportModified();
		}
	};


	public CompiledHTMLTableReportDto() {
		super();
	}

	public String getReport()  {
		if(! isDtoProxy()){
			return this.report;
		}

		if(isReportModified())
			return this.report;

		if(! GWT.isClient())
			return null;

		String _value = dtoManager.getProperty(this, instantiatePropertyAccess().report());

		return _value;
	}


	public void setReport(String report)  {
		/* old value */
		String oldValue = null;
		if(GWT.isClient())
			oldValue = getReport();

		/* set new value */
		this.report = report;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(report_pa, oldValue, report, this.report_m));

		/* set indicator */
		this.report_m = true;

		this.fireObjectChangedEvent(CompiledHTMLTableReportDtoPA.INSTANCE.report(), oldValue);
	}


	public boolean isReportModified()  {
		return report_m;
	}


	public static PropertyAccessor<CompiledHTMLTableReportDto, String> getReportPropertyAccessor()  {
		return report_pa;
	}


	@Override
	public String toString()  {
		return super.toString();
	}

	public static Dto2PosoMapper newPosoMapper()  {
		return new CompiledHTMLTableReportDto2PosoMap();
	}

	public CompiledHTMLTableReportDtoPA instantiatePropertyAccess()  {
		return GWT.create(CompiledHTMLTableReportDtoPA.class);
	}

	public void clearModified()  {
		this.report = null;
		this.report_m = false;
	}


	public boolean isModified()  {
		if(super.isModified())
			return true;
		if(report_m)
			return true;
		return false;
	}


	public List<PropertyAccessor> getPropertyAccessors()  {
		List<PropertyAccessor> list = super.getPropertyAccessors();
		list.add(report_pa);
		return list;
	}


	public List<PropertyAccessor> getModifiedPropertyAccessors()  {
		List<PropertyAccessor> list = super.getModifiedPropertyAccessors();
		if(report_m)
			list.add(report_pa);
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsByView(net.datenwerke.gxtdto.client.dtomanager.DtoView view)  {
		List<PropertyAccessor> list = super.getPropertyAccessorsByView(view);
		if(view.compareTo(DtoView.NORMAL) >= 0){
			list.add(report_pa);
		}
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsForDtos()  {
		List<PropertyAccessor> list = super.getPropertyAccessorsForDtos();
		return list;
	}




}
