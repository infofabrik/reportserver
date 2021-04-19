package net.datenwerke.gf.server.history;

import java.util.ArrayList;
import java.util.List;

import net.datenwerke.gf.client.history.dto.HistoryLinkDto;
import net.datenwerke.gf.client.history.rpc.HistoryRpcService;
import net.datenwerke.gf.service.history.HistoryLink;
import net.datenwerke.gf.service.history.HistoryService;
import net.datenwerke.gxtdto.client.dtomanager.Dto;
import net.datenwerke.gxtdto.server.dtomanager.DtoService;
import net.datenwerke.security.server.SecuredRemoteServiceServlet;
import net.datenwerke.security.service.security.SecurityService;
import net.datenwerke.security.service.security.SecurityTarget;
import net.datenwerke.security.service.security.exceptions.ViolatedSecurityException;
import net.datenwerke.security.service.security.rights.Read;

import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class HistoryRpcServiceImpl extends SecuredRemoteServiceServlet
		implements HistoryRpcService {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4008561014565465375L;

	private final HistoryService historyService;
	private final DtoService dtoService;

	private final SecurityService securityService;

	
	@Inject
	public HistoryRpcServiceImpl(
		DtoService dtoService,
		HistoryService historyService,
		SecurityService securityService){
		this.dtoService = dtoService;
		this.historyService = historyService;
		this.securityService = securityService;
	}


	@Override
	public List<HistoryLinkDto> getLinksFor(Dto dto) {
		Object obj = dtoService.loadPoso(dto);
		
		securityService.assertRights(obj, Read.class);
		
		List<HistoryLinkDto> list = new ArrayList<HistoryLinkDto>();
		for(HistoryLink link : historyService.buildLinksFor(obj))
			list.add((HistoryLinkDto) dtoService.createDto(link));
		
		return list;
	}
	
}
