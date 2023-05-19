package net.datenwerke.rs.base.service.reportengines.table.entities.filters;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import net.datenwerke.rs.base.service.reportengines.table.entities.FilterRange;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Filter.class)
public abstract class Filter_ {

	public static volatile ListAttribute<Filter, String> includeValues;
	public static volatile SingularAttribute<Filter, Boolean> caseSensitive;
	public static volatile ListAttribute<Filter, FilterRange> includeRanges;
	public static volatile SingularAttribute<Filter, Long> id;
	public static volatile ListAttribute<Filter, String> excludeValues;
	public static volatile ListAttribute<Filter, FilterRange> excludeRanges;
	public static volatile SingularAttribute<Filter, Long> version;

}

