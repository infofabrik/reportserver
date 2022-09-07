package net.datenwerke.rs.rest.resources;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.hibernate.proxy.HibernateProxy;

import net.datenwerke.rs.rest.objects.RestAbstractNode;
import net.datenwerke.rs.rest.objects.usermanager.RestGroup;
import net.datenwerke.rs.rest.objects.usermanager.RestOrganisationalUnit;
import net.datenwerke.rs.rest.objects.usermanager.RestUser;
import net.datenwerke.rs.rest.service.rest.annotations.RestAuthentication;
import net.datenwerke.security.service.usermanager.UserManagerService;
import net.datenwerke.security.service.usermanager.entities.AbstractUserManagerNode;
import net.datenwerke.security.service.usermanager.entities.Group;
import net.datenwerke.security.service.usermanager.entities.OrganisationalUnit;
import net.datenwerke.security.service.usermanager.entities.User;

@Path("/users")
@RestAuthentication
public class UserResource extends RsRestResource {
   
   @Context
   private HttpServletRequest request;
   
   private final Provider<UserManagerService> userManagerServiceProvider;
   
   @Inject
   public UserResource( 
         Provider<UserManagerService> userManagerServiceProvider
         ) {
      this.userManagerServiceProvider = userManagerServiceProvider;
   }
   
   @GET
   @Produces(MediaType.APPLICATION_JSON)
   @Path("/{path:.*}")
   public Response getUsers(@PathParam("path") String path) {
      
      List<RestAbstractNode> result = new ArrayList<>();
      
      final UserManagerService userManagerService = userManagerServiceProvider.get();
      
      String[] pathNodes = path.split("/");
      AbstractUserManagerNode next = userManagerService.getRoots().get(0);
      for (int i = 0; i< pathNodes.length; i++) {
         String pathNode = pathNodes[i];
         boolean last = i == pathNodes.length-1;
         
         if (!pathNode.equals("User Root")) {
            List<AbstractUserManagerNode> nodesWithName = userManagerService.getChildrenWithName(next, pathNode);
            if (1 != nodesWithName.size()) 
               return Response.status(Status.NOT_FOUND).build();
            
            next = nodesWithName.get(0);
         }
         
         if (next instanceof HibernateProxy)
            next = (AbstractUserManagerNode) ((HibernateProxy) next).getHibernateLazyInitializer()
                  .getImplementation();
         
         if (!(next instanceof OrganisationalUnit) && !last)
            return Response.status(Status.NOT_FOUND).build();
         
         if (!last)
            continue;
         
         if (!(next instanceof OrganisationalUnit))
            return Response.ok().entity("TODO: !OrganisationalUnit").build();
         
         List<AbstractUserManagerNode> children = next.getChildren();
         
         for (AbstractUserManagerNode child: children) {
            if (child instanceof User) {
               RestUser restUser = RestUser.fromUser((User)child);
               result.add(restUser);
            } else if (child instanceof OrganisationalUnit) {
               RestOrganisationalUnit restOu = RestOrganisationalUnit
                     .fromOrganisationalUnit((OrganisationalUnit) child);
               result.add(restOu);
            } else if (child instanceof Group) {
               RestGroup restGroup = RestGroup.fromGroup((Group)child);
               result.add(restGroup);
            }
         }
      }
      
      return Response.ok().entity(result).build();
   }

}
