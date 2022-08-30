package net.datenwerke.rs.onedrive.service.onedrive.action;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import net.datenwerke.rs.onedrive.service.onedrive.definitions.OneDriveDatasink;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ScheduleAsOneDriveFileAction.class)
public abstract class ScheduleAsOneDriveFileAction_ extends net.datenwerke.scheduler.service.scheduler.entities.AbstractAction_ {

	public static volatile SingularAttribute<ScheduleAsOneDriveFileAction, OneDriveDatasink> oneDriveDatasink;
	public static volatile SingularAttribute<ScheduleAsOneDriveFileAction, String> folder;
	public static volatile SingularAttribute<ScheduleAsOneDriveFileAction, String> name;
	public static volatile SingularAttribute<ScheduleAsOneDriveFileAction, Boolean> compressed;

}

