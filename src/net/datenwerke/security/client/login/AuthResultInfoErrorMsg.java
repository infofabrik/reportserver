package net.datenwerke.security.client.login;

public class AuthResultInfoErrorMsg extends AuthenticateResultInfo {

   /**
    * 
    */
   private static final long serialVersionUID = 8006438110041960063L;

   private String title;
   private String message;

   public void setMessage(String message) {
      this.message = message;
   }

   public String getMessage() {
      return message;
   }

   public void setTitle(String title) {
      this.title = title;
   }

   public String getTitle() {
      return title;
   }

}
