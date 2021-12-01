package net.datenwerke.rs.tsreportarea.client.tsreportarea.hooks;

import net.datenwerke.hookhandler.shared.hookhandler.interfaces.Hook;
import net.datenwerke.rs.tsreportarea.client.tsreportarea.dto.TsDiskGeneralReferenceDto;
import net.datenwerke.rs.tsreportarea.client.tsreportarea.ui.TsDiskMainComponent;

public interface GeneralReferenceHandlerHook extends Hook {

	boolean consumes(TsDiskGeneralReferenceDto item);

	void handle(TsDiskGeneralReferenceDto item, TsDiskMainComponent mainComponent);

}
