package net.datenwerke.rs.legacysaiku.server.rest.resources;


import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Provider;
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
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import net.datenwerke.rs.legacysaiku.server.rest.objects.acl.AclEntry;
import net.datenwerke.rs.legacysaiku.server.rest.objects.repository.IRepositoryObject;
import net.datenwerke.rs.legacysaiku.server.rest.objects.repository.RepositoryFileObject;
import net.datenwerke.rs.legacysaiku.service.saiku.SaikuSessionContainer;
import net.datenwerke.rs.saiku.service.saiku.entities.SaikuReport;
import net.datenwerke.rs.saiku.service.saiku.entities.SaikuReportVariant;

@Path("/legacysaiku/{username}/repository2")
@XmlAccessorType(XmlAccessType.NONE)
public class BasicRepositoryResource2 implements ISaikuRepository {

	private Provider<SaikuSessionContainer> saikuSessioncontainer;

	@Inject
	public BasicRepositoryResource2(Provider<SaikuSessionContainer> saikuSessioncontainer) {
		this.saikuSessioncontainer = saikuSessioncontainer;
	}
	
	/* (non-Javadoc)
	 * @see org.legacysaiku.web.rest.resources.ISaikuRepository#getRepository(java.lang.String, java.lang.String)
	 */
	@GET
	@Produces({"application/json" })
	public List<IRepositoryObject> getRepository (
			@QueryParam("path") String path,
			@QueryParam("type") String type, 
			@PathParam("username") String username) 
	{

		SaikuReport report = saikuSessioncontainer.get().getReport(username);
		if(report instanceof SaikuReportVariant){
			RepositoryFileObject variantObject = new RepositoryFileObject(report.getName() + ".saiku", report.getId().toString(), "saiku", "variant-"+report.getId() + ".saiku", null);
			return Arrays.asList((IRepositoryObject)variantObject);
		}
		
		return Collections.EMPTY_LIST;
	}
	
	@GET
	@Produces({"application/json" })
	@Path("/resource/isMDX")
	public boolean isMDX(@PathParam("username") String username) {
		SaikuReport report = saikuSessioncontainer.get().getReport(username);
		return report.isAllowMdx();
	}
	
	@GET
	@Produces({"application/json" })
	@Path("/resource/reset")
	public boolean reset(@PathParam("username") String username) {
		SaikuReport report = saikuSessioncontainer.get().getReport(username);
		
		report.setQueryXml(null);
		
		return true;
		
	}

	@GET
	@Produces({"application/json" })
	@Path("/resource/acl")
	public AclEntry getResourceAcl(@QueryParam("file") String file) {
		throw new RuntimeException("Not implemented.");
	}
	
	
	@POST
	@Produces({"application/json" })
	@Path("/resource/acl")
	public Response setResourceAcl(@FormParam("file") String file, @FormParam("acl") String aclEntry) {
		throw new RuntimeException("Not implemented.");
	}


	/* (non-Javadoc)
	 * @see org.legacysaiku.web.rest.resources.ISaikuRepository#getResource(java.lang.String)
	 */
	@GET
	@Produces({"text/plain" })
	@Path("/resource")
	public Response getResource (@QueryParam("file") String file, @PathParam("username") String username)
	{
		SaikuReport report = saikuSessioncontainer.get().getReport(username);
		if(file.equals("variant-" + report.getId() + ".saiku") && report instanceof SaikuReportVariant){
			String content = ((SaikuReportVariant) report).getQueryXml();
			byte[] doc = content.getBytes();
			return Response.ok(doc, MediaType.TEXT_PLAIN).header("content-length",doc.length).build();
		}
		return Response.status(Status.NOT_FOUND).build();
	}
	
	/* (non-Javadoc)
	 * @see org.legacysaiku.web.rest.resources.ISaikuRepository#saveResource(java.lang.String, java.lang.String)
	 */
	@POST
	@Path("/resource")
	public Response saveResource (
			@FormParam("file") String file, 
			@FormParam("content") String content)
	{
		throw new RuntimeException("Not implemented.");
	}
	
	/* (non-Javadoc)
	 * @see org.legacysaiku.web.rest.resources.ISaikuRepository#deleteResource(java.lang.String)
	 */
	@DELETE
	@Path("/resource")
	public Response deleteResource (
			@QueryParam("file") String file)
	{
		throw new RuntimeException("Not implemented.");
	}
	
	/* (non-Javadoc)
	 * @see org.legacysaiku.web.rest.resources.ISaikuRepository#saveResource(java.lang.String, java.lang.String)
	 */
	@POST
	@Path("/resource/move")
	public Response moveResource(@FormParam("source") String source, @FormParam("target") String target)
	{
		throw new RuntimeException("Not implemented.");
	}
	
}
