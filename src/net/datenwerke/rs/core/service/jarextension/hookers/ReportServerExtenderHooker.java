package net.datenwerke.rs.core.service.jarextension.hookers;

import java.util.Iterator;
import java.util.ServiceLoader;

import net.datenwerke.gf.service.lateinit.LateInitHook;
import net.datenwerke.rs.core.service.jarextension.ReportServerExtender;
import net.datenwerke.rs.core.service.jarextension.events.ReoportServerExtenderLoadFailedEvent;
import net.datenwerke.rs.utils.eventbus.EventBus;
import net.datenwerke.rs.utils.exception.ExceptionServices;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Provider;

public class ReportServerExtenderHooker implements LateInitHook {

	private final Logger logger = LoggerFactory.getLogger(getClass().getName());
	
	private final Provider<Injector> injectorProvider;
	private final Provider<EventBus> eventBusProvider;
	private final Provider<ExceptionServices> exceptionServiceProvider;

	@Inject
	public ReportServerExtenderHooker(
		Provider<Injector> injectorProvider,
		Provider<EventBus> eventBusProvider,
		Provider<ExceptionServices> exceptionServiceProvider){
		this.injectorProvider = injectorProvider;
		this.eventBusProvider = eventBusProvider;
		this.exceptionServiceProvider = exceptionServiceProvider;
	}
	
	@Override
	public void initialize() {
		logger.info( "Start loading ReportServer extensions.");
		
		ServiceLoader<ReportServerExtender> serviceLoader = ServiceLoader.load(ReportServerExtender.class);
		Iterator<ReportServerExtender> iterator = serviceLoader.iterator();
		
		Injector injector = injectorProvider.get();
		
		while(iterator.hasNext()){
			try{
				ReportServerExtender next = iterator.next();
				
				logger.info( "Start loading ReportServer extension: " + next.getClass().getName());
				
				try{
					injector.injectMembers(next);
					next.extend();
				}catch(Exception e){
					logger.warn( e.getMessage(), e);
					eventBusProvider.get().fireEvent(new ReoportServerExtenderLoadFailedEvent(next.getClass().getName(), exceptionServiceProvider.get().exceptionToString(e)));
				}
			} catch(Exception e){
				logger.warn( e.getMessage(), e);
				eventBusProvider.get().fireEvent(new ReoportServerExtenderLoadFailedEvent(exceptionServiceProvider.get().exceptionToString(e)));
			}
		}
	}

}
