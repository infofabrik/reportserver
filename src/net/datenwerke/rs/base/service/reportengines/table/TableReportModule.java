package net.datenwerke.rs.base.service.reportengines.table;

import com.google.inject.Singleton;
import com.google.inject.multibindings.Multibinder;
import com.google.inject.servlet.ServletScopes;

import net.datenwerke.rs.base.service.reportengines.table.columnfilter.FilterService;
import net.datenwerke.rs.base.service.reportengines.table.columnfilter.FilterServiceImpl;
import net.datenwerke.rs.base.service.reportengines.table.dot.PrefilterDotService;
import net.datenwerke.rs.base.service.reportengines.table.dot.PrefilterDotServiceImpl;
import net.datenwerke.rs.base.service.reportengines.table.dot.http.HttpExportService;
import net.datenwerke.rs.base.service.reportengines.table.dot.http.HttpExportServiceImpl;
import net.datenwerke.rs.base.service.reportengines.table.entities.Column;
import net.datenwerke.rs.base.service.reportengines.table.entities.filters.Filter;
import net.datenwerke.rs.base.service.reportengines.table.entities.format.ColumnFormatDate;
import net.datenwerke.rs.base.service.reportengines.table.entities.format.ColumnFormatTemplate;
import net.datenwerke.rs.base.service.reportengines.table.entities.format.enums.CurrencyType;
import net.datenwerke.rs.base.service.reportengines.table.output.generator.ExporterHelper;
import net.datenwerke.rs.base.service.reportengines.table.output.generator.ExporterHelperImpl;
import net.datenwerke.rs.base.service.reportengines.table.output.generator.TableOutputGeneratorImpl;
import net.datenwerke.rs.base.service.reportengines.table.output.metadata.TableMetadataExporter;
import net.datenwerke.rs.base.service.reportengines.table.output.metadata.TablePlainExporter;
import net.datenwerke.rs.base.service.reportengines.table.utils.RSTableToXLS;
import net.datenwerke.rs.base.service.reportengines.table.utils.TableReportColumnMetadataService;
import net.datenwerke.rs.base.service.reportengines.table.utils.TableReportColumnMetadataServiceImpl;
import net.datenwerke.rs.core.service.guice.AbstractReportServerModule;

public class TableReportModule extends AbstractReportServerModule {

   @Override
   protected void configure() {
      /* bind metadata exporter */
      Multibinder<TableMetadataExporter> metadataExporterBinder = Multibinder.newSetBinder(binder(),
            TableMetadataExporter.class);
      metadataExporterBinder.addBinding().to(TablePlainExporter.class);

      /* bind utilities */
      bind(TableReportUtils.class).to(TableReportUtilsImpl.class).in(Singleton.class);
      bind(TableReportColumnMetadataService.class).to(TableReportColumnMetadataServiceImpl.class).in(Singleton.class);

      bind(FilterService.class).to(FilterServiceImpl.class);
      bind(ExporterHelper.class).to(ExporterHelperImpl.class);
      
      /* bind dot services */
      bind(HttpExportService.class).to(HttpExportServiceImpl.class).in(ServletScopes.SESSION);
      bind(PrefilterDotService.class).to(PrefilterDotServiceImpl.class);

      /* bind startup */
      bind(TableReportStartup.class).asEagerSingleton();

      requestStaticInjection(ColumnFormatTemplate.class, ColumnFormatDate.class, TableOutputGeneratorImpl.class,
            RSTableToXLS.class, Filter.class, Column.class, CurrencyType.class);
   }

}
