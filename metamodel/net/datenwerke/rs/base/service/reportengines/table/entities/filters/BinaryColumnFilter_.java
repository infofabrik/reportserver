package net.datenwerke.rs.base.service.reportengines.table.entities.filters;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import net.datenwerke.rs.base.service.reportengines.table.entities.Column;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(BinaryColumnFilter.class)
public abstract class BinaryColumnFilter_ extends net.datenwerke.rs.base.service.reportengines.table.entities.filters.FilterSpec_ {

	public static volatile SingularAttribute<BinaryColumnFilter, Column> columnA;
	public static volatile SingularAttribute<BinaryColumnFilter, Column> columnB;
	public static volatile SingularAttribute<BinaryColumnFilter, BinaryOperator> operator;

}

