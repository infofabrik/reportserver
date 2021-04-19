package net.datenwerke.rs.dashboard.client.dashboard.ui.helper;

import java.util.LinkedHashMap;
import java.util.Map;

import net.datenwerke.gxtdto.client.forms.simpleform.providers.configs.lists.SFFCFancyStaticList;
import net.datenwerke.gxtdto.client.resources.BaseResources;
import net.datenwerke.rs.dashboard.client.dashboard.dto.LayoutTypeDto;
import net.datenwerke.rs.dashboard.client.dashboard.locale.DashboardMessages;

import com.google.gwt.resources.client.ImageResource;

public class SFFCDashboardLayout extends SFFCFancyStaticList<LayoutTypeDto> {

	@Override
	public Map<String, LayoutTypeDto> getValues() {
		Map<String, LayoutTypeDto> map = new LinkedHashMap<String, LayoutTypeDto>();
		
		map.put(DashboardMessages.INSTANCE.singleColLayout(), LayoutTypeDto.ONE_COLUMN);
		map.put(DashboardMessages.INSTANCE.twoColLayut(), LayoutTypeDto.TWO_COLUMN_EQUI);
		map.put(DashboardMessages.INSTANCE.twoColLayout1to2(), LayoutTypeDto.TWO_COLUMN_RIGHT_LARGE);
		map.put(DashboardMessages.INSTANCE.towColLayout2to1(), LayoutTypeDto.TWO_COLUMN_LEFT_LARGE);
		map.put(DashboardMessages.INSTANCE.threeColLayout(), LayoutTypeDto.THREE_COLUMN);
		
		return map;
	}

	@Override
	public int getHeight() {
		return 70;
	}
	
	@Override
	public int getWidth() {
		return 400;
	}

	@Override
	public Map<String, ImageResource> getIconMap() {
		Map<String, ImageResource> map = new LinkedHashMap<String, ImageResource>();
		
		map.put(DashboardMessages.INSTANCE.singleColLayout(), BaseResources.INSTANCE.columnSingle());
		map.put(DashboardMessages.INSTANCE.twoColLayut(), BaseResources.INSTANCE.columnDouble());
		map.put(DashboardMessages.INSTANCE.twoColLayout1to2(), BaseResources.INSTANCE.columnDoubleL());
		map.put(DashboardMessages.INSTANCE.towColLayout2to1(), BaseResources.INSTANCE.columnDoubleR());
		map.put(DashboardMessages.INSTANCE.threeColLayout(), BaseResources.INSTANCE.columnTripple());
		
		return map;
	}

	@Override
	public Map<String, String> getDescriptionMap() {
		Map<String, String> map = new LinkedHashMap<String, String>();
		
		map.put(DashboardMessages.INSTANCE.singleColLayout(), DashboardMessages.INSTANCE.singleColLayoutDesc());
		map.put(DashboardMessages.INSTANCE.twoColLayut(), DashboardMessages.INSTANCE.twoColLayoutDesc());
		map.put(DashboardMessages.INSTANCE.twoColLayout1to2(), DashboardMessages.INSTANCE.twoColLayout1to2Desc());
		map.put(DashboardMessages.INSTANCE.towColLayout2to1(), DashboardMessages.INSTANCE.twoColLayout2to1Desc());
		map.put(DashboardMessages.INSTANCE.threeColLayout(), DashboardMessages.INSTANCE.threeColLayoutDesc());
		
		return map;
	}

}
