package net.datenwerke.gxtdto.client.servercommunication.callback;

import com.google.gwt.http.client.Request;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.sencha.gxt.widget.core.client.info.DefaultInfoConfig;
import com.sencha.gxt.widget.core.client.info.Info;
import com.sencha.gxt.widget.core.client.info.InfoConfig;

import net.datenwerke.gxtdto.client.baseex.widget.mb.DwAlertMessageBox;
import net.datenwerke.gxtdto.client.locale.BaseMessages;

public class NotamCallback<T> implements AsyncCallback<T> {

   protected String successText = null;
   protected String successTitle = null;
   protected String errorText = null;
   protected String errorTitle = null;

   private Request request;

   public NotamCallback(String successText) {
      this.successText = successText;
      this.successTitle = BaseMessages.INSTANCE.changesApplied();

      doOnStartup();
   }

   public NotamCallback(String successText, String errorText) {
      this.successText = successText;
      this.successTitle = BaseMessages.INSTANCE.changesApplied();
      this.errorText = errorText;
      this.errorTitle = BaseMessages.INSTANCE.error();

      doOnStartup();
   }

   public NotamCallback(String errorTitle, String errorText, String successTitle, String successText) {
      this.successText = successText;
      this.errorText = errorText;
      this.errorTitle = errorTitle;
      this.successTitle = successTitle;

      doOnStartup();
   }

   @Override
   final public void onFailure(Throwable caught) {
      doOnFinish();
      doOnFailure(caught);
   }

   @Override
   final public void onSuccess(T result) {
      doOnFinish();
      if (null != successTitle && null != successText) {
         InfoConfig infoConfig = new DefaultInfoConfig(successTitle, successText);
         infoConfig.setWidth(350);
         Info.display(infoConfig);
      }
      doOnSuccess(result);
   }

   /**
    * Can be called by the implementor to display an error message
    * 
    * @param caught
    */
   protected void displayErrrorMessage(final Throwable caught) {
      if (null != errorText && null != errorTitle) {
         new DwAlertMessageBox(errorTitle, errorText) {
            protected void onHide() {
            }
         }.show();
      }
   }

   public void setRequest(Request request) {
      this.request = request;
   }

   public Request getRequest() {
      return request;
   }

   public void doOnSuccess(T result) {
   }

   public void doOnFailure(Throwable caught) {
      displayErrrorMessage(caught);
   }

   public void doOnStartup() {
   };

   public void doOnFinish() {
   }

}
