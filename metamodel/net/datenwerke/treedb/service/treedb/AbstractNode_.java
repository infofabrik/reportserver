package net.datenwerke.treedb.service.treedb;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(AbstractNode.class)
public abstract class AbstractNode_ {

	public static volatile SingularAttribute<AbstractNode, Date> lastUpdated;
	public static volatile SingularAttribute<AbstractNode, AbstractNode> parent;
	public static volatile ListAttribute<AbstractNode, AbstractNode> children;
	public static volatile SingularAttribute<AbstractNode, Long> flags;
	public static volatile SingularAttribute<AbstractNode, Integer> position;
	public static volatile SingularAttribute<AbstractNode, Long> id;
	public static volatile SingularAttribute<AbstractNode, Date> createdOn;
	public static volatile SingularAttribute<AbstractNode, Long> version;

}

