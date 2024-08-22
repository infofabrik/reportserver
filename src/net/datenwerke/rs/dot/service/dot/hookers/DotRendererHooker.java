package net.datenwerke.rs.dot.service.dot.hookers;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Optional;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.rs.base.service.renderer.BinaryFormat;
import net.datenwerke.rs.base.service.renderer.RendererException;
import net.datenwerke.rs.base.service.renderer.TextFormat;
import net.datenwerke.rs.base.service.renderer.hooks.RendererHook;
import net.datenwerke.rs.dot.client.dot.DotUiModule;
import net.datenwerke.rs.dot.service.dot.DotService;

public class DotRendererHooker implements RendererHook {

   private final Provider<DotService> dotServiceProvider;

   @Inject
   public DotRendererHooker(Provider<DotService> dotServiceProvider) {
      this.dotServiceProvider = dotServiceProvider;
   }

   @Override
   public boolean consumes(String contentType) {
      return DotUiModule.MIME_TYPE.contentEquals(contentType);
   }

   @Override
   public ByteArrayOutputStream render(BinaryFormat format, String source, Optional<Integer> width,
         Optional<Integer> height) throws RendererException {
      try {
         return dotServiceProvider.get().render(format, source, width, height);
      } catch (IOException e) {
         throw new RendererException(e);
      }
   }

   @Override
   public String render(TextFormat format, String source, Optional<Integer> width, Optional<Integer> height)
         throws RendererException {
      try {
         return dotServiceProvider.get().render(format, source, width, height);
      } catch (IOException e) {
         throw new RendererException(e);
      }
   }

}
