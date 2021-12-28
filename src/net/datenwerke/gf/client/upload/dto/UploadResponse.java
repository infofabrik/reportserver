package net.datenwerke.gf.client.upload.dto;

import java.io.Serializable;

import net.datenwerke.gxtdto.client.model.DwModel;

public class UploadResponse implements Serializable, DwModel {

   /**
    * 
    */
   private static final long serialVersionUID = 6068627923848012782L;

   private boolean success = true;
   private String id;
   private String name;
   private long length;
   private String errorMsg;

   public String getErrorMsg() {
      return errorMsg;
   }

   public void setErrorMsg(String errorMsg) {
      this.errorMsg = errorMsg;
   }

   public UploadResponse() {
   }

   public UploadResponse(String id) {
      this.id = id;
   }

   public String getId() {
      return id;
   }

   public void setId(String id) {
      this.id = id;
   }

   public boolean isSuccess() {
      return success;
   }

   public void setSuccess(boolean success) {
      this.success = success;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public long getLength() {
      return length;
   }

   public void setLength(long length) {
      this.length = length;
   }

}
