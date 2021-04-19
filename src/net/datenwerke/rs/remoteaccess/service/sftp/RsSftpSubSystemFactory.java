package net.datenwerke.rs.remoteaccess.service.sftp;

import org.apache.sshd.common.NamedFactory;
import org.apache.sshd.server.Command;

import com.google.inject.Inject;
import com.google.inject.Provider;

public class RsSftpSubSystemFactory implements NamedFactory<Command> {

	private final Provider<RsSftpSubSystem> subSystem;

	@Inject
    public RsSftpSubSystemFactory(
		Provider<RsSftpSubSystem> subSystem
    	) {
		
		this.subSystem = subSystem;
    }

    public Command create() {
        return subSystem.get();
    }

    public String getName() {
        return "sftp";
    }
}
