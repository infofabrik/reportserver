package net.datenwerke.rs.core.server.datasinks;

import javax.inject.Singleton;

import com.google.inject.Inject;

import net.datenwerke.gxtdto.client.servercommunication.exceptions.ServerCallFailedException;
import net.datenwerke.gxtdto.server.dtomanager.DtoService;
import net.datenwerke.rs.core.client.datasinkmanager.dto.DatasinkDefinitionDto;
import net.datenwerke.rs.core.client.datasinkmanager.rpc.DatasinkRpcService;
import net.datenwerke.rs.core.service.datasinkmanager.DatasinkService;
import net.datenwerke.rs.core.service.datasinkmanager.FolderedDatasink;
import net.datenwerke.rs.core.service.datasinkmanager.entities.DatasinkDefinition;
import net.datenwerke.security.server.SecuredRemoteServiceServlet;
import net.datenwerke.security.service.security.SecurityService;
import net.datenwerke.security.service.security.rights.Read;

@Singleton
public class DatasinkRpcServiceImpl extends SecuredRemoteServiceServlet implements DatasinkRpcService {

   /**
    * 
    */
   private static final long serialVersionUID = 2912917629076438824L;

   private final SecurityService securityService;
   private final DatasinkService datasinkService;
   private final DtoService dtoService;

   @Inject
   public DatasinkRpcServiceImpl(SecurityService securityService, DatasinkService datasinkService,
         DtoService dtoService) {
      this.securityService = securityService;
      this.datasinkService = datasinkService;
      this.dtoService = dtoService;
   }

   @Override
   public String getDefaultFolder(DatasinkDefinitionDto datasinkDto) throws ServerCallFailedException {
      DatasinkDefinition datasink = (DatasinkDefinition) dtoService.loadPoso(datasinkDto);

      securityService.assertRights(datasink, Read.class);

      if (!(datasink instanceof FolderedDatasink))
         throw new ServerCallFailedException("not a foldered datasink");

      FolderedDatasink folderedDatasink = (FolderedDatasink) datasink;
      return datasinkService.getDefaultFolder(folderedDatasink);
   }

}
