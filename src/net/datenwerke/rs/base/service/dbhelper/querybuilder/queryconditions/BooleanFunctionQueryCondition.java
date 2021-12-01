package net.datenwerke.rs.base.service.dbhelper.querybuilder.queryconditions;

import java.util.LinkedList;
import java.util.List;

import net.datenwerke.rs.base.service.dbhelper.querybuilder.ColumnNamingService;
import net.datenwerke.rs.base.service.reportengines.table.entities.Column;

public abstract class BooleanFunctionQueryCondition implements QryCondition {

	private QryCondition first;
	private QryCondition second;
	
	public BooleanFunctionQueryCondition(QryCondition first, QryCondition second) {
		this.first = first;
		this.second = second;
	}

	public abstract String getBooleanFunctionName();
	
	@Override
	public void appendToBuffer(StringBuffer buf, ColumnNamingService columnNamingService) {
		buf.append("((");
		first.appendToBuffer(buf, columnNamingService);
		buf.append(") ");
		buf.append(getBooleanFunctionName());
		buf.append(" (");
		second.appendToBuffer(buf, columnNamingService);
		buf.append("))");
	}
	
	@Override
	public List<Column> getContainedColumns() {
		LinkedList<Column> res = new LinkedList<Column>();
		res.addAll(first.getContainedColumns());
		res.addAll(second.getContainedColumns());
		return res;
	}

}
