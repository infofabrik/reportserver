package net.datenwerke.rs.base.service.datasources.hooker;

import java.sql.Connection;

import com.google.inject.Inject;

import net.datenwerke.dbpool.hooks.DbPoolConnectionHook;
import net.datenwerke.rs.utils.eventbus.EventBus;


public class StandardConnectionHook implements DbPoolConnectionHook {

	private final EventBus eventBus;

	@Inject
	public StandardConnectionHook(EventBus eventBus) {
		this.eventBus = eventBus;
	}

	@Override
	public void onAcquire(Connection connection) {
		
	}

	@Override
	public void onCheckIn(Connection connection) {
		
	}

	@Override
	public void onCheckOut(Connection connection) {
		
	}

	@Override
	public void onDestroy(Connection connection) {
		
	}

}
