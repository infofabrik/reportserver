package net.datenwerke.rs.remoteserver.client.remoteservermanager.objectinfo;

import com.google.gwt.resources.client.ImageResource;

import net.datenwerke.gxtdto.client.objectinformation.hooks.GeneralObjectInfoImpl;
import net.datenwerke.rs.remoteserver.client.remoteservermanager.dto.RemoteServerFolderDto;
import net.datenwerke.rs.theme.client.icon.BaseIcon;

public class RemoteServerFolderObjectInfo extends GeneralObjectInfoImpl<RemoteServerFolderDto>{

   @Override
   public boolean consumes(Object object) {
      return object instanceof RemoteServerFolderDto;
   }

   @Override
   protected String doGetName(RemoteServerFolderDto object) {
      return object.getName();
   }

   @Override
   protected String doGetType(RemoteServerFolderDto object) {
      return null;
   }

   @Override
   protected ImageResource doGetIconSmall(RemoteServerFolderDto object) {
      return BaseIcon.FOLDER.toImageResource();
   }

   @Override
   protected String doGetKey(RemoteServerFolderDto object) {
      return null;
   }
   
   @Override
   public boolean hasKeyAttribute() {
      return false;
   }

}
