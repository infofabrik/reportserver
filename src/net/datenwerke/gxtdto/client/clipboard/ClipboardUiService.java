package net.datenwerke.gxtdto.client.clipboard;

import com.google.gwt.user.client.ui.Widget;
import com.google.inject.ImplementedBy;

import net.datenwerke.gxtdto.client.clipboard.processor.ClipboardCopyProcessor;
import net.datenwerke.gxtdto.client.clipboard.processor.ClipboardPasteProcessor;

@ImplementedBy(ClipboardUiServiceImpl.class)
public interface ClipboardUiService {

   public void setClipboardItem(ClipboardItem clipboardItem);

   public ClipboardItem getClipboardItem();

   public void registerCopyHandler(Widget component, ClipboardCopyProcessor copyProcessor);

   public void detachCopyHandler(Widget component);

   public void registerPasteHandler(Widget component, ClipboardPasteProcessor pasteProcessor);

   public void detachPasteHandler(Widget component);

   void copyToSystemClipboard(String txt);

   String readFromSystemClipboard();
}
