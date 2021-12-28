package net.datenwerke.treedb.client.treedb.rpc;

import java.util.Collection;
import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

import net.datenwerke.gxtdto.client.dtomanager.Dto;
import net.datenwerke.gxtdto.client.dtomanager.Dto2PosoMapper;
import net.datenwerke.treedb.client.treedb.dto.AbstractNodeDto;
import net.datenwerke.treedb.client.treedb.dto.EntireTreeDTO;

public interface RPCTreeLoaderAsync {

   public void getRoot(Dto state, AsyncCallback<List<AbstractNodeDto>> callback);

   public void getChildren(AbstractNodeDto node, Dto state, Collection<Dto2PosoMapper> wlFilters,
         Collection<Dto2PosoMapper> blFilters, AsyncCallback<List<AbstractNodeDto>> callback);

   public void getChildrenAsFto(AbstractNodeDto node, Dto state, Collection<Dto2PosoMapper> wlFilters,
         Collection<Dto2PosoMapper> blFilters, AsyncCallback<String[][]> callback);

   public void loadAll(Dto state, Collection<Dto2PosoMapper> filters, Collection<Dto2PosoMapper> blackListFilters,
         AsyncCallback<EntireTreeDTO> callback);

   public void loadAll(Dto state, AsyncCallback<EntireTreeDTO> callback);

   public void loadAllAsFto(Dto state, AsyncCallback<String[][]> callback);

   public void loadFullViewNode(AbstractNodeDto node, Dto state, AsyncCallback<AbstractNodeDto> callback);

   public void loadNodeById(Long id, Dto state, AsyncCallback<AbstractNodeDto> callback);
}
