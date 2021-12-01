package net.datenwerke.rs.configservice.service.configservice;

import java.io.File;
import java.io.IOException;

import org.apache.commons.vfs2.FileObject;
import org.apache.commons.vfs2.FileSystemException;
import org.apache.commons.vfs2.Selectors;
import org.apache.commons.vfs2.VFS;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.gf.service.lateinit.LateInitHook;
import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.configservice.service.configservice.hookers.ConfigDirApplicationPropertiesProvider;
import net.datenwerke.rs.configservice.service.configservice.hooks.ConfigStoreHook;
import net.datenwerke.rs.configservice.service.configservice.terminal.ConfigCommand;
import net.datenwerke.rs.configservice.service.configservice.terminal.ConfigEchoCommand;
import net.datenwerke.rs.configservice.service.configservice.terminal.ConfigReloadCommand;
import net.datenwerke.rs.configservice.service.configservice.terminal.ConfigSubCommandHook;
import net.datenwerke.rs.configservice.service.configservice.terminal.DiffconfigfilesCommand;
import net.datenwerke.rs.configservice.service.configservice.terminal.DiffconfigfilesCreatallCommand;
import net.datenwerke.rs.configservice.service.configservice.terminal.DiffconfigfilesCreatemissingCommand;
import net.datenwerke.rs.configservice.service.configservice.terminal.DiffconfigfilesShowmissingCommand;
import net.datenwerke.rs.configservice.service.configservice.terminal.DiffconfigfilesSubCommandHook;
import net.datenwerke.rs.terminal.service.terminal.hooks.TerminalCommandHook;
import net.datenwerke.rs.terminal.service.terminal.objresolver.exceptions.ObjectResolverException;
import net.datenwerke.rs.utils.properties.ApplicationPropertiesProviderHook;

public class ConfigStartup {

   public static final String FSIMPORT_DIR = "fsimport";
   public static final String LIB_DIR = "lib";

   @Inject
   public ConfigStartup(
         HookHandlerService hookHandlerService,

         Provider<FileServerConfigStore> fileServerConfigStore,
         Provider<ConfigDirConfigStore> configDirConfigStore,

         Provider<ConfigCommand> configCommand,
         Provider<ConfigReloadCommand> configReloadCommand,
         Provider<ConfigEchoCommand> configEchoCommand,

         Provider<DiffconfigfilesCommand> diffconfigfilesCommand,
         Provider<DiffconfigfilesShowmissingCommand> diffconfigfilesShowmissingCommand,
         Provider<DiffconfigfilesCreatallCommand> diffconfigfilesCreateallCommand,
         Provider<DiffconfigfilesCreatemissingCommand> diffconfigfilesCreatemissingCommand,

         final Provider<ConfigService> configService,
         final Provider<ConfigDirService> configDirService,
         final FileserverImportHelper importHelper,

         ConfigDirApplicationPropertiesProvider configDirApplicationPropertiesProvider) {

      hookHandlerService.attachHooker(ConfigStoreHook.class, new ConfigStoreHook(configDirConfigStore));
      hookHandlerService.attachHooker(ConfigStoreHook.class, new ConfigStoreHook(fileServerConfigStore),
            HookHandlerService.PRIORITY_HIGH);

      hookHandlerService.attachHooker(TerminalCommandHook.class, configCommand);
      hookHandlerService.attachHooker(ConfigSubCommandHook.class, configReloadCommand);
      hookHandlerService.attachHooker(ConfigSubCommandHook.class, configEchoCommand);

      hookHandlerService.attachHooker(TerminalCommandHook.class, diffconfigfilesCommand);
      hookHandlerService.attachHooker(DiffconfigfilesSubCommandHook.class, diffconfigfilesShowmissingCommand);
      hookHandlerService.attachHooker(DiffconfigfilesSubCommandHook.class, diffconfigfilesCreateallCommand);
      hookHandlerService.attachHooker(DiffconfigfilesSubCommandHook.class, diffconfigfilesCreatemissingCommand);

      hookHandlerService.attachHooker(ApplicationPropertiesProviderHook.class, configDirApplicationPropertiesProvider);

      /* Add libs from LIB_DIR to webapp classloader */
      new LibDirClasspathHelper(configDirService.get()).loadLibs();

      /* Import fileserver configdir */
      hookHandlerService.attachHooker(LateInitHook.class, new LateInitHook() {
         @Override
         public void initialize() {
            if (configDirService.get().isEnabled()) {
               File importDir = new File(configDirService.get().getConfigDir(), FSIMPORT_DIR);
               if (importDir.exists() && importDir.isDirectory()) {

                  try { /* import files */
                     FileObject fileObject = VFS.getManager().resolveFile(importDir.getCanonicalPath());
                     FileObject[] files = fileObject.findFiles(Selectors.EXCLUDE_SELF);

                     for (FileObject f : files) {
                        try {
                           importHelper.importFile(new File(f.getName().getPath()));

                        } catch (IOException e) {
                           e.printStackTrace();
                        } catch (ObjectResolverException e) {
                           e.printStackTrace();
                        }
                     }

                  } catch (FileSystemException e1) {
                     e1.printStackTrace();
                  } catch (IOException e1) {
                     e1.printStackTrace();
                  }

                  configService.get().clearCache();
               }

            }
         }
      }, HookHandlerService.PRIORITY_HIGH);
   }

}
