package net.datenwerke.gf.client.treedb.stores;

import java.util.List;

import com.sencha.gxt.data.shared.loader.DataProxy;
import com.sencha.gxt.data.shared.loader.TreeLoader;

import net.datenwerke.treedb.client.treedb.dto.AbstractNodeDto;
import net.datenwerke.treedb.client.treedb.dto.decorator.AbstractNodeDtoDec;

public class EnhancedTreeLoader extends TreeLoader<AbstractNodeDto> {

   public EnhancedTreeLoader(DataProxy<AbstractNodeDto, List<AbstractNodeDto>> proxy) {
      super(proxy);
   }

   @Override
   public boolean hasChildren(AbstractNodeDto parent) {
      return ((AbstractNodeDtoDec) parent).hasChildren();
   }

}
