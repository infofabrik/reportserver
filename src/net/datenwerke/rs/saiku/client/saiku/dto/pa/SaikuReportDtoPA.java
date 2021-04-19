package net.datenwerke.rs.saiku.client.saiku.dto.pa;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import java.lang.String;
import net.datenwerke.dtoservices.dtogenerator.annotations.CorrespondingPoso;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.pa.ReportDtoPA;
import net.datenwerke.rs.saiku.client.saiku.dto.SaikuReportDto;
import net.datenwerke.rs.saiku.client.saiku.dto.decorator.SaikuReportDtoDec;

/**
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
@CorrespondingPoso(net.datenwerke.rs.saiku.service.saiku.entities.SaikuReport.class)
public interface SaikuReportDtoPA extends ReportDtoPA {


	public static final SaikuReportDtoPA INSTANCE = GWT.create(SaikuReportDtoPA.class);


	/* Properties */
	public ValueProvider<SaikuReportDto,Boolean> allowMdx();
	public ValueProvider<SaikuReportDto,Boolean> createdFromPivotReport();
	public ValueProvider<SaikuReportDto,Boolean> hideParents();
	public ValueProvider<SaikuReportDto,Long> originalPivotReportId();
	public ValueProvider<SaikuReportDto,String> queryXml();


}
