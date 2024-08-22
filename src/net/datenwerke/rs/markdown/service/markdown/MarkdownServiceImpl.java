package net.datenwerke.rs.markdown.service.markdown;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.commonmark.Extension;
import org.commonmark.ext.gfm.strikethrough.StrikethroughExtension;
import org.commonmark.ext.gfm.tables.TableBlock;
import org.commonmark.ext.gfm.tables.TablesExtension;
import org.commonmark.ext.image.attributes.ImageAttributesExtension;
import org.commonmark.ext.ins.InsExtension;
import org.commonmark.ext.task.list.items.TaskListItemMarker;
import org.commonmark.ext.task.list.items.TaskListItemsExtension;
import org.commonmark.node.BlockQuote;
import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.AttributeProvider;
import org.commonmark.renderer.html.HtmlRenderer;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.rs.dot.service.dot.DotService;
import net.datenwerke.rs.markdown.service.markdown.dot.DotExtension;

public class MarkdownServiceImpl implements MarkdownService{

   private final Provider<DotService> dotServiceProvider;
   
   @Inject
   public MarkdownServiceImpl(
         Provider<DotService> dotServiceProvider
         ) {
      this.dotServiceProvider = dotServiceProvider;
   }
   
   @Override
   public String renderHtml(String content) throws IOException {
      List<Extension> extensions = Arrays.asList(
            TablesExtension.create(), 
            StrikethroughExtension.create(),
            TaskListItemsExtension.create(),
            ImageAttributesExtension.create(),
            InsExtension.create(),
            DotExtension.create(dotServiceProvider)
            );
      
      AttributeProvider attProvider = (Node node, String tagName, Map<String, String> attributes) -> {
         if (node instanceof TableBlock) 
            attributes.put("class", "markdown");
         if (node instanceof BlockQuote)
            attributes.put("class", "markdown");
         if (node instanceof TaskListItemMarker) {
            attributes.remove("disabled");
            attributes.put("onclick", "event.target.checked = !event.target.checked ");
         }
      };
      
      Parser parser = Parser
            .builder()
            .extensions(extensions)
            .build();
      Node document = parser.parse(content);
      HtmlRenderer renderer = HtmlRenderer
            .builder()
            .nodeRendererFactory(new CustomHtmlNodeRendererFactory())
            .extensions(extensions)
            .attributeProviderFactory(context -> attProvider)
            .build();
      return renderer.render(document);
   }
   
}
