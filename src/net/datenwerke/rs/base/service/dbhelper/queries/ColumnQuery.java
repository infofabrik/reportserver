package net.datenwerke.rs.base.service.dbhelper.queries;

import java.util.HashSet;
import java.util.Set;

import net.datenwerke.rs.base.service.dbhelper.hooks.ColumnReferenceSqlProvider;
import net.datenwerke.rs.base.service.dbhelper.querybuilder.ColumnNamingService;
import net.datenwerke.rs.base.service.dbhelper.querybuilder.QueryBuilder;
import net.datenwerke.rs.base.service.dbhelper.querybuilder.queryconditions.QryCondition;
import net.datenwerke.rs.base.service.reportengines.table.entities.AdditionalColumnSpec;
import net.datenwerke.rs.base.service.reportengines.table.entities.Column;
import net.datenwerke.rs.base.service.reportengines.table.entities.ColumnReference;


public class ColumnQuery extends CompositeQuery {
	
	private QueryBuilder queryBuilder;
	private ColumnNamingService baseNameService;
	private ColumnNamingService uniqueNameService;

	public ColumnQuery(Query nestedQuery, QueryBuilder queryBuilder, ColumnNamingService baseNameService, ColumnNamingService uniqueNameService) {
		super(nestedQuery);
		this.queryBuilder = queryBuilder;
		this.baseNameService = baseNameService;
		this.uniqueNameService = uniqueNameService;
	}

	
	@Override
	public void appendToBuffer(StringBuffer buf) {
		if((null == queryBuilder.getColumns() || queryBuilder.getColumns().size() == 0) && (null == queryBuilder.getAdditionalColumns() || queryBuilder.getAdditionalColumns().size() == 0)){
			nestedQuery.appendToBuffer(buf);
			return;
		}
		
		buf.append("SELECT ");
		
		if(queryBuilder.getColumns().size() == 0){
			buf.append("colQry.*"); //$NON-NLS-1$
		}else{
			int i = 1;
			for(Column col : queryBuilder.getColumns()){
		
				if(i > 1)
					buf.append(", "); //$NON-NLS-1$
				
				/* column name */
				
				if(! (col instanceof ColumnReference)){
					buf.append(baseNameService.getColumnName(col));
				} else {
					boolean found = false;
					for(ColumnReferenceSqlProvider sqlProvider : queryBuilder.getHookHandler().getHookers(ColumnReferenceSqlProvider.class)){
						AdditionalColumnSpec spec = ((ColumnReference)col).getReference();
						
						if(sqlProvider.consumes(spec,queryBuilder)){
							buf.append(sqlProvider.getSelectSnipped(spec, queryBuilder));
							found = true;
							break;
						}
					}
					if(! found)
						throw new IllegalArgumentException("Could not find sql provider for column type: " + col.getClass());
				}
					
				buf.append(" AS ");
				buf.append(uniqueNameService.getColumnName(col));
				
				i++;
			}
		}
		
		/* additionally ad column specs */
		for(AdditionalColumnSpec col : queryBuilder.getAdditionalColumns()){
			buf.append(", "); //$NON-NLS-1$
			
			boolean found = false;
			for(ColumnReferenceSqlProvider sqlProvider : queryBuilder.getHookHandler().getHookers(ColumnReferenceSqlProvider.class)){
				if(sqlProvider.consumes(col,queryBuilder)){
					buf.append(sqlProvider.getSelectSnipped(col, queryBuilder));
					found = true;
					break;
				}
			}
			if(! found)
				throw new IllegalArgumentException("Could not find sql provider for column type: " + col.getClass());
			
			buf.append(" AS ").append(uniqueNameService.getColumnName(col));
		}
		
		/* if we have additional columns we have an extra level in the query. In this case
		 * we have to specifically select the columns that are used in prefilters for the where clause
		 * to work.
		 */
		if(queryBuilder.getColumns().size() > 0 && ! queryBuilder.getAdditionalColumns().isEmpty() && ! queryBuilder.getInnerConditions().isEmpty()){
			Set<String> listOfAddedColumns = new HashSet<String>();
			for(QryCondition cond : queryBuilder.getInnerConditions()){
				for(Column col : cond.getContainedColumns()){
					if(! (col instanceof ColumnReference) && ! listOfAddedColumns.contains(col.getName()) ){
						buf.append(", "); //$NON-NLS-1$
						buf.append(baseNameService.getColumnName(col));
						
						listOfAddedColumns.add(col.getName());
					}
				}
			}
		}
		
		
		buf.append(" FROM ( ");
		nestedQuery.appendToBuffer(buf);
		buf.append(") colQry");
	}

}
