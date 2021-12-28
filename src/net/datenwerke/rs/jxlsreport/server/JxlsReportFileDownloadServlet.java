package net.datenwerke.rs.jxlsreport.server;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;
import com.google.inject.persist.Transactional;

import net.datenwerke.rs.core.service.reportmanager.ReportService;
import net.datenwerke.rs.core.service.reportmanager.entities.AbstractReportManagerNode;
import net.datenwerke.rs.jxlsreport.service.jxlsreport.entities.JxlsReport;
import net.datenwerke.rs.jxlsreport.service.jxlsreport.entities.JxlsReportFile;
import net.datenwerke.rs.utils.misc.HttpUtils;
import net.datenwerke.security.server.SecuredHttpServlet;
import net.datenwerke.security.service.security.SecurityService;
import net.datenwerke.security.service.treedb.actions.ReadAction;

@Singleton
public class JxlsReportFileDownloadServlet extends SecuredHttpServlet {

   /**
    * 
    */
   private static final long serialVersionUID = -1345346400745387435L;

   /**
    * 
    */
   private final Provider<ReportService> reportManagerProvider;
   private final Provider<SecurityService> securityServiceProvider;
   private final Provider<HttpUtils> httpUtilsProvider;

   @Inject
   public JxlsReportFileDownloadServlet(Provider<ReportService> reportManagerProvider,
         Provider<SecurityService> securityServiceProvider, Provider<HttpUtils> httpUtilsProvider) {

      this.reportManagerProvider = reportManagerProvider;
      this.securityServiceProvider = securityServiceProvider;
      this.httpUtilsProvider = httpUtilsProvider;
   }

   @Override
   @Transactional(rollbackOn = { Exception.class })
   public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

      /* set utf-8 character encoding -> Ticket #691 */
      request.setCharacterEncoding("UTF-8");
      response.setCharacterEncoding("UTF-8");

      Long reportId = Long.valueOf(request.getParameter("id")); //$NON-NLS-1$

      ReportService reportService = reportManagerProvider.get();
      AbstractReportManagerNode rmn = reportService.getNodeById(reportId);

      /* check rights */
      validateRequest(rmn);

      if (rmn instanceof JxlsReport) {
         JxlsReport report = (JxlsReport) rmn;
         JxlsReportFile reportFile = report.getReportFile();

         /* set mime type */
         response.setContentType("application/xml"); //$NON-NLS-1$

         /* set header and encoding */
         response.setHeader(HttpUtils.CONTENT_DISPOSITION,
               httpUtilsProvider.get().makeContentDispositionHeader(true, reportFile.getName()));
         response.setCharacterEncoding("UTF-8"); //$NON-NLS-1$

         response.getOutputStream().write(reportFile.getContent());
      }
   }

   private void validateRequest(AbstractReportManagerNode report) {
      securityServiceProvider.get().assertActions(report, ReadAction.class);
   }
}
