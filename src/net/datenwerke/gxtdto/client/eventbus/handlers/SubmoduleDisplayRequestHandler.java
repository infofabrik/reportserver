package net.datenwerke.gxtdto.client.eventbus.handlers;

import com.google.gwt.event.shared.EventHandler;

import net.datenwerke.gxtdto.client.eventbus.events.SubmoduleDisplayRequest;

public interface SubmoduleDisplayRequestHandler extends EventHandler {

   void onSubmoduleDisplayRequest(SubmoduleDisplayRequest event);

}
