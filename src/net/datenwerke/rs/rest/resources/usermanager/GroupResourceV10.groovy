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
import net.datenwerke.rs.rest.objects.usermanager.RestGroup
import net.datenwerke.rs.rest.objects.usermanager.RestUser
import net.datenwerke.security.service.usermanager.UserManagerService

class GroupResourceV10 extends UserManagerResourceV10{

   @Inject
   public GroupResourceV10(Provider<UserManagerService> userManagerServiceProvider) {
      super(userManagerServiceProvider);
      // TODO Auto-generated constructor stub
   }
   
   @GET
   @Produces(MediaType.APPLICATION_JSON)
   @Path("/groups")
   public Response getGroups() {
      List<RestAbstractNode> result = new ArrayList<>()
      def allGroups = userManagerServiceProvider.get().getAllGroups()
      allGroups.each{result.add(RestGroup.fromGroup(it))}
      return Response.ok().entity(result).build()
   }
   
   @GET
   @Produces(MediaType.APPLICATION_JSON)
   @Path("/groups/{id}")
   public Response getGroup(@PathParam("id") long id) {
      RestAbstractNode result
      def groupSet = userManagerServiceProvider.get().getGroups(id as List)
      result = RestGroup.fromGroup(groupSet.getAt(0))
      return Response.ok().entity(result).build()
   }
   
   @GET
   @Produces(MediaType.APPLICATION_JSON)
   @Path("/groups/{id}/users")
   public Response getGroupUsers(@PathParam("id") long id) {
      List<RestAbstractNode> result
      def groupSet = userManagerServiceProvider.get().getGroups(id as List)
      def users = userManagerServiceProvider.get().getAllTransitiveUsers(groupSet.getAt(0))
      result = users.collect{RestUser.fromUser(it)}
      return Response.ok().entity(result).build()
   }
}
