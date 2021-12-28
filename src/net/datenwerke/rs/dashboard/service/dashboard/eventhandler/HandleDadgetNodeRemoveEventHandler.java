package net.datenwerke.rs.dashboard.service.dashboard.eventhandler;

import java.util.List;

import com.google.inject.Inject;

import net.datenwerke.rs.dashboard.service.dashboard.DadgetService;
import net.datenwerke.rs.dashboard.service.dashboard.dagets.LibraryDadget;
import net.datenwerke.rs.dashboard.service.dashboard.entities.DadgetNode;
import net.datenwerke.rs.utils.eventbus.EventHandler;
import net.datenwerke.rs.utils.exception.exceptions.NeedForcefulDeleteException;
import net.datenwerke.security.service.eventlogger.jpa.RemoveEntityEvent;

public class HandleDadgetNodeRemoveEventHandler implements
		EventHandler<RemoveEntityEvent> {

	private final DadgetService dadgetService;

	@Inject
	public HandleDadgetNodeRemoveEventHandler(DadgetService dadgetService) {
		this.dadgetService = dadgetService;
	}

	@Override
	public void handle(RemoveEntityEvent event) {
		DadgetNode node = (DadgetNode) event.getObject();
		
		List<LibraryDadget> libraryDadgets = dadgetService.getLibraryDadgetsWith(node);
		
		if(null != libraryDadgets && ! libraryDadgets.isEmpty()){
			String error = "Dadget is used."; 
			throw new NeedForcefulDeleteException(error);
		}
	}

}
