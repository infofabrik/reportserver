package net.datenwerke.rs.teamspace.service.teamspace.hooks;

import java.util.Map;

import com.google.inject.Inject;

import net.datenwerke.gf.service.upload.UploadedFile;
import net.datenwerke.gf.service.upload.hooks.FileUploadHandlerHook;
import net.datenwerke.rs.teamspace.client.teamspace.TeamSpaceUIModule;
import net.datenwerke.rs.teamspace.service.teamspace.TeamSpaceService;
import net.datenwerke.rs.teamspace.service.teamspace.entities.TeamSpace;
import net.datenwerke.rs.tsreportarea.service.tsreportarea.TsDiskService;
import net.datenwerke.rs.tsreportarea.service.tsreportarea.entities.AbstractTsDiskNode;
import net.datenwerke.rs.tsreportarea.service.tsreportarea.entities.TsDiskFileReference;

public class FileIntoTeamSpaceUploadHooker implements FileUploadHandlerHook {

   private final TsDiskService diskService;
   private final TeamSpaceService teamSpaceService;
   private final TsDiskService tsDiskService;

   @Inject
   public FileIntoTeamSpaceUploadHooker(
         TsDiskService diskService, 
         TeamSpaceService teamSpaceService, 
         TsDiskService tsDiskService
         ) {
      this.diskService = diskService;
      this.teamSpaceService = teamSpaceService;
      this.tsDiskService = tsDiskService;
   }

   @Override
   public String uploadOccured(UploadedFile uploadedFile, Map<String,String> context) {
      long teamspaceId = Long.valueOf(context.get(TeamSpaceUIModule.TEAMSPACE_IMPORT_TEAMSPACE_ID));
      Long folderId = null;
      if(context.containsKey(TeamSpaceUIModule.TEAMSPACE_IMPORT_FOLDER_ID))
         folderId = Long.parseLong(context.get(TeamSpaceUIModule.TEAMSPACE_IMPORT_FOLDER_ID));
      
      TeamSpace teamspace = teamSpaceService.getTeamSpaceById(teamspaceId);
      AbstractTsDiskNode folder = null;
      if (null != folderId)
         folder = tsDiskService.getNodeById(folderId);
      else
         folder = tsDiskService.getRoot(teamspace);
      
      TsDiskFileReference ref = new TsDiskFileReference();
      ref.setName(context.get(TeamSpaceUIModule.TEAMSPACE_IMPORT_NAME));
      ref.setDescription(null == context.get(TeamSpaceUIModule.TEAMSPACE_IMPORT_DESCRIPTION) ? ""
            : context.get(TeamSpaceUIModule.TEAMSPACE_IMPORT_DESCRIPTION));
      ref.setData(uploadedFile.getFileBytes());
      ref.setFileName(uploadedFile.getFileName());
      ref.setContentType(uploadedFile.getContentType());
      ref.setLastUpdated(uploadedFile.getUploadTime());
      
      folder.addChild(ref);
      
      diskService.persist(ref);
      diskService.merge(folder);
      
      return null;
   }

   @Override
   public boolean consumes(String handler) {
      return TeamSpaceUIModule.TEAMSPACE_FILE_IMPORT_HANDLER_ID.equals(handler);
   }

}