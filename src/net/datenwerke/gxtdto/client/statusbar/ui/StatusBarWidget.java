package net.datenwerke.gxtdto.client.statusbar.ui;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.safehtml.shared.UriUtils;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.InlineLabel;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;
import com.sencha.gxt.core.client.util.IconHelper;
import com.sencha.gxt.core.client.util.Padding;
import com.sencha.gxt.widget.core.client.container.HBoxLayoutContainer.HBoxLayoutAlign;
import com.sencha.gxt.widget.core.client.container.Viewport;
import com.sencha.gxt.widget.core.client.toolbar.FillToolItem;

import net.datenwerke.gxtdto.client.baseex.widget.DwContentPanel;
import net.datenwerke.gxtdto.client.baseex.widget.btn.DwTextButton;
import net.datenwerke.gxtdto.client.theme.CssClassConstant;
import net.datenwerke.gxtdto.client.utilityservices.toolbar.DwHookableToolbar;

@Singleton
public class StatusBarWidget extends DwContentPanel {

   @CssClassConstant
   public static final String CSS_NAME = "rs-status-bar";

   private final Provider<DwHookableToolbar> toolbarProvider;

   private List<Widget> leftGroup;

   private List<Widget> rightGroup;

   private DwHookableToolbar toolbar;

   private Viewport container;

   @Inject
   public StatusBarWidget(Provider<DwHookableToolbar> toolbarProvider) {
      this.toolbarProvider = toolbarProvider;

      initializeUI();
   }

   @Override
   public String getCssName() {
      return super.getCssName() + " " + CSS_NAME;
   }

   private void initializeUI() {
      setHeaderVisible(false);

      toolbar = toolbarProvider.get();
      toolbar.setShadow(false);
      toolbar.setPadding(new Padding(2));
      toolbar.setHBoxLayoutAlign(HBoxLayoutAlign.MIDDLE);

      /* base hookers */
      toolbar.addBaseHookersLeft();

      leftGroup = new ArrayList<Widget>();

      /* base hookers */
      toolbar.addBaseHookersRight();

      rightGroup = new ArrayList<Widget>();

      /* add panel to layout */
      add(toolbar);

      /* hide */
      possiblyHide();
   }

   public void addRight(String text, String icon) {
      addTextField(rightGroup, text, icon, 16, 16);
   }

   public void addRight(String text, String icon, int width, int height) {
      addTextField(rightGroup, text, icon, width, height);
   }

   public void addLeft(String text, String icon) {
      addTextField(leftGroup, text, icon, 16, 16);
   }

   public void addLeft(String text, String icon, int width, int height) {
      addTextField(leftGroup, text, icon, width, height);
   }

   public void addRight(Widget comp) {
      rightGroup.add(comp);

      redraw();
   }

   public void addLeft(Widget comp) {
      leftGroup.add(comp);

      redraw();
   }

   public void clear() {
      leftGroup.clear();
      rightGroup.clear();

      collapse();
      redraw();
   }

   private void addTextField(List<Widget> group, String text, String icon, int width, int height) {
      if (null == icon) {
         InlineLabel ill = new InlineLabel(text);
         ill.setWordWrap(false);
         group.add(ill);
      } else {
         group.add(new DwTextButton(text,
               IconHelper.getImageResource(UriUtils.fromString(GWT.getHostPageBaseURL() + icon), width, height)));
      }

      expand();
      setHeight(30);
      redraw();
   }

   public void removeRight(Widget comp) {
      rightGroup.remove(comp);
      redraw();
      possiblyHide();
   }

   public void removeLeft(Widget comp) {
      redraw();
      leftGroup.remove(comp);
      possiblyHide();
   }

   protected void redraw() {
      toolbar.clear();

      for (Widget l : leftGroup)
         toolbar.add(l);

      toolbar.add(new FillToolItem());

      for (Widget r : rightGroup)
         toolbar.add(r);

      Timer timer = new Timer() {
         @Override
         public void run() {
            forceLayout();
            if (null != container) {
               container.forceLayout();
            }
         }
      };
      timer.schedule(500);
   }

   private void possiblyHide() {
      if (rightGroup.size() == 0 && leftGroup.size() == 0) {
         collapse();
         if (null != container)
            container.forceLayout();
      }
   }

   public void setContainer(Viewport container) {
      this.container = container;
   }

   public void clearLeft() {
      leftGroup.clear();

      collapse();
      redraw();
   }

   public void clearRight() {
      rightGroup.clear();

      collapse();
      redraw();
   }

}
