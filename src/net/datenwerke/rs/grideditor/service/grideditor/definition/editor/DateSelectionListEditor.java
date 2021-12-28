package net.datenwerke.rs.grideditor.service.grideditor.definition.editor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import net.datenwerke.dtoservices.dtogenerator.annotations.ExposeToClient;
import net.datenwerke.dtoservices.dtogenerator.annotations.GenerateDto;

@GenerateDto(dtoPackage = "net.datenwerke.rs.grideditor.client.grideditor.dto", generateDto2Poso = false, createDecorator = true, additionalImports = Date.class)
public class DateSelectionListEditor extends SelectionListEditor {

   @ExposeToClient
   private List<Date> values = new ArrayList<Date>();

   @ExposeToClient
   private Map<String, Date> valueMap;

   public List<Date> getValues() {
      return values;
   }

   public void setValues(List<Date> values) {
      this.values = values;
   }

   public void addValue(Date value) {
      this.values.add(value);
   }

   public Map<String, Date> getValueMap() {
      return valueMap;
   }

   public void setValueMap(Map<String, Date> valueMap) {
      this.valueMap = valueMap;
   }

   public void addEntry(String key, Date value) {
      if (null == valueMap)
         valueMap = new TreeMap<String, Date>();
      valueMap.put(key, value);
   }

}
