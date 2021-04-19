package net.datenwerke.rs.remoteaccess.service.sftp;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.net.URL;
import java.security.KeyPair;
import java.util.Arrays;

import javax.persistence.EntityManager;

import org.apache.sshd.SshServer;
import org.apache.sshd.common.KeyPairProvider;
import org.apache.sshd.common.NamedFactory;
import org.apache.sshd.common.Session;
import org.apache.sshd.common.keyprovider.FileKeyPairProvider;
import org.apache.sshd.server.Command;
import org.apache.sshd.server.FileSystemFactory;
import org.apache.sshd.server.FileSystemView;
import org.apache.sshd.server.keyprovider.AbstractGeneratorHostKeyProvider;
import org.apache.sshd.server.session.ServerSession;
import org.bouncycastle.openssl.PEMReader;
import org.bouncycastle.openssl.PEMWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.persist.UnitOfWork;

import net.datenwerke.gf.service.properties.PropertiesService;
import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.configservice.service.configservice.hooks.ReloadConfigNotificationHook;
import net.datenwerke.rs.configservice.service.configservice.hooks.adapter.ReloadConfigNotificationHookAdapter;
import net.datenwerke.rs.remoteaccess.service.sftp.annotations.KeyLocation;
import net.datenwerke.rs.remoteaccess.service.sftp.annotations.SftpEnabled;
import net.datenwerke.rs.remoteaccess.service.sftp.annotations.SftpPort;
import net.datenwerke.rs.utils.misc.Nullable;

public class SftpServiceImpl implements SftpService {

	private final static String PROP_HOST_KEY = "rs:sftp:hostkey";
	
	private final Provider<SftpFileSystemView> fileSystemFactoryProvider;
	private final Provider<String> keyLocation;
	private final Provider<Boolean> sftpEnabled;
	private final Provider<SftpAuthenticator> authenticatorProvider;
	private final Provider<RsSftpSubSystemFactory> subSystemFactoryProvider;
	private final Provider<PropertiesService> propertiesService;
	
	
	private final Logger logger = LoggerFactory.getLogger(getClass().getName());
	
	private Provider<Integer> port;
	private SshServer sshd;

	private Provider<EntityManager> entityManagerProvider;

	private Provider<UnitOfWork> unitOfWorkProvider;

	
	

	@Inject
	public SftpServiceImpl(
		Provider<SftpFileSystemView> fileSystemFactoryProvider,
		Provider<SftpAuthenticator> authenticatorProvider,
		Provider<RsSftpSubSystemFactory> subSystemFactoryProvider,
		Provider<PropertiesService> propertiesService,
		
		Provider<EntityManager> entityManagerProvider,
		Provider<UnitOfWork> unitOfWorkProvider,
		
		HookHandlerService hookhandler,
		@Nullable @KeyLocation Provider<String> keyLocation,	
		@SftpPort Provider<Integer> port,
		@SftpEnabled Provider<Boolean> sftpEnabled
		){
		this.fileSystemFactoryProvider = fileSystemFactoryProvider;
		this.authenticatorProvider = authenticatorProvider;
		this.subSystemFactoryProvider = subSystemFactoryProvider;
		this.propertiesService = propertiesService;
		this.entityManagerProvider = entityManagerProvider;
		this.unitOfWorkProvider = unitOfWorkProvider;
		this.keyLocation = keyLocation;
		this.port = port;
		this.sftpEnabled = sftpEnabled;
		
		hookhandler.attachHooker(ReloadConfigNotificationHook.class, new ReloadConfigNotificationHookAdapter() {
			
			@Override
			public void reloadConfig() {
				try {
					shutdown();
					start();
				} catch (Exception e) {
					logger.warn("Failed to restart sftp server", e);
				}
			}
		});
	}
	
	@Override
	public void start() throws IOException {
		if(! sftpEnabled.get()){
			logger.info("SFTP server disabled. Internal SFTP remains inactive.");
			return;
		}
		
		if(null == keyLocation.get()){
			logger.info("No key location given for internal SFTP server. Internal SFTP remains inactive.");
			return;
		}
		
		sshd = SshServer.setUpDefaultServer();
		sshd.setPort(port.get());
		
		String keylo = keyLocation.get();
		if(keylo.contains(":")){
			sshd.setKeyPairProvider(new UrlKeyPairProvider(new URL[]{new URL(keyLocation.get())}));
		}else if(keylo.equals("$generated")){
			sshd.setKeyPairProvider(getGeneratorKeyPairProvider());
		}else{
			sshd.setKeyPairProvider(new FileKeyPairProvider(new String[]{keyLocation.get()}));
		}

		final SftpAuthenticator authenticator = authenticatorProvider.get();
		sshd.setPasswordAuthenticator(authenticator);
		
		sshd.setSubsystemFactories(Arrays.<NamedFactory<Command>>asList(subSystemFactoryProvider.get()));
		
		sshd.setFileSystemFactory(new FileSystemFactory() {
			@Override
			public FileSystemView createFileSystemView(Session session)
					throws IOException {
				SftpFileSystemView view = fileSystemFactoryProvider.get();
				view.setSession((ServerSession) session);
				view.setAuthenticator(authenticator);
				return view;
			}
		});
		
		sshd.start();
	}


	private KeyPairProvider getGeneratorKeyPairProvider() throws IOException {
		File tmpfile = File.createTempFile("rssftptmp", "");
		return new AbstractGeneratorHostKeyProvider(tmpfile.getAbsolutePath()){

			@Override
			protected KeyPair doReadKeyPair(InputStream is) throws Exception {
				String pem = propertiesService.get().get(PROP_HOST_KEY);
				if(null == pem)
					return null;
				PEMReader r = new PEMReader(new StringReader(pem));
				return (KeyPair) r.readObject();
			}



			@Override
			protected void doWriteKeyPair(KeyPair kp, OutputStream os) throws Exception {
				UnitOfWork unitOfWork = unitOfWorkProvider.get();
				EntityManager em = null;
				em = entityManagerProvider.get();
				em.getTransaction().begin();
				boolean success = false;
				try{
					StringWriter sw = new StringWriter();
					PEMWriter w = new PEMWriter(sw);
					w.writeObject(kp);
					w.flush();
					propertiesService.get().setProperty(PROP_HOST_KEY, sw.toString());
					success = true;
				} finally {
					try {
						if(null != em && success)
							em.getTransaction().commit();
						else if(null != em)
							em.getTransaction().rollback();
					} finally {
						unitOfWork.end();
					}
				}
			}

		};
	}

	@Override
	public boolean isActive() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isShutdown() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isTerminated() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void shutdown() {
		try {
			if(null != sshd)
				sshd.stop();
		} catch (InterruptedException e) {
			logger.warn("An exception occured while stopping the sftp service", e);
		}
	}


}
