package net.datenwerke.gxtdto.client.utils;

import net.datenwerke.rs.theme.client.icon.BaseIcon;

import com.google.gwt.resources.client.ImageResource;

public class IconGuesserService {

	public ImageResource guessIcon(String name){
		return BaseIcon.fromFileExtension(name).toImageResource();
	}
	
}
