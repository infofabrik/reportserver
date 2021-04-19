package net.datenwerke.rs.core.service.reportmanager.hookers;

import java.util.Enumeration;

import javax.inject.Inject;

import net.datenwerke.rs.core.service.parameters.entities.ParameterDefinition;
import net.datenwerke.rs.core.service.parameters.entities.ParameterInstance;
import net.datenwerke.rs.core.service.reportmanager.entities.reports.Report;
import net.datenwerke.rs.core.service.reportmanager.hooks.ConfigureReportViaRequestAndLocationImpl;

import com.google.inject.Injector;

public class ConfigureBaseReportViaRequestHooker extends ConfigureReportViaRequestAndLocationImpl {
	
	private Injector injector;

	@Inject
	public ConfigureBaseReportViaRequestHooker(Injector injector) {
		this.injector = injector;
	}

	@Override
	public void adjustReport(Report report, ParameterProvider req) {
		Enumeration<String> parameterNames = req.getParameterNames();
		while(parameterNames.hasMoreElements()){
			String name = parameterNames.nextElement();
			
			if(name.startsWith("p_") && name.length() > 2){ //$NON-NLS-1$
				String parameterName = name.substring(2);
				for(ParameterInstance instance : report.getParameterInstances()){
					injector.injectMembers(instance);
					
					ParameterDefinition definition = instance.getDefinition();
					if(parameterName.equals(definition.getKey()) && definition.isEditable()){
						instance.parseStringValue(req.getParameter(name));
						break;
					}
				}
			}
		}
		
	}

}
