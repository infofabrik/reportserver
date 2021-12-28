package net.datenwerke.security.service.authenticator;

import static java.util.stream.Collectors.toSet;

import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;

import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.core.service.reportserver.ReportServerService;
import net.datenwerke.rs.core.service.reportserver.ReportServerServiceImpl;
import net.datenwerke.rs.utils.eventbus.EventBus;
import net.datenwerke.security.client.login.AuthToken;
import net.datenwerke.security.service.authenticator.events.FailedLoginEvent;
import net.datenwerke.security.service.authenticator.events.LoginEvent;
import net.datenwerke.security.service.authenticator.events.LogoutEvent;
import net.datenwerke.security.service.authenticator.events.SuEvent;
import net.datenwerke.security.service.authenticator.exceptions.AuthenticatorRuntimeException;
import net.datenwerke.security.service.authenticator.hooks.PostAuthenticateHook;
import net.datenwerke.security.service.authenticator.hooks.PreAuthenticateHook;
import net.datenwerke.security.service.usermanager.UserManagerService;
import net.datenwerke.security.service.usermanager.entities.AbstractUserManagerNode;
import net.datenwerke.security.service.usermanager.entities.User;

@Singleton
public class AuthenticatorServiceImpl implements AuthenticatorService {

   protected final Logger logger = LoggerFactory.getLogger(getClass().getName());

   private final Provider<Set<ReportServerPAM>> pams;
   private final Provider<RequestUserCache> requestUserCacheProvider;
   private final Provider<UserManagerService> userManagerServiceProvider;
   private final HookHandlerService hookHandlerService;
   private final Provider<CurrentUser> currentUserProvider;
   private final ThreadLocal<Long> currentUserInThread;
   private final Provider<HttpServletRequest> servletRequestProvider;
   private final EventBus eventBus;
   private final Provider<ReportServerService> reportServerServiceProvider;

   private final Map<Long, Long> lastRequests = new HashMap<Long, Long>();

   @Inject
   public AuthenticatorServiceImpl(Provider<Set<ReportServerPAM>> pams,
         Provider<RequestUserCache> requestUserCacheProvider, Provider<UserManagerService> userManagerServiceProvider,
         HookHandlerService hookHandlerService, Provider<CurrentUser> currentUserProvider,
         Provider<HttpServletRequest> servletRequestProvider, EventBus eventBus,
         Provider<ReportServerService> reportServerServiceProvider) {
      /* store objects */
      this.pams = pams;
      this.requestUserCacheProvider = requestUserCacheProvider;
      this.userManagerServiceProvider = userManagerServiceProvider;
      this.hookHandlerService = hookHandlerService;
      this.currentUserProvider = currentUserProvider;
      this.servletRequestProvider = servletRequestProvider;
      this.eventBus = eventBus;
      this.currentUserInThread = new ThreadLocal<Long>();
      this.reportServerServiceProvider = reportServerServiceProvider;
   }

   public Set<String> getRequiredClientModules() {
      return pams.get().stream().map(ReportServerPAM::getClientModuleName).filter(modName -> null != modName)
            .collect(toSet());
   }

   public AuthenticationResult authenticate(final AuthToken[] tokens) {
      hookHandlerService.getHookers(PreAuthenticateHook.class).forEach(hook -> hook.authenticating(tokens));

      AuthenticationResult authRes = evaluateTokens(tokens);

      // call this after for collecting updated session data
      hookHandlerService.getHookers(PostAuthenticateHook.class).forEach(hook -> hook.authenticated(authRes));

      if (authRes.isAllowed() && null != authRes.getUser()) {

         // https://wiki.owasp.org/index.php/Testing_for_Session_Fixation_(OTG-SESS-003)
         servletRequestProvider.get().changeSessionId();

         setCurrentUserId(authRes.getUser().getId());

         eventBus.fireEvent(new LoginEvent("user", getCurrentUserId()));
      } else {
         String suser = (null != authRes.getUser()) ? authRes.getUser().getUsername() : "NULL";
         eventBus.fireEvent(new FailedLoginEvent("user", suser));
      }

      // call this after for collecting updated session data
      collectServerInformation(authRes);

      return authRes;
   }

   private void collectServerInformation(AuthenticationResult authRes) {
      if (authRes.isAllowed()) {
         HttpServletRequest httpServletRequest = servletRequestProvider.get();
         if (null == httpServletRequest)
            return;

         ((ReportServerServiceImpl) reportServerServiceProvider.get()).init(httpServletRequest);
      }
   }

   @Override
   public void setAuthenticated(Long userId) {
      setCurrentUserId(userId);
      eventBus.fireEvent(new LoginEvent("user", getCurrentUserId()));
      try {
         User user = getCurrentUser();
         if (null == user)
            logoff();
      } catch (Exception e) {
         logger.warn(e.getMessage(), e);
      }
   }

   @Override
   public void setAuthenticatedInThread(Long userId) {
      if (null != currentUserInThread.get())
         throw new AuthenticatorRuntimeException("User already logged in");
      currentUserInThread.set(userId);
   }

   @Override
   public void logoffUserInThread() {
      if (null == currentUserInThread.get())
         throw new AuthenticatorRuntimeException("No user logged in");
      currentUserInThread.set(null);
   }

   private AuthenticationResult evaluateTokens(AuthToken[] tokens) {
      User tmpUser = null;
      boolean result = true;

      for (ReportServerPAM pam : pams.get()) {
         AuthenticationResult authRes = pam.authenticate(tokens);

         if (!authRes.isAllowed())
            result = false;

         User authUser = authRes.getUser();
         if (null != authUser) {
            if (null == tmpUser)
               tmpUser = authUser;

            if (!tmpUser.equals(authUser))
               result = false;
         }
      }
      if (null == tmpUser)
         result = false;

      return new AuthenticationResult(result, tmpUser);
   }

   public User getCurrentUser() {
      if (null != currentUserInThread.get()) {
         Long id = currentUserInThread.get();
         AbstractUserManagerNode userNode = userManagerServiceProvider.get().getNodeById(id);
         if (!(userNode instanceof User))
            throw new AuthenticatorRuntimeException(
                  "Something went terribly wrong. UserId (" + id + ") does not point at user."); //$NON-NLS-1$ //$NON-NLS-2$

         return (User) userNode;
      }

      if (null == getCurrentUserId())
         throw new AuthenticatorRuntimeException("no user logged in");

      return requestUserCacheProvider.get().getUser(getCurrentUserId());
   }

   public boolean isAuthenticated() {
      if (null != currentUserInThread.get()) {
         return true;
      } else if (null != getCurrentUserId()) {
         return true;
      } else {
         // try login without any tokens to perform autologin
         authenticate(new AuthToken[0]);
         return null != getCurrentUserId();
      }
   }

   public void logoff() {
      CurrentUser currentUser = currentUserProvider.get();
      if (null != currentUser.getSudoParent()) {
         eventBus.fireEvent(new SuEvent("sued_user_end", currentUser.getCurrentUserId()));
         currentUser.setCurrentUserId(currentUser.getSudoParent());
         currentUserInThread.set(null);
         currentUser.setSudoParent(null);
      } else {
         eventBus.fireEvent(new LogoutEvent("user", getCurrentUserId()));
         setCurrentUserId(null);
         currentUserInThread.set(null);
         servletRequestProvider.get().getSession().invalidate();
         try {
            servletRequestProvider.get().logout();
         } catch (Exception e) {
            logger.warn(e.getMessage(), e);
         }
      }
   }

   @Override
   public void su(User user) {
      Long parent = getCurrentUserId();
      if (null == parent)
         throw new AuthenticatorRuntimeException("no user logged in");

      eventBus.fireEvent(new SuEvent("sued_user", user.getId()));

      CurrentUser currentUser = currentUserProvider.get();
      currentUser.setCurrentUserId(user.getId());
      currentUser.setSudoParent(parent);
   }

   private Long getCurrentUserId() {
      try {
         Long id = currentUserProvider.get().getCurrentUserId();

         if (null != id)
            lastRequests.put(id, new GregorianCalendar().getTimeInMillis());

         return id;
      } catch (Exception e) {
         return null;
      }
   }

   private void setCurrentUserId(Long userId) {
      try {
         currentUserProvider.get().setCurrentUserId(userId);
      } catch (Exception e) {
         logger.warn(e.getMessage(), e);
      }
   }

   @Override
   public Map<Long, Long> getLastRequests() {
      return new HashMap<Long, Long>(lastRequests);
   }

}
