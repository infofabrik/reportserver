package net.datenwerke.gxtdto.client.ui.helper.grid.keyvalue;

import java.io.Serializable;

import com.sencha.gxt.widget.core.client.event.RowDoubleClickEvent;

public class KeyValueProperty implements Serializable {

   /**
    * 
    */
   private static final long serialVersionUID = -6983346897176687545L;

   private final String key;
   private final String value;

   public KeyValueProperty(String key, String value) {
      this.key = key;
      this.value = value;
   }

   public String getKey() {
      return key;
   }

   public String getValue() {
      return value;
   }

   public void onRowDoubleClick(RowDoubleClickEvent event) {

   }

}