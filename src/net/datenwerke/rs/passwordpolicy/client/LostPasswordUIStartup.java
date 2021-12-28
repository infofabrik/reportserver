package net.datenwerke.rs.passwordpolicy.client;

import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.user.client.ui.Label;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.sencha.gxt.core.client.util.Margins;
import com.sencha.gxt.widget.core.client.box.MessageBox;
import com.sencha.gxt.widget.core.client.container.MarginData;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer.VerticalLayoutData;
import com.sencha.gxt.widget.core.client.form.TextField;
import com.sencha.gxt.widget.core.client.menu.Item;
import com.sencha.gxt.widget.core.client.menu.MenuItem;

import net.datenwerke.gxtdto.client.baseex.widget.DwWindow;
import net.datenwerke.gxtdto.client.baseex.widget.btn.DwTextButton;
import net.datenwerke.gxtdto.client.baseex.widget.mb.DwAlertMessageBox;
import net.datenwerke.gxtdto.client.baseex.widget.menu.DwMenuItem;
import net.datenwerke.gxtdto.client.dialog.error.DetailErrorDialog;
import net.datenwerke.gxtdto.client.dtomanager.callback.RsAsyncCallback;
import net.datenwerke.gxtdto.client.locale.BaseMessages;
import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.authenticator.client.login.hooks.LoginMenuHook;
import net.datenwerke.rs.passwordpolicy.client.lostpassword.LostPasswordDao;
import net.datenwerke.security.ext.client.security.locale.SecurityMessages;

public class LostPasswordUIStartup {

   @Inject
   public LostPasswordUIStartup(HookHandlerService hookHandler, final LostPasswordDao lostPasswordDao) {
      /* hook a menu item into the login dialog */
      final Provider<MenuItem> provider = new Provider<MenuItem>() {

         @Override
         public MenuItem get() {
            MenuItem menuItem = new DwMenuItem(SecurityMessages.INSTANCE.lostPassword().replaceAll("'", "''"));

            menuItem.addSelectionHandler(new SelectionHandler<Item>() {
               @Override
               public void onSelection(SelectionEvent<Item> selectionEvent) {
                  final DwWindow window = new DwWindow();
                  window.setHeading(SecurityMessages.INSTANCE.lostPassword());
                  window.setWidth(350);
                  window.setHeight(200);

                  VerticalLayoutContainer container = new VerticalLayoutContainer();

                  container.add(new Label(SecurityMessages.INSTANCE.lostPasswordMessageText()));

                  final TextField field = new TextField();
                  container.add(field, new VerticalLayoutData(1, 30, new Margins(10)));

                  DwTextButton cancel = new DwTextButton(BaseMessages.INSTANCE.cancel());
                  cancel.addSelectHandler(event -> window.hide());
                  window.addButton(cancel);

                  DwTextButton ok = new DwTextButton(BaseMessages.INSTANCE.submit());
                  ok.addSelectHandler(event -> {
                     window.hide();
                     lostPasswordDao.requestNewPassword(field.getCurrentValue(), new RsAsyncCallback<String>() {
                        @Override
                        public void onSuccess(String message) {
                           DwAlertMessageBox amb = new DwAlertMessageBox(BaseMessages.INSTANCE.infoLabel(), message);
                           amb.setIcon(MessageBox.ICONS.info());
                           amb.setWidth(400);
                           amb.show();

                           window.hide();
                        }

                        @Override
                        public void onFailure(Throwable caught) {
                           window.hide();
                           new DetailErrorDialog(caught).show();
                        }
                     });
                  });
                  window.addButton(ok);

                  window.add(container, new MarginData(10));

                  window.show();
               }
            });

            return menuItem;
         }
      };

      lostPasswordDao.isLostPasswordDisabled(new RsAsyncCallback<Boolean>() {
         @Override
         public void onSuccess(Boolean result) {
            if (!result)
               hookHandler.attachHooker(LoginMenuHook.class, new LoginMenuHook(provider));
         }

         @Override
         public void onFailure(Throwable caught) {
            super.onFailure(caught);
         }
      });
   }
}
