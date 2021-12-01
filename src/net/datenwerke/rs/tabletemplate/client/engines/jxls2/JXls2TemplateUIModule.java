package net.datenwerke.rs.tabletemplate.client.engines.jxls2;

import com.google.gwt.inject.client.AbstractGinModule;

public class JXls2TemplateUIModule extends AbstractGinModule {

    public static final String TEMPLATE_TYPE = "jxls2:template";

    @Override
    protected void configure() {
        bind(JXls2TemplateUIStartup.class).asEagerSingleton();
    }

}
