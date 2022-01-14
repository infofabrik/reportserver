package net.datenwerke.security.service.usermanager.mockup;

import java.util.List;
import java.util.Set;

import net.datenwerke.security.service.usermanager.UserPropertiesService;
import net.datenwerke.security.service.usermanager.entities.User;
import net.datenwerke.security.service.usermanager.entities.UserProperty;

public class UserPropertiesServiceMockup implements UserPropertiesService {

   @Override
   public void setProperty(User user, UserProperty property) {
      // TODO Auto-generated method stub

   }

   @Override
   public void setPropertyValue(User user, String key, Object value) {
      // TODO Auto-generated method stub

   }

   @Override
   public UserProperty getProperty(User user, String key) {
      // TODO Auto-generated method stub
      return null;
   }

   @Override
   public String getPropertyValue(User user, String key) {
      // TODO Auto-generated method stub
      return null;
   }

   @Override
   public void removeProperty(User user, String key) {
      // TODO Auto-generated method stub

   }

   @Override
   public boolean removeProperty(User user, UserProperty property) {
      // TODO Auto-generated method stub
      return false;
   }

   @Override
   public Set<UserProperty> getProperties(User user) {
      // TODO Auto-generated method stub
      return null;
   }

   @Override
   public void setProperties(User user, Set<UserProperty> properties) {
      // TODO Auto-generated method stub

   }

   @Override
   public List<String> getPropertyKeys() {
      return null;
   }

}
