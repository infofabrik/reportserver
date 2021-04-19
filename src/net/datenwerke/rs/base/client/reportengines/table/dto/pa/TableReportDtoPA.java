package net.datenwerke.rs.base.client.reportengines.table.dto.pa;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import java.lang.Boolean;
import java.lang.String;
import java.util.List;
import net.datenwerke.dtoservices.dtogenerator.annotations.CorrespondingPoso;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.rs.base.client.reportengines.table.dto.AdditionalColumnSpecDto;
import net.datenwerke.rs.base.client.reportengines.table.dto.ColumnDto;
import net.datenwerke.rs.base.client.reportengines.table.dto.PreFilterDto;
import net.datenwerke.rs.base.client.reportengines.table.dto.TableReportDto;
import net.datenwerke.rs.base.client.reportengines.table.dto.decorator.TableReportDtoDec;
import net.datenwerke.rs.core.client.datasourcemanager.dto.DatasourceContainerDto;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.pa.ReportDtoPA;

/**
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
@CorrespondingPoso(net.datenwerke.rs.base.service.reportengines.table.entities.TableReport.class)
public interface TableReportDtoPA extends ReportDtoPA {


	public static final TableReportDtoPA INSTANCE = GWT.create(TableReportDtoPA.class);


	/* Properties */
	public ValueProvider<TableReportDto,List<AdditionalColumnSpecDto>> additionalColumns();
	public ValueProvider<TableReportDto,Boolean> allowCubification();
	public ValueProvider<TableReportDto,Boolean> allowMdx();
	public ValueProvider<TableReportDto,List<ColumnDto>> columns();
	public ValueProvider<TableReportDto,Boolean> cubeFlag();
	public ValueProvider<TableReportDto,String> cubeXml();
	public ValueProvider<TableReportDto,Boolean> distinctFlag();
	public ValueProvider<TableReportDto,Boolean> enableSubtotals();
	public ValueProvider<TableReportDto,Boolean> hideParents();
	public ValueProvider<TableReportDto,DatasourceContainerDto> metadataDatasourceContainer();
	public ValueProvider<TableReportDto,PreFilterDto> preFilter();


}
