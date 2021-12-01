package net.datenwerke.rs.base.service.parameters.string;

import java.math.BigDecimal;

import net.datenwerke.rs.core.service.parameters.entities.Datatype;
import net.datenwerke.rs.core.service.parameters.entities.ParameterInstanceForJuel;

public class TextParameterInstanceForJuel extends
		ParameterInstanceForJuel {

	private String value;
	private Datatype returnType;

	public void setValue(String value) {
		this.value = value;
	}

	public Object getValue() {
		switch(returnType){
		case String:
			return value;
		case Integer:
			return Integer.parseInt(value);
		case Long:
			return Long.valueOf(value);
		case BigDecimal:
			return new BigDecimal(value);
		case Float:
			return Float.valueOf(value);
		case Double:
			return Double.valueOf(value);
		default:
			return value;
		}
	}
	
	public String asString(){
		return value;
	}
	
	public int asInteger(){
		return Integer.parseInt(value);
	}
	
	public double asDouble(){
		return Double.valueOf(value);
	}
	
	public float asFloat(){
		return Float.valueOf(value);
	}
	
	public BigDecimal asBigDecimal(){
		return new BigDecimal(value);
	}
	
	public Long asLong(){
		return Long.valueOf(value);
	}

	public void setReturnType(Datatype returnType) {
		this.returnType = returnType;
	}

	public Datatype getReturnType() {
		return returnType;
	}
	
	

}
