package net.datenwerke.rs.remotersserver.service.remotersserver.entities.dtogen;


import net.datenwerke.dtoservices.dtogenerator.poso2dtogenerator.interfaces.Poso2DtoPostProcessor;
import net.datenwerke.rs.remotersserver.client.remotersserver.dto.RemoteRsServerDto;
import net.datenwerke.rs.remotersserver.service.remotersserver.entities.RemoteRsServer;

public class RemoteRsServer2DtoPostProcessor implements Poso2DtoPostProcessor<RemoteRsServer, RemoteRsServerDto> {

   @Override
   public void dtoCreated(RemoteRsServer poso, RemoteRsServerDto dto) {
      if (null != poso.getApikey() && !"".equals(poso.getApikey().trim()))
         dto.setHasApikey(true);
   }

   @Override
   public void dtoInstantiated(RemoteRsServer poso, RemoteRsServerDto dto) {
      // TODO Auto-generated method stub
      
   }

   

}
