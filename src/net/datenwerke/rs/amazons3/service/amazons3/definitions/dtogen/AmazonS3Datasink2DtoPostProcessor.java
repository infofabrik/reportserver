package net.datenwerke.rs.amazons3.service.amazons3.definitions.dtogen;

import net.datenwerke.dtoservices.dtogenerator.poso2dtogenerator.interfaces.Poso2DtoPostProcessor;
import net.datenwerke.rs.amazons3.client.amazons3.dto.AmazonS3DatasinkDto;
import net.datenwerke.rs.amazons3.service.amazons3.definitions.AmazonS3Datasink;

public class AmazonS3Datasink2DtoPostProcessor implements Poso2DtoPostProcessor<AmazonS3Datasink, AmazonS3DatasinkDto> {

   @Override
   public void dtoCreated(AmazonS3Datasink poso, AmazonS3DatasinkDto dto) {
      if (null != poso.getSecretKey() && !"".equals(poso.getSecretKey().trim()))
         dto.setHasSecretKey(true);
   }

   @Override
   public void dtoInstantiated(AmazonS3Datasink poso, AmazonS3DatasinkDto dto) {
      // TODO Auto-generated method stub
   }
}
