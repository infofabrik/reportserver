package net.datenwerke.rs.base.service.reportengines.table.hookers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.rs.base.service.reportengines.table.hooks.TableOutputGeneratorProviderHook;
import net.datenwerke.rs.base.service.reportengines.table.output.generator.CSVOutputGenerator;
import net.datenwerke.rs.base.service.reportengines.table.output.generator.CompactJSONOutputGenerator;
import net.datenwerke.rs.base.service.reportengines.table.output.generator.DataCountOutputGenerator;
import net.datenwerke.rs.base.service.reportengines.table.output.generator.HTMLOutputGenerator;
import net.datenwerke.rs.base.service.reportengines.table.output.generator.JSONOutputGenerator;
import net.datenwerke.rs.base.service.reportengines.table.output.generator.MetaDataOutputGenerator;
import net.datenwerke.rs.base.service.reportengines.table.output.generator.PdfTableOutputGenerator;
import net.datenwerke.rs.base.service.reportengines.table.output.generator.RSTableOutputGenerator;
import net.datenwerke.rs.base.service.reportengines.table.output.generator.RSTableSimpleBeanOutputGenerator;
import net.datenwerke.rs.base.service.reportengines.table.output.generator.SimpleJxlsTemplateOutputGenerator;
import net.datenwerke.rs.base.service.reportengines.table.output.generator.TableOutputGenerator;
import net.datenwerke.rs.base.service.reportengines.table.output.generator.XLSOutputGenerator;
import net.datenwerke.rs.base.service.reportengines.table.output.generator.XLSStreamOutputGenerator;
import net.datenwerke.rs.configservice.service.configservice.ConfigService;

public class BaseTableOutputGeneratorProvider implements TableOutputGeneratorProviderHook {

   private final Provider<RSTableOutputGenerator> table;
   private final Provider<RSTableSimpleBeanOutputGenerator> bean;
   private final Provider<XLSStreamOutputGenerator> xlsStream;
   private final Provider<SimpleJxlsTemplateOutputGenerator> jxls;
   private final Provider<XLSOutputGenerator> xls;
   private final Provider<HTMLOutputGenerator> html;
   private final Provider<CSVOutputGenerator> csv;
   private final Provider<JSONOutputGenerator> json;
   private final Provider<CompactJSONOutputGenerator> jsonc;
   private final Provider<MetaDataOutputGenerator> metadata;
   private final Provider<DataCountOutputGenerator> datacount;
   private final Provider<PdfTableOutputGenerator> pdf;

   private final ConfigService configService;

   @Inject
   public BaseTableOutputGeneratorProvider(Provider<RSTableOutputGenerator> table,
         Provider<RSTableSimpleBeanOutputGenerator> bean, Provider<XLSStreamOutputGenerator> xlsStream,
         Provider<SimpleJxlsTemplateOutputGenerator> jxls, Provider<XLSOutputGenerator> xls,
         Provider<HTMLOutputGenerator> html, Provider<CSVOutputGenerator> csv, Provider<JSONOutputGenerator> json,
         Provider<CompactJSONOutputGenerator> jsonc, Provider<MetaDataOutputGenerator> metadata,
         Provider<DataCountOutputGenerator> datacount, Provider<PdfTableOutputGenerator> pdf,
         ConfigService configService) {
      super();
      this.table = table;
      this.bean = bean;
      this.xlsStream = xlsStream;
      this.jxls = jxls;
      this.xls = xls;
      this.html = html;
      this.csv = csv;
      this.json = json;
      this.jsonc = jsonc;
      this.metadata = metadata;
      this.datacount = datacount;
      this.pdf = pdf;
      this.configService = configService;
   }

   @Override
   public Collection<TableOutputGenerator> provideGenerators() {
      List<TableOutputGenerator> generators = new ArrayList<>();

      generators.add(table.get());
      generators.add(bean.get());
      generators.add(html.get());
      generators.add(csv.get());
      generators.add(json.get());
      generators.add(jsonc.get());
      generators.add(metadata.get());
      generators.add(datacount.get());
      generators.add(pdf.get());
      generators.add(jxls.get());

      if (configService.getConfigFailsafe("exportfilemd/excelexport.cf").getBoolean("xls.stream", true))
         generators.add(xlsStream.get());
      else
         generators.add(xls.get());

      return generators;
   }

}
