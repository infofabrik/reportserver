package net.datenwerke.rs.core.client.datasinkmanager.objectinfo;

import com.google.gwt.resources.client.ImageResource;

import net.datenwerke.gxtdto.client.objectinformation.hooks.GeneralObjectInfoImpl;
import net.datenwerke.rs.core.client.datasinkmanager.dto.DatasinkFolderDto;
import net.datenwerke.rs.theme.client.icon.BaseIcon;

public class DatasinkFolderObjectInfo extends GeneralObjectInfoImpl<DatasinkFolderDto>{

   @Override
   public boolean consumes(Object object) {
      return object instanceof DatasinkFolderDto;
   }

   @Override
   protected String doGetName(DatasinkFolderDto object) {
      return object.getName();
   }

   @Override
   protected String doGetType(DatasinkFolderDto object) {
      return null;
   }

   @Override
   protected ImageResource doGetIconSmall(DatasinkFolderDto object) {
      return BaseIcon.FOLDER.toImageResource();
   }

   @Override
   protected String doGetKey(DatasinkFolderDto object) {
      return null;
   }
   
   @Override
   public boolean hasKeyAttribute() {
      return false;
   }

}
