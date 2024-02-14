package net.datenwerke.rs.keyutils.server.keyutils;

import javax.inject.Singleton;

import com.google.inject.Inject;

import net.datenwerke.rs.keyutils.client.keyutils.rpc.KeyUtilsRpcService;
import net.datenwerke.rs.keyutils.service.keyutils.KeyNameGeneratorService;
import net.datenwerke.security.server.SecuredRemoteServiceServlet;

@Singleton
public class KeyUtilsRpcServiceImpl extends SecuredRemoteServiceServlet implements KeyUtilsRpcService{

   private static final long serialVersionUID = 37717464943120715L;
   
   private final KeyNameGeneratorService keyNameGeneratorService;
   
   @Inject
   public KeyUtilsRpcServiceImpl(KeyNameGeneratorService keyNameGeneratorService) {
      super();
      this.keyNameGeneratorService = keyNameGeneratorService;
   }

   @Override
   public String generateDefaultKey() {
      return keyNameGeneratorService.generateDefaultKey();
   }
}
