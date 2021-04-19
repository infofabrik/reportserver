package net.datenwerke.rs.base.service.reportengines.table.entities;

import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import net.datenwerke.rs.base.service.reportengines.table.entities.filters.Filter;

@StaticMetamodel(Filter.class)
public abstract class Filter_ {

	public static volatile SingularAttribute<Filter, Long> id;
	public static volatile ListAttribute<Filter, FilterRange> includeRanges;
	public static volatile ListAttribute<Filter, String> excludeValues;
	public static volatile ListAttribute<Filter, FilterRange> excludeRanges;
	public static volatile ListAttribute<Filter, String> includeValues;
	public static volatile SingularAttribute<Filter, Long> version;

}

