package net.datenwerke.rs.dsbundle.service.dsbundle.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import net.datenwerke.rs.core.service.datasourcemanager.entities.AbstractDatasourceManagerNode;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(DatabaseBundleEntry.class)
public abstract class DatabaseBundleEntry_ {

	public static volatile SingularAttribute<DatabaseBundleEntry, AbstractDatasourceManagerNode> database;
	public static volatile SingularAttribute<DatabaseBundleEntry, Long> id;
	public static volatile SingularAttribute<DatabaseBundleEntry, Long> version;
	public static volatile SingularAttribute<DatabaseBundleEntry, String> key;

}

