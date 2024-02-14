package net.datenwerke.rs.transport.client.transport;

import java.util.List;

import net.datenwerke.rs.transport.client.transport.dto.TransportCheckEntryDto;

public interface TransportUiService {

   void displayAnalysisResult(List<TransportCheckEntryDto> result);
}
