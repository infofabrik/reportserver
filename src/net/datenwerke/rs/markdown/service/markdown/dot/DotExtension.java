package net.datenwerke.rs.markdown.service.markdown.dot;

import org.commonmark.Extension;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;

import com.google.inject.Provider;

import net.datenwerke.rs.dot.service.dot.DotService;
import net.datenwerke.rs.markdown.service.markdown.dot.internal.DotBlockParser;
import net.datenwerke.rs.markdown.service.markdown.dot.internal.DotHtmlNodeRenderer;

public class DotExtension implements Parser.ParserExtension, HtmlRenderer.HtmlRendererExtension{

   private final Provider<DotService> dotServiceProvider;
   
   private DotExtension(Provider<DotService> dotServiceProvider) {
      this.dotServiceProvider = dotServiceProvider;
   }

   public static Extension create(Provider<DotService> dotServiceProvider) {
      return new DotExtension(dotServiceProvider);
   }
   
   @Override
   public void extend(Parser.Builder parserBuilder) {
      parserBuilder.customBlockParserFactory(new DotBlockParser.Factory());
   }

   @Override
   public void extend(HtmlRenderer.Builder rendererBuilder) {
      rendererBuilder.nodeRendererFactory(context -> new DotHtmlNodeRenderer(context, dotServiceProvider));
   }

}
