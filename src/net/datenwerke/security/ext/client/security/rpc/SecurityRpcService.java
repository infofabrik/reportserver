package net.datenwerke.security.ext.client.security.rpc;

import java.util.Collection;
import java.util.List;

import net.datenwerke.gxtdto.client.servercommunication.exceptions.ServerCallFailedException;
import net.datenwerke.security.client.security.GenericTargetIdentifier;
import net.datenwerke.security.client.security.dto.AceDto;
import net.datenwerke.security.client.security.dto.GenericSecurityTargetContainer;
import net.datenwerke.security.client.security.dto.SecurityViewInformation;
import net.datenwerke.treedb.client.treedb.dto.AbstractNodeDto;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("security_security")
public interface SecurityRpcService extends RemoteService {

	public SecurityViewInformation loadSecurityViewInformation(AbstractNodeDto node) throws ServerCallFailedException;
	
	public SecurityViewInformation loadGenericSecurityViewInformation(GenericTargetIdentifier targetIdentifier) throws ServerCallFailedException;

	public AceDto addACE(AbstractNodeDto node) throws ServerCallFailedException;
	
	public AceDto aceMoved(AbstractNodeDto node, AceDto ace, int index) throws ServerCallFailedException;
	
	public AceDto editACE(AbstractNodeDto node, AceDto ace) throws ServerCallFailedException;
	
	public void removeACEs(AbstractNodeDto node, List<AceDto> aceDtos) throws ServerCallFailedException;
	
	public AceDto addACE(GenericTargetIdentifier targetIdentifier) throws ServerCallFailedException;
	
	public AceDto aceMoved(GenericTargetIdentifier targetIdentifier, AceDto ace, int index) throws ServerCallFailedException;
	
	public AceDto editACE(GenericTargetIdentifier targetIdentifier, AceDto ace) throws ServerCallFailedException;
	
	public void removeACEs(GenericTargetIdentifier targetIdentifier, List<AceDto> aceDtos) throws ServerCallFailedException;

	public GenericSecurityTargetContainer loadGenericRights(Collection<GenericTargetIdentifier> targetIdentifiers);
	
}
