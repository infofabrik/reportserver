package net.datenwerke.gf.client.dtoinfo;

import com.google.gwt.inject.client.AbstractGinModule;
import com.google.inject.Singleton;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.gxtdto.client.dtoinfo.DtoInformationService;

/**
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class ClientDtoInfoModule extends AbstractGinModule {

	@Override
	protected void configure() {
		bind(DtoInformationService.class).to(ClientDtoInfoServiceImpl.class).in(Singleton.class);
	}
}
