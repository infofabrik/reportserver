package net.datenwerke.gxtdto.client.baseex.widget;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.LoadHandler;
import com.google.gwt.user.client.ui.Frame;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.core.client.dom.ScrollSupport.ScrollMode;
import com.sencha.gxt.widget.core.client.ContentPanel;
import com.sencha.gxt.widget.core.client.button.ToolButton;
import com.sencha.gxt.widget.core.client.container.AccordionLayoutContainer.AccordionLayoutAppearance;
import com.sencha.gxt.widget.core.client.container.MarginData;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer.VerticalLayoutData;

import net.datenwerke.gxtdto.client.theme.CssClassConstant;
import net.datenwerke.rs.theme.client.icon.BaseIcon;

public class DwContentPanel extends ContentPanel {

   @CssClassConstant
   public static final String CSS_NAME = "rs-cp";

   @CssClassConstant
   public static final String CSS_HEADER_NAME = "rs-cp-header";

   @CssClassConstant
   public static final String CSS_LIGHT_DARK = "rs-cp-ld";

   @CssClassConstant
   public static final String CSS_LIGHT_HEADER = "rs-cp-lh";

   @CssClassConstant
   public static final String CSS_BODY_NAME = "rs-cp-body";

   @CssClassConstant
   public static final String CSS_CONTENT_NAME = "rs-cp-content";

   private boolean scrollContainer;

   public DwContentPanel() {
      this((ContentPanelAppearance) GWT.create(ContentPanelAppearance.class));
   }

   public DwContentPanel(ContentPanelAppearance appearance) {
      super(appearance);

      getElement().addClassName(getCssName());
      getAppearance().getHeaderElem(getElement()).addClassName(getCssHeaderName());
      getAppearance().getBodyWrap(getElement()).addClassName(getCssBodyName());
      getAppearance().getContentElem(getElement()).addClassName(getCssContentName());
   }

   public static DwContentPanel newAccordeonInstance() {
      return new DwContentPanel(GWT.<AccordionLayoutAppearance>create(AccordionLayoutAppearance.class));
   }

   public void setHeaderIcon(BaseIcon icon) {
      getHeader().setIcon(icon.toImageResource());
   }

   public String getCssName() {
      return CSS_NAME;
   }

   public String getCssHeaderName() {
      return CSS_HEADER_NAME;
   }

   public String getCssBodyName() {
      return CSS_BODY_NAME;
   }

   public String getCssContentName() {
      return CSS_CONTENT_NAME;
   }

   public void setLightHeader() {
      getAppearance().getHeaderElem(getElement()).addClassName(CSS_LIGHT_HEADER);
   }

   public void setLightDarkStyle() {
      addClassName(CSS_LIGHT_DARK);
   }

   public void addClassName(String cls) {
      getElement().addClassName(cls);
   }

   public static DwContentPanel newHeadlessInstance() {
      DwContentPanel panel = new DwContentPanel();
      panel.setHeaderVisible(false);

      return panel;
   }

   public static DwContentPanel newHeadlessInstance(Widget widget) {
      DwContentPanel panel = newHeadlessInstance();
      panel.setWidget(widget);
      return panel;
   }

   public static DwContentPanel newHeadlessInstance(Widget widget, MarginData data) {
      DwContentPanel panel = newHeadlessInstance();
      panel.add(widget, data);
      return panel;
   }

   public static DwContentPanel newInlineInstance() {
      DwContentPanel panel = new DwContentPanel();

      panel.setHeaderVisible(false);
      panel.setBodyBorder(false);
      panel.setBorders(false);

      return panel;
   }

   public static DwContentPanel newInlineInstance(Widget widget) {
      DwContentPanel panel = newInlineInstance();
      panel.setWidget(widget);
      return panel;
   }

   public static DwContentPanel newInlineInstance(Widget widget, MarginData data) {
      DwContentPanel panel = newInlineInstance();
      panel.add(widget, data);
      return panel;
   }

   public static DwContentPanel newLightHeaderPanel(String header, Widget widget) {
      DwContentPanel panel = new DwContentPanel();
      panel.setLightHeader();
      panel.setHeading(header);
      panel.setWidget(widget);
      return panel;
   }

   public void enableScrollContainer() {
      if (scrollContainer)
         return;

      VerticalLayoutContainer scrollContainer = new VerticalLayoutContainer();
      scrollContainer.setScrollMode(ScrollMode.AUTOY);

      Widget w = getWidget();
      if (null != w) {
         remove(w);
         scrollContainer.add(w);
      }

      setWidget(scrollContainer);

      this.scrollContainer = true;
   }

   public void setScrollMode(ScrollMode scrollMode) {
      if (!scrollContainer)
         enableScrollContainer();
      ((VerticalLayoutContainer) getWidget()).setScrollMode(scrollMode);
   }

   @Override
   public void setWidget(Widget w) {
      if (!scrollContainer)
         super.setWidget(w);
      else {
         ((VerticalLayoutContainer) getWidget()).clear();
         ((VerticalLayoutContainer) getWidget()).add(w, new VerticalLayoutData(1, -1));
      }

   }

   @Override
   public void add(Widget child) {
      if (!scrollContainer)
         super.add(child);
      else
         ((VerticalLayoutContainer) getWidget()).add(child, new VerticalLayoutData(1, -1));
   }

   @Override
   public void add(Widget child, MarginData layoutData) {
      if (!scrollContainer)
         super.add(child, layoutData);
      else
         ((VerticalLayoutContainer) getWidget()).add(child, new VerticalLayoutData(1, -1, layoutData.getMargins()));
   }

   public void add(Widget child, VerticalLayoutData data) {
      if (!scrollContainer)
         throw new IllegalStateException("Shoudl be scroll cintainer");

      ((VerticalLayoutContainer) getWidget()).add(child, data);
   }

   public Frame setUrl(String url) {
      Frame f = new Frame(url);
      f.getElement().setPropertyInt("frameBorder", 0);
      f.setSize("100%", "100%");
      add(f);

      return f;
   }

   public Frame setUrl(String url, LoadHandler handler) {
      Frame f = new Frame(url);
      f.getElement().setPropertyInt("frameBorder", 0);
      f.setSize("100%", "100%");
      f.addLoadHandler(handler);
      add(f);

      return f;
   }

   public void setInfoText(String text) {
      ToolButton toolButton = new ToolButton(ToolButton.QUESTION);
      toolButton.setToolTip(text);
      addTool(toolButton);
   }

   @Override
   protected void assertPreRender() {
      // no GXT assertion because of AccordionLayout Bug
   }

}
