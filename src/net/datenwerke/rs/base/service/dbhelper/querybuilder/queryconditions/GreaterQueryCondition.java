package net.datenwerke.rs.base.service.dbhelper.querybuilder.queryconditions;

import net.datenwerke.rs.base.service.dbhelper.querybuilder.QueryBuilder;
import net.datenwerke.rs.base.service.reportengines.table.entities.Column;

public class GreaterQueryCondition extends CompareOpQueryCondition {

	public GreaterQueryCondition(Column column, Object data,
			QueryBuilder queryBuilder) {
		super(column, data, queryBuilder);
	}

	@Override
	protected String getCompareOpSymbol() {
		return ">";
	}

}
