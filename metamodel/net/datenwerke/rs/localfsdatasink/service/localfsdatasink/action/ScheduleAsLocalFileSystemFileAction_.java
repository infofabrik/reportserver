package net.datenwerke.rs.localfsdatasink.service.localfsdatasink.action;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import net.datenwerke.rs.localfsdatasink.service.localfsdatasink.definitions.LocalFileSystemDatasink;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ScheduleAsLocalFileSystemFileAction.class)
public abstract class ScheduleAsLocalFileSystemFileAction_ extends net.datenwerke.scheduler.service.scheduler.entities.AbstractAction_ {

	public static volatile SingularAttribute<ScheduleAsLocalFileSystemFileAction, String> folder;
	public static volatile SingularAttribute<ScheduleAsLocalFileSystemFileAction, String> name;
	public static volatile SingularAttribute<ScheduleAsLocalFileSystemFileAction, LocalFileSystemDatasink> localFileSystemDatasink;
	public static volatile SingularAttribute<ScheduleAsLocalFileSystemFileAction, Boolean> compressed;

}

