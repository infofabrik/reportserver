package net.datenwerke.gf.service.dtoservice;

import com.google.inject.AbstractModule;
import com.google.inject.Scopes;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.gxtdto.server.dtomanager.DtoService;

/**
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class DtoModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(DtoService.class).to(DtoServiceImpl.class).in(Scopes.SINGLETON);
	}
}
