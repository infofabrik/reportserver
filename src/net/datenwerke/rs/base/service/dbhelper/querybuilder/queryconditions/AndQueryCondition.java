package net.datenwerke.rs.base.service.dbhelper.querybuilder.queryconditions;


public class AndQueryCondition extends BooleanFunctionQueryCondition {
	
	public AndQueryCondition(QryCondition first, QryCondition second) {
		super(first, second);
	}

	@Override
	public String getBooleanFunctionName() {
		return "AND";
	}

}
