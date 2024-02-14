package net.datenwerke.rs.base.service.datasources.definitions;

import java.io.IOException;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.io.IOUtils;
import org.hibernate.envers.Audited;

import net.datenwerke.dtoservices.dtogenerator.annotations.ExposeToClient;
import net.datenwerke.dtoservices.dtogenerator.annotations.GenerateDto;
import net.datenwerke.gf.base.service.annotations.Indexed;
import net.datenwerke.rs.core.service.datasourcemanager.entities.CacheableDatasource;
import net.datenwerke.rs.core.service.datasourcemanager.entities.DatasourceDefinitionConfig;
import net.datenwerke.rs.utils.entitymerge.service.annotations.EntityMergeField;

@Entity
@Table(name = "CSV_DATASOURCE")
@Audited
@GenerateDto(dtoPackage = "net.datenwerke.rs.base.client.datasources.dto")
@Indexed
public class CsvDatasource extends FormatBasedDatasourceDefinition implements CacheableDatasource {

   /**
    * 
    */
   private static final long serialVersionUID = -3362289890273571147L;

   @ExposeToClient
   @EntityMergeField
   private String separator = ";";

   @ExposeToClient
   @EntityMergeField
   private String quote = "\"";

   @ExposeToClient
   @EntityMergeField
   private int databaseCache = -1;

   @Override
   @Transient
   public DatasourceDefinitionConfig createConfigObject() {
      return new DatasourceDefinitionConfig();
   }

   public void setQuote(String quote) {
      this.quote = quote;
   }

   public String getQuote() {
      return null == quote || "".equals(quote.trim()) ? "\"" : quote.trim();
   }

   public void setSeparator(String separator) {
      this.separator = separator;
   }

   public String getSeparator() {
      return null == separator || "".equals(separator.trim()) ? ";" : separator.trim();
   }

   public String getDataAsString(FormatBasedDatasourceConfig dsConfig) throws IOException {
      return IOUtils.toString(getDataStream(dsConfig));
   }

   public int getDatabaseCache() {
      return databaseCache;
   }

   public void setDatabaseCache(int databaseCache) {
      this.databaseCache = databaseCache;
   }

}
