package net.datenwerke.gxtdto.client.forms.simpleform;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.gxtdto.client.forms.simpleform.hooks.FormFieldProviderHook;
import net.datenwerke.gxtdto.client.forms.simpleform.providers.BooleanProvider;
import net.datenwerke.gxtdto.client.forms.simpleform.providers.CustomComponentProvider;
import net.datenwerke.gxtdto.client.forms.simpleform.providers.DateProvider;
import net.datenwerke.gxtdto.client.forms.simpleform.providers.DoubleProvider;
import net.datenwerke.gxtdto.client.forms.simpleform.providers.DtoModelProvider;
import net.datenwerke.gxtdto.client.forms.simpleform.providers.DynamicListProvider;
import net.datenwerke.gxtdto.client.forms.simpleform.providers.IntegerProvider;
import net.datenwerke.gxtdto.client.forms.simpleform.providers.LongProvider;
import net.datenwerke.gxtdto.client.forms.simpleform.providers.SeparatorProvider;
import net.datenwerke.gxtdto.client.forms.simpleform.providers.StaticLabelProvider;
import net.datenwerke.gxtdto.client.forms.simpleform.providers.StaticListProvider;
import net.datenwerke.gxtdto.client.forms.simpleform.providers.StringProvider;
import net.datenwerke.gxtdto.client.forms.simpleform.providers.TextAsListProvider;
import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;

public class SimpleFormStartup {

   @Inject
   public SimpleFormStartup(HookHandlerService hookHandler, Provider<BooleanProvider> booleanProvider,
         Provider<DtoModelProvider> baseModelProvider, Provider<CustomComponentProvider> customComponentProvider,
         Provider<DateProvider> dateProvider, Provider<DynamicListProvider> dynamicListProvider,
         Provider<IntegerProvider> integerProvider, Provider<LongProvider> longProvider,
         Provider<DoubleProvider> doubleProvider, Provider<SeparatorProvider> separatorProvider,
         Provider<StaticLabelProvider> staticLabelProvider, Provider<StaticListProvider> staticListProvider,
         Provider<StringProvider> stringProvider, Provider<TextAsListProvider> textAsListProvider

   ) {

      /* attach hooks */
      hookHandler.attachHooker(FormFieldProviderHook.class, booleanProvider, HookHandlerService.PRIORITY_LOW);
      hookHandler.attachHooker(FormFieldProviderHook.class, baseModelProvider, HookHandlerService.PRIORITY_LOWER);
      hookHandler.attachHooker(FormFieldProviderHook.class, customComponentProvider);
      hookHandler.attachHooker(FormFieldProviderHook.class, dateProvider, HookHandlerService.PRIORITY_LOW);
      hookHandler.attachHooker(FormFieldProviderHook.class, dynamicListProvider, HookHandlerService.PRIORITY_LOW);
      hookHandler.attachHooker(FormFieldProviderHook.class, integerProvider, HookHandlerService.PRIORITY_LOW);
      hookHandler.attachHooker(FormFieldProviderHook.class, longProvider, HookHandlerService.PRIORITY_LOW);
      hookHandler.attachHooker(FormFieldProviderHook.class, doubleProvider, HookHandlerService.PRIORITY_LOW);
      hookHandler.attachHooker(FormFieldProviderHook.class, separatorProvider, HookHandlerService.PRIORITY_LOW);
      hookHandler.attachHooker(FormFieldProviderHook.class, staticLabelProvider, HookHandlerService.PRIORITY_LOW);
      hookHandler.attachHooker(FormFieldProviderHook.class, staticListProvider, HookHandlerService.PRIORITY_LOW);
      hookHandler.attachHooker(FormFieldProviderHook.class, stringProvider, HookHandlerService.PRIORITY_LOW);
      hookHandler.attachHooker(FormFieldProviderHook.class, textAsListProvider, HookHandlerService.PRIORITY_LOW);
   }
}
