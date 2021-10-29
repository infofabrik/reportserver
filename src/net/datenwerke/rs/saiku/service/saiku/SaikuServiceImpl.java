package net.datenwerke.rs.saiku.service.saiku;

import org.apache.commons.configuration2.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;

import net.datenwerke.rs.utils.config.ConfigService;

public class SaikuServiceImpl implements SaikuService {

   private final Logger logger = LoggerFactory.getLogger(getClass().getName());

   private final ConfigService configService;

   @Inject
   public SaikuServiceImpl(ConfigService configService) {
      this.configService = configService;

      logger.info("loaded saiku service");
   }

   @Override
   public long getContextMaxSize() {
      Configuration cf = configService.getConfigFailsafe(SaikuModule.CONFIG_FILE);
      return cf.getLong("saiku.context.maxsize", 20);
   }

   @Override
   public long getContextExpiresAfter() {
      Configuration cf = configService.getConfigFailsafe(SaikuModule.CONFIG_FILE);
      return cf.getLong("saiku.context.expiresafter", 60);
   }

}
