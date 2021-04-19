package net.datenwerke.rs.base.service.dbhelper.db.mssql;

import net.datenwerke.rs.base.service.dbhelper.queries.OrderQuery;
import net.datenwerke.rs.base.service.dbhelper.queries.Query;
import net.datenwerke.rs.base.service.dbhelper.querybuilder.ColumnNamingService;
import net.datenwerke.rs.base.service.dbhelper.querybuilder.QueryBuilder;
import net.datenwerke.rs.base.service.dbhelper.querybuilder.QueryBuilder.OrderDefinition;
import net.datenwerke.rs.base.service.reportengines.table.entities.Order;

public class MsSQLOrderQuery extends OrderQuery {

	public MsSQLOrderQuery(Query nestedQuery, QueryBuilder queryBuilder, ColumnNamingService columnNamingService) {
		super(nestedQuery, queryBuilder, columnNamingService);
	}
	
	@Override
	public void appendToBuffer(StringBuffer buf) {
		/* count the visible columns to order by */
		if(queryBuilder.isDistinct()){
			/* count the visible columns to order by */
			int count = 0;
			for(OrderDefinition def : queryBuilder.getOrderDefinitions()){
				if(null == def.getColumn().isHidden() || !def.getColumn().isHidden())
					count++;
			}
			
			if(count == 0){
				nestedQuery.appendToBuffer(buf);
				return;
			}
		}
			
		buf.append("SELECT TOP 9223372036854775807 * FROM ("); // 9223372036854775807 = BIGINT_MAXVALUE
		nestedQuery.appendToBuffer(buf);
		buf.append(") orderQry ORDER BY ");
		
		int i = 1;
		for(OrderDefinition def : queryBuilder.getOrderDefinitions()){
			/* if distinct && hidden -> ignore */
			if(queryBuilder.ignoreHiddenColumns() && null != def.getColumn().isHidden() && def.getColumn().isHidden())
				continue;
			
			if(i > 1)
				buf.append(", "); //$NON-NLS-1$

			buf.append(columnNamingService.getColumnName(def.getColumn()))
				.append(' ') //$NON-NLS-1$
				.append( def.getOrder().equals(Order.ASC) ? "ASC" : "DESC" ); //$NON-NLS-1$ //$NON-NLS-2$

			i++;
		}
		
	}


}
