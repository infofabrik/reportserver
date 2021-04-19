package net.datenwerke.rs.utils.entitydiff.config;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.datenwerke.rs.utils.entitydiff.annotations.EntityDiffGuide;

public class GuidedDiffConfig implements EntityDiffConfig {

	private final EntityDiffGuide guide;
	private final String guideName;
	
	private final Map<Class<?>, List<String>> whitelist;
	private final Map<Class<?>, List<String>> blacklist;

	public GuidedDiffConfig(Class<?> type, EntityDiffGuide guide, String guideName) {
		this.guide = guide;
		this.guideName = guideName;
		
		whitelist = new HashMap<Class<?>, List<String>>();
		if(guide.whitelist().length > 0)
			whitelist.put(type, Arrays.asList(guide.whitelist()));
		
		blacklist = new HashMap<Class<?>, List<String>>();
		if(guide.blacklist().length > 0)
			blacklist.put(type, Arrays.asList(guide.blacklist()));
	}

	@Override
	public boolean ignoreId() {
		return guide.ignoreId();
	}

	@Override
	public boolean ignoreVersion() {
		return guide.ignoreVersion();
	}

	@Override
	public Map<Class<?>, List<String>> getFieldsToCompareWhiteList() {
		return this.whitelist;
	}

	@Override
	public Map<Class<?>, List<String>> getFieldsToCompareBlackList() {
		return this.blacklist;
	}

	public String getGuideName() {
		return guideName;
	}

}
