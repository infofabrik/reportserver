package net.datenwerke.rs.adminutils.service.su;

import com.google.inject.AbstractModule;

import net.datenwerke.rs.adminutils.service.su.genrights.GenRightsSuModule;

public class SuModule extends AbstractModule {

	@Override
	protected void configure() {
		install(new GenRightsSuModule());
	}

}
