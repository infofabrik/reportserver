package net.datenwerke.rs.condition.client.condition;

public interface Condition {

	public String getName();
	public String getDescription();
	public String getKey();
	public boolean hasExpression();
}