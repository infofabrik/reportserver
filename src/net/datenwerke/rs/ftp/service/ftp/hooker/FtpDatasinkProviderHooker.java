package net.datenwerke.rs.ftp.service.ftp.hooker;

import java.util.Arrays;
import java.util.Collection;

import net.datenwerke.rs.core.service.datasinkmanager.entities.DatasinkDefinition;
import net.datenwerke.rs.core.service.datasinkmanager.hooks.DatasinkProviderHook;
import net.datenwerke.rs.ftp.service.ftp.definitions.FtpDatasink;

public class FtpDatasinkProviderHooker implements DatasinkProviderHook {

	@Override
	public Collection<? extends Class<? extends DatasinkDefinition>> getDatasinks() {
		return (Collection<? extends Class<? extends DatasinkDefinition>>) Arrays.asList(new Class[]{FtpDatasink.class}); 
	}

}
