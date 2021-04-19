package net.datenwerke.rs.core.client.reportexecutor.rpc;


import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import net.datenwerke.gf.client.history.HistoryLocation;
import net.datenwerke.gxtdto.client.model.DwModel;
import net.datenwerke.gxtdto.client.servercommunication.exceptions.ServerCallFailedException;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;

@RemoteServiceRelativePath("executor")
public interface ReportExecutorRpcService extends RemoteService {

	public DwModel executeAs(String format, String executeToken, ReportDto report, DwModel config) throws ServerCallFailedException;
	
	public DwModel storePNGInSession(String executorToken, ReportDto report) throws ServerCallFailedException;
	
	public ReportDto createNewVariant(ReportDto reportVariantDto, String executeToken, String title, String description) throws ServerCallFailedException;
	
	public ReportDto editVariant(ReportDto reportVariantDto, String executeToken, String title, String description) throws ServerCallFailedException;
	
	public void deleteVariant(ReportDto reportVariantDto) throws ServerCallFailedException;

	public ReportDto loadFullReportForExecution(ReportDto report) throws ServerCallFailedException;
	
	public ReportDto loadReportForExecutionFrom(HistoryLocation location) throws ServerCallFailedException;

	public String getPreviewMode() throws ServerCallFailedException;

	public void setPreviewModeUserProperty(String value) throws ServerCallFailedException;

	public ReportDto loadFullReportUnmanaged(ReportDto report);
	
	public Integer getDefaultColumnWidth() throws ServerCallFailedException;
	
	public Integer getMaxColumnWidth() throws ServerCallFailedException;

	
}
