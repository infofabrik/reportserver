package net.datenwerke.rs.rest.resources.usermanager;

import static java.util.stream.Collectors.toList;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.hibernate.proxy.HibernateProxy;

import net.datenwerke.rs.rest.objects.usermanager.RestOrganisationalUnit;
import net.datenwerke.rs.rest.resources.RsRestResource;
import net.datenwerke.rs.rest.service.rest.RestUtilService;
import net.datenwerke.rs.rest.service.rest.annotations.RestAuthentication;
import net.datenwerke.security.service.usermanager.UserManagerService;
import net.datenwerke.security.service.usermanager.entities.AbstractUserManagerNode;
import net.datenwerke.security.service.usermanager.entities.OrganisationalUnit;
import net.datenwerke.security.service.usermanager.entities.User;

@Path("/users")
@RestAuthentication
public class UserResource extends RsRestResource {
   
   private final Provider<UserManagerService> userManagerServiceProvider;
   private final Provider<RestUtilService> restUtilServiceProvider;
   
   @Inject
   public UserResource( 
         Provider<UserManagerService> userManagerServiceProvider,
         Provider<RestUtilService> restUtilServiceProvider
         ) {
      this.userManagerServiceProvider = userManagerServiceProvider;
      this.restUtilServiceProvider = restUtilServiceProvider;
   }
   
   @GET
   @Produces(MediaType.APPLICATION_JSON)
   @Path("/{path:.*}")
   public Response getUsers(@PathParam("path") String path) {
      
      final UserManagerService userManagerService = userManagerServiceProvider.get();
      
      String[] pathNodes = path.split("/");
      AbstractUserManagerNode next = userManagerService.getRoots().get(0);
      RestOrganisationalUnit restLast = null;
      for (int i = 0; i< pathNodes.length; i++) {
         String pathNode = pathNodes[i];
         boolean last = i == pathNodes.length-1;
         
         if (!pathNode.equals("User Root")) {
            List<AbstractUserManagerNode> nodesWithName = userManagerService.getChildrenWithName(next, pathNode);
            if (1 != nodesWithName.size()) {
               // try with username in case it is a user
               List<AbstractUserManagerNode> childrenWithUsername = next.getChildren()
                     .stream()
                     .filter(child -> child instanceof User)
                     .filter(child -> pathNode.equals(((User)child).getUsername()))
                     .collect(toList());
               if (1 != childrenWithUsername.size()) 
                  return Response.status(Status.NOT_FOUND).build();
               else
                  next = childrenWithUsername.get(0);
            } else {
               next = nodesWithName.get(0);
            }
         }
         
         if (next instanceof HibernateProxy)
            next = (AbstractUserManagerNode) ((HibernateProxy) next).getHibernateLazyInitializer()
                  .getImplementation();
         
         if (!(next instanceof OrganisationalUnit) && !last)
            return Response.status(Status.NOT_FOUND).build();
         
         if (!last)
            continue;
         
         if (!(next instanceof OrganisationalUnit)) 
            return Response.ok().entity(restUtilServiceProvider.get().toRestObject(next)).build();
         
         restLast = (RestOrganisationalUnit) restUtilServiceProvider.get().toDetailedRestObject(next);
         
         restLast.getChildren().addAll(
               next.getChildren()
               .stream()
               .map(child -> restUtilServiceProvider.get().toRestObject(child))
               .collect(toList()));
      }
      
      return Response.ok().entity(restLast).build();
   }

}
