package net.datenwerke.rs.core.client.sendto;

import com.google.gwt.core.client.JavaScriptObject;

public class SendToJsonConfig extends JavaScriptObject {
	protected SendToJsonConfig(){}

	public final native int getWidth() /*-{ return null == this.width ? 640 : this.width; }-*/;
	public final native int getHeight() /*-{ return null == this.height ? 480 : this.height; }-*/;
	public final native JavaScriptObject getForm() /*-{ return this.form }-*/;
}