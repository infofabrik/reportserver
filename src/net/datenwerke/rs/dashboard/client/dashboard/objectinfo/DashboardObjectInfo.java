package net.datenwerke.rs.dashboard.client.dashboard.objectinfo;

import com.google.gwt.resources.client.ImageResource;

import net.datenwerke.gxtdto.client.objectinformation.hooks.GeneralObjectInfoImpl;
import net.datenwerke.rs.dashboard.client.dashboard.dto.DashboardNodeDto;
import net.datenwerke.rs.theme.client.icon.BaseIcon;

public class DashboardObjectInfo extends GeneralObjectInfoImpl<DashboardNodeDto>{

   @Override
   public boolean consumes(Object object) {
      return object instanceof DashboardNodeDto;
   }

   @Override
   protected String doGetName(DashboardNodeDto object) {
      return object.getName();
   }

   @Override
   protected String doGetType(DashboardNodeDto object) {
      return null;
   }

   @Override
   protected ImageResource doGetIconSmall(DashboardNodeDto object) {
      return BaseIcon.DASHBOARD.toImageResource();
   }
}
