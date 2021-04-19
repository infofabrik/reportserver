package net.datenwerke.security.client.security.dto.decorator;

import java.util.HashSet;
import java.util.Set;

import net.datenwerke.security.client.security.dto.AceAccessMapDto;
import net.datenwerke.security.client.security.dto.AceDto;
import net.datenwerke.security.service.security.entities.Ace;

/**
 * Dto for {@link Ace}
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
public class AceDtoDec extends AceDto {


	/**
	 * 
	 */
	private static final long serialVersionUID = -3230134883245155212L;

	public AceDtoDec() {
		super();
	}

	public void addAccessMap(AceAccessMapDto accessMap){
		if(null == getAccessMaps()) 
			setAccessMaps(new HashSet<AceAccessMapDto>()); 
		
		Set<AceAccessMapDto> maps = getAccessMaps();
		
		/* try to find old map for that secury */
		String securee = accessMap.getSecuree();
		if(null == securee)
			throw new IllegalArgumentException("AccessMap needs to have a securee.");
		AceAccessMapDto oldMap = getAccessMap(securee);
		if(null != oldMap)
			maps.remove(oldMap);
		
		maps.add(accessMap);
		
		/* set property */
		setAccessMaps(maps);
	}
	
	public AceAccessMapDto getAccessMap(String secureeId){
		if(null == secureeId)
			return null;
		
		for(AceAccessMapDto map : getAccessMaps())
			if(secureeId.equals(map.getSecuree()))
				return map;
		
		return null;
	}
	
}
