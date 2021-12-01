package net.datenwerke.treedb.client.treedb.rpc;

import java.util.Collection;
import java.util.List;

import net.datenwerke.gxtdto.client.dtomanager.Dto;
import net.datenwerke.gxtdto.client.dtomanager.Dto2PosoMapper;
import net.datenwerke.gxtdto.client.servercommunication.exceptions.ServerCallFailedException;
import net.datenwerke.treedb.client.treedb.dto.AbstractNodeDto;
import net.datenwerke.treedb.client.treedb.dto.EntireTreeDTO;

public interface RPCTreeLoader {
	public List<AbstractNodeDto> getRoot(Dto state) throws ServerCallFailedException;
	
	public List<AbstractNodeDto> getChildren(AbstractNodeDto node, Dto state, Collection<Dto2PosoMapper> wlFilters, final Collection<Dto2PosoMapper> blFilters) throws ServerCallFailedException;
	
	public String[][] getChildrenAsFto(AbstractNodeDto node, Dto state, Collection<Dto2PosoMapper> wlFilters, final Collection<Dto2PosoMapper> blFilters) throws ServerCallFailedException;

	public EntireTreeDTO loadAll(Dto state, Collection<Dto2PosoMapper> whiteListFilters, Collection<Dto2PosoMapper> blackListFilters) throws ServerCallFailedException;
	
	public EntireTreeDTO loadAll(Dto state) throws ServerCallFailedException;
	
	public String[][] loadAllAsFto(Dto state) throws ServerCallFailedException;
	
	public AbstractNodeDto loadFullViewNode(AbstractNodeDto node, Dto state) throws ServerCallFailedException;

	public AbstractNodeDto loadNodeById(Long id, Dto state)
			throws ServerCallFailedException;
}
