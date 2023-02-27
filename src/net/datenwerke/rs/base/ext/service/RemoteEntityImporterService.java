package net.datenwerke.rs.base.ext.service;

import net.datenwerke.eximport.im.ImportResult;

public interface RemoteEntityImporterService {

   /**
    * Note that restUrl, user and apikey will be removed in the near future. These
    * will be replaced by remoteServer entities.
    * 
    * @param restUrl         the REST URL to the export.
    * @param user            the user
    * @param apikey          the apikey
    * @param entityPath      path to the remote entity to be imported, e.g.
    *                        "/usermanager/ClassicModelCars"
    * @param target          path to the local directory to import the entity, e.g.
    *                        "/usermanager/import"
    * @param includeVariants in case of reports, true if report variants be
    *                        included. If false, only the base reports are
    *                        imported.
    */
   ImportResult importRemoteEntities(String restUrl, String user, String apikey, String remoteEntityPath,
         String localTarget, boolean includeVariants);

}
