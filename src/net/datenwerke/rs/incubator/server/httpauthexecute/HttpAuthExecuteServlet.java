package net.datenwerke.rs.incubator.server.httpauthexecute;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.proxy.HibernateProxy;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;
import com.google.inject.persist.Transactional;

import net.datenwerke.gf.service.tempfile.TempFileService;
import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.configservice.service.configservice.ConfigService;
import net.datenwerke.rs.core.server.reportexport.ReportExportServlet;
import net.datenwerke.rs.core.server.reportexport.helper.ReportSessionCache;
import net.datenwerke.rs.core.service.error.RsErrorHelper;
import net.datenwerke.rs.core.service.reportmanager.ReportExecutorService;
import net.datenwerke.rs.core.service.reportmanager.ReportService;
import net.datenwerke.rs.core.service.reportmanager.parameters.ParameterSetFactory;
import net.datenwerke.rs.core.service.reportserver.ReportServerService;
import net.datenwerke.rs.utils.exception.ExceptionServices;
import net.datenwerke.rs.utils.misc.HttpUtils;
import net.datenwerke.security.service.authenticator.AuthenticatorService;
import net.datenwerke.security.service.security.SecurityService;
import net.datenwerke.security.service.security.annotation.SecurityChecked;
import net.datenwerke.security.service.usermanager.UserManagerService;
import net.datenwerke.security.service.usermanager.entities.AbstractUserManagerNode;
import net.datenwerke.security.service.usermanager.entities.User;
import net.datenwerke.security.service.usermanager.entities.UserProperty;

@SecurityChecked(bypassInheritedMethods = true, bypass = true)
@Singleton
public class HttpAuthExecuteServlet extends ReportExportServlet {

   private static final long serialVersionUID = -5957975426293086762L;
   private static final String USER_PROPERTY_API_KEY = "apikey";

   private final Provider<UserManagerService> userManagerProvider;

   public static final String SERVLET_NAME = "httpauthexport";

   @Inject
   public HttpAuthExecuteServlet(Provider<AuthenticatorService> authenticatorService,
         Provider<HookHandlerService> hookHandlerProvider, Provider<SecurityService> securityServiceProvider,
         Provider<ReportExecutorService> reportExecutor, Provider<ReportService> reportService,
         Provider<ReportServerService> reportServerService, Provider<ConfigService> configService,
         Provider<UserManagerService> userManagerProvider, Provider<RsErrorHelper> errorHelperProvider,
         ParameterSetFactory parameterSetFactory, Provider<TempFileService> tempFileService,
         Provider<ExceptionServices> exceptionServices, Provider<ReportSessionCache> sessionCacheProvider,
         Provider<HttpUtils> httpUtilsProvider) {

      super(authenticatorService, hookHandlerProvider, securityServiceProvider, reportExecutor, reportService,
            reportServerService, errorHelperProvider, parameterSetFactory, tempFileService, exceptionServices,
            configService, sessionCacheProvider, httpUtilsProvider);

      this.userManagerProvider = userManagerProvider;
   }

   @Override
   @SecurityChecked(loginRequired = false)
   @Transactional(rollbackOn = { Exception.class })
   public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      try {
         if (null != getUser(req)) {
            authenticatorServiceProvider.get().setAuthenticatedInThread(getUser(req).getId());
         }
         super.doGet(req, resp);
      } finally {
         authenticatorServiceProvider.get().logoffUserInThread();
      }
   }

   @Override
   protected User getUser(HttpServletRequest req) {
      String username = req.getParameter("user");
      String reqApikey = req.getParameter("apikey");

      AbstractUserManagerNode umn = userManagerProvider.get().getUserByName(username);
      if (umn instanceof HibernateProxy)
         umn = (AbstractUserManagerNode) ((HibernateProxy) umn).getHibernateLazyInitializer().getImplementation();
      if (!(umn instanceof User))
         throw new IllegalArgumentException("Specified user id does not point to user");

      User user = (User) umn;
      if (null != umn) {
         UserProperty propApikey = user.getProperty(USER_PROPERTY_API_KEY);
         String apikey = null == propApikey ? null : propApikey.getValue();
         if (null != apikey && reqApikey.equals(apikey)) {
            return user;
         }
      }

      return super.getUser(req);
   }
}