package net.datenwerke.rs.markdown.service.markdown;

import com.google.inject.AbstractModule;

public class MarkdownModule extends AbstractModule {

   @Override
   protected void configure() {
      bind(MarkdownService.class).to(MarkdownServiceImpl.class);
      
      bind(MarkdownStartup.class).asEagerSingleton();
   }

}
