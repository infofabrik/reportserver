package net.datenwerke.rs.base.service.reportengines.jasper.hookers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.rs.base.service.reportengines.jasper.hooks.JasperOutputGeneratorProviderHook;
import net.datenwerke.rs.base.service.reportengines.jasper.output.generator.JasperCsvOutputGenerator;
import net.datenwerke.rs.base.service.reportengines.jasper.output.generator.JasperHTMLOutputGenerator;
import net.datenwerke.rs.base.service.reportengines.jasper.output.generator.JasperOutputGenerator;
import net.datenwerke.rs.base.service.reportengines.jasper.output.generator.JasperPDFOutputGenerator;
import net.datenwerke.rs.base.service.reportengines.jasper.output.generator.JasperPNGOutputGenerator;
import net.datenwerke.rs.base.service.reportengines.jasper.output.generator.JasperRTFOutputGenerator;
import net.datenwerke.rs.base.service.reportengines.jasper.output.generator.JasperXLSOutputGenerator;

public class BaseJasperOutputGeneratorProvider implements JasperOutputGeneratorProviderHook {

   private final Provider<JasperHTMLOutputGenerator> html;
   private final Provider<JasperPDFOutputGenerator> pdf;
   private final Provider<JasperXLSOutputGenerator> xls;
   private final Provider<JasperPNGOutputGenerator> png;
   private final Provider<JasperRTFOutputGenerator> rtf;
   private final Provider<JasperCsvOutputGenerator> csv;

   @Inject
   public BaseJasperOutputGeneratorProvider(Provider<JasperHTMLOutputGenerator> html,
         Provider<JasperPDFOutputGenerator> pdf, Provider<JasperXLSOutputGenerator> xls,
         Provider<JasperPNGOutputGenerator> png, Provider<JasperRTFOutputGenerator> rtf,
         Provider<JasperCsvOutputGenerator> csv) {
      super();
      this.html = html;
      this.pdf = pdf;
      this.xls = xls;
      this.png = png;
      this.rtf = rtf;
      this.csv = csv;
   }

   @Override
   public Collection<JasperOutputGenerator> provideGenerators() {
      List<JasperOutputGenerator> generators = new ArrayList<JasperOutputGenerator>();

      generators.add(html.get());
      generators.add(pdf.get());
      generators.add(xls.get());
      generators.add(png.get());
      generators.add(rtf.get());
      generators.add(csv.get());

      return generators;
   }

}
