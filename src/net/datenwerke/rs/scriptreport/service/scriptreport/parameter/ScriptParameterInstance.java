package net.datenwerke.rs.scriptreport.service.scriptreport.parameter;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.el.ELContext;
import javax.el.ExpressionFactory;
import javax.el.VariableMapper;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Type;
import org.hibernate.envers.Audited;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import net.datenwerke.dtoservices.dtogenerator.annotations.ExposeToClient;
import net.datenwerke.dtoservices.dtogenerator.annotations.GenerateDto;
import net.datenwerke.rs.core.service.parameters.entities.ParameterDefinitionForJuel;
import net.datenwerke.rs.core.service.parameters.entities.ParameterInstance;
import net.datenwerke.rs.core.service.parameters.entities.ParameterInstanceForJuel;
import net.datenwerke.rs.core.service.reportmanager.parameters.ParameterSet;
import net.datenwerke.rs.core.service.reportmanager.parameters.ParameterValue;
import net.datenwerke.rs.core.service.reportmanager.parameters.ParameterValueImpl;
import net.datenwerke.security.service.usermanager.entities.User;

/**
 * 
 *
 */
@Entity
@Table(name = "SCRIPT_PARAM_INST")
@Audited
@GenerateDto(dtoPackage = "net.datenwerke.rs.scriptreport.client.scriptreport.parameters.dto")
public class ScriptParameterInstance extends ParameterInstance<ScriptParameterDefinition> {

   /**
    * 
    */
   private static final long serialVersionUID = -2332220199796956927L;

   @ExposeToClient(allowArbitraryLobSize = true, disableHtmlEncode = true)
   @Lob
   @Type(type = "net.datenwerke.rs.utils.hibernate.RsClobType")
   private String value;

   @Override
   public String getSelectedValue(User user) {
      return getValue();
   }

   @Override
   public String getDefaultValue(User user, ParameterSet parameterSet) {
      return ((ScriptParameterDefinition) getDefinition()).getDefaultValue();
   }

   @Override
   protected Class<?> getType() {
      return String.class;
   }

   public String getValue() {
      return value;
   }

   public void setValue(String value) {
      this.value = value;
   }

   @Transient
   @Override
   public void configureParameterMap(User user, Map<String, ParameterValue> parameterMap, ParameterSet parameterSet) {
      Object value;

      if (getDefinition().isEditable() && !isStillDefault())
         value = this.getSelectedValue(user);
      else
         value = this.getDefaultValue(user, parameterSet);

      parameterMap.put(getKey(), new ParameterValueImpl(getKey(), value, getType()));

      try {
         final ObjectNode jsonObject = new ObjectMapper().readValue((String) value, ObjectNode.class);
         Iterator<Map.Entry<String, JsonNode>> it = jsonObject.fields();
         while (it.hasNext()) {
            Map.Entry<String, JsonNode> nextEntry = it.next();
            String key = nextEntry.getKey();
            JsonNode next = nextEntry.getValue();
            Object object = null;
            if (next.isArray()) {
               object = toList((ArrayNode) next);
               value = object;
            } else {
               object = next.asText();
            }

            if (null != object)
               parameterMap.put(getKey() + "_" + key,
                     new ParameterValueImpl(getKey() + "_" + key, value, object.getClass()));
            else
               parameterMap.put(getKey() + "_" + key,
                     new ParameterValueImpl(getKey() + "_" + key, value, Object.class));
         }
      } catch (Exception ex) {
      }
   }

   @Override
   public void configureEL(User user, ExpressionFactory factory, ELContext context, ParameterSet parameterSet) {
      VariableMapper vm = context.getVariableMapper();

      /* store the objects main value */
      String value;
      if (getDefinition().isEditable() && !isStillDefault())
         value = this.getSelectedValue(user);
      else
         value = this.getDefaultValue(user, parameterSet);

      /* provide access to the actual object */
      ParameterInstanceForJuel instanceForJuel = createParameterInstanceForJuel();
      configureParameterInstanceForJuel(instanceForJuel, value);
      vm.setVariable("_" + getKey(), factory.createValueExpression(instanceForJuel, ParameterInstanceForJuel.class)); //$NON-NLS-1$

      ParameterDefinitionForJuel definitionForJuel = getDefinition().createParameterDefinitionForJuel();
      getDefinition().configureParameterDefinitionForJuel(definitionForJuel);
      vm.setVariable("__" + getKey(), //$NON-NLS-1$
            factory.createValueExpression(definitionForJuel, ParameterDefinitionForJuel.class));

      if (null != value)
         vm.setVariable(getKey(), factory.createValueExpression(value, String.class));
      else
         vm.setVariable(getKey(), factory.createValueExpression(null, Object.class));

      try {
         final ObjectNode jsonObject = new ObjectMapper().readValue((String) value, ObjectNode.class);
         Iterator<Map.Entry<String, JsonNode>> it = jsonObject.fields();
         while (it.hasNext()) {
            Map.Entry<String, JsonNode> nextEntry = it.next();
            String key = nextEntry.getKey();
            JsonNode next = nextEntry.getValue();
            Object object = null;
            if (next.isArray()) {
               object = toList((ArrayNode) next);
            } else {
               object = next.asText();
            }

            if (null != value)
               vm.setVariable(getKey() + "_" + key, factory.createValueExpression(object, object.getClass()));
            else
               vm.setVariable(getKey() + "_" + key, factory.createValueExpression(null, Object.class));
         }
      } catch (Exception ex) {
      }
   }

   private List<String> toList(ArrayNode jsonArray) {
      List<String> list = new ArrayList<>();
      Iterator<JsonNode> it = jsonArray.elements();
      while (it.hasNext()) {
         JsonNode next = it.next();
         list.add(next.asText());
      }
      return list;
   }

}
