package net.datenwerke.rs.core.service.reportmanager.engine.config;

import java.util.Objects;

import net.datenwerke.dtoservices.dtogenerator.annotations.ExposeToClient;
import net.datenwerke.dtoservices.dtogenerator.annotations.GenerateDto;

@GenerateDto(dtoPackage = "net.datenwerke.rs.core.client.reportexporter.dto", createDecorator = true)
public class RECJxls implements ReportExecutionConfig {

   /**
    * 
    */
   private static final long serialVersionUID = 912269255461484917L;

   @ExposeToClient
   private int numberColumnWidth = 8;

   @ExposeToClient
   private int textColumnWidth = 8;

   @ExposeToClient
   private int dateColumnWidth = 8;

   @ExposeToClient
   private int currencyColumnWidth = 8;

   @ExposeToClient
   public boolean jxlsReport = false;

   public boolean isJxlsReport() {
      return jxlsReport;
   }

   public void setJxlsReport(boolean jxlsReport) {
      this.jxlsReport = jxlsReport;
   }

   public int getNumberColumnWidth() {
      return numberColumnWidth;
   }

   public void setNumberColumnWidth(int numberColumnWidth) {
      this.numberColumnWidth = numberColumnWidth;
   }

   public void setDateColumnWidth(int dateColumnWidth) {
      this.dateColumnWidth = dateColumnWidth;
   }

   public int getTextColumnWidth() {
      return textColumnWidth;
   }

   public int getDateColumnWidth() {
      return dateColumnWidth;
   }

   public void setTextColumnWidth(int textColumnWidth) {
      this.textColumnWidth = textColumnWidth;
   }

   public int getCurrencyColumnWidth() {
      return currencyColumnWidth;
   }

   public void setCurrencyColumnWidth(int currencyColumnWidth) {
      this.currencyColumnWidth = currencyColumnWidth;
   }

   @Override
   public int hashCode() {
      return Objects.hash(numberColumnWidth, textColumnWidth, dateColumnWidth, currencyColumnWidth, jxlsReport);
   }

   @Override
   public boolean equals(Object obj) {
      if (obj == this)
         return true;
      if (obj == null)
         return false;

      if (obj instanceof RECJxls) {
         final RECJxls other = (RECJxls) obj;
         return numberColumnWidth == other.numberColumnWidth 
               && textColumnWidth == other.textColumnWidth
               && dateColumnWidth == other.dateColumnWidth 
               && currencyColumnWidth == other.currencyColumnWidth
               && jxlsReport == other.jxlsReport;
      } else {
         return false;
      }
   }

}