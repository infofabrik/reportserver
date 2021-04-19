package net.datenwerke.rs.base.service.dbhelper.queries;



public class CountQuery extends CompositeQuery{


	public CountQuery(Query nestedQuery) {
		super(nestedQuery);
	}
	

	@Override
	public void appendToBuffer(StringBuffer buf) {
		buf.append("SELECT COUNT(*) FROM (");
		nestedQuery.appendToBuffer(buf);
		buf.append(") countQry");
	}

}
