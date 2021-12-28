package net.datenwerke.gxtdto.client.servercommunication.exceptions;

import com.google.gwt.core.shared.GWT;

import net.datenwerke.gxtdto.client.locale.BaseMessages;

public class ViolatedSecurityExceptionDto extends ExpectedException {

   /**
    * 
    */
   private static final long serialVersionUID = 3105308528853752910L;

   public ViolatedSecurityExceptionDto() {
      super("Violated Security"); //$NON-NLS-1$
   }

   public ViolatedSecurityExceptionDto(String msg) {
      super(msg);
   }

   public String getTitle() {
      if (GWT.isClient())
         return BaseMessages.INSTANCE.violatedSecurity();
      return "Violated Security";
   }
}
