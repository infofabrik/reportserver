package net.datenwerke.rs.ftp.client.ftp;

import com.google.gwt.inject.client.AbstractGinModule;

import net.datenwerke.gf.client.treedb.UITree;
import net.datenwerke.rs.ftp.client.ftp.provider.FtpTreeProvider;
import net.datenwerke.rs.ftp.client.ftp.provider.SftpTreeProvider;
import net.datenwerke.rs.ftp.client.ftp.provider.annotations.DatasinkTreeFtp;
import net.datenwerke.rs.ftp.client.ftp.provider.annotations.DatasinkTreeSftp;

/**
 * 
 *
 */
public class FtpUIModule extends AbstractGinModule {

	@Override
	protected void configure() {
		/* bind trees */
		bind(UITree.class).annotatedWith(DatasinkTreeFtp.class).toProvider(FtpTreeProvider.class);
		bind(UITree.class).annotatedWith(DatasinkTreeSftp.class).toProvider(SftpTreeProvider.class);
		
		bind(FtpUiStartup.class).asEagerSingleton();
	}

}
