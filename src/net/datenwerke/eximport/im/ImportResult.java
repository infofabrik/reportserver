package net.datenwerke.eximport.im;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.google.common.base.MoreObjects;

/**
 * 
 *
 */
public class ImportResult {

   private final String name;
   private final String description;
   private final Date date;
   private final Map<String, Object> importedObjects;
   private final Map<String, ImportResultExtraData> extraData;
   private final Set<String> ignoredReferencesWithNoConfig;

   public ImportResult(String name, String description, Date date, Map<String, Object> importedObjects,
         Map<String, ImportResultExtraData> extraData, Set<String> ignoredReferencesWithNoConfig) {
      super();

      this.name = name;
      this.description = description;
      this.date = date;
      this.importedObjects = importedObjects;
      this.extraData = extraData;
      this.ignoredReferencesWithNoConfig = ignoredReferencesWithNoConfig;
   }
   
   public static class ImportResultExtraData {
      public final Object object;
      public final long timestamp;
      public final ImportStrategy importStrategy;
      public final boolean skip;
      
      public ImportResultExtraData(Object object, long timestamp, ImportStrategy strategy, boolean skip) {
         this.object = object;
         this.timestamp = timestamp;
         this.importStrategy = strategy;
         this.skip = skip;
      }
      
      public static ImportResultExtraData imported(Object object, ImportStrategy strategy) {
         return new ImportResultExtraData(object, System.currentTimeMillis(), strategy, false);
      }
      
      public static ImportResultExtraData skipped(Object object, ImportStrategy strategy) {
         return new ImportResultExtraData(object, System.currentTimeMillis(), strategy, true);
      }
      
      public enum ImportStrategy {
         NONE,
         CREATE,
         MERGE
      }
      
      public String getStrategy() {
         List<String> strategies = new ArrayList<>();
         
         if (skip) strategies.add("Skip");
         if (importStrategy == ImportStrategy.MERGE) strategies.add("Merge");
         if (importStrategy == ImportStrategy.CREATE) strategies.add("Create");
         
         return String.join(",", strategies);
      }
      
      @Override
      public String toString() {
         String prefix = "";
         
         if (importStrategy == ImportStrategy.MERGE) prefix += "M";
         if (importStrategy == ImportStrategy.CREATE) prefix += "+";
         if (skip) prefix += "S";
         
         return prefix + " " + (object != null ? object.toString() : "null");
      }
   }

   public String getName() {
      return name;
   }

   public String getDescription() {
      return description;
   }

   public Date getDate() {
      return date;
   }

   public Map<String, Object> getImportedObjects() {
      return importedObjects;
   }
   
   public Map<String, ImportResultExtraData> getExtraData() {
      return extraData;
   }

   public Set<String> getIgnoredReferencesWithNoConfig() {
      return ignoredReferencesWithNoConfig;
   }
   
   @Override
   public String toString() {
      return MoreObjects.toStringHelper(getClass())
         .add("name", name)
         .add("date", date)
         .add("imported objects", importedObjects.size())
         .toString();
   }

}
