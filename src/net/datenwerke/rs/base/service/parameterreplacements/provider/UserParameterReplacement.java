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

public class UserParameterReplacement extends ParameterSetReplacementProviderImpl {

   public static final String USER_REPLACEMENT_FOR_JUEL = "_RS_USER";

   public static final String USER_REPLACEMENT_FIRSTNAME = "_RS_USER_FIRSTNAME";
   public static final String USER_REPLACEMENT_LASTNAME = "_RS_USER_LASTNAME";
   public static final String USER_REPLACEMENT_USERNAME = "_RS_USER_USERNAME";
   public static final String USER_REPLACEMENT_TITLE = "_RS_USER_TITLE";
   public static final String USER_REPLACEMENT_EMAIL = "_RS_USER_EMAIL";
   public static final String USER_REPLACEMENT_ID = "_RS_USER_ID";

   @Override
   public void extendJuel(User user, Report report, ExpressionFactory factory, ELContext context) {
      VariableMapper vm = context.getVariableMapper();

      UserForJuel juelObject = UserForJuel.createInstance(user);

      vm.setVariable(USER_REPLACEMENT_FOR_JUEL, factory.createValueExpression(juelObject, UserForJuel.class)); // $NON-NLS-1$
   }

   @Override
   public Map<String, ParameterValue> provideReplacements(User user, Report report) {
      Map<String, ParameterValue> reps = new HashMap<String, ParameterValue>();

      reps.put(USER_REPLACEMENT_FIRSTNAME, new ParameterValueImpl(USER_REPLACEMENT_FIRSTNAME,
            null == user.getFirstname() ? "" : user.getFirstname(), String.class));
      reps.put(USER_REPLACEMENT_LASTNAME, new ParameterValueImpl(USER_REPLACEMENT_LASTNAME,
            null == user.getLastname() ? "" : user.getLastname(), String.class));
      reps.put(USER_REPLACEMENT_USERNAME, new ParameterValueImpl(USER_REPLACEMENT_USERNAME,
            null == user.getUsername() ? "" : user.getUsername(), String.class));
      reps.put(USER_REPLACEMENT_TITLE, new ParameterValueImpl(USER_REPLACEMENT_TITLE,
            null == user.getTitle() ? "" : user.getTitle(), String.class));
      reps.put(USER_REPLACEMENT_EMAIL, new ParameterValueImpl(USER_REPLACEMENT_EMAIL,
            null == user.getEmail() ? "" : user.getEmail(), String.class));
      reps.put(USER_REPLACEMENT_ID, new ParameterValueImpl(USER_REPLACEMENT_ID, user.getId(), Long.class));

      return reps;
   }
}
