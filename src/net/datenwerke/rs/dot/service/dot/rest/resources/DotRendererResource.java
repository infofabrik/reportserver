package net.datenwerke.rs.dot.service.dot.rest.resources;

import java.util.Date;
import java.util.Optional;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.glassfish.jersey.media.multipart.ContentDisposition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.datenwerke.rs.base.service.renderer.TextFormat;
import net.datenwerke.rs.dot.service.dot.DotService;
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
   public Response render(@QueryParam("path") String dotPath, @DefaultValue("1200") @QueryParam("width") int width, @DefaultValue("false") @QueryParam("wrap_html") Boolean wrapHtml) {
      if (null == dotPath) 
         return Response.status(Status.INTERNAL_SERVER_ERROR).build();
      
      try {
         final FileServerFile file = terminalServiceProvider.get().getSingleObjectOfTypeByQuery(FileServerFile.class,
               dotPath, terminalSessionProvider.get(), Read.class);

         if (!securityServiceProvider.get().checkRights(file, Read.class))
            return Response.status(Status.UNAUTHORIZED).build();

         String dot = new String(file.getData());

         return generateResponse(width, dot, wrapHtml);
      } catch (Exception e) {
         logger.error(ExceptionUtils.getRootCauseMessage(e), e);
         return Response.status(Status.INTERNAL_SERVER_ERROR.getStatusCode(), ExceptionUtils.getRootCauseMessage(e))
               .build();
      }
   }
   
   @POST
   @Consumes(MediaType.TEXT_PLAIN)
   public Response renderExternal(String dot, @DefaultValue("1200") @QueryParam("width") int width, @DefaultValue("false") @QueryParam("wrap_html") Boolean wrapHtml) {
      try {
         return generateResponse(width, dot, wrapHtml);
      } catch (Exception e) {
         logger.error(ExceptionUtils.getRootCauseMessage(e), e);
         return Response.status(Status.INTERNAL_SERVER_ERROR.getStatusCode(), ExceptionUtils.getRootCauseMessage(e))
               .build();
      }
   }
   
   private Response generateResponse(int width, String dot, Boolean wrapHtml) throws Exception {
      String contentTypeHeader = "image/svg+xml";
      String fileName = "graph.svg";
      String imageStr = dotServiceProvider.get().render(TextFormat.SVG, dot, Optional.of(width), Optional.empty());
      if (wrapHtml) {
         contentTypeHeader = "text/html";
         fileName = "graph.html";
         imageStr = new StringBuilder()
               .append("<html>\n")
               .append("<head></head>\n")
               .append("<body>\n")
               .append(imageStr)
               .append("</body>")
               .toString();
      }
      ContentDisposition contentDisposition = ContentDisposition.type("inline").fileName(fileName)
            .creationDate(new Date()).build();
      return Response.status(Status.OK).type(contentTypeHeader).entity(imageStr)
            .header("Content-Disposition", contentDisposition).build();
   }
}
