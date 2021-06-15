package net.datenwerke.rs.core.service.reportmanager.helpers;

import net.datenwerke.rs.core.service.reportmanager.ReportExecutorService;

public class ReportIconHelper {

   public String getSmallIconForOutputFormat(String format) {
      if (ReportExecutorService.OUTPUT_FORMAT_CSV.equals(format))
         return "csv";
      if (ReportExecutorService.OUTPUT_FORMAT_EXCEL.equals(format))
         return "xls";
      if (ReportExecutorService.OUTPUT_FORMAT_HTML.equals(format))
         return "html";
      if (ReportExecutorService.OUTPUT_FORMAT_PDF.equals(format))
         return "pdf";
      if (ReportExecutorService.OUTPUT_FORMAT_PNG.equals(format))
         return "png";
      if (ReportExecutorService.OUTPUT_FORMAT_RTF.equals(format))
         return "doc";

      return "file";
   }

}
