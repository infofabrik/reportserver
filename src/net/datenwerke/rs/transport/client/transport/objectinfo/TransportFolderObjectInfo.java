package net.datenwerke.rs.transport.client.transport.objectinfo;

import com.google.gwt.resources.client.ImageResource;

import net.datenwerke.gxtdto.client.objectinformation.hooks.GeneralObjectInfoImpl;
import net.datenwerke.rs.theme.client.icon.BaseIcon;
import net.datenwerke.rs.transport.client.transport.dto.TransportFolderDto;

public class TransportFolderObjectInfo extends GeneralObjectInfoImpl<TransportFolderDto>{

   @Override
   public boolean consumes(Object object) {
      return object instanceof TransportFolderDto;
   }

   @Override
   protected String doGetName(TransportFolderDto object) {
      return object.getName();
   }

   @Override
   protected String doGetType(TransportFolderDto object) {
      return null;
   }

   @Override
   protected ImageResource doGetIconSmall(TransportFolderDto object) {
      return BaseIcon.FOLDER.toImageResource();
   }

   @Override
   protected String doGetKey(TransportFolderDto object) {
      return null;
   }
   
   @Override
   public boolean hasKeyAttribute() {
      return false;
   }

}
