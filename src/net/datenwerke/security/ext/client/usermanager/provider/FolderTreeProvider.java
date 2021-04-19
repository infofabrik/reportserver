package net.datenwerke.security.ext.client.usermanager.provider;

import java.util.ArrayList;
import java.util.List;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.gf.client.managerhelper.tree.ManagerHelperTree;
import net.datenwerke.gf.client.managerhelper.tree.ManagerHelperTreeFactory;
import net.datenwerke.gf.client.treedb.TreeDBUIService;
import net.datenwerke.gf.client.treedb.stores.EnhancedTreeStore;
import net.datenwerke.gxtdto.client.dtomanager.Dto2PosoMapper;
import net.datenwerke.security.client.usermanager.dto.AbstractUserManagerNodeDto;
import net.datenwerke.security.client.usermanager.dto.posomap.OrganisationalUnitDto2PosoMap;
import net.datenwerke.security.ext.client.usermanager.UserManagerTreeLoaderDao;
import net.datenwerke.security.ext.client.usermanager.UserManagerTreeManagerDao;
import net.datenwerke.security.ext.client.usermanager.UserManagerUIModule;

/**
 * Provides the user manager tree with all goodies.
 *
 */
public class FolderTreeProvider implements Provider<ManagerHelperTree>{

	private final TreeDBUIService treeDBUIService;
	private final UserManagerTreeLoaderDao userManagerTreeLoader;
	private final UserManagerTreeManagerDao userManagerTreeManager;
	private final ManagerHelperTreeFactory treeFactory;
	
	@Inject
	public FolderTreeProvider(
		TreeDBUIService treeDBUIService,	
		UserManagerTreeLoaderDao userManagerTreeLoader,
		UserManagerTreeManagerDao userManagerTreeManager,
		ManagerHelperTreeFactory treeFactory
		){
		
		/* store objects */
		this.treeDBUIService = treeDBUIService;
		this.userManagerTreeLoader = userManagerTreeLoader;
		this.userManagerTreeManager = userManagerTreeManager;
		this.treeFactory = treeFactory;
	}

	public ManagerHelperTree get() {
		/* store */
		List<Dto2PosoMapper> filters = new ArrayList<Dto2PosoMapper>();
		filters.add(new OrganisationalUnitDto2PosoMap());
		EnhancedTreeStore store = treeDBUIService.getUITreeStore(AbstractUserManagerNodeDto.class, userManagerTreeLoader, false, filters);
		
		/* build tree */
		final ManagerHelperTree tree = treeFactory.create(UserManagerUIModule.class, store, userManagerTreeLoader, userManagerTreeManager);
		tree.configureIconProvider();

		return tree;
	}
}
