package net.datenwerke.rs.base.service.reportengines.table.hookers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.google.inject.Inject;

import net.datenwerke.gxtdto.client.dtomanager.Dto;
import net.datenwerke.gxtdto.client.model.DwModel;
import net.datenwerke.gxtdto.client.servercommunication.exceptions.ExpectedException;
import net.datenwerke.gxtdto.server.dtomanager.DtoService;
import net.datenwerke.rs.base.client.reportengines.table.dto.TableReportDto;
import net.datenwerke.rs.base.client.reportengines.table.ui.model.TableReportPreviewConfig;
import net.datenwerke.rs.base.service.reportengines.locale.ReportEnginesMessages;
import net.datenwerke.rs.base.service.reportengines.table.entities.Column;
import net.datenwerke.rs.base.service.reportengines.table.entities.Column.CellFormatter;
import net.datenwerke.rs.base.service.reportengines.table.entities.TableReport;
import net.datenwerke.rs.base.service.reportengines.table.output.object.RSTableModel;
import net.datenwerke.rs.base.service.reportengines.table.output.object.RSTableRow;
import net.datenwerke.rs.core.client.reportexecutor.dto.PreviewModel;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;
import net.datenwerke.rs.core.service.reportmanager.ReportExecutorService;
import net.datenwerke.rs.core.service.reportmanager.engine.config.RECSinglePage;
import net.datenwerke.rs.core.service.reportmanager.engine.config.ReportExecutionConfig;
import net.datenwerke.rs.core.service.reportmanager.hooks.ReportExecutorExecuteAsHooker;
import net.datenwerke.security.service.usermanager.entities.User;

public class ExecuteAsTableReportPreviewHooker implements ReportExecutorExecuteAsHooker{

	private static final int PREVIEW_MAX_SIZE = 4000;
	
	private final DtoService dtoService;
	
	@Inject
	public ExecuteAsTableReportPreviewHooker(DtoService dtoService) {
		this.dtoService = dtoService;
	}

	@Override
	public boolean consumes(ReportDto report, String format) {
		return report instanceof TableReportDto && "TABLE_REPORT_PREVIEW".equals(format);
	}

	@Override
	public ExecuteConfig getConfig(final ReportDto report, User currentUser, String format, final DwModel configModel) {
		final TableReportPreviewConfig config = (TableReportPreviewConfig) configModel;
		return new ExecuteConfig() {
			
			private List<ReportExecutionConfig> cfg = new ArrayList<ReportExecutionConfig>();
			private boolean configured = false;
	
			@Override
			public String getFormat() {
				return ReportExecutorService.OUTPUT_FORMAT_TABLE;
			}
			
			@Override
			public Collection<ReportExecutionConfig> getConfig() {
				if(((TableReportDto)report).isEnableSubtotals() || configured)
					return cfg;
				
				configured = true;
				
				final int pageNumber = Math.max(1, config.getPagenumber());
				final int pageSize = Math.max(1, config.getPagesize());
				
				cfg.add(new RECSinglePage(Math.max(1, pageNumber), Math.min(1000, pageSize)));
				return cfg;
			}
		};
	}

	@Override
	public Dto adjustResult(ReportDto reportDto, User currentUser, Collection<ReportExecutionConfig> config, Object result) {
		if(! (result instanceof RSTableModel))
			throw new IllegalStateException("Expected RsTableModel");
		
		RSTableModel table = (RSTableModel) result;
		
		int size = table.getColumnCount();
		PreviewModel model = new PreviewModel();
		model.setSize(size);
		model.setColumnNames(table.getTableDefinition().getColumnNames());

		CellFormatter[] formatter = new CellFormatter[size];
		TableReport report = null;
		try {
			report = (TableReport) dtoService.createUnmanagedPoso(reportDto);
		} catch (ExpectedException e1) {
			throw new RuntimeException(e1);
		}
		
		for(int i = 0; i < size; i++){
			final Column col = report.getVisibleColumnByPos(i);
			formatter[i] = null != col ? col.getCellFormatter(currentUser) : Column.DUMMY_FORMATTER;
		}
		
		if(report.isEnableSubtotals()){
			int rowsInGroup = 0;
			int groups = 0;
			int[] aggIndices = report.getAggregateColumnIndices();
			int[] groupIndices = report.getSubtotalGroupColumnIndices();
			
			RSTableRow lastRow = null;
			for(RSTableRow row: table.getData()){
				if(row.isSubtotalRow()){
					/* details */
					String[] sRow = new String[size];
					sRow[0] = ReportEnginesMessages.INSTANCE.detailRowsInGroup(rowsInGroup);
					for(int i = 1; i < size; i++)
						sRow[i] = "...";
					model.addRow(sRow);
					
					/* subtotals */
					sRow = new String[size];
					int aIndex = 0;
					int gIndex = 0;
					for(int i = 0; i < size; i++){
						if(aIndex < aggIndices.length && aggIndices[aIndex] == i){
							sRow[i] = StringUtils.left(formatter[i].format(row.getAt(i)), PREVIEW_MAX_SIZE);
							aIndex++;
						} else if(gIndex < groupIndices.length && groupIndices[gIndex] == i){
							sRow[i] = StringUtils.left(formatter[i].format(lastRow.getAt(i)), PREVIEW_MAX_SIZE);
							gIndex++;
						} else 
							sRow[i] = "";
							
					}
					model.addRow(sRow);
					
					rowsInGroup = 0;
					
					if(groups > 50){
						sRow = new String[size];
						for(int i = 0; i < size; i++)
							sRow[i] = "...";
						model.addRow(sRow);
						break;
					}
					groups++;
				} else 
					rowsInGroup++;
				
				lastRow = row;
			}
		} else {
			for(RSTableRow row: table.getData()){
				String[] sRow = new String[size];
				String[] dRow = new String[size];
				
				for(int i = 0; i < size; i++){
					Object value = row.getAt(i);
					sRow[i] = StringUtils.left(formatter[i].format(value), PREVIEW_MAX_SIZE);
					dRow[i] = StringUtils.left(null == value ? null : String.valueOf(value), PREVIEW_MAX_SIZE);
				}
				
				model.addRow(sRow);
				model.addRawRow(dRow);
			}
		}
		
		return model;
	}
	
	@Override
	public ReportDto adjustReport(ReportDto report, User currentUser,  String format, Collection<ReportExecutionConfig> config) {
		return report;
	}

}
