package net.datenwerke.rs.base.service.dbhelper;

import net.datenwerke.rs.base.service.datasources.table.impl.TableDBDataSource;
import net.datenwerke.rs.base.service.dbhelper.querybuilder.ManagedQuery;

public interface ManagedQueryFactory {

	public ManagedQuery create(String query, DatabaseHelper dbHelper, TableDBDataSource tableDBDataSource);

}
