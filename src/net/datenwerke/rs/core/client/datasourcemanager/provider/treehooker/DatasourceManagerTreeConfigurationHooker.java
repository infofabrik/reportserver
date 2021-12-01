package net.datenwerke.rs.core.client.datasourcemanager.provider.treehooker;

import com.google.gwt.event.dom.client.DoubleClickEvent;
import com.google.inject.Inject;
import com.sencha.gxt.widget.core.client.menu.Menu;
import com.sencha.gxt.widget.core.client.menu.MenuItem;
import com.sencha.gxt.widget.core.client.menu.SeparatorMenuItem;

import net.datenwerke.gf.client.managerhelper.hooks.TreeConfiguratorHook;
import net.datenwerke.gf.client.managerhelper.tree.ManagerHelperTree;
import net.datenwerke.gf.client.treedb.helper.menu.DeleteMenuItem;
import net.datenwerke.gf.client.treedb.helper.menu.DuplicateMenuItem;
import net.datenwerke.gf.client.treedb.helper.menu.InsertMenuItem;
import net.datenwerke.gf.client.treedb.helper.menu.ReloadMenuItem;
import net.datenwerke.gf.client.treedb.helper.menu.TreeDBUIMenuProvider;
import net.datenwerke.gf.client.treedb.icon.IconMapping;
import net.datenwerke.gf.client.treedb.icon.TreeDBUIIconProvider;
import net.datenwerke.gxtdto.client.baseex.widget.menu.DwMenu;
import net.datenwerke.gxtdto.client.baseex.widget.menu.DwMenuItem;
import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.core.client.datasourcemanager.DatasourceTreeManagerDao;
import net.datenwerke.rs.core.client.datasourcemanager.DatasourceUIModule;
import net.datenwerke.rs.core.client.datasourcemanager.dto.DatasourceFolderDto;
import net.datenwerke.rs.core.client.datasourcemanager.hooks.DatasourceDefinitionConfigProviderHook;
import net.datenwerke.rs.core.client.datasourcemanager.locale.DatasourcesMessages;
import net.datenwerke.rs.theme.client.icon.BaseIcon;
import net.datenwerke.treedb.client.treedb.dto.AbstractNodeDto;

public class DatasourceManagerTreeConfigurationHooker implements
		TreeConfiguratorHook {

	private final HookHandlerService hookHandler;
	private final DatasourceTreeManagerDao treeHandler;
	
	@Inject
	public DatasourceManagerTreeConfigurationHooker(
		HookHandlerService hookHandler,
		DatasourceTreeManagerDao treeHandler	
		){
		
		/* store objects */
		this.hookHandler = hookHandler;
		this.treeHandler = treeHandler;
	}
	
	@Override
	public boolean consumes(ManagerHelperTree tree) {
		return DatasourceUIModule.class.equals(tree.getGuarantor());
	}

	@Override
	public void configureTreeIcons(TreeDBUIIconProvider iconProvider) {
		iconProvider.addMappings(
				hookHandler.getHookers(DatasourceDefinitionConfigProviderHook.class)
					.stream()
					.map(config -> new IconMapping(config.getDatasourceClass(),  config.getDatasourceIcon()))
					.toArray(IconMapping[]::new));
	}

	@Override
	public void configureTreeMenu(TreeDBUIMenuProvider menuProvider) {
		/* Folder */
		Menu folderMenu = menuProvider.createOrGetMenuFor(DatasourceFolderDto.class);
		MenuItem insertItem = generateInsertMenu();
		folderMenu.add(insertItem);
		folderMenu.add(new DeleteMenuItem(treeHandler));
		folderMenu.add(new SeparatorMenuItem());
		folderMenu.add(new ReloadMenuItem());
		
		/* DSDDB-specific context menu*/
		for(DatasourceDefinitionConfigProviderHook config : hookHandler.getHookers(DatasourceDefinitionConfigProviderHook.class)){
			Menu dsMenu = menuProvider.createOrGetMenuFor(config.getDatasourceClass());
			insertItem = generateInsertMenu();
			insertItem.disable();
			dsMenu.add(insertItem);
			dsMenu.add(new DuplicateMenuItem(treeHandler));
			dsMenu.add(new DeleteMenuItem(treeHandler));
		}
	}

	private MenuItem generateInsertMenu(){
		final Menu insertMenu = new DwMenu();
		InsertMenuItem folderInsert = new InsertMenuItem(new DatasourceFolderDto(), DatasourcesMessages.INSTANCE.folder(), treeHandler);
		folderInsert.setIcon(BaseIcon.FOLDER_O);
		insertMenu.add(folderInsert);
		
		hookHandler.getHookers(DatasourceDefinitionConfigProviderHook.class).forEach(config -> {
			InsertMenuItem item = new InsertMenuItem(config.instantiateDatasource(), config.getDatasourceName(), treeHandler);
			item.setIcon(config.getDatasourceIcon());
			insertMenu.add(item);
			
			item.setAvailableCallback(config::isAvailable);
		});
		
		MenuItem insertItem = new DwMenuItem(DatasourcesMessages.INSTANCE.insert(), BaseIcon.FILE_O);
		insertItem.setSubMenu(insertMenu);
		
		return insertItem;
	}

	@Override
	public void onDoubleClick(AbstractNodeDto selectedItem, DoubleClickEvent event) {
		// ignore double clicks
	}
	
	@Override
	public void configureFolderTypes(ManagerHelperTree tree) {
		tree.addFolderTypes(DatasourceFolderDto.class);
	}
}
