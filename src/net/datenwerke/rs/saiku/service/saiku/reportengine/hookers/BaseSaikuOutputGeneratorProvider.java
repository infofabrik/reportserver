package net.datenwerke.rs.saiku.service.saiku.reportengine.hookers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.rs.saiku.service.saiku.reportengine.hooks.SaikuOutputGeneratorProviderHook;
import net.datenwerke.rs.saiku.service.saiku.reportengine.output.generator.SaikuCSVOutputGenerator;
import net.datenwerke.rs.saiku.service.saiku.reportengine.output.generator.SaikuChartHTMLOutputGenerator;
import net.datenwerke.rs.saiku.service.saiku.reportengine.output.generator.SaikuHTMLOutputGenerator;
import net.datenwerke.rs.saiku.service.saiku.reportengine.output.generator.SaikuOutputGenerator;
import net.datenwerke.rs.saiku.service.saiku.reportengine.output.generator.SaikuPDFOutputGenerator;
import net.datenwerke.rs.saiku.service.saiku.reportengine.output.generator.SaikuXLSOutputGenerator;

public class BaseSaikuOutputGeneratorProvider implements SaikuOutputGeneratorProviderHook {

   private final Provider<SaikuPDFOutputGenerator> saikuPDFOutputGenerator;
   private final Provider<SaikuCSVOutputGenerator> saikuCSVOutputGenerator;
   private final Provider<SaikuXLSOutputGenerator> saikuXLSOutputGenerator;
   private final Provider<SaikuHTMLOutputGenerator> saikuHTMLOutputGenerator;
   private final Provider<SaikuChartHTMLOutputGenerator> saikuChartHTMLOutputGenerator;

   @Inject
   public BaseSaikuOutputGeneratorProvider(Provider<SaikuPDFOutputGenerator> saikuPDFOutputGenerator,
         Provider<SaikuCSVOutputGenerator> saikuCSVOutputGenerator,
         Provider<SaikuXLSOutputGenerator> saikuXLSOutputGenerator,
         Provider<SaikuHTMLOutputGenerator> saikuHTMLOutputGenerator,
         Provider<SaikuChartHTMLOutputGenerator> saikuChartHTMLOutputGenerator) {
      super();
      this.saikuPDFOutputGenerator = saikuPDFOutputGenerator;
      this.saikuCSVOutputGenerator = saikuCSVOutputGenerator;
      this.saikuXLSOutputGenerator = saikuXLSOutputGenerator;
      this.saikuHTMLOutputGenerator = saikuHTMLOutputGenerator;
      this.saikuChartHTMLOutputGenerator = saikuChartHTMLOutputGenerator;
   }

   @Override
   public Collection<SaikuOutputGenerator> provideGenerators() {
      List<SaikuOutputGenerator> generators = new ArrayList<SaikuOutputGenerator>();

      generators.add(saikuPDFOutputGenerator.get());
      generators.add(saikuCSVOutputGenerator.get());
      generators.add(saikuXLSOutputGenerator.get());
      generators.add(saikuHTMLOutputGenerator.get());
      generators.add(saikuChartHTMLOutputGenerator.get());

      return generators;
   }

}
