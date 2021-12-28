package net.datenwerke.rs.fileserver.client.fileserver;

import com.google.gwt.inject.client.AbstractGinModule;
import com.google.inject.Singleton;

import net.datenwerke.gf.client.treedb.UITree;
import net.datenwerke.rs.fileserver.client.fileserver.eximport.ex.FileServerExportUIModule;
import net.datenwerke.rs.fileserver.client.fileserver.provider.BasicTreeProvider;
import net.datenwerke.rs.fileserver.client.fileserver.provider.FolderTreeProvider;
import net.datenwerke.rs.fileserver.client.fileserver.provider.FullTreeProvider;
import net.datenwerke.rs.fileserver.client.fileserver.provider.annotations.FileServerManagerAdminViewTree;
import net.datenwerke.rs.fileserver.client.fileserver.provider.annotations.FileServerTreeBasic;
import net.datenwerke.rs.fileserver.client.fileserver.provider.annotations.FileServerTreeFolders;
import net.datenwerke.rs.fileserver.client.fileserver.provider.annotations.FileServerTreeFull;

public class FileServerUiModule extends AbstractGinModule {

   public static final String UPLOAD_SERVLET_ACTION = "fileServerUploadFile";
   public static final String UPLOAD_SERVLET_KEY_UPLOAD = "fileUpload";

   public static final String FILE_ACCESS_SERVLET = "fileServerAccess";

   public static final String ADMIN_TREE_MENU_NAME = "fileserver:admin:tree:menu";

   public static final String FILESERVER_HISTORY_TOKEN = "fileservermgr";

   public static final String FILE_UPLOAD_HANDLER_ID = "fileserver_file_upload_handler";
   public static final String UPLOAD_FILE_ID_FIELD = "fileID";

   @Override
   protected void configure() {
      /* bind trees */
      bind(UITree.class).annotatedWith(FileServerTreeBasic.class).toProvider(BasicTreeProvider.class);
      bind(UITree.class).annotatedWith(FileServerTreeFull.class).toProvider(FullTreeProvider.class);
      bind(UITree.class).annotatedWith(FileServerTreeFolders.class).toProvider(FolderTreeProvider.class);
      bind(UITree.class).annotatedWith(FileServerManagerAdminViewTree.class).toProvider(FullTreeProvider.class)
            .in(Singleton.class);

      bind(FileServerUiService.class).to(FileServerUiServiceImpl.class).in(Singleton.class);

      install(new FileServerExportUIModule());

      bind(FileServerUiStartup.class).asEagerSingleton();
   }

}
