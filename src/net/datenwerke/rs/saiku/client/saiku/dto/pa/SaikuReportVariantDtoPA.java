package net.datenwerke.rs.saiku.client.saiku.dto.pa;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import net.datenwerke.dtoservices.dtogenerator.annotations.CorrespondingPoso;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;
import net.datenwerke.rs.saiku.client.saiku.dto.SaikuReportVariantDto;
import net.datenwerke.rs.saiku.client.saiku.dto.decorator.SaikuReportVariantDtoDec;
import net.datenwerke.rs.saiku.client.saiku.dto.pa.SaikuReportDtoPA;

/**
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
@CorrespondingPoso(net.datenwerke.rs.saiku.service.saiku.entities.SaikuReportVariant.class)
public interface SaikuReportVariantDtoPA extends SaikuReportDtoPA {


	public static final SaikuReportVariantDtoPA INSTANCE = GWT.create(SaikuReportVariantDtoPA.class);


	/* Properties */
	public ValueProvider<SaikuReportVariantDto,ReportDto> baseReport();


}
