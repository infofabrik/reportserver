package net.datenwerke.rs.box.service.box.definitions.dtogen;

import net.datenwerke.dtoservices.dtogenerator.poso2dtogenerator.interfaces.Poso2DtoPostProcessor;
import net.datenwerke.rs.box.client.box.dto.BoxDatasinkDto;
import net.datenwerke.rs.box.service.box.definitions.BoxDatasink;

public class BoxDatasink2DtoPostProcessor implements Poso2DtoPostProcessor<BoxDatasink, BoxDatasinkDto> {

   @Override
   public void dtoCreated(BoxDatasink poso, BoxDatasinkDto dto) {
      if (null != poso.getRefreshToken() && !"".equals(poso.getRefreshToken().trim()))
         dto.setHasRefreshToken(true);

      if (null != poso.getSecretKey() && !"".equals(poso.getSecretKey().trim()))
         dto.setHasSecretKey(true);
   }

   @Override
   public void dtoInstantiated(BoxDatasink poso, BoxDatasinkDto dto) {
      // TODO Auto-generated method stub
   }
}
