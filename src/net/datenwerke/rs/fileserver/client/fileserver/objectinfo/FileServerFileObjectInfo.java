package net.datenwerke.rs.fileserver.client.fileserver.objectinfo;

import java.util.Date;

import com.google.gwt.resources.client.ImageResource;

import net.datenwerke.gxtdto.client.objectinformation.hooks.ObjectInfoKeyInfoProviderImpl;
import net.datenwerke.rs.fileserver.client.fileserver.dto.FileServerFileDto;
import net.datenwerke.rs.theme.client.icon.BaseIcon;


public class FileServerFileObjectInfo extends ObjectInfoKeyInfoProviderImpl<FileServerFileDto>{
   

   @Override
   public boolean consumes(Object object) {
      return object instanceof FileServerFileDto;
   }

   @Override
   protected String doGetName(FileServerFileDto object) {
      return object.getName();
   }

   @Override
   protected String doGetDescription(FileServerFileDto object) {
      return object.getDescription();
   }

   @Override
   protected String doGetType(FileServerFileDto object) {
      return null;
   }

   @Override
   protected Date doGetLastUpdatedOn(FileServerFileDto object) {
      return object.getLastUpdated();
   }

   @Override
   protected Date doGetCreatedOn(FileServerFileDto object) {
      return object.getCreatedOn();
   }

   @Override
   protected ImageResource doGetIconSmall(FileServerFileDto object) {
      return BaseIcon.FILE.toImageResource();
   }

}
