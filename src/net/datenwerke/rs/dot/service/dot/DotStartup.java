package net.datenwerke.rs.dot.service.dot;

import com.google.inject.Inject;

import guru.nidi.graphviz.engine.Graphviz;
import guru.nidi.graphviz.engine.GraphvizV8Engine;
import net.datenwerke.gf.service.lateinit.LateInitHook;
import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;

public class DotStartup {

   @Inject
   public DotStartup(HookHandlerService hookHandler) {
      hookHandler.attachHooker(LateInitHook.class, () -> Graphviz.useEngine(new GraphvizV8Engine()));
   }
}
