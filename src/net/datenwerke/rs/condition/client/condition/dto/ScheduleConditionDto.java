package net.datenwerke.rs.condition.client.condition.dto;

import net.datenwerke.gf.base.client.dtogenerator.RsDto;
import net.datenwerke.rs.condition.client.condition.Condition;

public class ScheduleConditionDto extends RsDto {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String expression;
	private Condition condition;
	
	public ScheduleConditionDto() {
		super();
	}

	public String getExpression() {
		return expression;
	}

	public void setExpression(String expression) {
		this.expression = expression;
	}

	public Condition getCondition() {
		return condition;
	}

	public void setCondition(Condition condition) {
		this.condition = condition;
	}


}
