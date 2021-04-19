package net.datenwerke.rs.base.client.reportengines.table.ui.model;

public enum Expressions {
	IN("$X{IN, column, parameterkey}"),
	NOTIN("$X{NOTIN, column, parameterkey}"),
	NOTEQUAL("$X{NOTEQUAL, column, parameterkey}"),
	LESS("$X{LESS, column, parameterkey}"),
	LESS_EQUAL("$X{LESS], column, parameterkey}"),
	GREATER("$X{GREATER, column, parameterkey}"),
	GREATER_EQUAL("$X{[GREATER, column, parameterkey}"),
	BETWEEN("$X{BETWEEN, column, lowerParameterkey, upperParameterkey}"),
	BETWEEN_LEFT_EQUAL("$X{[BETWEEN, column, lowerParameterkey, upperParameterKey}"),
	BETWEEN_RIGHT_EQUAL("$X{BETWEEN], column, lowerParameterkey, upperParameterKey}"),
	BETWEEN_EQUAL("$X{[BETWEEN], column, lowerParameterkey, upperParameterKey} ");

	private String value;
	
	Expressions(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
};