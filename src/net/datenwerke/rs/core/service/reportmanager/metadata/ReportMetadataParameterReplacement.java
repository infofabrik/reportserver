package net.datenwerke.rs.core.service.reportmanager.metadata;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.el.ELContext;
import javax.el.ExpressionFactory;
import javax.el.VariableMapper;

import net.datenwerke.rs.core.service.reportmanager.entities.reports.Report;
import net.datenwerke.rs.core.service.reportmanager.entities.reports.ReportMetadata;
import net.datenwerke.rs.core.service.reportmanager.interfaces.ReportVariant;
import net.datenwerke.rs.core.service.reportmanager.parameters.ParameterSetReplacementProviderImpl;
import net.datenwerke.rs.core.service.reportmanager.parameters.ParameterValue;
import net.datenwerke.rs.core.service.reportmanager.parameters.ParameterValueImpl;
import net.datenwerke.security.service.usermanager.entities.User;

public class ReportMetadataParameterReplacement extends ParameterSetReplacementProviderImpl{
	
	
	private static final String METADATA_REPLACEMENT_FOR_JUEL = "_RS_METADATA";
	private static final String METADATA_PRAEFIX = "_RS_METADATA_";
	private static final String METADATA_PRAEFIX_SUPER = "_RS_METADATA_SUPER_";
	
	@Override
	public void extendJuel(User user, Report report, ExpressionFactory factory, ELContext context) {
		if(null == report || null == report.getReportMetadata())
			return;
		
		VariableMapper vm = context.getVariableMapper();

		HashMap<String, ReportMetadataForJuel> set = new HashMap<String, ReportMetadataForJuel>();
		Set<ReportMetadata> reportMetadata = report.getReportMetadata();
		if(null != reportMetadata){
			for(ReportMetadata rm : reportMetadata){
				set.put(rm.getName(), ReportMetadataForJuel.fromReportMetadata(rm));
			}
		}

		vm.setVariable(METADATA_REPLACEMENT_FOR_JUEL, factory.createValueExpression(set, HashMap.class)); //$NON-NLS-1$
	}
	
	@Override
	public Map<String, ParameterValue> provideReplacements(User user, Report report) {
		Map<String, ParameterValue> replacementMap = new HashMap<String, ParameterValue>();
		
		if(report instanceof ReportVariant){
			addEntriesToMap(((ReportVariant) report).getBaseReport(), replacementMap, METADATA_PRAEFIX_SUPER);
			addEntriesToMap(((ReportVariant) report).getBaseReport(), replacementMap, METADATA_PRAEFIX);
		}

		addEntriesToMap(report, replacementMap, METADATA_PRAEFIX);
		
		return replacementMap;
	}

	private void addEntriesToMap(Report report, Map<String, ParameterValue> replacementMap, String praefix){
		if(null == report || null == report.getReportMetadata())
			return;
		
		Set<ReportMetadata> reportMetadata = report.getReportMetadata();
		if(null != reportMetadata){
			for(ReportMetadata rmd : reportMetadata){
				replacementMap.put(praefix + rmd.getName(), new ParameterValueImpl(praefix + rmd.getName(), rmd.getValue(), String.class));
			}
		}
	}
	
}
