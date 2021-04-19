package net.datenwerke.rs.terminal.service.terminal.basecommands;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import net.datenwerke.rs.base.service.reportengines.table.output.object.RSStringTableRow;
import net.datenwerke.rs.base.service.reportengines.table.output.object.RSTableModel;
import net.datenwerke.rs.terminal.service.terminal.TerminalSession;
import net.datenwerke.rs.terminal.service.terminal.helpers.AutocompleteHelper;
import net.datenwerke.rs.terminal.service.terminal.helpers.CommandParser;
import net.datenwerke.rs.terminal.service.terminal.helpmessenger.annotations.Argument;
import net.datenwerke.rs.terminal.service.terminal.helpmessenger.annotations.CliHelpMessage;
import net.datenwerke.rs.terminal.service.terminal.helpmessenger.annotations.NonOptArgument;
import net.datenwerke.rs.terminal.service.terminal.hooks.TerminalCommandHook;
import net.datenwerke.rs.terminal.service.terminal.locale.TerminalMessages;
import net.datenwerke.rs.terminal.service.terminal.obj.CommandResult;
import net.datenwerke.rs.terminal.service.terminal.obj.CommandResultList;
import net.datenwerke.rs.terminal.service.terminal.obj.DisplayMode;
import net.datenwerke.rs.utils.jpa.EntityUtils;

import org.apache.commons.lang.StringUtils;

import com.google.inject.Inject;
import com.google.inject.Provider;

public class HqlTerminalCommand implements TerminalCommandHook {

	public static final String BASE_COMMAND = "hql";
	
	private final Provider<EntityManager> entityManagerProvider;
	private final EntityUtils entityUtils;
	
	@Inject
	public HqlTerminalCommand(
		Provider<EntityManager> entityManagerProvider,
		EntityUtils entityUtils
		){
		
		/* store objects */
		this.entityManagerProvider = entityManagerProvider;
		this.entityUtils = entityUtils;
	}
	
	@Override
	public boolean consumes(CommandParser parser, TerminalSession session) {
		return BASE_COMMAND.equals(parser.getBaseCommand());
	}

	@CliHelpMessage(
		messageClass = TerminalMessages.class,
		name = BASE_COMMAND,
		description = "commandHql_description",
		args = {
			@Argument(flag="w", description="commandHql_wFlag")
		},
		nonOptArgs = {
			@NonOptArgument(name="query", description="commandHql_hqlArgument")
		}
	)
	@Override
	public CommandResult execute(CommandParser parser, TerminalSession session) {
		String arg = StringUtils.join(parser.getNonOptionArguments(), " ");

		try{
			/* execute query */
			List resultList = entityManagerProvider.get().createQuery(arg).getResultList();
			
			/* prepare result */
			CommandResult result = new CommandResult();
			
			/* simple result */
			if(! resultList.isEmpty() && entityUtils.isEntity(resultList.get(0))){
				if(resultList.get(0) instanceof Object[]){
					RSTableModel table = new RSTableModel();
					
					for(Object objArr : resultList){
						List<String> row = new ArrayList<String>();
						for(Object obj : (Object[])objArr){
							if(null != obj)
								row.add(obj.toString());
							else
								row.add("null");
						}
						table.addDataRow(new RSStringTableRow(row));
					}
					
					result.addResultTable(table);
				} else {
					List<String> stringResults = new ArrayList<String>();
					for(Object obj : resultList)
						stringResults.add(null == obj ? "NULL" : obj.toString());
					CommandResultList entryList = new CommandResultList(stringResults);
					entryList.setDenyBreakUp(true);
					result.addEntry(entryList);
				}
			}
			
			if(parser.hasOption("w"))
				result.setDisplayMode(DisplayMode.WINDOW);
			
			return result;
		} catch(NoResultException e){
			return new CommandResult("No result found");
		} catch(Exception e){
			return new CommandResult("Could not execute query: " + e.getMessage());
		}
	}

	@Override
	public void addAutoCompletEntries(AutocompleteHelper autocompleteHelper, TerminalSession session) {
		autocompleteHelper.autocompleteBaseCommand(BASE_COMMAND);
	}

}
