package net.datenwerke.rs.markdown.service.markdown;

import java.io.IOException;

import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;

public class MarkdownServiceImpl implements MarkdownService{

   @Override
   public String renderHtml(String content) throws IOException {
      Parser parser = Parser.builder().build();
      Node document = parser.parse(content);
      HtmlRenderer renderer = HtmlRenderer.builder().build();
      return renderer.render(document);
   }
}
