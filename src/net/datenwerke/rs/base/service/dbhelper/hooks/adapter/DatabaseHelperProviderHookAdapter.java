package net.datenwerke.rs.base.service.dbhelper.hooks.adapter;

import java.util.Collection;
import java.util.HashSet;

import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.rs.base.service.dbhelper.DatabaseHelper;
import net.datenwerke.rs.base.service.dbhelper.hooks.DatabaseHelperProviderHook;

/**
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.hookservices.HookAdapterProcessor")
public class DatabaseHelperProviderHookAdapter implements DatabaseHelperProviderHook {

	@Override
	public Collection<DatabaseHelper> provideDatabaseHelpers()  {
		return new HashSet();
	}



}
