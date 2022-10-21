package net.datenwerke.rs.incubator.service.xslt.terminal.commands;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.List;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import com.google.inject.Inject;

import net.datenwerke.rs.fileserver.service.fileserver.FileServerService;
import net.datenwerke.rs.fileserver.service.fileserver.entities.AbstractFileServerNode;
import net.datenwerke.rs.fileserver.service.fileserver.entities.FileServerFile;
import net.datenwerke.rs.incubator.service.xslt.locale.XsltCommandMessages;
import net.datenwerke.rs.terminal.service.terminal.TerminalSession;
import net.datenwerke.rs.terminal.service.terminal.helpers.AutocompleteHelper;
import net.datenwerke.rs.terminal.service.terminal.helpers.CommandParser;
import net.datenwerke.rs.terminal.service.terminal.helpmessenger.annotations.CliHelpMessage;
import net.datenwerke.rs.terminal.service.terminal.helpmessenger.annotations.NonOptArgument;
import net.datenwerke.rs.terminal.service.terminal.hooks.TerminalCommandHook;
import net.datenwerke.rs.terminal.service.terminal.obj.CommandResult;
import net.datenwerke.rs.terminal.service.terminal.vfs.exceptions.VFSException;
import net.datenwerke.security.service.security.SecurityService;
import net.datenwerke.security.service.security.exceptions.ViolatedSecurityException;
import net.datenwerke.security.service.security.rights.Read;
import net.datenwerke.security.service.security.rights.Write;
import net.sf.saxon.lib.FeatureKeys;
import net.sf.saxon.lib.Logger;
import net.sf.saxon.lib.StandardErrorListener;
import net.sf.saxon.lib.StandardLogger;

public class XsltCommand implements TerminalCommandHook {

   public static final String BASE_COMMAND = "xslt";

   private final FileServerService fileService;
   private final SecurityService securityService;

   @Inject
   public XsltCommand(FileServerService fileService, SecurityService securityService) {
      this.fileService = fileService;
      this.securityService = securityService;
   }

   @Override
   public boolean consumes(CommandParser parser, TerminalSession session) {
      return BASE_COMMAND.equals(parser.getBaseCommand());
   }

   @CliHelpMessage(messageClass = XsltCommandMessages.class, name = BASE_COMMAND, description = "commandXslt_description", nonOptArgs = {
         @NonOptArgument(name = "stylesheet", description = "commandXslt_stylesheet", mandatory = true),
         @NonOptArgument(name = "intput", description = "commandXslt_input", mandatory = true),
         @NonOptArgument(name = "output", description = "commandXslt_output", mandatory = true) })
   @Override
   public CommandResult execute(CommandParser parser, TerminalSession session) {
      List<String> args = parser.getNonOptionArguments();
      if (args.size() < 3)
         throw new IllegalArgumentException("Expected at least 3 arguments");

      try {
         FileServerFile stylesheet = (FileServerFile) session.getObjectResolver().getObject(args.get(0), Read.class);
         FileServerFile input = (FileServerFile) session.getObjectResolver().getObject(args.get(1), Read.class);

         FileServerFile output = null;

         try {
            output = (FileServerFile) session.getObjectResolver().getObject(args.get(2));
         } catch (Exception e) {
         }
         if (null != output && !securityService.checkRights(output, Write.class))
            throw new ViolatedSecurityException(output, Write.class);

         if (null == output) {
            AbstractFileServerNode parent = null;
            try {
               parent = (AbstractFileServerNode) session.getFileSystem().getCurrentLocation().getObject();
               if (!securityService.checkRights(parent, Write.class))
                  throw new ViolatedSecurityException(parent, Write.class);
            } catch (VFSException e) {
               return new CommandResult(e.getMessage());
            }

            output = new FileServerFile();
            output.setName(args.get(2));
            output.setContentType("text/xml");
            if (null != parent)
               parent.addChild(output);
            fileService.persist(output);
         }

         ByteArrayOutputStream errorOut = new ByteArrayOutputStream();
         TransformerFactory transformerFactory = TransformerFactory.newInstance();

         transformerFactory.setAttribute(FeatureKeys.ALLOW_EXTERNAL_FUNCTIONS, "false");

         Logger log = new StandardLogger(new PrintStream(errorOut));

         ((StandardErrorListener) transformerFactory.getErrorListener()).setLogger(log);

         Transformer trans = transformerFactory
               .newTransformer(new StreamSource(new StringReader(new String(stylesheet.getData()))));

         StringWriter out = new StringWriter();
         trans.transform(new StreamSource(new StringReader(new String(input.getData()))), new StreamResult(out));

         final String result = out.toString();

         String error = errorOut.toString();

         output.setData(result.getBytes());
         fileService.merge(output);

         return new CommandResult("Transformation succeded: " + error);

      } catch (RuntimeException e) {
         throw e;
      } catch (Exception e) {
         throw new RuntimeException(e);
      }
   }

   @Override
   public void addAutoCompletEntries(AutocompleteHelper autocompleteHelper, TerminalSession session) {
      autocompleteHelper.autocompleteBaseCommand(BASE_COMMAND);
   }

}
