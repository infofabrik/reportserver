package net.datenwerke.gxtdto.client.utils.modelkeyprovider;

import java.util.HashMap;

import net.datenwerke.gxtdto.client.dtomanager.Dto;
import net.datenwerke.gxtdto.client.dtomanager.IdedDto;

import com.sencha.gxt.data.shared.ModelKeyProvider;

public class DtoTypeAwareIdProvider implements ModelKeyProvider<Dto> {

	private final HashMap<Dto, String> map = new HashMap<Dto, String>();
	private int cnt = 0;
	
	@Override
	public String getKey(Dto item) {
		if(item instanceof IdedDto)
			return item.getClass().getName() + "--" + String.valueOf(((IdedDto) item).getDtoId());
		
		if(! map.containsKey(item))
			map.put(item, item.getClass().getName() + "--xxid--" + cnt++);
		return map.get(item);
	}

}
