package net.datenwerke.gxtdto.client.dtomanager.events;

import java.util.List;

import net.datenwerke.gxtdto.client.dtomanager.Dto;

public class DtoAddedEvent extends DtoBaseEventImpl {
	
	private List<Dto> dtos;

	public DtoAddedEvent(List<Dto> dtos) {
		super(dtos.isEmpty() || null == dtos ? null : dtos.get(0));
		this.dtos = dtos;
	}
	
	public List<Dto> getDtos() {
		return dtos;
	}
}
