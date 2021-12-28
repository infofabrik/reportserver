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
abstract public class ParameterSetReplacementProviderImpl implements ParameterSetReplacementProvider {

   @Override
   public void extendJuel(User user, Report report, ExpressionFactory factory, ELContext context) {

   }

   @Override
   public Map<String, ParameterValue> provideReplacements(User user, Report report) {
      return null;
   }

}
