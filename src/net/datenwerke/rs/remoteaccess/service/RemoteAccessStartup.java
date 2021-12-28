package net.datenwerke.rs.remoteaccess.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;

import net.datenwerke.gf.service.lateinit.LateInitHook;
import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.remoteaccess.service.sftp.SftpService;
import net.datenwerke.rs.remoteaccess.service.sftp.genrights.SftpSecurityTarget;
import net.datenwerke.security.service.security.SecurityService;

public class RemoteAccessStartup {

	protected final Logger logger = LoggerFactory.getLogger(getClass().getName());

	@Inject
	public RemoteAccessStartup(

			SecurityService securityService,
			HookHandlerService hookHandlerService,
			final SftpService sftpService
			){

		securityService.registerSecurityTarget(SftpSecurityTarget.class);

		hookHandlerService.attachHooker(LateInitHook.class, new LateInitHook() {

			@Override
			public void initialize() {
				try{
					sftpService.start();
				}catch(Exception e){
					logger.warn( "Failed to start SFTP Server", e);
				}
			}
		});

	}
}
