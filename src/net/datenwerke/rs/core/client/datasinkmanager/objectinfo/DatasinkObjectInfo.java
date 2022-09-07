package net.datenwerke.rs.core.client.datasinkmanager.objectinfo;

import com.google.gwt.resources.client.ImageResource;

import net.datenwerke.gxtdto.client.objectinformation.hooks.GeneralObjectInfoImpl;
import net.datenwerke.rs.core.client.datasinkmanager.dto.DatasinkDefinitionDto;

public class DatasinkObjectInfo extends GeneralObjectInfoImpl<DatasinkDefinitionDto>{
   
   @Override
   public boolean consumes(Object object) {
      return object instanceof DatasinkDefinitionDto;
   }

   @Override
   protected String doGetName(DatasinkDefinitionDto object) {
      return object.getName();
   }

   @Override
   protected String doGetType(DatasinkDefinitionDto object) {
      return null;
   }

   @Override
   protected ImageResource doGetIconSmall(DatasinkDefinitionDto object) {
      return object.toIcon().toImageResource();
   }
}
