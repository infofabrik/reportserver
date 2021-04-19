package net.datenwerke.rs.base.service.dbhelper.querybuilder.queryconditions;

import java.util.LinkedList;
import java.util.List;

import net.datenwerke.rs.base.service.dbhelper.querybuilder.ColumnNamingService;
import net.datenwerke.rs.base.service.reportengines.table.entities.Column;

public class IsNullQueryCondition implements QryCondition {

	private Column column;
	
	public IsNullQueryCondition(Column column) {
		this.column = column;
	}
	
	@Override
	public void appendToBuffer(StringBuffer buf, ColumnNamingService columnNamingService) {
		buf.append('(');
		buf.append(columnNamingService.getColumnName(column));
		buf.append(" IS NULL)");
	}

	@Override
	public List<Column> getContainedColumns() {
		LinkedList<Column> res = new LinkedList<Column>();
		res.add(column);
		return res;
	}

}
