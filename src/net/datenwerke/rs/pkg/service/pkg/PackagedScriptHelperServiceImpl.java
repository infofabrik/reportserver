package net.datenwerke.rs.pkg.service.pkg;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.zip.ZipEntry;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.script.Compilable;
import javax.script.CompiledScript;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.script.SimpleBindings;
import javax.servlet.ServletContext;

import org.apache.commons.configuration2.ImmutableHierarchicalConfiguration;
import org.apache.commons.configuration2.XMLConfiguration;
import org.apache.commons.configuration2.builder.BasicConfigurationBuilder;
import org.apache.commons.configuration2.builder.fluent.Parameters;
import org.apache.commons.configuration2.builder.fluent.XMLBuilderParameters;
import org.apache.commons.configuration2.ex.ConfigurationException;
import org.apache.commons.configuration2.io.FileHandler;
import org.apache.commons.lang3.text.StrMatcher;
import org.apache.commons.lang3.text.StrTokenizer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.datenwerke.rs.fileserver.service.fileserver.FileServerService;
import net.datenwerke.rs.fileserver.service.fileserver.entities.AbstractFileServerNode;
import net.datenwerke.rs.fileserver.service.fileserver.entities.FileServerFile;
import net.datenwerke.rs.fileserver.service.fileserver.entities.FileServerFolder;
import net.datenwerke.rs.fileserver.service.fileserver.terminal.commands.unzip.BasepathZipExtractConfigFactory;
import net.datenwerke.rs.license.service.LicenseService;
import net.datenwerke.rs.reportdoc.service.helper.ReportDocGroovyHelper;
import net.datenwerke.rs.scripting.service.scripting.ScriptResult;
import net.datenwerke.rs.scripting.service.scripting.SimpleScriptingService;
import net.datenwerke.rs.terminal.service.terminal.TerminalService;
import net.datenwerke.rs.terminal.service.terminal.TerminalSession;
import net.datenwerke.rs.terminal.service.terminal.vfs.VFSLocation;
import net.datenwerke.rs.terminal.service.terminal.vfs.VirtualFileSystemDeamon;
import net.datenwerke.rs.terminal.service.terminal.vfs.exceptions.VFSException;
import net.datenwerke.rs.utils.zip.ZipExtractionConfig;
import net.datenwerke.rs.utils.zip.ZipUtilsService;

public class PackagedScriptHelperServiceImpl implements PackagedScriptHelperService {

   private final Logger logger = LoggerFactory.getLogger(getClass().getName());

   private static final String GROOVY = "groovy";

   private final static String PKG_DIR = "pkg";

   private final Provider<ReportDocGroovyHelper> groovyHelperProvider;
   private final LicenseService licenseService;
   private TerminalService terminalService;
   private SimpleScriptingService scriptingService;
   private FileServerService fileServerService;
   private ZipUtilsService zipUtilsService;
   private Provider<ServletContext> servletContext;

   private BasepathZipExtractConfigFactory zipExtractConfigFactory;

   @Inject
   public PackagedScriptHelperServiceImpl(
         Provider<ReportDocGroovyHelper> groovyHelperProvider, 
         LicenseService licenseService,
         TerminalService terminalService, 
         SimpleScriptingService scriptingService, 
         FileServerService fileServerService,
         BasepathZipExtractConfigFactory zipExtractConfigFactory, 
         ZipUtilsService zipUtilsService,

         Provider<ServletContext> servletContext
         ) {

      this.groovyHelperProvider = groovyHelperProvider;
      this.licenseService = licenseService;
      this.terminalService = terminalService;
      this.scriptingService = scriptingService;
      this.fileServerService = fileServerService;
      this.zipExtractConfigFactory = zipExtractConfigFactory;
      this.zipUtilsService = zipUtilsService;
      this.servletContext = servletContext;
   }

   private CompiledScript compile(String script) throws IOException, ScriptException {
      ScriptEngineManager manager = new ScriptEngineManager();
      ScriptEngine groovy = manager.getEngineByName(GROOVY);
      Compilable compGroovy = (Compilable) groovy;
      return compGroovy.compile(script);
   }

   @Override
   public File getPackageDirectory() {
      return new File(servletContext.get().getRealPath(""), PKG_DIR);
   }

   @Override
   public FileServerFolder extractPackageTemporarily(InputStream is) throws FileNotFoundException, IOException {
      FileServerFolder targetDir = new FileServerFolder(UUID.randomUUID().toString());
      try {
         getFileServerTempDir().addChild(targetDir);
         fileServerService.persist(targetDir);
         fileServerService.merge(getFileServerTempDir());

         zipUtilsService.extractZip(is, zipExtractConfigFactory.create(targetDir));
         return targetDir;
      } catch (Exception e) {
         fileServerService.forceRemove(targetDir);
         throw e;
      }
   }

   @Override
   public FileServerFolder getFileServerTempDir() {
      FileServerFolder tmpDir = (FileServerFolder) fileServerService.getNodeByPath("/tmp", false);
      if (null == tmpDir) {
         tmpDir = new FileServerFolder("tmp");
         AbstractFileServerNode root = fileServerService.getRoots().get(0);
         root.addChild(tmpDir);
         fileServerService.persist(tmpDir);
         fileServerService.merge(root);
      }

      return tmpDir;
   }

   @Override
   public String executePackage(FileServerFolder targetDir) {
      return executePackage(targetDir, "");
   }

   @Override
   public String executePackage(FileServerFolder targetDir, String scriptOptions) {
      return executePackage(targetDir, scriptOptions, true, true, Optional.empty());
   }

   @Override
   public String executePackage(FileServerFolder targetDir, String scriptOptions, boolean executeRunScriptCommands,
         boolean executeCopyFilesCommands, Optional<String> copyFilesCustomDstPathPrefix) {
      final FileServerFile configFile = targetDir.getChildrenOfType(FileServerFile.class).stream()
            .filter(f -> f.getName().equals("package.xml")).findAny()
            .orElseThrow(() -> new IllegalArgumentException("Missing package.xml declaration."));

      StringBuilder resultBuilder = new StringBuilder();

      Parameters params = new Parameters();
      XMLBuilderParameters xmlParams = params.xml().setEncoding("UTF-8");
      BasicConfigurationBuilder<XMLConfiguration> builder = new BasicConfigurationBuilder<XMLConfiguration>(
            XMLConfiguration.class).configure(xmlParams);
      try {
         XMLConfiguration config = builder.getConfiguration();
         FileHandler handler = new FileHandler(config);
         handler.load(new ByteArrayInputStream(configFile.getData()));
         for (ImmutableHierarchicalConfiguration cfgNode : config.immutableChildConfigurationsAt(".")) {
            String name = cfgNode.getRootElementName();
            if ("runScript".equals(name)) {
               if (executeRunScriptCommands)
                  executePackageCommandRunSkript(targetDir, scriptOptions, resultBuilder, cfgNode);
            } else if ("copyFile".equals(name)) {
               if (executeCopyFilesCommands)
                  executePackageCommandCopyFiles(targetDir, cfgNode, copyFilesCustomDstPathPrefix);
            } else {
               logger.error("Error during initialisation. Unknown package operation :" + name);
            }
         }
      } catch (ConfigurationException e) {
         logger.warn("Could not execute package.", e);
         throw new IllegalStateException(e);
      }

      String result = resultBuilder.toString().trim();
      return "".equals(result) ? null : result;
   }

   private void executePackageCommandCopyFiles(FileServerFolder targetDir, ImmutableHierarchicalConfiguration cfgNode,
         Optional<String> customDstPathPrefix) {
      TerminalSession unscopedTerminalSession = terminalService.getUnscopedTerminalSession();
      unscopedTerminalSession.setCheckRights(false);
      VirtualFileSystemDeamon fs = unscopedTerminalSession.getFileSystem();
      VFSLocation targetLocation = fs.getLocationFor(targetDir);

      try {
         String srcPath = cfgNode.getString("[@src]");
         fs.setLocation(targetLocation);
         VFSLocation srcLocation = fs.getLocation(srcPath);

         String dstPath = customDstPathPrefix.isPresent() ? customDstPathPrefix.get() : cfgNode.getString("[@dst]");
         AbstractFileServerNode dstNode = fileServerService.getNodeByPath(dstPath);
         VFSLocation dstLocation = fs.getLocationFor(dstNode);

         dstLocation.getFilesystemManager().copyFilesTo(srcLocation, dstLocation, true);
      } catch (VFSException e) {
         logger.info("Could not copy file during package execution.", e);
         throw new IllegalStateException(e);
      }
   }

   private void executePackageCommandRunSkript(FileServerFolder targetDir, String scriptOptions,
         StringBuilder resultBuilder, ImmutableHierarchicalConfiguration cfgNode) {
      TerminalSession unscopedTerminalSession = terminalService.getUnscopedTerminalSession();
      unscopedTerminalSession.setCheckRights(false);
      VirtualFileSystemDeamon fs = unscopedTerminalSession.getFileSystem();
      VFSLocation targetLocation = fs.getLocationFor(targetDir);
      try {
         String scriptPath = cfgNode.getString("[@src]");
         fs.setLocation(targetLocation);
         VFSLocation scriptLocation = fs.getLocation(scriptPath);
         FileServerFile scriptFile = (FileServerFile) scriptLocation.getObject();

         Object result = null;

         if (licenseService.isEnterprise()) {
            result = scriptingService.executeScript(new String(scriptFile.getData()), unscopedTerminalSession,
                  new HashMap<String, Object>(), "--baseDir " + targetLocation.toString() + "/"
                        + ("".equals(scriptOptions) ? "" : " " + scriptOptions));
         } else {
            CompiledScript compiled = compile(new String(scriptFile.getData()));

            SimpleBindings binding = new SimpleBindings();
            ReportDocGroovyHelper groovyHelper = groovyHelperProvider.get();
            groovyHelper.setSession(unscopedTerminalSession);
            binding.put("GLOBALS", groovyHelper);

            StrTokenizer tokenizer = new StrTokenizer("--baseDir " + targetLocation.toString() + "/"
                  + ("".equals(scriptOptions) ? "" : " " + scriptOptions));
            tokenizer.setDelimiterMatcher(StrMatcher.trimMatcher());
            tokenizer.setQuoteMatcher(StrMatcher.quoteMatcher());

            binding.put("args", tokenizer.getTokenArray());
            binding.put("tout", System.out);

            result = compiled.eval(binding);
         }

         if (result instanceof String)
            resultBuilder.append(result);
         else if (result instanceof ScriptResult && null != ((ScriptResult) result).getResult())
            resultBuilder.append(((ScriptResult) result).getResult());
      } catch (Exception e) {
         logger.info("Could not execute package script.", e);
         throw new IllegalStateException(e);
      }
   }

   @Override
   public List<File> listPackages() {
      return Arrays.asList(getPackageDirectory()
            .listFiles((dir, name) -> name.toLowerCase().endsWith(".zip")
                  || name.toLowerCase().endsWith(".rsp") && validateZip(new File(dir, name), false)));
   }

   @Override
   public boolean validateZip(InputStream is, final boolean requireAutorun) {
      final List<ZipEntry> entries = new ArrayList<ZipEntry>();
      try {
         zipUtilsService.extractZip(is, new ZipExtractionConfig() {

            @Override
            public boolean isAllowedFile(ZipEntry entry) {
               return entry.getName().equals("package.xml");
            }

            @Override
            public void processContent(ZipEntry entry, byte[] content) {
               Parameters params = new Parameters();
               XMLBuilderParameters xmlParams = params.xml().setEncoding("UTF-8");
               BasicConfigurationBuilder<XMLConfiguration> builder = new BasicConfigurationBuilder<XMLConfiguration>(
                     XMLConfiguration.class).configure(xmlParams);
               try {
                  XMLConfiguration config = builder.getConfiguration();
                  FileHandler handler = new FileHandler(config);
                  handler.load(new ByteArrayInputStream(content));

                  if (config.getRootElementName().equals("packagedscript")) {
                     boolean autorun = "true".equals(config.getString("[@autorun]"));

                     if (autorun || !requireAutorun) {
                        entries.add(entry);
                     }
                  }
               } catch (ConfigurationException e) {
                  e.printStackTrace();
               }
            }
         });
      } catch (FileNotFoundException e) {
      } catch (IOException e) {
      }
      return entries.size() > 0;
   }

   @Override
   public boolean validateZip(File f, final boolean requireAutorun) {
      try {
         return validateZip(new FileInputStream(f), requireAutorun);
      } catch (FileNotFoundException e) {
      }
      return false;
   }
}
