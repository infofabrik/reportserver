package net.datenwerke.gxtdto.client.forms.wizard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.widget.core.client.container.CardLayoutContainer;

import net.datenwerke.gxtdto.client.baseex.widget.DwWindow;
import net.datenwerke.gxtdto.client.baseex.widget.btn.DwTextButton;
import net.datenwerke.gxtdto.client.baseex.widget.mb.DwAlertMessageBox;
import net.datenwerke.gxtdto.client.forms.locale.FormsMessages;
import net.datenwerke.gxtdto.client.locale.BaseMessages;

/**
 * A Dialog capable of navigating between multiple pages by means of
 * prev/next/finish buttons
 * 
 *
 */
public abstract class WizardDialog extends DwWindow {

   private CardLayoutContainer cardLayoutContainer;
   private DwTextButton prevButton;
   private DwTextButton nextButton;
   private DwTextButton cancelButton;
   private DwTextButton finishButton;
   private List<Widget> pages;
   private int currentPage;

   public WizardDialog() {
      this(new Widget[] {});
   }

   public WizardDialog(Widget... pages) {
      super();
      cardLayoutContainer = new CardLayoutContainer();
      setWidget(cardLayoutContainer);
      setModal(true);

      this.pages = new ArrayList<Widget>();
      this.pages.addAll(Arrays.asList(pages));

      for (Widget page : pages)
         add(page);

      createButtons();
      showPage(0);
   }

   public int getPageCount() {
      return pages.size();
   }

   public void addPage(Widget page) {
      pages.add(page);
      cardLayoutContainer.add(page);
      if (page instanceof WizardAware)
         ((WizardAware) page).setWizard(this);
   }

   public void addPage(int index, Widget page) {
      pages.add(index, page);
      cardLayoutContainer.add(page);
   }

   public Widget removePage(int index) {
      return pages.remove(index);
   }

   public boolean removePage(Widget page) {
      return pages.remove(page);
   }

   public void showPage(int pageNum) {
      if (pageNum > pages.size() - 1)
         return;

      Widget page = pages.get(pageNum);
      cardLayoutContainer.setActiveWidget(page);

      prevButton.setEnabled(pageNum > 0);
      nextButton.setEnabled(pageNum < pages.size() - 1);

      finishButton.setEnabled(pageNum == pages.size() - 1);

      if (page instanceof WizardResizer)
         setHeight(((WizardResizer) page).getPageHeight());
      else
         setHeight(-1);

      currentPage = pageNum;

      forceLayout();
   }

   private void notifyListenersOfPageChange(final int pageNr, final Widget page) {
      pages.stream().filter(p -> p instanceof WizardPageChangeListener)
            .forEach(p -> ((WizardPageChangeListener) p).onPageChange(pageNr, page));
   }

   private void createButtons() {
      prevButton = new DwTextButton(BaseMessages.INSTANCE.prev());
      nextButton = new DwTextButton(BaseMessages.INSTANCE.next());
      cancelButton = new DwTextButton(BaseMessages.INSTANCE.cancel());
      finishButton = new DwTextButton(FormsMessages.INSTANCE.finish());

      finishButton.setEnabled(false);

      prevButton.addSelectHandler(event -> {
         notifyListenersOfPageChange(currentPage, pages.get(currentPage));
         showPage(currentPage - 1);
      });

      nextButton.addSelectHandler(event -> {
         if (!currentPageValid())
            return;

         notifyListenersOfPageChange(currentPage, pages.get(currentPage));
         showPage(currentPage + 1);
      });

      cancelButton.addSelectHandler(event -> WizardDialog.this.hide());

      finishButton.addSelectHandler(event -> {
         if (!currentPageValid())
            return;
         onFinish();
      });

      addButton(prevButton);
      addButton(nextButton);
      addButton(finishButton);

   }

   private boolean currentPageValid() {
      Widget current = cardLayoutContainer.getActiveWidget();
      if (current instanceof Validatable) {
         if (!((Validatable) current).isValid()) {
            new DwAlertMessageBox(FormsMessages.INSTANCE.invalidConfigTitle(),
                  FormsMessages.INSTANCE.invalidConfigMessage()).show();
            return false;
         }
      }
      return true;
   }

   public DwTextButton getCancelButton() {
      return cancelButton;
   }

   public DwTextButton getFinishButton() {
      return finishButton;
   }

   public abstract void onFinish();

}
