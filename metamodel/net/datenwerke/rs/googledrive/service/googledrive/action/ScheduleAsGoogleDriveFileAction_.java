package net.datenwerke.rs.googledrive.service.googledrive.action;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import net.datenwerke.rs.googledrive.service.googledrive.definitions.GoogleDriveDatasink;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ScheduleAsGoogleDriveFileAction.class)
public abstract class ScheduleAsGoogleDriveFileAction_ extends net.datenwerke.scheduler.service.scheduler.entities.AbstractAction_ {

	public static volatile SingularAttribute<ScheduleAsGoogleDriveFileAction, String> folder;
	public static volatile SingularAttribute<ScheduleAsGoogleDriveFileAction, String> name;
	public static volatile SingularAttribute<ScheduleAsGoogleDriveFileAction, Boolean> compressed;
	public static volatile SingularAttribute<ScheduleAsGoogleDriveFileAction, GoogleDriveDatasink> googleDriveDatasink;

}

