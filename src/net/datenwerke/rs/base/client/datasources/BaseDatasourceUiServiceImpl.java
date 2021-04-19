package net.datenwerke.rs.base.client.datasources;

import java.util.Collection;

import net.datenwerke.rs.base.client.dbhelper.dto.DatabaseHelperDto;
import net.datenwerke.rs.base.client.reportengines.table.dto.AggregateFunctionDto;

public class BaseDatasourceUiServiceImpl implements BaseDatasourceUiService {

	private Collection<DatabaseHelperDto> databaseHelper;
	
	@Override
	public Collection<DatabaseHelperDto> getDatabaseHelpers() {
		if(null == this.databaseHelper)
			throw new IllegalStateException("DatabseHelper were not yet set."); //$NON-NLS-1$
		return this.databaseHelper;
	}

	@Override
	public void setDatabaseHelpers(Collection<DatabaseHelperDto> databaseHelper) {
		if(null != this.databaseHelper)
			throw new IllegalStateException("DatabseHelper were already set."); //$NON-NLS-1$
		this.databaseHelper = databaseHelper;
	}

	@Override
	public boolean isNumericalAggregation(AggregateFunctionDto aggregate) {
		switch(aggregate){
		case SUM:
		case AVG:
		case VARIANCE:
			return true;
		}
		return false;
	}
}
