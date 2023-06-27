package net.datenwerke.rs.dropbox.service.dropbox.action;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import net.datenwerke.rs.dropbox.service.dropbox.definitions.DropboxDatasink;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ScheduleAsDropboxFileAction.class)
public abstract class ScheduleAsDropboxFileAction_ extends net.datenwerke.scheduler.service.scheduler.entities.AbstractAction_ {

	public static volatile SingularAttribute<ScheduleAsDropboxFileAction, String> folder;
	public static volatile SingularAttribute<ScheduleAsDropboxFileAction, String> name;
	public static volatile SingularAttribute<ScheduleAsDropboxFileAction, Boolean> compressed;
	public static volatile SingularAttribute<ScheduleAsDropboxFileAction, DropboxDatasink> dropboxDatasink;

}

