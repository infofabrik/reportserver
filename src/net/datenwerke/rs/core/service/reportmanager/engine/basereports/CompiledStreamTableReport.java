package net.datenwerke.rs.core.service.reportmanager.engine.basereports;

import java.util.Objects;

import net.datenwerke.rs.core.service.reportmanager.engine.CompiledReport;

public class CompiledStreamTableReport implements CompiledReport {

   /**
    * 
    */
   private static final long serialVersionUID = -2495473276012232477L;

   private final Object report;

   public CompiledStreamTableReport(Object report) {
      this.report = report;
   }

   public Object getReport() {
      return report;
   }

   public String getMimeType() {
      return "";
   }

   public String getFileExtension() {
      return "";
   }

   @Override
   public int hashCode() {
      return Objects.hashCode(report);
   }

   @Override
   public boolean equals(Object obj) {
      if (obj == this)
         return true;
      if (obj == null)
         return false;
      
      if (obj instanceof CompiledStreamTableReport) {
         final CompiledStreamTableReport other = (CompiledStreamTableReport) obj;
         return Objects.equals(report, other.report);
      } else {
         return false;
      }
   }

   @Override
   public boolean hasData() {
      return report != null;
   }

   @Override
   public boolean isStringReport() {
      return false;
   }
}
