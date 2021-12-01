package net.datenwerke.rs.base.service.dbhelper.queries;


public class SimpleWrapperQuery implements Query {

	private String query;
	
	public SimpleWrapperQuery(String query) {
		this.query = query;
	}
	
	@Override
	public void appendToBuffer(StringBuffer buf) {
		buf.append("SELECT * FROM (").append(query).append(") wrappedQry");
	}
	
	@Override
	public String toString() {
		return query;
	}
}
