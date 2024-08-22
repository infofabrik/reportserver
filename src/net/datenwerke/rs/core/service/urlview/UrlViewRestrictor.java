package net.datenwerke.rs.core.service.urlview;

import org.apache.commons.configuration2.HierarchicalConfiguration;

import com.google.inject.Inject;

import net.datenwerke.rs.utils.eventbus.EventBus;
import net.datenwerke.security.service.eventlogger.events.InvalidConfigEvent;
import net.datenwerke.security.service.usermanager.UserManagerService;
import net.datenwerke.security.service.usermanager.entities.AbstractUserManagerNode;
import net.datenwerke.security.service.usermanager.entities.Group;
import net.datenwerke.security.service.usermanager.entities.OrganisationalUnit;
import net.datenwerke.security.service.usermanager.entities.User;

public class UrlViewRestrictor {

   @Inject
   private static UserManagerService userService;

   @Inject
   private static EventBus eventBus;

   private static final String GROUP_RESTRICTION = "groups";
   private static final String OU_RESTRICTION = "ous";
   private static final String USER_RESTRICTION = "users";

   private static final String URLVIEW_ERROR_NAME = "urlview";

   private final User user;

   public UrlViewRestrictor(User user) {
      this.user = user;
   }

   public boolean restrictionApplies(HierarchicalConfiguration conf) {
      if (null == conf)
         return false;

      /* check users */
      String[] userList = conf.getString(USER_RESTRICTION).split(",");
      if (null != userList) {
         String userName = user.getUsername().trim();
         boolean found = false;
         for (String entry : userList) {
            String trimmedEntry = entry.trim();
            if (trimmedEntry.equals(userName)) {
               found = true;
               break;
            }
         }
         if (found)
            return false;
      }

      /* check groups */
      String[] groupList = conf.getString(GROUP_RESTRICTION).split(",");
      if (null != groupList) {
         boolean found = false;
         for (String entry : groupList) {
            String trimmedEntry = entry.trim();
            try {
               Long id = Long.valueOf(trimmedEntry);
               AbstractUserManagerNode node = userService.getNodeById(id);
               Group group = (Group) node;
               if (userService.userInFolk(user, group)) {
                  found = true;
                  break;
               }
            } catch (Exception e) {
               eventBus
                     .fireEvent(new InvalidConfigEvent(URLVIEW_ERROR_NAME, "Could not find group for entry: " + entry));
            }
         }
         if (found)
            return false;
      }

      /* check ous */
      String[] ouList = conf.getString(OU_RESTRICTION).split(",");
      if (null != ouList) {
         boolean found = false;
         for (String entry : ouList) {
            String trimmedEntry = entry.trim();
            try {
               Long id = Long.valueOf(trimmedEntry);
               AbstractUserManagerNode node = userService.getNodeById(id);
               OrganisationalUnit ou = (OrganisationalUnit) node;
               if (userService.userInFolk(user, ou)) {
                  found = true;
                  break;
               }
            } catch (Exception e) {
               eventBus.fireEvent(new InvalidConfigEvent(URLVIEW_ERROR_NAME,
                     "Could not find organisational unit for entry: " + entry));
            }
         }
         if (found)
            return false;
      }

      return true;
   }
}
