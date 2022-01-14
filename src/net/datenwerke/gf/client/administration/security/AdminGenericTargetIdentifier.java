package net.datenwerke.gf.client.administration.security;

import net.datenwerke.gf.service.genrights.AdministrationViewSecurityTarget;
import net.datenwerke.security.client.security.GenericTargetIdentifier;
import net.datenwerke.security.service.security.annotation.GenericTargetIdentifierMapper;

@GenericTargetIdentifierMapper(AdministrationViewSecurityTarget.class)
public class AdminGenericTargetIdentifier implements GenericTargetIdentifier {

   /**
    * 
    */
   private static final long serialVersionUID = 5143699016878107485L;
}