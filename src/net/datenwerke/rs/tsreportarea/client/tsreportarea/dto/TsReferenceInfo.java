package net.datenwerke.rs.tsreportarea.client.tsreportarea.dto;

import java.io.Serializable;

import net.datenwerke.rs.teamspace.client.teamspace.dto.TeamSpaceDto;

public class TsReferenceInfo implements Serializable {

   /**
    * 
    */
   private static final long serialVersionUID = 1L;

   private TeamSpaceDto teamSpace;
   private boolean hardlink;

   public void setTeamSpace(TeamSpaceDto teamSpace) {
      this.teamSpace = teamSpace;
   }

   public TeamSpaceDto getTeamSpace() {
      return teamSpace;
   }

   public boolean isHardlink() {
      return hardlink;
   }

   public void setHardlink(boolean hardlink) {
      this.hardlink = hardlink;
   }

}