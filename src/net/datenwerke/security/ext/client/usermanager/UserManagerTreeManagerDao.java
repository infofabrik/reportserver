package net.datenwerke.security.ext.client.usermanager;

import com.google.inject.Inject;

import net.datenwerke.gxtdto.client.dtomanager.Dto2PosoMapper;
import net.datenwerke.security.client.usermanager.dto.AbstractUserManagerNodeDto;
import net.datenwerke.security.ext.client.usermanager.rpc.UserManagerTreeManagerAsync;
import net.datenwerke.treedb.client.treedb.TreeDbManagerDao;

public class UserManagerTreeManagerDao extends TreeDbManagerDao {

	@Inject
	public UserManagerTreeManagerDao(UserManagerTreeManagerAsync treeManager){
		super(treeManager);
	}
	
	@Override
	public Dto2PosoMapper getBaseNodeMapper() {
		return AbstractUserManagerNodeDto.newPosoMapper();
	}
}
