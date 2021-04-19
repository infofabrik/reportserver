package net.datenwerke.rs.birt.client.reportengines.dto.pa;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import net.datenwerke.dtoservices.dtogenerator.annotations.CorrespondingPoso;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.rs.birt.client.reportengines.dto.BirtReportVariantDto;
import net.datenwerke.rs.birt.client.reportengines.dto.decorator.BirtReportVariantDtoDec;
import net.datenwerke.rs.birt.client.reportengines.dto.pa.BirtReportDtoPA;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;

/**
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
@CorrespondingPoso(net.datenwerke.rs.birt.service.reportengine.entities.BirtReportVariant.class)
public interface BirtReportVariantDtoPA extends BirtReportDtoPA {


	public static final BirtReportVariantDtoPA INSTANCE = GWT.create(BirtReportVariantDtoPA.class);


	/* Properties */
	public ValueProvider<BirtReportVariantDto,ReportDto> baseReport();


}
