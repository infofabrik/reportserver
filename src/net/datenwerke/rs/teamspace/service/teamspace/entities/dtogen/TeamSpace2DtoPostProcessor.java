package net.datenwerke.rs.teamspace.service.teamspace.entities.dtogen;

import javax.inject.Inject;

import net.datenwerke.dtoservices.dtogenerator.poso2dtogenerator.interfaces.Poso2DtoPostProcessor;
import net.datenwerke.gxtdto.server.dtomanager.DtoService;
import net.datenwerke.rs.teamspace.client.teamspace.dto.TeamSpaceRoleDto;
import net.datenwerke.rs.teamspace.client.teamspace.dto.decorator.TeamSpaceDtoDec;
import net.datenwerke.rs.teamspace.service.teamspace.TeamSpaceService;
import net.datenwerke.rs.teamspace.service.teamspace.entities.TeamSpace;
import net.datenwerke.rs.teamspace.service.teamspace.entities.TeamSpaceRole;

public class TeamSpace2DtoPostProcessor implements Poso2DtoPostProcessor<TeamSpace, TeamSpaceDtoDec> {

   private TeamSpaceService teamSpaceService;
   private DtoService dtoService;

   @Inject
   public TeamSpace2DtoPostProcessor(TeamSpaceService teamSpaceService, DtoService dtoService) {

      this.teamSpaceService = teamSpaceService;
      this.dtoService = dtoService;
   }

   @Override
   public void dtoCreated(TeamSpace poso, TeamSpaceDtoDec dto) {
      TeamSpaceRole role = teamSpaceService.getRole(poso);
      TeamSpaceRoleDto roleDto = (TeamSpaceRoleDto) dtoService.createDto(role);
      dto.setRole(roleDto);
      dto.setTeamSpaceOwner(teamSpaceService.isOwner(poso));
   }

   @Override
   public void dtoInstantiated(TeamSpace poso, TeamSpaceDtoDec dto) {
      // TODO Auto-generated method stub

   }

}
