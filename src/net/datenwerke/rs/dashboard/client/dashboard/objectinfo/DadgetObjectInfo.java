package net.datenwerke.rs.dashboard.client.dashboard.objectinfo;

import com.google.gwt.resources.client.ImageResource;

import net.datenwerke.gxtdto.client.objectinformation.hooks.GeneralObjectInfoImpl;
import net.datenwerke.rs.dashboard.client.dashboard.dto.DadgetNodeDto;
import net.datenwerke.rs.theme.client.icon.BaseIcon;

public class DadgetObjectInfo extends GeneralObjectInfoImpl<DadgetNodeDto>{

   @Override
   public boolean consumes(Object object) {
      return object instanceof DadgetNodeDto;
   }

   @Override
   protected String doGetName(DadgetNodeDto object) {
      return object.getName();
   }

   @Override
   protected String doGetType(DadgetNodeDto object) {
      return null;
   }

   @Override
   protected ImageResource doGetIconSmall(DadgetNodeDto object) {
      return BaseIcon.DADGET.toImageResource();
   }

   @Override
   protected String doGetKey(DadgetNodeDto object) {
      return null;
   }

   @Override
   public boolean hasKeyAttribute() {
      return false;
   }
}
