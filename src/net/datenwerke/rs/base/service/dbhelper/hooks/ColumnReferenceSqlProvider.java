package net.datenwerke.rs.base.service.dbhelper.hooks;

import net.datenwerke.hookhandler.shared.hookhandler.interfaces.Hook;
import net.datenwerke.rs.base.service.dbhelper.querybuilder.QueryBuilder;
import net.datenwerke.rs.base.service.reportengines.table.entities.AdditionalColumnSpec;

public interface ColumnReferenceSqlProvider extends Hook {

	boolean consumes(AdditionalColumnSpec col, QueryBuilder queryBuilder);

	String getSelectSnipped(AdditionalColumnSpec col, QueryBuilder queryBuilder);

}
