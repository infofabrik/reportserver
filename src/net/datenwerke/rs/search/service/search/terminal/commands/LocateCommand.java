package net.datenwerke.rs.search.service.search.terminal.commands;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;

import joptsimple.OptionSet;
import net.datenwerke.gf.service.history.HistoryLink;
import net.datenwerke.gf.service.history.HistoryService;
import net.datenwerke.rs.search.service.search.SearchService;
import net.datenwerke.rs.search.service.search.locale.SearchMessages;
import net.datenwerke.rs.terminal.service.terminal.TerminalSession;
import net.datenwerke.rs.terminal.service.terminal.exceptions.TerminalException;
import net.datenwerke.rs.terminal.service.terminal.helpers.AutocompleteHelper;
import net.datenwerke.rs.terminal.service.terminal.helpers.CommandParser;
import net.datenwerke.rs.terminal.service.terminal.helpmessenger.annotations.Argument;
import net.datenwerke.rs.terminal.service.terminal.helpmessenger.annotations.CliHelpMessage;
import net.datenwerke.rs.terminal.service.terminal.helpmessenger.annotations.NonOptArgument;
import net.datenwerke.rs.terminal.service.terminal.hooks.TerminalCommandHook;
import net.datenwerke.rs.terminal.service.terminal.obj.CommandResult;
import net.datenwerke.rs.utils.jpa.EntityUtils;
import net.datenwerke.security.service.security.rights.Read;

public class LocateCommand implements TerminalCommandHook{
	
	private final Logger logger = LoggerFactory.getLogger(getClass().getName());
	
	private final static String BASE_COMMAND = "locate";

	private final SearchService searchService;
	private final EntityUtils entityUtils;
	private final HistoryService historyService;
	
	@Inject
	public LocateCommand(
			SearchService searchService,
			EntityUtils entityUtils, 
			HistoryService historyService
	) {
		this.searchService = searchService;
		this.entityUtils = entityUtils;
		this.historyService = historyService;
	}
	

	@Override
	public boolean consumes(CommandParser parser, TerminalSession session) {
		return BASE_COMMAND.equals(parser.getBaseCommand());
	}

	@Override
	@CliHelpMessage(
			messageClass = SearchMessages.class,
			name = BASE_COMMAND,
			description = "commandSearch_description",
			args = {
				@Argument(flag="t", hasValue=true, valueName="type", description="commandSearch_tArgument")
			},
			nonOptArgs = {
				@NonOptArgument(name="query", description="commandSearch_queryArgument", mandatory = true)
			}
		)
	public CommandResult execute(CommandParser parser, TerminalSession session) throws TerminalException {
		
		Class<?> entityType; 
		
		String query = getQuery(parser);
		
		if(parser.hasOption("t")){
			OptionSet parsed = parser.parse("t:");
			String typeName = String.valueOf(parsed.valueOf("t"));
			entityType = entityUtils.getEntityBySimpleName(typeName);
		}else{
			entityType = Object.class;
		}
		
		Collection<Object> resolvedObjects = session.getObjectResolver().getObjects(query,Read.class);
		if (null != resolvedObjects && resolvedObjects.size() != 0) {
			List<?> searchResults;
			if (resolvedObjects instanceof List)
			  searchResults = (List<?>)resolvedObjects;
			else
			  searchResults = new ArrayList(resolvedObjects);
			
			return produceResult(searchResults);
		}
		
		List<?> searchResults;
		query = searchService.enhanceQuery(query);
		searchResults = searchService.locate(entityType, query);
		
		return produceResult(searchResults);
	}
	
	private String getQuery(CommandParser parser) {
		String query = null;
		
		if(parser.hasOption("t")){
			OptionSet parsed = parser.parse("t:");
			List<?> nonOptionArguments = parsed.nonOptionArguments();
			
			if(null == nonOptionArguments || nonOptionArguments.isEmpty())
				throw new IllegalArgumentException("Expected query");
			
			query = (String) nonOptionArguments.get(0);
		}else{
			 query = parser.getArgumentNr(1);
		}
		
		return query;
	}
	
	private CommandResult produceResult(List<?> searchResults) {
		CommandResult cr = new CommandResult();
		for(Object o : searchResults){
			try {
				List<HistoryLink> links = historyService.buildLinksFor(o);
				for(HistoryLink link : links){
					cr.addResultHyperLink(link.getObjectCaption() + " (" + link.getHistoryLinkBuilderId() + ")", link.getLink());
				}
			} catch (IllegalArgumentException e) {
				logger.warn( e.getMessage(), e);
			}
			
		}
		
		return cr;
	}

	@Override
	public void addAutoCompletEntries(AutocompleteHelper autocompleteHelper, TerminalSession session) {
		autocompleteHelper.autocompleteBaseCommand(BASE_COMMAND);
	}

}
