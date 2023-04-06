package net.datenwerke.rs.remoteserver.service.remoteservermanager.entities.dtogen;

import net.datenwerke.dtoservices.dtogenerator.poso2dtogenerator.interfaces.Poso2DtoPostProcessor;
import net.datenwerke.rs.remoteserver.client.remoteservermanager.dto.RemoteServerDto;
import net.datenwerke.rs.remoteserver.service.remoteservermanager.entities.RemoteServer;

public class RemoteServer2DtoPostProcessor implements Poso2DtoPostProcessor<RemoteServer, RemoteServerDto> {

   @Override
   public void dtoCreated(RemoteServer poso, RemoteServerDto dto) {
      if (null != poso.getApikey() && !"".equals(poso.getApikey().trim()))
         dto.setHasApikey(true);
   }

   @Override
   public void dtoInstantiated(RemoteServer poso, RemoteServerDto dto) {
      // TODO Auto-generated method stub

   }

}
