package net.datenwerke.gf.service;

import net.datenwerke.gf.service.download.FileDownloadModule;
import net.datenwerke.gf.service.tempfile.TempFileModule;
import net.datenwerke.gf.service.upload.FileUploadModule;

import com.google.inject.AbstractModule;

public class DwGwtFrameworkModule extends AbstractModule {
	
	public static final String CONFIG_FILE = "main/main.cf";

	@Override
	protected void configure() {
		install(new TempFileModule());
		install(new FileUploadModule());
		install(new FileDownloadModule());
		
		bind(DwGwtFrameworkStartup.class).asEagerSingleton();
	}

}
