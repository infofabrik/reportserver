package net.datenwerke.rs.dot.service.dot;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import guru.nidi.graphviz.engine.Format;
import guru.nidi.graphviz.engine.Graphviz;
import guru.nidi.graphviz.model.Graph;
import guru.nidi.graphviz.parse.Parser;

public class DotServiceImpl implements DotService {

   @Override
   public ByteArrayOutputStream render(BinaryFormat format, String dot, int width) throws IOException {
      ByteArrayOutputStream os = new ByteArrayOutputStream();
      Graph g = new Parser().read(dot).toImmutable();
      Graphviz.fromGraph(g).width(width).render(convertFormat(format)).toOutputStream(os);
      return os;
   }

   @Override
   public String render(TextFormat format, String dot, int width) throws IOException {
      Graph g = new Parser().read(dot).toImmutable();
      return Graphviz.fromGraph(g).width(width).render(convertFormat(format)).toString();
   }
   
   private Format convertFormat(BinaryFormat format) {
      switch (format) {
      case PNG:
         return Format.PNG;
      case PS:
         return Format.PS;
      default:
         return Format.PNG;
      }
   }
   
   private Format convertFormat(TextFormat format) {
      switch (format) {
      case DOT:
         return Format.DOT;
      case JSON:
         return Format.JSON;
      case JSON0:
         return Format.JSON0;
      case PLAIN:
         return Format.PLAIN;
      case PLAIN_EXT:
         return Format.PLAIN_EXT;
      case SVG:
         return Format.SVG;
      case XDOT:
         return Format.XDOT;
      default:
         return Format.SVG;
      }
   }

}
