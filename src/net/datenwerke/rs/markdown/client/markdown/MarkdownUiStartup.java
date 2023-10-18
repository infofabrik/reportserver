package net.datenwerke.rs.markdown.client.markdown;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.gf.client.managerhelper.hooks.MainPanelViewToolbarConfiguratorHook;
import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.markdown.client.markdown.hookers.MarkdownFileViewPreviewHooker;
import net.datenwerke.rs.markdown.client.markdown.hookers.ReloadButtonToolbarHookerMarkdown;
import net.datenwerke.rs.markdown.client.markdown.hooks.MarkdownFileViewHook;

public class MarkdownUiStartup {

   @Inject
   public MarkdownUiStartup(final HookHandlerService hookHandler,
         final Provider<MarkdownFileViewPreviewHooker> markdownFilePreviewViewProvider,
         final Provider<ReloadButtonToolbarHookerMarkdown> reloadButtonToolbarHookerMarkdown) {

      /* attach views */
      hookHandler.attachHooker(MarkdownFileViewHook.class, markdownFilePreviewViewProvider);
      /* toolbar */
      hookHandler.attachHooker(MainPanelViewToolbarConfiguratorHook.class, reloadButtonToolbarHookerMarkdown);
   }
}
