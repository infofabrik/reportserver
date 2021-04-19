package net.datenwerke.gxtdto.client.dtomanager;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.datenwerke.gxtdto.client.dtoinfo.DtoInformationService;
import net.datenwerke.gxtdto.client.dtomanager.events.BeforeDtoDetachedEvent;
import net.datenwerke.gxtdto.client.dtomanager.events.DtoAddedEvent;
import net.datenwerke.gxtdto.client.dtomanager.events.DtoChangedEvent;
import net.datenwerke.gxtdto.client.dtomanager.events.DtoListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;
import com.sencha.gxt.core.client.ValueProvider;

/**
 * 
 *
 */
public class ClientDtoManagerServiceImpl implements ClientDtoManagerService {

	private Map<Dto, Set<DtoListener>> specificDtoChangeListener = new HashMap<Dto, Set<DtoListener>>();
	private Set<DtoListener> generalDtoChangeListener = new HashSet<DtoListener>();
	
	private final Logger logger = LoggerFactory.getLogger(getClass().getName());

	final private ProxyIdGenerator proxyIdGenerator;
	final private DtoInformationService dtoInformationService;
	
	// TODO: This might result in a client side memory leak
	private Map<Long, Dto> proxyToDto = new HashMap<Long, Dto>();
	
	private Map<DtoView, Map<Dto, Dto>> registeredDtos = new HashMap<DtoView, Map<Dto,Dto>>();
	
	
	@Inject
	public ClientDtoManagerServiceImpl(
		ProxyIdGenerator proxyIdGenerator,
		DtoInformationService dtoInformationService
		){
		
		/* store objects */
		this.proxyIdGenerator = proxyIdGenerator;
		this.dtoInformationService = dtoInformationService;
		
		/* prepare map */
		for(DtoView res : DtoView.values())
			registeredDtos.put(res, new HashMap<Dto, Dto>());
	}
	
	@Override
	public <X extends Dto> X registerDto(X dto) {
		List<Dto> fireAddList = new ArrayList<Dto>();
		
		X proxy = registerDto(dto, fireAddList);
		
		if(! fireAddList.isEmpty())
			fireAddEvent(fireAddList);
		
		return proxy;
	}
	
	public <X extends Dto> X registerDto(X dto, List<Dto> fireAddList) {
		if(! dtoInformationService.isProxyableDto(dto))
			return dto;
		
		/* prepare accumulator */
		HashMap<DtoView, Set<Dto>> acc = new HashMap<DtoView, Set<Dto>>();
		for(DtoView res : DtoView.values())
			acc.put(res, new HashSet<Dto>());
		
		doRegisterDto(dto, acc, fireAddList);
		
		return createProxy(dto);
	}
	
	@Override
	public void registerDtoContainer(DtoContainer container) {
		container.registerDtos(new DtoRegistrar(this));
	}


	@Override
	public void registerDtoContainer(Collection<DtoContainer> containers) {
		DtoRegistrar registrar = new DtoRegistrar(this);
		
		for(DtoContainer container : containers)
			container.registerDtos(registrar);
	}
	
	private void doRegisterDto(Dto dto, Map<DtoView, Set<Dto>> newlyRegisteredDtos, List<Dto> fireAddList) {
		if(null == dtoInformationService.getDtoId(dto)){
			logger.warn( "Exception registering a dto", (new IllegalArgumentException("Dtos registered need to have an ID: " + dto)));
			return;
		}

		/* put dto to set */
		newlyRegisteredDtos.get(dto.getDtoView()).add(dto);
		
		/* first register all referenced Dtos */
		for(PropertyAccessor dtoPa : dto.getPropertyAccessorsForDtos()){
			Object refDto = dtoPa.getValue(dto);
			if(refDto instanceof Dto)
				if(! newlyRegisteredDtos.get(((Dto)refDto).getDtoView()).contains(refDto))
					doRegisterDto((Dto)refDto, newlyRegisteredDtos, fireAddList);
				
			if(refDto instanceof Collection)
				for(Object rDto : ((Collection)refDto))
					if(! newlyRegisteredDtos.get(((Dto)rDto).getDtoView()).contains(rDto))
						doRegisterDto((Dto)rDto, newlyRegisteredDtos, fireAddList);
		}
		
		/* if new dto is registered .. simply store dto and return new proxy */
		boolean found = false;
		for(DtoView restriction : DtoView.values()){
			if(registeredDtos.get(restriction).containsKey(dto)){
				found = true;
				break;
			}
		}
		
		storeInDotMap(dto);
		
		if(! found)
			fireAddList.add(dto);
		else
			fireUpdateEvent(dto);
	}

	private void storeInDotMap(Dto dto) {
		Set<DtoView> knownLevels = new HashSet<DtoView>();
		for(DtoView restriction : DtoView.values())
			if(registeredDtos.get(restriction).containsKey(dto))
				knownLevels.add(restriction);
		
		if(knownLevels.isEmpty()){
			registeredDtos.get(dto.getDtoView()).put(dto, dto);
		} else {
			/* put dto's own level to knwon levels */
			knownLevels.add(dto.getDtoView());
			
			for(DtoView res : knownLevels){
				if(dto.getDtoView().compareTo(res) >= 0)
					registeredDtos.get(res).put(dto, dto);
				else {
					/* merge dtos */
					Dto dtoInStore = registeredDtos.get(res).get(dto);
					
					boolean silence = dtoInStore.isSilenceEvents();
					dtoInStore.silenceEvents(true);
					
					for(PropertyAccessor pa : dto.getPropertyAccessorsByView(dto.getDtoView()))
						dtoInStore.deepSet(pa, pa.getValue(dto));
					
					dtoInStore.silenceEvents(silence);
				}
			}
		}
	}

	private void fireAddEvent(List<Dto> dtos) {
		/* build event */
		List<Dto> proxyList = new ArrayList<Dto>();
		for(Dto dto : dtos)
			proxyList.add(createProxy(dto));
		
		DtoAddedEvent event = new DtoAddedEvent(proxyList);
		
		/* general listener */
		for(DtoListener listener : generalDtoChangeListener)
			listener.dtoAdded(event);
	}

	private void fireUpdateEvent(Dto dto) {
		/* build event */
		DtoChangedEvent event = new DtoChangedEvent(createProxy(dto));
		
		/* specific listener */
		if(null != specificDtoChangeListener.get(dto))
			for(DtoListener listener : specificDtoChangeListener.get(dto))
				listener.dtoChanged(event);
		
		/* general listener */
		for(DtoListener listener : generalDtoChangeListener)
			listener.dtoChanged(event);
	}
	
	private BeforeDtoDetachedEvent fireBeforeDetachEvent(Dto dto){
		BeforeDtoDetachedEvent event = new BeforeDtoDetachedEvent(createProxy(dto));
		
		/* specific listener */
		if(null != specificDtoChangeListener.get(dto))
			for(DtoListener listener : specificDtoChangeListener.get(dto))
				listener.beforeDtoDetached(event);
		
		/* general listener (beware of concurrent modification) */
		for(DtoListener listener : generalDtoChangeListener.toArray(new DtoListener[]{}))
			listener.beforeDtoDetached(event);
		
		return event;
	}
	
	public <X extends Dto> X newProxy(X dto){
		if(! dtoInformationService.isProxyableDto(dto))
			throw new IllegalArgumentException("Dto is not proxyable: " + dto);
		if(! dto.isDtoProxy())
			throw new IllegalArgumentException("Dto is no proxy: " + dto);
		
		return createProxy(dto);
	}

	@SuppressWarnings("unchecked")
	private <X extends Dto> X createProxy(X dto) {
		/* return dto object if dto is not proxiable */
		if(! dtoInformationService.isProxyableDto(dto))
			return dto;
		
		Long proxyId = proxyIdGenerator.nextId();
		
		X proxy = (X) dtoInformationService.createInstance(dto.getClass());
		proxy.markAsProxy(this, proxyId, dtoInformationService.getDtoId(dto), dto.getDtoView());
		
		proxyToDto.put(proxyId, dto);
		
		return proxy;
	}
	
	@SuppressWarnings("unchecked")
	private Collection<Dto> createProxy(Collection<Dto> dtos) {
		Collection<Dto> proxies;
		if(dtos instanceof Set)
			proxies = new HashSet<Dto>();
		else 
			proxies = new ArrayList<Dto>();
		
		for(Dto dto : dtos)
			proxies.add(createProxy(dto));
		
		return proxies;
	}

	@Override
	public <X extends Dto> Collection<X> registerDtos(Collection<X> dtos) {
		Collection<X> list = instantiateCollection(dtos);
		
		List<Dto> fireAddList = new ArrayList<Dto>();
		
		for(X dto : dtos){
			X proxy = registerDto(dto, fireAddList);	
			list.add(proxy);
		}

		if(! fireAddList.isEmpty())
			fireAddEvent(fireAddList);
		
		return list;
	}

	@Override
	public <X> X getProperty(Dto proxy, ValueProvider valueProvider){
		if(null == proxy || ! proxy.isDtoProxy() || ! proxyToDto.containsKey(proxy.getDtoProxyId()))
			throw new IllegalArgumentException("Unknown proxy: " + proxy);
		
		Dto unwrapped = unwrap(proxy);
		if(null == unwrapped)
			logger.warn("ARGH: NullPointer: " + proxy + " - " + valueProvider.getPath() + " - " +  dtoInformationService.getDtoId(proxy) + " - " + proxy.getDtoView());
		X value = (X) valueProvider.getValue(unwrapped);
		if(value instanceof Dto)
			return (X) createProxy((Dto)value);
		if(value instanceof Collection && ! ((Collection)value).isEmpty() && ((Collection)value).iterator().next() instanceof Dto)
			return (X) createProxy((Collection<Dto>)value);
			
		return value;
	}
	
	public <X> void setProperty(Dto proxy, ValueProvider vp, X value) {
		if(null == proxy || ! proxy.isDtoProxy() || ! proxyToDto.containsKey(proxy.getDtoProxyId()))
			throw new IllegalArgumentException("Unknown proxy: " + proxy);

		vp.setValue(unwrap(proxy), value);
	}
	
	private Dto unwrap(Dto proxy) {
		Dto unwrapped = null;
		for(DtoView res : DtoView.values()){
			if(res.compareTo(proxy.getDtoView()) >= 0){
				Map<Dto,Dto> map = registeredDtos.get(res);
				unwrapped = map.get(proxyToDto.get(proxy.getDtoProxyId()));
				if(null != unwrapped)
					break;
			}
		}
		return unwrapped;
	}

	@Override
	public <X extends Dto> void detachDto(X dto) {
		/* fire event */
		BeforeDtoDetachedEvent event = fireBeforeDetachEvent(dto);
		
		/* remove all dtos/proxies from internal maps */
		Collection<Dto> toRemove = event.getDtosForDetach();
		toRemove.add(dto);
		
		for(Dto dtoToRemove : toRemove)
			proxyToDto.remove(dtoToRemove.getDtoProxyId());
		
		/* remove from registered dtos */
		for(DtoView res : DtoView.values())
			registeredDtos.get(res).remove(dto);
		
		/* clear specific listeners */
		specificDtoChangeListener.remove(dto);
	}

	@Override
	public <X extends Dto> void detachDtos(Collection<X> dtos) {
		for(X dto : dtos)
			detachDto(dto);
	}

	@Override
	public void onDtoChange(Dto dto, DtoListener listener) {
		if(null == specificDtoChangeListener.get(dto))
			specificDtoChangeListener.put(dto, new HashSet<DtoListener>());
		specificDtoChangeListener.get(dto).add(listener);
	}

	@Override
	public void onDtoChange(DtoListener listener) {
		generalDtoChangeListener.add(listener);
	}

	@Override
	public void removeChangeListener(Dto dto, DtoListener listener) {
		if(null == specificDtoChangeListener.get(dto))
			return;
		specificDtoChangeListener.get(dto).remove(listener);
	}

	@Override
	public void removeDtoChangeListener(DtoListener listener) {
		generalDtoChangeListener.remove(listener);
	}
	
	@Override
	public <X extends Dto> X deepClone(X dto){
		X newDto = (X) dtoInformationService.createInstance(dto.getClass());
		
		/* id */
		if(newDto instanceof IdedDto)
			((IdedDto)newDto).setDtoId(((IdedDto)dto).getDtoId());
		
		/* property */
		for(PropertyAccessor pa: dto.getPropertyAccessors())
			pa.setValue(newDto, pa.getValue(dto));
		return newDto;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <X extends Dto> X unproxy(X proxy) {
		if(! proxy.isDtoProxy())
			return proxy;
		
		Dto unwrapped = unwrap(proxy);
		
		/* create instance and set id */
		X newDto = (X) dtoInformationService.createInstance(proxy.getClass());
		((IdedDto)newDto).setDtoId(dtoInformationService.getDtoId(proxy));
		
		for(PropertyAccessor accessor : proxy.getPropertyAccessors()){
			if(! accessor.isModified(proxy))
				accessor.setValue(newDto, accessor.getValue(unwrapped));
			else {
				Object value = accessor.getValue(proxy);
				if(value instanceof Dto)
					value = unproxy((Dto)value);
				if(value instanceof Collection && !((Collection)value).isEmpty() && ((Collection)value).iterator().next() instanceof Dto)
					value = unproxy((Collection<Dto>) value);
				
				accessor.setValue(newDto,value);
			}
		}
		
		return newDto;
	}
	
	@Override
	public <X extends Dto> Collection<X> unproxy(Collection<X> proxies){
		if(null == proxies)
			return null;
		
		Collection<X> col = instantiateCollection(proxies);
		
		for(X proxy : proxies)
			col.add(unproxy(proxy));
		
		return col;
	}
	
	protected <X> Collection<X> instantiateCollection(Collection<X> col){
		if(col instanceof List)
			return new ArrayList<X>();
		return new HashSet<X>();
	}

}
