package net.datenwerke.security.service.security;

/**
 * A security target with hierarchical ACL's.
 * 
 */
public interface HierarchicalSecurityTarget extends SecurityTarget {

   public HierarchicalSecurityTarget getParentTarget();
}
