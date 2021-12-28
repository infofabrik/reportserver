package net.datenwerke.rs.core.client.reportmanager.provider;

import java.util.Collection;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.gf.client.history.HistoryUiService;
import net.datenwerke.gf.client.managerhelper.tree.ManagerHelperTree;
import net.datenwerke.gf.client.treedb.dnd.UITreeDragDropConfiguration;
import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.core.client.reportmanager.ReportManagerTreeManagerDao;
import net.datenwerke.rs.core.client.reportmanager.ReportManagerUIModule;
import net.datenwerke.rs.core.client.reportmanager.dto.ReportFolderDto;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;
import net.datenwerke.rs.core.client.reportmanager.hooks.ReportTypeConfigHook;
import net.datenwerke.rs.core.client.reportmanager.ui.ReportManagerMainPanel;

public class FullTreeProvider implements Provider<ManagerHelperTree>{

	private final HookHandlerService hookHandler;
	private final FolderAndReportTreeProvider basicTreeProvider;
	private final ReportManagerTreeManagerDao reportManagerTreeHandler;
	private final ReportManagerMainPanel mainPanel;
	private ManagerHelperTree managerHelperTree;
	
	@Inject
	public FullTreeProvider(
			HookHandlerService hookHandler,
			
			FolderAndReportTreeProvider basicTreeProvider,
			ReportManagerTreeManagerDao reportManagerTreeHandler,
			ReportManagerMainPanel mainPanel, 
			HistoryUiService historyService 
		){
		
		this.hookHandler = hookHandler;
		this.basicTreeProvider = basicTreeProvider;
		this.reportManagerTreeHandler = reportManagerTreeHandler;
		this.mainPanel = mainPanel;
		
	}
	
	

	private ManagerHelperTree getTree(){
		if(null == managerHelperTree){
			managerHelperTree = basicTreeProvider.get();
		}
		return managerHelperTree;
	}
	
	public ManagerHelperTree get() {
		/* build tree */
		final ManagerHelperTree tree = getTree();
		tree.getStore().enableDtoAwareness(true);
				
		/* dnd */
		UITreeDragDropConfiguration dndConfig = new UITreeDragDropConfiguration();
		dndConfig.addDropTarget(ReportFolderDto.class);
		
		Collection<ReportTypeConfigHook> configs = hookHandler.getHookers(ReportTypeConfigHook.class);
		for(ReportTypeConfigHook config : configs){
			dndConfig.addDropTarget(ReportDto.class, config.getReportVariantClass());
			dndConfig.denyDropCombination(ReportFolderDto.class, config.getReportVariantClass());
			
			for(ReportTypeConfigHook innerConfig : configs){
				if(config != innerConfig)
					dndConfig.denyDropCombination(config.getReportVariantClass(), innerConfig.getReportVariantClass());
			}
		}
		
		tree.enableDragDrop(reportManagerTreeHandler, dndConfig);
		tree.enableClipboardProvider();
		
		/* set selections */
		tree.setSelectionProvider(mainPanel);
		
		/* double click */
		tree.enableDoubleClickAction();
		
		/* history location */
		tree.setHistoryLocation(ReportManagerUIModule.REPORTMANAGER_FAV_HISTORY_TOKEN);
		
		/* tree menu */
		tree.configureMenuProvider(ReportManagerUIModule.ADMIN_TREE_MENU_NAME);
		
		return tree;
	}
}
