package net.datenwerke.rs.fileserver.client.fileserver.ui.panels;

import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.IFrameElement;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.sencha.gxt.core.client.dom.ScrollSupport.ScrollMode;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;

import net.datenwerke.gf.client.managerhelper.mainpanel.MainPanelView;
import net.datenwerke.gxtdto.client.dtomanager.callback.RsAsyncCallback;
import net.datenwerke.gxtdto.client.locale.BaseMessages;
import net.datenwerke.gxtdto.client.utilityservices.UtilsUIService;
import net.datenwerke.rs.fileserver.client.fileserver.FileServerDao;
import net.datenwerke.rs.fileserver.client.fileserver.dto.FileServerFileDto;
import net.datenwerke.rs.fileserver.client.fileserver.locale.FileServerMessages;
import net.datenwerke.rs.theme.client.icon.BaseIcon;
import net.datenwerke.treedb.client.treedb.dto.AbstractNodeDto;

public class MarkdownFilePreviewView extends MainPanelView {

   private final Provider<FileServerDao> fileServerDao;
   private final Provider<UtilsUIService> utilsUIService;
   

   @Inject
   public MarkdownFilePreviewView(Provider<FileServerDao> fileServerDao, Provider<UtilsUIService> utilsUIService) {
      this.fileServerDao = fileServerDao;
      this.utilsUIService = utilsUIService;
   }

   @Override
   public String getComponentHeader() {
      return FileServerMessages.INSTANCE.previewLabel() + " Markdown";
   }
   
   @Override
   public String getViewId() {
      return "_file_markdown_preview";
   }

   @Override
   public Widget getViewComponent(AbstractNodeDto selectedNode) {
      
      VerticalLayoutContainer wrapper = new VerticalLayoutContainer();
      wrapper.setScrollMode(ScrollMode.NONE);
      final IFrameElement iframe = Document.get().createIFrameElement();
      iframe.setAttribute("width", "100%");
      iframe.setAttribute("height", "100%");
      iframe.setAttribute("frameborder", "0");      
      wrapper.getElement().appendChild(iframe);

     
      mask(BaseMessages.INSTANCE.loadingMsg());
      fileServerDao.get().loadMarkdownAsHtml((FileServerFileDto) getSelectedNode(), new RsAsyncCallback<String>() {
         @Override
         public void onSuccess(String result) {
            unmask();
            fillIframe(iframe, result);
         }

         @Override
         public void onFailure(Throwable caught) {
            super.onFailure(caught);
            unmask();
         }
      });
      return wrapper;
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
   public ImageResource getIcon() {
      return BaseIcon.SITEMAP.toImageResource();
   }
   
}