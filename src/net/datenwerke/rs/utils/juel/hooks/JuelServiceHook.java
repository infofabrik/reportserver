package net.datenwerke.rs.utils.juel.hooks;

import java.util.Properties;

import javax.el.ELContext;
import javax.el.ExpressionFactory;

import net.datenwerke.hookhandler.shared.hookhandler.interfaces.Hook;
import de.odysseus.el.util.SimpleContext;

public interface JuelServiceHook extends Hook {

	void adaptContext(SimpleContext context);

	void propertiesForFactoryCreation(Properties properties);

	void adaptFactory(ExpressionFactory factory);

	void aboutToEvaluate(ExpressionFactory factory, ELContext context,
			String template);

}
