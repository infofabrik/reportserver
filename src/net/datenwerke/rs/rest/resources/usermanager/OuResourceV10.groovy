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
import net.datenwerke.rs.rest.objects.usermanager.RestOrganisationalUnitInfo
import net.datenwerke.rs.rest.objects.usermanager.RestUser
import net.datenwerke.security.service.usermanager.UserManagerService
import net.datenwerke.security.service.usermanager.entities.OrganisationalUnit

class OuResourceV10 extends UserManagerResourceV10{

   @Inject
   public OuResourceV10(Provider<UserManagerService> userManagerServiceProvider) {
      super(userManagerServiceProvider);
   }
   
   
   @GET
   @Produces(MediaType.APPLICATION_JSON)
   @Path("/ous")
   public Response getOrganisationalUnits() {
      List<RestAbstractNode> result = new ArrayList<>()
      def allOUs = userManagerServiceProvider.get().getAllOUs()
      allOUs.each{result.add(RestOrganisationalUnitInfo.fromOrganisationalUnit(it))}
      return Response.ok().entity(result).build()
   }
   
   @GET
   @Produces(MediaType.APPLICATION_JSON)
   @Path("/ous/{id}")
   public Response getOrganisationalUnit(@PathParam("id") long id) {
      RestAbstractNode result
      def ouSet = userManagerServiceProvider.get().getOUs(id as List)
      result = RestOrganisationalUnitInfo.fromOrganisationalUnit(ouSet.getAt(0))
      return Response.ok().entity(result).build()
   }
   
   @GET
   @Produces(MediaType.APPLICATION_JSON)
   @Path("/ous/{id}/users")
   public Response getgetOrganisationalUnitUsers(@PathParam("id") long id) {
      List<RestAbstractNode> result
      def ouSet = userManagerServiceProvider.get().getOUs(id as List)
      OrganisationalUnit ou = ouSet.getAt(0)
      def userSet = userManagerServiceProvider.get().getAllTransitiveUsers(ou)
      result = userSet.collect{RestUser.fromUser(it)}
      return Response.ok().entity(result).build()
   }
}
