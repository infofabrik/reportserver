package net.datenwerke.rs.crystal.service.crystal;

import java.io.Serializable;

import net.datenwerke.dtoservices.dtogenerator.annotations.ExposeToClient;
import net.datenwerke.dtoservices.dtogenerator.annotations.GenerateDto;
import net.datenwerke.rs.core.client.parameters.dto.ParameterProposalDto;
import net.datenwerke.rs.core.service.parameters.entities.ParameterDefinition;
import net.datenwerke.rs.utils.entitycloner.annotation.EnclosedEntity;

@GenerateDto(dtoPackage = "net.datenwerke.rs.crystal.client.crystal.dto", proxyableDto = false, dtoImplementInterfaces = ParameterProposalDto.class)
public class CrystalParameterProposal implements Serializable {

   private static final long serialVersionUID = -7663623995399473020L;

   @ExposeToClient(id = true)
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
