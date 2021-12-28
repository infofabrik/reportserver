package net.datenwerke.security.service.usermanager;

import java.util.List;
import java.util.Set;

import net.datenwerke.security.service.usermanager.entities.User;
import net.datenwerke.security.service.usermanager.entities.UserProperty;

public interface UserPropertiesService {

   public void setProperty(User user, UserProperty property);

   public void setPropertyValue(User user, String key, Object value);

   public UserProperty getProperty(User user, String key);

   public String getPropertyValue(User user, String key);

   public void removeProperty(User user, String key);

   public boolean removeProperty(User user, UserProperty property);

   public Set<UserProperty> getProperties(User user);

   public void setProperties(User user, Set<UserProperty> properties);

   public List<String> getPropertyKeys();

}
