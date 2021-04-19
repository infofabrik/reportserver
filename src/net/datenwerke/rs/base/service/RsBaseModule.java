package net.datenwerke.rs.base.service;

import com.google.inject.AbstractModule;

import net.datenwerke.rs.base.service.datasources.DatasourceExtensionModule;
import net.datenwerke.rs.base.service.parameterreplacements.BaseParameterReplacementModule;
import net.datenwerke.rs.base.service.parameters.BaseParametersModule;
import net.datenwerke.rs.base.service.parameters.datetime.dtogen.DateTimeParameterInstance2DtoPostProcessor;
import net.datenwerke.rs.base.service.parameters.datetime.dtogen.DateTimeParameterInstance2PosoPostProcessor;
import net.datenwerke.rs.base.service.reportengines.BaseReportEnginesModule;
import net.datenwerke.rs.core.service.internaldb.InternalDbModule;

public class RsBaseModule extends AbstractModule {

	@Override
	protected void configure() {
		/* install sub modules */
		install(new DatasourceExtensionModule());
		
		install(new BaseReportEnginesModule());

		install(new BaseParametersModule());
		
		install(new BaseParameterReplacementModule());
		
		install(new InternalDbModule());
		
		requestStaticInjection(DateTimeParameterInstance2PosoPostProcessor.class);
		requestStaticInjection(DateTimeParameterInstance2DtoPostProcessor.class);
	}

}
