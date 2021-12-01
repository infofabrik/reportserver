package net.datenwerke.rs.legacysaiku.server.rest.resources;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import net.datenwerke.rs.legacysaiku.server.rest.objects.repository.IRepositoryObject;

public interface ISaikuRepository {

	/**
	 * Get Saved Queries.
	 * @return A list of SavedQuery Objects.
	 */
	@GET
	@Produces({ "application/json" })
	public List<IRepositoryObject> getRepository(
			@QueryParam("path") String path, @QueryParam("type") String type, @PathParam("username") String username);

	/**
	 * Load a resource.
	 * @param file - The name of the repository file to load.
	 * @return A Repository File Object.
	 */
	@GET
	@Produces({ "text/plain" })
	@Path("/resource")
	public Response getResource(@QueryParam("file") String file, @PathParam("username") String username);

	/**
	 * Save a resource.
	 * @param file - The name of the repository file to load.
	 * @param content - The content to save.
	 * @return Status
	 */
	@POST
	@Path("/resource")
	public Response saveResource(@FormParam("file") String file,
			@FormParam("content") String content);

	/**
	 * Delete a resource.
	 * @param file - The name of the repository file to load.
	 * @return Status
	 */
	@DELETE
	@Path("/resource")
	public Response deleteResource(@QueryParam("file") String file);

}