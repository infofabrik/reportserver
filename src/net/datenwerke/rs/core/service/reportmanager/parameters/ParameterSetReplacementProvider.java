package net.datenwerke.rs.core.service.reportmanager.parameters;

import java.util.Map;

import javax.el.ELContext;
import javax.el.ExpressionFactory;

import net.datenwerke.rs.core.service.reportmanager.entities.reports.Report;
import net.datenwerke.security.service.usermanager.entities.User;

/**
 * 
 * 
 */
public interface ParameterSetReplacementProvider {

   public Map<String, ParameterValue> provideReplacements(User user, Report report);

   public void extendJuel(User user, Report report, ExpressionFactory factory, ELContext context);
}
