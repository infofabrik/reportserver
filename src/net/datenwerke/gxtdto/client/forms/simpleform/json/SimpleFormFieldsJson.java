package net.datenwerke.gxtdto.client.forms.simpleform.json;

import com.google.gwt.core.client.JavaScriptObject;

public class SimpleFormFieldsJson extends JavaScriptObject {

   protected SimpleFormFieldsJson() {
   }

   public final native int length() /*-{ return this.length; }-*/;

   public final native SimpleFormFieldJson get(int i) /*-{ return this[i];  }-*/;
}
