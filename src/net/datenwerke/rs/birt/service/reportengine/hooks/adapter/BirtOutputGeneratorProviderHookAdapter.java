package net.datenwerke.rs.birt.service.reportengine.hooks.adapter;

import java.util.Collection;
import java.util.HashSet;

import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.rs.birt.service.reportengine.hooks.BirtOutputGeneratorProviderHook;
import net.datenwerke.rs.birt.service.reportengine.output.generator.BirtOutputGenerator;

/**
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.hookservices.HookAdapterProcessor")
public class BirtOutputGeneratorProviderHookAdapter implements BirtOutputGeneratorProviderHook {

   @Override
   public Collection<BirtOutputGenerator> provideGenerators() {
      return new HashSet();
   }

}
