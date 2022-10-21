package net.datenwerke.rs.googledrive.service.googledrive.definitions.dtogen;

import net.datenwerke.dtoservices.dtogenerator.poso2dtogenerator.interfaces.Poso2DtoPostProcessor;
import net.datenwerke.rs.googledrive.client.googledrive.dto.GoogleDriveDatasinkDto;
import net.datenwerke.rs.googledrive.service.googledrive.definitions.GoogleDriveDatasink;

public class GoogleDriveDatasink2DtoPostProcessor
      implements Poso2DtoPostProcessor<GoogleDriveDatasink, GoogleDriveDatasinkDto> {

   @Override
   public void dtoCreated(GoogleDriveDatasink poso, GoogleDriveDatasinkDto dto) {
      if (null != poso.getRefreshToken() && !"".equals(poso.getRefreshToken().trim()))
         dto.setHasRefreshToken(true);

      if (null != poso.getSecretKey() && !"".equals(poso.getSecretKey().trim()))
         dto.setHasSecretKey(true);
   }

   @Override
   public void dtoInstantiated(GoogleDriveDatasink poso, GoogleDriveDatasinkDto dto) {
      // TODO Auto-generated method stub
   }
}
