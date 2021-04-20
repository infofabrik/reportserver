package net.datenwerke.rs.remoteaccess.service.sftp;

import java.io.IOException;

import javax.persistence.EntityManager;

import org.apache.sshd.common.util.GenericUtils;
import org.apache.sshd.server.channel.ChannelSession;
import org.apache.sshd.server.command.Command;
import org.apache.sshd.sftp.server.SftpSubsystemFactory;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.persist.UnitOfWork;

public class RsSftpSubSystemFactory extends SftpSubsystemFactory{

   
   private final Provider<UnitOfWork> unitOfWorkProvider;
   private final Provider<EntityManager> entityManagerProvider;
   
   @Inject
   public RsSftpSubSystemFactory(Provider<UnitOfWork> unitOfWorkProvider, Provider<EntityManager> entityManagerProvider) {
      this.unitOfWorkProvider = unitOfWorkProvider;
      this.entityManagerProvider = entityManagerProvider;
   }
   
   @Override
   public Command createSubsystem(ChannelSession channel) throws IOException {
       RsSftpSubsystem subsystem = new RsSftpSubsystem(
               resolveExecutorService(),
               getUnsupportedAttributePolicy(), getFileSystemAccessor(),
               getErrorStatusDataHandler(), unitOfWorkProvider, entityManagerProvider);
       GenericUtils.forEach(getRegisteredListeners(), subsystem::addSftpEventListener);
       return subsystem;
   }
}
