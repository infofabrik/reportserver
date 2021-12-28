package net.datenwerke.rs.jxlsreport.service.jxlsreport.hookers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.rs.jxlsreport.service.jxlsreport.reportengine.hooks.JxlsOutputGeneratorProviderHook;
import net.datenwerke.rs.jxlsreport.service.jxlsreport.reportengine.output.generator.JxlsHTMLOutputGenerator;
import net.datenwerke.rs.jxlsreport.service.jxlsreport.reportengine.output.generator.JxlsOutputGenerator;
import net.datenwerke.rs.jxlsreport.service.jxlsreport.reportengine.output.generator.JxlsOutputGeneratorImpl;

public class BaseJxlsOutputGeneratorProvider implements JxlsOutputGeneratorProviderHook {

   private final Provider<JxlsOutputGeneratorImpl> jxlsOutputGenerator;
   private final Provider<JxlsHTMLOutputGenerator> jxlsHtmlOutputGenerator;

   @Inject
   public BaseJxlsOutputGeneratorProvider(Provider<JxlsOutputGeneratorImpl> jxlsOutputGenerator,
         Provider<JxlsHTMLOutputGenerator> jxlsHtmlOutputGenerator) {
      super();
      this.jxlsOutputGenerator = jxlsOutputGenerator;
      this.jxlsHtmlOutputGenerator = jxlsHtmlOutputGenerator;
   }

   @Override
   public Collection<JxlsOutputGenerator> provideGenerators() {
      List<JxlsOutputGenerator> generators = new ArrayList<JxlsOutputGenerator>();

      generators.add(jxlsOutputGenerator.get());
      generators.add(jxlsHtmlOutputGenerator.get());

      return generators;
   }

}
