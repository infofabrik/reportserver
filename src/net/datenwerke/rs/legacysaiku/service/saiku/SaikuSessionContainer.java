package net.datenwerke.rs.legacysaiku.service.saiku;

import java.util.HashMap;

import org.legacysaiku.olap.query.IQuery;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.google.inject.servlet.SessionScoped;

import net.datenwerke.rs.saiku.service.saiku.entities.SaikuReport;

@SessionScoped
public class SaikuSessionContainer {

	private HashMap<String, SaikuReport> saikureports = new HashMap<String, SaikuReport>();
	private HashMap<String, IQuery> queries = new HashMap<String, IQuery>();
	private BiMap<SaikuReport, IQuery> reportQueryMap = HashBiMap.create();
	
	public void putReport(String token, SaikuReport report){
		saikureports.put(token, report);
	}
	
	public SaikuReport getReport(String token){
		return saikureports.get(token);
	}
	
	public void putQuery(String queryName, SaikuReport report, IQuery query) {
		queries.put(queryName, query);
		reportQueryMap.put(report, query);
	}
	
	public IQuery getQuery(String queryName) {
		return queries.get(queryName);
	}

	public HashMap<String, IQuery> getQueries() {
		return queries;
	}
	
	public void removeQuery(String queryName) {
		if (queries.containsKey(queryName)) {
			IQuery q = queries.remove(queryName);
			reportQueryMap.inverse().remove(q);
			try {
				q.cancel();
			} catch (Exception e) {}
			q = null;
		}
	}

	public IQuery getQueryForReport(SaikuReport report) {
		return reportQueryMap.get(report);
	}
	
	public void putQuery(String queryName, IQuery query) {
		SaikuReport report = reportQueryMap.inverse().get(getQuery(queryName));
		if(null == report)
			throw new RuntimeException("Cannot create query " + queryName + ". No report assigned.");
		
		putQuery(queryName, report, query);
	}
}
