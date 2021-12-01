package net.datenwerke.rs.incubator.service.misc.terminal.commands;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import net.datenwerke.dbpool.DbPoolService;
import net.datenwerke.rs.base.service.datasources.definitions.DatabaseDatasource;
import net.datenwerke.rs.base.service.datasources.locale.DatasourcesMessages;
import net.datenwerke.rs.base.service.reportengines.table.output.object.TableDefinition;
import net.datenwerke.rs.terminal.service.terminal.TerminalSession;
import net.datenwerke.rs.terminal.service.terminal.helpers.AutocompleteHelper;
import net.datenwerke.rs.terminal.service.terminal.helpers.CommandParser;
import net.datenwerke.rs.terminal.service.terminal.helpmessenger.annotations.CliHelpMessage;
import net.datenwerke.rs.terminal.service.terminal.helpmessenger.annotations.NonOptArgument;
import net.datenwerke.rs.terminal.service.terminal.hijacker.data.SplitTableResult;
import net.datenwerke.rs.terminal.service.terminal.hooks.InteractiveCommandHook;
import net.datenwerke.rs.terminal.service.terminal.obj.CommandResult;
import net.datenwerke.rs.terminal.service.terminal.objresolver.ObjectResolverDeamon;
import net.datenwerke.rs.terminal.service.terminal.objresolver.exceptions.ObjectResolverException;
import net.datenwerke.security.service.security.rights.Execute;
import net.datenwerke.security.service.security.rights.Read;

import com.google.inject.Inject;

public class SqlTerminalCommand implements InteractiveCommandHook {


	/**
	 * 
	 */
	private static final long serialVersionUID = -312563615096477728L;

	public static final String BASE_COMMAND = "sql";

	private static final int MAX_NR_OF_ROWS = 100;
	private static final int ABSOLUTE_MAX_NR_OF_ROWS = 1000000;

	private final DbPoolService<?> dbPoolService;
	
	private boolean keepInteractiveSession = false;
	
	private DatabaseDatasource database;
	
	
	@Inject
	public SqlTerminalCommand(
		DbPoolService dbPoolService	
		){
		
		/* store objects */
		this.dbPoolService = dbPoolService;
	}
	
	@Override
	public boolean consumes(CommandParser parser, TerminalSession session) {
		return BASE_COMMAND.equals(parser.getBaseCommand());
	}

	@CliHelpMessage(
		messageClass = DatasourcesMessages.class,
		name = BASE_COMMAND,
		description = "commandSql_description",
		nonOptArgs = {
			@NonOptArgument(name="datasource", description="commandSql_datasource")
		}
	)
	@Override
	public CommandResult execute(CommandParser parser, TerminalSession session) {
		List<String> arguments = parser.getNonOptionArguments();
		
		if(1 != arguments.size())
			throw new IllegalArgumentException("Expect one argument");
		
		ObjectResolverDeamon objectResolver = session.getObjectResolver();
		
		try {
			Collection<Object> datasources = objectResolver.getObjects(arguments.get(0), Read.class, Execute.class);
			if(1 != datasources.size())
				throw new IllegalArgumentException("Expected one datasource");
			if(! (datasources.iterator().next() instanceof DatabaseDatasource))
				throw new IllegalArgumentException("Expected an sql database");
			
			database = (DatabaseDatasource) datasources.iterator().next();
			
			keepInteractiveSession = true;
		} catch (ObjectResolverException e) {
			throw new IllegalArgumentException(e);
		}
		
		
		
		return new CommandResult();
	}
	
	@Override
	public CommandResult executeSubsequent(String command) {
		CommandResult result = new CommandResult();
		if(null != command && command.trim().equals("bye")) {
			keepInteractiveSession = false;
			result.addResultLine("Good Bye");
		} else {
			String query = command;
			
			try{
				Connection connection = dbPoolService.getConnection(database.getConnectionConfig()).get();
				
				PreparedStatement stmt = null;
				try{
					stmt = connection.prepareStatement(query);
					
					ResultSet resultSet = stmt.executeQuery();
					
					/* set table definition */
					ResultSetMetaData metaData = resultSet.getMetaData();
					TableDefinition td = TableDefinition.fromResultSetMetaData(metaData);
					
					/* set data */
					List<List<String>> data = new ArrayList<List<String>>();
					while(resultSet.next()){
						List<String> rowData = new ArrayList<String>();
						for(int i = 1; i <= td.size(); i++)
							rowData.add(String.valueOf(resultSet.getObject(i)));
						
						data.add(rowData);
					}
					
					if(data.size() > ABSOLUTE_MAX_NR_OF_ROWS)
						new CommandResult("result set is bigger than " + ABSOLUTE_MAX_NR_OF_ROWS);

					return new SplitTableResult(td,data,MAX_NR_OF_ROWS);
				} finally {
//					if(null != stmt && ! stmt.isClosed())
//						stmt.close();
					if(null != connection)
						connection.close();
				}
			} catch (SQLException e) {
				return new CommandResult(e.getMessage());
			} catch(Exception e){
				keepInteractiveSession = false;
				if(e instanceof RuntimeException)
					throw (RuntimeException)e;
				throw new RuntimeException(e);
			} 
		}
		
		return result;
	}
	
	@Override
	public void addAutoCompletEntries(AutocompleteHelper autocompleteHelper, TerminalSession session) {
		autocompleteHelper.autocompleteBaseCommand(BASE_COMMAND);
	}

	@Override
	public boolean isKeepInteractiveSession() {
		return keepInteractiveSession;
	}

	@Override
	public CommandResult ctrlC() {
		return new CommandResult();
	}


}
