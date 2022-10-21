package net.datenwerke.rs.samba.service.samba.definitions.dtogen;

import net.datenwerke.dtoservices.dtogenerator.poso2dtogenerator.interfaces.Poso2DtoPostProcessor;
import net.datenwerke.rs.samba.client.samba.dto.SambaDatasinkDto;
import net.datenwerke.rs.samba.service.samba.definitions.SambaDatasink;

public class SambaDatasink2DtoPostProcessor implements Poso2DtoPostProcessor<SambaDatasink, SambaDatasinkDto> {

   @Override
   public void dtoCreated(SambaDatasink poso, SambaDatasinkDto dto) {
      if (null != poso.getPassword() && !"".equals(poso.getPassword().trim()))
         dto.setHasPassword(true);
   }

   @Override
   public void dtoInstantiated(SambaDatasink poso, SambaDatasinkDto dto) {
      // TODO Auto-generated method stub

   }

}
