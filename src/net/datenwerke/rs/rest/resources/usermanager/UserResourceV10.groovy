package net.datenwerke.rs.rest.resources.usermanager

import javax.inject.Provider
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.PathParam
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response

import com.google.inject.Inject

import net.datenwerke.rs.rest.objects.RestAbstractNode
import net.datenwerke.rs.rest.objects.usermanager.RestUser
import net.datenwerke.security.service.usermanager.UserManagerService

class UserResourceV10 extends UserManagerResourceV10{

   @Inject
   public UserResourceV10(Provider<UserManagerService> userManagerServiceProvider) {
      super(userManagerServiceProvider);
   }
   
   @GET
   @Produces(MediaType.APPLICATION_JSON)
   @Path("/users")
   public Response getUsers() {
      List<RestAbstractNode> result = new ArrayList<>()
      def allUsers = userManagerServiceProvider.get().getAllUsers()
      allUsers.each{result.add(RestUser.fromUser(it))}
      return Response.ok().entity(result).build()
   }
   
   @GET
   @Produces(MediaType.APPLICATION_JSON)
   @Path("/users/{id}")
   public Response getUser(@PathParam("id") long id) {
      RestAbstractNode result
      def userSet = userManagerServiceProvider.get().getUsers(id as List)
      result = RestUser.fromUser(userSet.getAt(0))
      return Response.ok().entity(result).build()
   }
}
