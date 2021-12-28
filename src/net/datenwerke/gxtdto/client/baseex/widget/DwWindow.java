package net.datenwerke.gxtdto.client.baseex.widget;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.widget.core.client.Window;
import com.sencha.gxt.widget.core.client.WindowManager;
import com.sencha.gxt.widget.core.client.container.MarginData;

import net.datenwerke.gxtdto.client.baseex.widget.btn.DwTextButton;
import net.datenwerke.gxtdto.client.baseex.widget.layout.DwFlowContainer;
import net.datenwerke.gxtdto.client.forms.simpleform.SimpleForm;
import net.datenwerke.gxtdto.client.locale.BaseMessages;
import net.datenwerke.gxtdto.client.theme.CssClassConstant;
import net.datenwerke.rs.theme.client.icon.BaseIcon;

public class DwWindow extends Window {

   @CssClassConstant
   public static final String CSS_NAME = "rs-w";

   @CssClassConstant
   public static final String CSS_HEADER_NAME = "rs-w-header";

   @CssClassConstant
   public static final String CSS_BODY_NAME = "rs-w-body";

   @CssClassConstant
   public static final String CSS_CONTENT_NAME = "rs-w-content";

   @CssClassConstant
   public static final String CSS_BBAR = "rs-w-bbar";

   private boolean centerOnShow;

   public DwWindow() {
      this(GWT.<WindowAppearance>create(WindowAppearance.class));
   }

   public DwWindow(WindowAppearance appearance) {
      super(appearance);
      initCss();
   }

   public static DwWindow newAutoSizeDialog() {
      return newAutoSizeDialog(400);
   }

   public static DwWindow newAutoSizeDialog(int width) {
      DwWindow dialog = new DwWindow();

      dialog.setModal(true);
      dialog.setPixelSize(-1, -1);
      dialog.setMinWidth(width);
      dialog.setMinHeight(0);

      return dialog;
   }

   @Override
   /* weird sencha bug, that otherwise window position is off */
   public void add(final Widget child) {
      if (!(child instanceof SimpleForm))
         super.add(child);
      else {
         final DwFlowContainer container = new DwFlowContainer();
         Scheduler.get().scheduleDeferred(() -> container.add(child));
         super.add(container);
      }
   }

   @Override
   /* weird sencha bug, that otherwise window position is off */
   public void add(final Widget child, final MarginData layoutData) {
      if (!(child instanceof SimpleForm))
         super.add(child, layoutData);
      else {
         final DwFlowContainer container = new DwFlowContainer();
         Scheduler.get().scheduleDeferred(() -> container.add(child, layoutData));
         super.add(container);
      }
   }

   protected void initCss() {
      getElement().addClassName(getCssName());
      getAppearance().getHeaderElem(getElement()).addClassName(getCssHeaderName());
      getAppearance().getBodyWrap(getElement()).addClassName(getCssBodyName());
      getAppearance().getContentElem(getElement()).addClassName(getCssContentName());

      getButtonBar().getElement().addClassName(CSS_BBAR);
   }

   public void addClassName(String name) {
      getElement().addClassName(name);
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

   public interface OnButtonClickHandler {
      public void onClick();
   }

   public void setSize(int width, int height) {
      int maxHeight = com.google.gwt.user.client.Window.getClientHeight() - 10;
      int maxWidth = com.google.gwt.user.client.Window.getClientWidth() - 10;

      super.setSize(Math.min(width, maxWidth) + "px", Math.min(height, maxHeight) + "px");
   }

   public DwTextButton addCancelButton() {
      return addCancelButton(() -> {
      });
   }

   public DwTextButton addCancelButton(final OnButtonClickHandler handler) {
      return addSpecButton(handler, BaseMessages.INSTANCE.cancel());
   }

   /**
    * 
    * 
    * Careful with real form submits, as the form needs to be attached during the
    * submit.
    * 
    * @param handler
    */
   public DwTextButton addSubmitButton(final OnButtonClickHandler handler) {
      return addSpecButton(handler, BaseMessages.INSTANCE.submit());
   }

   public DwTextButton addSpecButton(final OnButtonClickHandler handler, String label) {
      DwTextButton specBtn = new DwTextButton(label);
      addButton(specBtn);
      specBtn.addSelectHandler(event -> {
         handler.onClick();
         hide();
      });

      return specBtn;
   }

   public DwTextButton addSpecButton(final OnButtonClickHandler handler, String label, final boolean hide) {
      DwTextButton specBtn = new DwTextButton(label);
      addButton(specBtn);
      if (null != handler) {
         specBtn.addSelectHandler(event -> {
            handler.onClick();
            if (hide)
               hide();
         });
      } else {
         specBtn.addSelectHandler(event -> {
            if (hide)
               hide();
         });
      }

      return specBtn;
   }

   public void setHeaderIcon(BaseIcon icon) {
      getHeader().setIcon(icon.toImageResource());
   }

   public void setCenterOnShow(boolean center) {
      centerOnShow = center;
   }

   @Override
   public void show() {
      super.show();
      if (centerOnShow) {
         Scheduler.get().scheduleDeferred(() -> {
            center();
            forceLayout();
         });
      }
   }

   @Override
   protected void afterShow() {
      super.afterShow();

      // don't switch windows via tab
      WindowManager.get().unregister(this);
   }
}
