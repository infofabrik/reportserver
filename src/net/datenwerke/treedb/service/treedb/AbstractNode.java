package net.datenwerke.treedb.service.treedb;

import static java.util.stream.Collectors.toList;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.EntityManager;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Hibernate;
import org.hibernate.envers.Audited;
import org.hibernate.proxy.HibernateProxy;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.dtoservices.dtogenerator.annotations.AdditionalField;
import net.datenwerke.dtoservices.dtogenerator.annotations.ExposeToClient;
import net.datenwerke.dtoservices.dtogenerator.annotations.GenerateDto;
import net.datenwerke.eximport.ex.annotations.ExportableField;
import net.datenwerke.gf.base.service.annotations.Field;
import net.datenwerke.gxtdto.client.dtomanager.DtoView;
import net.datenwerke.rs.utils.entitycloner.annotation.EntityClonerIgnore;
import net.datenwerke.rs.utils.entitycloner.annotation.TransientID;
import net.datenwerke.rs.utils.entitycloner.annotation.TransientKey;
import net.datenwerke.treedb.service.treedb.annotation.TreeDBAllowedChildren;
import net.datenwerke.treedb.service.treedb.annotation.TreeDBDeniedChildren;
import net.datenwerke.treedb.service.treedb.annotation.TreeDBTree;
import net.datenwerke.treedb.service.treedb.dtogen.AbstractNode2DtoPostProcessor;
import net.datenwerke.treedb.service.treedb.exceptions.UnknownBaseClassException;
import net.datenwerke.treedb.service.treedb.exceptions.UnsupportedChildException;


/**
 * This class provides a generic tree-like structure to entities
 * 
 * 
 *
 */
@MappedSuperclass
@SuppressWarnings("unchecked")
@Table(uniqueConstraints={@UniqueConstraint(columnNames={"parent", "position"})})
@GenerateDto(
	dtoPackage="net.datenwerke.treedb.client.treedb.dto",
	abstractDto=true,
	poso2DtoPostProcessors=AbstractNode2DtoPostProcessor.class,
	createDecorator=true,
	additionalFields = {
		@AdditionalField(name="hasChildren", type=Boolean.class),
		@AdditionalField(name="parentNodeId", type=Long.class),
		@AdditionalField(name="parentNodeType", type=String.class),
		@AdditionalField(name="rootPath", type=ArrayList.class, generics=Long.class),
		@AdditionalField(name="rootName", type=String.class)
	}
)
public abstract class AbstractNode<N extends AbstractNode<N>> implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2412084533298505334L;

	public static final long FLAG_WRITE_PROTECTION = 1;
	public static final long FLAG_CONFIGURATION_PROTECTION = 2;
	
	@Inject
	protected static Provider<EntityManager> entityManagerProvider;
	
	@ExposeToClient
	@Audited
	private long flags = 0;
	
	@ExposeToClient(
		mergeDtoValueBack=false,
		view=DtoView.LIST
	)
	@Audited
	private Date createdOn = new Date();
	
	@ExposeToClient(
		mergeDtoValueBack=false,
		view=DtoView.LIST
	)
	@Audited
	private Date lastUpdated;
	
	@ExportableField(exportField=false)
    @ManyToOne(fetch=FetchType.LAZY)
    @Audited
	private N parent;

	@ExportableField(exportField=false)
    @EntityClonerIgnore
    @OneToMany(cascade=CascadeType.ALL, mappedBy="parent", fetch=FetchType.LAZY)
    // hibernate bug! cannot use order by with table per class or joined strategy.
    // order when selecting children
    //@OrderBy("position")
	private List<N> children = new ArrayList<N>();
	
    @ExposeToClient(mergeDtoValueBack=false, view=DtoView.MINIMAL)
	private int position;
	
	@Version
	private Long version;
	
	@ExposeToClient(id=true)
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	@Field(name="nodeId")
	private Long id;
	
	@ExportableField(exportField=false)
	@Transient @TransientID
	private Long oldTransientId;

	@ExportableField(exportField=false)
	@Transient @TransientKey
	private String oldTransientKey;
	
	@Transient
	private boolean updateLastUpdated = false;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id){
		this.id = id;
	}
	
    public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}
	
	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}
	
	/**
     * Get all of this nodes childnodes sorted by position.
     * 
     */
    public List<N> getChildrenSorted(){
    	/* copy list */
		List<N> copyList = new ArrayList<N>(children);
    	
    	sortNodeArray(copyList);
    	
    	return copyList;
	}
	
    protected void sortNodeArray(List<N> nodeList){
		Collections.sort(nodeList, new Comparator<N>() {
			public int compare(N o1, N o2) {
				if( o1.getPosition() < o2.getPosition() )
					return -1;
				if( o1.getPosition() > o2.getPosition() )
					return 1;
				return 0;
			}
    		
    	});
    }
    
    /**
     * Do never call unless with a very good cause
     * @param children
     */
    public void setChildren(List<N> children){
    	this.children = children;
    }
    
    /**
     * Returns the children unsorted.
     * 
     * @see #getChildrenSorted()
     */
    public List<N> getChildren(){
    	return new ArrayList<N>(children);
    }
    
    public <T extends N> List<T> getChildrenOfType(Class<T> type){
    	List<T> children = new ArrayList<T>();
    	
    	for(N child : getChildren()){
    		if(child instanceof HibernateProxy)
    			child = (N) ((HibernateProxy)child).getHibernateLazyInitializer().getImplementation();
    		if(type.isAssignableFrom(child.getClass()))
    			children.add((T)child);
    	}
    	
    	return children;
    }
    
    /**
     * Retrieves all children nodes of this node, also transitively. I.e. if there are
     * any child folders, also all their children nodes are retrieved.
     * 
     * @return all (transitive) children nodes
     */
    public List<N> getDescendants(){
    	final List<N> desc = new ArrayList<>(children);
    	children.forEach(child -> desc.addAll(child.getDescendants()));
    	return desc;
    }
    
    /**
     * Retrieves all children nodes of this node having this type, also transitively.
     * I.e. if there are any child folders, also all their children nodes are
     * retrieved.
     * 
     * @param type the type of the (transitive) children nodes we are filtering for
     * @return all (transitive) children nodes
     */
    public <T extends AbstractNode<N>> List<T> getDescendants(Class<T> type) {
       return getDescendants()
             .stream()
             .filter(descendant -> type.isInstance(descendant))
             .map(descendant -> type.cast(descendant))
             .collect(toList());
    }
    
    /**
     * 
     */
    public boolean hasChildren(){
		if(!Hibernate.isInitialized(children))
			return hasChildrenLazy();
	
		return ! children.isEmpty();
    }
    
    protected boolean hasChildrenLazy() {
    	EntityManager em = entityManagerProvider.get();
    	CriteriaBuilder cb = em.getCriteriaBuilder();
    	CriteriaQuery<Long> cq = cb.createQuery(Long.class);
    	Root root = cq.from(getBaseType());
    	cq.select(root.get(AbstractNode_.id));
    	cq.where(cb.equal(root.get(AbstractNode_.parent),this));
    	
		return ! em.createQuery(cq).setMaxResults(1).getResultList().isEmpty();
	}
    
    public boolean hasChildrenOfType(Class<? extends AbstractNode<?>> type){
    	for(N child : getChildren()){
    		if(child instanceof HibernateProxy)
    			child = (N) ((HibernateProxy)child).getHibernateLazyInitializer().getImplementation();
    	
    		if(type.isAssignableFrom(child.getClass()))
    			return true;
    	}
    	return false;
    }
    
    public boolean hasDescendantsOfType(Class<? extends AbstractNode<?>> type){
    	if(hasChildrenOfType(type))
    		return true;
    	for(N child : getChildren())
    		return child.hasDescendantsOfType(type);
    	return false;
    }
    
    /**
     * Get this nodes parent node. 
     * Returns null for the root element
     * 
     */
    public N getParent() {
		if(parent instanceof HibernateProxy)
			parent = (N) ((HibernateProxy)parent).getHibernateLazyInitializer().getImplementation();
    	return parent;
	}
    
    /**
     * Should not be called. Always go for (root.addChild())
     */
    public void setParent(AbstractNode<N> abstractNode) {
		this.parent = (N)abstractNode;
	}
    
	/**
	 * Tells whether this entity is a root
	 */
	final public boolean isRoot(){
		return null == parent;
	}

	public boolean isAncestorOf(N node){
		N parent = (N) node.getParent();
		while(null != parent){
			if(equals(parent))
				return true;
			parent = (N) parent.getParent();
		}
		return false;
	}
	
	public List<N> getRootLine(){
		List<N> rootLine = new ArrayList<N>();
		
		N parent = getParent();
		while(null != parent){
			rootLine.add((N) parent);
			parent = (N) parent.getParent();
		}
		
		return rootLine;
	}
	
	/**
     * Add a new childnode to this node. 
     * Childnode will become detached from old parent node if necessary
     * 
     * @param child
     */
	 final public void addChild(N child) {
		 addChild(child, children.size() + 1);
     }
	 
	 final public void addChild(N child, int position) {
		/* make sure child is not denied */
		boolean foundInListOfDeniedChildren = false;
        for(Class<?> potentialChild : getDeniedChildren()){
        	try{
        		potentialChild.cast(child);
    	    	if(foundInListOfDeniedChildren)
    	        	throw new UnsupportedChildException(this.getClass(), child.getClass());
        	} catch(ClassCastException e){}
        }
	    	
        /* make sure child is allowed */
		boolean foundInListOfAllowedChildren = false;
        for(Class<?> potentialChild : getSupportedChildren()){
        	try{
        		potentialChild.cast(child);
        		foundInListOfAllowedChildren = true;
        		break;
        	} catch(ClassCastException e){}
        }
    	if(! foundInListOfAllowedChildren)
        	throw new UnsupportedChildException(this.getClass(), child.getClass());
    	
    	if (child.getParent() != null) 
            child.getParent().removeChild(child);

    	if(position > children.size())
    		children.add(child);
    	else 
    		children.add(position, child);

    	/* update positions - do not sort again*/
    	for(int pos = 0; pos < children.size(); pos++)
    		children.get(pos).setPosition(pos);
    		
        child.setParent(this);
    }	 
	    
    
    
    /**
     * Returns the tree's base class;
     */
    final public Class<?> getBaseType(){
    	return getBaseType(getClass(),getClass());
    }
    
    /**
     * Returns the tree's base class
     * @param clazz
     */
    final private Class<?> getBaseType(Class<?> clazz, Class<?> requestingClazz){
    	if(null == clazz)
    		throw new UnknownBaseClassException(requestingClazz);
    	if(clazz.isAnnotationPresent(TreeDBTree.class))
    		return clazz;
    	return getBaseType(clazz.getSuperclass(), requestingClazz);
    }
    
    final public Class<? extends TreeDBManager> getManagerClass(){
    	Class<? extends TreeDBManager> managerClass = getManagerClass(getClass());
    	if(null == managerClass)
    		throw new IllegalStateException("Could not find manager for " + getClass().getName()); //$NON-NLS-1$
    	return managerClass;
    }
    
    
    private Class<? extends TreeDBManager> getManagerClass(Class<?> clazz) {
    	if(null == clazz)
    		return null;
    	
    	if(clazz.isAnnotationPresent(TreeDBTree.class))
    		return ((TreeDBTree)clazz.getAnnotation(TreeDBTree.class)).manager();
    	
    	return getManagerClass(clazz.getSuperclass());
	}

	/**
     * Returns a list with supported children
     */
    final public Class<?>[] getSupportedChildren(){
    	if(this instanceof HibernateProxy) // $codepro.audit.disable useOfInstanceOfWithThis
    		return getSupportedChildren(Hibernate.getClass(this));
    	return getSupportedChildren(getClass());
    }
    
    /**
     * Returns a list with supported children
     * @param clazz
     */
    private Class<?>[] getSupportedChildren(Class<? extends AbstractNode> clazz) {
    	TreeDBAllowedChildren anno = clazz.getAnnotation(TreeDBAllowedChildren.class);
    	if(null != anno)
			return anno.value();
    	return new Class<?>[]{};
	}
    

	/**
     * Returns a list with denied child types
     */
    final public Class<?>[] getDeniedChildren(){
    	if(this instanceof HibernateProxy) // $codepro.audit.disable useOfInstanceOfWithThis
    		return getDeniedChildren(Hibernate.getClass(this));
    	return getDeniedChildren(getClass());
    }
    
    /**
     * Returns a list with denied child types
     * @param clazz
     */
    private Class<?>[] getDeniedChildren(Class<? extends AbstractNode> clazz) {
    	TreeDBDeniedChildren anno = clazz.getAnnotation(TreeDBDeniedChildren.class);
    	if(null != anno)
			return anno.value();
    	return new Class<?>[]{};
	}

    /**
     * Method does not remove the child from the database
     */
	public N removeChild(N child) {
        children.remove(child);
        child.setParent(null);
        return child;
    }
	
    /**
     * Take care of order and check that parent is set
     */
    @SuppressWarnings("unused")
	@PrePersist @PreUpdate
    final private void prePersist(){
    	/* call template method */
    	doPrePersist();
    	
    	/* set last updated */
    	if(updateLastUpdated){
    		setLastUpdated(new Date());
    		setUpdateLastUpdated(false);
    	}
    }
    
    /**
     * may be overridden
     */
    protected void doPrePersist() {
    	
	}
    
    /**
     * Delete all Children
     */
    @SuppressWarnings("unused")
	@PreRemove
    final private void preRemove(){
    	/* call template method */
    	doPreRemove();
    }

    /**
     * Can be overridden in order to perform additional checks
     */
	protected void doPreRemove() {
	}
    
    
	@Override
    public boolean equals(Object obj) {
    	/* returns true if objects are in the same tree and have the same id */
    	if(! (obj instanceof AbstractNode))
    		return false;
    	
    	/* cast object */
    	AbstractNode<N> node = null;
    	try {
    		node = (AbstractNode<N>) obj;
    	} catch(ClassCastException e){
    		return false;
    	}
    	
    	/* test id */
    	if(null == getId() && null != node.getId())
    		return false;
    	if(null != getId() && ! getId().equals(node.getId()))
    		return false;
    	
    	/* test base tree */
    	if(! getBaseType().equals(node.getBaseType()))
    		return false;
    	
    	return true;
    }
    
    @Override
    public int hashCode() {
    	if(null != getId())
    		return getId().hashCode();
    	
    	return super.hashCode();
    }

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setLastUpdated(Date lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

	public Date getLastUpdated() {
		return lastUpdated;
	}

	public void setOldTransientId(Long oldTransientId) {
		this.oldTransientId = oldTransientId;
	}

	public Long getOldTransientId() {
		return oldTransientId;
	}

	public void setOldTransientKey(String oldTransientKey) {
		this.oldTransientKey = oldTransientKey;
	}
	
	public String getOldTransientKey() {
		return oldTransientKey;
	}
	
	public Long getIdOrOldTransient(){
		return null != id ? id : oldTransientId;
	}

	public void setFlags(long flags) {
		this.flags = flags;
	}

	public long getFlags() {
		return flags;
	}
	
	public void addFlag(long bitfield){
		if (wouldContainFlag(FLAG_CONFIGURATION_PROTECTION, this.flags | bitfield) && !wouldContainFlag(FLAG_WRITE_PROTECTION, this.flags | bitfield)) {
			throw new IllegalArgumentException("Not allowed: Report would be config protected, but not write protected");
		}
		
		this.flags |= bitfield;
	}
	
	public void removeFlag(long bitfield){
		if(hasFlag(bitfield)) {
			if (wouldContainFlag(FLAG_CONFIGURATION_PROTECTION, this.flags ^ bitfield) && !wouldContainFlag(FLAG_WRITE_PROTECTION, this.flags ^ bitfield)) {
				throw new IllegalArgumentException("Not allowed: Report would be config protected, but not write protected");
			}
			
			this.flags ^= bitfield;
		}
	}
	
	public boolean hasFlag(long flag){
		return (this.flags & flag) == flag;
	}
	
	public boolean wouldContainFlag(long flag, long newFlag){
		return (newFlag & flag) == flag;
	}
	
	public void clearFlags() {
		setFlags(0);
	}
	
	public boolean isWriteProtected(){
		return hasFlag(FLAG_WRITE_PROTECTION);
	}
	
	public void setWriteProtection(boolean w){
		if(w)
			addFlag(FLAG_WRITE_PROTECTION);
		else
			removeFlag(FLAG_WRITE_PROTECTION);
	}
	
	public boolean isConfigurationProtected(){
		return hasFlag(FLAG_CONFIGURATION_PROTECTION);
	}
	
	public void setConfigurationProtection(boolean w){
		if(w)
			addFlag(FLAG_CONFIGURATION_PROTECTION);
		else
			removeFlag(FLAG_CONFIGURATION_PROTECTION);
	}
	
	public boolean isFolder(){
		return false;
	}
	
	public void setUpdateLastUpdated(boolean updateLastUpdated) {
		this.updateLastUpdated = updateLastUpdated;
	}
	
	public boolean isUpdateLastUpdated() {
		return updateLastUpdated;
	}
	
	abstract public String getNodeName();

	abstract public String getRootNodeName();
	
	public boolean idsMatch(AbstractNode<?> node){
		if(null == node)
			return false;
		
		if (getClass() != node.getClass())
			return false;
		
		Long id = getIdOrOldTransient();
		Long otherId = node.getIdOrOldTransient();
		
		if(null != id && ! id.equals(otherId))
			return false;
		if(null == id && null != otherId)
			return false;
		
		return true;
	}
}
