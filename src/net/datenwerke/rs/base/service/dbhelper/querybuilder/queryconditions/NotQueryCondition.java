package net.datenwerke.rs.base.service.dbhelper.querybuilder.queryconditions;

import java.util.List;

import net.datenwerke.rs.base.service.dbhelper.querybuilder.ColumnNamingService;
import net.datenwerke.rs.base.service.reportengines.table.entities.Column;

public class NotQueryCondition implements QryCondition{

	private QryCondition inner;
	
	public NotQueryCondition(QryCondition inner) {
		this.inner = inner;
	}

	@Override
	public void appendToBuffer(StringBuffer buf, ColumnNamingService columnNamingService) {
		buf.append("(NOT(");
		inner.appendToBuffer(buf, columnNamingService);
		buf.append("))");
	}
	
	@Override
	public List<Column> getContainedColumns() {
		return inner.getContainedColumns();
	}

}
