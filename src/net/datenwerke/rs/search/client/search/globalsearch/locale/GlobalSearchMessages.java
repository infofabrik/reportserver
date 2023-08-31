package net.datenwerke.rs.search.client.search.globalsearch.locale;

import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.Messages;

public interface GlobalSearchMessages extends Messages {

   public final static GlobalSearchMessages INSTANCE = GWT.create(GlobalSearchMessages.class);

   String emptyText();

   String noResultTitle();

   String noResultDesc();

}
