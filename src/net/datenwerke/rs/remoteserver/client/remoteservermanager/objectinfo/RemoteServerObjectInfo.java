package net.datenwerke.rs.remoteserver.client.remoteservermanager.objectinfo;

import com.google.gwt.resources.client.ImageResource;

import net.datenwerke.gxtdto.client.objectinformation.hooks.GeneralObjectInfoImpl;
import net.datenwerke.rs.remoteserver.client.remoteservermanager.dto.RemoteServerDefinitionDto;

public class RemoteServerObjectInfo extends GeneralObjectInfoImpl<RemoteServerDefinitionDto>{
   
   @Override
   public boolean consumes(Object object) {
      return object instanceof RemoteServerDefinitionDto;
   }

   @Override
   protected String doGetName(RemoteServerDefinitionDto object) {
      return object.getName();
   }

   @Override
   protected String doGetType(RemoteServerDefinitionDto object) {
      return null;
   }

   @Override
   protected ImageResource doGetIconSmall(RemoteServerDefinitionDto object) {
      return object.toIcon().toImageResource();
   }

   @Override
   protected String doGetKey(RemoteServerDefinitionDto object) {
      return object.getKey();
   }
   
   @Override
   public boolean hasKeyAttribute() {
      return true;
   }
}
