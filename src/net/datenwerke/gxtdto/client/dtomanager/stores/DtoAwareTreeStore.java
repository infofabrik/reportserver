package net.datenwerke.gxtdto.client.dtomanager.stores;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.google.inject.Inject;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.SortDir;
import com.sencha.gxt.data.shared.TreeStore;
import com.sencha.gxt.data.shared.event.StoreDataChangeEvent;
import com.sencha.gxt.data.shared.event.StoreDataChangeEvent.StoreDataChangeHandler;
import com.sencha.gxt.data.shared.loader.ChildTreeStoreBinding;
import com.sencha.gxt.data.shared.loader.Loader;
import com.sencha.gxt.data.shared.loader.TreeLoader;
import com.sencha.gxt.widget.core.client.tree.Tree;

import net.datenwerke.gxtdto.client.awareness.TreePanelAware;
import net.datenwerke.gxtdto.client.dtoinfo.DtoInformationService;
import net.datenwerke.gxtdto.client.dtomanager.ClientDtoManagerService;
import net.datenwerke.gxtdto.client.dtomanager.Dto;
import net.datenwerke.gxtdto.client.dtomanager.events.BeforeDtoDetachedEvent;
import net.datenwerke.gxtdto.client.dtomanager.events.DtoAddedEvent;
import net.datenwerke.gxtdto.client.dtomanager.events.DtoChangedEvent;
import net.datenwerke.gxtdto.client.dtomanager.events.DtoListener;
import net.datenwerke.gxtdto.client.dtomanager.stores.marker.NoAutomaticStoreAddableDto;
import net.datenwerke.gxtdto.client.stores.HasLoader;
import net.datenwerke.gxtdto.client.utils.sort.AlphabeticStoreSortInfo;

/**
 * Handles change events if underlying dto is already known
 *
 */
public class DtoAwareTreeStore<X extends Dto> extends TreeStore<X>
      implements TreePanelAware<X>, PositionSortableStore, HasLoader<X, List<X>> {

   protected Tree<X, String> treePanel;

   protected Set<Class<?>> typeFilter;

   protected Dto imaginaryRoot;
   protected Object imaginaryRootId;

   private boolean positionSortable = false;

   @Inject
   protected static ClientDtoManagerService dtoManager;

   @Inject
   protected static DtoInformationService dtoInfoService;

   private TreeLoader<X> treeLoader;

   private StoreSortInfo<X> positionSorter = new StoreSortInfo<X>(new Comparator<X>() {
      @Override
      public int compare(X o1, X o2) {
         if (o1 instanceof TreeDto && o2 instanceof TreeDto)
            return ((Integer) ((TreeDto) o1).getPosition()).compareTo(((TreeDto) o2).getPosition());
         return 0;
      }
   }, SortDir.DESC);

   private StoreSortInfo<X> alphabeticSorter = new AlphabeticStoreSortInfo<X>();

   private DtoListener dtoChangeListener = new DtoListener() {

      protected X getAndCheckDto(Dto dto) {
         /* check type */
         if (!isOfAllowedType(dto))
            return null;

         try {
            return (X) dto;
         } catch (ClassCastException ex) {
         }

         return null;
      }

      protected List<X> getAndCheckDto(List<Dto> dtos) {
         List<X> list = new ArrayList<X>();

         for (Dto dto : dtos) {
            X x = getAndCheckDto(dto);
            if (null != x)
               list.add(x);
         }

         return list;
      }

      @Override
      public void dtoChanged(DtoChangedEvent e) {
         X dto = getAndCheckDto(e.getDto());
         if (null != dto && null != findModel(dto))
            updateDto(dto);
         else if (null != dto && null == findModel(dto) && dto instanceof TreeDto)
            checkPotentiallyNewDto((TreeDto) dto);
      }

      @Override
      public void dtoAdded(DtoAddedEvent e) {
         List<X> dtos = getAndCheckDto(e.getDtos());
         if (null != dtos && !dtos.isEmpty())
            checkPotentiallyNewDtos(dtos);
      };

      public void beforeDtoDetached(BeforeDtoDetachedEvent e) {
         X dto = getAndCheckDto(e.getDto());
         if (null != dto && null != findModel(dto))
            removeDto(dto);
      };
   };

   private boolean dtoAware;

   public DtoAwareTreeStore(ModelKeyProvider<? super X> mkp, TreeLoader<X> loader) {
      this(mkp, loader, true);
   }

   public DtoAwareTreeStore(ModelKeyProvider<? super X> mkp, TreeLoader<X> loader, boolean dtoAware) {
      super(mkp);
      loader.addLoadHandler(new ChildTreeStoreBinding<X>(this));

      this.treeLoader = loader;

      if (dtoAware)
         enableDtoAwareness(true);

      setAlphabeticSorter();
   }

   public void setImaginaryRoot(Dto root) {
      this.imaginaryRoot = root;
      this.imaginaryRootId = dtoInfoService.getDtoId(imaginaryRoot);
   }

   public void setPositionSorter() {
      clearSortInfo();
      addSortInfo(positionSorter);
      positionSortable = true;
   }

   public void setAlphabeticSorter() {
      clearSortInfo();
      addSortInfo(alphabeticSorter);
      positionSortable = false;
   }

   protected boolean isOfAllowedType(Dto dto) {
      if (dto instanceof NoAutomaticStoreAddableDto)
         return false;

      if (null == typeFilter)
         return true;

      Class<?> dtoClass = dto.getClass();
      while (null != dtoClass) {
         if (typeFilter.contains(dtoClass))
            return true;
         dtoClass = dtoClass.getSuperclass();
      }

      return false;
   }

   public void enableDtoAwareness(boolean enable) {
      if (dtoAware && enable)
         return;
      if (!enable) {
         dtoManager.removeDtoChangeListener(dtoChangeListener);
         return;
      }
      dtoAware = true;

      addStoreDataChangeHandler(new StoreDataChangeHandler<X>() {
         @Override
         public void onDataChange(StoreDataChangeEvent<X> event) {
            if (getRootCount() > 0 || null != imaginaryRoot)
               dtoManager.onDtoChange(dtoChangeListener);
            else
               dtoManager.removeDtoChangeListener(dtoChangeListener);
         }
      });

      /* make sure that we are listening even if we have no content */
      dtoManager.onDtoChange(dtoChangeListener);
   }

   public boolean isDtoAware() {
      return dtoAware;
   }

   protected void removeDto(X dto) {
      remove(dto);
   }

   protected void checkPotentiallyNewDtos(List<X> dtos) {
      Map<Dto, List<X>> addMap = new IdentityHashMap<Dto, List<X>>();
      for (X dto : dtos) {
         if (dto instanceof TreeDto) {
            TreeDto tDto = (TreeDto) dto;

            /* get parent */
            Dto parent = findParent(tDto);
            if (null == parent)
               parent = isImaginaryRootParent(tDto);

            if (null != parent) {
               if (!addMap.containsKey(parent))
                  addMap.put(parent, new ArrayList<X>());
               addMap.get(parent).add(dto);
            }
         }
      }

      for (Entry<Dto, List<X>> e : addMap.entrySet())
         addNewDtos(e.getValue(), e.getKey());
   }

   public void reCheckDto(X dto) {
      if (null != dto && null != findModel(dto))
         updateDto(dto);
      else if (null != dto && dto instanceof TreeDto && null == findModel(dto))
         checkPotentiallyNewDto((TreeDto) dto);
   }

   @SuppressWarnings("unchecked")
   protected void checkPotentiallyNewDto(TreeDto dto) {
      Dto parent = findParent(dto);

      /* check imaginary root */
      if (null == parent)
         parent = isImaginaryRootParent(dto);

      /* if we found a parent .. add child to store */
      if (null != parent)
         addNewDto((X) dto, parent);
   }

   private X findParent(TreeDto dto) {
      Object parentId = dto.getParentNodeId();
      String parentType = dto.getParentNodeType();

      /* only proceed if both properties are set */
      if (null == parentId || null == parentType)
         return null;

      for (X model : getAll()) {
         Object modelId = dtoInfoService.getDtoId(model);

         if (null != modelId && model.getClass().getName().equals(parentType) && modelId.equals(parentId)) {
            return model;
         }
      }

      return null;
   }

   private Dto isImaginaryRootParent(TreeDto dto) {
      Object parentId = dto.getParentNodeId();
      String parentType = dto.getParentNodeType();

      /* only proceed if both properties are set */
      if (null == parentId || null == parentType)
         return null;

      /* check imaginary root */
      if (null != imaginaryRoot) {
         if (imaginaryRoot.getClass().getName().equals(parentType) && parentId.equals(imaginaryRootId))
            return imaginaryRoot;
      }

      return null;
   }

   private void addNewDto(X dto, Dto parent) {
      /* tell parent that it now has children */
      ((TreeDto) parent).setHasChildren(true);

      /* tell tree */
      addLater(parent, dto);
   }

   private void addNewDtos(List<X> dtos, Dto parent) {
      /* tell parent that it now has children */
      boolean silence = parent.isSilenceEvents();
      parent.silenceEvents(true);
      ((TreeDto) parent).setHasChildren(true);
      parent.silenceEvents(silence);

      /* tell tree */
      addLater(parent, dtos);
   }

   @Override
   public void insert(int index, List<? extends X> rootNodes) {
      for (X child : rootNodes)
         if (!isOfAllowedType(child))
            throw new IllegalArgumentException(
                  "Tried to add a dto of wrong type (" + child.getClass() + ") to a type filtered store.");

      super.insert(index, rootNodes);
   }

   public void insert(X parent, int index, java.util.List<X> children) {
      for (X child : children)
         if (!isOfAllowedType(child))
            throw new IllegalArgumentException(
                  "Tried to add a dto of wrong type (" + child.getClass() + ") to a type filtered store.");

      super.insert(parent, index, children);
   };

   protected void updateDto(final X dto) {
      /* might have to move dto */
      X parentInStore = getParent(dto);

      if (null == parentInStore)
         update(dto);
      else {
         int newPos = ((TreeDto) dto).getPosition();
         Object parentNodeId = ((TreeDto) dto).getParentNodeId();
         if (null != parentNodeId && parentNodeId.equals(dtoInfoService.getDtoId(parentInStore)) && (!positionSortable
               || (getChildCount(parentInStore) > newPos && dto.equals(getChild(parentInStore, newPos))))) {
            update(dto); // dto at same position
         } else {
            final X newParent = findParent((TreeDto) dto);
            if (null == newParent)
               return; // new parent is not in tree

            if (null != dto && null != findModel(dto))
               remove(dto);

            List<X> listDto = new ArrayList<X>();
            listDto.add(dto);
            appendModel(newParent, listDto);
         }
      }
   }

   protected X getChild(X parentInStore, int newPos) {
      List<X> children = getChildren(parentInStore);
      if (!children.isEmpty() && children.size() > newPos)
         return children.get(newPos);
      return null;
   }

   protected void appendModel(X p, List<X> children) {
      if (null == children || children.size() == 0)
         return;

      if (p == null) {
         add(children);
      } else {
         if (null != treePanel) {
            if (treePanel.isLeaf(p))
               treePanel.setLeaf(p, false);
         }
         add(p, children);
      }
      for (X child : children) {
         List sub = (List) getChildren(child);
         appendModel(child, sub);
      }
   }

   private void addLater(final Dto newParent, final X dto) {
      if (null != treePanel) {
         if (!newParent.equals(imaginaryRoot))
            if (treePanel.isLeaf((X) newParent))
               treePanel.setLeaf((X) newParent, false);
      }
      if (null != findModel(dto))
         remove(dto);

      if (newParent.equals(imaginaryRoot))
         add(dto);
      else {
         add((X) newParent, dto);
//			treePanel.setExpanded((X)newParent, true);
      }
   }

   private void addLater(final Dto newParent, final List<X> dtos) {
      if (null != treePanel) {
         if (!newParent.equals(imaginaryRoot))
            if (treePanel.isLeaf((X) newParent))
               treePanel.setLeaf((X) newParent, false);
      }
      for (X dto : dtos)
         if (null != findModel(dto))
            remove(dto);

      if (isSorted())
         Collections.sort(dtos, buildFullComparator());

      if (newParent.equals(imaginaryRoot))
         add(dtos);
      else {
         add((X) newParent, dtos);
//			treePanel.setExpanded((X)newParent, true);
      }
   }

   @Override
   public void makeAwareOfTreePanel(Tree<X, String> panel) {
      this.treePanel = panel;
   }

   public void addTypeFilter(Class<?> type) {
      if (null == typeFilter)
         typeFilter = new HashSet<Class<?>>();
      typeFilter.add(type);
   }

   public void setTypeFilter(Set<Class<?>> filter) {
      typeFilter = filter;
   }

   @Override
   public boolean isPositionSortableEnabled() {
      return positionSortable;
   }

   @Override
   public Loader<X, List<X>> getLoader() {
      return treeLoader;
   }

}
