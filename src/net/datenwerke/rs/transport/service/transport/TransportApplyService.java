package net.datenwerke.rs.transport.service.transport;

import java.util.Optional;

import org.apache.commons.lang3.tuple.ImmutablePair;

import com.google.inject.ImplementedBy;

import net.datenwerke.eximport.im.ImportResult;
import net.datenwerke.rs.transport.service.transport.entities.Transport;

@ImplementedBy(DummyTransportApplyServiceImpl.class)
public interface TransportApplyService {
    
   Optional<ImmutablePair<ImportResult, Exception>> applyTransport(Transport transport);
}
