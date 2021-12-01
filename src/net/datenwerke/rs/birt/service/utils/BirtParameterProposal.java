package net.datenwerke.rs.birt.service.utils;

import java.io.Serializable;

import net.datenwerke.dtoservices.dtogenerator.annotations.ExposeToClient;
import net.datenwerke.dtoservices.dtogenerator.annotations.GenerateDto;
import net.datenwerke.rs.core.client.parameters.dto.ParameterProposalDto;
import net.datenwerke.rs.core.service.parameters.entities.ParameterDefinition;
import net.datenwerke.rs.utils.entitycloner.annotation.EnclosedEntity;

@GenerateDto(
		dtoPackage="net.datenwerke.rs.birt.client.utils.dto",
		proxyableDto=false,
		dtoImplementInterfaces=ParameterProposalDto.class
	)
public class BirtParameterProposal implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4302541126311802177L;

	@ExposeToClient(id=true)
	private String key;
	
	@ExposeToClient
	private String name;
	
	@ExposeToClient
	private String type;

	@EnclosedEntity
	@ExposeToClient
	private ParameterDefinition parameterProposal;
	
	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	public ParameterDefinition getParameterProposal() {
		return parameterProposal;
	}
	
	public void setParameterProposal(ParameterDefinition parameterProposal) {
		this.parameterProposal = parameterProposal;
	}
	
}
