package net.datenwerke.gxtdto.client.objectinformation.hooks;

import java.util.Date;

import net.datenwerke.treedb.client.treedb.dto.decorator.AbstractNodeDtoDec;

public abstract class GeneralObjectInfoImpl <T extends AbstractNodeDtoDec> extends ObjectInfoKeyInfoProviderImpl<T>{


   @Override
   protected String doGetDescription(T object) {
      return object.getDescription();
   }

   @Override
   protected Date doGetLastUpdatedOn(T object) {
      return object.getLastUpdated();
   }

   @Override
   protected Date doGetCreatedOn(T object) {
       return object.getCreatedOn();
   }
}