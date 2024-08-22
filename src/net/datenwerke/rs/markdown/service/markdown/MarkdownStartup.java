package net.datenwerke.rs.markdown.service.markdown;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.base.service.renderer.hooks.RendererHook;
import net.datenwerke.rs.markdown.service.markdown.hookers.MarkdownRendererHooker;

public class MarkdownStartup {

   @Inject
   public MarkdownStartup(
         HookHandlerService hookService,
         Provider<MarkdownRendererHooker> markdownRendererHookerProvider
         ) {
      
      hookService.attachHooker(RendererHook.class, markdownRendererHookerProvider);
   }
}
