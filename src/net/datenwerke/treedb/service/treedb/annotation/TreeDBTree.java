package net.datenwerke.treedb.service.treedb.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import net.datenwerke.treedb.service.treedb.AbstractNode;
import net.datenwerke.treedb.service.treedb.TreeDBManager;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface TreeDBTree {
	
	@SuppressWarnings("unchecked")
	Class<? extends AbstractNode>[] rootTypes();
	boolean multipleRoots() default false;
	
	Class<? extends TreeDBManager> manager();
}
