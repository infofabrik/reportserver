package net.datenwerke.eximport.ex;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ExportConfig {

   private List<ExporterSpecificExportConfig> specificExporterConfigs = new ArrayList<ExporterSpecificExportConfig>();

   private Set<ExportItemConfig<?>> itemConfigs = new HashSet<ExportItemConfig<?>>();
   private Date date;
   private String name = "";
   private String description = "";
   private Object node;
   private boolean includePathToRoot;

   public ExportConfig() {
      date = Calendar.getInstance().getTime();
   }

   public void setItemConfigs(Set<ExportItemConfig<?>> itemConfigs) {
      this.itemConfigs = itemConfigs;
   }

   public boolean addItemConfig(ExportItemConfig<?>... configs) {
      return itemConfigs.addAll(Arrays.asList(configs));
   }

   public boolean addItemConfig(Collection<ExportItemConfig<?>> configs) {
      return itemConfigs.addAll(configs);
   }

   public boolean containsItemConfig(ExportItemConfig<?> config) {
      return itemConfigs.contains(config);
   }

   public Set<ExportItemConfig<?>> getItemConfigs() {
      return itemConfigs;
   }

   public List<ExporterSpecificExportConfig> getSpecificExporterConfigs() {
      return specificExporterConfigs;
   }

   public void setSpecificExporterConfigs(List<ExporterSpecificExportConfig> specificExporterConfigs) {
      this.specificExporterConfigs = specificExporterConfigs;
   }

   public void addSpecificExporterConfigs(ExporterSpecificExportConfig... specificConfig) {
      this.specificExporterConfigs.addAll(Arrays.asList(specificConfig));
   }

   public Date getDate() {
      return date;
   }

   public void setDate(Date date) {
      this.date = date;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public String getDescription() {
      return description;
   }

   public void setDescription(String description) {
      this.description = description;
   }

   public Object getNode() {
      return node;
   }

   public void setNode(Object node) {
      this.node = node;
   }

   public boolean isIncludePathToRoot() {
      return includePathToRoot;
   }

   public void setIncludePathToRoot(boolean includePathToRoot) {
      this.includePathToRoot = includePathToRoot;
   }

   
}
