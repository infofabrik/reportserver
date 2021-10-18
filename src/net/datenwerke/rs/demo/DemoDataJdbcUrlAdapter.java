package net.datenwerke.rs.demo;

import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.concurrent.Future;

import javax.inject.Provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import net.datenwerke.dbpool.DbPoolService;
import net.datenwerke.dbpool.config.predefined.StandardConnectionConfig;
import net.datenwerke.dbpool.hooks.adapter.JdbcUrlAdapterHookAdapter;
import net.datenwerke.gf.service.tempfile.annotations.TempDirLocation;
import net.datenwerke.rs.base.service.dbhelper.DBHelperService;
import net.datenwerke.rs.core.service.internaldb.pool.DemoDbConnectionPool;
import net.datenwerke.rs.utils.properties.ApplicationPropertiesService;

@Singleton
public class DemoDataJdbcUrlAdapter extends JdbcUrlAdapterHookAdapter {

	private final Logger logger = LoggerFactory.getLogger(getClass().getName());
	
	public static final String DEMODATA_URL = "rs:demodata";
	public static final String DEMODATA_MONDRIAN_URL = "rs:mondrian:demodata";
	
	private boolean demoDataInstalled = false;
	private String url;
	
	private DbPoolService<Connection> dbPoolService;
	private DBHelperService dbHelperService;
	private Provider<String> tempDir;
	private ApplicationPropertiesService propertiesService;
	
	@Inject
	public DemoDataJdbcUrlAdapter(
			DbPoolService dbPoolService,
			DBHelperService dbHelperService, 
			@TempDirLocation Provider<String> tempDir,
			ApplicationPropertiesService propertiesService) {
		this.demoDataInstalled = demoDataInstalled;
		this.dbPoolService = dbPoolService;
		this.dbHelperService = dbHelperService;
		this.tempDir = tempDir;
		this.propertiesService = propertiesService;
	}

	public boolean isDemoDataInstalled() {
		return demoDataInstalled;
	}
	
	protected synchronized void installDemoData(){
		if(isDemoDataInstalled())
			return;
		
		/* install demo data */
		logger.info("Loading demodata");
		try(Connection connection = getDemoConnection().get()){
			connection.prepareStatement("DROP ALL OBJECTS").execute();

			/* classicmodes */
			connection.prepareStatement("RUNSCRIPT FROM 'classpath:resources/demo/demodata.sql'").execute();
			connection.commit();

			/* foodmart */
//			MondrianLoader ml = new MondrianLoader();
//			ml.setAggregates(true);
//			ml.setTables(true);
//			ml.setData(true);
//			ml.setIndexes(true);
//			ml.setJdbcOutput(true);
//			ml.setConnection(connection);
//			ml.load();
			connection.prepareStatement("RUNSCRIPT FROM 'classpath:resources/demo/FoodMart.sql'").execute();
			connection.commit();
			
		} catch (Exception e) {
			logger.warn("Failed to load demodata", e);
		}
		
		logger.info("Loading demodata: success");
		demoDataInstalled = true;
	}
	
	protected Future<Connection> getDemoConnection() throws SQLException{
		return dbPoolService.getConnection(new DemoDbConnectionPool(getDemoJdbcConnectionUrl(), null, getDemoJdbcConnectionUrl()), new StandardConnectionConfig());
	}

	private String getDemoJdbcConnectionUrl() {
		if(null != url)
			return url;
		
		if(propertiesService.getBoolean("rs.install.demodata.inmemory", false)){
			logger.info("Demodata URL: " + "jdbc:h2:mem:demodata;DB_CLOSE_DELAY=-1");
			url = "jdbc:h2:mem:demodata;DB_CLOSE_DELAY=-1";
			return url;
		}
		
		url = propertiesService.getString("rs.install.demodata.url", null);
		if(null != url){
			url = url.replace("\\","/"); //Windows: RS-2780 https://stackoverflow.com/questions/5784895/java-properties-backslash
			logger.info("Demodata URL: " + url);
			return url;
		}
		
		/* h2 file in temp dir */
		File dir = new File(tempDir.get());
		if(! dir.exists() && ! dir.isDirectory()){
			logger.warn( "cannot access tempdir: " + tempDir + ". Will load demo data into main memory.");
			return "jdbc:h2:mem:demodata;DB_CLOSE_DELAY=-1";
		}
		
		try{
			File tmpFile = new File(tempDir.get() + File.separatorChar + "rs_demo_data_db");

			/* remove potential old files (if not installed)*/
			String[] files = new String[]{"rs_demo_data_db.mv.db", "rs_demo_data_db.trace.db", "rs_demo_data_db.lock.db","rs_demo_data_db.h2.db"};
			for(String f : files){
				File file = new File(tempDir.get() + File.separatorChar + f);
				if(file.exists())
					file.delete();
				file.deleteOnExit();
			}
			
			url = "jdbc:h2:file:" + tmpFile.getAbsoluteFile().toString().replace("\\","/"); //Windows: RS-2780 https://stackoverflow.com/questions/5784895/java-properties-backslash
			logger.info("Demodata URL: " + url);
			return url;
		} catch(Exception e){
			logger.warn( "could not prepare temp file for demo data. Load demo data in main memory.", e);
			return "jdbc:h2:mem:demodata;DB_CLOSE_DELAY=-1";
		}
	}
	
	@Override
	public String adaptJdbcUrl(String url) {
		if(DEMODATA_URL.equals(url))
			return getDemodataUrl();
		if(DEMODATA_MONDRIAN_URL.equals(url))
			return getMondrianDemodataUrl();
		return url;
	}

	protected String getMondrianDemodataUrl() {
		return "jdbc:mondrian:Jdbc=" + getDemodataUrl();
	}

	protected String getDemodataUrl() {
		if(! isDemoDataInstalled())
			installDemoData();
		
		return getDemoJdbcConnectionUrl();
	}
}
