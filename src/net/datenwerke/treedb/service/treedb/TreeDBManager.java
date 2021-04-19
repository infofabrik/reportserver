package net.datenwerke.treedb.service.treedb;

import java.util.List;

public interface TreeDBManager<A extends AbstractNode<A>> {

	/**
	 * Searches for a node by its id
	 */
	public A getNodeById(long id);
	
	public List<A> getRoots();
	
	public List<A> getVirtualRoots();
	
	public List<A> getAllNodes();
	
	public void persist(A node);
	
	public A merge(A node);
	
	public void remove(A node);
	
	public void forceRemove(A node);
	
	public void move(A node, A newParent);
	
	public void move(A node, A newParent, int index);
	
	public void copy(A node, A newParent, boolean deep);

	boolean allowsMultipleRoots();

	Class<AbstractNode<?>> getBaseType();

	A updateFlags(A node, long flags);
	
	public boolean isFolder(A node); 
}
