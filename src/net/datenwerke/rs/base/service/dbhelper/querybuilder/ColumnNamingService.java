package net.datenwerke.rs.base.service.dbhelper.querybuilder;

import net.datenwerke.rs.base.service.reportengines.table.entities.Column;


public interface ColumnNamingService {
	public String getColumnName(Column column);
}
