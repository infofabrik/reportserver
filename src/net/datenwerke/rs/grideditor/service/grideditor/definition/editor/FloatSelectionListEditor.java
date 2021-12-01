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
public class FloatSelectionListEditor extends SelectionListEditor {

	@ExposeToClient
	private List<Float> values = new ArrayList<Float>();
	
	@ExposeToClient
	private Map<String,Float> valueMap;
	
	public List<Float> getValues() {
		return values;
	}

	public void setValues(List<Float> values) {
		this.values = values;
	}
	
	public void addValue(Float value){
		this.values.add(value);
	}

	public Map<String, Float> getValueMap() {
		return valueMap;
	}

	public void setValueMap(Map<String, Float> valueMap) {
		this.valueMap = valueMap;
	}
	
	public void addEntry(String key, Float value) {
		if(null== valueMap)
			valueMap = new TreeMap<String, Float>();
		valueMap.put(key,value);
	}
	
	
	
}
