package net.datenwerke.gxtdto.client.utils.sort;

import java.util.Comparator;

import com.sencha.gxt.data.shared.SortDir;
import com.sencha.gxt.data.shared.Store.StoreSortInfo;

import net.datenwerke.gxtdto.client.dtomanager.Dto;
import net.datenwerke.gxtdto.client.dtomanager.FolderDto;
import net.datenwerke.gxtdto.client.dtomanager.stores.TreeDto;

public class AlphabeticStoreSortInfo<X extends Dto> extends StoreSortInfo<X> {

   public AlphabeticStoreSortInfo() {
      this(SortDir.ASC);
   }

   public AlphabeticStoreSortInfo(SortDir dir) {
      super(new Comparator<X>() {
         @Override
         public int compare(X o1, X o2) {
            if (o1 instanceof TreeDto && o2 instanceof TreeDto) {
               if (o1 instanceof FolderDto && !(o2 instanceof FolderDto))
                  return -1;
               else if (o2 instanceof FolderDto && !(o1 instanceof FolderDto))
                  return 1;

               String o1Title = o1.toDisplayTitle();
               String o2Title = o2.toDisplayTitle();

               if (null != o1Title && null != o2Title)
                  return o1Title.compareToIgnoreCase(o2Title);
               if (null == o1Title && null == o2Title)
                  return 0;
               if (null == o1Title)
                  return 1;
               else
                  return -1;
            }
            return 0;
         }
      }, dir);
   }

}
