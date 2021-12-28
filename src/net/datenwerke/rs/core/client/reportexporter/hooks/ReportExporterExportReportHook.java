package net.datenwerke.rs.core.client.reportexporter.hooks;

import com.google.inject.Provider;

import net.datenwerke.hookhandler.shared.hookhandler.interfaces.ObjectHook;
import net.datenwerke.rs.core.client.reportexporter.exporter.ReportExporter;

public class ReportExporterExportReportHook extends ObjectHook<ReportExporter> {

   public ReportExporterExportReportHook(ReportExporter object) {
      super(object);
   }

   public ReportExporterExportReportHook(Provider<? extends ReportExporter> object) {
      super(object);
   }

}
