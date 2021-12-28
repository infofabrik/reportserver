package net.datenwerke.rs.remoteaccess.service.sftp.mockup;

import org.apache.sshd.common.session.SessionContext;

import net.datenwerke.rs.utils.mockrequest.MockHttpSessionWrapper;
import net.datenwerke.rs.utils.mockrequest.MockServletRequest;

public class SftpServletRequest extends MockServletRequest {

   public void setSftpSession(SessionContext session) {
      if (getWrapperSession() instanceof MockHttpSessionWrapper)
         ((MockHttpSessionWrapper) this.getWrapperSession()).setSession(session);
   }
}
