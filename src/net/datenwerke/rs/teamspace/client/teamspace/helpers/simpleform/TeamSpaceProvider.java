package net.datenwerke.rs.teamspace.client.teamspace.helpers.simpleform;

import java.util.LinkedHashMap;
import java.util.Map;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ListStore;

import net.datenwerke.gxtdto.client.forms.simpleform.SimpleFormFieldConfiguration;
import net.datenwerke.gxtdto.client.forms.simpleform.providers.DtoModelProvider;
import net.datenwerke.gxtdto.client.forms.simpleform.providers.configs.SFFCBaseModel;
import net.datenwerke.gxtdto.client.forms.simpleform.providers.configs.SFFCTeamSpace;
import net.datenwerke.gxtdto.client.locale.BaseMessages;
import net.datenwerke.rs.teamspace.client.teamspace.TeamSpaceUIService;
import net.datenwerke.rs.teamspace.client.teamspace.dto.TeamSpaceDto;
import net.datenwerke.rs.teamspace.client.teamspace.dto.pa.TeamSpaceDtoPA;

public class TeamSpaceProvider extends DtoModelProvider{

	private static TeamSpaceDtoPA tsPa = GWT.create(TeamSpaceDtoPA.class);
	
	private final TeamSpaceUIService teamSpaceService;
	
	@Inject
	public TeamSpaceProvider(
		TeamSpaceUIService teamSpaceService	
		){
		
		/* store objects */
		this.teamSpaceService = teamSpaceService;
	}
	
	@Override
	public boolean doConsumes(Class<?> type,
			SimpleFormFieldConfiguration... configs) {
		return type.equals(TeamSpaceDto.class);
	}
	
	@Override
	public Widget createFormField() {
		final Map<ValueProvider<TeamSpaceDto, String>, String> displayProperties = new LinkedHashMap<ValueProvider<TeamSpaceDto, String>, String>();
		displayProperties.put(tsPa.name(), BaseMessages.INSTANCE.name());
		displayProperties.put(new ValueProvider<TeamSpaceDto, String>() {
			
			@Override
			public void setValue(TeamSpaceDto object, String value) {
			}
			
			@Override
			public String getValue(TeamSpaceDto object) {
				return String.valueOf(object.getId());
			}
			
			@Override
			public String getPath() {
				return "";
			}
		}, BaseMessages.INSTANCE.id());
		
		final SFFCTeamSpace teamSpaceConfig = getTeamSpaceConfig();
		
		configs = new SimpleFormFieldConfiguration[]{
			new SFFCBaseModel<TeamSpaceDto>() {
				@Override
				public ListStore<TeamSpaceDto> getAllItemsStore() {
					if(teamSpaceConfig.isLoadAll())
						return TeamSpaceProvider.this.teamSpaceService.getAllTeamSpacesStore();
					return TeamSpaceProvider.this.teamSpaceService.getTeamSpacesStore();
				}

				@Override
				public Map<ValueProvider<TeamSpaceDto, String>, String> getDisplayProperties() {
					return displayProperties;
				}

				@Override
				public boolean isMultiSelect() {
					return teamSpaceConfig.isMulti();
				}
				
			}
		};
		
		return super.createFormField();
	}

	private SFFCTeamSpace getTeamSpaceConfig() {
		for(SimpleFormFieldConfiguration config : configs)
			if(config instanceof SFFCTeamSpace)
				return (SFFCTeamSpace) config;
		return new SFFCTeamSpace() {
			@Override
			public boolean isMulti() {
				return false;
			}

			@Override
			public boolean isLoadAll() {
				return false;
			}
		};
	}
}
