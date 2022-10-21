package net.datenwerke.rs.teamspace.client.teamspace.dto.decorator;

import java.util.ArrayList;
import java.util.List;

import net.datenwerke.gxtdto.client.dtomanager.IdedDto;
import net.datenwerke.gxtdto.client.locale.BaseMessages;
import net.datenwerke.rs.teamspace.client.teamspace.TeamSpaceApp;
import net.datenwerke.rs.teamspace.client.teamspace.dto.TeamSpaceAppDto;
import net.datenwerke.rs.teamspace.client.teamspace.dto.TeamSpaceDto;
import net.datenwerke.rs.teamspace.client.teamspace.dto.TeamSpaceRoleDto;

/**
 * Dto Decorator for {@link TeamSpaceDto}
 *
 */
public class TeamSpaceDtoDec extends TeamSpaceDto implements IdedDto {

   private static final long serialVersionUID = 1L;
   private static final int MAX_DISPLAY_TITLE_SIZE = 50;

   /* the current users role in this teamspace */
   private TeamSpaceRoleDto role;

   /* whether or not the current user owns this teamspace */
   private boolean teamSpaceOwner;

   public TeamSpaceDtoDec() {
      super();
   }

   public List<TeamSpaceAppDto> getInstalledApps() {
      List<TeamSpaceAppDto> installedApps = new ArrayList<TeamSpaceAppDto>();

      for (TeamSpaceAppDto app : getApps())
         if (app.isInstalled())
            installedApps.add(app);

      return installedApps;
   }

   public boolean isAppInstalled(TeamSpaceApp app) {
      for (TeamSpaceAppDto installedApp : getInstalledApps())
         if (app.getAppId().equals(installedApp.getType()))
            return true;
      return false;
   }

   public TeamSpaceRoleDto getRole() {
      return role;
   }

   public void setRole(TeamSpaceRoleDto role) {
      this.role = role;
   }

   public boolean isTeamSpaceOwner() {
      return teamSpaceOwner;
   }

   public void setTeamSpaceOwner(boolean teamSpaceOwner) {
      this.teamSpaceOwner = teamSpaceOwner;
   }

   @Override
   public String toDisplayTitle() {
      String name = getName();
      try {
         if (name.length() <= MAX_DISPLAY_TITLE_SIZE)
            return name;

         return name.substring(0, MAX_DISPLAY_TITLE_SIZE) + "...";
      } catch (NullPointerException e) {
         return BaseMessages.INSTANCE.unnamed();
      }
   }

}
