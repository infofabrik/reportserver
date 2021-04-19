package net.datenwerke.rs.scp.service.scp.definitions.dtogen;

import net.datenwerke.dtoservices.dtogenerator.poso2dtogenerator.interfaces.Poso2DtoPostProcessor;
import net.datenwerke.rs.scp.client.scp.dto.ScpDatasinkDto;
import net.datenwerke.rs.scp.service.scp.definitions.ScpDatasink;

public class ScpDatasink2DtoPostProcessor implements Poso2DtoPostProcessor<ScpDatasink, ScpDatasinkDto> {

   @Override
   public void dtoCreated(ScpDatasink poso, ScpDatasinkDto dto) {
      if (null != poso.getPassword() && !"".equals(poso.getPassword().trim()))
         dto.setHasPassword(true);

      if (null != poso.getPrivateKeyPassphrase() && !"".equals(poso.getPrivateKeyPassphrase().trim()))
         dto.setHasPrivateKeyPassphrase(true);

   }

   @Override
   public void dtoInstantiated(ScpDatasink poso, ScpDatasinkDto dto) {
      // TODO Auto-generated method stub

   }

}
