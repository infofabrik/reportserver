package net.datenwerke.rs.legacysaiku.server.rest.resources;

import javax.ws.rs.Path;

@Path("/legacysaiku/{username}/datasources")
public class DataSourceResource {

//    DatasourceService datasourceService;
//    
//    private static final Logger log = LoggerFactory.getLogger(DataSourceResource.class);
//    
//    public void setDatasourceService(DatasourceService ds) {
//    	datasourceService = ds;
//    }
//    
//    /**
//     * Get Data Sources.
//     * @return A Collection of SaikuDatasource's.
//     */
//    @GET
//    @Produces({"application/json" })
//     public Collection<SaikuDatasource> getDatasources() {
//    	try {
//			return datasourceService.getDatasources().values();
//		} catch (SaikuServiceException e) {
//			log.error(this.getClass().getName(),e);
//			return new ArrayList<SaikuDatasource>();
//		}
//    }
//    
//    /**
//     * Delete Data Source.
//     * @param datasourceName - The name of the data source.
//     * @return A GONE Status.
//     */
//    @DELETE
//	@Path("/{datasource}")
//	public Status deleteDatasource(@PathParam("datasource") String datasourceName){
//    	datasourceService.removeDatasource(datasourceName);
//		return(Status.GONE);
//    }
//    
//    /**
//     * Get Data Source.
//     * @param datasourceName.
//     * @return A Saiku Datasource.
//     */
//    @GET
//    @Produces({"application/json" })
//	@Path("/{datasource}")
//	public SaikuDatasource getDatasource(@PathParam("datasource") String datasourceName){
//    	return datasourceService.getDatasource(datasourceName);
//    }
//
//    @POST
//    @Consumes({"application/json" })
//	@Path("/{datasource}")
//	public Status addDatasource(@PathParam("datasource") String datasourceName , @Context SaikuDatasource ds){
//    	System.out.println("ds not null:" + (ds != null));
//    	System.out.println("ds name:"+ds.getName());
//    	datasourceService.addDatasource(ds);
//    	return Status.OK;
//    }

}
