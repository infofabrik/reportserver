package net.datenwerke.rs.condition.service.condition.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import net.datenwerke.rs.base.service.reportengines.table.entities.TableReport;
import net.datenwerke.rs.condition.client.condition.Condition;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Condition.class)
public abstract class Condition_ {

	public static volatile SingularAttribute<Condition, TableReport> report;
	public static volatile SingularAttribute<Condition, String> name;
	public static volatile SingularAttribute<Condition, String> description;
	public static volatile SingularAttribute<Condition, Long> id;
	public static volatile SingularAttribute<Condition, Long> version;
	public static volatile SingularAttribute<Condition, String> key;

}

