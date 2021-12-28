package net.datenwerke.rs.remoteaccess.service.sftp;

import java.io.IOException;

import javax.persistence.EntityManager;

import org.apache.sshd.common.util.buffer.Buffer;
import org.apache.sshd.server.channel.ChannelSession;
import org.apache.sshd.server.session.ServerSession;
import org.apache.sshd.sftp.common.SftpConstants;
import org.apache.sshd.sftp.server.SftpEventListener;
import org.apache.sshd.sftp.server.SftpSubsystem;
import org.apache.sshd.sftp.server.SftpSubsystemConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Provider;
import com.google.inject.persist.UnitOfWork;

public class RsSftpSubsystem extends SftpSubsystem {

   protected static final Logger log = LoggerFactory.getLogger(RsSftpSubsystem.class.getName());

   private final Provider<UnitOfWork> unitOfWorkProvider;
   private final Provider<EntityManager> entityManagerProvider;

   public RsSftpSubsystem(Provider<UnitOfWork> unitOfWorkProvider, Provider<EntityManager> entityManagerProvider,
         SftpSubsystemConfigurator conf, ChannelSession channel) {
      super(channel, conf);
      this.unitOfWorkProvider = unitOfWorkProvider;
      this.entityManagerProvider = entityManagerProvider;
   }

   @Override
   protected void process(Buffer buffer) throws IOException {
      /* start super.process() */
      int length = buffer.getInt();
      int type = buffer.getUByte();
      int id = buffer.getInt();
      if (log.isDebugEnabled()) {
         log.debug("process({})[length={}, type={}, id={}] processing", getServerSession(), length,
               SftpConstants.getCommandMessageName(type), id);
      }
      try {
         SftpEventListener listener = getSftpEventListenerProxy();
         ServerSession session = getServerSession();
         listener.received(session, type, id);
      } catch (IOException | RuntimeException e) {
         if (type == SftpConstants.SSH_FXP_INIT) {
            throw e;
         }
         sendStatus(prepareReply(buffer), id, e, type);
         return;
      }
      /* end super.process() */

      UnitOfWork unitOfWork = null;
      EntityManager em = null;
      boolean success = false;
      try {
         unitOfWork = unitOfWorkProvider.get();
         unitOfWork.begin();

         em = entityManagerProvider.get();
         em.getTransaction().begin();

         doProcess(buffer, length, type, id);

         success = true;
      } catch (Exception e) {
         log.warn(e.getMessage(), e);
      } finally {
         try {
            if (null != em) {
               if (success)
                  em.getTransaction().commit();
               else
                  em.getTransaction().rollback();
            }
         } finally {
            if (null != unitOfWork)
               unitOfWork.end();
         }
      }
   }

}
