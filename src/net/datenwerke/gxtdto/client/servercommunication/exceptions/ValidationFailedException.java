package net.datenwerke.gxtdto.client.servercommunication.exceptions;

public class ValidationFailedException extends ExpectedException {

   /**
    * 
    */
   private static final long serialVersionUID = 8533685858563549723L;

   private String header;
   private String message;

   public ValidationFailedException() {
      super();
      // dummy constructor for serialization purposes
   }

   public ValidationFailedException(String header, String msg) {
      super(header + " - " + msg);
      this.header = header;
      this.message = msg;
   }

   public String getHeader() {
      return header;
   }

   public String getMessage() {
      return message;
   }

}
