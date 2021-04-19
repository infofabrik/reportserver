package net.datenwerke.security.client.security.dto;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import net.datenwerke.security.client.security.GenericTargetIdentifier;

public class GenericSecurityTargetContainer implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7234701403435513124L;

	
	private Map<String, Collection<RightDto>> availableRightsPerTarget = new HashMap<String, Collection<RightDto>>();
	
	public void addRight(GenericTargetIdentifier targetIdentifier, RightDto right){
		String key = targetIdentifier.getClass().getName();
		if(! availableRightsPerTarget.containsKey(key))
			availableRightsPerTarget.put(key, new HashSet<RightDto>());
		availableRightsPerTarget.get(key).add(right);
	}

	public Collection<RightDto> getRights(Class<? extends GenericTargetIdentifier> targetIdentifier) {
		String key = targetIdentifier.getName();
		if(! availableRightsPerTarget.containsKey(key))
			return new HashSet<RightDto>();
		return availableRightsPerTarget.get(key);
	}
}
