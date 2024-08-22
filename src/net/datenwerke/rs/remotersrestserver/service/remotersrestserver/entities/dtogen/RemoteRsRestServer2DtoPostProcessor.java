package net.datenwerke.rs.remotersrestserver.service.remotersrestserver.entities.dtogen;


import net.datenwerke.dtoservices.dtogenerator.poso2dtogenerator.interfaces.Poso2DtoPostProcessor;
import net.datenwerke.rs.remotersrestserver.client.remotersrestserver.dto.RemoteRsRestServerDto;
import net.datenwerke.rs.remotersrestserver.service.remotersrestserver.entities.RemoteRsRestServer;

public class RemoteRsRestServer2DtoPostProcessor implements Poso2DtoPostProcessor<RemoteRsRestServer, RemoteRsRestServerDto> {

   @Override
   public void dtoCreated(RemoteRsRestServer poso, RemoteRsRestServerDto dto) {
      if (null != poso.getApikey() && !"".equals(poso.getApikey().trim()))
         dto.setHasApikey(true);
   }

   @Override
   public void dtoInstantiated(RemoteRsRestServer poso, RemoteRsRestServerDto dto) {
      // TODO Auto-generated method stub
      
   }

   

}
