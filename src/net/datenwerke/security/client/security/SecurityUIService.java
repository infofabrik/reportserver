package net.datenwerke.security.client.security;

import net.datenwerke.security.client.security.dto.GenericSecurityTargetContainer;
import net.datenwerke.security.client.security.dto.RightDto;

public interface SecurityUIService {

   public static final String REPORTSERVER_EVENT_GENERIC_RIGHTS_LOADED = "REPORTSERVER_EVENT_GENERIC_RIGHTS_LOADED"; //$NON-NLS-1$

   void setGenericSecurityContainer(GenericSecurityTargetContainer genericSecurityContainer);

   boolean hasRight(Class<? extends GenericTargetIdentifier> identifier, Class<? extends RightDto> toCheck);

   boolean isSuperUser();
}
