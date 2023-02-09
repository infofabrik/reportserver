package net.datenwerke.rs.base.ext.service.rest.resources;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.ws.rs.GET;
import javax.ws.rs.MatrixParam;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.commons.lang3.exception.ExceptionUtils;

import net.datenwerke.eximport.ExportService;
import net.datenwerke.eximport.ex.ExportConfig;
import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.adminutils.service.systemconsole.generalinfo.GeneralInfoService;
import net.datenwerke.rs.base.ext.service.ExportOptions;
import net.datenwerke.rs.base.ext.service.hooks.ExportConfigHook;
import net.datenwerke.rs.base.ext.service.reportmanager.ReportExportOptions;
import net.datenwerke.rs.core.service.reportmanager.entities.AbstractReportManagerNode;
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

   private final Provider<SecurityService> securityServiceProvider;
   private final Provider<HookHandlerService> hookHandlerServiceProvider;
   private final Provider<TerminalService> terminalServiceProvider;
   private final Provider<TerminalSession> terminalSessionProvider;
   private final Provider<ExportService> exportServiceProvider;
   private final Provider<GeneralInfoService> generalInfoServiceProvider;
   private final Provider<AuthenticatorService> authenticatorServiceProvider;
   
   @Inject
   public NodeExporterResource(
         Provider<SecurityService> securityServiceProvider,
         Provider<HookHandlerService> hookHandlerServiceProvider,
         Provider<TerminalService> terminalServiceProvider,
         Provider<TerminalSession> terminalSessionProvider,
         Provider<ExportService> exportServiceProvider,
         Provider<GeneralInfoService> generalInfoServiceProvider,
         Provider<AuthenticatorService> authenticatorServiceProvider
         ) {
      this.securityServiceProvider = securityServiceProvider;
      this.hookHandlerServiceProvider = hookHandlerServiceProvider;
      this.terminalServiceProvider = terminalServiceProvider;
      this.terminalSessionProvider = terminalSessionProvider;
      this.exportServiceProvider = exportServiceProvider;
      this.generalInfoServiceProvider = generalInfoServiceProvider;
      this.authenticatorServiceProvider = authenticatorServiceProvider;
   }
   
   @GET
   @Path("/{path:.+}")
   @Produces(MediaType.APPLICATION_JSON)
   public Response getExportedNode(@PathParam("path") String path, @MatrixParam("includeVariants") final boolean includeVariants) {
      securityServiceProvider.get().assertRights(ExportSecurityTarget.class, Execute.class);
      
      if (null == path) 
         throw new IllegalArgumentException("path cannot be null");
      try {
         final AbstractNode node = terminalServiceProvider.get().getSingleObjectOfTypeByQuery(AbstractNode.class, path,
               terminalSessionProvider.get(), Read.class);
         System.out.println("Node found: " + node);
         System.out.println("Exporting " + path);
         System.out.println("Include variants: " + includeVariants);
         
         final ExportConfig exportConfig = hookHandlerServiceProvider.get().getHookers(ExportConfigHook.class)
            .stream()
            .filter(hooker -> hooker.consumes(node))
            .map(hooker -> { 
               ExportOptions exportOptions = null;
               if (node instanceof AbstractReportManagerNode) {
                  exportOptions = new ReportExportOptions() {
                     @Override
                     public boolean includeVariants() {
                        return includeVariants;
                     }
                  };
               } else {
                  exportOptions = new ExportOptions() {};
               }
               return hooker.configure(node, exportOptions);
               })
            .findAny()
            .orElseThrow(() -> new IllegalStateException("No ExportConfigHook hooker configured"));
         
         final String exportXML = exportServiceProvider.get().export(exportConfig);
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
         //TODO: nicht immer exception werfen, sondern Response.Status(), etc
         throw new RuntimeException(ExceptionUtils.getRootCauseMessage(e), e);
      }
   }
   
}
