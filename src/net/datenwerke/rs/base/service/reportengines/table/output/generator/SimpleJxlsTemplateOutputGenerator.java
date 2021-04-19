package net.datenwerke.rs.base.service.reportengines.table.output.generator;

import com.google.inject.ImplementedBy;

@ImplementedBy(DummyJxlsTemplateOutputGenerator.class)
public interface SimpleJxlsTemplateOutputGenerator extends TableOutputGenerator {
}
