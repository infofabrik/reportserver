package net.datenwerke.rs.search.client.search.locale;

import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.Messages;

public interface SearchMessages extends Messages {

   public static SearchMessages INSTANCE = GWT.create(SearchMessages.class);

   String searchBtnLabel();

   String searchAreaModule();

   String entryColumnLabel();

   String noResultsFound();

   String previewLabel();

   String listLabel();

   String filterLabel();

   String emptySearchTabHeader();

   String noResultTitle();

   String noResultDesc();

}
