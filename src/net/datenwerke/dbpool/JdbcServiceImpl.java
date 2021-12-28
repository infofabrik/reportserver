package net.datenwerke.dbpool;

import com.google.inject.Inject;

import net.datenwerke.dbpool.hooks.JdbcUrlAdapterHook;
import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;

public class JdbcServiceImpl implements JdbcService {

   private final HookHandlerService hookHandler;

   @Inject
   public JdbcServiceImpl(HookHandlerService hookHandler) {
      this.hookHandler = hookHandler;
   }

   @Override
   public String adaptJdbcUrl(String jdbcUrl) {
      if (null == jdbcUrl)
         return null;

      for (JdbcUrlAdapterHook adapter : hookHandler.getHookers(JdbcUrlAdapterHook.class))
         jdbcUrl = adapter.adaptJdbcUrl(jdbcUrl);

      return jdbcUrl;
   }
}
