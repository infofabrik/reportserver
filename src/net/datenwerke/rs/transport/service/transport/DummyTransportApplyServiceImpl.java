package net.datenwerke.rs.transport.service.transport;

import java.util.Optional;

import org.apache.commons.lang3.tuple.ImmutablePair;

import net.datenwerke.eximport.im.ImportResult;
import net.datenwerke.rs.transport.service.transport.entities.Transport;

public class DummyTransportApplyServiceImpl implements TransportApplyService {

   @Override
   public Optional<ImmutablePair<ImportResult, Exception>> applyTransport(Transport transport) {
      throw new UnsupportedOperationException();
   }

}
