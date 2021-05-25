package net.datenwerke.rs.core.server.datasources;

import net.datenwerke.gxtdto.client.servercommunication.exceptions.ServerCallFailedException;
import net.datenwerke.gxtdto.server.dtomanager.DtoService;
import net.datenwerke.rs.core.client.datasourcemanager.dto.DatasourceDefinitionDto;
import net.datenwerke.rs.core.client.datasourcemanager.rpc.DatasourceRpcService;
import net.datenwerke.rs.core.client.datasourcemanager.rpc.DatasourceTreeLoader;
import net.datenwerke.rs.core.client.datasourcemanager.rpc.DatasourceTreeManager;
import net.datenwerke.rs.core.service.datasourcemanager.DatasourceService;
import net.datenwerke.rs.core.service.datasourcemanager.entities.AbstractDatasourceManagerNode;
import net.datenwerke.rs.core.service.datasourcemanager.entities.DatasourceDefinition;
import net.datenwerke.rs.utils.entitycloner.EntityClonerService;
import net.datenwerke.security.server.TreeDBManagerTreeHandler;
import net.datenwerke.security.service.security.SecurityService;
import net.datenwerke.security.service.security.rights.Read;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.inject.persist.Transactional;

/**
 * 
 *
 */
@Singleton
public class DatasourceManagerTreeHandlerRpcServiceImpl extends TreeDBManagerTreeHandler<AbstractDatasourceManagerNode>
      implements DatasourceTreeLoader, DatasourceTreeManager, DatasourceRpcService {

   /**
    * 
    */
   private static final long serialVersionUID = -455777535667237770L;

   private final DatasourceService datasourceService;

   @Inject
   public DatasourceManagerTreeHandlerRpcServiceImpl(DatasourceService datasourceService, DtoService dtoGenerator,
         SecurityService securityService, EntityClonerService entityClonerService) {

      super(datasourceService, dtoGenerator, securityService, entityClonerService);

      /* store objects */
      this.datasourceService = datasourceService;
   }

   @Override
   @Transactional(rollbackOn = { Exception.class })
   public DatasourceDefinitionDto getDefaultDatasource() throws ServerCallFailedException {
      DatasourceDefinition ds = datasourceService.getDefaultDatasource();

      if (null == ds)
         return null;

      if (!securityService.checkRights(ds, Read.class))
         return null;

      return (DatasourceDefinitionDto) dtoService.createDto(ds);
   }

   @Override
   protected boolean allowDuplicateNode(AbstractDatasourceManagerNode node) {
      return node instanceof DatasourceDefinition;
   }

   @Override
   protected void nodeCloned(AbstractDatasourceManagerNode clonedNode) {
      if (!(clonedNode instanceof DatasourceDefinition))
         throw new IllegalArgumentException();
      DatasourceDefinition datasource = (DatasourceDefinition) clonedNode;

      datasource.setName(datasource.getName() == null ? "copy" : datasource.getName() + " (copy)");
   }

}
