package net.datenwerke.rs.fileserver.server.fileserver;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;
import com.google.inject.name.Named;
import com.google.inject.persist.Transactional;

import groovy.lang.Closure;
import net.datenwerke.gf.client.upload.dto.FileToUpload;
import net.datenwerke.gxtdto.client.dtomanager.Dto;
import net.datenwerke.gxtdto.client.servercommunication.exceptions.ExpectedException;
import net.datenwerke.gxtdto.client.servercommunication.exceptions.ServerCallFailedException;
import net.datenwerke.gxtdto.server.dtomanager.DtoService;
import net.datenwerke.rs.eximport.service.eximport.ex.http.HttpExportService;
import net.datenwerke.rs.eximport.service.genrights.ExportSecurityTarget;
import net.datenwerke.rs.fileserver.client.fileserver.dto.AbstractFileServerNodeDto;
import net.datenwerke.rs.fileserver.client.fileserver.dto.FileServerFileDto;
import net.datenwerke.rs.fileserver.client.fileserver.dto.FileServerFolderDto;
import net.datenwerke.rs.fileserver.client.fileserver.eximport.ex.rpc.FileServerExportRpcService;
import net.datenwerke.rs.fileserver.client.fileserver.rpc.FileServerRpcService;
import net.datenwerke.rs.fileserver.client.fileserver.rpc.FileServerTreeLoader;
import net.datenwerke.rs.fileserver.client.fileserver.rpc.FileServerTreeManager;
import net.datenwerke.rs.fileserver.service.fileserver.FileServerService;
import net.datenwerke.rs.fileserver.service.fileserver.entities.AbstractFileServerNode;
import net.datenwerke.rs.fileserver.service.fileserver.entities.FileServerFile;
import net.datenwerke.rs.fileserver.service.fileserver.entities.FileServerFolder;
import net.datenwerke.rs.fileserver.service.fileserver.eximport.FileServerExporter;
import net.datenwerke.rs.fileserver.service.fileserver.terminal.commands.unzip.BasepathZipExtractConfigFactory;
import net.datenwerke.rs.keyutils.service.keyutils.KeyNameGeneratorService;
import net.datenwerke.rs.utils.entitycloner.EntityClonerService;
import net.datenwerke.rs.utils.zip.ZipExtractionConfig;
import net.datenwerke.rs.utils.zip.ZipUtilsService;
import net.datenwerke.security.server.TreeDBManagerTreeHandler;
import net.datenwerke.security.service.security.SecurityService;
import net.datenwerke.security.service.security.annotation.ArgumentVerification;
import net.datenwerke.security.service.security.annotation.GenericTargetVerification;
import net.datenwerke.security.service.security.annotation.RightsVerification;
import net.datenwerke.security.service.security.annotation.SecurityChecked;
import net.datenwerke.security.service.security.rights.Execute;
import net.datenwerke.security.service.security.rights.Read;
import net.datenwerke.security.service.security.rights.Write;
import net.datenwerke.security.service.treedb.actions.InsertAction;
import net.datenwerke.treedb.client.treedb.dto.AbstractNodeDto;
import net.datenwerke.treedb.ext.service.eximport.helper.TreeNodeExportHelperService;

/**
 * 
 *
 */
@Singleton
public class FileServerRpcServiceImpl extends TreeDBManagerTreeHandler<AbstractFileServerNode>
      implements FileServerRpcService, FileServerTreeLoader, FileServerTreeManager, FileServerExportRpcService {

   /**
    * 
    */
   private static final long serialVersionUID = -8869851090677352067L;

   private final FileServerService fileService;
   private final Provider<HttpExportService> httpExportServiceProvider;
   private final BasepathZipExtractConfigFactory extractConfigFactory;
   private final Provider<ZipUtilsService> zipUtilsServiceProvider;
   private final Provider<TreeNodeExportHelperService> exportHelper;

   @Inject
   public FileServerRpcServiceImpl(
         DtoService dtoService, 
         FileServerService fileService,
         SecurityService securityService, 
         EntityClonerService entityClonerService, 
         Provider<HttpExportService> httpExportServiceProvider, 
         BasepathZipExtractConfigFactory extractConfigFactory, 
         Provider<ZipUtilsService> zipUtilsServiceProvider,
         Provider<TreeNodeExportHelperService> exportHelper,
         KeyNameGeneratorService keyGeneratorService
         ) {

      super(fileService, 
            dtoService, 
            securityService, 
            entityClonerService,
            keyGeneratorService);

      /* store objects */
      this.fileService = fileService;
      this.httpExportServiceProvider = httpExportServiceProvider;
      this.extractConfigFactory = extractConfigFactory;
      this.zipUtilsServiceProvider = zipUtilsServiceProvider;
      this.exportHelper = exportHelper;
   }

   @SecurityChecked(argumentVerification = {
         @ArgumentVerification(name = "node", isDto = true, verify = @RightsVerification(rights = Write.class)) })
   @Override
   @Transactional(rollbackOn = Exception.class)
   public void updateFile(@Named("node") FileServerFileDto fileDto, String data) throws ServerCallFailedException {
      FileServerFile file = (FileServerFile) dtoService.loadPoso(fileDto);
      file.setData(data.getBytes());
      fileService.merge(file);
   }

   @Override
   protected boolean allowDuplicateNode(AbstractFileServerNode realNode) {
      return true;
   }

   @SecurityChecked(argumentVerification = {
         @ArgumentVerification(name = "node", isDto = true, verify = @RightsVerification(rights = Read.class)) })
   @Override
   @Transactional(rollbackOn = Exception.class)
   public String loadFileDataAsString(@Named("node") FileServerFileDto fileDto) {
      FileServerFile file = (FileServerFile) dtoService.loadPoso(fileDto);
      if (null == file.getData())
         return "";
      return new String(file.getData());
   }

   @Override
   @SecurityChecked(genericTargetVerification = {
         @GenericTargetVerification(target = ExportSecurityTarget.class, verify = @RightsVerification(rights = Execute.class)) })
   @Transactional(rollbackOn = { Exception.class })
   public void quickExport(AbstractFileServerNodeDto nodeDto) throws ServerCallFailedException {
      AbstractFileServerNode node = (AbstractFileServerNode) dtoService.loadPoso(nodeDto);

      /* export report */
      String exportXML = exportHelper.get().export(node, true, FileServerExporter.EXPORTER_NAME, false, false);

      httpExportServiceProvider.get().storeExport(exportXML, node.getName());
   }
   
   

   @Override
   public String loadResult() throws ServerCallFailedException {
      return httpExportServiceProvider.get().getAndRemoveStoredExport();
   }

   @SecurityChecked(argumentVerification = {
         @ArgumentVerification(name = "parentNode", isDto = true, verify = @RightsVerification(actions = InsertAction.class)) })
   @Override
   @Transactional(rollbackOn = { Exception.class })
   public List<FileServerFileDto> uploadFiles(@Named("parentNode") FileServerFolderDto folderDto,
         List<FileToUpload> files) throws ServerCallFailedException {
      List<FileServerFileDto> dtoFiles = new ArrayList<FileServerFileDto>();

      FileServerFolder folder = (FileServerFolder) dtoService.loadPoso(folderDto);

      for (FileToUpload uFile : files) {
         FileServerFile file = fileService.getFileFromUploadFile(uFile);
         file.setKey(keyGeneratorService.generateDefaultKey());
         folder.addChild(file);

         fileService.persist(file);

         dtoFiles.add((FileServerFileDto) dtoService.createDto(file));
      }

      fileService.merge(folder);

      return dtoFiles;
   }

   @SecurityChecked(argumentVerification = {
         @ArgumentVerification(name = "parentNode", isDto = true, verify = @RightsVerification(actions = InsertAction.class)) })
   @Override
   @Transactional(rollbackOn = { Exception.class })
   public List<AbstractFileServerNodeDto> uploadAndExtract(@Named("parentNode") FileServerFolderDto folderDto,
         FileToUpload fileToUpload) throws ServerCallFailedException {
      List<AbstractFileServerNodeDto> dtoFiles = new ArrayList<AbstractFileServerNodeDto>();

      FileServerFolder folder = (FileServerFolder) dtoService.loadPoso(folderDto);

      if (null != fileToUpload) {
         FileServerFile archive = fileService.getFileFromUploadFile(fileToUpload);

         /* create folder to extract into */
         FileServerFolder base = new FileServerFolder();
         String name = archive.getName();
         base.setName(name.endsWith(".zip") ? name.substring(0, name.length() - 4) : name);
         folder.addChild(base);
         fileService.persist(base);
         dtoFiles.add((AbstractFileServerNodeDto) dtoService.createListDto(base));

         base.addChild(archive);

         ZipExtractionConfig zec = extractConfigFactory.create(base);

         try {
            zipUtilsServiceProvider.get().extractZip(archive.getData(), zec);

            /* remove zip */
            base.removeChild(archive);
         } catch (IOException e) {
            throw new ServerCallFailedException(e);
         }
      }

      fileService.merge(folder);

      return dtoFiles;
   }

   @Override
   protected void nodeCloned(AbstractFileServerNode clonedNode, AbstractFileServerNode realNode) {
      if (!(clonedNode instanceof FileServerFile))
         throw new IllegalArgumentException();
      FileServerFile clone = (FileServerFile) clonedNode;
      FileServerFile real = (FileServerFile) realNode;

      Closure getAllNodes = new Closure(null) {
         public List<AbstractFileServerNode> doCall() {
            return realNode.getParent().getChildren();
         }
      };
  
      clone.setName(clone.getName() == null
            ? keyGeneratorService.getNextCopyNameFileServerFile("", getAllNodes)
            : keyGeneratorService.getNextCopyNameFileServerFile(clone.getName(), getAllNodes));
      clone.setKey(keyGeneratorService.getNextCopyKey(real.getKey(), fileService));
   }
   
   @SecurityChecked(
         argumentVerification = {
               @ArgumentVerification(
                     name = "node", 
                     isDto = true, 
                     verify = @RightsVerification(
                           rights = Write.class
                     )
               ) 
         }
   )
   @Override
   @Transactional(rollbackOn = { Exception.class })
   public AbstractNodeDto updateNode(@Named("node") AbstractNodeDto node, Dto state) throws ServerCallFailedException {
      /* check if there already is a file with the same key */
      if (node instanceof FileServerFileDto) {
         String key = ((FileServerFileDto) node).getKey();
         if (null != key && !"".equals(key.trim())) {
            try {
               long id = fileService.getFileIdFromKey(((FileServerFileDto) node).getKey());
               if (id != node.getId())
                  throw new ExpectedException("There is already a file with the same key: " + id);
               /*
                * if the file id is the same as the id of the file to be changed do nothing
                * because this is ok
                */
            } catch (NoResultException e) {
               /* do nothing because this is good */
            }
         }
      }
      return super.updateNode(node, state);
   }
   
   @Override
   protected void doSetInitialProperties(AbstractFileServerNode inserted) {
      if (inserted instanceof FileServerFile) {
         ((FileServerFile)inserted).setKey(keyGeneratorService.generateDefaultKey(fileService));
      }
   }

}
