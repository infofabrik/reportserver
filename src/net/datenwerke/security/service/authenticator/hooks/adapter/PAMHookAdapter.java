package net.datenwerke.security.service.authenticator.hooks.adapter;

import java.util.LinkedHashSet;

import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.security.service.authenticator.ReportServerPAM;
import net.datenwerke.security.service.authenticator.hooks.PAMHook;

/**
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.hookservices.HookAdapterProcessor")
public class PAMHookAdapter implements PAMHook {

   @Override
   public void beforeStaticPamConfig() {
   }

   @Override
   public void afterStaticPamConfig(LinkedHashSet<ReportServerPAM> pams) {
   }

}
