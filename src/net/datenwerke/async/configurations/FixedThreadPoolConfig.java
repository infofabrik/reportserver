package net.datenwerke.async.configurations;

import net.datenwerke.async.PoolConfiguration;

public class FixedThreadPoolConfig implements PoolConfiguration {

   private final int coreSize;

   public FixedThreadPoolConfig(int coreSize) {
      this.coreSize = coreSize;
   }

   @Override
   public PoolType getType() {
      return PoolType.FIXED;
   }

   @Override
   public int getCoreSize() {
      return coreSize;
   }

}
