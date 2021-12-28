package net.datenwerke.security.service.eventlogger;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.rs.utils.eventlogger.eventbus.LoggedEvent;
import net.datenwerke.security.service.authenticator.AuthenticatorService;
import net.datenwerke.security.service.authenticator.exceptions.AuthenticatorRuntimeException;
import net.datenwerke.security.service.usermanager.entities.User;

public abstract class DwLoggedEvent implements LoggedEvent {

   protected Long userId = null;
   private Map<String, String> loggedProperties = new HashMap<String, String>();
   private Date date = new GregorianCalendar().getTime();

   public DwLoggedEvent() {
   }

   public DwLoggedEvent(Object... properties) {
      for (int i = 0; i < properties.length; i += 2) {
         String key = null == properties[i] ? null : properties[i].toString();
         if (null == key)
            throw new IllegalArgumentException();
         String value = properties.length < i + 1 ? "NULL"
               : null == properties[i + 1] ? "NULL" : properties[i + 1].toString();

         loggedProperties.put(key, value);
      }
   }

   @Inject
   public void setAuthenticatorServiceProvider(Provider<AuthenticatorService> authenticatorServiceProvider) {
      try {
         if (null == userId) {
            User user = authenticatorServiceProvider.get().getCurrentUser();
            if (null != user)
               this.userId = user.getId();
         }
      } catch (AuthenticatorRuntimeException e) {
         loggedProperties.put("user_load_error", e.getMessage());
      }
   }

   @Override
   public Long getLoggedUserId() {
      return userId;
   }

   @Override
   public Map<String, String> getLoggedProperties() {
      return loggedProperties;
   }

   public void setLoggedProperties(Map<String, String> properties) {
      this.loggedProperties = properties;
   }

   public void addLoggedProperties(Map<String, String> properties) {
      this.loggedProperties.putAll(properties);
   }

   public void addLoggedProperty(String key, String value) {
      this.loggedProperties.put(key, value);
   }

   public void addLoggedProperty(String key, Long l) {
      if (null == l)
         addLoggedProperty(key, "NULL");
      else
         addLoggedProperty(key, l.toString());
   }

   @Override
   public Date getDate() {
      return date;
   }

   @Override
   public void aboutToBeLogged() {
   }
}
