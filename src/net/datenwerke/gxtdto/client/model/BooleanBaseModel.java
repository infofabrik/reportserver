package net.datenwerke.gxtdto.client.model;

/**
 * A wrapper object to wrap Strings in a BaseModel e.g. for adding them to a
 * store.
 *
 */
public class BooleanBaseModel implements DwModel {

   /**
    * 
    */
   private static final long serialVersionUID = 9212304761181571631L;
   private boolean value;

   public BooleanBaseModel() {
   }

   public BooleanBaseModel(boolean value) {
      setValue(value);
   }

   public boolean isValue() {
      return value;
   }

   public void setValue(boolean value) {
      this.value = value;
   }

}
