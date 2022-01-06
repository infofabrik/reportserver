package net.datenwerke.rs.search.service.search.hooks;

import net.datenwerke.hookhandler.shared.hookhandler.interfaces.Hook;
import net.datenwerke.treedb.service.treedb.AbstractNode;

/**
 * Allows to filter the search results.
 */
public interface SearchResultAllowerHook extends Hook {

   /**
    * Allows to filter the search results using non-standard checks.
    * 
    * @param node the node found during the search
    * @return true if the node is being allowed to be shown in the result list,
    *         else false
    */
   boolean allow(AbstractNode<? extends AbstractNode<?>> node);
}
