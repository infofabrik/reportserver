package net.datenwerke.rs.grideditor.service.grideditor.definition.editor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import net.datenwerke.dtoservices.dtogenerator.annotations.ExposeToClient;
import net.datenwerke.dtoservices.dtogenerator.annotations.GenerateDto;

@GenerateDto(dtoPackage = "net.datenwerke.rs.grideditor.client.grideditor.dto", generateDto2Poso = false, createDecorator = true, additionalImports = BigDecimal.class)
public class DecimalSelectionListEditor extends SelectionListEditor {

   @ExposeToClient
   private List<BigDecimal> values = new ArrayList<BigDecimal>();

   @ExposeToClient
   private Map<String, BigDecimal> valueMap;

   public List<BigDecimal> getValues() {
      return values;
   }

   public void setValues(List<BigDecimal> values) {
      this.values = values;
   }

   public void addValue(BigDecimal value) {
      this.values.add(value);
   }

   public Map<String, BigDecimal> getValueMap() {
      return valueMap;
   }

   public void setValueMap(Map<String, BigDecimal> valueMap) {
      this.valueMap = valueMap;
   }

   public void addEntry(String key, BigDecimal value) {
      if (null == valueMap)
         valueMap = new TreeMap<String, BigDecimal>();
      valueMap.put(key, value);
   }

}
