package net.datenwerke.rs.saikupivot.service;

/**
 * 
 * Needed for current pivot implementation
 * 
 *
 */
@Deprecated
public class SaikuDatasourceQueryTransformerMarker {

   private String query;

   public SaikuDatasourceQueryTransformerMarker(String query) {
      super();
      this.query = query;
   }

   public String getQuery() {
      return query;
   }

   public void setQuery(String query) {
      this.query = query;
   }

}
