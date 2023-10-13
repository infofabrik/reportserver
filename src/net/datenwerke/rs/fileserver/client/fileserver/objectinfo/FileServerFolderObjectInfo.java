package net.datenwerke.rs.fileserver.client.fileserver.objectinfo;

import com.google.gwt.resources.client.ImageResource;

import net.datenwerke.gxtdto.client.objectinformation.hooks.GeneralObjectInfoImpl;
import net.datenwerke.rs.fileserver.client.fileserver.dto.FileServerFolderDto;
import net.datenwerke.rs.theme.client.icon.BaseIcon;

public class FileServerFolderObjectInfo extends GeneralObjectInfoImpl<FileServerFolderDto>{

   @Override
   public boolean consumes(Object object) {
      return object instanceof FileServerFolderDto;
   }

   @Override
   protected String doGetName(FileServerFolderDto object) {
      return object.getName();
   }

   @Override
   protected String doGetType(FileServerFolderDto object) {
      return null;
   }

   @Override
   protected ImageResource doGetIconSmall(FileServerFolderDto object) {
      return BaseIcon.FOLDER.toImageResource();
   }

   @Override
   protected String doGetKey(FileServerFolderDto object) {
      return null;
   }
   
   @Override
   public boolean hasKeyAttribute() {
      return false;
   }

}
