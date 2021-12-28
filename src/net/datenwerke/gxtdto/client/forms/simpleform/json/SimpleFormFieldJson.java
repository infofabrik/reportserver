package net.datenwerke.gxtdto.client.forms.simpleform.json;

import com.google.gwt.core.client.JavaScriptObject;

public class SimpleFormFieldJson extends JavaScriptObject {

   protected SimpleFormFieldJson() {
   }

   public final native String getType() /*-{ return this.type; }-*/;

   public final native String getLabel() /*-{ return this.label; }-*/;

   public final native String getId() /*-{ return this.id; }-*/;

   public final native String getValue() /*-{ return this.value; }-*/;

}
