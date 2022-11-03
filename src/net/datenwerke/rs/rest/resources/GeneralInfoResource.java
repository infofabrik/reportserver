package net.datenwerke.rs.rest.resources;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import net.datenwerke.rs.adminutils.service.systemconsole.generalinfo.GeneralInfoService;
import net.datenwerke.rs.adminutils.service.systemconsole.genrights.SystemConsoleSecurityTarget;
import net.datenwerke.rs.rest.service.rest.annotations.RestAuthentication;
import net.datenwerke.security.service.security.SecurityService;
import net.datenwerke.security.service.security.rights.Read;

@Path("/general-info")
@RestAuthentication
public class GeneralInfoResource extends RsRestResource {

   private final Provider<GeneralInfoService> generalInfoServiceProvider;
   private final Provider<SecurityService> securityServiceProvider;
   
   @Inject
   public GeneralInfoResource(
         Provider<GeneralInfoService> generalInfoServiceProvider,
         Provider<SecurityService> securityServiceProvider
         ) {
      this.generalInfoServiceProvider = generalInfoServiceProvider;
      this.securityServiceProvider = securityServiceProvider;
   }
   
   @GET
   @Produces(MediaType.APPLICATION_JSON)
   public Response getGeneralInfo() {
      securityServiceProvider.get().assertRights(SystemConsoleSecurityTarget.class, Read.class);
      
      return Response.ok().entity(generalInfoServiceProvider.get().getGeneralInfo()).build();
   }
   
}
