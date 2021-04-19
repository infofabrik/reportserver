package net.datenwerke.rs.core.service.reportmanager.parameters;

public class ParameterValueImpl implements ParameterValue {

	final private String name;
	final private Object value;
	final private Class<?> type;
	
	public ParameterValueImpl(String name, Object value, Class<?> type) {
		super();
		this.name = name;
		this.value = value;
		this.type = type;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public Object getValue() {
		return value;
	}

	@Override
	public Class<?> getType() {
		return type;
	}

}
