package net.datenwerke.rs.base.service.datasources.statementmanager;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;

import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.base.service.datasources.statementmanager.hooks.StatementCancellationHook;
import net.datenwerke.security.service.authenticator.AuthenticatorService;

@Singleton
public class StatementManagerServiceImpl implements StatementManagerService {
	
	
	private Map<Long, Map<String, Map<String, StatementContainer>>> statementMap = new ConcurrentHashMap<Long, Map<String,Map<String,StatementContainer>>>();
	private Map<String, Date> dateMap = new ConcurrentHashMap<String, Date>();
	private Provider<AuthenticatorService> authenticatorService;
	private HookHandlerService hookHandlerService;
	
	@Inject
	public StatementManagerServiceImpl(
			Provider<AuthenticatorService> authenticatorService, 
			HookHandlerService hookHandlerService) {
		
		this.authenticatorService = authenticatorService;
		this.hookHandlerService = hookHandlerService;
	}
	
	public synchronized void registerStatement(String statementId, Statement statement, Connection connection){
		Map<String,StatementContainer> userStatementMap = getUserStatementMap(statementId);
		dateMap.put(statementId, new Date());
		if(null != userStatementMap) {
			if(statementId.contains(":")){
				userStatementMap.put(statementId.substring(1 + statementId.indexOf(":")), new StatementContainer(statement, connection));
			}else{
				userStatementMap.put("anonstmt-" + UUID.randomUUID(), new StatementContainer(statement, connection));
			}
		}
	}
	
	@Override
	public synchronized void unregisterStatement(String statementId){
		dateMap.remove(statementId);
		
		if(statementId.contains(":")){
			Map<String,StatementContainer> userStatementMap = getUserStatementMap(statementId);
			if(null != userStatementMap){
				userStatementMap.remove(statementId.substring(1 + statementId.indexOf(":")));
				if(userStatementMap.isEmpty()){
					getUserStatementMap().remove(statementId.substring(statementId.indexOf(":")));
					unregisterStatement(statementId.substring(0, statementId.indexOf(":")));
				}
			}
		}else{
			Map<String,Map<String,StatementContainer>> userStatementMap = getUserStatementMap();
			if(null != userStatementMap){
				userStatementMap.remove(statementId);
				if (userStatementMap.isEmpty()) {
					getUserStatementMap().remove(statementId);
				}
			}
		}
		
		if (getUserStatementMap().isEmpty()) {
			statementMap.remove(getCurrentUserId());
		}
	}
	
	@Override
	public Map<Long, Map<String, Map<String, StatementContainer>>> getAllStatements(){
		return statementMap;
	}
	
	@Override
	public Date getRegisterDate(String id){
		return dateMap.get(id);
	}
	
	@Override
	public synchronized Collection<StatementContainer> getStatements(String statementId) {
		Map<String,StatementContainer> userStatementMap = getUserStatementMap(statementId);
		if(null == userStatementMap)
			return Collections.EMPTY_SET;
		if(statementId.contains(":")){
			StatementContainer stmtContainer = userStatementMap.get(statementId.substring(1 + statementId.indexOf(":")));
			if(null == stmtContainer)
				return Collections.EMPTY_SET;
			return Collections.singleton(stmtContainer);
		}else{
			return userStatementMap.values();
		}
	}
	
	
	private synchronized Map<String, StatementContainer> getUserStatementMap(String statementId){
		Map<String,Map<String,StatementContainer>> userStatementMap = getUserStatementMap();
		if(null == userStatementMap)
			return null;
		if(statementId.contains(":")){
			statementId = statementId.substring(statementId.indexOf(":"));
		}
		
		Map<String, StatementContainer> hashMap = userStatementMap.get(statementId);
		if(null == hashMap){
			hashMap = new ConcurrentHashMap<String, StatementContainer>();
			userStatementMap.put(statementId, hashMap);
		}
		
		return hashMap;
	}
	
	private Long getCurrentUserId(){
		try{
			AuthenticatorService as = authenticatorService.get();
			return as.getCurrentUser().getId();
		}catch(Exception e){
			return -1L;
		}
	}
	
	@Override
	public Map<String, Map<String, StatementContainer>> getUserStatementMap() {
		Long currentUserId = getCurrentUserId();
		return getUserStatementMap(currentUserId);
	}
	
	@Override
	public synchronized Map<String, Map<String, StatementContainer>> getUserStatementMap(Long currentUserId) {
		
		Map<String,Map<String, StatementContainer>> map = statementMap.get(currentUserId);
		if(null == map){
			map = new ConcurrentHashMap<String,Map<String, StatementContainer>>();
			statementMap.put(currentUserId, map);
		}
		return map;
	}
	
	@Override
	public void cancelStatement(String statementId) {
		Collection<StatementContainer> statements = getStatements(statementId);
		for(StatementContainer statementContainer: statements){
			boolean handled = false;
			for(StatementCancellationHook hooker : hookHandlerService.getHookers(StatementCancellationHook.class)){
				if(hooker.consumes(statementContainer.getStatement(), statementContainer.getConnection())){
					hooker.cancelStatement(statementContainer.getStatement(), statementContainer.getConnection(), statementId);
					handled = hooker.overridesDefaultMechanism(statementContainer.getStatement(), statementContainer.getConnection());
				}
			}
			if(!handled){
				try {
					statementContainer.getStatement().cancel();
				} catch (SQLException e) { }
			}
		}
		
		getUserStatementMap().remove(statementId);
		if (getUserStatementMap().isEmpty()) {
			statementMap.remove(getCurrentUserId());
		}
	}
	
}
