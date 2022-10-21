package net.datenwerke.treedb.ext.client.locale;

import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.Messages;

public interface TreeDbExtMessages extends Messages {

   public final static TreeDbExtMessages INSTANCE = GWT.create(TreeDbExtMessages.class);

   String logTypeCreate();

   String logTypeMerge();

   String logTypeRemove();
}
