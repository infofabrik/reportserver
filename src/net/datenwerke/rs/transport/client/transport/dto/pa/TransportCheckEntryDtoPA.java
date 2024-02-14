package net.datenwerke.rs.transport.client.transport.dto.pa;

import com.google.gwt.core.client.GWT;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.PropertyAccess;

import net.datenwerke.rs.transport.client.transport.dto.TransportCheckEntryDto;

public interface TransportCheckEntryDtoPA extends PropertyAccess<TransportCheckEntryDto> {

   public static final TransportCheckEntryDtoPA INSTANCE = GWT.create(TransportCheckEntryDtoPA.class);

   /* Properties */
   ValueProvider<TransportCheckEntryDto, String> key();
   
   ValueProvider<TransportCheckEntryDto, String> result();
   
   ValueProvider<TransportCheckEntryDto, String> errorMsg();
   
}

