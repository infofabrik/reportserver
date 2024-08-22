package net.datenwerke.rs.transport.client.transport.dto;

import java.io.Serializable;

public class TransportCheckEntryDto implements Serializable {

   /**
    * 
    */
   private static final long serialVersionUID = 1L;

   private String key;
   private String result;
   private String errorMsg;

   public TransportCheckEntryDto() {
   }
   
   public TransportCheckEntryDto(String key, String result, String errorMsg) {
      this.key = key;
      this.result = result;
      this.errorMsg = errorMsg;
   }

   public String getKey() {
      return key;
   }

   public void setKey(String key) {
      this.key = key;
   }

   public String getResult() {
      return result;
   }

   public void setResult(String result) {
      this.result = result;
   }

   public String getErrorMsg() {
      return errorMsg;
   }

   public void setErrorMsg(String errorMsg) {
      this.errorMsg = errorMsg;
   }

}
