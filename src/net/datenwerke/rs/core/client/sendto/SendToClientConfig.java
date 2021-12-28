package net.datenwerke.rs.core.client.sendto;

import net.datenwerke.gxtdto.client.dtomanager.Dto;

public class SendToClientConfig extends Dto {

   /**
    * 
    */
   private static final long serialVersionUID = -3426742554314661101L;

   private String id;
   private String title;
   private String form;
   private String icon;
   private boolean supportsScheduling;
   private boolean selectFormat;

   public SendToClientConfig() {
   }

   public SendToClientConfig(String title) {

   }

   public String getId() {
      return id;
   }

   public void setId(String id) {
      this.id = id;
   }

   public String getTitle() {
      return title;
   }

   public void setTitle(String title) {
      this.title = title;
   }

   public String getForm() {
      return form;
   }

   public void setForm(String form) {
      this.form = form;
   }

   public void setIcon(String icon) {
      this.icon = icon;
   }

   public String getIcon() {
      return icon;
   }

   public boolean isSelectFormat() {
      return selectFormat;
   }

   public void setSelectFormat(boolean selectFormat) {
      this.selectFormat = selectFormat;
   }

   public boolean isSupportsScheduling() {
      return supportsScheduling;
   }

   public void setSupportsScheduling(boolean supportsScheduling) {
      this.supportsScheduling = supportsScheduling;
   }

}
