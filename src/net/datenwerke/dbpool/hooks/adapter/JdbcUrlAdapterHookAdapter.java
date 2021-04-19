package net.datenwerke.dbpool.hooks.adapter;

import java.lang.String;
import net.datenwerke.dbpool.hooks.JdbcUrlAdapterHook;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;

/**
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.hookservices.HookAdapterProcessor")
public class JdbcUrlAdapterHookAdapter implements JdbcUrlAdapterHook {

	@Override
	public String adaptJdbcUrl(String url)  {
		return url;
	}



}
