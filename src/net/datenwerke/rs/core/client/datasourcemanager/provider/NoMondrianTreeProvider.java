package net.datenwerke.rs.core.client.datasourcemanager.provider;

import java.util.ArrayList;
import java.util.List;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.gf.client.managerhelper.tree.ManagerHelperTree;
import net.datenwerke.gf.client.managerhelper.tree.ManagerHelperTreeFactory;
import net.datenwerke.gf.client.treedb.TreeDBUIService;
import net.datenwerke.gf.client.treedb.stores.EnhancedTreeStore;
import net.datenwerke.gxtdto.client.dtomanager.Dto2PosoMapper;
import net.datenwerke.rs.base.client.datasources.dto.posomap.CsvDatasourceDto2PosoMap;
import net.datenwerke.rs.base.client.datasources.dto.posomap.DatabaseDatasourceDto2PosoMap;
import net.datenwerke.rs.base.client.datasources.dto.posomap.FormatBasedDatasourceDefinitionDto2PosoMap;
import net.datenwerke.rs.birt.client.datasources.dto.posomap.BirtReportDatasourceDefinitionDto2PosoMap;
import net.datenwerke.rs.core.client.datasourcemanager.DatasourceTreeLoaderDao;
import net.datenwerke.rs.core.client.datasourcemanager.DatasourceTreeManagerDao;
import net.datenwerke.rs.core.client.datasourcemanager.DatasourceUIModule;
import net.datenwerke.rs.core.client.datasourcemanager.dto.AbstractDatasourceManagerNodeDto;
import net.datenwerke.rs.core.client.datasourcemanager.dto.posomap.DatasourceFolderDto2PosoMap;
import net.datenwerke.rs.dsbundle.client.dsbundle.dto.posomap.DatabaseBundleDto2PosoMap;
import net.datenwerke.rs.incubator.client.scriptdatasource.dto.posomap.ScriptDatasourceDto2PosoMap;
import net.datenwerke.rs.saiku.client.datasource.dto.posomap.MondrianDatasourceDto2PosoMap;

public class NoMondrianTreeProvider implements Provider<ManagerHelperTree>{

	private final TreeDBUIService treeDBUIService;
	private final DatasourceTreeLoaderDao datasourceTreeLoader;
	private final DatasourceTreeManagerDao datasourceTreeManager;
	private final ManagerHelperTreeFactory treeFactory;
	
	@Inject
	public NoMondrianTreeProvider(
		TreeDBUIService treeDBUIService,	
		DatasourceTreeLoaderDao datasourceTreeLoader,
		DatasourceTreeManagerDao datasourceTreeManager,
		ManagerHelperTreeFactory treeFactory
		){
		
		this.treeDBUIService = treeDBUIService;
		this.datasourceTreeLoader = datasourceTreeLoader;
		this.datasourceTreeManager = datasourceTreeManager;
		this.treeFactory = treeFactory;
	}

	public ManagerHelperTree get() {
		/* store */
		List<Dto2PosoMapper> filters = new ArrayList<Dto2PosoMapper>();
		filters.add(new DatasourceFolderDto2PosoMap());
		
		filters.add(new BirtReportDatasourceDefinitionDto2PosoMap());
		filters.add(new DatabaseDatasourceDto2PosoMap());
		filters.add(new DatabaseBundleDto2PosoMap());
		filters.add(new FormatBasedDatasourceDefinitionDto2PosoMap());
		filters.add(new CsvDatasourceDto2PosoMap());
		filters.add(new ScriptDatasourceDto2PosoMap());
		
		EnhancedTreeStore store = treeDBUIService.getUITreeStore(AbstractDatasourceManagerNodeDto.class, datasourceTreeLoader, false, filters);
		
		/* build tree */
		final ManagerHelperTree tree = treeFactory.create(DatasourceUIModule.class, store, datasourceTreeLoader, datasourceTreeManager);
		tree.configureIconProvider();

		return tree;
	}
}
