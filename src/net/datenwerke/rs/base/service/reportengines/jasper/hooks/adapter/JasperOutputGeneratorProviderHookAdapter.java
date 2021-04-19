package net.datenwerke.rs.base.service.reportengines.jasper.hooks.adapter;

import java.util.Collection;
import java.util.HashSet;

import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.rs.base.service.reportengines.jasper.hooks.JasperOutputGeneratorProviderHook;
import net.datenwerke.rs.base.service.reportengines.jasper.output.generator.JasperOutputGenerator;

/**
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.hookservices.HookAdapterProcessor")
public class JasperOutputGeneratorProviderHookAdapter implements JasperOutputGeneratorProviderHook {

	@Override
	public Collection<JasperOutputGenerator> provideGenerators()  {
		return new HashSet();
	}



}
