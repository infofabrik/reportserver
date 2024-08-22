package net.datenwerke.rs.dot.service.dot;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Optional;

import net.datenwerke.rs.base.service.renderer.BinaryFormat;
import net.datenwerke.rs.base.service.renderer.TextFormat;

public interface DotService {

   ByteArrayOutputStream render(BinaryFormat format, String dot, Optional<Integer> width, Optional<Integer> height)
         throws IOException;

   String render(TextFormat format, String dot, Optional<Integer> width, Optional<Integer> height) throws IOException;

}
