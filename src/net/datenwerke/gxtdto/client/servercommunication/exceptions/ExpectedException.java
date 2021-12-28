package net.datenwerke.gxtdto.client.servercommunication.exceptions;

import com.google.gwt.core.shared.GWT;

import net.datenwerke.gxtdto.client.locale.BaseMessages;

public class ExpectedException extends ServerCallFailedException {

   /**
    * 
    */
   private static final long serialVersionUID = -4898107548666705902L;

   public ExpectedException() {
      super();
   }

   public ExpectedException(String msg) {
      super(msg);
   }

   public ExpectedException(Throwable e) {
      super(e);
   }

   public ExpectedException(String msg, Exception e) {
      super(msg);
      initCause(e);
   }

   public String getTitle() {
      if (GWT.isClient())
         return BaseMessages.INSTANCE.encounteredError();
      return "Encountered an error";
   }

}
