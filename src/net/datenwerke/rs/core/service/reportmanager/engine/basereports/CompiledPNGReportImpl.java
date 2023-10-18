package net.datenwerke.rs.core.service.reportmanager.engine.basereports;

import java.awt.image.BufferedImage;
import java.util.Objects;

public class CompiledPNGReportImpl implements CompiledPngReport{
   
   private static final long serialVersionUID = -890780478058336670L;
   private final BufferedImage[] report;
   
   public CompiledPNGReportImpl(BufferedImage[] report) {
      super();
      this.report = report;
   }

   @Override
   public String getMimeType() {
      // TODO Auto-generated method stub
      return "image/png"; //$NON-NLS-1$
   }

   @Override
   public String getFileExtension() {
      // TODO Auto-generated method stub
      return "png"; //$NON-NLS-1$
   }

   @Override
   public boolean hasData() {
      if(Objects.nonNull(report)) return true;
      return false;
   }

   @Override
   public boolean isStringReport() {   
      return false;
   }

   @Override
   public BufferedImage[] getReport() {
      // TODO Auto-generated method stub
      return report;
   }

}
