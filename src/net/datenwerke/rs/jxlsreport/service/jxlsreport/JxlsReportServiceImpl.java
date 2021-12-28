package net.datenwerke.rs.jxlsreport.service.jxlsreport;

import javax.inject.Singleton;
import javax.persistence.EntityManager;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.rs.jxlsreport.service.jxlsreport.entities.JxlsReportFile;
import net.datenwerke.security.service.eventlogger.annotations.FireRemoveEntityEvents;

@Singleton
public class JxlsReportServiceImpl implements JxlsReportService {

	private final Provider<EntityManager> entityManagerProvider;
	
	@Inject
	public JxlsReportServiceImpl(
		Provider<EntityManager> entityManagerProvider
		) {
		this.entityManagerProvider = entityManagerProvider;
	}

	@Override
	@FireRemoveEntityEvents
	public void remove(JxlsReportFile file) {
		EntityManager em = entityManagerProvider.get();
		file = em.find(file.getClass(), file.getId());
		if(null != file)
			em.remove(file);
	}

}
