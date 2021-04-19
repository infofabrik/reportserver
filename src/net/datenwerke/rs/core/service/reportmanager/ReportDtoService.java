package net.datenwerke.rs.core.service.reportmanager;

import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;
import net.datenwerke.rs.core.service.reportmanager.entities.reports.Report;

import com.google.inject.ImplementedBy;


/**
 * 
 *
 */
@ImplementedBy(ReportDtoServiceImpl.class)
public interface ReportDtoService {

	public Report getReferenceReport(ReportDto reportDto);

	public Report getReport(ReportDto reportDto);

	/**
	 * 
	 * @param realReport should be an unmanaged report
	 */
	ReportDto loadFullDtoForExecution(Report realReport);
}