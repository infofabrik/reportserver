package net.datenwerke.gf.service.upload;

import com.google.inject.AbstractModule;

public class FileUploadModule extends AbstractModule{

	@Override
	protected void configure() {

		bind(FileUploadService.class).to(FileUploadServiceImpl.class);
		
		bind(FileUploadStartup.class).asEagerSingleton();
	}

}
