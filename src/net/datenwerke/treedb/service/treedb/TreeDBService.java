package net.datenwerke.treedb.service.treedb;

/**
 * 
 *
 */
public interface TreeDBService {

   /**
    * Merges the given node with the representation in the database and returns the
    * merged node.
    * 
    * @param <N>  The type of the node
    * @param node The node holding the new data
    * @return The merged node
    */
   public <N extends AbstractNode<N>> N merge(N node);

   /**
    * Saves the given node to the database
    * 
    * @param <N>  The type of the node
    * @param node The node itself
    */
   public <N extends AbstractNode<N>> void persist(N node);

   /**
    * Removes the given node from the database
    * 
    * @param <N>  The type of the node
    * @param node The node to be removed
    */
   public <N extends AbstractNode<N>> void remove(N node);

   public <N extends AbstractNode<N>> N updateFlags(N node, long flags);

   public <A extends AbstractNode<?>> Class<? extends TreeDBManager<? extends A>> getManagerClassForNode(
         Class<A> nodeType);
   
}
