package net.datenwerke.rs.terminal.service.terminal.vfs;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.terminal.service.terminal.hooks.TerminalCommandHook;
import net.datenwerke.rs.terminal.service.terminal.hooks.TerminalSessionDeamonHook;
import net.datenwerke.rs.terminal.service.terminal.objresolver.hooks.ObjectResolverHook;
import net.datenwerke.rs.terminal.service.terminal.vfs.commands.VfsCommandCd;
import net.datenwerke.rs.terminal.service.terminal.vfs.commands.VfsCommandCp;
import net.datenwerke.rs.terminal.service.terminal.vfs.commands.VfsCommandListPath;
import net.datenwerke.rs.terminal.service.terminal.vfs.commands.VfsCommandLs;
import net.datenwerke.rs.terminal.service.terminal.vfs.commands.VfsCommandMkdir;
import net.datenwerke.rs.terminal.service.terminal.vfs.commands.VfsCommandMv;
import net.datenwerke.rs.terminal.service.terminal.vfs.commands.VfsCommandPwd;
import net.datenwerke.rs.terminal.service.terminal.vfs.commands.VfsCommandRm;
import net.datenwerke.rs.terminal.service.terminal.vfs.hookers.VfsObjectResolver;

public class VirtualFileSystemStartup {

   @Inject
   public VirtualFileSystemStartup(HookHandlerService hookHandler,

         Provider<VirtualFileSystemDeamon> vfsDeamonProvider, Provider<VfsObjectResolver> objectResolverProvider,

         Provider<VfsCommandCd> cdCommandProvider, Provider<VfsCommandCp> cpCommandProvider,
         Provider<VfsCommandLs> lsCommandProvider, Provider<VfsCommandListPath> listPathCommandProvider,
         Provider<VfsCommandMkdir> mkdirCommandProvider, Provider<VfsCommandMv> mvCommandProvider,
         Provider<VfsCommandPwd> pwdCommandProvider, Provider<VfsCommandRm> rmCommandProvider) {

      hookHandler.attachHooker(TerminalSessionDeamonHook.class, vfsDeamonProvider);

      hookHandler.attachHooker(ObjectResolverHook.class, objectResolverProvider, HookHandlerService.PRIORITY_LOWER);

      hookHandler.attachHooker(TerminalCommandHook.class, cdCommandProvider);
      hookHandler.attachHooker(TerminalCommandHook.class, cpCommandProvider);
      hookHandler.attachHooker(TerminalCommandHook.class, lsCommandProvider);
      hookHandler.attachHooker(TerminalCommandHook.class, listPathCommandProvider);
      hookHandler.attachHooker(TerminalCommandHook.class, mkdirCommandProvider);
      hookHandler.attachHooker(TerminalCommandHook.class, mvCommandProvider);
      hookHandler.attachHooker(TerminalCommandHook.class, pwdCommandProvider);
      hookHandler.attachHooker(TerminalCommandHook.class, rmCommandProvider);
   }
}
