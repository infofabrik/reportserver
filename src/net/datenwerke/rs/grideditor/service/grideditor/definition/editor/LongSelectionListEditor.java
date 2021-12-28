package net.datenwerke.rs.grideditor.service.grideditor.definition.editor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import net.datenwerke.dtoservices.dtogenerator.annotations.ExposeToClient;
import net.datenwerke.dtoservices.dtogenerator.annotations.GenerateDto;

@GenerateDto(dtoPackage = "net.datenwerke.rs.grideditor.client.grideditor.dto", generateDto2Poso = false, createDecorator = true)
public class LongSelectionListEditor extends SelectionListEditor {

   @ExposeToClient
   private List<Long> values = new ArrayList<Long>();

   @ExposeToClient
   private Map<String, Long> valueMap;

   public List<Long> getValues() {
      return values;
   }

   public void setValues(List<Long> values) {
      this.values = values;
   }

   public void addValue(Long value) {
      this.values.add(value);
   }

   public Map<String, Long> getValueMap() {
      return valueMap;
   }

   public void setValueMap(Map<String, Long> valueMap) {
      this.valueMap = valueMap;
   }

   public void addEntry(String key, Long value) {
      if (null == valueMap)
         valueMap = new TreeMap<String, Long>();
      valueMap.put(key, value);
   }

}
