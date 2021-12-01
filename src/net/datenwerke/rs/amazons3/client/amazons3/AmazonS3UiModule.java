package net.datenwerke.rs.amazons3.client.amazons3;

import com.google.gwt.inject.client.AbstractGinModule;
import com.google.inject.Singleton;

import net.datenwerke.gf.client.treedb.UITree;
import net.datenwerke.rs.amazons3.client.amazons3.provider.AmazonS3TreeProvider;
import net.datenwerke.rs.amazons3.client.amazons3.provider.annotations.DatasinkTreeAmazonS3;

public class AmazonS3UiModule extends AbstractGinModule {

   public final static String AMAZON_S3_NAME = "Amazon S3";

   @Override
   protected void configure() {
      bind(AmazonS3UiService.class).to(AmazonS3UiServiceImpl.class).in(Singleton.class);

      /* bind trees */
      bind(UITree.class).annotatedWith(DatasinkTreeAmazonS3.class).toProvider(AmazonS3TreeProvider.class);
      bind(AmazonS3UiStartup.class).asEagerSingleton();

   }

}
