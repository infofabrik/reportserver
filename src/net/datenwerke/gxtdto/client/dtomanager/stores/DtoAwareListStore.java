package net.datenwerke.gxtdto.client.dtomanager.stores;

import com.google.inject.Inject;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.event.StoreAddEvent;
import com.sencha.gxt.data.shared.event.StoreClearEvent;
import com.sencha.gxt.data.shared.event.StoreDataChangeEvent;
import com.sencha.gxt.data.shared.event.StoreFilterEvent;
import com.sencha.gxt.data.shared.event.StoreHandlers;
import com.sencha.gxt.data.shared.event.StoreRecordChangeEvent;
import com.sencha.gxt.data.shared.event.StoreRemoveEvent;
import com.sencha.gxt.data.shared.event.StoreSortEvent;
import com.sencha.gxt.data.shared.event.StoreUpdateEvent;

import net.datenwerke.gxtdto.client.dtomanager.ClientDtoManagerService;
import net.datenwerke.gxtdto.client.dtomanager.Dto;
import net.datenwerke.gxtdto.client.dtomanager.events.BeforeDtoDetachedEvent;
import net.datenwerke.gxtdto.client.dtomanager.events.DtoChangedEvent;
import net.datenwerke.gxtdto.client.dtomanager.events.DtoListener;
import net.datenwerke.gxtdto.client.utils.modelkeyprovider.DtoTypeAwareIdProvider;
import net.datenwerke.gxtdto.client.utils.sort.AlphabeticStoreSortInfo;

public class DtoAwareListStore<X extends Dto> extends ListStore<X> {

   @Inject
   private static ClientDtoManagerService dtoManager;

   private DtoListener dtoChangeListener = new DtoListener() {

      @SuppressWarnings("unchecked")
      @Override
      public void dtoChanged(DtoChangedEvent e) {
         try {
            X dto = (X) e.getDto();
            if (null != findModel(dto))
               updateDto(dto);
         } catch (ClassCastException ex) {
         }
      }

      public void beforeDtoDetached(BeforeDtoDetachedEvent e) {
         try {
            X dto = (X) e.getDto();
            if (null != findModel(dto))
               removeDto(dto);
         } catch (ClassCastException ex) {
         }
      };

   };

   private StoreSortInfo<X> alphabeticSorter = new AlphabeticStoreSortInfo();

   public DtoAwareListStore() {
      this(new DtoTypeAwareIdProvider(), true);
   }

   public DtoAwareListStore(ModelKeyProvider<? super X> keyProvider, boolean changeAware) {
      super(keyProvider);

      addSortInfo(alphabeticSorter);

      if (changeAware)
         enableDtoAwareness();
   }

   protected void enableDtoAwareness() {
      addStoreHandlers(new StoreHandlers<X>() {
         @Override
         public void onAdd(StoreAddEvent<X> event) {
            updateListener();
         }

         @Override
         public void onRemove(StoreRemoveEvent<X> event) {
            updateListener();
         }

         @Override
         public void onFilter(StoreFilterEvent<X> event) {

         }

         @Override
         public void onClear(StoreClearEvent<X> event) {
            updateListener();
         }

         @Override
         public void onUpdate(StoreUpdateEvent<X> event) {
            updateListener();
         }

         @Override
         public void onDataChange(StoreDataChangeEvent<X> event) {
            updateListener();
         }

         @Override
         public void onRecordChange(StoreRecordChangeEvent<X> event) {
            updateListener();
         }

         @Override
         public void onSort(StoreSortEvent<X> event) {

         }

      });
   }

   protected void updateListener() {
      if (size() > 0)
         dtoManager.onDtoChange(dtoChangeListener);
      else
         dtoManager.removeDtoChangeListener(dtoChangeListener);
   }

   protected void updateDto(X dto) {
      update(dto);
   }

   protected void removeDto(X dto) {
      remove(dto);
   }

}
