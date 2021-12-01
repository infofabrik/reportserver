package net.datenwerke.rs.core.service.reportmanager;

import org.hibernate.proxy.HibernateProxy;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.gf.service.dtoservice.DtoServiceImpl;
import net.datenwerke.gxtdto.server.dtomanager.DtoCreationHelper;
import net.datenwerke.gxtdto.server.dtomanager.DtoService;
import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.core.client.reportmanager.dto.interfaces.ReportVariantDto;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;
import net.datenwerke.rs.core.service.reportmanager.entities.reports.Report;
import net.datenwerke.rs.core.service.reportmanager.hooks.AdjustReportForExecutionHook;
import net.datenwerke.rs.core.service.reportmanager.interfaces.ReportVariant;

public class ReportDtoServiceImpl implements ReportDtoService {

	private final ReportService reportService;
	private final Provider<DtoService> dtoServiceProvider;
	private final HookHandlerService hookHandler;
	
	@Inject
	public ReportDtoServiceImpl(
		ReportService reportService,
		Provider<DtoService> dtoServiceProvider,
		HookHandlerService hookHandler) {
		
		this.reportService = reportService;
		this.dtoServiceProvider = dtoServiceProvider;
		this.hookHandler = hookHandler;
	}

	/**
	 * The reference Report of a reportdto is either the corresponding report if its a base report or the parent ..
	 */
	@Override
	public Report getReferenceReport(ReportDto reportDto) {
		if(null == reportDto.getId() && null == reportDto.getKey()){
			throw new IllegalStateException("report id and key are null");
		}
		
		Report referenceReport;
		if(null != reportDto.getId())
			referenceReport = (Report) reportService.getReportById(reportDto.getId());
		else
			referenceReport = (Report) reportService.getReportByKey(reportDto.getKey());
		
		if(referenceReport instanceof ReportVariant)
			referenceReport = reportService.getReportById((((ReportVariant) referenceReport).getBaseReport().getId()));
		
		return referenceReport;
	}

	@Override
	public Report getReport(ReportDto reportDto) {
		Report referenceReport = getReferenceReport(reportDto);
		
		Report report;
		if(null != reportDto.getId())
			report = (Report) reportService.getReportById(reportDto.getId());
		else
			report = (Report) reportService.getReportByKey(reportDto.getKey());
		
		if(! referenceReport.equals(report.getParent()) && ! report.equals(referenceReport))
			throw new IllegalStateException();
	
		return report;
	}
	
	/**
	 * Loads a DTO that can be used to execute a report. This message is safe to call from within DTO generation.
	 * 
	 */
	@Override
	public ReportDto loadFullDtoForExecution(Report realReport) {
		/* load metadata */
		for(AdjustReportForExecutionHook adjuster : hookHandler.getHookers(AdjustReportForExecutionHook.class))
			adjuster.adjust(realReport);
		
		DtoServiceImpl dtoService = (DtoServiceImpl) dtoServiceProvider.get();
		DtoCreationHelper creationHelper = dtoService.getCreationHelper();
		
		try{
			dtoService.setCreationHelper(null);

			if(realReport instanceof HibernateProxy)
				realReport = (Report) ((HibernateProxy)realReport).getHibernateLazyInitializer().getImplementation();
			
			ReportDto tmpReport = (ReportDto) dtoService.createDto(realReport);
			
			/* if report is a variant we need the full base report as well */
			if(realReport instanceof ReportVariant)
				((ReportVariantDto)tmpReport).setBaseReport((ReportDto)dtoService.createDto(realReport.getParent()));

			for(AdjustReportForExecutionHook adjuster : hookHandler.getHookers(AdjustReportForExecutionHook.class))
				adjuster.adjust(tmpReport);
			
			return tmpReport;
		} finally {
			dtoService.setCreationHelper(creationHelper);
		}		
	}

}
