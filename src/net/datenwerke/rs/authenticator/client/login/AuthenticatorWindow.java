package net.datenwerke.rs.authenticator.client.login;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.sencha.gxt.core.client.util.Margins;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.Container;
import com.sencha.gxt.widget.core.client.container.FlowLayoutContainer;
import com.sencha.gxt.widget.core.client.container.MarginData;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.Viewport;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.form.Field;
import com.sencha.gxt.widget.core.client.menu.Menu;

import net.datenwerke.gf.client.login.LoginService;
import net.datenwerke.gf.client.theme.ThemeUiService;
import net.datenwerke.gxtdto.client.baseex.widget.btn.DwSplitButton;
import net.datenwerke.gxtdto.client.baseex.widget.btn.DwTextButton;
import net.datenwerke.gxtdto.client.baseex.widget.menu.DwMenu;
import net.datenwerke.gxtdto.client.locale.BaseMessages;
import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.authenticator.client.login.hooks.LoginMenuHook;
import net.datenwerke.rs.authenticator.client.login.locale.LoginMessages;
import net.datenwerke.rs.authenticator.client.login.pam.ClientPAM;
import net.datenwerke.security.client.login.AuthToken;
import net.datenwerke.security.client.login.hooks.AuthenticatorWindowExtraOptionHook;
import net.datenwerke.security.client.login.hooks.AuthenticatorWindowExtraOptionHook.ExtraOptionPosition;

public class AuthenticatorWindow extends VerticalLayoutContainer {

   @Inject
   private static LoginService loginService;

   @Inject
   private static HookHandlerService hookHandlerService;

   @Inject
   private static ThemeUiService themeService;

   private TextButton loginButton;

   private Viewport viewport;

   public AuthenticatorWindow(List<ClientPAM> clientPAM) {
      super();

      /* create top toolbar */
      final FlowLayoutContainer topTb = new FlowLayoutContainer();
      topTb.addStyleName("rs-login-tbar");
      add(topTb, new VerticalLayoutData(1, -1));

      /* create logo */
      topTb.add(themeService.getLoginLogo());

      /* create language */
      hookHandlerService.getHookers(AuthenticatorWindowExtraOptionHook.class).stream()
            .filter(hooker -> hooker.getPosition() == ExtraOptionPosition.TOP)
            .forEach(hooker -> hooker.configure(topTb));

      /* load fields */
      // for now only a single page
      final FlowLayoutContainer fieldWrapper = new FlowLayoutContainer();
      fieldWrapper.addStyleName("rs-login-fields");
      add(fieldWrapper, new VerticalLayoutData(1, -1, new Margins(70, 0, 0, 0)));

      /* load fields */
      clientPAM.get(0).addFields(fieldWrapper, () -> loginButton.fireEvent(new SelectEvent()));

      /* load additional fields */
      hookHandlerService.getHookers(AuthenticatorWindowExtraOptionHook.class).stream()
            .filter(hooker -> hooker.getPosition() == ExtraOptionPosition.FIELD)
            .forEach(hooker -> hooker.configure(fieldWrapper));

      createLoginMenu();

      fieldWrapper.add(loginButton, new MarginData(0, 0, 10, 190));

      /* initialize event handling */
      initEventHandling(loginButton, clientPAM);

      /* display first page */
      /* set focus */
      addAttachHandler(event -> Scheduler.get().scheduleDeferred(() -> setFocusOnFirstField()));
   }

   private void initEventHandling(final TextButton finishButton, final List<ClientPAM> clientPAM) {
      finishButton.addSelectHandler(event -> {
         viewport.mask(BaseMessages.INSTANCE.busyMsg());
         List<AuthToken> tokens = new ArrayList<>();
         List<ClientPAM> cpams = new ArrayList<>(clientPAM);
         cpams.add(new ClientPAM() {
            @Override
            public void addResult(List<AuthToken> results, List<ClientPAM> next) {
               loginService.authenticate(results.toArray(new AuthToken[] {}), authenticateSucceeded -> {
                  viewport.unmask();
                  setFocusOnFirstField();
               });
            }

            public String getModuleName() {
               return null;
            }

            @Override
            public void addFields(FlowLayoutContainer container, ProceeedCallback callback) {
            }
         });

         cpams.get(0).addResult(tokens, cpams.subList(1, cpams.size()));
      });
   }

   private void setFocusOnFirstField() {
      Field firstField = getFirstField(this);
      if (null != firstField) {
         firstField.focus();
      }
   }

   private Field getFirstField(Container parent) {
      for (Widget c : parent)
         if (c instanceof Field)
            return (Field) c;

      for (Widget c : parent) {
         if (c instanceof Container) {
            Field res = getFirstField((Container) c);
            if (res != null)
               return res;
         }
      }

      return null;
   }

   private void createLoginMenu() {
      List<LoginMenuHook> hookers = hookHandlerService.getHookers(LoginMenuHook.class);

      /* load buttons */
      if (!hookers.isEmpty()) {
         loginButton = new DwSplitButton(LoginMessages.INSTANCE.login());
         final Menu m = new DwMenu();
         hookers.forEach(h -> m.add(h.getObject()));
         loginButton.setMenu(m);
      } else
         loginButton = new DwTextButton(LoginMessages.INSTANCE.login());

      loginButton.addStyleName("rs-login-btn");
      loginButton.setWidth(150);
   }

   public void setViewPort(Viewport p) {
      this.viewport = p;
   }

}
