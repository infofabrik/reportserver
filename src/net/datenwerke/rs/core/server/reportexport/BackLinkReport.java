package net.datenwerke.rs.core.server.reportexport;

import java.io.Serializable;
import java.util.UUID;

import net.datenwerke.rs.core.service.reportmanager.entities.reports.Report;

public class BackLinkReport implements Serializable {

   /**
    * 
    */
   private static final long serialVersionUID = -6423821722701693015L;

   private String id;
   private long reportId;
   private Report adjustedReport;
   private String outputFormat;

   private String predecessor;

   public BackLinkReport(long reportId, Report adjustedReport, String outputFormat) {
      this.reportId = reportId;
      this.adjustedReport = adjustedReport;
      this.outputFormat = outputFormat;
      this.id = UUID.randomUUID().toString().substring(0, 8);
   }

   public String getId() {
      return id;
   }

   public long getReportId() {
      return reportId;
   }

   public Report getAdjustedReport() {
      return adjustedReport;
   }

   public String getOutputFormat() {
      return outputFormat;
   }

   public void setPredecessor(String predecessor) {
      this.predecessor = predecessor;
   }

   public String getPredecessor() {
      return predecessor;
   }
}