package net.datenwerke.gxtdto.client.clipboard;

import java.util.IdentityHashMap;
import java.util.Map;

import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.sencha.gxt.core.client.GXT;
import com.sencha.gxt.widget.core.client.info.Info;

import net.datenwerke.gxtdto.client.clipboard.processor.ClipboardCopyProcessor;
import net.datenwerke.gxtdto.client.clipboard.processor.ClipboardPasteProcessor;
import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;

@Singleton
public class ClipboardUiServiceImpl implements ClipboardUiService {

   private ClipboardItem clipboardItem;

   private Map<Widget, ClipboardProviderHandler> providerMap = new IdentityHashMap<Widget, ClipboardProviderHandler>();
   private Map<Widget, ClipboardReceiverHandler> receiverMap = new IdentityHashMap<Widget, ClipboardReceiverHandler>();

   private final HookHandlerService hookHandler;

   @Inject
   public ClipboardUiServiceImpl(HookHandlerService hookHandler) {
      this.hookHandler = hookHandler;
   }

   @Override
   public void setClipboardItem(ClipboardItem clipboardItem) {
      this.clipboardItem = clipboardItem;
      if (clipboardItem instanceof ClipboardItemDescriber)
         Info.display("Clipboard", ((ClipboardItemDescriber) clipboardItem).describe());

      for (ClipboardListener listener : hookHandler.getHookers(ClipboardListener.class))
         listener.itemCopiedToClipboard(clipboardItem);
   }

   @Override
   public ClipboardItem getClipboardItem() {
      return clipboardItem;
   }

   @Override
   public void registerCopyHandler(Widget target, ClipboardCopyProcessor itemProvider) {
      if (providerMap.containsKey(target))
         detachCopyHandler(target);

      providerMap.put(target, new ClipboardProviderHandler(this, target, itemProvider));
   }

   @Override
   public void registerPasteHandler(Widget target, ClipboardPasteProcessor pasteProcessor) {
      if (receiverMap.containsKey(target))
         detachCopyHandler(target);

      receiverMap.put(target, new ClipboardReceiverHandler(this, target, pasteProcessor));
   }

   @Override
   public void detachCopyHandler(Widget target) {
      if (providerMap.containsKey(target))
         providerMap.remove(target).bind(null);
   }

   @Override
   public void detachPasteHandler(Widget target) {
      if (receiverMap.containsKey(target))
         receiverMap.remove(target).bind(null);
   }

   @Override
   public String readFromSystemClipboard() {
      try {
         if (GXT.isIE())
            return readFromSystemClipBoardIe();
         if (GXT.isGecko())
            return readFromSystemClipBoardFf();
      } catch (Exception e) {
         e.printStackTrace();
      }

      return null;
   }

   @Override
   public void copyToSystemClipboard(String txt) {
      try {
         if (GXT.isIE())
            copyToSystemClipBoardIe(txt);
         if (GXT.isGecko())
            copyToSystemClipBoardFf(txt);
      } catch (Exception e) {
         e.printStackTrace();
      }
   }

   public native void copyToSystemClipBoardFf(String text) /*-{
		netscape.security.PrivilegeManager.enablePrivilege('UniversalXPConnect');
		
		var str = Components.classes["@mozilla.org/supports-string;1"].  
		createInstance(Components.interfaces.nsISupportsString);  
		if (!str) return;  
		  
		str.data = text;  
		  
		var trans = Components.classes["@mozilla.org/widget/transferable;1"].  
		createInstance(Components.interfaces.nsITransferable);  
		if (!trans) return;  
		  
		trans.addDataFlavor("text/unicode");  
		trans.setTransferData("text/unicode", str, text.length * 2);  
		  
		var clipid = Components.interfaces.nsIClipboard;  
		var clip = Components.classes["@mozilla.org/widget/clipboard;1"].getService(clipid);  
		if (!clip) return;  
		  
		clip.setData(trans, null, clipid.kGlobalClipboard);  
	}-*/;

   public native void copyToSystemClipBoardIe(String text) /*-{
		window.clipboardData.setData("Text", text); 
	}-*/;

   public native String readFromSystemClipBoardFf() /*-{
		netscape.security.PrivilegeManager.enablePrivilege('UniversalXPConnect');
		
		var clip = Components.classes["@mozilla.org/widget/clipboard;1"].getService(Components.interfaces.nsIClipboard);  
	    if (!clip) return null;  
	      
	    var trans = Components.classes["@mozilla.org/widget/transferable;1"].createInstance(Components.interfaces.nsITransferable);  
	    if (!trans) return null;  
	    trans.addDataFlavor("text/unicode");  
	    
	    clip.getData(trans, clip.kGlobalClipboard);  
  
		var str       = new Object();  
		var strLength = new Object();  
		  
		trans.getTransferData("text/unicode", str, strLength);
		
		if (str) {  
	      str = str.value.QueryInterface(Components.interfaces.nsISupportsString);  
	      return str.data.substring(0, strLength.value / 2);  
	    }
	    
	    return null;  
	}-*/;

   public native String readFromSystemClipBoardIe() /*-{
		return window.clipboardData.getData("Text"); 
	}-*/;
}
