package net.datenwerke.rs.dot.service.dot;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import guru.nidi.graphviz.engine.Format;
import guru.nidi.graphviz.engine.Graphviz;
import guru.nidi.graphviz.model.Graph;
import guru.nidi.graphviz.parse.Parser;

public class DotServiceImpl implements DotService {

   @Override
   public ByteArrayOutputStream render(Format format, String dot, int width) throws IOException {
      ByteArrayOutputStream os = new ByteArrayOutputStream();
      Graph g = new Parser().read(dot).toImmutable();
      Graphviz.fromGraph(g).width(width).render(format).toOutputStream(os);
      return os;
   }

}
