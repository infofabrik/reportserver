package net.datenwerke.rs.fileserver.client.fileserver.ui.panels;

import com.google.gwt.dom.client.IFrameElement;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.sencha.gxt.core.client.dom.ScrollSupport.ScrollMode;
import com.sencha.gxt.core.client.util.Margins;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer.VerticalLayoutData;

import net.datenwerke.gf.client.managerhelper.mainpanel.MainPanelView;
import net.datenwerke.gxtdto.client.baseex.widget.DwContentPanel;
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

      DwContentPanel wrapper = new DwContentPanel();
      wrapper.setLightHeader();
      wrapper.setHeading(FileServerMessages.INSTANCE.previewLabel() + " (" + selectedNode.getId() + ")");
      wrapper.setScrollMode(ScrollMode.NONE);

      final IFrameElement iframe = utilsUIService.get().createIFrame();
      VerticalLayoutContainer innerContainer = new VerticalLayoutContainer();
      innerContainer.getElement().appendChild(iframe);
      wrapper.add(innerContainer, new VerticalLayoutData(1, 1, new Margins(10)));

      mask(BaseMessages.INSTANCE.loadingMsg());
      fileServerDao.get().loadMarkdownAsHtml((FileServerFileDto) getSelectedNode(), new RsAsyncCallback<String>() {
         @Override
         public void onSuccess(String result) {
            utilsUIService.get().setIFrameContent(iframe, result);
            unmask();
         }

         @Override
         public void onFailure(Throwable caught) {
            super.onFailure(caught);
            unmask();
         }
      });
      VerticalLayoutContainer outer = new VerticalLayoutContainer();
      outer.setScrollMode(ScrollMode.AUTOY);
      outer.add(wrapper, new VerticalLayoutData(1, 1, new Margins(10)));
      return outer;
   }
   
   @Override
   public ImageResource getIcon() {
      return BaseIcon.SITEMAP.toImageResource();
   }
   
}