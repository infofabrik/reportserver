package net.datenwerke.rs.terminal.server.terminal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.inject.persist.Transactional;

import net.datenwerke.gxtdto.client.servercommunication.exceptions.ExpectedException;
import net.datenwerke.gxtdto.client.servercommunication.exceptions.ServerCallFailedException;
import net.datenwerke.gxtdto.server.dtomanager.DtoService;
import net.datenwerke.rs.terminal.client.terminal.dto.AutocompleteResultDto;
import net.datenwerke.rs.terminal.client.terminal.dto.CommandResultDto;
import net.datenwerke.rs.terminal.client.terminal.dto.exceptions.CommandNotFoundExceptionDto;
import net.datenwerke.rs.terminal.client.terminal.dto.exceptions.MaxAutocompleteResultsExceededExceptionDto;
import net.datenwerke.rs.terminal.client.terminal.dto.exceptions.SessionNotFoundExceptionDto;
import net.datenwerke.rs.terminal.client.terminal.rpc.TerminalRpcService;
import net.datenwerke.rs.terminal.service.terminal.TerminalService;
import net.datenwerke.rs.terminal.service.terminal.TerminalSession;
import net.datenwerke.rs.terminal.service.terminal.exceptions.CommandNotFoundException;
import net.datenwerke.rs.terminal.service.terminal.exceptions.MaxAutocompleteResultsExceededException;
import net.datenwerke.rs.terminal.service.terminal.exceptions.SessionNotFoundException;
import net.datenwerke.rs.terminal.service.terminal.exceptions.TerminalException;
import net.datenwerke.rs.terminal.service.terminal.genrights.TerminalSecurityTarget;
import net.datenwerke.rs.terminal.service.terminal.obj.AutocompleteResult;
import net.datenwerke.rs.terminal.service.terminal.obj.CommandResult;
import net.datenwerke.security.server.SecuredRemoteServiceServlet;
import net.datenwerke.security.service.security.annotation.GenericTargetVerification;
import net.datenwerke.security.service.security.annotation.RightsVerification;
import net.datenwerke.security.service.security.annotation.SecurityChecked;
import net.datenwerke.security.service.security.rights.Execute;

@Singleton
public class TerminalRpcServiceImpl extends SecuredRemoteServiceServlet
		implements TerminalRpcService {

	private final Logger logger = LoggerFactory.getLogger(getClass().getName());
	
	class TerminalCommandRollbackException extends RuntimeException{
		/**
		 * 
		 */
		private static final long serialVersionUID = -3401647716212093896L;
		
		private final CommandResultDto result;

		public TerminalCommandRollbackException(CommandResultDto result) {
			this.result = result;
		}

		public CommandResultDto getResult() {
			return result;
		}
	}
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2096158793766783006L;
	
	private final DtoService dtoService;
	private final TerminalService terminalService;

	@Inject
	public TerminalRpcServiceImpl(
		DtoService dtoService,
		TerminalService terminalService
		){
		
		/* store objects */
		this.dtoService = dtoService;
		this.terminalService = terminalService;
	}
	
	@Override
	@Transactional(rollbackOn={Exception.class})
	@SecurityChecked(
		genericTargetVerification = @GenericTargetVerification(
			target=TerminalSecurityTarget.class,
			verify=@RightsVerification(rights=Execute.class)
		)
	)
	public String initSession() throws ServerCallFailedException {
		return terminalService.initTerminalSession().getSessionId();
	}
	
	@Override
	@Transactional(rollbackOn={Exception.class})
	@SecurityChecked(
		genericTargetVerification = @GenericTargetVerification(
			target=TerminalSecurityTarget.class,
			verify=@RightsVerification(rights=Execute.class)
		)
	)
	public void closeSession(String sessionId) throws ServerCallFailedException {
		terminalService.closeTerminalSession(sessionId);
	}

	
	@Override
	@Transactional(rollbackOn={Exception.class})
	@SecurityChecked(
		genericTargetVerification = @GenericTargetVerification(
			target=TerminalSecurityTarget.class,
			verify=@RightsVerification(rights=Execute.class)
		)
	)
	public AutocompleteResultDto autocomplete(String sessionId, String command, int cursorPosition) throws ServerCallFailedException, ExpectedException {
		try {
			TerminalSession session = terminalService.getTerminalSession(sessionId);
			
			AutocompleteResult result = session.autocomplete(command, cursorPosition);
			return (AutocompleteResultDto) dtoService.createDto(result);
		} catch (MaxAutocompleteResultsExceededException e) {
			throw new MaxAutocompleteResultsExceededExceptionDto(e.getNumberOfResults());
		} catch(SessionNotFoundException e){
			throw new SessionNotFoundExceptionDto();
		}
	}

	@Override
	@Transactional(rollbackOn={Exception.class})
		@SecurityChecked(
		genericTargetVerification = @GenericTargetVerification(
			target=TerminalSecurityTarget.class,
			verify=@RightsVerification(rights=Execute.class)
		)
	)
	public AutocompleteResultDto autocomplete(String sessionId, String command,
			int cursorPosition, boolean forceResult) throws ServerCallFailedException, ExpectedException {
		try {
			TerminalSession session = terminalService.getTerminalSession(sessionId);
			
			AutocompleteResult result = session.autocomplete(command, cursorPosition, forceResult);
			return (AutocompleteResultDto) dtoService.createDto(result);
		} catch (MaxAutocompleteResultsExceededException e) {
			throw new MaxAutocompleteResultsExceededExceptionDto(e.getNumberOfResults());
		} catch(SessionNotFoundException e){
			throw new SessionNotFoundExceptionDto();
		}
	}

	@Override
	@SecurityChecked(
			genericTargetVerification = @GenericTargetVerification(
				target=TerminalSecurityTarget.class,
				verify=@RightsVerification(rights=Execute.class)
			)
		)
	public CommandResultDto execute(String sessionId, String command) throws ServerCallFailedException, ExpectedException {
		try{
			CommandResultDto result = doExecute(sessionId, command);
			return result;
		} catch(TerminalCommandRollbackException e){
			return e.getResult();
		}
	}

	@Transactional(rollbackOn={Exception.class})
	@SecurityChecked(
		genericTargetVerification = @GenericTargetVerification(
			target=TerminalSecurityTarget.class,
			verify=@RightsVerification(rights=Execute.class)
		)
	)
	protected CommandResultDto doExecute(String sessionId, String command) throws ServerCallFailedException, ExpectedException {
		
		try{
			TerminalSession session = terminalService.getTerminalSession(sessionId);
			
			CommandResult result = session.execute(command);
			CommandResultDto resultDto = (CommandResultDto) dtoService.createDto(result);
			
			if(! result.isCommitTransaction())
				throw new TerminalCommandRollbackException(resultDto);
			
			return resultDto;
		} catch(CommandNotFoundException e){
			throw new CommandNotFoundExceptionDto();
		} catch(SessionNotFoundException e){
			throw new SessionNotFoundExceptionDto();
		} catch(Exception e){
			if(e instanceof NullPointerException)
				logger.warn( e.getMessage(), e);
			
			if(e instanceof TerminalCommandRollbackException)
				throw (TerminalCommandRollbackException)e;
			else
				throw new ServerCallFailedException(e);
		}
	}

	@Override
	@Transactional(rollbackOn={Exception.class})
	@SecurityChecked(
		genericTargetVerification = @GenericTargetVerification(
			target=TerminalSecurityTarget.class,
			verify=@RightsVerification(rights=Execute.class)
		)
	)
	public CommandResultDto ctrlCPressed(String sessionId)
			throws ServerCallFailedException, ExpectedException {
		try{
			TerminalSession session = terminalService.getTerminalSession(sessionId);
			
			CommandResult result = session.ctrlC();
			return (CommandResultDto) dtoService.createDto(result);
		} catch(CommandNotFoundException e){
			throw new CommandNotFoundExceptionDto();
		} catch(SessionNotFoundException e){
			throw new SessionNotFoundExceptionDto();
		} catch(TerminalException e){
			throw new ServerCallFailedException(e);
		}
	}
	



}
