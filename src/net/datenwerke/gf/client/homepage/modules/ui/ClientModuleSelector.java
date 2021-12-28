package net.datenwerke.gf.client.homepage.modules.ui;

import net.datenwerke.gf.client.homepage.modules.ClientMainModule;

public interface ClientModuleSelector {

   public abstract void moduleTextUpdated(ClientMainModule module, String text);

   public void moduleTooltipUpdated(ClientMainModule module, String text);

}