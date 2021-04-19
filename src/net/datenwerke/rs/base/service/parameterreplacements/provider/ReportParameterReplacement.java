package net.datenwerke.rs.base.service.parameterreplacements.provider;

import java.util.HashMap;
import java.util.Map;

import javax.el.ELContext;
import javax.el.ExpressionFactory;
import javax.el.VariableMapper;

import net.datenwerke.rs.core.service.reportmanager.entities.reports.Report;
import net.datenwerke.rs.core.service.reportmanager.parameters.ParameterSetReplacementProviderImpl;
import net.datenwerke.rs.core.service.reportmanager.parameters.ParameterValue;
import net.datenwerke.rs.core.service.reportmanager.parameters.ParameterValueImpl;
import net.datenwerke.security.service.usermanager.entities.User;

public class ReportParameterReplacement extends
		ParameterSetReplacementProviderImpl {
	public static final String REPORT_REPLACEMENT_FOR_JUEL = "_RS_REPORT";
	
	public static final String REPORT_REPLACEMENT_NAME = "_RS_REPORT_NAME";
	public static final String REPORT_REPLACEMENT_DESCRIPTION = "_RS_REPORT_DESCRIPTION";
	public static final String REPORT_REPLACEMENT_KEY = "_RS_REPORT_KEY";
	public static final String REPORT_REPLACEMENT_ID = "_RS_REPORT_ID";

	@Override
	public void extendJuel(User user, Report report, ExpressionFactory factory, ELContext context) {
		if(null == report)
			return;
		
		VariableMapper vm = context.getVariableMapper();
		
		ReportForJuel juelObject = new ReportForJuel(report);

		vm.setVariable(REPORT_REPLACEMENT_FOR_JUEL, factory.createValueExpression(juelObject, ReportForJuel.class)); //$NON-NLS-1$
	}
	
	@Override
	public Map<String, ParameterValue> provideReplacements(User user, Report report) {
		Map<String, ParameterValue> reps = new HashMap<String, ParameterValue>();
		
		reps.put(REPORT_REPLACEMENT_NAME, new ParameterValueImpl(REPORT_REPLACEMENT_NAME, (report != null && report.getName() != null) ? report.getName() : "", String.class));
		reps.put(REPORT_REPLACEMENT_DESCRIPTION, new ParameterValueImpl(REPORT_REPLACEMENT_DESCRIPTION,(report != null && report.getDescription() != null) ? report.getDescription() : "", String.class));
		reps.put(REPORT_REPLACEMENT_KEY, new ParameterValueImpl(REPORT_REPLACEMENT_KEY, null == report ? "" : report.getKey() != null ? report.getKey() : report.getOldTransientKey() != null ? report.getOldTransientKey() : "", String.class));
		reps.put(REPORT_REPLACEMENT_ID, new ParameterValueImpl(REPORT_REPLACEMENT_ID, null == report ? null : report.getId() != null ? report.getId() : report.getOldTransientId() != null ? report.getOldTransientId() : null, Long.class));
		
		return reps;
	}
}
