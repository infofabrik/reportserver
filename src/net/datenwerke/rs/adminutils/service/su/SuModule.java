package net.datenwerke.rs.adminutils.service.su;

import net.datenwerke.rs.adminutils.service.su.genrights.GenRightsSuModule;

import com.google.inject.AbstractModule;

public class SuModule extends AbstractModule {

	@Override
	protected void configure() {
		install(new GenRightsSuModule());
	}

}
