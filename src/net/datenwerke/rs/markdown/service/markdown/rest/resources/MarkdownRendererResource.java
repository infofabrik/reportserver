package net.datenwerke.rs.markdown.service.markdown.rest.resources;

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

import net.datenwerke.rs.fileserver.service.fileserver.entities.FileServerFile;
import net.datenwerke.rs.markdown.service.markdown.MarkdownService;
import net.datenwerke.rs.rest.resources.RsRestResource;
import net.datenwerke.rs.rest.service.rest.annotations.RestAuthentication;
import net.datenwerke.rs.terminal.service.terminal.TerminalService;
import net.datenwerke.rs.terminal.service.terminal.TerminalSession;
import net.datenwerke.security.service.security.SecurityService;
import net.datenwerke.security.service.security.rights.Read;

@Path("/markdown-renderer")
@RestAuthentication
public class MarkdownRendererResource extends RsRestResource {

   private final Logger logger = LoggerFactory.getLogger(getClass().getName());

   private final Provider<MarkdownService> mdServiceProvider;
   private final Provider<TerminalService> terminalServiceProvider;
   private final Provider<TerminalSession> terminalSessionProvider;
   private final Provider<SecurityService> securityServiceProvider;

   @Inject
   public MarkdownRendererResource(
         Provider<MarkdownService> mdServiceProvider,
         Provider<TerminalService> terminalServiceProvider,
         Provider<TerminalSession> terminalSessionProvider,
         Provider<SecurityService> securityServiceProvider
         ) {
      this.mdServiceProvider = mdServiceProvider;
      this.terminalServiceProvider = terminalServiceProvider;
      this.terminalSessionProvider = terminalSessionProvider;
      this.securityServiceProvider = securityServiceProvider;
   }

   @GET
   @Produces(MediaType.TEXT_PLAIN)
   public Response render(@QueryParam("path") String mdPath,
         @DefaultValue("false") @QueryParam("wrap_html") Boolean wrapHtml) {
      if (null == mdPath) 
         return Response.status(Status.INTERNAL_SERVER_ERROR).build();
      
      try {
         final FileServerFile file = terminalServiceProvider.get().getSingleObjectOfTypeByQuery(FileServerFile.class,
               mdPath, terminalSessionProvider.get(), Read.class);

         if (!securityServiceProvider.get().checkRights(file, Read.class))
            return Response.status(Status.UNAUTHORIZED).build();

         String dot = new String(file.getData());

         return generateResponse(dot, wrapHtml);
      } catch (Exception e) {
         logger.error(ExceptionUtils.getRootCauseMessage(e), e);
         return Response.status(Status.INTERNAL_SERVER_ERROR.getStatusCode(), ExceptionUtils.getRootCauseMessage(e))
               .build();
      }
   }
   
   @POST
   @Consumes(MediaType.TEXT_PLAIN)
   @Produces(MediaType.TEXT_PLAIN)
   public Response renderExternal(String mdString, @DefaultValue("false") @QueryParam("wrap_html") Boolean wrapHtml) {
      try {
         return generateResponse(mdString, wrapHtml);
      } catch (Exception e) {
         logger.error(ExceptionUtils.getRootCauseMessage(e), e);
         return Response.status(Status.INTERNAL_SERVER_ERROR.getStatusCode(), ExceptionUtils.getRootCauseMessage(e))
               .build();
      }
   }
   

   
   private Response generateResponse(String md, Boolean wrapHtml) throws IOException {
      try {
         ContentDisposition contentDisposition = ContentDisposition.type("inline").fileName("data.html")
               .creationDate(new Date()).build();
         String mdStr = mdServiceProvider.get().renderHtml(md);
         if (wrapHtml) {
            mdStr = new StringBuilder()
                  .append("<html>\n")
                  .append("<head></head>\n")
                  .append("<body>\n")
                  .append(mdStr)
                  .append("</body>")
                  .toString();
         }
         return Response.status(Status.OK).type("text/html").entity(mdStr)
               .header("Content-Disposition", contentDisposition).build();
      } catch (Exception e) {
         logger.error(ExceptionUtils.getRootCauseMessage(e), e);
         return Response.status(Status.INTERNAL_SERVER_ERROR.getStatusCode(), ExceptionUtils.getRootCauseMessage(e))
               .build();
      }
   }
}
