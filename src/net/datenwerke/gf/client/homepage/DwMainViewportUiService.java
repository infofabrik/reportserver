package net.datenwerke.gf.client.homepage;

import java.util.Collection;

import net.datenwerke.gf.client.dispatcher.Dispatchable;
import net.datenwerke.gf.client.homepage.modules.ClientMainModule;
import net.datenwerke.gf.client.homepage.modules.ClientTempModule;

public interface DwMainViewportUiService extends Dispatchable {

   void showModule(ClientMainModule module);

   void addTempModule(ClientTempModule module);

   void removeTempModule(ClientTempModule module);

   Collection<ClientTempModule> getTempModules();

   void setLoadingMask();

   void unmask();
}
