package net.datenwerke.rs.grideditor.service.grideditor.definition.editor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import net.datenwerke.dtoservices.dtogenerator.annotations.ExposeToClient;
import net.datenwerke.dtoservices.dtogenerator.annotations.GenerateDto;

@GenerateDto(dtoPackage = "net.datenwerke.rs.grideditor.client.grideditor.dto", generateDto2Poso = false, createDecorator = true)
public class TextSelectionListEditor extends SelectionListEditor {

   @ExposeToClient
   private List<String> values = new ArrayList<String>();

   @ExposeToClient
   private Map<String, String> valueMap;

   public List<String> getValues() {
      return values;
   }

   public void setValues(List<String> values) {
      this.values = values;
   }

   public void addValue(String value) {
      this.values.add(value);
   }

   public Map<String, String> getValueMap() {
      return valueMap;
   }

   public void setValueMap(Map<String, String> valueMap) {
      this.valueMap = valueMap;
   }

   public void addEntry(String key, String value) {
      if (null == valueMap)
         valueMap = new TreeMap<String, String>();
      valueMap.put(key, value);
   }

}
