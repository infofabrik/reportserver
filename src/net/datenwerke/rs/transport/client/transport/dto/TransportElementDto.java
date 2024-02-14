package net.datenwerke.rs.transport.client.transport.dto;

import com.google.common.base.MoreObjects;

public class TransportElementDto {

   private String type;
   
   private String key;
   
   public TransportElementDto(String type, String key) {
      this.type = type;
      this.key = key;
   }

   public String getExportedDataArea() {
      return type;
   }

   public void setType(String type) {
      this.type = type;
   }

   public String getKey() {
      return key;
   }

   public void setKey(String key) {
      this.key = key;
   }
   
   @Override
   public String toString() {
      return MoreObjects.toStringHelper(getClass())
         .add("exportedDataArea", type)
         .add("Key", key)
         .toString();
   }
   
}
