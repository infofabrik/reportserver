package net.datenwerke.gf.server.juel;

import javax.inject.Singleton;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.gf.client.juel.dto.JuelResultDto;
import net.datenwerke.gf.client.juel.rpc.JuelRpcService;
import net.datenwerke.gf.service.juel.JuelResult;
import net.datenwerke.gxtdto.client.servercommunication.exceptions.ServerCallFailedException;
import net.datenwerke.gxtdto.server.dtomanager.DtoService;
import net.datenwerke.rs.utils.juel.SimpleJuel;
import net.datenwerke.security.server.SecuredRemoteServiceServlet;

@Singleton
public class JuelRpcServiceImpl extends SecuredRemoteServiceServlet implements JuelRpcService {

   /**
    * 
    */
   private static final long serialVersionUID = 5137215532189202055L;

   private final Provider<SimpleJuel> juelServiceProvider;
   private final Provider<DtoService> dtoServiceProvider;

   @Inject
   public JuelRpcServiceImpl(Provider<SimpleJuel> juelServiceProvider, Provider<DtoService> dtoServiceProvider) {
      super();

      this.juelServiceProvider = juelServiceProvider;
      this.dtoServiceProvider = dtoServiceProvider;
   }

   @Override
   public JuelResultDto evaluateExpression(String expression) throws ServerCallFailedException {
      Object result = juelServiceProvider.get().parseAsObject(expression);

      try {
         return (JuelResultDto) dtoServiceProvider.get().createDto(new JuelResult(result));
      } catch (Exception e) {
         throw new ServerCallFailedException(e);
      }
   }

}
