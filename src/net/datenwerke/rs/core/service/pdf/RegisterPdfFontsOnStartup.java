package net.datenwerke.rs.core.service.pdf;

import com.google.inject.Inject;

import net.datenwerke.gf.service.lateinit.LateInitHook;
import net.datenwerke.rs.birt.service.reportengine.BirtReportService;
import net.datenwerke.rs.utils.misc.PdfUtils;

public class RegisterPdfFontsOnStartup implements LateInitHook {

   private final PdfUtils pdfUtils;
   private final BirtReportService birtService;

   @Inject
   public RegisterPdfFontsOnStartup(PdfUtils pdfUtils, BirtReportService birtService) {
      this.pdfUtils = pdfUtils;
      this.birtService = birtService;
   }

   @Override
   public void initialize() {
      pdfUtils.registerPdfFonts();
      birtService.clearFontCache();
   }

}
