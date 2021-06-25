package net.datenwerke.rs.base.client.datasources;

import java.util.Collection;

import net.datenwerke.rs.base.client.dbhelper.dto.DatabaseHelperDto;
import net.datenwerke.rs.base.client.reportengines.table.dto.AggregateFunctionDto;

public interface BaseDatasourceUiService {

   public void setDatabaseHelpers(Collection<DatabaseHelperDto> databaseHelper);

   public Collection<DatabaseHelperDto> getDatabaseHelpers();

   public boolean isNumericalAggregation(AggregateFunctionDto aggregate);

}
