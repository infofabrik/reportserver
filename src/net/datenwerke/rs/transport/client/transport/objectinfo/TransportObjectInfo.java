package net.datenwerke.rs.transport.client.transport.objectinfo;

import com.google.gwt.resources.client.ImageResource;

import net.datenwerke.gxtdto.client.objectinformation.hooks.GeneralObjectInfoImpl;
import net.datenwerke.rs.transport.client.transport.dto.TransportDto;

public class TransportObjectInfo extends GeneralObjectInfoImpl<TransportDto>{
   
   @Override
   public boolean consumes(Object object) {
      return object instanceof TransportDto;
   }

   @Override
   protected String doGetName(TransportDto object) {
      return object.getCreatedOnStr()+"-"+object.getShortKey();
   }

   @Override
   protected String doGetType(TransportDto object) {
      return null;
   }

   @Override
   protected ImageResource doGetIconSmall(TransportDto object) {
      return object.toIcon().toImageResource();
   }

   @Override
   protected String doGetKey(TransportDto object) {
      return object.getKey();
   }
   
   @Override
   public boolean hasKeyAttribute() {
      return true;
   }
}
