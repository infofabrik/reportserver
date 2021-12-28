package net.datenwerke.gxtdto.client.utils.handlers;

import com.sencha.gxt.data.shared.event.StoreAddEvent;
import com.sencha.gxt.data.shared.event.StoreClearEvent;
import com.sencha.gxt.data.shared.event.StoreDataChangeEvent;
import com.sencha.gxt.data.shared.event.StoreFilterEvent;
import com.sencha.gxt.data.shared.event.StoreHandlers;
import com.sencha.gxt.data.shared.event.StoreRecordChangeEvent;
import com.sencha.gxt.data.shared.event.StoreRemoveEvent;
import com.sencha.gxt.data.shared.event.StoreSortEvent;
import com.sencha.gxt.data.shared.event.StoreUpdateEvent;

public class GenericStoreHandler<M> implements StoreHandlers<M> {

   protected void handleEvent() {

   }

   protected void handleDataChangeEvent() {

   }

   @Override
   public void onAdd(StoreAddEvent<M> event) {
      handleEvent();
      handleDataChangeEvent();
   }

   @Override
   public void onRemove(StoreRemoveEvent<M> event) {
      handleEvent();
      handleDataChangeEvent();
   }

   @Override
   public void onFilter(StoreFilterEvent<M> event) {
      handleEvent();
   }

   @Override
   public void onClear(StoreClearEvent<M> event) {
      handleEvent();
      handleDataChangeEvent();
   }

   @Override
   public void onUpdate(StoreUpdateEvent<M> event) {
      handleEvent();
      handleDataChangeEvent();
   }

   @Override
   public void onDataChange(StoreDataChangeEvent<M> event) {
      handleEvent();
      handleDataChangeEvent();
   }

   @Override
   public void onRecordChange(StoreRecordChangeEvent<M> event) {
      handleEvent();
      handleDataChangeEvent();
   }

   @Override
   public void onSort(StoreSortEvent<M> event) {
      handleEvent();
   }

}
