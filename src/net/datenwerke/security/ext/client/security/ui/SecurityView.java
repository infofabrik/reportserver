package net.datenwerke.security.ext.client.security.ui;

import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.sencha.gxt.core.client.dom.ScrollSupport.ScrollMode;
import com.sencha.gxt.core.client.util.Margins;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer.VerticalLayoutData;

import net.datenwerke.gf.client.managerhelper.mainpanel.MainPanelView;
import net.datenwerke.rs.theme.client.icon.BaseIcon;
import net.datenwerke.security.client.security.dto.GrantAccessDto;
import net.datenwerke.security.client.treedb.dto.SecuredAbstractNodeDto;
import net.datenwerke.security.client.treedb.dto.decorator.SecuredAbstractNodeDtoDec;
import net.datenwerke.security.ext.client.security.locale.SecurityMessages;
import net.datenwerke.security.ext.client.security.ui.aclview.NodeACLView;
import net.datenwerke.treedb.client.treedb.dto.AbstractNodeDto;

public class SecurityView extends MainPanelView {

   public static final String VIEW_ID = "SecurityView";

   private final Provider<NodeACLView> aclViewProvider;

   @Inject
   public SecurityView(Provider<NodeACLView> aclViewProvider) {
      super();

      /* store objects */
      this.aclViewProvider = aclViewProvider;
   }

   @Override
   public String getViewId() {
      return VIEW_ID;
   }

   @Override
   public boolean isSticky() {
      return true;
   }

   @Override
   public boolean appliesTo(AbstractNodeDto item) {
      if (!(item instanceof SecuredAbstractNodeDto))
         return false;

      return ((SecuredAbstractNodeDtoDec) item).hasAccessRight(GrantAccessDto.class);
   }

   @Override
   public String getComponentHeader() {
      return SecurityMessages.INSTANCE.permissionManagement();
   }

   @Override
   public ImageResource getIcon() {
      return BaseIcon.LOCK.toImageResource();
   }

   @Override
   public Widget getViewComponent(AbstractNodeDto selectedNode) {
      NodeACLView aclView = aclViewProvider.get();
      aclView.initialize((SecuredAbstractNodeDto) getSelectedNode());

      VerticalLayoutContainer wrapper = new VerticalLayoutContainer();
      wrapper.setScrollMode(ScrollMode.AUTOY);
      wrapper.add(aclView, new VerticalLayoutData(1, -1, new Margins(10)));

      return wrapper;
   }

}
