package net.datenwerke.rs.legacysaiku.server.rest.resources;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import mondrian3.olap.MondrianServer;
import mondrian3.olap.MondrianServer.MondrianVersion;
import mondrian3.server.monitor.ConnectionInfo;
import mondrian3.server.monitor.Monitor;
import mondrian3.server.monitor.ServerInfo;
import mondrian3.server.monitor.StatementInfo;

@Path("/legacysaiku/statistics")
public class StatisticsResource {

//	StringWriter sqlWriter = new StringWriter();
//	StringWriter mdxWriter = new StringWriter();
//	StringWriter profileWriter = new StringWriter();
//	StringWriter saikuWriter = new StringWriter();
//	
//	public StatisticsResource() {
//		setupLog("mondrian3.sql", "DEBUG", sqlWriter);
//		setupLog("mondrian3.mdx", "DEBUG", mdxWriter);
//		setupLog("mondrian3.profile", "DEBUG", profileWriter);
//		setupLog("org.legacysaiku.service", "INFO", saikuWriter);
//		System.out.println("##########################SETUP LOG");
//		
//		
//	}
	
//	private void setupLog(String category, String level, StringWriter writer) {
//		Logger pkgLogger = Logger.getRootLogger().getLoggerRepository().getLogger(category);
//		pkgLogger.setLevel(Level.toLevel(level));
//		WriterAppender appender = new WriterAppender(new PatternLayout("%d{ISO8601} %p - %m%n"), writer);
//		appender.setName("CONSOLE_APPENDER");
//		appender.setThreshold(Level.ERROR);
//		pkgLogger.addAppender(appender);
//		Logger.getRootLogger().addAppender(appender);
//	}
	
	
	@GET
	@Produces({"application/json" })
	@Path("/mondrian")
	public MondrianStats getMondrianStats() {
		
		MondrianServer mondrianServer = MondrianServer.forId(null);
		if (mondrianServer != null) {
			MondrianVersion mv = mondrianServer.getVersion();
			
			final Monitor monitor = mondrianServer.getMonitor();
	        final ServerInfo server = monitor.getServer();
	        
	        int statementCurrentlyOpenCount = server.statementCurrentlyOpenCount();
	        int connectionCurrentlyOpenCount = server.connectionCurrentlyOpenCount();
	        int sqlStatementCurrentlyOpenCount = server.sqlStatementCurrentlyOpenCount();
	        int statementCurrentlyExecutingCount = server.statementCurrentlyExecutingCount();
	        float avgCellDimensionality =  ((float) server.cellCoordinateCount / (float) server.cellCount);
	        
	        final List<ConnectionInfo> connections = monitor.getConnections();
	        final List<StatementInfo> statements = monitor.getStatements();
	        
	        return new MondrianStats(
	        		server,
	        		mv,
	        		statementCurrentlyOpenCount,
	        		connectionCurrentlyOpenCount,
	        		sqlStatementCurrentlyOpenCount,
	        		statementCurrentlyExecutingCount,
	        		avgCellDimensionality,
	        		connections,
	        		statements
	        		);
		}
		
		return null;
	}
	

	@GET
	@Produces({"application/json" })
	@Path("/mondrian/server")
	public ServerInfo getMondrianServer() {
		MondrianServer mondrianServer = MondrianServer.forId(null);
		if (mondrianServer != null) {
			MondrianVersion mv = mondrianServer.getVersion();
			
			final Monitor monitor = mondrianServer.getMonitor();
	        final ServerInfo server = monitor.getServer();
	        return server;
		}
		return null;
	}
	
//	@GET
//	@Produces({"text/plain" })
//	@Path("/log/sql")
//	public String getSqlLog() {
//		return sqlWriter.toString();
//	}
//	
//	@GET
//	@Produces({"text/plain" })
//	@Path("/log/mdx")
//	public String getMdxLog() {
//		return mdxWriter.toString();
//	}
//	
//	@GET
//	@Produces({"text/plain" })
//	@Path("/log/profile")
//	public String getProfileLog() {
//		return profileWriter.toString();
//	}
//	
//	@GET
//	@Produces({"text/plain" })
//	@Path("/log/saiku")
//	public String getSaikuLog() {
//		return saikuWriter.toString();
//	}
	
	
}
