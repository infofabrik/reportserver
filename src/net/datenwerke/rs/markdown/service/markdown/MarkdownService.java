package net.datenwerke.rs.markdown.service.markdown;

import java.io.IOException;

public interface MarkdownService {
   
   String renderHtml(String content) throws IOException;
}
