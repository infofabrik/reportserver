package net.datenwerke.rs.dot.service.dot.rest.resources;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.StreamingOutput;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.glassfish.jersey.media.multipart.ContentDisposition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import guru.nidi.graphviz.engine.Format;
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

         ContentDisposition contentDisposition = ContentDisposition.type("inline").fileName("graph.svg")
               .creationDate(new Date()).build();

         return Response.status(Status.OK).type("image/svg+xml").entity(new StreamingOutput() {
            @Override
            public void write(OutputStream output) throws IOException, WebApplicationException {
               byte[] imageBytes = dotServiceProvider.get().render(Format.SVG, dot, width).toByteArray();
               output.write(imageBytes);
               output.flush();
            }
         }).header("Content-Disposition", contentDisposition).build();
      } catch (Exception e) {
         logger.error(ExceptionUtils.getRootCauseMessage(e), e);
         return Response.status(Status.INTERNAL_SERVER_ERROR.getStatusCode(), ExceptionUtils.getRootCauseMessage(e))
               .build();
      }
   }

}
