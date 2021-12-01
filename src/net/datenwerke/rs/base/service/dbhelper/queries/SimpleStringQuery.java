package net.datenwerke.rs.base.service.dbhelper.queries;


public class SimpleStringQuery implements Query {

	private String query;
	
	public SimpleStringQuery(String query) {
		this.query = query;
	}
	
	@Override
	public void appendToBuffer(StringBuffer buf) {
		buf.append(query);
	}
	
	@Override
	public String toString() {
		return query;
	}
}
