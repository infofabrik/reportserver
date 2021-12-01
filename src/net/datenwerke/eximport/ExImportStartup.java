package net.datenwerke.eximport;

import net.datenwerke.eximport.ex.enclosed.EnclosedEntityExporter;
import net.datenwerke.eximport.ex.enclosed.EnclosedObjectExporter;
import net.datenwerke.eximport.hooker.ByteArrayExporterHelperHooker;
import net.datenwerke.eximport.hooker.DateExporterHelperHooker;
import net.datenwerke.eximport.hooker.EnumExporterHelperHooker;
import net.datenwerke.eximport.hooker.StringExporterHelperHooker;
import net.datenwerke.eximport.hooks.BasicObjectExImporterHelperHook;
import net.datenwerke.eximport.hooks.ExImportIdProviderHook;
import net.datenwerke.eximport.hooks.ExporterProviderHook;
import net.datenwerke.eximport.hooks.ImporterProviderHook;
import net.datenwerke.eximport.ids.EntityIdProviderHooker;
import net.datenwerke.eximport.im.enclosed.EnclosedEntityImporter;
import net.datenwerke.eximport.im.enclosed.EnclosedObjectImporter;
import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;

import com.google.inject.Inject;
import com.google.inject.Provider;

public class ExImportStartup {

   @Inject
   public ExImportStartup(ByteArrayExporterHelperHooker byteArrayExporterHooker,
         EnumExporterHelperHooker enumExporterHooker, DateExporterHelperHooker dateExporterHooker,
         StringExporterHelperHooker stringExporterHooker,

         HookHandlerService hookHandler, Provider<EnclosedEntityExporter> enclosedEntityExporterProvider,
         Provider<EnclosedObjectExporter> enclosedObjectExporterProvider,

         Provider<EnclosedEntityImporter> enclosedEntityImporterProvider,
         Provider<EnclosedObjectImporter> enclosedObjectImporterProvider,

         Provider<EntityIdProviderHooker> entityIdProvider) {
      hookHandler.attachHooker(BasicObjectExImporterHelperHook.class, byteArrayExporterHooker,
            HookHandlerService.PRIORITY_LOW);
      hookHandler.attachHooker(BasicObjectExImporterHelperHook.class, enumExporterHooker,
            HookHandlerService.PRIORITY_LOW);
      hookHandler.attachHooker(BasicObjectExImporterHelperHook.class, stringExporterHooker,
            HookHandlerService.PRIORITY_LOW);
      hookHandler.attachHooker(BasicObjectExImporterHelperHook.class, dateExporterHooker,
            HookHandlerService.PRIORITY_LOW);

      hookHandler.attachHooker(ExporterProviderHook.class, new ExporterProviderHook(enclosedEntityExporterProvider),
            HookHandlerService.PRIORITY_LOW);
      hookHandler.attachHooker(ExporterProviderHook.class, new ExporterProviderHook(enclosedObjectExporterProvider),
            HookHandlerService.PRIORITY_LOWER);

      hookHandler.attachHooker(ImporterProviderHook.class, new ImporterProviderHook(enclosedEntityImporterProvider),
            HookHandlerService.PRIORITY_LOW);
      hookHandler.attachHooker(ImporterProviderHook.class, new ImporterProviderHook(enclosedObjectImporterProvider),
            HookHandlerService.PRIORITY_LOWER);

      hookHandler.attachHooker(ExImportIdProviderHook.class, entityIdProvider, HookHandlerService.PRIORITY_LOW);
   }
}
