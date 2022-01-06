package net.datenwerke.rs.search.service.search.hooks;

import net.datenwerke.hookhandler.shared.hookhandler.interfaces.Hook;
import net.datenwerke.treedb.service.treedb.AbstractNode;

public interface SearchResultAllowHook extends Hook {

   boolean allow(AbstractNode<? extends AbstractNode<?>> node);
}
