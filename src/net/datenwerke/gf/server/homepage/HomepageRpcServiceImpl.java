package net.datenwerke.gf.server.homepage;

import static java.util.stream.Collectors.toList;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.configuration2.Configuration;

import com.google.inject.Provider;

import net.datenwerke.gf.client.homepage.rpc.HomepageRpcService;
import net.datenwerke.gf.service.DwGwtFrameworkModule;
import net.datenwerke.gf.service.lateinit.LateInitStartup;
import net.datenwerke.rs.configservice.service.configservice.ConfigService;
import net.datenwerke.security.server.SecuredRemoteServiceServlet;
import net.datenwerke.security.service.security.annotation.SecurityChecked;

@Singleton
public class HomepageRpcServiceImpl extends SecuredRemoteServiceServlet implements HomepageRpcService {

   private static final long serialVersionUID = 870178758140654429L;
   private static final String CONFIG_KEY_PAGE_TITLE = "pagetitle";

   public final static String SESSION_REDIRECT_URL = "rs.redirect.url";

   public static final String CONFIG_FILE = "security/whitelist.cf";
   public static final String ALLOWED_URLS = "urls.url";

   private ConfigService configService;
   private Provider<HttpServletRequest> request;

   @Inject
   public HomepageRpcServiceImpl(ConfigService configService, Provider<HttpServletRequest> request) {
      this.configService = configService;
      this.request = request;
   }

   @SecurityChecked(loginRequired = false)
   @Override
   public String getPageTitle() {
      return configService.getConfigFailsafe(DwGwtFrameworkModule.CONFIG_FILE).getString(CONFIG_KEY_PAGE_TITLE);
   }

   @SecurityChecked(loginRequired = false)
   @Override
   public boolean isStartupComplete() {
      return LateInitStartup.isStartupCompleted();
   }

   @Override
   public String getSessionRedirect() {
      HttpSession session = request.get().getSession();
      String url = (String) session.getAttribute(SESSION_REDIRECT_URL);
      session.removeAttribute(SESSION_REDIRECT_URL);
      return url;
   }

   @Override
   public void assertAllowsRedirect(final String redir) {
      if (null == redir || "".equals(redir.trim())) // no redirect requested
         return;

      if (null != redir) {
         String locationHost = request.get().getScheme() + "://" + request.get().getServerName();
         List<String> allowedUrls = fetchAllowedUrls();
         boolean allowRedirect = redir.startsWith(locationHost)
               || allowedUrls.stream().anyMatch(url -> redir.startsWith(url));

         if (!allowRedirect)
            throw new IllegalArgumentException("Redir not allowed");
      }

   }

   private List<String> fetchAllowedUrls() {
      Configuration config = configService.getConfigFailsafe(CONFIG_FILE);

      return config.getList(ALLOWED_URLS).stream().filter(o -> o instanceof String).map(o -> (String) o)
            .collect(toList());
   }
}
