package net.datenwerke.gf.service.tempfile;

import javax.inject.Provider;

import net.datenwerke.gf.service.lateinit.LateInitHook;
import net.datenwerke.gf.service.maintenance.hooks.MaintenanceTask;
import net.datenwerke.gf.service.tempfile.annotations.TempDirLocation;
import net.datenwerke.gf.service.tempfile.maintenance.TempFileMaintenance;
import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;

import com.google.inject.Inject;

public class TempFileStartup {

	@Inject
	public TempFileStartup(
		HookHandlerService hookHandler,
		TempFileMaintenance tempFileMaintenance, 
		@TempDirLocation final Provider<String> tmpDir
		){
		
		hookHandler.attachHooker(MaintenanceTask.class, tempFileMaintenance);
		
		/* synchronize tmpdirs */
		hookHandler.attachHooker(LateInitHook.class, new LateInitHook() {
			
			@Override
			public void initialize() {
				System.setProperty("java.io.tmpdir", tmpDir.get());
			}
		}, HookHandlerService.PRIORITY_HIGH);
	}
}
