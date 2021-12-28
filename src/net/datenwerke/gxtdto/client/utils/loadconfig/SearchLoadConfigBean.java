package net.datenwerke.gxtdto.client.utils.loadconfig;

import com.sencha.gxt.data.shared.loader.PagingLoadConfigBean;

public class SearchLoadConfigBean extends PagingLoadConfigBean implements SearchLoadConfig {

   private static final long serialVersionUID = 1349995679344003596L;
   private String query;

   @Override
   public String getQuery() {
      return this.query;
   }

   @Override
   public void setQuery(String query) {
      this.query = query;
   }
}