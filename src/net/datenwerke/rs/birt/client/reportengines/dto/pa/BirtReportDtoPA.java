package net.datenwerke.rs.birt.client.reportengines.dto.pa;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import net.datenwerke.dtoservices.dtogenerator.annotations.CorrespondingPoso;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.rs.birt.client.reportengines.dto.BirtReportDto;
import net.datenwerke.rs.birt.client.reportengines.dto.BirtReportFileDto;
import net.datenwerke.rs.birt.client.reportengines.dto.decorator.BirtReportDtoDec;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.pa.ReportDtoPA;

/**
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
@CorrespondingPoso(net.datenwerke.rs.birt.service.reportengine.entities.BirtReport.class)
public interface BirtReportDtoPA extends ReportDtoPA {


	public static final BirtReportDtoPA INSTANCE = GWT.create(BirtReportDtoPA.class);


	/* Properties */
	public ValueProvider<BirtReportDto,BirtReportFileDto> reportFile();


}
