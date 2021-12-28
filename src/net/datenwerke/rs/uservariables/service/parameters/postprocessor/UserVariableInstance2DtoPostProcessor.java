package net.datenwerke.rs.uservariables.service.parameters.postprocessor;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.dtoservices.dtogenerator.poso2dtogenerator.interfaces.Poso2DtoPostProcessor;
import net.datenwerke.rs.uservariables.client.parameters.dto.UserVariableParameterInstanceDto;
import net.datenwerke.rs.uservariables.client.parameters.dto.decorator.UserVariableParameterInstanceDtoDec;
import net.datenwerke.rs.uservariables.service.parameters.UserVariableParameterInstance;
import net.datenwerke.security.service.authenticator.AuthenticatorService;
import net.datenwerke.security.service.usermanager.entities.User;

public class UserVariableInstance2DtoPostProcessor
      implements Poso2DtoPostProcessor<UserVariableParameterInstance, UserVariableParameterInstanceDto> {

   private final Provider<AuthenticatorService> authenticatorServiceProvider;

   @Inject
   public UserVariableInstance2DtoPostProcessor(Provider<AuthenticatorService> authenticatorServiceProvider) {

      this.authenticatorServiceProvider = authenticatorServiceProvider;
   }

   @Override
   public void dtoCreated(UserVariableParameterInstance poso, UserVariableParameterInstanceDto dto) {
      try {
         User currentUser = authenticatorServiceProvider.get().getCurrentUser();
         Object value = poso.getSelectedValue(currentUser);
         if (null != value)
            ((UserVariableParameterInstanceDtoDec) dto).setValue(value.toString());
      } catch (Exception e) {
      }
   }

   @Override
   public void dtoInstantiated(UserVariableParameterInstance poso, UserVariableParameterInstanceDto dto) {

   }

}
