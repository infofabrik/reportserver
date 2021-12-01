package net.datenwerke.rs.utils.entitydiff.config;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StrictDiffConfig implements EntityDiffConfig {

	private Map<Class<?>, List<String>> fieldsToCompareWhiteList = new HashMap<Class<?>, List<String>>();
	private Map<Class<?>, List<String>> fieldsToCompareBlackList = new HashMap<Class<?>, List<String>>();
	
	@Override
	public boolean ignoreId() {
		return false;
	}

	@Override
	public boolean ignoreVersion() {
		return false;
	}

	@Override
	public Map<Class<?>, List<String>> getFieldsToCompareWhiteList() {
		return fieldsToCompareWhiteList;
	}
	
	public void setFieldsToCompareWhite(Map<Class<?>, List<String>> fieldsToCompare) {
		this.fieldsToCompareWhiteList = fieldsToCompare;
	}

	public void addFieldWhiteList(Class<?> type, List<String> fieldList){
		fieldsToCompareWhiteList.put(type, fieldList);
	}

	public Map<Class<?>, List<String>> getFieldsToCompareBlackList() {
		return fieldsToCompareBlackList;
	}

	public void setFieldsToCompareBlackList(
			Map<Class<?>, List<String>> fieldsToCompareBlackList) {
		this.fieldsToCompareBlackList = fieldsToCompareBlackList;
	}
	
	public void addFieldBlackList(Class<?> type, List<String> fieldList){
		fieldsToCompareBlackList.put(type, fieldList);
	}

	


}
