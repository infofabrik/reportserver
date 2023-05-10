package net.datenwerke.gxtdto.client.utilityservices;

import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.IFrameElement;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Frame;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.gf.client.dispatcher.DispatcherService;
import net.datenwerke.gxtdto.client.baseex.widget.btn.DwTextButton;
import net.datenwerke.gxtdto.client.baseex.widget.window.SimpleDialogWindow;
import net.datenwerke.gxtdto.client.locale.BaseMessages;

public class UtilsUIServiceImpl implements UtilsUIService {

   private static final String GWT_DOWNLOAD_FRAME = "__gwt_downloadFrame";
   private HandlerRegistration iframeLoadHandler;

   private final Provider<DispatcherService> dispatcherServiceProvider;

   @Inject
   public UtilsUIServiceImpl(Provider<DispatcherService> dispatcherService) {
      this.dispatcherServiceProvider = dispatcherService;
   }

   @Override
   public Widget asIframe(final String html) {
      final IFrameElement iframe = Document.get().createIFrameElement();
      iframe.setAttribute("width", "100%");
      iframe.setAttribute("height", "100%");
      iframe.setAttribute("frameborder", "0");
      FlowPanel innerBox = new FlowPanel() {
         @Override
         protected void onLoad() {
            super.onLoad();

            // Fill the IFrame with the content html
            fillIframe(iframe, html);
         }
      };
      innerBox.getElement().appendChild(iframe);

      return innerBox;
   }

   private final native void fillIframe(IFrameElement iframe, String content) /*-{
	  var doc = iframe.document;
	 
	  if(iframe.contentDocument)
	    doc = iframe.contentDocument; // For NS6
	  else if(iframe.contentWindow)
	    doc = iframe.contentWindow.document; // For IE5.5 and IE6
	 
	   // Put the content in the iframe
	  doc.open();
	  doc.writeln(content);
	  doc.close();
	}-*/;

   @Override
   public void redirectWithoutAsking(String url) {
      dispatcherServiceProvider.get().setWarnOnExit(false);
      redirect(url);
   }

   public native void redirect(String url) /*-{
    	$wnd.location = url;
	}-*/;

   public native void redirectInPopup(String url) /*-{
	       $wnd.open(url, "_blank", "");
	}-*/;

   public void triggerDownload(String url) {
      if (url == null)
         throw new IllegalArgumentException("download url was null");

      Frame downloadFrame = Frame.wrap(Document.get().getElementById(GWT_DOWNLOAD_FRAME));
      iframeLoadHandler = downloadFrame.addLoadHandler(event -> {
         iframeLoadHandler.removeHandler();
         String content = getIFrameContent(GWT_DOWNLOAD_FRAME);
         String title = getIFrameTitle(GWT_DOWNLOAD_FRAME);
         showHtmlPopupWindows(title, content, false);
      });

      downloadFrame.setUrl(url);
   }

   @Override
   public void showHtmlPopupWindows(final String title, final String html, final boolean maximize) {
      SimpleDialogWindow popup = new SimpleDialogWindow() {

         @Override
         protected void initializeUi() {

            /* create window */
            setWidth(700);
            setHeading(title);
            setHeight(550);

            setWidget(asIframe(html));

            DwTextButton okButton = new DwTextButton(BaseMessages.INSTANCE.ok());
            okButton.addSelectHandler(event -> hide());
            addButton(okButton);
         }
      };
      popup.show();
      if (maximize)
         popup.maximize();
   }

   private native String getIFrameContent(String name) /*-{
		var iframe = $doc.getElementById(name);
		var doc = iframe.document;
	
		if(iframe.contentDocument)
			doc = iframe.contentDocument;
		else if(iframe.contentWindow)
			doc = iframe.contentWindow.document;
	
	
		var markup = doc.documentElement.innerHTML;
		
		return markup;
	}-*/;

   private native String getIFrameTitle(String name) /*-{
		var iframe = $doc.getElementById(name);
		var doc = iframe.document;
	
		if(iframe.contentDocument)
			doc = iframe.contentDocument;
		else if(iframe.contentWindow)
			doc = iframe.contentWindow.document;
	
	
		var title = doc.documentElement.title;
		
		return title;
	}-*/;

   @Override
   public void reloadPageWithoutAsking() {
      dispatcherServiceProvider.get().setWarnOnExit(false);
      reloadPage();
   }

   public native void reloadPage() /*-{
	    $wnd.location.reload();
	}-*/;

   public native void reloadPage(String locale) /*-{
	    var currLocation = $wnd.location.toString();
	    $wnd.location.href = currLocation + "?locale=" + locale;
	}-*/;

   @Override
   public native String guessUserTimezone() /*-{
		var timezone = Intl.DateTimeFormat().resolvedOptions().timeZone;
		//only happens in IE
		if (timezone === undefined) timezone = 'Europe/Berlin'; 
		return timezone;
	}-*/;
}
