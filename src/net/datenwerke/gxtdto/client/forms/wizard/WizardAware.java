package net.datenwerke.gxtdto.client.forms.wizard;

/**
 * Classes implementing this can handle the corresponding {@link WizardDialog}
 * as they receive a reference to its instance.
 */
public interface WizardAware {

   /**
    * Sets the corresponding {@link WizardDialog}.
    * 
    * @param dialog the {@link WizardDialog}
    */
   public void setWizard(WizardDialog dialog);
}
