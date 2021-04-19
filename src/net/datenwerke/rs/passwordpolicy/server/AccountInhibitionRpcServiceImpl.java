package net.datenwerke.rs.passwordpolicy.server;

import net.datenwerke.gxtdto.client.servercommunication.exceptions.ServerCallFailedException;
import net.datenwerke.rs.passwordpolicy.client.accountinhibition.AccountInhibitionConfiguration;
import net.datenwerke.rs.passwordpolicy.client.accountinhibition.dto.InhibitionState;
import net.datenwerke.rs.passwordpolicy.client.accountinhibition.rpc.AccountInhibitionRpcService;
import net.datenwerke.rs.passwordpolicy.service.BsiPasswordPolicy;
import net.datenwerke.rs.passwordpolicy.service.BsiPasswordPolicyService;
import net.datenwerke.rs.passwordpolicy.service.BsiPasswordPolicyUserMetadata;
import net.datenwerke.security.client.usermanager.dto.UserDto;
import net.datenwerke.security.server.SecuredRemoteServiceServlet;
import net.datenwerke.security.service.security.SecurityService;
import net.datenwerke.security.service.security.SecurityServiceSecuree;
import net.datenwerke.security.service.security.annotation.ArgumentVerification;
import net.datenwerke.security.service.security.annotation.RightsVerification;
import net.datenwerke.security.service.security.annotation.SecurityChecked;
import net.datenwerke.security.service.security.rights.Read;
import net.datenwerke.security.service.security.rights.Write;
import net.datenwerke.security.service.usermanager.UserManagerService;
import net.datenwerke.security.service.usermanager.entities.AbstractUserManagerNode;
import net.datenwerke.security.service.usermanager.entities.User;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.inject.name.Named;
import com.google.inject.persist.Transactional;

/**
 * 
 *
 */
@Singleton
public class AccountInhibitionRpcServiceImpl extends SecuredRemoteServiceServlet implements AccountInhibitionRpcService {

	/**
	 * 
	 */
	private static final long serialVersionUID = -950374241170257726L;
	
	private final SecurityService securityService;
	private final UserManagerService userManagerService;
	private final BsiPasswordPolicy bsiPasswordPolicy;

	private final BsiPasswordPolicyService bsiPasswordPolicyService;
	
	
	@Inject
	public AccountInhibitionRpcServiceImpl(
			SecurityService securityService,
			UserManagerService userManagerService,
			BsiPasswordPolicy bsiPasswordPolicy, 
			BsiPasswordPolicyService bsiPasswordPolicyService) {
		
		this.securityService = securityService;
		this.userManagerService = userManagerService;
		this.bsiPasswordPolicy = bsiPasswordPolicy;
		this.bsiPasswordPolicyService = bsiPasswordPolicyService;
	}
	
	
	
	@Override
	@SecurityChecked(
			argumentVerification = {
			@ArgumentVerification(name = "user",isDto = true,verify = @RightsVerification(rights=Read.class))}
	)
	@Transactional(rollbackOn={Exception.class})
	public InhibitionState getInhibitionState(@Named("user")UserDto user) {
		
		AbstractUserManagerNode node = userManagerService.getNodeById(user.getId());
		if(node instanceof User){

			BsiPasswordPolicyUserMetadata metadata = bsiPasswordPolicyService.getUserMetadata((User) node);
			
			if(null != metadata.getAccountInhibited() && metadata.getAccountInhibited()){
				return InhibitionState.INHIBITED_ADMINISTRATIVELY;
			}
			
			if(metadata.getFailedLoginCount() > bsiPasswordPolicy.getAccountLockoutThreshold()){
				return InhibitionState.BLOCKED_TEMPORARILY;
			}
			
			return InhibitionState.USABLE;
		}
		return null;
	}

	private void inhibitAccount(@Named("user")User user){
		BsiPasswordPolicyUserMetadata metadata = bsiPasswordPolicyService.getUserMetadata((User) user);
		metadata.setAccountInhibited(true);
		bsiPasswordPolicyService.updateUserMetadata(user, metadata);
		
	}

	private void deactivateInhibition(@Named("user")User user) {
		BsiPasswordPolicyUserMetadata metadata = bsiPasswordPolicyService.getUserMetadata(user);
		metadata.setAccountInhibited(false);
		bsiPasswordPolicyService.updateUserMetadata(user, metadata);
	}

	private void unblockAccount(@Named("user")User user){
		BsiPasswordPolicyUserMetadata metadata = bsiPasswordPolicyService.getUserMetadata(user);
		metadata.setFailedLoginCount(0);
		bsiPasswordPolicyService.updateUserMetadata(user, metadata);
	}
	
	@Override
	@Transactional(rollbackOn={Exception.class})
	public void applyAccountInhibitionConfiguration(AccountInhibitionConfiguration accountInhibitionConfiguration) throws ServerCallFailedException {
		AbstractUserManagerNode node = userManagerService.getNodeById(accountInhibitionConfiguration.getUserId());
		
		if(! securityService.checkRights(node, SecurityServiceSecuree.class, Write.class))
			return;
		
		if(node instanceof User){
			if(accountInhibitionConfiguration.isInhibitionState()){
				inhibitAccount((User)node);
			}else{
				deactivateInhibition((User)node);
				unblockAccount((User)node);
			}
		}
		
		BsiPasswordPolicyUserMetadata metadata = bsiPasswordPolicyService.getUserMetadata((User)node);
		metadata.setAccountExpirationDate(accountInhibitionConfiguration.getExpirationDate());
		bsiPasswordPolicyService.updateUserMetadata((User) node, metadata);

		userManagerService.merge((User)node);
	}
	
	
	@SecurityChecked(
			argumentVerification = {
			@ArgumentVerification(name = "user",isDto = true,verify = @RightsVerification(rights=Read.class))}
	)
	@Transactional(rollbackOn={Exception.class})
	@Override
	public AccountInhibitionConfiguration getAccountInhibitionConfiguration(@Named("user")UserDto user){
		AbstractUserManagerNode node = userManagerService.getNodeById(user.getId());
		BsiPasswordPolicyUserMetadata metadata = bsiPasswordPolicyService.getUserMetadata((User) node);
		
		AccountInhibitionConfiguration accountInhibitionModel = new AccountInhibitionConfiguration();
		accountInhibitionModel.setInhibitionState(getInhibitionState(user).equals(InhibitionState.INHIBITED_ADMINISTRATIVELY));
		accountInhibitionModel.setBlockedTemporarily(getInhibitionState(user).equals(InhibitionState.BLOCKED_TEMPORARILY));
		accountInhibitionModel.setExpirationDate(metadata.getAccountExpirationDate());
		accountInhibitionModel.setUserId(user.getId());
		
		return accountInhibitionModel;
	}

}
