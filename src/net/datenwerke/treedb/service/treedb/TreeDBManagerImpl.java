package net.datenwerke.treedb.service.treedb;

import java.util.List;

import org.hibernate.proxy.HibernateProxy;

import com.google.inject.Inject;

import net.datenwerke.rs.utils.entitycloner.EntityClonerService;
import net.datenwerke.rs.utils.reflection.ReflectionService;
import net.datenwerke.treedb.service.treedb.annotation.TreeDBTree;

public abstract class TreeDBManagerImpl<A extends AbstractNode<A>> implements TreeDBManager<A> {

	@Inject
	protected EntityClonerService entityCloner; 
	
	@Inject
	protected TreeDBService treeDbService;
	
	@Inject
	protected ReflectionService reflectionService;

	
	@Override
	public List<A> getVirtualRoots() {
		return getRoots();
	}
	
	@Override
	public boolean isFolder(A node) {
		return node.isFolder();
	}
	
	@Override
	public  void copy(A source, A target, boolean deep) {
		beforeNodeCopy(source, target);
		
		A copiedNode = copy(source, target);
		
		if(deep)
			for(A child : source.getChildren())
				copy(child, copiedNode, true);
	}
	
	protected A copy(A source, A target) {
		beforeNodeCopy(source, target);
		
		A cloned = cloneNode(source);
		target.addChild(cloned);
		persist(cloned);
		merge(target);
		
		return cloned;
	}
	
	protected void beforeNodeCopy(A source, A target) {
	}

	protected A cloneNode(A node) {
		return entityCloner.cloneEntity(node);
	}
	
	@Override
	public void move(A node, A newParent) {
		if(null == node || null == newParent)
			throw new IllegalArgumentException();
		if(node.equals(newParent))
			throw new IllegalArgumentException("cannot move node onto itself");
		
		for(A rootLine : newParent.getRootLine())
			if(rootLine.equals(node))
				throw new IllegalArgumentException("node is in new parent's rootline");
		
		A oldParent = node.getParent();
		beforeNodeMoveToParent(node, newParent, oldParent);
		
		newParent.addChild(node);
		
		nodeMovedToParent(node, newParent, oldParent);
	}
	
	@Override
	public void move(A node, A newParent, int index) {
		if(null == node || null == newParent)
			throw new IllegalArgumentException();
		if(node.equals(newParent))
			throw new IllegalArgumentException("cannot move node onto itself");
		
		for(A rootLine : newParent.getRootLine())
			if(rootLine.equals(node))
				throw new IllegalArgumentException("node is in new parent's rootline");
		
		A oldParent = node.getParent();
		beforeNodeMoveToParent(node, newParent, oldParent);
		
		newParent.addChild(node, index);
		
		nodeMovedToParent(node, newParent, oldParent);
	}
	
	protected void beforeNodeMoveToParent(A node, A newParent, A oldParent) {
	}
	
	protected void nodeMovedToParent(A node, A newParent, A oldParent) {
	}

	
	@Override
	public void persist(A node) {
		node.setUpdateLastUpdated(true);
		treeDbService.persist(node);
	}

	@Override
	public A merge(A node) {
		/* deproxy if necessary - RS-2016 Hibernate does not seem able to cope with its own proxies */
		if(node instanceof HibernateProxy)
			node = (A) ((HibernateProxy)node).getHibernateLazyInitializer().getImplementation();
		
		node.setUpdateLastUpdated(true);
		node = (A) treeDbService.merge(node);
		return node;
	}
	
	@Override
	public A updateFlags(A node, long flags){
		return treeDbService.updateFlags(node, flags);
	}
	
	@Override
	public void remove(A node) {
		doRemove(node, false);
	}
	
	
	public void forceRemove(A node) {
		doRemove(node, true);
	};
	
	protected void doRemove(A node, boolean force) {
		for(A child : node.getChildren()){
			if(force)
				forceRemove(child);
			else
				remove(child);
		}
		
		treeDbService.remove(node);
	}
	
	
	@Override
	public boolean allowsMultipleRoots(){
		Class<AbstractNode<?>> clazz = getBaseType();
		return clazz.getAnnotation(TreeDBTree.class).multipleRoots();
	}
	
	@Override
	public Class<AbstractNode<?>> getBaseType(){
		Class<?> clazz = reflectionService.getGenericType(getClass());
		
    	while(null != clazz){
    		if(clazz.isAnnotationPresent(TreeDBTree.class))
    			return (Class<AbstractNode<?>>) clazz;
    		clazz = clazz.getSuperclass();
    	}
    	return null;
    }

}
