package net.datenwerke.rs.license.service;

import com.google.inject.Inject;

import net.datenwerke.gf.service.lateinit.LateInitHook;
import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;

public class LicenseStartup {

	@Inject
	public LicenseStartup(
		HookHandlerService hookHandler,
		final LicenseService licenseService
		) {
		hookHandler.attachHooker(LateInitHook.class, new LateInitHook() {
			@Override
			public void initialize() {
				licenseService.checkInit();
			}
		} );
	}
}
