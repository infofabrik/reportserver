package net.datenwerke.gxtdto.client.utils.loadconfig;

import com.sencha.gxt.data.shared.loader.PagingLoadConfig;

public interface SearchLoadConfig extends PagingLoadConfig {
   String getQuery();

   void setQuery(String query);
}