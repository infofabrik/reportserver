package net.datenwerke.rs.terminal.service.terminal.obj;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import net.datenwerke.dtoservices.dtogenerator.annotations.ExposeToClient;
import net.datenwerke.dtoservices.dtogenerator.annotations.GenerateDto;
import net.datenwerke.gf.base.client.dtogenerator.RsDto;
import net.datenwerke.rs.base.service.reportengines.table.output.object.RSTableModel;
import net.datenwerke.rs.utils.entitycloner.annotation.EnclosedEntity;

@GenerateDto(dtoPackage = "net.datenwerke.rs.terminal.client.terminal.dto", createDecorator = true, whitelist = RsDto.class)
public class CommandResult {

   @ExposeToClient
   @EnclosedEntity
   private List<CommandResultEntry> entryList = new ArrayList<CommandResultEntry>();

   @ExposeToClient
   @EnclosedEntity
   private Set<CommandResultModifier> modifiers = new HashSet<CommandResultModifier>();

   @ExposeToClient
   private DisplayMode displayMode = DisplayMode.NORMAL;

   @ExposeToClient
   @EnclosedEntity
   private List<CommandResultExtension> extensions = new ArrayList<CommandResultExtension>();

   private boolean commitTransaction = true;

   private Object resultObject;

   public CommandResult() {
      // dummy
   }

   public CommandResult(CommandResultEntry entry) {
      addEntry(entry);
   }

   public CommandResult(String msg) {
      addResultLine(msg);
   }

   public CommandResult(List<String> list) {
      entryList.add(new CommandResultList(list));
   }

   public CommandResult(RSTableModel table) {
      entryList.add(new CommandResultTable(table));
   }

   public CommandResult(Object object) {
      setResultObject(object);
   }

   public CommandResult addResultLine(String line) {
      entryList.add(new CommandResultLine(line));
      return this;
   }

   public CommandResult addResultLine() {
      entryList.add(new CommandResultLine());
      return this;
   }

   public CommandResult addResultTable(RSTableModel table) {
      entryList.add(new CommandResultTable(table));
      return this;
   }

   public CommandResult addResultList(List<String> list) {
      entryList.add(new CommandResultList(list));
      return this;
   }

   public CommandResult addResultHyperLink(String caption, String historyToken) {
      entryList.add(new CommandResultHyperlink(caption, historyToken));
      return this;
   }

   public CommandResult addResultAnchor(String text, String url, String target) {
      entryList.add(new CommandResultAnchor(text, url, target));
      return this;
   }

   public CommandResult addResultHtml(String html) {
      entryList.add(new CommandResultHtml(html));
      return this;
   }

   public List<CommandResultEntry> getEntryList() {
      return entryList;
   }

   public void setEntryList(List<CommandResultEntry> entryList) {
      this.entryList = entryList;
   }

   public void addEntry(CommandResultEntry entry) {
      entryList.add(entry);
   }

   public static CommandResult createEmptyInstance() {
      return new CommandResult();
   }

   public void setDisplayMode(DisplayMode displayMode) {
      this.displayMode = displayMode;
   }

   public DisplayMode getDisplayMode() {
      return displayMode;
   }

   public void setModifiers(Set<CommandResultModifier> modifiers) {
      this.modifiers = modifiers;
   }

   public Set<CommandResultModifier> getModifiers() {
      return modifiers;
   }

   public void addModifier(Class<? extends CommandResultModifier> modifierType) {
      try {
         addModifier(modifierType.newInstance());
      } catch (Exception e) {
         throw new RuntimeException(e);
      }
   }

   public void addModifiers(Set<CommandResultModifier> resultModifier) {
      if (null == resultModifier)
         return;
      this.modifiers.addAll(resultModifier);
   }

   public void addExtensions(List<CommandResultExtension> resultExtension) {
      if (null == resultExtension)
         return;
      this.extensions.addAll(resultExtension);
   }

   public void addModifier(CommandResultModifier modifier) {
      modifiers.add(modifier);
   }

   public boolean hasModifier(Class<CommandResultModifier> modifierType) {
      for (CommandResultModifier modifier : modifiers)
         if (modifierType.isAssignableFrom(modifier.getClass()))
            return true;
      return false;
   }

   public void removeModifier(Class<? extends CommandResultModifier> modifierType) {
      Iterator<CommandResultModifier> it = modifiers.iterator();
      while (it.hasNext()) {
         if (modifierType.isAssignableFrom(it.next().getClass()))
            it.remove();
      }

   }

   public void setExtensions(List<CommandResultExtension> extensions) {
      this.extensions = extensions;
   }

   public List<CommandResultExtension> getExtensions() {
      return extensions;
   }

   public void addExtension(CommandResultExtension ext) {
      extensions.add(ext);
   }

   public void setCommitTransaction(boolean commitTransaction) {
      this.commitTransaction = commitTransaction;
   }

   public boolean isCommitTransaction() {
      return commitTransaction;
   }

   public void setResultObject(Object resultObject) {
      this.resultObject = resultObject;
   }

   public Object getResultObject() {
      return resultObject;
   }

   public boolean containsByteData() {
      return getResultObject() instanceof byte[];
   }

   public byte[] getByteData() {
      return (byte[]) getResultObject();
   }

   @Override
   public String toString() {
      if (getResultObject() instanceof String || getResultObject() instanceof StringBuffer
            || getResultObject() instanceof StringBuilder)
         return getResultObject().toString();
      if (getResultObject() instanceof byte[])
         return new String((byte[]) getResultObject());

      StringBuffer buf = new StringBuffer();

      for (CommandResultEntry entry : getEntryList())
         buf.append(entry.toString()).append("\n");

      return buf.toString();
   }

}
