package net.datenwerke.rs.scriptreport.client.scriptreport.parameters;

import com.google.gwt.core.client.JavaScriptObject;

public class ScriptParameterOverlay extends JavaScriptObject {

	protected ScriptParameterOverlay() {
	}

	public final native void setEditable(boolean editable)/*-{
		this.editable = editable;
	}-*/;
	
	public final native void setMandatory(boolean mandatory)/*-{
		this.mandatory = mandatory;
	}-*/;

	public final native void isDefault(boolean isDefault)/*-{
		this.isDefault = isDefault;
	}-*/;
	
	public final native void setName(String name)/*-{
		this.name = name;
	}-*/;
	
	public final native void setKey(String key)/*-{
		this.key = key;
	}-*/;
	
	public final native void setValue(String value)/*-{
		this.value = value;
	}-*/;

	public final native void setDefaultValue(String defaultValue)/*-{
		this.defaultValue = defaultValue;
	}-*/;

}
