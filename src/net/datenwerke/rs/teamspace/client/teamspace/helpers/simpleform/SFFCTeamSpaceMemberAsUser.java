package net.datenwerke.rs.teamspace.client.teamspace.helpers.simpleform;

import java.util.LinkedHashMap;
import java.util.Map;

import com.google.gwt.core.client.GWT;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ListStore;

import net.datenwerke.gxtdto.client.forms.simpleform.providers.configs.SFFCBaseModel;
import net.datenwerke.rs.teamspace.client.teamspace.dto.TeamSpaceDto;
import net.datenwerke.rs.teamspace.client.teamspace.dto.TeamSpaceMemberDto;
import net.datenwerke.security.client.usermanager.dto.UserDto;
import net.datenwerke.security.client.usermanager.dto.pa.UserDtoPA;
import net.datenwerke.security.ext.client.usermanager.locale.UsermanagerMessages;

public class SFFCTeamSpaceMemberAsUser implements SFFCBaseModel<UserDto> {

   private static UserDtoPA userPa = GWT.create(UserDtoPA.class);

   private final TeamSpaceDto teamSpace;

   public SFFCTeamSpaceMemberAsUser(TeamSpaceDto teamSpace) {

      this.teamSpace = teamSpace;
   }

   public ListStore<UserDto> getAllItemsStore() {
      ListStore<UserDto> store = new ListStore<UserDto>(userPa.dtoId());

      for (TeamSpaceMemberDto member : teamSpace.getMembers())
         if (member.getFolk() instanceof UserDto)
            store.add((UserDto) member.getFolk());

      return store;
   }

   public Map<ValueProvider<UserDto, String>, String> getDisplayProperties() {
      Map<ValueProvider<UserDto, String>, String> displayProperties = new LinkedHashMap<ValueProvider<UserDto, String>, String>();

      displayProperties.put(userPa.firstname(), UsermanagerMessages.INSTANCE.firstname());
      displayProperties.put(userPa.lastname(), UsermanagerMessages.INSTANCE.lastname());

      return displayProperties;
   }

   public boolean isMultiSelect() {
      return false;
   }
}
