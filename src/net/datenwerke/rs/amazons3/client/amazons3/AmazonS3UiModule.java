package net.datenwerke.rs.amazons3.client.amazons3;

import com.google.gwt.inject.client.AbstractGinModule;
import com.google.inject.Singleton;

import net.datenwerke.gf.client.treedb.UITree;
import net.datenwerke.rs.amazons3.client.amazons3.dto.AmazonS3DatasinkDto;
import net.datenwerke.rs.amazons3.client.amazons3.provider.AmazonS3TreeProvider;
import net.datenwerke.rs.amazons3.client.amazons3.provider.annotations.DatasinkTreeAmazonS3;
import net.datenwerke.rs.core.client.datasinkmanager.dto.DatasinkDefinitionDto;
import net.datenwerke.rs.theme.client.icon.BaseIcon;

public class AmazonS3UiModule extends AbstractGinModule {

   public final static String NAME = "Amazon S3";
   public final static BaseIcon ICON = BaseIcon.AMAZON;
   public final static Class<? extends DatasinkDefinitionDto> TYPE = AmazonS3DatasinkDto.class;

   @Override
   protected void configure() {
      bind(AmazonS3UiService.class).to(AmazonS3UiServiceImpl.class).in(Singleton.class);

      /* bind trees */
      bind(UITree.class).annotatedWith(DatasinkTreeAmazonS3.class).toProvider(AmazonS3TreeProvider.class);
      bind(AmazonS3UiStartup.class).asEagerSingleton();

   }

}
