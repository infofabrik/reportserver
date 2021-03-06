/*
 *   Copyright 2015 OSBI Ltd
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

import java.io.InputStream;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.StreamingOutput;

import org.saiku.database.dto.SaikuUser;

import com.sun.jersey.core.header.FormDataContentDisposition;

import net.datenwerke.rs.saiku.server.rest.objects.DataSourceMapper;

/**
 * AdminResource for the Saiku 3.0+ Admin console
 */
@Path("/saiku/admin")
public class AdminResource {

//    private DatasourceService datasourceService;
//
//    private UserService userService;
//    private static final Logger log = LoggerFactory.getLogger(DataSourceResource.class);
//    private OlapDiscoverService olapDiscoverService;
//    private LogExtractor logExtractor;
//
//    public LogExtractor getLogExtractor() {
//        return logExtractor;
//    }
//
//    public void setLogExtractor(LogExtractor logExtractor) {
//        this.logExtractor = logExtractor;
//    }
//
//    public void setOlapDiscoverService(OlapDiscoverService olapDiscoverService) {
//        this.olapDiscoverService = olapDiscoverService;
//    }
//
//    public void setDatasourceService(DatasourceService ds) {
//        datasourceService = ds;
//    }
//
//    public void setUserService(UserService us) {
//        userService = us;
//    }
//
//
//    private IDatasourceManager repositoryDatasourceManager;
//
//    public IDatasourceManager getRepositoryDatasourceManager() {
//        return repositoryDatasourceManager;
//    }
//
//    public void setRepositoryDatasourceManager(
//        IDatasourceManager repositoryDatasourceManager) {
//        this.repositoryDatasourceManager = repositoryDatasourceManager;
//    }
   /**
    * Get all the available data sources on the platform.
    * 
    * @return A response containing a list of datasources.
    */
   @GET
   @Produces({ "application/json" })
   @Path("/datasources")
//    @ReturnType("java.lang.List<SaikuDatasource>")
   public Response getAvailableDataSources() {
//        if(!userService.isAdmin()){
//            return Response.status(Response.Status.FORBIDDEN).build();
//        }
//        List<DataSourceMapper> l = new ArrayList<>();
//        try {
//            for (SaikuDatasource d : datasourceService.getDatasources().values()) {
//                l.add(new DataSourceMapper(d));
//            }
//            return Response.ok().entity(l).build();
//        } catch (SaikuServiceException e) {
//            log.error(this.getClass().getName(), e);
//            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getLocalizedMessage()).type("text/plain").build();
//        }
      throw new RuntimeException("not implemented");
   }

   /**
    * Update a specific Saiku data source.
    * 
    * @param json The Json data source object
    * @param id   The datasource id.
    * @return A response containing the datasource.
    */
   @PUT
   @Produces({ "application/json" })
   @Consumes({ "application/json" })
   @Path("/datasources/{id}")
//    @ReturnType("org.saiku.web.rest.objects.DataSourceMapper")
   public Response updateDatasource(DataSourceMapper json, @PathParam("id") String id) {
//        if(!userService.isAdmin()){
//            return Response.status(Response.Status.FORBIDDEN).build();
//        }
//
//        try {
//            datasourceService.addDatasource( json.toSaikuDataSource(), true );
//            return Response.ok().type("application/json").entity(json).build();
//        }
//        catch (Exception e){
//            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getLocalizedMessage())
//                           .type("text/plain").build();
//        }
      throw new RuntimeException("not implemented");
   }

   /**
    * Refresh a Saiku data source.
    * 
    * @param id The data source id.
    * @return A response containing the data source definition.
    */
   @GET
   @Produces({ "application/json" })
   @Path("/datasources/{id}/refresh")
//    @ReturnType("java.util.List<SaikuConnection>")
   public Response refreshDatasource(@PathParam("id") String id) {
//        if(!userService.isAdmin()){
//            return Response.status(Response.Status.FORBIDDEN).build();
//        }
//
//        try {
//            olapDiscoverService.refreshConnection(id);
//            return Response.ok().entity(olapDiscoverService.getConnection(id)).type("application/json").build();
//        } catch (Exception e) {
//            log.error(this.getClass().getName(), e);
//            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getLocalizedMessage())
//                           .type("text/plain").build();
//        }
      throw new RuntimeException("not implemented");

   }

   /**
    * Create a data source on the Saiku server.
    * 
    * @param json The json data source object
    * @return A response containing the data source object
    */
   @POST
   @Produces({ "application/json" })
   @Consumes({ "application/json" })
   @Path("/datasources")
//    @ReturnType("org.saiku.web.rest.objects.DataSourceMapper")
   public Response createDatasource(DataSourceMapper json) {
//        if(!userService.isAdmin()){
//            return Response.status(Response.Status.FORBIDDEN).build();
//        }
//
//        try {
//            datasourceService.addDatasource(json.toSaikuDataSource(), false);
//            return Response.ok().entity(json).type("application/json").build();
//        } catch (Exception e) {
//            log.error("Error adding data source", e);
//            return Response.serverError().status(Response.Status.INTERNAL_SERVER_ERROR)
//                           .entity(e.getLocalizedMessage())
//                           .type("text/plain").build();
//        }
      throw new RuntimeException("not implemented");
   }

   /**
    * Delete data source from the Saiku server
    * 
    * @param id The data source ID
    * @return A response containing a list of data sources remaining on the
    *         platform.
    */
   @DELETE
   @Path("/datasources/{id}")
   public Response deleteDatasource(@PathParam("id") String id) {
//
//        if(!userService.isAdmin()){
//            return Response.status(Response.Status.FORBIDDEN).build();
//        }
//        datasourceService.removeDatasource(id);
//        return Response.ok().type("application/json").entity(datasourceService.getDatasources()).build();
      throw new RuntimeException("not implemented");
   }

   /**
    * Get all the available schema.
    * 
    * @return A list of schema
    */
   @GET
   @Produces({ "application/json" })
   @Path("/schema")
//    @ReturnType("java.util.List<MondrianSchema>")
   public Response getAvailableSchema() {
//
//        if(!userService.isAdmin()){
//            return Response.status(Response.Status.FORBIDDEN).build();
//        }
//        return Response.ok().entity(datasourceService.getAvailableSchema()).build();
      throw new RuntimeException("not implemented");
   }

   /**
    * Upload a new schema to the Saiku server.
    * 
    * @param is     Input stream (file form data param)
    * @param detail Detail (file form data param)
    * @param name   Schema name
    * @param id     Schema id
    * @return A response containing a list of available schema.
    */
   @PUT
   @Produces({ "application/json" })
   @Consumes(MediaType.MULTIPART_FORM_DATA)
   @Path("/schema/{id}")
//    @ReturnType("java.util.List<MondrianSchema>")
   public Response uploadSchemaPut(InputStream is, FormDataContentDisposition detail, String name,
         @PathParam("id") String id) {
//    public Response uploadSchemaPut(@FormDataParam("file") InputStream is, @FormDataParam("file") FormDataContentDisposition detail,
//                                    @FormDataParam("name") String name, @PathParam("id") String id) {
//        if(!userService.isAdmin()){
//            return Response.status(Response.Status.FORBIDDEN).build();
//        }
//        String path = "/datasources/" + name + ".xml";
//        String schema = getStringFromInputStream(is);
//        try {
//            datasourceService.addSchema(schema, path, name);
//            return Response.ok().entity(datasourceService.getAvailableSchema()).build();
//        } catch (Exception e) {
//            log.error("Error uploading schema: "+name, e);
//            return Response.serverError().status(Response.Status.INTERNAL_SERVER_ERROR)
//                           .entity(e.getLocalizedMessage())
//                           .type("text/plain").build();
//        }
      throw new RuntimeException("not implemented");

   }

   /**
    * Upload new schema to the Saiku server
    * 
    * @param is     Input stream (file form data param)
    * @param detail Detail (file form data param)
    * @param name   Schema name
    * @param id     Schema id
    * @return A response containing a list of available schema.
    */
   @POST
   @Produces({ "application/json" })
   @Consumes("multipart/form-data")
   @Path("/schema/{id}")
//    @ReturnType("java.util.List<MondrianSchema>")
   public Response uploadSchema(InputStream is, FormDataContentDisposition detail, String name,
         @PathParam("id") String id) {
//    public Response uploadSchema(@FormDataParam("file") InputStream is, @FormDataParam("file") FormDataContentDisposition detail,
//                                 @FormDataParam("name") String name, @PathParam("id") String id) {
//        if(!userService.isAdmin()){
//            return Response.status(Response.Status.FORBIDDEN).build();
//        }
//        String path = "/datasources/" + name + ".xml";
//        String schema = getStringFromInputStream(is);
//        try {
//            datasourceService.addSchema(schema, path, name);
//            return Response.ok().entity(datasourceService.getAvailableSchema()).build();
//        } catch (Exception e) {
//            log.error("Error uploading schema: "+name, e);
//            return Response.serverError().status(Response.Status.INTERNAL_SERVER_ERROR)
//                           .entity(e.getLocalizedMessage())
//                           .type("text/plain").build();
//        }
      throw new RuntimeException("not implemented");
   }

   /**
    * Updates the locale parameter of the datasource
    * 
    * @param locale         the new locale for the data source
    * @param datasourceName ID of the data source whose locale should be changed
    * @return Response indicating success or fail
    */
   @PUT
   @Produces({ "application/json" })
   @Consumes({ "application/json" })
   @Path("/datasources/{datasourceName}/locale")
//    @ReturnType("org.saiku.web.rest.objects.DataSourceMapper")
   public Response updateDatasourceLocale(String locale, @PathParam("datasourceName") String datasourceName) {
//        try {
//            boolean overwrite = true;
//            SaikuDatasource saikuDatasource = datasourceService.getDatasource(datasourceName);
//            datasourceService.setLocaleOfDataSource(saikuDatasource, locale);
//            datasourceService.addDatasource(saikuDatasource, overwrite);
//            return Response.ok().type("application/json").entity(new DataSourceMapper(saikuDatasource)).build();
//        } catch(SaikuDataSourceException e){
//            return Response.ok().type("application/json").entity(e.getLocalizedMessage()).build();
//        } catch (Exception e) {
//            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getLocalizedMessage())
//                .type("text/plain").build();
//        }
      throw new RuntimeException("not implemented");
   }

   /**
    * Get existing Saiku users from the Saiku server.
    * 
    * @return A list of available users.
    */
   @GET
   @Produces({ "application/json" })
   @Path("/users")
//    @ReturnType("java.util.List<SaikuUser>")
   public Response getExistingUsers() {
//        if(!userService.isAdmin()){
//            return Response.status(Response.Status.FORBIDDEN).build();
//        }
//        return Response.ok().entity(userService.getUsers()).build();
      throw new RuntimeException("not implemented");
   }

   /**
    * Delete a schema from the Saiku server.
    * 
    * @param id The schema ID.
    * @return A response containing available schema.
    */
   @DELETE
   @Path("/schema/{id}")
//    @ReturnType("java.util.List<MondrianSchema>")
   public Response deleteSchema(@PathParam("id") String id) {
//        if(!userService.isAdmin()){
//            return Response.status(Response.Status.FORBIDDEN).build();
//        }
//        datasourceService.removeSchema(id);
//        return Response.status(Response.Status.NO_CONTENT).entity(datasourceService.getAvailableSchema()).build();
      throw new RuntimeException("not implemented");
   }

   /**
    * Get Saved Schema By ID
    * 
    * @param id
    * @return a schema file.
    */
   @GET
   @Path("/schema/{id}")
   @Produces("application/xml")
//    @ReturnType("MondrianSchema")
   public Response getSavedSchema(@PathParam("id") String id) {
//        if(!userService.isAdmin()){
//            return Response.status(Response.Status.FORBIDDEN).build();
//        }
//        String p = "";
//        for(MondrianSchema s :datasourceService.getAvailableSchema()){
//            if(s.getName().equals(id)){
//
//                try {
//                    p= repositoryDatasourceManager.getInternalFileData(s.getPath());
//                } catch (RepositoryException e) {
//                    Response.serverError().entity(e.getLocalizedMessage()).build();
//                }
//                break;
//            }
//        }
//
//        return Response
//            .ok(p.getBytes(), MediaType.APPLICATION_OCTET_STREAM)
//            .header("content-disposition", "attachment; filename = " + id)
//            .build();
      throw new RuntimeException("not implemented");
   }

   /**
    * Import a legacy data source into the Saiku server.
    * 
    * @return A status 200.
    */
   @GET
   @Path("/datasource/import")
   public Response importLegacyDatasources() {
//
//        if(!userService.isAdmin()){
//            return Response.status(Response.Status.FORBIDDEN).build();
//        }
//        datasourceService.importLegacyDatasources();
//        return Response.ok().build();
      throw new RuntimeException("not implemented");
   }

   /**
    * Import legacy schema.
    * 
    * @return A status 200
    */
   @GET
   @Path("/schema/import")
   public Response importLegacySchema() {
//        if(!userService.isAdmin()){
//            return Response.status(Response.Status.FORBIDDEN).build();
//        }
//
//        datasourceService.importLegacySchema();
//        return Response.ok().build();
      throw new RuntimeException("not implemented");
   }

   /**
    * Import legacy users into the Saiku server/
    * 
    * @return A status 200.
    */
   @GET
   @Path("/users/import")
   public Response importLegacyUsers() {
//
//        if(!userService.isAdmin()){
//            return Response.status(Response.Status.FORBIDDEN).build();
//        }
//        datasourceService.importLegacyUsers();
//        return Response.ok().build();
      throw new RuntimeException("not implemented");
   }

   /**
    * Get user details for a user in the Saiku server.
    * 
    * @param id The user ID.
    * @return A response containing the user details object for the selected user.
    */
   @GET
   @Produces({ "application/json" })
   @Path("/users/{id}")
//    @ReturnType("org.saiku.database.dto.SaikuUser")
   public Response getUserDetails(@PathParam("id") int id) {
//        if(!userService.isAdmin()){
//            return Response.status(Response.Status.FORBIDDEN).build();
//        }
//        return Response.ok().entity(userService.getUser(id)).build();
      throw new RuntimeException("not implemented");
   }

   /**
    * Update a users user details on the Saiku server.
    * 
    * @param jsonString SaikuUser object.
    * @param userName   The username for the user to be updated.
    * @return A response containing a user object.
    */
   @PUT
   @Produces({ "application/json" })
   @Consumes("application/json")
   @Path("/users/{username}")
//    @ReturnType("org.saiku.database.dto.SaikuUser")
   public Response updateUserDetails(SaikuUser jsonString, @PathParam("username") String userName) {
//        if(!userService.isAdmin()){
//            return Response.status(Response.Status.FORBIDDEN).build();
//        }
//        if(jsonString.getPassword() == null || jsonString.getPassword().equals("")) {
//            return Response.ok().entity(userService.updateUser(jsonString, false)).build();
//        }
//        else{
//            return Response.ok().entity(userService.updateUser(jsonString, true)).build();
//        }
      throw new RuntimeException("not implemented");
   }

   /**
    * Create user details on the Saiku server.
    * 
    * @param jsonString SaikuUser object
    * @return A response containing the user object.
    */
   @POST
   @Produces({ "application/json" })
   @Consumes({ "application/json" })
   @Path("/users")
//    @ReturnType("org.saiku.database.dto.SaikuUser")
   public Response createUserDetails(SaikuUser jsonString) {
//
//        if(!userService.isAdmin()){
//            return Response.status(Response.Status.FORBIDDEN).build();
//        }
//        return Response.ok().entity(userService.addUser(jsonString)).build();
      throw new RuntimeException("not implemented");
   }

   /**
    * Delete a user from the Saiku server.
    * 
    * @param username The username to remove
    * @return A status 200.
    */
   @DELETE
   @Produces({ "application/json" })
   @Path("/users/{username}")
   public Response removeUser(@PathParam("username") String username) {
//        if(!userService.isAdmin()){
//            return Response.status(Response.Status.FORBIDDEN).build();
//        }
//        userService.removeUser(username);
//        return Response.ok().build();
      throw new RuntimeException("not implemented");
   }

   /**
    * Get string from an input stream object.
    * 
    * @param is The input stream to convert.
    * @return A string representation of the input stream.
    */
   private static String getStringFromInputStream(InputStream is) {
//
//        BufferedReader br = null;
//        StringBuilder sb = new StringBuilder();
//
//        String line;
//        try {
//
//            br = new BufferedReader(new InputStreamReader(is));
//            while ((line = br.readLine()) != null) {
//                sb.append(line);
//            }
//
//        } catch (IOException e) {
//            log.error("IO Exception when reading from input stream", e);
//        } finally {
//            if (br != null) {
//                try {
//                    br.close();
//                } catch (IOException e) {
//                    log.error("IO Exception closing input stream",e );
//                }
//            }
//        }
//
//        return sb.toString();
      throw new RuntimeException("not implemented");
   }

   /**
    * Get the Saiku server version.
    * 
    * @return A Response containing the Saiku server version.
    */
   @GET
   @Produces("text/plain")
   @Path("/version")
//    @ReturnType("java.lang.String")
   public Response getVersion() {
//        Properties prop = new Properties();
//        InputStream input = null;
//        String version = "";
//        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
//        InputStream is = classloader.getResourceAsStream("org/saiku/web/rest/resources/version.properties");
//        try {
//
//            //input = new FileInputStream("version.properties");
//
//            // load a properties file
//            prop.load(is);
//
//            // get the property value and print it out
//            version = prop.getProperty("VERSION");
//        } catch (IOException ex) {
//            log.error("IO Exception when reading input stream", ex);
//        }
//        return Response.ok().entity(version).type("text/plain").build();
      throw new RuntimeException("not implemented");
   }

   /**
    * Backup the Saiku server repository.
    * 
    * @return A Zip file containing the backup.
    */
   @GET
   @Produces("application/zip")
   @Path("/backup")
   public StreamingOutput getBackup() {
//        if(!userService.isAdmin()){
//            return null;
//        }
//        return new StreamingOutput() {
//            public void write(OutputStream output) throws IOException, WebApplicationException {
//                BufferedOutputStream bus = new BufferedOutputStream(output);
//                bus.write(datasourceService.exportRepository());
//
//            }
//        };
      throw new RuntimeException("not implemented");
   }

   /**
    * Restore the repository on a Saiku server.
    * 
    * @param is     The input stream
    * @param detail The file detail
    * @return A status 200.
    */
   @POST
   @Produces("text/plain")
   @Consumes("multipart/form-data")
   @Path("/restore")
   public Response postRestore(InputStream is, FormDataContentDisposition detail) {
//    public Response postRestore(@FormDataParam("file") InputStream is, @FormDataParam("file") FormDataContentDisposition detail){
//        if(!userService.isAdmin()){
//            return Response.status(Response.Status.FORBIDDEN).build();
//        }
//        try {
//            byte[] bytes = IOUtils.toByteArray(is);
//            datasourceService.restoreRepository(bytes);
//            return Response.ok().entity("Restore Ok").type("text/plain").build();
//        } catch (IOException e) {
//            log.error("Error reading restore file", e);
//        }
//        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Restore Ok").type("text/plain").build();
      throw new RuntimeException("not implemented");
   }

   /**
    * Restore old legacy files on the Saiku server.
    * 
    * @param is     Input stream
    * @param detail The file detail
    * @return A status 200
    */
   @POST
   @Produces("text/plain")
   @Consumes("multipart/form-data")
   @Path("/legacyfiles")
   public Response postRestoreFiles(InputStream is, FormDataContentDisposition detail) {
//    public Response postRestoreFiles(@FormDataParam("file") InputStream is, @FormDataParam("file") FormDataContentDisposition detail){
//        if(!userService.isAdmin()){
//            return Response.status(Response.Status.FORBIDDEN).build();
//        }
//        try {
//            byte[] bytes = IOUtils.toByteArray(is);
//            datasourceService.restoreLegacyFiles(bytes);
//            return Response.ok().entity("Restore Ok").type("text/plain").build();
//        } catch (IOException e) {
//            log.error("Error reading restore file", e);
//        }
//        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Restore Ok").type("text/plain").build();
      throw new RuntimeException("not implemented");
   }

   @GET
   @Produces("text/plain")
   @Path("/log/{logname}")
   public Response getLogFile(@PathParam("logname") String logname) {
//        if(!userService.isAdmin()){
//            return Response.status(Response.Status.FORBIDDEN).build();
//        }
//        try {
//            return Response.status(Response.Status.OK).entity(logExtractor.readLog(logname)).build();
//        } catch (IOException e) {
//            log.error("Could not read log file",e);
//            return Response.serverError().entity("Could not read log file").build();
//        }
      throw new RuntimeException("not implemented");
   }

   @GET
   @Produces("application/json")
   @Path("/datakeys")
   public Response getPropertiesKeys() {
//        return Response.ok(repositoryDatasourceManager.getAvailablePropertiesKeys()).build();
      throw new RuntimeException("not implemented");
   }
}
