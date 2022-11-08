package net.datenwerke.rs.saiku.service.saiku;

import com.google.inject.AbstractModule;
import com.google.inject.multibindings.Multibinder;

import net.datenwerke.rs.saiku.server.rest.SaikuRestModule;
import net.datenwerke.rs.saiku.service.datasource.MondrianDatasource;
import net.datenwerke.rs.saiku.service.saiku.reportengine.output.metadata.SaikuMetadataExporter;
import net.datenwerke.rs.saiku.service.saiku.reportengine.output.metadata.SaikuPlainMetadataExporter;

public class SaikuModule extends AbstractModule {

   public static final String CONFIG_FILE = "reportengines/reportengines.cf";

   public final static String OUTPUT_FORMAT_CHART_HTML = "SAIKU_CHART_HTML";
   public static final String SAIKU_CHARTS_DEFAULT_COLOR = "\"#1f77b4\", \"#aec7e8\", \"#ff7f0e\", \"#ffbb78\", \"#2ca02c\", \"#98df8a\", \"#d62728\", \"#ff9896\", \"#9467bd\", \"#c5b0d5\", \"#8c564b\", \"#c49c94\", \"#e377c2\", \"#f7b6d2\", \"#7f7f7f\", \"#c7c7c7\", \"#bcbd22\", \"#dbdb8d\", \"#17becf\", \"#9edae5\"";

   @Override
   protected void configure() {
      requestStaticInjection(MondrianDatasource.class);

      /* bind metadata exporter */
      Multibinder<SaikuMetadataExporter> metadataExporterBinder = Multibinder.newSetBinder(binder(),
            SaikuMetadataExporter.class);
      metadataExporterBinder.addBinding().to(SaikuPlainMetadataExporter.class);

      bind(ThinQueryService.class).to(ThinQueryServiceImpl.class); // singleton necessary due to context map
      bind(OlapUtilService.class).to(OlapUtilServiceImpl.class);
      bind(SaikuService.class).to(SaikuServiceImpl.class);

      bind(SaikuStartup.class).asEagerSingleton();

      install(new SaikuRestModule());
   }

}
