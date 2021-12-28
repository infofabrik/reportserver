package net.datenwerke.rs.condition.client.condition.dto.pa;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.PropertyAccess;

import net.datenwerke.rs.condition.client.condition.Condition;
import net.datenwerke.rs.condition.client.condition.dto.ScheduleConditionDto;

public interface ScheduleConditionDtoPA extends PropertyAccess<ScheduleConditionDto> {

   public static final ScheduleConditionDtoPA INSTANCE = GWT.create(ScheduleConditionDtoPA.class);

   /* Properties */
   public ValueProvider<ScheduleConditionDto, String> expression();

   public ValueProvider<ScheduleConditionDto, Condition> condition();

   @Path("condition.name")
   public ValueProvider<ScheduleConditionDto, String> name();

}
