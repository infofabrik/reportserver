package net.datenwerke.gxtdto.client.clipboard;

import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.user.client.ui.Widget;

import net.datenwerke.gxtdto.client.clipboard.processor.ClipboardCopyProcessor;

public class ClipboardProviderHandler extends ClipboardHandler {

   private final ClipboardUiService clipboardService;
   private final ClipboardCopyProcessor itemProvider;

   public ClipboardProviderHandler(ClipboardUiService clipboardService, Widget target,
         ClipboardCopyProcessor itemProvider) {
      super(target);
      this.clipboardService = clipboardService;
      this.itemProvider = itemProvider;
   }

   @Override
   public void onKeyDown(KeyDownEvent event) {
      int code = event.getNativeKeyCode();

      if ((67 == code || 99 == code) && event.isControlKeyDown() && (event.isAltKeyDown() || event.isShiftKeyDown()))
         clipboardService.setClipboardItem(itemProvider.getItem());
   }
}
