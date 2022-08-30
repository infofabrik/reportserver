package net.datenwerke.rs.amazons3.service.amazons3.action;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import net.datenwerke.rs.amazons3.service.amazons3.definitions.AmazonS3Datasink;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ScheduleAsAmazonS3FileAction.class)
public abstract class ScheduleAsAmazonS3FileAction_ extends net.datenwerke.scheduler.service.scheduler.entities.AbstractAction_ {

	public static volatile SingularAttribute<ScheduleAsAmazonS3FileAction, String> folder;
	public static volatile SingularAttribute<ScheduleAsAmazonS3FileAction, AmazonS3Datasink> amazonS3Datasink;
	public static volatile SingularAttribute<ScheduleAsAmazonS3FileAction, String> name;
	public static volatile SingularAttribute<ScheduleAsAmazonS3FileAction, Boolean> compressed;

}

