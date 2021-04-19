package net.datenwerke.rs.base.service.reportengines.table.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import net.datenwerke.rs.base.service.reportengines.table.entities.filters.Filter;
import net.datenwerke.rs.base.service.reportengines.table.entities.format.ColumnFormat;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Column.class)
public abstract class Column_ {

	public static volatile SingularAttribute<Column, Boolean> subtotalGroup;
	public static volatile SingularAttribute<Column, NullHandling> nullHandling;
	public static volatile SingularAttribute<Column, Boolean> hidden;
	public static volatile SingularAttribute<Column, ColumnFormat> format;
	public static volatile SingularAttribute<Column, AggregateFunction> aggregateFunction;
	public static volatile SingularAttribute<Column, Integer> type;
	public static volatile SingularAttribute<Column, Long> version;
	public static volatile SingularAttribute<Column, Integer> previewWidth;
	public static volatile SingularAttribute<Column, Filter> filter;
	public static volatile SingularAttribute<Column, String> nullReplacementFormat;
	public static volatile SingularAttribute<Column, String> name;
	public static volatile SingularAttribute<Column, String> alias;
	public static volatile SingularAttribute<Column, Long> id;
	public static volatile SingularAttribute<Column, Integer> position;
	public static volatile SingularAttribute<Column, String> dimension;
	public static volatile SingularAttribute<Column, Order> order;

}

