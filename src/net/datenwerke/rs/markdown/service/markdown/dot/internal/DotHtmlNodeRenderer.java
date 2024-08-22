package net.datenwerke.rs.markdown.service.markdown.dot.internal;

import static java.util.stream.Collectors.toSet;

import java.util.Set;
import java.util.stream.Stream;

import org.commonmark.node.Node;
import org.commonmark.renderer.NodeRenderer;
import org.commonmark.renderer.html.HtmlNodeRendererContext;
import org.commonmark.renderer.html.HtmlWriter;

import com.google.inject.Provider;

import net.datenwerke.rs.base.service.renderer.TextFormat;
import net.datenwerke.rs.dot.service.dot.DotService;
import net.datenwerke.rs.markdown.service.markdown.dot.DotBlock;

public class DotHtmlNodeRenderer implements NodeRenderer {

   private final HtmlWriter html;
   private final Provider<DotService> dotServiceProvider;
   
   public DotHtmlNodeRenderer(HtmlNodeRendererContext context, Provider<DotService> dotServiceProvider) {
      this.dotServiceProvider = dotServiceProvider;
      this.html = context.getWriter();
   }

   @Override
   public Set<Class<? extends Node>> getNodeTypes() {
      return Stream.of(DotBlock.class).collect(toSet());
   }

   @Override
   public void render(Node node) {
      if (node instanceof DotBlock) {
         DotBlock dotBlock = (DotBlock) node;
         String dotContent = dotBlock.getContent();
   
         // Render the DOT content
         try {
            String svg = dotServiceProvider.get().render(TextFormat.SVG, dotContent, dotBlock.getWidth(),
                  dotBlock.getHeight());
            html.raw(svg);
         } catch (Exception e) {
            throw new RuntimeException(e);
         }
      } 
   }
}