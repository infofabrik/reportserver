package net.datenwerke.rs.dot.service.dot;

import java.io.IOException;

import guru.nidi.graphviz.engine.Format;

public interface DotService {

   String render(Format format, String dot, int width) throws IOException;
}
