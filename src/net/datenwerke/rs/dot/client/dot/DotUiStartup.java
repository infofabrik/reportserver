package net.datenwerke.rs.dot.client.dot;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.gf.client.managerhelper.hooks.MainPanelViewToolbarConfiguratorHook;
import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.dot.client.dot.hookers.DotFileViewPreviewHooker;
import net.datenwerke.rs.dot.client.dot.hookers.ReloadButtonHooker;
import net.datenwerke.rs.dot.client.dot.hooks.DotFileViewHook;

public class DotUiStartup {

   @Inject
   public DotUiStartup(final HookHandlerService hookHandler, 
         final Provider<DotFileViewPreviewHooker> dotFilePreviewViewProvider,
         final Provider<ReloadButtonHooker> reloadButtonHookerProvider
         ) {

      /* attach views */
      hookHandler.attachHooker(DotFileViewHook.class, dotFilePreviewViewProvider);
      /* toolbar */
      hookHandler.attachHooker(MainPanelViewToolbarConfiguratorHook.class, reloadButtonHookerProvider);
   }
}
