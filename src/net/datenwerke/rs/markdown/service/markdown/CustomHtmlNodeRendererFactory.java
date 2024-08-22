package net.datenwerke.rs.markdown.service.markdown;

import java.util.Collections;
import java.util.Set;

import org.commonmark.node.Document;
import org.commonmark.node.Node;
import org.commonmark.renderer.NodeRenderer;
import org.commonmark.renderer.html.HtmlNodeRendererContext;
import org.commonmark.renderer.html.HtmlNodeRendererFactory;
import org.commonmark.renderer.html.HtmlWriter;

public class CustomHtmlNodeRendererFactory implements HtmlNodeRendererFactory {

   @Override
   public NodeRenderer create(HtmlNodeRendererContext context) {
      return new CustomHtmlNodeRenderer(context);
   }

   static class CustomHtmlNodeRenderer implements NodeRenderer {

      private final HtmlNodeRendererContext context;

      CustomHtmlNodeRenderer(HtmlNodeRendererContext context) {
         this.context = context;
      }

      @Override
      public Set<Class<? extends Node>> getNodeTypes() {
         return Collections.singleton(Document.class);
      }

      @Override
      public void render(Node node) {
         if (node instanceof Document) {
            HtmlWriter html = context.getWriter();
            html.raw("<!DOCTYPE html>\n");
            html.raw("<html>\n<head>\n");
            html.raw("<title>Markdown</title>\n");

            html.raw("<link rel=\"stylesheet\" type=\"text/css\" href=\"reportserver/rstheme\">\n");

            html.raw("</head>\n<body style=\"background-color:white !important\">\n");
            renderChildren(node.getFirstChild());
            html.raw("</body>\n</html>\n");
         }

      }

      private void renderChildren(Node node) {
         while (node != null) {
            context.render(node);
            node = node.getNext();
         }
      }
   }
}