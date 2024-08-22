package net.datenwerke.rs.core.client.datasourcemanager.objectinfo;

import com.google.gwt.resources.client.ImageResource;

import net.datenwerke.gxtdto.client.objectinformation.hooks.GeneralObjectInfoImpl;
import net.datenwerke.rs.core.client.datasourcemanager.dto.DatasourceFolderDto;
import net.datenwerke.rs.theme.client.icon.BaseIcon;

public class DatasourceFolderObjectInfo extends GeneralObjectInfoImpl<DatasourceFolderDto>{

   @Override
   public boolean consumes(Object object) {
      return object instanceof DatasourceFolderDto;
   }

   @Override
   protected String doGetName(DatasourceFolderDto object) {
      return object.getName();
   }

   @Override
   protected String doGetType(DatasourceFolderDto object) {
      return null;
   }

   @Override
   protected ImageResource doGetIconSmall(DatasourceFolderDto object) {
      return BaseIcon.FOLDER.toImageResource();
   }

   @Override
   protected String doGetKey(DatasourceFolderDto object) {
      return null;
   }
   
   @Override
   public boolean hasKeyAttribute() {
      return false;
   }

}
