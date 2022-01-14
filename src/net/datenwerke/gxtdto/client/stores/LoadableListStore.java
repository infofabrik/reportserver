package net.datenwerke.gxtdto.client.stores;

import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.loader.ListLoadResult;
import com.sencha.gxt.data.shared.loader.LoadResultListStoreBinding;
import com.sencha.gxt.data.shared.loader.Loader;

public class LoadableListStore<C, M, D extends ListLoadResult<M>> extends ListStore<M> implements HasLoader<C, D> {

   private Loader<C, D> loader;

   public LoadableListStore(ModelKeyProvider<? super M> keyProvider, Loader<C, D> loader) {
      super(keyProvider);
      this.loader = loader;

      loader.addLoadHandler(new LoadResultListStoreBinding<C, M, D>(this));
   }

   @Override
   public Loader<C, D> getLoader() {
      return loader;
   }

}
