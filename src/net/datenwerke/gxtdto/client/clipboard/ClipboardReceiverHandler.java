package net.datenwerke.gxtdto.client.clipboard;

import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.user.client.ui.Widget;

import net.datenwerke.gxtdto.client.clipboard.processor.ClipboardPasteProcessor;

public class ClipboardReceiverHandler extends ClipboardHandler {

   private final ClipboardUiService clipboardService;
   private final ClipboardPasteProcessor pasteProcessor;

   public ClipboardReceiverHandler(ClipboardUiService clipboardService, Widget target,
         ClipboardPasteProcessor pasteProcessor) {
      super(target);
      this.clipboardService = clipboardService;
      this.pasteProcessor = pasteProcessor;
   }

   @Override
   public void onKeyDown(KeyDownEvent event) {
      int code = event.getNativeKeyCode();

      if ((118 == code || 86 == code) && event.isControlKeyDown() && (event.isAltKeyDown() || event.isShiftKeyDown())
            && null != clipboardService.getClipboardItem())
         pasteProcessor.paste(clipboardService.getClipboardItem());
   }
}
