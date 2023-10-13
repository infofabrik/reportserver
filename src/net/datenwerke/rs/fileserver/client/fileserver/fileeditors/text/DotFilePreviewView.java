package net.datenwerke.rs.fileserver.client.fileserver.fileeditors.text;

import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;

import net.datenwerke.gf.client.managerhelper.mainpanel.MainPanelView;
import net.datenwerke.gxtdto.client.dtomanager.callback.RsAsyncCallback;
import net.datenwerke.gxtdto.client.locale.BaseMessages;
import net.datenwerke.rs.fileserver.client.fileserver.FileServerDao;
import net.datenwerke.rs.fileserver.client.fileserver.dto.FileServerFileDto;
import net.datenwerke.rs.fileserver.client.fileserver.locale.FileServerMessages;
import net.datenwerke.rs.theme.client.icon.BaseIcon;
import net.datenwerke.treedb.client.treedb.dto.AbstractNodeDto;

public class DotFilePreviewView extends MainPanelView {
   @Inject
   private FileServerDao fileServerDao;

   @Override
   public String getComponentHeader() {
      return FileServerMessages.INSTANCE.previewLabel() + " SVG";
   }
   
   @Override
   public String getViewId() {
      return "_file_svg_preview";
   }

   @Override
   public Widget getViewComponent(AbstractNodeDto selectedNode) {
      ScrollPanel view = new ScrollPanel();
      view.setHeight("100%");
      view.setWidth("100%");
      mask(BaseMessages.INSTANCE.loadingMsg());
      fileServerDao.loadDotAsSVG((FileServerFileDto) getSelectedNode(), new RsAsyncCallback<String>() {
         @Override
         public void onSuccess(String result) {
            unmask();
            HTML hWidget = new HTML();
            hWidget.setHTML(result);
            view.add(hWidget);
         }

         @Override
         public void onFailure(Throwable caught) {
            super.onFailure(caught);
            unmask();
         }
      });
      return view;
   }
   
   @Override
   public ImageResource getIcon() {
      return BaseIcon.SITEMAP.toImageResource();
   }
   
}