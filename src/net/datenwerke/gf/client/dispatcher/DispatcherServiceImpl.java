package net.datenwerke.gf.client.dispatcher;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.safehtml.shared.UriUtils;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.Window.ClosingEvent;
import com.google.gwt.user.client.Window.ClosingHandler;
import com.google.gwt.user.client.Window.Location;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;
import com.sencha.gxt.core.client.util.DelayedTask;

import net.datenwerke.gf.client.config.ClientConfigService;
import net.datenwerke.gf.client.dispatcher.hooks.DispatcherTakeOverHook;
import net.datenwerke.gf.client.homepage.DwMainViewportUiService;
import net.datenwerke.gf.client.homepage.HomepageDao;
import net.datenwerke.gf.client.login.LoginService;
import net.datenwerke.gxtdto.client.dtomanager.callback.RsAsyncCallback;
import net.datenwerke.gxtdto.client.utilityservices.UtilsUIService;
import net.datenwerke.gxtdto.client.waitonevent.CallbackOnEventDone;
import net.datenwerke.gxtdto.client.waitonevent.WaitOnEventUIService;
import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.security.client.login.AuthenticateCallback;

/**
 * A simple dispatcher service that checks if the user is currently logged in
 * and display either the login or the homepage screen
 * 
 *
 */
@Singleton
public class DispatcherServiceImpl implements DispatcherService {

   protected static final String REPORTSERVER_FORCE_LOGIN = "forceLogin";

   private final Provider<LoginService> loginService;
   private final Provider<HomepageDao> homepage;
   private final Provider<DwMainViewportUiService> homepageProvider;
   private final WaitOnEventUIService waitOnEventService;
   private final HookHandlerService hookHandler;
   private final ClientConfigService clientConfigService;
   private final UtilsUIService utilsUIService;
   private final HomepageDao homepageDao;

   private boolean warnOnExit = true;

   @Inject
   public DispatcherServiceImpl(Provider<LoginService> login, Provider<HomepageDao> homepage,
         Provider<DwMainViewportUiService> homepageProvider, WaitOnEventUIService waitOnEventService,
         HookHandlerService hookHandler, ClientConfigService clientConfigService, UtilsUIService utilsUIService,
         HomepageDao homepageDao) {

      this.loginService = login;
      this.homepage = homepage;
      this.homepageProvider = homepageProvider;
      this.waitOnEventService = waitOnEventService;
      this.hookHandler = hookHandler;
      this.clientConfigService = clientConfigService;
      this.utilsUIService = utilsUIService;
      this.homepageDao = homepageDao;
   }

   /**
    * Returns control to the dispatcher that decides which module will be rendered.
    */
   public void dispatch() {
      homepage.get().isStartupComplete(new RsAsyncCallback<Boolean>() {
         @Override
         public void onSuccess(Boolean isReady) {
            if (isReady) {
               doDispatch();
            } else {
               /* check for parameter */
               if ("true".equals(Location.getParameter(REPORTSERVER_FORCE_LOGIN)))
                  doDispatch();
               else {
                  DelayedTask dt = new DelayedTask() {
                     @Override
                     public void onExecute() {
                        dispatch();
                     }
                  };

                  dt.delay(5000);
               }
            }
         }

      });

   }

   private void doDispatch() {
      /* register callback function and trigger event */
      waitOnEventService.triggerEvent(REPORTSERVER_EVENT_BEFORE_STARTUP, new CallbackOnEventDone() {
         public void execute() {
            final LoginService loginS = loginService.get();
            loginS.tryReAuthenticate(new AuthenticateCallback() {

               public void execute(final boolean authenticateSucceeded) {
                  waitOnEventService.triggerEvent(REPORTSERVER_EVENT_DISPATCHING_STARTED);
                  if (authenticateSucceeded) {
                     handleRedirect();
                  } else {
                     dispatchTo(loginS);
                  }
               }
            });
         }
      });
   }

   private void handleRedirect() {
      final String redir = Window.Location.getParameter("redir");
      final String safeRedir = UriUtils.fromString(null != redir ? redir : "").asString();

      homepageDao.assertAllowsRedirect(safeRedir, new RsAsyncCallback<Void>() {
         @Override
         public void onSuccess(Void result) {
            if (null != redir)
               redirect(safeRedir, true);
            else {
               homepageDao.getSessionRedirect(new RsAsyncCallback<String>() {
                  @Override
                  public void onSuccess(String url) {
                     if (null == url || url.isEmpty()) {
                        completeDispatch();
                     } else {
                        redirect(url, true);
                     }
                  }
               });
            }
         }

         @Override
         public void onFailure(Throwable caught) {
            super.onFailure(caught);
         }
      });

   }

   private void completeDispatch() {
      boolean takeOverActive = false;
      for (DispatcherTakeOverHook hooker : hookHandler.getHookers(DispatcherTakeOverHook.class)) {
         if (hooker.isActive()) {
            Dispatchable dispatchable = hooker.getDispatcheable();
            dispatchTo(dispatchable);

            takeOverActive = true;
            break;
         }
      }

      /* if no one takes over use the default login */
      if (!takeOverActive)
         dispatchTo(homepageProvider.get());

      /* notify application of login */
      Scheduler.get().scheduleDeferred(new Command() {
         @Override
         public void execute() {
            waitOnEventService.triggerEvent(REPORTSERVER_EVENT_USER_LOGGED_IN_APPLICATION_LOADED);

            /* setup client */
            if (clientConfigService.getBoolean("warnonexit", true)) {
               Window.addWindowClosingHandler(new ClosingHandler() {
                  @Override
                  public void onWindowClosing(ClosingEvent event) {
                     if (isWarnOnExit())
                        event.setMessage("reportserver");
                  }
               });
            }
         }
      });
   }

   private void dispatchTo(Dispatchable dispatchable) {
      RootPanel.get().clear();
      disableDefaultContextMenu();
      hideLoadingPanel();
      RootPanel.get().add(dispatchable.getWidget());
   }

   /**
    * Disable the browser's default right click response.
    */
   public void disableDefaultContextMenu() {
//		RootPanel.get().addDomHandler(new ContextMenuHandler() {
//			@Override 
//			public void onContextMenu(ContextMenuEvent event) {
//				event.preventDefault();
//				event.stopPropagation();
//			}
//		}, ContextMenuEvent.getType());
   }

   private void hideLoadingPanel() {
      final Element loading = DOM.getElementById("loading");
      if (loading != null) {
         Timer t = new Timer() {
            @Override
            public void run() {
               loading.removeFromParent();
            }
         };
         t.schedule(500);
      }
   }

   @Override
   public void redirect(String url, boolean disableExitWarning) {
      if (disableExitWarning)
         setWarnOnExit(false);

      utilsUIService.redirect(url);

   }

   @Override
   public boolean isWarnOnExit() {
      return warnOnExit;
   }

   @Override
   public void setWarnOnExit(boolean warnOnExit) {
      this.warnOnExit = warnOnExit;
   }

}
