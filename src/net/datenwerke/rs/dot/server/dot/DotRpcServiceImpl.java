package net.datenwerke.rs.dot.server.dot;

import java.io.IOException;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.inject.name.Named;

import net.datenwerke.gxtdto.server.dtomanager.DtoService;
import net.datenwerke.rs.dot.client.dot.DotUiModule;
import net.datenwerke.rs.dot.client.dot.rpc.DotRpcService;
import net.datenwerke.rs.dot.service.dot.DotService;
import net.datenwerke.rs.dot.service.dot.TextFormat;
import net.datenwerke.rs.fileserver.client.fileserver.dto.FileServerFileDto;
import net.datenwerke.rs.fileserver.service.fileserver.entities.FileServerFile;
import net.datenwerke.security.server.SecuredRemoteServiceServlet;
import net.datenwerke.security.service.security.annotation.ArgumentVerification;
import net.datenwerke.security.service.security.annotation.RightsVerification;
import net.datenwerke.security.service.security.annotation.SecurityChecked;
import net.datenwerke.security.service.security.rights.Read;

@Singleton
public class DotRpcServiceImpl extends SecuredRemoteServiceServlet implements DotRpcService {


   private static final long serialVersionUID = -7588671470961776759L;
   private final DotService dotService;
   private final DtoService dtoService;

   @Inject
   public DotRpcServiceImpl(
         DotService dotService, 
         DtoService dtoService
         ) {
      this.dotService = dotService;
      this.dtoService = dtoService;

   }
  
   @SecurityChecked(argumentVerification = {
         @ArgumentVerification(name = "node", isDto = true, verify = @RightsVerification(rights = Read.class)) })  
   @Override
   public String loadDotAsSVG(@Named("node") FileServerFileDto fileDto) {
      FileServerFile file = (FileServerFile) dtoService.loadPoso(fileDto);
      if (null == file.getData())
         throw new IllegalArgumentException("No data contained in file");
      if(!DotUiModule.MIME_TYPE.equals(file.getContentType())){
         throw new IllegalArgumentException("Wrong content type: '" + DotUiModule.MIME_TYPE + "' required");
      }
      String dataString = new String(file.getData());
      try {
         return dotService.render(TextFormat.SVG, dataString, 1400);
      } catch (IOException e) {
         return e.toString();
      }
   }
   
}
