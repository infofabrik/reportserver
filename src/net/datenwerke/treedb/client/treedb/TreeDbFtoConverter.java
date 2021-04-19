package net.datenwerke.treedb.client.treedb;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.datenwerke.gxtdto.client.dtoinfo.DtoInformationService;
import net.datenwerke.gxtdto.client.dtomanager.DtoView;
import net.datenwerke.gxtdto.client.dtomanager.PropertyAccessor;
import net.datenwerke.gxtdto.client.dtomanager.fto.FtoSupervisor;
import net.datenwerke.treedb.client.treedb.dto.decorator.AbstractNodeDtoDec;

import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.i18n.client.DateTimeFormat.PredefinedFormat;
import com.google.inject.Inject;

public class TreeDbFtoConverter {
	
	private final DtoInformationService dtoInformationService;
	
	private List<TypeConverter> typeConverters = new ArrayList<TreeDbFtoConverter.TypeConverter>();
	
	@Inject
	public TreeDbFtoConverter(DtoInformationService dtoInformationService) {
		this.dtoInformationService = dtoInformationService;
	}
	
	
	public AbstractNodeDtoDec convert(String[] fto){
		/* parse key/value pairs from fto */
		String type = null;
		Long id = null;
		for(String s : fto){
			if(null == s)
				continue;
			int idx = s.indexOf(":");
			if(idx > -1){
				String key = s.substring(0, idx);
				String val = s.substring(idx + 1);
				
				if("_fto_dto_type".equals(key))
					type = val;
				else if("_fto_dto_id".equals(key))
					id = Long.parseLong(val);
			}
		}
		
		/* create dto instance */
		AbstractNodeDtoDec dto = dtoInformationService.createInstance(type);
		dto.silenceEvents(true);
		int cnt = 0;
		for(PropertyAccessor pa : dto.getPropertyAccessors()){
			String val = fto[cnt++];
			if(null == val || val.startsWith("_fto_ignore_this:") || val.startsWith("_fto_dto_type:") || val.startsWith("_fto_dto_id:"))
				continue;
			
			try{
				if(dto instanceof FtoSupervisor && ((FtoSupervisor)dto).consumes(pa) ){
					((FtoSupervisor)dto).decodeFromFto(val, pa, dto);
				} else {
					Object convertedVal = valueOf(val, pa.getType());
					pa.setValue(dto, convertedVal);
				}
			}catch (Exception e) {
				// ignore
			}
		}

		// id setzen
		if(null != id && null == dto.getDtoId())
			dto.setDtoId(id);
		
		/* set view */
		dto.setDtoView(DtoView.FTO);
		
		/* turn on events again */
		dto.silenceEvents(false);
		
		return dto;
	}
	
	private Object valueOf(String str, Class<?> type){
		
		if(type.equals(String.class))
			return String.valueOf(str);
		
		if(type.equals(Boolean.class))
			return Boolean.valueOf(str);
		
		if(type.equals(Byte.class))
			return Byte.valueOf(str);
		
		if(type.equals(Double.class))
			return Double.valueOf(str);
		
		if(type.equals(Float.class))
			return Float.valueOf(str);
		
		if(type.equals(Integer.class))
			return Integer.valueOf(str);
		
		if(type.equals(Long.class))
			return Long.valueOf(str);
		
		if(type.equals(Short.class))
			return Short.valueOf(str);
		
		for(TypeConverter tc : typeConverters){
			if(tc.consumes(type))
				return tc.convert(type, str);
		}
		
		return null;
	}
	
	public void addTypeConverter(TypeConverter tc){
		typeConverters.add(tc);
	}
	
	public static interface TypeConverter {
		public Object convert(Class<?> type, String val);
		public boolean consumes(Class<?> type);
	}
	

}
