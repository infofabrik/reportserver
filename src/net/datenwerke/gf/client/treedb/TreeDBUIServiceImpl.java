package net.datenwerke.gf.client.treedb;

import java.util.Collection;
import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Singleton;
import com.sencha.gxt.data.client.loader.RpcProxy;
import com.sencha.gxt.data.shared.loader.TreeLoader;

import net.datenwerke.gf.client.treedb.stores.EnhancedTreeLoader;
import net.datenwerke.gf.client.treedb.stores.EnhancedTreeStore;
import net.datenwerke.gxtdto.client.dtomanager.Dto2PosoMapper;
import net.datenwerke.treedb.client.treedb.TreeDbLoaderDao;
import net.datenwerke.treedb.client.treedb.dto.AbstractNodeDto;

/**
 * 
 *
 */
@Singleton
public class TreeDBUIServiceImpl implements TreeDBUIService {

	@Override
	public RpcProxy<AbstractNodeDto, List<AbstractNodeDto>> getUITreeProxy(
			final TreeDbLoaderDao rpcTreeLoader, final Collection<Dto2PosoMapper> wlFilters, final Collection<Dto2PosoMapper> blFilters) {

		RpcProxy<AbstractNodeDto, List<AbstractNodeDto>> proxy = new RpcProxy<AbstractNodeDto, List<AbstractNodeDto>>() {
			@Override
			public void load(AbstractNodeDto loadConfig,
					AsyncCallback<List<AbstractNodeDto>> callback) {
				if(null == loadConfig)
					rpcTreeLoader.getRoot(callback);
				else if(loadConfig instanceof AbstractNodeDto)
					rpcTreeLoader.getChildren((AbstractNodeDto) loadConfig, true, wlFilters, blFilters, callback);
			}
		};
		
		return proxy;
	}
	
	@Override
	public EnhancedTreeLoader getUITreeLoader(TreeDbLoaderDao rpcTreeLoader) {
		return getUITreeLoader(rpcTreeLoader, null, null);
	}
	
	@Override
	public EnhancedTreeLoader getUITreeLoader(TreeDbLoaderDao rpcTreeLoader, Collection<Dto2PosoMapper> wlFilters) {
		return getUITreeLoader(rpcTreeLoader, wlFilters, null);
	}
	
	@Override
	public EnhancedTreeLoader getUITreeLoader(TreeDbLoaderDao rpcTreeLoader, Collection<Dto2PosoMapper> wlFilters, Collection<Dto2PosoMapper> blFilters) {
		RpcProxy<AbstractNodeDto, List<AbstractNodeDto>> proxy = getUITreeProxy(rpcTreeLoader, wlFilters, blFilters);
		
		EnhancedTreeLoader loader = new EnhancedTreeLoader(proxy);
		
		return loader;
	}
	
	@Override
	public EnhancedTreeLoader getUITreeLoader(TreeDbLoaderDao rpcTreeLoader, RpcProxy<AbstractNodeDto, List<AbstractNodeDto>> proxy) {
		EnhancedTreeLoader loader = new EnhancedTreeLoader(proxy);
		return loader;
	}

	@Override
	public EnhancedTreeStore getUITreeStore(Class<?> baseType, TreeDbLoaderDao rpcTreeLoader, boolean changeAware) {
		return getUITreeStore(baseType, rpcTreeLoader, changeAware, null, null);
	}
	
	@Override
	public EnhancedTreeStore getUITreeStore(Class<?> baseType, TreeDbLoaderDao rpcTreeLoader, boolean changeAware, Collection<Dto2PosoMapper> wlFilters) {
		return getUITreeStore(baseType, rpcTreeLoader, changeAware, wlFilters, null);
	}
	
	@Override
	public EnhancedTreeStore getUITreeStore(Class<?> baseType, TreeDbLoaderDao rpcTreeLoader, boolean changeAware, Collection<Dto2PosoMapper> wlFilters, Collection<Dto2PosoMapper> blFilters) {
		TreeLoader<AbstractNodeDto> loader = getUITreeLoader(rpcTreeLoader, wlFilters, blFilters);
		
		EnhancedTreeStore store = new EnhancedTreeStore(rpcTreeLoader, loader, changeAware, wlFilters, blFilters);
		store.addTypeFilter(baseType);
		configureStore(store);
		
		return store;
	}
	
	@Override
	public EnhancedTreeStore getUITreeStore(Class<?> baseType, TreeDbLoaderDao rpcTreeLoader, RpcProxy<AbstractNodeDto, List<AbstractNodeDto>> proxy, boolean changeAware) {
		TreeLoader<AbstractNodeDto> loader = getUITreeLoader(rpcTreeLoader, proxy);
		
		EnhancedTreeStore store = new EnhancedTreeStore(rpcTreeLoader, loader, changeAware);
		store.addTypeFilter(baseType);
		configureStore(store);
		
		return store;
	}
	
	@Override
	public EnhancedTreeStore getUITreeStore(Class<?> baseType, TreeDbLoaderDao rpcTreeLoader, TreeLoader<AbstractNodeDto> loader, boolean changeAware) {
		EnhancedTreeStore store = new EnhancedTreeStore(rpcTreeLoader, loader, changeAware);
		store.addTypeFilter(baseType);
		configureStore(store);
		
		return store;
	}

	private void configureStore(EnhancedTreeStore store) {
		store.setAlphabeticSorter();
	}


}
