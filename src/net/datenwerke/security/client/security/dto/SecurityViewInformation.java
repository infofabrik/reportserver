package net.datenwerke.security.client.security.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.datenwerke.security.client.treedb.dto.SecuredAbstractNodeDto;

/**
 * Contains all the information needed to display the security view for an "ACE container".
 *
 */
public class SecurityViewInformation implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4590378711038525589L;

	private List<AceDto> aces = new ArrayList<AceDto>();
	private List<AceDto> inheritedAces = new ArrayList<AceDto>();
	private Map<AceDto,SecuredAbstractNodeDto> referencesToInheritedNodes = new HashMap<AceDto, SecuredAbstractNodeDto>();
	
	private Collection<SecureeDto> availableSecurees = new ArrayList<SecureeDto>();
	
	public void setAces(List<AceDto> aces) {
		this.aces = aces;
	}

	public List<AceDto> getAces() {
		return aces;
	}

	public void setAvailableSecurees(Collection<SecureeDto> availableSecurees) {
		this.availableSecurees = availableSecurees;
	}

	public Collection<SecureeDto> getAvailableSecurees() {
		return availableSecurees;
	}

	public void setInheritedAces(List<AceDto> inheritedAces) {
		this.inheritedAces = inheritedAces;
	}

	public List<AceDto> getInheritedAces() {
		return inheritedAces;
	}

	public void setReferencesToInheritedNodes(Map<AceDto,SecuredAbstractNodeDto> referencesToInheritedNodes) {
		this.referencesToInheritedNodes = referencesToInheritedNodes;
	}

	public Map<AceDto,SecuredAbstractNodeDto> getReferencesToInheritedNodes() {
		return referencesToInheritedNodes;
	}

}
