package net.datenwerke.rs.dashboard.client.dashboard.objectinfo;

import com.google.gwt.resources.client.ImageResource;

import net.datenwerke.gxtdto.client.objectinformation.hooks.GeneralObjectInfoImpl;
import net.datenwerke.rs.dashboard.client.dashboard.dto.DashboardFolderDto;
import net.datenwerke.rs.theme.client.icon.BaseIcon;

public class DashboardFolderObjectInfo extends GeneralObjectInfoImpl<DashboardFolderDto>{
   
   @Override
   public boolean consumes(Object object) {
      return object instanceof DashboardFolderDto;
   }

   @Override
   protected String doGetName(DashboardFolderDto object) {
      return object.getName();
   }

   @Override
   protected String doGetType(DashboardFolderDto object) {
      return null;
   }

   @Override
   protected ImageResource doGetIconSmall(DashboardFolderDto object) {
      return BaseIcon.FOLDER.toImageResource();
   }
}
