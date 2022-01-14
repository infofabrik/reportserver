package net.datenwerke.security.server;

import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import com.google.inject.name.Named;
import com.google.inject.persist.Transactional;

import net.datenwerke.gxtdto.client.dtomanager.Dto;
import net.datenwerke.gxtdto.client.dtomanager.Dto2PosoMapper;
import net.datenwerke.gxtdto.client.dtomanager.DtoView;
import net.datenwerke.gxtdto.client.servercommunication.exceptions.ExpectedException;
import net.datenwerke.gxtdto.client.servercommunication.exceptions.NeedForcefulDeleteClientException;
import net.datenwerke.gxtdto.client.servercommunication.exceptions.ServerCallFailedException;
import net.datenwerke.gxtdto.client.servercommunication.exceptions.ViolatedSecurityExceptionDto;
import net.datenwerke.gxtdto.server.dtomanager.DtoService;
import net.datenwerke.rs.utils.entitycloner.EntityClonerService;
import net.datenwerke.rs.utils.exception.exceptions.NeedForcefulDeleteException;
import net.datenwerke.security.service.security.SecurityService;
import net.datenwerke.security.service.security.SecurityServiceSecuree;
import net.datenwerke.security.service.security.SecurityTarget;
import net.datenwerke.security.service.security.annotation.ArgumentVerification;
import net.datenwerke.security.service.security.annotation.ReturnObjectValidation;
import net.datenwerke.security.service.security.annotation.ReturnObjectValidation.Mode;
import net.datenwerke.security.service.security.annotation.RightsVerification;
import net.datenwerke.security.service.security.annotation.SecurityChecked;
import net.datenwerke.security.service.security.rights.Read;
import net.datenwerke.security.service.security.rights.Write;
import net.datenwerke.security.service.treedb.actions.InsertAction;
import net.datenwerke.security.service.treedb.actions.MoveNodeActionForNode;
import net.datenwerke.security.service.treedb.actions.MoveNodeActionForReference;
import net.datenwerke.security.service.treedb.actions.RemoveNodeAction;
import net.datenwerke.treedb.client.treedb.dto.AbstractNodeDto;
import net.datenwerke.treedb.client.treedb.dto.EntireTreeDTO;
import net.datenwerke.treedb.client.treedb.rpc.RPCTreeLoader;
import net.datenwerke.treedb.client.treedb.rpc.RPCTreeManager;
import net.datenwerke.treedb.service.treedb.AbstractNode;
import net.datenwerke.treedb.service.treedb.TreeDBManager;

/**
 * 
 *
 * @param <A>
 */
@SuppressWarnings("unchecked")
public abstract class TreeDBManagerTreeHandler<A extends AbstractNode<A>> extends SecuredRemoteServiceServlet
      implements RPCTreeManager, RPCTreeLoader {

   /**
    * 
    */
   private static final long serialVersionUID = 4244137109847242791L;

   final protected TreeDBManager<A> treeDBManager;
   final protected DtoService dtoService;
   final protected SecurityService securityService;
   final protected EntityClonerService entityClonerService;

   public TreeDBManagerTreeHandler(TreeDBManager<A> treeDBManager, DtoService dtoGenerator,
         SecurityService securityService, EntityClonerService entityClonerService) {

      /* store objects */
      this.dtoService = dtoGenerator;
      this.treeDBManager = treeDBManager;
      this.securityService = securityService;
      this.entityClonerService = entityClonerService;
   }

   @SecurityChecked(argumentVerification = {
         @ArgumentVerification(name = "node", isDto = true, verify = @RightsVerification(rights = Read.class)) }, returnObjectInstanceOnFailure = true,

         returnObjectValidation = @ReturnObjectValidation(isDto = true, mode = Mode.FILTER, verify = @RightsVerification(rights = Read.class)))
   @Transactional(rollbackOn = { Exception.class })
   public final List<AbstractNodeDto> getChildren(@Named("node") AbstractNodeDto node, Dto state,
         final Collection<Dto2PosoMapper> wlFilters, final Collection<Dto2PosoMapper> blFilters)
         throws ServerCallFailedException {
      return doGetChildren(node, state, wlFilters, blFilters);
   }

   protected List<AbstractNodeDto> doGetChildren(final AbstractNodeDto node, Dto state,
         final Collection<Dto2PosoMapper> wlFilters, final Collection<Dto2PosoMapper> blFilters) {
      final boolean filter = isFilter(wlFilters, blFilters);

      final Set<Class<?>> wlFilterList = convertFilters(filter, wlFilters);
      final Set<Class<?>> blFilterList = convertFilters(filter, blFilters);

      return ((Collection<A>) treeDBManager.getNodeById(node.getId()).getChildrenSorted()).stream()
            .filter(child -> securityService.checkRights((SecurityTarget) child, SecurityServiceSecuree.class,
                  Read.class))
            .filter(child -> passesFilters(child, filter, wlFilterList, blFilterList))
            .map(child -> (AbstractNodeDto) dtoService.createListDto(child)).collect(toList());
   }

   @SecurityChecked(returnObjectValidation = @ReturnObjectValidation(isDto = true, mode = Mode.FILTER, verify = @RightsVerification(rights = Read.class)))
   @Transactional(rollbackOn = { Exception.class })
   public List<AbstractNodeDto> getRoot(Dto state) throws ServerCallFailedException {
      return getRoots().stream().map(root -> (AbstractNodeDto) dtoService.createListDto(root)).collect(toList());
   }

   protected Collection<A> getRoots() {
      return treeDBManager.getVirtualRoots();
   }

   @SecurityChecked(argumentVerification = {
         @ArgumentVerification(name = "node", isDto = true, verify = @RightsVerification(rights = Read.class)) })
   @Override
   @Transactional(rollbackOn = { Exception.class })
   public AbstractNodeDto loadFullViewNode(@Named("node") AbstractNodeDto node, Dto state)
         throws ServerCallFailedException {
      A realNode = (A) treeDBManager.getNodeById(node.getId());
      return (AbstractNodeDto) dtoService.createDtoFullAccess(realNode);
   }

   @Override
   @Transactional(rollbackOn = { Exception.class })
   public AbstractNodeDto loadNodeById(Long id, Dto state) throws ServerCallFailedException {
      A realNode = (A) treeDBManager.getNodeById(id);

      securityService.assertRights((SecurityTarget) realNode, Read.class);

      return (AbstractNodeDto) dtoService.createDto(realNode);
   }

   @Override
   @Transactional(rollbackOn = { Exception.class })
   public EntireTreeDTO loadAll(Dto state) throws ServerCallFailedException {
      return loadAll(state, null, null);
   }

   @Override
   public String[][] loadAllAsFto(final Dto state) throws ServerCallFailedException {
      final EntireTreeDTO d = loadAll(state, null, null);

      return Stream
            .concat(d.getChildrenMap().values().stream().flatMap(nodes -> nodes.stream().map(dtoService::dto2Fto)),
                  d.getRoots().stream().map(dtoService::dto2Fto))
            .toArray(String[][]::new);
   }

   @SecurityChecked(argumentVerification = {
         @ArgumentVerification(name = "node", isDto = true, verify = @RightsVerification(rights = Read.class)) })
   @Override
   public final String[][] getChildrenAsFto(@Named("node") AbstractNodeDto node, Dto state,
         final Collection<Dto2PosoMapper> wlFilters, final Collection<Dto2PosoMapper> blFilters)
         throws ServerCallFailedException {
      return doGetChildrenAsFto(node, state, wlFilters, blFilters);
   }

   protected String[][] doGetChildrenAsFto(final AbstractNodeDto node, final Dto state,
         final Collection<Dto2PosoMapper> wlFilters, final Collection<Dto2PosoMapper> blFilters) {
      final boolean filter = isFilter(wlFilters, blFilters);

      final Set<Class<?>> wlFilterList = convertFilters(filter, wlFilters);
      final Set<Class<?>> blFilterList = convertFilters(filter, blFilters);

      return treeDBManager.getNodeById(node.getId()).getChildrenSorted().stream()
            .filter(child -> securityService.checkRights((SecurityTarget) child, SecurityServiceSecuree.class,
                  Read.class))
            .filter(child -> passesFilters(child, filter, wlFilterList, blFilterList))
            .map(child -> (AbstractNodeDto) dtoService.createDto(child, DtoView.LIST_FTO, DtoView.MINIMAL))
            .map(dto -> dtoService.dto2Fto(dto)).toArray(String[][]::new);
   }

   private Set<Class<?>> convertFilters(boolean filter, Collection<Dto2PosoMapper> filters) {
      Collection<Dto2PosoMapper> cFilters = null;
      if (filter && null == filters)
         cFilters = new HashSet<Dto2PosoMapper>();
      else
         cFilters = filters;

      Set<Class<?>> filterList = new HashSet<Class<?>>();
      if (filter) {
         for (Dto2PosoMapper filterDtoMapper : cFilters)
            filterList.add(dtoService.getPosoFromDtoMapper(filterDtoMapper));
      }

      return filterList;
   }

   private boolean isFilter(Collection<Dto2PosoMapper> wlFilters, Collection<Dto2PosoMapper> blFilters) {
      return (null != wlFilters && !wlFilters.isEmpty()) || (null != blFilters && !blFilters.isEmpty());
   }

   @Override
   @Transactional(rollbackOn = { Exception.class })
   public EntireTreeDTO loadAll(Dto state, Collection<Dto2PosoMapper> wlFilters, Collection<Dto2PosoMapper> blFilters)
         throws ServerCallFailedException {
      boolean filter = isFilter(wlFilters, blFilters);

      Set<Class<?>> wlFilterList = convertFilters(filter, wlFilters);
      Set<Class<?>> blFilterList = convertFilters(filter, blFilters);

      EntireTreeDTO treeDto = new EntireTreeDTO();

      Collection<A> roots = getRoots();

      for (A root : roots) {
         if (!securityService.checkRights((SecurityTarget) root, SecurityServiceSecuree.class, Read.class))
            continue;
         AbstractNodeDto rootDto = (AbstractNodeDto) dtoService.createListDto(root);
         treeDto.addRoot(rootDto);
         addChildren(treeDto, rootDto, root, filter, filter ? wlFilterList : null, filter ? blFilterList : null);
      }

      return treeDto;
   }

   private boolean passesFilters(A node, boolean filter, Set<Class<?>> wlFilterList, Set<Class<?>> blFilterList) {
      if (!filter)
         return true;

      boolean passes = true;
      if (null != wlFilterList) {
         passes = false;
         for (Class<?> wlFilter : wlFilterList) {
            if (wlFilter.isAssignableFrom(node.getClass())) {
               passes = true;
               break;
            }
         }
      }
      if (!passes)
         return false;
      if (null != blFilterList) {
         for (Class<?> blFilter : blFilterList) {
            if (blFilter.isAssignableFrom(node.getClass())) {
               passes = false;
               break;
            }
         }
         if (!passes)
            return false;
      }

      return true;
   }

   private void addChildren(final EntireTreeDTO treeDto, final AbstractNodeDto parentDto, final A parent,
         final boolean filter, final Set<Class<?>> wlFilterList, final Set<Class<?>> blFilterList) {
      parent.getChildrenSorted().stream().filter(
            child -> securityService.checkRights((SecurityTarget) child, SecurityServiceSecuree.class, Read.class))
            .filter(child -> passesFilters(child, filter, wlFilterList, blFilterList)).forEach(child -> {
               AbstractNodeDto childDto = (AbstractNodeDto) dtoService.createListDto(child);
               treeDto.addChild(parentDto, childDto);
               addChildren(treeDto, childDto, child, filter, wlFilterList, blFilterList);
            });
   }

   /**
    * Deletes the specified node
    */
   @SecurityChecked(argumentVerification = {
         @ArgumentVerification(name = "node", isDto = true, verify = @RightsVerification(actions = RemoveNodeAction.class)) })
   @Override
   @Transactional(rollbackOn = { Exception.class })
   public void deleteNode(@Named("node") AbstractNodeDto node, Dto state)
         throws ServerCallFailedException, NeedForcefulDeleteClientException {
      deleteNode(node, state, false);
   }

   /**
    * Deletes the specified node
    */
   @SecurityChecked(argumentVerification = {
         @ArgumentVerification(name = "node", isDto = true, verify = @RightsVerification(actions = RemoveNodeAction.class)) })
   @Override
   @Transactional(rollbackOn = { Exception.class })
   public void deleteNodeWithForce(@Named("node") AbstractNodeDto node, Dto state)
         throws ServerCallFailedException, NeedForcefulDeleteClientException {
      deleteNode(node, state, true);
   }

   protected void deleteNode(@Named("node") AbstractNodeDto node, Dto state, boolean force)
         throws ServerCallFailedException, NeedForcefulDeleteClientException {
      A uNode = treeDBManager.getNodeById(node.getId());

      /* do not allow the deletion of root nodes */
      if (uNode.isRoot())
         throw new ExpectedException("Root cannot be deleted.");

      try {
         if (force)
            treeDBManager.forceRemove(uNode);
         else
            treeDBManager.remove(uNode);
      } catch (NeedForcefulDeleteException e) {
         throw new NeedForcefulDeleteClientException(e);
      }
   }

   @SecurityChecked(argumentVerification = {
         @ArgumentVerification(name = "node", isDto = true, verify = @RightsVerification(rights = Read.class)) })
   @Override
   @Transactional(rollbackOn = { Exception.class })
   public AbstractNodeDto refreshNode(@Named("node") AbstractNodeDto node, Dto state) throws ServerCallFailedException {
      /* get real report */
      A realNode = treeDBManager.getNodeById(node.getId());

      return (AbstractNodeDto) dtoService.createDtoFullAccess(realNode);
   }

   @SecurityChecked(argumentVerification = {
         @ArgumentVerification(name = "node", isDto = true, verify = @RightsVerification(rights = Write.class)) })
   @Override
   @Transactional(rollbackOn = { Exception.class })
   public AbstractNodeDto setFlag(@Named("node") AbstractNodeDto node, long flagToSet, long flagToUnset,
         boolean updateNode, Dto state) throws ServerCallFailedException {
      /* get real report */
      A realNode = treeDBManager.getNodeById(node.getId());

      realNode.addFlag(flagToSet);

      realNode.removeFlag(flagToUnset);

      if (realNode.isConfigurationProtected() && !realNode.isWriteProtected()) {
         throw new IllegalArgumentException("Report is config protected, but not write protected");
      }

      treeDBManager.updateFlags(realNode, realNode.getFlags());

      if (updateNode) {
         return updateNode(node, state);
      }

      return (AbstractNodeDto) dtoService.createDtoFullAccess(realNode);
   }

   /**
    * inserts an object of the specified type
    * 
    * @throws ServerCallFailedException
    */
   @SecurityChecked(argumentVerification = {
         @ArgumentVerification(name = "parentNode", isDto = true, verify = @RightsVerification(actions = InsertAction.class)) })
   @Override
   @Transactional(rollbackOn = { Exception.class })
   public AbstractNodeDto insertNode(AbstractNodeDto objectTypeToInsert, @Named("parentNode") AbstractNodeDto node,
         Dto state) throws ServerCallFailedException {
      /* create new object */
      A inserted = (A) dtoService.instantiatePoso(objectTypeToInsert.getClass());

      /* load parent object */
      A parent = treeDBManager.getNodeById(node.getId());

      /* set properties */
      parent.addChild(inserted);
      doSetInitialProperties(inserted);

      /* persist child */
      treeDBManager.persist(inserted);

      return (AbstractNodeDto) dtoService.createDtoFullAccess(inserted);
   }

   @SecurityChecked(argumentVerification = {
         @ArgumentVerification(name = "toDuplicate", isDto = true, verify = @RightsVerification(rights = Read.class), parentChecks = @RightsVerification(actions = InsertAction.class)) })
   @Override
   @Transactional(rollbackOn = { Exception.class })
   public AbstractNodeDto duplicateNode(@Named("toDuplicate") AbstractNodeDto toDuplicate, Dto state)
         throws ServerCallFailedException {
      A realNode = treeDBManager.getNodeById(toDuplicate.getId());
      if (!allowDuplicateNode(realNode))
         throw new ServerCallFailedException("node is not to be duplicated");
      A parent = (A) realNode.getParent();

      A clonedNode = entityClonerService.cloneEntity(realNode);
      nodeCloned(clonedNode);
      parent.addChild(clonedNode);
      treeDBManager.persist(clonedNode);

      return (AbstractNodeDto) dtoService.createDtoFullAccess(clonedNode);
   }

   protected void nodeCloned(A clonedNode) {

   }

   protected boolean allowDuplicateNode(A realNode) {
      return false;
   }

   /**
    * Allows a subclass to set a nodes initial properties after insertion.
    * 
    * @param inserted
    */
   protected void doSetInitialProperties(A inserted) {

   }

   @SecurityChecked(argumentVerification = {
         @ArgumentVerification(name = "node", isDto = true, verify = @RightsVerification(actions = MoveNodeActionForNode.class)),
         @ArgumentVerification(name = "reference", isDto = true, verify = @RightsVerification(actions = MoveNodeActionForReference.class)) })
   @Override
   @Transactional(rollbackOn = { Exception.class })
   public AbstractNodeDto moveNodeAppend(@Named("node") AbstractNodeDto node,
         @Named("reference") AbstractNodeDto reference, Dto state) throws ServerCallFailedException {
      /* get objects */
      A realNode = treeDBManager.getNodeById(node.getId());
      A parent = treeDBManager.getNodeById(reference.getId());

      /* move node */
      A oldParent = (A) realNode.getParent();
      treeDBManager.move(realNode, parent);

      /* notify subclasses */
      nodeMovedToParent(realNode, parent, oldParent);

      /* merge parent */
      treeDBManager.merge(parent);

      return (AbstractNodeDto) dtoService.createDto(realNode);
   }

   @SecurityChecked(argumentVerification = {
         @ArgumentVerification(name = "reference", isDto = true, verify = @RightsVerification(actions = MoveNodeActionForReference.class)) })
   @Override
   @Transactional(rollbackOn = { Exception.class })
   public List<AbstractNodeDto> moveNodesAppend(List<AbstractNodeDto> nodes,
         @Named("reference") AbstractNodeDto reference, Dto state) throws ServerCallFailedException {
      /* get real nodes */
      List<A> realNodes = new ArrayList();
      for (AbstractNodeDto nodeDto : nodes) {
         A node = treeDBManager.getNodeById(nodeDto.getId());

         securityService.assertActions(node, MoveNodeActionForNode.class);

         realNodes.add(node);
      }

      /* move */
      A parent = treeDBManager.getNodeById(reference.getId());
      List<AbstractNodeDto> resultList = new ArrayList<AbstractNodeDto>();

      for (A node : realNodes) {
         /* move node */
         A oldParent = (A) node.getParent();
         treeDBManager.move(node, parent);

         /* notify subclasses */
         nodeMovedToParent(node, parent, oldParent);

         resultList.add((AbstractNodeDto) dtoService.createDto(node));
      }

      /* merge parent */
      treeDBManager.merge(parent);

      return resultList;
   }

   protected void nodeMovedToParent(A realNode, A parent, A oldParent) {
   }

   @SecurityChecked(argumentVerification = {
         @ArgumentVerification(name = "node", isDto = true, verify = @RightsVerification(actions = MoveNodeActionForNode.class)) })
   @Override
   @Transactional(rollbackOn = { Exception.class })
   public AbstractNodeDto moveNodeInsert(@Named("node") AbstractNodeDto node, AbstractNodeDto reference, int index,
         Dto state) throws ServerCallFailedException {
      /* get objects */
      A realNode = treeDBManager.getNodeById(node.getId());
      A realReference = treeDBManager.getNodeById(reference.getId());
      A parent = (A) realReference.getParent();

      /* check rights */
      if (!securityService.checkRights((SecurityTarget) parent, SecurityServiceSecuree.class, Read.class)) {
         throw new ViolatedSecurityExceptionDto("Could not insert node."); //$NON-NLS-1$
      }

      /* move node */
      A oldParent = (A) realNode.getParent();
      treeDBManager.move(realNode, parent, index);

      /* notify subclasses */
      nodeMovedToParent(realNode, parent, oldParent);

      /* merge parent */
      treeDBManager.merge(parent);

      return (AbstractNodeDto) dtoService.createDto(realNode);
   }

   @SecurityChecked(argumentVerification = {
         @ArgumentVerification(name = "node", isDto = true, verify = @RightsVerification(rights = Write.class)) })
   @Override
   @Transactional(rollbackOn = { Exception.class })
   public AbstractNodeDto updateNode(@Named("node") AbstractNodeDto nodeDto, Dto state)
         throws ServerCallFailedException {
      /* get object */
      A realNode = treeDBManager.getNodeById(nodeDto.getId());

      if (!realNode.isWriteProtected() && !realNode.isConfigurationProtected()) {
         /* copy values */
         dtoService.mergePoso(nodeDto, realNode);

         /* do something */
         doUpdateNode(nodeDto, realNode);

         /* merge node */
         treeDBManager.merge(realNode);
      }

      return (AbstractNodeDto) dtoService.createDtoFullAccess(realNode);
   }

   /**
    * Allows a subclass to handle specific update tasks.
    * 
    * @param node
    * @param realNode
    */
   protected void doUpdateNode(AbstractNodeDto node, A realNode) throws ServerCallFailedException {

   }

}
