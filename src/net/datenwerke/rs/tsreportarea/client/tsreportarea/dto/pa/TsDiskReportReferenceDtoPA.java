package net.datenwerke.rs.tsreportarea.client.tsreportarea.dto.pa;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import java.lang.Boolean;
import net.datenwerke.dtoservices.dtogenerator.annotations.CorrespondingPoso;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;
import net.datenwerke.rs.tsreportarea.client.tsreportarea.dto.TsDiskReportReferenceDto;
import net.datenwerke.rs.tsreportarea.client.tsreportarea.dto.decorator.TsDiskReportReferenceDtoDec;
import net.datenwerke.rs.tsreportarea.client.tsreportarea.dto.pa.TsDiskGeneralReferenceDtoPA;

/**
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
@CorrespondingPoso(net.datenwerke.rs.tsreportarea.service.tsreportarea.entities.TsDiskReportReference.class)
public interface TsDiskReportReferenceDtoPA extends TsDiskGeneralReferenceDtoPA {


	public static final TsDiskReportReferenceDtoPA INSTANCE = GWT.create(TsDiskReportReferenceDtoPA.class);


	/* Properties */
	public ValueProvider<TsDiskReportReferenceDto,Boolean> hardlink();
	public ValueProvider<TsDiskReportReferenceDto,ReportDto> report();


}
