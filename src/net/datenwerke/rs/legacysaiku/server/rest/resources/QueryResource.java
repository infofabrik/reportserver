package net.datenwerke.rs.legacysaiku.server.rest.resources;

import java.io.IOException;
import java.io.StringReader;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.codehaus.jackson.annotate.JsonAutoDetect.Visibility;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.type.TypeFactory;
import org.olap4j.OlapException;
import org.olap4j.metadata.Cube;
import org.legacysaiku.olap.dto.SaikuDimensionSelection;
import org.legacysaiku.olap.dto.SaikuMember;
import org.legacysaiku.olap.dto.SaikuQuery;
import org.legacysaiku.olap.dto.SaikuTag;
import org.legacysaiku.olap.dto.resultset.CellDataSet;
import org.legacysaiku.olap.query.IQuery;
import org.legacysaiku.olap.util.ObjectUtil;
import org.legacysaiku.olap.util.SaikuProperties;
import org.legacysaiku.olap.util.formatter.CellSetFormatter;
import org.legacysaiku.olap.util.formatter.FlattenedCellSetFormatter;
import org.legacysaiku.olap.util.formatter.HierarchicalCellSetFormatter;
import org.legacysaiku.olap.util.formatter.ICellSetFormatter;
import org.legacysaiku.service.util.exception.SaikuServiceException;
import org.legacysaiku.web.svg.PdfReport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.datenwerke.rs.legacysaiku.server.rest.objects.MdxQueryObject;
import net.datenwerke.rs.legacysaiku.server.rest.objects.SavedQuery;
import net.datenwerke.rs.legacysaiku.server.rest.objects.SelectionRestObject;
import net.datenwerke.rs.legacysaiku.server.rest.objects.resultset.QueryResult;
import net.datenwerke.rs.legacysaiku.server.rest.resources.exceptions.SaikuWsException;
import net.datenwerke.rs.legacysaiku.server.rest.util.RestUtil;
import net.datenwerke.rs.legacysaiku.service.saiku.OlapQueryService;
import net.datenwerke.rs.legacysaiku.service.saiku.OlapUtilService;
import net.datenwerke.rs.legacysaiku.service.saiku.SaikuSessionContainer;
import net.datenwerke.rs.saiku.service.saiku.entities.SaikuReport;
import net.datenwerke.rs.legacysaiku.service.saiku.locale.SaikuEngineMessages;

/**
 * QueryServlet contains all the methods required when manipulating an OLAP Query.
 *
 */
@Path("/legacysaiku/{username}/query")
@XmlAccessorType(XmlAccessType.NONE)
public class QueryResource {

	private static final Logger log = LoggerFactory.getLogger(QueryResource.class);

	private OlapQueryService olapQueryService;

	private Provider<SaikuSessionContainer> saikuSessionContainer;

	private OlapUtilService olapUtilService;

	private Provider<PdfReport> pdfReportProvider;
	
	@Inject
	public QueryResource(
			OlapQueryService olapQueryService, 
			Provider<SaikuSessionContainer> saikuSessionContainer, 
    		OlapUtilService olapUtilService, 
    		Provider<PdfReport> pdfReportProvider
    		) {
				this.olapQueryService = olapQueryService;
				this.saikuSessionContainer = saikuSessionContainer;
				this.olapUtilService = olapUtilService;
				this.pdfReportProvider = pdfReportProvider;
	}

	/**
	 * Return a list of open queries.
	 */
	@GET
	@Produces({"application/json" })
	public List<String> getQueries(@PathParam("username") String username) {
		return new ArrayList<String>(saikuSessionContainer.get().getQueries().keySet());
	}

	@GET
	@Produces({"application/json" })
	@Path("/{queryname}")
	public SaikuQuery getQuery(
			@PathParam("queryname") String queryName,
			@PathParam("username") String username){

		if (log.isDebugEnabled()) {
			log.debug("TRACK\t"  + "\t/query/" + queryName + "\tGET");
		}
		return ObjectUtil.convert(getQuery(queryName));
	}

	/**
	 * Delete query from the query pool.
	 * @return a HTTP 410(Works) or HTTP 404(Call failed).
	 */
	@DELETE
	@Path("/{queryname}")
	public Status deleteQuery(
			@PathParam("queryname") String queryName, 
			@PathParam("username") String username){
		if (log.isDebugEnabled()) {
			log.debug("TRACK\t"  + "\t/query/" + queryName + "\tDELETE");
		}
		try{
			saikuSessionContainer.get().removeQuery(queryName);
			return(Status.GONE);
		}
		catch(Exception e){
			log.error("Cannot delete query (" + queryName + ")",e);
			return(Status.NOT_FOUND);
		}
	}

	/**
	 * Create a new Saiku Query.
	 * @param connectionName the name of the Saiku connection.
	 * @param cubeName the name of the cube.
	 * @param catalogName the catalog name.
	 * @param schemaName the name of the schema.
	 * @param queryName the name you want to assign to the query.
	 * 
	 * @return a query model.
	 * @throws SaikuWsException 
	 * 
	 */
	@POST
	@Produces({"application/json" })
	@Path("/{queryname}")
	public SaikuQuery createQuery(
			@FormParam("connection") String connectionName, 
			@FormParam("cube") String cubeName,
			@FormParam("catalog") String catalogName, 
			@FormParam("schema") String schemaName, 
			@FormParam("xml") @DefaultValue("") String xml,
			@PathParam("queryname") String queryName, 
			@PathParam("username") String username
		) throws SaikuWsException  {
		if (log.isDebugEnabled()) {
			log.debug("TRACK\t"  + "\t/query/" + queryName + "\tPOST\t xml:" + (xml == null));
		}
		try {
			Cube cube = getCube(username);
			SaikuReport report = getReport(username);
			connectionName = report.getUuid();
			
			if((null == xml || xml.length() == 0) && report instanceof SaikuReport)
				xml = ((SaikuReport) report).getQueryXml();
			if(null != xml)
				xml = xml.trim();
			
			IQuery query; 
			if (xml != null && xml.length() > 0) {
				query = olapQueryService.createNewOlapQuery(queryName, report, connectionName, cube, xml);
			}else {
				query = olapQueryService.createNewOlapQuery(queryName, report, connectionName, cube);
			}
			saikuSessionContainer.get().putQuery(queryName, report, query);
			return ObjectUtil.convert(query);
		} catch(Exception e) {
			log.info("error during saiku query loading", e);
			throw new SaikuWsException(getFriendlyErrorMessage(e));
		}
		
	}

	private String getFriendlyErrorMessage(Exception e) {
		Throwable cause = e;
		while(null != cause){
			if(cause instanceof OlapException){
				String msg = cause.getMessage();
				if(msg.startsWith("Unable to find a member with name ")){
					msg = SaikuEngineMessages.INSTANCE.errorUnableToFindMember(msg.substring("Unable to find a member with name".length()));
				}
				
				return "[[XX_RS_ERROR]]" + msg + "[[/XX_RS_ERROR]]";
			}
			
			cause = cause.getCause();
		}
		
		return "[[XX_RS_ERROR]]Error creating query: The current variant only supports Mondrian 4. Either RESET the variant or switch the report datasource.[[/XX_RS_ERROR]]";
	}

	@GET
	@Produces({"application/json" })
	@Path("/{queryname}/properties")
	public Properties getProperties(
			@PathParam("queryname") String queryName, 
			@PathParam("username") String username ) {
		if (log.isDebugEnabled()) {
			log.debug("TRACK\t"  + "\t/query/" + queryName + "/properties\tGET");
		}
		
		return getQuery(queryName).getProperties();
	}


	@POST
	@Produces({"application/json" })
	@Path("/{queryname}/properties")
	public Properties setProperties(
			@PathParam("queryname") String queryName, 
			@FormParam("properties") String properties, 
			@PathParam("username") String username) 
	{
		if (log.isDebugEnabled()) {
			log.debug("TRACK\t"  + "\t/query/" + queryName + "/properties\tPOST");
		}
		try {
			Properties props = new Properties();
			StringReader sr = new StringReader(properties);
			props.load(sr);
			getQuery(queryName).setProperties(props);
			return getProperties(queryName, username);
		} catch(Exception e) {
			log.error("Cannot set properties for query (" + queryName + ")",e);
			return null;
		}
	}

	@POST
	@Produces({"application/json" })
	@Path("/{queryname}/properties/{propertyKey}")
	public Properties setProperties(
			@PathParam("queryname") String queryName, 
			@PathParam("propertyKey") String propertyKey, 
			@FormParam("propertyValue") String propertyValue, 
			@PathParam("username") String username) 
	{
		if (log.isDebugEnabled()) {
			log.debug("TRACK\t"  + "\t/query/" + queryName + "/properties/"+ propertyKey + "\tPOST");
		}
		try{
			Properties props = new Properties();
			props.put(propertyKey, propertyValue);
			getQuery(queryName).setProperties(props);
			return getProperties(queryName, username);
		}catch(Exception e){
			log.error("Cannot set property (" + propertyKey + " ) for query (" + queryName + ")",e);
			return null;
		}
	}

	@GET
	@Path("/{queryname}/mdx")
	public MdxQueryObject getMDXQuery(
			@PathParam("queryname") String queryName, 
			@PathParam("username") String username){
		if (log.isDebugEnabled()) {
			log.debug("TRACK\t"  + "\t/query/" + queryName + "/mdx/\tGET");
		}
		try {
			String mdx = getQuery(queryName).getMdx();
			return new MdxQueryObject(mdx);
		}
		catch (Exception e) {
			log.error("Cannot get mdx for query (" + queryName + ")",e);
			return null;
		}
	}
	
	@POST
	@Consumes("application/x-www-form-urlencoded")
	@Path("/{queryname}/mdx")
	public void setMDXQuery(
			@PathParam("queryname") String queryName, 
			@FormParam("mdx") String mdx, 
			@PathParam("username") String username){
		if (log.isDebugEnabled()) {
			log.debug("TRACK\t"  + "\t/query/" + queryName + "/mdx/\tPOST");
		}
		try {
			getQuery(queryName).setMdx(mdx);
		}
		catch (Exception e) {
			log.error("Cannot set mdx for query (" + queryName + ")",e);
		}
	}

	@GET
	@Produces({"application/json" })
	@Path("/{queryname}/xml")
	public SavedQuery getQueryXml(
			@PathParam("queryname") String queryName, 
			@PathParam("username") String username){
		if (log.isDebugEnabled()) {
			log.debug("TRACK\t"  + "\t/query/" + queryName + "/xml/\tGET");
		}
		try {
			String xml = getQuery(queryName).toXml();
			return new SavedQuery(queryName, null, xml);
		}
		catch (Exception e) {
			log.error("Cannot get xml for query (" + queryName + ")",e);
			return null; 
		}
	}

	@GET
	@Produces({"application/vnd.ms-excel" })
	@Path("/{queryname}/export/xls")
	public Response getQueryExcelExport(
			@PathParam("queryname") String queryName, 
			@PathParam("username") String username){
		if (log.isDebugEnabled()) {
			log.debug("TRACK\t"  + "\t/query/" + queryName + "/export/xls/\tGET");
		}
		return getQueryExcelExport(queryName, "flattened", username);
	}

	@GET
	@Produces({"application/vnd.ms-excel" })
	@Path("/{queryname}/export/xls/{format}")
	public Response getQueryExcelExport(
			@PathParam("queryname") String queryName,
			@PathParam("format") @DefaultValue("flattened") String format, 
			@PathParam("username") String username){
		if (log.isDebugEnabled()) {
			log.debug("TRACK\t"  + "\t/query/" + queryName + "/export/xls/"+format+"\tGET");
		}
		try {
			byte[] doc = olapQueryService.getExport(getQuery(queryName),"xls",format);
			String name = SaikuProperties.webExportExcelName + "." + SaikuProperties.webExportExcelFormat;
			return Response.ok(doc, MediaType.APPLICATION_OCTET_STREAM).header(
					"content-disposition",
					"attachment; filename = " + name).header(
							"content-length",doc.length).build();
		}
		catch (Exception e) {
			log.error("Cannot get excel for query (" + queryName + ")",e);
			return Response.serverError().build();
		}
	}

	@GET
	@Produces({"text/csv" })
	@Path("/{queryname}/export/csv")
	public Response getQueryCsvExport(
			@PathParam("queryname") String queryName, 
			@PathParam("username") String username){
		if (log.isDebugEnabled()) {
			log.debug("TRACK\t"  + "\t/query/" + queryName + "/export/csv\tGET");
		}
		return getQueryCsvExport(queryName, "flattened", username);
	}

	@GET
	@Produces({"text/csv" })
	@Path("/{queryname}/export/csv/{format}")
	public Response getQueryCsvExport(
			@PathParam("queryname") String queryName,
			@PathParam("format") String format, 
			@PathParam("username") String username){
		if (log.isDebugEnabled()) {
			log.debug("TRACK\t"  + "\t/query/" + queryName + "/export/csv/"+format+"\tGET");
		}
		try {
			byte[] doc = olapQueryService.getExport(getQuery(queryName),"csv",format);
			String name = SaikuProperties.webExportCsvName;
			return Response.ok(doc, MediaType.APPLICATION_OCTET_STREAM).header(
					"content-disposition",
					"attachment; filename = " + name + ".csv").header(
							"content-length",doc.length).build();
		}
		catch (Exception e) {
			log.error("Cannot get csv for query (" + queryName + ")",e);
			return Response.serverError().build();
		}
	}
	


	@POST
	@Produces({"application/pdf" })
	@Path("/{queryname}/export/pdf")
	public Response exportPdfWithChart(
			@PathParam("queryname")  String queryName,
			@PathParam("svg")  @DefaultValue("") String svg, 
			@PathParam("username") String username)
	{
		return exportPdfWithChartAndFormat(queryName, null, svg, username);
	}
		
	@GET
	@Produces({"application/pdf" })
	@Path("/{queryname}/export/pdf")
	public Response exportPdf(
			@PathParam("queryname")  String queryName, 
			@PathParam("username") String username)
	{
		return exportPdfWithChartAndFormat(queryName, null, null, username);
	}

	@GET
	@Produces({"application/pdf" })
	@Path("/{queryname}/export/pdf/{format}")
	public Response exportPdfWithFormat(
			@PathParam("queryname")  String queryName,
			@PathParam("format") String format, 
			@PathParam("username") String username)
	{
		return exportPdfWithChartAndFormat(queryName, format, null, username);
	}
	
	@POST
	@Produces({"application/pdf" })
	@Path("/{queryname}/export/pdf/{format}")
	public Response exportPdfWithChartAndFormat(
			@PathParam("queryname")  String queryName,
			@PathParam("format") String format,
			@FormParam("svg") @DefaultValue("") String svg, 
			@PathParam("username") String username)
	{
		
		try {
			PdfReport pdf = pdfReportProvider.get();
			CellDataSet cs = null;
			if (StringUtils.isNotBlank(format)) {
				cs = olapQueryService.execute(getQuery(queryName), format);
			} else {
				cs = olapQueryService.execute(getQuery(queryName));
			}
			
			byte[] doc  = pdf.pdf(cs, svg);
			return Response.ok(doc).type("application/pdf").header(
					"content-disposition",
					"attachment; filename = export.pdf").header(
							"content-length",doc.length).build();
		} catch (Exception e) {
			log.error("Error exporting query to  PDF", e);
			return Response.serverError().entity(e.getMessage()).status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}

	@DELETE
	@Path("/{queryname}/result")
	public Status cancel(
			@PathParam("queryname") String queryName, 
			@PathParam("username") String username){
		if (log.isDebugEnabled()) {
			log.debug("TRACK\t"  + "\t/query/" + queryName + "/result\tDELETE");
		}
		try {

			getQuery(queryName).cancel();
			return Response.Status.OK;
		}
		catch (Exception e) {
			log.error("Cannot execute query (" + queryName + ")",e);
			return Response.Status.INTERNAL_SERVER_ERROR;
		}
	}
	
	@GET
	@Produces({"application/json" })
	@Path("/{queryname}/result")
	public QueryResult execute(
			@PathParam("queryname") String queryName, 
			@QueryParam("limit") @DefaultValue("0") int limit, 
			@PathParam("username") String username){
		if (log.isDebugEnabled()) {
			log.debug("TRACK\t"  + "\t/query/" + queryName + "/result\tGET");
		}
		try {

			CellDataSet cs = olapQueryService.execute(getQuery(queryName));
			return RestUtil.convert(cs, limit);
		}
		catch (Exception e) {
			log.error("Cannot execute query (" + queryName + ")",e);
			String error = ExceptionUtils.getRootCauseMessage(e);
			return new QueryResult(error);
		}
	}

	@POST
	@Produces({"application/json" })
	@Path("/{queryname}/result/{format}")
	public QueryResult executeMdx(
			@PathParam("queryname") String queryName,
			@PathParam("format") String formatter,
			@FormParam("mdx") String mdx, 
			@FormParam("limit") @DefaultValue("0") int limit, 
			@PathParam("username") String username) 
	{
		if (log.isDebugEnabled()) {
			log.debug("TRACK\t"  + "\t/query/" + queryName + "/result"+formatter+"\tPOST");
		}
		try {
			ICellSetFormatter icf;
			formatter = formatter == null ? "" : formatter.toLowerCase(); 
			if(formatter.equals("flat")) {
				icf = new CellSetFormatter();
			}
			else if (formatter.equals("hierarchical")) {
				icf = new HierarchicalCellSetFormatter();
			}
			else if (formatter.equals("flattened")) {
				icf = new FlattenedCellSetFormatter();
			} else {
				icf = new FlattenedCellSetFormatter();
			}

			IQuery query = olapQueryService.qm2mdx(getQuery(queryName));
			CellDataSet cs = olapQueryService.executeMdx(query ,mdx, icf);
			
			query.setMdx(mdx);
			saikuSessionContainer.get().putQuery(queryName, query);
			
			return RestUtil.convert(cs, limit);
		}
		catch (Exception e) {
			log.error("Cannot execute query (" + queryName + ") using mdx:\n" + mdx,e);
			String error = ExceptionUtils.getRootCauseMessage(e);
			return new QueryResult(error);
		}
	}
		
	@POST
	@Produces({"application/json" })
	@Path("/{queryname}/result")
	public QueryResult executeMdx(
			@PathParam("queryname") String queryName,
			@FormParam("mdx") String mdx,
			@FormParam("limit") @DefaultValue("0") int limit, 
			@PathParam("username") String username)
	{
		if (log.isDebugEnabled()) {
			log.debug("TRACK\t"  + "\t/query/" + queryName + "/result\tPOST\t"+mdx);
		}
		try {
			IQuery query = olapQueryService.qm2mdx(getQuery(queryName));
			saikuSessionContainer.get().putQuery(queryName, query);
			CellDataSet cs = olapQueryService.executeMdx(query,mdx);
			return RestUtil.convert(cs, limit);
		}
		catch (Exception e) {
			log.error("Cannot execute query (" + queryName + ") using mdx:\n" + mdx,e);
			String error = ExceptionUtils.getRootCauseMessage(e);
			return new QueryResult(error);
		}
	}
	
	@GET
	@Produces({"application/json" })
	@Path("/{queryname}/result/metadata/dimensions/{dimension}/hierarchies/{hierarchy}/levels/{level}")
	public Response getLevelMembers(
			@PathParam("queryname") String queryName, 
			@PathParam("dimension") String dimensionName, 
			@PathParam("hierarchy") String hierarchyName,
			@PathParam("level") String levelName,
			@QueryParam("result") @DefaultValue("true") boolean result, 
			@PathParam("username") String username)
	{
		if (log.isDebugEnabled()) {
			log.debug("TRACK\t" 
					+ "\t/query/" + queryName + "/result/metadata/dimensions/" + dimensionName 
					+ "/hierarchies/" + hierarchyName + "/levels/" + levelName + "\tGET");
		}
		try {
			List<SaikuMember> ms = olapQueryService.getResultMetadataMembers(getQuery(queryName), result, dimensionName, hierarchyName, levelName);
			return Response.ok(ms).build();
		}
		catch (Exception e) {
			log.error("Cannot execute query (" + queryName + ")",e);
			String error = ExceptionUtils.getRootCauseMessage(e);
			return Response.serverError().entity(error).build();
		}
	}

	@POST
	@Produces({"application/json" })
	@Path("/{queryname}/qm2mdx")
	public SaikuQuery transformQm2Mdx(
			@PathParam("queryname") String queryName, 
			@PathParam("username") String username)
	{
		if (log.isDebugEnabled()) {
			log.debug("TRACK\t"  + "\t/query/" + queryName + "/qm2mdx\tPOST\t");
		}
		try {
			IQuery query = olapQueryService.qm2mdx(getQuery(queryName));
			saikuSessionContainer.get().putQuery(queryName, query);
			return ObjectUtil.convert(query);
		}
		catch (Exception e) {
			log.error("Cannot transform Qm2Mdx query (" + queryName + ")",e);
		}
		return null;
	}
	
	@GET
	@Produces({"application/json" })
	@Path("/{queryname}/explain")
	public QueryResult getExplainPlan(
			@PathParam("queryname") String queryName, 
			@PathParam("username") String username)
	{
		if (log.isDebugEnabled()) {
			log.debug("TRACK\t"  + "\t/query/" + queryName + "/explain\tGET");
		}
		QueryResult rsc;
		ResultSet rs = null;
		try {
			Long start = (new Date()).getTime();
			rs = olapQueryService.explain(getQuery(queryName));
			rsc = RestUtil.convert(rs);
			Long runtime = (new Date()).getTime()- start;
			rsc.setRuntime(runtime.intValue());

		}
		catch (Exception e) {
			log.error("Cannot get explain plan for query (" + queryName + ")",e);
			String error = ExceptionUtils.getRootCauseMessage(e);
			rsc =  new QueryResult(error);

		}
		// no need to close resultset, its an EmptyResultset
		return rsc;

	}

	@GET
	@Produces({"application/json" })
	@Path("/{queryname}/drillthrough")
	public QueryResult drillthrough(
			@PathParam("queryname") String queryName, 
			@QueryParam("maxrows") @DefaultValue("100") Integer maxrows,
			@QueryParam("position") String position,
			@QueryParam("returns") String returns, 
			@PathParam("username") String username)
	{
		if (log.isDebugEnabled()) {
			log.debug("TRACK\t"  + "\t/query/" + queryName + "/drillthrough\tGET");
		}
		QueryResult rsc;
		ResultSet rs = null;
		try {
			Long start = (new Date()).getTime();
			if (position == null) {
				rs = olapQueryService.drillthrough(getQuery(queryName), maxrows, returns);
			} else {
				String[] positions = position.split(":");
				List<Integer> cellPosition = new ArrayList<Integer>();

				for (String p : positions) {
					Integer pInt = Integer.parseInt(p);
					cellPosition.add(pInt);
				}

				rs = olapQueryService.drillthrough(getQuery(queryName), cellPosition, maxrows, returns);
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
				Connection con = null;
				try {
					 statement = rs.getStatement();
					 con = rs.getStatement().getConnection();
				} catch (Exception e) {
					throw new SaikuServiceException(e);
				} finally {
					try {
						rs.close();
						if (statement != null) {
							statement.close();
						}
					} catch (Exception ee) {};

					rs = null;
				}
			}
		}
		return rsc;
	}


//	@GET
//	@Produces({"text/csv" })
//	@Path("/{queryname}/drillthrough/export/csv")
//	public Response getDrillthroughExport(			
//			@PathParam("queryname") String queryName, 
//			@QueryParam("maxrows") @DefaultValue("100") Integer maxrows,
//			@QueryParam("position") String position,
//			@QueryParam("returns") String returns, 
//			@PathParam("username") String username)
//	{
//		if (log.isDebugEnabled()) {
//			log.debug("TRACK\t"  + "\t/query/" + queryName + "/drillthrough/export/csv (maxrows:" + maxrows + " position" + position + ")\tGET");
//		}
//		
//		
//		ResultSet rs = null;
//
//		try {
//			if (position == null) {
//				rs = olapQueryService.drillthrough(getQuery(queryName), maxrows, returns);
//			} else {
//				String[] positions = position.split(":");
//				List<Integer> cellPosition = new ArrayList<Integer>();
//
//				for (String p : positions) {
//					Integer pInt = Integer.parseInt(p);
//					cellPosition.add(pInt);
//				}
//
//				rs = olapQueryService.drillthrough(getQuery(queryName), cellPosition, maxrows, returns);
//			}
//			byte[] doc = olapQueryService.exportResultSetCsv(rs);
//			String name = SaikuProperties.webExportCsvName;
//			return Response.ok(doc, MediaType.APPLICATION_OCTET_STREAM).header(
//					"content-disposition",
//					"attachment; filename = " + name + "-drillthrough.csv").header(
//							"content-length",doc.length).build();
//
//
//		} catch (Exception e) {
//			log.error("Cannot export drillthrough query (" + queryName + ")",e);
//			return Response.serverError().build();
//		}
//		finally {
//			if (rs != null) {
//				try {
//					Statement statement = rs.getStatement();
//					statement.close();
//					rs.close();
//				} catch (SQLException e) {
//					throw new SaikuServiceException(e);
//				} finally {
//					rs = null;
//				}
//			}
//		}
//	}
	
	@GET
	@Produces({"application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"})
	@Path("/{queryname}/drillthrough/export/csv")
	/* Dont use csv - always return drills as excel */
	public Response getDrillthroughExportCsv(			
			@PathParam("queryname") String queryName, 
			@QueryParam("maxrows") @DefaultValue("100") Integer maxrows,
			@QueryParam("position") String position,
			@QueryParam("returns") String returns, 
			@PathParam("username") String username)
	{
		return getDrillthroughExportExcel(queryName, maxrows, position, returns, username);
	}

	@GET
	@Produces({"application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"})
	@Path("/{queryname}/drillthrough/export/xls")
	public Response getDrillthroughExportExcel(			
			@PathParam("queryname") String queryName, 
			@QueryParam("maxrows") @DefaultValue("100") Integer maxrows,
			@QueryParam("position") String position,
			@QueryParam("returns") String returns, 
			@PathParam("username") String username)
	{
		if (log.isDebugEnabled()) {
			log.debug("TRACK\t"  + "\t/query/" + queryName + "/drillthrough/export/xls (maxrows:" + maxrows + " position" + position + ")\tGET");
		}
		ResultSet rs = null;

		try {
			if (position == null) {
				rs = olapQueryService.drillthrough(getQuery(queryName), maxrows, returns);
			} else {
				String[] positions = position.split(":");
				List<Integer> cellPosition = new ArrayList<Integer>();

				for (String p : positions) {
					Integer pInt = Integer.parseInt(p);
					cellPosition.add(pInt);
				}

				rs = olapQueryService.drillthrough(getQuery(queryName), cellPosition, maxrows, returns);
			}
			byte[] doc = olapQueryService.exportResultSetXLS(rs);
			String name = SaikuProperties.webExportCsvName;
			return Response.ok(doc, MediaType.APPLICATION_OCTET_STREAM).header(
					"content-disposition",
					"attachment; filename = " + name + "-drillthrough.xlsx").header(
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
					rs = null;
				}
			}
		}
	}

	
	@GET
	@Produces({"application/json" })
	@Path("/{queryname}/result/{format}")
	public QueryResult execute(
			@PathParam("queryname") String queryName,
			@PathParam("format") String formatter,
			@QueryParam("limit") @DefaultValue("0") int limit, 
			@PathParam("username") String username)
	{
		if (log.isDebugEnabled()) {
			log.debug("TRACK\t"  + "\t/query/" + queryName + "/result"+formatter+"\tGET");
		}
		try {
			SaikuReport report = getReport(username);
			if(null != report)
				report.setHideParents("flattened".equals(formatter));
			
			CellDataSet cs = olapQueryService.execute(getQuery(queryName),formatter);
			return RestUtil.convert(cs, limit);
		}
		catch (Exception e) {
			log.error("Cannot execute query (" + queryName + ")",e);
			String error = ExceptionUtils.getRootCauseMessage(e);
			return new QueryResult(error);
		}
	}

	/*
	 * Axis Methods.
	 */

	/**
	 * Return a list of dimensions for an axis in a query.
	 * @param queryName the name of the query.
	 * @param axisName the name of the axis.
	 * @return a list of available dimensions.
	 */
	@GET
	@Produces({"application/json" })
	@Path("/{queryname}/axis/{axis}")
	public List<SaikuDimensionSelection> getAxisInfo(
			@PathParam("queryname") String queryName, 
			@PathParam("axis") String axisName, 
			@PathParam("username") String username)
	{
		if (log.isDebugEnabled()) {
			log.debug("TRACK\t"  + "\t/query/" + queryName + "/axis/"+axisName+"\tGET");
		}
		return olapQueryService.getAxisSelection(getQuery(queryName), axisName);
	}

	/**
	 * Remove all dimensions and selections on an axis
	 * @param queryName the name of the query.
	 * @param axisName the name of the axis.
	 */
	@DELETE
	@Produces({"application/json" })
	@Path("/{queryname}/axis/{axis}")
	public Response clearAxis(
			@PathParam("queryname") String queryName, 
			@PathParam("axis") String axisName, 
			@PathParam("username") String username)
	{
		try {
			if (log.isDebugEnabled()) {
				log.debug("TRACK\t"  + "\t/query/" + queryName + "/axis/"+axisName+"\tDELETE");
			}
			axisName = StringUtils.isNotBlank(axisName) ? axisName.toUpperCase() : null;
			if (axisName != null) {
				IQuery query = getQuery(queryName);
				query.clearAxis(axisName);
				return Response.ok().entity(ObjectUtil.convert(query)).build();
				
			}
			throw new Exception("Clear Axis: Axis name cannot be null");
		} catch(Exception e) {
			log.error("Cannot clear axis for query (" + queryName + ")",e);
			return Response.serverError().entity(e.getMessage()).status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}

	@DELETE
	@Produces({"application/json" })
	@Path("/{queryname}/axis/")
	public void clearAllAxisSelections(
			@PathParam("queryname") String queryName, 
			@PathParam("username") String username)
	{
		if (log.isDebugEnabled()) {
			log.debug("TRACK\t"  + "\t/query/" + queryName + "/axis\tDELETE");
		}
		getQuery(queryName).resetQuery();
	}

	@PUT
	@Produces({"application/json" })
	@Path("/{queryname}/swapaxes")
	public SaikuQuery swapAxes(
			@PathParam("queryname") String queryName, 
			@PathParam("username") String username)	
	{
		if (log.isDebugEnabled()) {
			log.debug("TRACK\t"  + "\t/query/" + queryName + "/swapaxes\tPUT");
		}
		IQuery query = olapQueryService.swapAxes(getQuery(queryName));
		return ObjectUtil.convert(query);
	}

	@POST
	@Produces({"application/json" })
	@Path("/{queryname}/cell/{position}/{value}")
	public Status setCell(
			@PathParam("queryname") String queryName,
			@PathParam("position") String position,
			@PathParam("value") String value, 
			@PathParam("username") String username)	
	{
		if (log.isDebugEnabled()) {
			log.debug("TRACK\t"  + "\t/query/" + queryName + "/cell/" + position+ "/" + value + "\tGET");
		}
		String[] positions = position.split(":");
		List<Integer> cellPosition = new ArrayList<Integer>();

		for (String p : positions) {
			Integer pInt = Integer.parseInt(p);
			cellPosition.add(pInt);
		}

		olapQueryService.setCellValue(getQuery(queryName), cellPosition, value, null);
		return Status.OK;

	}


	/*
	 * Dimension Methods
	 */


	/**
	 * Return a dimension and its selections for an axis in a query.
	 * @param queryName the name of the query.
	 * @param axis the name of the axis.
	 * @param dimension the name of the axis.
	 * @return a list of available dimensions.
	 */
	@GET
	@Produces({"application/json" })
	@Path("/{queryname}/axis/{axis}/dimension/{dimension}") 
	public SaikuDimensionSelection getAxisDimensionInfo(
			@PathParam("queryname") String queryName, 
			@PathParam("axis") String axis,
			@PathParam("dimension") String dimension, 
			@PathParam("username") String username)
	{
    try {
		  if (log.isDebugEnabled()) {
			  log.debug("TRACK\t"  + "\t/query/" + queryName + "/axis/"+axis+"/dimension/"+dimension +"\tGET");
		  }
		  return olapQueryService.getAxisDimensionSelections(getQuery(queryName), axis, dimension);
    } catch (Exception e) {
      log.error("Cannot decode dimension " + dimension + " for query (" + queryName + ")", e);
		  return olapQueryService.getAxisDimensionSelections(getQuery(queryName), axis, dimension);
    }
	}

	/**
	 * Move a dimension from one axis to another.
	 * @param queryName the name of the query.
	 * @param axisName the name of the axis.
	 * @param dimensionName the name of the dimension. 
	 * 
	 * @return HTTP 200 or HTTP 500.
	 * 
	 */
	@POST
	@Path("/{queryname}/axis/{axis}/dimension/{dimension}")
	public Response moveDimension(
			@PathParam("queryname") String queryName, 
			@PathParam("axis") String axisName, 
			@PathParam("dimension") String dimensionName, 
			@FormParam("position") @DefaultValue("-1") int position, 
			@PathParam("username") String username)
	{
		try{
		  if (log.isDebugEnabled()) {
			  log.debug("TRACK\t"  + "\t/query/" + queryName + "/axis/"+axisName+"/dimension/"+dimensionName+"\tPOST");
		  }
			olapQueryService.moveDimension(getQuery(queryName), axisName, dimensionName, position);
			return Response.ok().build();
		} catch(Exception e) {
			log.error("Cannot move dimension "+ dimensionName+ " for query (" + queryName + ")",e);
			return Response.serverError().entity(e.getMessage()).status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}

	/**
	 * Delete a dimension.
	 */
	@DELETE
	@Path("/{queryname}/axis/{axis}/dimension/{dimension}")
	public Response deleteDimension(
			@PathParam("queryname") String queryName, 
			@PathParam("axis") String axisName, 
			@PathParam("dimension") String dimensionName, 
			@PathParam("username") String username)
	{
		try {
			if (log.isDebugEnabled()) {
				log.debug("TRACK\t"  + "\t/query/" + queryName + "/axis/"+axisName+"/dimension/"+dimensionName+"\tDELETE");
			}
			olapQueryService.removeDimension(getQuery(queryName), axisName, dimensionName);
			return Response.ok().build();
		} catch(Exception e){
			log.error("Cannot remove dimension "+ dimensionName+ " for query (" + queryName + ")",e);
			return Response.serverError().entity(e.getMessage()).status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}

	@PUT
	@Consumes("application/x-www-form-urlencoded")
	@Path("/{queryname}/axis/{axis}/dimension/{dimension}/")
	public Response updateSelections(
			@PathParam("queryname") String queryName,
			@PathParam("axis") String axisName, 
			@PathParam("dimension") String dimensionName, 
			@FormParam("selections") String selectionJSON, 
			@PathParam("username") String username
			) {
		try{
		  if (log.isDebugEnabled()) {
			  log.debug("TRACK\t"  + "\t/query/" + queryName + "/axis/"+axisName+"/dimension/"+dimensionName +"\tPUT\t");
		  }

			if (selectionJSON != null) {
				ObjectMapper mapper = new ObjectMapper();
				List<SelectionRestObject> selections = mapper.readValue(selectionJSON, TypeFactory.collectionType(ArrayList.class, SelectionRestObject.class));

				// remove stuff first, then add, removing removes all selections for that level first
				for (SelectionRestObject selection : selections) {
					if (selection.getType() != null && "member".equals(selection.getType().toLowerCase())) {
						if (selection.getAction() != null && "delete".equals(selection.getAction().toLowerCase())) {
							olapQueryService.removeMember(getQuery(queryName), dimensionName, selection.getUniquename(), "MEMBER");
						}
					}
					if (selection.getType() != null && "level".equals(selection.getType().toLowerCase())) {
						if (selection.getAction() != null && "delete".equals(selection.getAction().toLowerCase())) {
							olapQueryService.removeLevel(getQuery(queryName), dimensionName, selection.getHierarchy(), selection.getUniquename());
						}
					}
				}
				for (SelectionRestObject selection : selections) {
					if (selection.getType() != null && "member".equals(selection.getType().toLowerCase())) {
						if (selection.getAction() != null && "add".equals(selection.getAction().toLowerCase())) {
							olapQueryService.includeMember(getQuery(queryName), dimensionName, selection.getUniquename(), "MEMBER", -1);
						}
					}
					if (selection.getType() != null && "level".equals(selection.getType().toLowerCase())) {
						if (selection.getAction() != null && "add".equals(selection.getAction().toLowerCase())) {
							olapQueryService.includeLevel(getQuery(queryName), dimensionName, selection.getHierarchy(), selection.getUniquename());
						}
					}
				}
				SaikuDimensionSelection dimsels = getAxisDimensionInfo(queryName, axisName, dimensionName, username);
				if (dimsels != null && dimsels.getSelections().size() == 0) {
					moveDimension(queryName, "UNUSED", dimensionName, -1, username);
				}
				return Response.ok().build();
			}
			throw new Exception("Form did not contain 'selections' parameter");
		} catch (Exception e){
			log.error("Cannot updates selections for query (" + queryName + ")",e);
			return Response.serverError().entity(e.getMessage()).status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	

	@DELETE
	@Consumes("application/x-www-form-urlencoded")
	@Path("/{queryname}/axis/{axis}/dimension/{dimension}/member/")
	public Response removeMembers(
			@PathParam("queryname") String queryName,
			@PathParam("axis") String axisName, 
			@PathParam("dimension") String dimensionName, 
			MultivaluedMap<String, String> formParams, 
			@PathParam("username") String username) {
		try{
		  if (log.isDebugEnabled()) {
			  log.debug("TRACK\t"  + "\t/query/" + queryName + "/axis/"+axisName+"/dimension/"+ dimensionName +"\tPUT");
		  }
			if (formParams.containsKey("selections")) {
				LinkedList<String> sels = (LinkedList<String>) formParams.get("selections");
				String selectionJSON = (String) sels.getFirst();
				ObjectMapper mapper = new ObjectMapper(); // can reuse, share globally
				List<SelectionRestObject> selections = mapper.readValue(selectionJSON, TypeFactory.collectionType(ArrayList.class, SelectionRestObject.class));
				for (SelectionRestObject member : selections) {
					removeMember("MEMBER", queryName, axisName, dimensionName, member.getUniquename(), username);
				}
				return Response.ok().build();
			}
			throw new Exception("Form did not contain 'selections' parameter");
		} catch (Exception e){
			log.error("Cannot updates selections for query (" + queryName + ")",e);
			return Response.serverError().entity(e.getMessage()).status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}
	/**
	 * Move a member.
	 */
	@POST
	@Path("/{queryname}/axis/{axis}/dimension/{dimension}/member/{member}")
	public Response includeMember(
			@FormParam("selection") @DefaultValue("MEMBER") String selectionType, 
			@PathParam("queryname") String queryName,
			@PathParam("axis") String axisName, 
			@PathParam("dimension") String dimensionName, 
			@PathParam("member") String uniqueMemberName, 
			@FormParam("position") @DefaultValue("-1") int position, 
			@FormParam("memberposition") @DefaultValue("-1") int memberposition, 
			@PathParam("username") String username)
	{
		try{
		  if (log.isDebugEnabled()) {
			  log.debug("TRACK\t"  + "\t/query/" + queryName + "/axis/"+axisName+"/dimension/"+dimensionName+"/member/"+ uniqueMemberName +"\tPOST");
		  }
			olapQueryService.moveDimension(getQuery(queryName), axisName, dimensionName, position);

			boolean ret = olapQueryService.includeMember(getQuery(queryName), dimensionName, uniqueMemberName, selectionType, memberposition);
			if(ret == true){
				return Response.ok().status(Status.CREATED).build();
			}
			else{
				throw new Exception("Couldn't include member "+ dimensionName);
				
			}
		} catch (Exception e){
			log.error("Cannot include member "+ dimensionName+ " for query (" + queryName + ")",e);
			return Response.serverError().entity(e.getMessage()).status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}

	@DELETE
	@Path("/{queryname}/axis/{axis}/dimension/{dimension}/member/{member}")
	public Response removeMember(
			@FormParam("selection") @DefaultValue("MEMBER") String selectionType, 
			@PathParam("queryname") String queryName,
			@PathParam("axis") String axisName, 
			@PathParam("dimension") String dimensionName, 
			@PathParam("member") String uniqueMemberName, 
			@PathParam("username") String username)
	{

		try{
		  if (log.isDebugEnabled()) {
			  log.debug("TRACK\t"  + "\t/query/" + queryName + "/axis/"+axisName+"/dimension/"+ dimensionName +"/member/"+ uniqueMemberName +"\tDELETE");
		  }
			boolean ret = olapQueryService.removeMember(getQuery(queryName), dimensionName , uniqueMemberName , selectionType);
			if(ret == true){
				SaikuDimensionSelection dimsels = olapQueryService.getAxisDimensionSelections(getQuery(queryName), axisName, dimensionName);
				if (dimsels != null && dimsels.getSelections().size() == 0) {
					olapQueryService.moveDimension(getQuery(queryName), "UNUSED", dimensionName, -1);
				}
				return Response.ok().build();
			}
			else{
				throw new Exception("Cannot remove member "+ dimensionName+ " for query (" + queryName + ")");
			}
		} catch (Exception e){
			log.error("Cannot remove member "+ dimensionName+ " for query (" + queryName + ")",e);
			return Response.serverError().entity(e.getMessage()).status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@PUT
	@Path("/{queryname}/axis/{axis}/dimension/{dimension}/children")
	public Response includeChildren(
			@PathParam("queryname") String queryName,
			@PathParam("axis") String axisName, 
			@PathParam("dimension") String dimensionName, 
			@FormParam("member") String uniqueMemberName, 
			@PathParam("username") String username)
	{
		
		try{
			if (log.isDebugEnabled()) {
				log.debug("TRACK\t"  + "\t/query/" + queryName + "/axis/"+axisName+"/dimension/"+dimensionName+"/children/"+uniqueMemberName+"\tPOST");
			}

			boolean ret = olapQueryService.includeChildren(getQuery(queryName), dimensionName, uniqueMemberName);
			if(ret == true){
				return Response.ok().status(Status.CREATED).build();
			}
			else{
				throw new Exception("Couldn't include children for "+ uniqueMemberName);
				
			}
		} catch (Exception e){
			log.error("Cannot include children for "+ dimensionName+ " for query (" + queryName + ")",e);
			return Response.serverError().entity(e.getMessage()).status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@DELETE
	@Path("/{queryname}/axis/{axis}/dimension/{dimension}/children")
	public Response removeChildren(
			@PathParam("queryname") String queryName,
			@PathParam("axis") String axisName, 
			@PathParam("dimension") String dimensionName, 
			@FormParam("member") String uniqueMemberName, 
			@PathParam("username") String username)
	{
		if (log.isDebugEnabled()) {
			log.debug("TRACK\t"  + "\t/query/" + queryName + "/axis/"+axisName+"/dimension/"+dimensionName+"/children/"+uniqueMemberName+"\tDELETE");
		}
		try{
			boolean ret = olapQueryService.includeChildren(getQuery(queryName), dimensionName, uniqueMemberName);
			if(ret == true){
				return Response.ok().status(Status.CREATED).build();
			}
			else{
				throw new Exception("Couldn't remove children for "+ uniqueMemberName);
				
			}
		} catch (Exception e){
			log.error("Cannot include children for "+ dimensionName+ " for query (" + queryName + ")",e);
			return Response.serverError().entity(e.getMessage()).status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}


	@POST
	@Path("/{queryname}/axis/{axis}/dimension/{dimension}/hierarchy/{hierarchy}/{level}")
	public Response includeLevel(
			@PathParam("queryname") String queryName,
			@PathParam("axis") String axisName, 
			@PathParam("dimension") String dimensionName, 
			@PathParam("hierarchy") String uniqueHierarchyName, 
			@PathParam("level") String uniqueLevelName, 
			@FormParam("position") @DefaultValue("-1") int position, 
			@PathParam("username") String username)
	{

		try{
		  if (log.isDebugEnabled()) {
			  log.debug("TRACK\t"  + "\t/query/" + queryName + "/axis/"+axisName+"/dimension/"+ dimensionName +"/hierarchy/"+ uniqueHierarchyName +"/"+ uniqueLevelName +"\tPOST");
		  }
			olapQueryService.moveDimension(getQuery(queryName), axisName, dimensionName, position);
			boolean ret = olapQueryService.includeLevel(getQuery(queryName), dimensionName, uniqueHierarchyName, uniqueLevelName);
			if(ret == true){
				return Response.ok().status(Status.CREATED).build();
			}
			else{
				throw new Exception("Something went wrong including level: " + uniqueLevelName);
			}
		} catch (Exception e){
			log.error("Cannot include level of hierarchy "+ uniqueHierarchyName+ " for query (" + queryName + ")",e);
			return Response.serverError().entity(e.getMessage()).status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}
	

	@DELETE
	@Path("/{queryname}/axis/{axis}/dimension/{dimension}/hierarchy/{hierarchy}/{level}")
	public Response removeLevel(
			@PathParam("queryname") String queryName,
			@PathParam("axis") String axisName, 
			@PathParam("dimension") String dimensionName, 
			@PathParam("hierarchy") String uniqueHierarchyName, 
			@PathParam("level") String uniqueLevelName, 
			@PathParam("username") String username)
	{
		try{
		  if (log.isDebugEnabled()) {
			  log.debug("TRACK\t"  + "\t/query/" + queryName + "/axis/"+axisName+"/dimension/"+dimensionName+"/hierarchy/"+ uniqueHierarchyName +"/"+ uniqueLevelName+"\tDELETE");
		  }
			boolean ret = olapQueryService.removeLevel(getQuery(queryName), dimensionName, uniqueHierarchyName, uniqueLevelName);
			
			if(ret == true){
				SaikuDimensionSelection dimsels = olapQueryService.getAxisDimensionSelections(getQuery(queryName), axisName, dimensionName);
				if (dimsels != null && dimsels.getSelections().size() == 0) {
					olapQueryService.moveDimension(getQuery(queryName), "UNUSED", dimensionName, -1);
				}
				return Response.ok().build();
			}
			else{
				log.error("Cannot remove level of hierarchy "+ uniqueHierarchyName + " for query (" + queryName + ")");
			}
			throw new Exception("Something went wrong removing level: " + uniqueLevelName + " from " + uniqueHierarchyName+ " for query (" + queryName + ")");
		} catch (Exception e){
			log.error("Cannot include level of hierarchy "+ uniqueHierarchyName+ " for query (" + queryName + ")",e);
			return Response.serverError().entity(e.getMessage()).status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	
	
	@PUT
	@Produces({"application/json" })
	@Path("/{queryname}/tag")
	public Status activateTag(
			@PathParam("queryname") String queryName,
			@FormParam("tag") String tagJSON, 
			@PathParam("username") String username)
	{
		if (log.isDebugEnabled()) {
			log.debug("TRACK\t"  + "\t/query/" + queryName + "/tags\tPUT");
		}
		try {
			ObjectMapper mapper = new ObjectMapper();
		    mapper.setVisibilityChecker(mapper.getVisibilityChecker().withFieldVisibility(Visibility.ANY));
			SaikuTag tag = mapper.readValue(tagJSON, SaikuTag.class);
			
			getQuery(queryName).setTag(tag);
			return Status.OK;
		}
		catch (Exception e) {
			log.error("Cannot add tag " + tagJSON + " for query (" + queryName + ")",e);
		}
		return Status.INTERNAL_SERVER_ERROR;
	}
	
	@DELETE
	@Produces({"application/json" })
	@Path("/{queryname}/tag")
	public Status deactivateTag(
			@PathParam("queryname") String queryName,
			@PathParam("tagname") String tagName, 
			@PathParam("username") String username)
	{
		if (log.isDebugEnabled()) {
			log.debug("TRACK\t"  + "\t/query/" + queryName + "/tags\tPUT");
		}
		try {
			getQuery(queryName).removeTag();
			return Status.OK;
		}
		catch (Exception e) {
			log.error("Cannot remove tag " + tagName + " for query (" + queryName + ")",e);
		}
		return Status.INTERNAL_SERVER_ERROR;

	}
	
	@POST
	@Produces({"application/json" })
	@Path("/{queryname}/axis/{axis}/sort/{sortorder}/{sortliteral}")
	public void sortAxis(
			@PathParam("queryname") String queryName, 
			@PathParam("axis") String axisName,
			@PathParam("sortorder") String sortOrder,
			@PathParam("sortliteral") String sortLiteral, 
			@PathParam("username") String username)
	{
		if (log.isDebugEnabled()) {
			log.debug("TRACK\t"  + "\t/query/" + queryName + "/axis/"+axisName+"/sort/" + sortOrder + "/" + sortLiteral +"\tPOST");
		}
		olapQueryService.sortAxis(getQuery(queryName), axisName, sortLiteral, sortOrder);
	}
	
	@DELETE
	@Produces({"application/json" })
	@Path("/{queryname}/axis/{axis}/sort")
	public void clearSortAxis(
			@PathParam("queryname") String queryName, 
			@PathParam("axis") String axisName, 
			@PathParam("username") String username)
	{
		if (log.isDebugEnabled()) {
			log.debug("TRACK\t"  + "\t/query/" + queryName + "/axis/"+axisName+"/sort/\tDELETE");
		}
		olapQueryService.clearSort(getQuery(queryName), axisName);
	}
	
	@POST
	@Produces({"application/json" })
	@Path("/{queryname}/axis/{axis}/limit/{limitfunction}")
	public void limitAxis(
			@PathParam("queryname") String queryName, 
			@PathParam("axis") String axisName,
			@PathParam("limitfunction") String limitfunction,
			@FormParam("n") String n,
			@FormParam("sortliteral") String sortLiteral, 
			@PathParam("username") String username)
	{
		if (log.isDebugEnabled()) {
			log.debug("TRACK\t"  + "\t/query/" + queryName + "/axis/"+axisName+"/limit/" + limitfunction+ "(" + n  + ", sort:"+ sortLiteral +"\tPOST");
		}
		olapQueryService.limitAxis(getQuery(queryName), axisName, limitfunction, n, sortLiteral);
	}
	
	@DELETE
	@Produces({"application/json" })
	@Path("/{queryname}/axis/{axis}/limit")
	public void clearLimitAxis(
			@PathParam("queryname") String queryName, 
			@PathParam("axis") String axisName, 
			@PathParam("username") String username)
	{
		if (log.isDebugEnabled()) {
			log.debug("TRACK\t"  + "\t/query/" + queryName + "/axis/"+axisName+"/limit/\tDELETE");
		}
		olapQueryService.clearLimit(getQuery(queryName), axisName);
	}

	@POST
	@Produces({"application/json" })
	@Path("/{queryname}/axis/{axis}/filter")
	public void filterAxis(
			@PathParam("queryname") String queryName, 
			@PathParam("axis") String axisName,
			@FormParam("filterCondition") String filterCondition, 
			@PathParam("username") String username)
	{
		if (log.isDebugEnabled()) {
			log.debug("TRACK\t"  + "\t/query/" + queryName + "/axis/"+axisName+"/filter/ (" + filterCondition +" )\tPOST");
		}
		olapQueryService.filterAxis(getQuery(queryName), axisName, filterCondition);
	}
	
	@DELETE
	@Produces({"application/json" })
	@Path("/{queryname}/axis/{axis}/filter")
	public void clearFilter(
			@PathParam("queryname") String queryName, 
			@PathParam("axis") String axisName, 
			@PathParam("username") String username)
	{
		if (log.isDebugEnabled()) {
			log.debug("TRACK\t"  + "\t/query/" + queryName + "/axis/"+axisName+"/filter/\tDELETE");
		}
		olapQueryService.clearFilter(getQuery(queryName), axisName);
	}

	private SaikuReport getReport(String username) {
		return saikuSessionContainer.get().getReport(username);
	}

	private Cube getCube(String username) throws ClassNotFoundException, IOException, SQLException {
		SaikuReport report = getReport(username);
		Cube cube = olapUtilService.getCube(report);
		return cube;
	}

	private IQuery getQuery(String queryName) {
		return saikuSessionContainer.get().getQuery(queryName);
	}
}
