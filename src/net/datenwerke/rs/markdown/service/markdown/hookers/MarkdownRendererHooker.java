package net.datenwerke.rs.markdown.service.markdown.hookers;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Optional;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.rs.base.service.renderer.BinaryFormat;
import net.datenwerke.rs.base.service.renderer.RendererException;
import net.datenwerke.rs.base.service.renderer.TextFormat;
import net.datenwerke.rs.base.service.renderer.hooks.RendererHook;
import net.datenwerke.rs.markdown.client.markdown.MarkdownUiModule;
import net.datenwerke.rs.markdown.service.markdown.MarkdownService;

public class MarkdownRendererHooker implements RendererHook {

   private final Provider<MarkdownService> markdownServiceProvider;

   @Inject
   public MarkdownRendererHooker(
         Provider<MarkdownService> markdownServiceProvider
         ) {
      this.markdownServiceProvider = markdownServiceProvider;
   }

   @Override
   public boolean consumes(String contentType) {
      return MarkdownUiModule.MIME_TYPE.contentEquals(contentType);
   }

   @Override
   public ByteArrayOutputStream render(BinaryFormat format, String source, Optional<Integer> width,
         Optional<Integer> height) throws RendererException {
      throw new UnsupportedOperationException("not supported");
   }

   @Override
   public String render(TextFormat format, String source, Optional<Integer> width, Optional<Integer> height)
         throws RendererException {
      if (!TextFormat.HTML.equals(format)) {
         throw new UnsupportedOperationException("not supported: " + format.name());
      }
      try {
         return markdownServiceProvider.get().renderHtml(source);
      } catch (IOException e) {
         throw new RendererException(e);
      }
   }

}
