package net.datenwerke.rs.base.ext.service;

import java.util.Map;

import net.datenwerke.eximport.im.ImportResult;
import net.datenwerke.rs.remotersrestserver.service.remotersrestserver.entities.RemoteRsRestServer;

public interface RemoteEntityImporterService {

   public static final String CONFIG_FILE = "main/rssync.cf";

   /**
    * Note that restUrl, user and apikey will be removed in the near future. These
    * will be replaced by remoteServer entities.
    * 
    * @param remoteRsServer  the remote RS server.
    * @param entityPath      path to the remote entity to be imported, e.g.
    *                        "/usermanager/ClassicModelCars"
    * @param target          path to the local directory to import the entity, e.g.
    *                        "/usermanager/import"
    * @param includeVariants in case of reports, true if report variants be
    *                        included. If false, only the base reports are
    *                        imported.
    */
   ImportResult importRemoteEntities(RemoteRsRestServer remoteRsServer, String remoteEntityPath, String localTarget,
         boolean includeVariants);

   /**
    * Note that restUrl, user and apikey will be removed in the near future. These
    * will be replaced by remoteServer entities.
    * 
    * @param remoteRsServer  the remote RS server.
    * @param entityPath      path to the remote entity to be imported, e.g.
    *                        "/usermanager/ClassicModelCars"
    * @param target          path to the local directory to import the entity, e.g.
    *                        "/usermanager/import"
    * @param includeVariants in case of reports, true if report variants be
    *                        included. If false, only the base reports are
    *                        imported.
    * @param errors          list of errors. Can be edited in the method to add new
    *                        errors if found.
    */
   Map<String, Object> checkImportRemoteEntities(RemoteRsRestServer remoteRsServer, String remoteEntityPath,
         String localTarget, boolean includeVariants, Map<String, Object> errors);

}
