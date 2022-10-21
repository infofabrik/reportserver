package net.datenwerke.rs.base.service.dbhelper.querybuilder.queryconditions;

public class OrQueryCondition extends BooleanFunctionQueryCondition {

   public OrQueryCondition(QryCondition first, QryCondition second) {
      super(first, second);
   }

   @Override
   public String getBooleanFunctionName() {
      return "OR";
   }
}
