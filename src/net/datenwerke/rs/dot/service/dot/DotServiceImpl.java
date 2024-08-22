package net.datenwerke.rs.dot.service.dot;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import javax.inject.Inject;
import javax.inject.Provider;

import guru.nidi.graphviz.engine.Format;
import guru.nidi.graphviz.engine.Graphviz;
import guru.nidi.graphviz.model.Graph;
import guru.nidi.graphviz.parse.Parser;
import net.datenwerke.rs.adminutils.service.systemconsole.generalinfo.GeneralInfoService;
import net.datenwerke.rs.base.service.renderer.BinaryFormat;
import net.datenwerke.rs.base.service.renderer.TextFormat;
import net.datenwerke.rs.utils.file.RsFileUtils;

public class DotServiceImpl implements DotService {
   
   private final Provider<GeneralInfoService> generalInfoServiceProvider;
   
   @Inject
   public DotServiceImpl(
         Provider<GeneralInfoService> generalInfoServiceProvider
         ) {
      this.generalInfoServiceProvider = generalInfoServiceProvider;
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
   
   private void renderPreconditions() {
      // V8 writes a file in user.home, so it must be readable and writable
      String userHome = generalInfoServiceProvider.get().getUserHome(false);
      Path userHomePath = Paths.get(userHome);
      if (! RsFileUtils.checkReadable(userHomePath))
         throw new IllegalArgumentException("'" + userHome + "' is not readable!");
      if (! RsFileUtils.checkWritable(userHomePath))
         throw new IllegalArgumentException("'" + userHome + "' is not writable!");
   }

   @Override
   public ByteArrayOutputStream render(BinaryFormat format, String dot, Optional<Integer> width,
         Optional<Integer> height) throws IOException {
      renderPreconditions();
      ByteArrayOutputStream os = new ByteArrayOutputStream();
      Graph g = new Parser().read(dot).toImmutable();
      configureSize(g, width, height)
         .render(convertFormat(format))
         .toOutputStream(os);
      return os;
   }

   @Override
   public String render(TextFormat format, String dot, Optional<Integer> width,
         Optional<Integer> height) throws IOException {
      renderPreconditions();
      Graph g = new Parser().read(dot).toImmutable();
      return configureSize(g, width, height)
         .render(convertFormat(format))
         .toString();
   }
   
   private Graphviz configureSize(Graph g, Optional<Integer> width,
         Optional<Integer> height) {
      if (width.isPresent() && height.isPresent()) {
         return Graphviz.fromGraph(g)
            .width(width.get())
            .height(height.get());
      } else if (width.isPresent()) {
         return Graphviz.fromGraph(g)
            .width(width.get());
      } else if (height.isPresent()) {
         return Graphviz.fromGraph(g)
            .height(height.get());
      } else {
         return Graphviz.fromGraph(g);
      }
   }

}
