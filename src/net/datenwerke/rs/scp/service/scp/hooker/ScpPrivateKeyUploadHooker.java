package net.datenwerke.rs.scp.service.scp.hooker;

import java.util.Map;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.gf.service.upload.UploadedFile;
import net.datenwerke.gf.service.upload.hooks.FileUploadHandlerHook;
import net.datenwerke.rs.core.service.datasinkmanager.DatasinkTreeService;
import net.datenwerke.rs.core.service.datasinkmanager.entities.AbstractDatasinkManagerNode;
import net.datenwerke.rs.scp.client.scp.ScpUIModule;
import net.datenwerke.rs.scp.service.scp.definitions.ScpDatasink;
import net.datenwerke.security.service.security.SecurityService;
import net.datenwerke.security.service.security.rights.Write;
import net.datenwerke.security.service.treedb.actions.UpdateAction;

public class ScpPrivateKeyUploadHooker implements FileUploadHandlerHook {

   private final Provider<SecurityService> securityServiceProvider;
   private final Provider<DatasinkTreeService> datasinkServiceProvider;

   @Inject
   public ScpPrivateKeyUploadHooker(
         Provider<SecurityService> securityServiceProvider,
         Provider<DatasinkTreeService> datasinkServiceProvider
         ) {
      this.securityServiceProvider = securityServiceProvider;
      this.datasinkServiceProvider = datasinkServiceProvider;
   }

   @Override
   public boolean consumes(String handler) {
      return ScpUIModule.SCP_PRIVATE_KEY_UPLOAD_HANDLER_ID.equals(handler);
   }

   @Override
   public String uploadOccured(UploadedFile uploadedFile) {
      Map<String, String> metadataMap = uploadedFile.getMetadata();

      long datasinkId = Long.valueOf(metadataMap.get(ScpUIModule.SCP_UPLOAD_DATASINK_ID_FIELD));
      byte[] privateKey = uploadedFile.getFileBytes();

      if (null == privateKey || 0 == privateKey.length)
         return null;

      SecurityService securityService = securityServiceProvider.get();
      securityService.assertUserLoggedIn();

      DatasinkTreeService datasinkService = datasinkServiceProvider.get();
      AbstractDatasinkManagerNode rmn = datasinkService.getNodeById(datasinkId);

      securityService.assertActions(rmn, UpdateAction.class);
      securityService.assertRights(rmn, Write.class);

      if (rmn instanceof ScpDatasink) {
         ScpDatasink scpDatasink = (ScpDatasink) rmn;
         scpDatasink.setPrivateKey(privateKey);

         /* merge into datasink */
         datasinkService.merge(scpDatasink);
      }

      return null;
   }

}
