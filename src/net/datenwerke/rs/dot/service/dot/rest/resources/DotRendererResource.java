package net.datenwerke.rs.dot.service.dot.rest.resources;

import java.io.IOException;
import java.util.Date;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.glassfish.jersey.media.multipart.ContentDisposition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.datenwerke.rs.dot.service.dot.DotService;
import net.datenwerke.rs.dot.service.dot.TextFormat;
import net.datenwerke.rs.fileserver.service.fileserver.entities.FileServerFile;
import net.datenwerke.rs.rest.resources.RsRestResource;
import net.datenwerke.rs.rest.service.rest.annotations.RestAuthentication;
import net.datenwerke.rs.terminal.service.terminal.TerminalService;
import net.datenwerke.rs.terminal.service.terminal.TerminalSession;
import net.datenwerke.security.service.security.SecurityService;
import net.datenwerke.security.service.security.rights.Read;

@Path("/dot-renderer")
@RestAuthentication
public class DotRendererResource extends RsRestResource {

   private final Logger logger = LoggerFactory.getLogger(getClass().getName());

   private final Provider<DotService> dotServiceProvider;
   private final Provider<TerminalService> terminalServiceProvider;
   private final Provider<TerminalSession> terminalSessionProvider;
   private final Provider<SecurityService> securityServiceProvider;

   @Inject
   public DotRendererResource(
         Provider<DotService> dotServiceProvider,
         Provider<TerminalService> terminalServiceProvider,
         Provider<TerminalSession> terminalSessionProvider,
         Provider<SecurityService> securityServiceProvider
         ) {
      this.dotServiceProvider = dotServiceProvider;
      this.terminalServiceProvider = terminalServiceProvider;
      this.terminalSessionProvider = terminalSessionProvider;
      this.securityServiceProvider = securityServiceProvider;
   }

   @GET
   @Produces("image/svg+xml")
   public Response render(@QueryParam("path") String dotPath, @DefaultValue("1200") @QueryParam("width") int width) {
      if (null == dotPath) 
         return Response.status(Status.INTERNAL_SERVER_ERROR).build();
      
      try {
         final FileServerFile file = terminalServiceProvider.get().getSingleObjectOfTypeByQuery(FileServerFile.class,
               dotPath, terminalSessionProvider.get(), Read.class);

         if (!securityServiceProvider.get().checkRights(file, Read.class))
            return Response.status(Status.UNAUTHORIZED).build();

         String dot = new String(file.getData());

         return generateResponse(width, dot);
      } catch (Exception e) {
         logger.error(ExceptionUtils.getRootCauseMessage(e), e);
         return Response.status(Status.INTERNAL_SERVER_ERROR.getStatusCode(), ExceptionUtils.getRootCauseMessage(e))
               .build();
      }
   }
   
   @POST
   @Consumes(MediaType.TEXT_PLAIN)
   @Produces("image/svg+xml")
   public Response renderExternal(String dot, @DefaultValue("1200") @QueryParam("width") int width) {
      try {
         return generateResponse(width, dot);
      } catch (IOException e) {
         logger.error(ExceptionUtils.getRootCauseMessage(e), e);
         return Response.status(Status.INTERNAL_SERVER_ERROR.getStatusCode(), ExceptionUtils.getRootCauseMessage(e))
               .build();
      }
   }
   
   private Response generateResponse(int width, String dot) throws IOException {
      ContentDisposition contentDisposition = ContentDisposition.type("inline").fileName("graph.svg")
            .creationDate(new Date()).build();
      String imageStr = dotServiceProvider.get().render(TextFormat.SVG, dot, width);
      return Response.status(Status.OK).type("image/svg+xml").entity(imageStr)
            .header("Content-Disposition", contentDisposition).build();
   }
}
