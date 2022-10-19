package net.datenwerke.rs.rest.resources;

import static java.util.stream.Collectors.toList;

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

import net.datenwerke.rs.rest.objects.usermanager.RestGroup;
import net.datenwerke.rs.rest.objects.usermanager.RestOrganisationalUnit;
import net.datenwerke.rs.rest.objects.usermanager.RestOrganisationalUnitInfo;
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
         
         if (!(next instanceof OrganisationalUnit)) {
            if (next instanceof Group) {
               RestGroup restGroup = RestGroup.fromGroup((Group)next);
               return Response.ok().entity(restGroup).build();
            } else if (next instanceof User) {
               RestUser restUser = RestUser.fromUser((User)next);
               return Response.ok().entity(restUser).build();
            }
         }
         
         restLast = RestOrganisationalUnit.fromOrganisationalUnit((OrganisationalUnit)next);
         List<AbstractUserManagerNode> children = next.getChildren();
         
         for (AbstractUserManagerNode child: children) {
            if (child instanceof User) {
               RestUser restUser = RestUser.fromUser((User)child);
               restLast.getChildren().add(restUser);
            } else if (child instanceof OrganisationalUnit) {
               RestOrganisationalUnitInfo restOu = RestOrganisationalUnitInfo
                     .fromOrganisationalUnit((OrganisationalUnit) child);
               restLast.getChildren().add(restOu);
            } else if (child instanceof Group) {
               RestGroup restGroup = RestGroup.fromGroup((Group)child);
               restLast.getChildren().add(restGroup);
            }
         }
      }
      
      return Response.ok().entity(restLast).build();
   }

}
