package net.datenwerke.rs.base.service.dbhelper.querybuilder.queryconditions;

import net.datenwerke.rs.base.service.dbhelper.DatabaseHelper;
import net.datenwerke.rs.base.service.dbhelper.querybuilder.ColumnNamingService;
import net.datenwerke.rs.base.service.dbhelper.querybuilder.QueryBuilder;
import net.datenwerke.rs.base.service.reportengines.table.entities.Column;

public class LikeQueryCondition extends CompareOpQueryCondition {

	public LikeQueryCondition(Column column, Object data, QueryBuilder queryBuilder) {
		super(column, data, queryBuilder);
	}

	@Override
	public void appendToBuffer(StringBuffer buf, ColumnNamingService columnNamingService) {
		boolean caseSensitive = null != column.getFilter() ? column.getFilter().isCaseSensitive() : true;
		if(! caseSensitive && data instanceof String)
			data = ((String)data).toLowerCase();
		
		
		DatabaseHelper dbHelper = queryBuilder.getDbHelper();
		
		switch(compareOpConfig){
			case COLUMN_DATA:
				if(! caseSensitive)
					buf.append(" LOWER("); //$NON-NLS-1$
				buf.append(dbHelper.prepareColumnForComparison(columnNamingService.getColumnName(column), column)).append(" ");
				if(! caseSensitive)
					buf.append(") "); //$NON-NLS-1$
				buf.append(getCompareOpSymbol()).append(" ");
				if(! caseSensitive)
					buf.append(" LOWER("); //$NON-NLS-1$
				buf.append(queryBuilder.nextReplacement(data, column.getType()));
				if(! caseSensitive)
					buf.append(')');
			break;
			
			case COLUMN_SUBQUERY:
				if(! caseSensitive)
					buf.append(" LOWER("); //$NON-NLS-1$
				buf.append(dbHelper.prepareColumnForComparison(columnNamingService.getColumnName(column), column)).append(" ");
				if(! caseSensitive)
					buf.append(") "); //$NON-NLS-1$
				
				buf.append(getCompareOpSymbol()).append(" ");
				
				if(! caseSensitive)
					buf.append(" LOWER("); //$NON-NLS-1$
				buf.append('(');
				query.appendToBuffer(buf);
				if(! caseSensitive)
					buf.append(")");
				buf.append(")");
			break;
			
			default:
				throw new RuntimeException("Invalid Query Condition Configuration");
		}
	}
	
	@Override
	protected String getCompareOpSymbol() {
		return "LIKE";
	}
	
}
