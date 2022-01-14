package net.datenwerke.gxtdto.client.dialog.properties;

import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.client.ui.Widget;

public interface PropertiesDialogCard {

   ImageResource getIcon();

   Widget getCard();

   String getName();

   void cancelPressed();

   String isValid();

   void submitPressed();

   int getHeight();

}
