package net.datenwerke.security.ext.client.usermanager.objectinfo;

import com.google.gwt.resources.client.ImageResource;

import net.datenwerke.gxtdto.client.objectinformation.hooks.GeneralObjectInfoImpl;
import net.datenwerke.rs.theme.client.icon.BaseIcon;
import net.datenwerke.security.client.usermanager.dto.OrganisationalUnitDto;

public class UserFolderObjectInfo extends GeneralObjectInfoImpl<OrganisationalUnitDto>{
   
   @Override
   public boolean consumes(Object object) {
      return object instanceof OrganisationalUnitDto;
   }

   @Override
   protected String doGetName(OrganisationalUnitDto object) {
      return object.getName();
   }

   @Override
   protected String doGetType(OrganisationalUnitDto object) {
      return null;
   }

   @Override
   protected ImageResource doGetIconSmall(OrganisationalUnitDto object) {
      return BaseIcon.FOLDER.toImageResource();
   }
}
