package net.datenwerke.security.ext.client.usermanager.objectinfo;

import com.google.gwt.resources.client.ImageResource;
import com.google.inject.Inject;

import net.datenwerke.gxtdto.client.objectinformation.hooks.GeneralObjectInfoImpl;
import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.theme.client.icon.BaseIcon;
import net.datenwerke.security.client.usermanager.dto.UserDto;

public class UserObjectInfo extends GeneralObjectInfoImpl<UserDto> {
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
   protected String doGetType(UserDto user) {
      return null;
   }

   @Override
   protected ImageResource doGetIconSmall(UserDto user) {
      return BaseIcon.USER_PROFILE.toImageResource();
   }

   @Override
   protected String doGetKey(UserDto object) {
      return null;
   }
   
   @Override
   public boolean hasKeyAttribute() {
      return false;
   }
}
