package net.datenwerke.gxtdto.client.servercommunication.exceptions;

import com.google.gwt.core.shared.GWT;

import net.datenwerke.gxtdto.client.locale.BaseMessages;

public class NonFatalException extends ServerCallFailedException {

   /**
    * 
    */
   private static final long serialVersionUID = 6628762392625525950L;

   public NonFatalException() {
      super();
   }

   public NonFatalException(String msg) {
      super(msg);
   }

   public NonFatalException(String msg, Exception e) {
      super(msg, e);
   }

   public NonFatalException(Exception e) {
      super(e);
   }

   @Override
   public String getTitle() {
      if (GWT.isClient())
         return BaseMessages.INSTANCE.encounteredError();
      return "Encountered an error";
   }

}
