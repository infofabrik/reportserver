package net.datenwerke.rs.dot.service.dot;

import com.google.inject.Inject;
import com.google.inject.Provider;

import guru.nidi.graphviz.engine.Graphviz;
import guru.nidi.graphviz.engine.GraphvizV8Engine;
import net.datenwerke.gf.service.lateinit.LateInitHook;
import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.base.service.renderer.hooks.RendererHook;
import net.datenwerke.rs.dot.service.dot.hookers.DotRendererHooker;

public class DotStartup {

   @Inject
   public DotStartup(
         HookHandlerService hookHandler,
         Provider<DotRendererHooker> dotRendererHookerProvider
         ) {
      
      hookHandler.attachHooker(LateInitHook.class, () -> Graphviz.useEngine(new GraphvizV8Engine()));
      
      hookHandler.attachHooker(RendererHook.class, dotRendererHookerProvider);
   }
}
