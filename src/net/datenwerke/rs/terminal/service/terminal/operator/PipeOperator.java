package net.datenwerke.rs.terminal.service.terminal.operator;

import net.datenwerke.rs.terminal.service.terminal.ExecuteCommandConfig;
import net.datenwerke.rs.terminal.service.terminal.TerminalSession;
import net.datenwerke.rs.terminal.service.terminal.exceptions.TerminalException;
import net.datenwerke.rs.terminal.service.terminal.helpers.CommandParser;
import net.datenwerke.rs.terminal.service.terminal.hooks.PipedTerminalCommandHook;
import net.datenwerke.rs.terminal.service.terminal.hooks.TerminalCommandHook;
import net.datenwerke.rs.terminal.service.terminal.obj.CommandResult;

public class PipeOperator implements TerminalCommandOperator {

	@Override
	public Integer consumes(String command, CommandParser parser, ExecuteCommandConfig config) {
		int maxPos = getMaxPipePos(command, parser);
		if(maxPos > 0)
			return maxPos;
		return null;
	}

	private int getMaxPipePos(String command, CommandParser parser) {
		int pos = 0;
		int maxPos = 0;
		char lastChar = '-';
		int seenQuotes = 0;
		for(char c : parser.getRawCommand().toCharArray()){
			pos++;
			if('\"' == c && lastChar != '\\')
				seenQuotes++;
			else if('|' == c && seenQuotes % 2 == 0){
				maxPos = pos;
			}
			
			lastChar = c;
		}
		return maxPos;
	}

	@Override
	public CommandResult execute(String command, CommandParser parser,
			ExecuteCommandConfig config, TerminalSession session) throws TerminalException {
		int maxPos = getMaxPipePos(command, parser);
		
		String firstCommand = command.substring(0, maxPos - 1);
		try{
			String secondCommand = command.substring(maxPos);
			if(secondCommand.trim().equals(""))
				throw new TerminalException("Invalid pipe position");
			
			final CommandResult result = session.execute(firstCommand, new ExecuteCommandConfig() {
				
				@Override
				public CommandResult execute(TerminalCommandHook commandHook,
						CommandParser parser, TerminalSession session)
						throws TerminalException {
					return commandHook.execute(parser, session);
				}
				
				@Override
				public boolean allowOperators() {
					return true;
				}
				
				@Override
				public boolean allowInteractive() {
					return false;
				}
				
				@Override
				public boolean allowHijackers() {
					return false;
				}
			});
			
			return session.execute(secondCommand, new ExecuteCommandConfig() {
				
				@Override
				public CommandResult execute(TerminalCommandHook commandHook,
						CommandParser parser, TerminalSession session)
						throws TerminalException {
					CommandResult pipedResult = null;
					if(commandHook instanceof PipedTerminalCommandHook)
						pipedResult =  ((PipedTerminalCommandHook)commandHook).execute(result, parser, session);
					else 
						pipedResult = commandHook.execute(parser, session);
					
					pipedResult.setCommitTransaction(result.isCommitTransaction() && pipedResult.isCommitTransaction());
					
					return pipedResult;
				}
				
				@Override
				public boolean allowOperators() {
					return true;
				}
				
				@Override
				public boolean allowInteractive() {
					return false;
				}
				
				@Override
				public boolean allowHijackers() {
					return false;
				}
			});
			
		} catch(IndexOutOfBoundsException e){
			throw new TerminalException("Invalid pipe position");
		}
	}

}
