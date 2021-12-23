package net.datenwerke.rs.emaildatasink.client.emaildatasink;

import com.google.gwt.inject.client.AbstractGinModule;
import com.google.inject.Singleton;

import net.datenwerke.gf.client.treedb.UITree;
import net.datenwerke.rs.core.client.datasinkmanager.dto.DatasinkDefinitionDto;
import net.datenwerke.rs.emaildatasink.client.emaildatasink.dto.EmailDatasinkDto;
import net.datenwerke.rs.emaildatasink.client.emaildatasink.provider.EmailDatasinkTreeProvider;
import net.datenwerke.rs.emaildatasink.client.emaildatasink.provider.annotations.DatasinkTreeEmail;
import net.datenwerke.rs.theme.client.icon.BaseIcon;

public class EmailDatasinkUiModule extends AbstractGinModule {
   
   public final static String NAME = "Email - SMTP";
   public final static BaseIcon ICON = BaseIcon.SEND;
   public final static Class<? extends DatasinkDefinitionDto> TYPE = EmailDatasinkDto.class;

   public static final String DATASINK_SUBJECT = "datasink_subject";
   public static final String DATASINK_MESSSAGE = "datasink_message";
   public static final String DATASINK_RCPTLIST = "datasink_rcptlist";
   
   @Override
   protected void configure() {
      bind(EmailDatasinkUiService.class).to(EmailDatasinkUiServiceImpl.class).in(Singleton.class);
      
      /* bind trees */
      bind(UITree.class).annotatedWith(DatasinkTreeEmail.class).toProvider(EmailDatasinkTreeProvider.class);
      bind(EmailDatasinkUiStartup.class).asEagerSingleton();
   }

}