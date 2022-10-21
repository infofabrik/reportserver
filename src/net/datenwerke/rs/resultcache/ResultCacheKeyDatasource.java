package net.datenwerke.rs.resultcache;

import net.datenwerke.rs.core.service.datasourcemanager.entities.CacheableDatasource;
import net.datenwerke.rs.core.service.datasourcemanager.entities.DatasourceDefinition;
import net.datenwerke.rs.core.service.datasourcemanager.entities.DatasourceDefinitionConfig;

public class ResultCacheKeyDatasource extends ResultCacheKey {

   protected DatasourceDefinition datasource;
   protected DatasourceDefinitionConfig config;
   private boolean prohibitCaching;

   public ResultCacheKeyDatasource(DatasourceDefinition ds) {
      this(ds, null);
   }

   public ResultCacheKeyDatasource(DatasourceDefinition datasource, DatasourceDefinitionConfig config) {
      this.datasource = datasource;
      this.config = config;

      if (datasource instanceof CacheableDatasource && 0 == ((CacheableDatasource) datasource).getDatabaseCache())
         setProhibitCaching(true);
   }

   public DatasourceDefinition getDatasource() {
      return datasource;
   }

   public DatasourceDefinitionConfig getConfig() {
      return config;
   }

   public void setProhibitCaching(boolean prohibit) {
      this.prohibitCaching = prohibit;
   }

   @Override
   public boolean matches(ResultCacheKey target) {
      if (!(target instanceof ResultCacheKeyDatasource))
         return false;
      return null == datasource ? null == ((ResultCacheKeyDatasource) target).getDatasource()
            : datasource.equals(((ResultCacheKeyDatasource) target).getDatasource());
   }

   @Override
   public int hashCode() {
      final int prime = 31;
      int result = 1;
      result = prime * result + ((datasource == null) ? 0 : datasource.hashCode());
      return result;
   }

   @Override
   public boolean equals(Object obj) {
      if (prohibitCaching)
         return false;

      if (this == obj)
         return true;
      if (obj == null)
         return false;
      if (getClass() != obj.getClass())
         return false;
      ResultCacheKeyDatasource other = (ResultCacheKeyDatasource) obj;
      if (datasource == null) {
         if (other.datasource != null)
            return false;
      } else if (!datasource.equals(other.datasource))
         return false;
      /* careful .. custom check */
      return null != config ? config.contentEquals(datasource, other.getConfig()) : null == other.getConfig();
   }

}
