package net.datenwerke.rs.legacysaiku.server.rest.resources;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response.Status;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import net.datenwerke.rs.saiku.server.rest.objects.SavedQuery;

@Path("/legacysaiku/{username}/repository")
@XmlAccessorType(XmlAccessType.NONE)
public class BasicRepositoryResource {

   /**
    * Get Saved Queries.
    * 
    * @return A list of SavedQuery Objects.
    */
   @GET
   @Produces({ "application/json" })
   public List<SavedQuery> getSavedQueries() {
      throw new RuntimeException("Not implemented.");
   }

   /**
    * Delete Query.
    * 
    * @param queryName - The name of the query.
    * @return A GONE Status if the query was deleted, otherwise it will return a
    *         NOT FOUND Status code.
    */
   @DELETE
   @Produces({ "application/json" })
   @Path("/{queryname}")
   public Status deleteQuery(@PathParam("queryname") String queryName) {
      throw new RuntimeException("Not implemented.");
   }

   /**
    * 
    * @param queryName - The name of the query.
    * @param newName   - The saved query name.
    * @return An OK Status, if the save was good, otherwise a NOT FOUND Status when
    *         not saved properly.
    */
   @POST
   @Produces({ "application/json" })
   @Path("/{queryname}")
   public Status saveQuery(@PathParam("queryname") String queryName, @FormParam("newname") String newName) {

      throw new RuntimeException("Not implemented.");
   }

   /**
    * Load a query.
    * 
    * @param queryName - The name of the query to load.
    * @return A Saiku Query Object.
    */
   @GET
   @Produces({ "application/json" })
   @Path("/{queryname}")
   public SavedQuery loadQuery(@PathParam("queryname") String queryName) {
//		try{
//			String uri = repo.getName().getPath();
//			if (uri != null && !uri.endsWith("" + File.separatorChar)) {
//				uri += File.separatorChar;
//			}
//
//			String filename = queryName;
//			if (uri != null) {
//				if (!filename.endsWith(".saiku")) {
//					filename += ".saiku";
//				}
//				String filepath = repo.getName().getPath();
//				if (!filepath.endsWith("" + File.separatorChar)) {
//					filepath += File.separatorChar;
//				}
//				
//				File queryFile = new File(uri+filename);
//
//				if (queryFile.exists()) {
//					FileReader fi = new FileReader(queryFile);
//					BufferedReader br = new BufferedReader(fi);
//					String chunk ="",xml ="";
//					while ((chunk = br.readLine()) != null) {
//						xml += chunk + "\n";
//					}
//					SimpleDateFormat sf = new SimpleDateFormat("dd - MMM - yyyy HH:mm:ss");
//					SavedQuery sq = new SavedQuery(filename, sf.format(new Date(queryFile.lastModified())),xml);
//					return sq;
//				}
//				else {
//					throw new Exception("File does not exist:" + uri);
//				}
//			}
//			else {
//				throw new Exception("Cannot save query because uriis null");
//			}
//		} catch(Exception e){
//			log.error("Cannot load query (" + queryName + ")",e);
//		}
      throw new RuntimeException("Not implemented.");
   }
}
