package net.datenwerke.rs.core.service.contexthelp;

import java.io.StringWriter;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;

import com.google.inject.Inject;

import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.core.client.contexthelp.dto.ContextHelpInfo;
import net.datenwerke.rs.core.service.contexthelp.hooks.ContextHelpAdapterHook;
import net.datenwerke.rs.utils.man.ManPageService;

public class ContextHelpServiceImpl implements ContextHelpService {

   private final ManPageService manPageService;
   private final HookHandlerService hookHandler;

   @Inject
   public ContextHelpServiceImpl(ManPageService manPageService, HookHandlerService hookHandler) {
      super();

      this.manPageService = manPageService;
      this.hookHandler = hookHandler;
   }

   @Override
   public String getContextHelp(ContextHelpInfo info) {
      String manpage = manPageService.getManPageFailsafe("context/" + info.getId());

      VelocityContext context = new VelocityContext();

      StringWriter out = new StringWriter();
      Velocity.evaluate(context, out, "", manpage);

      String result = out.toString();

      for (ContextHelpAdapterHook adapter : hookHandler.getHookers(ContextHelpAdapterHook.class)) {
         String adapted = adapter.adaptContextHelp(result, info);
         if (null != adapted)
            result = adapted;
      }

      return result;
   }

}
