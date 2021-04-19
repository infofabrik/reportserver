package net.datenwerke.gxtdto.server.dtomanager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;

import com.google.inject.Inject;

import net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoGenerator;
import net.datenwerke.dtoservices.dtogenerator.poso2dtogenerator.interfaces.Poso2DtoGenerator;
import net.datenwerke.gxtdto.client.dtomanager.Dto;
import net.datenwerke.gxtdto.client.dtomanager.DtoView;
import net.datenwerke.gxtdto.client.dtomanager.IdedDto;

public class DtoCreationHelper {

	private final DtoService dtoService;
	
	public boolean modeUnmanaged = false;
	
	private final Map<Object, List<CallbackOnPosoCreation>> callbackOnPosoMap = new IdentityHashMap<Object, List<CallbackOnPosoCreation>>();
	private final Map<Object, List<CallbackOnDtoCreation>> callbackOnDtoMap = new IdentityHashMap<Object, List<CallbackOnDtoCreation>>();
	
	
	private final Map<Object, Object> mergedPosoMap = new IdentityHashMap<Object, Object>();
	private final Map<Object, Object> createdPosoMap = new IdentityHashMap<Object, Object>();
	private final Map<Object, Object> createdUnmanagedPosoMap = new IdentityHashMap<Object, Object>();
	
	private final Map<Object, Object> createdDtoMap = new IdentityHashMap<Object, Object>();
	
	private final Map<Object, Dto2PosoGenerator> mergedPosoGeneratorMap = new IdentityHashMap<Object, Dto2PosoGenerator>();
	private final Map<Object, Dto2PosoGenerator> createdUnmanagedPosoGeneratorMap = new IdentityHashMap<Object, Dto2PosoGenerator>();
	private final Map<Object, Dto2PosoGenerator> createdPosoGeneratorMap = new IdentityHashMap<Object, Dto2PosoGenerator>();
	
	private final Map<Object, Poso2DtoGenerator> createdDtoGeneratorMap = new IdentityHashMap<Object, Poso2DtoGenerator>();
	
	private final Map<Class<?>, Map<Object, Object>> dto2PosoMap = new IdentityHashMap<Class<?>, Map<Object, Object>>();
	
	@Inject
	public DtoCreationHelper(
		DtoService dtoService) {
		
		super();
		this.dtoService = dtoService;
	}
	
	public void onDtoCreation(Object dto, Object poso, CallbackOnDtoCreation callback){
		if(null == dto || dto.getClass().isEnum())
			return;
		
		if(null == callbackOnDtoMap.get(poso))
			callbackOnDtoMap.put(poso, new ArrayList<CallbackOnDtoCreation>());
		callbackOnDtoMap.get(poso).add(callback);
		
		/* check if we have already created dto */
		Object bestDto = createdDtoMap.get(poso);
		if(null == bestDto || null != bestDto && bestDto instanceof Dto && dto instanceof Dto && ((Dto)bestDto).getDtoView().compareTo(((Dto)dto).getDtoView()) < 0){
			createdDtoMap.put(poso, dto);
			bestDto = dto;
		} 
		
		for(CallbackOnDtoCreation cb : callbackOnDtoMap.get(poso))
			cb.callback(bestDto);
	}
	
	public void dtoCreatedFor(Object poso, Object dto, Poso2DtoGenerator<?, ?> generator){
		if(poso.getClass().isEnum() || poso instanceof java.lang.Enum || (null != poso.getClass().getSuperclass() && java.lang.Enum.class.equals(poso.getClass().getSuperclass())))
			return;
		
		if(createdDtoMap.containsKey(poso))
			throw new IllegalArgumentException("Dto has already been created");
		
		createdDtoGeneratorMap.put(poso, generator);
		createdDtoMap.put(poso, dto);
		
		if(callbackOnDtoMap.containsKey(poso))
			for(CallbackOnDtoCreation callback : callbackOnDtoMap.get(poso))
				callback.callback(dto);
	}
	

	public void onPosoCreation(Object dto, CallbackOnPosoCreation callback){
		if(null == callbackOnPosoMap.get(dto))
			callbackOnPosoMap.put(dto, new ArrayList<CallbackOnPosoCreation>());
		callbackOnPosoMap.get(dto).add(callback);

		if(createdPosoMap.containsKey(dto))
			callback.callback(createdPosoMap.get(dto));
	}
	
	public void unmanagedPosoCreatedFor(Object dto, Object createUnmanagedPoso, Dto2PosoGenerator<?, ?> generator){
		if(dto.getClass().isEnum())
			return;
		
		if(createdUnmanagedPosoMap.containsKey(dto))
			throw new IllegalArgumentException("Poso has already been created");

		if(dto instanceof IdedDto){
			Object posoId = ((IdedDto)dto).getDtoId();
			if(null != posoId){
				if(! dto2PosoMap.containsKey(dto.getClass()))
					dto2PosoMap.put(dto.getClass(), new HashMap<Object, Object>());
				dto2PosoMap.get(dto.getClass()).put(posoId, createUnmanagedPoso);
			}
		}
		
		createdUnmanagedPosoMap.put(dto, createUnmanagedPoso);
		createdUnmanagedPosoGeneratorMap.put(dto, generator);
	}
	
	public void posoCreatedFor(Object dto, Object poso, Dto2PosoGenerator<?, ?> generator){
		if(dto.getClass().isEnum() || dto instanceof java.lang.Enum || (null != dto.getClass().getSuperclass() && java.lang.Enum.class.equals(dto.getClass().getSuperclass())))
			return;
		
		if(createdPosoMap.containsKey(dto))
			throw new IllegalArgumentException("Poso has already been created");
		
		createdPosoGeneratorMap.put(dto, generator);
		createdPosoMap.put(dto, poso);
		
		if(callbackOnPosoMap.containsKey(dto))
			for(CallbackOnPosoCreation callback : callbackOnPosoMap.get(dto))
				callback.callback(poso);
	}
	
	public void posoMergedFor(Object dto, Object poso, Dto2PosoGenerator<?, ?> generator){
		if(dto.getClass().isEnum() || dto instanceof java.lang.Enum || (null != dto.getClass().getSuperclass() && java.lang.Enum.class.equals(dto.getClass().getSuperclass())))
			return;
		
		/* arno: not sure if this is a good idea to have this check also for merged */
		if(mergedPosoMap.containsKey(dto))
			throw new IllegalArgumentException("Poso has already been merged");
		
		mergedPosoGeneratorMap.put(dto, generator);
		mergedPosoMap.put(dto, poso);
	}
	
	@SuppressWarnings("rawtypes")
	public void posoInstantiatedFor(Object poso, Dto2PosoGenerator generator){
		if(null != generator)
			generator.postProcessInstantiate(poso);
	}
	
	@SuppressWarnings("rawtypes")
	public void posoLoadedFor(Object dto, Object poso, Dto2PosoGenerator generator){
		if(dto.getClass().isEnum() || dto instanceof java.lang.Enum || (null != dto.getClass().getSuperclass() && java.lang.Enum.class.equals(dto.getClass().getSuperclass())))
			return;
		
		if(null != generator)
			generator.postProcessLoad(dto, poso);
	}
	
	public void cleanup(){
		for(Object dto : callbackOnPosoMap.keySet()){
			if(! createdPosoMap.containsKey(dto)){
				for(CallbackOnPosoCreation callback : callbackOnPosoMap.get(dto)){
					Object posoId = dto instanceof IdedDto ? ((IdedDto)dto).getDtoId() : null;

					if(null == posoId || ! dto2PosoMap.containsKey(dto.getClass()) || ! dto2PosoMap.get(dto.getClass()).containsKey(posoId))
						callback.callback(null);
					else
						callback.callback(dto2PosoMap.get(dto.getClass()).get(posoId));
				}
			}
		}
		
		
		/* call create generators */
		for(Object dto : createdPosoMap.keySet()){
			Object poso = createdPosoMap.get(dto);
			Dto2PosoGenerator generator = createdPosoGeneratorMap.get(dto);
			
			generator.postProcessCreate(dto, poso);
		}
		
		/* call create generators */
		for(Object dto : createdUnmanagedPosoMap.keySet()){
			Object poso = createdUnmanagedPosoMap.get(dto);
			Dto2PosoGenerator generator = createdUnmanagedPosoGeneratorMap.get(dto);
			
			generator.postProcessCreateUnmanaged(dto, poso);
		}
		
		/* call merge generators */
		for(Object dto : mergedPosoMap.keySet()){
			Object poso = mergedPosoMap.get(dto);
			Dto2PosoGenerator generator = mergedPosoGeneratorMap.get(dto);
			
			generator.postProcessMerge(dto, poso);
		}
	
	}
	
	public void setModeUnmanaged(boolean modeUnmanaged) {
		this.modeUnmanaged = modeUnmanaged;
	}
	
	public boolean isModeUnmanaged() {
		return modeUnmanaged;
	}

	public void createdDto(Object dto, Object poso, DtoView here,
			DtoView referenced) {
		
	}
}
