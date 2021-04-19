package net.datenwerke.rs.terminal.service.terminal;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.terminal.service.terminal.exceptions.CommandNotFoundException;
import net.datenwerke.rs.terminal.service.terminal.exceptions.MaxAutocompleteResultsExceededException;
import net.datenwerke.rs.terminal.service.terminal.exceptions.TerminalException;
import net.datenwerke.rs.terminal.service.terminal.helpers.AutocompleteHelper;
import net.datenwerke.rs.terminal.service.terminal.helpers.CommandParser;
import net.datenwerke.rs.terminal.service.terminal.hooks.InteractiveCommandHook;
import net.datenwerke.rs.terminal.service.terminal.hooks.TerminalCommandHook;
import net.datenwerke.rs.terminal.service.terminal.hooks.TerminalSessionDeamonHook;
import net.datenwerke.rs.terminal.service.terminal.hooks.TerminalSessionHijackHook;
import net.datenwerke.rs.terminal.service.terminal.obj.AutocompleteResult;
import net.datenwerke.rs.terminal.service.terminal.obj.CommandResult;
import net.datenwerke.rs.terminal.service.terminal.obj.InteractiveResultModifier;
import net.datenwerke.rs.terminal.service.terminal.objresolver.ObjectResolverDeamon;
import net.datenwerke.rs.terminal.service.terminal.operator.TerminalCommandOperator;
import net.datenwerke.rs.terminal.service.terminal.vfs.VirtualFileSystemDeamon;

import com.google.inject.Inject;
import com.google.inject.Provider;

public class TerminalSession implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3494091153006001081L;

	private static final int MAX_AUTOCOMPLETE_RESULTS = 100;
	
	private final HookHandlerService hookHandlerService;
	private final Provider<AutocompleteHelper> autoHelperProvider;
	
	private final String sessionId;
	
	private InteractiveCommandHook currentInteractiveCommand;
	private TerminalSessionHijackHook hijacker;
	
	private Map<Class<?>, TerminalSessionDeamonHook> deamonMap = new HashMap<Class<?>, TerminalSessionDeamonHook>();

	private boolean checkRights = true;
	
	@Inject
	public TerminalSession(
		Provider<AutocompleteHelper> autoHelperProvider,
		HookHandlerService hookHandlerService
		){
		
		/* store objects */
		this.autoHelperProvider = autoHelperProvider;
		this.hookHandlerService = hookHandlerService;
		
		/* init */
		this.sessionId = UUID.randomUUID().toString();
		initDeamons();
	}
	
	private void initDeamons() {
		for(TerminalSessionDeamonHook deamon : hookHandlerService.getHookers(TerminalSessionDeamonHook.class)){
			deamon.init(this);
			deamonMap.put(deamon.getClass(), deamon);
		}
	}
	
	public <D extends TerminalSessionDeamonHook> D getDeamon(Class<D> type){
		return (D) deamonMap.get(type);
	}

	public String getSessionId(){
		return sessionId;
	}
	
	
	public AutocompleteResult autocomplete(String command, int cursorPosition) throws MaxAutocompleteResultsExceededException {
		return autocomplete(command, cursorPosition, false);
	}

	public AutocompleteResult autocomplete(String command, int cursorPosition, boolean forceResult) throws MaxAutocompleteResultsExceededException {
		if(null != hijacker)
			return hijacker.autocomplete(this, command, cursorPosition, forceResult);
		
		/* create parser and search for responsible hook */
		CommandParser parser = new CommandParser(command);

		AutocompleteHelper autoHelper = autoHelperProvider.get();
		autoHelper.init(parser, cursorPosition);
		
		for(TerminalCommandHook commandHooker : hookHandlerService.getHookers(TerminalCommandHook.class))
			commandHooker.addAutoCompletEntries(autoHelper, this);
		
		/* ask deamons to fill in */
		for(TerminalSessionDeamonHook deamon : deamonMap.values() )
			deamon.autocomplete(autoHelper);
		
		AutocompleteResult result = autoHelper.getResult();
		
		if(!forceResult && result.size() > MAX_AUTOCOMPLETE_RESULTS)
			throw new MaxAutocompleteResultsExceededException(result.size());
		
		return result;
	}
	
	public CommandResult execute(String command) throws TerminalException{
		return execute(command, new ExecuteCommandConfigImpl());
	}
	
	public CommandResult execute(String command, ExecuteCommandConfig config) throws TerminalException{
		if(config.allowHijackers() && null != hijacker){
			if(hijacker.wantsToContinue(this, command))
				return hijacker.execute(this, command);
			else
				stopHijacking();
		}
		
		if(config.allowInteractive() && null != currentInteractiveCommand)
			return handleInteractiveCommand(command);
		
		if(null == command)
			throw new CommandNotFoundException();
		
		/* create parser and search for responsible hook */
		CommandParser parser = new CommandParser(command);
		
		if(config.allowOperators()){
			TerminalCommandOperator maxOp = null;
			int maxLength = -1;
			for(TerminalCommandOperator operator : hookHandlerService.getHookers(TerminalCommandOperator.class)){
				Integer length = operator.consumes(command, parser, config);
				if(null != length && length > maxLength){
					maxLength = length;
					maxOp = operator;
				}
			}
			if(null != maxOp){
				return maxOp.execute(command, parser, config, this);
			}
		}
		
		for(TerminalCommandHook commandHooker : hookHandlerService.getHookers(TerminalCommandHook.class)){
			if(commandHooker.consumes(parser, this)){
				CommandResult result = config.execute(commandHooker, parser, this);
				
				if(config.allowInteractive() && commandHooker instanceof InteractiveCommandHook && ((InteractiveCommandHook)commandHooker).isKeepInteractiveSession()){
					currentInteractiveCommand = (InteractiveCommandHook) commandHooker;
					result.addModifier(InteractiveResultModifier.class);
				}
				
				if(config.allowHijackers()){
					for(TerminalSessionHijackHook hijackHooker : hookHandlerService.getHookers(TerminalSessionHijackHook.class)){
						if(hijackHooker.consumes(this, result)){
							hijacker = hijackHooker;
							result = hijackHooker.adapt(this, result);
							break;
						}
					}
				}
				
				return result;
			}
		}
		
		throw new CommandNotFoundException();
	}
	
	public CommandResult ctrlC() throws TerminalException {
		if(null != hijacker)
			return hijacker.ctrlC(this);
		
		if(null == currentInteractiveCommand)
			return new CommandResult();
		
		CommandResult result = currentInteractiveCommand.ctrlC();
		
		if(! currentInteractiveCommand.isKeepInteractiveSession()){
			result.removeModifier(InteractiveResultModifier.class);
			currentInteractiveCommand = null;
		} else
			result.addModifier(InteractiveResultModifier.class);
		
		return result;
	}
	
	public void stopHijacking(){
		hijacker = null;
	}

	
	private CommandResult handleInteractiveCommand(String command) {
		CommandResult result = currentInteractiveCommand.executeSubsequent(command);
		
		if(! currentInteractiveCommand.isKeepInteractiveSession()){
			result.removeModifier(InteractiveResultModifier.class);
			currentInteractiveCommand = null;
		} else
			result.addModifier(InteractiveResultModifier.class);
		
		for(TerminalSessionHijackHook hijackHooker : hookHandlerService.getHookers(TerminalSessionHijackHook.class)){
			if(hijackHooker.consumes(this, result)){
				hijacker = hijackHooker;
				result = hijackHooker.adapt(this, result);
				break;
			}
		}
		
		return result;
	}

	public VirtualFileSystemDeamon getFileSystem() {
		return getDeamon(VirtualFileSystemDeamon.class);
	}
	
	public ObjectResolverDeamon getObjectResolver() {
		return getDeamon(ObjectResolverDeamon.class);
	}

	public void setCheckRights(boolean checkRights) {
		this.checkRights = checkRights;
	}
	
	public boolean isCheckRights() {
		return checkRights;
	}



}
