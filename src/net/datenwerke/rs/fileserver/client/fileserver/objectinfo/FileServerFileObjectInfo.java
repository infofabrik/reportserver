package net.datenwerke.rs.fileserver.client.fileserver.objectinfo;

import com.google.gwt.resources.client.ImageResource;

import net.datenwerke.gxtdto.client.objectinformation.hooks.GeneralObjectInfoImpl;
import net.datenwerke.rs.fileserver.client.fileserver.dto.FileServerFileDto;
import net.datenwerke.rs.theme.client.icon.BaseIcon;


public class FileServerFileObjectInfo extends GeneralObjectInfoImpl<FileServerFileDto>{
   

   @Override
   public boolean consumes(Object object) {
      return object instanceof FileServerFileDto;
   }

   @Override
   protected String doGetName(FileServerFileDto object) {
      return object.getName();
   }

   @Override
   protected String doGetType(FileServerFileDto object) {
      return null;
   }


   @Override
   protected ImageResource doGetIconSmall(FileServerFileDto object) {
      return BaseIcon.FILE.toImageResource();
   }

   @Override
   protected String doGetKey(FileServerFileDto object) {
      return null;
   }
   
   @Override
   public boolean hasKeyAttribute() {
      return false;
   }

}
