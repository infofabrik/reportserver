package net.datenwerke.rs.base.service.reportengines.table.hooks.adapter;

import java.util.Collection;
import java.util.HashSet;

import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.rs.base.service.reportengines.table.hooks.TableOutputGeneratorProviderHook;
import net.datenwerke.rs.base.service.reportengines.table.output.generator.TableOutputGenerator;

/**
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.hookservices.HookAdapterProcessor")
public class TableOutputGeneratorProviderHookAdapter implements TableOutputGeneratorProviderHook {

	@Override
	public Collection<TableOutputGenerator> provideGenerators()  {
		return new HashSet();
	}



}
