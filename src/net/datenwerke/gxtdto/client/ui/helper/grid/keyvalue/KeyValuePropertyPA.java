package net.datenwerke.gxtdto.client.ui.helper.grid.keyvalue;

import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;

public interface KeyValuePropertyPA extends PropertyAccess<KeyValueProperty> {

   @Path("key")
   public ModelKeyProvider<KeyValueProperty> id();

   /* Properties */
   public ValueProvider<KeyValueProperty, String> key();

   public ValueProvider<KeyValueProperty, String> value();
}
