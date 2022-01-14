package net.datenwerke.security.service.usermanager;

import java.util.List;
import java.util.Set;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.persistence.EntityManager;

import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.utils.simplequery.annotations.OrderBy;
import net.datenwerke.rs.utils.simplequery.annotations.SimpleQuery;
import net.datenwerke.security.service.eventlogger.annotations.FireRemoveEntityEvents;
import net.datenwerke.security.service.usermanager.entities.User;
import net.datenwerke.security.service.usermanager.entities.UserProperty;
import net.datenwerke.security.service.usermanager.entities.UserProperty__;
import net.datenwerke.security.service.usermanager.hooks.UserPropertyChangedHook;

public class UserPropertiesServiceImpl implements UserPropertiesService {

   private final Provider<EntityManager> entityManagerProvider;
   private final HookHandlerService hookHandlerService;

   @Inject
   public UserPropertiesServiceImpl(Provider<EntityManager> entityManagerProvider,
         HookHandlerService hookHandlerService) {
      this.entityManagerProvider = entityManagerProvider;
      this.hookHandlerService = hookHandlerService;
   }

   @Override
   public void setProperty(User user, UserProperty property) {
      UserProperty oldProperty = getProperty(user, property.getKey());
      fireBeforeChangeEvent(user, oldProperty, property);

      if (null != oldProperty)
         removeProperty(user, oldProperty);

      if (null != property)
         user.getProperties().add(property);

      fireAfterChangeEvent(user, oldProperty, property);
   }

   @Override
   public void setPropertyValue(User user, String key, Object value) {
      UserProperty oldProperty = getProperty(user, key);

      UserProperty newProperty = new UserProperty(key, String.valueOf(value));
      setProperty(user, newProperty);

      if (null != oldProperty) {
         removeProperty(user, oldProperty);
      }
   }

   @Override
   @SimpleQuery(select = UserProperty__.key, from = UserProperty.class, distinct = true, orderBy = @OrderBy(attribute = UserProperty__.key))
   public List<String> getPropertyKeys() {
      return null; // magic
   }

   @Override
   public UserProperty getProperty(User user, String key) {
      if (null == key)
         return null;
      for (UserProperty property : user.getProperties())
         if (key.equals(property.getKey()))
            return property;
      return null;
   }

   @Override
   public String getPropertyValue(User user, String key) {
      for (UserProperty property : user.getProperties())
         if (key.equals(property.getKey()))
            return property.getValue();
      return null;
   }

   @Override
   public void removeProperty(User user, String key) {
      UserProperty prop = getProperty(user, key);

      if (null != prop)
         removeProperty(user, prop);
   }

   @Override
   public boolean removeProperty(User user, UserProperty property) {
      fireBeforeChangeEvent(user, property, null);
      boolean res = user.getProperties().remove(property);
      remove(property);
      fireAfterChangeEvent(user, property, null);
      return res;
   }

   @Override
   public Set<UserProperty> getProperties(User user) {
      return user.getProperties();
   }

   @Override
   public void setProperties(User user, Set<UserProperty> properties) {
      for (UserProperty up : properties) {
         setProperty(user, up);
      }
   }

   private void fireBeforeChangeEvent(User user, UserProperty oldValue, UserProperty newValue) {
      for (UserPropertyChangedHook h : hookHandlerService.getHookers(UserPropertyChangedHook.class)) {
         h.beforeUserPropertyChange(user, oldValue, newValue);
      }
   }

   private void fireAfterChangeEvent(User user, UserProperty oldValue, UserProperty newValue) {
      for (UserPropertyChangedHook h : hookHandlerService.getHookers(UserPropertyChangedHook.class)) {
         h.afterUserPropertyChange(user, oldValue, newValue);
      }
   }

   @FireRemoveEntityEvents
   private void remove(UserProperty property) {
      EntityManager em = entityManagerProvider.get();
      property = em.find(property.getClass(), property.getId());
      if (null != property)
         em.remove(property);
   }

}
