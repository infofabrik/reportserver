package net.datenwerke.gxtdto.client.baseex.widget.btn.impl;

import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.sencha.gxt.widget.core.client.Dialog.PredefinedButton;
import com.sencha.gxt.widget.core.client.box.ConfirmMessageBox;
import com.sencha.gxt.widget.core.client.box.PromptMessageBox;
import com.sencha.gxt.widget.core.client.event.DialogHideEvent;
import com.sencha.gxt.widget.core.client.event.DialogHideEvent.DialogHideHandler;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.menu.Item;
import com.sencha.gxt.widget.core.client.menu.MenuItem;

import net.datenwerke.gxtdto.client.baseex.widget.btn.DwTextButton;
import net.datenwerke.gxtdto.client.baseex.widget.mb.DwConfirmMessageBox;
import net.datenwerke.gxtdto.client.baseex.widget.mb.DwPromptMessageBox;
import net.datenwerke.gxtdto.client.baseex.widget.menu.DwMenu;
import net.datenwerke.gxtdto.client.baseex.widget.menu.DwMenuItem;
import net.datenwerke.gxtdto.client.locale.BaseMessages;
import net.datenwerke.rs.theme.client.icon.BaseIcon;

public class DwRemoveButton extends DwTextButton {

   private boolean confirmSingleItem;
   private boolean protectSingleItem;
   private String removeConfirmTitle = BaseMessages.INSTANCE.confirmDeleteTitle();
   private String removeConfirmMessage = BaseMessages.INSTANCE.confirmDeleteMsg("");

   private String removePromptDescription = BaseMessages.INSTANCE.confirmPromptDescription("REMOVE");

   private boolean protectAll;
   private String removeAllConfirmTitle = BaseMessages.INSTANCE.confirmDeleteTitle();
   private String removeAllConfirmMessage = BaseMessages.INSTANCE.confirmDeleteMsg("");

   public DwRemoveButton() {
      super(BaseMessages.INSTANCE.remove());

      init();
   }

   private void init() {
      setIcon(BaseIcon.DELETE);
      addSelectHandler(new SelectHandler() {
         @Override
         public void onSelect(SelectEvent event) {
            if (!canRemoveSingle())
               return;

            if (!isConfirmSingleItem())
               onRemove();
            else if (isProtectSingleItem()) {
               SafeHtmlBuilder sb = new SafeHtmlBuilder().appendEscaped(getRemoveConfirmMessage())
                     .appendHtmlConstant("<br/><br/>").appendEscaped(getRemovePromptDescription());

               final PromptMessageBox pmb = new DwPromptMessageBox(SafeHtmlUtils.fromString(getRemoveConfirmTitle()),
                     sb.toSafeHtml());
               pmb.setWidth(400);
               pmb.getButton(PredefinedButton.OK).disable();
               pmb.getTextField().addValueChangeHandler(new ValueChangeHandler<String>() {
                  @Override
                  public void onValueChange(ValueChangeEvent<String> event) {
                     if ("REMOVE".equals(event.getValue()))
                        pmb.getButton(PredefinedButton.OK).enable();
                     else
                        pmb.getButton(PredefinedButton.OK).disable();
                  }
               });
               pmb.addDialogHideHandler(new DialogHideHandler() {
                  @Override
                  public void onDialogHide(DialogHideEvent event) {
                     if (event.getHideButton() == PredefinedButton.OK
                           && "REMOVE".equals(pmb.getTextField().getValue())) {
                        onRemove();
                     }
                  }
               });
               pmb.show();
            } else {
               ConfirmMessageBox cmb = new DwConfirmMessageBox(getRemoveConfirmTitle(), getRemoveConfirmMessage());
               cmb.addDialogHideHandler(new DialogHideHandler() {
                  @Override
                  public void onDialogHide(DialogHideEvent event) {
                     if (event.getHideButton() == PredefinedButton.YES) {
                        onRemove();
                     }
                  }
               });
               cmb.show();
            }
         }
      });

      MenuItem removeAllButton = new DwMenuItem(BaseMessages.INSTANCE.removeAll(), BaseIcon.DELETE);
      removeAllButton.addSelectionHandler(new SelectionHandler<Item>() {
         @Override
         public void onSelection(SelectionEvent<Item> event) {
            if (!canRemoveAll())
               return;

            if (isProtectAll()) {
               SafeHtmlBuilder sb = new SafeHtmlBuilder().appendEscaped(getRemoveAllConfirmMessage())
                     .appendHtmlConstant("<br/><br/>").appendEscaped(getRemovePromptDescription());

               final PromptMessageBox pmb = new DwPromptMessageBox(SafeHtmlUtils.fromString(getRemoveAllConfirmTitle()),
                     sb.toSafeHtml());
               pmb.setWidth(400);
               pmb.getButton(PredefinedButton.OK).disable();
               pmb.getTextField().addValueChangeHandler(new ValueChangeHandler<String>() {
                  @Override
                  public void onValueChange(ValueChangeEvent<String> event) {
                     if ("REMOVE".equals(event.getValue()))
                        pmb.getButton(PredefinedButton.OK).enable();
                     else
                        pmb.getButton(PredefinedButton.OK).disable();
                  }
               });
               pmb.addDialogHideHandler(new DialogHideHandler() {
                  @Override
                  public void onDialogHide(DialogHideEvent event) {
                     if (event.getHideButton() == PredefinedButton.OK
                           && "REMOVE".equals(pmb.getTextField().getValue())) {
                        onRemoveAll();
                     }
                  }
               });
               pmb.show();
            } else {
               ConfirmMessageBox cmb = new DwConfirmMessageBox(getRemoveAllConfirmTitle(),
                     getRemoveAllConfirmMessage());
               cmb.addDialogHideHandler(new DialogHideHandler() {
                  @Override
                  public void onDialogHide(DialogHideEvent event) {
                     if (event.getHideButton() == PredefinedButton.YES) {
                        onRemoveAll();
                     }
                  }
               });
               cmb.show();
            }
         }
      });

      DwMenu menu = new DwMenu();
      menu.add(removeAllButton);
      setMenu(menu);
   }

   protected boolean canRemoveAll() {
      return true;
   }

   protected boolean canRemoveSingle() {
      return true;
   }

   protected void onRemove() {
   }

   protected void onRemoveAll() {
   }

   public void setProtectAll(boolean protectAll) {
      this.protectAll = protectAll;
   }

   public boolean isProtectAll() {
      return protectAll;
   }

   public void setProtectSingleItem(boolean protectSingleItem) {
      this.protectSingleItem = protectSingleItem;
      if (protectSingleItem)
         setConfirmSingleItem(true);
   }

   public boolean isProtectSingleItem() {
      return protectSingleItem;
   }

   public void setConfirmSingleItem(boolean confirmSingleItem) {
      this.confirmSingleItem = confirmSingleItem;
   }

   public boolean isConfirmSingleItem() {
      return confirmSingleItem;
   }

   public void setRemoveConfirmMessage(String removeConfirmMessage) {
      this.removeConfirmMessage = removeConfirmMessage;
   }

   public String getRemoveConfirmMessage() {
      return removeConfirmMessage;
   }

   public void setRemoveConfirmTitle(String removeConfirmTitle) {
      this.removeConfirmTitle = removeConfirmTitle;
   }

   public String getRemoveConfirmTitle() {
      return removeConfirmTitle;
   }

   public void setRemoveAllConfirmMessage(String removeAllConfirmMessage) {
      this.removeAllConfirmMessage = removeAllConfirmMessage;
   }

   public String getRemoveAllConfirmMessage() {
      return removeAllConfirmMessage;
   }

   public void setRemoveAllConfirmTitle(String removeAllConfirmTitle) {
      this.removeAllConfirmTitle = removeAllConfirmTitle;
   }

   public String getRemoveAllConfirmTitle() {
      return removeAllConfirmTitle;
   }

   public String getRemovePromptDescription() {
      return removePromptDescription;
   }

   public void disableRemoveAll() {
      setMenu(null);
   }
}