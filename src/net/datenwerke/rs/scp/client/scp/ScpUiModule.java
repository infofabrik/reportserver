package net.datenwerke.rs.scp.client.scp;

import com.google.gwt.inject.client.AbstractGinModule;
import com.google.inject.Singleton;

import net.datenwerke.gf.client.treedb.UITree;
import net.datenwerke.gf.service.upload.hooks.FileUploadHandlerHook;
import net.datenwerke.rs.core.client.datasinkmanager.dto.DatasinkDefinitionDto;
import net.datenwerke.rs.scp.client.scp.dto.ScpDatasinkDto;
import net.datenwerke.rs.scp.client.scp.provider.ScpTreeProvider;
import net.datenwerke.rs.scp.client.scp.provider.annotations.DatasinkTreeScp;
import net.datenwerke.rs.scp.service.scp.definitions.ScpDatasink;
import net.datenwerke.rs.scp.service.scp.hooker.ScpPrivateKeyUploadHooker;
import net.datenwerke.rs.theme.client.icon.BaseIcon;

public class ScpUiModule extends AbstractGinModule {

   public final static String NAME = "SCP";
   public final static BaseIcon ICON = BaseIcon.ARROW_UP;
   public final static Class<? extends DatasinkDefinitionDto> TYPE = ScpDatasinkDto.class;
   
   /**
    * Identifies the {@link FileUploadHandlerHook} to use
    * {@link ScpPrivateKeyUploadHooker} in case of SCP private key upload
    * ({@link ScpDatasink}).
    */
   public static final String SCP_PRIVATE_KEY_UPLOAD_HANDLER_ID = "scp_private_key_upload_handler";

   /**
    * Field containing the datasink id used while uploading a SCP datasink
    * ({@link ScpDatasink}) private key.
    */
   public static final String SCP_UPLOAD_DATASINK_ID_FIELD = "scp_datasinkID";

   @Override
   protected void configure() {
      bind(ScpUiService.class).to(ScpUiServiceImpl.class).in(Singleton.class);

      /* bind trees */
      bind(UITree.class).annotatedWith(DatasinkTreeScp.class).toProvider(ScpTreeProvider.class);

      bind(ScpUiStartup.class).asEagerSingleton();
   }

}
