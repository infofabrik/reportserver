package net.datenwerke.gxtdto.client.baseex.widget;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.widget.core.client.Component;
import com.sencha.gxt.widget.core.client.Dialog.PredefinedButton;
import com.sencha.gxt.widget.core.client.TabPanel;
import com.sencha.gxt.widget.core.client.box.ConfirmMessageBox;
import com.sencha.gxt.widget.core.client.event.DialogHideEvent;
import com.sencha.gxt.widget.core.client.event.DialogHideEvent.DialogHideHandler;
import com.sencha.gxt.widget.core.client.event.HideEvent;
import com.sencha.gxt.widget.core.client.event.HideEvent.HideHandler;
import com.sencha.gxt.widget.core.client.menu.MenuItem;
import com.sencha.gxt.widget.core.client.menu.SeparatorMenuItem;

import net.datenwerke.gxtdto.client.baseex.widget.mb.DwConfirmMessageBox;
import net.datenwerke.gxtdto.client.baseex.widget.menu.DwMenu;
import net.datenwerke.gxtdto.client.baseex.widget.menu.DwMenuItem;
import net.datenwerke.gxtdto.client.locale.BaseMessages;
import net.datenwerke.gxtdto.client.theme.CssClassConstant;

public class DwTabPanel extends TabPanel {

   @CssClassConstant
   public static final String CSS_NAME = "rs-tp";

   @CssClassConstant
   public static final String CSS_ACTIVE_NAME = "rs-tp-active";

   @CssClassConstant
   public static final String CSS_TAB_BAR_NAME = "rs-tp-bar";

   @CssClassConstant
   public static final String CSS_TAB_BODY_NAME = "rs-tp-body";

   @CssClassConstant
   public static final String CSS_TAB_STRIP_WRAP_NAME = "rs-tp-sw";

   private Widget currentTab;

   public DwTabPanel() {
      super();
      initCss();
   }

   public DwTabPanel(TabPanelAppearance appearance) {
      super(appearance);
      initCss();
   }

   private void initCss() {
      getElement().addClassName(getCssName());
      getAppearance().getBar(getElement()).addClassName(getCssTabbarName());
      getAppearance().getBody(getElement()).addClassName(getCssTabbodyName());
      getAppearance().getStripWrap(getElement()).addClassName(getCssTabStripWrapName());
   }

   public String getCssActiveName() {
      return CSS_ACTIVE_NAME;
   }

   private String getCssTabStripWrapName() {
      return CSS_TAB_STRIP_WRAP_NAME;
   }

   public String getCssName() {
      return CSS_NAME;
   }

   public String getCssTabbarName() {
      return CSS_TAB_BAR_NAME;
   }

   public String getCssTabbodyName() {
      return CSS_TAB_BODY_NAME;
   }

   @Override
   protected void onItemContextMenu(final Widget item, int x, int y) {
      if (!isCloseContextMenu())
         return;

      /* show close menu */
      if (closeContextMenu == null) {
         closeContextMenu = new DwMenu();
         closeContextMenu.addHideHandler(new HideHandler() {
            @Override
            public void onHide(HideEvent event) {
               currentTab = null;
            }
         });

         closeContextMenu.add(new DwMenuItem(BaseMessages.INSTANCE.closeTabText(), new SelectionHandler<MenuItem>() {

            @Override
            public void onSelection(SelectionEvent<MenuItem> event) {
               if (null != currentTab)
                  close(currentTab);
            }
         }));

         closeContextMenu
               .add(new DwMenuItem(BaseMessages.INSTANCE.closeOtherTabsText(), new SelectionHandler<MenuItem>() {
                  @Override
                  public void onSelection(SelectionEvent<MenuItem> event) {
                     final List<Widget> widgets = new ArrayList<Widget>();

                     for (Widget currentItem : DwTabPanel.this) {
                        if (currentItem != currentTab && getConfig(currentItem).isClosable()) {
                           widgets.add(currentItem);
                        }
                     }

                     askClose(widgets);
                  }
               }));

         closeContextMenu.add(new SeparatorMenuItem());
         closeContextMenu
               .add(new DwMenuItem(BaseMessages.INSTANCE.closeAllsTabText(), new SelectionHandler<MenuItem>() {
                  @Override
                  public void onSelection(SelectionEvent<MenuItem> event) {
                     final List<Widget> widgets = new ArrayList<Widget>();

                     for (Widget currentItem : DwTabPanel.this) {
                        if (getConfig(currentItem).isClosable()) {
                           widgets.add(currentItem);
                        }
                     }

                     askClose(widgets);
                  }
               }));
      }

      currentTab = item;

      if (closeContextMenu.getWidget(0) instanceof Component)
         ((Component) closeContextMenu.getWidget(0)).setEnabled(getConfig(item).isClosable());
      closeContextMenu.setData("tab", item);
      boolean hasClosable = false;
      for (Widget item2 : this) {
         if (getConfig(item2).isClosable() && item2 != item) {
            hasClosable = true;
            break;
         }
      }

      if (closeContextMenu.getWidget(1) instanceof Component)
         ((Component) closeContextMenu.getWidget(1)).setEnabled(hasClosable);

      closeContextMenu.showAt(x, y);
   }

   protected void askClose(final List<Widget> widgets) {
      boolean unsavedChanges = false;
      for (Widget currentItem : widgets) {
         if (hasUnchangedChanges(currentItem)) {
            unsavedChanges = true;
            break;
         }
      }

      if (!unsavedChanges)
         doClose(widgets);
      else {
         ConfirmMessageBox cmb = new DwConfirmMessageBox(BaseMessages.INSTANCE.warning(),
               BaseMessages.INSTANCE.unsavedChanges());
         cmb.addDialogHideHandler(new DialogHideHandler() {
            @Override
            public void onDialogHide(DialogHideEvent event) {
               if (event.getHideButton() == PredefinedButton.YES)
                  doClose(widgets);
            }
         });
         cmb.show();
      }
   }

   protected void doClose(List<Widget> widgets) {
      for (Widget currentItem : widgets)
         forceClose(currentItem);
   }

   protected void forceClose(Widget currentItem) {
      super.close(currentItem);
   }

   protected boolean hasUnchangedChanges(Widget currentItem) {
      return false;
   }

   @Override
   public void setActiveWidget(Widget item, boolean fireEvents) {
      if (getActiveWidget() != null)
         findItem(getWidgetIndex(getActiveWidget())).removeClassName(getCssActiveName());

      super.setActiveWidget(item, fireEvents);

      if (getActiveWidget() != null)
         findItem(getWidgetIndex(getActiveWidget())).addClassName(getCssActiveName());
   }

}
