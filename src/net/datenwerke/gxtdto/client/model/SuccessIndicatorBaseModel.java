package net.datenwerke.gxtdto.client.model;

import java.util.ArrayList;

public class SuccessIndicatorBaseModel implements DwModel {

   /**
    * 
    */
   private static final long serialVersionUID = -8511693173829022226L;

   private boolean success;
   private ArrayList<KeyValueBaseModel<String>> data = new ArrayList<KeyValueBaseModel<String>>();

   public void setSuccess(boolean success) {
      this.success = success;
   }

   public boolean isSuccess() {
      return success;
   }

   public ArrayList<KeyValueBaseModel<String>> getData() {
      return data;
   }

   public void setData(ArrayList<KeyValueBaseModel<String>> data) {
      this.data = data;
   }

   public void addData(KeyValueBaseModel<String> data) {
      this.data.add(data);
   }

}
