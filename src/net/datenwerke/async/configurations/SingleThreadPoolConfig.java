package net.datenwerke.async.configurations;

import net.datenwerke.async.PoolConfiguration;

public class SingleThreadPoolConfig implements PoolConfiguration {

   @Override
   public PoolType getType() {
      return PoolType.SINGLE;
   }

   @Override
   public int getCoreSize() {
      return 0;
   }

}
