package net.datenwerke.rs.adminutils.client.systemconsole.memory.dto;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;

public interface MemoryInfoDtoPA extends PropertyAccess<MemoryInfoDto> {

   public static final MemoryInfoDtoPA INSTANCE = GWT.create(MemoryInfoDtoPA.class);

   /* Properties */
   ValueProvider<MemoryInfoDto, Long> free();

   ValueProvider<MemoryInfoDto, Long> freeMb();

   ValueProvider<MemoryInfoDto, Long> total();

   ValueProvider<MemoryInfoDto, Long> totalMb();

   ValueProvider<MemoryInfoDto, Long> used();

   ValueProvider<MemoryInfoDto, Long> usedMb();

   ValueProvider<MemoryInfoDto, Long> max();

   ValueProvider<MemoryInfoDto, Long> maxMb();

   ValueProvider<MemoryInfoDto, Long> timestamp();

   @Path("timestamp")
   ModelKeyProvider<MemoryInfoDto> key();
}
