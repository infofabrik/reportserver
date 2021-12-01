package net.datenwerke.rs.base.service.dbhelper.hooks.adapter;

import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.rs.base.service.dbhelper.hooks.FilterReplacementProviderHook;
import net.datenwerke.rs.base.service.dbhelper.querybuilder.ManagedQuery;
import net.datenwerke.rs.base.service.dbhelper.querybuilder.QueryBuilder;
import net.datenwerke.rs.base.service.reportengines.table.entities.Column;
import net.datenwerke.rs.utils.juel.SimpleJuel;

/**
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.hookservices.HookAdapterProcessor")
public class FilterReplacementProviderHookAdapter implements FilterReplacementProviderHook {

	@Override
	public void enhance(SimpleJuel juel, Column column, QueryBuilder queryBuilder, ManagedQuery query)  {
	}



}
