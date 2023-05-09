package net.datenwerke.rs.dot.service.dot;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public interface DotService {

   ByteArrayOutputStream render(BinaryFormat format, String dot, int width) throws IOException;
   
   String render(TextFormat format, String dot, int width) throws IOException;
}
