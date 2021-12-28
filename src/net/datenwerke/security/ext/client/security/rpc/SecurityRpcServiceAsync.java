package net.datenwerke.security.ext.client.security.rpc;

import java.util.Collection;
import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

import net.datenwerke.security.client.security.GenericTargetIdentifier;
import net.datenwerke.security.client.security.dto.AceDto;
import net.datenwerke.security.client.security.dto.GenericSecurityTargetContainer;
import net.datenwerke.security.client.security.dto.SecurityViewInformation;
import net.datenwerke.treedb.client.treedb.dto.AbstractNodeDto;

public interface SecurityRpcServiceAsync {

   void loadSecurityViewInformation(AbstractNodeDto node, AsyncCallback<SecurityViewInformation> callback);

   void editACE(AbstractNodeDto node, AceDto ace, AsyncCallback<AceDto> callback);

   void removeACEs(AbstractNodeDto node, List<AceDto> aceDtos, AsyncCallback<Void> callback);

   void addACE(AbstractNodeDto node, AsyncCallback<AceDto> callback);

   void aceMoved(AbstractNodeDto node, AceDto ace, int index, AsyncCallback<AceDto> callback);

   void loadGenericSecurityViewInformation(GenericTargetIdentifier targetIdentifier,
         AsyncCallback<SecurityViewInformation> callback);

   void addACE(GenericTargetIdentifier targetIdentifier, AsyncCallback<AceDto> callback);

   void aceMoved(GenericTargetIdentifier targetIdentifier, AceDto ace, int index, AsyncCallback<AceDto> callback);

   void editACE(GenericTargetIdentifier targetIdentifier, AceDto ace, AsyncCallback<AceDto> callback);

   void removeACEs(GenericTargetIdentifier targetIdentifier, List<AceDto> aceDtos, AsyncCallback<Void> callback);

   void loadGenericRights(Collection<GenericTargetIdentifier> targetIdentifiers,
         AsyncCallback<GenericSecurityTargetContainer> callback);

}
