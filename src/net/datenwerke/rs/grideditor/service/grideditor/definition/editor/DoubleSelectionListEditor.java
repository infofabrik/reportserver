package net.datenwerke.rs.grideditor.service.grideditor.definition.editor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import net.datenwerke.dtoservices.dtogenerator.annotations.ExposeToClient;
import net.datenwerke.dtoservices.dtogenerator.annotations.GenerateDto;

@GenerateDto(dtoPackage = "net.datenwerke.rs.grideditor.client.grideditor.dto", generateDto2Poso = false, createDecorator = true)
public class DoubleSelectionListEditor extends SelectionListEditor {

   @ExposeToClient
   private List<Double> values = new ArrayList<Double>();

   @ExposeToClient
   private Map<String, Double> valueMap;

   public List<Double> getValues() {
      return values;
   }

   public void setValues(List<Double> values) {
      this.values = values;
   }

   public void addValue(Double value) {
      this.values.add(value);
   }

   public Map<String, Double> getValueMap() {
      return valueMap;
   }

   public void setValueMap(Map<String, Double> valueMap) {
      this.valueMap = valueMap;
   }

   public void addEntry(String key, Double value) {
      if (null == valueMap)
         valueMap = new TreeMap<String, Double>();
      valueMap.put(key, value);
   }

}
