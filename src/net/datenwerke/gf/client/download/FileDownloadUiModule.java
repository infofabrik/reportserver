package net.datenwerke.gf.client.download;

import com.google.gwt.inject.client.AbstractGinModule;

public class FileDownloadUiModule extends AbstractGinModule {

	public static final String META_FIELD_PREFIX = "rsfd_meta_";

	@Override
	protected void configure() {
		bind(FileDownloadUiService.class).to(FileDownloadUiServiceImpl.class);
	}

}
