package net.datenwerke.rs.eximport.client.eximport.ex.dto;

import java.io.Serializable;

public class ExportedNodeDto implements Serializable {

   /**
    * 
    */
   private static final long serialVersionUID = 1L;

   private String path;
   private String node;
   private String exportedBy;
   private String exportTimeStamp;
   private String reportServerVersion;
   private String export;

   public ExportedNodeDto() {
   }

   public String getPath() {
      return path;
   }

   public void setPath(String path) {
      this.path = path;
   }

   public String getExportedBy() {
      return exportedBy;
   }

   public void setExportedBy(String exportedBy) {
      this.exportedBy = exportedBy;
   }

   public String getExportTimeStamp() {
      return exportTimeStamp;
   }

   public void setExportTimeStamp(String exportTimeStamp) {
      this.exportTimeStamp = exportTimeStamp;
   }

   public String getReportServerVersion() {
      return reportServerVersion;
   }

   public void setReportServerVersion(String reportServerVersion) {
      this.reportServerVersion = reportServerVersion;
   }

   public String getExport() {
      return export;
   }

   public void setExport(String export) {
      this.export = export;
   }

   public String getNode() {
      return node;
   }

   public void setNode(String node) {
      this.node = node;
   }
}
