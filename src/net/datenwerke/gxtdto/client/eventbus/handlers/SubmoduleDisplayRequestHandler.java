package net.datenwerke.gxtdto.client.eventbus.handlers;

import net.datenwerke.gxtdto.client.eventbus.events.SubmoduleDisplayRequest;

import com.google.gwt.event.shared.EventHandler;

public interface SubmoduleDisplayRequestHandler extends EventHandler{
	
	void onSubmoduleDisplayRequest(SubmoduleDisplayRequest event);

}
