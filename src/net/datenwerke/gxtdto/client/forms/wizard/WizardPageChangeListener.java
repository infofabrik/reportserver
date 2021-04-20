package net.datenwerke.gxtdto.client.forms.wizard;

import com.google.gwt.user.client.ui.Widget;

/**
 * Classes implementing this will be notified when the visible page of a
 * {@link WizardDialog} changes.
 */
public interface WizardPageChangeListener {

   /**
    * Called when the visible page of a {@link WizardDialog} changes by either the
    * "next" button or the "previous" button of the wizard.
    * 
    * @param pageNr the page number of the changing page
    * @param page   the changing page
    */
   void onPageChange(int pageNr, Widget page);
}
