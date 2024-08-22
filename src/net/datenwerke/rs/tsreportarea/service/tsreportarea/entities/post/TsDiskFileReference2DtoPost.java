package net.datenwerke.rs.tsreportarea.service.tsreportarea.entities.post;

import com.google.inject.Inject;

import net.datenwerke.dtoservices.dtogenerator.poso2dtogenerator.interfaces.Poso2DtoPostProcessor;
import net.datenwerke.rs.core.service.reportmanager.helpers.ReportIconHelper;
import net.datenwerke.rs.tsreportarea.client.tsreportarea.dto.TsDiskFileReferenceDto;
import net.datenwerke.rs.tsreportarea.client.tsreportarea.dto.decorator.TsDiskFileReferenceDtoDec;
import net.datenwerke.rs.tsreportarea.client.tsreportarea.dto.decorator.TsDiskGeneralReferenceDtoDec;
import net.datenwerke.rs.tsreportarea.service.tsreportarea.entities.TsDiskFileReference;

public class TsDiskFileReference2DtoPost implements Poso2DtoPostProcessor<TsDiskFileReference, TsDiskFileReferenceDto> {

   private final ReportIconHelper iconHelper;
   
   @Inject
   public TsDiskFileReference2DtoPost(ReportIconHelper iconHelper) {
      this.iconHelper = iconHelper;
   }
   
   @Override
   public void dtoCreated(TsDiskFileReference poso, TsDiskFileReferenceDto dto) {
      String filename = poso.getFileName();
      if (null != filename && filename.contains(".")) {
         String ending = filename.substring(filename.lastIndexOf(".")+1);
         
         String iconStr = iconHelper.getSmallIconForOutputFormat(ending.toUpperCase());
         if ("file".equals(iconStr)) {
            switch (ending) {
            case "xlsx":
            case "xls":
            case "xlsm":
               iconStr = "xls";
               break;
            case "docx":
            case "doc":
            case "dot":
            case "dotm":
               iconStr = "doc";
               break;
            case "jpg":
            case "jpeg":
            case "tif":
            case "tiff":
            case "bmp":
               iconStr = "png";
               break;
            }
            
         }
         ((TsDiskFileReferenceDtoDec) dto).setIconStr(iconStr);
               
         ((TsDiskFileReferenceDtoDec) dto).setTypeStr(ending);
      }
      
      ((TsDiskGeneralReferenceDtoDec) dto).setReferenceLastUpdated(poso.getReferenceLastUpdated());
   }

   @Override
   public void dtoInstantiated(TsDiskFileReference poso, TsDiskFileReferenceDto dto) {

   }

}

