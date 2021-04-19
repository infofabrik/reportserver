package net.datenwerke.rs.crystal.client.crystal.dto.pa;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import net.datenwerke.dtoservices.dtogenerator.annotations.CorrespondingPoso;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.pa.ReportDtoPA;
import net.datenwerke.rs.crystal.client.crystal.dto.CrystalReportDto;
import net.datenwerke.rs.crystal.client.crystal.dto.CrystalReportFileDto;
import net.datenwerke.rs.crystal.client.crystal.dto.decorator.CrystalReportDtoDec;

/**
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
@CorrespondingPoso(net.datenwerke.rs.crystal.service.crystal.entities.CrystalReport.class)
public interface CrystalReportDtoPA extends ReportDtoPA {


	public static final CrystalReportDtoPA INSTANCE = GWT.create(CrystalReportDtoPA.class);


	/* Properties */
	public ValueProvider<CrystalReportDto,CrystalReportFileDto> reportFile();


}
