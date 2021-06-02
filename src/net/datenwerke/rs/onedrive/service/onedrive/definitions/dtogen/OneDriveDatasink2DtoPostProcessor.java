package net.datenwerke.rs.onedrive.service.onedrive.definitions.dtogen;

import net.datenwerke.dtoservices.dtogenerator.poso2dtogenerator.interfaces.Poso2DtoPostProcessor;
import net.datenwerke.rs.onedrive.client.onedrive.dto.OneDriveDatasinkDto;
import net.datenwerke.rs.onedrive.service.onedrive.definitions.OneDriveDatasink;

public class OneDriveDatasink2DtoPostProcessor implements Poso2DtoPostProcessor<OneDriveDatasink, OneDriveDatasinkDto> {

   @Override
   public void dtoCreated(OneDriveDatasink poso, OneDriveDatasinkDto dto) {
      if (null != poso.getRefreshToken() && !"".equals(poso.getRefreshToken().trim()))
         dto.setHasRefreshToken(true);

      if (null != poso.getSecretKey() && !"".equals(poso.getSecretKey().trim()))
         dto.setHasSecretKey(true);
   }

   @Override
   public void dtoInstantiated(OneDriveDatasink poso, OneDriveDatasinkDto dto) {
      // TODO Auto-generated method stub
   }
}