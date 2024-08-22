/* Copyright (C) OSBI Ltd - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by OSBI LTD, 2014
 */

package net.datenwerke.rs.saiku.server.rest.resources;

//import org.saiku.service.license.ILicenseUtils;
//import org.saiku.database.Database;
//import org.saiku.service.license.Base64Coder;
//import org.saiku.service.user.UserService;
//import org.saiku.web.rest.objects.UserList;
//
//import com.qmino.miredot.annotations.ReturnType;
//
//import org.springframework.stereotype.Component;
//
//import java.io.ByteArrayOutputStream;
//import java.io.IOException;
import java.io.InputStream;
//import java.io.ObjectInputStream;
//import java.sql.SQLException;
//import java.util.ArrayList;
import java.util.List;
//
//import javax.jcr.RepositoryException;
//import javax.ws.rs.*;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.saiku.license.SaikuLicense;

import net.datenwerke.rs.saiku.server.rest.objects.UserList;

/**
 * Saiku license information resource.
 *
 * @since 3.0
 * @author tbarber
 */
//@Component
@Path("/saiku/api/license")
public class License {

//  private ILicenseUtils licenseUtils;
//  private UserService userService;
//
//  public ILicenseUtils getLicenseUtils() {
//    return licenseUtils;
//  }
//
//  public void setLicenseUtils(ILicenseUtils licenseUtils) {
//    this.licenseUtils = licenseUtils;
//  }
//
//  private Database databaseManager;
//
//  public Database getDatabaseManager() {
//    return databaseManager;
//  }
//
//  public void setDatabaseManager(Database databaseManager) {
//    this.databaseManager = databaseManager;
//  }
//
//  public void setUserService(UserService us) {
//    userService = us;
//  }

  /**
   * Get the saiku
   * @summary Get the Saiku License installed on the current server
   * @return A response containing a license object.
   */
  @GET
  @Produces({ "application/json" })
  public Response getLicense() {
//    try {
//      return Response.ok().entity(licenseUtils.getLicense()).build();
//    } catch (IOException | RepositoryException | ClassNotFoundException e) {
//      e.printStackTrace();
//    }
//    return Response.serverError().build();
	  return Response.ok().entity(new SaikuLicense()).build();
  }

  private static final int SIZE = 2048;


  /**
   * Upload a new license to the Saiku server.
   * @summary Upload a new license
   * @param is A license encapsulated in an input stream
   * @return An acknowledgement as to whether the server installation was successful.
   */
  @POST
  @Consumes("application/x-java-serialized-object")
  @Produces("text/plain")
//  @ReturnType("java.lang.String")
  public Response saveLicense(InputStream is) {
//    ObjectInputStream si = null;
//    byte[] sig;
//    byte[] data = null;
//    try {
//      si = new ObjectInputStream(is);
//    } catch (IOException e) {
//      e.printStackTrace();
//    }
//
//    try {
//      int sigLength = si.readInt();
//      sig = new byte[sigLength];
//      si.read(sig);
//
//      ByteArrayOutputStream dataStream = new ByteArrayOutputStream();
//      byte[] buf = new byte[SIZE];
//      int len;
//      while ((len = si.read(buf)) != -1) {
//        dataStream.write(buf, 0, len);
//      }
//      dataStream.flush();
//      data = dataStream.toByteArray();
//      dataStream.close();
//    } catch (IOException e) {
//      e.printStackTrace();
//    } finally {
//      try {
//        si.close();
//      } catch (IOException e) {
//        e.printStackTrace();
//      }
//    }
//
//
//    getLicenseUtils().setLicense(new String(Base64Coder.encode(data)));
//
//    return Response.ok("License Upload Successful").build();
    throw new RuntimeException("not implemented");
  }

  /**
   * Validate the license installed on the server.
   * @summary License validation
   * @return A response indicating whether the operation was successful.
   */
  @GET
  @Path("/validate")
  @Produces({ "text/plain" })
//  @ReturnType("java.lang.String")
  public Response validateLicense() {
//    if(!userService.isAdmin()){
//      return Response.status(Response.Status.FORBIDDEN).build();
//    }
//    try {
//      licenseUtils.validateLicense();
//    } catch (IOException e) {
//      e.printStackTrace();
//      return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
//                     .entity(e.getLocalizedMessage()).build();
//    } catch (ClassNotFoundException e) {
//      e.printStackTrace();
//      return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
//                     .entity(e.getLocalizedMessage()).build();
//    } catch (LicenseException e) {
//      return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
//                     .entity(e.getLocalizedMessage()).build();
//    } catch (RepositoryException e) {
//      return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
//                     .entity("Could not find license file").build();
//    } catch (Exception e) {
//      e.printStackTrace();
//    }
    return Response.ok().entity("Valid License").build();


  }



  /**
   * Get the current user list from the server.
   * @summary Get the user list
   * @return A list of users.
   */
  @GET
  @Path("/users")
  @Produces({"application/json"})
//  @ReturnType("java.util.ArrayList<UserList>")
  public Response getUserlist(){
//    if(!userService.isAdmin()){
//      return Response.status(Response.Status.FORBIDDEN).build();
//    }
//    try {
//      List<String> l = getAuthUsers();
//      if(l!=null) {
//        List<UserList> ul = new ArrayList();
//        int i = 0;
//        for (String l2 : l) {
//          ul.add(new UserList(l2, i));
//          i++;
//        }
//        return Response.ok().entity(ul).build();
//      }
//    } catch (SQLException e) {
//      e.printStackTrace();
//    }
//    return null;
	  throw new RuntimeException("Not implemented.");
  }

  /**
   * Upload a user list to the server.
   * @summary Upload user list
   * @param l A List of UserList objects
   * @return A response indicating whether the operation was successful.
   */
  @POST
  @Path("/users")
  @Produces({"text/plain"})
  @Consumes({"application/json"})
//  @ReturnType("java.lang.String")
  public Response createUserList(List<UserList> l){
//    try {
//      List<String> l3 = new ArrayList<>();
//      for(UserList l2 : l){
//        l3.add(l2.getName());
//      }
//      addUsers(l3);
//    } catch (SQLException e) {
//      e.printStackTrace();
//    }
//    return Response.ok().entity("List created").build();
	  throw new RuntimeException("Not implemented.");
  }

  /**
   * Update the list of users with new users.
   * @summary Update user list
   * @param l A list of UserList objects
   * @return A response indicating whether the operation was successful.
   */
  @PUT
  @Path("/users")
  @Produces({"text/plain"})
  @Consumes({"application/json"})
//  @ReturnType("java.lang.String")
  public Response updateUserList(List<UserList> l){
//    try {
//      List<String> l3 = new ArrayList<>();
//      for(UserList l2 : l){
//        l3.add(l2.getName());
//      }
//      updateUsers(l3);
//    } catch (SQLException e) {
//      e.printStackTrace();
//    }
//    return Response.ok().entity("List updated").build();
	  
	  throw new RuntimeException("Not implemented.");
  }

  /**
   * Delete the user list from the server.
   * @summary Delete user list.
   * @return A response indicating whether the operation was successful.
   */
  @DELETE
  @Path("/users")
  @Produces({"application/json"})
//  @ReturnType("java.lang.String")
  public Response deleteUserlist(){

//    try {
//      List<String> l = getAuthUsers();
//      List<UserList> ul = new ArrayList<>();
//      int i = 0;
//      for(String l2 : l) {
//        ul.add(new UserList(l2, i));
//        i++;
//      }
//      return Response.ok().entity(ul).build();
//    } catch (SQLException e) {
//      e.printStackTrace();
//    }
//    return null;
	  throw new RuntimeException("Not implemented.");
  }

  /**
   * Get the valid users from the database.
   * @return a list of usernames
   * @throws SQLException
   */
//  private List<String> getAuthUsers() throws SQLException {
//    return databaseManager.getUsers();
//  }

  /**
   * Get the user quota for existing users with no license
   * @return a list of user quota.
   */
  @GET
  @Produces("application/json")
  @Path("/quota")
//  @ReturnType("java.util.List<UserQuota>")
  public Response getUserQuota(){
//    if(!userService.isAdmin()){
//      return Response.status(Response.Status.FORBIDDEN).build();
//    }
//    return Response.ok().entity(100000000).build();
	  throw new RuntimeException("Not implemented.");
  }


//  /**
//   * Add users to the database.
//   * @param l List of usernames
//   * @throws SQLException
//   */
//  public void addUsers(List<String> l) throws SQLException {
//    databaseManager.addUsers(l);
//  }
//
//  /**
//   * Add users to the database.
//   * @param l List of usernames
//   * @throws SQLException
//   */
//  public void updateUsers(List<String> l) throws SQLException {
//    databaseManager.addUsers(l);
//  }

}
