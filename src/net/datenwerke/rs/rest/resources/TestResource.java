package net.datenwerke.rs.rest.resources;

import static net.datenwerke.rs.remotersrestserver.service.remotersrestserver.RemoteRsRestServerModule.TEST_RS_VERSION;
import static net.datenwerke.rs.remotersrestserver.service.remotersrestserver.RemoteRsRestServerModule.TEST_RS_MESSAGE;
import static net.datenwerke.rs.remotersrestserver.service.remotersrestserver.RemoteRsRestServerModule.TEST_RS_DATE;
import static net.datenwerke.rs.remotersrestserver.service.remotersrestserver.RemoteRsRestServerModule.TEST_RS_MESSAGE_CONTENT;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import net.datenwerke.rs.adminutils.service.systemconsole.generalinfo.GeneralInfoService;
import net.datenwerke.rs.rest.service.rest.annotations.RestAuthentication;
import net.datenwerke.rs.utils.misc.DateUtils;

@Path("/test")
@RestAuthentication
public class TestResource extends RsRestResource {

   private final Provider<GeneralInfoService> generalInfoServiceProvider;
   
   @Inject
   public TestResource(
         Provider<GeneralInfoService> generalInfoServiceProvider
         ) {
      this.generalInfoServiceProvider = generalInfoServiceProvider;
   }
   
   @GET
   @Produces(MediaType.APPLICATION_JSON)
   public Response test() {
      Map<String,String> results =  Stream.of(new String[][] {
         { TEST_RS_VERSION, generalInfoServiceProvider.get().getRsVersion() }, 
         { TEST_RS_MESSAGE, TEST_RS_MESSAGE_CONTENT }, 
         { TEST_RS_DATE, DateUtils.formatCurrentDate() }, 
       }).collect(Collectors.toMap(data -> data[0], data -> data[1]));
      return Response.ok().entity(results).build();
   }
   
}
