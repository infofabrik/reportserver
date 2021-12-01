package net.datenwerke.rs.base.service.reportengines.jasper.output.generator;

import java.util.Arrays;

import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.base.service.reportengines.jasper.entities.JasperReport;
import net.datenwerke.rs.base.service.reportengines.jasper.hooks.JasperExportHook;
import net.datenwerke.rs.base.service.reportengines.jasper.output.object.CompiledRSJasperReport;
import net.datenwerke.rs.core.service.reportmanager.engine.config.ReportExecutionConfig;
import net.datenwerke.security.service.usermanager.entities.User;
import net.sf.jasperreports.engine.JRAbstractExporter;
import net.sf.jasperreports.engine.design.JasperDesign;

public abstract class JasperOutputGeneratorImpl implements JasperOutputGenerator {

	protected final HookHandlerService hookHandler;
	
	public JasperOutputGeneratorImpl(HookHandlerService hookHandler){
		this.hookHandler = hookHandler;
	}
	
	public boolean hasConfig(Class<? extends ReportExecutionConfig> type, ReportExecutionConfig... configs){
		return null != getConfig(type, configs);
	}
	
	public <C extends ReportExecutionConfig> C getConfig(final Class<C> type, final ReportExecutionConfig... configs){
		if(null == configs || configs.length == 0)
			return null;
		
		return Arrays.stream(configs)
			.filter(config -> type.isAssignableFrom(config.getClass()))
			.map(config -> (C) config)
			.findAny()
			.orElse(null);
	}
	
	@Override
	public void adjustDesign(JasperDesign jasperDesign, String outputFormat,
			ReportExecutionConfig... configs) {
	}

	protected void callPostHooks(final String outputFormat,
			final JRAbstractExporter exporter, final JasperReport report, final CompiledRSJasperReport cjrReport, final User user) {
		
		hookHandler.getHookers(JasperExportHook.class)
			.stream()
			.filter(cb -> null != cb.getFormats() && cb.getFormats().contains(outputFormat))
			.forEach(cb -> cb.afterExport(outputFormat, exporter, report, cjrReport, user));
	}

	protected void callPreHooks(final String outputFormat, final JRAbstractExporter exporter, final JasperReport report, final User user) {
		hookHandler.getHookers(JasperExportHook.class)
			.stream()
			.filter(cb -> null != cb.getFormats() && cb.getFormats().contains(outputFormat))
			.forEach(cb -> cb.beforeExport(outputFormat, exporter, report, user));
	}
	
	@Override
	public boolean isCatchAll() {
		return false;
	}

}
