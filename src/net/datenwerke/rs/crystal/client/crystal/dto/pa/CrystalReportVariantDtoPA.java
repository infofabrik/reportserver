package net.datenwerke.rs.crystal.client.crystal.dto.pa;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import net.datenwerke.dtoservices.dtogenerator.annotations.CorrespondingPoso;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;
import net.datenwerke.rs.crystal.client.crystal.dto.CrystalReportVariantDto;
import net.datenwerke.rs.crystal.client.crystal.dto.decorator.CrystalReportVariantDtoDec;
import net.datenwerke.rs.crystal.client.crystal.dto.pa.CrystalReportDtoPA;

/**
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
@CorrespondingPoso(net.datenwerke.rs.crystal.service.crystal.entities.CrystalReportVariant.class)
public interface CrystalReportVariantDtoPA extends CrystalReportDtoPA {


	public static final CrystalReportVariantDtoPA INSTANCE = GWT.create(CrystalReportVariantDtoPA.class);


	/* Properties */
	public ValueProvider<CrystalReportVariantDto,ReportDto> baseReport();


}
