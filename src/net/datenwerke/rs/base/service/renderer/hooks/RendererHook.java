package net.datenwerke.rs.base.service.renderer.hooks;

import java.io.ByteArrayOutputStream;
import java.util.Optional;

import net.datenwerke.hookhandler.shared.hookhandler.interfaces.Hook;
import net.datenwerke.rs.base.service.renderer.BinaryFormat;
import net.datenwerke.rs.base.service.renderer.RendererException;
import net.datenwerke.rs.base.service.renderer.TextFormat;

public interface RendererHook extends Hook {

   boolean consumes(String contentType);

   ByteArrayOutputStream render(BinaryFormat format, String source, Optional<Integer> width, Optional<Integer> height)
         throws RendererException;

   String render(TextFormat format, String source, Optional<Integer> width, Optional<Integer> height)
         throws RendererException;
}
