/*  
 *   Copyright 2012 OSBI Ltd
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 */
package net.datenwerke.rs.saiku.server.rest.resources;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.saiku.repository.IRepositoryObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Provider;

import net.datenwerke.rs.saiku.service.saiku.SaikuSessionContainer;
import net.datenwerke.rs.saiku.service.saiku.entities.SaikuReport;
import net.datenwerke.rs.saiku.service.saiku.entities.SaikuReportVariant;
import net.datenwerke.rs.utils.jpa.EntityUtils;

/**
 * QueryServlet contains all the methods required when manipulating an OLAP
 * Query.
 * 
 * @author Paul Stoellberger
 *
 */
@Path("/saiku/api/{username}/repository")
public class BasicRepositoryResource2 implements Serializable {

   /**
    * 
    */
   private static final long serialVersionUID = 5050853945841568750L;

   private static final Logger log = LoggerFactory.getLogger(BasicRepositoryResource2.class);

   // private ISessionService sessionService;
   //
   // //private Acl acl;
   // private DatasourceService datasourceService;
   // private FileObject repo;
   //
   // public void setDatasourceService(DatasourceService ds) {
   // datasourceService = ds;
   // }
   //
   // public void setPath(String path) throws Exception {
   // FileSystemManager fileSystemManager;
   // try {
   // if (!path.endsWith("" + File.separatorChar)) {
   // path += File.separatorChar;
   // }
   // fileSystemManager = VFS.getManager();
   // FileObject fileObject;
   // fileObject = fileSystemManager.resolveFile(path);
   // if (fileObject == null) {
   // throw new IOException("File cannot be resolved: " + path);
   // }
   // if(!fileObject.exists()) {
   // throw new IOException("File does not exist: " + path);
   // }
   // repo = fileObject;
   // } catch (Exception e) {
   // log.error("Error setting path for repository: " + path, e);
   // }
   // }
   //
   // /*public void setAcl(Acl acl) {
   // this.acl = acl;
   // }*/
   //
   // /**
   // * Sets the sessionService
   // * @summary Set the session service
   // * @param sessionService The session service
   // */
   // public void setSessionService(ISessionService sessionService){
   // this.sessionService = sessionService;
   // }

   private final Provider<SaikuSessionContainer> saikuSessioncontainer;
   private final Provider<EntityManager> entityManagerProvider;
   private final EntityUtils entityUtils;

   @Inject
   public BasicRepositoryResource2(Provider<SaikuSessionContainer> saikuSessioncontainer,
         Provider<EntityManager> entityManagerProvider, EntityUtils entityUtils) {
      this.saikuSessioncontainer = saikuSessioncontainer;
      this.entityManagerProvider = entityManagerProvider;
      this.entityUtils = entityUtils;

      log.info("loaded basicRepositoryResource2");
   }

   /*
    * (non-Javadoc)
    * 
    * @see
    * org.saiku.web.rest.resources.ISaikuRepository#getRepository(java.lang.String,
    * java.lang.String)
    */
   @GET
   @Produces({ "application/json" })
   public List<IRepositoryObject> getRepository(@QueryParam("path") String path, @QueryParam("type") String type) {
      // String username =
      // sessionService.getAllSessionObjects().get("username").toString();
      // List<String> roles = (List<String> )
      // sessionService.getAllSessionObjects().get("roles");
      // String[] t = type.split(",");
      // List<IRepositoryObject> l = new ArrayList<>();
      // List<IRepositoryObject> l2;
      // if(path==null){
      // l = (datasourceService.getFiles(Arrays.asList(t), username, roles));
      // }
      // else{
      // l = (datasourceService.getFiles(Arrays.asList(t), username, roles, path));
      // }
      //
      //
      // return l;

      // TODO:SAIKU
      throw new RuntimeException("not implemented");

   }

   @GET
   @Produces({ "application/json" })
   @Path("/resource/reset")
   public boolean reset(@PathParam("username") String username) {
      SaikuReport report = saikuSessioncontainer.get().getReport(username);

      if (report.getIdOrOldTransient() != null) {
         /* We reload the schema definition from the db. */
         EntityManager em = entityManagerProvider.get();
         SaikuReport managedReport = em.find(report.getClass(), report.getIdOrOldTransient());
         if (null == managedReport) {
            report.setQueryXml(null);
            return true;
         }
         managedReport.setQueryXml(null);
         try {
            /*
             * We load data we know we need before putting it into the session.
             */
            entityUtils.deepHibernateUnproxy(managedReport);
            saikuSessioncontainer.get().putReport(username, managedReport);

            em.detach(report);
         } catch (Exception e) {
            throw new RuntimeException("Could not reset for " + username, e);
         }
      }

      report.setQueryXml(null);
      return true;

   }

   @GET
   @Produces({ "application/json" })
   @Path("/resource/isMDX")
   public boolean isMDX(@PathParam("username") String username) {
      return saikuSessioncontainer.get().getReport(username).isAllowMdx();
   }

   /**
    * Get the ACL information for a given resource.
    * 
    * @param file The file object
    * @return An AclEntry Object.
    */
   @GET
   @Produces({ "application/json" })
   @Path("/resource/acl")
//	@ReturnType("org.saiku.repository.AclEntry")
   public Object getResourceAcl(@QueryParam("file") String file) {
      // try {
      // String username =
      // sessionService.getAllSessionObjects().get("username").toString();
      // List<String> roles = (List<String> )
      // sessionService.getAllSessionObjects().get("roles");
      // return datasourceService.getResourceACL(file, username, roles);
      //
      // } catch (Exception e) {
      // log.error("Error retrieving ACL for file: " + file, e);
      // }
      // throw new SaikuServiceException("You dont have permission to retrieve ACL for
      // file: " + file);
      throw new RuntimeException("not implemented");

   }

   /**
    * Set the ACL information for a file/folder.
    * 
    * @param file     The file you want to change
    * @param aclEntry The ACL information.
    * @return A response 200.
    */
   @POST
   @Produces({ "application/json" })
   @Path("/resource/acl")
   public Response setResourceAcl(@FormParam("file") String file, @FormParam("acl") String aclEntry) {
      // try {
      // String username =
      // sessionService.getAllSessionObjects().get("username").toString();
      // List<String> roles = (List<String> )
      // sessionService.getAllSessionObjects().get("roles");
      // datasourceService.setResourceACL(file, aclEntry, username, roles);
      // return Response.ok().build();
      //
      // //log.debug("Repo file does not exist or cannot grant access. repo file:" +
      // repoFile + " - file: " + file);
      // } catch (Exception e) {
      // log.error("An error occured while setting permissions to file: " + file, e);
      // }
      // return Response.serverError().build();
      throw new RuntimeException("not implemented");
   }

   /**
    * Get an object from the repository.
    * 
    * @param file - The name of the repository file to load.
    * @return A response containing the file data.
    */
   @GET
   @Produces({ "text/plain" })
   @Path("/resource")
   public Response getResource(@PathParam("username") String username, @QueryParam("file") String file) {
      // String username =
      // sessionService.getAllSessionObjects().get("username").toString();
      // List<String> roles = (List<String> )
      // sessionService.getAllSessionObjects().get("roles");
      //
      // byte[] data = new byte[0];
      // try {
      // data = datasourceService.getFileData(file, username,
      // roles).getBytes("UTF-8");
      // } catch (UnsupportedEncodingException e) {
      // log.error("Error reading file encoding",e);
      // }
      // return Response.ok(data, MediaType.TEXT_PLAIN).header(
      // "content-length",data.length).build();
      // /*
      // if ( !acl.canRead(file, username, roles) ) {
      // return Response.serverError().status(Status.FORBIDDEN).build();
      // }
      // */

      SaikuReport report = saikuSessioncontainer.get().getReport(username);
      if (file.equals("variant-" + report.getId() + ".saiku") && report instanceof SaikuReportVariant) {
         String content = ((SaikuReportVariant) report).getQueryXml();
         byte[] doc = content.getBytes();
         return Response.ok(doc, MediaType.TEXT_PLAIN).header("content-length", doc.length).build();
      }
      return Response.status(Status.NOT_FOUND).build();
   }

   /**
    * Save an object to the repository.
    * 
    * @param file    - The name of the repository file to load.
    * @param content - The content to save.
    * @return A response status 200.
    */
   @POST
   @Path("/resource")
   public Response saveResource(@FormParam("file") String file, @FormParam("content") String content) {
      // String username =
      // sessionService.getAllSessionObjects().get("username").toString();
      // List<String> roles = (List<String> )
      // sessionService.getAllSessionObjects().get("roles");
      // String resp = datasourceService.saveFile(content, file, username, roles);
      // if(resp.equals("Save Okay")){
      // return Response.ok().build();
      // }
      // else{
      // return Response.serverError().entity("Cannot save resource to ( file: " +
      // file + ")").type("text/plain").build();
      // }
      // /*
      // return Response.serverError().status(Status.FORBIDDEN)
      // .entity("You don't have permissions to save here!")
      // .type("text/plain").build();
      // */
      throw new RuntimeException("not implemented");
   }

   /**
    * Delete a resource from the repository
    * 
    * @param file - The name of the repository file to load.
    * @return a response status 200.
    */
   @DELETE
   @Path("/resource")
   public Response deleteResource(@QueryParam("file") String file) {
      // String username =
      // sessionService.getAllSessionObjects().get("username").toString();
      // List<String> roles = (List<String> )
      // sessionService.getAllSessionObjects().get("roles");
      // String resp = datasourceService.removeFile(file, username, roles);
      // if(resp.equals("Remove Okay")){
      // return Response.ok().build();
      // }
      // else{
      // return Response.serverError().entity("Cannot save resource to ( file: " +
      // file + ")").type("text/plain").build();
      // }
      throw new RuntimeException("not implemented");

   }

   /**
    * Move an object within the repository.
    * 
    * @param source Source object
    * @param target Target location
    * @return A response status 200
    */
   @POST
   @Path("/resource/move")
   public Response moveResource(@FormParam("source") String source, @FormParam("target") String target) {
      // String username =
      // sessionService.getAllSessionObjects().get("username").toString();
      // List<String> roles = (List<String> )
      // sessionService.getAllSessionObjects().get("roles");
      // String resp = datasourceService.moveFile(source, target, username, roles);
      // if(resp.equals("Move Okay")){
      // return Response.ok().entity("{}").build();
      // }
      // else{
      // return Response.serverError().entity("Cannot move resource to ( file: " +
      // target + ")").type("text/plain").build();
      // }
      //
      //
      //
      // /*try {
      // if (source == null || source.startsWith("/") || source.startsWith(".")) {
      // throw new IllegalArgumentException("Path cannot be null or start with \"/\"
      // or \".\" - Illegal Path: " + source);
      // }
      // if (target == null || target.startsWith("/") || target.startsWith(".")) {
      // throw new IllegalArgumentException("Path cannot be null or start with \"/\"
      // or \".\" - Illegal Path: " + target);
      // }
      //
      // String username =
      // sessionService.getAllSessionObjects().get("username").toString();
      // List<String> roles = (List<String> )
      // sessionService.getAllSessionObjects().get("roles");
      // FileObject targetFile = repo.resolveFile(target);
      //
      // if ( !acl.canWrite(target,username, roles) ) {
      // return Response.serverError().status(Status.FORBIDDEN)
      // .entity("You don't have permissions to save here!")
      // .type("text/plain").build();
      // }
      //
      // if (targetFile == null) throw new Exception("Repo File not found");
      //
      // if (targetFile.exists()) {
      // throw new Exception("Target file exists already. Cannot write: " + target);
      // }
      //
      // FileObject sourceFile = repo.resolveFile(source);
      // if ( !acl.canRead(source, username, roles) ) {
      // return Response.serverError().status(Status.FORBIDDEN).entity("You don't have
      // permissions to read the source file: " + source).build();
      // }
      //
      // if (!sourceFile.exists()) {
      // throw new Exception("Source file does not exist: " + source);
      // }
      // if (!sourceFile.canRenameTo(targetFile)) {
      // throw new Exception("Cannot rename " + source + " to " + target);
      // }
      // sourceFile.moveTo(targetFile);
      // return Response.ok().build();
      // } catch(Exception e){
      // log.error("Cannot move resource from " + source + " to " + target ,e);
      // return Response.serverError().entity("Cannot move resource from " + source +
      // " to " + target + " ( " + e.getMessage() + ")").type("text/plain").build();
      // }
      // */
      throw new RuntimeException("not implemented");

   }

}
