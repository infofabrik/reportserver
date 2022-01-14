package net.datenwerke.dbpool.hooks;

import java.sql.Connection;

import com.google.inject.Inject;
import com.mchange.v2.c3p0.ConnectionCustomizer;

import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;

public class C3p0ConnectionHook implements ConnectionCustomizer {

   @Inject
   static protected HookHandlerService hookHandler;

   public C3p0ConnectionHook() {
      // dummy
   }

   @Override
   public void onAcquire(final Connection c, String parentDataSourceIdentityToken) throws Exception {
      hookHandler.getHookers(DbPoolConnectionHook.class).forEach(hooker -> hooker.onAcquire(c));
   }

   @Override
   public void onDestroy(final Connection c, String parentDataSourceIdentityToken) throws Exception {
      hookHandler.getHookers(DbPoolConnectionHook.class).forEach(hooker -> hooker.onDestroy(c));
   }

   @Override
   public void onCheckOut(final Connection c, String parentDataSourceIdentityToken) throws Exception {
      hookHandler.getHookers(DbPoolConnectionHook.class).forEach(hooker -> hooker.onCheckOut(c));
   }

   @Override
   public void onCheckIn(final Connection c, String parentDataSourceIdentityToken) throws Exception {
      hookHandler.getHookers(DbPoolConnectionHook.class).forEach(hooker -> hooker.onCheckIn(c));
   }

}
