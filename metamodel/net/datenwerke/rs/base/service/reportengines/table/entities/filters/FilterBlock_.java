package net.datenwerke.rs.base.service.reportengines.table.entities.filters;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(FilterBlock.class)
public abstract class FilterBlock_ {

	public static volatile SingularAttribute<FilterBlock, String> description;
	public static volatile SetAttribute<FilterBlock, FilterSpec> filters;
	public static volatile SingularAttribute<FilterBlock, Long> id;
	public static volatile SingularAttribute<FilterBlock, Long> version;
	public static volatile SetAttribute<FilterBlock, FilterBlock> childBlocks;

}

