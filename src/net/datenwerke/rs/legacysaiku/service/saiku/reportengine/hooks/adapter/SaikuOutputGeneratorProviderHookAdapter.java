package net.datenwerke.rs.legacysaiku.service.saiku.reportengine.hooks.adapter;

import java.util.Collection;
import java.util.HashSet;

import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.rs.legacysaiku.service.saiku.reportengine.hooks.SaikuOutputGeneratorProviderHook;
import net.datenwerke.rs.legacysaiku.service.saiku.reportengine.output.generator.SaikuOutputGenerator;

/**
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.hookservices.HookAdapterProcessor")
public class SaikuOutputGeneratorProviderHookAdapter implements SaikuOutputGeneratorProviderHook {

	@Override
	public Collection<SaikuOutputGenerator> provideGenerators()  {
		return new HashSet();
	}



}
