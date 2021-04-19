package net.datenwerke.gxtdto.client.dtomanager.fto;

import net.datenwerke.gxtdto.client.dtomanager.Dto;
import net.datenwerke.gxtdto.client.dtomanager.PropertyAccessor;

import com.sencha.gxt.core.client.ValueProvider;

public interface FtoSupervisor {

	public boolean consumes(ValueProvider vp);
	
	public String adaptFtoGeneration(ValueProvider vp, Dto dto);

	public void decodeFromFto(String val, PropertyAccessor pa, Dto dto);
}
