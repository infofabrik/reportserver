package net.datenwerke.rs.core.client.reportmanager;

import com.google.gwt.inject.client.AbstractGinModule;
import com.google.inject.Singleton;

import net.datenwerke.gf.client.treedb.UITree;
import net.datenwerke.rs.core.client.reportmanager.helper.reportselector.ReportSelectionField;
import net.datenwerke.rs.core.client.reportmanager.provider.FolderAndReportTreeProvider;
import net.datenwerke.rs.core.client.reportmanager.provider.FolderTreeProvider;
import net.datenwerke.rs.core.client.reportmanager.provider.FullTreeProvider;
import net.datenwerke.rs.core.client.reportmanager.provider.annotations.ReportManagerAdminViewTree;
import net.datenwerke.rs.core.client.reportmanager.provider.annotations.ReportManagerTreeFolders;
import net.datenwerke.rs.core.client.reportmanager.provider.annotations.ReportManagerTreeFoldersAndReports;

/**
 * 
 *
 */
public class ReportManagerUIModule extends AbstractGinModule {
	
	public static final String REPORTMANAGER_FAV_HISTORY_TOKEN = "reportmgr";
	public static final String ADMIN_TREE_MENU_NAME = "reportmanager:admin:tree:menu";
	
	@Override
	protected void configure() {
		/* bind trees */
		bind(UITree.class).annotatedWith(ReportManagerTreeFolders.class).toProvider(FolderTreeProvider.class);
		bind(UITree.class).annotatedWith(ReportManagerTreeFoldersAndReports.class).toProvider(FolderAndReportTreeProvider.class);
		bind(UITree.class).annotatedWith(ReportManagerAdminViewTree.class).toProvider(FullTreeProvider.class).in(Singleton.class);
		
		/* bind service */
		bind(ReportManagerUIService.class).to(ReportManagerUIServiceImpl.class).in(Singleton.class);
		
		/* bind startup class */
		bind(ReportManagerUIStartup.class).asEagerSingleton();
		
		requestStaticInjection(ReportSelectionField.class);
	}



}
