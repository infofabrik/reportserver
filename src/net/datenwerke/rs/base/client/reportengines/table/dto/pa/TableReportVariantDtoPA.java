package net.datenwerke.rs.base.client.reportengines.table.dto.pa;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import net.datenwerke.dtoservices.dtogenerator.annotations.CorrespondingPoso;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.rs.base.client.reportengines.table.dto.TableReportVariantDto;
import net.datenwerke.rs.base.client.reportengines.table.dto.decorator.TableReportVariantDtoDec;
import net.datenwerke.rs.base.client.reportengines.table.dto.pa.TableReportDtoPA;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;

/**
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
@CorrespondingPoso(net.datenwerke.rs.base.service.reportengines.table.entities.TableReportVariant.class)
public interface TableReportVariantDtoPA extends TableReportDtoPA {


	public static final TableReportVariantDtoPA INSTANCE = GWT.create(TableReportVariantDtoPA.class);


	/* Properties */
	public ValueProvider<TableReportVariantDto,ReportDto> baseReport();


}
