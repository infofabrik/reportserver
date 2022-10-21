package net.datenwerke.rs.condition.client.condition.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import net.datenwerke.rs.condition.client.condition.Condition;

public class SimpleCondition implements Serializable, Condition {

   /**
    * 
    */
   private static final long serialVersionUID = 1L;

   private String key;
   private boolean hasExpression;

   private String name;
   private String description;
   private List<String> replacements = new ArrayList<>();

   public String getKey() {
      return key;
   }

   public void setKey(String key) {
      this.key = key;
   }

   public boolean hasExpression() {
      return hasExpression;
   }

   public void setHasExpression(boolean hasExpression) {
      this.hasExpression = hasExpression;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public String getDescription() {
      return description;
   }

   public void setDescription(String description) {
      this.description = description;
   }

   public List<String> getReplacements() {
      return replacements;
   }

   public void setReplacements(List<String> replacements) {
      this.replacements = replacements;
   }

   public void addReplacement(String replacement) {
      if (null == replacements)
         replacements = new ArrayList<>();
      replacements.add(replacement);
   }

}
