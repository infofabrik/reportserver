package net.datenwerke.security.ext.client.usermanager.objectinfo;

import java.util.Date;

import com.google.gwt.resources.client.ImageResource;

import net.datenwerke.gxtdto.client.objectinformation.hooks.ObjectInfoKeyInfoProviderImpl;
import net.datenwerke.rs.theme.client.icon.BaseIcon;
import net.datenwerke.security.client.usermanager.dto.GroupDto;

public class GroupObjectInfo extends ObjectInfoKeyInfoProviderImpl<GroupDto>{

   @Override
   public boolean consumes(Object object) {
      return object instanceof GroupDto;
   }

   @Override
   protected String doGetName(GroupDto object) {
      return object.getName();
   }

   @Override
   protected String doGetDescription(GroupDto object) {
      return object.getDescription();
   }

   @Override
   protected String doGetType(GroupDto object) {
      return null;
   }

   @Override
   protected Date doGetLastUpdatedOn(GroupDto object) {
      return object.getLastUpdated();
   }

   @Override
   protected Date doGetCreatedOn(GroupDto object) {
      return object.getCreatedOn();
   }

   @Override
   protected ImageResource doGetIconSmall(GroupDto object) {
      return BaseIcon.GROUP.toImageResource();
   }

}
