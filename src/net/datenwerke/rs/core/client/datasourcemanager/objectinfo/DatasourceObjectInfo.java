package net.datenwerke.rs.core.client.datasourcemanager.objectinfo;

import com.google.gwt.resources.client.ImageResource;

import net.datenwerke.gxtdto.client.objectinformation.hooks.GeneralObjectInfoImpl;
import net.datenwerke.rs.core.client.datasourcemanager.dto.DatasourceDefinitionDto;
import net.datenwerke.rs.theme.client.icon.BaseIcon;

public class DatasourceObjectInfo extends GeneralObjectInfoImpl<DatasourceDefinitionDto>{

   @Override
   public boolean consumes(Object object) {
      return object instanceof DatasourceDefinitionDto;
   }

   @Override
   protected String doGetName(DatasourceDefinitionDto object) {
      return object.getName();
   }

   @Override
   protected String doGetType(DatasourceDefinitionDto object) {
      return null;
   }

   @Override
   protected ImageResource doGetIconSmall(DatasourceDefinitionDto object) {
      // TODO Auto-generated method stub
      return BaseIcon.DATABASE.toImageResource();
   }

}
