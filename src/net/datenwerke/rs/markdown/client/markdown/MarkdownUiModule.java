package net.datenwerke.rs.markdown.client.markdown;

import com.google.gwt.inject.client.AbstractGinModule;

public class MarkdownUiModule extends AbstractGinModule {

   public static final String MIME_TYPE = "text/markdown";
   
   @Override
   protected void configure() {
      bind(MarkdownUiStartup.class).asEagerSingleton();
   }

}
