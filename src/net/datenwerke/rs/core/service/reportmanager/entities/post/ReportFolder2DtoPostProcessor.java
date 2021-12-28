package net.datenwerke.rs.core.service.reportmanager.entities.post;

import net.datenwerke.dtoservices.dtogenerator.poso2dtogenerator.interfaces.Poso2DtoPostProcessor;
import net.datenwerke.rs.core.client.reportmanager.dto.ReportFolderDto;
import net.datenwerke.rs.core.service.reportmanager.entities.ReportFolder;

public class ReportFolder2DtoPostProcessor implements Poso2DtoPostProcessor<ReportFolder, ReportFolderDto> {

   @Override
   public void dtoCreated(ReportFolder arg0, ReportFolderDto arg1) {
      arg1.setIsReportRoot(null == arg0.getParent());
   }

   @Override
   public void dtoInstantiated(ReportFolder arg0, ReportFolderDto arg1) {
      // TODO Auto-generated method stub

   }

}
