package net.datenwerke.rs.base.service.reportengines.jasper.output.object;

import net.datenwerke.rs.core.service.reportmanager.engine.CompiledReport;
import net.datenwerke.rs.core.service.reportmanager.engine.basereports.HasPages;
import net.sf.jasperreports.engine.JasperPrint;

/**
 * Simple wrapper object that stores a completed JasperReport along with
 * parameters of interest.
 * 
 *
 */
abstract public class CompiledRSJasperReport implements CompiledReport, HasPages {

   /**
    * 
    */
   private static final long serialVersionUID = -7282903771389742735L;

   private int pageWidth;
   private int pageHeight;
   private int pages;

   abstract public void setReport(Object report);

   /**
    * Extracts all information from the JasperPrint object.
    * 
    * @param jasperPrint
    */
   public void setData(JasperPrint jasperPrint) {
      setPageHeight(jasperPrint.getPageHeight());
      setPageWidth(jasperPrint.getPageWidth());
      setPages(jasperPrint.getPages().size());
   }

   private void setPages(int size) {
      this.pages = size;
   }

   public int getPages() {
      return pages;
   }

   public int getPageWidth() {
      return pageWidth;
   }

   public void setPageWidth(int pagewidth) {
      this.pageWidth = pagewidth;
   }

   public int getPageHeight() {
      return pageHeight;
   }

   public void setPageHeight(int pageheight) {
      this.pageHeight = pageheight;
   }

   @Override
   public boolean hasData() {
      return true;
   }

   @Override
   public boolean isStringReport() {
      return false;
   }
}
