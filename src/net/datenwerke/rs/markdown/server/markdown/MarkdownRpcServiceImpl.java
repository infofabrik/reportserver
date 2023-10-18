package net.datenwerke.rs.markdown.server.markdown;

import java.io.IOException;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.inject.name.Named;

import net.datenwerke.gxtdto.server.dtomanager.DtoService;
import net.datenwerke.rs.fileserver.client.fileserver.dto.FileServerFileDto;
import net.datenwerke.rs.fileserver.service.fileserver.entities.FileServerFile;
import net.datenwerke.rs.markdown.client.markdown.MarkdownUiModule;
import net.datenwerke.rs.markdown.client.markdown.rpc.MarkdownRpcService;
import net.datenwerke.rs.markdown.service.markdown.MarkdownService;
import net.datenwerke.security.server.SecuredRemoteServiceServlet;
import net.datenwerke.security.service.security.annotation.ArgumentVerification;
import net.datenwerke.security.service.security.annotation.RightsVerification;
import net.datenwerke.security.service.security.annotation.SecurityChecked;
import net.datenwerke.security.service.security.rights.Read;

@Singleton
public class MarkdownRpcServiceImpl extends SecuredRemoteServiceServlet implements MarkdownRpcService {

   private static final long serialVersionUID = -2085463673107689690L;
   private final MarkdownService mdService;
   private final DtoService dtoService;

   @Inject
   public MarkdownRpcServiceImpl(
         MarkdownService mdService, 
         DtoService dtoService
         ) {
      this.mdService = mdService;
      this.dtoService = dtoService;

   }
  
   @SecurityChecked(argumentVerification = {
         @ArgumentVerification(name = "node", isDto = true, verify = @RightsVerification(rights = Read.class)) })  
   @Override
   public String loadMarkdownAsHtml(@Named("node") FileServerFileDto fileDto) {
      FileServerFile file = (FileServerFile) dtoService.loadPoso(fileDto);
      if (null == file.getData())
         throw new IllegalArgumentException("No data contained in file");
      if(!MarkdownUiModule.MIME_TYPE.equals(file.getContentType())){
         throw new IllegalArgumentException("Wrong content type: '" + MarkdownUiModule.MIME_TYPE + "' required");
      }
      String dataString = new String(file.getData());
      try {
         return mdService.renderHtml(dataString);
      } catch (IOException e) {
         return e.toString();
      }
   }   
}
