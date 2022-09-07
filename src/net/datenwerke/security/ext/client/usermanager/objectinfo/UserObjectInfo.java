package net.datenwerke.security.ext.client.usermanager.objectinfo;

import java.util.Date;
import com.google.gwt.resources.client.ImageResource;
import com.google.inject.Inject;
import net.datenwerke.gxtdto.client.objectinformation.hooks.ObjectInfoKeyInfoProviderImpl;
import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.theme.client.icon.BaseIcon;
import net.datenwerke.security.client.usermanager.dto.UserDto;

public class UserObjectInfo extends ObjectInfoKeyInfoProviderImpl<UserDto> {
   @Inject
   public UserObjectInfo(HookHandlerService hookHandler) {
   }

   @Override
   public boolean consumes(Object object) {
      return object instanceof UserDto;
   }

   @Override
   protected String doGetName(UserDto object) {
      return object.getLastname() + " " + object.getFirstname();
   }

   @Override
   protected String doGetDescription(UserDto object) {
      return object.getDescription();
   }

   @Override
   protected Date doGetLastUpdatedOn(UserDto object) {
      return object.getLastUpdated();
   }

   @Override
   protected Date doGetCreatedOn(UserDto object) {
      return object.getCreatedOn();
   }

   @Override
   protected String doGetType(UserDto user) {
      return null;
   }

   @Override
   protected ImageResource doGetIconSmall(UserDto user) {
      return BaseIcon.USER_PROFILE.toImageResource();
   }
}
