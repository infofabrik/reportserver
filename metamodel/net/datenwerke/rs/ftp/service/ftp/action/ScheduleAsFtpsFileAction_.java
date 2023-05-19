package net.datenwerke.rs.ftp.service.ftp.action;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import net.datenwerke.rs.ftp.service.ftp.definitions.FtpsDatasink;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ScheduleAsFtpsFileAction.class)
public abstract class ScheduleAsFtpsFileAction_ extends net.datenwerke.scheduler.service.scheduler.entities.AbstractAction_ {

	public static volatile SingularAttribute<ScheduleAsFtpsFileAction, String> folder;
	public static volatile SingularAttribute<ScheduleAsFtpsFileAction, String> name;
	public static volatile SingularAttribute<ScheduleAsFtpsFileAction, Boolean> compressed;
	public static volatile SingularAttribute<ScheduleAsFtpsFileAction, FtpsDatasink> ftpsDatasink;

}

