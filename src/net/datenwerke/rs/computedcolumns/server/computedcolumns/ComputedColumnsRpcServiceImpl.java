package net.datenwerke.rs.computedcolumns.server.computedcolumns;

import net.datenwerke.gxtdto.client.servercommunication.exceptions.ExpectedException;
import net.datenwerke.gxtdto.client.servercommunication.exceptions.ServerCallFailedException;
import net.datenwerke.gxtdto.server.dtomanager.DtoService;
import net.datenwerke.rs.base.client.reportengines.table.dto.TableReportDto;
import net.datenwerke.rs.base.service.reportengines.table.SimpleDataSupplier;
import net.datenwerke.rs.base.service.reportengines.table.entities.ColumnReference;
import net.datenwerke.rs.base.service.reportengines.table.entities.TableReport;
import net.datenwerke.rs.base.service.reportengines.table.output.object.TableDefinition;
import net.datenwerke.rs.computedcolumns.client.computedcolumns.dto.ComputedColumnDto;
import net.datenwerke.rs.computedcolumns.client.computedcolumns.rpc.ComputedColumnsRpcService;
import net.datenwerke.rs.computedcolumns.service.computedcolumns.entities.ComputedColumn;
import net.datenwerke.rs.core.service.reportmanager.ReportDtoService;
import net.datenwerke.rs.core.service.reportmanager.entities.reports.Report;
import net.datenwerke.security.server.SecuredRemoteServiceServlet;
import net.datenwerke.security.service.security.SecurityService;
import net.datenwerke.security.service.security.exceptions.ViolatedSecurityException;
import net.datenwerke.security.service.security.rights.Execute;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.inject.name.Named;
import com.google.inject.persist.Transactional;

@Singleton
public class ComputedColumnsRpcServiceImpl extends SecuredRemoteServiceServlet
		implements ComputedColumnsRpcService {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3404036079518006885L;

	private final DtoService dtoService;
	private final ReportDtoService reportDtoService;
	private final SimpleDataSupplier dataSupplyer;

	private final SecurityService securityService;
	
	@Inject
	public ComputedColumnsRpcServiceImpl(
			DtoService dtoService,
			ReportDtoService reportDtoService,
			SecurityService securityService,
			SimpleDataSupplier dataSupplyer
		) {

		/* store objects */
		this.dtoService = dtoService;
		this.reportDtoService = reportDtoService;
		this.securityService = securityService;
		this.dataSupplyer = dataSupplyer;
	}


	@Override
	@Transactional(rollbackOn={Exception.class})
	public Integer getColumnType(@Named("node")TableReportDto reportDto, ComputedColumnDto oldColumn, ComputedColumnDto newColumn) throws ServerCallFailedException {
		return doGetColumnType(reportDto, oldColumn, newColumn);
	}
	
	@Override
	@Transactional(rollbackOn={Exception.class})
	public Integer getColumnType(@Named("node")TableReportDto reportDto, ComputedColumnDto column) throws ServerCallFailedException {
		return doGetColumnType(reportDto, null, column);
	}
	
	private Integer doGetColumnType(@Named("node")TableReportDto reportDto, ComputedColumnDto oldColumn, ComputedColumnDto newColumn) throws ServerCallFailedException {
		/* get reference report */
		TableReport referenceReport = (TableReport) reportDtoService.getReferenceReport(reportDto);
		
		/* check rights */
		securityService.assertRights(referenceReport, Execute.class);
		
		/* create temp variant (without column) */
		if (null != oldColumn)
			reportDto.getAdditionalColumns().remove(oldColumn);
		reportDto.getColumns().clear();
		Report adjustedReport = (Report) dtoService.createUnmanagedPoso(reportDto);
		referenceReport = (TableReport) referenceReport.createTemporaryVariant(adjustedReport);
		
		ComputedColumn computedColumn = (ComputedColumn) dtoService.createPoso(newColumn);
		ColumnReference reference = new ColumnReference();
		reference.setReference(computedColumn);
		
		try {
			TableDefinition definition = dataSupplyer.getInfo(referenceReport, reference);
			return definition.getSqlColumnTypes().get(definition.size() - 1);
		} catch (Exception e) {
			throw new ExpectedException(e);
		}
	}

}
