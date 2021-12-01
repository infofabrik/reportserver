package net.datenwerke.rs.utils.juel;

public class VariableAssignment {

	private Object value;
	private Class<?> type;
	
	public VariableAssignment(Object value){
		this(value, null != value ? value.getClass() : null);
	}
	
	public VariableAssignment(Object value, Class<?> type) {
		super();
		this.value = value;
		this.type = type;
	}
	public Object getValue() {
		return value;
	}
	public void setValue(Object value) {
		this.value = value;
	}
	public Class<?> getType() {
		return type;
	}
	public void setType(Class<?> type) {
		this.type = type;
	}
	
	
}
