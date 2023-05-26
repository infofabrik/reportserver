package net.datenwerke.rs.pkg.server.pkg;

import java.io.ByteArrayInputStream;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;
import com.google.inject.name.Named;
import com.google.inject.persist.Transactional;

import net.datenwerke.gf.client.upload.dto.FileToUpload;
import net.datenwerke.gxtdto.client.servercommunication.exceptions.ServerCallFailedException;
import net.datenwerke.gxtdto.server.dtomanager.DtoService;
import net.datenwerke.rs.fileserver.client.fileserver.dto.FileServerFolderDto;
import net.datenwerke.rs.fileserver.service.fileserver.FileServerService;
import net.datenwerke.rs.fileserver.service.fileserver.entities.FileServerFile;
import net.datenwerke.rs.fileserver.service.fileserver.entities.FileServerFolder;
import net.datenwerke.rs.pkg.client.pkg.rpc.PkgRpcService;
import net.datenwerke.rs.pkg.service.pkg.PackagedScriptHelperService;
import net.datenwerke.rs.terminal.service.terminal.genrights.TerminalSecurityTarget;
import net.datenwerke.security.server.SecuredRemoteServiceServlet;
import net.datenwerke.security.service.security.annotation.ArgumentVerification;
import net.datenwerke.security.service.security.annotation.GenericTargetVerification;
import net.datenwerke.security.service.security.annotation.RightsVerification;
import net.datenwerke.security.service.security.annotation.SecurityChecked;
import net.datenwerke.security.service.security.rights.Execute;
import net.datenwerke.security.service.treedb.actions.InsertAction;

/**
 * 
 *
 */
@Singleton
public class PkgRpcServiceImpl extends SecuredRemoteServiceServlet
      implements PkgRpcService {

   /**
    * 
    */
   private static final long serialVersionUID = -8869851090677352067L;

   private final FileServerService fileService;
   private Provider<PackagedScriptHelperService> packagedScriptHelperProvider;
   private final DtoService dtoService;

   @Inject
   public PkgRpcServiceImpl(
         DtoService dtoService, 
         FileServerService fileService,
         Provider<PackagedScriptHelperService> packagedScriptHelperProvider
         ) {

      /* store objects */
      this.dtoService = dtoService;
      this.fileService = fileService;
      this.packagedScriptHelperProvider = packagedScriptHelperProvider;
   }


   @SecurityChecked(
         argumentVerification = {
               @ArgumentVerification(
                     name = "parentNode", 
                     isDto = true, 
                     verify = @RightsVerification(
                           actions = InsertAction.class
                     )
               )
         }, 
         genericTargetVerification = @GenericTargetVerification(
               target = TerminalSecurityTarget.class, 
               verify = @RightsVerification(
                     rights = Execute.class
               )
         )
   )
   @Override
   @Transactional(rollbackOn = { Exception.class })
   public String uploadAndExecute(@Named("parentNode") FileServerFolderDto folderDto,
         FileToUpload fileToUpload) throws ServerCallFailedException {
      if (null != fileToUpload) {
         FileServerFile archive = fileService.getFileFromUploadFile(fileToUpload);
         final PackagedScriptHelperService packagedScriptHelper = packagedScriptHelperProvider.get();
         FileServerFolder uploadPackage = null;
         try {
            uploadPackage = packagedScriptHelper.extractPackageTemporarily(new ByteArrayInputStream(archive.getData()));
            String result = packagedScriptHelper.executePackage(uploadPackage, null);
            
            if (null != result)
               return result;
            
         } catch (Exception e) {
            throw new ServerCallFailedException(e);
         } finally {
            fileService.forceRemove(uploadPackage);
         }
      }

      return "ok";
   }

}
