package net.datenwerke.rs.teamspace.client.teamspace.dto.pa;

import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;

import net.datenwerke.rs.teamspace.client.teamspace.dto.StrippedDownTeamSpaceMemberDto;
import net.datenwerke.rs.teamspace.client.teamspace.dto.TeamSpaceRoleDto;

public interface StrippedDownTeamSpaceMemberDtoPa extends PropertyAccess<StrippedDownTeamSpaceMemberDto> {

   @Path("folkId")
   public ModelKeyProvider<StrippedDownTeamSpaceMemberDto> dtoId();

   /* Properties */
   public ValueProvider<StrippedDownTeamSpaceMemberDto, Long> folkId();

   public ValueProvider<StrippedDownTeamSpaceMemberDto, TeamSpaceRoleDto> role();

   public ValueProvider<StrippedDownTeamSpaceMemberDto, String> name();

   public ValueProvider<StrippedDownTeamSpaceMemberDto, String> firstname();

   public ValueProvider<StrippedDownTeamSpaceMemberDto, String> lastname();

}
