package net.datenwerke.gxtdto.client.dtomanager.events;

import net.datenwerke.gxtdto.client.dtomanager.Dto;

public interface DtoBaseEvent extends DtoEvent {

	public abstract Dto getDto();

}