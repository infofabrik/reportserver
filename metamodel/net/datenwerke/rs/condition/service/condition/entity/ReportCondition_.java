package net.datenwerke.rs.condition.service.condition.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import net.datenwerke.rs.base.service.reportengines.table.entities.TableReport;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ReportCondition.class)
public abstract class ReportCondition_ {

	public static volatile SingularAttribute<ReportCondition, TableReport> report;
	public static volatile SingularAttribute<ReportCondition, String> name;
	public static volatile SingularAttribute<ReportCondition, String> description;
	public static volatile SingularAttribute<ReportCondition, Long> id;
	public static volatile SingularAttribute<ReportCondition, Long> version;
	public static volatile SingularAttribute<ReportCondition, String> key;

}

