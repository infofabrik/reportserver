package net.datenwerke.rs.terminal.service.terminal.helpmessenger;

import java.lang.reflect.Method;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import com.google.inject.Inject;

import net.datenwerke.rs.base.service.reportengines.table.output.object.RSStringTableRow;
import net.datenwerke.rs.base.service.reportengines.table.output.object.RSTableModel;
import net.datenwerke.rs.terminal.service.terminal.TerminalSession;
import net.datenwerke.rs.terminal.service.terminal.helpers.CommandParser;
import net.datenwerke.rs.terminal.service.terminal.helpmessenger.annotations.Argument;
import net.datenwerke.rs.terminal.service.terminal.helpmessenger.annotations.CliHelpMessage;
import net.datenwerke.rs.terminal.service.terminal.helpmessenger.annotations.NonOptArgument;
import net.datenwerke.rs.terminal.service.terminal.hooks.SubCommandContainer;
import net.datenwerke.rs.terminal.service.terminal.hooks.TerminalCommandHook;
import net.datenwerke.rs.terminal.service.terminal.locale.TerminalMessages;
import net.datenwerke.rs.terminal.service.terminal.obj.CommandResult;
import net.datenwerke.rs.utils.localization.LocalizationServiceImpl;
import net.datenwerke.rs.utils.reflection.ProxyUtils;

public class HelpMessageInterceptor implements MethodInterceptor {

	private final TerminalMessages messages = LocalizationServiceImpl.getMessages(TerminalMessages.class);
	
	private ProxyUtils proxyUtils;
	
	@Inject
	public void setProxyUtils(ProxyUtils proxyUtils) {
		this.proxyUtils = proxyUtils;
	}
	
	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		Object[] args = invocation.getArguments();
		if(args.length != 2)
			throw new IllegalArgumentException("Expect two arguments");
		if(! (args[0] instanceof CommandParser))
			throw new IllegalArgumentException("Expect arg 1 to be " + CommandParser.class);
		if(! (args[1] instanceof TerminalSession))
			throw new IllegalArgumentException("Expect arg 2 to be " + TerminalSession.class);
		
		
		CommandParser parser = (CommandParser) args[0];
		for(String arg : parser.getArguments()){
			if("-h".equals(arg) || "-?".equals(arg) || "--help".equals(arg)){
				return displayHelpMessage(invocation);
			}
		}
		
		try{
			return invocation.proceed();
		} catch(IllegalArgumentException e){
			return displayHelpMessage(invocation, e.getMessage());
		}
	}

	private CommandResult displayHelpMessage(MethodInvocation invocation) throws Throwable {
		return displayHelpMessage(invocation, null);
	}
	
	private CommandResult displayHelpMessage(MethodInvocation invocation, String msg) throws Throwable {
		if(invocation.getThis() instanceof SubCommandContainer)
			return displayMessageForSubCommands(invocation, msg);
		else
			return displayMessageForSimpleCommand(invocation, msg);
	}

	private CommandResult displayMessageForSubCommands(MethodInvocation invocation, String message) throws Throwable {
		Object[] args = invocation.getArguments();
		CommandParser parser = (CommandParser) args[0];
		TerminalSession session = (TerminalSession) args[1];
		
		SubCommandContainer subCommandContainer = (SubCommandContainer) invocation.getThis();
		TerminalCommandHook subCommand = subCommandContainer.getSubCommand(parser, session);
		if(null != subCommand)
			return (CommandResult) invocation.proceed();
		
		CommandResult result = new CommandResult();
		
		CliHelpMessage helpAnno = invocation.getMethod().getAnnotation(CliHelpMessage.class);
		
		if(null != message){
			result.addResultLine(message);
			result.addResultLine();
		}
		
		result.addResultLine(helpAnno.name() + ", version " + helpAnno.version());
		result.addResultLine();
		
		result.addResultLine(messages.usage() + ": " + helpAnno.name() + " SubCommand");
		result.addResultLine();
		
		RSTableModel table = new RSTableModel();
		table.addDataRow(new RSStringTableRow("h", ":", messages.helpFlagDescription()));
		
				
		for(TerminalCommandHook cmd: subCommandContainer.getSubCommands()){
			Class<?> cmdType = cmd.getClass();
			Class<?> noProxy = proxyUtils.getUnproxiedClass(cmdType);
			Method method = noProxy.getMethod("execute", CommandParser.class, TerminalSession.class);
			CliHelpMessage subCommandHelp = method.getAnnotation(CliHelpMessage.class);
			if(null != subCommandHelp){
				String desc = LocalizationServiceImpl.getMessage(subCommandHelp.messageClass(), subCommandHelp.description());
				table.addDataRow(new RSStringTableRow(subCommandHelp.name(), ":", desc));
			}
		}
		
		result.addResultTable(table);
		
		result.addResultLine(messages.helpDescription() + ": " + LocalizationServiceImpl.getMessage(helpAnno.messageClass(), helpAnno.description()));
		
		return result;
	}

	private CommandResult displayMessageForSimpleCommand(MethodInvocation invocation, String message) {
		
		CommandResult result = new CommandResult();
		
		if(null != message){
			result.addResultLine(message);
			result.addResultLine();
		}
		
		CliHelpMessage helpAnno = invocation.getMethod().getAnnotation(CliHelpMessage.class);
		
		result.addResultLine(helpAnno.name() + ", version " + helpAnno.version());
		result.addResultLine();
		
		result.addResultLine(messages.usage() + ": " + getUsage(helpAnno));
		result.addResultLine();
		
		RSTableModel table = new RSTableModel();
		table.addDataRow(new RSStringTableRow("h", ":", messages.helpFlagDescription()));
		
		for(Argument arg : helpAnno.args()){
			String desc = LocalizationServiceImpl.getMessage(helpAnno.messageClass(), arg.description());
			table.addDataRow(new RSStringTableRow(arg.flag(), ":", desc));
		}
		
		for(NonOptArgument arg : helpAnno.nonOptArgs()){
			String desc = LocalizationServiceImpl.getMessage(helpAnno.messageClass(), arg.description());
			table.addDataRow(new RSStringTableRow(arg.name(), ":", desc));
		}
		
		result.addResultTable(table);
		
		result.addResultLine(messages.helpDescription() + ": " + LocalizationServiceImpl.getMessage(helpAnno.messageClass(), helpAnno.description()));
		
		return result;
	}

	private String getUsage(CliHelpMessage helpAnno) {
		StringBuilder sb = new StringBuilder();
		
		sb.append(helpAnno.name()).append(" ");
		
		for(Argument arg : helpAnno.args()){
			if(!arg.mandatory())
				sb.append("[");
			
			sb.append("-").append(arg.flag());
			
			if(!arg.mandatory())
				sb.append("]");
			
			sb.append(" ");
		}
		
		for(NonOptArgument arg : helpAnno.nonOptArgs()){
			if(!arg.mandatory())
				sb.append("[");
			
			sb.append(arg.name());
			
			if(!arg.mandatory())
				sb.append("]");
			
			sb.append(" ");
			
			if(arg.varArgs()){
				sb.append("[");
				
				sb.append(arg.name());
				
				sb.append("...] ");
			}
		}
		
		return sb.toString();
	}

}
