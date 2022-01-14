package net.datenwerke.gxtdto.client.clipboard.locale;

import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.Messages;

public interface ClipboardMessages extends Messages {

   public static final ClipboardMessages INSTANCE = GWT.create(ClipboardMessages.class);

   String dtoCopiedToClipboard(String dtoDesc);

   String dtoListCopiedToClipboard(int size);

}
