package net.datenwerke.rs.core.client.datasourcemanager.objectinfo;

import java.util.Date;

import com.google.gwt.resources.client.ImageResource;

import net.datenwerke.gxtdto.client.objectinformation.hooks.ObjectInfoKeyInfoProviderImpl;
import net.datenwerke.rs.core.client.datasourcemanager.dto.DatasourceDefinitionDto;
import net.datenwerke.rs.theme.client.icon.BaseIcon;

public class DatasourceObjectInfo extends ObjectInfoKeyInfoProviderImpl<DatasourceDefinitionDto>{

   @Override
   public boolean consumes(Object object) {
      return object instanceof DatasourceDefinitionDto;
   }

   @Override
   protected String doGetName(DatasourceDefinitionDto object) {
      return object.getName();
   }

   @Override
   protected String doGetDescription(DatasourceDefinitionDto object) {
      return object.getDescription();
   }

   @Override
   protected String doGetType(DatasourceDefinitionDto object) {
      return null;
   }

   @Override
   protected Date doGetLastUpdatedOn(DatasourceDefinitionDto object) {
      return object.getLastUpdated();
   }

   @Override
   protected Date doGetCreatedOn(DatasourceDefinitionDto object) {
      return object.getCreatedOn();
   }

   @Override
   protected ImageResource doGetIconSmall(DatasourceDefinitionDto object) {
      // TODO Auto-generated method stub
      return BaseIcon.DATABASE.toImageResource();
   }

}
