package net.datenwerke.security.ext.client.usermanager.objectinfo;

import com.google.gwt.resources.client.ImageResource;

import net.datenwerke.gxtdto.client.objectinformation.hooks.GeneralObjectInfoImpl;
import net.datenwerke.rs.theme.client.icon.BaseIcon;
import net.datenwerke.security.client.usermanager.dto.GroupDto;

public class GroupObjectInfo extends GeneralObjectInfoImpl<GroupDto>{

   @Override
   public boolean consumes(Object object) {
      return object instanceof GroupDto;
   }

   @Override
   protected String doGetName(GroupDto object) {
      return object.getName();
   }

   @Override
   protected String doGetType(GroupDto object) {
      return null;
   }

   @Override
   protected ImageResource doGetIconSmall(GroupDto object) {
      return BaseIcon.GROUP.toImageResource();
   }

}
