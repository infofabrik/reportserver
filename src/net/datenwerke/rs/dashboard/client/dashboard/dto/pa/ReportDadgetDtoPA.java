package net.datenwerke.rs.dashboard.client.dashboard.dto.pa;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import java.lang.String;
import net.datenwerke.dtoservices.dtogenerator.annotations.CorrespondingPoso;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;
import net.datenwerke.rs.dashboard.client.dashboard.dto.ReportDadgetDto;
import net.datenwerke.rs.dashboard.client.dashboard.dto.decorator.ReportDadgetDtoDec;
import net.datenwerke.rs.dashboard.client.dashboard.dto.pa.DadgetDtoPA;
import net.datenwerke.rs.tsreportarea.client.tsreportarea.dto.TsDiskReportReferenceDto;

/**
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
@CorrespondingPoso(net.datenwerke.rs.dashboard.service.dashboard.dagets.ReportDadget.class)
public interface ReportDadgetDtoPA extends DadgetDtoPA {


	public static final ReportDadgetDtoPA INSTANCE = GWT.create(ReportDadgetDtoPA.class);


	/* Properties */
	public ValueProvider<ReportDadgetDto,String> config();
	public ValueProvider<ReportDadgetDto,ReportDto> report();
	public ValueProvider<ReportDadgetDto,TsDiskReportReferenceDto> reportReference();
	public ValueProvider<ReportDadgetDto,Boolean> showExecuteButton();
	public ValueProvider<ReportDadgetDto,ReportDto> reportForDisplay();


}
