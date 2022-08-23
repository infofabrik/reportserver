package net.datenwerke.rs.core.client.reportexporter;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.inject.Inject;

import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.base.client.AvailableReportProperties;
import net.datenwerke.rs.core.client.reportexporter.exporter.ReportExporter;
import net.datenwerke.rs.core.client.reportexporter.hooks.ReportExporterExportReportHook;
import net.datenwerke.rs.core.client.reportexporter.hooks.ReportExporterProviderHook;
import net.datenwerke.rs.core.client.reportexporter.hooks.VetoReportExporterHook;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportPropertyDto;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportStringPropertyDto;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.decorator.ReportDtoDec;

/**
 * 
 *
 */
public class ReportExporterUIServiceImpl implements ReportExporterUIService {

   private final HookHandlerService hookHandler;

   @Inject
   public ReportExporterUIServiceImpl(HookHandlerService hookHandler) {

      /* store objects */
      this.hookHandler = hookHandler;
   }

   @Override
   public List<ReportExporter> getCleanedUpAvailableExporters(ReportDto report, boolean removeHiddenInExportList) {
      List<ReportExporter> exporters = getAvailableExporters(report);

      Iterator<ReportExporter> exporterIt = exporters.iterator();
      if (!exporterIt.hasNext())
         return exporters;

      /* remove unallowed */
      while (exporterIt.hasNext()) {
         ReportExporter exporter = exporterIt.next();
         for (VetoReportExporterHook vetoer : hookHandler.getHookers(VetoReportExporterHook.class)) {
            if (vetoer.doesVetoExporter(exporter, report)) {
               exporterIt.remove();
               break;
            }
         }
      }
      /* remove the ones that should not be shown in export list */
      if (removeHiddenInExportList) {
         exporterIt = exporters.iterator();
         while (exporterIt.hasNext()) {
            ReportExporter exporter = exporterIt.next();
            if (!exporter.showInExportList()) {
               exporterIt.remove();
               break;
            }
         }
      }

      /* is there one exporter wants to be at the top? */
      exporterIt = exporters.iterator();
      ReportExporter topExporter = null;
      while (exporterIt.hasNext()) {
         ReportExporter exporter = exporterIt.next();
         if (exporter.wantsToBeTop(report)) {
            topExporter = exporter;
            exporterIt.remove();
            break;
         }
      }

      if (null != topExporter)
         exporters.add(0, topExporter);

      /* is there a default exporter set? */
      String defaultFormat = getDefaultOutputFormat(report);
      if (null != getDefaultOutputFormat(report)) {
         exporterIt = exporters.iterator();
         while (exporterIt.hasNext()) {
            ReportExporter exporter = exporterIt.next();
            if (defaultFormat.trim().toUpperCase().equals(exporter.getOutputFormat().toUpperCase())) {
               exporterIt.remove();
               exporters.add(0, exporter);
               break;
            }
         }
      }

      return exporters;
   }

   private String getDefaultOutputFormat(ReportDto report) {
      ReportDtoDec rep = (ReportDtoDec) report;

      ReportPropertyDto property = rep
            .getEffectiveReportProperty(AvailableReportProperties.PROPERTY_OUTPUT_FORMAT_DEFAULT.getValue());

      if (null != property && property instanceof ReportStringPropertyDto) {
         String defaultFormatString = ((ReportStringPropertyDto) property).getStrValue();
         return defaultFormatString;
      }

      return null;
   }

   @Override
   public List<ReportExporter> getAvailableExporters(ReportDto report) {
      List<ReportExporter> exporters = new ArrayList<ReportExporter>();

      List<ReportExporterExportReportHook> hookers = hookHandler.getHookers(ReportExporterExportReportHook.class);
      for (ReportExporterExportReportHook hooker : hookers) {
         if (hooker.getObject().consumes(report))
            exporters.add(hooker.getObject());
      }

      for (ReportExporterProviderHook hooker : hookHandler.getHookers(ReportExporterProviderHook.class))
         exporters.addAll(hooker.getExporters(report));

      return exporters;
   }

   @Override
   public List<ReportExporter> getUsableExporters(ReportDto report) {
      List<ReportExporter> exporters = new ArrayList<ReportExporter>();

      for (ReportExporter exporter : getAvailableExporters(report))
         if (exporter.consumes(report) && exporter.consumesConfiguration(report))
            exporters.add(exporter);

      return exporters;
   }

   @Override
   public String getExportServletPath() {
      String url = GWT.getModuleBaseURL() + "reportexport?nonce=" + Math.random(); //$NON-NLS-1$
      return url;
   }

}
