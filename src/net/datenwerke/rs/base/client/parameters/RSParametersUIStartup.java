package net.datenwerke.rs.base.client.parameters;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.gf.client.login.LoginService;
import net.datenwerke.gxtdto.client.dtomanager.callback.RsAsyncCallback;
import net.datenwerke.gxtdto.client.waitonevent.SynchronousCallbackOnEventTrigger;
import net.datenwerke.gxtdto.client.waitonevent.WaitOnEventTicket;
import net.datenwerke.gxtdto.client.waitonevent.WaitOnEventUIService;
import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.base.client.parameters.blatext.BlatextConfigurator;
import net.datenwerke.rs.base.client.parameters.datasource.DatasourceConfigurator;
import net.datenwerke.rs.base.client.parameters.datasource.DatasourceParameterDao;
import net.datenwerke.rs.base.client.parameters.datasource.DatasourceParameterUiService;
import net.datenwerke.rs.base.client.parameters.datetime.DateTimeConfigurator;
import net.datenwerke.rs.base.client.parameters.headline.HeadlineConfigurator;
import net.datenwerke.rs.base.client.parameters.separator.SeparatorConfigurator;
import net.datenwerke.rs.base.client.parameters.string.TextParameterConfigurator;
import net.datenwerke.rs.core.client.parameters.config.ParameterConfigurator;
import net.datenwerke.rs.core.client.parameters.hooks.ParameterProviderHook;

/**
 * 
 *
 */
public class RSParametersUIStartup implements ParameterProviderHook {

   @SuppressWarnings("unchecked")
   private final List<Provider<? extends ParameterConfigurator>> parameters;

   @Inject
   public RSParametersUIStartup(Provider<TextParameterConfigurator> basicString,
         Provider<DateTimeConfigurator> dateTime, Provider<DatasourceConfigurator> datasource,
         // TODO .. anders
         // Provider<UserVariableConfigurator> userVariable,

         Provider<BlatextConfigurator> blatext, Provider<HeadlineConfigurator> headline,
         Provider<SeparatorConfigurator> separator,

         final DatasourceParameterUiService dsParameterUiService, final WaitOnEventUIService waitOnEventService,
         final DatasourceParameterDao dsParamDao,

         HookHandlerService hookHandler) {

      /* store parameters */
      parameters = new ArrayList<Provider<? extends ParameterConfigurator>>();
      parameters.add(basicString);
      parameters.add(dateTime);
      parameters.add(datasource);

      parameters.add(blatext);
      parameters.add(headline);
      parameters.add(separator);

      waitOnEventService.callbackOnEvent(LoginService.REPORTSERVER_EVENT_AFTER_INITIAL_LOGIN,
            new SynchronousCallbackOnEventTrigger() {
               public void execute(final WaitOnEventTicket ticket) {
                  waitOnEventService.signalProcessingDone(ticket);
                  dsParamDao.allowDatasourceParameterPostProcessing(new RsAsyncCallback<Boolean>() {
                     @Override
                     public void onSuccess(Boolean result) {
                        dsParameterUiService.setAllowPostProcessing(Boolean.TRUE.equals(result));
                     }
                  });

               }
            });

      /* attach hook handler */
      hookHandler.attachHooker(ParameterProviderHook.class, this);
   }

   @SuppressWarnings("unchecked")
   public Collection<ParameterConfigurator> parameterProviderHook_getConfigurators() {
      List<ParameterConfigurator> configurations = new ArrayList<ParameterConfigurator>();

      for (Provider<? extends ParameterConfigurator> provider : parameters)
         configurations.add(provider.get());

      return configurations;
   }
}
