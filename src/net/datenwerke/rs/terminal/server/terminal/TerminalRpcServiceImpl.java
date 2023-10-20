package net.datenwerke.rs.terminal.server.terminal;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;
import com.google.inject.persist.Transactional;

import net.datenwerke.gxtdto.client.dtomanager.Dto2PosoMapper;
import net.datenwerke.gxtdto.client.servercommunication.exceptions.ExpectedException;
import net.datenwerke.gxtdto.client.servercommunication.exceptions.ServerCallFailedException;
import net.datenwerke.gxtdto.server.dtomanager.DtoService;
import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
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
import net.datenwerke.rs.terminal.service.terminal.hooks.OpenTerminalHandlerHook;
import net.datenwerke.rs.terminal.service.terminal.obj.AutocompleteResult;
import net.datenwerke.rs.terminal.service.terminal.obj.CommandResult;
import net.datenwerke.rs.terminal.service.terminal.vfs.hooks.TreeBasedVirtualFileSystem;
import net.datenwerke.security.server.SecuredRemoteServiceServlet;
import net.datenwerke.security.service.security.annotation.GenericTargetVerification;
import net.datenwerke.security.service.security.annotation.RightsVerification;
import net.datenwerke.security.service.security.annotation.SecurityChecked;
import net.datenwerke.security.service.security.rights.Execute;
import net.datenwerke.security.service.treedb.entities.SecuredAbstractNode;
import net.datenwerke.treedb.client.treedb.dto.AbstractNodeDto;

@Singleton
public class TerminalRpcServiceImpl extends SecuredRemoteServiceServlet implements TerminalRpcService {

   private final Logger logger = LoggerFactory.getLogger(getClass().getName());

   class TerminalCommandRollbackException extends RuntimeException {
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
   private final Provider<HookHandlerService> hookHandlerProvider;

   @Inject
   public TerminalRpcServiceImpl(DtoService dtoService, 
         TerminalService terminalService,
         Provider<HookHandlerService> hookHandlerProvider) {

      /* store objects */
      this.dtoService = dtoService;
      this.terminalService = terminalService;
      this.hookHandlerProvider = hookHandlerProvider;
   }

   @Override
   @Transactional(rollbackOn = { Exception.class })
   @SecurityChecked(genericTargetVerification = @GenericTargetVerification(target = TerminalSecurityTarget.class, verify = @RightsVerification(rights = Execute.class)))
   public HashMap<String, String> initSession(AbstractNodeDto nodeDto, Dto2PosoMapper dto2PosoMapper) throws ServerCallFailedException {
      HashMap<String, String> properties = new HashMap<String, String>();
      String sessionId = terminalService.initTerminalSession().getSessionId();
      properties.put("sessionId", sessionId);
      String pathWay = null;

      if (null != nodeDto) {
         try {
            OpenTerminalHandlerHook hook = getOpenTerminalHandlerHook(dtoService.getPosoFromDtoMapper(dto2PosoMapper).getName());
            SecuredAbstractNode<?> node = hook.getNode(nodeDto.getId());
            TreeBasedVirtualFileSystem<?> vfs = hook.getVfs();
            
            if (null != vfs && null != node) {
               TerminalSession session = terminalService.getTerminalSession(sessionId);
               vfs.init(session);
               if (node.isFolder())
                  pathWay = vfs.getLocationFor(node).getAbsolutePath();
               else
                  pathWay = vfs.getLocationFor(node).getParentLocation().getAbsolutePath();

            }
         } catch (SessionNotFoundException e) {
            throw new SessionNotFoundExceptionDto();
         }
      }
      
      properties.put("pathWay", pathWay);
      return properties;
   }

   @Override
   @Transactional(rollbackOn = { Exception.class })
   @SecurityChecked(genericTargetVerification = @GenericTargetVerification(target = TerminalSecurityTarget.class, verify = @RightsVerification(rights = Execute.class)))
   public void closeSession(String sessionId) throws ServerCallFailedException {
      terminalService.closeTerminalSession(sessionId);
   }

   @Override
   @Transactional(rollbackOn = { Exception.class })
   @SecurityChecked(genericTargetVerification = @GenericTargetVerification(target = TerminalSecurityTarget.class, verify = @RightsVerification(rights = Execute.class)))
   public AutocompleteResultDto autocomplete(String sessionId, String command, int cursorPosition)
         throws ServerCallFailedException, ExpectedException {
      try {
         TerminalSession session = terminalService.getTerminalSession(sessionId);

         AutocompleteResult result = session.autocomplete(command, cursorPosition);
         return (AutocompleteResultDto) dtoService.createDto(result);
      } catch (MaxAutocompleteResultsExceededException e) {
         throw new MaxAutocompleteResultsExceededExceptionDto(e.getNumberOfResults());
      } catch (SessionNotFoundException e) {
         throw new SessionNotFoundExceptionDto();
      }
   }

   @Override
   @Transactional(rollbackOn = { Exception.class })
   @SecurityChecked(genericTargetVerification = @GenericTargetVerification(target = TerminalSecurityTarget.class, verify = @RightsVerification(rights = Execute.class)))
   public AutocompleteResultDto autocomplete(String sessionId, String command, int cursorPosition, boolean forceResult)
         throws ServerCallFailedException, ExpectedException {
      try {
         TerminalSession session = terminalService.getTerminalSession(sessionId);

         AutocompleteResult result = session.autocomplete(command, cursorPosition, forceResult);
         return (AutocompleteResultDto) dtoService.createDto(result);
      } catch (MaxAutocompleteResultsExceededException e) {
         throw new MaxAutocompleteResultsExceededExceptionDto(e.getNumberOfResults());
      } catch (SessionNotFoundException e) {
         throw new SessionNotFoundExceptionDto();
      }
   }

   @Override
   @SecurityChecked(genericTargetVerification = @GenericTargetVerification(target = TerminalSecurityTarget.class, verify = @RightsVerification(rights = Execute.class)))
   public CommandResultDto execute(String sessionId, String command)
         throws ServerCallFailedException, ExpectedException {
      try {
         CommandResultDto result = doExecute(sessionId, command);
         return result;
      } catch (TerminalCommandRollbackException e) {
         return e.getResult();
      }
   }

   @Transactional(rollbackOn = { Exception.class })
   @SecurityChecked(genericTargetVerification = @GenericTargetVerification(target = TerminalSecurityTarget.class, verify = @RightsVerification(rights = Execute.class)))
   protected CommandResultDto doExecute(String sessionId, String command)
         throws ServerCallFailedException, ExpectedException {

      try {
         TerminalSession session = terminalService.getTerminalSession(sessionId);

         CommandResult result = session.execute(command);
         CommandResultDto resultDto = (CommandResultDto) dtoService.createDto(result);

         if (!result.isCommitTransaction())
            throw new TerminalCommandRollbackException(resultDto);

         return resultDto;
      } catch (CommandNotFoundException e) {
         throw new CommandNotFoundExceptionDto();
      } catch (SessionNotFoundException e) {
         throw new SessionNotFoundExceptionDto();
      } catch (Exception e) {
         if (e instanceof NullPointerException)
            logger.warn(e.getMessage(), e);

         if (e instanceof TerminalCommandRollbackException)
            throw (TerminalCommandRollbackException) e;
         else
            throw new ServerCallFailedException(e);
      }
   }

   @Override
   @Transactional(rollbackOn = { Exception.class })
   @SecurityChecked(genericTargetVerification = @GenericTargetVerification(target = TerminalSecurityTarget.class, verify = @RightsVerification(rights = Execute.class)))
   public CommandResultDto ctrlCPressed(String sessionId) throws ServerCallFailedException, ExpectedException {
      try {
         TerminalSession session = terminalService.getTerminalSession(sessionId);

         CommandResult result = session.ctrlC();
         return (CommandResultDto) dtoService.createDto(result);
      } catch (CommandNotFoundException e) {
         throw new CommandNotFoundExceptionDto();
      } catch (SessionNotFoundException e) {
         throw new SessionNotFoundExceptionDto();
      } catch (TerminalException e) {
         throw new ServerCallFailedException(e);
      }
   }

   private OpenTerminalHandlerHook getOpenTerminalHandlerHook(String type) {
      return hookHandlerProvider.get().getHookers(OpenTerminalHandlerHook.class)
            .stream()
            .filter(h -> h.consumes(type))
            .findAny()
            .get();
   }

}
