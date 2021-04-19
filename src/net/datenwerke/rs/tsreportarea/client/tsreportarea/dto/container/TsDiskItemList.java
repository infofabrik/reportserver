package net.datenwerke.rs.tsreportarea.client.tsreportarea.dto.container;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import net.datenwerke.gxtdto.client.dtomanager.DtoContainer;
import net.datenwerke.gxtdto.client.dtomanager.DtoRegistrar;
import net.datenwerke.rs.tsreportarea.client.tsreportarea.dto.AbstractTsDiskNodeDto;

/**
 * 
 *
 */
public class TsDiskItemList implements Serializable, DtoContainer {

	private List<AbstractTsDiskNodeDto> items = new ArrayList<AbstractTsDiskNodeDto>();
	
	private List<AbstractTsDiskNodeDto> path = new ArrayList<AbstractTsDiskNodeDto>();
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8645371464170972130L;

	@Override
	public void registerDtos(DtoRegistrar registrar) {
		for(AbstractTsDiskNodeDto node : path)
			registrar.registerDto(node);
		for(AbstractTsDiskNodeDto node : items)
			registrar.registerDto(node);
	}

	public void setPath(List<AbstractTsDiskNodeDto> path) {
		this.path = path;
	}

	public List<AbstractTsDiskNodeDto> getPath() {
		return new ArrayList<AbstractTsDiskNodeDto>(path);
	}

	public void setItems(List<AbstractTsDiskNodeDto> items) {
		this.items = items;
	}

	public List<AbstractTsDiskNodeDto> getItems() {
		return new ArrayList<AbstractTsDiskNodeDto>(items);
	}

	public AbstractTsDiskNodeDto getItem(Long id) {
		if(null == id)
			return null;
		
		for(AbstractTsDiskNodeDto item : getItems())
			if(id.equals(item.getId()))
				return item;
		
		return null;
	}

}
