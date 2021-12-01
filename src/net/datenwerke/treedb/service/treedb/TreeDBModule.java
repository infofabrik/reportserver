package net.datenwerke.treedb.service.treedb;

import com.google.inject.AbstractModule;
import com.google.inject.Scopes;

public class TreeDBModule extends AbstractModule {

	@Override
	protected void configure() {
		/* bind services */
		bind(TreeDBService.class).to(TreeDBServiceImpl.class).in(Scopes.SINGLETON);
		
		requestStaticInjection(AbstractNode.class);
	}

}
