package net.datenwerke.rs.ftp.service.ftp.action;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import net.datenwerke.rs.ftp.service.ftp.definitions.FtpDatasink;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ScheduleAsFtpFileAction.class)
public abstract class ScheduleAsFtpFileAction_ extends net.datenwerke.scheduler.service.scheduler.entities.AbstractAction_ {

	public static volatile SingularAttribute<ScheduleAsFtpFileAction, String> folder;
	public static volatile SingularAttribute<ScheduleAsFtpFileAction, String> name;
	public static volatile SingularAttribute<ScheduleAsFtpFileAction, Boolean> compressed;
	public static volatile SingularAttribute<ScheduleAsFtpFileAction, FtpDatasink> ftpDatasink;

}

