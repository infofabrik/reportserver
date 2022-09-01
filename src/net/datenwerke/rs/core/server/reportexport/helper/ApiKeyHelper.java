package net.datenwerke.rs.core.server.reportexport.helper;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.hibernate.proxy.HibernateProxy;

import com.google.inject.Provider;

import net.datenwerke.security.service.usermanager.UserManagerService;
import net.datenwerke.security.service.usermanager.entities.AbstractUserManagerNode;
import net.datenwerke.security.service.usermanager.entities.User;
import net.datenwerke.security.service.usermanager.entities.UserProperty;

public class ApiKeyHelper {

   private final static String AUTH_USER = "user";
   private final static String AUTH_METHOD = "Bearer";
   private final static String USER_PROPERTY_API_KEY = "apikey";
   
   private final Provider<UserManagerService> userManagerProvider; 
   
   @Inject
   public ApiKeyHelper(Provider<UserManagerService> userManagerProvider) {
      this.userManagerProvider = userManagerProvider;
   }
   
   public User getUser(HttpServletRequest httpRequest, String reqApikey) {
      String username = httpRequest.getParameter(AUTH_USER);
      
      AbstractUserManagerNode umn = userManagerProvider.get().getUserByName(username);
      if (umn instanceof HibernateProxy)
         umn = (AbstractUserManagerNode) ((HibernateProxy) umn).getHibernateLazyInitializer().getImplementation();
      if (!(umn instanceof User))
         throw new IllegalArgumentException("Specified username does not point to user");

      User user = (User) umn;
      if (null != umn) {
         UserProperty propApikey = user.getProperty(USER_PROPERTY_API_KEY);
         String apikey = null == propApikey ? null : propApikey.getValue();
         if (null != apikey && reqApikey.equals(apikey)) {
            return user;
         }
      }
      
      return null;
   }

   public String readApiKeyFromBearer(HttpServletRequest httpRequest) {
      String apiKey = null;

      String authHeader = httpRequest.getHeader("Authorization");
      if (authHeader != null) {
         authHeader = authHeader.trim();
         if (authHeader.toLowerCase().startsWith(AUTH_METHOD.toLowerCase() + " ")) {
            apiKey = authHeader.substring(AUTH_METHOD.length()).trim();
         }
      }

      return apiKey;
   }
}
