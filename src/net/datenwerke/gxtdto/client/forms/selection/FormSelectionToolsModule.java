package net.datenwerke.gxtdto.client.forms.selection;

import com.google.gwt.inject.client.AbstractGinModule;

public class FormSelectionToolsModule extends AbstractGinModule {

   @Override
   protected void configure() {
      requestStaticInjection(SelectionPopup.class);
   }

}
