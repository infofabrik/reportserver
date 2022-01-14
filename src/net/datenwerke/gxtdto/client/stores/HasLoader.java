package net.datenwerke.gxtdto.client.stores;

import com.sencha.gxt.data.shared.loader.Loader;

public interface HasLoader<C, M> {

   public Loader<C, M> getLoader();
}
