package net.datenwerke.gxtdto.client.model;

public class KeyValueBaseModel<O> implements DwModel {

   /**
    * 
    */
   private static final long serialVersionUID = 3515445093119027017L;

   private String key;
   private O value;

   public KeyValueBaseModel() {
   }

   public KeyValueBaseModel(String key, O value) {
      this.key = key;
      this.value = value;
   }

   public void setKey(String key) {
      this.key = key;
   }

   public String getKey() {
      return key;
   }

   public void setValue(O value) {
      this.value = value;
   }

   public O getValue() {
      return value;
   }

}
