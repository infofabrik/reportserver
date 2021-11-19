package net.datenwerke.rs.core.client.datasinkmanager;

import com.google.gwt.inject.client.AbstractGinModule;
import com.google.gwt.inject.client.assistedinject.GinFactoryModuleBuilder;
import com.google.inject.Singleton;

import net.datenwerke.gf.client.treedb.UITree;
import net.datenwerke.rs.core.client.datasinkmanager.helper.forms.DatasinkSelectionFieldFactory;
import net.datenwerke.rs.core.client.datasinkmanager.provider.BasicTreeProvider;
import net.datenwerke.rs.core.client.datasinkmanager.provider.FolderTreeProvider;
import net.datenwerke.rs.core.client.datasinkmanager.provider.FullTreeProvider;
import net.datenwerke.rs.core.client.datasinkmanager.provider.annotations.DatasinkManagerAdminViewTree;
import net.datenwerke.rs.core.client.datasinkmanager.provider.annotations.DatasinkTreeBasic;
import net.datenwerke.rs.core.client.datasinkmanager.provider.annotations.DatasinkTreeFolders;

/**
 * 
 *
 */
public class DatasinkUIModule extends AbstractGinModule {

	public static final String DATASINK_FAV_HISTORY_TOKEN = "datasinkmgr";
	
	public static final String PROPERTY_CONTAINER = "Datasink_PropertyContainer";

	public static final String ADMIN_TREE_MENU_NAME = "datasink:admin:tree:menu";
	
	public static final String DATASINK_KEY = "datasink_key";
	public static final String DATASINK_FILENAME = "datasink_filename";
	public static final String DATASINK_FOLDER = "datasink_folder";
	
	@Override
	protected void configure() {
		/* bind trees */
		bind(UITree.class).annotatedWith(DatasinkTreeBasic.class).toProvider(BasicTreeProvider.class);
		bind(UITree.class).annotatedWith(DatasinkManagerAdminViewTree.class).toProvider(FullTreeProvider.class).in(Singleton.class);
		bind(UITree.class).annotatedWith(DatasinkTreeFolders.class).toProvider(FolderTreeProvider.class);
		
		/* bind service */
		bind(DatasinkUIService.class).to(DatasinkUIServiceImpl.class).in(Singleton.class);
		
		install(new GinFactoryModuleBuilder().build(DatasinkSelectionFieldFactory.class));
		
		/* bind startup */
		bind(DatasinkUIStartup.class).asEagerSingleton();
	}

}
