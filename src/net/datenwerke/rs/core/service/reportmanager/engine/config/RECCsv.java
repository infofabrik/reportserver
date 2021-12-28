package net.datenwerke.rs.core.service.reportmanager.engine.config;

import java.util.Objects;

import net.datenwerke.dtoservices.dtogenerator.annotations.ExposeToClient;
import net.datenwerke.dtoservices.dtogenerator.annotations.GenerateDto;

@GenerateDto(
      dtoPackage = "net.datenwerke.rs.core.client.reportexporter.dto", 
      createDecorator = true
      )
public class RECCsv implements ReportExecutionConfig {

   /**
    * 
    */
   private static final long serialVersionUID = 6096389901801569328L;

   @ExposeToClient
   private boolean printHeader = true;

   @ExposeToClient
   private String separator = ";";

   @ExposeToClient
   private String quote = "\"";

   @ExposeToClient
   private String lineSeparator = "\r\n";

   @ExposeToClient
   private String charset = "UTF-8";

   public String getCharset() {
      return charset;
   }

   public void setCharset(String charset) {
      this.charset = charset;
   }

   public String getLineSeparator() {
      return lineSeparator;
   }

   public void setLineSeparator(String lineSeparator) {
      this.lineSeparator = lineSeparator;
   }

   public boolean isPrintHeader() {
      return printHeader;
   }

   public void setPrintHeader(boolean printHeader) {
      this.printHeader = printHeader;
   }

   public String getSeparator() {
      return separator;
   }

   public void setSeparator(String separator) {
      this.separator = separator;
   }

   public String getQuote() {
      return quote;
   }

   public void setQuote(String quote) {
      this.quote = quote;
   }

   @Override
   public int hashCode() {
      return Objects.hash(printHeader, separator, quote, lineSeparator, charset);
   }

   @Override
   public boolean equals(Object obj) {
      if (obj == this)
         return true;
      if (obj == null)
         return false;

      if (obj instanceof RECCsv) {
         final RECCsv other = (RECCsv) obj;
         return printHeader == other.printHeader
               && Objects.equals(separator, other.separator)
               && Objects.equals(quote, other.quote)
               && Objects.equals(lineSeparator, other.lineSeparator)
               && Objects.equals(charset, other.charset);
      } else {
         return false;
      }
   }

}
