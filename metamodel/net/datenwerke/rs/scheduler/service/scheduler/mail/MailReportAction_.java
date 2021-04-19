package net.datenwerke.rs.scheduler.service.scheduler.mail;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(MailReportAction.class)
public abstract class MailReportAction_ extends net.datenwerke.scheduler.service.scheduler.entities.AbstractAction_ {

	public static volatile SingularAttribute<MailReportAction, String> subject;
	public static volatile SingularAttribute<MailReportAction, Boolean> compressed;
	public static volatile SingularAttribute<MailReportAction, String> message;

}

