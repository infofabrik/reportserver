package net.datenwerke.rs.incubator.service.filterreplacements.today;

import net.datenwerke.rs.base.service.dbhelper.DatabaseHelper;
import net.datenwerke.rs.base.service.dbhelper.hooks.FilterReplacementProviderHook;
import net.datenwerke.rs.base.service.dbhelper.querybuilder.ManagedQuery;
import net.datenwerke.rs.base.service.dbhelper.querybuilder.QueryBuilder;
import net.datenwerke.rs.base.service.reportengines.table.entities.Column;
import net.datenwerke.rs.utils.juel.SimpleJuel;
import net.datenwerke.rs.utils.juel.wrapper.TodayWrapper;

public class TodayFilterReplacementProviderHooker implements
		FilterReplacementProviderHook {

	private static final String TODAY = "today";

	@Override
	public void enhance(SimpleJuel juel, Column col, QueryBuilder queryBuilder, ManagedQuery query) {
		if(null == col.getType())
			return;
		Class c = DatabaseHelper.mapSQLTypeToJava(col.getType());
		
		String format = "yyyyMMdd";
		if(c.equals(java.sql.Date.class))
			format = "yyyy-MM-dd";
		else if(c.equals(java.sql.Time.class))
			format="hh:mm:ss";
		else if(c.equals(java.sql.Timestamp.class))
			format = "yyyy-MM-dd hh:mm:ss";
		
		juel.addReplacement(TODAY, new TodayWrapper(format));
	}

}
