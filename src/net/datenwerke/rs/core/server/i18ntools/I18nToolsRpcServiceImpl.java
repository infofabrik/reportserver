package net.datenwerke.rs.core.server.i18ntools;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;

import net.datenwerke.gxtdto.server.dtomanager.DtoService;
import net.datenwerke.rs.core.client.i18tools.dto.FormatPatternsDto;
import net.datenwerke.rs.core.client.i18tools.rpc.I18nToolsRpcService;
import net.datenwerke.rs.core.service.i18ntools.I18nToolsService;
import net.datenwerke.security.server.SecuredRemoteServiceServlet;
import net.datenwerke.security.service.authenticator.AuthenticatorService;
import net.datenwerke.security.service.security.annotation.SecurityChecked;
import net.datenwerke.security.service.usermanager.UserManagerService;
import net.datenwerke.security.service.usermanager.entities.User;

import com.google.inject.persist.Transactional;

@Singleton
public class I18nToolsRpcServiceImpl extends SecuredRemoteServiceServlet implements I18nToolsRpcService {

	private static final long serialVersionUID = -4361640926787911944L;
	private I18nToolsService i18nToolsService;
	private Provider<AuthenticatorService> authenticatorService;
	private UserManagerService userManagerService;
	private DtoService dtoService;
	
	
	@Inject
	public I18nToolsRpcServiceImpl(
			I18nToolsService i18nToolsService, 
			UserManagerService userManagerService,
			Provider<AuthenticatorService> authenticatorService, 
			DtoService dtoService) {
		
		this.i18nToolsService = i18nToolsService;
		this.userManagerService = userManagerService;
		this.authenticatorService = authenticatorService;
		this.dtoService = dtoService;
	}
	
	@Override
	public String getDecimalSeparator() {
		User currentUser = authenticatorService.get().getCurrentUser();
		String sep = i18nToolsService.getUserDecimalSeparator(currentUser);
		if(null != sep)
			return sep;
		
		return i18nToolsService.getSystemDecimalSeparator();
	}
	
	@Override
	@Transactional(rollbackOn=Exception.class)
	public void setDecimalSeparator(String separatorChar) {
		User currentUser = authenticatorService.get().getCurrentUser();
		i18nToolsService.setUserDecimalSeparator(currentUser, separatorChar);
		
		userManagerService.merge(currentUser);
	}
	
	@Override
	@SecurityChecked(loginRequired=false)
	public FormatPatternsDto getFormatPatterns() {
		return (FormatPatternsDto) dtoService.createDto(i18nToolsService.getFormatPatterns());
	}
}
