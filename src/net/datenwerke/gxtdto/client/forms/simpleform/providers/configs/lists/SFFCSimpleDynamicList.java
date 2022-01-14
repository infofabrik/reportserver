package net.datenwerke.gxtdto.client.forms.simpleform.providers.configs.lists;

import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ListStore;

public interface SFFCSimpleDynamicList<M> extends SFFCSimpleList {

   public ListStore<M> getStore();

   public ValueProvider<?, String> getStoreKeyForDisplay();
}
