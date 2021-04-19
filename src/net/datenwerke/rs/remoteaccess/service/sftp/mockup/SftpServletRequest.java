package net.datenwerke.rs.remoteaccess.service.sftp.mockup;

import net.datenwerke.rs.utils.mockrequest.MockHttpSessionWrapper;
import net.datenwerke.rs.utils.mockrequest.MockServletRequest;

import org.apache.sshd.server.session.ServerSession;

public class SftpServletRequest extends MockServletRequest {

	public void setSftpSession(ServerSession session) {
		if(getWrapperSession() instanceof MockHttpSessionWrapper)
			((MockHttpSessionWrapper)this.getWrapperSession()).setSession(session);
	}
}
