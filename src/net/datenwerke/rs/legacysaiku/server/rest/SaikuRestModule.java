package net.datenwerke.rs.legacysaiku.server.rest;

import org.glassfish.jersey.server.ResourceConfig;

import com.google.inject.AbstractModule;

import net.datenwerke.rs.legacysaiku.server.rest.resources.BasicRepositoryResource;
import net.datenwerke.rs.legacysaiku.server.rest.resources.BasicRepositoryResource2;
import net.datenwerke.rs.legacysaiku.server.rest.resources.BasicTagRepositoryResource;
import net.datenwerke.rs.legacysaiku.server.rest.resources.DataSourceResource;
import net.datenwerke.rs.legacysaiku.server.rest.resources.ExporterResource;
import net.datenwerke.rs.legacysaiku.server.rest.resources.OlapDiscoverResource;
import net.datenwerke.rs.legacysaiku.server.rest.resources.QueryResource;
import net.datenwerke.rs.legacysaiku.server.rest.resources.SaikuI18nResource;
import net.datenwerke.rs.legacysaiku.server.rest.resources.SessionResource;
import net.datenwerke.rs.legacysaiku.server.rest.resources.StatisticsResource;

public class SaikuRestModule extends AbstractModule {

   @Override
   protected void configure() {
      /* register rest resources */
//		ResourceConfig rc = new PackagesResourceConfig(this.getClass().getPackage().getName());

      ResourceConfig cnrc = new ResourceConfig(BasicRepositoryResource.class,
            BasicRepositoryResource2.class, BasicTagRepositoryResource.class, DataSourceResource.class,
            ExporterResource.class, OlapDiscoverResource.class, QueryResource.class, SessionResource.class,
            StatisticsResource.class, SaikuI18nResource.class);

      for (Class<?> resource : cnrc.getClasses()) {
         bind(resource);
      }
   }

}
