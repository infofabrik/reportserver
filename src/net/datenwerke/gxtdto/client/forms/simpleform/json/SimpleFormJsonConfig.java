package net.datenwerke.gxtdto.client.forms.simpleform.json;

import com.google.gwt.core.client.JavaScriptObject;

public class SimpleFormJsonConfig extends JavaScriptObject {

	protected SimpleFormJsonConfig(){}
	
	public final native String getLabelAlign() /*-{ return this.labelAlign; }-*/;
	public final native SimpleFormFieldsJson getFields() /*-{ return this.fields;  }-*/;
}
