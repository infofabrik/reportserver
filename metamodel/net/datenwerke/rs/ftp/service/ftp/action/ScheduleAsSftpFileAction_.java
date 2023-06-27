package net.datenwerke.rs.ftp.service.ftp.action;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import net.datenwerke.rs.ftp.service.ftp.definitions.SftpDatasink;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ScheduleAsSftpFileAction.class)
public abstract class ScheduleAsSftpFileAction_ extends net.datenwerke.scheduler.service.scheduler.entities.AbstractAction_ {

	public static volatile SingularAttribute<ScheduleAsSftpFileAction, SftpDatasink> sftpDatasink;
	public static volatile SingularAttribute<ScheduleAsSftpFileAction, String> folder;
	public static volatile SingularAttribute<ScheduleAsSftpFileAction, String> name;
	public static volatile SingularAttribute<ScheduleAsSftpFileAction, Boolean> compressed;

}

