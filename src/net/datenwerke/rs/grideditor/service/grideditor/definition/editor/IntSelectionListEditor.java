package net.datenwerke.rs.grideditor.service.grideditor.definition.editor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import net.datenwerke.dtoservices.dtogenerator.annotations.ExposeToClient;
import net.datenwerke.dtoservices.dtogenerator.annotations.GenerateDto;

@GenerateDto(
	dtoPackage="net.datenwerke.rs.grideditor.client.grideditor.dto",
	generateDto2Poso=false,
	createDecorator=true
)
public class IntSelectionListEditor extends SelectionListEditor {

	@ExposeToClient
	private List<Integer> values = new ArrayList<Integer>();
	
	@ExposeToClient
	private Map<String,Integer> valueMap;
	
	public List<Integer> getValues() {
		return values;
	}

	public void setValues(List<Integer> values) {
		this.values = values;
	}
	
	public void addValue(Integer value){
		this.values.add(value);
	}

	public Map<String, Integer> getValueMap() {
		return valueMap;
	}

	public void setValueMap(Map<String, Integer> valueMap) {
		this.valueMap = valueMap;
	}
	
	public void addEntry(String key, Integer value) {
		if(null== valueMap)
			valueMap = new TreeMap<String, Integer>();
		valueMap.put(key,value);
	}
	
	
	
}
