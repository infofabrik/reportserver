package net.datenwerke.gf.client.treedb;

import java.util.Collection;
import java.util.List;

import net.datenwerke.gf.client.treedb.stores.EnhancedTreeLoader;
import net.datenwerke.gf.client.treedb.stores.EnhancedTreeStore;
import net.datenwerke.gxtdto.client.dtomanager.Dto2PosoMapper;
import net.datenwerke.treedb.client.treedb.TreeDbLoaderDao;
import net.datenwerke.treedb.client.treedb.dto.AbstractNodeDto;

import com.sencha.gxt.data.client.loader.RpcProxy;
import com.sencha.gxt.data.shared.loader.TreeLoader;


/**
 * 
 *
 */
public interface TreeDBUIService {

	public abstract RpcProxy<AbstractNodeDto, List<AbstractNodeDto>> getUITreeProxy(TreeDbLoaderDao treeLoaderDao, Collection<Dto2PosoMapper> wlFilters, final Collection<Dto2PosoMapper> blFilters);
	
	/**
	 * Constructs a basic tree loader needed to create an ext tree.
	 */
	public abstract EnhancedTreeLoader getUITreeLoader(TreeDbLoaderDao treeLoaderDao);
	public abstract EnhancedTreeLoader getUITreeLoader(TreeDbLoaderDao treeLoaderDao, Collection<Dto2PosoMapper> wlFilters);
	public abstract EnhancedTreeLoader getUITreeLoader(TreeDbLoaderDao treeLoaderDao, Collection<Dto2PosoMapper> wlFilters, final Collection<Dto2PosoMapper> blFilters);

	/**
	 * Creates a loader object using the specified proxy.
	 * @param rpcTreeLoader
	 * @param proxy
	 */
	public abstract EnhancedTreeLoader getUITreeLoader(TreeDbLoaderDao rpcTreeLoader, RpcProxy<AbstractNodeDto, List<AbstractNodeDto>> proxy);
	
	/**
	 * Constructs a basic tree store needed to create an ext tree.
	 */
	public EnhancedTreeStore getUITreeStore(Class<?> baseType, TreeDbLoaderDao treeLoaderDao, boolean changeAware);

	public abstract EnhancedTreeStore getUITreeStore(Class<?> baseType, TreeDbLoaderDao rpcTreeLoader, TreeLoader<AbstractNodeDto> loader, boolean changeAware);

	public abstract EnhancedTreeStore getUITreeStore(Class<?> baseType, TreeDbLoaderDao rpcTreeLoader, RpcProxy<AbstractNodeDto, List<AbstractNodeDto>> proxy, boolean changeAware);

	EnhancedTreeStore getUITreeStore(Class<?> baseType, TreeDbLoaderDao rpcTreeLoader, boolean changeAware,
			Collection<Dto2PosoMapper> wlFilters);
	
	EnhancedTreeStore getUITreeStore(Class<?> baseType, TreeDbLoaderDao rpcTreeLoader, boolean changeAware,
			Collection<Dto2PosoMapper> wlFilters, Collection<Dto2PosoMapper> blFilters);

	
}
