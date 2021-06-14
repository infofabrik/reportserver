/**
 * 
 */
package net.datenwerke.rs.terminal.client.terminal.security;

import net.datenwerke.rs.terminal.service.terminal.genrights.TerminalSecurityTarget;
import net.datenwerke.security.client.security.GenericTargetIdentifier;
import net.datenwerke.security.service.security.annotation.GenericTargetIdentifierMapper;

/**
 * Transport object to identify the corresponding generic security target.
 *
 */
@GenericTargetIdentifierMapper(TerminalSecurityTarget.class)
public class TerminalGenericTargetIdentifier implements GenericTargetIdentifier {

   /**
    * 
    */
   private static final long serialVersionUID = 5065607232376600170L;

}