package net.datenwerke.gf.client.treedb.stores;

import java.util.Collection;

import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.loader.TreeLoader;

import net.datenwerke.gf.client.treedb.UITree;
import net.datenwerke.gxtdto.client.dtomanager.Dto2PosoMapper;
import net.datenwerke.gxtdto.client.dtomanager.callback.RsAsyncCallback;
import net.datenwerke.gxtdto.client.dtomanager.stores.DtoAwareTreeStore;
import net.datenwerke.gxtdto.client.utils.modelkeyprovider.DtoIdModelKeyProvider;
import net.datenwerke.treedb.client.treedb.TreeDbLoaderDao;
import net.datenwerke.treedb.client.treedb.dto.AbstractNodeDto;
import net.datenwerke.treedb.client.treedb.dto.EntireTreeDTO;

/**
 * 
 *
 */
public class EnhancedTreeStore extends DtoAwareTreeStore<AbstractNodeDto> {

	private final TreeDbLoaderDao treeLoader;

	private Collection<Dto2PosoMapper> entireTreeWhitelistFilters = null;
	private Collection<Dto2PosoMapper> entireTreeBlacklistFilters = null;

	public EnhancedTreeStore(TreeDbLoaderDao treeLoader, TreeLoader<AbstractNodeDto> loader) {
		this(treeLoader, loader, true);
	}
	
	public EnhancedTreeStore(TreeDbLoaderDao treeLoader, TreeLoader<AbstractNodeDto> loader, boolean changeAware) {
		this(new DtoIdModelKeyProvider(), treeLoader, loader, changeAware);
	}

	public EnhancedTreeStore(TreeDbLoaderDao treeLoader, TreeLoader<AbstractNodeDto> loader, boolean changeAware, Collection<Dto2PosoMapper> whitelistFilters) {
		this(treeLoader, loader, changeAware);
		this.entireTreeWhitelistFilters = whitelistFilters;
	}

	public EnhancedTreeStore(TreeDbLoaderDao treeLoader,
			TreeLoader<AbstractNodeDto> loader,
			boolean changeAware,
			Collection<Dto2PosoMapper> wlFilters,
			Collection<Dto2PosoMapper> blFilters) {
		this(treeLoader, loader, changeAware);
		this.entireTreeWhitelistFilters = wlFilters;
		this.entireTreeBlacklistFilters = blFilters;
	}

	public EnhancedTreeStore(ModelKeyProvider<? super AbstractNodeDto> mkp,	TreeDbLoaderDao treeLoader, TreeLoader<AbstractNodeDto> loader, boolean changeAware) {
		super(mkp, loader, changeAware);
		this.treeLoader = treeLoader;
	}

	public void loadEntireTree(boolean useFto){
		loadEntireTree(new EnhancedTreeStoreLoadListener() {
			public void loadComplete() {}
		}, useFto);
	}

	public void loadEntireTree(final EnhancedTreeStoreLoadListener listener, boolean useFto){
		treeLoader.loadAll(entireTreeWhitelistFilters, entireTreeBlacklistFilters, new RsAsyncCallback<EntireTreeDTO>() {
			@Override
			public void onSuccess(EntireTreeDTO result) {
				/* clear store */
				clear();
				fillEntireTree(result);
				listener.loadComplete();
			}
		}, useFto);
	}

	protected void fillEntireTree(EntireTreeDTO tree) {
		for(AbstractNodeDto root : tree.getRoots()){
			add(root);
			fillTreeWithChildren(root, tree);
		}
	}

	private void fillTreeWithChildren(AbstractNodeDto parent, EntireTreeDTO tree) {
		for(AbstractNodeDto child : tree.getChildrenFor(parent)){
			add(parent, child);
			fillTreeWithChildren(child, tree);
		}

	}

	protected UITree getUiTree(){
		return (UITree)treePanel;
	}


}
