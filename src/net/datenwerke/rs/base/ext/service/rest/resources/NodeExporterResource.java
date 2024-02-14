package net.datenwerke.rs.base.ext.service.rest.resources;

import java.util.Optional;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.ws.rs.GET;
import javax.ws.rs.MatrixParam;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.datenwerke.eximport.ExportService;
import net.datenwerke.eximport.ex.ExportConfig;
import net.datenwerke.rs.adminutils.service.systemconsole.generalinfo.GeneralInfoService;
import net.datenwerke.rs.eximport.client.eximport.ex.dto.ExportedNodeDto;
import net.datenwerke.rs.eximport.service.genrights.ExportSecurityTarget;
import net.datenwerke.rs.rest.resources.RsRestResource;
import net.datenwerke.rs.rest.service.rest.annotations.RestAuthentication;
import net.datenwerke.rs.terminal.service.terminal.TerminalService;
import net.datenwerke.rs.terminal.service.terminal.TerminalSession;
import net.datenwerke.security.service.authenticator.AuthenticatorService;
import net.datenwerke.security.service.security.SecurityService;
import net.datenwerke.security.service.security.rights.Execute;
import net.datenwerke.security.service.security.rights.Read;
import net.datenwerke.treedb.service.treedb.AbstractNode;

@Path("/node-exporter")
@RestAuthentication
public class NodeExporterResource extends RsRestResource {
   
   private final Logger logger = LoggerFactory.getLogger(getClass().getName());

   private final Provider<SecurityService> securityServiceProvider;
   private final Provider<TerminalService> terminalServiceProvider;
   private final Provider<TerminalSession> terminalSessionProvider;
   private final Provider<ExportService> exportServiceProvider;
   private final Provider<GeneralInfoService> generalInfoServiceProvider;
   private final Provider<AuthenticatorService> authenticatorServiceProvider;
   
   @Inject
   public NodeExporterResource(
         Provider<SecurityService> securityServiceProvider,
         Provider<TerminalService> terminalServiceProvider,
         Provider<TerminalSession> terminalSessionProvider,
         Provider<ExportService> exportServiceProvider,
         Provider<GeneralInfoService> generalInfoServiceProvider,
         Provider<AuthenticatorService> authenticatorServiceProvider
         ) {
      this.securityServiceProvider = securityServiceProvider;
      this.terminalServiceProvider = terminalServiceProvider;
      this.terminalSessionProvider = terminalSessionProvider;
      this.exportServiceProvider = exportServiceProvider;
      this.generalInfoServiceProvider = generalInfoServiceProvider;
      this.authenticatorServiceProvider = authenticatorServiceProvider;
   }
   
   @GET
   @Path("/{path:.+}")
   @Produces(MediaType.APPLICATION_JSON)
   public Response getExportedNode(@PathParam("path") String path,
         @MatrixParam("includeVariants") final boolean includeVariants, @MatrixParam("flatten") final boolean flatten) {
      if (!securityServiceProvider.get().checkRights(ExportSecurityTarget.class, Execute.class)) 
         return Response.status(Status.UNAUTHORIZED).build();
      
      if (null == path) 
         return Response.status(Status.INTERNAL_SERVER_ERROR).build();
      try {
         final AbstractNode<?> node = terminalServiceProvider.get().getSingleObjectOfTypeByQuery(AbstractNode.class, path,
               terminalSessionProvider.get(), Read.class);
         
         if (!securityServiceProvider.get().checkRights(node, Read.class))
            return Response.status(Status.UNAUTHORIZED).build();
         
         final Optional<ExportConfig> exportConfig = exportServiceProvider.get().configureExport(node, includeVariants, flatten);

         if (!exportConfig.isPresent()) {
            String err = "no exporter found";
            logger.error(err);
            return Response.status(Status.INTERNAL_SERVER_ERROR.getStatusCode(), err).build();
         }
         final String exportXML = exportServiceProvider.get().export(exportConfig.get());
         final ExportedNodeDto exportDto = new ExportedNodeDto();
         final GeneralInfoService generalInfoService = generalInfoServiceProvider.get();
         exportDto.setExportTimeStamp(generalInfoService.getNow());
         exportDto.setNode(node.toString());
         exportDto.setReportServerVersion(generalInfoService.getRsVersion());
         exportDto.setPath(path);
         exportDto.setExport(exportXML);
         exportDto.setExportedBy(authenticatorServiceProvider.get().getCurrentUser().toString());
         
         return Response.ok().entity(exportDto).build();
      } catch (Exception e) {
         logger.error(ExceptionUtils.getRootCauseMessage(e), e);
         return Response.status(Status.INTERNAL_SERVER_ERROR.getStatusCode(), ExceptionUtils.getRootCauseMessage(e)).build();
      }
   }
   
}
