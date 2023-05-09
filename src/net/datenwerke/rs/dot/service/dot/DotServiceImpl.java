package net.datenwerke.rs.dot.service.dot;

import java.io.IOException;

import guru.nidi.graphviz.engine.Format;
import guru.nidi.graphviz.engine.Graphviz;
import guru.nidi.graphviz.model.Graph;
import guru.nidi.graphviz.parse.Parser;

public class DotServiceImpl implements DotService {

   @Override
   public String render(Format format, String dot, int width) throws IOException {
      Graph g = new Parser().read(dot).toImmutable();
      return Graphviz.fromGraph(g).width(width).render(format).toString();
   }

}
