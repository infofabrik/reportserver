package net.datenwerke.rs.jxlsreport.service.jxlsreport.reportengine.hooks.adapter;

import java.util.Collection;
import java.util.HashSet;

import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.rs.jxlsreport.service.jxlsreport.reportengine.hooks.JxlsOutputGeneratorProviderHook;
import net.datenwerke.rs.jxlsreport.service.jxlsreport.reportengine.output.generator.JxlsOutputGenerator;

/**
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.hookservices.HookAdapterProcessor")
public class JxlsOutputGeneratorProviderHookAdapter implements JxlsOutputGeneratorProviderHook {

   @Override
   public Collection<JxlsOutputGenerator> provideGenerators() {
      return new HashSet();
   }

}
