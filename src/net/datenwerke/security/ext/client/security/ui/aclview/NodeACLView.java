package net.datenwerke.security.ext.client.security.ui.aclview;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;

import net.datenwerke.gf.client.treedb.UITree;
import net.datenwerke.security.client.security.dto.AceDto;
import net.datenwerke.security.client.security.dto.HierarchicalAceDto;
import net.datenwerke.security.client.security.dto.SecurityViewInformation;
import net.datenwerke.security.client.treedb.dto.SecuredAbstractNodeDto;
import net.datenwerke.security.ext.client.usermanager.provider.annotations.UserManagerTreeBasicSingleton;

public class NodeACLView extends BasicACLView<SecuredAbstractNodeDto> {

	@Inject
	public NodeACLView(
		@UserManagerTreeBasicSingleton UITree userManagerTree) {
		super(userManagerTree);
	}


	protected AceDto createNewACE() {
		return new HierarchicalAceDto();
	}
	
	protected void doInitialize(AsyncCallback<SecurityViewInformation> callback) {
		/* load neccessary information */
		securityDao.loadSecurityViewInformation(target, callback);
	}

	protected void doRemoveACEs(final List<AceDto> aces, AsyncCallback<Void> callback) {
		securityDao.removeACEs(target, aces, callback);
	}
	
	protected void doAddAce(AsyncCallback<AceDto> callback) {
		securityDao.addACE(target, callback);
	}
	
	protected void doAceMoved(AceDto ace, int index, AsyncCallback<AceDto> callback) {
		securityDao.aceMoved(target, ace, index, callback);
	}

	protected void doEditACE(AceDto ace, AsyncCallback<AceDto> callback) {
		securityDao.editACE(target, ace, callback);
	}
}
