package net.datenwerke.rs.base.service.dbhelper.querybuilder.queryconditions;

import java.util.List;

import net.datenwerke.rs.base.service.dbhelper.querybuilder.ColumnNamingService;
import net.datenwerke.rs.base.service.reportengines.table.entities.Column;

public interface QryCondition {
	
	public void appendToBuffer(StringBuffer buf, ColumnNamingService columnNamingService);
	
	public List<Column> getContainedColumns();
}
