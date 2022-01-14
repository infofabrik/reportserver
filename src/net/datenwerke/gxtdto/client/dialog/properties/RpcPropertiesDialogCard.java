package net.datenwerke.gxtdto.client.dialog.properties;

import net.datenwerke.gxtdto.client.utilityservices.submittracker.SubmitTrackerToken;

public interface RpcPropertiesDialogCard extends PropertiesDialogCard {

   public void submitPressed(SubmitTrackerToken token);
}
