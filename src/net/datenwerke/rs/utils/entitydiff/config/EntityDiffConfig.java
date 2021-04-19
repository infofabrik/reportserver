package net.datenwerke.rs.utils.entitydiff.config;

import java.util.List;
import java.util.Map;

public interface EntityDiffConfig {

	boolean ignoreId();
	
	boolean ignoreVersion();
	
	Map<Class<?>, List<String>> getFieldsToCompareWhiteList();
	
	Map<Class<?>, List<String>> getFieldsToCompareBlackList();
}
