package net.datenwerke.security.ext.server;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.inject.name.Named;
import com.google.inject.persist.Transactional;

import net.datenwerke.gxtdto.client.servercommunication.exceptions.ExpectedException;
import net.datenwerke.gxtdto.client.servercommunication.exceptions.ServerCallFailedException;
import net.datenwerke.gxtdto.client.servercommunication.exceptions.ViolatedSecurityExceptionDto;
import net.datenwerke.gxtdto.server.dtomanager.DtoService;
import net.datenwerke.security.client.security.GenericTargetIdentifier;
import net.datenwerke.security.client.security.dto.AceDto;
import net.datenwerke.security.client.security.dto.GenericSecurityTargetContainer;
import net.datenwerke.security.client.security.dto.HierarchicalAceDto;
import net.datenwerke.security.client.security.dto.RightDto;
import net.datenwerke.security.client.security.dto.SecureeDto;
import net.datenwerke.security.client.security.dto.SecurityViewInformation;
import net.datenwerke.security.client.security.dto.decorator.AceAccessMapDtoDec;
import net.datenwerke.security.client.security.dto.decorator.AceDtoDec;
import net.datenwerke.security.client.treedb.dto.SecuredAbstractNodeDto;
import net.datenwerke.security.ext.client.security.rpc.SecurityRpcService;
import net.datenwerke.security.server.SecuredRemoteServiceServlet;
import net.datenwerke.security.service.genrights.security.GenericSecurityTargetAdminViewSecurityTarget;
import net.datenwerke.security.service.security.GenericSecurityTargetMarker;
import net.datenwerke.security.service.security.HierarchicalSecurityTarget;
import net.datenwerke.security.service.security.Securee;
import net.datenwerke.security.service.security.SecurityService;
import net.datenwerke.security.service.security.SecurityTarget;
import net.datenwerke.security.service.security.annotation.ArgumentVerification;
import net.datenwerke.security.service.security.annotation.GenericTargetIdentifierMapper;
import net.datenwerke.security.service.security.annotation.GenericTargetVerification;
import net.datenwerke.security.service.security.annotation.RightsVerification;
import net.datenwerke.security.service.security.annotation.SecurityChecked;
import net.datenwerke.security.service.security.entities.AccessType;
import net.datenwerke.security.service.security.entities.Ace;
import net.datenwerke.security.service.security.entities.AceAccessMap;
import net.datenwerke.security.service.security.entities.Acl;
import net.datenwerke.security.service.security.entities.GenericSecurityTargetEntity;
import net.datenwerke.security.service.security.entities.HierarchicalAce;
import net.datenwerke.security.service.security.entities.HierarchicalAcl;
import net.datenwerke.security.service.security.entities.InheritanceType;
import net.datenwerke.security.service.security.rights.GrantAccess;
import net.datenwerke.security.service.security.rights.Read;
import net.datenwerke.security.service.security.rights.Right;
import net.datenwerke.security.service.treedb.entities.SecuredAbstractNode;
import net.datenwerke.security.service.usermanager.entities.AbstractUserManagerNode;
import net.datenwerke.treedb.client.treedb.dto.AbstractNodeDto;
import net.datenwerke.treedb.service.treedb.AbstractNode;
import net.datenwerke.treedb.service.treedb.TreeDBService;

/**
 * 
 *
 */
@Singleton
public class SecurityRpcServiceImpl extends SecuredRemoteServiceServlet implements SecurityRpcService {

   /**
    * 
    */
   private static final long serialVersionUID = -7255134757363387391L;

   private final TreeDBService treeDBService;
   private final SecurityService securityService;
   private final DtoService dtoService;

   @Inject
   public SecurityRpcServiceImpl(TreeDBService treeDBService, SecurityService securityService, DtoService dtoService) {

      this.treeDBService = treeDBService;
      this.securityService = securityService;
      this.dtoService = dtoService;
   }

   @SecurityChecked(argumentVerification = {
         @ArgumentVerification(name = "node", isDto = true, verify = @RightsVerification(rights = Read.class)) })
   @Transactional(rollbackOn = { Exception.class })
   @Override
   public SecurityViewInformation loadSecurityViewInformation(@Named("node") AbstractNodeDto node)
         throws ServerCallFailedException {
      /* load real node */
      Object realNode = dtoService.loadPoso(node);

      /* validate obejct */
      if (null == realNode)
         throw new IllegalArgumentException("Could not find corresponding entity in database."); //$NON-NLS-1$
      if (!(realNode instanceof SecuredAbstractNode))
         throw new IllegalArgumentException("Node " + node.getClass().getName() + " is not a secured node"); //$NON-NLS-1$ //$NON-NLS-2$

      /* get acl */
      Acl acl = ((SecuredAbstractNode) realNode).getAcl();

      /* create list of aces */
      List<AceDto> aceDtos = new ArrayList<AceDto>();
      if (null != acl && null != acl.getAces())
         for (Ace ace : acl.getAces())
            aceDtos.add((AceDto) dtoService.createDto(ace));

      /* create list of inherited aces */
      List<AceDto> inheritedACEDtos = new ArrayList<AceDto>();
      Map<AceDto, SecuredAbstractNodeDto> referencedNodes = new HashMap<AceDto, SecuredAbstractNodeDto>();
      HierarchicalSecurityTarget parentTarget = ((SecuredAbstractNode) realNode).getParentTarget();
      while (null != parentTarget) {
         Acl parentACL = parentTarget.getAcl();
         SecuredAbstractNodeDto nodeDto = (SecuredAbstractNodeDto) dtoService.createDto(parentTarget);

         /* add aces */
         if (null != parentACL && null != parentACL.getAces()) {
            for (Ace ace : parentACL.getAces()) {
               if (ace instanceof HierarchicalAce && ((HierarchicalAce) ace).isInheritedACE()) {
                  AceDto aceDto = (AceDto) dtoService.createDto(ace);
                  inheritedACEDtos.add(aceDto);
                  referencedNodes.put(aceDto, nodeDto);
               }
            }
         }

         parentTarget = parentTarget.getParentTarget();
      }

      /* load securees */
      Collection<Securee> securees = securityService.getRegisteredSecureesForTarget(realNode.getClass());
      List<SecureeDto> secureeDtos = new ArrayList<SecureeDto>();
      for (Securee securee : securees)
         secureeDtos.add((SecureeDto) dtoService.createDto(securee));

      /* create information object and configure it */
      SecurityViewInformation information = new SecurityViewInformation();
      information.setAces(aceDtos);
      information.setReferencesToInheritedNodes(referencedNodes);
      information.setInheritedAces(inheritedACEDtos);
      information.setAvailableSecurees(secureeDtos);

      return information;
   }

   @SecurityChecked(genericTargetVerification = {
         @GenericTargetVerification(target = GenericSecurityTargetAdminViewSecurityTarget.class, verify = @RightsVerification(rights = Read.class)) })
   @Transactional(rollbackOn = { Exception.class })
   @Override
   public SecurityViewInformation loadGenericSecurityViewInformation(GenericTargetIdentifier targetIdentifier)
         throws ServerCallFailedException {
      /* get target */
      Class<?> genericTargetMarker = loadGenericSecurityMarker(targetIdentifier);
      GenericSecurityTargetEntity genericTarget = securityService.loadGenericTarget(genericTargetMarker);

      /* get acl */
      Acl acl = genericTarget.getAcl();

      /* create list of aces */
      List<AceDto> aceDtos = new ArrayList<AceDto>();
      if (null != acl && null != acl.getAces())
         for (Ace ace : acl.getAces())
            aceDtos.add((AceDto) dtoService.createDto(ace));

      /* load securees */
      Collection<Securee> securees = securityService.getRegisteredSecureesForTarget(genericTargetMarker);
      List<SecureeDto> secureeDtos = new ArrayList<SecureeDto>();
      for (Securee securee : securees)
         secureeDtos.add((SecureeDto) dtoService.createDto(securee));

      /* sort list */
      Collections.sort(secureeDtos, new Comparator<SecureeDto>() {
         @Override
         public int compare(SecureeDto o1, SecureeDto o2) {
            return o1.getName().compareTo(o2.getName());
         }
      });

      /* create information object and configure it */
      SecurityViewInformation information = new SecurityViewInformation();
      information.setAces(aceDtos);
      information.setAvailableSecurees(secureeDtos);

      return information;
   }

   @Transactional(rollbackOn = { Exception.class })
   @Override
   public GenericSecurityTargetContainer loadGenericRights(Collection<GenericTargetIdentifier> targetIdentifiers) {
      GenericSecurityTargetContainer securityContainer = new GenericSecurityTargetContainer();

      for (GenericTargetIdentifier targetIdentifier : targetIdentifiers) {
         /* load target entity and securees */
         Class<?> genericTargetMarker = loadGenericSecurityMarker(targetIdentifier);
         GenericSecurityTargetEntity genericTarget = securityService.loadGenericTarget(genericTargetMarker);

         Collection<Securee> securees = securityService.getRegisteredSecureesForTarget(genericTargetMarker);

         for (Securee securee : securees) {
            for (Right right : securee.getRights()) {
               if (securityService.checkRights(genericTarget, securee.getClass(), right.getClass())) {
                  securityContainer.addRight(targetIdentifier, (RightDto) dtoService.createDto(right));
               }
            }
         }
      }

      return securityContainer;
   }

   @SecurityChecked(argumentVerification = {
         @ArgumentVerification(name = "node", isDto = true, verify = @RightsVerification(rights = GrantAccess.class)) })
   @Override
   @Transactional(rollbackOn = { Exception.class })
   public AceDto aceMoved(@Named("node") AbstractNodeDto nodeDto, AceDto aceDto, int index)
         throws ServerCallFailedException {
      /* load real ace and check access rights */
      Ace realACE = (Ace) dtoService.loadPoso(aceDto);
      if (null == realACE)
         throw new IllegalArgumentException("Could not load corresponding ACE"); //$NON-NLS-1$

      /* validation */
      validateGrantAccessRightsBasic(nodeDto, realACE, aceDto);

      /* move ace */
      aceMoved(realACE, index);

      /* create dto */
      return (AceDto) dtoService.createDto(realACE);
   }

   @Override
   @Transactional(rollbackOn = { Exception.class })
   public AceDto aceMoved(@Named("target") GenericTargetIdentifier targetIdentifier, AceDto aceDto, int index)
         throws ServerCallFailedException {

      securityService.assertRights(loadGenericSecurityMarker(targetIdentifier), GrantAccess.class);

      /* load real ace and check access rights */
      Ace realACE = (Ace) dtoService.loadPoso(aceDto);
      if (null == realACE)
         throw new IllegalArgumentException("Could not load corresponding ACE"); //$NON-NLS-1$

      /* validation */
      validateGrantAccessRightsBasic(targetIdentifier, realACE, aceDto);

      /* move ace */
      aceMoved(realACE, index);

      /* create dto */
      return (AceDto) dtoService.createDto(realACE);
   }

   protected void aceMoved(Ace ace, int index) {
      /* get acl remove ace and insert ace */
      Acl acl = ace.getAcl();
      acl.removeACE(ace);
      acl.addAce(ace, index);
      securityService.merge(acl);
   }

   @SecurityChecked(argumentVerification = {
         @ArgumentVerification(name = "node", isDto = true, verify = @RightsVerification(rights = GrantAccess.class)) })
   @Override
   @Transactional(rollbackOn = { Exception.class })
   public AceDto addACE(@Named("node") AbstractNodeDto nodeDto) throws ServerCallFailedException {
      AbstractNode node = (AbstractNode) dtoService.loadPoso(nodeDto);
      if (null == node || !(node instanceof SecuredAbstractNode))
         throw new IllegalArgumentException(
               "Node is either null or not of type: " + SecuredAbstractNode.class.getName()); //$NON-NLS-1$

      SecuredAbstractNode securedNode = (SecuredAbstractNode) node;

      HierarchicalAcl acl = securedNode.getAcl();
      if (null == acl) {
         acl = new HierarchicalAcl();
         securedNode.setAcl(acl);

         securityService.persist(acl);
      }

      /* create ace */
      HierarchicalAce ace = securityService.createHierarchicalACE(node.getClass());
      acl.addAce(ace);
      securityService.persist(ace);

      /* merge node */
      treeDBService.merge(securedNode);

      return (AceDto) dtoService.createDto(ace);
   }

   @Override
   @Transactional(rollbackOn = { Exception.class })
   public AceDto addACE(GenericTargetIdentifier targetIdentifier) throws ServerCallFailedException {

      securityService.assertRights(loadGenericSecurityMarker(targetIdentifier), GrantAccess.class);

      Class<?> genericTargetMarker = loadGenericSecurityMarker(targetIdentifier);
      GenericSecurityTargetEntity target = securityService.loadGenericTarget(genericTargetMarker);
      if (null == target)
         throw new IllegalArgumentException("Could not load target entity"); //$NON-NLS-1$

      Acl acl = target.getAcl();
      if (null == acl) {
         acl = new Acl();
         target.setAcl(acl);

         securityService.persist(acl);
      }

      /* create ace */
      Ace ace = securityService.createACE(target);
      acl.addAce(ace);
      securityService.persist(ace);

      /* merge target */
      securityService.merge(target);

      /* return ace */
      return (AceDto) dtoService.createDto(ace);
   }

   @SuppressWarnings("unchecked")
   @SecurityChecked(argumentVerification = {
         @ArgumentVerification(name = "node", isDto = true, verify = @RightsVerification(rights = GrantAccess.class)) })
   @Transactional(rollbackOn = { Exception.class })
   public AceDto editACE(@Named("node") AbstractNodeDto nodeDto, AceDto aceDto) throws ServerCallFailedException {
      /* load real ace and check access rights */
      Ace realACE = (Ace) dtoService.loadPoso(aceDto);
      if (null == realACE)
         throw new IllegalArgumentException("Could not load corresponding ACE"); //$NON-NLS-1$

      validateGrantAccessRightsForEdit(nodeDto, realACE, aceDto);

      /* load node */
      SecuredAbstractNode securedNode = (SecuredAbstractNode) dtoService.loadPoso(nodeDto);

      /* copy data (this is done by hand for better security) */
      Collection<Securee> securees = securityService.getRegisteredSecureesForTarget(securedNode.getClass());
      editACE(realACE, aceDto, securees);

      /* return new ace */
      return (AceDto) dtoService.createDto(realACE);
   }

   @Transactional(rollbackOn = { Exception.class })
   @Override
   public AceDto editACE(GenericTargetIdentifier targetIdentifier, AceDto aceDto) throws ServerCallFailedException {
      securityService.assertRights(loadGenericSecurityMarker(targetIdentifier), GrantAccess.class);

      /* load real ace and check access rights */
      Ace realACE = (Ace) dtoService.loadPoso(aceDto);
      if (null == realACE)
         throw new IllegalArgumentException("Could not load corresponding ACE"); //$NON-NLS-1$

      validateGrantAccessRightsForEdit(targetIdentifier, realACE, aceDto);

      /* load node */
      Class<?> targetMarker = loadGenericSecurityMarker(targetIdentifier);

      /* copy data (this is done by hand for better security) */
      Collection<Securee> securees = securityService.getRegisteredSecureesForTarget(targetMarker);
      editACE(realACE, aceDto, securees);

      /* return new ace */
      return (AceDto) dtoService.createDto(realACE);
   }

   /**
    * Copies the parameters from the dto to the entity object.
    * 
    * @param realACE
    * @param aceDto
    * @param securees The securies registered for the ACL container
    *                 (SecurityTarget).
    * @throws ExpectedException
    */
   protected void editACE(Ace realACE, AceDto aceDto, Collection<Securee> securees) throws ExpectedException {
      /* folk */
      AbstractUserManagerNode newFolk = (AbstractUserManagerNode) dtoService.loadPoso(aceDto.getFolk());
      realACE.setFolk(newFolk);

      /* accesstype */
      realACE.setAccesstype((AccessType) dtoService.createPoso(aceDto.getAccesstype()));

      /* inheritance */
      if (realACE instanceof HierarchicalAce) {
         InheritanceType inheritancetype = (InheritanceType) dtoService
               .createPoso(((HierarchicalAceDto) aceDto).getInheritancetype());
         ((HierarchicalAce) realACE).setInheritancetype(inheritancetype);
      }

      /* actual rights */

      for (Securee securee : securees) {
         AceAccessMap accessMap = realACE.getAccessMap(securee.getSecureeId());
         AceAccessMapDtoDec dtoAccessMap = (AceAccessMapDtoDec) ((AceDtoDec) aceDto)
               .getAccessMap(securee.getSecureeId());

         /* clear access */
         accessMap.clearAccess();

         /* copy rights */
         for (Right right : securee.getRights())
            if (dtoAccessMap.hasAccessRight(right.getBitField()))
               accessMap.addAccessRight(right);
      }

      /* merge ace */
      securityService.merge(realACE);
   }

   @SecurityChecked(argumentVerification = {
         @ArgumentVerification(name = "node", isDto = true, verify = @RightsVerification(rights = GrantAccess.class)) })
   @Transactional(rollbackOn = { Exception.class })
   @Override
   public void removeACEs(@Named("node") AbstractNodeDto nodeDto, List<AceDto> aceDtos)
         throws ServerCallFailedException {
      for (AceDto aceDto : aceDtos)
         removeACE(nodeDto, aceDto);
   }

   @SecurityChecked(argumentVerification = {
         @ArgumentVerification(name = "node", isDto = true, verify = @RightsVerification(rights = GrantAccess.class)) })
   protected void removeACE(@Named("node") AbstractNodeDto nodeDto, AceDto aceDto) throws ServerCallFailedException {
      /* load real ace and check access rights */
      Ace realACE = (Ace) dtoService.loadPoso(aceDto);
      if (null == realACE)
         throw new IllegalArgumentException("Could not load corresponding ACE"); //$NON-NLS-1$

      validateGrantAccessRightsForRemove(nodeDto, realACE, aceDto);

      /* remove ace */
      securityService.remove(realACE);
   }

   @Override
   @Transactional(rollbackOn = { Exception.class })
   public void removeACEs(GenericTargetIdentifier targetIdentifier, List<AceDto> aceDtos)
         throws ServerCallFailedException {

      securityService.assertRights(loadGenericSecurityMarker(targetIdentifier), GrantAccess.class);

      for (AceDto aceDto : aceDtos)
         removeACE(targetIdentifier, aceDto);
   }

   protected void removeACE(GenericTargetIdentifier targetIdentifier, AceDto aceDto) throws ServerCallFailedException {
      /* load real ace and check access rights */
      Ace realACE = (Ace) dtoService.loadPoso(aceDto);
      if (null == realACE)
         throw new IllegalArgumentException("Could not load corresponding ACE"); //$NON-NLS-1$

      validateGrantAccessRightsForRemove(targetIdentifier, realACE, aceDto);

      /* remove ace */
      securityService.remove(realACE);
   }

   private void validateGrantAccessRightsBasic(GenericTargetIdentifier targetIdentifier, Ace realACE, AceDto aceDto)
         throws ViolatedSecurityExceptionDto {
      Class<?> genericTargetMarker = loadGenericSecurityMarker(targetIdentifier);
      GenericSecurityTargetEntity target = securityService.loadGenericTarget(genericTargetMarker);

      /* make sure ace's are not hierarchical */
      if ((realACE instanceof HierarchicalAce) || (aceDto instanceof HierarchicalAceDto))
         throw new IllegalArgumentException("ACEs are hierarchical"); //$NON-NLS-1$

      /* validate ace position */
      validateACEAtCorrectACL(target, realACE);
   }

   private void validateGrantAccessRightsBasic(AbstractNodeDto nodeDto, Ace realACE, AceDto aceDto)
         throws ViolatedSecurityExceptionDto {
      /* get node that belongs to ace */
      AbstractNode node = (AbstractNode) dtoService.loadPoso(nodeDto);
      if (!(node instanceof SecuredAbstractNode))
         throw new IllegalArgumentException("The submitted node is not of type " + SecuredAbstractNode.class.getName()); //$NON-NLS-1$

      SecuredAbstractNode securedNode = (SecuredAbstractNode) node;

      /* make sure ace's are both hierarchical */
      if (!(realACE instanceof HierarchicalAce) || !(aceDto instanceof HierarchicalAceDto))
         throw new IllegalArgumentException("ACEs are not hierarchical"); //$NON-NLS-1$

      /* validate ace position */
      validateACEAtCorrectACL(securedNode, realACE);
   }

   private void validateGrantAccessRightsForEdit(AbstractNodeDto nodeDto, Ace realACE, AceDto aceDto)
         throws ViolatedSecurityExceptionDto {
      /* basic validation */
      validateGrantAccessRightsBasic(nodeDto, realACE, aceDto);

      /* get node that belongs to ace */
      AbstractNode node = (AbstractNode) dtoService.loadPoso(nodeDto);
      SecuredAbstractNode securedNode = (SecuredAbstractNode) node;

      /* test that user has all necessary rights */
      Collection<Securee> securees = securityService.getRegisteredSecureesForTarget(securedNode.getClass());
      validateThatUserHasRightsForGrantRevoke(securedNode, realACE, aceDto, securees);
   }

   private void validateGrantAccessRightsForEdit(GenericTargetIdentifier targetIdentifier, Ace realACE, AceDto aceDto)
         throws ViolatedSecurityExceptionDto {
      /* basic validation */
      validateGrantAccessRightsBasic(targetIdentifier, realACE, aceDto);

      /* get target that belongs to ace */
      Class<?> targetMarker = loadGenericSecurityMarker(targetIdentifier);
      GenericSecurityTargetEntity target = securityService.loadGenericTarget(targetMarker);

      Collection<Securee> securees = securityService.getRegisteredSecureesForTarget(targetMarker);

      /* test that user has all necessary rights */
      validateThatUserHasRightsForGrantRevoke(target, realACE, aceDto, securees);
   }

   /**
    * Makes sure that the user has all the rights s/he wants to grant/revoke
    * 
    * @param target
    * @param realACE
    * @param aceDto
    * @param securees
    * @throws ViolatedSecurityExceptionDto
    */
   private void validateThatUserHasRightsForGrantRevoke(SecurityTarget target, Ace realACE, AceDto aceDto,
         Collection<Securee> securees) throws ViolatedSecurityExceptionDto {
      /* load folks */
      AbstractUserManagerNode currentFolk = realACE.getFolk();
      AbstractUserManagerNode newFolk = (AbstractUserManagerNode) dtoService.loadPoso(aceDto.getFolk());
      boolean folksMatch = currentFolk == newFolk;

      /* make sure user has rights he wants to grant or revoke */

      for (Securee securee : securees) {
         AceAccessMap accessMap = realACE.getAccessMap(securee.getSecureeId());
         AceAccessMapDtoDec dtoAccessMap = (AceAccessMapDtoDec) ((AceDtoDec) aceDto)
               .getAccessMap(securee.getSecureeId());

         for (Right right : securee.getRights()) {
            boolean hasRight = accessMap.hasAccessRight(right.getBitField());
            boolean hasRightDTO = dtoAccessMap.hasAccessRight(right.getBitField());

            /* do they differ */
            if (hasRight ^ hasRightDTO || !folksMatch && hasRightDTO) {
               /* make sure user has necessary rights */
               if (!securityService.checkRights(target, securee.getClass(), right.getClass()))
                  throw new ViolatedSecurityExceptionDto();
            }
         }
      }
   }

   private void validateGrantAccessRightsForRemove(AbstractNodeDto nodeDto, Ace realACE, AceDto aceDto)
         throws ViolatedSecurityExceptionDto {
      /* basic validation */
      validateGrantAccessRightsBasic(nodeDto, realACE, aceDto);

      /* get node that belongs to ace */
      AbstractNode node = (AbstractNode) dtoService.loadPoso(nodeDto);
      SecuredAbstractNode securedNode = (SecuredAbstractNode) node;

      /* make sure user has rights he wants to grant or revoke */
      Collection<Securee> securees = securityService.getRegisteredSecureesForTarget(securedNode.getClass());
      validateThatUserHasRightsForRemove(securedNode, realACE, aceDto, securees);
   }

   private void validateGrantAccessRightsForRemove(GenericTargetIdentifier targetIdentifier, Ace realACE, AceDto aceDto)
         throws ViolatedSecurityExceptionDto {
      /* basic validation */
      validateGrantAccessRightsBasic(targetIdentifier, realACE, aceDto);

      /* get node that belongs to ace */
      Class<?> targetMarker = loadGenericSecurityMarker(targetIdentifier);
      GenericSecurityTargetEntity target = securityService.loadGenericTarget(targetMarker);
      Collection<Securee> securees = securityService.getRegisteredSecureesForTarget(targetMarker);

      /* make sure user has rights he wants to grant or revoke */
      validateThatUserHasRightsForRemove(target, realACE, aceDto, securees);
   }

   /**
    * Validates that the user has all the rights s/he "revokes" by deleting this
    * ACE.
    * 
    * @param target
    * @param realACE
    * @param aceDto
    * @param securees
    * @throws ViolatedSecurityExceptionDto
    */
   private void validateThatUserHasRightsForRemove(SecurityTarget target, Ace realACE, AceDto aceDto,
         Collection<Securee> securees) throws ViolatedSecurityExceptionDto {

      for (Securee securee : securees) {
         AceAccessMap accessMap = realACE.getAccessMap(securee.getSecureeId());

         for (Right right : securee.getRights()) {
            boolean hasRight = accessMap.hasAccessRight(right.getBitField());

            /* does the user have the right that s/he wants to revoke */
            if (hasRight) {
               /* make sure user has necessary rights */
               if (!securityService.checkRights(target, securee.getClass(), right.getClass()))
                  throw new ViolatedSecurityExceptionDto();
            }
         }
      }
   }

   /**
    * Tests that ACE is on the ACL it claims to be
    * 
    * @param target
    * @param realACE
    */
   private void validateACEAtCorrectACL(SecurityTarget target, Ace realACE) {
      Acl acl = realACE.getAcl();

      /* make sure, that acl and the node's acl match */
      if (!acl.equals(target.getAcl()))
         throw new IllegalArgumentException("The two ACLs do not match."); //$NON-NLS-1$
   }

   private Class<? extends GenericSecurityTargetMarker> loadGenericSecurityMarker(
         GenericTargetIdentifier targetIdentifier) {
      if (!targetIdentifier.getClass().isAnnotationPresent(GenericTargetIdentifierMapper.class))
         throw new IllegalArgumentException("Could not find " + GenericTargetIdentifierMapper.class.getName() //$NON-NLS-1$
               + " annotation on " + targetIdentifier.getClass().getName()); //$NON-NLS-1$

      GenericTargetIdentifierMapper mappingAnnotation = targetIdentifier.getClass()
            .getAnnotation(GenericTargetIdentifierMapper.class);
      if (null == mappingAnnotation.value())
         throw new IllegalStateException(
               targetIdentifier.getClass().getName() + " does not return a valid GenericSecurityTargetMarker"); //$NON-NLS-1$

      return mappingAnnotation.value();
   }

}
