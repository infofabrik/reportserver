package net.datenwerke.gxtdto.client.baseex.widget.window;

import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;

import net.datenwerke.gxtdto.client.baseex.widget.DwWindow;
import net.datenwerke.gxtdto.client.baseex.widget.btn.DwTextButton;
import net.datenwerke.gxtdto.client.locale.BaseMessages;

public class SimpleDialogWindow extends DwWindow {

   public SimpleDialogWindow() {
      super();

      initializeUi();
   }

   protected void initializeUi() {
      /* cancel */
      DwTextButton cancelBtn = new DwTextButton(BaseMessages.INSTANCE.cancel());
      cancelBtn.addSelectHandler(new SelectHandler() {
         @Override
         public void onSelect(SelectEvent event) {
            cancelButtonClicked();
         }
      });
      getButtonBar().add(cancelBtn);

      /* submit */
      DwTextButton submitBtn = new DwTextButton(BaseMessages.INSTANCE.submit());
      submitBtn.addSelectHandler(new SelectHandler() {

         @Override
         public void onSelect(SelectEvent event) {
            submitButtonClicked();
         }
      });
      getButtonBar().add(submitBtn);
   }

   protected void cancelButtonClicked() {
      hide();
   }

   protected void submitButtonClicked() {
      hide();
   }
}
