package net.datenwerke.gf.server.history;

import static java.util.stream.Collectors.toList;

import java.util.List;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import net.datenwerke.gf.client.history.dto.HistoryLinkDto;
import net.datenwerke.gf.client.history.rpc.HistoryRpcService;
import net.datenwerke.gf.service.history.HistoryService;
import net.datenwerke.gxtdto.client.dtomanager.Dto;
import net.datenwerke.gxtdto.server.dtomanager.DtoService;
import net.datenwerke.security.server.SecuredRemoteServiceServlet;
import net.datenwerke.security.service.security.SecurityService;
import net.datenwerke.security.service.security.rights.Read;

@Singleton
public class HistoryRpcServiceImpl extends SecuredRemoteServiceServlet implements HistoryRpcService {

   /**
    * 
    */
   private static final long serialVersionUID = -4008561014565465375L;

   private final HistoryService historyService;
   private final DtoService dtoService;

   private final SecurityService securityService;

   @Inject
   public HistoryRpcServiceImpl(DtoService dtoService, HistoryService historyService, SecurityService securityService) {
      this.dtoService = dtoService;
      this.historyService = historyService;
      this.securityService = securityService;
   }

   @Override
   public List<HistoryLinkDto> getLinksFor(Dto dto) {
      Object obj = dtoService.loadPoso(dto);

      securityService.assertRights(obj, Read.class);

      return historyService.buildLinksFor(obj)
            .stream()
            .map(link -> (HistoryLinkDto) dtoService.createDto(link))
            .collect(toList());
   }

}
