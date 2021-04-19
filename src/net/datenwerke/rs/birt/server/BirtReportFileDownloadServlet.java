package net.datenwerke.rs.birt.server;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;
import com.google.inject.persist.Transactional;

import net.datenwerke.rs.birt.service.reportengine.entities.BirtReport;
import net.datenwerke.rs.birt.service.reportengine.entities.BirtReportFile;
import net.datenwerke.rs.core.service.reportmanager.ReportService;
import net.datenwerke.rs.core.service.reportmanager.entities.AbstractReportManagerNode;
import net.datenwerke.rs.utils.misc.HttpUtils;
import net.datenwerke.security.server.SecuredHttpServlet;
import net.datenwerke.security.service.security.SecurityService;
import net.datenwerke.security.service.treedb.actions.ReadAction;

@Singleton
public class BirtReportFileDownloadServlet extends SecuredHttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7092450658564936400L;
	private final Provider<ReportService> reportManagerProvider;
	private final Provider<SecurityService> securityServiceProvider;
	private final Provider<HttpUtils> httpUtilsProvider;


	@Inject
	public BirtReportFileDownloadServlet(
			Provider<ReportService> reportManagerProvider,
			Provider<SecurityService> securityServiceProvider, 
			Provider<HttpUtils> httpUtilsProvider) {
		
				this.reportManagerProvider = reportManagerProvider;
				this.securityServiceProvider = securityServiceProvider;
				this.httpUtilsProvider = httpUtilsProvider;
	}
	
	
	@Override
	@Transactional(rollbackOn={Exception.class})
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		/* set utf-8 character encoding -> Ticket #691*/
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		Long reportId = Long.valueOf(request.getParameter("id")); //$NON-NLS-1$
		
		ReportService reportService = reportManagerProvider.get();
		AbstractReportManagerNode rmn = reportService.getNodeById(reportId);
		
		/* check rights */
		validateRequest(rmn);
		
		if(rmn instanceof BirtReport){
			BirtReport report = (BirtReport)rmn;
			BirtReportFile reportFile = report.getReportFile();
			
			/* set mime type */
			response.setContentType("application/xml"); //$NON-NLS-1$
			
			/* set header and encoding */
			response.setHeader(HttpUtils.CONTENT_DISPOSITION, httpUtilsProvider.get().makeContentDispositionHeader(true, reportFile.getName())); 
			response.setCharacterEncoding("UTF-8"); //$NON-NLS-1$
			
			response.getOutputStream().write(reportFile.getContent().getBytes());
		}
	}
	
	
	private void validateRequest(AbstractReportManagerNode report) {
		securityServiceProvider.get().assertActions(report, ReadAction.class);
	}
}
