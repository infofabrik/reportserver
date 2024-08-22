package net.datenwerke.rs.fileserver.service.fileserver.hookers;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;

import java.util.AbstractMap.SimpleEntry;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.stream.Collectors;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.apache.commons.lang3.tuple.ImmutablePair;

import com.google.inject.Provider;

import net.datenwerke.rs.fileserver.service.fileserver.entities.FileServerFile;
import net.datenwerke.rs.fileserver.service.fileserver.hooks.FileServerEntryProviderHook;
import net.datenwerke.rs.fileserver.service.fileserver.locale.FileserverMessages;

public class UsageStatisticsDetailedFilesProviderHooker implements FileServerEntryProviderHook {

   private final Provider<EntityManager> entityManagerProvider;
   
   @Inject
   public UsageStatisticsDetailedFilesProviderHooker(
         Provider<EntityManager> entityManagerProvider
         ) {
      this.entityManagerProvider = entityManagerProvider;
   }
   
   @Override
   public Map<ImmutablePair<String, String>, Object> provideEntry() {
      List<FileServerFile> allFiles = entityManagerProvider.get()
            .createQuery("FROM " + FileServerFile.class.getSimpleName()).getResultList();
      Map<String, List<FileServerFile>> groupedFiles = allFiles.stream()
            .collect(Collectors.groupingBy(file -> {
               if (null == file.getName())
                  return "(" + FileserverMessages.INSTANCE.emptyName() + ")";
               String filename = file.getName();
               if (!filename.contains("."))
                  return "(" + FileserverMessages.INSTANCE.noFileEnding() + ")";
               return filename.substring(filename.lastIndexOf("."));
            }, TreeMap::new, toList()));
      return groupedFiles
         .entrySet()
         .stream()
         .map(entry -> new SimpleEntry<>(ImmutablePair.of(entry.getKey(), entry.getKey()), entry.getValue().size()))
         .collect(toMap(Entry::getKey, Entry::getValue, (val1, val2) -> val2, LinkedHashMap::new));
   }

}
