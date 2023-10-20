package net.datenwerke.rs.terminal.client.terminal;

import net.datenwerke.gxtdto.client.dtomanager.Dto2PosoMapper;
import net.datenwerke.rs.terminal.client.terminal.dto.CommandResultDto;
import net.datenwerke.treedb.client.treedb.dto.AbstractNodeDto;

public interface TerminalUIService {

   public void initTerminal();

   void displayTerminalWindow();

   void displayTerminalMaximizedWindow();

   boolean isInitialized();

   public void processExternalResult(CommandResultDto result);

   public void displayResult(CommandResultDto result);

   void displayTerminalWindow(AbstractNodeDto node, Dto2PosoMapper dto2PosoMapper);
   
}
