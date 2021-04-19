package net.datenwerke.rs.incubator.service;

import com.google.inject.AbstractModule;

import net.datenwerke.rs.incubator.service.aliascmd.AliasCmdModule;
import net.datenwerke.rs.incubator.service.crypto.FileServerKeyStoreKryptoCredentialProvider;
import net.datenwerke.rs.incubator.service.misc.terminal.MiscCommandStartup;
import net.datenwerke.rs.incubator.service.schedulernotification.SchedulerNotificationModule;
import net.datenwerke.rs.incubator.service.xslt.XsltCommandModule;

public class RsIncubatorModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(MiscCommandStartup.class).asEagerSingleton();
		
		install(new AliasCmdModule());
		install(new XsltCommandModule());
		install(new SchedulerNotificationModule());
		
		requestStaticInjection(FileServerKeyStoreKryptoCredentialProvider.class);
	}

}
