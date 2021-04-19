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

import java.io.IOException;
import java.io.InputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.type.CollectionType;
import org.codehaus.jackson.type.JavaType;
import org.olap4j.metadata.Cube;
import org.saiku.olap.dto.SimpleCubeElement;
import org.saiku.olap.dto.resultset.CellDataSet;
import org.saiku.olap.query2.ThinQuery;
import org.saiku.olap.util.SaikuProperties;
import org.saiku.service.util.exception.SaikuServiceException;
import org.saiku.web.export.JSConverter;
import org.saiku.web.export.PdfReport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.rs.saiku.server.rest.objects.resultset.QueryResult;
import net.datenwerke.rs.saiku.server.rest.util.RestUtil;
import net.datenwerke.rs.saiku.service.saiku.OlapUtilService;
import net.datenwerke.rs.saiku.service.saiku.SaikuSessionContainer;
import net.datenwerke.rs.saiku.service.saiku.ThinQueryService;
import net.datenwerke.rs.saiku.service.saiku.entities.SaikuReport;
import net.datenwerke.security.service.authenticator.AuthenticatorService;
import net.datenwerke.security.service.security.SecurityService;
import net.datenwerke.security.service.security.exceptions.ViolatedSecurityException;
import net.datenwerke.security.service.security.rights.Execute;

/**
 * Saiku Query Endpoints
 */
@Path("/saiku/api/{username}/query")
@XmlAccessorType(XmlAccessType.NONE)
public class Query2Resource {

	private static final Logger log = LoggerFactory.getLogger(Query2Resource.class);

	//    private ThinQueryServiceImpl thinQueryService;
	//
	//    //@Autowired
	//    public void setThinQueryService(ThinQueryServiceImpl tqs) {
	//        thinQueryService = tqs;
	//    }
	//
	//    private ISaikuRepository repository;
	//
	//    //@Autowired
	//    public void setRepository(ISaikuRepository repository){
	//        this.repository = repository;
	//    }

	private final OlapUtilService olapUtilService;
	private final ThinQueryService thinQueryService;
	private Provider<SaikuSessionContainer> saikuSessionContainer;
	private final Provider<AuthenticatorService> authenticatorService;
	private final SecurityService securityService;
	

	@Inject
	public Query2Resource(
			OlapUtilService olapUtils,
			ThinQueryService thinQueryService,
			Provider<SaikuSessionContainer> saikuSessionContainer,
			Provider<AuthenticatorService> authenticatorService,
			SecurityService securityService
			){
		this.olapUtilService = olapUtils;
		this.thinQueryService = thinQueryService;
		this.saikuSessionContainer = saikuSessionContainer;
		this.authenticatorService = authenticatorService;
		this.securityService = securityService;
	}


	/**
	 * Delete query from the query pool.
	 * @param queryName The query name
	 * @return a HTTP 410(Works) or HTTP 500(Call failed).
	 */
	@DELETE
	@Path("/{queryname}")
	public Status deleteQuery(@PathParam("queryname") String queryName){
		//        if (log.isDebugEnabled()) {
		//            log.debug("TRACK\t"  + "\t/query/" + queryName + "\tDELETE");
		//        }
		//        try{
		//            thinQueryService.deleteQuery(queryName);
		//            return(Status.GONE);
		//        }
		//        catch(Exception e){
		//            log.error("Cannot delete query (" + queryName + ")",e);
		//            throw new WebApplicationException(e);
		//        }

		throw new RuntimeException("not implemented");
	}

	/**
	 * Create a new Saiku Query.
	 * @param queryName The query name
	 * @param fileFormParam The file
	 * @param jsonFormParam The json
	 * @param formParams The form params
	 * @return a query model.
	 *
	 */
	@POST
	@Produces({"application/json" })
	@Path("/{queryname}")
	public ThinQuery createQuery(
			@PathParam("username") String username,
			@PathParam("queryname") String queryName,
			@FormParam("json") String jsonFormParam,
			@FormParam("file") String fileFormParam,
			MultivaluedMap<String, String> formParams) throws ServletException
	{
		//        try {
		//            ThinQuery tq;
		//            String file = fileFormParam,
		//                    json = jsonFormParam;
		//            if (formParams != null) {
		//                json = formParams.containsKey("json") ? formParams.getFirst("json") : jsonFormParam;
		//                file = formParams.containsKey("file") ? formParams.getFirst("file") : fileFormParam;
		//            }
		//            String filecontent = null;
		//            if (StringUtils.isNotBlank(json)) {
		//                filecontent = json;
		//            } else if (StringUtils.isNotBlank(file)) {
		//                Response f = repository.getResource(file);
		//                filecontent = new String( (byte[]) f.getEntity());
		//            }
		//            if (StringUtils.isBlank(filecontent)) {
		//                throw new SaikuServiceException("Cannot create new query. Empty file content " + StringUtils.isNotBlank(json) + " or read from file:" + file);
		//            }
		//            if (thinQueryService.isOldQuery(filecontent)) {
		//                tq = thinQueryService.convertQuery(filecontent);
		//            } else {
		//                ObjectMapper om = new ObjectMapper();
		//                tq = om.readValue(filecontent, ThinQuery.class);
		//            }
		//
		//            if (log.isDebugEnabled()) {
		//                log.debug("TRACK\t"  + "\t/query/" + queryName + "\tPOST\t tq:" + (tq == null) + " file:" + (file));
		//            }
		//
		//            if (tq == null) {
		//                throw new SaikuServiceException("Cannot create blank query (ThinQuery object = null)");
		//            }
		//            tq.setName(queryName);
		//
		//            //			SaikuCube cube = tq.getCube();
		//            //			if (StringUtils.isNotBlank(xml)) {
		//            //				String query = ServletUtil.replaceParameters(formParams, xml);
		//            //				return thinQueryService.createNewOlapQuery(queryName, query);
		//            //			}
		//            return thinQueryService.createQuery(tq);
		//        } catch (Exception e) {
		//            log.error("Error creating new query", e);
		//            throw new WebApplicationException(e);
		//        }


		try {
			ThinQuery tq;
			String file = fileFormParam,
					json = jsonFormParam;
			if (formParams != null) {
				json = formParams.containsKey("json") ? formParams.getFirst("json") : jsonFormParam;
				file = formParams.containsKey("file") ? formParams.getFirst("file") : fileFormParam;
			}
			String filecontent = null;
			if (StringUtils.isNotBlank(json)) {
				filecontent = json;
			} else if (StringUtils.isNotBlank(file)) {
				throw new RuntimeException("not implemented");
			}
			if (StringUtils.isBlank(filecontent)) {
				throw new SaikuServiceException("Cannot create new query. Empty file content " + StringUtils.isNotBlank(json) + " or read from file:" + file);
			}
			
			SaikuReport report = getReport(username);
			
			/* check if report has a query */
			if(null != report.getQueryXml() && ! "".equals(report.getQueryXml())){
				filecontent = report.getQueryXml();
			}
			
			if (thinQueryService.isOldQuery(filecontent)) {
				tq = thinQueryService.convertQuery(filecontent, getCube(username), report);
			} else {
				ObjectMapper om = new ObjectMapper();
				tq = om.readValue(filecontent, ThinQuery.class);
			}

			if (log.isDebugEnabled()) {
				log.debug("TRACK\t"  + "\t/query/" + queryName + "\tPOST\t tq:" + (tq == null) + " file:" + (file));
			}

			if (tq == null) {
				throw new SaikuServiceException("Cannot create blank query (ThinQuery object = null)");
			}
			tq.setName(queryName);

			ThinQuery query = thinQueryService.createQuery(tq, getCube(username));
			
			saikuSessionContainer.get().putQuery(query, report);
			
			return query;
		} catch (Exception e) {
			log.error("Error creating new query", e);
			throw new WebApplicationException(e);
		}
	}


	/**
	 *
	 * Execute a Saiku Query
	 * @param tq Thin Query model
	 * @return A query result set.
	 */
	@POST
	@Consumes({"application/json" })
	@Path("/execute")
	public QueryResult execute( @PathParam("username") String username, ThinQuery tq) {
		//        try {
		//            if (thinQueryService.isMdxDrillthrough(tq)) {
		//                Long start = (new Date()).getTime();
		//                ResultSet rs = thinQueryService.drillthrough(tq);
		//                QueryResult rsc = RestUtil.convert(rs);
		//                rsc.setQuery(tq);
		//                Long runtime = (new Date()).getTime()- start;
		//                rsc.setRuntime(runtime.intValue());
		//                return rsc;
		//            }
		//
		//            QueryResult qr = RestUtil.convert(thinQueryService.execute(tq));
		//            ThinQuery tqAfter = thinQueryService.getContext(tq.getName()).getOlapQuery();
		//            qr.setQuery(tqAfter);
		//            return qr;
		//        }
		//        catch (Exception e) {
		//            log.error("Cannot execute query (" + tq + ")",e);
		//            String error = ExceptionUtils.getRootCauseMessage(e);
		//            return new QueryResult(error);
		//        }

		try {
			SaikuReport report = getReport(username);

			if (thinQueryService.isMdxDrillthrough(tq, report)) {
				Long start = (new Date()).getTime();
				ResultSet rs = thinQueryService.drillthrough(tq, report);
				QueryResult rsc = RestUtil.convert(rs);
				rsc.setQuery(tq);
				Long runtime = (new Date()).getTime()- start;
				rsc.setRuntime(runtime.intValue());
				return rsc;
			}

			Cube cube = getCube(username);

			QueryResult qr = RestUtil.convert(thinQueryService.execute(username, tq, report, cube));
			ThinQuery tqAfter = thinQueryService.getContext(tq.getName()).getOlapQuery();
			qr.setQuery(tqAfter);
			
			saikuSessionContainer.get().putQuery(tqAfter);
			
			return qr;
		}
		catch (Exception e) {
			log.error("Cannot execute query (" + tq + ")",e);
			String error = ExceptionUtils.getRootCauseMessage(e);
			return new QueryResult(error);
		}
	}

	/**
	 * Cancel a running query.
	 * @param queryName The query name
	 * @return A 410 on success
	 */
	@DELETE
	@Path("/{queryname}/cancel")
	public Response cancel(@PathParam("username") String username, @PathParam("queryname") String queryName){
		if (log.isDebugEnabled()) {
			log.debug("TRACK\t"  + "\t/query/" + queryName + "\tDELETE");
		}
		try{
			thinQueryService.cancel(queryName);
			return Response.ok(Status.GONE).build();
		}
		catch(Exception e){
			log.error("Cannot cancel query (" + queryName + ")",e);
			String error = ExceptionUtils.getRootCauseMessage(e);
			throw new WebApplicationException(Response.serverError().entity(error).build());
		}
	}

	/**
	 * Enrich a thin query model
	 * @param tq The thin query
	 * @return An updated thin query.
	 */
	@POST
	@Consumes({"application/json" })
	@Path("/enrich")
	public ThinQuery enrich(@PathParam("username") String username, ThinQuery tq) {
		//		        try {
		//		            return thinQueryService.updateQuery(tq);
		//		        }
		//		        catch (Exception e) {
		//		            log.error("Cannot enrich query (" + tq + ")",e);
		//		            String error = ExceptionUtils.getRootCauseMessage(e);
		//		            throw new WebApplicationException(Response.serverError().entity(error).build());
		//		        }
		try {
			 ThinQuery updateQuery = thinQueryService.updateQuery(tq, getCube(username), getReport(username));
			 
			 saikuSessionContainer.get().putQuery(updateQuery);
			 
			 return updateQuery;
		}
		catch (Exception e) {
			log.error("Cannot enrich query (" + tq + ")",e);
			String error = ExceptionUtils.getRootCauseMessage(e);
			throw new WebApplicationException(Response.serverError().entity(error).build());
		}
	}

	/**
	 * Get level members from a query.
	 * @param queryName The query name
	 * @param hierarchyName The hierarchy name
	 * @param levelName The level name
	 * @param result Use the current result
	 * @param searchString The search string
	 * @param searchLimit The search limit
	 */
	@GET
	@Produces({"application/json" })
	@Path("/{queryname}/result/metadata/hierarchies/{hierarchy}/levels/{level}")
	public List<SimpleCubeElement> getLevelMembers(
			@PathParam("username") String username,
			@PathParam("queryname") String queryName,
			@PathParam("hierarchy") String hierarchyName,
			@PathParam("level") String levelName,
			@QueryParam("result") @DefaultValue("true") boolean result,
			@QueryParam("search") String searchString,
			@QueryParam("searchlimit") @DefaultValue("-1") int searchLimit)
	{
		//        if (log.isDebugEnabled()) {
		//            log.debug("TRACK\t"
		//                    + "\t/query/" + queryName + "/result/metadata"
		//                    + "/hierarchies/" + hierarchyName + "/levels/" + levelName + "\tGET");
		//        }
		//        try {
		//            return thinQueryService.getResultMetadataMembers(queryName, result, hierarchyName, levelName, searchString, searchLimit);
		//        }
		//        catch (Exception e) {
		//            log.error("Cannot execute query (" + queryName + ")",e);
		//            String error = ExceptionUtils.getRootCauseMessage(e);
		//            throw new WebApplicationException(Response.serverError().entity(error).build());
		//        }

		if (log.isDebugEnabled()) {
			log.debug("TRACK\t"
					+ "\t/query/" + queryName + "/result/metadata"
					+ "/hierarchies/" + hierarchyName + "/levels/" + levelName + "\tGET");
		}
		try {
			return thinQueryService.getResultMetadataMembers(queryName, getCube(username), result, hierarchyName, levelName, searchString, searchLimit);
		}
		catch (Exception e) {
			log.error("Cannot execute query (" + queryName + ")",e);
			String error = ExceptionUtils.getRootCauseMessage(e);
			throw new WebApplicationException(Response.serverError().entity(error).build());
		}
	}


	/**
	 * Query export to excel.
	 * @param queryName The query name
	 * @return A response containing an excel spreadsheet.
	 */
	@GET
	@Produces({"application/vnd.ms-excel" })
	@Path("/{queryname}/export/xls")
	public Response getQueryExcelExport(@PathParam("username") String username, @PathParam("queryname") String queryName){
		//        if (log.isDebugEnabled()) {
		//            log.debug("TRACK\t"  + "\t/query/" + queryName + "/export/xls/\tGET");
		//        }
		//        return getQueryExcelExport(queryName, "flattened", null);

		if (log.isDebugEnabled()) {
			log.debug("TRACK\t"  + "\t/query/" + queryName + "/export/xls/\tGET");
		}
		return getQueryExcelExport(username, queryName, "flattened", null);
	}

	/**
	 * Query export to excel
	 * @param queryName The query
	 * @param format The cellset format
	 * @param name The export name
	 * @return A response containing and excel spreadsheet.
	 */
	@GET
	@Produces({"application/vnd.ms-excel" })
	@Path("/{queryname}/export/xls/{format}")
	public Response getQueryExcelExport(
			@PathParam("username") String username,
			@PathParam("queryname") String queryName,
			@PathParam("format") @DefaultValue("flattened") String format, @QueryParam("exportname") @DefaultValue("")
			String name){
		//        if (log.isDebugEnabled()) {
		//            log.debug("TRACK\t"  + "\t/query/" + queryName + "/export/xls/"+format+"\tGET");
		//        }
		//        try {
		//            byte[] doc = thinQueryService.getExport(queryName,"xls",format);
		//            if(name == null || name.equals("")) {
		//                name = SaikuProperties.webExportExcelName + "." + SaikuProperties.webExportExcelFormat;
		//            }
		//            return Response.ok(doc, MediaType.APPLICATION_OCTET_STREAM).header(
		//                    "content-disposition",
		//                    "attachment; filename = " + name).header(
		//                    "content-length",doc.length).build();
		//        }
		//        catch (Exception e) {
		//            log.error("Cannot get excel for query (" + queryName + ")",e);
		//            String error = ExceptionUtils.getRootCauseMessage(e);
		//            throw new WebApplicationException(Response.serverError().entity(error).build());
		//        }

		if (log.isDebugEnabled()) {
			log.debug("TRACK\t"  + "\t/query/" + queryName + "/export/xls/"+format+"\tGET");
		}
		try {
			byte[] doc = thinQueryService.getExport(queryName,getCube(username), "xls",format);
			if(name == null || name.equals("")) {
				name = SaikuProperties.webExportExcelName + "." + SaikuProperties.webExportExcelFormat;
			}
			return Response.ok(doc, MediaType.APPLICATION_OCTET_STREAM).header(
					"content-disposition",
					"attachment; filename = " + name).header(
							"content-length",doc.length).build();
		}
		catch (Exception e) {
			log.error("Cannot get excel for query (" + queryName + ")",e);
			String error = ExceptionUtils.getRootCauseMessage(e);
			throw new WebApplicationException(Response.serverError().entity(error).build());
		}
	}

	/**
	 * Get CSV export of a query.
	 * @param queryName The query name
	 * @return A response containing a CSV file
	 */
	@GET
	@Produces({"text/csv" })
	@Path("/{queryname}/export/csv")
	public Response getQueryCsvExport(
			@PathParam("username") String username,
			@PathParam("queryname") String queryName) {
		//        if (log.isDebugEnabled()) {
		//            log.debug("TRACK\t"  + "\t/query/" + queryName + "/export/csv\tGET");
		//        }
		//        return getQueryCsvExport(queryName, "flattened", null);

		if (log.isDebugEnabled()) {
			log.debug("TRACK\t"  + "\t/query/" + queryName + "/export/csv\tGET");
		}
		return getQueryCsvExport(username, queryName, "flattened", null);
	}

	/**
	 * Get CSV export of a query.
	 * @param queryName The query name
	 * @param format The cell set format
	 * @param name The export name
	 * @return A response containing a CSV file
	 */
	@GET
	@Produces({"text/csv" })
	@Path("/{queryname}/export/csv/{format}")
	public Response getQueryCsvExport(
			@PathParam("username") String username,
			@PathParam("queryname") String queryName,
			@PathParam("format") String format, @QueryParam("exportname") @DefaultValue("") String name){
		//        if (log.isDebugEnabled()) {
		//            log.debug("TRACK\t"  + "\t/query/" + queryName + "/export/csv/"+format+"\tGET");
		//        }
		//        try {
		//            byte[] doc = thinQueryService.getExport(queryName,"csv",format);
		//            if(name == null || name.equals("")) {
		//                 name = SaikuProperties.webExportCsvName;
		//            }
		//
		//            return Response.ok(doc, MediaType.APPLICATION_OCTET_STREAM).header(
		//                    "content-disposition",
		//                    "attachment; filename = " + name + ".csv").header(
		//                    "content-length",doc.length).build();
		//        }
		//        catch (Exception e) {
		//            log.error("Cannot get csv for query (" + queryName + ")",e);
		//            String error = ExceptionUtils.getRootCauseMessage(e);
		//            throw new WebApplicationException(Response.serverError().entity(error).build());
		//        }

		if (log.isDebugEnabled()) {
			log.debug("TRACK\t"  + "\t/query/" + queryName + "/export/csv/"+format+"\tGET");
		}
		try {
			byte[] doc = thinQueryService.getExport(queryName,getCube(username),"csv",format);
			if(name == null || name.equals("")) {
				name = SaikuProperties.webExportCsvName;
			}

			return Response.ok(doc, MediaType.APPLICATION_OCTET_STREAM).header(
					"content-disposition",
					"attachment; filename = " + name + ".csv").header(
							"content-length",doc.length).build();
		}
		catch (Exception e) {
			log.error("Cannot get csv for query (" + queryName + ")",e);
			String error = ExceptionUtils.getRootCauseMessage(e);
			throw new WebApplicationException(Response.serverError().entity(error).build());
		}

	}

	/**
	 * Zoom into a query result table.
	 * @param queryName The query name
	 * @param positionListString The zoom position
	 * @return A new thin query model with a reduced table.
	 */
	@POST
	@Consumes("application/x-www-form-urlencoded")
	@Path("/{queryname}/zoomin")
	public ThinQuery zoomIn(
			@PathParam("username") String username,
			@PathParam("queryname") String queryName,
			@FormParam("selections") String positionListString) {
		//        try {
		//
		//            if (log.isDebugEnabled()) {
		//                log.debug("TRACK\t"  + "\t/query/" + queryName + "/zoomIn\tPUT");
		//            }
		//            List<List<Integer>> realPositions = new ArrayList<>();
		//            if (StringUtils.isNotBlank(positionListString)) {
		//                ObjectMapper mapper = new ObjectMapper();
		//                String[] positions = mapper.readValue(positionListString,
		//                    mapper.getTypeFactory().constructArrayType(String.class));
		//                if (positions != null && positions.length > 0) {
		//                    for (String position : positions) {
		//                        String[] rPos = position.split(":");
		//                        List<Integer> cellPosition = new ArrayList<>();
		//
		//                        for (String p : rPos) {
		//                            Integer pInt = Integer.parseInt(p);
		//                            cellPosition.add(pInt);
		//                        }
		//                        realPositions.add(cellPosition);
		//                    }
		//                }
		//            }
		//            return thinQueryService.zoomIn(queryName, realPositions);
		//
		//        } catch (Exception e){
		//            log.error("Cannot zoom in on query (" + queryName + ")",e);
		//            throw new WebApplicationException(e);
		//        }

		try {

			if (log.isDebugEnabled()) {
				log.debug("TRACK\t"  + "\t/query/" + queryName + "/zoomIn\tPUT");
			}
			List<List<Integer>> realPositions = new ArrayList<>();
			if (StringUtils.isNotBlank(positionListString)) {
				ObjectMapper mapper = new ObjectMapper();
				String[] positions = mapper.readValue(positionListString,
						mapper.getTypeFactory().constructArrayType(String.class));
				if (positions != null && positions.length > 0) {
					for (String position : positions) {
						String[] rPos = position.split(":");
						List<Integer> cellPosition = new ArrayList<>();

						for (String p : rPos) {
							Integer pInt = Integer.parseInt(p);
							cellPosition.add(pInt);
						}
						realPositions.add(cellPosition);
					}
				}
			}
			ThinQuery zoomIn = thinQueryService.zoomIn(queryName, getCube(username), getReport(username), realPositions);
			
			saikuSessionContainer.get().putQuery(zoomIn);
			
			return zoomIn;

		} catch (Exception e){
			log.error("Cannot zoom in on query (" + queryName + ")",e);
			throw new WebApplicationException(e);
		}
	}

	/**
	 * Drill through on the query result set.
	 * @param queryName The query name
	 * @param maxrows The max rows returned
	 * @param position The position
	 * @param returns The returned dimensions and levels
	 * @return A query result set.
	 */
	@GET
	@Produces({"application/json" })
	@Path("/{queryname}/drillthrough")
	public QueryResult drillthrough(
			@PathParam("username") String username,
			@PathParam("queryname") String queryName,
			@QueryParam("maxrows") @DefaultValue("100") Integer maxrows,
			@QueryParam("position") String position,
			@QueryParam("returns") String returns)
	{
		//        if (log.isDebugEnabled()) {
		//            log.debug("TRACK\t"  + "\t/query/" + queryName + "/drillthrough\tGET");
		//        }
		//        QueryResult rsc;
		//        ResultSet rs = null;
		//        try {
		//            Long start = (new Date()).getTime();
		//            if (position == null) {
		//                rs = thinQueryService.drillthrough(queryName, maxrows, returns);
		//            } else {
		//                String[] positions = position.split(":");
		//                List<Integer> cellPosition = new ArrayList<>();
		//
		//                for (String p : positions) {
		//                    Integer pInt = Integer.parseInt(p);
		//                    cellPosition.add(pInt);
		//                }
		//
		//                rs = thinQueryService.drillthrough(queryName, cellPosition, maxrows, returns);
		//            }
		//            rsc = RestUtil.convert(rs);
		//            Long runtime = (new Date()).getTime()- start;
		//            rsc.setRuntime(runtime.intValue());
		//
		//        }
		//        catch (Exception e) {
		//            log.error("Cannot execute query (" + queryName + ")",e);
		//            String error = ExceptionUtils.getRootCauseMessage(e);
		//            rsc =  new QueryResult(error);
		//
		//        }
		//        finally {
		//            if (rs != null) {
		//                Statement statement = null;
		//                try {
		//                    statement = rs.getStatement();
		//                } catch (Exception e) {
		//                    throw new SaikuServiceException(e);
		//                } finally {
		//                    try {
		//                        rs.close();
		//                        if (statement != null) {
		//                            statement.close();
		//                        }
		//                    } catch (Exception ee) {}
		//
		//                }
		//            }
		//        }
		//        return rsc;

		if (log.isDebugEnabled()) {
			log.debug("TRACK\t"  + "\t/query/" + queryName + "/drillthrough\tGET");
		}
		QueryResult rsc;
		ResultSet rs = null;
		try {
			Long start = (new Date()).getTime();
			if (position == null) {
				rs = thinQueryService.drillthrough(queryName, getReport(username), maxrows, returns);
			} else {
				String[] positions = position.split(":");
				List<Integer> cellPosition = new ArrayList<>();

				for (String p : positions) {
					Integer pInt = Integer.parseInt(p);
					cellPosition.add(pInt);
				}

				rs = thinQueryService.drillthrough(queryName, getReport(username), cellPosition, maxrows, returns);
			}
			rsc = RestUtil.convert(rs);
			Long runtime = (new Date()).getTime()- start;
			rsc.setRuntime(runtime.intValue());

		}
		catch (Exception e) {
			log.error("Cannot execute query (" + queryName + ")",e);
			String error = ExceptionUtils.getRootCauseMessage(e);
			rsc =  new QueryResult(error);

		}
		finally {
			if (rs != null) {
				Statement statement = null;
				try {
					statement = rs.getStatement();
				} catch (Exception e) {
					throw new SaikuServiceException(e);
				} finally {
					try {
						rs.close();
						if (statement != null) {
							statement.close();
						}
					} catch (Exception ee) {}

				}
			}
		}
		return rsc;
	}


	/**
	 * Export the drill through to a CSV file for further analysis
	 * @param queryName The query name
	 * @param maxrows The max rows
	 * @param position The position
	 * @param returns The returned dimensions and levels
	 * @return A response containing a CSV file
	 */
	@GET
	@Produces({"text/csv" })
	@Path("/{queryname}/drillthrough/export/csv")
	public Response getDrillthroughExport(
			@PathParam("username") String username,
			@PathParam("queryname") String queryName,
			@QueryParam("maxrows") @DefaultValue("100") Integer maxrows,
			@QueryParam("position") String position,
			@QueryParam("returns") String returns)
	{
		//        if (log.isDebugEnabled()) {
		//            log.debug("TRACK\t"  + "\t/query/" + queryName + "/drillthrough/export/csv (maxrows:" + maxrows + " position" + position + ")\tGET");
		//        }
		//        ResultSet rs = null;
		//
		//        try {
		//            if (position == null) {
		//                rs = thinQueryService.drillthrough(queryName, maxrows, returns);
		//            } else {
		//                String[] positions = position.split(":");
		//                List<Integer> cellPosition = new ArrayList<>();
		//
		//                for (String p : positions) {
		//                    Integer pInt = Integer.parseInt(p);
		//                    cellPosition.add(pInt);
		//                }
		//
		//                rs = thinQueryService.drillthrough(queryName, cellPosition, maxrows, returns);
		//            }
		//            byte[] doc = thinQueryService.exportResultSetCsv(rs);
		//            String name = SaikuProperties.webExportCsvName;
		//            return Response.ok(doc, MediaType.APPLICATION_OCTET_STREAM).header(
		//                    "content-disposition",
		//                    "attachment; filename = " + name + "-drillthrough.csv").header(
		//                    "content-length",doc.length).build();
		//
		//
		//        } catch (Exception e) {
		//            log.error("Cannot export drillthrough query (" + queryName + ")",e);
		//            return Response.serverError().build();
		//        }
		//        finally {
		//            if (rs != null) {
		//                try {
		//                    Statement statement = rs.getStatement();
		//                    statement.close();
		//                    rs.close();
		//                } catch (SQLException e) {
		//                    throw new SaikuServiceException(e);
		//                } finally {
		//                }
		//            }
		//        }

		if (log.isDebugEnabled()) {
			log.debug("TRACK\t"  + "\t/query/" + queryName + "/drillthrough/export/csv (maxrows:" + maxrows + " position" + position + ")\tGET");
		}
		ResultSet rs = null;

		try {
			if (position == null) {
				rs = thinQueryService.drillthrough(queryName, getReport(username), maxrows, returns);
			} else {
				String[] positions = position.split(":");
				List<Integer> cellPosition = new ArrayList<>();

				for (String p : positions) {
					Integer pInt = Integer.parseInt(p);
					cellPosition.add(pInt);
				}

				rs = thinQueryService.drillthrough(queryName, getReport(username), cellPosition, maxrows, returns);
			}
			byte[] doc = thinQueryService.exportResultSetCsv(rs);
			String name = SaikuProperties.webExportCsvName;
			return Response.ok(doc, MediaType.APPLICATION_OCTET_STREAM).header(
					"content-disposition",
					"attachment; filename = " + name + "-drillthrough.csv").header(
							"content-length",doc.length).build();


		} catch (Exception e) {
			log.error("Cannot export drillthrough query (" + queryName + ")",e);
			return Response.serverError().build();
		}
		finally {
			if (rs != null) {
				try {
					Statement statement = rs.getStatement();
					statement.close();
					rs.close();
				} catch (SQLException e) {
					throw new SaikuServiceException(e);
				} finally {
				}
			}
		}
	}

	/**
	 * Export PDF with chart
	 * @param queryName The query.
	 * @param svg The SVG string
	 * @return A response with a PDF file
	 */
	@POST
	@Produces({"application/pdf" })
	@Path("/{queryname}/export/pdf")
	public Response exportPdfWithChart(
			@PathParam("username") String username,
			@PathParam("queryname")  String queryName,
			@PathParam("svg")  @DefaultValue("") String svg)
	{
		return exportPdfWithChartAndFormat(username, queryName, null, svg, null);
	}

	/**
	 * Export table to PDF.
	 * @param queryName The query name
	 * @return A response with a PDF export.
	 */
	@GET
	@Produces({"application/pdf" })
	@Path("/{queryname}/export/pdf")
	public Response exportPdf(@PathParam("username") String username, @PathParam("queryname")  String queryName)
	{
		return exportPdfWithChartAndFormat(username, queryName, null, null, null);
	}

	/**
	 * Export to PDF with cellset format.
	 * @param queryName The query
	 * @param format The cellset format
	 * @param name The name of the export.
	 * @return A response with a PDF
	 */
	@GET
	@Produces({"application/pdf" })
	@Path("/{queryname}/export/pdf/{format}")
	public Response exportPdfWithFormat(
			@PathParam("username") String username,
			@PathParam("queryname")  String queryName,
			@PathParam("format") String format, @QueryParam("exportname") String name)
	{
		return exportPdfWithChartAndFormat(username, queryName, format, null, name);
	}

	/**
	 * Export PDF with chart and cellset format.
	 * @param queryName The query name
	 * @param format The cell set format
	 * @param svg The SVG
	 * @param name The export name
	 * @return A response with a PDF contained.
	 */
	@POST
	@Produces({"application/pdf" })
	@Path("/{queryname}/export/pdf/{format}")
	public Response exportPdfWithChartAndFormat(
			@PathParam("username") String username,
			@PathParam("queryname")  String queryName,
			@PathParam("format") String format,
			@FormParam("svg") @DefaultValue("") String svg, @QueryParam("name") String name)
	{
		//        try {
		//            CellDataSet cellData = thinQueryService.getFormattedResult(queryName, format);
		//            QueryResult queryResult = RestUtil.convert(cellData);
		//            PdfReport pdf = new PdfReport();
		//            byte[] doc  = pdf.createPdf(queryResult, svg);
		//            if(name==null || name.equals("")){
		//                name = "export";
		//            }
		//            return Response.ok(doc).type("application/pdf").header(
		//                    "content-disposition",
		//                    "attachment; filename = "+name+".pdf").header(
		//                    "content-length",doc.length).build();
		//        } catch (Exception e) {
		//            log.error("Error exporting query to  PDF", e);
		//            return Response.serverError().entity(e.getMessage()).status(Status.INTERNAL_SERVER_ERROR).build();
		//        }

		try {
			CellDataSet cellData = thinQueryService.getFormattedResult(queryName, getCube(username), format);
			QueryResult queryResult = RestUtil.convert(cellData);
			PdfReport pdf = new PdfReport();
			byte[] doc  = pdf.createPdf(queryResult, svg);
			if(name==null || name.equals("")){
				name = "export";
			}
			return Response.ok(doc).type("application/pdf").header(
					"content-disposition",
					"attachment; filename = "+name+".pdf").header(
							"content-length",doc.length).build();
		} catch (Exception e) {
			log.error("Error exporting query to  PDF", e);
			return Response.serverError().entity(e.getMessage()).status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}

	/**
	 * Get HTML export
	 * @param queryname The query name
	 * @param format The cellset format
	 * @param css The css stylesheet
	 * @param tableonly Export table only or chart as well
	 * @param wrapcontent Wrap content
	 * @return A response with a HTML export.
	 */
	@GET
	@Produces({"text/html" })
	@Path("/{queryname}/export/html")
//	@ReturnType("java.lang.String")
	public Response exportHtml(
			@PathParam("username") String username,
			@PathParam("queryname") String queryname,
			@QueryParam("format") String format,
			@QueryParam("css") @DefaultValue("false") Boolean css,
			@QueryParam("tableonly") @DefaultValue("false") Boolean tableonly,
			@QueryParam("wrapcontent") @DefaultValue("true") Boolean wrapcontent)
	{
		//        ThinQuery tq = thinQueryService.getContext(queryname).getOlapQuery();
		//        return exportHtml(tq, format, css, tableonly, wrapcontent);

		ThinQuery tq = thinQueryService.getContext(queryname).getOlapQuery();
		return exportHtml(username, tq, format, css, tableonly, wrapcontent);
	}

	/**
	 * Get HTML export
	 * @param tq The current thin query model
	 * @param format The cellset format
	 * @param css The css stylesheet
	 * @param tableonly Export table only or chart as well
	 * @param wrapcontent Wrap content
	 * @return A response with a HTML export.
	 */
	@POST
	@Produces({"text/html" })
	@Path("/export/html")
//	@ReturnType("java.lang.String")
	public Response exportHtml(
			@PathParam("username") String username,
			ThinQuery tq,
			@QueryParam("format") String format,
			@QueryParam("css") @DefaultValue("false") Boolean css,
			@QueryParam("tableonly") @DefaultValue("false") Boolean tableonly,
			@QueryParam("wrapcontent") @DefaultValue("true") Boolean wrapcontent)
	{

		//        try {
		//            CellDataSet cs;
		//            if (StringUtils.isNotBlank(format)) {
		//                cs = thinQueryService.execute(tq, format);
		//            } else {
		//                cs = thinQueryService.execute(tq);
		//            }
		//            QueryResult qr = RestUtil.convert(cs);
		//            String content = JSConverter.convertToHtml(qr, wrapcontent);
		//            String html = "";
		//            if (!tableonly) {
		//                html += "<!DOCTYPE html><html><head><meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\">\n";
		//                if (css) {
		//                    html += "<style>\n";
		//                    InputStream is = JSConverter.class.getResourceAsStream("saiku.table.full.css");
		//                    String cssContent = IOUtils.toString(is);
		//                    html += cssContent;
		//                    html += "</style>\n";
		//                }
		//                html += "</head>\n<body><div class='workspace_results'>\n";
		//            }
		//            html += content;
		//            if (!tableonly) {
		//                html += "\n</div></body></html>";
		//            }
		//            return Response.ok(html).build();
		//        } catch (Exception e) {
		//            log.error("Error exporting query to  HTML", e);
		//            return Response.serverError().entity(e.getMessage()).status(Status.INTERNAL_SERVER_ERROR).build();
		//        }

		try {
			CellDataSet cs;
			if (StringUtils.isNotBlank(format)) {
				cs = thinQueryService.execute(username, tq, getReport(username), getCube(username), format);
			} else {
				cs = thinQueryService.execute(username, tq,getReport(username), getCube(username));
			}
			QueryResult qr = RestUtil.convert(cs);
			String content = JSConverter.convertToHtml(qr, wrapcontent);
			String html = "";
			if (!tableonly) {
				html += "<!DOCTYPE html><html><head><meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\">\n";
				if (css) {
					html += "<style>\n";
					InputStream is = JSConverter.class.getResourceAsStream("saiku.table.full.css");
					String cssContent = IOUtils.toString(is);
					html += cssContent;
					html += "</style>\n";
				}
				html += "</head>\n<body><div class='workspace_results'>\n";
			}
			html += content;
			if (!tableonly) {
				html += "\n</div></body></html>";
			}
			return Response.ok(html).build();
		} catch (Exception e) {
			log.error("Error exporting query to  HTML", e);
			return Response.serverError().entity(e.getMessage()).status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}

	/**
	 * Drill across on a result set
	 * @param queryName The query name
	 * @param position The drill position
	 * @param returns The dimensions and levels returned
	 * @return The new thin query object.
	 */
	@POST
	@Produces({"application/json" })
	@Path("/{queryname}/drillacross")
	public ThinQuery drillacross(
			@PathParam("username") String username,
			@PathParam("queryname") String queryName,
			@FormParam("position") String position,
			@FormParam("drill") String returns)
	{
		//        if (log.isDebugEnabled()) {
		//            log.debug("TRACK\t"  + "\t/query/" + queryName + "/drillacross\tPOST");
		//        }
		//
		//        try {
		//            String[] positions = position.split(":");
		//            List<Integer> cellPosition = new ArrayList<>();
		//            for (String p : positions) {
		//                Integer pInt = Integer.parseInt(p);
		//                cellPosition.add(pInt);
		//            }
		//            ObjectMapper mapper = new ObjectMapper();
		//
		//          CollectionType ct =
		//              mapper.getTypeFactory().constructCollectionType(ArrayList.class, String.class);
		//
		//          JavaType st = mapper.getTypeFactory().uncheckedSimpleType(String.class);
		//
		//
		//            Map<String, List<String>> levels = mapper.readValue(returns, mapper.getTypeFactory().constructMapType(Map.class, st, ct));
		//          return thinQueryService.drillacross(queryName, cellPosition, levels);
		//
		//        }
		//        catch (Exception e) {
		//            log.error("Cannot execute query (" + queryName + ")",e);
		//            String error = ExceptionUtils.getRootCauseMessage(e);
		//            throw new WebApplicationException(Response.serverError().entity(error).build());
		//
		//        }

		if (log.isDebugEnabled()) {
			log.debug("TRACK\t"  + "\t/query/" + queryName + "/drillacross\tPOST");
		}

		try {
			String[] positions = position.split(":");
			List<Integer> cellPosition = new ArrayList<>();
			for (String p : positions) {
				Integer pInt = Integer.parseInt(p);
				cellPosition.add(pInt);
			}
			ObjectMapper mapper = new ObjectMapper();

			CollectionType ct =
					mapper.getTypeFactory().constructCollectionType(ArrayList.class, String.class);

			JavaType st = mapper.getTypeFactory().uncheckedSimpleType(String.class);


			Map<String, List<String>> levels = mapper.readValue(returns, mapper.getTypeFactory().constructMapType(Map.class, st, ct));
			ThinQuery drillacross = thinQueryService.drillacross(queryName, getCube(username), getReport(username), cellPosition, levels);

			saikuSessionContainer.get().putQuery(drillacross);
			
			return drillacross;
		}
		catch (Exception e) {
			log.error("Cannot execute query (" + queryName + ")",e);
			String error = ExceptionUtils.getRootCauseMessage(e);
			throw new WebApplicationException(Response.serverError().entity(error).build());

		}
	}


	private SaikuReport getReport(String username) {
		if(! authenticatorService.get().isAuthenticated())
    		throw new IllegalStateException("not authenticated");
		
		SaikuReport report = saikuSessionContainer.get().getReport(username);
		
		securityService.assertRights(report, Execute.class);
		
		return report;
	}

	private Cube getCube(String username) throws ClassNotFoundException, IOException, SQLException {
		SaikuReport report = getReport(username);
		Cube cube = olapUtilService.getCube(report);
		return cube;
	}

}
